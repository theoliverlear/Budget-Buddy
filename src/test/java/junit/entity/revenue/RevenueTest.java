package junit.entity.revenue;

import org.budgetbuddy.entity.category.Category;
import org.budgetbuddy.entity.revenue.Revenue;
import org.budgetbuddy.entity.revenue.Revenues;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RevenueTest {
    //============================-Variables-=================================
    Revenues testRevenues = new Revenues();
    Revenue testRevenue = new Revenue("Paycheck", 2000);


    //----------------------------Test-Instantiation--------------------------------
    @Test
    public void testRevenuesNotNull(){
        assertNotNull(this.testRevenues);
    }
    @Test
    public void testRevenueNotNull(){
        assertNotNull(this.testRevenue);
    }

    //----------------------------Test-Methods--------------------------------

    //----------------------------Test-Getters--------------------------------
    @Test
    public void testGetName(){
        assertEquals("Paycheck", this.testRevenue.getName());
    }
    @Test
    public void testGetAmount(){
        assertEquals(2000, this.testRevenue.getAmount());
    }

    //----------------------------Test-Setters--------------------------------
    @Test
    public void testSetName(){
        this.testRevenue.setName("Stock Dividend");
        assertEquals("Stock Dividend", this.testRevenue.getName());
    }
    @Test
    public void testSetAmount(){
        this.testRevenue.setAmount(10);
        assertEquals(10, this.testRevenue.getAmount());
    }
    @Test
    public void testSetId(){
        this.testRevenue.setId(100123L);
        assertEquals(100123L, this.testRevenue.getId());
    }


}
