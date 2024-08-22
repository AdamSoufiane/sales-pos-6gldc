package ai.shreds.adapter; 
  
 import ai.shreds.shared.AdapterCategoryDTO; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.stereotype.Component; 
 import org.springframework.web.client.RestClientException; 
 import org.springframework.web.client.RestTemplate; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import java.util.UUID; 
  
 @Component 
 public class AdapterCategoryClient { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryClient.class); 
     private final RestTemplate restTemplate; 
     private final String categoryServiceUrl; 
  
     public AdapterCategoryClient(RestTemplate restTemplate, @Value("${category.service.url}") String categoryServiceUrl) { 
         this.restTemplate = restTemplate; 
         this.categoryServiceUrl = categoryServiceUrl; 
     } 
  
     public AdapterCategoryDTO getCategoryById(UUID id) { 
         String url = String.format("%s/categories/%s", categoryServiceUrl, id.toString()); 
         try { 
             logger.info("Fetching category with ID: {} from URL: {}", id, url); 
             AdapterCategoryDTO response = restTemplate.getForObject(url, AdapterCategoryDTO.class); 
             logger.info("Received response: {}", response); 
             return response; 
         } catch (RestClientException e) { 
             logger.error("Error fetching category with ID: {} from URL: {}", id, url, e); 
             throw new RuntimeException("Failed to fetch category details", e); 
         } 
     } 
 } 
  
 package ai.shreds.shared; 
  
 import lombok.Data; 
 import java.util.UUID; 
 import java.sql.Timestamp; 
  
 @Data 
 public class AdapterCategoryDTO { 
     private UUID id; 
     private String name; 
     private String description; 
     private UUID category_id; 
     private Timestamp created_at; 
     private Timestamp updated_at; 
 } 
 