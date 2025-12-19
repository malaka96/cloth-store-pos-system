package repository.impl;

import db.DBConnector;
import repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public void add(String name, String category, Double price, int stockQty, int isActive, String barcode) throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "Insert into products (ProductName, Category, Price, StockQty, is_active, Barcode) values (?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setObject(1,name);
            preparedStatement.setObject(2,category);
            preparedStatement.setObject(3,price);
            preparedStatement.setObject(4,stockQty);
            preparedStatement.setObject(5,isActive);
            preparedStatement.setObject(5,barcode);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(String name, String category, Double price, int stockQty, int isActive, String barcode) throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "Update products set ProductName = ?, Category = ?, Price = ?, StockQty = ?, is_active = ?, Barcode = ? where Barcode = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setObject(1,name);
            preparedStatement.setObject(2,category);
            preparedStatement.setObject(3,price);
            preparedStatement.setObject(4,stockQty);
            preparedStatement.setObject(5,isActive);
            preparedStatement.setObject(6,barcode);
            preparedStatement.setObject(7,barcode);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(String name) throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "Delete from products where ProductName = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setObject(1,name);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public ResultSet getAll() throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "Select ProductName, Category, Price, StockQty, is_active, Barcode from products";
        return connection.prepareStatement(sql).executeQuery();
    }

    @Override
    public ResultSet searchProduct(String barcode) throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "Select ProductName, Category, Price, StockQty, is_active, Barcode from products where Barcode = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, barcode);
        return  preparedStatement.executeQuery();
    }

}
