package ai.shreds.infrastructure;

import ai.shreds.domain.DomainSupplierEntity;
import ai.shreds.domain.DomainSupplierRepositoryPort;
import ai.shreds.shared.SharedRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import ai.shreds.domain.SupplierNotFoundException;

@Repository
public class InfrastructureSupplierRepositoryImpl implements DomainSupplierRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureSupplierRepositoryImpl.class);

    @Autowired
    private EntityManager entityManager;

    /**
     * Finds all suppliers based on the provided filtering criteria.
     * 
     * @param params The filtering criteria for retrieving suppliers.
     * @return A list of suppliers that match the filtering criteria.
     */
    @Override
    public List<DomainSupplierEntity> findAll(SharedRequestParams params) {
        logger.info("Finding all suppliers with params: {}", params);
        StringBuilder queryStr = new StringBuilder("SELECT s FROM DomainSupplierEntity s WHERE 1=1");
        if (params.getName() != null && !params.getName().isEmpty()) {
            queryStr.append(" AND s.name LIKE :name");
        }
        if (params.getContact_info() != null && !params.getContact_info().isEmpty()) {
            queryStr.append(" AND s.contact_info LIKE :contact_info");
        }
        if (params.getAddress() != null && !params.getAddress().isEmpty()) {
            queryStr.append(" AND s.address LIKE :address");
        }
        TypedQuery<DomainSupplierEntity> query = entityManager.createQuery(queryStr.toString(), DomainSupplierEntity.class);
        if (params.getName() != null && !params.getName().isEmpty()) {
            query.setParameter("name", "%" + params.getName() + "%");
        }
        if (params.getContact_info() != null && !params.getContact_info().isEmpty()) {
            query.setParameter("contact_info", "%" + params.getContact_info() + "%");
        }
        if (params.getAddress() != null && !params.getAddress().isEmpty()) {
            query.setParameter("address", "%" + params.getAddress() + "%");
        }
        List<DomainSupplierEntity> result = query.getResultList();
        logger.info("Found {} suppliers", result.size());
        return result;
    }

    /**
     * Finds a supplier by its unique ID.
     * 
     * @param id The unique identifier of the supplier.
     * @return The supplier entity with the specified ID.
     * @throws SupplierNotFoundException If no supplier is found with the specified ID.
     */
    @Override
    public DomainSupplierEntity findById(Long id) {
        logger.info("Finding supplier by ID: {}", id);
        return Optional.ofNullable(entityManager.find(DomainSupplierEntity.class, id))
                      .orElseThrow(() -> new SupplierNotFoundException("Supplier not found."));
    }
}