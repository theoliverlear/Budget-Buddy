//================================-Variables-=================================
let accountNameDiv = document.getElementsByClassName('account-name-div');
let accountNameText = document.getElementsByClassName('account-name-text');
//================================-Functions-=================================

//---------------------------Load-Page-With-Account---------------------------
async function loadPageWithAccountName() {
    // This is efficient and should be a setter for values held in the decided
    // data storage.
    // Check if the user is logged in.
    if (await getIsLoggedInFromServer()) {
        // If the user is logged in, set the account name to the user's name.
        await setAccountName();
    } else {
        // If the user is not logged in, set the account name to the default.
        setAccountNameToDefault();
    }
}
//------------------------Get-Is-Logged-In-From-Server------------------------
async function getIsLoggedInFromServer() {
    // Call server to check if user is logged in.
    let isLoggedIn = false;
    try {
        let response = await fetch('/user/loggedin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        // If the response is ok, the user is logged in. Otherwise, the user 
        // is not logged in and isLoggedIn is already set to false.
        isLoggedIn = response.ok;
    } catch (error) {
        // If a bad request is made, it is caught and logged. The user is set
        // to not logged in to prevent any errors.
        console.error('Error: ', error);
        isLoggedIn = false;
    }
    // Return whether the user is logged in.
    return isLoggedIn;
}
//-----------------------------Logout-From-Server-----------------------------
async function logoutFromServer() {
    // Call server to logout user.
    try {
        let response = await fetch('/user/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
    } catch (error) {
        // If a bad request is made, it is caught and logged.
        console.error('Error: ', error);
    }
}
//------------------------Get-Account-Name-From-Server------------------------
async function getAccountNameFromServer() {
    // Call server to get the user's name. The default name is 'Account'.
    let username = 'Account';
    try {
        let response = await fetch('/user/current/username', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        // If the response is ok, the username is set to the response text,
        // which is the user's username.
        if (response.ok) {
            username = response.text();
        } else {
            // If the response is not ok, the username is set to the default.
            username = 'Account';
        }
    } catch (error) {
        // If a bad request is made, it is caught and logged.
        console.error('Error: ', error);
    }
    return username;
}
//------------------------------Set-Account-Name------------------------------
async function setAccountName() {
    // TODO: This is extremely inefficient. Use some sort of data storage
    //       instead.
    // Get the user's name from the server and set the account name to the
    // user's name.
    let username = await getAccountNameFromServer();
    Array.from(accountNameText).forEach(nameText => {
        // Set the text content of the account name to the user's name.
        nameText.textContent = username;
    });
}
//------------------------Set-Account-Name-To-Default-------------------------
function setAccountNameToDefault() {
    // Set the account name to the default name, 'Account'.
    Array.from(accountNameText).forEach(nameText => {
        nameText.textContent = 'Account';
    });
}
//------------------------------Show-Logout-Text------------------------------
function showLogoutText() {
    // Set the account name to 'Logout'.
    Array.from(accountNameText).forEach(nameText => {
        nameText.textContent = 'Logout';
    });
}
//-----------------------------Hover-Logout-Text------------------------------
async function hoverLogoutText() {
    // TODO: This is extremely inefficient. Use some sort of data storage
    //       instead.
    // When hovering over an account button, call the server to check if user
    // is logged in. If the user is logged in, show the logout text.
    if (await getIsLoggedInFromServer()) {
        showLogoutText();
    }
}
//---------------------------Hover-Off-Logout-Text----------------------------
async function hoverOffLogoutText() {
    // TODO: This is extremely inefficient. Use some sort of data storage
    //       instead.
    // When hovering off an account button, call the server to check if user
    // is logged in. If the user is logged in, set the account name to the 
    // user's username.
    if (await getIsLoggedInFromServer()) {
        await setAccountName();
    }
}
//--------------------------------Click-Logout--------------------------------
async function clickLogout() {
    // TODO: This is extremely inefficient. Use some sort of data storage
    //       instead.
    // When clicking on an account button, call the server to check if user
    // is logged in. If the user is logged in, call the server to logout the
    // user and set the account name to the default.
    if (await getIsLoggedInFromServer()) {
        await logoutFromServer();
        setAccountNameToDefault();
    }
}
//=============================-Event-Listeners-==============================
Array.from(accountNameDiv).forEach(nameDiv => {
    nameDiv.addEventListener('click', clickLogout);
    nameDiv.addEventListener('mouseleave', hoverOffLogoutText);
    nameDiv.addEventListener('mouseenter', hoverLogoutText);
});
//================================-Init-Load-=================================
loadPageWithAccountName().catch(error => {
    console.error('Error: ', error);
});