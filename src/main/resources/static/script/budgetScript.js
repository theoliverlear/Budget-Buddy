import { BudgetItem } from './BudgetItem.js';

let budgetItemNameInput = document.getElementById('item-name-input');
let budgetItemCostInput = document.getElementById('item-cost-input');
let budgetItemCategoryInput = document.getElementById('item-category-input');
let addButton = document.getElementById('add-button');
let budgetItems = document.getElementsByClassName('budget-item');
let budgetItemsContainer = document.getElementById('budget-items-container');
let budgetItemsRemoveButtons = document.getElementsByClassName('remove-budget-item-button');
let addItemButton = document.getElementById('add-item-button');
let itemInputerDiv = document.getElementById('item-inputer');

function addBudgetItem() {
    console.log(`
    Name: ${budgetItemNameInput.value}
    Cost: ${budgetItemCostInput.value}
    Category: ${budgetItemCategoryInput.value}
    `)
    let budgetInputName = budgetItemNameInput.value;
    let budgetInputCost = budgetItemCostInput.value;
    let budgetInputCategory = budgetItemCategoryInput.value;
    let budgetItem = new BudgetItem(budgetInputName, budgetInputCost, budgetInputCategory);
    console.log(budgetItem.toString());
    let budgetItemHtml = budgetItem.buildOuterHtml();
    console.log(budgetItemHtml);
    let tempDiv = document.createElement('div');
    tempDiv.classList.add('budget-item');
    tempDiv.innerHTML = budgetItemHtml;
    budgetItemsContainer.appendChild(tempDiv);
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
addButton.addEventListener('click', addBudgetItem);

function deleteBudgetItem() {
    console.log("Delete button clicked");
    if (this.classList.contains('remove-budget-item-button')) {
        let parent = this.parentElement.parentElement.parentElement;
        parent.remove();
    }
}
Array.from(budgetItemsRemoveButtons).forEach(button => {
    button.addEventListener('click', deleteBudgetItem);
});