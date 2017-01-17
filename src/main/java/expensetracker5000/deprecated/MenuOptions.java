package expensetracker5000.deprecated;

/**
 * Created by nicholas on 1/8/17.
 */
public class MenuOptions {
    public static void mainMenuOptions() {
        System.out.println("Welcome to Expense Tracker 5000!");
        System.out.println("Enter q at any prompt to go back one menu level");
        System.out.println("Please choose from the following categories:");
        System.out.println("1 - food");
        System.out.println("2 - rent+utilities");
        System.out.println("3 - transportation");
        System.out.println("4 - recreation");
        System.out.println("5 - clothing");
        System.out.println("6 - vacation");
    }

    public static void categoryOptions(String expenseCategory) {
        System.out.println("Please choose from the following actions to modify "
                + expenseCategory + " expenses:");
        System.out.println("1 - add new expense");
        System.out.println("2 - add multiple expenses");
        System.out.println("3 - list all entered expenses");
        System.out.println("4 - analyze " + expenseCategory + " expenses");
        System.out.println("5 - open expense file");
    }

    public static void newFileOptions(String expenseCategory) {
        System.out.println(expenseCategory + " category does not exist, enter a new expense");
        System.out.println("1 - add new expense");
        System.out.println("2 - add multiple expenses");
    }

    public static void analysisOptions(String category) {
        System.out.println("Analyze " + category + " expenses:");
        System.out.println("1 - sum of category expenses");
        System.out.println("2 - sum of subcategory expenses");
        System.out.println("3 - mean of category expenses");
        System.out.println("4 - mean of subcategory expenses");
        System.out.println("5 - median of category expenses");
    }
}
