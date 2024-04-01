package org.budgetbuddy.model.graph;

//=================================-Imports-==================================
public class Graph<X, Y> {
    //============================-Variables-=================================
    X xAxis;
    Y yAxis;
    //===========================-Constructors-===============================
    public Graph() {

    }
    public Graph(X xAxis, Y yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    public X getXAxis() {
        return xAxis;
    }
    public Y getYAxis() {
        return yAxis;
    }
    //=============================-Setters-==================================
    public void setXAxis(X xAxis) {
        this.xAxis = xAxis;
    }
    public void setYAxis(Y yAxis) {
        this.yAxis = yAxis;
    }
}
