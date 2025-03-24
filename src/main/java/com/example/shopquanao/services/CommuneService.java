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
import com.example.shopquanao.models.Commune;
import com.example.shopquanao.models.District;
import com.example.shopquanao.repositories.CommuneRepository;
import com.example.shopquanao.repositories.DistrictRepository;
import com.example.shopquanao.requestDto.CommuneRequestDTO;
import com.example.shopquanao.responseDto.CommuneDTO;

/**
 *
 * @author haidu
 */
@Service
public class CommuneService extends BaseService<Commune, String, CommuneDTO, CommuneRequestDTO>{

    @Autowired
    private CommuneRepository cr;
    
    @Autowired
    private DistrictRepository dr;
    
    @Override
    protected CommuneRepository getRepository() {
        return cr;
    }

    @Override
    protected Function<Commune, CommuneDTO> getMapper() {
        return commune -> new CommuneDTO(commune);
    }

    @Override
    protected Class<Commune> getEntityClass() {
        return Commune.class;
    }
    
    
    
    @Override
	public Page<CommuneDTO> getAll(String searchText, Pageable pageable) {
    	
    	if(searchText == null || searchText.trim().isEmpty()) {
    		return getRepository().findAll(pageable).map(getMapper());
    	}
    	
    	return getRepository().findAllByNameContainingIgnoreCase(searchText, pageable).map(getMapper());
	}

	public List<CommuneDTO> getAllUnlimited(String districtId){
    	if(districtId == null || districtId.trim().isEmpty()) {
    		return getRepository().findAll().stream().map(CommuneDTO:: new).collect(Collectors.toList());
    	}
    	return getRepository().getAllByDistrictCommuneId(districtId).stream().map(CommuneDTO:: new).collect(Collectors.toList());
    }
    
    private Commune handleData(Commune c, CommuneRequestDTO data) {
		
    	District district = dr.findById(data.getDistrictId()).orElseThrow(()-> new CommonException(HttpStatus.BAD_REQUEST, "District isn't exist"));
			
		c.setName(data.getName());
		c.setDistrictCommune(district);
		
		Commune cSave = getRepository().save(c);
		return cSave;
	}

	@Override
	public Optional<CommuneDTO> create(CommuneRequestDTO data) {
		Commune c = new Commune();
		return Optional.ofNullable(getMapper().apply(handleData(c, data)));
	}

	@Override
	public Optional<CommuneDTO> update(String id, CommuneRequestDTO data) {
		Commune entityFind = getRepository().findById(id).orElseThrow(()
                -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
		return Optional.ofNullable(getMapper().apply(handleData(entityFind, data)));
	}
    
}
