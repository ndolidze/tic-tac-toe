package ge.tsu.tictactoe;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {

  private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);

  public static Connection getConnection() {
    Properties props = new Properties();
    try (var inputStream = DatabaseConnection.class.getResourceAsStream(
      "/application.properties")) {
      props.load(inputStream);
    } catch (IOException e) {
      logger.error("Couldn't read application properties {}", e.getMessage());
    }

    Connection conn = null;
    try {
      conn = DriverManager.getConnection(
        props.getProperty("db.url"),
        props.getProperty("db.username"),
        props.getProperty("db.password")
      );
    } catch (SQLException e) {
      logger.error("Couldn't connect to database {}", e.getMessage());
    }
    return conn;
  }

}
