package com.fonepay.sample.controller;

import com.fonepay.FonepayService;
import com.fonepay.model.*;
import com.fonepay.sample.model.PaymentRequest;
import com.fonepay.sample.service.FonepayPaymentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.security.SecureRandom;

@Controller
public class PaymentController {

    @Autowired
    FonepayPaymentRequestService fonepayPaymentRequestService;

    @Value("${fonepay.merchantCodePID}")
    private String merchantCodePID;

    @Value("${fonepay.merchantSecretKey}")
    private String merchantSecretKey;

    @Value("${merchantPaymentSuccessReturnUrl}")
    private String merchantPaymentSuccessReturnUrl;

    @Value("${fonepay.fonepayPaymentUrl}")
    private String fonepayPaymentUrl;

    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("paymentList", fonepayPaymentRequestService.getPaymentRequestList());

        return "welcome";
    }


    @GetMapping("/payment")
    public String paymentGet(Model model) {
        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setProductNumberPrn(randomString(15));

        model.addAttribute("paymentForm", paymentRequest);

        return "payment";
    }

    private String randomString(int len) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    // Step 1A
    // Merchant sends GET or POST payment request to fonepay
    @PostMapping("/payment")
    public String paymentPost(@ModelAttribute("paymentForm") @Valid PaymentRequest paymentRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "payment";
        }

        paymentRequest.setMerchantCodePid(merchantCodePID);

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
        return "redirect:" + fonepayPaymentRequestUrl;
    }

    // Step 1B
    @GetMapping("/paymentValidate")
    public String paymentValidate(@RequestParam("PRN") String productNumberPrn,
                                  @RequestParam("BID") String billId,
                                  @RequestParam("UID") String fonepayTraceIdUid,
                                  Model model
    ) {

        PaymentRequest paymentRequest = fonepayPaymentRequestService.getPaymentRequestByPrn(productNumberPrn);
        paymentRequest.setResponseFonepayTraceId(fonepayTraceIdUid);
        paymentRequest.setResponseBillId(billId);
        paymentRequest.setResponseFonepayVerificationReturnUrl(fonepayPaymentUrl);

        FonepayPaymentVerificationRequest fonepayPaymentVerification =
                FonepayPaymentVerificationRequestBuilder.aFonepayPaymentVerificationRequest()
                        .withProductNumberPrn(productNumberPrn)
                        .withMerchantCodePid(merchantCodePID)
                        .withBillIdBid(billId)
                        .withFonepayTraceIdUniqueUid(fonepayTraceIdUid)
                        .withFonepayVerificationUrl(fonepayPaymentUrl)
                        .withMerchantRequestedAmountAmt(paymentRequest.getAmountAmt() + "")
                        .withMerchantSecretKey(merchantSecretKey)
                        .build();

        String fonepayUrlForPaymentVerification = FonepayService.generateFonepayUrlForPaymentVerificationRequest(fonepayPaymentVerification);

        String paymentVerificationMessage;
        FonepayPaymentVerificationResponse fonepayPaymentVerificationResponse;
        try {
            RestTemplate restTemplate = new RestTemplate();
            fonepayPaymentVerificationResponse =
                    restTemplate.getForObject(fonepayUrlForPaymentVerification, FonepayPaymentVerificationResponse.class);

            paymentRequest.setResponseTotalAmountPaidByCustomer(fonepayPaymentVerificationResponse.getTxnAmount());
            paymentRequest.setResponseCustomerPaidFromBankCode(fonepayPaymentVerificationResponse.getBankCode());
            paymentRequest.setResponseInitiatorCustomer(fonepayPaymentVerificationResponse.getInitiator());
            paymentRequest.setResponseFonepayMessage(fonepayPaymentVerificationResponse.getMessage());
            paymentRequest.setResponseFonepayCode(fonepayPaymentVerificationResponse.getResponse_code());

            fonepayPaymentRequestService.savePaymentRequest(paymentRequest);
        } catch (Exception e) {
            paymentVerificationMessage = e.toString();
            model.addAttribute("paymentVerificationMessage", paymentVerificationMessage);
            return "paymentValidation";
        }

        if (fonepayPaymentVerificationResponse.getResponse_code().equalsIgnoreCase("successful")) {
            paymentVerificationMessage = "Payment was successful fonepay ";
        } else {
            paymentVerificationMessage = "Payment failed fonepay ";
        }
        model.addAttribute("paymentVerificationMessage", paymentVerificationMessage);
        model.addAttribute("paymentRequest", paymentRequest);
        return "paymentValidation";
    }


}
