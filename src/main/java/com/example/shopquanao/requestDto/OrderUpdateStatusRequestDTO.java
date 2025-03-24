package com.example.shopquanao.requestDto;

import com.example.shopquanao.enums.OrderStatusEnum;

import jakarta.validation.constraints.NotNull;

public class OrderUpdateStatusRequestDTO {

	@NotNull
	private OrderStatusEnum status;

	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}
	
	
	
}
