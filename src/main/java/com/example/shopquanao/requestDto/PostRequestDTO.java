/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.requestDto;

import java.util.List;
import java.util.Set;

import com.example.shopquanao.enums.PostStatusEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author haidu
 */
public class PostRequestDTO {
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String slug;
    
    @NotBlank
    private String shortContent;
    
    @NotBlank
    private String content;
    
    @NotBlank
    private String image;
    
    private List<String> tags;
    
    @NotNull
    private PostStatusEnum status;
    
    @NotEmpty
    private Set<String> postCategoryIds;

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

	public Set<String> getPostCategoryIds() {
		return postCategoryIds;
	}

	public void setPostCategoryIds(Set<String> postCategoryIds) {
		this.postCategoryIds = postCategoryIds;
	}
}
