package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterKafkaProducer; 
 import ai.shreds.adapter.dto.*; 
 import ai.shreds.domain.*; 
 import ai.shreds.infrastructure.InfrastructureEntityMapper; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import lombok.extern.slf4j.Slf4j; 
 import lombok.RequiredArgsConstructor; 
  
 @Service 
 @Slf4j 
 @RequiredArgsConstructor 
 public class ApplicationPurchaseService implements ApplicationPurchaseServiceInputPort { 
  
     private final DomainSupplierValidationPort supplierValidationPort; 
     private final DomainProductValidationPort productValidationPort; 
     private final DomainPurchaseTransactionPort purchaseTransactionPort; 
     private final AdapterKafkaProducer kafkaProducer; 
     private final InfrastructureEntityMapper entityMapper; 
  
     @Override 
     public AdapterPurchaseResponseDTO processPurchaseTransaction(AdapterPurchaseRequestDTO request) { 
         try { 
             // Validate supplier 
             boolean isSupplierValid = supplierValidationPort.validateSupplier(request.getSupplierId()); 
             if (!isSupplierValid) { 
                 log.error("Invalid Supplier ID: {}", request.getSupplierId()); 
                 return new AdapterPurchaseResponseDTO(400, null, "Invalid Supplier ID"); 
             } 
  
             // Validate products 
             boolean areProductsValid = productValidationPort.validateProducts( 
                     request.getProducts().stream().map(AdapterProductDTO::getProductId).toList() 
             ); 
             if (!areProductsValid) { 
                 log.error("Invalid Product IDs: {}", request.getProducts().stream().map(AdapterProductDTO::getProductId).toList()); 
                 return new AdapterPurchaseResponseDTO(400, null, "Invalid Product IDs"); 
             } 
  
             // Map to domain entity 
             DomainPurchaseTransactionEntity domainTransaction = entityMapper.mapAdapterToDomain(request); 
  
             // Save transaction 
             purchaseTransactionPort.savePurchaseTransaction(domainTransaction); 
  
             // Send Kafka message asynchronously 
             kafkaProducer.sendMessageAsync("ProductAdded", domainTransaction.toString()); 
  
             // Prepare response 
             AdapterPurchaseDataDTO responseData = entityMapper.mapDomainToAdapter(domainTransaction); 
             return new AdapterPurchaseResponseDTO(200, responseData, null); 
         } catch (SpecificException e) { 
             log.error("Specific error occurred: ", e); 
             return new AdapterPurchaseResponseDTO(500, null, "Specific error occurred"); 
         } catch (Exception e) { 
             log.error("Internal Server Error: ", e); 
             return new AdapterPurchaseResponseDTO(500, null, "Internal Server Error"); 
         } 
     } 
 } 
 