package org.budgetbuddy.entity.time;
//=================================-Imports-==================================
import jakarta.persistence.*;

@Entity
public class TimeInterval {
    //============================-Variables-=================================
    @Id
    double triggersPerYear;
    String intervalType;
    //============================-Constants-=================================
    @Transient
    public static final TimeInterval DAILY = new TimeInterval(365);
    @Transient
    public static final TimeInterval WEEKLY = new TimeInterval(52);
    @Transient
    public static final TimeInterval BIWEEKLY = new TimeInterval(26);
    @Transient
    public static final TimeInterval MONTHLY = new TimeInterval(12);
    @Transient
    public static final TimeInterval BIMONTHLY = new TimeInterval(6);
    @Transient
    public static final TimeInterval QUARTERLY = new TimeInterval(4);
    @Transient
    public static final TimeInterval SEMIANNUALLY = new TimeInterval(2);
    @Transient
    public static final TimeInterval ANNUALLY = new TimeInterval(1);
    @Transient
    public static final TimeInterval BIENNIALLY = new TimeInterval(0.5);
    //===========================-Constructors-===============================
    public TimeInterval() {
        this.triggersPerYear = 0;
        this.intervalType = "Custom";
    }
    public TimeInterval(double triggersPerYear) {
        this.triggersPerYear = triggersPerYear;
        this.intervalType = this.determineIntervalType();
    }
    public TimeInterval(double triggersPerYear, String intervalType) {
        this.triggersPerYear = triggersPerYear;
        this.intervalType = intervalType;
    }
    //=============================-Methods-==================================

    //----------------------Determine-Interval-Type---------------------------
    public String determineIntervalType() {
        // NOTE: Switch statements do not work with double values. A long
        //       if/else chain is easiest alternative.
        if (this.triggersPerYear == 365) {
            return "Daily";
        } else if (this.triggersPerYear == 52) {
            return "Weekly";
        } else if (this.triggersPerYear == 26) {
            return "Biweekly";
        } else if (this.triggersPerYear == 12) {
            return "Monthly";
        } else if (this.triggersPerYear == 6) {
            return "Bimonthly";
        } else if (this.triggersPerYear == 4) {
            return "Quarterly";
        } else if (this.triggersPerYear == 2) {
            return "Semiannually";
        } else if (this.triggersPerYear == 1) {
            return "Annually";
        } else if (this.triggersPerYear == 0.5) {
            return "Biennially";
        } else {
            return "Custom";
        }
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof TimeInterval timeInterval) {
            return this.triggersPerYear == timeInterval.triggersPerYear;
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        return Double.hashCode(this.triggersPerYear);
    }
    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        return "Occurs %f times per year".formatted(this.triggersPerYear);
    }
    //=============================-Getters-==================================
    public double getTriggersPerYear() {
        return this.triggersPerYear;
    }
    public String getIntervalType() {
        return this.intervalType;
    }
    //=============================-Setters-==================================
    public void setTriggersPerYear(double triggersPerYear) {
        this.triggersPerYear = triggersPerYear;
    }
    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }
}
