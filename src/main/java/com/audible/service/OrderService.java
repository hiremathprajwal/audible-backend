package com.audible.service;

import java.util.List;

import com.audible.dto.OrderDTO;

public interface OrderService {
    OrderDTO placeOrder(Integer customerId, String paymentMethod);

    List<OrderDTO> getOrdersByCustomerId(Integer customerId);
}
