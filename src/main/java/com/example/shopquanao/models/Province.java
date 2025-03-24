/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author haidu
 */
@Entity
@Table(name = "Province")
public class Province extends BaseEntity{
    
    @Column(nullable = false, length = 191)
    private String name;
    
    @OneToMany(mappedBy = "provinceDistrict", cascade = CascadeType.ALL)
    private List<District> districts;

    public Province() {
    }

    public Province(String id, String name, List<District> districts) {
        this.setId(id);
        this.name = name;
        this.districts= districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
    
}
