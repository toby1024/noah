package cn.skio.gateway.auth.service;


import cn.skio.gateway.auth.entity.User;
import cn.skio.gateway.auth.jwt.JwtTokenUtil;
import cn.skio.gateway.auth.jwt.JwtUser;
import cn.skio.gateway.auth.jwt.JwtUserFactory;
import cn.skio.gateway.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {

    private static final String USER_CATCH_PREFIX = "user_";
    private static final String TOKEN_CATCH_PREFIX = "token_";

    @Value("${redis.catch_time_out_days}")
    private int catch_time_out;

    @Autowired
    private UserRepository userRepository;

    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String login(String username, String password) {
        ValueOperations<String, JwtUser> jwtUserCatch = redisTemplate.opsForValue();
        ValueOperations<String, String> tokenCatch = redisTemplate.opsForValue();

        JwtUser jwtUser = null;
        String token = null;

        if(redisTemplate.hasKey(USER_CATCH_PREFIX + username) && (jwtUser = jwtUserCatch.get(USER_CATCH_PREFIX + username)) != null){
            token = tokenCatch.get(TOKEN_CATCH_PREFIX + jwtUser.getUuid());
            if(token == null){
                token = jwtTokenUtil.generateToken(jwtUser);
                tokenCatch.set(TOKEN_CATCH_PREFIX + jwtUser.getUuid(), token, catch_time_out, TimeUnit.DAYS);
            }
        } else {
            User user = userRepository.findByUsername(username);
            jwtUser = JwtUserFactory.create(user);
            jwtUserCatch.set(USER_CATCH_PREFIX + username, jwtUser, catch_time_out, TimeUnit.DAYS);

            token = jwtTokenUtil.generateToken(jwtUser);
            tokenCatch.set(TOKEN_CATCH_PREFIX + jwtUser.getUuid(), token, catch_time_out, TimeUnit.DAYS);
        }

        if(jwtUser != null && new BCryptPasswordEncoder().matches(password, jwtUser.getPassword()) && jwtUser.isActive()) {
            return token;
        }else{
            return null;
        }
    }

    public String register(String username, String rawPassword) {

        if(userRepository.findByUsername(username) != null) {
            return null;
        }
        User userToAdd = new User();
        userToAdd.setUsername(username);
        userToAdd.setPassword(new BCryptPasswordEncoder().encode(rawPassword));
        userToAdd.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
        User userAdded = userRepository.save(userToAdd);

        ValueOperations<String, JwtUser> jwtUserCatch = redisTemplate.opsForValue();
        JwtUser jwtUser = JwtUserFactory.create(userAdded);
        jwtUserCatch.set(USER_CATCH_PREFIX + username, jwtUser, catch_time_out, TimeUnit.DAYS);

        String token = jwtTokenUtil.generateToken(jwtUser);
        ValueOperations<String, String> tokenCatch = redisTemplate.opsForValue();
        tokenCatch.set(TOKEN_CATCH_PREFIX + jwtUser.getUuid(), token, catch_time_out, TimeUnit.DAYS);

        return token;
    }

    public List<User> activeUsers(int pageNo, int pageSize) {
        return userRepository.findAllByLockedAndAndEnabledAndAndExpired(false, true, false,
                new PageRequest(pageNo, pageSize));
    }
}
