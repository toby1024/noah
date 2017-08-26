package cn.skio.oauth.security;

import cn.skio.oauth.entity.User;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUuid(),
                user.getUsername(),
                user.getPassword(),
                user.isLocked(),
                user.isExpired(),
                user.isEnabled());
    }
}
