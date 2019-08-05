<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="<b> ${pageContext.request.contextPath} </b>"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Fonepay Payment Demo</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>

<body>

<div class="container">
    <img src="https://dev-merchant-login.fonepay.com/assets/img/brand.png">

    <br>

    <a href="payment">Pay Using fonepay</a>
    <br>
    <a href="/">Home</a>

    <div class="jumbotron">

        <p><b> ${paymentVerificationMessage} </b></p>

        <p> ID : <b> ${paymentRequest.id} </b></p>

        <p> RESPONSE_FONEPAY_TRACE_ID: <b> ${paymentRequest.responseFonepayTraceId} </b></p>
        <p> RESPONSE_FONEPAY_MESSAGE : <b> ${paymentRequest.responseFonepayMessage} </b></p>
        <p> RESPONSE_FONEPAY_CODE : <b> ${paymentRequest.responseFonepayCode} </b></p>
        <p> AMOUNT_AMT: <b> ${paymentRequest.amountAmt} </b></p>
        <p> RESPONSE_TOTAL_AMOUNT_PAID_BY_CUSTOMER : <b> ${paymentRequest.responseTotalAmountPaidByCustomer} </b></p>
        <p> RESPONSE_PAID_FROM_BANK_CODE : <b> ${paymentRequest.responseCustomerPaidFromBankCode} </b></p>
        <p>RESPONSE_INITIATOR_CUSTOMER : <b> ${paymentRequest.responseInitiatorCustomer} </b></p>

        <p> MERCHANT_CODE_PID: <b> ${paymentRequest.merchantCodePid} </b></p>
        <p> PRODUCT_NUMBER_PRN: <b> ${paymentRequest.productNumberPrn} </b></p>
        <p> DATE_DT: <b> ${paymentRequest.dateDt.toString()} </b></p>
        <p> REMARKS_1_R1: <b>  <c:out value="${paymentRequest.remarks1}"/> </b></p>
        <p> REMARKS_2_R2: <b>  <c:out value="${paymentRequest.remarks2}"/> </b></p>

        <p> RESPONSE_BILL_ID: <b> ${paymentRequest.responseBillId} </b></p>
        <p> RESPONSE_FONEPAY_VERIFICATION_RETURN_URL : <b> ${paymentRequest.responseFonepayVerificationReturnUrl} </b>
        </p>

    </div>

</div>

</body>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</html>