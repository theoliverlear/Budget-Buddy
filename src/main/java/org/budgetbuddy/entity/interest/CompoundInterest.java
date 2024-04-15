package org.budgetbuddy.entity.interest;
//=================================-Imports-==================================
import jakarta.persistence.Entity;
import org.budgetbuddy.entity.holding.debt.Debt;
import org.budgetbuddy.entity.holding.savings.Saving;
import org.budgetbuddy.entity.time.TimeInterval;

@Entity
public class CompoundInterest extends Interest {
    //============================-Variables-=================================

    //===========================-Constructors-===============================
    public CompoundInterest() {
        super();
    }
    public CompoundInterest(double rateAsDecimal) {
        super(rateAsDecimal);
    }
    public CompoundInterest(double rateAsDecimal, TimeInterval chargeInterestInterval) {
        super(rateAsDecimal, chargeInterestInterval);
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //-------------------------Calculate-Interest-----------------------------
    @Override
    public double calculateInterest(double principal, TimeInterval timeInterval) {
        // TODO: Implement cost of compound interest. Include interest on
        //       interest on top of interest on principal.
        return 0;
    }
    //------------------------Add-Interest-To-Debt----------------------------
    @Override
    public Debt addInterestToDebt(Debt debt) {
        // TODO: Implement adding compound interest to debt.
        return null;
    }
    //-----------------------Add-Interest-To-Saving---------------------------
    @Override
    public Saving addInterestToSaving(Saving saving) {
        // TODO: Implement adding compound interest to saving.
        return null;
    }
    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
