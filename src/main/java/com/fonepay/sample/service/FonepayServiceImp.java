package com.fonepay.sample.service;

import com.fonepay.sample.model.PaymnetRequest;
import com.fonepay.sample.repository.PaymentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FonepayServiceImp implements FonepayService {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;

    @Override
    public PaymnetRequest getPaymentRequestByPrn(String prn) {
        return paymentRequestRepository.findByProductNumberPrn(prn);
    }
}
