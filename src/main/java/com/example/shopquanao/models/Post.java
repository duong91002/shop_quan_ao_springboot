/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import com.example.shopquanao.enums.PostStatusEnum;
import com.example.shopquanao.utils.StringListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;

/**
 *
 * @author haidu
 */
@Entity
@Table(name = "Post")
public class Post extends BaseEntity{
    
    
    @Column(nullable = false, length = 191)
    private String name;
    
    @Column(nullable = false, length = 191, unique = true)
    private String slug;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String shortContent;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    
    @Column(length = 191)
    private String image;

    @Column(columnDefinition = "json")
    @Convert(converter = StringListConverter.class)
    private List<String> tags; 
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('DRAFT', 'PUBLISHED') DEFAULT 'DRAFT'")
    private PostStatusEnum status;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "createdById")
    private User userPost;
    
    @ManyToMany
    @JoinTable(
        name = "_PostCategories",
        joinColumns = @JoinColumn(name = "A"),
        inverseJoinColumns = @JoinColumn(name = "B")
    )
    private Set<PostCategory> postCategories;

    public Post() {
    }

    public Post(String id, String name, String slug, String shortContent, String content, String image, List<String> tags, PostStatusEnum status, User userPost, Set<PostCategory> postCategories) {
        this.setId(id);
        this.name = name;
        this.slug = slug;
        this.shortContent = shortContent;
        this.content = content;
        this.image = image;
        this.tags = tags;
        this.status = status;
        this.userPost = userPost;
        this.postCategories = postCategories;
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

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public PostStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PostStatusEnum status) {
        this.status = status;
    }

    public User getUserPost() {
        return userPost;
    }

    public void setUserPost(User userPost) {
        this.userPost = userPost;
    }

    public Set<PostCategory> getPostCategories() {
        return postCategories;
    }

    public void setPostCategories(Set<PostCategory> postCategories) {
        this.postCategories = postCategories;
    }

    
}
