package expensetracker5000.file_modification;

import expensetracker5000.analysis.ExpenseTraits;
import expensetracker5000.analysis.Analysis;
import expensetracker5000.menus.AnalysisMenu;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import static expensetracker5000.menus.CurrentDate.currentDateWithDay;
import static expensetracker5000.menus.ExpenseDirectory.FOLDERPATH;
import static expensetracker5000.menus.MenuOptions.categoryOptions;
import static expensetracker5000.menus.MenuOptions.newFileOptions;
import static expensetracker5000.menus.TextInput.userInput;

/**
 * Created by nicholas on 8/5/16.
 */
public class Category {
    private String expenseFolderPath = FOLDERPATH.expenseFolderPath();
    private String currentDate = currentDateWithDay();

    public Category(String categoryFile, String chosenCategory) {
        String choice;
        String filePath = expenseFolderPath + categoryFile;
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
                    File f = new File(filePath);
                    try {
                        Desktop.getDesktop().edit(f);
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
                    writeList(filePath, "yes");
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
            } catch (Exception e) {
                System.out.println("Failed to write expense: " + cL);
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<ExpenseTraits> createList(String multipleEntries) {
        String expense, subcategory, description;
        Scanner newExpenseInput = new Scanner(System.in);

        List<ExpenseTraits> entries = new ArrayList<>();

        while (true) {
            try {
                System.out.println("Enter the expense amount:");
                expense = newExpenseInput.next();

                if (expense.equals("q")) {
                    System.out.println("Going back...\n");
                    break;
                } else {
                    double userInput = Double.parseDouble(expense);
                    expense = Double.toString(userInput);
                    newExpenseInput.nextLine();

                    System.out.println("Enter the subcategory:");
                    subcategory = newExpenseInput.nextLine();

                    if (subcategory.equals("q")) {
                        System.out.println("Going back...\n");
                        break;
                    } else {
                        System.out.println("Enter the expense description:");
                        description = newExpenseInput.nextLine();

                        if (description.equals("q")) {
                            System.out.println("Going back...\n");
                            break;
                        } else {
                            ExpenseTraits ET = new ExpenseTraits(currentDate, expense, subcategory, description);
                            entries.add(ET);

                            System.out.println("\n--- New expense added! ---\n");

                            if (multipleEntries.equals("yes")) {
                                continue;
                            } else if (multipleEntries.equals("no")) {
                                break;
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a dollar amount.");
            }
        }
        return entries;
    }
}