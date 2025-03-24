/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import com.example.shopquanao.models.Commune;

/**
 *
 * @author haidu
 */
public class CommuneDTO extends BaseDTO{
    private String name;
    private String districtId;

    public CommuneDTO() {
    }

    public CommuneDTO(Commune c) {
        super(c.getId(), c.getCreatedAt(), c.getUpdatedAt());
        this.name = c.getName();
        this.districtId = c.getDistrictCommune().getId();
    }

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
