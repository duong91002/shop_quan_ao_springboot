/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import java.util.List;

import com.example.shopquanao.enums.PostStatusEnum;
import com.example.shopquanao.models.Post;

/**
 *
 * @author haidu
 */
public class PostDTO extends BaseDTO{
    private String name;
    private String slug;
    private String shortContent;
    private String content;
    private String image;
    private List<String> tags;
    private PostStatusEnum status;

    public PostDTO(Post p) {
        super(p.getId(), p.getCreatedAt(), p.getUpdatedAt());
        this.name = p.getName();
        this.slug = p.getSlug();
        this.shortContent = p.getShortContent();
        this.content = p.getContent();
        this.image = p.getImage();
        this.tags = p.getTags();
        this.status = p.getStatus();
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
    
    
}
