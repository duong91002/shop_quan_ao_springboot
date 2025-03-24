/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.services;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.shopquanao.exceptions.CommonException;
import com.example.shopquanao.models.Option;
import com.example.shopquanao.models.OptionType;
import com.example.shopquanao.repositories.OptionRepository;
import com.example.shopquanao.repositories.OptionTypeRepository;
import com.example.shopquanao.requestDto.OptionRequestDTO;
import com.example.shopquanao.responseDto.OptionDTO;

/**
 *
 * @author haidu
 */
@Service
public class OptionService extends BaseService<Option, String, OptionDTO, OptionRequestDTO>{

    @Autowired
    private OptionRepository or;
    
    @Autowired
    private OptionTypeRepository otr;
    
    @Override
    protected OptionRepository getRepository() {
        return or;
    }

    @Override
    protected Function<Option, OptionDTO> getMapper() {
        return option -> new OptionDTO(option);
    }

    @Override
    protected Class<Option> getEntityClass() {
        return Option.class;
    }
    
    
    
    @Override
	public Page<OptionDTO> getAll(String searchText, Pageable pageable) {
    	if(searchText == null || searchText.trim().isEmpty()) {
    		return getRepository().findAll(pageable).map(getMapper());
    	}
    	
    	return getRepository().findAllByNameContainingIgnoreCase(searchText, pageable).map(getMapper());
	}

	private Option handleData(Option entity, OptionRequestDTO data) {
    	Optional<Option> eFindByValue = getRepository().findByValue(data.getValue());
		if (eFindByValue.isPresent() && eFindByValue.get().getId() != entity.getId()) throw new CommonException(HttpStatus.BAD_REQUEST, "Value is exist!");
		
		Optional<OptionType> optionType = otr.findById(data.getTypeId());
		if(optionType.isEmpty()) throw new CommonException(HttpStatus.BAD_REQUEST, "Option type isn't exist!");
		
		entity.setName(data.getName());
		entity.setValue(data.getValue());
		entity.setOptionTypeOption(optionType.get());
		
		Option dataSave = getRepository().save(entity);
		return dataSave;
    }

	@Override
	public Optional<OptionDTO> create(OptionRequestDTO data) {
		Option entity = new Option();
		return Optional.ofNullable(getMapper().apply(handleData(entity, data)));
	}

	@Override
	public Optional<OptionDTO> update(String id, OptionRequestDTO data) {
		Option entity = getRepository().findById(id).orElseThrow(()
				-> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
		return Optional.ofNullable(getMapper().apply(handleData(entity, data)));
	}
    
}
