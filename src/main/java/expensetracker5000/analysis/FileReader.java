package expensetracker5000.analysis;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static expensetracker5000.menus.TextInput.userLineInput;

/**
 * Created by nicholas on 8/6/16.
 */
public class FileReader {
    private List<BigDecimal> expenseList;
    private Scanner fileParser;
    private String subcategory;

    public void printExpenseFile(File fileToRead) {
        try {
            System.out.println("");
            fileParser = new Scanner(fileToRead);
            String output = String.format("%-20s %-18s %-20s %-20s %n", "Date",
                    "Amount", "Subcategory", "Description");
            output += "---------------------------------------------------------------------------\n";

            while (fileParser.hasNextLine()) {
                String[] delimiter = fileParser.nextLine().split("\\t");
                for (String del : delimiter) {
                    output += del.format("%-20s", del);
                }
                 output += "\n";
            }
            System.out.println(output + "\n");
            JOptionPane.showMessageDialog(null, output);
//            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileParser.close();
        }
    }

    public List<BigDecimal> readExpenses(String fileName, String statType, String readType) {
        while (true) {
            try {
                File f = new File(fileName);
                fileParser = new Scanner(f);

                expenseList = new ArrayList<>();

                if (readType.equals("subcategory")) {
                    System.out.println("Enter the subcategory you would like the " + statType + " of:");
                    subcategory = userLineInput();

                    if (subcategory.equals("q")) { break; }
                }

                while (fileParser.hasNextLine()) {
                    retrieveExpenses(fileParser, readType, subcategory);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                fileParser.close();
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

    private void retrieveExpenses (Scanner scanner, String readType, String subcategory) {
        String line = scanner.nextLine();
        String[] delimiter = line.split("\\t");
        String readExpense = delimiter[1];
        BigDecimal bDExpense = new BigDecimal(readExpense);
        String readSubcategory = delimiter[2];

        if (readType.equals("subcategory")) {
            if (readSubcategory.equals(subcategory)) {
                expenseList.add(bDExpense);
            }
        } else { expenseList.add(bDExpense); }
    }
}