<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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
    <h2>Pay Using Fonepay (Demo)</h2>

    <br>
    <a href="payment">Pay Using fonepay</a>
    <br>
    <a href="/">Home</a>
    <br>

    <c:forEach var="paymentRequest" items="${paymentList}">

        <div class="jumbotron">

            <b>#</b>
                ${paymentRequest.id} <br>

            <b>RESPONSE_FONEPAY_TRACE_ID:</b>
                ${paymentRequest.responseFonepayTraceId} <br>

            <b>MERCHANT_CODE_PID: </b>
                ${paymentRequest.merchantCodePid} <br>

            <b>PRODUCT_NUMBER_PRN: </b>
                ${paymentRequest.productNumberPrn} <br>

            <b>AMOUNT_AMT: </b>
                ${paymentRequest.amountAmt} <br>

            <b>DATE_DT: </b>
                ${paymentRequest.dateDt} <br>

            <b>REMARKS_1_R1: </b>
            <c:out value="${paymentRequest.remarks1}"/><br>

            <b>REMARKS_2_R2: </b>
            <c:out value="${paymentRequest.remarks2}"/><br>

            <b>RESPONSE_BILL_ID: </b>
                ${paymentRequest.responseBillId} <br>

            <b>RESPONSE_FONEPAY_VERIFICATION_RETURN_URL: </b>
                ${paymentRequest.responseFonepayVerificationReturnUrl} <br>

            <b>RESPONSE_PAID_FROM_BANK_CODE: </b>
                ${paymentRequest.responseCustomerPaidFromBankCode} <br>

            <b>RESPONSE_INITIATOR_CUSTOMER: </b>
                ${paymentRequest.responseInitiatorCustomer} <br>

            <b>RESPONSE_TOTAL_AMOUNT_PAID_BY_CUSTOMER: </b>
                ${paymentRequest.responseTotalAmountPaidByCustomer} <br>

            <b>RESPONSE_FONEPAY_MESSAGE: </b>
                ${paymentRequest.responseFonepayMessage} <br>

            <b>RESPONSE_FONEPAY_CODE: </b>
                ${paymentRequest.responseFonepayCode} <br>

        </div>

        <hr>
    </c:forEach>


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