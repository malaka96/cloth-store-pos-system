package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductRepository {
    void add(String name, String category, Double price, int stockQty, int isActive, String barcode) throws SQLException;
    void update(String name, String category, Double price, int stockQty, int isActive, String barcode) throws SQLException;
    void delete(String name) throws SQLException;
    ResultSet getAll() throws SQLException;
    ResultSet searchProduct(String barcode) throws SQLException;
    int getProductCount() throws SQLException;
}
