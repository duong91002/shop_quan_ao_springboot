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
@Table(name = "District")
public class District extends BaseEntity{
    
    @Column(nullable = false, length = 191)
    private String name;
    
    @OneToMany(mappedBy = "districtCommune", cascade = CascadeType.ALL)
    private List<Commune> communes;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "provinceId")
    private Province provinceDistrict;

    public District() {
    }

    public District(String id, String name, List<Commune> communes, Province provinceDistrict) {
        this.name = name;
        this.communes = communes;
        this.provinceDistrict = provinceDistrict;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }

    public Province getProvinceDistrict() {
        return provinceDistrict;
    }

    public void setProvinceDistrict(Province provinceDistrict) {
        this.provinceDistrict = provinceDistrict;
    }
    
}
