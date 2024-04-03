package junit.entity.budget;

//=================================-Imports-==================================
import java.util.ArrayList;
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.budget.BudgetItem;
import org.budgetbuddy.entity.category.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetTest {
    //============================-Variables-=================================
    Budget budgetTest;
    ArrayList<BudgetItem> budgetListTest = new ArrayList<>();
    BudgetItem  budgetTestItem1 = new BudgetItem("Starbucks Coffee", 3.99, Category.FOOD);
    BudgetItem budgetTestItem2 = new BudgetItem("Sneakers", 59.99, Category.PERSONAL_SPENDING);
    BudgetItem budgetTestItem3 = new BudgetItem("Television", 199.99, Category.ENTERTAINMENT);

    //----------------------------Test-Methods--------------------------------
    @Test
    public void testAddBudgetItem () {
        budgetTest = new Budget(budgetListTest);
        budgetTest.addBudgetItem(budgetTestItem3);
        assertTrue(budgetTest.getBudgetItems().contains(budgetTestItem3));
    }
    @Test
    public void testAddNullItem() {
        budgetTest = new Budget(budgetListTest);
        budgetTest.addBudgetItem(null);
        assertEquals( 0, budgetTest.getBudgetItems().size());
    }

    //----------------------------Test-Getters--------------------------------
    @Test
    public void testGetBudgetItems(){
        budgetListTest.add(budgetTestItem1);
        budgetListTest.add(budgetTestItem2);
        budgetListTest.add(budgetTestItem3);
        budgetTest = new Budget(budgetListTest);
        ArrayList<BudgetItem> actualItems = budgetTest.getBudgetItems();
        assertEquals(budgetListTest, actualItems);
        assertNotNull(budgetTest.getBudgetItems());
    }
    @Test
    public void testGetIdNotNull(){
        budgetTest = new Budget();
        budgetTest.setId(34867893567L);
        assertNotNull(budgetTest.getBudgetItems());
    }



    //----------------------------Test-Setters--------------------------------
    @Test
    public void testSetBudgetItems(){
        budgetTest = new Budget();
        budgetListTest.add(budgetTestItem1);
        budgetListTest.add(budgetTestItem2);
        budgetListTest.add(new BudgetItem("Hamburger", 2, Category.FOOD));
        budgetTest.setBudgetItems(budgetListTest);
        assertSame(budgetListTest, budgetTest.getBudgetItems());
    }
    @Test
    public void testSetId(){
        budgetTest = new Budget();
        budgetTest.setId(3113113L);
        assertEquals(3113113L, budgetTest.getId());
    }
}
