package com.fonepay.sample.service;

import com.fonepay.sample.model.PaymentRequest;

import java.util.List;

public interface FonepayPaymentRequestService {
    PaymentRequest getPaymentRequestByPrn(String prn);

    List<PaymentRequest> getPaymentRequestList();

    PaymentRequest savePaymentRequest(PaymentRequest paymentRequest);

    PaymentRequest getByFonepayTraceIdNativeQuery(Long fonepayTraceId);
}
