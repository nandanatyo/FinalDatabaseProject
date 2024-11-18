package internal.model;

public class IncomeAndProfit {
    private double salesRevenue;
    private double operatingProfitMargin;
    private double operatingProfitMarginRatio;

    // Constructor
    public IncomeAndProfit(double salesRevenue, double operatingProfitMargin, double operatingProfitMarginRatio) {
        this.salesRevenue = salesRevenue;
        this.operatingProfitMargin = operatingProfitMargin;
        this.operatingProfitMarginRatio = operatingProfitMarginRatio;
    }

    // Getters
    public double getSalesRevenue() {
        return salesRevenue;
    }

    public double getOperatingProfitMargin() {
        return operatingProfitMargin;
    }

    public double getOperatingProfitMarginRatio() {
        return operatingProfitMarginRatio;
    }
}
