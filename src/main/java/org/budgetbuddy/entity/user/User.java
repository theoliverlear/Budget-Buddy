package org.budgetbuddy.entity.user;
//=================================-Imports-==================================
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.budget.BudgetConverter;
import org.budgetbuddy.convert.entity.budget.BudgetHistoryConverter;
import org.budgetbuddy.convert.entity.finance.FinanceConverter;
import org.budgetbuddy.convert.entity.holding.debt.DebtConverter;
import org.budgetbuddy.convert.entity.holding.debt.DebtHistoryConverter;
import org.budgetbuddy.convert.entity.finance.FinanceHistoryConverter;
import org.budgetbuddy.convert.entity.holding.saving.SavingConverter;
import org.budgetbuddy.convert.entity.holding.saving.SavingHistoryConverter;
import org.budgetbuddy.convert.entity.purchase.PurchaseHistoryConverter;
import org.budgetbuddy.convert.entity.revenue.RevenueConverter;
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
import org.budgetbuddy.entity.revenue.Revenue;
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
    @Lob
    Budget currentBudget;
    //---------------------------Budget-History-------------------------------
    @Convert(converter = BudgetHistoryConverter.class)
    @Column(name = "budget_history")
    @Lob
    BudgetHistory budgetHistory;
    //---------------------------Current-Revenue------------------------------
    @Convert(converter = RevenueConverter.class)
    @Column(name = "current_revenue")
    @Lob
    Revenue currentRevenue;
    //---------------------------Current-Debt---------------------------------
    @Convert(converter = DebtConverter.class)
    @Column(name = "current_debt")
    @Lob
    Debt currentDebt;
    //---------------------------Debt-History---------------------------------
    @Convert(converter = DebtHistoryConverter.class)
    @Column(name = "debt_history")
    @Lob
    DebtHistory debtHistory;
    //---------------------------Current-Finance------------------------------
    @Convert(converter = FinanceConverter.class)
    @Column(name = "current_finance")
    @Lob
    Finance currentFinance;
    //---------------------------Finance-History------------------------------
    @Convert(converter = FinanceHistoryConverter.class)
    @Column(name = "finance_history")
    @Lob
    FinanceHistory financeHistory;
    //---------------------------Purchase-History-----------------------------
    @Convert(converter = PurchaseHistoryConverter.class)
    @Column(name = "purchase_history")
    @Lob
    PurchaseHistory purchaseHistory;
    //---------------------------Current-Savings-------------------------------
    @Convert(converter = SavingConverter.class)
    @Column(name = "current_savings")
    @Lob
    Saving currentSavings;
    //---------------------------Saving-History-------------------------------
    @Convert(converter = SavingHistoryConverter.class)
    @Column(name = "saving_history")
    @Lob
    SavingHistory savingHistory;
    //---------------------------Tax-History----------------------------------
    @Convert(converter = TaxHistoryConverter.class)
    @Column(name = "tax_history")
    @Lob
    TaxHistory taxHistory;
    //===========================-Constructors-===============================
    public User() {
        this.username = "Unknown User";
        this.safePassword = new SafePassword();
        this.currentBudget = new Budget();
        this.budgetHistory = new BudgetHistory();
        this.currentRevenue = new Revenue();
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
        this.currentRevenue = new Revenue();
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
                Revenue currentRevenue,
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
        this.currentRevenue = currentRevenue;
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
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        if (obj instanceof User comparedUser) {
            // Check if the fields of the User objects are equal.
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
            if (this.id != null) {
                // If the User id is not null, an equality check can be
                // performed using that field.
                boolean sameId = this.id.equals(comparedUser.id);
                return sameId && sameUsername && samePassword &&
                        sameCurrentBudget && sameBudgetHistory &&
                        sameCurrentDebt && sameDebtHistory && sameCurrentFinance
                        && sameFinanceHistory && samePurchaseHistory &&
                        sameCurrentSavings && sameSavingHistory && sameTaxHistory;
            } else {
                // If the User id is null, return whether all fields are equal
                // except for the id field.
                return sameUsername && samePassword &&
                        sameCurrentBudget && sameBudgetHistory &&
                        sameCurrentDebt && sameDebtHistory && sameCurrentFinance
                        && sameFinanceHistory && samePurchaseHistory &&
                        sameCurrentSavings && sameSavingHistory && sameTaxHistory;
            }
        }
        // If we have reached this point, the objects are not instances of the
        // same class and are not equal, so we return false.
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        // If the User id is not null, return the hashcode of the id field.
        if (this.id != null) {
            return this.id.hashCode();
        } else {
            // If the User id is null, return the hashcode of the username
            // field -- which will also be unique.
            return this.username.hashCode();
        }
    }
    //------------------------------To-String---------------------------------
    // TODO: Implement the toString method for the User class. NOTE: Do not
    //       include password information in the toString method.
    //=============================-Getters-==================================
    // Due to the extensive number of fields, getters are created with Lombok.
    //=============================-Setters-==================================
    // Due to the extensive number of fields, setters are created with Lombok.
}
