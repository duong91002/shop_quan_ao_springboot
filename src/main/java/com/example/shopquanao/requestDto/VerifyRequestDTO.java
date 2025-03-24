package com.example.shopquanao.requestDto;

import jakarta.validation.constraints.NotBlank;

public class VerifyRequestDTO {
	
	@NotBlank
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	
	
}
