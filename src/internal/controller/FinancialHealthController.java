package internal.controller;

import internal.model.FinanceDataAndAsset;

import java.util.List;

public class FinancialHealthController {
    public String determineFinancialHealth(List<FinanceDataAndAsset> financeDataList) {
        double averageTotalAssets = 0.0;
        double averageOperatingExpenseRatio = 0.0;
        double averageAdminExpenseRatio = 0.0;
        double averageFixedAssetRatio = 0.0;
        int count = financeDataList.size();

        if (count == 0) {
            return "No financial data available to assess the company's health.";
        }

        for (FinanceDataAndAsset data : financeDataList) {
            averageTotalAssets += data.getTotalAsset();
            averageOperatingExpenseRatio += data.getOperatingExpense() / data.getTotalAsset();
            averageAdminExpenseRatio += data.getGeneralAdministrativeExpense() / data.getTotalAsset();
            averageFixedAssetRatio += data.getFixedAsset() / data.getTotalAsset();
        }

        averageTotalAssets /= count;
        averageOperatingExpenseRatio /= count;
        averageAdminExpenseRatio /= count;
        averageFixedAssetRatio /= count;

        // Define thresholds for financial health
        boolean isHealthy = averageOperatingExpenseRatio < 0.3 && averageAdminExpenseRatio < 0.15 && averageFixedAssetRatio > 0.5;

        // Return assessment based on calculated values
        StringBuilder result = new StringBuilder();
        result.append("In this program, a company is considered financially healthy if the following conditions are met:\n");
        result.append("  1. Operating Expense Ratio < 30%\n");
        result.append("  2. General Administrative Expense Ratio < 15%\n");
        result.append("  3. Fixed Asset Ratio > 50%\n\n");
        result.append("Financial Health Assessment:\n");
        result.append("Average Total Assets: ").append(averageTotalAssets).append("\n");
        result.append("Average Operating Expense Ratio: ").append(String.format("%.2f%%", averageOperatingExpenseRatio * 100)).append("\n");
        result.append("Average General Administrative Expense Ratio: ").append(String.format("%.2f%%", averageAdminExpenseRatio * 100)).append("\n");
        result.append("Average Fixed Asset Ratio: ").append(String.format("%.2f%%", averageFixedAssetRatio * 100)).append("\n\n");

        if (isHealthy) {
            result.append("Conclusion: The company is financially healthy.");
        } else {
            result.append("Conclusion: The company is not financially healthy. Consider reviewing operating and administrative expenses.");
        }

        return result.toString();
    }
}
