package org.budgetbuddy.entity.interest;
//=================================-Imports-==================================
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.budgetbuddy.entity.holding.debt.Debt;
import org.budgetbuddy.entity.holding.saving.Saving;
import org.budgetbuddy.entity.time.TimeInterval;

@Entity
@DiscriminatorValue("simple")
public class SimpleInterest extends Interest {
    //============================-Variables-=================================

    //===========================-Constructors-===============================
    public SimpleInterest() {
        super();
    }
    public SimpleInterest(double rateAsDecimal) {
        super(rateAsDecimal);
    }
    public SimpleInterest(double rateAsDecimal, TimeInterval chargeInterestInterval) {
        super(rateAsDecimal, chargeInterestInterval);
    }
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
