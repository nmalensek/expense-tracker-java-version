package expensetracker5000.file_modification;

import expensetracker5000.analysis.ExpenseTraits;
import expensetracker5000.analysis.FileReader;
import expensetracker5000.menus.AnalysisMenu;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

import static expensetracker5000.menus.CurrentDate.currentDateWithDay;
import static expensetracker5000.menus.CurrentDate.currentYearMonth;
import static expensetracker5000.menus.ExpenseDirectory.FOLDERPATH;
import static expensetracker5000.menus.MenuOptions.categoryOptions;
import static expensetracker5000.menus.MenuOptions.newFileOptions;
import static expensetracker5000.menus.TextInput.userInput;
import static expensetracker5000.menus.TextInput.userLineInput;

/**
 * Created by nicholas on 8/5/16.
 */
public class Category {
    private String expenseFolderPath = FOLDERPATH.expenseFolderPath();
    private String currentDate = currentDateWithDay();
    private static String yearAndMonth = currentYearMonth();
    BigDecimal stringToBigDecimal = new BigDecimal("0.00");

    public Category(String chosenCategory) {
        String choice;
        String filePath = expenseFolderPath + yearAndMonth + " " + chosenCategory + ".txt";
        File checkFile = new File(filePath);

        while (true) {
            if (checkFile.exists() && !checkFile.isDirectory()) {
                categoryOptions(chosenCategory);
                choice = userInput();
                FileReader a = new FileReader();
                AnalysisMenu am = new AnalysisMenu();

                if (choice.equals("1")) {
                    writeExpense(filePath, "no");
                } else if (choice.equals("2")) {
                    System.out.println("feature disabled until gui is implemented");
                } else if (choice.equals("3")) {
                    a.printExpenseFile(filePath);
                } else if (choice.equals("4")) {
                    if (!am.AnalysisMainMenu(filePath, chosenCategory)) {
                    } else {
                        break;
                    }
                } else if (choice.equals("5")) {
                    try {
                        Desktop.getDesktop().edit(checkFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (choice.equals("q")) {
                    break;
                } else {
                    System.out.println("Not a valid option!\n");
                }
            } else {
                newFileOptions(chosenCategory);
                choice = userInput();

                if (choice.equals("1")) {
                    writeExpense(filePath, "no");
                } else if (choice.equals("2")) {
                    System.out.println("feature disabled until gui is implemented");
                } else if (choice.equals("q")) {
                    break;
                } else {
                    System.out.println("Not a valid option!\n");
                }
            }

        }
    }

    public void writeExpense(String fileToWrite, String numerousEntries) {
        List<ExpenseTraits> createdList = createExpense(numerousEntries);
        BufferedWriter writer = null;
        File categoryLog = new File(fileToWrite);

        for (ExpenseTraits expense : createdList) {
            try {
                writer = new BufferedWriter(new FileWriter(categoryLog, true));
                writer.write(expense.toString());
                System.out.println("\n--- New expense(s) added! ---\n");
            } catch (IOException e) {
                System.out.println("Failed to write expense: " + expense);
                e.printStackTrace();
            } finally {
                closeWriter(writer);
            }
        }
    }

    private void closeWriter(BufferedWriter writer) {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ExpenseTraits> createExpense(String multipleEntries) {
        List<ExpenseTraits> entries = new ArrayList<>();

        ExpenseTraits eT = new ExpenseTraits(currentDate, expenseEntry(),
                subcategoryEntry(), descriptionEntry());
        entries.add(eT);

        return entries;
    }

    private BigDecimal expenseEntry() {
        System.out.println("Enter the expense amount:");
        String input = userInput();
        try {
            stringToBigDecimal = new BigDecimal(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a dollar amount.");
            expenseEntry();
        }
        return stringToBigDecimal;
    }

    private String subcategoryEntry() {
        System.out.println("Enter the subcategory:");
        return userLineInput();
    }

    private String descriptionEntry() {
        System.out.println("Enter the expense description:");
        return userLineInput();
    }
}