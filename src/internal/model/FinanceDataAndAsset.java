package internal.model;

public class FinanceDataAndAsset {
    private double totalAsset;
    private double fixedAsset;
    private double costOfGoodsSold;
    private double operatingExpense;
    private double generalAdministrativeExpense;

    // Constructor
    public FinanceDataAndAsset(double totalAsset, double fixedAsset, double costOfGoodsSold,
                               double operatingExpense, double generalAdministrativeExpense) {
        this.totalAsset = totalAsset;
        this.fixedAsset = fixedAsset;
        this.costOfGoodsSold = costOfGoodsSold;
        this.operatingExpense = operatingExpense;
        this.generalAdministrativeExpense = generalAdministrativeExpense;
    }

    // Getters
    public double getTotalAsset() {
        return totalAsset;
    }

    public double getFixedAsset() {
        return fixedAsset;
    }

    public double getCostOfGoodsSold() {
        return costOfGoodsSold;
    }

    public double getOperatingExpense() {
        return operatingExpense;
    }

    public double getGeneralAdministrativeExpense() {
        return generalAdministrativeExpense;
    }
}
