package cn.skio.gateway.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String uuid;
    private String username;
    private String password;
    private String email;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private Date lastPasswordResetDate;
    private boolean locked = false;
    private boolean expired = false;
    private boolean enabled = true;

    public boolean isActive(){
        return this.isEnabled() && !this.isLocked() && !this.isExpired();
    }

}
