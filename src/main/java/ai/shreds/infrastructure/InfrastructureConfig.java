package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; 
 import org.springframework.data.jpa.repository.config.EnableJpaRepositories; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import org.springframework.transaction.annotation.EnableTransactionManagement; 
 import javax.sql.DataSource; 
 import org.springframework.boot.jdbc.DataSourceBuilder; 
 import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter; 
 import java.util.Properties; 
  
 @Configuration 
 @EnableTransactionManagement 
 @EnableJpaRepositories(basePackages = "ai.shreds.infrastructure") 
 public class InfrastructureConfig { 
  
     @Value("${spring.datasource.url}") 
     private String databaseUrl; 
  
     @Value("${spring.datasource.username}") 
     private String databaseUsername; 
  
     @Value("${spring.datasource.password}") 
     private String databasePassword; 
  
     @Value("${spring.jpa.properties.hibernate.dialect}") 
     private String hibernateDialect; 
  
     @Value("${spring.jpa.show-sql}") 
     private boolean showSql; 
  
     @Bean 
     public DataSource dataSource() { 
         return DataSourceBuilder.create() 
                 .url(databaseUrl) 
                 .username(databaseUsername) 
                 .password(databasePassword) 
                 .build(); 
     } 
  
     @Bean 
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
         LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean(); 
         em.setDataSource(dataSource()); 
         em.setPackagesToScan(new String[] { "ai.shreds.domain" }); 
  
         HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); 
         em.setJpaVendorAdapter(vendorAdapter); 
         em.setJpaProperties(hibernateProperties()); 
  
         return em; 
     } 
  
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         JpaTransactionManager transactionManager = new JpaTransactionManager(); 
         transactionManager.setEntityManagerFactory(entityManagerFactory().getObject()); 
         return transactionManager; 
     } 
  
     private Properties hibernateProperties() { 
         Properties properties = new Properties(); 
         properties.put("hibernate.dialect", hibernateDialect); 
         properties.put("hibernate.show_sql", showSql); 
         return properties; 
     } 
 } 
 