package com.ecommerce_yt.Service;

import com.ecommerce_yt.response.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    void sendLoginOtp(String email) throws Exception;
    String createUser(SignupRequest signupRequest) throws Exception;
}
