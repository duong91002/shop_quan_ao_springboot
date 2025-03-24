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
public class CommuneRequestDTO {
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String districtId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
    
}
