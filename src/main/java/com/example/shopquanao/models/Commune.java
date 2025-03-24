/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author haidu
 */
@Entity
@Table(name = "Commune")
public class Commune extends BaseEntity{
    
    @Column(nullable = false, length = 191)
    private String name;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "districtId")
    private District districtCommune;

    @OneToMany(mappedBy = "communeOrder", cascade = CascadeType.ALL)
    private List<Order> orders;
    
    public Commune() {
    }

    public Commune(String id, String name, District districtCommune, List<Order> orders) {
        this.setId(id);
        this.name = name;
        this.districtCommune = districtCommune;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public District getDistrictCommune() {
        return districtCommune;
    }

    public void setDistrictCommune(District districtCommune) {
        this.districtCommune = districtCommune;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    
}
