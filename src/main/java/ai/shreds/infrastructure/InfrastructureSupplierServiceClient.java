package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainSupplierValidationPort; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.stereotype.Service; 
 import org.springframework.web.client.RestTemplate; 
 import org.springframework.web.client.HttpClientErrorException; 
 import org.springframework.retry.annotation.Backoff; 
 import org.springframework.retry.annotation.Retryable; 
 import org.springframework.retry.annotation.CircuitBreaker; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Service 
 public class InfrastructureSupplierServiceClient implements DomainSupplierValidationPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureSupplierServiceClient.class); 
     private final RestTemplate restTemplate; 
     private final String supplierServiceUrl; 
  
     public InfrastructureSupplierServiceClient(RestTemplate restTemplate, @Value("${supplier.service.url}") String supplierServiceUrl) { 
         this.restTemplate = restTemplate; 
         this.supplierServiceUrl = supplierServiceUrl; 
     } 
  
     @Override 
     @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000)) 
     @CircuitBreaker(maxAttempts = 3, resetTimeout = 10000) 
     public boolean validateSupplier(String supplierId) { 
         try { 
             String url = supplierServiceUrl + "/supplier/" + supplierId; 
             logger.info("Validating supplier with ID: {}", supplierId); 
             restTemplate.getForEntity(url, Void.class); 
             logger.info("Supplier with ID: {} is valid", supplierId); 
             return true; 
         } catch (HttpClientErrorException.NotFound e) { 
             logger.warn("Supplier with ID: {} not found", supplierId); 
             return false; 
         } catch (Exception e) { 
             logger.error("Error validating supplier with ID: {}", supplierId, e); 
             throw new RuntimeException("Error validating supplier", e); 
         } 
     } 
 } 
 