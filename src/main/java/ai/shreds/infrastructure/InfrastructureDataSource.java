package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.boot.jdbc.DataSourceBuilder; 
 import javax.sql.DataSource; 
 import lombok.extern.slf4j.Slf4j; 
  
 @Configuration 
 @Slf4j 
 public class InfrastructureDataSource { 
  
     @Bean 
     public DataSource dataSource() { 
         String url = System.getenv("DB_URL"); 
         String username = System.getenv("DB_USERNAME"); 
         String password = System.getenv("DB_PASSWORD"); 
  
         if (url == null || url.isEmpty()) { 
             log.error("Database URL is null or empty"); 
             throw new IllegalArgumentException("Database URL cannot be null or empty"); 
         } 
  
         if (username == null || username.isEmpty()) { 
             log.error("Database username is null or empty"); 
             throw new IllegalArgumentException("Database username cannot be null or empty"); 
         } 
  
         if (password == null || password.isEmpty()) { 
             log.error("Database password is null or empty"); 
             throw new IllegalArgumentException("Database password cannot be null or empty"); 
         } 
  
         try { 
             return DataSourceBuilder.create() 
                     .url(url) 
                     .username(username) 
                     .password(password) 
                     .driverClassName("org.postgresql.Driver") 
                     .build(); 
         } catch (Exception e) { 
             log.error("Failed to configure the data source", e); 
             throw new RuntimeException("Failed to configure the data source", e); 
         } 
     } 
 } 
 // Note: Ensure to include necessary configurations and properties in the application.properties file.