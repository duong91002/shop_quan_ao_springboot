package com.example.shopquanao.enums;

public enum PostStatusEnum {
	DRAFT("DRAFT"), PUBLISHED("PUBLISHED");
	
	private final String value;
	
	PostStatusEnum(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
