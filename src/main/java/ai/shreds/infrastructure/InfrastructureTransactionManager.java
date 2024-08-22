package ai.shreds.infrastructure;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * InfrastructureTransactionManager is responsible for managing transactions in the infrastructure layer.
 * It provides a PlatformTransactionManager bean configured with the EntityManagerFactory.
 */
@Configuration
public class InfrastructureTransactionManager {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureTransactionManager.class);
    private final EntityManagerFactory entityManagerFactory;

    public InfrastructureTransactionManager(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Provides a PlatformTransactionManager bean for managing transactions.
     * @return a configured JpaTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        try {
            logger.info("Configuring transaction manager");
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory);
            return transactionManager;
        } catch (PersistenceException e) {
            logger.error("PersistenceException while configuring transaction manager", e);
            throw new RuntimeException("Failed to configure transaction manager due to persistence issue", e);
        } catch (Exception e) {
            logger.error("Exception while configuring transaction manager", e);
            throw new RuntimeException("Failed to configure transaction manager", e);
        }
    }
}