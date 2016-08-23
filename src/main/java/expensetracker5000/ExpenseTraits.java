package expensetracker5000;

import java.util.Date;

/**
 * Created by nicholas on 8/6/16.
 */
public class ExpenseTraits {

    private String date;
    private String subcategory;
    private String description;
    private String expense;

    public ExpenseTraits(String date, String expense, String subcategory, String description) {

        this.date = date;
        this.expense = expense;
        this.subcategory = subcategory;
        this.description = description;

    }

//    public String getDate() { return date; }
//    public String getSubcategory() { return subcategory; }
    public String getExpense() { return expense; }
//    public String getDescription() { return description; }

    @Override
    public String toString() {
        String str = "";
        str += this.date + "\t";
        str += this.expense + "\t";
        str += this.subcategory + "\t";
        str += this.description + "\t";
        str += "\n";
        return str;
    }
}
