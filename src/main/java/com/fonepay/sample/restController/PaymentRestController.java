package com.fonepay.sample.restController;

import com.fonepay.FonepayService;
import com.fonepay.model.FonepayPaymentRequest;
import com.fonepay.model.FonepayPaymentRequestBuilder;
import com.fonepay.sample.model.PaymentRequest;
import com.fonepay.sample.service.FonepayPaymentRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/fonepay")
public class PaymentRestController {

    private final FonepayPaymentRequestService fonepayPaymentRequestService;

    @Value("${fonepay.merchantCodePID}")
    private String merchantCodePID;

    @Value("${fonepay.merchantSecretKey}")
    private String merchantSecretKey;

    @Value("${merchantPaymentSuccessReturnUrl}")
    private String merchantPaymentSuccessReturnUrl;

    @Value("${fonepay.fonepayPaymentUrl}")
    private String fonepayPaymentUrl;

    public PaymentRestController(FonepayPaymentRequestService fonepayPaymentRequestService) {
        this.fonepayPaymentRequestService = fonepayPaymentRequestService;
    }

    @PostMapping(path = "/pay", consumes = "application/json", produces = "application/json")
    public Map<String, String> doPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentRequest.setMerchantCodePid(merchantCodePID);
        paymentRequest.setProductNumberPrn(randomString(15));


        fonepayPaymentRequestService.savePaymentRequest(paymentRequest);

        FonepayPaymentRequest fonepayPaymentRequest =
                FonepayPaymentRequestBuilder.aFonepayPaymentRequest()
                        .withMerchantCodePid(paymentRequest.getMerchantCodePid())
                        .withProductNumberPrn(paymentRequest.getProductNumberPrn())
                        .withAmountAmt(paymentRequest.getAmountAmt())
                        .withRemarks1(paymentRequest.getRemarks1())
                        .withFonepayUrl(fonepayPaymentUrl)
                        .withMerchantWebsiteReturnUrl(merchantPaymentSuccessReturnUrl)
                        .withMerchantSecretKey(merchantSecretKey)
                        .build();

        String fonepayPaymentRequestUrl = FonepayService.generateFonepayUrlForPaymentRequest(fonepayPaymentRequest);

        HashMap<String, String> map = new HashMap<>();
        map.put("fonepayRedirectUrl", fonepayPaymentRequestUrl);
        return map;
    }

    private String randomString(int len) {
        final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
