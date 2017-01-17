package expensetracker5000.gui;

import javax.swing.*;
import java.math.BigDecimal;

/**
 * Created by nicholas on 1/16/17.
 */
public class ExpenseDialog {
    private JTextField amount;
    private JTextField subcategory;
    private JTextField description;

    public JPanel expenseEntryPanel() {
        amount = new JTextField(6);
        subcategory = new JTextField(10);
        description = new JTextField(10);

        JPanel entryPanel = new JPanel();
        entryPanel.add(new JLabel("Amount:"));
        entryPanel.add(amount);
        entryPanel.add(new JLabel("Category:"));
        entryPanel.add(subcategory);
        entryPanel.add(new JLabel("Description:"));
        entryPanel.add(description);

        entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));

        return entryPanel;
    }

    public BigDecimal getAmount() throws NumberFormatException {
        BigDecimal expense = new BigDecimal(amount.getText());
        return expense;
    }

    public String getSubcategory() { return subcategory.getText(); }
    public String getDescription() { return description.getText(); }
}
