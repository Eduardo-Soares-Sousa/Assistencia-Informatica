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
    <title>Adicionar Forma de Pagamento - ITech.com</title>
</head>
<style>
body{
    background: #201b2c;
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
    opacity: 0;
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


.wrapper{
    transition: opacity 0.5s ease;
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
    <div class="wrapper" id="wrapper">
        <span class="icon-close" id="icon-close">
            <ion-icon name="close"></ion-icon>
        </span>
        <div class="form-box register">
            <h2>Adicionar Método de Pagamento</h2>
            <form action="registerPaymentMethod" method="post">
                <div class="input-box">
                    <span class="icon"><ion-icon name="document-text-outline"></ion-icon></span>
                    <input type="text" name="method" id="method" minlength="2" maxlength="100" required> <span id="1"></span>
                    <label for="method">Novo método*</label>
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