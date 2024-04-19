package org.budgetbuddy.entity.finance;
//=================================-Imports-==================================
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.expense.RecurringExpenseArrayListConverter;
import org.budgetbuddy.convert.entity.revenue.RecurringRevenueArrayListConverter;
import org.budgetbuddy.convert.entity.revenue.RecurringRevenueConverter;
import org.budgetbuddy.entity.expense.RecurringExpense;
import org.budgetbuddy.entity.revenue.RecurringRevenue;

import java.util.ArrayList;

//=================================-Imports-==================================
@Entity
@Getter
@Setter
public class Finance {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Convert(converter = RecurringRevenueConverter.class)
    RecurringRevenue income;
    @Convert(converter = RecurringExpenseArrayListConverter.class)
    ArrayList<RecurringExpense> bills;
    @Convert(converter = RecurringRevenueArrayListConverter.class)
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Finance finance) {
            boolean idIsSame = this.id.equals(finance.id);
            boolean incomeIsSame = this.income.equals(finance.income);
            boolean billsAreSame = this.bills.equals(finance.bills);
            boolean otherRevenueStreamsAreSame = this.otherRevenueStreams.equals(finance.otherRevenueStreams);
            return idIsSame && incomeIsSame && billsAreSame && otherRevenueStreamsAreSame;
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
