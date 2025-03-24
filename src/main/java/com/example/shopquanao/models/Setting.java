/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.shopquanao.enums.SettingKeyEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author haidu
 */
@Entity
@Table(name = "Setting")
public class Setting {
    
    @Id
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "`key`")
    private SettingKeyEnum key;

    @Column(nullable = false, length =191)
    private String value;
    
    @CreatedDate
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
    
    public Setting() {
    }

    public Setting(SettingKeyEnum key, String value) {
        this.key = key;
        this.value = value;
    }

    public SettingKeyEnum getKey() {
		return key;
	}

	public void setKey(SettingKeyEnum key) {
		this.key = key;
	}

	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

   
}
