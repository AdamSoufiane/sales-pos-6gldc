package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.jdbc.datasource.DriverManagerDataSource; 
 import javax.sql.DataSource; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Configuration 
 public class InfrastructureDataSourceConfig { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureDataSourceConfig.class); 
  
     @Value("${spring.datasource.url}") 
     private String url; 
  
     @Value("${spring.datasource.username}") 
     private String username; 
  
     @Value("${spring.datasource.password}") 
     private String password; 
  
     @Value("${spring.datasource.driver-class-name}") 
     private String driverClassName; 
  
     @Bean 
     public DataSource configure() { 
         try { 
             validateDataSourceProperties(); 
             logger.info("Configuring data source with URL: {}", url); 
             DriverManagerDataSource dataSource = new DriverManagerDataSource(); 
             dataSource.setDriverClassName(driverClassName); 
             dataSource.setUrl(url); 
             dataSource.setUsername(username); 
             dataSource.setPassword(password); 
             return dataSource; 
         } catch (Exception e) { 
             logger.error("Error configuring the data source", e); 
             throw new RuntimeException("Error configuring the data source", e); 
         } 
     } 
  
     private void validateDataSourceProperties() { 
         if (url == null || url.isEmpty()) { 
             throw new IllegalArgumentException("Data source URL must not be null or empty"); 
         } 
         if (username == null || username.isEmpty()) { 
             throw new IllegalArgumentException("Data source username must not be null or empty"); 
         } 
         if (password == null || password.isEmpty()) { 
             throw new IllegalArgumentException("Data source password must not be null or empty"); 
         } 
         if (driverClassName == null || driverClassName.isEmpty()) { 
             throw new IllegalArgumentException("Data source driver class name must not be null or empty"); 
         } 
     } 
 } 
 