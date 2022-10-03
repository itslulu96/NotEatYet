function setFormMessage(formElement, type, message) {
    const messageElement = formElement.querySelector(".form__message");

    messageElement.textContent = message;
    messageElement.classList.remove("form__message--success", "form__message--error");
    messageElement.classList.add(`form__message--${type}`);
}

function setInputError(inputElement, message) {
    inputElement.classList.add("form__input--error");
    inputElement.parentElement.querySelector(".form__input-error-message").textContent = message;
}

function clearInputError(inputElement) {
    inputElement.classList.remove("form__input--error");
    inputElement.parentElement.querySelector(".form__input-error-message").textContent = "";
}

document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.querySelector("#login");
    const createAccountForm = document.querySelector("#createAccount");

    document.querySelector("#linkCreateAccount").addEventListener("click", e => {
        e.preventDefault();
        loginForm.classList.add("form--hidden");
        createAccountForm.classList.remove("form--hidden");
    });

    document.querySelector("#linkLogin").addEventListener("click", e => {
        e.preventDefault();
        loginForm.classList.remove("form--hidden");
        createAccountForm.classList.add("form--hidden");
    });

    loginForm.addEventListener("submit", e => {
        e.preventDefault();

        var idPassword = document.getElementById("password").value;
        console.log("pass: " + idPassword);
        var idEmail = document.getElementById("email").value;
        console.log("email: " + idEmail);
        // inserire chiamata post per il login

        var idRicevuto = -1;

        $.ajax({
       		async: false,
            url: 'ristoranti/login',
            type: 'POST',
            data: `{"email":"${idEmail}","password":"${idPassword}"}`,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(result) {
                idRicevuto = result;
                console.log(idRicevuto);
            }
        });

        console.log(idRicevuto);
        if (idRicevuto == -1) {
            setFormMessage(loginForm, "error", "Invalid username/password combination");
            console.log("FALLIMENTO");
        } else {
            localStorage.setItem('cookieRistorante', idRicevuto);
            console.log("SUCCESSO");
            window.location.href = "pannello-controllo.html";
        }
    });

    document.querySelectorAll(".form__input").forEach(inputElement => {
        inputElement.addEventListener("blur", e => {
            if (e.target.id === "signupUsername" && e.target.value.length > 0 && e.target.value.length < 10) {
                setInputError(inputElement, "Username must be at least 10 characters in length");
            }
        });

        inputElement.addEventListener("input", e => {
            clearInputError(inputElement);
        });
    });
});