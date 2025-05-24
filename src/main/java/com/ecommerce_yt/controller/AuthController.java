package com.ecommerce_yt.controller;

import com.ecommerce_yt.Model.User;
import com.ecommerce_yt.repository.UserRepository;
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

    @PostMapping("/signup")
    public ResponseEntity<User> createUserHandler(@RequestBody SignupRequest req){
       User user = new User();
       user.setEmail(req.getEmail());
       user.setFullName(req.getFullName()); // this won't save it in our Database
        // with the above we get the below response
        //        {
        //            "id": null,
        //                "email": "ashok@ecommerce.com",
        //                "fullName": "ashok",
        //                "mobile": null,
        //                "role": "ROLE_CUSTOMER",
        //                "addresses": []
        //        }
        User savedUser = userRepository.save(user);
        // after this when we save this in database we get this output. with a generated id
        //        {
        //            "id": 1,
        //                "email": "ashok@ecommerce.com",
        //                "fullName": "ashok",
        //                "mobile": null,
        //                "role": "ROLE_CUSTOMER",
        //                "addresses": []
        //        }
       return ResponseEntity.ok(savedUser);

    }
}
