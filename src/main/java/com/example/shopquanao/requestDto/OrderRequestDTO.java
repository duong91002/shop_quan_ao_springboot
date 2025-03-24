/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.requestDto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 *
 * @author haidu
 */
public class OrderRequestDTO {
    
    @NotBlank
    private String customerName;
    
    @NotBlank
    private String customerEmail;
    
    
    private String customerPhone;
    
    @NotBlank
    private String communeId;
    
    @NotEmpty()
    @Size(min = 1)
    private List<OrderItemRequestDTO> orderItems;
    
    private String notes;
    
    @NotBlank
    private String customerAddress;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

	public String getCommuneId() {
		return communeId;
	}

	public void setCommuneId(String communeId) {
		this.communeId = communeId;
	}

	public List<OrderItemRequestDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemRequestDTO> orderItems) {
		this.orderItems = orderItems;
	}
}
