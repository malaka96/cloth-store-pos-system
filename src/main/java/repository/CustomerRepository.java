package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerRepository {

    void add(String name, String phone, String email, String address, int isActive) throws SQLException;
    void update(String name, String phone, String email, String address, int isActive) throws SQLException;
    void delete(String phone) throws SQLException;
    ResultSet getAll() throws SQLException;

}
