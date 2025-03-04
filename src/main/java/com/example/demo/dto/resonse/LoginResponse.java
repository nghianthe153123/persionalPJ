package com.example.demo.dto.resonse;

import lombok.Data;

@Data
public class LoginResponse {
    private String sessionId;
    private String username;
}
