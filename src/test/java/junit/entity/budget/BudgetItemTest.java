package junit.entity.budget;
//=================================-Imports-==================================
import org.budgetbuddy.entity.budget.BudgetItem;
import org.budgetbuddy.entity.category.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetItemTest {
    //============================-Variables-=================================
    BudgetItem testBudgetItem = new BudgetItem("Netflix", 15.99, Category.ENTERTAINMENT);
    //----------------------------Test-Getters--------------------------------
    @Test
    public void testGetName() {
        assertEquals("Netflix", this.testBudgetItem.getName());
    }
    @Test
    public void testGetAmount() {
        assertEquals(15.99, this.testBudgetItem.getAmount());
    }
    @Test
    public void testGetCategory() {
        boolean sameCategory = this.testBudgetItem.getCategory().equals(Category.ENTERTAINMENT);
        assertTrue(sameCategory);
    }
    //----------------------------Test-Setters--------------------------------
    @Test
    public void testSetId() {
        this.testBudgetItem.setId(1L);
        assertEquals(1L, this.testBudgetItem.getId());
    }
    @Test
    public void testSetName() {
        this.testBudgetItem.setName("Hulu");
        assertEquals("Hulu", this.testBudgetItem.getName());
    }
    @Test
    public void testSetAmount() {
        this.testBudgetItem.setAmount(7.99);
        assertEquals(7.99, this.testBudgetItem.getAmount());
    }
    @Test
    public void testSetCategory() {
        this.testBudgetItem.setCategory(Category.PERSONAL_SPENDING);
        boolean sameCategory = this.testBudgetItem.getCategory().equals(Category.PERSONAL_SPENDING);
        assertTrue(sameCategory);
    }
}
