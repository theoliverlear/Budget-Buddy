package junit.model.graph;
//=================================-Imports-==================================
import org.budgetbuddy.model.graph.PieGraph;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PieGraphTest {
    //============================-Variables-=================================
    PieGraph testPieGraph = new PieGraph();
    //============================-Variables-=================================

    //---------------------Instantiate-Pie-Graph-Test-------------------------
    @Test
    public void testInstantiatePieGraph() {
        //----------------------Make-Assertions-------------------------------
        assertNotNull(this.testPieGraph, "PieGraph object should be instantiated");
    }
    //-----------------------Test-Add-Item-To-Graph---------------------------
    @Test
    public void testAddItemToGraph() {
        //---------------Setup-Values-And-Expectations------------------------
        String expectedGraphItemTitle = "Car Insurance";
        double expectedGraphItemCost = 127.00;
        this.testPieGraph.addItemToGraph(expectedGraphItemTitle, expectedGraphItemCost);
        double actualGraphItemCost = 0.0;
        String actualGraphItemTitle = null;
        //--------------------Retrieve-Actual-Data----------------------------
        outer: for (Map.Entry<HashMap<String, Double>, Double> item : this.testPieGraph.getItemsAsPercentages().entrySet()) {
            for (Map.Entry<String, Double> entry : item.getKey().entrySet()) {
                if (entry.getKey().equals(expectedGraphItemTitle)) {
                    actualGraphItemTitle = entry.getKey();
                    actualGraphItemCost = entry.getValue();
                    break outer;
                }
            }
        }
        //-----------------------Build-Messages-------------------------------
        String graphItemNullMessage = "Graph item should be found";
        String graphItemTitleMessage = "Graph item title should be correct";
        String graphItemCostMessage = "Graph item cost should be correct";
        //----------------------Make-Assertions-------------------------------
        assertNotNull(actualGraphItemTitle, graphItemNullMessage);
        assertEquals(expectedGraphItemTitle, actualGraphItemTitle, graphItemTitleMessage);
        assertEquals(expectedGraphItemCost, actualGraphItemCost, graphItemCostMessage);
    }
    //---------------------Test-Calculate-Percentages-------------------------
    @Test
    public void testCalculatePercentages() {
        //---------------Setup-Values-And-Expectations------------------------
        String insuranceTitle = "Car Insurance";
        double expectedInsurancesCost = 70.00;
        String groceriesTitle = "Groceries";
        double expectedGroceriesCost = 30.00;
        this.testPieGraph.addItemToGraph(insuranceTitle, expectedInsurancesCost);
        this.testPieGraph.addItemToGraph(groceriesTitle, expectedGroceriesCost);
        double expectedInsurancePercentage = 0.7;
        double expectedGroceriesPercentage = 0.3;
        //--------------------Retrieve-Actual-Data----------------------------
        for (Map.Entry<HashMap<String, Double>, Double> item : this.testPieGraph.getItemsAsPercentages().entrySet()) {
            for (Map.Entry<String, Double> entry : item.getKey().entrySet()) {
                if (entry.getKey().equals(insuranceTitle)) {
                    expectedInsurancePercentage = item.getValue();
                }
                if (entry.getKey().equals(groceriesTitle)) {
                    expectedGroceriesPercentage = item.getValue();
                }
            }
        }
        String insurancePercentageMessage = "Insurance percentage should be correct";
        String groceriesPercentageMessage = "Groceries percentage should be correct";
        //----------------------Make-Assertions-------------------------------
        assertEquals(expectedInsurancePercentage, 0.7, insurancePercentageMessage);
        assertEquals(expectedGroceriesPercentage, 0.3, groceriesPercentageMessage);
    }
}
