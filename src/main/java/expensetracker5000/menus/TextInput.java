package expensetracker5000.menus;

import java.util.Scanner;

/**
 * Created by nicholas on 1/8/17.
 */
public class TextInput {
    public static String userInput() {
        Scanner input = new Scanner(System.in);
        String user_selection;

        user_selection = input.next();
        return user_selection;
    }
}
