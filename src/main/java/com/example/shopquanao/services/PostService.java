/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopquanao.enums.PostStatusEnum;
import com.example.shopquanao.exceptions.CommonException;
import com.example.shopquanao.models.Post;
import com.example.shopquanao.models.PostCategory;
import com.example.shopquanao.models.User;
import com.example.shopquanao.repositories.PostCategoryRepository;
import com.example.shopquanao.repositories.PostRepository;
import com.example.shopquanao.requestDto.PostRequestDTO;
import com.example.shopquanao.responseDto.PostDTO;

/**
 *
 * @author haidu
 */
@Service
public class PostService extends BaseService<Post, String, PostDTO, PostRequestDTO>{
    
    @Autowired
    private PostRepository pr;
    
    @Autowired
    private PostCategoryRepository pcr;
    
    @Autowired
    private AuthService as;

    @Override
    protected PostRepository getRepository() {
        return pr;
    }

    @Override
    protected Function<Post, PostDTO> getMapper() {
        return post -> new PostDTO(post);
    }

    @Override
    protected Class<Post> getEntityClass() {
        return Post.class;
    }
    
    @Override
	public Page<PostDTO> getAll(String searchText, Pageable pageable) {
    	User user = as.getCurrentUser();
    	
    	Boolean isAdmin = user.isAdmin(user.getRole().name());
		
    	if(searchText == null || searchText.trim().isEmpty()) {
    		if(isAdmin) {
    			return getRepository().findAll(pageable).map(getMapper());
    		}
    		return getRepository().findAllByStatus(PostStatusEnum.PUBLISHED, pageable).map(getMapper());
    	}
    	
		if(isAdmin) {
			return getRepository().findAllByNameContainingIgnoreCase(searchText, pageable).map(getMapper());
		}
		return getRepository().findAllByStatusAndNameContainingIgnoreCase(PostStatusEnum.PUBLISHED, searchText, pageable).map(getMapper());
	}

	private Set<PostCategory> handlePostCategory(Set<String> categoryIds) {
    	List<PostCategory> categoryList = pcr.findAllById(categoryIds);
    	
    	if (categoryList.size() != categoryIds.size()) 
    	    throw new CommonException(HttpStatus.BAD_REQUEST, "Some categories do not exist!");
    	
		Set<PostCategory> categorySet = new HashSet<>(categoryList);
		return categorySet;
    }
    
    private Post handleData(Post entity, PostRequestDTO data) { 
		
    	Optional<Post> eFindBySlug = getRepository().findBySlug(data.getSlug());
    	
        if (eFindBySlug.isPresent() && !eFindBySlug.get().getId().equals(entity.getId())) {
             throw new CommonException(HttpStatus.BAD_REQUEST, "Slug is exist!");
        }
    	System.out.println(as.getCurrentUser());
		entity.setName(data.getName());
		entity.setImage(data.getImage());
		entity.setShortContent(data.getShortContent());
		entity.setContent(data.getContent());
		entity.setSlug(data.getSlug());
		entity.setStatus(data.getStatus());
		entity.setTags(data.getTags());
		entity.setUserPost(as.getCurrentUser());
		entity.setPostCategories(handlePostCategory(data.getPostCategoryIds()));
		
		Post dataSave = getRepository().save(entity);
		return dataSave;
    }

	@Override
	@Transactional
	public Optional<PostDTO> create(PostRequestDTO data) {
		Post entity = new Post();
		return Optional.ofNullable(getMapper().apply(handleData(entity, data)));
	}

	@Override
	@Transactional
	public Optional<PostDTO> update(String id, PostRequestDTO data) {
		Post entity = getRepository().findById(id).orElseThrow(()
                -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
		return Optional.ofNullable(getMapper().apply(handleData(entity, data)));
	}
    
}
