package ai.shreds.infrastructure;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ai.shreds.infrastructure")
public class InfrastructureDatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureDatabaseConfig.class);

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Value("${spring.datasource.driver-class-name}")
    private String dataSourceDriverClassName;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        try {
            dataSource.setUrl(dataSourceUrl);
            dataSource.setUsername(dataSourceUsername);
            dataSource.setPassword(dataSourcePassword);
            dataSource.setDriverClassName(dataSourceDriverClassName);
            dataSource.setInitialSize(5);
            dataSource.setMaxTotal(10);
        } catch (Exception e) {
            logger.error("Error configuring the DataSource", e);
        }
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("ai.shreds.domain");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}