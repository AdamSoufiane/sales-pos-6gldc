package ai.shreds.domain;

import ai.shreds.shared.SharedRequestParams;
import java.util.List;

/**
 * DomainSupplierRepositoryPort is an interface in the domain layer that defines the contract for data access operations related to suppliers.
 */
public interface DomainSupplierRepositoryPort {
    /**
     * Retrieves all supplier records from the database, with optional filtering criteria.
     *
     * @param params the filtering criteria
     * @return a list of supplier entities
     */
    List<DomainSupplierEntity> findAll(SharedRequestParams params);

    /**
     * Retrieves a specific supplier record by its unique ID.
     *
     * @param id the unique identifier of the supplier
     * @return the supplier entity
     */
    DomainSupplierEntity findById(Long id);
}