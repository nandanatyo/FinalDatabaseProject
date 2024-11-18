package internal.model;

public class Productivity {
    private double returnOnAsset;
    private double operationalEfficiency;
    private double salesGrowth;
    private int year;

    public Productivity(double returnOnAsset, double operationalEfficiency, double salesGrowth, int year) {
        this.returnOnAsset = returnOnAsset;
        this.operationalEfficiency = operationalEfficiency;
        this.salesGrowth = salesGrowth;
        this.year = year;
    }

    public double getReturnOnAsset() { return returnOnAsset; }
    public double getOperationalEfficiency() { return operationalEfficiency; }
    public double getSalesGrowth() { return salesGrowth; }
    public int getYear() { return year; }
}
