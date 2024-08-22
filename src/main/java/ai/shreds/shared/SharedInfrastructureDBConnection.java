package ai.shreds.shared; 
  
 import java.sql.Connection; 
 import java.sql.SQLException; 
 import javax.sql.DataSource; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Component; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
  
 @Component 
 @RequiredArgsConstructor 
 @Slf4j 
 public class SharedInfrastructureDBConnection { 
  
     private final DataSource dataSource; 
     private Connection connection; 
  
     public synchronized Connection connect() { 
         try { 
             if (connection == null || connection.isClosed()) { 
                 connection = dataSource.getConnection(); 
             } 
         } catch (SQLException e) { 
             log.error("Failed to connect to the database", e); 
         } 
         return connection; 
     } 
  
     public synchronized void disconnect() { 
         try { 
             if (connection != null && !connection.isClosed()) { 
                 connection.close(); 
             } 
         } catch (SQLException e) { 
             log.error("Failed to disconnect from the database", e); 
         } 
     } 
 } 
 