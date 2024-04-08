package junit.entity.budget;
//=================================-Imports-==================================
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.budget.BudgetHistory;
import org.budgetbuddy.model.format.FormattedDate;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BudgetHistoryTest {

    //============================-Variables-=================================
    BudgetHistory testBudgetHistory;
    Budget testBudget;
    HashMap<Budget, FormattedDate> testBudgetHistoryMap;

    //----------------------------Test-Instantiation--------------------------------
    @Test
    public void testBudgetHistoryNotNull(){
        this.testBudgetHistory = new BudgetHistory();
        assertNotNull(this.testBudgetHistory);

    }
}
