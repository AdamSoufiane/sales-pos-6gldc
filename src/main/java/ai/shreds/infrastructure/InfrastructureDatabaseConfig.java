package ai.shreds.infrastructure;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
@EnableTransactionManagement
public class InfrastructureDatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureDatabaseConfig.class);

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public MongoClient mongoClient() {
        try {
            logger.info("Creating MongoClient with URI: " + mongoUri);
            return MongoClients.create(mongoUri);
        } catch (Exception e) {
            logger.error("Failed to create MongoClient", e);
            throw e;
        }
    }

    @Bean
    public MongoDatabaseFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, "inventoryDB");
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager(MongoDatabaseFactory mongoDbFactory) {
        return new MongoTransactionManager(mongoDbFactory);
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }
}