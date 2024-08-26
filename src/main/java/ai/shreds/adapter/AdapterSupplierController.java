package ai.shreds.adapter;

import ai.shreds.application.ApplicationGetAllSuppliersInputPort;
import ai.shreds.application.ApplicationGetSupplierByIdInputPort;
import ai.shreds.shared.SharedRequestParams;
import ai.shreds.shared.SharedSupplierDTO;
import ai.shreds.adapter.AdapterSupplierMapper;
import ai.shreds.adapter.AdapterSupplierResponseDTO;
import ai.shreds.domain.SupplierNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class AdapterSupplierController {

    private final ApplicationGetAllSuppliersInputPort getAllSuppliersInputPort;
    private final ApplicationGetSupplierByIdInputPort getSupplierByIdInputPort;
    private final AdapterSupplierMapper supplierMapper;

    @GetMapping
    public ResponseEntity<List<AdapterSupplierResponseDTO>> getAllSuppliers(AdapterSupplierRequestParams params) {
        SharedRequestParams sharedParams = new SharedRequestParams(params.getName(), params.getContact_info(), params.getAddress());
        List<SharedSupplierDTO> suppliers = getAllSuppliersInputPort.getAllSuppliers(sharedParams);
        List<AdapterSupplierResponseDTO> response = suppliers.stream()
                .map(supplierMapper::toAdapterSupplierResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdapterSupplierResponseDTO> getSupplierById(@PathVariable Integer id) {
        try {
            SharedSupplierDTO supplier = getSupplierByIdInputPort.getSupplierById(id.longValue());
            AdapterSupplierResponseDTO response = supplierMapper.toAdapterSupplierResponseDTO(supplier);
            return ResponseEntity.ok(response);
        } catch (SupplierNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<String> handleSupplierNotFoundException(SupplierNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
    }
}