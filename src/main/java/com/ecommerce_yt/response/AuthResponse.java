package com.ecommerce_yt.response;

import com.ecommerce_yt.domain.USER_ROLE;
import lombok.*;
import org.springframework.web.bind.annotation.SessionAttributes;

@Data // for getter setter method access.
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public USER_ROLE getRole() {
        return role;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }
}
