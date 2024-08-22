package ai.shreds.domain;

import ai.shreds.adapter.AdapterProductCreateRequest;
import ai.shreds.adapter.AdapterProductUpdateRequest;
import java.math.BigDecimal;

public class ProductLogic {

    public void validateProductData(Object params) {
        if (params instanceof AdapterProductCreateRequest) {
            validateCreateRequest((AdapterProductCreateRequest) params);
        } else if (params instanceof AdapterProductUpdateRequest) {
            validateUpdateRequest((AdapterProductUpdateRequest) params);
        } else {
            throw new IllegalArgumentException("Invalid parameter type");
        }
    }

    private void validateCreateRequest(AdapterProductCreateRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be empty");
        }
        if (request.getPrice() == null || request.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value");
        }
        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID must not be null");
        }
    }

    private void validateUpdateRequest(AdapterProductUpdateRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be empty");
        }
        if (request.getPrice() == null || request.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value");
        }
        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID must not be null");
        }
    }
}