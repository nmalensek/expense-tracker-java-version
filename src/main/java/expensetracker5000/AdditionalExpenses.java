package expensetracker5000;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nicholas on 8/21/16.
 */
public class AdditionalExpenses extends MainMenu {

    public static void main(String[] args) {
        String timestamp = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
        Archive arc = new Archive();

        arc.checkForFolders();
        arc.moveToArchive(timestamp);

        try {
            while (additionalInputOptions()) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void additionalOptions() {
        System.out.println("7 - Moving expenses");
    }

    private static boolean additionalInputOptions() {
        String choice;
        String timestamp = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());

        initialOptions();
        additionalOptions();

        choice = selection();
        Category cat = null;

        if (choice.equals("1")) {
            cat = new Category(timestamp + " food.txt", "food");
        } else if (choice.equals("2")) {
            cat = new Category(timestamp + " rent+utilities.txt", "rent/utilities");
        } else if (choice.equals("3")) {
            cat = new Category(timestamp + " transportation.txt", "transportation");
        } else if (choice.equals("4")) {
            cat = new Category(timestamp + " recreation.txt", "recreation");
        } else if (choice.equals("5")) {
            cat = new Category(timestamp + " clothing.txt", "clothing");
        } else if (choice.equals("6")) {
            cat = new Category(timestamp + " vacation.txt", "vacation");
        } else if (choice.equals("7")) {
            cat = new Category(timestamp + " moving.txt", "moving");
        } else if (choice.equals("q")) {
            System.out.println("Goodbye!");
            return false;
        } else {
            System.out.println("Not a valid option!\n");
        }

        return true;
    }
}
