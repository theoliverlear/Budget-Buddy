package org.budgetbuddy.entity.holding.debt;
//=================================-Imports-==================================
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.holding.debt.DebtKeyDeserializer;
import org.budgetbuddy.convert.entity.interest.InterestConverter;
import org.budgetbuddy.entity.interest.Interest;

@Entity
@Getter
@Setter
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
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of Debt. If it is, cast it to a
        // Debt object.
        if (obj instanceof Debt debt) {
            // Check if the fields of the Debt objects are equal.
            boolean debtAmountIsSame = this.debtAmount == debt.debtAmount;
            boolean monthlyPaymentIsSame = this.monthlyPayment == debt.monthlyPayment;
            boolean monthsRemainingIsSame = this.monthsRemaining == debt.monthsRemaining;
            // If the Debt interest is not null, an equality check can be
            // performed using that field.
            if (this.interest != null) {
                boolean interestIsSame = this.interest.equals(debt.interest);
                // If the Debt id is not null, an equality check can be
                // performed using that field.
                if (this.id != null) {
                    // Return whether all fields are equal.
                    boolean idIsSame = this.id.equals(debt.id);
                    return idIsSame && debtAmountIsSame && monthlyPaymentIsSame
                            && monthsRemainingIsSame && interestIsSame;
                } else {
                    // If the Debt id is null, return whether all fields are
                    // equal except for the id field.
                    return debtAmountIsSame && monthlyPaymentIsSame &&
                            monthsRemainingIsSame && interestIsSame;
                }
                // If the Debt interest is null, return whether all fields are
                // equal except for the interest field.
            } else {
                // If the Debt id is not null, an equality check can be
                // performed using that field.
                if (this.id != null) {
                    // Return whether all fields are equal except for the
                    // interest field.
                    boolean idIsSame = this.id.equals(debt.id);
                    return idIsSame && debtAmountIsSame && monthlyPaymentIsSame
                            && monthsRemainingIsSame;
                } else {
                    // If the Debt id is null, return whether all fields are
                    // equal except for the id and interest fields.
                    return debtAmountIsSame && monthlyPaymentIsSame &&
                            monthsRemainingIsSame;
                }
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
    // TODO: Implement the toString method for the Debt class.
    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
