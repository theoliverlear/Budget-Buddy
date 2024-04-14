package junit.entity.expense;

import org.budgetbuddy.entity.category.Category;
import org.budgetbuddy.entity.expense.Expense;
import org.budgetbuddy.entity.expense.Expenses;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {
    //============================-Variables-=================================
    Expenses testExpenses = new Expenses();
    Expense testExpense = new Expense("Lunch", 5, Category.FOOD);


    //----------------------------Test-Instantiation--------------------------------
    @Test
    public void testExpenseNotNull(){
        assertNotNull(testExpense);
    }

    @Test
    public void testExpensesNotNull(){
        assertNotNull(this.testExpenses);
        assertNotNull(this.testExpenses.getExpenses());
    }

    //----------------------------Test-Methods--------------------------------
    @Test
    public void testAddExpense(){
        this.testExpenses.addExpense(this.testExpense);
        assertTrue(this.testExpenses.getExpenses().contains(this.testExpense));
    }
    @Test
    public void testRemoveExpense(){
        this.testExpenses.addExpense(this.testExpense);
        this.testExpenses.removeExpense(this.testExpense);
        assertEquals(0, this.testExpenses.getExpenses().size());
    }

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
