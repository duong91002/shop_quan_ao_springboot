/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import java.util.List;

import com.example.shopquanao.enums.OrderPaymentStatusEnum;
import com.example.shopquanao.enums.OrderStatusEnum;
import com.example.shopquanao.utils.StringListConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/**
 *
 * @author haidu
 */
@Entity
@Table(name = "`Order`")
public class Order extends BaseEntity{
    
    @Column(nullable = false, length = 191)
    private String customerName;
    
    @Column(nullable = false, length = 191)
    private String customerEmail;
    
    @Column(length = 191)
    private String customerPhone;
    
    @Column(nullable = false, length = 191)
    private double totalAmount;
    
    @Column(columnDefinition = "json")
    @Convert(converter = StringListConverter.class)
    private List<String> variants; 
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('PENDING', 'COMPLETED', 'CANCELLED') DEFAULT 'PENDING'")
    private OrderStatusEnum status  = OrderStatusEnum.PENDING;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('UNPAID', 'PAID') DEFAULT 'UNPAID'")
    private OrderPaymentStatusEnum paymentStatus= OrderPaymentStatusEnum.UNPAID;
    
    @Column(length = 191)
    private String notes;
    
    @Column(nullable = false, length = 191)
    private String customerAddress;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "communeId")
    private Commune communeOrder;
    
    @OneToMany(mappedBy = "orderOrderItem", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private User userOrder;
    
    public Order() {
    }

    public Order(String id, String customerName, String customerEmail, String customerPhone, double totalAmount, List<String> variants, String notes, String customerAddress, Commune communeOrder, List<OrderItem> orderItems, User userOrder) {
        this.setId(id);
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.totalAmount = totalAmount;
        this.variants = variants;
        this.notes = notes;
        this.customerAddress = customerAddress;
        this.communeOrder = communeOrder;
        this.orderItems = orderItems;
        this.userOrder = userOrder;
    }

	public User getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(User userOrder) {
		this.userOrder = userOrder;
	}

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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<String> getVariants() {
        return variants;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public OrderPaymentStatusEnum getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(OrderPaymentStatusEnum paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public Commune getCommuneOrder() {
        return communeOrder;
    }

    public void setCommuneOrder(Commune communeOrder) {
        this.communeOrder = communeOrder;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
       
}
