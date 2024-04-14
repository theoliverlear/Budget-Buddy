package junit.entity.expense;

import org.budgetbuddy.entity.category.Category;
import org.budgetbuddy.entity.expense.Expense;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {
    //============================-Variables-=================================
    Expense testExpense = new Expense("Lunch", 5, Category.FOOD);


    //----------------------------Test-Instantiation--------------------------------
    @Test
    public void testExpenseNotNull(){
        assertNotNull(testExpense);
    }

    //----------------------------Test-Methods--------------------------------

    //----------------------------Test-Getters--------------------------------
    @Test
    public void testGetName(){
        assertEquals("Lunch", this.testExpense.getName());
    }
    @Test
    public void testGetAmount(){
        assertEquals(5, this.testExpense.getAmount());
    }

    @Test
    public void testGetCategory(){
        assertEquals(Category.FOOD, this.testExpense.getCategory());
    }
    //----------------------------Test-Setters--------------------------------
    @Test
    public void testSetName(){
        this.testExpense.setName("T-Shirt");
        assertEquals("T-Shirt", this.testExpense.getName());
    }
    @Test
    public void testSetAmount(){
        this.testExpense.setAmount(20);
        assertEquals(20, this.testExpense.getAmount());
    }
    @Test
    public void testSetExpense() {
        this.testExpense.setCategory(Category.ENTERTAINMENT);
        assertEquals(Category.ENTERTAINMENT, this.testExpense.getCategory());
    }
    @Test
    public void testSetId(){
        this.testExpense.setId(100123L);
        assertEquals(100123L, this.testExpense.getId());
    }
}
