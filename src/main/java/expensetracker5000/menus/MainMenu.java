package expensetracker5000.menus;

import expensetracker5000.file_modification.Archive;
import expensetracker5000.file_modification.ExpenseWriter;

import static expensetracker5000.menus.MenuOptions.mainMenuOptions;
import static expensetracker5000.menus.TextInput.userInput;

/**
 * Created by nicholas on 8/5/16.
 */
public class MainMenu {

    public static void main(String[] args) {
        Archive arc = new Archive();

        arc.checkForFolders();
        arc.checkForOldFiles();

        try {
            while (inputOptions()) {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean inputOptions() {
        String choice;

        mainMenuOptions();
        choice = userInput();
        ExpenseWriter cat = null;
        if (choice.equals("1")) {
            cat = new ExpenseWriter();
        } else if (choice.equals("2")) {
            cat = new ExpenseWriter();
        } else if (choice.equals("3")) {
            cat = new ExpenseWriter();
        } else if (choice.equals("4")) {
            cat = new ExpenseWriter();
        } else if (choice.equals("5")) {
            cat = new ExpenseWriter();
        } else if (choice.equals("6")) {
            cat = new ExpenseWriter();
        } else if (choice.equals("q")) {
            System.out.println("Goodbye!");
            return false;
        } else {
            System.out.println("Not a valid option!\n");
        }

        return true;
    }
}