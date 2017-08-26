package cn.skio.oauth.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    private Integer id;

    private String uuid;
    private String username;
    private String password;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private Date lastPasswordResetDate;
    private boolean locked;
    private boolean expired;
    private boolean enabled;

}
