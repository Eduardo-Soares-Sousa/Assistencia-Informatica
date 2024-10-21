<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/home.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>ITech.com</title>
<style>
@charset "UTF-8";
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

*{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}

body{
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: url('img/teste3.jpg') no-repeat;
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

.navegation .btn-login{
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

.navegation .btn-login:hover{
    background: white;
    color: #162938;
}

.wrapper{
    position: relative;
    width: 400px;
    height: 450px;
    background: transparent;
    border: 2px solid rgba(255, 255, 255, .5);
    border-radius: 20px;
    backdrop-filter: blur(20px);
    box-shadow: 0 0 30px rgba(0, 0, 0, .5);
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    transform: scale(0);
    transition: transform .5s ease, height .2s ease;
}

.wrapper.active-popup{
    transform: scale(1);
}

.wrapper.active{
    height: 550px;
}

.wrapper .form-box{
    width: 100%;
    padding: 40px;
}

.wrapper .form-box.login{
    transition: transform .18s ease;
    transform: translateX(0);
}

.wrapper.active .form-box.login{
    transition: none;
    transform: translateX(-400px);
}

.wrapper .form-box.register{
    position: absolute;
    transition: none;
    transform: translateX(400px);
}

.wrapper.active .form-box.register{
    transition: transform .18s ease;
    transform: translateX(0);
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
    cursor: pointer;
    z-index: 1;
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
    border-bottom: 2px solid ##fff;
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

.remember-forgot{
    font-size: .9em;
    color: #fff;
    font-weight: 500;
    margin: -15px 0 15px;
    display: flex;
    justify-content: space-between;
}

.remember-forgot label input{
    accent-color: #fff;
    margin-right: 3px;
}

.remember-forgot a{
    color: #fff;
    text-decoration: none;
}

.remember-forgot a:hover{
    text-decoration: underline;
}

.btn{
    width: 100%;
    height: 45px;
    background: #778899;
    border: none;
    outline: none;
    border-radius: 6px;
    cursor: pointer;
    font-size: 1em;
    color: white;
    font-weight: 500;
}

.login-register {
    font-size: .9em;
    color: #fff;
    text-align: center;
    font-weight: 500;
    margin: 25px 0 10px;
}

.login-register p a {
    color: #fff;
    text-decoration: none;
    font-weight: 600;
}

.login-register p a:hover{
    text-decoration: underline;
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
</head>
<body>
<div class="alerts-container">
        <c:choose>
            <c:when test="${result == 'registered'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Funcionário cadastrado com sucesso. Faça o login.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:when>
            <c:when test="${result == 'loginSucess'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Login efetuado com sucesso.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:when>
            <c:when test="${result == 'loginError'}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    E-mail e/ou senha inválidos.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:when>
        </c:choose>
        <c:if test="${result == 'notRegistered'}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                E-mail já cadastrado. Tente novamente.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
</div>
<header>
        <h2 class="logo">Logo</h2>
        <nav class="navegation">
            <a href="registerEmployee.jsp">Home</a>
            <a href="#">About</a>
            <a href="#">Contact us</a>
            <button class="btn-login">Login</button>
        </nav>
    </header>

    <div class="wrapper">
        <span class="icon-close">
            <ion-icon name="close"></ion-icon>
        </span>

        <div class="form-box login">
            <h2>Login</h2>
            <form action="loginEmployee" method="post">
                <div class="input-box">
                    <span class="icon"><ion-icon name="mail"></ion-icon></span>
                    <input type="email" name="email" id="email" required>
                    <label for="email">Email*</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
                    <input type="password" name="password" id="password" required>
                    <label for="password">Senha*</label>
                </div>
                <div class="remember-forgot">
                    <label><input type="checkbox">Lembrar-me</label>
                    <a href="#">Esqueci minha senha</a>
                </div>
                <button type="submit" class="btn">Login</button>
                <div class="login-register">
                    <p>Não tem conta? <a href="#" class="register-link">Cadastrar</a></p>
                </div>
            </form>
        </div>

        <div class="form-box register">
            <h2>Cadastro</h2>
            <form action="registerEmployee" method="post">
                <div class="input-box">
                    <span class="icon"><ion-icon name="person"></ion-icon></span>
                    <input type="text" name="name" id="name" minlength="3" maxlength="50" required> <span id="0"></span>
                    <label for="name">Nome completo*</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="mail"></ion-icon></span>
                    <input type="email" name="email" id="email" required> <span id="1"></span>
                    <label for="email">Email*</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
                    <input type="password" name="password" id="password" required> <span id="2"></span>
                    <label for="password">Senha*</label>
                </div>
                <div class="remember-forgot">
                    <label><input type="checkbox">Eu concordo com os termos</label>
                </div>
                <button type="submit" class="btn">Cadastrar</button>
                <div class="login-register">
                    <p>
                        já tenho conta? <a href="#" class="login-link">Login</a>
                    </p>
                </div>
            </form>
        </div>
    </div>

<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script type="text/javascript" src="js/home.js"></script>
<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
</script>
</body>
</html>