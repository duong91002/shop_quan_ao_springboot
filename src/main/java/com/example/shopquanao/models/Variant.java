/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;

import com.example.shopquanao.enums.VariantStatusEnum;

/**
 *
 * @author haidu
 */
@Entity
@Table(name = "Variant")
public class Variant extends BaseEntity{
   
    
    @Column(nullable = false, length = 191)
    private String name;
    
    @Column(nullable = false)
    private double price;
    
    @Column()
    private Double discount;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('DRAFT', 'PUBLISHED', 'OUT_OF_STOCK') DEFAULT 'DRAFT'")
    private VariantStatusEnum status = VariantStatusEnum.DRAFT;
    
    @OneToMany(mappedBy = "variantOrderItem", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "productId", referencedColumnName = "id")
    private Product productVariant;
    
    @ManyToMany
    @JoinTable(
        name = "_VariantOptions",
        joinColumns = @JoinColumn(name = "B"),
        inverseJoinColumns = @JoinColumn(name = "A")
    )
    private Set<Option> variantOptions ;
        
    public Variant() {
    }

    public Variant(String id, String name, double price, Double discount, String productId, List<OrderItem> orderItems, Product productVariant, Set<Option> variantOptions) {
        this.setId(id);
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.orderItems = orderItems;
        this.productVariant = productVariant;
        this.variantOptions= variantOptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public VariantStatusEnum getStatus() {
        return status;
    }

    public void setStatus(VariantStatusEnum status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Product getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(Product productVariant) {
        this.productVariant = productVariant;
    }

    public Set<Option> getVariantOptions() {
        return variantOptions;
    }

    public void setVariantOptions(Set<Option> variantOptions) {
        this.variantOptions = variantOptions;
    }
    
}
