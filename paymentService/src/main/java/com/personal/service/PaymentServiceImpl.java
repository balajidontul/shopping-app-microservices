package com.personal.service;

import com.personal.PaymentServiceException;
import com.personal.entity.TransactionDetails;
import com.personal.model.PaymentRequest;
import com.personal.model.PaymentResponse;
import com.personal.repository.TransactionDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {

        log.info("doPayment");

        TransactionDetails transactionDetails = TransactionDetails.builder()
                .orderId(paymentRequest.getOrderId())
                .amount(paymentRequest.getAmount())
                .PaymentMode(paymentRequest.getPaymentMode())
                .paymentStatus("SUCCESS")
                .referenceNumber(String.valueOf((int) Math.random() * 1000))
                .paymentDate(Instant.now())
                .build();

        transactionDetails = transactionDetailRepository.save(transactionDetails);

        log.info("Completed: {}", transactionDetails.getId());

        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetails(Long orderId) {

        TransactionDetails transactionDetails = transactionDetailRepository.findByOrderId(orderId).orElseThrow(() ->
                new PaymentServiceException("payment details not found", "PAYMENT_NOT_FOUND")
        );


        PaymentResponse paymentResponse = PaymentResponse.builder()
                .orderId(transactionDetails.getOrderId())
                .amount(transactionDetails.getAmount())
                .paymentDate(transactionDetails.getPaymentDate())
                .paymentStatus(transactionDetails.getPaymentStatus())
                .referenceNumber(transactionDetails.getReferenceNumber())
                .PaymentMode(transactionDetails.getPaymentMode())
                .build();

        return paymentResponse;
    }
}
