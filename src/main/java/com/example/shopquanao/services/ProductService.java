/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopquanao.enums.ProductStatusEnum;
import com.example.shopquanao.exceptions.CommonException;
import com.example.shopquanao.models.Category;
import com.example.shopquanao.models.Option;
import com.example.shopquanao.models.Product;
import com.example.shopquanao.models.User;
import com.example.shopquanao.models.Variant;
import com.example.shopquanao.repositories.CategoryRepository;
import com.example.shopquanao.repositories.OptionRepository;
import com.example.shopquanao.repositories.ProductRepository;
import com.example.shopquanao.requestDto.ProductRequestDTO;
import com.example.shopquanao.responseDto.ProductDTO;

/**
 *
 * @author haidu
 */
@Service
public class ProductService extends BaseService<Product, String, ProductDTO, ProductRequestDTO>{
    
    @Autowired
    private ProductRepository pr;
    
    @Autowired
    private CategoryRepository cr;
    
    @Autowired
    private OptionRepository or;
    
    @Autowired
    private AuthService as;

    @Override
    protected ProductRepository getRepository() {
       return pr;
    }

    @Override
    protected Function<Product, ProductDTO> getMapper() {
        return product -> new ProductDTO(product);
    }

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }
    
    private Set<Category> handleCategory(Set<String> categoryIds) {
    	List<Category> categoryList = cr.findAllById(categoryIds);
    	if (categoryList.size() != categoryIds.size()) throw new CommonException(HttpStatus.BAD_REQUEST, "Some categories do not exist!");
		Set<Category> categorySet = new HashSet<>(categoryList);
		return categorySet;
    }
   
    private Set<Option> handleOption(List<String>  optionIds){
    	List<Option> optionList = or.findAllById(optionIds);
    	if (optionList.size() != optionIds.size()) throw new CommonException(HttpStatus.BAD_REQUEST, "Some options do not exist!");
		Set<Option> optionSet = new HashSet<>(optionList);
    	return optionSet;
    }
    private List<Variant> handleVariant(Product entity, ProductRequestDTO data) {
    	List<Variant> variants = data.getVariants().stream()
			    .map(dto -> {
			        Variant variant = new Variant();
			        variant.setName(dto.getName());
			        variant.setPrice(dto.getPrice());
			        variant.setDiscount(dto.getDiscount());
			        variant.setStatus(dto.getStatus());
			        
			        variant.setProductVariant(entity);
			        
			        variant.setVariantOptions(handleOption(dto.getOptionIds()));
			        return variant;
			    })
			    .collect(Collectors.toList());
    	return variants;
    }
    
    private Product handleData(Product entity, ProductRequestDTO data) {
    	
        Optional<Product> eFindBySlug = getRepository().findBySlug(data.getSlug());
        if (eFindBySlug.isPresent() && !eFindBySlug.get().getId().equals(entity.getId())) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "Slug is exist!");
        }

        entity.setName(data.getName());
        entity.setSlug(data.getSlug());
        entity.setShortDescription(data.getShortDescription());
        entity.setDescription(data.getDescription());
        entity.setImages(data.getImages());
        entity.setTags(data.getTags());
        entity.setStatus(data.getStatus());

        entity.setProductCategories(handleCategory(data.getProductCategoryIds()));

        List<Variant> variants = handleVariant(entity, data);
        
        if (!variants.isEmpty()) {
            entity.setVariantProduct(variants.get(0));
            entity.setVariants(variants);
        } else {
            entity.setVariantProduct(null);
        }

        Product saveData = getRepository().save(entity);
        
        return saveData;
    }
    
    @Override
	public Page<ProductDTO> getAll(String searchText, Pageable pageable) {
    	User user = as.getCurrentUser();
		Boolean isAdmin = user.isAdmin(user.getRole().name());
		
		if(searchText == null || searchText.trim().isEmpty()) {
			if(isAdmin) {
				return getRepository().findAll(pageable).map(getMapper());
			}
			return getRepository().findAllByStatusAndDeletedAtIsNull(ProductStatusEnum.PUBLISHED, pageable).map(getMapper());
		}
		if(isAdmin) {
			return getRepository().findAllByNameContainingIgnoreCase(searchText, pageable).map(getMapper());
		}
		return getRepository().findAllByStatusAndDeletedAtIsNullAndNameContainingIgnoreCase(ProductStatusEnum.PUBLISHED, searchText, pageable).map(getMapper());
	}
    
    @Override
	public Optional<ProductDTO> getById(String id) {
    	
    	User user = as.getCurrentUser();
		Boolean isAdmin = user.isAdmin(user.getRole().name());
		
		if(isAdmin) {
			Optional<Product> entity = getRepository().findById(id);
			
			if(entity.isEmpty()) throw new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: "+ id);
			
			return entity.map(getMapper());
		}
    	
		Optional<Product> entity = getRepository().findByIdAndStatusAndDeletedAtIsNull(id, ProductStatusEnum.PUBLISHED);
		
		if(entity.isEmpty()) throw new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: "+ id);
		
		return entity.map(getMapper());
	}
    
    @Override
    @Transactional
	public Optional<ProductDTO> create(ProductRequestDTO data) {
		Product entity = new Product();
		return Optional.ofNullable(getMapper().apply(handleData(entity, data)));
	}

	@Override
	@Transactional
	public Optional<ProductDTO> update(String id, ProductRequestDTO data) {
		Product entityFind = getRepository().findById(id).orElseThrow(()
	                -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
		return Optional.ofNullable(getMapper().apply(handleData(entityFind, data)));
	}

	@Override
	public void deleteById(String id) {
		Product entityFind = getRepository().findById(id).orElseThrow(()
                -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
		entityFind.setDeletedAt(LocalDateTime.now());
		getRepository().save(entityFind);
	}
}
