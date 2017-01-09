package expensetracker5000.menus;

import expensetracker5000.analysis.Analysis;

import static expensetracker5000.menus.MenuOptions.analysisOptions;
import static expensetracker5000.menus.TextInput.userInput;

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
            userChoice = userInput();

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
}





