package expensetracker5000.analysis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

/**
 * Created by nicholas on 1/11/17.
 */
public class CalculateStatistics {
    private BigDecimal two = new BigDecimal("2.00");
    FileReader fileReader = new FileReader();

    public void statFromFile(String fileName, String statType, String totalOrSubcategory) {
        try {
            List<BigDecimal> valuesToAnalyze = fileReader.readExpenses(
                    fileName, statType, totalOrSubcategory);

            if (statType.equals("sum")) {
                printStatistic("Sum", sumFromList(valuesToAnalyze));
            } else if (statType.equals("mean")) {
                printStatistic("Mean", meanFromList(valuesToAnalyze));
            } else if (statType.equals("median")) {
                Collections.sort(valuesToAnalyze);
                printStatistic("Median", medianFromList(valuesToAnalyze));
            }
        } catch (NullPointerException npe) {
            System.out.println("Going back...\n");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("No expenses to calculate, please add an expense\n");
        }
    }

    private BigDecimal sumFromList(List<BigDecimal> expenseList) {
        BigDecimal sum = new BigDecimal("0.00");
        for (BigDecimal bD : expenseList) {
            sum = sum.add(bD);
        }
        return sum;
    }

    private BigDecimal meanFromList(List<BigDecimal> expenseList) {
        BigDecimal numberOfExpenses = new BigDecimal(expenseList.size());
        BigDecimal mean = sumFromList(expenseList).divide(numberOfExpenses);

        return mean.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal medianFromList(List<BigDecimal> medianList) {
        BigDecimal median;
        if (medianList.size() % 2 == 0) {
            median = ((medianList.get(medianList.size() / 2 - 1).add(
                    medianList.get(medianList.size() / 2)).divide(two)));
        } else {
            median = (medianList.get((medianList.size() - 1) / 2));
        }
        return median;
    }

    private void printStatistic(String statType, BigDecimal result) {
        System.out.println("\n--- " + statType + " of (sub)category expenses: ---\n");
        System.out.println(result);
        System.out.println("\n--------------------------------------\n");
    }

    public static void main(String[] args) {
        CalculateStatistics cs = new CalculateStatistics();
        cs.statFromFile("./.expenses/2017-01 food.txt", "median",
                "total");
    }
}
