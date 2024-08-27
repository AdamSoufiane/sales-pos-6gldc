package ai.shreds.domain;

import ai.shreds.infrastructure.InfrastructureSupplierServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class DomainSupplierValidationService implements DomainSupplierValidationPort {

    private static final Logger log = LoggerFactory.getLogger(DomainSupplierValidationService.class);
    private final InfrastructureSupplierServiceClient supplierServiceClient;

    @Autowired
    public DomainSupplierValidationService(InfrastructureSupplierServiceClient supplierServiceClient) {
        this.supplierServiceClient = supplierServiceClient;
    }

    @Override
    @CircuitBreaker(name = "supplierService", fallbackMethod = "fallbackValidateSupplier")
    public boolean validateSupplier(String supplierId) {
        try {
            log.info("Validating supplier with ID: {}", supplierId);
            boolean isValid = supplierServiceClient.validateSupplier(supplierId);
            log.info("Supplier validation result for ID {}: {}", supplierId, isValid);
            return isValid;
        } catch (Exception e) {
            log.error("Error validating supplier with ID: {}", supplierId, e);
            return false;
        }
    }

    /**
     * Fallback method invoked when the SupplierService is unavailable.
     * @param supplierId The ID of the supplier to validate.
     * @param t The throwable that caused the fallback.
     * @return false indicating the supplier could not be validated.
     */
    public boolean fallbackValidateSupplier(String supplierId, Throwable t) {
        log.warn("Fallback method invoked for supplier validation with ID: {}", supplierId, t);
        return false;
    }
}