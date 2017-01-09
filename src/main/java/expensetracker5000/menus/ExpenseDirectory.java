package expensetracker5000.menus;

/**
 * Created by nicholas on 1/8/17.
 */
public enum ExpenseDirectory {
    FOLDERPATH;

    public String expenseFolderPath() {
        return "./.expenses/";
    }

    public String archiveFolderPath() {
        return "./.expenses/archive";
    }
}
