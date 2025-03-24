/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.shopquanao.enums.ProductStatusEnum;
import com.example.shopquanao.models.Product;

/**
 *
 * @author haidu
 */
public class ProductDTO extends BaseDTO{
    private String name;
    private String slug;
    private String shortDescription;
    private String description;
    private List<String> images;
    private List<String> tags;
    private ProductStatusEnum status;
    private LocalDateTime deletedAt;
    private List<VariantDTO> variants;
    private String defaultVariantId;
    private Set<CategoryDTO> productCategories;

    public ProductDTO(Product p) {
        super(p.getId(), p.getCreatedAt(), p.getUpdatedAt());
        this.name = p.getName();
        this.slug = p.getSlug();
        this.shortDescription = p.getShortDescription();
        this.description = p.getDescription();
        this.images = p.getImages();
        this.tags = p.getTags();
        this.status = p.getStatus();
        this.deletedAt = p.getDeletedAt();
        this.defaultVariantId = p.getVariantProduct().getId();
        this.variants = p.getVariants().stream().map(VariantDTO::new).collect(Collectors.toList());
        this.productCategories = p.getProductCategories().stream().map(CategoryDTO::new).collect(Collectors.toSet());
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

	public List<VariantDTO> getVariants() {
		return variants;
	}

	public void setVariants(List<VariantDTO> variants) {
		this.variants = variants;
	}

	public String getDefaultVariantId() {
		return defaultVariantId;
	}

	public void setDefaultVariantId(String defaultVariantId) {
		this.defaultVariantId = defaultVariantId;
	}

	public Set<CategoryDTO> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(Set<CategoryDTO> productCategories) {
		this.productCategories = productCategories;
	}
    
    
}
