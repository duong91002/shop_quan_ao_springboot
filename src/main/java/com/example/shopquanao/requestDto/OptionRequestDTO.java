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
public class OptionRequestDTO {
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String value;
    
    @NotBlank
    private String typeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

    
    
}
