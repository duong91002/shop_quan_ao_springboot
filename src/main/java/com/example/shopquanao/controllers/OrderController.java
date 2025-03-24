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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopquanao.requestDto.OrderRequestDTO;
import com.example.shopquanao.requestDto.OrderUpdateStatusRequestDTO;
import com.example.shopquanao.responseDto.OrderDTO;
import com.example.shopquanao.services.OrderService;
import com.example.shopquanao.utils.View;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 *
 * @author haidu
 */
@Tag(name = "Order Management", description = "API Management Order")
@RestController
@RequestMapping("/api/order")
@SecurityRequirement(name = "bearerAuth")
public class OrderController{

    private final OrderService os;

    public OrderController(OrderService os) {
        this.os = os;
    }
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
        Page<OrderDTO> data = os.getAll(searchText, pageable);
        return ResponseEntity.ok(view.responseList(data));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable String id) {
        return ResponseEntity.ok(view.responseOptional(os.getById(id)));
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody OrderRequestDTO entity) {
        return ResponseEntity.ok(view.responseOptional(os.create(entity)));
    }
    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> update(@PathVariable String id, @Valid @RequestBody OrderUpdateStatusRequestDTO entity) {
        return ResponseEntity.ok(view.responseOptional(os.updateStatus(id, entity)));
    }
    
    @PutMapping("/{id}/payment")
    public ResponseEntity<Map<String, Object>> updatePayment(@PathVariable String id) {
        return ResponseEntity.ok(view.responseOptional(os.updatePayment(id)));
    }
    
}
