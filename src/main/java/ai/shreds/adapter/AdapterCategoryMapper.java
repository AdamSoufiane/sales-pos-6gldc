package ai.shreds.adapter; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.shared.AdapterCategoryCreateRequest; 
 import ai.shreds.shared.AdapterCategoryUpdateRequest; 
 import ai.shreds.shared.AdapterCategoryResponse; 
 import org.mapstruct.Mapper; 
 import org.mapstruct.Mapping; 
 import org.mapstruct.Mappings; 
 import org.mapstruct.factory.Mappers; 
  
 /** 
  * Mapper interface for converting between adapter requests and domain entities,  
  * as well as converting domain entities to adapter responses. 
  */ 
 @Mapper(componentModel = "spring") 
 public interface AdapterCategoryMapper { 
  
     AdapterCategoryMapper INSTANCE = Mappers.getMapper(AdapterCategoryMapper.class); 
  
     /** 
      * Converts an AdapterCategoryCreateRequest to a DomainCategoryEntity. 
      * @param request the create request 
      * @return the domain category entity 
      */ 
     @Mappings({ 
         @Mapping(target = "id", ignore = true), 
         @Mapping(target = "created_at", ignore = true), 
         @Mapping(target = "updated_at", ignore = true) 
     }) 
     DomainCategoryEntity toDomain(AdapterCategoryCreateRequest request); 
  
     /** 
      * Converts an AdapterCategoryUpdateRequest to a DomainCategoryEntity. 
      * @param request the update request 
      * @return the domain category entity 
      */ 
     @Mappings({ 
         @Mapping(target = "id", ignore = true), 
         @Mapping(target = "created_at", ignore = true), 
         @Mapping(target = "updated_at", ignore = true) 
     }) 
     DomainCategoryEntity toDomain(AdapterCategoryUpdateRequest request); 
  
     /** 
      * Converts a DomainCategoryEntity to an AdapterCategoryResponse. 
      * @param domain the domain category entity 
      * @return the adapter category response 
      */ 
     @Mappings({ 
         @Mapping(source = "id", target = "id"), 
         @Mapping(source = "name", target = "name"), 
         @Mapping(source = "description", target = "description"), 
         @Mapping(source = "category_id", target = "category_id"), 
         @Mapping(source = "created_at", target = "created_at"), 
         @Mapping(source = "updated_at", target = "updated_at") 
     }) 
     AdapterCategoryResponse toResponse(DomainCategoryEntity domain); 
  
     /** 
      * Converts a DomainCategoryEntity to an AdapterCategoryCreateRequest. 
      * @param domain the domain category entity 
      * @return the create request 
      */ 
     @Mappings({ 
         @Mapping(source = "id", target = "id"), 
         @Mapping(source = "name", target = "name"), 
         @Mapping(source = "description", target = "description"), 
         @Mapping(source = "category_id", target = "category_id"), 
         @Mapping(source = "created_at", target = "created_at"), 
         @Mapping(source = "updated_at", target = "updated_at") 
     }) 
     AdapterCategoryCreateRequest toCreateRequest(DomainCategoryEntity domain); 
  
     /** 
      * Converts a DomainCategoryEntity to an AdapterCategoryUpdateRequest. 
      * @param domain the domain category entity 
      * @return the update request 
      */ 
     @Mappings({ 
         @Mapping(source = "id", target = "id"), 
         @Mapping(source = "name", target = "name"), 
         @Mapping(source = "description", target = "description"), 
         @Mapping(source = "category_id", target = "category_id"), 
         @Mapping(source = "created_at", target = "created_at"), 
         @Mapping(source = "updated_at", target = "updated_at") 
     }) 
     AdapterCategoryUpdateRequest toUpdateRequest(DomainCategoryEntity domain); 
 } 
 