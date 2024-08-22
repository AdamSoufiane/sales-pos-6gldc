package ai.shreds.infrastructure;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;
import javax.annotation.PreDestroy;

@Configuration
@EnableTransactionManagement
public class InfrastructureLocalContainerEntityManagerFactoryBean {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureLocalContainerEntityManagerFactoryBean.class);

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        try {
            em.setDataSource(dataSource);
            em.setPackagesToScan(new String[]{"ai.shreds.domain"});

            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            em.setJpaProperties(jpaProperties());
        } catch (Exception e) {
            logger.error("Error configuring EntityManagerFactoryBean", e);
            throw new RuntimeException(e);
        }
        return em;
    }

    private Properties jpaProperties() {
        Properties props = new Properties();
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return props;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        EntityManagerFactory emf = entityManagerFactory().getObject();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @PreDestroy
    public void closeEntityManagerFactory() {
        // Closes the EntityManagerFactory to release resources
        try {
            entityManagerFactory().destroy();
        } catch (Exception e) {
            logger.error("Error closing EntityManagerFactoryBean", e);
        }
    }
}