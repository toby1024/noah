package cn.skio.oauth.service;

import cn.skio.oauth.entity.User;
import cn.skio.oauth.repository.UserRepository;
import cn.skio.oauth.security.JwtTokenUtil;
import cn.skio.oauth.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private JwtTokenUtil jwtTokenUtil;

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        return jwtTokenUtil.generateToken(JwtUserFactory.create(user));
    }

    public User register(String username, String rawPassword) {

        if(userRepository.findByUsername(username) != null) {
            return null;
        }
        User userToAdd = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
        return userRepository.save(userToAdd);
    }
}
