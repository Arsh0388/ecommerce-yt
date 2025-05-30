package com.ecommerce_yt.controller;

import com.ecommerce_yt.Model.LoginVerification;
import com.ecommerce_yt.Model.User;
import com.ecommerce_yt.Service.AuthService;
import com.ecommerce_yt.Service.AuthServiceImpl;
import com.ecommerce_yt.domain.USER_ROLE;
import com.ecommerce_yt.repository.UserRepository;
import com.ecommerce_yt.repository.VerificationCodeRepository;
import com.ecommerce_yt.response.ApiResponse;
import com.ecommerce_yt.response.AuthResponse;
import com.ecommerce_yt.response.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    // with the spring security starter dependency available. we won't be able t create a new sign up without configuration
    // but we should be able to sign up and create a user.  without providing a jwt token.
    private final UserRepository userRepository;
    private final AuthService authService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthServiceImpl authServiceImpl;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {
        // Fetch OTP verification record by email
        LoginVerification verification = verificationCodeRepository.findByEmail(req.getEmail());

        AuthResponse res = new AuthResponse();

        if (verification == null) {
            res.setMessage("OTP not sent or invalid email");
            return ResponseEntity.status(400).body(res);
        }
        // verify otp now
        if (!verification.getOtp().equals(req.getOtp())) {
            res.setMessage("OTP is Incorrect");
            return ResponseEntity.status(400).body(res);
        }
        // timer for otp.
        if (verification.getExpiryTime().isBefore(LocalDateTime.now())) {
            res.setMessage("OTP expired");
            return ResponseEntity.status(400).body(res);
        }
        String jwtToken = authServiceImpl.createUser(req); // this will create the user and return jwt token.
        // setup the jwt token
        res.setJwt(jwtToken);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginHandler(@RequestBody LoginVerification req) {

        User user = userRepository.findByEmail(req.getEmail());
        ApiResponse res = new ApiResponse();
        if (user == null) {
            res.setMessage("User not found");
            return ResponseEntity.status(404).body(res);
        }
        if (!user.getPassword().equals(req.getPassword())) {
            res.setMessage("Password Mismatch");
            return ResponseEntity.status(401).body(res);
        }

        // create the jwt session for the user .
        res.setMessage("Login successful");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/send_otp")
    public ResponseEntity<ApiResponse> sendOtpHandler(@RequestBody SignupRequest req) throws Exception {
        String otp = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);

        LoginVerification verification = verificationCodeRepository.findByEmail(req.getEmail());

        if (verification == null) {
            verification = new LoginVerification();
            verification.setEmail(req.getEmail());
        }

        verification.setFullName(req.getFullName());
        verification.setPassword(req.getPassword()); // hash this in real app
        verification.setOtp(otp);
        verification.setExpiryTime(expiry);

        verificationCodeRepository.save(verification);

        // send OTP via email
        authServiceImpl.sendLoginOtp(req.getEmail()); // already implemented.

        ApiResponse res = new ApiResponse();
        res.setMessage("OTP sent to " + req.getEmail());
        return ResponseEntity.ok(res);
    }



}


