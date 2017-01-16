package expensetracker5000.menus;

import expensetracker5000.categories.Categories;
import expensetracker5000.gui.ExpenseMenu;
import expensetracker5000.gui.StartWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nicholas on 1/15/17.
 */
public class ButtonCreator {
    private ExpenseMenu expenseMenu;
    private Categories currentCategory;
    private StartWindow mainFrame;
    private ButtonCreator buttonCreator;

    public ButtonCreator(StartWindow mainFrame) {
        this.mainFrame = mainFrame;
        this.buttonCreator = this;
    }

    public JButton createCategoryButton(Categories category, String label) {
        JButton newButton = new JButton(label);
        this.mainFrame = mainFrame;
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentCategory = category;
                mainFrame.hideMainPane();
                expenseMenu = new ExpenseMenu(currentCategory, buttonCreator);
                mainFrame.addPane(expenseMenu.createExpensePane());
                expenseMenu.showExpensePanel();
            }
        });
        return newButton;
    }

    public JButton backButton(JPanel currentPanel) {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentPanel.setVisible(false);
                mainFrame.showMainPane();
            }
        });
        return backButton;
    }

    //TODO simplify first param to return type of end method if possible
    public JButton newExpensesButton() {
        JButton expenseButton = new JButton("Add expense");
        expenseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentCategory.newExpense();
            }
        });
        return expenseButton;
    }

    public JButton newListButton() {
        JButton listExpensesButton = new JButton("List expenses");
        listExpensesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentCategory.listExpenses();
            }
        });
        return listExpensesButton;
    }

    public JButton newAnalysisButton() {
        JButton analyzeExpensesButton = new JButton("Analyze expenses");
        analyzeExpensesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentCategory.analyze();
            }
        });
        return analyzeExpensesButton;
    }


    public JButton newOpenFileButton() {
        JButton openFileButton = new JButton("Open expense file");
        openFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentCategory.openFile();
            }
        });
        return openFileButton;
    }
}
