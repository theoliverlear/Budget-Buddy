package org.budgetbuddy.entity.finance;
//=================================-Imports-==================================
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.expense.RecurringExpenseArrayListConverter;
import org.budgetbuddy.convert.entity.finance.FinanceKeyDeserializer;
import org.budgetbuddy.convert.entity.revenue.RecurringRevenueArrayListConverter;
import org.budgetbuddy.convert.entity.revenue.RecurringRevenueConverter;
import org.budgetbuddy.entity.expense.RecurringExpense;
import org.budgetbuddy.entity.revenue.RecurringRevenue;

import java.util.ArrayList;

//=================================-Imports-==================================
@Entity
@Getter
@Setter
@JsonDeserialize(keyUsing = FinanceKeyDeserializer.class)
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
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of Finance. If it is, cast it
        // to a Finance object.
        if (obj instanceof Finance finance) {
            // Check if the fields of the Finance objects are equal.
            boolean incomeIsSame = this.income.equals(finance.income);
            boolean billsAreSame = this.bills.equals(finance.bills);
            boolean otherRevenueStreamsAreSame = this.otherRevenueStreams.equals(finance.otherRevenueStreams);
            // If the Finance id is not null, an equality check can be
            // performed using that field.
            if (this.id != null) {
                // Return whether all fields are equal.
                boolean idIsSame = this.id.equals(finance.id);
                return idIsSame && incomeIsSame && billsAreSame && otherRevenueStreamsAreSame;
            } else {
                // If the Finance id is null, return whether all fields are
                // equal except for the id field.
                return incomeIsSame && billsAreSame && otherRevenueStreamsAreSame;
            }
        }
        // If we have reached this point, the objects are not instances of the
        // same class and are not equal, so we return false.
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        // Return the hashcode of the id field.
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------
    // TODO: Implement the toString method for the Finance class.
    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
