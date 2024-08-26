package ai.shreds.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Manages the database connection lifecycle.
 */
@Slf4j
@Component
public class InfrastructureDBConnection {

    private final DataSource dataSource;
    private Connection connection;

    @Autowired
    public InfrastructureDBConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Establishes a connection to the database.
     *
     * @return Connection object
     * @throws SQLException if a database access error occurs
     */
    public Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = dataSource.getConnection();
                log.info("Database connection established.");
            } catch (SQLException e) {
                log.error("Failed to establish database connection.", e);
                throw e;
            }
        }
        return connection;
    }

    /**
     * Closes the database connection if it is active.
     */
    public void disconnect() {
        if (isConnectionActive()) {
            try {
                connection.close();
                log.info("Database connection closed.");
            } catch (SQLException e) {
                log.error("Failed to close database connection.", e);
                // Optionally re-throw the exception depending on use case
            } finally {
                connection = null;
            }
        }
    }

    /**
     * Checks if the database connection is active.
     *
     * @return true if the connection is active, false otherwise
     */
    private boolean isConnectionActive() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            log.error("Error checking database connection status.", e);
            return false;
        }
    }
}