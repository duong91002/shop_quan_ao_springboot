package com.example.shopquanao.enums;

public enum ProductStatusEnum {
	DRAFT("DRAFT"), PUBLISHED("PUBLISHED"), OUT_OF_STOCK("OUT_OF_STOCK");
	
	private final String value;

	ProductStatusEnum(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}
}
