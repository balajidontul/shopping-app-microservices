package com.personal.entity;

import com.personal.model.PaymentMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderid;

    @Column(name = "PRODUCTID")
    private long productId;

    @Column(name = "QUANTITY")
    private long quantity;

    @Column(name = "DATE")
    private Instant orderDate;

    @Column(name = "STATUS")
    private String orderStatus;

    @Column(name = "PAYMENTMD")
    private PaymentMode paymentMode;

    @Column(name = "ORDERAMOUNT")
    private long orderAmount;
}
