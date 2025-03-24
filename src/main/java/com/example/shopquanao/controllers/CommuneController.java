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

import com.example.shopquanao.models.Commune;
import com.example.shopquanao.requestDto.CommuneRequestDTO;
import com.example.shopquanao.responseDto.CommuneDTO;
import com.example.shopquanao.services.CommuneService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 *
 * @author haidu
 */
@Tag(name = "Commune Management", description = "API Management Commune")
@RestController
@RequestMapping("/api/commune")
public class CommuneController extends BaseController<Commune, String, CommuneDTO, CommuneRequestDTO>{
    
    private final CommuneService cs;

    public CommuneController(CommuneService cs) {
        this.cs = cs;
    }

    @Override
    protected CommuneService getService() {
        return cs;
    }
    
    @GetMapping("/list-unlimite")
    public ResponseEntity<Map<String, Object>> getAllUnlimited() {
        List<CommuneDTO> data = getService().getAllUnlimited("");
        return ResponseEntity.ok(view.responseListUnlimited(data));
    }
    
    @GetMapping("/list-unlimite/{districtId}")
    public ResponseEntity<Map<String, Object>> getAllUnlimited(@PathVariable String districtId) {
        List<CommuneDTO> data = getService().getAllUnlimited(districtId);
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
	public ResponseEntity<Map<String, Object>> create(@Valid CommuneRequestDTO entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> update(String id, @Valid CommuneRequestDTO entity) {
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
