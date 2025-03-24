/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author haidu
 */
@Entity
@Table(name = "OrderItem")
public class OrderItem extends BaseEntity{
    
    @Column(nullable = false)
    private int quantity;
    
    @Column(nullable = false)
    private double price;
    
    @Column(nullable = false)
    private double total;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "orderId")
    private Order orderOrderItem;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "variantId")
    private Variant variantOrderItem;
    

    public OrderItem() {
    }

    public OrderItem(String id, int quantity, double price, double total, Order orderOrderItem, Variant variantOrderItem) {
        this.setId(id);
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.orderOrderItem = orderOrderItem;
        this.variantOrderItem = variantOrderItem;
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

    public Order getOrderOrderItem() {
        return orderOrderItem;
    }

    public void setOrderOrderItem(Order orderOrderItem) {
        this.orderOrderItem = orderOrderItem;
    }

    public Variant getVariantOrderItem() {
        return variantOrderItem;
    }

    public void setVariantOrderItem(Variant variantOrderItem) {
        this.variantOrderItem = variantOrderItem;
    }
    
}
