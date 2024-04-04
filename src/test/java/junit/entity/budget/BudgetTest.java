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
    Budget testBudget;
    ArrayList<BudgetItem> testBudgetList = new ArrayList<>();
    BudgetItem  testBudgetItem1 = new BudgetItem("Starbucks Coffee", 3.99, Category.FOOD);
    BudgetItem testBudgetItem2 = new BudgetItem("Sneakers", 59.99, Category.PERSONAL_SPENDING);
    BudgetItem testBudgetItem3 = new BudgetItem("Television", 199.99, Category.ENTERTAINMENT);

    //----------------------------Test-Methods--------------------------------
    @Test
    public void testAddBudgetItem () {
        this.testBudget = new Budget(this.testBudgetList);
        this.testBudget.addBudgetItem(this.testBudgetItem3);
        assertTrue(this.testBudget.getBudgetItems().contains(this.testBudgetItem3));
    }
    @Test
    public void testBudgetListSize() {
        this.testBudget = new Budget(this.testBudgetList);
        for (int i = 0; i < 10000; i++) {
            this.testBudgetList.add(new BudgetItem("TestObject" + i, i, Category.ENTERTAINMENT));
        }
        assertEquals( 10000, this.testBudget.getBudgetItems().size());
    }

    //----------------------------Test-Getters--------------------------------
    @Test
    public void testGetIdNotNull(){
        this.testBudget = new Budget();
        this.testBudget.setId(34867893567L);
        assertNotNull(this.testBudget.getBudgetItems());
    }
    @Test
    public void testBudgetNotNull(){
        this.testBudget = new Budget();
        assertNotNull(this.testBudget);
    }

    //----------------------------Test-Getters--------------------------------
    @Test
    public void testGetBudgetItems(){
        this.testBudgetList.add(this.testBudgetItem1);
        this.testBudgetList.add(this.testBudgetItem2);
        this.testBudgetList.add(this.testBudgetItem3);
        this.testBudget = new Budget(this.testBudgetList);
        ArrayList<BudgetItem> actualItems = this.testBudget.getBudgetItems();
        assertEquals(this.testBudgetList, actualItems);
        assertNotNull(this.testBudget.getBudgetItems());
    }




    //----------------------------Test-Setters--------------------------------
    @Test
    public void testSetBudgetItems(){
        this.testBudget = new Budget();
        this.testBudgetList.add(this.testBudgetItem1);
        this.testBudgetList.add(this.testBudgetItem2);
        this.testBudgetList.add(new BudgetItem("Hamburger", 2, Category.FOOD));
        this.testBudget.setBudgetItems(this.testBudgetList);
        assertSame(this.testBudgetList, this.testBudget.getBudgetItems());
    }
    @Test
    public void testSetId(){
        this.testBudget = new Budget();
        this.testBudget.setId(3113113L);
        assertEquals(3113113L, this.testBudget.getId());
    }
}
