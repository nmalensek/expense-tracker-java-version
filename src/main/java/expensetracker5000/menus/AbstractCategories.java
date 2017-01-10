package expensetracker5000.menus;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static expensetracker5000.menus.CurrentDate.currentYearMonth;
import static expensetracker5000.menus.ExpenseDirectory.FOLDERPATH;

/**
 * Created by nicholas on 1/9/17.
 */
abstract class AbstractCategories {
    public void newExpense() {}

    public void multipleExpenses() {}

    public void listExpenses() {}

    public void analyze() {}

    public void openFile() {
        File file = new File(FOLDERPATH.expenseFolderPath() + currentYearMonth()
                + " " + getName() + ".txt");
        try {
            Desktop.getDesktop().edit(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() { return ""; }
}
