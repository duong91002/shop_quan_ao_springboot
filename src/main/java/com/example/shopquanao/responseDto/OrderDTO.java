/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.shopquanao.enums.OrderPaymentStatusEnum;
import com.example.shopquanao.enums.OrderStatusEnum;
import com.example.shopquanao.models.Order;

/**
 *
 * @author haidu
 */
public class OrderDTO extends BaseDTO {
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private double totalAmount;
    private List<OrderItemDTO> orderItems;
    private OrderStatusEnum status;
    private OrderPaymentStatusEnum paymentStatus;
    private String notes;
    private String customerAddress;

    public OrderDTO(Order o) {
        super(o.getId(), o.getCreatedAt(), o.getUpdatedAt());
        this.customerName = o.getCustomerName();
        this.customerEmail = o.getCustomerEmail();
        this.customerPhone = o.getCustomerPhone();
        this.totalAmount = o.getTotalAmount();
        this.orderItems = o.getOrderItems().stream().map(OrderItemDTO:: new).collect(Collectors.toList());
        this.status = o.getStatus();
        this.paymentStatus = o.getPaymentStatus();
        this.notes = o.getNotes();
        this.customerAddress = o.getCustomerAddress();
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

    public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
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
    
}
