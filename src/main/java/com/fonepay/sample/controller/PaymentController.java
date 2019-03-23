package com.fonepay.sample.controller;

import com.fonepay.FonepayService;
import com.fonepay.model.FonepayPaymentRequest;
import com.fonepay.model.FonepayPaymentRequestBuilder;
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

import javax.validation.Valid;
import java.util.UUID;

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
        model.addAttribute("paymentForm", new PaymentRequest());

        return "payment";
    }

    @PostMapping("/payment")
    public String paymentPost(@ModelAttribute("paymentForm") @Valid PaymentRequest paymentRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "payment";
        }

        paymentRequest.setMerchantCodePid(merchantCodePID);
        paymentRequest.setProductNumberPrn(UUID.randomUUID().toString().replace("-", ""));

        fonepayPaymentRequestService.savePaymentRequest(paymentRequest);

        FonepayPaymentRequest fonepayPaymentRequest = FonepayPaymentRequestBuilder.aFonepayPaymentRequest()
                .merchantCodePid(paymentRequest.getMerchantCodePid())
                .productNumberPrn(paymentRequest.getProductNumberPrn())
                .amountAmt(paymentRequest.getAmountAmt())
                .remarks1(paymentRequest.getRemarks1())
                .fonepayUrl(fonepayPaymentUrl)
                .merchantWebsiteReturnUrl(merchantPaymentSuccessReturnUrl)
                .merchantSecretKey(merchantSecretKey)
                .build();

        String fonepayPaymentRequestUrl = FonepayService.generateFonepayUrlForPaymentRequest(fonepayPaymentRequest);
        return "redirect:" + fonepayPaymentRequestUrl;
    }


}
