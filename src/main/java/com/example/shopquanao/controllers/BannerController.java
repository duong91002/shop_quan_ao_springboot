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

import com.example.shopquanao.models.Banner;
import com.example.shopquanao.requestDto.BannerRequestDTO;
import com.example.shopquanao.responseDto.BannerDTO;
import com.example.shopquanao.services.BannerService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 *
 * @author haidu
 */
@Tag(name = "Banner Management", description = "API Management Banner")
@RestController
@RequestMapping("/api/banner")
public class BannerController extends BaseController<Banner, String, BannerDTO, BannerRequestDTO>{

    private final BannerService bs;

    public BannerController(BannerService bs) {
        this.bs = bs;
    }
    
    @Override
    protected BannerService getService() {
        return bs;
    }

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getById(String id) {
		// TODO Auto-generated method stub
		return super.getById(id);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> create(@Valid BannerRequestDTO entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> update(String id, @Valid BannerRequestDTO entity) {
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
