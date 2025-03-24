/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import com.example.shopquanao.models.User;

/**
 *
 * @author haidu
 */
public class UserDTO extends BaseDTO{
    private String name;
    private String email;
    private String image;
    private Boolean isActive;

    public UserDTO(User u) {
        super(u.getId(), u.getCreatedAt(), u.getUpdatedAt());
        this.name = u.getName();
        this.email = u.getEmail();
        this.image = u.getImage();
        this.isActive = u.getIsActive();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
    
    
}
