/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopquanao.enums.ProductStatusEnum;
import com.example.shopquanao.models.Product;

/**
 *
 * @author haidu
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
	Page<Product> findAllByStatusAndDeletedAtIsNull(ProductStatusEnum status, Pageable pageable);
	
	Optional<Product> findByIdAndStatusAndDeletedAtIsNull(String id, ProductStatusEnum status);
	
	Optional<Product> findBySlug(String slug);
	
	Page<Product> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
	
	Page<Product> findAllByStatusAndDeletedAtIsNullAndNameContainingIgnoreCase(ProductStatusEnum status, String name, Pageable pageable);
}
