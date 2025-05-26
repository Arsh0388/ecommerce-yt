package com.ecommerce_yt.repository;

import com.ecommerce_yt.Model.Seller;
import com.ecommerce_yt.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
    Seller findByEmail(String email);
}
