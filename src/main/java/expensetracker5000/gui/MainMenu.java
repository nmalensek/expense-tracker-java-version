package expensetracker5000.gui;

import expensetracker5000.categories.Categories;
import expensetracker5000.categories.CategoryCreator;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nicholas on 1/14/17.
 */
public class MainMenu {
    private JFrame mainGUI;
    private JPanel contentPane;
    private JLabel chooseCategory;
    private CategoryCreator categoryCreator;
    private List<Categories> categoriesList;

    public void createFrame() {
        mainGUI = new JFrame();
        mainGUI.setTitle("Welcome to ExpenseTracker5000!");
        mainGUI.setBounds(100, 100, 450, 300);


        contentPane = new JPanel(new FlowLayout());
        retrieveCategories();
        addButtons();

        mainGUI.add(contentPane);
        mainGUI.setVisible(true);
    }

    private void addButtons() {
        for (Categories category : categoriesList) {
            JButton newButton = createButton(category, category.getName());
            contentPane.add(newButton);
        }
    }

    private JButton createButton(Categories category, String label) {
        JButton newButton = new JButton(label);
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(label);
            }
        });
        return newButton;
    }

    private void retrieveCategories() {
        categoryCreator = new CategoryCreator();
        categoriesList = categoryCreator.getAvailableCategories();
    }

    public static void main(String[] args) {
        MainMenu mainTest = new MainMenu();
        mainTest.createFrame();
    }
}
