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
            <a href="serviceOrder">Service</a>
            <a href="workOrderListManager">Order List</a>
            <a href="clientList">Client List</a>
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
                    <th scope="col">Descrição</th>
                    <th scope="col">Data Emissão</th>
                    <th scope="col">Data Finalização</th>
                    <th scope="col">Valor</th>
                    <th scope="col">Forma Pagamento</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Observação</th>
                    <th scope="col">Código do Cliente</th>
                    <th scope="col">Nome do Cliente</th>
                    <th scope="col"></th>
                    <th scope="col"></th>

                </tr>
            </thead>

            <tbody>
                <c:if test="${empty clientes}">
                    <tr>
                        <td colspan="10">Nenhuma ordem de serviço encontrada.</td>
                    </tr>
                </c:if>
      	        <c:if test="${not empty serviceOrders}">
    		        <c:forEach var="order" items="${serviceOrders}">
    		            <tr>
    		                <th scope="row">${order.codigo}</th>
    		                <td>${order.descricao}</td>
    		                <td>${order.dataEmissao}</td>
    		                <td>${order.dataFinalizacao}</td>
    		                <td>${order.valor}</td>
    		                <td>${order.paymentMethod}</td>
    		                <td>${order.status}</td>
    		                <td>${order.observacao}</td>
    		                <td>${order.cliente.codigo}</td>
    		                <td>${order.cliente.nome}</td>
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