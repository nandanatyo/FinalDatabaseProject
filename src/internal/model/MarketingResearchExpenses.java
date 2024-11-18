package internal.model;

public class MarketingResearchExpenses {
    private double advertisingExpenses;
    private double rndExpenses;

    // Constructor
    public MarketingResearchExpenses(double advertisingExpenses, double rndExpenses) {
        this.advertisingExpenses = advertisingExpenses;
        this.rndExpenses = rndExpenses;
    }

    // Getters
    public double getAdvertisingExpenses() {
        return advertisingExpenses;
    }

    public double getRndExpenses() {
        return rndExpenses;
    }
}
