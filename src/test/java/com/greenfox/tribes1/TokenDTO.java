package com.greenfox.tribes1;

import com.greenfox.tribes1.Security.Model.JwtToken;

import java.util.Date;

public class TokenDTO {
    JwtToken jwtToken;
    Date expiration;

    public TokenDTO() {
    }

    public TokenDTO(JwtToken jwtToken, Date expiration) {
        this.jwtToken = jwtToken;
        this.expiration = expiration;
    }

    public JwtToken getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}