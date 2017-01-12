package expensetracker5000.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import static expensetracker5000.menus.TextInput.userLineInput;

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

    public List<BigDecimal> readExpenses(String fileName, String statType, String readType) {
        List<BigDecimal> expenseList = null;
        Scanner sc = null;
        String subcategory = "";

        while (true) {
            try {
                File f = new File(fileName);
                sc = new Scanner(f);

                expenseList = new ArrayList<>();

                if (readType.equals("subcategory")) {
                    System.out.println("Enter the subcategory you would like the " + statType + " of:");
                    subcategory = userLineInput();

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

                    if (readType.equals("subcategory")) {
                        if (readSubcategory.equals(subcategory)) {
                            expenseList.add(bDExpense);
                        }
                    } else {
                        expenseList.add(bDExpense);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                sc.close();
            }

            if (readType.equals("subcategory")) {
                if (expenseList.size() == 0) {
                    System.out.println("--No expenses found for that category, please re-enter.--\n");
                } else {
                    return expenseList;
                }
            } else {
                return expenseList;
            }
        }
        return null;
    }
}