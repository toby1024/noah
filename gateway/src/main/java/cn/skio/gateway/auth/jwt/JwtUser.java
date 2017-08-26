package cn.skio.gateway.auth.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private String username;
    private String password;
    private String uuid;
    private boolean locked;
    private boolean expired;
    private boolean enabled;

    public JwtUser(String uuid, String username, String password, boolean locked, boolean expired, boolean enabled) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.locked = locked;
        this.expired = expired;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return expired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
