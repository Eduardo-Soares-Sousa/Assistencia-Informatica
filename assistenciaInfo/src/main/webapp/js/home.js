const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const btnPopup = document.querySelector('.btnLogin-popup');
const iconClose = document.querySelector('.icon-close');

registerLink.addEventListener('click', ()=> {
    wrapper.classList.add('active');
});

loginLink.addEventListener('click', ()=> {
    wrapper.classList.remove('active');
});

btnPopup.addEventListener('click', ()=> {
    wrapper.classList.add('active');
});

btnPopup.addEventListener('click', ()=> {
    wrapper.classList.remove('active-popup');
});

iconClose.addEventListener('click', ()=> {
    wrapper.classList.remove('active-popup');
});

function goToStep2() {
    document.getElementById("step1").style.display = "none";
    document.getElementById("step2").style.display = "block";
}

function backStep1() {
    document.getElementById("step1").style.display = "block";
    document.getElementById("step2").style.display = "none";
}

function registerForm() {
    const wrapper = document.querySelector('.wrapper');
    wrapper.classList.toggle('active-popup');
}