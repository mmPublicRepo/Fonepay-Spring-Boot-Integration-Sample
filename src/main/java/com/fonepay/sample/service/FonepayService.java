package com.fonepay.sample.service;

import com.fonepay.sample.model.PaymnetRequest;

public interface FonepayService {
    PaymnetRequest getPaymentRequestByPrn(String prn);
}
