/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.shopquanao.enums.ProductStatusEnum;
import com.example.shopquanao.utils.StringListConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



/**
 *
 * @author haidu
 */
@Entity
@Table(name = "Product")
public class Product extends BaseEntity{
    
    @Column(nullable = false, length = 191)
    private String name;
    
    @Column(nullable = false, length = 191, unique = true)
    private String slug;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String shortDescription;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false, columnDefinition = "json")
    @Convert(converter = StringListConverter.class)
    private List<String> images;
    
    @Column(columnDefinition = "json")
    @Convert(converter = StringListConverter.class)
    private List<String> tags;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('DRAFT', 'PUBLISHED', 'OUT_OF_STOCK') DEFAULT 'DRAFT'")
    private ProductStatusEnum status = ProductStatusEnum.DRAFT;
    
    @Column(columnDefinition = "DATETIME(3)")
    private LocalDateTime deletedAt;
    
    @OneToOne
    @JoinColumn(name = "defaultVariantId", referencedColumnName = "id", unique = true)
    private Variant variantProduct;
    
    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Variant> variants = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
        name = "_ProductCategories",
        joinColumns = @JoinColumn(name = "B"),
        inverseJoinColumns = @JoinColumn(name = "A")
    )
    private Set<Category> productCategories;
            
    public Product() {
    }

    public Product(String id, String name, ProductStatusEnum status,  String slug, String shortDescription, String description, List<String> images, List<String> tags, LocalDateTime deletedAt, Variant variantProduct, Set<Category> productCategories) {
        this.setId(id);
        this.name = name;
        this.status = status;
        this.slug = slug;
        this.shortDescription = shortDescription;
        this.description = description;
        this.images = images;
        this.tags = tags;
        this.deletedAt = deletedAt;
        this.variantProduct = variantProduct;
        this.productCategories = productCategories;
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

    public Variant getVariantProduct() {
        return variantProduct;
    }

    public void setVariantProduct(Variant variantProduct) {
        this.variantProduct = variantProduct;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public Set<Category> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(Set<Category> productCategories) {
        this.productCategories = productCategories;
    }
    
}