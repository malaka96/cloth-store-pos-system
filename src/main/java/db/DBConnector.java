package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DBConnector {

    private static final Dotenv dotenv = Dotenv.load(); // dotenv reference

    private Connection connection;
    private static DBConnector instance;

    private DBConnector() throws SQLException {
        connection = DriverManager.getConnection(dotenv.get("DB_URL"),dotenv.get("DB_USER"),dotenv.get("DB_PASSWORD"));
    }

    public static DBConnector getInstance() throws SQLException {
        if(instance == null) instance = new DBConnector();
        return instance;
    }

    public Connection getConnection(){
        return instance.connection;
    }

}
