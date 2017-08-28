package cn.skio.gateway.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class JwtUser implements UserDetails {

    private String username;
    private String password;
    private String uuid;
    private boolean locked;
    private boolean expired;
    private boolean enabled;

    protected JwtUser(){
        this.username = null;
        this.password = null;
        this.uuid = null;
        this.locked = true;
        this.expired = true;
        this.enabled = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
