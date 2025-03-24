/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.controllers;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopquanao.exceptions.CommonException;
import com.example.shopquanao.services.BaseService;
import com.example.shopquanao.utils.View;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

/**
 *
 * @author haidu
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
public abstract class BaseController<T, ID, DTO, RDTO> {

    protected abstract BaseService<T, ID, DTO, RDTO> getService();
    
    View view = new View();

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(
    		@RequestParam(required = false) String searchText,
    		@PageableDefault(
            size = 10, 
            page = 0, 
            sort = "createdAt", 
            direction = Sort.Direction.DESC
    ) Pageable pageable) {
        Page<DTO> data = getService().getAll(searchText, pageable);
        return ResponseEntity.ok(view.responseList(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable ID id) {
        Optional<DTO> data = getService().getById(id);
        return ResponseEntity.ok(view.responseOptional(data));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid RDTO entity) {
        Optional<DTO> data = getService().create(entity);
        if(!data.isPresent()) throw new CommonException(HttpStatus.NOT_FOUND, "Not found!");
        return ResponseEntity.created(URI.create("")).body(view.responseOptional(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable ID id, @Valid @RequestBody RDTO entity) {
        Optional<DTO> data = getService().update(id, entity);
        if(!data.isPresent()) throw new CommonException(HttpStatus.NOT_FOUND, "Not found!");
        return ResponseEntity.ok(view.responseOptional(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable ID id) {
        getService().deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
