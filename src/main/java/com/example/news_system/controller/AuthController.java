package com.example.news_system.controller;

import com.example.news_system.dto.request.RegisterReqDTO;
import com.example.news_system.service.AuthService;
import com.example.news_system.dto.request.LoginReqDTO;
import com.example.news_system.dto.response.auth.AuthResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResDTO> login(
            @RequestBody LoginReqDTO request)
    {
        return ResponseEntity.ok(authService.login(request));

    }
    @PostMapping("/register")
    public ResponseEntity<AuthResDTO> register(
            @RequestBody RegisterReqDTO request)
    {
        return ResponseEntity.ok(authService.register(request));

    }




}