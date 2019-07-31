package com.fonepay.sample.repository;

import com.fonepay.sample.model.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {
    PaymentRequest findByProductNumberPrn(String prn);

    List<PaymentRequest> findFirst20ByOrderByProductNumberPrnDesc();

    @Query(value = "SELECT * FROM PAYMENT_REQUEST WHERE RESPONSE_FONEPAY_TRACE_ID=:fonepayTraceId", nativeQuery = true)
    PaymentRequest getByFonepayTraceIdNativeQuery(@Param("fonepayTraceId") Long fonepayTraceId);
}
