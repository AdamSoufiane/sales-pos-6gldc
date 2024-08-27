package ai.shreds.domain;

import java.util.List;

public interface DomainProductValidationPort {
    /**
     * Validates the existence of products by their IDs.
     *
     * @param productIds List of product IDs to validate.
     * @return true if all products are valid, false otherwise.
     */
    boolean validateProducts(List<String> productIds);
}