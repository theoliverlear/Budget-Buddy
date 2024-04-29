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
    public PieGraph(HashMap<String, Double> itemsWithoutPercentages) {
        this.itemsAsPercentages = new HashMap<>();
        this.addAllItemsToGraph(itemsWithoutPercentages);
    }
    //=============================-Methods-==================================

    // TODO: Add a getter method where a given key matches a HashMap and
    //       returns the value of the key in the HashMap.
    //-----------------------Add-All-Items-To-Graph---------------------------
    public void addAllItemsToGraph(HashMap<String, Double> itemsWithoutPercentages) {
        // Loop through the items without percentages and add them to the
        // graph.
        for (Map.Entry<String, Double> item : itemsWithoutPercentages.entrySet()) {
            this.addItemToGraph(item.getKey(), item.getValue());
        }
        // Recalculate the percentages with the new items.
        this.calculatePercentages();
    }
    //-------------------------Add-Item-To-Graph------------------------------
    public void addItemToGraph(String item, Double cost) {
        // Create a new HashMap with the item and cost and add it to the
        // itemsAsPercentages HashMap.
        HashMap<String, Double> itemAsPercentage = new HashMap<>();
        itemAsPercentage.put(item, cost);
        this.itemsAsPercentages.put(itemAsPercentage, 0.0);
        // Recalculate the percentages with the new item.
        this.calculatePercentages();
    }
    //-----------------------Calculate-Percentages----------------------------
    public void calculatePercentages() {
        // Loop through the itemsAsPercentages and calculate the total cost.
        double totalCost = 0.0;
        for (Map.Entry<HashMap<String, Double>, Double> item : this.itemsAsPercentages.entrySet()) {
            for (Map.Entry<String, Double> entry : item.getKey().entrySet()) {
                // Add the cost of the item to the total cost.
                totalCost += entry.getValue();
            }
        }
        // Loop through the itemsAsPercentages and calculate the percentage of
        // the total cost for each item.
        for (Map.Entry<HashMap<String, Double>, Double> item : this.itemsAsPercentages.entrySet()) {
            double categoryTotal = 0.0;
            for (Map.Entry<String, Double> entry : item.getKey().entrySet()) {
                // Add the cost of the item to the category total.
                categoryTotal += entry.getValue();
            }
            // Set the value of the category to the percentage of the total
            // cost.
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
    public void setItemsAsPercentages(HashMap<HashMap<String, Double>, Double> itemsAsPercentages) {
        this.itemsAsPercentages = itemsAsPercentages;
    }
}
