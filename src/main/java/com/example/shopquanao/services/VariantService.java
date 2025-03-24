package com.example.shopquanao.services;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.shopquanao.enums.VariantStatusEnum;
import com.example.shopquanao.models.User;
import com.example.shopquanao.models.Variant;
import com.example.shopquanao.repositories.VariantRepository;
import com.example.shopquanao.requestDto.VariantRequestDTO;
import com.example.shopquanao.responseDto.VariantDTO;

@Service
public class VariantService extends BaseService<Variant, String, VariantDTO, VariantRequestDTO>{

	@Autowired
    private VariantRepository vr;
	
	@Autowired
    private AuthService as;
	
	@Override
	protected VariantRepository getRepository() {
		// TODO Auto-generated method stub
		return vr;
	}

	@Override
	protected Function<Variant, VariantDTO> getMapper() {
		// TODO Auto-generated method stub
		return variant -> new VariantDTO(variant);
	}

	@Override
	protected Class<Variant> getEntityClass() {
		// TODO Auto-generated method stub
		return Variant.class;
	}

	@Override
	public Page<VariantDTO> getAll(String searchText, Pageable pageable) {
		User user = as.getCurrentUser();
		Boolean isAdmin = user.isAdmin(user.getRole().name());
		
		if(searchText == null || searchText.trim().isEmpty()) {
			if(isAdmin) {
				return getRepository().findAll(pageable).map(getMapper());
			}
			return getRepository().findAllByStatus(VariantStatusEnum.PUBLISHED, pageable).map(getMapper());
		}
		
		if(isAdmin) {
			return getRepository().findAllByNameContainingIgnoreCase(searchText, pageable).map(getMapper());
		}
		return getRepository().findAllByStatusAndNameContainingIgnoreCase(VariantStatusEnum.PUBLISHED, searchText, pageable).map(getMapper());
	}

}
