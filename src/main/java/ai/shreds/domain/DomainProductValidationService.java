package ai.shreds.domain;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import ai.shreds.infrastructure.InfrastructureProductServiceClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainProductValidationService implements DomainProductValidationPort {

    private static final Logger log = LoggerFactory.getLogger(DomainProductValidationService.class);
    private final InfrastructureProductServiceClient productServiceClient;

    /**
     * Validates the existence of products by calling the ProductService.
     * @param productIds List of product IDs to validate.
     * @return boolean indicating whether all products exist.
     */
    @Override
    public boolean validateProducts(List<String> productIds) {
        try {
            boolean result = productServiceClient.validateProducts(productIds);
            log.info("Product validation result: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error validating products: {}", e.getMessage());
            return false;
        }
    }
}