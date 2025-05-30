package com.ecommerce_yt.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp_verification")
@Data
public class LoginVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String fullName;
    private String password; // Should be hashed if stored here temporarily
    private String otp;
    private LocalDateTime expiryTime;
}
