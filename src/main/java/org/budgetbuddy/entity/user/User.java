package org.budgetbuddy.entity.user;
//=================================-Imports-==================================
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.budget.BudgetConverter;
import org.budgetbuddy.convert.entity.budget.BudgetHistoryConverter;
import org.budgetbuddy.convert.entity.holding.debt.DebtConverter;
import org.budgetbuddy.convert.entity.holding.debt.DebtHistoryConverter;
import org.budgetbuddy.convert.entity.finance.FinanceHistoryConverter;
import org.budgetbuddy.convert.entity.holding.saving.SavingConverter;
import org.budgetbuddy.convert.entity.holding.saving.SavingHistoryConverter;
import org.budgetbuddy.convert.entity.purchase.PurchaseHistoryConverter;
import org.budgetbuddy.convert.entity.tax.TaxHistoryConverter;
import org.budgetbuddy.convert.entity.user.SafePasswordConverter;
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.budget.BudgetHistory;
import org.budgetbuddy.entity.holding.debt.Debt;
import org.budgetbuddy.entity.holding.debt.DebtHistory;
import org.budgetbuddy.entity.finance.Finance;
import org.budgetbuddy.entity.finance.FinanceHistory;
import org.budgetbuddy.entity.purchase.PurchaseHistory;
import org.budgetbuddy.entity.holding.saving.Saving;
import org.budgetbuddy.entity.holding.saving.SavingHistory;
import org.budgetbuddy.entity.tax.TaxHistory;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    //============================-Variables-=================================

    //---------------------------------Id-------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    //------------------------------Username----------------------------------
    @Column(name = "username")
    String username;
    //---------------------------Safe-Password--------------------------------
    @Convert(converter = SafePasswordConverter.class)
    @Column(name = "hashed_password")
    SafePassword safePassword;
    //---------------------------Current-Budget-------------------------------
    @Convert(converter = BudgetConverter.class)
    @Column(name = "current_budget")
    Budget currentBudget;
    //---------------------------Budget-History-------------------------------
    @Convert(converter = BudgetHistoryConverter.class)
    @Column(name = "budget_history")
    BudgetHistory budgetHistory;
    //---------------------------Current-Debt---------------------------------
    @Convert(converter = DebtConverter.class)
    @Column(name = "current_debt")
    Debt currentDebt;
    //---------------------------Debt-History---------------------------------
    @Convert(converter = DebtHistoryConverter.class)
    @Column(name = "debt_history")
    DebtHistory debtHistory;
    //---------------------------Current-Finance------------------------------
    @Convert(converter = FinanceConverter.class)
    @Column(name = "current_finance")
    Finance currentFinance;
    //---------------------------Finance-History------------------------------
    @Convert(converter = FinanceHistoryConverter.class)
    @Column(name = "finance_history")
    FinanceHistory financeHistory;
    //---------------------------Purchase-History-----------------------------
    @Convert(converter = PurchaseHistoryConverter.class)
    @Column(name = "purchase_history")
    PurchaseHistory purchaseHistory;
    //---------------------------Current-Savings-------------------------------
    @Convert(converter = SavingConverter.class)
    @Column(name = "current_savings")
    Saving currentSavings;
    //---------------------------Saving-History-------------------------------
    @Convert(converter = SavingHistoryConverter.class)
    @Column(name = "saving_history")
    SavingHistory savingHistory;
    //---------------------------Tax-History----------------------------------
    @Convert(converter = TaxHistoryConverter.class)
    @Column(name = "tax_history")
    TaxHistory taxHistory;
    //===========================-Constructors-===============================
    public User() {
        this.username = "Unknown User";
        this.safePassword = new SafePassword();
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
    public User(String username, SafePassword safePassword) {
        this.username = username;
        this.safePassword = safePassword;
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
                SafePassword safePassword,
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
        this.safePassword = safePassword;
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
            boolean samePassword = this.safePassword.equals(comparedUser.safePassword);
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
