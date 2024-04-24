package org.budgetbuddy.entity.holding.debt;
//=================================-Imports-==================================
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.holding.debt.DebtKeyDeserializer;
import org.budgetbuddy.convert.entity.interest.InterestConverter;
import org.budgetbuddy.entity.interest.Interest;

@Entity
@JsonDeserialize(keyUsing = DebtKeyDeserializer.class)
public class Debt {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    double debtAmount;
    @Convert(converter = InterestConverter.class)
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Debt debt) {
            boolean debtAmountIsSame = this.debtAmount == debt.debtAmount;
            boolean monthlyPaymentIsSame = this.monthlyPayment == debt.monthlyPayment;
            boolean monthsRemainingIsSame = this.monthsRemaining == debt.monthsRemaining;
            if (this.interest != null) {
                boolean interestIsSame = this.interest.equals(debt.interest);
                if (this.id != null) {
                    boolean idIsSame = this.id.equals(debt.id);
                    return idIsSame && debtAmountIsSame && monthlyPaymentIsSame
                            && monthsRemainingIsSame && interestIsSame;
                } else {
                    return debtAmountIsSame && monthlyPaymentIsSame &&
                            monthsRemainingIsSame && interestIsSame;
                }
            } else {
                if (this.id != null) {
                    boolean idIsSame = this.id.equals(debt.id);
                    return idIsSame && debtAmountIsSame && monthlyPaymentIsSame
                            && monthsRemainingIsSame;
                } else {
                    return debtAmountIsSame && monthlyPaymentIsSame &&
                            monthsRemainingIsSame;
                }
            }
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
