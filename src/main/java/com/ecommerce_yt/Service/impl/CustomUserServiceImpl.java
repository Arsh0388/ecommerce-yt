package com.ecommerce_yt.Service.impl;

import com.ecommerce_yt.Model.Seller;
import com.ecommerce_yt.Model.User;
import com.ecommerce_yt.domain.USER_ROLE;
import com.ecommerce_yt.repository.SellerRepository;
import com.ecommerce_yt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl  implements UserDetailsService {
    private UserRepository userRepository;
    private SellerRepository sellerRepository;
    private final String SELLER_PREFIX = "SELLER";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        if (username.startsWith(SELLER_PREFIX)) {
            String actualUsername = username.substring(SELLER_PREFIX.length());
            Seller seller = sellerRepository.findByEmail(actualUsername);

            if (seller != null) {
                return buildUserDetails(seller.getEmail(),seller.getPassword(), seller.getRole());
            }
        }
        else {
            User user = userRepository.findByEmail(username);
            if (user != null) {
                return buildUserDetails(user.getEmail(),user.getPassword(), user.getRole());
            }
        }
        throw new UsernameNotFoundException("User or seller not found with email" +  username);
    }

    private UserDetails buildUserDetails(String email, String password, USER_ROLE role) {
        if (role == null){
            // default role is customer
            role = USER_ROLE.ROLE_CUSTOMER;

        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + role));
        return new org.springframework.security.core.userdetails
                .User(email, password, authorityList);
    }
}
