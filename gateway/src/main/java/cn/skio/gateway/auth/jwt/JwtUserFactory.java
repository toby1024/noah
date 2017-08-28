package cn.skio.gateway.auth.jwt;


import cn.skio.gateway.auth.entity.User;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUsername(),
                user.getPassword(),
                user.getUuid(),
                user.isLocked(),
                user.isExpired(),
                user.isEnabled());
    }
}
