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
    BudgetItem  budgetItemTest1 = new BudgetItem("Starbucks Coffee", 3.99, Category.FOOD);
    BudgetItem budgetItemTest2 = new BudgetItem("Sneakers", 59.99, Category.PERSONAL_SPENDING);
    BudgetItem budgetItemTest3 = new BudgetItem("Television", 199.99, Category.ENTERTAINMENT);

    //----------------------------Test-Methods--------------------------------
    @Test
    public void testAddBudgetItem () {
        this.budgetTest = new Budget(this.budgetListTest);
        this.budgetTest.addBudgetItem(this.budgetItemTest3);
        assertTrue(this.budgetTest.getBudgetItems().contains(this.budgetItemTest3));
    }
    @Test
    public void testAddNullItem() {
        this.budgetTest = new Budget(this.budgetListTest);
        this.budgetTest.addBudgetItem(null);
        assertEquals( 0, this.budgetTest.getBudgetItems().size());
    }

    //----------------------------Test-Getters--------------------------------
    @Test
    public void testGetBudgetItems(){
        this.budgetListTest.add(this.budgetItemTest1);
        this.budgetListTest.add(this.budgetItemTest2);
        this.budgetListTest.add(this.budgetItemTest3);
        this.budgetTest = new Budget(this.budgetListTest);
        ArrayList<BudgetItem> actualItems = this.budgetTest.getBudgetItems();
        assertEquals(this.budgetListTest, actualItems);
        assertNotNull(this.budgetTest.getBudgetItems());
    }
    @Test
    public void testGetIdNotNull(){
        this.budgetTest = new Budget();
        this.budgetTest.setId(34867893567L);
        assertNotNull(this.budgetTest.getBudgetItems());
    }



    //----------------------------Test-Setters--------------------------------
    @Test
    public void testSetBudgetItems(){
        this.budgetTest = new Budget();
        this.budgetListTest.add(this.budgetItemTest1);
        this.budgetListTest.add(this.budgetItemTest2);
        this.budgetListTest.add(new BudgetItem("Hamburger", 2, Category.FOOD));
        this.budgetTest.setBudgetItems(this.budgetListTest);
        assertSame(this.budgetListTest, this.budgetTest.getBudgetItems());
    }
    @Test
    public void testSetId(){
        this.budgetTest = new Budget();
        this.budgetTest.setId(3113113L);
        assertEquals(3113113L, this.budgetTest.getId());
    }
}
