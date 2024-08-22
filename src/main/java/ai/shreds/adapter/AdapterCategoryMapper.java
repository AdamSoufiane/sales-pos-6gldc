package ai.shreds.adapter; 
  
 import ai.shreds.shared.AdapterCategoryResponseDTO; 
 import ai.shreds.domain.DomainCategoryEntity; 
 import org.springframework.stereotype.Component; 
  
 /** 
  * Mapper class to convert between DomainCategoryEntity and AdapterCategoryResponseDTO. 
  */ 
 @Component 
 public class AdapterCategoryMapper { 
  
     /** 
      * Converts a DomainCategoryEntity to an AdapterCategoryResponseDTO. 
      *  
      * @param entity the domain entity to convert 
      * @return the corresponding AdapterCategoryResponseDTO 
      */ 
     public AdapterCategoryResponseDTO toResponseDTO(DomainCategoryEntity entity) { 
         if (entity == null) { 
             return null; 
         } 
         return AdapterCategoryResponseDTO.builder() 
                 .id(entity.getId()) 
                 .name(entity.getName()) 
                 .description(entity.getDescription()) 
                 .categoryId(entity.getCategoryId()) 
                 .createdAt(entity.getCreatedAt()) 
                 .updatedAt(entity.getUpdatedAt()) 
                 .build(); 
     } 
  
     /** 
      * Converts an AdapterCategoryResponseDTO to a DomainCategoryEntity. 
      *  
      * @param dto the DTO to convert 
      * @return the corresponding DomainCategoryEntity 
      */ 
     public DomainCategoryEntity toEntity(AdapterCategoryResponseDTO dto) { 
         if (dto == null) { 
             return null; 
         } 
         return DomainCategoryEntity.builder() 
                 .id(dto.getId()) 
                 .name(dto.getName()) 
                 .description(dto.getDescription()) 
                 .categoryId(dto.getCategoryId()) 
                 .createdAt(dto.getCreatedAt()) 
                 .updatedAt(dto.getUpdatedAt()) 
                 .build(); 
     } 
 } 
 