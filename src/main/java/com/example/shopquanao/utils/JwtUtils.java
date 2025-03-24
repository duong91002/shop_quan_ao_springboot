/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.shopquanao.enums.UserRoleEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 *
 * @author haidu
 */
@Component
public class JwtUtils {
    
    private final String SECRET_KEY = "shop_quan_ao_shop_quan_ao_shop_quan_ao";
    private final long EXPIRATION_TIME = 86400000;
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); 
    Date now = new Date();
    Date expiryDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
    
    public String generateToken(String username, UserRoleEnum role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role.name())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public Claims decodeToken(String token) {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

}

