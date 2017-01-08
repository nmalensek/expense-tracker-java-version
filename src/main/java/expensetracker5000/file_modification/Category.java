package expensetracker5000.file_modification;

import expensetracker5000.analysis.ExpenseTraits;
import expensetracker5000.analysis.Analysis;
import expensetracker5000.menus.AnalysisMenu;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


/**
 * Created by nicholas on 8/5/16.
 */
public class Category {

    public Category(String categoryFile, String chosenCategory) {
        String choice;
        String actualPath = "./expenses/" + categoryFile;
        File checkFile = new File(actualPath);


        while (true) {
            if (checkFile.exists() && !checkFile.isDirectory()) {
                options(chosenCategory);
                choice = selection();
                Analysis a = new Analysis();
                AnalysisMenu am = new AnalysisMenu();

                if (choice.equals("1")) {
                    writeList(actualPath, "no");
                } else if (choice.equals("2")) {
                    writeList(actualPath, "yes");
                } else if (choice.equals("3")) {
                    a.printExpenseFile(actualPath);
                } else if (choice.equals("4")) {
                    if (!am.AnalysisMainMenu(actualPath, chosenCategory)) {
                    } else {
                        break;
                    }
                } else if (choice.equals("5")) {
                    File f = new File(actualPath);
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
                choice = selection();

                if (choice.equals("1")) {
                    writeList(actualPath, "no");
                } else if (choice.equals("2")) {
                    writeList(actualPath, "yes");
                } else if (choice.equals("q")) {
                    break;
                } else {
                    System.out.println("Not a valid option!\n");
                }
            }

        }
    }

    private static void options(String expenseCategory) {
        System.out.println("Please choose from the following actions to modify " + expenseCategory + " expenses:");
        System.out.println("1 - Add new expense");
        System.out.println("2 - Add multiple expenses");
        System.out.println("3 - List all entered expenses");
        System.out.println("4 - Analyze " + expenseCategory + " expenses");
        System.out.println("5 - Open expense file");
    }

    private static void newFileOptions(String expenseCategory) {
        System.out.println(expenseCategory + " category does not exist, enter a new expense");
        System.out.println("1 - Add new expense");
        System.out.println("2 - Add multiple expenses");
    }

    private static String selection() {
        Scanner input = new Scanner(System.in);
        String user_selection;

        user_selection = input.next();

        return user_selection;
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
        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

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
                            ExpenseTraits ET = new ExpenseTraits(current_date, expense, subcategory, description);
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