/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.shopquanao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopquanao.models.Province;

/**
 *
 * @author haidu
 */
@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {
	Page<Province> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
