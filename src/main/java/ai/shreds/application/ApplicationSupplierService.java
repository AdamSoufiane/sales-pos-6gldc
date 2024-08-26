package ai.shreds.application;

import ai.shreds.adapter.AdapterCreateSupplierRequest;
import ai.shreds.adapter.AdapterUpdateSupplierRequest;
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
        SharedSupplierDTO sharedSupplierDTO = new SharedSupplierDTO(null, request.getName(), request.getContact_info(), request.getAddress(), request.getCompany_name(), request.getDue_date(), null, null);
        DomainSupplierEntity supplierEntity = domainSupplierEntityMapper.toEntity(sharedSupplierDTO);
        domainSupplierRepositoryPort.save(supplierEntity);
        log.info("Supplier created with id: {}", supplierEntity.getId());
        return domainSupplierEntityMapper.toDTO(supplierEntity);
    }

    @Override
    @Transactional
    public SharedSupplierDTO updateSupplier(Long id, AdapterUpdateSupplierRequest request) {
        validateUpdateRequest(request);
        DomainSupplierEntity supplierEntity = domainSupplierRepositoryPort.findById(id);
        if (supplierEntity != null) {
            supplierEntity.setName(request.getName() != null ? request.getName() : supplierEntity.getName());
            supplierEntity.setContact_info(request.getContact_info() != null ? request.getContact_info() : supplierEntity.getContact_info());
            supplierEntity.setAddress(request.getAddress() != null ? request.getAddress() : supplierEntity.getAddress());
            supplierEntity.setCompany_name(request.getCompany_name() != null ? request.getCompany_name() : supplierEntity.getCompany_name());
            supplierEntity.setDue_date(request.getDue_date() != null ? request.getDue_date() : supplierEntity.getDue_date());
            domainSupplierRepositoryPort.save(supplierEntity);
            log.info("Supplier updated with id: {}", id);
            return domainSupplierEntityMapper.toDTO(supplierEntity);
        } else {
            throw new SupplierNotFoundException("Supplier not found");
        }
    }

    @Override
    @Transactional
    public void deleteSupplier(Long id) {
        if (!domainSupplierRepositoryPort.existsById(id)) {
            throw new SupplierNotFoundException("Supplier not found");
        }
        domainSupplierRepositoryPort.deleteById(id);
        log.info("Supplier deleted with id: {}", id);
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
            if (!StringUtils.hasText(((AdapterCreateSupplierRequest) request).getCompany_name())) {
                throw new IllegalArgumentException("Company name is required");
            }
            if (((AdapterCreateSupplierRequest) request).getDue_date() == null) {
                throw new IllegalArgumentException("Due date is required");
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
            if (((AdapterUpdateSupplierRequest) request).getCompany_name() != null && ((AdapterUpdateSupplierRequest) request).getCompany_name().isEmpty()) {
                throw new IllegalArgumentException("Company name is invalid");
            }
            if (((AdapterUpdateSupplierRequest) request).getDue_date() == null) {
                throw new IllegalArgumentException("Due date is invalid");
            }
        }
    }

    public static class SupplierNotFoundException extends RuntimeException {
        public SupplierNotFoundException(String message) {
            super(message);
        }
    }
}