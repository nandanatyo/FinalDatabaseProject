package internal.model;

public class FinanceDataAndAsset {
    private double totalAsset;
    private double fixedAsset;
    private double costOfGoodsSold;
    private double operatingExpense;
    private double generalAdministrativeExpense;
    private int year;

    public FinanceDataAndAsset(double totalAsset, double fixedAsset, double costOfGoodsSold,
                               double operatingExpense, double generalAdministrativeExpense, int year) {
        this.totalAsset = totalAsset;
        this.fixedAsset = fixedAsset;
        this.costOfGoodsSold = costOfGoodsSold;
        this.operatingExpense = operatingExpense;
        this.generalAdministrativeExpense = generalAdministrativeExpense;
        this.year = year;
    }

    public double getTotalAsset() { return totalAsset; }
    public double getFixedAsset() { return fixedAsset; }
    public double getCostOfGoodsSold() { return costOfGoodsSold; }
    public double getOperatingExpense() { return operatingExpense; }
    public double getGeneralAdministrativeExpense() { return generalAdministrativeExpense; }
    public int getYear() { return year; }
}
