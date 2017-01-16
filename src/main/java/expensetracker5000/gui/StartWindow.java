package expensetracker5000.gui;

import expensetracker5000.categories.Category;
import expensetracker5000.categories.CategoryCreator;
import expensetracker5000.menus.ButtonCreator;

import javax.swing.*;
import java.util.List;
import java.awt.*;

/**
 * Created by nicholas on 1/14/17.
 */
public class StartWindow {
    private JFrame mainGUI;
    private JPanel contentPane;
    private JLabel chooseCategory;
    private CategoryCreator categoryCreator;
    private List<Category> categoriesList;
    private ButtonCreator bC;

    public void createFrame() {
        mainGUI = new JFrame();
        mainGUI.setTitle("Welcome to ExpenseTracker5000!");
        mainGUI.setBounds(100, 100, 375, 150);
        bC = new ButtonCreator(this);

        chooseCategory = new JLabel("Please select a category:");

        contentPane = new JPanel(new FlowLayout());
        contentPane.add(chooseCategory);
        retrieveCategories();
        addButtons();

        mainGUI.add(contentPane);
        mainGUI.setVisible(true);
    }

    private void addButtons() {
        for (Category category : categoriesList) {
            JButton newButton = bC.createCategoryButton(category, category.getName());
            contentPane.add(newButton);
        }
    }

    private void retrieveCategories() {
        categoryCreator = new CategoryCreator();
        categoriesList = categoryCreator.getAvailableCategories();
    }

    public void addPane(JPanel panel) { mainGUI.add(panel); }

    public void showMainPane() { contentPane.setVisible(true); }
    public void hideMainPane() { contentPane.setVisible(false); }

    public static void main(String[] args) {
        StartWindow mainTest = new StartWindow();
        mainTest.createFrame();
    }
}
