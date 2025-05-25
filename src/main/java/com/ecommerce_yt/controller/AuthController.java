package com.ecommerce_yt.controller;

import com.ecommerce_yt.Model.User;
import com.ecommerce_yt.Service.AuthService;
import com.ecommerce_yt.domain.USER_ROLE;
import com.ecommerce_yt.repository.UserRepository;
import com.ecommerce_yt.response.AuthResponse;
import com.ecommerce_yt.response.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    // with the spring security starter dependency available. we won't be able t create a new sign up without configuration
    // but we should be able to sign up and create a user.  without providing a jwt token.
    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req){
        //       User user = new User();
        //       user.setEmail(req.getEmail());
        //       user.setFullName(req.getFullName()); // this won't save it in our Database
        // with the above we get the below response
        //        {
        //            "id": null,
        //                "email": "ashok@ecommerce.com",
        //                "fullName": "ashok",
        //                "mobile": null,
        //                "role": "ROLE_CUSTOMER",
        //                "addresses": []
        //        }
        // User savedUser = userRepository.save(user);
        // after this when we save this in database we get this output. with a generated id
        //        {
        //            "id": 1,
        //                "email": "ashok@ecommerce.com",
        //                "fullName": "ashok",
        //                "mobile": null,
        //                "role": "ROLE_CUSTOMER",
        //                "addresses": []
        //        }
        String jwt = authService.createUser(req);
        AuthResponse res = new AuthResponse();

        res.setJwt(jwt);
        res.setMessage("register success");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);


       return ResponseEntity.ok(res);

    }
}
