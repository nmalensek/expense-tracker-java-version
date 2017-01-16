package expensetracker5000.categories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicholas on 1/14/17.
 */
public class CategoryCreator {
    private List<Category> availableCategories = new ArrayList<>();

    public CategoryCreator() {
        addCategories();
    }

    private Category[] categoryList = {
            new Category("Food"),
            new Category("Rent & Utilities"),
            new Category("Transportation"),
            new Category("Recreation"),
            new Category("Clothing"),
            new Category("Vacation"),
    };

    public void addCategories() {
        for (Category category : categoryList) {
            availableCategories.add(category);
        }
    }

    public List<Category> getAvailableCategories() {
        return availableCategories;
    }
}
