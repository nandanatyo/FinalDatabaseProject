package internal.model;

public class IncomeAndProfit {
    private double salesRevenue;
    private double operatingProfitMargin;
    private double operatingProfitMarginRatio;
    private int year;

    public IncomeAndProfit(double salesRevenue, double operatingProfitMargin, double operatingProfitMarginRatio, int year) {
        this.salesRevenue = salesRevenue;
        this.operatingProfitMargin = operatingProfitMargin;
        this.operatingProfitMarginRatio = operatingProfitMarginRatio;
        this.year = year;
    }

    public double getSalesRevenue() { return salesRevenue; }
    public double getOperatingProfitMargin() { return operatingProfitMargin; }
    public double getOperatingProfitMarginRatio() { return operatingProfitMarginRatio; }
    public int getYear() { return year; }
}
