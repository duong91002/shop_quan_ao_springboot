/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import com.example.shopquanao.enums.BannerStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

/**
 *
 * @author haidu
 */
@Entity
@Table(name = "Banner")
public class Banner extends BaseEntity {

    @Column(nullable = false, length = 191)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('DRAFT', 'PUBLISHED') DEFAULT 'DRAFT'")
    private BannerStatusEnum status;

    @Column(length = 191)
    private String redirectUrl;

    public Banner() {
    }

    public Banner(String id, String name, BannerStatusEnum status, String redirectUrl) {
        this.setId(id);
        this.name = name;
        this.status = status;
        this.redirectUrl = redirectUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BannerStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BannerStatusEnum status) {
        this.status = status;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

}
