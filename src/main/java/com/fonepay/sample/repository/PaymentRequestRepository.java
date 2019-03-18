package com.fonepay.sample.repository;

import com.fonepay.sample.model.PaymnetRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRequestRepository extends JpaRepository<PaymnetRequest, Long> {
    PaymnetRequest findByProductNumberPrn(String prn);
}
