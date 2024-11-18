package internal.model;

public class Productivity {
    private double returnOnAsset;
    private double operationalEfficiency;
    private double salesGrowth;

    // Constructor
    public Productivity(double returnOnAsset, double operationalEfficiency, double salesGrowth) {
        this.returnOnAsset = returnOnAsset;
        this.operationalEfficiency = operationalEfficiency;
        this.salesGrowth = salesGrowth;
    }

    // Getters
    public double getReturnOnAsset() {
        return returnOnAsset;
    }

    public double getOperationalEfficiency() {
        return operationalEfficiency;
    }

    public double getSalesGrowth() {
        return salesGrowth;
    }
}
