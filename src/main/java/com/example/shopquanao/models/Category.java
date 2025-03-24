/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 *
 * @author haidu
 */
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class,
	    property = "id"
	)
@Entity
@Table(name = "Category")
public class Category extends BaseEntity{
     
    @Column(nullable = false, length = 191)
    private String name;
    
    @Column(nullable = false, length = 191, unique = true)
    private String slug;
    
    @Column(length = 191)
    private String image;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(columnDefinition = "DATETIME(3)")
    private LocalDateTime deletedAt;
    
    @ManyToOne
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    private Category parentCategory;
    
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> subCategories = new ArrayList<>();
    
    
    @ManyToMany(mappedBy = "productCategories")
    private Set<Product> products ;

    public Category() {
    }

    public Category(String id, String name, String slug, String image, String description, Category parentCategory, LocalDateTime deletedAt, Set<Product> products) {
        this.setId(id);
        this.name = name;
        this.slug = slug;
        this.image = image;
        this.description = description;
        this.parentCategory = parentCategory;
        this.deletedAt = deletedAt;
        this.products = products;
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

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    
}
