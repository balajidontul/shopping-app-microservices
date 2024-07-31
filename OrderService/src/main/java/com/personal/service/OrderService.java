package com.personal.service;

import com.personal.model.OrderRequest;
import com.personal.model.OrderResponse;

public interface OrderService {
    long createOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
