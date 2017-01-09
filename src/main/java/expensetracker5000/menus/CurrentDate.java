package expensetracker5000.menus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by nicholas on 1/8/17.
 */
public class CurrentDate {
    public static String currentDateWithDay() {
        return LocalDate.now().toString();
    }

    public static String currentYearMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }
}
