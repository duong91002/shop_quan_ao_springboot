/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.controllers;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopquanao.requestDto.SettingRequestDTO;
import com.example.shopquanao.responseDto.SettingDTO;
import com.example.shopquanao.services.SettingService;
import com.example.shopquanao.utils.View;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 *
 * @author haidu
 */
@Tag(name = "Setting Management", description = "API Management Setting")
@RestController
@RequestMapping("/api/setting")
@SecurityRequirement(name = "bearerAuth")
public class SettingController{

    private final SettingService ss;

    public SettingController(SettingService ss) {
        this.ss = ss;
    }
    
    View view = new View();
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@PageableDefault(
            size = 10, 
            page = 0, 
            sort = "createdAt", 
            direction = Sort.Direction.DESC
    ) Pageable pageable) {
        Page<SettingDTO> data = ss.getAll(pageable);
        return ResponseEntity.ok(view.responseList(data));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> update(@PathVariable String id, @Valid @RequestBody SettingRequestDTO entity) {
        return ResponseEntity.ok(view.responseOptional(ss.update(id, entity)));
    }
}
