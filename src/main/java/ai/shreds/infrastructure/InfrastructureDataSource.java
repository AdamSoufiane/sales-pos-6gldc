package ai.shreds.infrastructure; 
  
 import org.springframework.boot.jdbc.DataSourceBuilder; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import javax.sql.DataSource; 
  
 @Configuration 
 public class InfrastructureDataSource { 
  
     @Bean 
     public DataSource dataSource() { 
         return DataSourceBuilder.create() 
                 .url("jdbc:mysql://localhost:3306/supplierdb") 
                 .username("root") 
                 .password("password") 
                 .driverClassName("com.mysql.cj.jdbc.Driver") 
                 .build(); 
     } 
 } 
 