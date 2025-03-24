package com.example.shopquanao.enums;

public enum UserRoleEnum {
	USER("USER"), ADMIN("ADMIN");
	
	private final String value;

	UserRoleEnum(String value) {
        this.value = value;
    }
	
	public String getValue() {
		return value;
	}
}
