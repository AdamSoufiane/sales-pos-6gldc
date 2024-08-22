package ai.shreds.application;

import ai.shreds.adapter.AdapterCategoryCreateRequest;
import ai.shreds.adapter.AdapterCategoryResponse;

/**
 * ApplicationCreateCategoryInputPort defines the contract for creating a new category.
 */
public interface ApplicationCreateCategoryInputPort {

    /**
     * Creates a new category with the provided details.
     *
     * @param request the details of the category to be created
     * @return the created category details
     * @throws ValidationException if the input data is invalid
     * @throws ParentCategoryNotFoundException if the parent category does not exist
     */
    AdapterCategoryResponse createCategory(AdapterCategoryCreateRequest request) throws ValidationException, ParentCategoryNotFoundException;
}

class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}

class ParentCategoryNotFoundException extends Exception {
    public ParentCategoryNotFoundException(String message) {
        super(message);
    }
}