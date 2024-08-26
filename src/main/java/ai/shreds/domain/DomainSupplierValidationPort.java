package ai.shreds.domain;

public interface DomainSupplierValidationPort {
    /**
     * Validates the existence of a supplier by its unique identifier.
     *
     * @param supplierId the unique identifier of the supplier
     * @return true if the supplier is valid, false otherwise
     */
    boolean validateSupplier(String supplierId);
}