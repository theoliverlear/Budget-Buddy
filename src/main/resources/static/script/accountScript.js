//================================-Variables-=================================

//------------------------------User-Info-Types-------------------------------
let signupType = document.getElementById('sign-up-type');
let loginType = document.getElementById('login-type');
//--------------------------User-Info-Type-Sections---------------------------
let signupSection = document.getElementById('sign-up-section');
let loginSection = document.getElementById('login-section');
//-------------------------------Signup-Inputs--------------------------------
let signupUsernameInput = document.getElementById('sign-up-username-input');
let signupPasswordInput = document.getElementById('sign-up-password-input');
let signupPasswordConfirmInput = document.getElementById('sign-up-confirm-password-input');
let signupTermsCheckbox = document.getElementById('sign-up-terms-agree-input');
//-------------------------------Login-Inputs---------------------------------
let loginUsernameInput = document.getElementById('login-username-input');
let loginPasswordInput = document.getElementById('login-password-input');
//--------------------------------Popup-Boxes---------------------------------
let signupPopupDiv = document.getElementById('sign-up-popup');
let signupPopupText = document.getElementById('sign-up-user-popup-text');
let loginPopupDiv = document.getElementById('login-popup');
let loginPopupText = document.getElementById('login-user-popup-text');
//----------------------------------Buttons-----------------------------------
let signupButton = document.getElementById('sign-up-button');
let loginButton = document.getElementById('login-button');
//================================-Functions-=================================

//------------------------------User-Type-Change------------------------------
function userTypeChange() {
    // If the background of the type is the leaf green, then then that type is
    // selected.
    let signupChosen = signupType.style.backgroundColor === 'var(--leaf-green)';
    let loginChosen = loginType.style.backgroundColor === 'var(--leaf-green)';
    // Check if the clicked type is a sign-up type and if it is not already
    // selected.
    if (this.id === 'sign-up-type' && !signupChosen) {
        // Change the background of the selected type to leaf green and the
        // other type to accent green. Display the sign-up section and hide
        // the login section.
        signupType.style.backgroundColor = 'var(--leaf-green)';
        loginType.style.backgroundColor = 'var(--accent-green)';
        signupSection.style.display = 'flex';
        loginSection.style.display = 'none';
    } else if (this.id === 'login-type' && !loginChosen) {
        // Otherwise, if the clicked type is a login type and it is not
        // already selected, change the background of the selected type to
        // leaf green and the other type to accent green. Display the login
        // section and hide the sign-up section.
        loginType.style.backgroundColor = 'var(--leaf-green)';
        signupType.style.backgroundColor = 'var(--accent-green)';
        loginSection.style.display = 'flex';
        signupSection.style.display = 'none';
    }
}
//---------------------------Signup-Passwords-Match---------------------------
function signupPasswordsMatch() {
    // If the password and confirm password inputs have the same value, then
    // the passwords match. The status of the password and confirm password
    // equality is returned.
    return signupPasswordInput.value === signupPasswordConfirmInput.value;
}
//----------------------------Signup-Terms-Agreed-----------------------------
function signupTermsAgreed() {
    // If the signup terms checkbox is checked, the user has agreed to the
    // terms and conditions. The status of the checkbox is returned.
    return signupTermsCheckbox.checked;
}
//-----------------------------Signup-Empty-Info------------------------------
function signupEmptyInfo() {
    // If any of the signup inputs are empty, then the user has not filled out
    // all fields. The status of whether any of the fields are empty is
    // returned.
    return signupUsernameInput.value === '' ||
           signupPasswordInput.value === '' ||
           signupPasswordConfirmInput.value === '';
}
//----------------------------Password-Match-Popup----------------------------
function passwordMatchPopup() {
    // If the passwords do not match, display the popup box with the text
    // 'Passwords do not match'.
    if (!signupPasswordsMatch()) {
        signupPopupDiv.style.display = 'block';
        signupPopupText.textContent = 'Passwords do not match.';
    } else {
        // Otherwise, hide the popup box and clear the text.
        signupPopupText.textContent = '';
        signupPopupDiv.style.display = 'none';
    }
}
//-----------------------------Signup-Terms-Popup-----------------------------
function signupTermsPopup() {
    // If the user has not agreed to the terms and conditions, display the
    // popup box with the text 'Please agree to the terms and conditions'.
    if (!signupTermsAgreed()) {
        signupPopupDiv.style.display = 'block';
        signupPopupText.textContent = 'Please agree to the terms and conditions.';
    } else {
        // Otherwise, hide the popup box and clear the text.
        signupPopupText.textContent = '';
        signupPopupDiv.style.display = 'none';
    }
}
//--------------------------Signup-Empty-Info-Popup---------------------------
function signupEmptyInfoPopup() {
    // If the user has not filled out all fields, display the popup box with
    // the text 'Please fill out all fields'.
    if (signupEmptyInfo()) {
        signupPopupDiv.style.display = 'block';
        signupPopupText.textContent = 'Please fill out all fields.';
    } else {
        // Otherwise, hide the popup box and clear the text.
        signupPopupText.textContent = '';
        signupPopupDiv.style.display = 'none';
    }
}
//---------------------------Signup-User-To-Server----------------------------
function signupUserToServer() {
    // Send a POST request to the server to sign up the user.
    fetch('/user/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        // Send the username and password to the server.
        body: JSON.stringify({
            username: signupUsernameInput.value,
            password: signupPasswordInput.value
        })
    }).then(response => {
        // If the response status is 201, the user was created successfully.
        // We redirect the user to the budget page.
        if (response.status === 201) {
            window.location.href = '/budget/';
        } else {
            // Otherwise, display the popup box with the text 'Username
            // already exists' since that is the only other possible response.
            signupPopupDiv.style.display = 'block';
            signupPopupText.textContent = 'Username already exists.';
        }
    });
}
//--------------------------------Signup-Chain--------------------------------
function signupChain() {
    // Check if the user has filled out all fields. If not, display the popup
    // box.
    signupEmptyInfoPopup();
    // Check if the terms of service have been agreed to. If not, display the
    // popup box.
    signupTermsPopup();
    // If the user has filled out all fields, the passwords match, and the
    // terms of service have been agreed to, sign up the user.
    if (!signupEmptyInfo() && signupPasswordsMatch() && signupTermsAgreed()) {
        signupUserToServer();
    }
}
//------------------------------Login-Empty-Info------------------------------
function loginEmptyInfo() {
    // If the login username or password inputs are empty, then the user has
    // not filled out all fields. The status of whether any of the fields are
    // empty is returned.
    return loginUsernameInput.value === '' || loginPasswordInput.value === '';
}
//----------------------------Login-User-To-Server----------------------------
function loginUserToServer() {
    // Send a POST request to the server to login the user.
    fetch('/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        // Send the username and password to the server.
        body: JSON.stringify({
            username: loginUsernameInput.value,
            password: loginPasswordInput.value
        })
        // Receive a JSON response from the server.
    }).then(response => response.json()).then(jsonData => {
        // If the user is authorized, redirect the user to the budget page.
        if (jsonData.isAuthorized) {
            window.location.href = '/budget/';
        } else {
            // Otherwise, display the popup box with the text 'Invalid
            // username or password' since that is the only other possible
            // response.
            loginPopupDiv.style.display = 'block';
            loginPopupText.textContent = 'Invalid username or password.';
        }
    });
}
//--------------------------------Login-Chain---------------------------------
function loginChain() {
    // Check if the user has filled out all fields. If they have, log them
    // into the server.
    if (!loginEmptyInfo()) {
        loginUserToServer();
    }
}
//=============================-Event-Listeners-==============================
signupType.addEventListener('click', userTypeChange);
loginType.addEventListener('click', userTypeChange);
signupPasswordInput.addEventListener('input', passwordMatchPopup);
signupPasswordConfirmInput.addEventListener('input', passwordMatchPopup);
signupButton.addEventListener('click', signupChain);
loginButton.addEventListener('click', loginChain);

