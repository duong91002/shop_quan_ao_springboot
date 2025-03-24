/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.shopquanao.exceptions.CommonException;
import com.example.shopquanao.models.District;
import com.example.shopquanao.models.Province;
import com.example.shopquanao.repositories.DistrictRepository;
import com.example.shopquanao.repositories.ProvinceRepository;
import com.example.shopquanao.requestDto.DistrictRequestDTO;
import com.example.shopquanao.responseDto.DistrictDTO;

/**
 *
 * @author haidu
 */
@Service
public class DistrictService extends BaseService<District, String, DistrictDTO, DistrictRequestDTO>{

    @Autowired
    private DistrictRepository dr;
    
    @Autowired
    private ProvinceRepository pr;
    
    @Override
    protected DistrictRepository getRepository() {
        return dr;
    }

    @Override
    protected Function<District, DistrictDTO> getMapper() {
        return district -> new DistrictDTO(district);
    }

    @Override
    protected Class<District> getEntityClass() {
        return District.class;
    }
    
    
    
    @Override
	public Page<DistrictDTO> getAll(String searchText, Pageable pageable) {
    	if(searchText == null || searchText.trim().isEmpty()) {
    		return getRepository().findAll(pageable).map(getMapper());
    	}
    	
    	return getRepository().findAllByNameContainingIgnoreCase(searchText, pageable).map(getMapper());
	}

	public List<DistrictDTO> getAllUnlimited(String province){
    	
    	if(province == null || province.trim().isEmpty()) {
    		return getRepository().findAll().stream().map(DistrictDTO:: new).collect(Collectors.toList());
    	}
    	
    	return getRepository().getAllByProvinceDistrictId(province).stream().map(DistrictDTO:: new).collect(Collectors.toList());
    }
    
    private District handleData(District d, DistrictRequestDTO data) {
		
    	Province province = pr.findById(data.getProvinceId()).orElseThrow(()-> new CommonException(HttpStatus.BAD_REQUEST, "Province isn't exist"));
			
		d.setName(data.getName());
		d.setProvinceDistrict(province);
		
		District cSave = getRepository().save(d);
		return cSave;
	}

	@Override
	public Optional<DistrictDTO> create(DistrictRequestDTO data) {
		District d = new District();
		return Optional.ofNullable(getMapper().apply(handleData(d, data)));
	}

	@Override
	public Optional<DistrictDTO> update(String id, DistrictRequestDTO data) {
		District entityFind = getRepository().findById(id).orElseThrow(()
                -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
		return Optional.ofNullable(getMapper().apply(handleData(entityFind, data)));
	}
}
