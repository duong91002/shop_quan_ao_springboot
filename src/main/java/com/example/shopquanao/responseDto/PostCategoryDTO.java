/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import com.example.shopquanao.models.PostCategory;

/**
 *
 * @author haidu
 */
public class PostCategoryDTO extends BaseDTO{
    private String name;
    private String slug;

    public PostCategoryDTO(PostCategory pc) {
        super(pc.getId(), pc.getCreatedAt(), pc.getUpdatedAt());
        this.name = pc.getName();
        this.slug = pc.getSlug();
    }

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
