/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.services;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.shopquanao.models.Province;
import com.example.shopquanao.repositories.ProvinceRepository;
import com.example.shopquanao.requestDto.ProvinceRequestDTO;
import com.example.shopquanao.responseDto.ProvinceDTO;

/**
 *
 * @author haidu
 */
@Service
public class ProvinceService extends BaseService<Province, String, ProvinceDTO, ProvinceRequestDTO>{

    @Autowired
    private ProvinceRepository pr;
    
    @Override
    protected ProvinceRepository getRepository() {
        return pr;
    }

    @Override
    protected Function<Province, ProvinceDTO> getMapper() {
        return province -> new ProvinceDTO(province);
    }

    @Override
    protected Class<Province> getEntityClass() {
        return Province.class;
    }
    
    public List<ProvinceDTO> getAllUnlimited(){
    	List<Province> list =  getRepository().findAll();
    	return list.stream().map(ProvinceDTO:: new).collect(Collectors.toList());
    }

	@Override
	public Page<ProvinceDTO> getAll(String searchText, Pageable pageable) {
		if(searchText == null || searchText.trim().isEmpty()) {
    		return getRepository().findAll(pageable).map(getMapper());
    	}
    	
    	return getRepository().findAllByNameContainingIgnoreCase(searchText, pageable).map(getMapper());
	}
    
    
}
