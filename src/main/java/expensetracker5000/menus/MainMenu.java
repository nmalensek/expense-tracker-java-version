package expensetracker5000.menus;

import expensetracker5000.file_modification.Archive;
import expensetracker5000.file_modification.Category;

import static expensetracker5000.menus.CurrentDate.currentYearMonth;
import static expensetracker5000.menus.TextInput.userInput;

/**
 * Created by nicholas on 8/5/16.
 */
public class MainMenu {
    private static String yearAndMonth = currentYearMonth();

    public static void main(String[] args) {
        Archive arc = new Archive();

        arc.checkForFolders();
        arc.moveToArchive(yearAndMonth);

        try {
            while (inputOptions()) {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean inputOptions() {
        String choice;

        initialOptions();
        choice = userInput();
        Category cat = null;
        if (choice.equals("1")) {
            cat = new Category(yearAndMonth + " food.txt", "food");
        } else if (choice.equals("2")) {
            cat = new Category(yearAndMonth + " rent+utilities.txt", "rent/utilities");
        } else if (choice.equals("3")) {
            cat = new Category(yearAndMonth + " transportation.txt", "transportation");
        } else if (choice.equals("4")) {
            cat = new Category(yearAndMonth + " recreation.txt", "recreation");
        } else if (choice.equals("5")) {
            cat = new Category(yearAndMonth + " clothing.txt", "clothing");
        } else if (choice.equals("6")) {
            cat = new Category(yearAndMonth + " vacation.txt", "vacation");
        } else if (choice.equals("q")) {
            System.out.println("Goodbye!");
            return false;
        } else {
            System.out.println("Not a valid option!\n");
        }

        return true;
    }

    public static void initialOptions() {
        System.out.println("Welcome to Expense Tracker 5000!");
        System.out.println("Enter q at any prompt to go back one menu level");
        System.out.println("Please choose from the following categories:");
        System.out.println("1 - Food");
        System.out.println("2 - Rent/Utilities");
        System.out.println("3 - Transportation");
        System.out.println("4 - Recreation");
        System.out.println("5 - Clothing");
        System.out.println("6 - Vacation");
    }
}