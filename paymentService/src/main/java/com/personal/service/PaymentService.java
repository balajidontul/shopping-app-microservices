package com.personal.service;

import com.personal.model.PaymentRequest;
import com.personal.model.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetails(Long orderId);
}
