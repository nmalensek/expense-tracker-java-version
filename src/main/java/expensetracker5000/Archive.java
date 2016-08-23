import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nicholas on 8/14/16.
 */
public class Archive {

    public void checkForFolders() {
        String expenseFolder = "./expenses";
        String archive = "./expenses/expense_archive";

        File currentExpenseFolder = new File(expenseFolder);
        File archiveFolder = new File(archive);

        if (currentExpenseFolder.exists()) {
            //archive folder exists, do nothing
        } else {
            try {
                System.out.println("Creating expenses folder...");
                Files.createDirectory(Paths.get(expenseFolder));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (archiveFolder.exists()) {
            //archive folder exists, do nothing
        } else {
            try {
                System.out.println("Creating archive folder...\n");
                Files.createDirectory(Paths.get(archive));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void moveToArchive(String currentMonth) {
        String archiveLocation = "./expenses/expense_archive/";
        String expenseLocation = "./expenses/";

        Path archiveDir = Paths.get(archiveLocation);
        Path expenseDir = Paths.get(expenseLocation);

        File directory = new File(expenseLocation);
        String[] myFiles = directory.list(new FilenameFilter() {
            public boolean accept(File directory, String fileName) {
                return !fileName.startsWith(currentMonth) && fileName.endsWith(".txt");
            }
        });
        for (String f : myFiles) {
            try {
                System.out.println("Archiving last month's files...\n");
                Path convertExpenseString = Paths.get(expenseLocation + f);
                Path convertArchiveString = Paths.get(archiveLocation + f);
                Files.move(convertExpenseString, convertArchiveString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        String timestamp = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
//        Archive arc = new Archive();
//
//        arc.moveToArchive(timestamp);
    }
}
