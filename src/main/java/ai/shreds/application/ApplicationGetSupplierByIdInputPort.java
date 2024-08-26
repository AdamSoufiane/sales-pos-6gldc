package ai.shreds.application;

import ai.shreds.shared.SharedSupplierDTO;

/**
 * ApplicationGetSupplierByIdInputPort provides an abstraction for retrieving a supplier by its unique ID.
 */
public interface ApplicationGetSupplierByIdInputPort {
    /**
     * Retrieves the details of a specific supplier by its ID.
     * 
     * @param id the unique identifier of the supplier
     * @return SharedSupplierDTO containing supplier details
     */
    SharedSupplierDTO getSupplierById(Long id);
}