package com.example.shopquanao.enums;



public enum BannerStatusEnum {
	DRAFT("DRAFT"), PUBLISHED("PUBLISHED");
	
	private final String value;

	BannerStatusEnum(String value) {
        this.value = value;
    }
	
	public String getValue() {
		return value;
	}
   
}
