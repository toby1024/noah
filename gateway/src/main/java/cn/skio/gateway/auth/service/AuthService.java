package cn.skio.gateway.auth.service;


import cn.skio.gateway.auth.entity.User;
import cn.skio.gateway.auth.jwt.JwtTokenUtil;
import cn.skio.gateway.auth.jwt.JwtUserFactory;
import cn.skio.gateway.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        return jwtTokenUtil.generateToken(JwtUserFactory.create(user));
    }

    public String register(String username, String rawPassword) {

        if(userRepository.findByUsername(username) != null) {
            return null;
        }
        User userToAdd = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userToAdd.setUsername(username);
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
        User userAdded = userRepository.save(userToAdd);
        return jwtTokenUtil.generateToken(JwtUserFactory.create(userAdded));
    }

    public List<User> activeUsers(int pageNo, int pageSize) {
        return userRepository.findAllByLockedAndAndEnabledAndAndExpired(true, true, true, new PageRequest(pageNo, pageSize));
    }
}
