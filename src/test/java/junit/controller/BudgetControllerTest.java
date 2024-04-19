package junit.controller;

import org.budgetbuddy.controller.BudgetController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BudgetControllerTest {
    //============================-Variables-=================================
    BudgetController testBudgetController = new BudgetController();

    //----------------------------Test-Instantiation--------------------------------
    @Test
    public void testBudgetControllerNotNull(){
        assertNotNull(testBudgetController);
    }
}
