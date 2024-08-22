package ai.shreds.shared;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.datasource.DataSourceUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequiredArgsConstructor
@Slf4j
public class SharedInfrastructureDBConnection {

    private final DataSource dataSource;

    private Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(SharedInfrastructureDBConnection.class);

    public synchronized Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DataSourceUtils.getConnection(dataSource);
            }
        } catch (SQLException e) {
            logger.error("Failed to connect to the database", e);
        }
        return connection;
    }

    public synchronized void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        } catch (SQLException e) {
            logger.error("Failed to disconnect from the database", e);
        }
    }
}