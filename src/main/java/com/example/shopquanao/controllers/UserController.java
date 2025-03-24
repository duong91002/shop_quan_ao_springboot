/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.controllers;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopquanao.models.User;
import com.example.shopquanao.requestDto.UserRequestDTO;
import com.example.shopquanao.responseDto.UserDTO;
import com.example.shopquanao.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


/**
 *
 * @author haidu
 */
@Tag(name = "User Management", description = "API Management User")
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController<User, String, UserDTO, UserRequestDTO>{
    
    private final UserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    @Override
    protected UserService getService() {
        return us;
    }
    
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getUserInfo(Principal principal) {
    	if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Unauthorized"));
        }
    	Optional<UserDTO> user = us.getUserByEmail(principal.getName());
        return ResponseEntity.ok(view.responseOptional(user));
    }

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getAll(String searchText, Pageable pageable) {
		// TODO Auto-generated method stub
		return super.getAll(searchText, pageable);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getById(String id) {
		// TODO Auto-generated method stub
		return super.getById(id);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> create(@Valid UserRequestDTO entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> update(String id, @Valid UserRequestDTO entity) {
		// TODO Auto-generated method stub
		return super.update(id, entity);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteById(String id) {
		// TODO Auto-generated method stub
		return super.deleteById(id);
	}
    
 
}
