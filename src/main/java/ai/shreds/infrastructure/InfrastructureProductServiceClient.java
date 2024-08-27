package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductValidationPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InfrastructureProductServiceClient implements DomainProductValidationPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureProductServiceClient.class);
    private final RestTemplate restTemplate;
    @Value("${external.product-service.url}")
    private String productServiceUrl;

    @Override
    public boolean validateProducts(List<String> productIds) {
        try {
            String url = productServiceUrl + "/products/validate";
            Boolean response = restTemplate.postForObject(url, productIds, Boolean.class);
            logger.debug("Product validation response: {}", response);
            return response != null && response;
        } catch (HttpStatusCodeException e) {
            logger.error("HTTP error while validating products: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            logger.error("Unexpected error while validating products: {}", e.getMessage());
            return false;
        }
    }
}