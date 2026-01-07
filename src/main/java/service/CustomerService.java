package service;

import javafx.collections.ObservableList;
import model.dto.Customer;

import java.sql.SQLException;

public interface CustomerService {

    ObservableList<Customer> getAll() throws SQLException;
    void update(String name, String phone, String email, String address, int isActive) throws SQLException;
    void add(String name, String phone, String email, String address, int isActive) throws SQLException;
    void delete(String phone) throws SQLException;
    Customer searchCustomer(String phone) throws SQLException;
    int getCustomerCount() throws SQLException;

}
