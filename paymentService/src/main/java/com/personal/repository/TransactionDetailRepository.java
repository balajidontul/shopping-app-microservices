package com.personal.repository;

import com.personal.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetails, Long> {


    public Optional<TransactionDetails> findByOrderId(long orderId);
}
