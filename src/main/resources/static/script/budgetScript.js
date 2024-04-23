import { BudgetItem } from './BudgetItem.js';

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

function getBudgetItems() {
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
getBudgetItems();
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
}
function removeBudgetItemFromAccount(name, amount, category) {
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
    }).then(response => {}).catch(error => {
        console.error('Error:', error);
    });
}
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

function hideItemInputer() {
    itemInputerDiv.style.display = 'none';
}
function showItemInputer() {
    itemInputerDiv.style.display = 'flex';
}
function clearItemInputs() {
    budgetItemNameInput.value = '';
    budgetItemCostInput.value = '';
    budgetItemCategoryInput.value = '';
}
addItemButton.addEventListener('click', showItemInputer);
function attachRemoveButtonListener() {
    budgetItemsRemoveButtons = document.getElementsByClassName('remove-budget-item-button');
    Array.from(budgetItemsRemoveButtons).forEach(button => {
        button.addEventListener('click', deleteBudgetItem);
    });
}
addButton.addEventListener('click', addBudgetItemFromInput);

function editBudgetItem() {
    console.log("Edit button clicked");
    let parent = this.parentElement.parentElement.parentElement;
    let name = parent.getElementsByClassName('budget-item-name-text')[0].innerText;
    let amount = parent.getElementsByClassName('budget-item-amount-text')[0].innerText;
    let category = parent.getElementsByClassName('budget-item-category-text')[0].innerText;
    let editBudgetItem = new BudgetItem(name, amount, category);
    let editBudgetItemHtml = editBudgetItem.buildEditItemHtml();
    let tempDiv = document.createElement('div');
    tempDiv.innerHTML = editBudgetItemHtml;
    parent.replaceWith(tempDiv);
    attachSaveButtonListener();
    attachEditButtonListener();
}
function attachEditButtonListener() {
    budgetItemsEditButtons = document.getElementsByClassName('edit-budget-item-button');
    Array.from(budgetItemsEditButtons).forEach(button => {
        button.addEventListener('click', editBudgetItem);
    });
}
Array.from(budgetItemsEditButtons).forEach(button => {
    button.addEventListener('click', editBudgetItem);
});
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
    addBudgetItemToAccount(name, amount, category);
    attachEditButtonListener();
    attachRemoveButtonListener();
}
function attachSaveButtonListener() {
    budgetItemsSaveButtons = document.getElementsByClassName('save-budget-item-button');
    Array.from(budgetItemsSaveButtons).forEach(button => {
        button.addEventListener('click', saveBudgetItem);
    });
}
Array.from(budgetItemsSaveButtons).forEach(button => {
    button.addEventListener('click', saveBudgetItem);
});
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