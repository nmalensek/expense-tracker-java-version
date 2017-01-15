package expensetracker5000.categories;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static expensetracker5000.menus.CurrentDate.currentYearMonth;
import static expensetracker5000.menus.ExpenseDirectory.FOLDERPATH;

/**
 * Created by nicholas on 1/9/17.
 */
public class Categories {
    private String name;
    private File categoryFile;
    private static List<Categories> availableCategories = new ArrayList<>();

    public Categories(String name) {
        this.name = name;
        setFilePath();
        availableCategories.add(this);
    }
    public void newExpense() {}

    public void multipleExpenses() {}

    public void listExpenses() {}

    public void analyze() {}

    public void openFile() {
        try {
            Desktop.getDesktop().edit(categoryFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFilePath() {
        categoryFile = new File(FOLDERPATH.expenseFolderPath() + currentYearMonth()
                + " " + getName() + ".txt");
    }

    public String getName() { return name; }

    public List<Categories> getAvailableCategories() { return availableCategories; }
}
