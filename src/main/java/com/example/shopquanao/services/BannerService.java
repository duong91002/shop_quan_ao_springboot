/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.services;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.shopquanao.enums.BannerStatusEnum;
import com.example.shopquanao.models.Banner;
import com.example.shopquanao.models.User;
import com.example.shopquanao.repositories.BannerRepository;
import com.example.shopquanao.requestDto.BannerRequestDTO;
import com.example.shopquanao.responseDto.BannerDTO;

/**
 *
 * @author haidu
 */
@Service
public class BannerService extends BaseService<Banner, String, BannerDTO, BannerRequestDTO> {

	@Autowired
	private BannerRepository br;

	@Autowired
	private AuthService as;

	@Override
	protected BannerRepository getRepository() {
		return br;
	}

	@Override
	protected Function<Banner, BannerDTO> getMapper() {
		return banner -> new BannerDTO(banner);
	}

	@Override
	protected Class<Banner> getEntityClass() {
		return Banner.class;
	}

	@Override
	public Page<BannerDTO> getAll(String searchText, Pageable pageable) {
		User user = as.getCurrentUser();

		Boolean isAdmin = user.isAdmin(user.getRole().name());

		if (searchText == null || searchText.trim().isEmpty()) {
			if (isAdmin) {
				return getRepository().findAll(pageable).map(getMapper());
			}
			return getRepository().findAllByStatus(BannerStatusEnum.PUBLISHED, pageable).map(getMapper());
		}
		if (isAdmin) {
			return getRepository().findAllByNameContainingIgnoreCase(searchText, pageable).map(getMapper());
		}
		return getRepository()
				.findAllByStatusAndNameContainingIgnoreCase(BannerStatusEnum.PUBLISHED, searchText, pageable)
				.map(getMapper());

	}

}
