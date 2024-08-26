package ai.shreds.domain;

import ai.shreds.shared.SharedRequestParams;
import ai.shreds.domain.DomainSupplierServiceException;
import ai.shreds.domain.SupplierNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DomainSupplierService implements DomainSupplierRepositoryPort {

    private final DomainSupplierRepositoryPort repository;

    public DomainSupplierService(DomainSupplierRepositoryPort repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DomainSupplierEntity> findAll(SharedRequestParams params) {
        if (params == null) {
            throw new IllegalArgumentException("Params cannot be null");
        }
        try {
            return repository.findAll(params);
        } catch (IllegalArgumentException e) {
            throw new DomainSupplierServiceException("Invalid parameters: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new DomainSupplierServiceException("Error retrieving suppliers: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public DomainSupplierEntity findById(Long id) {
        try {
            DomainSupplierEntity supplier = repository.findById(id);
            if (supplier == null) {
                throw new SupplierNotFoundException("Supplier not found with ID: " + id);
            }
            return supplier;
        } catch (SupplierNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainSupplierServiceException("Error retrieving supplier: " + e.getMessage(), e);
        }
    }
}