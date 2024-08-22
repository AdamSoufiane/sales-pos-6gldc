package ai.shreds.infrastructure; 
  
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.context.annotation.Primary; 
 import org.springframework.data.jpa.repository.config.EnableJpaRepositories; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; 
 import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import org.springframework.transaction.annotation.EnableTransactionManagement; 
  
 import javax.sql.DataSource; 
 import com.zaxxer.hikari.HikariConfig; 
 import com.zaxxer.hikari.HikariDataSource; 
 import java.util.Properties; 
  
 /** 
  * InfrastructureDatabaseConfig is responsible for configuring the database connection settings. 
  * It provides methods to configure the DataSource, EntityManagerFactory, and TransactionManager. 
  */ 
 @Configuration 
 @EnableTransactionManagement 
 @EnableJpaRepositories(basePackages = "ai.shreds.infrastructure") 
 public class InfrastructureDatabaseConfig { 
  
     @Value("${spring.datasource.url}") 
     private String dbUrl; 
  
     @Value("${spring.datasource.username}") 
     private String dbUsername; 
  
     @Value("${spring.datasource.password}") 
     private String dbPassword; 
  
     @Value("${spring.datasource.driver-class-name}") 
     private String dbDriverClassName; 
  
     @Value("${spring.jpa.properties.hibernate.dialect}") 
     private String hibernateDialect; 
  
     @Value("${spring.jpa.show-sql}") 
     private boolean showSql; 
  
     @Bean 
     @Primary 
     public DataSource dataSource() { 
         HikariConfig hikariConfig = new HikariConfig(); 
         hikariConfig.setJdbcUrl(dbUrl); 
         hikariConfig.setUsername(dbUsername); 
         hikariConfig.setPassword(dbPassword); 
         hikariConfig.setDriverClassName(dbDriverClassName); 
         return new HikariDataSource(hikariConfig); 
     } 
  
     @Bean 
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
         LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean(); 
         entityManagerFactoryBean.setDataSource(dataSource()); 
         entityManagerFactoryBean.setPackagesToScan("ai.shreds.domain"); 
         entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); 
         Properties jpaProperties = new Properties(); 
         jpaProperties.put("hibernate.dialect", hibernateDialect); 
         jpaProperties.put("hibernate.show_sql", showSql); 
         entityManagerFactoryBean.setJpaProperties(jpaProperties); 
         return entityManagerFactoryBean; 
     } 
  
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         JpaTransactionManager transactionManager = new JpaTransactionManager(); 
         transactionManager.setEntityManagerFactory(entityManagerFactory().getObject()); 
         return transactionManager; 
     } 
 } 
 