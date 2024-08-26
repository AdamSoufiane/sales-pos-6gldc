package ai.shreds.domain.mapper; 
  
 import ai.shreds.adapter.dto.AdapterProductAddedRequestParams; 
 import ai.shreds.adapter.dto.AdapterProductAddedResponseDTO; 
 import ai.shreds.domain.entity.DomainProductAddedEvent; 
 import ai.shreds.domain.entity.DomainProductAddedEventResponse; 
 import org.mapstruct.Mapper; 
 import org.mapstruct.Mapping; 
 import org.mapstruct.Mappings; 
 import org.mapstruct.factory.Mappers; 
  
 /** 
  * Mapper interface for converting between AdapterProductAddedRequestParams and DomainProductAddedEvent, 
  * and between DomainProductAddedEventResponse and AdapterProductAddedResponseDTO. 
  */ 
 @Mapper 
 public interface DomainProductAddedEventMapper { 
  
     DomainProductAddedEventMapper INSTANCE = Mappers.getMapper(DomainProductAddedEventMapper.class); 
  
     /** 
      * Maps AdapterProductAddedRequestParams to DomainProductAddedEvent. 
      * 
      * @param params the request parameters 
      * @return the domain event 
      */ 
     @Mappings({ 
             @Mapping(source = "productId", target = "productId"), 
             @Mapping(source = "initialQuantity", target = "initialQuantity"), 
             @Mapping(source = "creationTime", target = "creationTime"), 
             @Mapping(source = "alertQuantity", target = "alertQuantity"), 
             @Mapping(source = "warehouseLocation", target = "warehouseLocation") 
     }) 
     DomainProductAddedEvent mapToDomainEvent(AdapterProductAddedRequestParams params); 
  
     /** 
      * Maps DomainProductAddedEventResponse to AdapterProductAddedResponseDTO. 
      * 
      * @param response the domain response 
      * @return the adapter response DTO 
      */ 
     @Mappings({ 
             @Mapping(source = "message", target = "message") 
     }) 
     AdapterProductAddedResponseDTO mapToAdapterResponse(DomainProductAddedEventResponse response); 
 } 
 