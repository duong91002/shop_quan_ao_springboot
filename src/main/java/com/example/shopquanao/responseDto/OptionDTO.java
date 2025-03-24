/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import com.example.shopquanao.models.Option;

/**
 *
 * @author haidu
 */
public class OptionDTO extends BaseDTO{
    private String name;
    private String value;
    private String typeId;
    private OptionTypeDTO optionType;

    public OptionDTO(Option o) {
        super(o.getId(), o.getCreatedAt(), o.getUpdatedAt());
        this.name = o.getName();
        this.value = o.getValue();
        this.typeId = o.getOptionTypeOption().getId();
        this.optionType = new OptionTypeDTO(o.getOptionTypeOption());
    }

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

	public OptionTypeDTO getOptionType() {
		return optionType;
	}

	public void setOptionType(OptionTypeDTO optionType) {
		this.optionType = optionType;
	}
    
    
}
