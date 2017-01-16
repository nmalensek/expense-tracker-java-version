package expensetracker5000.gui;

import expensetracker5000.categories.Categories;
import expensetracker5000.menus.ButtonCreator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nicholas on 1/15/17.
 */
public class ExpenseMenu {
    private JPanel expensePane;
    private JLabel actionLabel;
    private Categories currentCategory;
    private ButtonCreator buttonCreator;

    public ExpenseMenu(Categories currentCategory, ButtonCreator buttonCreator) {
        this.currentCategory = currentCategory;
        this.buttonCreator = buttonCreator;
    }

    public JPanel createExpensePane() {
        actionLabel = new JLabel("What would you like to do with " +
                currentCategory.getName() + " expenses?");

        expensePane = new JPanel(new FlowLayout());
        expensePane.add(actionLabel);
        addButtons();

        return expensePane;
    }

    private void addButtons() {
        expensePane.add(buttonCreator.newExpensesButton());
        expensePane.add(buttonCreator.newListButton());
        expensePane.add(buttonCreator.newAnalysisButton());
        expensePane.add(buttonCreator.newOpenFileButton());
        expensePane.add(buttonCreator.backButton(expensePane));
    }

    public void showExpensePanel() { expensePane.setVisible(true); }
}
