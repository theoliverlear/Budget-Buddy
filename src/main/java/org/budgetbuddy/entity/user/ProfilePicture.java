package org.budgetbuddy.entity.user;
//=================================-Imports-==================================
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProfilePicture {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    // TODO: Create a converter for images and store them in the database.
    //       This way people can use URLs or upload their own images.
    String url;
    //===========================-Constructors-===============================
    public ProfilePicture() {
        this.url = "Unspecified URL";
    }
    public ProfilePicture(String url) {
        this.url = url;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
