/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.requestDto;

import com.example.shopquanao.enums.BannerStatusEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author haidu
 */
public class BannerRequestDTO {
    
    @NotBlank
    private String name;
    
    @NotNull
    private BannerStatusEnum status;
    
    @NotBlank
    private String redirectUrl;

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
