package ai.shreds.application;

import ai.shreds.adapter.AdapterCreateSupplierRequest;
import ai.shreds.adapter.AdapterUpdateSupplierRequest;
import ai.shreds.adapter.AdapterDeleteSupplierResponse;
import ai.shreds.shared.SharedSupplierDTO;
import ai.shreds.domain.DomainSupplierEntity;
import ai.shreds.domain.DomainSupplierRepositoryPort;
import ai.shreds.domain.DomainSupplierEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationSupplierService implements ApplicationCreateSupplierInputPort, ApplicationUpdateSupplierInputPort, ApplicationDeleteSupplierInputPort {

    private final DomainSupplierRepositoryPort domainSupplierRepositoryPort;
    private final DomainSupplierEntityMapper domainSupplierEntityMapper;

    @Override
    @Transactional
    public SharedSupplierDTO createSupplier(AdapterCreateSupplierRequest request) {
        validateCreateRequest(request);
        DomainSupplierEntity supplierEntity = domainSupplierEntityMapper.toEntity(request);
        domainSupplierRepositoryPort.save(supplierEntity);
        log.info("Supplier created with id: {}", supplierEntity.getId());
        return domainSupplierEntityMapper.toDTO(supplierEntity);
    }

    @Override
    @Transactional
    public SharedSupplierDTO updateSupplier(Long id, AdapterUpdateSupplierRequest request) {
        validateUpdateRequest(request);
        Optional<DomainSupplierEntity> supplierEntity = domainSupplierRepositoryPort.findById(id);
        supplierEntity.ifPresentOrElse(
                existingSupplier -> {
                    existingSupplier.setName(request.getName() != null ? request.getName() : existingSupplier.getName());
                    existingSupplier.setContact_info(request.getContact_info() != null ? request.getContact_info() : existingSupplier.getContact_info());
                    existingSupplier.setAddress(request.getAddress() != null ? request.getAddress() : existingSupplier.getAddress());
                    domainSupplierRepositoryPort.save(existingSupplier);
                    log.info("Supplier updated with id: {}", id);
                },
                () -> {
                    throw new SupplierNotFoundException("Supplier not found");
                }
        );
        return supplierEntity.map(domainSupplierEntityMapper::toDTO).orElseThrow(() -> new SupplierNotFoundException("Supplier not found")); 
    }

    @Override
    @Transactional
    public AdapterDeleteSupplierResponse deleteSupplier(Long id) {
        if (!domainSupplierRepositoryPort.existsById(id)) {
            throw new SupplierNotFoundException("Supplier not found");
        }
        domainSupplierRepositoryPort.deleteById(id);
        log.info("Supplier deleted with id: {}", id);
        AdapterDeleteSupplierResponse response = new AdapterDeleteSupplierResponse();
        response.setStatus_code(200);
        response.setData(null);
        response.setError(null);
        return response;
    }

    private void validateCreateRequest(AdapterCreateSupplierRequest request) {
        validateSupplierRequest(request, true);
    }

    private void validateUpdateRequest(AdapterUpdateSupplierRequest request) {
        validateSupplierRequest(request, false);
    }

    private void validateSupplierRequest(Object request, boolean isCreate) {
        if (isCreate) {
            if (!StringUtils.hasText(((AdapterCreateSupplierRequest) request).getName())) {
                throw new IllegalArgumentException("Supplier name is required");
            }
            if (!StringUtils.hasText(((AdapterCreateSupplierRequest) request).getContact_info())) {
                throw new IllegalArgumentException("Contact information is required");
            }
            if (!StringUtils.hasText(((AdapterCreateSupplierRequest) request).getAddress())) {
                throw new IllegalArgumentException("Address is required");
            }
        } else {
            if (((AdapterUpdateSupplierRequest) request).getName() != null && ((AdapterUpdateSupplierRequest) request).getName().isEmpty()) {
                throw new IllegalArgumentException("Supplier name is invalid");
            }
            if (((AdapterUpdateSupplierRequest) request).getContact_info() != null && ((AdapterUpdateSupplierRequest) request).getContact_info().isEmpty()) {
                throw new IllegalArgumentException("Contact information is invalid");
            }
            if (((AdapterUpdateSupplierRequest) request).getAddress() != null && ((AdapterUpdateSupplierRequest) request).getAddress().isEmpty()) {
                throw new IllegalArgumentException("Address is invalid");
            }
        }
    }

    public static class SupplierNotFoundException extends RuntimeException {
        public SupplierNotFoundException(String message) {
            super(message);
        }
    }
}