/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author haidu
 */
public class OptionTypeRequestDTO {
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String value;
    
    @NotNull
    private String guideLink;

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
