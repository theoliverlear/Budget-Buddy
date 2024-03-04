package org.budgetbuddy.entity.debt;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.budgetbuddy.entity.interest.Interest;

@Entity
public class Debt {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    double debtAmount;
    @Transient
    Interest interest;
    int monthsRemaining;
    double monthlyPayment;
    //===========================-Constructors-===============================
    public Debt() {
        this.interest = null;
        this.debtAmount = 0;
    }
    public Debt(double debtAmount, Interest interest) {
        this.debtAmount = debtAmount;
        this.interest = interest;
    }
    public Debt(double debtAmount, Interest interest, int monthsRemaining) {
        this.debtAmount = debtAmount;
        this.interest = interest;
        this.monthsRemaining = monthsRemaining;
        this.monthlyPayment = this.calculateMonthlyPayment();
    }
    public Debt(double debtAmount, Interest interest, double monthlyPayment) {
        this.debtAmount = debtAmount;
        this.interest = interest;
        this.monthlyPayment = monthlyPayment;
        this.monthsRemaining = this.calculateMonthsRemaining();
    }
    public Debt(double debtAmount, Interest interest, int monthsRemaining, double monthlyPayment) {
        this.debtAmount = debtAmount;
        this.interest = interest;
        this.monthsRemaining = monthsRemaining;
        this.monthlyPayment = monthlyPayment;
    }
    //=============================-Methods-==================================

    //---------------------Calculate-Monthly-Payment--------------------------
    public double calculateMonthlyPayment() {
        // TODO: Implement calculate monthly payment method
        return 0;
    }
    //---------------------Calculate-Months-Remaining-------------------------
    public int calculateMonthsRemaining() {
        // TODO: Implement calculate months remaining method
        return 0;
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
