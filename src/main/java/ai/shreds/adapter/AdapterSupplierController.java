package ai.shreds.adapter;

import ai.shreds.application.ApplicationCreateSupplierInputPort;
import ai.shreds.application.ApplicationUpdateSupplierInputPort;
import ai.shreds.application.ApplicationDeleteSupplierInputPort;
import ai.shreds.shared.SharedSupplierDTO;
import ai.shreds.adapter.AdapterCreateSupplierRequest;
import ai.shreds.adapter.AdapterUpdateSupplierRequest;
import ai.shreds.adapter.AdapterSupplierResponse;
import ai.shreds.adapter.AdapterDeleteSupplierResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import org.springframework.lang.NonNull;

/**
 * Controller for managing supplier-related operations.
 */
@Slf4j
@RestController
@RequestMapping("/suppliers")
public class AdapterSupplierController {

    private final ApplicationCreateSupplierInputPort createSupplierService;
    private final ApplicationUpdateSupplierInputPort updateSupplierService;
    private final ApplicationDeleteSupplierInputPort deleteSupplierService;

    public AdapterSupplierController(@NonNull ApplicationCreateSupplierInputPort createSupplierService,
                                     @NonNull ApplicationUpdateSupplierInputPort updateSupplierService,
                                     @NonNull ApplicationDeleteSupplierInputPort deleteSupplierService) {
        this.createSupplierService = createSupplierService;
        this.updateSupplierService = updateSupplierService;
        this.deleteSupplierService = deleteSupplierService;
    }

    /**
     * Creates a new supplier.
     * @param request the request body containing supplier details
     * @return ResponseEntity with the created supplier details or an error message
     */
    @PostMapping
    public ResponseEntity<AdapterSupplierResponse> createSupplier(@Valid @RequestBody AdapterCreateSupplierRequest request) {
        log.info("Creating supplier with name: {}", request.getName());
        try {
            SharedSupplierDTO createdSupplier = createSupplierService.createSupplier(request);
            return new ResponseEntity<>(new AdapterSupplierResponse(HttpStatus.CREATED.value(), createdSupplier, null), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating supplier: ", e);
            return new ResponseEntity<>(new AdapterSupplierResponse(HttpStatus.BAD_REQUEST.value(), null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates an existing supplier.
     * @param id the ID of the supplier to update
     * @param request the request body containing updated supplier details
     * @return ResponseEntity with the updated supplier details or an error message
     */
    @PutMapping("/{id}")
    public ResponseEntity<AdapterSupplierResponse> updateSupplier(@PathVariable Long id, @Valid @RequestBody AdapterUpdateSupplierRequest request) {
        log.info("Updating supplier with id: {}", id);
        try {
            SharedSupplierDTO updatedSupplier = updateSupplierService.updateSupplier(id, request);
            return new ResponseEntity<>(new AdapterSupplierResponse(HttpStatus.OK.value(), updatedSupplier, null), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating supplier: ", e);
            return new ResponseEntity<>(new AdapterSupplierResponse(HttpStatus.BAD_REQUEST.value(), null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes an existing supplier.
     * @param id the ID of the supplier to delete
     * @return ResponseEntity with a success message or an error message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<AdapterDeleteSupplierResponse> deleteSupplier(@PathVariable Long id) {
        log.info("Deleting supplier with id: {}", id);
        try {
            deleteSupplierService.deleteSupplier(id);
            return new ResponseEntity<>(new AdapterDeleteSupplierResponse(HttpStatus.OK.value(), null, null), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting supplier: ", e);
            return new ResponseEntity<>(new AdapterDeleteSupplierResponse(HttpStatus.BAD_REQUEST.value(), null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}