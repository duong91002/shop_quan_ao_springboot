/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.requestDto;

import java.util.List;

import com.example.shopquanao.enums.VariantStatusEnum;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author haidu
 */
public class VariantRequestDTO {
    
    @NotBlank
    private String name;
    
    @NotNull
    @Min(value = 1)
    private double price;
    
    @Min(value = 1)
    @Max(value = 100)
    private Double discount;
    
    @NotNull
    private VariantStatusEnum status;
    
    private List<String> optionIds;

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

	public VariantStatusEnum getStatus() {
        return status;
    }

    public void setStatus(VariantStatusEnum status) {
        this.status = status;
    }

	public List<String> getOptionIds() {
		return optionIds;
	}

	public void setOptionIds(List<String> optionIds) {
		this.optionIds = optionIds;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
}
