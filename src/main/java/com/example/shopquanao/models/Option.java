/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author haidu
 */
@Entity
@Table(name = "`Option`")
public class Option extends BaseEntity{
    
    @Column(nullable = false, length = 191)
    private String name;
    
    @Column(nullable = false, length = 191, unique = true)
    private String value;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "typeId")
    private OptionType optionTypeOption;
    
    @ManyToMany(mappedBy = "variantOptions")
    private Set<Variant> variants;
    
    public Option() {
    }

    public Option(String id, String name, String value, OptionType optionTypeOption, Set<Variant> variants) {
        this.setId(id);
        this.name = name;
        this.value = value;
        this.optionTypeOption = optionTypeOption;
        this.variants = variants;
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

    public OptionType getOptionTypeOption() {
        return optionTypeOption;
    }

    public void setOptionTypeOption(OptionType optionTypeOption) {
        this.optionTypeOption = optionTypeOption;
    }

    public Set<Variant> getVariants() {
        return variants;
    }

    public void setVariants(Set<Variant> variants) {
        this.variants = variants;
    }
    
}
