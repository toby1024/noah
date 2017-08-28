package cn.skio.gateway.auth.service;


import cn.skio.gateway.auth.entity.User;
import cn.skio.gateway.auth.jwt.JwtTokenUtil;
import cn.skio.gateway.auth.jwt.JwtUser;
import cn.skio.gateway.auth.jwt.JwtUserFactory;
import cn.skio.gateway.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        ValueOperations<String, JwtUser> valueOperations = redisTemplate.opsForValue();
        JwtUser jwtUer = null;

        if(redisTemplate.hasKey("user_" + username)){
            jwtUer = valueOperations.get("user_" + username);
        } else {
            User user = userRepository.findByUsername(username);
            jwtUer = JwtUserFactory.create(user);
            valueOperations.set("user_" + username, jwtUer, 10, TimeUnit.DAYS);
        }

        if(jwtUer != null && new BCryptPasswordEncoder().matches(password, jwtUer.getPassword()) && jwtUer.isActive()) {
            return jwtTokenUtil.generateToken(jwtUer);
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

        ValueOperations<String, JwtUser> valueOperations = redisTemplate.opsForValue();
        JwtUser jwtUer = JwtUserFactory.create(userAdded);
        valueOperations.set("user_" + username, jwtUer, 10, TimeUnit.DAYS);

        return jwtTokenUtil.generateToken(jwtUer);
    }

    public List<User> activeUsers(int pageNo, int pageSize) {
        return userRepository.findAllByLockedAndAndEnabledAndAndExpired(false, true, false,
                new PageRequest(pageNo, pageSize));
    }
}
