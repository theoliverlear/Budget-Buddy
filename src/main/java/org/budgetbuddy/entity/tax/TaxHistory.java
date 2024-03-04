package org.budgetbuddy.entity.tax;

import jakarta.persistence.*;
import org.budgetbuddy.model.format.FormattedDate;

import java.util.HashMap;

//=================================-Imports-==================================
@Entity
public class TaxHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient // TODO: Create a converter for this, then remove annotation.
    HashMap<Tax, FormattedDate> taxHistoryMap;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
