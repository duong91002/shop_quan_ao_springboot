/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopquanao.models.OptionType;
import com.example.shopquanao.requestDto.OptionTypeRequestDTO;
import com.example.shopquanao.responseDto.OptionTypeDTO;
import com.example.shopquanao.services.OptionTypeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 *
 * @author haidu
 */
@Tag(name = "Option Type Management", description = "API Management Option Type")
@RestController
@RequestMapping("/api/option-type")
public class OptionTypeController extends BaseController<OptionType, String, OptionTypeDTO, OptionTypeRequestDTO>{

    private final OptionTypeService ots; 

    public OptionTypeController(OptionTypeService ots) {
        this.ots = ots;
    }
    
    @Override
    protected OptionTypeService getService() {
        return ots;
    }
    
    @GetMapping("/list-unlimite")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getAllUnlimited() {
        List<OptionTypeDTO> data = getService().getAllUnlimited();
        return ResponseEntity.ok(view.responseListUnlimited(data));
    }

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getAll(String serachText, Pageable pageable) {
		// TODO Auto-generated method stub
		return super.getAll(serachText, pageable);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getById(String id) {
		// TODO Auto-generated method stub
		return super.getById(id);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> create(@Valid OptionTypeRequestDTO entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> update(String id, @Valid OptionTypeRequestDTO entity) {
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
