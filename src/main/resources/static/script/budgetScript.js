import { BudgetItem } from './BudgetItem.js';
import { colorPalette, colorPaletteBorders } from "./colorScript.js";
let budgetItemNameInput = document.getElementById('item-name-input');
let budgetItemCostInput = document.getElementById('item-cost-input');
let budgetItemCategoryInput = document.getElementById('item-category-input');
let addButton = document.getElementById('add-button');
let budgetItems = document.getElementsByClassName('budget-item');
let budgetItemsContainer = document.getElementById('budget-items-container');
let budgetItemsEditButtons = document.getElementsByClassName('edit-budget-item-button');
let budgetItemsSaveButtons = document.getElementsByClassName('save-budget-item-button');
let budgetItemsRemoveButtons = document.getElementsByClassName('remove-budget-item-button');
let addItemButton = document.getElementById('add-item-button');
let itemInputerDiv = document.getElementById('item-inputer');
//------------------------------Get-Budget-Items------------------------------
function loadBudgetItemsToPage() {
    fetch('/budget/get', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        return response.json();
    }).then(data => {
        console.log(data);
        data.budgetItems.forEach(item => {
            console.log(item);
            addBudgetItemToPage(item.name, item.amount, item.category.title);
        });
    }).catch(error => {
        console.error('Error:', error);
    });
}
loadBudgetItemsToPage();
//-------------------------Add-Budget-Item-To-Account-------------------------
function addBudgetItemToAccount(name, amount, category) {
    fetch('/budget/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            amount: amount,
            category: category
        })
    }).then(response => {
        if (response.status === 201) {
            addBudgetItemToPage(name, amount, category);
        } else {
            console.log('Error:', response.status);
        }
    }).catch(error => {
        console.error('Error:', error);
    });
    buildCharts().then(() => {
        console.log('Rebuilt Charts');
    });
}
function amountFloatArtifactRemover(amount) {
    amount = amount.replace('$', '');
    amount = parseFloat(amount);
    return amount;
}
//----------------------Remove-Budget-Item-From-Account-----------------------
function removeBudgetItemFromAccount(name, amount, category) {
    console.log("Remove button clicked");
    amount = amountFloatArtifactRemover(amount);
    fetch('/budget/remove', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            amount: amount,
            category: category
        })
    }).then(response => {
        if (response.status === 200) {
            console.log('Item removed');
        } else {
            console.log('Error:', response.status);
        }
    }).catch(error => {
        console.error('Error:', error);
    });
    buildCharts().then(() => {
        console.log('Rebuilt Charts');
    });
}
//-------------------------Add-Budget-Item-From-Input-------------------------
function addBudgetItemFromInput() {
    let budgetInputName = budgetItemNameInput.value;
    let budgetInputCost = budgetItemCostInput.value;
    let budgetInputCategory = budgetItemCategoryInput.value;
    let budgetItem = new BudgetItem(budgetInputName, budgetInputCost, budgetInputCategory);
    let budgetItemHtml = budgetItem.buildAddItemHtml();
    let tempDiv = document.createElement('div');
    tempDiv.classList.add('budget-item');
    tempDiv.innerHTML = budgetItemHtml;
    budgetItemsContainer.appendChild(tempDiv);
    addBudgetItemToAccount(budgetInputName, budgetInputCost, budgetInputCategory);
    attachEditButtonListener();
    attachRemoveButtonListener();
    hideItemInputer();
    clearItemInputs();
}
//--------------------------Add-Budget-Item-To-Page---------------------------
function addBudgetItemToPage(name, amount, category) {
    let budgetItem = new BudgetItem(name, amount, category);
    let budgetItemHtml = budgetItem.buildAddItemHtml();
    let tempDiv = document.createElement('div');
    tempDiv.classList.add('budget-item');
    tempDiv.innerHTML = budgetItemHtml;
    budgetItemsContainer.appendChild(tempDiv);
    attachEditButtonListener();
    attachRemoveButtonListener();
    hideItemInputer();
    clearItemInputs();
}
//-----------------------------Hide-Item-Inputter-----------------------------
function hideItemInputer() {
    itemInputerDiv.style.display = 'none';
}
//-----------------------------Show-Item-Inputter-----------------------------
function showItemInputer() {
    itemInputerDiv.style.display = 'flex';
}
//-----------------------------Clear-Item-Inputs------------------------------
function clearItemInputs() {
    budgetItemNameInput.value = '';
    budgetItemCostInput.value = '';
    budgetItemCategoryInput.value = '';
}
addItemButton.addEventListener('click', showItemInputer);
//-----------------------Attach-Remove-Button-Listener------------------------
function attachRemoveButtonListener() {
    budgetItemsRemoveButtons = document.getElementsByClassName('remove-budget-item-button');
    Array.from(budgetItemsRemoveButtons).forEach(button => {
        button.addEventListener('click', deleteBudgetItem);
    });
}
addButton.addEventListener('click', addBudgetItemFromInput);
let lastEditedName = '';
//------------------------------Edit-Budget-Item------------------------------
function editBudgetItem() {
    console.log("Edit button clicked");
    let parent = this.parentElement.parentElement.parentElement;
    let name = parent.getElementsByClassName('budget-item-name-text')[0].innerText;
    lastEditedName = name;
    let amount = parent.getElementsByClassName('budget-item-amount-text')[0].innerText.replace('$', '');
    let category = parent.getElementsByClassName('budget-item-category-text')[0].innerText;
    let editBudgetItem = new BudgetItem(name, amount, category);
    let editBudgetItemHtml = editBudgetItem.buildEditItemHtml();
    let tempDiv = document.createElement('div');
    tempDiv.innerHTML = editBudgetItemHtml;
    parent.replaceWith(tempDiv);
    attachSaveButtonListener();
    attachEditButtonListener();
}
//------------------------Attach-Edit-Button-Listener-------------------------
function attachEditButtonListener() {
    budgetItemsEditButtons = document.getElementsByClassName('edit-budget-item-button');
    Array.from(budgetItemsEditButtons).forEach(button => {
        button.addEventListener('click', editBudgetItem);
    });
}
Array.from(budgetItemsEditButtons).forEach(button => {
    button.addEventListener('click', editBudgetItem);
});
//------------------------------Save-Budget-Item------------------------------
function saveBudgetItem() {
    console.log("Save button clicked");
    let parent = this.parentElement.parentElement;
    let name = parent.getElementsByClassName('budget-item-input')[0].value;
    let amount = parent.getElementsByClassName('budget-item-input')[1].value;
    let category = parent.getElementsByClassName('budget-item-input')[2].value;
    let saveBudgetItem = new BudgetItem(name, amount, category);
    let saveBudgetItemHtml = saveBudgetItem.buildAddItemHtml();
    let tempDiv = document.createElement('div');
    tempDiv.classList.add('budget-item');
    tempDiv.innerHTML = saveBudgetItemHtml;
    parent.replaceWith(tempDiv);
    if (name === lastEditedName) {
        editBudgetItemToAccount(name, amount, category);
    } else {
        editBudgetItemToAccountWithNewName(name, amount, category);
    }
    attachEditButtonListener();
    attachRemoveButtonListener();
}
function editBudgetItemToAccount(name, amount, category) {
    amount = amountFloatArtifactRemover(amount);
    fetch('/budget/edit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            amount: amount,
            category: category
        })
    }).then(response => {
        if (response.status === 200) {
            console.log('Item edited');
        } else {
            console.log('Error:', response.status);
        }
    }).catch(error => {
        console.error('Error:', error);
    });
    buildCharts().then(() => {
        console.log('Rebuilt Charts');
    });
}
function editBudgetItemToAccountWithNewName(name, amount, category) {
    amount = amountFloatArtifactRemover(amount);
    fetch(`/budget/edit/${name}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: lastEditedName,
            amount: amount,
            category: category
        })
    }).then(response => {
        if (response.status === 200) {
            console.log('Item edited');
        } else {
            console.log('Error:', response.status);
        }
    }).catch(error => {
        console.error('Error:', error);
    });
    buildCharts().then(() => {
        console.log('Rebuilt Charts');
    });
}
//------------------------Attach-Save-Button-Listener-------------------------
function attachSaveButtonListener() {
    budgetItemsSaveButtons = document.getElementsByClassName('save-budget-item-button');
    Array.from(budgetItemsSaveButtons).forEach(button => {
        button.addEventListener('click', saveBudgetItem);
    });
}
Array.from(budgetItemsSaveButtons).forEach(button => {
    button.addEventListener('click', saveBudgetItem);
});
//-----------------------------Delete-Budget-Item-----------------------------
function deleteBudgetItem() {
    console.log("Delete button clicked");
    if (this.classList.contains('remove-budget-item-button')) {
        let name = this.parentElement.parentElement.getElementsByClassName('budget-item-name-text')[0].innerText;
        let amount = this.parentElement.parentElement.getElementsByClassName('budget-item-amount-text')[0].innerText;
        let category = this.parentElement.parentElement.getElementsByClassName('budget-item-category-text')[0].innerText;
        removeBudgetItemFromAccount(name, amount, category);
        let parent = this.parentElement.parentElement.parentElement;
        parent.remove();
    }
}
Array.from(budgetItemsRemoveButtons).forEach(button => {
    button.addEventListener('click', deleteBudgetItem);
});
async function getBudgetFromAccount() {
    let budget = await fetch('/budget/get', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        return response.json();
    }).then(data => {
        return data.budgetItems;
    }).catch(error => {
        console.error('Error:', error);
    });
    return budget;
}
async function buildCharts() {
    let budget = await getBudgetFromAccount();
    buildPieChart(budget);
}
buildCharts().then(() => {
    console.log('Charts built');
});
let pieChart = null;
let budgetGraph = document.getElementById('budget-graph');
function buildPieChart(budget) {
    if (pieChart) {
        pieChart.destroy();
    }
    let colors = colorPalette(budget.length);
    let colorsPaletteBorders = colorPaletteBorders(colors);
    pieChart = new Chart(budgetGraph, {
        type: 'pie',
        data: {
            labels: budget.map(item => item.name),
            datasets: [{
                    label: 'Budget',
                    data: budget.map(item => item.amount),
                    backgroundColor: colors,
                    borderColor: colorPaletteBorders,
                    borderWidth: 1
                }]
        },
        options: {
            title: {
                display: true,
                text: 'Budget Items'
            }
        }
    });
}
