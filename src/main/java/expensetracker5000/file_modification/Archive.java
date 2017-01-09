package expensetracker5000.file_modification;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        if (folderToCheckFor.exists()) {
            //folder exists, do nothing
        } else {
            try {
                System.out.println(folderType + " folder does not exist, " +
                        "creating " + folderType.toLowerCase() + " folder...");
                Files.createDirectory(Paths.get(folderPath));
                System.out.println(folderType + " folder created...\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void moveToArchive(String currentMonth) {

        File directory = new File(expenseFolderPath);
        String[] myFiles = directory.list(new FilenameFilter() {
            public boolean accept(File directory, String fileName) {
                return !fileName.startsWith(currentMonth) && fileName.endsWith(".txt");
            }
        });

        moveFiles(myFiles, expenseFolderPath, archiveFolderPath);
    }

    private void moveFiles(String[] filesToMove, String expensePath, String archivePath){
        for (String file : filesToMove) {
            try {
                System.out.println("Archiving last month's files...\n");
                Path convertExpenseString = Paths.get(expensePath + file);
                Path convertArchiveString = Paths.get(archivePath + file);
                Files.move(convertExpenseString, convertArchiveString);
                System.out.println("Archiving complete...\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public static void main(String[] args) {
//        String timestamp = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
//        Archive arc = new Archive();
//
//        arc.moveToArchive(timestamp);
//    }
}