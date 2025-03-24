/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.controllers;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopquanao.models.Option;
import com.example.shopquanao.requestDto.OptionRequestDTO;
import com.example.shopquanao.responseDto.OptionDTO;
import com.example.shopquanao.services.OptionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 *
 * @author haidu
 */
@Tag(name = "Option Management", description = "API Management Option")
@RestController
@RequestMapping("/api/option")
public class OptionController extends BaseController<Option, String, OptionDTO, OptionRequestDTO>{

    private final OptionService os;

    public OptionController(OptionService os) {
        this.os = os;
    }
    
    
    @Override
    protected OptionService getService() {
        return os;
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
	public ResponseEntity<Map<String, Object>> create(@Valid OptionRequestDTO entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}


	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> update(String id, @Valid OptionRequestDTO entity) {
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
