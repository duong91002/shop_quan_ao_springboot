/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import com.example.shopquanao.models.OrderItem;

/**
 *
 * @author haidu
 */
public class OrderItemDTO extends BaseDTO{
    private int quantity;
    private double price;
    private double total;
    private VariantDTO variant;

    public OrderItemDTO(OrderItem oi) {
        super(oi.getId(), oi.getCreatedAt(), oi.getUpdatedAt());
        this.quantity = oi.getQuantity();
        this.price = oi.getPrice();
        this.total = oi.getTotal();
        this.variant = new VariantDTO(oi.getVariantOrderItem());
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

	public VariantDTO getVariant() {
		return variant;
	}

	public void setVariant(VariantDTO variant) {
		this.variant = variant;
	}
    
    
}
