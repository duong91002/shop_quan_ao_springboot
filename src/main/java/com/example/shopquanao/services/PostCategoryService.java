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
import com.example.shopquanao.models.PostCategory;
import com.example.shopquanao.repositories.PostCategoryRepository;
import com.example.shopquanao.requestDto.PostCategoryRequestDTO;
import com.example.shopquanao.responseDto.PostCategoryDTO;

/**
 *
 * @author haidu
 */
@Service
public class PostCategoryService extends BaseService<PostCategory, String, PostCategoryDTO, PostCategoryRequestDTO>{

    @Autowired
    private PostCategoryRepository pr;
    
    @Override
    protected PostCategoryRepository getRepository() {
        return pr;
    }

    @Override
    protected Function<PostCategory, PostCategoryDTO> getMapper() {
      return postCategory -> new PostCategoryDTO(postCategory);
    }

    @Override
    protected Class<PostCategory> getEntityClass() {
        return PostCategory.class;
    }
    
    
    
    @Override
	public Page<PostCategoryDTO> getAll(String searchText, Pageable pageable) {
    	if(searchText == null || searchText.trim().isEmpty()) {
    		return getRepository().findAll(pageable).map(getMapper());
    	}
    	
    	return getRepository().findAllByNameContainingIgnoreCase(searchText, pageable).map(getMapper());
	}

	private PostCategory handleData(PostCategory entity, PostCategoryRequestDTO data) {
		Optional<PostCategory> eFindBySlug = getRepository().findBySlug(data.getSlug());
		if (eFindBySlug.isPresent() && eFindBySlug.get().getId() != entity.getId()) throw new CommonException(HttpStatus.BAD_REQUEST, "Slug is exist!");
		
		entity.setName(data.getName());
		entity.setSlug(data.getSlug());
		
		PostCategory cSave = getRepository().save(entity);
		return cSave;
	}
    
    @Override
	public Optional<PostCategoryDTO> create(PostCategoryRequestDTO data) {
    	PostCategory entity = new PostCategory();
		return Optional.ofNullable(getMapper().apply(handleData(entity, data)));
	}

	@Override
	public Optional<PostCategoryDTO> update(String id, PostCategoryRequestDTO data) {
		PostCategory entityFind = getRepository().findById(id).orElseThrow(()
	                -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
		return Optional.ofNullable(getMapper().apply(handleData(entityFind, data)));
	}
    
}
