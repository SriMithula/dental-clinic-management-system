const loginForm = document.getElementById("loginForm");

const username = document.getElementById("username");
const password = document.getElementById("password");

const usernameError = document.getElementById("usernameError");
const passwordError = document.getElementById("passwordError");

loginForm.addEventListener("submit", function (event) {

    let isValid = true;

    usernameError.textContent = "";
    passwordError.textContent = "";

    username.classList.remove("input-error");
    password.classList.remove("input-error");

    // Username validation
    if (username.value.trim() === "") {
        usernameError.textContent = "Username is required.";
        username.classList.add("input-error");
        isValid = false;
    }

    // Password validation
    if (password.value === "") {
        passwordError.textContent = "Password is required.";
        password.classList.add("input-error");
        isValid = false;
    }

    if (!isValid) {
        event.preventDefault();
    }
});