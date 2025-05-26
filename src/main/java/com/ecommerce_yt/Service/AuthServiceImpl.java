package com.ecommerce_yt.Service;

import com.ecommerce_yt.Model.Cart;
import com.ecommerce_yt.Model.User;
import com.ecommerce_yt.config.JWTProvider;
import com.ecommerce_yt.domain.USER_ROLE;
import com.ecommerce_yt.repository.CartRepository;
import com.ecommerce_yt.repository.UserRepository;
import com.ecommerce_yt.response.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JWTProvider jwtProvider;
    @Override
    public String createUser(SignupRequest req) {

        // check if the  user already exists with the provided email
        User user = userRepository.findByEmail(req.getEmail());

        if (user == null) { // IT'S A NEW USER
            User createdUser = new User();
            createdUser.setEmail(req.getEmail());
            createdUser.setFullName(req.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER); // create a customer account for now
            createdUser.setMobile("5877784938");
            createdUser.setPassword(passwordEncoder.encode(req.getOtp()));
            // save user in database
            user = userRepository.save(createdUser);
            // initialize the cart for the customer
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart); // An empty cart is created

            // we can create cart and watchlist here
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication  authentication = new UsernamePasswordAuthenticationToken(req.getEmail(),null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateJWT(authentication); // this will generate new jwt token using our class
    }
}
