<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/listOrder.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Lista de Formas de Pagamento - ITech.com</title>
<style>
    body{
        background: #201b2c;
        background-size: cover;
        background-position: center;
    }

    thead tr th{
        color: #fff;
    }

    .container{
        background-color: #2f4f4f;
    }

    .navegation a:hover {
        color: #fff;
    }

    .container{
        margin-top: 500px;
        max-height: 600px;
        max-width: 800px;
        overflow-y: auto;
    }

    thead{
        position: sticky;
        top: 0;
        background-color: #2f4f4f;
        z-index: 1;
    }

    thead tr th {
        color: #fff;
        background-color: inherit;
    }

    .navegation .btnLogin-popup{
        margin-left: 40px;
        font-size: 1.1em;
        font-weight: 500;
        width: 130px;
        height: 50px;
        background: transparent;
        border: 2px solid white;
        outline: none;
        border-radius: 6px;
        cursor: pointer;
        color: white;
        transition: .5s;
    }

    .navegation .btnLogin-popup:hover{
        background: white;
        color: #162938;
    }
</style>
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <div class="container">
    	<table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nome</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>

            <tbody>
                <c:if test="${empty paymentMethods}">
                    <tr>
                        <td colspan="10">Nenhuma forma de pagamento encontrada.</td>
                    </tr>
                </c:if>
      	        <c:if test="${not empty paymentMethods}">
    		        <c:forEach var="payment" items="${paymentMethods}">
    		            <tr>
    		                <th scope="row">${payment.codigo}</th>
    		                <td>${payment.name}</td>
    		                <td><a href="deletePaymentMethod?codigo=${payment.codigo}" class="btn btn-danger"> Excluir </a></td>
          		            <td><a href="updatePaymentMethod?codigo=${payment.codigo}" class="btn btn-warning"> Editar </a></td>
    		            </tr>
    		        </c:forEach>
    	        </c:if>
            </tbody>
        </table>
    </div>
    <script
    		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    		crossorigin="anonymous">
    </script>
</body>
</html>