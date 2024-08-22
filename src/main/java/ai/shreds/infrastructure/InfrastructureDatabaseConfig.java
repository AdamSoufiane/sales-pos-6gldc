package ai.shreds.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ai.shreds.infrastructure")
public class InfrastructureDatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureDatabaseConfig.class);

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Value("${spring.datasource.driver-class-name}")
    private String databaseDriverClassName;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${spring.jpa.show-sql}")
    private boolean showSql;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hibernateDdlAuto;

    @Bean
    public DataSource dataSource() {
        try {
            org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
            dataSource.setDriverClassName(databaseDriverClassName);
            dataSource.setUrl(databaseUrl);
            dataSource.setUsername(databaseUsername);
            dataSource.setPassword(databasePassword);
            logger.info("DataSource configured successfully.");
            return dataSource;
        } catch (Exception e) {
            logger.error("Failed to configure DataSource", e);
            throw new RuntimeException("Failed to configure DataSource", e);
        }
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        try {
            LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactory.setDataSource(dataSource());
            entityManagerFactory.setPackagesToScan("ai.shreds.domain");
            entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

            Properties jpaProperties = new Properties();
            jpaProperties.put("hibernate.dialect", hibernateDialect);
            jpaProperties.put("hibernate.show_sql", showSql);
            jpaProperties.put("hibernate.hbm2ddl.auto", hibernateDdlAuto);
            entityManagerFactory.setJpaProperties(jpaProperties);

            logger.info("EntityManagerFactory configured successfully.");
            return entityManagerFactory;
        } catch (Exception e) {
            logger.error("Failed to configure EntityManagerFactory", e);
            throw new RuntimeException("Failed to configure EntityManagerFactory", e);
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        logger.info("TransactionManager configured successfully.");
        return transactionManager;
    }
}