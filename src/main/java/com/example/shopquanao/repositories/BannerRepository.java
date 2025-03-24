/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopquanao.enums.BannerStatusEnum;
import com.example.shopquanao.models.Banner;

/**
 *
 * @author haidu
 */
@Repository
public interface BannerRepository extends JpaRepository<Banner, String>{
	Page<Banner> findAllByStatus(BannerStatusEnum status, Pageable pageable);
	
	Page<Banner> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
	
	Page<Banner> findAllByStatusAndNameContainingIgnoreCase(BannerStatusEnum status, String name,  Pageable pageable);
}
