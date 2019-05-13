package com.fonepay.sample.repository;

import com.fonepay.sample.model.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {
    PaymentRequest findByProductNumberPrn(String prn);

    List<PaymentRequest> findFirst20ByOrderByProductNumberPrnDesc();
}
