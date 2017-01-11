package expensetracker5000.file_modification;

import expensetracker5000.analysis.ExpenseTraits;
import expensetracker5000.analysis.Analysis;
import expensetracker5000.menus.AnalysisMenu;

import java.awt.*;
import java.io.*;
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
    private String input;

    public Category(String chosenCategory) {
        String choice;
        String filePath = expenseFolderPath + yearAndMonth + " " + chosenCategory + ".txt";
        File checkFile = new File(filePath);

        while (true) {
            if (checkFile.exists() && !checkFile.isDirectory()) {
                categoryOptions(chosenCategory);
                choice = userInput();
                Analysis a = new Analysis();
                AnalysisMenu am = new AnalysisMenu();

                if (choice.equals("1")) {
                    writeList(filePath, "no");
                } else if (choice.equals("2")) {
                    writeList(filePath, "yes");
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
                    writeList(filePath, "no");
                } else if (choice.equals("2")) {
                    System.out.println("feature disabled until GUI is implemented");
                } else if (choice.equals("q")) {
                    break;
                } else {
                    System.out.println("Not a valid option!\n");
                }
            }

        }
    }

    public void writeList(String fileToWrite, String numerousEntries) {
        List<ExpenseTraits> createdList = createList(numerousEntries);
        BufferedWriter writer = null;
        File categoryLog = new File(fileToWrite);

        for (ExpenseTraits cL : createdList) {
            try {
                writer = new BufferedWriter(new FileWriter(categoryLog, true));
                writer.write(cL.toString());
            } catch (IOException e) {
                System.out.println("Failed to write expense: " + cL);
                e.printStackTrace();
            } finally {
                try {
                    System.out.println("\n--- New expense(s) added! ---\n");
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<ExpenseTraits> createList(String multipleEntries) {
        String expense, subcategory, description;

        List<ExpenseTraits> entries = new ArrayList<>();

        while (true) {
            expense = expenseEntry();

            System.out.println("Enter the subcategory:");
            subcategory = userLineInput();

            System.out.println("Enter the expense description:");
            description = userLineInput();

            ExpenseTraits ET = new ExpenseTraits(currentDate, expense, subcategory, description);
            entries.add(ET);

            if (multipleEntries.equals("yes")) {
                continue;
            } else if (multipleEntries.equals("no")) {
                break;
            }
        }
        return entries;
    }

    private String expenseEntry() {
        System.out.println("Enter the expense amount:");
        input = userInput();
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a dollar amount.");
            expenseEntry();
        }
        return input;
    }
}