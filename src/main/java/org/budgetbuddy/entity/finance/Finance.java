package org.budgetbuddy.entity.finance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import org.budgetbuddy.entity.expense.RecurringExpense;
import org.budgetbuddy.entity.revenue.RecurringRevenue;

import java.util.ArrayList;

//=================================-Imports-==================================
@Entity
public class Finance {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;
    @Transient // TODO: Create a converter for this, then remove annotation.
    RecurringRevenue income;
    @Transient // TODO: Create a converter for this, then remove annotation.
    ArrayList<RecurringExpense> bills;
    @Transient // TODO: Create a converter for this, then remove annotation.
    ArrayList<RecurringRevenue> otherRevenueStreams;
    //===========================-Constructors-===============================
    public Finance() {
        this.income = new RecurringRevenue();
        this.bills = new ArrayList<>();
        this.otherRevenueStreams = new ArrayList<>();
    }
    public Finance(RecurringRevenue income, ArrayList<RecurringExpense> bills) {
        this.income = income;
        this.bills = bills;
        this.otherRevenueStreams = new ArrayList<>();
    }
    public Finance(RecurringRevenue income, ArrayList<RecurringExpense> bills, ArrayList<RecurringRevenue> otherRevenueStreams) {
        this.income = income;
        this.bills = bills;
        this.otherRevenueStreams = otherRevenueStreams;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
