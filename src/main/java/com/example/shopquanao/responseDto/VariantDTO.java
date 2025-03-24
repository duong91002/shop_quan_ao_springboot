/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import java.util.Set;
import java.util.stream.Collectors;

import com.example.shopquanao.enums.VariantStatusEnum;
import com.example.shopquanao.models.Variant;

/**
 *
 * @author haidu
 */
public class VariantDTO extends BaseDTO{
    private String name;
    private double price;
    private Double discount;
    private VariantStatusEnum status;
    private String productId;
    private Set<OptionDTO> options;

    public VariantDTO(Variant v) {
        super(v.getId(), v.getCreatedAt(), v.getUpdatedAt());
        this.name = v.getName();
        this.price = v.getPrice();
        this.discount = v.getDiscount();
        this.status = v.getStatus();
        this.productId = v.getProductVariant().getId();
        this.options = v.getVariantOptions().stream().map(OptionDTO::new).collect(Collectors.toSet());
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Set<OptionDTO> getOptions() {
		return options;
	}

	public void setVariantOptions(Set<OptionDTO> options) {
		this.options = options;
	}
    
}
