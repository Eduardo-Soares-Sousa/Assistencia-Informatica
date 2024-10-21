<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/service.css">
    <link
    	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
    	rel="stylesheet"
    	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
    	crossorigin="anonymous">
    <title>Service Order - ITech.com</title>
</head>
<style>
body{
    background: url('img/teste.jpg') no-repeat;
    background-size: cover;
    background-position: center;
}

.btn{
    background: #696969;
    color: #fff;
}

.btn:hover{
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

.label-payment {
    position: absolute;
    top: 30%;
    left: 5px;
    transform: translateY(-50%);
    font-size: 1em;
    color: #fff;
    font-weight: 500;
    pointer-events: none;
    transition: .5s;
}

.select-payment:focus~.label-payment,
.select-payment:not(:placeholder-shown)~.label-payment {
    top: -10px;
}

.input-box.select {
    padding-bottom: 5px;
}

.select .icon{
    cursor: pointer;
    top: -2px;
}

.wrapper{
    transition: opacity 0.5s ease;
    top: 20px;
    height: 720px;
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
</style>
<body>
<div class="alerts-container">
    <c:if test="${result == 'notRegistered'}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
    	    A <b>ordem de serviço</b> não foi salva. Verifique as credenciais.
    		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    	</div>
    </c:if>
    <c:if test="${result == 'loginFail'}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            Não há usuário logado, favor realizar o login.
        	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <c:if test="${result == 'registered'}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
    	    <b>ordem de serviço</b> salva com sucesso.
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

    <header>
        <h2 class="logo">Logo</h2>
        <nav class="navegation">
            <a href="home.jsp">Home</a>
            <a href="service.jsp">Service</a>
            <a href="workOrderListManager">Order List</a>
            <a href="#">About</a>
            <a href="#">Contact us</a>
            <button class="btn-login">Login</button>
        </nav>
    </header>

    <div class="wrapper" id="wrapper">
        <span class="icon-close" id="icon-close">
            <ion-icon name="close"></ion-icon>
        </span>
        <div class="form-box register">
            <h2>Ordem de Serviço</h2>
            <form action="serviceOrder" method="post">
                <div class="input-box">
                    <span class="icon"><ion-icon name="document-text-outline"></ion-icon></span>
                    <input type="text" name="descricao" id="descricao" minlength="4" maxlength="100" required> <span id="1"></span>
                    <label for="descricao">Descrição*</label>
                </div>
                <div class="input-box">
                    <input type="date" name="dataEmissao" id="dataEmissao" required> <span id="2"></span>
                    <label for="dataEmissao">Data de emissão*</label>
                </div>
                <div class="input-box">
                    <input type="date" name="dataFinalizacao" id="dataFinalizacao" required> <span id="3"></span>
                    <label for="dataFinalizacao">Data de finalização*</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="cash-outline"></ion-icon></span>
                    <input type="number" name="valor" id="valor" step="0.1" min="0.1" required> <span id="4"></span>
                    <label for="valor">Valor*</label>
                </div>
                <div class="input-box select">
                    <span class="icon"><ion-icon name="chevron-down-outline"></ion-icon></span>
                    <select class="select-payment" name="formaPagamento" id="formaPagamento" required>
                        <option value="" selected>Selecione</option>
                        <option value="1">Á vista</option>
                        <option value="2">Cartão de crédito</option>
                        <option value="3">Cartão de débito</option>
                        <option value="4">Pix</option>
                    </select> <span id="5"></span>
                    <label class="label-payment" for="formaPagamento">Forma de Pagamento*</label>
                </div>
                <div class="input-box select">
                    <span class="icon"><ion-icon name="chevron-down-outline"></ion-icon></span>
                    <select class="select-payment" name="status" id="status" required>
                        <option value="EMAPROVACAO" selected>Em aprovação</option>
                        <option value="APROVADA">Aprovada</option>
                        <option value="EMANDAMENTO">Em andamento</option>
                        <option value="FINALIZADA">Finalizada</option>
                    </select> <span id="6"></span>
                    <label class="label-payment" for="status">Status do serviço*</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="text-outline"></ion-icon></span>
                    <input type="text" name="observacao" id="observacao" minlength="0" maxlength="100"> <span id="7"></span>
                    <label for="observacao">Observação*</label>
                </div>
                <button type="submit" class="btn">Salvar</button>
            </form>
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

        window.onload = function() {
            setTimeout(function() {
                const formBox = document.querySelector('.wrapper');
                formBox.style.opacity = 1;
            }, 1000);
        };

        closeWrapper.addEventListener('click', function() {
            wrapper.style.opacity = 0;
        });
    </script>
</body>
</html>