/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopquanao.models.Province;
import com.example.shopquanao.requestDto.ProvinceRequestDTO;
import com.example.shopquanao.responseDto.ProvinceDTO;
import com.example.shopquanao.services.ProvinceService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 *
 * @author haidu
 */
@Tag(name = "Province Management", description = "API Management Province")
@RestController
@RequestMapping("/api/province")
public class ProvinceController extends BaseController<Province, String, ProvinceDTO, ProvinceRequestDTO>{

    private final ProvinceService ps;

    public ProvinceController(ProvinceService ps) {
        this.ps = ps;
    }
    
    @Override
    protected ProvinceService getService() {
        return ps;
    }
    
    @GetMapping("/list-unlimite")
    public ResponseEntity<Map<String, Object>> getAllUnlimited() {
        List<ProvinceDTO> data = getService().getAllUnlimited();
        return ResponseEntity.ok(view.responseListUnlimited(data));
    }

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getById(String id) {
		// TODO Auto-generated method stub
		return super.getById(id);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> create(@Valid ProvinceRequestDTO entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> update(String id, @Valid ProvinceRequestDTO entity) {
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
