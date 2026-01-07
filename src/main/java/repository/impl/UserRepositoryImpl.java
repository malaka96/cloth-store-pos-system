package repository.impl;

import db.DBConnector;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public ResultSet getUser(String email, String password) throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "select email, password from users where email = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, email);
        preparedStatement.setObject(2, password);
        return preparedStatement.executeQuery();

    }
}
