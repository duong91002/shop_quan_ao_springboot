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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopquanao.models.District;
import com.example.shopquanao.requestDto.DistrictRequestDTO;
import com.example.shopquanao.responseDto.DistrictDTO;
import com.example.shopquanao.services.DistrictService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 *
 * @author haidu
 */
@Tag(name = "District Management", description = "API Management District")
@RestController
@RequestMapping("/api/district")
public class DistrictController extends BaseController<District, String, DistrictDTO, DistrictRequestDTO>{
    
    private final DistrictService ds;

    public DistrictController(DistrictService ds) {
        this.ds = ds;
    }
    
    @Override
    protected DistrictService getService() {
       return ds;
    }
    
    @GetMapping("/list-unlimite")
    public ResponseEntity<Map<String, Object>> getAllUnlimited() {
        List<DistrictDTO> data = getService().getAllUnlimited("");
        return ResponseEntity.ok(view.responseListUnlimited(data));
    }
    
    @GetMapping("/list-unlimite/{provinceId}")
    public ResponseEntity<Map<String, Object>> getAllUnlimited(@PathVariable String provinceId) {
        List<DistrictDTO> data = getService().getAllUnlimited(provinceId);
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
	public ResponseEntity<Map<String, Object>> create(@Valid DistrictRequestDTO entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> update(String id, @Valid DistrictRequestDTO entity) {
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
