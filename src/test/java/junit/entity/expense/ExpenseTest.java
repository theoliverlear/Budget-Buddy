package junit.entity.expense;

import org.budgetbuddy.entity.expense.Expense;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {
    //============================-Variables-=================================
    Expense testExpense = new Expense("Lunch", 5);


    //----------------------------Test-Instantiation--------------------------------
    @Test
    public void testExpenseNotNull(){
        assertNotNull(testExpense);
    }

    //----------------------------Test-Methods--------------------------------

    //----------------------------Test-Getters--------------------------------
    public void testGetName(){
        assertEquals("Lunch", this.testExpense.getName());
    }
    public void testGetAmount(){
        assertEquals(5, this.testExpense.getAmount());
    }

    //----------------------------Test-Setters--------------------------------
}
