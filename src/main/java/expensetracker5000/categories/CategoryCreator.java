package expensetracker5000.categories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicholas on 1/14/17.
 */
public class CategoryCreator {
    private List<Categories> availableCategories = new ArrayList<>();

    public CategoryCreator() {
        addCategories();
    }

    private Categories[] categoryList = {
            new Categories("Food"),
            new Categories("Rent & Utilities"),
            new Categories("Transportation"),
            new Categories("Recreation"),
            new Categories("Clothing"),
            new Categories("Vacation"),
    };

    public void addCategories() {
        for (Categories category : categoryList) {
            availableCategories.add(category);
        }
    }

    public List<Categories> getAvailableCategories() {
        return availableCategories;
    }
}
