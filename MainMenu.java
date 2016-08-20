import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by nicholas on 8/5/16.
 */
public class MainMenu {

    public static void main(String[] args) {
        String choice;
        String timestamp = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());

        Archive arc = new Archive();

        arc.checkForFolders();
        arc.moveToArchive(timestamp);

        try {
            while (true) {

                choice = null;
                InitialOptions();
                choice = Selection();
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
                } else if (choice.equals("q") || choice.equals("7")) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Not a valid option!\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void InitialOptions() {
        System.out.println("Welcome to Expense Tracker 5000!");
        System.out.println("Please choose from the following categories:");
        System.out.println("1 - Food");
        System.out.println("2 - Rent/Utilities");
        System.out.println("3 - Transportation");
        System.out.println("4 - Recreation");
        System.out.println("5 - Clothing");
        System.out.println("6 - Vacation");
        System.out.println("7/q - Quit");
    }

    private static String Selection() {
        Scanner input = new Scanner(System.in);
        String user_selection;

        user_selection = input.next();

        return user_selection;
    }

}
