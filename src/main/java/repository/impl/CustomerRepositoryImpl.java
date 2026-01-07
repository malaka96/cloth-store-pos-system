package repository.impl;

import db.DBConnector;
import repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public void add(String name, String phone, String email, String address, int isActive) throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "insert into customers (CustName, Phone, Email, Address, is_active) values(?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setObject(1,name);
            preparedStatement.setObject(2,phone);
            preparedStatement.setObject(3,email);
            preparedStatement.setObject(4,address);
            preparedStatement.setObject(5,isActive);
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void update(String name, String phone, String email, String address, int isActive) throws SQLException {

        String sql = "update customers set CustName = ?,Email = ?, Address = ?, is_active = ? where Phone = ?";
        Connection connection = DBConnector.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, name);
            preparedStatement.setObject(2, email);
            preparedStatement.setObject(3, address);
            preparedStatement.setObject(4, isActive);
            preparedStatement.setObject(5, phone);
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void delete(String phone) throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "delete from customers where Phone = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setObject(1,phone);
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public ResultSet getAll() throws SQLException {
        Connection connect = DBConnector.getInstance().getConnection();
        String sql = "select CustName, Phone, Email, Address, is_active from customers";
        return connect.prepareStatement(sql).executeQuery();
    }

    @Override
    public ResultSet searchCustomer(String phone) throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "select CustName, Phone, Email, Address, is_active from customers where Phone = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,phone);
        return preparedStatement.executeQuery();
    }

    @Override
    public int getCustomerCount() throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "select count(*) from customers";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        }
        return 0;
    }
}
