package com.ecommerce_yt.response;

import lombok.Data;
public class ApiResponse {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

}
