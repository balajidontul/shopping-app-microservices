package com.personal.service;

import com.personal.Exception.OrderServiceExceptionHandler;
import com.personal.entity.Orders;
import com.personal.external.client.PaymentService;
import com.personal.external.client.ProductService;
import com.personal.external.client.model.PaymentRequest;
import com.personal.model.OrderRequest;
import com.personal.model.OrderResponse;
import com.personal.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public long createOrder(OrderRequest orderRequest) {

        log.info("creating order"+ orderRequest);
        Orders order = Orders.builder()
                .productId(orderRequest.getProductId())
                .orderAmount(orderRequest.getTotalAmount())
                .orderStatus("Created")
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .paymentMode(orderRequest.getPaymentMode())
                .build();

        order = orderRepository.save(order);

        log.info("order created");

        productService.reduceQuantity(order.getProductId(), orderRequest.getQuantity());

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getOrderid())
                .amount(order.getOrderAmount())
                .paymentMode(order.getPaymentMode())
                .build();

        String orderStatus = null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("payment success");
            orderStatus = "PLACED";
        }catch (Exception e){
            log.info("payment failed");
            orderStatus = "FAILED";
        }

        order.setOrderStatus(orderStatus);

        order = orderRepository.save(order);

        return order.getOrderid();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderServiceExceptionHandler(
                        "Order with given Id not found", "ORDER_NOT_FOUND"));

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(order.getOrderid())
                .orderAmount(order.getOrderAmount())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .build();

        OrderResponse.ProductDetails productDetails =
                restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+order.getProductId(),
                        OrderResponse.ProductDetails.class);

        orderResponse.setProductDetails(productDetails);

        OrderResponse.PaymentDetails paymentDetails =
                restTemplate.getForObject("http://PAYMENT-SERVICE/payment/"+order.getOrderid(),
                        OrderResponse.PaymentDetails.class);

        orderResponse.setPaymentDetails(paymentDetails);

        return orderResponse;
    }
}
