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
@Table(name = "OptionType")
public class OptionType extends BaseEntity{
    
    @Column(nullable = false, length = 191)
    private String name;
    
    @Column(nullable = false, length = 191)
    private String value;
    
    @Column(length = 191)
    private String guideLink;

    @OneToMany(mappedBy = "optionTypeOption", cascade = CascadeType.ALL)
    private List<Option> options;
    
    public OptionType() {
    }

    public OptionType(String id, String name, String value, String guideLink, List<Option> options) {
        this.setId(id);
        this.name = name;
        this.value = value;
        this.guideLink = guideLink;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGuideLink() {
        return guideLink;
    }

    public void setGuideLink(String guideLink) {
        this.guideLink = guideLink;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    
}
