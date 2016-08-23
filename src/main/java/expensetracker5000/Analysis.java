import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
                String nextLine = scan.nextLine();
                System.out.println(nextLine);
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
                    System.out.println("Enter the subcategory you would like the " + statType +" of:");
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
                    String readSubcategory = delimiter[2];

                    ExpenseTraits eT = new ExpenseTraits(null, readExpense, null, null);

                    if (readType.equals("subcategory")) {
                        if (readSubcategory.equals(subcategory)) {
                            expenseTraitsList.add(eT);
                        } else {
                            //don't add
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
            Analysis a = new Analysis();
            List<ExpenseTraits> valuesToAnalyze = a.readExpenses(fileName, statType, totalOrSubcategory);
            double sum = 0;
            double convert = 0;
            List<Double> medianList = new ArrayList<>();

            for (ExpenseTraits vTA : valuesToAnalyze) {
                convert = Double.parseDouble(vTA.getExpense());
                sum += convert;
                medianList.add(convert);
            }

            if (statType.equals("sum")) {
                System.out.println("\n--- Sum of (sub)category expenses: ---\n");
                System.out.println(sum);
                System.out.println("\n--------------------------------------\n");
            } else if (statType.equals("mean")) {
                double mean = sum / valuesToAnalyze.size();
                BigDecimal twoDigits = new BigDecimal(mean).setScale(2, RoundingMode.HALF_UP);

                System.out.println("\n--- Mean of (sub)category expenses: ---\n");
                System.out.println(twoDigits);
                System.out.println("\n---------------------------------------\n");

            } else if (statType.equals("median")) {
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
            }
        } catch (NullPointerException npe) {
            System.out.println("Going back...\n");
        }
    }

    public static void main(String[] args) {
        Analysis a = new Analysis();
        a.statFromFile("2016-08 food.txt", "mean", "subcategory");
    }
}
