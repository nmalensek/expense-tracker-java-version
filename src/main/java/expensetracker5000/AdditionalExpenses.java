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

        if (choice.equals("7")) {
            cat = new Category(timestamp + " moving.txt", "moving");
        } else {
            inputOptions();
        } return inputOptions();
    }
}
