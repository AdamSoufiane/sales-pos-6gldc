package ai.shreds.application;

/**
 * Interface for deleting a supplier.
 */
public interface ApplicationDeleteSupplierInputPort {
    /**
     * Deletes a supplier by ID.
     *
     * @param id the ID of the supplier to delete
     */
    void deleteSupplier(Long id);
}