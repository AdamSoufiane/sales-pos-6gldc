package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import javax.persistence.EntityManagerFactory; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 /** 
  * Configuration class for setting up the PlatformTransactionManager. 
  */ 
 @Configuration 
 public class InfrastructurePlatformTransactionManager { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructurePlatformTransactionManager.class); 
  
     private final EntityManagerFactory entityManagerFactory; 
  
     public InfrastructurePlatformTransactionManager(EntityManagerFactory entityManagerFactory) { 
         this.entityManagerFactory = entityManagerFactory; 
     } 
  
     /** 
      * Creates and configures a PlatformTransactionManager bean. 
      *  
      * @return the configured PlatformTransactionManager 
      */ 
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         logger.info("Configuring PlatformTransactionManager"); 
         JpaTransactionManager transactionManager = new JpaTransactionManager(); 
         transactionManager.setEntityManagerFactory(entityManagerFactory); 
         return transactionManager; 
     } 
 } 
 