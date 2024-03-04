package org.budgetbuddy.entity.interest;

import jakarta.persistence.*;
import org.budgetbuddy.entity.debt.Debt;
import org.budgetbuddy.entity.savings.Saving;
import org.budgetbuddy.entity.time.TimeInterval;

//=================================-Imports-==================================
@Entity
public abstract class Interest {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    double rateAsDecimal;
    @Transient // TODO: Create a converter for this, then remove annotation.
    TimeInterval chargeInterestInterval;
    //===========================-Constructors-===============================
    public Interest() {
        this.rateAsDecimal = 0;
    }
    public Interest(double rateAsDecimal) {
        this.rateAsDecimal = rateAsDecimal;
    }
    public Interest(double rateAsDecimal, TimeInterval chargeInterestInterval) {
        this.rateAsDecimal = rateAsDecimal;
        this.chargeInterestInterval = chargeInterestInterval;
    }
    //=============================-Methods-==================================

    //-------------------------Calculate-Interest-----------------------------
    public abstract double calculateInterest(double principal, TimeInterval timeInterval);
    //------------------------Add-Interest-To-Debt----------------------------
    public abstract Debt addInterestToDebt(Debt debt);
    //-----------------------Add-Interest-To-Saving---------------------------
    public abstract Saving addInterestToSaving(Saving saving);

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
