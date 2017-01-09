package expensetracker5000.menus;

/**
 * Created by nicholas on 1/8/17.
 */
public class MenuOptions {
    public static void mainMenuOptions() {
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

    public static void categoryOptions(String expenseCategory) {
        System.out.println("Please choose from the following actions to modify "
                + expenseCategory + " expenses:");
        System.out.println("1 - Add new expense");
        System.out.println("2 - Add multiple expenses");
        System.out.println("3 - List all entered expenses");
        System.out.println("4 - Analyze " + expenseCategory + " expenses");
        System.out.println("5 - Open expense file");
    }

    public static void newFileOptions(String expenseCategory) {
        System.out.println(expenseCategory + " category does not exist, enter a new expense");
        System.out.println("1 - Add new expense");
        System.out.println("2 - Add multiple expenses");
    }

    public static void analysisOptions(String category) {
        System.out.println("Analyze " + category + " expenses:");
        System.out.println("1 - Sum of category expenses");
        System.out.println("2 - Sum of subcategory expenses");
        System.out.println("3 - Mean of category expenses");
        System.out.println("4 - Mean of subcategory expenses");
        System.out.println("5 - Median of category expenses");
    }
}
