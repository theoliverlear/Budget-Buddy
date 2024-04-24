let signupType = document.getElementById('sign-up-type');
let loginType = document.getElementById('login-type');
let signupSection = document.getElementById('sign-up-section');
let loginSection = document.getElementById('login-section');

let signupUsernameInput = document.getElementById('sign-up-username-input');
let signupPasswordInput = document.getElementById('sign-up-password-input');
let signupPasswordConfirmInput = document.getElementById('sign-up-confirm-password-input');
let signupTermsCheckbox = document.getElementById('sign-up-terms-agree-input');
let signupPopupDiv = document.getElementById('sign-up-popup');
let signupPopupText = document.getElementById('sign-up-user-popup-text');
let signupButton = document.getElementById('sign-up-button');

let loginUsernameInput = document.getElementById('login-username-input');
let loginPasswordInput = document.getElementById('login-password-input');
let loginPopupDiv = document.getElementById('login-popup');
let loginPopupText = document.getElementById('login-user-popup-text');
let loginButton = document.getElementById('login-button');

function userTypeChange() {
    let signupChosen = signupType.style.backgroundColor === 'var(--leaf-green)';
    let loginChosen = loginType.style.backgroundColor === 'var(--leaf-green)';
    if (this.id === 'sign-up-type' && !signupChosen) {
        signupType.style.backgroundColor = 'var(--leaf-green)';
        loginType.style.backgroundColor = 'var(--accent-green)';
        signupSection.style.display = 'flex';
        loginSection.style.display = 'none';
    } else if (this.id === 'login-type' && !loginChosen) {
        loginType.style.backgroundColor = 'var(--leaf-green)';
        signupType.style.backgroundColor = 'var(--accent-green)';
        loginSection.style.display = 'flex';
        signupSection.style.display = 'none';
    }
}

function signupPasswordsMatch() {
    return signupPasswordInput.value === signupPasswordConfirmInput.value;
}

function signupTermsAgreed() {
    return signupTermsCheckbox.checked;
}

function signupEmptyInfo() {
    return signupUsernameInput.value === '' ||
           signupPasswordInput.value === '' ||
           signupPasswordConfirmInput.value === '';
}

function passwordMatchPopup() {
    if (!signupPasswordsMatch()) {
        signupPopupDiv.style.display = 'block';
        signupPopupText.textContent = 'Passwords do not match.';
    } else {
        signupPopupText.textContent = '';
        signupPopupDiv.style.display = 'none';
    }
}

function signupTermsPopup() {
    if (!signupTermsAgreed()) {
        signupPopupDiv.style.display = 'block';
        signupPopupText.textContent = 'Please agree to the terms and conditions.';
    } else {
        signupPopupText.textContent = '';
        signupPopupDiv.style.display = 'none';
    }
}

function signupEmptyInfoPopup() {
    if (signupEmptyInfo()) {
        signupPopupDiv.style.display = 'block';
        signupPopupText.textContent = 'Please fill out all fields.';
    } else {
        signupPopupText.textContent = '';
        signupPopupDiv.style.display = 'none';
    }
}

function signupChain() {
    signupEmptyInfoPopup();
    signupTermsPopup();
    if (!signupEmptyInfo() && signupPasswordsMatch() && signupTermsAgreed()) {
        fetch('/user/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: signupUsernameInput.value,
                password: signupPasswordInput.value
            })
        }).then(response => {
            if (response.status === 201) {
                window.location.href = '/budget/';
            } else {
                signupPopupDiv.style.display = 'block';
                signupPopupText.textContent = 'Username already exists.';
            }
        })
    }
}
function loginEmptyInfo() {
    return loginUsernameInput.value === '' || loginPasswordInput.value === '';
}
function loginChain() {
    if (!loginEmptyInfo()) {
        fetch('/user/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: loginUsernameInput.value,
                password: loginPasswordInput.value
            })
        }).then(response => response.json()).then(jsonData => {
            if (jsonData.isAuthorized) {
                window.location.href = '/budget/';
            } else {
                loginPopupDiv.style.display = 'block';
                loginPopupText.textContent = 'Invalid username or password.';
            }
        });
    }
}

signupType.addEventListener('click', userTypeChange);
loginType.addEventListener('click', userTypeChange);

signupPasswordInput.addEventListener('input', passwordMatchPopup);
signupPasswordConfirmInput.addEventListener('input', passwordMatchPopup);
signupButton.addEventListener('click', signupChain);

loginButton.addEventListener('click', loginChain);

