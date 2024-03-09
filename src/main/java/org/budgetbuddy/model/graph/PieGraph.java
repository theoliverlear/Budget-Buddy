package org.budgetbuddy.model.graph;
//=================================-Imports-==================================
import java.util.HashMap;
import java.util.Map;

public class PieGraph extends Graph<String, Double>{
    //============================-Variables-=================================
    HashMap<HashMap<String, Double>, Double> itemsAsPercentages;
    //===========================-Constructors-===============================
    public PieGraph() {
        this.itemsAsPercentages = new HashMap<>();
    }
    public PieGraph(HashMap<HashMap<String, Double>, Double> itemsAsPercentages) {
        this.itemsAsPercentages = itemsAsPercentages;
    }
    //=============================-Methods-==================================

    //-------------------------Add-Item-To-Graph------------------------------
    public void addItemToGraph(String item, Double cost) {
        HashMap<String, Double> itemAsPercentage = new HashMap<>();
        itemAsPercentage.put(item, cost);
        this.itemsAsPercentages.put(itemAsPercentage, 0.0);
        this.calculatePercentages();
    }
    //-----------------------Calculate-Percentages----------------------------
    public void calculatePercentages() {
        double totalCost = 0.0;
        for (Map.Entry<HashMap<String, Double>, Double> item : this.itemsAsPercentages.entrySet()) {
            for (Map.Entry<String, Double> entry : item.getKey().entrySet()) {
                totalCost += entry.getValue();
            }
        }
        for (Map.Entry<HashMap<String, Double>, Double> item : this.itemsAsPercentages.entrySet()) {
            double categoryTotal = 0.0;
            for (Map.Entry<String, Double> entry : item.getKey().entrySet()) {
                categoryTotal += entry.getValue();
            }
            item.setValue(categoryTotal / totalCost);
        }
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    public HashMap<HashMap<String, Double>, Double> getItemsAsPercentages() {
        return this.itemsAsPercentages;
    }
    //=============================-Setters-==================================
}
