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
import com.example.shopquanao.models.OptionType;
import com.example.shopquanao.repositories.OptionTypeRepository;
import com.example.shopquanao.requestDto.OptionTypeRequestDTO;
import com.example.shopquanao.responseDto.OptionTypeDTO;

/**
 *
 * @author haidu
 */
@Service
public class OptionTypeService extends BaseService<OptionType, String, OptionTypeDTO, OptionTypeRequestDTO>{

    @Autowired
    private OptionTypeRepository otr;
    
    @Override
    protected OptionTypeRepository getRepository() {
        return otr;
    }

    @Override
    protected Function<OptionType, OptionTypeDTO> getMapper() {
        return optionType -> new OptionTypeDTO(optionType);
    }

    @Override
    protected Class<OptionType> getEntityClass() {
        return OptionType.class;
    }
    
    
    @Override
	public Page<OptionTypeDTO> getAll(String searchText, Pageable pageable) {
    	if(searchText == null || searchText.trim().isEmpty()) {
    		return getRepository().findAll(pageable).map(getMapper());
    	}
    	
    	return getRepository().findAllByNameContainingIgnoreCase(searchText, pageable).map(getMapper());
	}

	public List<OptionTypeDTO> getAllUnlimited(){
    	List<OptionType> list =  getRepository().findAll();
    	return list.stream().map(OptionTypeDTO:: new).collect(Collectors.toList());
    }
    
    private OptionType handleData(OptionType entity, OptionTypeRequestDTO data) {
    	
		Optional<OptionType> eFindByValue = getRepository().findByValue(data.getValue());
		if (eFindByValue.isPresent() && eFindByValue.get().getId() != entity.getId()) throw new CommonException(HttpStatus.BAD_REQUEST, "Value is exist!");
		
		entity.setName(data.getName());
		entity.setGuideLink(data.getGuideLink());
		entity.setValue(data.getValue());
		
		OptionType dataSave = getRepository().save(entity);
		return dataSave;
    }

	@Override
	public Optional<OptionTypeDTO> create(OptionTypeRequestDTO data) {
		OptionType entity = new OptionType();
		return Optional.ofNullable(getMapper().apply(handleData(entity, data)));
	}

	@Override
	public Optional<OptionTypeDTO> update(String id, OptionTypeRequestDTO data) {
		OptionType entity = getRepository().findById(id).orElseThrow(()
				-> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
		return Optional.ofNullable(getMapper().apply(handleData(entity, data)));
	}
    
}
