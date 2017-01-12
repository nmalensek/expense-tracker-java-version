package expensetracker5000.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by nicholas on 8/6/16.
 */
public class Analysis {

    public void printExpenseFile(String expenseFile) {
        Scanner scan = null;
        try {
            System.out.println("");
            File fileToRead = new File(expenseFile);
            scan = new Scanner(fileToRead);

            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }

    public List<ExpenseTraits> readExpenses(String fileName, String statType, String readType) {
        List<ExpenseTraits> expenseTraitsList = null;
        Scanner sc = null;
        Scanner subCategorySelection = new Scanner(System.in);
        String subcategory = "";

        while (true) {
            try {
                File f = new File(fileName);
                sc = new Scanner(f);

                expenseTraitsList = new ArrayList<>();

                if (readType.equals("subcategory")) {
                    System.out.println("Enter the subcategory you would like the " + statType + " of:");
                    subcategory = subCategorySelection.nextLine();

                    if (subcategory.equals("q")) {
                        break;
                    } else {
                        //keep going
                    }
                } else {
                    //don't select a subcategory
                }

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] delimiter = line.split("\\t");
                    String readExpense = delimiter[1];
                    BigDecimal bDExpense = new BigDecimal(readExpense);
                    String readSubcategory = delimiter[2];

                    ExpenseTraits eT = new ExpenseTraits(null, bDExpense,
                            null, null);

                    if (readType.equals("subcategory")) {
                        if (readSubcategory.equals(subcategory)) {
                            expenseTraitsList.add(eT);
                        }
                    } else {
                        expenseTraitsList.add(eT);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                sc.close();
            }
            if (readType.equals("subcategory")) {
                if (expenseTraitsList.size() == 0) {
                    System.out.println("--No expenses found for that category, please re-enter.--\n");
                } else {
                    return expenseTraitsList;
                }
            } else {
                return expenseTraitsList;
            }
        }
        return null;
    }

    public void statFromFile(String fileName, String statType, String totalOrSubcategory) {
        try {
            List<ExpenseTraits> valuesToAnalyze = readExpenses(fileName, statType, totalOrSubcategory);
            List<Double> medianList = new ArrayList<>();

            if (statType.equals("sum")) {
                printStatistic("Sum", sumFromFile(valuesToAnalyze));
            } else if (statType.equals("mean")) {
                printStatistic("Mean", meanFromFile(valuesToAnalyze));
            } else if (statType.equals("median")) {
                try {
                    //TODO: fix median calculation, currently reads from empty list
                    Collections.sort(medianList);
                    double median;
                    if (medianList.size() % 2 == 0) {
                        median = ((medianList.get(medianList.size() / 2 - 1) +
                                medianList.get(medianList.size() / 2)) / 2);
                    } else {
                        median = (medianList.get((medianList.size() - 1) / 2));
                    }
                    System.out.println("\n--- Median of (sub)category expenses: ---\n");
                    System.out.println(median);
                    System.out.println("\n-----------------------------------------\n");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No expenses available to calculate median, please add an expense\n");
                }
            }
        } catch (NullPointerException npe) {
            System.out.println("Going back...\n");
        }
    }

    private BigDecimal sumFromFile(List<ExpenseTraits> expenseList) {
        BigDecimal sum = new BigDecimal("0.00");
        for (ExpenseTraits eT : expenseList) { sum = sum.add(eT.getExpense()); }
        return sum;
    }

    private BigDecimal meanFromFile(List<ExpenseTraits> expenseList) {
        BigDecimal mean = new BigDecimal("0.00");
        try {
            BigDecimal numberOfExpenses = new BigDecimal(expenseList.size());
            mean = sumFromFile(expenseList).divide(numberOfExpenses);
        } catch (NumberFormatException nfe) {
            System.out.println("No expenses available to calculate the mean, please add an expense\n");
        }
        return mean.setScale(2, RoundingMode.HALF_UP);
    }

    private void printStatistic(String statType, BigDecimal result) {
        System.out.println("\n--- Sum of (sub)category expenses: ---\n");
        System.out.println(result);
        System.out.println("\n--------------------------------------\n");
    }

    public static void main(String[] args) {
        Analysis a = new Analysis();
        a.statFromFile("2016-08 food.txt", "mean", "subcategory");
    }
}