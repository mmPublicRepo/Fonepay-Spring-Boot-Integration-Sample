package com.fonepay.sample.service;

import com.fonepay.sample.model.PaymentRequest;
import com.fonepay.sample.repository.PaymentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FonepayPaymentRequestServiceImp implements FonepayPaymentRequestService {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;

    @Override
    public PaymentRequest getPaymentRequestByPrn(String prn) {
        return paymentRequestRepository.findByProductNumberPrn(prn);
    }

    @Override
    public List<PaymentRequest> getPaymentRequestList() {
        return paymentRequestRepository.findFirst20ByOrderByProductNumberPrnDesc();
    }

    @Override
    public PaymentRequest savePaymentRequest(PaymentRequest paymentRequest) {
        return paymentRequestRepository.save(paymentRequest);
    }

    @Override
    public PaymentRequest getByFonepayTraceIdNativeQuery(Long fonepayTraceId) {
        return paymentRequestRepository.getByFonepayTraceIdNativeQuery(fonepayTraceId);
    }

}
