/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopquanao.enums.VariantStatusEnum;
import com.example.shopquanao.models.Variant;

/**
 *
 * @author haidu
 */
@Repository
public interface VariantRepository extends JpaRepository<Variant, String>{
	Page<Variant> findAllByStatus(VariantStatusEnum status, Pageable pageable);
	
	Page<Variant> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
	
	Page<Variant> findAllByStatusAndNameContainingIgnoreCase(VariantStatusEnum status, String name, Pageable pageable);
}
