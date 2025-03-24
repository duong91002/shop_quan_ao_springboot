/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.shopquanao.models.Category;

/**
 *
 * @author haidu
 */
public class CategoryDTO extends BaseDTO{
    private String name;
    private String slug;
    private String image;
    private String description;
    private LocalDateTime deletedAt;
    private String parentId;
    private List<Category> subCategories;

    public CategoryDTO() {
    }

    public CategoryDTO(Category c) {
        super(c.getId(), c.getCreatedAt(), c.getUpdatedAt());
        this.name = c.getName();
        this.slug = c.getSlug();
        this.image = c.getImage();
        this.description = c.getDescription();
        this.deletedAt = c.getDeletedAt();
        this.parentId = c.getParentCategory() != null ? c.getParentCategory().getId() : null;
        this.subCategories = c.getSubCategories();
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}
    
}
