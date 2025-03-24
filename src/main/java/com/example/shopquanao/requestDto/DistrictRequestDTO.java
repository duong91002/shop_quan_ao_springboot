/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.requestDto;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author haidu
 */
public class DistrictRequestDTO {
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String provinceId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
    
}
