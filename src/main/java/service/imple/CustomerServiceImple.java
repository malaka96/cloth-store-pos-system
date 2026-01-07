package service.imple;

import javafx.collections.ObservableList;
import model.dto.Customer;
import repository.CustomerRepository;
import repository.impl.CustomerRepositoryImpl;
import service.CustomerService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerServiceImple implements CustomerService {

    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public ObservableList<Customer> getAll() throws SQLException {
        ObservableList<Customer> all = javafx.collections.FXCollections.observableArrayList();
        ResultSet resultSet = customerRepository.getAll();
        while (resultSet.next()) {
            all.add(new Customer(resultSet.getString("CustName"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Address"),
                    resultSet.getInt("is_active")));
        }
        return all;
    }

    @Override
    public void update(String name, String phone, String email, String address, int isActive) throws SQLException {
        customerRepository.update(name, phone, email, address, isActive);
    }

    @Override
    public void add(String name, String phone, String email, String address, int isActive) throws SQLException {
        customerRepository.add(name, phone, email, address, isActive);
    }

    @Override
    public void delete(String phone) throws SQLException {
        customerRepository.delete(phone);
    }

    @Override
    public Customer searchCustomer(String phone) throws SQLException {
        ResultSet resultSet = customerRepository.searchCustomer(phone);
        Customer customer = null;
        if(resultSet.next()){
            customer = new Customer(resultSet.getString("CustName"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Address"),
                    resultSet.getInt("is_active"));
        }
        return customer;
    }

    @Override
    public int getCustomerCount() throws SQLException {
        return customerRepository.getCustomerCount();
    }

}
