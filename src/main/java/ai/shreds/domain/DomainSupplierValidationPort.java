package ai.shreds.domain;

/**
 * Interface defining the contract for validating suppliers within the domain layer.
 */
public interface DomainSupplierValidationPort {
    /**
     * Validates the existence of a supplier by its unique identifier.
     *
     * @param supplierId the unique identifier of the supplier
     * @return true if the supplier is valid, false otherwise
     */
    boolean validateSupplier(String supplierId);
}