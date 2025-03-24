/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

import com.example.shopquanao.enums.UserRoleEnum;

/**
 *
 * @author haidu
 */
@Entity
@Table(name = "User")
public class User extends BaseEntity{
    
    @Column(length = 191)
    private String name;
    
    @Column(nullable = false, length = 191, unique = true)
    private String email;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('USER', 'ADMIN') DEFAULT 'USER'")
    private UserRoleEnum role = UserRoleEnum.USER;
    
    @Column(length = 191)
    private String password;
    
    @Column(length = 191)
    private String image;
    
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isActive = false;
    
    @OneToMany(mappedBy = "userPost", cascade = CascadeType.ALL)
    private List<Post> posts;
    
    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL)
    private List<Order> orders;

    public User() {
    }

    public User(String id, String name, UserRoleEnum role, String email, String password, String image, Boolean isActive, List<Post> posts, List<Order> orders) {
        this.setId(id);
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
        this.image = image;
        this.isActive = isActive;
        this.posts = posts;
        this.orders = orders;
    }
    
    public boolean isAdmin(String role) {
    	if(role == "ADMIN") return true;
    	return false;
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

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

	public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
    
}
