package com.personal.controller;

import com.personal.model.PaymentRequest;
import com.personal.model.PaymentResponse;
import com.personal.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {}


    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
        long paymentId = paymentService.doPayment(paymentRequest);
        return new ResponseEntity<>(paymentId, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable("orderId") Long orderId){
        PaymentResponse paymentResponse = paymentService.getPaymentDetails(orderId);

        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

}
