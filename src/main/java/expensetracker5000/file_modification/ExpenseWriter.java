package expensetracker5000.file_modification;

import expensetracker5000.analysis.ExpenseTraits;
import expensetracker5000.gui.ExpenseDialog;

import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

import static expensetracker5000.menus.CurrentDate.currentDateWithDay;

/**
 * Created by nicholas on 8/5/16.
 */
public class ExpenseWriter {
    private File fileToWrite;
    private String currentDate = currentDateWithDay();
    private ExpenseDialog dialogs = new ExpenseDialog();
    private BigDecimal expense;
    private String subcategory;
    private String description;

    public ExpenseWriter(File fileToWrite) {
        this.fileToWrite = fileToWrite;
    }

    private void writeExpense(List<ExpenseTraits> expensesToWrite) {
        BufferedWriter writer = null;

        for (ExpenseTraits expense : expensesToWrite) {
            try {
                writer = new BufferedWriter(new java.io.FileWriter(fileToWrite, true));
                writer.write(expense.toString());
                System.out.println("\n--- New expense(s) added! ---\n");
            } catch (IOException e) {
                System.out.println("Failed to write expense: " + expense);
                e.printStackTrace();
            } finally {
                closeWriter(writer);
            }
        }
    }

    private void closeWriter(BufferedWriter writer) {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createExpense() {
        List<ExpenseTraits> entries = new ArrayList<>();

        ExpenseTraits eT = new ExpenseTraits(currentDate, expense,
                subcategory, description);
        entries.add(eT);
        resetFields();
        writeExpense(entries);
    }

    public void expenseEntry() {
        int result = createExpenseDialog();
        processEntry(result);
    }

    private int createExpenseDialog() {
        return JOptionPane.showConfirmDialog(null, dialogs.expenseEntryPanel(),
                "Title", JOptionPane.OK_CANCEL_OPTION);
    }

    private void processEntry(int result) {
        if (result == JOptionPane.OK_OPTION) {
            try {
                subcategory = dialogs.getSubcategory();
                description = dialogs.getDescription();
                expense = dialogs.getAmount();
                createExpense();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Invalid amount, " +
                        "please enter a dollar amount.");
                expenseEntry();
            }
        }
    }

    private void resetFields() {
        expense = null;
        description = "";
        subcategory = "";
    }
}