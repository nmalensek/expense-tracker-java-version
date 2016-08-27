package expensetracker5000;

import java.util.Scanner;

/**
 * Created by nicholas on 8/17/16.
 */
public class AnalysisMenu {

    public boolean AnalysisMainMenu(String categoryFileToAnalyze, String categoryToAnalyze) {
        String userChoice;
        boolean toMainMenu = false;

        while (true) {
            Analysis a = new Analysis();
            analysisOptions(categoryToAnalyze);
            userChoice = userSelection();

            if (userChoice.equals("1")) {
                a.statFromFile(categoryFileToAnalyze, "sum", "");
            } else if (userChoice.equals("2")) {
                a.statFromFile(categoryFileToAnalyze, "sum", "subcategory");
            } else if (userChoice.equals("3")) {
                a.statFromFile(categoryFileToAnalyze, "mean", "");
            } else if (userChoice.equals("4")) {
                a.statFromFile(categoryFileToAnalyze, "mean", "subcategory");
            } else if (userChoice.equals("5")) {
                a.statFromFile(categoryFileToAnalyze, "median", "");
            } else if (userChoice.equals("q")) {
                break;
            } else if (userChoice.equals("e")) {
                toMainMenu = true;
                break;
            } else {
                System.out.println("Not a valid option!");
            }
        } return toMainMenu;
    }

    private static void analysisOptions(String category) {
        System.out.println("Analyze " + category + " expenses:");
        System.out.println("1 - Sum of category expenses");
        System.out.println("2 - Sum of subcategory expenses");
        System.out.println("3 - Mean of category expenses");
        System.out.println("4 - Mean of subcategory expenses");
        System.out.println("5 - Median of category expenses");
    }

    private static String userSelection() {
        Scanner input = new Scanner(System.in);
        String user_selection;

        user_selection = input.next();

        return user_selection;
    }

}





