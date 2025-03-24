/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import com.example.shopquanao.enums.BannerStatusEnum;
import com.example.shopquanao.models.Banner;

/**
 *
 * @author haidu
 */
public class BannerDTO extends BaseDTO{
    private String name;
    private BannerStatusEnum status;
    private String redirectUrl;

    public BannerDTO() {
    }

    public BannerDTO(Banner b) {
        super(b.getId(), b.getCreatedAt(), b.getUpdatedAt());
        this.name = b.getName();
        this.status = b.getStatus();
        this.redirectUrl = b.getRedirectUrl();
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
