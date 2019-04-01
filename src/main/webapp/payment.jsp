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
    <br>

    <a href="payment">Pay Using fonepay</a>
    <br>
    <a href="/">Home</a>

    <h2>Pay Using Fonepay (Demo)</h2>
    <h3>Pay for Unique Product Number:${paymentForm.productNumberPrn}</h3>
    <b>(Note: Product Number Should be unique for every request to fonepay)</b>

    <br>
    <form:form class="form-group" method="POST" action="${contextPath}/payment" modelAttribute="paymentForm">

        <spring:bind path="amountAmt">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input class="form-control" type="number" path="amountAmt" placeholder="Amount"
                            autofocus="true" max="999999"></form:input>
                <form:errors path="amountAmt"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="remarks1">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input class="form-control" type="text" path="remarks1" placeholder="Payment Remarks"
                            maxlength="160"></form:input>
                <form:errors path="remarks1"></form:errors>
            </div>

        </spring:bind><spring:bind path="productNumberPrn">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input class="form-control" type="text" path="productNumberPrn" placeholder="PRN"
                        readonly="true"></form:input>
            <form:errors path="productNumberPrn"></form:errors>
        </div>
    </spring:bind>


        <button class="btn btn-lg btn-danger btn-block" type="submit">Pay using Fonepay</button>
    </form:form>

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