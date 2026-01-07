package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserRepository {
    ResultSet getUser(String email) throws SQLException;
}
