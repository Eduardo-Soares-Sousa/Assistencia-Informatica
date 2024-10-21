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
<title>Lista de Ordem de serviço - ITech.com</title>
<style>
    body{
        background: url('img/teste1.jpg') no-repeat;
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
        max-height: 600px;
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
</style>
</head>
<body>
    <header>
        <h2 class="logo">Logo</h2>
        <nav class="navegation">
            <a href="home.jsp">Home</a>
            <a href="listOfClient">Service</a>
            <a href="workOrderListManager">Order List</a>
            <a href="#">About</a>
            <a href="#">Contact us</a>
            <button class="btn-login">Login</button>
        </nav>
    </header>
    <div class="container">
    	<table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Email</th>
                    <th scope="col">Telefone</th>
                    <th scope="col">CPF</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>

            <tbody>
      	        <c:if test="${not empty serviceOrders}">
    		        <c:forEach var="client" items="${serviceOrders}">
    		            <tr>
    		                <th scope="row">${client.codigo}</th>
    		                <td>${client.nome}</td>
    		                <td>${client.email}</td>
    		                <td>${client.telefone}</td>
    		                <td>${client.cpf}</td>
    		                <td><a href="deleteServiceOrder?codigo=${order.codigo}" class="btn btn-danger"> Excluir </a></td>
          		            <td><a href="updateServiceOrder?codigo=${order.codigo}" class="btn btn-warning"> Editar </a></td>
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