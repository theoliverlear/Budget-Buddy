package org.budgetbuddy.entity.interest;
//=================================-Imports-==================================
import jakarta.persistence.Entity;
import org.budgetbuddy.entity.holding.debt.Debt;
import org.budgetbuddy.entity.holding.savings.Saving;
import org.budgetbuddy.entity.time.TimeInterval;

@Entity
public class SimpleInterest extends Interest {
    //============================-Variables-=================================

    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //-------------------------Calculate-Interest-----------------------------
    @Override
    public double calculateInterest(double principal, TimeInterval timeInterval) {
        // TODO: Implement cost of simple interest. Only include interest on
        //       principal.
        return 0;
    }
    //------------------------Add-Interest-To-Debt----------------------------
    @Override
    public Debt addInterestToDebt(Debt debt) {
        // TODO: Implement adding simple interest to debt.
        return null;
    }
    //-----------------------Add-Interest-To-Saving---------------------------
    @Override
    public Saving addInterestToSaving(Saving saving) {
        // TODO: Implement adding simple interest to saving.
        return null;
    }
    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
