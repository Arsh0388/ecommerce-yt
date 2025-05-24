package com.ecommerce_yt.controller;

import com.ecommerce_yt.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// this class will act as a controller.
@RestController
public class HomeController {

    @GetMapping("first-endpoint")
    public ApiResponse HomeControllerHandler() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Hello world");
        return apiResponse;
    }
}
