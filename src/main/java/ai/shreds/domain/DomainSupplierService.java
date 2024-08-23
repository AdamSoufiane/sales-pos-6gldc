package ai.shreds.domain;

import ai.shreds.domain.entity.DomainSupplierEntity;
import ai.shreds.domain.repository.DomainSupplierRepositoryPort;
import ai.shreds.domain.exception.SupplierNotFoundException;
import ai.shreds.domain.exception.InvalidSupplierDataException;
import ai.shreds.domain.exception.InvalidSupplierIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainSupplierService {
    private final DomainSupplierRepositoryPort domainSupplierRepositoryPort;

    /**
     * Saves a supplier entity to the database.
     * @param supplier The supplier entity to be saved.
     */
    public void saveSupplier(DomainSupplierEntity supplier) {
        validateSupplier(supplier);
        domainSupplierRepositoryPort.save(supplier);
    }

    /**
     * Finds a supplier entity by its ID.
     * @param id The ID of the supplier entity.
     * @return The found supplier entity.
     */
    public DomainSupplierEntity findSupplierById(Long id) {
        validateSupplierId(id);
        return domainSupplierRepositoryPort.findById(id);
    }

    /**
     * Deletes a supplier entity by its ID.
     * @param id The ID of the supplier entity to be deleted.
     */
    public void deleteSupplierById(Long id) {
        validateSupplierId(id);
        if (domainSupplierRepositoryPort.existsById(id)) {
            domainSupplierRepositoryPort.deleteById(id);
        } else {
            throw new SupplierNotFoundException("Supplier not found");
        }
    }

    /**
     * Validates supplier data before saving.
     * @param supplier The supplier to validate.
     */
    private void validateSupplier(DomainSupplierEntity supplier) {
        // Validation logic for supplier entity
        if (supplier.getName() == null || supplier.getName().isEmpty()) {
            throw new InvalidSupplierDataException("Supplier name is required");
        }
        if (supplier.getContactInfo() == null || supplier.getContactInfo().isEmpty()) {
            throw new InvalidSupplierDataException("Supplier contact information is required");
        }
        if (supplier.getAddress() == null || supplier.getAddress().isEmpty()) {
            throw new InvalidSupplierDataException("Supplier address is required");
        }
    }

    /**
     * Validates supplier ID before finding or deleting.
     * @param id The supplier ID to validate.
     */
    private void validateSupplierId(Long id) {
        // Validation logic for supplier ID
        if (id == null || id <= 0) {
            throw new InvalidSupplierIdException("Invalid supplier ID");
        }
    }
}

/**
 * Exception thrown when a supplier is not found.
 */
class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(String message) {
        super(message);
    }
}

/**
 * Exception thrown when supplier data is invalid.
 */
class InvalidSupplierDataException extends RuntimeException {
    public InvalidSupplierDataException(String message) {
        super(message);
    }
}

/**
 * Exception thrown when a supplier ID is invalid.
 */
class InvalidSupplierIdException extends RuntimeException {
    public InvalidSupplierIdException(String message) {
        super(message);
    }
}