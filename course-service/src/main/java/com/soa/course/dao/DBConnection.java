
package com.soa.course.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
    private static Connection connection;

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties props = new Properties();
            try (InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
                if (in == null) throw new RuntimeException("application.properties not found");
                props.load(in);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load application.properties", e);
            }
            String driver = props.getProperty("db.driver");
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String pass = props.getProperty("db.password");
            try { Class.forName(driver); } catch (ClassNotFoundException e) { throw new RuntimeException("Driver not found:"+driver, e);}            
            connection = DriverManager.getConnection(url, user, pass);
            initSchema(connection);
        }
        return connection;
    }

    private static void initSchema(Connection conn) {
        String ddl = """
            CREATE TABLE IF NOT EXISTS courses (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                teacher VARCHAR(255),
                credits INT NOT NULL
            );
        """;
        try (Statement st = conn.createStatement()) { st.execute(ddl); }
        catch (SQLException e) { throw new RuntimeException("Failed to init schema", e); }
    }
}
