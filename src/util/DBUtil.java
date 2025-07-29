package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DBUtil {
    private static final String CONFIG_FILE = "db_config.properties";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties props = new Properties();
                FileInputStream fis = new FileInputStream(CONFIG_FILE);
                props.load(fis);

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");

                connection = DriverManager.getConnection(url, user, password);
            } catch (IOException e) {
                System.err.println("Error loading DB config: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Error connecting to DB: " + e.getMessage());
            }
        }
        return connection;
    }
}
