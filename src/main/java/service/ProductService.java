package service;

import javafx.collections.ObservableList;
import model.dto.Product;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface ProductService {
    void add(String name, String category, Double price, int stockQty, int isActive) throws SQLException;
    void update(String name, String category, Double price, int stockQty, int isActive) throws SQLException;
    void delete(String name) throws SQLException;
    ObservableList<Product> getAll() throws SQLException;
}
