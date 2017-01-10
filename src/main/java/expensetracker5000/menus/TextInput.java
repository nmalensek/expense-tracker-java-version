package expensetracker5000.menus;

import java.util.Scanner;

/**
 * Created by nicholas on 1/8/17.
 */
public class TextInput {
    public static String userInput() {
        Scanner input = new Scanner(System.in);

        return input.next();
    }

    public static String userLineInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
