package com.ecommerce_yt.repository;

import com.ecommerce_yt.Model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, String> {
    VerificationCode findByEmail(String email);
}
