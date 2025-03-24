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

import com.example.shopquanao.models.Category;
/**
 *
 * @author haidu
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, String>{
	
	Optional<Category> findBySlug(String slug);
	
	Page<Category> findAllByDeletedAtIsNull(Pageable pageable);
	
	Page<Category> findAllByDeletedAtIsNullAndNameContainingIgnoreCase(String name, Pageable pageable);
	
	Optional<Category> findByIdAndDeletedAtIsNull(String id);

}
