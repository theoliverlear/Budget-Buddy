package org.budgetbuddy.entity.savings;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.budgetbuddy.entity.interest.Interest;

import java.util.Optional;

@Entity
public class Saving {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    double amount;
    @Transient // TODO: Create a converter for this, then remove annotation.
    Optional<Interest> interest;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
