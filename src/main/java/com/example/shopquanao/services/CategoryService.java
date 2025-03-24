/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.shopquanao.exceptions.CommonException;
import com.example.shopquanao.models.Category;
import com.example.shopquanao.repositories.CategoryRepository;
import com.example.shopquanao.requestDto.CategoryRequestDTO;
import com.example.shopquanao.responseDto.CategoryDTO;

/**
 *
 * @author haidu
 */
@Service
public class CategoryService extends BaseService<Category, String, CategoryDTO, CategoryRequestDTO>{

    @Autowired
    private CategoryRepository cr;
    
    @Override
    protected CategoryRepository getRepository() {
       return cr;
    }

    @Override
    protected Function<Category, CategoryDTO> getMapper() {
        return category -> new CategoryDTO(category);
    }

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }
    
    private Category handleData(Category c, CategoryRequestDTO data) {
		
		if (data.getParentId() != null ) {
			
			Category parentCategory= cr.findById(data.getParentId()).orElseThrow(()-> new CommonException(HttpStatus.BAD_REQUEST, "Parrent category isn't exist"));
			
			if (c.getId() == parentCategory.getId()) throw new CommonException(HttpStatus.BAD_REQUEST, "parent category mustn't same category!");
			
			c.setParentCategory(parentCategory);
		}
		
		Optional<Category> eFindBySlug = cr.findBySlug(data.getSlug());
		if (eFindBySlug.isPresent() && eFindBySlug.get().getId() != c.getId()) throw new CommonException(HttpStatus.BAD_REQUEST, "Slug is exist!");
		
		c.setName(data.getName());
		c.setImage(data.getImage());
		c.setDescription(data.getDescription());
		c.setSlug(data.getSlug());
		
		Category cSave = getRepository().save(c);
		return cSave;
	}
    
	@Override
	public Page<CategoryDTO> getAll(String searchText, Pageable pageable) {
		if(searchText == null || searchText.trim().isEmpty()) {
			return getRepository().findAllByDeletedAtIsNull(pageable).map(getMapper());
		}
		return getRepository().findAllByDeletedAtIsNullAndNameContainingIgnoreCase(searchText, pageable).map(getMapper());
	}

	@Override
	public Optional<CategoryDTO> getById(String id) {
		Optional<Category> entity = getRepository().findByIdAndDeletedAtIsNull(id);
		
		if(entity.isEmpty()) throw new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: "+ id);
		
		return entity.map(getMapper());
	}

	@Override
	public Optional<CategoryDTO> create(CategoryRequestDTO data) {
		Category c = new Category();
		return Optional.ofNullable(getMapper().apply(handleData(c, data)));
	}

	@Override
	public Optional<CategoryDTO> update(String id, CategoryRequestDTO data) {
		 Category entityFind = getRepository().findById(id).orElseThrow(()
	                -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
		return Optional.ofNullable(getMapper().apply(handleData(entityFind, data)));
	}

	@Override
	public void deleteById(String id) {
		Category entityFind = getRepository().findById(id).orElseThrow(()
                -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
		entityFind.setDeletedAt(LocalDateTime.now());
		cr.save(entityFind);
	}
    
	
}
