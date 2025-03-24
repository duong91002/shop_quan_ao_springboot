/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.shopquanao.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopquanao.enums.PostStatusEnum;
import com.example.shopquanao.models.Post;

/**
 *
 * @author haidu
 */
@Repository
public interface PostRepository extends JpaRepository<Post, String> {
	Optional<Post> findBySlug(String slug);
	
	Page<Post> findAllByStatus(PostStatusEnum status, Pageable pageable);
	
	Page<Post> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
	
	Page<Post> findAllByStatusAndNameContainingIgnoreCase(PostStatusEnum status, String name, Pageable pageable);
}
