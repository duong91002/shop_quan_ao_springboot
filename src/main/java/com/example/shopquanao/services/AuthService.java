/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.services;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shopquanao.exceptions.CommonException;
import com.example.shopquanao.models.User;
import com.example.shopquanao.repositories.UserRepository;
import com.example.shopquanao.utils.JwtUtils;

import io.jsonwebtoken.Claims;

/**
 *
 * @author haidu
 */
@Service
public class AuthService {

	@Autowired
    private UserRepository ur;
    
    @Autowired
    private PasswordEncoder pe;
    
    @Autowired
    private JwtUtils jwt;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private RedisService redisService;

    
    public Map<String, Object> register(String email, String password) {

        if (ur.findByEmail(email).isEmpty()) throw new CommonException(HttpStatus.BAD_REQUEST, "Email already exist!");

        User u = new User();
        u.setEmail(email);
        u.setPassword(pe.encode(password));

        ur.save(u);
        String token = jwt.generateToken(u.getEmail(), u.getRole());
        
        emailService.sendVerificationEmail(email, token);
        
        return Map.of("message", "success");
    }
    
    public Map<String, Object> login(String email, String password){
        
        User user = ur.findByEmail(email).get();
    	
        if (user == null) throw new CommonException(HttpStatus.BAD_REQUEST, "Email does not exist!");    
        
        if (!user.getIsActive()) throw new CommonException(HttpStatus.BAD_REQUEST, "The user hasn't verified their email!");
        
        if (!pe.matches(password, user.getPassword())) throw new CommonException(HttpStatus.BAD_REQUEST, "Wrong password!");
     
        String token = jwt.generateToken(user.getEmail(), user.getRole());
        return Map.of("token", token);
    }
    
    public Map<String, Object> verifyEmail(String code){
    	
    	String token = redisService.getToken(code);

        if (token == null) throw new CommonException(HttpStatus.BAD_REQUEST, "Invalid or expired verification link.");
    	
    	Claims claims;
        try {
            claims = jwt.decodeToken(token);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "Invalid JWT Token!");
        }
        
        String email = claims.getSubject();

        User user = ur.findByEmail(email).get();
        
        if(user == null) throw new CommonException(HttpStatus.BAD_REQUEST, "Email does not exist!");
        
        if(user.getIsActive()) throw new CommonException(HttpStatus.BAD_REQUEST, "The user has verified their email!");
        
        user.setIsActive(true);
        
        ur.save(user);
        
        redisService.deleteToken(code);
        
    	return Map.of("token", token);
    }
    
    public Map<String, Object> resendVerification(String email){
    	User user = ur.findByEmail(email).get();
    	 
    	if(user == null) throw new CommonException(HttpStatus.BAD_REQUEST, "Email does not exist!");
        
        if(user.getIsActive()) throw new CommonException(HttpStatus.BAD_REQUEST, "The user has verified their email!");
        
        String token = jwt.generateToken(user.getEmail(), user.getRole());
        
        emailService.sendVerificationEmail(email, token);
        
    	return Map.of("message", "success");
    }
    
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CommonException(HttpStatus.UNAUTHORIZED, "Bad Credential!");
        }

        Object principal = authentication.getPrincipal();
        String username;
        
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return ur.findByEmail(username)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
