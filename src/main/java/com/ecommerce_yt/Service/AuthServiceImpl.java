package com.ecommerce_yt.Service;

import com.ecommerce_yt.Model.Cart;
import com.ecommerce_yt.Model.User;
import com.ecommerce_yt.Model.LoginVerification;
import com.ecommerce_yt.config.JWTProvider;
import com.ecommerce_yt.domain.USER_ROLE;
import com.ecommerce_yt.repository.CartRepository;
import com.ecommerce_yt.repository.UserRepository;
import com.ecommerce_yt.repository.VerificationCodeRepository;
import com.ecommerce_yt.response.SignupRequest;
import com.ecommerce_yt.utils.OtpUtil;
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
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;

    @Override
    public void sendLoginOtp(String email) throws Exception {
        // otp will be send to the user.
        String SIGNING_PREFIX = "signin_";

        if (email.startsWith(SIGNING_PREFIX)) {
            email = email.substring(SIGNING_PREFIX.length());

            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new Exception("user not exist with provided email");
            }
        }

        LoginVerification isExist = verificationCodeRepository.findByEmail(email);
        if (isExist!=null) {
            verificationCodeRepository.delete(isExist); // create new verification code
        }
        String otp = OtpUtil.generateOtp();
        LoginVerification loginVerification = new LoginVerification();
        loginVerification.setOtp(otp);
        loginVerification.setEmail(email);
        verificationCodeRepository.save(loginVerification);

        // send email to the user.
        String subject = "zosh bazaar login/signup otp";

        String text = "your login/signup otp is ";
        emailService.sendVerificationOtpEmail(email,otp,subject,text);
    }

    @Override
    public String createUser(SignupRequest req) throws Exception {

        LoginVerification loginVerification = verificationCodeRepository.findByEmail(req.getEmail());
        if (loginVerification == null || !loginVerification.getOtp().equals(req.getOtp())) {
            throw new Exception("wrong otp...");
        }
        // check if the  user already exists with the provided email
        User user = userRepository.findByEmail(req.getEmail());

        if (user == null) { // IT'S A NEW USER
            User createdUser = new User();
            createdUser.setEmail(req.getEmail());
            createdUser.setFullName(req.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER); // create a customer account for now
            createdUser.setMobile("");
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
