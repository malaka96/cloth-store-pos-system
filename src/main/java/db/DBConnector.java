package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private Connection connection;
    private static DBConnector instance;

    private DBConnector() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clothify","root","1234");
    }

    public static DBConnector getInstance() throws SQLException {
        if(instance == null) instance = new DBConnector();
        return instance;
    }

    public Connection getConnection(){
        return instance.connection;
    }

}
