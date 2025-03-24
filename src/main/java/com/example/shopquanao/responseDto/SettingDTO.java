/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.responseDto;

import com.example.shopquanao.enums.SettingKeyEnum;
import com.example.shopquanao.models.Setting;

/**
 *
 * @author haidu
 */
public class SettingDTO {
    private SettingKeyEnum key;
    private String value;

    public SettingDTO(Setting s) {
        this.key = s.getKey();
        this.value = s.getValue();
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
