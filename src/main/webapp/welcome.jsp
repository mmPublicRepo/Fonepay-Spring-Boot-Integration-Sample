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

    <div class="table-responsive">
        <table class="table table-striped w-auto">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">MERCHANT_CODE_PID</th>
                <th scope="col">PRODUCT_NUMBER_PRN</th>
                <th scope="col">AMOUNT_AMT</th>
                <th scope="col">DATE_DT</th>
                <th scope="col">REMARKS_1_R1</th>
                <th scope="col">REMARKS_2_R2</th>

                <th scope="col">RESPONSE_BILL_ID</th>
                <th scope="col">RESPONSE_FONEPAY_TRACE_ID</th>
                <th scope="col">RESPONSE_FONEPAY_VERIFICATION_RETURN_URL</th>
                <th scope="col">RESPONSE_PAID_FROM_BANK_CODE</th>
                <th scope="col">RESPONSE_INITIATOR_CUSTOMER</th>
                <th scope="col">RESPONSE_TOTAL_AMOUNT_PAID_BY_CUSTOMER</th>
                <th scope="col">RESPONSE_FONEPAY_MESSAGE</th>
                <th scope="col">RESPONSE_FONEPAY_CODE</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="paymentRequest" items="${paymentList}">

                <tr>
                    <td>${paymentRequest.id}</td>
                    <td>${paymentRequest.merchantCodePid}</td>
                    <td>${paymentRequest.productNumberPrn}</td>
                    <td>${paymentRequest.amountAmt}</td>
                    <td>${paymentRequest.dateDt}</td>
                    <td>${paymentRequest.remarks1}</td>
                    <td>${paymentRequest.remarks2}</td>

                    <td>${paymentRequest.responseBillId}</td>
                    <td>${paymentRequest.responseFonepayTraceId}</td>
                    <td>${paymentRequest.responseFonepayVerificationReturnUrl}</td>
                    <td>${paymentRequest.responseCustomerPaidFromBankCode}</td>
                    <td>${paymentRequest.responseInitiatorCustomer}</td>
                    <td>${paymentRequest.responseTotalAmountPaidByCustomer}</td>
                    <td>${paymentRequest.responseFonepayMessage}</td>
                    <td>${paymentRequest.responseFonepayCode}</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
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