/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopquanao.requestDto.AuthRequestDTO;
import com.example.shopquanao.requestDto.ResendVerifyRequestDTO;
import com.example.shopquanao.requestDto.VerifyRequestDTO;
import com.example.shopquanao.services.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 *
 * @author haidu
 */
@Tag(name = "Auth Management", description = "API Management Auth")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService as;

    public AuthController(AuthService as) {
        this.as = as;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody AuthRequestDTO request) {
        Map<String, Object> response = as.register(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody AuthRequestDTO request) {
        Map<String, Object> response = as.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/verify-email")
    public ResponseEntity<Map<String, Object>> verifyEmail(@RequestBody VerifyRequestDTO request ){
    	Map<String, Object> response = as.verifyEmail(request.getCode());
    	return ResponseEntity.ok(response);
    }
    
    @PostMapping("/resend-verification")
    public ResponseEntity<Map<String, Object>> resendVerification(@RequestBody ResendVerifyRequestDTO request){
    	Map<String, Object> response = as.resendVerification(request.getEmail());
    	return ResponseEntity.ok(response);
    }
}
