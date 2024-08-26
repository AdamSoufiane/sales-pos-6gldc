package ai.shreds.application;

import ai.shreds.domain.DomainSupplierEntity;
import ai.shreds.domain.DomainSupplierRepositoryPort;
import ai.shreds.shared.SharedRequestParams;
import ai.shreds.shared.SharedSupplierDTO;
import ai.shreds.adapter.AdapterSupplierMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import ai.shreds.domain.SupplierNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationSupplierService implements ApplicationGetAllSuppliersInputPort, ApplicationGetSupplierByIdInputPort {

    private final DomainSupplierRepositoryPort domainSupplierRepositoryPort;
    private final AdapterSupplierMapper adapterSupplierMapper;

    @Override
    public List<SharedSupplierDTO> getAllSuppliers(SharedRequestParams params) {
        List<DomainSupplierEntity> suppliers = domainSupplierRepositoryPort.findAll(params);
        return suppliers.stream()
                .map(adapterSupplierMapper::toSharedSupplierDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SharedSupplierDTO getSupplierById(Integer id) {
        DomainSupplierEntity supplier = domainSupplierRepositoryPort.findById(id.longValue());
        if (supplier == null) {
            throw new SupplierNotFoundException("Supplier not found.");
        }
        return adapterSupplierMapper.toDTO(supplier);
    }
}