package expensetracker5000.file_modification;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static expensetracker5000.menus.CurrentDate.currentYearMonth;
import static expensetracker5000.menus.ExpenseDirectory.FOLDERPATH;

/**
 * Created by nicholas on 8/14/16.
 */
public class Archive {
    private String expenseFolderPath = FOLDERPATH.expenseFolderPath();
    private String archiveFolderPath = FOLDERPATH.archiveFolderPath();

    public void checkForFolders() {
        File currentExpenseFolder = new File(expenseFolderPath);
        File archiveFolder = new File(archiveFolderPath);

        checkForFolder(currentExpenseFolder, expenseFolderPath, "Expense");
        checkForFolder(archiveFolder, archiveFolderPath, "Archive");
    }

    private void checkForFolder(File folderToCheckFor, String folderPath,
                                String folderType) {
        if (!folderToCheckFor.exists()) {
            try {
                System.out.println(folderType + " folder does not exist, " +
                        "creating " + folderType.toLowerCase() + " folder...");
                Files.createDirectory(Paths.get(folderPath));
                System.out.println(folderType + " folder created...\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkForOldFiles() {
        File directory = new File(expenseFolderPath);
        String[] myFiles = directory.list(new FilenameFilter() {
            public boolean accept(File directory, String fileName) {
                return !fileName.startsWith(currentYearMonth()) && fileName.endsWith(".txt");
            }
        });
        if (myFiles.length > 0) { moveFiles(myFiles, expenseFolderPath, archiveFolderPath); }
    }

    private void moveFiles(String[] filesToMove, String expensePath, String archivePath){
        System.out.println("Archiving last month's files...");
        for (String file : filesToMove) {
            try {
                Path convertExpenseString = Paths.get(expensePath + file);
                Path convertArchiveString = Paths.get(archivePath + file);
                Files.move(convertExpenseString, convertArchiveString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Archiving complete...\n");
    }
}