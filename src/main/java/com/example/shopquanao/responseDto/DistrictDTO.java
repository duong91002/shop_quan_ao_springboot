/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import com.example.shopquanao.models.District;

/**
 *
 * @author haidu
 */
public class DistrictDTO extends BaseDTO{
    private String name;
    private String provinceId;
    public DistrictDTO(District d) {
        super(d.getId(), d.getCreatedAt(), d.getUpdatedAt());
        this.name = d.getName();
        this.provinceId = d.getProvinceDistrict().getId();
    }

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
