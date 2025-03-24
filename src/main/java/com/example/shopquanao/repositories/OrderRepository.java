/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.shopquanao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopquanao.models.Order;

/**
 *
 * @author haidu
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
	Page<Order> findAllByUserOrderId(String userId, Pageable pageable);
	
	Page<Order> findAllByCustomerEmailContainingIgnoreCase(String customerEmail, Pageable pageable);
	
	Page<Order> findAllByUserOrderIdAndCustomerEmailContainingIgnoreCase(String userId, String customerEmail, Pageable pageable);
}
