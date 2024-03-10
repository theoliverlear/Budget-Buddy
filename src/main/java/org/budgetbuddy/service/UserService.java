package org.budgetbuddy.service;
//=================================-Imports-==================================
import org.budgetbuddy.entity.finance.FinanceHistory;
import org.budgetbuddy.repository.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //============================-Variables-=================================
    UserRepository userRepository;
    BudgetHistoryRepository budgetHistoryRepository;
    BudgetRepository budgetRepository;
    DebtHistoryRepository debtHistoryRepository;
    DebtRepository debtRepository;
    ExpenseHistoryRepository expenseHistoryRepository;
    ExpenseRepository expenseRepository;
    FinanceHistoryRepository financeHistoryRepository;
    FinanceRepository financeRepository;
    PurchaseHistoryRepository purchaseHistoryRepository;
    SavingHistoryRepository savingHistoryRepository;
    SavingRepository savingRepository;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    public UserRepository getUserRepository() {
        return this.userRepository;
    }
    public BudgetHistoryRepository getBudgetHistoryRepository() {
        return this.budgetHistoryRepository;
    }
    public BudgetRepository getBudgetRepository() {
        return this.budgetRepository;
    }
    public DebtHistoryRepository getDebtHistoryRepository() {
        return this.debtHistoryRepository;
    }
    public DebtRepository getDebtRepository() {
        return this.debtRepository;
    }
    public ExpenseHistoryRepository getExpenseHistoryRepository() {
        return this.expenseHistoryRepository;
    }
    public ExpenseRepository getExpenseRepository() {
        return this.expenseRepository;
    }
    public FinanceHistoryRepository getFinanceHistoryRepository() {
        return this.financeHistoryRepository;
    }
    public FinanceRepository getFinanceRepository() {
        return this.financeRepository;
    }
    public PurchaseHistoryRepository getPurchaseHistoryRepository() {
        return this.purchaseHistoryRepository;
    }
    public SavingHistoryRepository getSavingHistoryRepository() {
        return this.savingHistoryRepository;
    }
    public SavingRepository getSavingRepository() {
        return this.savingRepository;
    }
    //=============================-Setters-==================================
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void setBudgetHistoryRepository(BudgetHistoryRepository budgetHistoryRepository) {
        this.budgetHistoryRepository = budgetHistoryRepository;
    }
    public void setBudgetRepository(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }
    public void setDebtHistoryRepository(DebtHistoryRepository debtHistoryRepository) {
        this.debtHistoryRepository = debtHistoryRepository;
    }
    public void setDebtRepository(DebtRepository debtRepository) {
        this.debtRepository = debtRepository;
    }
    public void setExpenseHistoryRepository(ExpenseHistoryRepository expenseHistoryRepository) {
        this.expenseHistoryRepository = expenseHistoryRepository;
    }
    public void setExpenseRepository(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public void setFinanceHistoryRepository(FinanceHistoryRepository financeHistoryRepository) {
        this.financeHistoryRepository = financeHistoryRepository;
    }
    public void setFinanceRepository(FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }
    public void setPurchaseHistoryRepository(PurchaseHistoryRepository purchaseHistoryRepository) {
        this.purchaseHistoryRepository = purchaseHistoryRepository;
    }
    public void setSavingHistoryRepository(SavingHistoryRepository savingHistoryRepository) {
        this.savingHistoryRepository = savingHistoryRepository;
    }
    public void setSavingRepository(SavingRepository savingRepository) {
        this.savingRepository = savingRepository;
    }
}
