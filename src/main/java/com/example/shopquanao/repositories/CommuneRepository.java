/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.shopquanao.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopquanao.models.Commune;

/**
 *
 * @author haidu
 */
@Repository
public interface CommuneRepository extends JpaRepository<Commune, String> {
    List<Commune> getAllByDistrictCommuneId(String districtId);
    
    Page<Commune> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
