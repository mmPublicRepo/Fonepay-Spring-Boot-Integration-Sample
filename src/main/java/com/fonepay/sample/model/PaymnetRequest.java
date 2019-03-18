package com.fonepay.sample.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENT_REQUEST")
@Data
public class PaymnetRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MERCHANT_CODE_PID", length = 20)
    private String merchantCodePid;

    @Column(name = "PRODUCT_NUMBER_PRN", length = 50)
    private String productNumberPrn;

    @Column(name = "AMOUNT_AMT", length = 18)
    private Double amountAmt;

    @Column(name = "CURRENCY_CRN", length = 3)
    private Double currencyCrn;

    @Column(name = "DATE_DT", length = 10)
    private String dateDt;

    @Column(name = "REMARKS_1_R1", length = 160)
    private String remarks1;

    @Column(name = "REMARKS_2_R2", length = 50)
    private String remarks2;

    @Column(name = "MD", length = 3)
    private String md;
}
