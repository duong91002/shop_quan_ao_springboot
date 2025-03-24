package com.example.shopquanao.enums;

public enum VariantStatusEnum {
	
	DRAFT("DRAFT"), PUBLISHED("PUBLISHED"), OUT_OF_STOCK("OUT_OF_STOCK");
	
	private final String value;
	
	VariantStatusEnum(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	
}
