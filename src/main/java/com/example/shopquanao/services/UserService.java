/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.shopquanao.models.User;
import com.example.shopquanao.repositories.UserRepository;
import com.example.shopquanao.requestDto.UserRequestDTO;
import com.example.shopquanao.responseDto.UserDTO;

import java.util.Optional;
import java.util.function.Function;
/**
 *
 * @author haidu
 */
@Service
public class UserService extends BaseService<User, String, UserDTO, UserRequestDTO>{
    
    @Autowired
    private UserRepository ur;
    
    public UserService(UserRepository ur) {
        this.ur = ur;
    }
    
    @Override
    protected UserRepository getRepository() {
        return ur;
    }
    
    @Override
    protected Function<User, UserDTO> getMapper() {
        return user -> new UserDTO(user);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
    
    public Optional<UserDTO> getUserByEmail(String email) {
    	User u = ur.findByEmail(email).get();
    	return Optional.ofNullable(getMapper().apply(u));
    }

	@Override
	public Page<UserDTO> getAll(String searchText, Pageable pageable) {
		if(searchText == null || searchText.trim().isEmpty()) {
    		return getRepository().findAll(pageable).map(getMapper());
    	}
    	
    	return getRepository().findAllByNameContainingIgnoreCase(searchText, pageable).map(getMapper());
	}
    
}
