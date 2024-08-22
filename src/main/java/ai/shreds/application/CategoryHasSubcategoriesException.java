package ai.shreds.application;

/**
 * CategoryHasSubcategoriesException is thrown when a category to be deleted has subcategories.
 */
public class CategoryHasSubcategoriesException extends RuntimeException {

    /**
     * Constructs a new CategoryHasSubcategoriesException with the specified detail message.
     *
     * @param message the detail message.
     */
    public CategoryHasSubcategoriesException(String message) {
        super(message);
    }
}