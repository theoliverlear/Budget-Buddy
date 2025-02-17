package org.budgetbuddy.entity.interest;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.time.TimeIntervalConverter;
import org.budgetbuddy.entity.holding.debt.Debt;
import org.budgetbuddy.entity.holding.saving.Saving;
import org.budgetbuddy.entity.time.TimeInterval;

//=================================-Imports-==================================
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "interest_type")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleInterest.class, name = "simple"),
        @JsonSubTypes.Type(value = CompoundInterest.class, name = "compound")
})
public abstract class Interest {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    double rateAsDecimal;
    @Convert(converter = TimeIntervalConverter.class)
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
    @Override
    public String toString() {
        return "%.2f%% - %s%n".formatted(this.rateAsDecimal,
                                         this.chargeInterestInterval);
    }
    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
