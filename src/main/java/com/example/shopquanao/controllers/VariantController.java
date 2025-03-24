package com.example.shopquanao.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopquanao.models.Variant;
import com.example.shopquanao.requestDto.VariantRequestDTO;
import com.example.shopquanao.responseDto.VariantDTO;
import com.example.shopquanao.services.VariantService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Variant Management", description = "API Management Variant")
@RestController
@RequestMapping("/api/variant")
public class VariantController extends BaseController<Variant, String, VariantDTO, VariantRequestDTO>{

    private final VariantService vs;

    public VariantController(VariantService vs) {
        this.vs = vs;
    }
    
    @Override
    protected VariantService getService() {
        return vs;
    }

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getById(String id) {
		// TODO Auto-generated method stub
		return super.getById(id);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> create(@Valid VariantRequestDTO entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> update(String id, @Valid VariantRequestDTO entity) {
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

