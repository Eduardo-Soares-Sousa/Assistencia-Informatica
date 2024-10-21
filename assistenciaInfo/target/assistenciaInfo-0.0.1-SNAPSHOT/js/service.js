window.onload = function() {
        // Esconde o formulário inicialmente
        const formBox = document.querySelector('.form-box');
        formBox.style.opacity = 0;
        formBox.style.transition = 'opacity 0.5s ease-in-out';

        // Mostra o formulário após 2 segundos
        setTimeout(function() {
            formBox.style.opacity = 1;
        }, 2000);
};

window.onload = function() {
        const formBox = document.querySelector('.form-box');
        formBox.style.opacity = 0;
        formBox.style.transition = 'opacity 0.5s ease-in-out';
        setTimeout(function() {
            formBox.style.opacity = 1;
        }, 2000);
};