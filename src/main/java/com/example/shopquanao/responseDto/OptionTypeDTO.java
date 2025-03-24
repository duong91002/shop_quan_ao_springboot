/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import com.example.shopquanao.models.OptionType;

/**
 *
 * @author haidu
 */
public class OptionTypeDTO extends BaseDTO{
    private String name;
    private String value;
    private String guideLink;

    public OptionTypeDTO(OptionType ot) {
        super(ot.getId(), ot.getCreatedAt(), ot.getUpdatedAt());
        this.name = ot.getName();
        this.value = ot.getValue();
        this.guideLink = ot.getGuideLink();
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

    public String getGuideLink() {
        return guideLink;
    }

    public void setGuideLink(String guideLink) {
        this.guideLink = guideLink;
    }
    
    
}
