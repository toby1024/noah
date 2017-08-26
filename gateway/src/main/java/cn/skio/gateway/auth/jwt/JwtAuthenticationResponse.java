package cn.skio.gateway.auth.jwt;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
    private final String uuid;

    public JwtAuthenticationResponse(String token, String uuid) {
        this.token = token;
        this.uuid = uuid;
    }

    public String getToken() {
        return this.token;
    }

    public String getUuid() {
        return uuid;
    }
}
