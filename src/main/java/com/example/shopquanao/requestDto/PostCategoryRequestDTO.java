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
public class PostCategoryRequestDTO {
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String slug;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
