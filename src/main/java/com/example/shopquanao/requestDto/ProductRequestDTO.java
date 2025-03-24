/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.requestDto;

import java.util.List;
import java.util.Set;

import com.example.shopquanao.enums.ProductStatusEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author haidu
 */
public class ProductRequestDTO {
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String slug;
    
    @NotBlank
    private String shortDescription;
    
    @NotBlank
    private String description;
    
    @NotEmpty
    @Size(min = 1)
    private List<String> images;
    
    private List<String> tags;
    
    @NotNull
    private ProductStatusEnum status;
    
//    private String defaultVariantId;

    @NotEmpty
    private List<VariantRequestDTO> variants;
    
    @NotEmpty
    private Set<String> productCategoryIds;

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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ProductStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProductStatusEnum status) {
        this.status = status;
    }

	public List<VariantRequestDTO> getVariants() {
		return variants;
	}

	public void setVariants(List<VariantRequestDTO> variants) {
		this.variants = variants;
	}

	public Set<String> getProductCategoryIds() {
		return productCategoryIds;
	}

	public void setProductCategoryIds(Set<String> productCategoryIds) {
		this.productCategoryIds = productCategoryIds;
	}    
    
}
