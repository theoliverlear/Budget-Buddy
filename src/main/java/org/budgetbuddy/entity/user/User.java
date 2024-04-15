package org.budgetbuddy.entity.user;
//=================================-Imports-==================================
import jakarta.persistence.*;
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
public class User {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    @Column(name = "username")
    String username;
    @Transient // TODO: Create a converter for this, then remove annotation.
    SafePassword password;
    @Transient // TODO: Create a converter for this, then remove annotation.
    Budget budget;
    @Transient // TODO: Create a converter for this, then remove annotation.
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
        this.budget = new Budget();
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
                Budget budget,
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
        this.budget = budget;
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

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    public Long getId() {
        return this.id;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
}
