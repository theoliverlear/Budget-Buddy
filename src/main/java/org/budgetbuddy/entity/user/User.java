package org.budgetbuddy.entity.user;
//=================================-Imports-==================================
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.budget.BudgetConverter;
import org.budgetbuddy.convert.entity.budget.BudgetHistoryConverter;
import org.budgetbuddy.convert.entity.user.SafePasswordConverter;
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.budget.BudgetHistory;
import org.budgetbuddy.entity.holding.debt.Debt;
import org.budgetbuddy.entity.holding.debt.DebtHistory;
import org.budgetbuddy.entity.finance.Finance;
import org.budgetbuddy.entity.finance.FinanceHistory;
import org.budgetbuddy.entity.purchase.PurchaseHistory;
import org.budgetbuddy.entity.holding.savings.Saving;
import org.budgetbuddy.entity.holding.savings.SavingHistory;
import org.budgetbuddy.entity.tax.TaxHistory;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    @Column(name = "username")
    String username;
    @Convert(converter = SafePasswordConverter.class)
    @Column(name = "hashed_password")
    SafePassword password;
    @Convert(converter = BudgetConverter.class)
    @Column(name = "current_budget")
    Budget currentBudget;
    @Convert(converter = BudgetHistoryConverter.class)
    BudgetHistory budgetHistory;
    @Transient // TODO: Create a converter for this, then remove annotation.
    Debt currentDebt;
    @Transient // TODO: Create a converter for this, then remove annotation.
    DebtHistory debtHistory;
    @Transient // TODO: Create a converter for this, then remove annotation.
    Finance currentFinance;
    @Transient // TODO: Create a converter for this, then remove annotation.
    FinanceHistory financeHistory;
    @Transient // TODO: Create a converter for this, then remove annotation.
    PurchaseHistory purchaseHistory;
    @Transient // TODO: Create a converter for this, then remove annotation.
    Saving currentSavings;
    @Transient // TODO: Create a converter for this, then remove annotation.
    SavingHistory savingHistory;
    @Transient
    TaxHistory taxHistory;
    //===========================-Constructors-===============================
    public User() {
        this.username = "Unknown User";
        this.password = new SafePassword();
        this.currentBudget = new Budget();
        this.budgetHistory = new BudgetHistory();
        this.currentDebt = new Debt();
        this.debtHistory = new DebtHistory();
        this.currentFinance = new Finance();
        this.financeHistory = new FinanceHistory();
        this.purchaseHistory = new PurchaseHistory();
        this.currentSavings = new Saving();
        this.savingHistory = new SavingHistory();
    }
    public User(String username,
                SafePassword password,
                Budget currentBudget,
                BudgetHistory budgetHistory,
                Debt currentDebt,
                DebtHistory debtHistory,
                Finance currentFinance,
                FinanceHistory financeHistory,
                PurchaseHistory purchaseHistory,
                Saving currentSavings,
                SavingHistory savingHistory,
                TaxHistory taxHistory) {
        this.username = username;
        this.password = password;
        this.currentBudget = currentBudget;
        this.budgetHistory = budgetHistory;
        this.currentDebt = currentDebt;
        this.debtHistory = debtHistory;
        this.currentFinance = currentFinance;
        this.financeHistory = financeHistory;
        this.purchaseHistory = purchaseHistory;
        this.currentSavings = currentSavings;
        this.savingHistory = savingHistory;
        this.taxHistory = taxHistory;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof User comparedUser) {
            boolean sameId = this.id.equals(comparedUser.id);
            boolean sameUsername = this.username.equals(comparedUser.username);
            boolean samePassword = this.password.equals(comparedUser.password);
            boolean sameCurrentBudget = this.currentBudget.equals(comparedUser.currentBudget);
            boolean sameBudgetHistory = this.budgetHistory.equals(comparedUser.budgetHistory);
            boolean sameCurrentDebt = this.currentDebt.equals(comparedUser.currentDebt);
            boolean sameDebtHistory = this.debtHistory.equals(comparedUser.debtHistory);
            boolean sameCurrentFinance = this.currentFinance.equals(comparedUser.currentFinance);
            boolean sameFinanceHistory = this.financeHistory.equals(comparedUser.financeHistory);
            boolean samePurchaseHistory = this.purchaseHistory.equals(comparedUser.purchaseHistory);
            boolean sameCurrentSavings = this.currentSavings.equals(comparedUser.currentSavings);
            boolean sameSavingHistory = this.savingHistory.equals(comparedUser.savingHistory);
            boolean sameTaxHistory = this.taxHistory.equals(comparedUser.taxHistory);
            return sameId && sameUsername && samePassword &&
                    sameCurrentBudget && sameBudgetHistory &&
                    sameCurrentDebt && sameDebtHistory && sameCurrentFinance
                    && sameFinanceHistory && samePurchaseHistory &&
                    sameCurrentSavings && sameSavingHistory && sameTaxHistory;
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    // Due to the extensive number of fields, getters are created with Lombok.
    //=============================-Setters-==================================
    // Due to the extensive number of fields, setters are created with Lombok.
}
