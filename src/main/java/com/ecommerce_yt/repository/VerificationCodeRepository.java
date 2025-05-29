package com.ecommerce_yt.repository;

import com.ecommerce_yt.Model.LoginVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<LoginVerification, String> {
    LoginVerification findByEmail(String email);
}
