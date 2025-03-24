/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.services;

import com.example.shopquanao.enums.SettingKeyEnum;
import com.example.shopquanao.exceptions.CommonException;
import com.example.shopquanao.models.Setting;
import com.example.shopquanao.repositories.SettingRepository;
import com.example.shopquanao.requestDto.SettingRequestDTO;
import com.example.shopquanao.responseDto.SettingDTO;

import java.util.Optional;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author haidu
 */
@Service
public class SettingService {

    @Autowired
    private SettingRepository sr;
    
    private Function<Setting, SettingDTO> getMapper() {
        return setting -> new SettingDTO(setting);
    }
    
    public Page<SettingDTO> getAll(Pageable pageable) {
        return sr.findAll(pageable).map(getMapper());
    }


	public Optional<SettingDTO> update(String id, SettingRequestDTO data) {
		SettingKeyEnum key = SettingKeyEnum.valueOf(id);
        
        Setting entityFind = sr.findByKey(key).orElseThrow(()
                -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with key: " + id ));
        
        entityFind.setValue(data.getValue());
        
        Setting save = sr.save(entityFind);
		return Optional.ofNullable(getMapper().apply(save));
	}
    
    
}
