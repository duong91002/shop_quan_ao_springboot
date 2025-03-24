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

import com.example.shopquanao.models.OptionType;

/**
 *
 * @author haidu
 */
@Repository
public interface OptionTypeRepository extends JpaRepository<OptionType, String>{
    Optional<OptionType> findByValue(String value);
    
    Page<OptionType> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
