/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import com.example.shopquanao.models.Province;

/**
 *
 * @author haidu
 */
public class ProvinceDTO extends BaseDTO{
    private String name;
    public ProvinceDTO(Province p) {
        super(p.getId(), p.getCreatedAt(), p.getUpdatedAt());
        this.name = p.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
}
