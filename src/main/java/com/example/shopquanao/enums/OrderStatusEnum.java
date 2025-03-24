package com.example.shopquanao.enums;

public enum OrderStatusEnum {
	PENDING("PENDING"), COMPLETED("COMPLETED"), CANCELLED("CANCELLED");
	
	private final String value;
	OrderStatusEnum(String value){
		this.value= value;
	}
	public String getValue() {
		return value;
	}
}
