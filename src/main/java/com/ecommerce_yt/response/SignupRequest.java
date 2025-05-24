package com.ecommerce_yt.response;

import lombok.Data;
import lombok.Getter;

@Data
public class SignupRequest {
    private String email;
    private String fullName;
    private String otp;
}
