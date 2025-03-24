package com.example.shopquanao.enums;

public enum OrderPaymentStatusEnum {
	UNPAID("UNPAID"), PAID("PAID");
	
	private final String value;
	OrderPaymentStatusEnum(String value){
		this.value =value;
	}
	public String getValue() {
		return value;
	}
}
