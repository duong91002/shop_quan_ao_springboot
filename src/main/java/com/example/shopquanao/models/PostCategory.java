/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;

/**
 *
 * @author haidu
 */
@Entity
@Table(name = "PostCategory")
public class PostCategory extends BaseEntity{
    
    @Column(nullable = false, length = 191)
    private String name;
    
    @Column(nullable = false, length = 191, unique = true)
    private String slug;
    
    @ManyToMany(mappedBy = "postCategories")
    private Set<Post> posts;

    public PostCategory() {
    }

    public PostCategory(String id, String name, String slug, Set<Post> posts) {
        this.setId(id);
        this.name = name;
        this.slug = slug;
        this.posts = posts;
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

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
    
}
