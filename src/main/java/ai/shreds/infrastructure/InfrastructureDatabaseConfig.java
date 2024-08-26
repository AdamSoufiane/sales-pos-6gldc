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
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.constraints.NotEmpty;

@Configuration
@EnableJpaRepositories(basePackages = "ai.shreds.infrastructure")
@EnableTransactionManagement
public class InfrastructureDatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureDatabaseConfig.class);

    @Value("${spring.datasource.url}")
    @NotEmpty(message = "Database URL must not be empty")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    @NotEmpty(message = "Database username must not be empty")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    @NotEmpty(message = "Database password must not be empty")
    private String dbPassword;

    @Value("${spring.datasource.driver-class-name}")
    @NotEmpty(message = "Database driver class name must not be empty")
    private String dbDriverClassName;

    @Bean
    public DataSource dataSource() {
        logger.info("Configuring DataSource with URL: {}", dbUrl);
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setDriverClassName(dbDriverClassName);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        logger.info("Configuring EntityManagerFactory");
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "ai.shreds.domain" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        logger.info("Configuring TransactionManager");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    private java.util.Properties additionalProperties() {
        java.util.Properties properties = new java.util.Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        return properties;
    }
}