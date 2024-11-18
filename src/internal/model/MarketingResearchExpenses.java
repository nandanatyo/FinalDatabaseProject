package internal.model;

public class MarketingResearchExpenses {
    private double advertisingExpenses;
    private double rndExpenses;
    private int year;

    public MarketingResearchExpenses(double advertisingExpenses, double rndExpenses, int year) {
        this.advertisingExpenses = advertisingExpenses;
        this.rndExpenses = rndExpenses;
        this.year = year;
    }

    public double getAdvertisingExpenses() { return advertisingExpenses; }
    public double getRndExpenses() { return rndExpenses; }
    public int getYear() { return year; }
}
