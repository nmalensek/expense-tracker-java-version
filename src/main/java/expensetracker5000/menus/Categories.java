package expensetracker5000.menus;

/**
 * Created by nicholas on 1/9/17.
 */
public class Categories {
    class Food extends AbstractCategories {
        public String getName() {
            return "food";
        }
    }

    class RentAndUtilities extends AbstractCategories {
        public String getName() {
            return "rent+utilities";
        }
    }

    class Transportation extends AbstractCategories {
        public String getName() {
            return "transportation";
        }
    }

    class Recreation extends AbstractCategories {
        public String getName() {
            return "recreation";
        }
    }

    class Clothing extends AbstractCategories {
        public String getName() {
            return "clothing";
        }
    }

    class Vacation extends AbstractCategories {
        public String getName() {
            return "vacation";
        }
    }
}
