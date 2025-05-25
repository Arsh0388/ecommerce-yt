package com.ecommerce_yt.Service;

import com.ecommerce_yt.response.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    String createUser(SignupRequest signupRequest);
}
