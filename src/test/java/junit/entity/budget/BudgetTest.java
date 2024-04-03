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
    Budget budgetTest = new Budget();
    ArrayList<BudgetItem> budgetListTest = new ArrayList<>();
    BudgetItem  budgetTestItem1 = new BudgetItem("Starbucks Coffee", 3.99, Category.FOOD);
    BudgetItem budgetTestItem2 = new BudgetItem("Sneakers", 59.99, Category.PERSONAL_SPENDING);
    BudgetItem budgetTestItem3 = new BudgetItem("Television", 199.99, Category.ENTERTAINMENT);

    //----------------------------Test-Setters--------------------------------
    @Test
    public void testSetBudgetItems(){
        budgetListTest.add(budgetTestItem1);
        budgetListTest.add(budgetTestItem2);
        budgetListTest.add(budgetTestItem3);
        budgetTest.setBudgetItems(budgetListTest);
        assertSame(budgetListTest, budgetTest.getBudgetItems());
    }
}
