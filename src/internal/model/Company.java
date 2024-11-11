package internal.model;

public class Company {
    private int companyId;
    private String firmName;
    private String subsector;
    private double totalAsset;

    public Company(int companyId, String firmName, String subsector, double totalAsset) {
        this.companyId = companyId;
        this.firmName = firmName;
        this.subsector = subsector;
        this.totalAsset = totalAsset;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getFirmName() {
        return firmName;
    }

    public String getSubsector() {
        return subsector;
    }

    public double getTotalAsset() {
        return totalAsset;
    }

    @Override
    public String toString() {
        return "Company ID: " + companyId + ", Firm Name: " + firmName + ", Subsector: " + subsector + ", Total Asset: " + totalAsset;
    }
}
