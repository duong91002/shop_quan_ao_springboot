/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopquanao.enums.OrderPaymentStatusEnum;
import com.example.shopquanao.enums.OrderStatusEnum;
import com.example.shopquanao.exceptions.CommonException;
import com.example.shopquanao.models.Commune;
import com.example.shopquanao.models.Order;
import com.example.shopquanao.models.OrderItem;
import com.example.shopquanao.models.User;
import com.example.shopquanao.models.Variant;
import com.example.shopquanao.repositories.CommuneRepository;
import com.example.shopquanao.repositories.OrderRepository;
import com.example.shopquanao.repositories.VariantRepository;
import com.example.shopquanao.requestDto.OrderItemRequestDTO;
import com.example.shopquanao.requestDto.OrderRequestDTO;
import com.example.shopquanao.requestDto.OrderUpdateStatusRequestDTO;
import com.example.shopquanao.responseDto.OrderDTO;

/**
 *
 * @author haidu
 */
@Service
public class OrderService extends BaseService<Order, String, OrderDTO, OrderRequestDTO> {

	@Autowired
	private OrderRepository or;

	@Autowired
	private CommuneRepository cr;

	@Autowired
	private VariantRepository vr;

	@Autowired
	private AuthService as;

	@Override
	protected OrderRepository getRepository() {
		return or;
	}

	@Override
	protected Function<Order, OrderDTO> getMapper() {
		return order -> new OrderDTO(order);
	}

	@Override
	protected Class<Order> getEntityClass() {
		return Order.class;
	}
	

	@Override
	public Page<OrderDTO> getAll(String searchText, Pageable pageable) {
		User user = as.getCurrentUser();

		Boolean isAdmin = user.isAdmin(user.getRole().name());

		if (searchText == null || searchText.trim().isEmpty()) {
			if (isAdmin) {
				return getRepository().findAll(pageable).map(getMapper());
			}
			return getRepository().findAllByUserOrderId(user.getId(), pageable).map(getMapper());
		}
		if (isAdmin) {
			return getRepository().findAllByCustomerEmailContainingIgnoreCase(searchText, pageable).map(getMapper());
		}
		return getRepository()
				.findAllByUserOrderIdAndCustomerEmailContainingIgnoreCase(user.getId(), searchText, pageable)
				.map(getMapper());

	}

	private double calculateTotal(int quantity, double price, double discount) {
		if (discount == 0)
			return quantity * price;
		return quantity * price * (1 - discount / 100);
	}

	private List<OrderItem> handleDataOrderItem(Order entity, List<OrderItemRequestDTO> data) {

		List<OrderItem> list = data.stream().map(t -> {
			Optional<Variant> variant = vr.findById(t.getVariantId());

			if (variant.isEmpty())
				throw new CommonException(HttpStatus.BAD_REQUEST, "Variant isn't exist!");

			OrderItem oi = new OrderItem();

			double price = variant.get().getPrice();
			int quantity = t.getQuantity();
			double discount = 0;

			if (variant.get().getDiscount() != null) {
				discount = variant.get().getDiscount();
			}

			oi.setQuantity(quantity);
			oi.setPrice(price);
			oi.setTotal(calculateTotal(quantity, price, discount));
			oi.setVariantOrderItem(variant.get());
			oi.setOrderOrderItem(entity);

			return oi;
		}).collect(Collectors.toList());

		return list;
	}

	private Order handleData(Order entity, OrderRequestDTO data) {
		Optional<Commune> commune = cr.findById(data.getCommuneId());

		if (commune.isEmpty())
			throw new CommonException(HttpStatus.BAD_REQUEST, "Commune isn't exist!");

		entity.setCommuneOrder(commune.get());

		List<OrderItem> orderItemList = handleDataOrderItem(entity, data.getOrderItems());

		entity.setOrderItems(orderItemList);

		User user = as.getCurrentUser();

		entity.setNotes(data.getNotes());
		entity.setPaymentStatus(OrderPaymentStatusEnum.UNPAID);
		entity.setStatus(OrderStatusEnum.PENDING);
		entity.setCustomerName(data.getCustomerName());
		entity.setCustomerEmail(data.getCustomerEmail());
		entity.setCustomerPhone(data.getCustomerPhone());
		entity.setCustomerAddress(data.getCustomerAddress());

		double totalAmount = 0;
		for (OrderItem o : orderItemList) {
			totalAmount += o.getTotal();
		}

		entity.setTotalAmount(totalAmount);
		entity.setUserOrder(user);

		Order dataSave = getRepository().save(entity);
		return dataSave;
	}

	@Override
	@Transactional
	public Optional<OrderDTO> create(OrderRequestDTO data) {
		Order entity = new Order();
		return Optional.ofNullable(getMapper().apply(handleData(entity, data)));
	}

	@Transactional
	public Optional<OrderDTO> updateStatus(String id, OrderUpdateStatusRequestDTO data) {
		Order entityFind = getRepository().findById(id)
				.orElseThrow(() -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
		entityFind.setStatus(data.getStatus());
		return Optional.ofNullable(getMapper().apply(entityFind));
	}

	@Transactional
	public Optional<OrderDTO> updatePayment(String id) {
		Order entityFind = getRepository().findById(id)
				.orElseThrow(() -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
		entityFind.setPaymentStatus(OrderPaymentStatusEnum.PAID);
		return Optional.ofNullable(getMapper().apply(entityFind));
	}

}
