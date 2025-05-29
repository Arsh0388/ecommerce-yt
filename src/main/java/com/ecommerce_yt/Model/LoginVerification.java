package com.ecommerce_yt.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "login_verification")
@Data
public class LoginVerification {

    @Id
    private String email; // or another unique ID field

    private String password; // or any other fields you need
}
