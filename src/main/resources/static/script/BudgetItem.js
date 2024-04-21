export class BudgetItem {
    //===========================-Constructors-===============================
    constructor(name, amount, category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }
    //=============================-Methods-==================================
    buildOuterHtml() {
        return `
        <div class="budget-item">
                <div class="budget-item-info">
                    <div class="budget-item-name">
                        <h4 class="budget-item-name-text">
                            ${this.name}
                        </h4>
                    </div>
                    <div class="budget-item-amount">
                        <h4 class="budget-item-amount-text">
                            $${this.amount}
                        </h4>
                    </div>
                    <div class="budget-item-category">
                        <h4 class="budget-item-category-text">
                            ${this.category}
                        </h4>
                    </div>
                </div>
                <div class="budget-item-buttons">
                    <div class="budget-item-button">
                        <h4 class="budget-item-button-text">
                            Edit
                        </h4>
                    </div>
                    <div class="budget-item-button remove-budget-item-button">
                        <h4 class="budget-item-button-text">
                            Remove
                        </h4>
                    </div>
                </div>
            </div>
        `
    }

    toString() {
        return `Name: ${this.name}, Amount: ${this.amount}, Category: ${this.category}`;
    }

    //=============================-Getters-==================================
    get getName() {
        return this.name;
    }
    get getAmount() {
        return this.amount;
    }
    get getCategory() {
        return this.category;
    }
    //=============================-Setters-==================================
    set setName(name) {
        this.name = name;
    }
    set setAmount(amount) {
        this.amount = amount;
    }
    set setCategory(category) {
        this.category = category;
    }
}