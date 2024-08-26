package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; 
 import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.core.env.Environment; 
 import javax.sql.DataSource; 
 import org.springframework.boot.jdbc.DataSourceBuilder; 
 import javax.persistence.EntityManagerFactory; 
 import java.util.HashMap; 
 import java.util.Map; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Configuration 
 public class InfrastructureEntityManagerFactory { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureEntityManagerFactory.class); 
  
     @Autowired 
     private Environment env; 
  
     /** 
      * Creates and configures the DataSource bean. 
      * @return DataSource 
      */ 
     @Bean 
     public DataSource dataSource() { 
         try { 
             return DataSourceBuilder.create() 
                     .driverClassName(env.getProperty("spring.datasource.driver-class-name")) 
                     .url(env.getProperty("spring.datasource.url")) 
                     .username(env.getProperty("spring.datasource.username")) 
                     .password(env.getProperty("spring.datasource.password")) 
                     .build(); 
         } catch (Exception e) { 
             logger.error("Failed to create DataSource", e); 
             throw new RuntimeException("Failed to create DataSource", e); 
         } 
     } 
  
     /** 
      * Creates and configures the EntityManagerFactory bean. 
      * @return EntityManagerFactory 
      */ 
     @Bean 
     public EntityManagerFactory entityManagerFactory() { 
         try { 
             LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean(); 
             em.setDataSource(dataSource()); 
             em.setPackagesToScan(new String[]{"ai.shreds.domain"}); 
  
             HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); 
             em.setJpaVendorAdapter(vendorAdapter); 
             em.setJpaPropertyMap(jpaProperties()); 
  
             em.afterPropertiesSet(); 
  
             return em.getObject(); 
         } catch (Exception e) { 
             logger.error("Failed to create EntityManagerFactory", e); 
             throw new RuntimeException("Failed to create EntityManagerFactory", e); 
         } 
     } 
  
     /** 
      * Sets JPA properties. 
      * @return Map<String, Object> 
      */ 
     private Map<String, Object> jpaProperties() { 
         Map<String, Object> properties = new HashMap<>(); 
         properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect")); 
         properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto")); 
         properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql")); 
         properties.put("hibernate.format_sql", env.getProperty("spring.jpa.format-sql")); 
         return properties; 
     } 
 } 
 