package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.boot.jdbc.DataSourceBuilder; 
 import org.springframework.web.client.RestTemplate; 
 import javax.sql.DataSource; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Configuration 
 public class InfrastructureConfig { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureConfig.class); 
  
     @Value("${spring.datasource.url}") 
     private String dataSourceUrl; 
  
     @Value("${spring.datasource.username}") 
     private String dataSourceUsername; 
  
     @Value("${spring.datasource.password}") 
     private String dataSourcePassword; 
  
     @Bean 
     public DataSource dataSource() { 
         if (dataSourceUrl == null || dataSourceUrl.isEmpty() || 
             dataSourceUsername == null || dataSourceUsername.isEmpty() || 
             dataSourcePassword == null || dataSourcePassword.isEmpty()) { 
             throw new IllegalArgumentException("Database configuration properties cannot be null or empty"); 
         } 
         try { 
             return DataSourceBuilder.create() 
                     .url(dataSourceUrl) 
                     .username(dataSourceUsername) 
                     .password(dataSourcePassword) 
                     .build(); 
         } catch (Exception e) { 
             logger.error("Error configuring the DataSource", e); 
             throw e; 
         } 
     } 
  
     @Bean 
     public RestTemplate restTemplate() { 
         return new RestTemplate(); 
     } 
  
     @Bean 
     public ExternalServiceConfig externalServiceConfig() { 
         return new ExternalServiceConfig(); 
     } 
  
     public static class ExternalServiceConfig { 
  
         @Value("${supplier.service.url}") 
         private String supplierServiceUrl; 
  
         @Value("${product.service.url}") 
         private String productServiceUrl; 
  
         public String getSupplierServiceUrl() { 
             return supplierServiceUrl; 
         } 
  
         public String getProductServiceUrl() { 
             return productServiceUrl; 
         } 
  
         /** 
          * This class holds the configuration URLs for external services such as 
          * SupplierService and ProductService. These URLs are injected from the 
          * application properties. 
          */ 
     } 
 }