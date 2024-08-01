package com.personal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FallbackController {

    @RequestMapping("/orderServiceFallback")
    public ResponseEntity<String> orderServiceFallback() {
        return new ResponseEntity<>("Order Service is down. Please try again later.", HttpStatus.OK);
    }

    @RequestMapping("/productServiceFallback")
    public ResponseEntity<String> productServiceFallback() {
        return new ResponseEntity<>("Product Service is down. Please try again later.", HttpStatus.OK);
    }

    @RequestMapping("/paymentServiceFallback")
    public ResponseEntity<String> paymentServiceFallback() {
        return new ResponseEntity<>("Payment Service is down. Please try again later.", HttpStatus.OK);
    }
}
