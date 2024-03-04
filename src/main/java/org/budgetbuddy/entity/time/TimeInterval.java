package org.budgetbuddy.entity.time;
//=================================-Imports-==================================
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TimeInterval {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    //============================-Constants-=================================
    // TODO: Add constants for the following time intervals.
    public static final TimeInterval DAILY = null;
    public static final TimeInterval WEEKLY = null;
    public static final TimeInterval BIWEEKLY = null;
    public static final TimeInterval MONTHLY = null;
    public static final TimeInterval BIMONTHLY = null;
    public static final TimeInterval QUARTERLY = null;
    public static final TimeInterval SEMIANNUALLY = null;
    public static final TimeInterval ANNUALLY = null;
    public static final TimeInterval BIENNIALLY = null;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
