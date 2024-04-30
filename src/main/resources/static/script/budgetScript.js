//=================================-Imports-==================================
import { BudgetItem } from './BudgetItem.js';
import { colorPalette, colorPaletteBorders } from "./colorScript.js";
//=================================-Variables-================================

//---------------------------------Containers---------------------------------
let budgetItemsContainer = document.getElementById('budget-items-container');
//-----------------------------------Cache------------------------------------
let lastEditedName = '';
//---------------------------------Item-Input---------------------------------
let itemInputerDiv = document.getElementById('item-inputer');
let budgetItemNameInput = document.getElementById('item-name-input');
let budgetItemCostInput = document.getElementById('item-cost-input');
let budgetItemCategoryInput = document.getElementById('item-category-input');
//----------------------------------Buttons-----------------------------------
let budgetItemsEditButtons = document.getElementsByClassName('edit-budget-item-button');
let budgetItemsSaveButtons = document.getElementsByClassName('save-budget-item-button');
let budgetItemsRemoveButtons = document.getElementsByClassName('remove-budget-item-button');
let addButton = document.getElementById('add-button');
let addItemButton = document.getElementById('add-item-button');
//-----------------------------------Charts-----------------------------------
let pieChart = null;
let budgetGraph = document.getElementById('budget-graph');
//================================-Functions-=================================

//------------------------------Get-Budget-Items------------------------------
function loadBudgetItemsToPage() {
    // Call server to get the user's budget items.
    fetch('/budget/get', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        return response.json();
    }).then(data => {
        // For each budget item, add it to the page.
        data.budgetItems.forEach(item => {
            addBudgetItemToPage(item.name, item.amount, item.category.title);
        });
    }).catch(error => {
        // If a bad request is made, it is caught and logged.
        console.error('Error: ', error);
    });
}
//-------------------------Add-Budget-Item-To-Account-------------------------
function addBudgetItemToAccount(name, amount, category) {
    // Given the name, amount, and category of a budget item, we send a POST
    // request to the server to add the budget item to the user's account.
    fetch('/budget/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        // Send the name, amount, and category to the server.
        body: JSON.stringify({
            name: name,
            amount: amount,
            category: category
        })
    }).then(response => {
        // If the response status is 201, then the item was added
        // successfully, so we add the item to the page.
        if (response.status === 201) {
            addBudgetItemToPage(name, amount, category);
        }
    }).catch(error => {
        // If a bad request is made, it is caught and logged.
        console.error('Error:', error);
    });
    // Using the new budget information, we rebuild the charts.
    buildCharts().then(() => {
        console.log('Rebuilt Charts');
    });
}
//-----------------------Amount-Float-Artifact-Remover------------------------
function amountFloatArtifactRemover(amount) {
    // Given an amount, we remove the dollar sign and convert it to a float so
    // that it can be sent to the server without causing errors.
    amount = amount.replace('$', '');
    amount = parseFloat(amount);
    return amount;
}
//----------------------Remove-Budget-Item-From-Account-----------------------
function removeBudgetItemFromAccount(name, amount, category) {
    // Given the name, amount, and category of a budget item, we send a POST
    // request to the server to remove the budget item from the user's
    // account.
    // We remove any artifacts from the amount and convert it to a float so it
    // can be sent to the server without causing errors.
    amount = amountFloatArtifactRemover(amount);
    fetch('/budget/remove', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        // Send the name, amount, and category to the server.
        body: JSON.stringify({
            name: name,
            amount: amount,
            category: category
        })
    }).catch(error => {
        // If a bad request is made, it is caught and logged.
        console.error('Error: ', error);
    });
    // Using the new budget information, we rebuild the charts.
    buildCharts().then(() => {
        console.log('Rebuilt Charts');
    });
}
//-------------------------Add-Budget-Item-From-Input-------------------------
function addBudgetItemFromInput() {
    //-----------------------------Get-Inputs---------------------------------
    let budgetInputName = budgetItemNameInput.value;
    let budgetInputCost = budgetItemCostInput.value;
    let budgetInputCategory = budgetItemCategoryInput.value;
    //-------------------------Build-Budget-Item------------------------------
    let budgetItem = new BudgetItem(budgetInputName, budgetInputCost, budgetInputCategory);
    //----------------------------Create-Html---------------------------------
    let budgetItemHtml = budgetItem.buildAddItemHtml();
    let tempDiv = document.createElement('div');
    tempDiv.classList.add('budget-item');
    tempDiv.innerHTML = budgetItemHtml;
    budgetItemsContainer.appendChild(tempDiv);
    //---------------------Add-Budget-Item-To-Account-------------------------
    addBudgetItemToAccount(budgetInputName, budgetInputCost, budgetInputCategory);
    //--------------------------Attach-Listeners------------------------------
    attachEditButtonListener();
    attachRemoveButtonListener();
    //--------------------------Sanitize-Inputs-------------------------------
    hideItemInputer();
    clearItemInputs();
}
//--------------------------Add-Budget-Item-To-Page---------------------------
function addBudgetItemToPage(name, amount, category) {
    // Given the name, amount, and category of a budget item, we add the item
    // to the page.
    let budgetItem = new BudgetItem(name, amount, category);
    // Create the HTML for the budget item.
    let budgetItemHtml = budgetItem.buildAddItemHtml();
    let tempDiv = document.createElement('div');
    tempDiv.classList.add('budget-item');
    tempDiv.innerHTML = budgetItemHtml;
    // Add the budget item to the page.
    budgetItemsContainer.appendChild(tempDiv);
    //--------------------------Attach-Listeners------------------------------
    attachEditButtonListener();
    attachRemoveButtonListener();
    //--------------------------Sanitize-Inputs-------------------------------
    hideItemInputer();
    clearItemInputs();
}
//-----------------------------Hide-Item-Inputter-----------------------------
function hideItemInputer() {
    // Hide the item inputter by setting the display to none.
    itemInputerDiv.style.display = 'none';
}
//-----------------------------Show-Item-Inputter-----------------------------
function showItemInputer() {
    // Show the item inputter by setting the display to flex.
    itemInputerDiv.style.display = 'flex';
}
//-----------------------------Clear-Item-Inputs------------------------------
function clearItemInputs() {
    // Clear the item inputs by setting the value of each input to an empty
    // string.
    budgetItemNameInput.value = '';
    budgetItemCostInput.value = '';
    budgetItemCategoryInput.value = '';
}
//------------------------------Edit-Budget-Item------------------------------
function editBudgetItem() {
    // Get the parent of the edit button.
    let parent = this.parentElement.parentElement.parentElement;
    // Get the name of the budget item.
    let name = parent.getElementsByClassName('budget-item-name-text')[0].innerText;
    // Cache the name of the budget item.
    lastEditedName = name;
    // Save the amount and category of the budget item.
    let amount = parent.getElementsByClassName('budget-item-amount-text')[0].innerText.replace('$', '');
    let category = parent.getElementsByClassName('budget-item-category-text')[0].innerText;
    // Create a new budget item with the name, amount, and category.
    let editBudgetItem = new BudgetItem(name, amount, category);
    //----------------------------Create-Html---------------------------------
    let editBudgetItemHtml = editBudgetItem.buildEditItemHtml();
    let tempDiv = document.createElement('div');
    tempDiv.innerHTML = editBudgetItemHtml;
    parent.replaceWith(tempDiv);
    //--------------------------Attach-Listeners------------------------------
    attachSaveButtonListener();
    attachEditButtonListener();
}
//------------------------------Save-Budget-Item------------------------------
function saveBudgetItem() {
    // Get the parent of the save button.
    let parent = this.parentElement.parentElement;
    // Get the name, amount, and category of the budget item.
    let name = parent.getElementsByClassName('budget-item-input')[0].value;
    let amount = parent.getElementsByClassName('budget-item-input')[1].value;
    let category = parent.getElementsByClassName('budget-item-input')[2].value;
    // Create a new budget item with the name, amount, and category.
    let saveBudgetItem = new BudgetItem(name, amount, category);
    //----------------------------Create-Html---------------------------------
    let saveBudgetItemHtml = saveBudgetItem.buildAddItemHtml();
    let tempDiv = document.createElement('div');
    tempDiv.classList.add('budget-item');
    tempDiv.innerHTML = saveBudgetItemHtml;
    parent.replaceWith(tempDiv);
    // If the name of the budget item is the same as the last edited name, we
    // edit the budget item to the account as is. Otherwise, we edit the
    // budget item to the account with the new name.
    if (name === lastEditedName) {
        editBudgetItemToAccount(name, amount, category);
    } else {
        editBudgetItemToAccountWithNewName(name, amount, category);
    }
    //--------------------------Attach-Listeners------------------------------
    attachEditButtonListener();
    attachRemoveButtonListener();
}
//------------------------Edit-Budget-Item-To-Account-------------------------
function editBudgetItemToAccount(name, amount, category) {
    // Remove any artifacts from the amount and convert it to a float so it
    // can be sent to the server without causing errors. We send a POST
    // request to the server to edit the budget item to the user's account.
    amount = amountFloatArtifactRemover(amount);
    fetch('/budget/edit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        // Send the name, amount, and category to the server.
        body: JSON.stringify({
            name: name,
            amount: amount,
            category: category
        })
    }).then(response => {
        // If the response status is not 200, we log the error.
        if (response.status !== 200) {
            console.error('Error: ', response.status);
        }
    }).catch(error => {
        // If a bad request is made, it is caught and logged.
        console.error('Error: ', error);
    });
    // Using the new budget information, we rebuild the charts.
    buildCharts().then(() => {
        console.log('Rebuilt Charts');
    });
}
//-----------------Edit-Budget-Item-To-Account-With-New-Name------------------
function editBudgetItemToAccountWithNewName(name, amount, category) {
    // Remove any artifacts from the amount and convert it to a float so it
    // can be sent to the server without causing errors. We send a POST
    // request to the server to edit the budget item to the user's account.
    amount = amountFloatArtifactRemover(amount);
    fetch(`/budget/edit/${name}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        // Send the name, amount, and category to the server.
        body: JSON.stringify({
            name: lastEditedName,
            amount: amount,
            category: category
        })
    }).catch(error => {
        // If a bad request is made, it is caught and logged.
        console.error('Error: ', error);
    });
    // Using the new budget information, we rebuild the charts.
    buildCharts().then(() => {
        console.log('Rebuilt Charts');
    });
}
//-----------------------------Delete-Budget-Item-----------------------------
function deleteBudgetItem() {
    // We check if the item clicked is the remove button.
    if (this.classList.contains('remove-budget-item-button')) {
        // If it is, we get the name, amount, and category of the budget item.
        let name = this.parentElement.parentElement.getElementsByClassName('budget-item-name-text')[0].innerText;
        let amount = this.parentElement.parentElement.getElementsByClassName('budget-item-amount-text')[0].innerText;
        let category = this.parentElement.parentElement.getElementsByClassName('budget-item-category-text')[0].innerText;
        // We send a request to the server to remove the budget item from the
        // user's account.
        removeBudgetItemFromAccount(name, amount, category);
        // We remove the budget item from the page.
        let parent = this.parentElement.parentElement.parentElement;
        parent.remove();
    }
}
//--------------------------Get-Budget-From-Account---------------------------
async function getBudgetFromAccount() {
    // We send a GET request to the server to get the user's budget items.
    let budget = await fetch('/budget/get', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        return response.json();
    }).then(data => {
        // We return the budget items from the response.
        return data.budgetItems;
    }).catch(error => {
        // If a bad request is made, it is caught and logged.
        console.error('Error: ', error);
    });
    // We return the budget items list.
    return budget;
}
//--------------------------------Build-Charts--------------------------------
async function buildCharts() {
    // We get the budget items from the user's account.
    let budget = await getBudgetFromAccount();
    // We build the pie chart with the budget items.
    buildPieChart(budget);
}
//------------------------------Build-Pie-Charts------------------------------
function buildPieChart(budget) {
    // If there is already a pie chart (is truthy), we destroy it.
    if (pieChart) {
        pieChart.destroy();
    }
    // We get the colors for the pie chart from the imported colorPalette()
    // function.
    let colors = colorPalette(budget.length);
    // We get the border colors for the pie chart from the imported
    // colorPaletteBorders() function.
    let colorsPaletteBorders = colorPaletteBorders(colors);
    // We create a new pie chart with the budget items.
    pieChart = new Chart(budgetGraph, {
        type: 'pie',
        data: {
            // Map the budget item names to the labels.
            labels: budget.map(item => item.name),
            datasets: [{
                    label: 'Budget',
                    // Map the budget item amounts to the data.
                    data: budget.map(item => item.amount),
                    // Set the background colors to the colors color palette.
                    backgroundColor: colors,
                    // Set the border colors to the colorsPaletteBorders color
                    // palette.
                    borderColor: colorsPaletteBorders,
                    borderWidth: 1
                }]
        },
        options: {
            title: {
                display: true,
                // Set the title to 'Budget Items'.
                text: 'Budget Items'
            }
        }
    });
}
//------------------------Attach-Edit-Button-Listener-------------------------
function attachEditButtonListener() {
    budgetItemsEditButtons = document.getElementsByClassName('edit-budget-item-button');
    Array.from(budgetItemsEditButtons).forEach(button => {
        button.addEventListener('click', editBudgetItem);
    });
}
//------------------------Attach-Save-Button-Listener-------------------------
function attachSaveButtonListener() {
    budgetItemsSaveButtons = document.getElementsByClassName('save-budget-item-button');
    Array.from(budgetItemsSaveButtons).forEach(button => {
        button.addEventListener('click', saveBudgetItem);
    });
}
//-----------------------Attach-Remove-Button-Listener------------------------
function attachRemoveButtonListener() {
    budgetItemsRemoveButtons = document.getElementsByClassName('remove-budget-item-button');
    Array.from(budgetItemsRemoveButtons).forEach(button => {
        button.addEventListener('click', deleteBudgetItem);
    });
}
//=============================-Event-Listeners-==============================
Array.from(budgetItemsEditButtons).forEach(button => {
    button.addEventListener('click', editBudgetItem);
});
Array.from(budgetItemsRemoveButtons).forEach(button => {
    button.addEventListener('click', deleteBudgetItem);
});
Array.from(budgetItemsSaveButtons).forEach(button => {
    button.addEventListener('click', saveBudgetItem);
});
addButton.addEventListener('click', addBudgetItemFromInput);
addItemButton.addEventListener('click', showItemInputer);
//================================-Init-Load-=================================
loadBudgetItemsToPage();
buildCharts().then(() => {
    console.log('Charts built');
});