package com.ui.automation.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;

public class DatabaseUtil {
    private static Properties config;

    public static void setConfig(Properties properties) {
        config = properties;
    }

    private static String getDbUrl() {
        return config.getProperty("jdbc.url");
    }
    private static String getDbUser() {
        return config.getProperty("jdbc.user");
    }
    private static String getDbPassword() {
        return config.getProperty("jdbc.password");
    }

    /**
     * Polls the database until a specific transaction ID reaches the desired status.
     * Throws a timeout exception if the status is not reached within the specified duration.
     *
     * @param transactionId The ID of the transaction to check.
     * @param expectedStatus The status to wait for (e.g., 2 for success).
     * @param timeoutSeconds The maximum time to wait in seconds.
     */
    public static void waitForTransactionStatus(String transactionId, int expectedStatus, int timeoutSeconds) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + Duration.ofSeconds(timeoutSeconds).toMillis();
        boolean statusReached = false;

        while (System.currentTimeMillis() < endTime) {
            try (Connection conn = DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPassword())) {
                String sql = "SELECT status FROM transactions WHERE transaction_id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, transactionId);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int currentStatus = rs.getInt("status");
                    if (currentStatus == expectedStatus) {
                        statusReached = true;
                        break; // Exit the loop
                    }
                }
            } catch (SQLException e) {
                // Log the exception but continue polling
                System.err.println("Database query failed: " + e.getMessage());
            }

            // Wait for a short interval before polling again
            try {
                Thread.sleep(500); // 500ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (!statusReached) {
            throw new RuntimeException("Timeout: Transaction " + transactionId + " did not reach status " + expectedStatus + " within " + timeoutSeconds + " seconds.");
        }
    }
} 