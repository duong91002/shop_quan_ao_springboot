/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopquanao.models.Post;
import com.example.shopquanao.requestDto.PostRequestDTO;
import com.example.shopquanao.responseDto.PostDTO;
import com.example.shopquanao.services.PostService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 *
 * @author haidu
 */
@Tag(name = "Post Management", description = "API Management Post")
@RestController
@RequestMapping("/api/post")
public class PostController extends BaseController<Post, String, PostDTO, PostRequestDTO>{

    private final PostService ps;

    public PostController(PostService ps) {
        this.ps = ps;
    }
    
    @Override
    protected PostService getService() {
       return ps;
    }

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getById(String id) {
		// TODO Auto-generated method stub
		return super.getById(id);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> create(@Valid PostRequestDTO entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> update(String id, @Valid PostRequestDTO entity) {
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
