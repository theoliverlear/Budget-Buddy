console.log('globalScript.js loaded');
let accountNameDiv = document.getElementsByClassName('account-name-div');
let accountNameText = document.getElementsByClassName('account-name-text');
loadPageWithAccountName().then(() => {
    console.log('Page loaded with account name');
}).catch(error => {
    console.error('Error: ', error);
});
async function loadPageWithAccountName() {
    // This is efficient and should be a setter for values held in the decided
    // data storage.
    if (await getIsLoggedInFromServer()) {
        await setAccountName();
    } else {
        setAccountNameToDefault();
    }
}

async function getIsLoggedInFromServer() {
    let isLoggedIn = false;
    try {
        let response = await fetch('/user/loggedin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        isLoggedIn = response.ok;
    } catch (error) {
        console.error('Error: ', error);
        isLoggedIn = false;
    }
    console.log("Logged in: " + isLoggedIn)
    return isLoggedIn;
}
async function logoutFromServer() {
    try {
        let response = await fetch('/user/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            console.log('Logout successful');
        } else {
            console.log('Not logged in');
        }
    } catch (error) {
        console.error('Error:', error);
    }
}
async function getAccountNameFromServer() {
    let username = 'Account';
    try {
        let response = await fetch('/user/current/username', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            username = response.text();
        } else {
            username = 'Account';
        }
    } catch (error) {
        console.error('Error: ', error);
    }
    return username;
}
async function setAccountName() {
    // TODO: This is extremely inefficient. Use some sort of data storage
    //       instead.
    let username = await getAccountNameFromServer();
    Array.from(accountNameText).forEach(nameText => {
        nameText.textContent = username;
    });
}
function setAccountNameToDefault() {
    Array.from(accountNameText).forEach(nameText => {
        nameText.textContent = 'Account';
    });
}
function showLogoutText() {
    Array.from(accountNameText).forEach(nameText => {
        nameText.textContent = 'Logout';
    });
}
async function hoverLogoutText() {
    // TODO: This is extremely inefficient. Use some sort of data storage
    //       instead.
    if (await getIsLoggedInFromServer()) {
        showLogoutText();
    }
}
async function hoverOffLogoutText() {
    // TODO: This is extremely inefficient. Use some sort of data storage
    //       instead.
    if (await getIsLoggedInFromServer()) {
        await setAccountName();
    }
}
async function clickLogout() {
    // TODO: This is extremely inefficient. Use some sort of data storage
    //       instead.
    if (await getIsLoggedInFromServer()) {
        await logoutFromServer();
        setAccountNameToDefault();
    }
}
Array.from(accountNameDiv).forEach(nameDiv => {
    nameDiv.addEventListener('click', clickLogout);
    nameDiv.addEventListener('mouseleave', hoverOffLogoutText);
    nameDiv.addEventListener('mouseenter', hoverLogoutText);
});
