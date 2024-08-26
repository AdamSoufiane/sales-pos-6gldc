package ai.shreds.domain; 
  
 import ai.shreds.adapter.AdapterProductAddedRequestParams; 
 import ai.shreds.adapter.AdapterProductAddedResponseDTO; 
 import org.mapstruct.Mapper; 
 import org.mapstruct.Mapping; 
 import org.mapstruct.factory.Mappers; 
  
 @Mapper 
 public interface DomainInventoryEntityMapper { 
  
     DomainInventoryEntityMapper INSTANCE = Mappers.getMapper(DomainInventoryEntityMapper.class); 
  
     /** 
      * Maps AdapterProductAddedRequestParams to DomainInventoryEntity. 
      * @param params AdapterProductAddedRequestParams containing product details. 
      * @return DomainInventoryEntity with mapped fields. 
      */ 
     @Mapping(source = "productId", target = "productId") 
     @Mapping(source = "initialQuantity", target = "initialQuantity") 
     @Mapping(source = "creationTime", target = "creationTime") 
     @Mapping(source = "alertQuantity", target = "alertQuantity") 
     @Mapping(source = "warehouseLocation", target = "warehouseLocation") 
     DomainInventoryEntity mapToDomainEntity(AdapterProductAddedRequestParams params); 
  
     /** 
      * Maps a domain response to AdapterProductAddedResponseDTO. 
      * @param domainResponse The domain response message. 
      * @return AdapterProductAddedResponseDTO with the response message. 
      */ 
     @Mapping(source = "message", target = "message") 
     AdapterProductAddedResponseDTO mapToAdapterResponse(String domainResponse); 
  
     /** 
      * Safely maps AdapterProductAddedRequestParams to DomainInventoryEntity with validation. 
      * @param params AdapterProductAddedRequestParams containing product details. 
      * @return DomainInventoryEntity with mapped fields. 
      */ 
     default DomainInventoryEntity safeMapToDomainEntity(AdapterProductAddedRequestParams params) { 
         if (params == null || params.getProductId() == null || params.getInitialQuantity() <= 0 || params.getAlertQuantity() > params.getInitialQuantity() || params.getWarehouseLocation() == null) { 
             throw new IllegalArgumentException("Invalid ProductAdded event data"); 
         } 
         return mapToDomainEntity(params); 
     } 
 } 
  
 // Implementation Note: Use Lombok annotations for getter and setter methods. 
 // Implementation Note: Ensure proper exception handling if mapping fails. 
 