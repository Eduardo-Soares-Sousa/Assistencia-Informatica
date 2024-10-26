<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link
    	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
    	rel="stylesheet"
    	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
    	crossorigin="anonymous">
    <title>Adicionar Forma de Pagamento - ITech.com</title>
</head>
<style>
    body{
        background: #201b2c;
        background-size: cover;
        background-position: center;
    }

    .logo{
        color: white;
    }

    header{
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        padding: 20px 100px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        z-index: 99;
    }

    .logo{
        font-size: 2em;
        color: white;
        user-select: none;
    }

    .navegation  a{
        position: relative;
        font-size: 1.1em;
        text-decoration: none;
        margin-left: 40px;
        color: white;
        font-weight: 500;
    }

    .navegation a::after{
        content: "";
        position: absolute;
        left: 0;
        bottom: -6px;
        width: 100%;
        height: 10%;
        background: white;
        border-radius: 5px;
        transform-origin: right;
        transform: scaleX(0);
        transition: transform .5s;
    }

    .navegation a:hover::after{
        transform-origin: left;
        transform: scaleX(1);
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

    .btn-salvar{
        background: #696969;
        color: #fff;
    }

    .btn-salvar:hover{
        background: #a9a9a9;
    }

    .select .select-payment{
        width: 100%;
        height: 100%;
        border-radius: 10px;
        cursor: pointer;
        overflow: hidden;
        border: none;
        background: #696969;
        color: #fff;
        appearance: none;
        padding-left: 10px;
        z-index: 999;
    }

    .input-box.select {
        padding-bottom: 5px;
    }

    .select .icon{
        cursor: pointer;
        top: -2px;
    }

    .main-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100%;
        margin-top: 150px;
        opacity: 0;
        transition: opacity 1s ease;
    }

    .wrapper{
        position: relative;
        width: 400px;
        height: 720px;
        background: transparent;
        border: 2px solid rgba(255, 255, 255, .5);
        border-radius: 20px;
        backdrop-filter: blur(20px);
        box-shadow: 0 0 30px rgba(0, 0, 0, .5);
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .form-box{
        opacity: 0;
        transition: opacity 1s ease;
    }

    .wrapper .icon-close{
        position: absolute;
        top: 0;
        right: 0;
        width: 45px;
        height: 45px;
        background: #162938;
        font-size: 2em;
        color: white;
        display: flex;
        justify-content: center;
        align-items: center;
        border-bottom-left-radius: 20px;
        border-top-right-radius: 20px;
        cursor: pointer;
        z-index: 1;
    }

    .wrapper{
        top: 20px;
        height: 300px;
        width: 700px;
    }

    .wrapper.form-box{
        max-width: 500px;
    }

    .alerts-container {
        position: absolute;
        top: 0;
        width: 100%;
        display: flex;
        justify-content: center;
        padding: 20px;
        z-index: 100;
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

    .form-box h2{
        font-size: 2em;
        color: #fff;
        text-align: center;
    }

    .input-box{
        position: relative;
        width: 100%;
        height: 50px;
        border-bottom: 2px solid #fff;
        margin: 30px 0;
    }

    .input-box label{
        position: absolute;
        top: 50%;
        left: 5px;
        transform: translateY(-50%);
        font-size: 1em;
        color: #fff;
        font-weight: 500;
        pointer-events: none;
        transition: .5s;
    }

    .input-box input:focus~label, .input-box input:not(:placeholder-shown)~label{
        top: -5px;
    }

    .input-box input{
        width: 100%;
        height: 100%;
        background: transparent;
        border: none;
        outline: none;
        font-size: 1em;
        color: #fff;
        font-weight: 600;
        padding: 0 35px 0 5px;
    }

    .input-box .icon{
        position: absolute;
        right: 8px;
        font-size: 1.2em;
        color: #fff;
        line-height: 57px;
    }

    .btn-salvar{
        width: 100%;
        height: 45px;
        background: #696969;
        border: none;
        outline: none;
        border-radius: 6px;
        cursor: pointer;
        font-size: 1em;
        color: #fff;
        font-weight: 500;
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
        border-radius: 10px;
        margin-top: 50px;
        max-height: 600px;
        max-width: 700px;
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
<body>
<div class="alerts-container">
    <c:if test="${result == 'notRegistered'}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
    	    Não foi possível salvar a forma de pagamento :(.
    		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    	</div>
    </c:if>
    <c:if test="${result == 'registered'}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
    	    Forma de pagamento salva com sucesso.
    		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>
</div>
    <c:choose>
        <c:when test="${service == null}">
    	    <input type="hidden" name="codigo" value="0">
    	</c:when>
    	<c:when test="${service != null}">
    	    <input type="hidden" name="codigo" value="${service.codigo}">
    	</c:when>
    </c:choose>
    <jsp:include page="navbar.jsp"/>
    <div class="main-container">
        <div class="wrapper" id="wrapper">
            <span class="icon-close" id="icon-close">
                <ion-icon name="close"></ion-icon>
            </span>
            <div class="form-box register">
                <h2>Adicionar Método de Pagamento</h2>
                <form action="paymentMethod" method="post">
                    <div class="input-box">
                        <span class="icon"><ion-icon name="document-text-outline"></ion-icon></span>
                        <input type="text" name="method" id="method" minlength="2" maxlength="100" required> <span id="1"></span>
                        <label for="method">Novo método*</label>
                    </div>
                    <button type="submit" class="btn-salvar">Salvar</button>
                </form>
            </div>
        </div>
        <div class="container" id="container">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nome</th>
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
    </div>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script
    		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    		crossorigin="anonymous">
    </script>
    <script type="text/javascript" src="js/service.js"></script>
    <script>
        const closeWrapper = document.getElementById('icon-close');
        const wrapper = document.getElementById('wrapper');
        const container = document.getElementById('container');

        window.addEventListener("load", function() {
            setTimeout(function() {
                document.querySelector(".main-container").style.opacity = "1";
            }, 400);
        })

        window.addEventListener("load", function() {
            setTimeout(function() {
                document.querySelector('.form-box').style.opacity = "1";
            }, 400);
        })

        closeWrapper.addEventListener('click', function() {
            wrapper.style.opacity = 0;
            container.style.opacity = 0;
        });
    </script>
</body>
</html>