package service.imple;

import javafx.collections.ObservableList;
import model.dto.Product;
import repository.ProductRepository;
import repository.impl.ProductRepositoryImpl;
import service.ProductService;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductServiceImple implements ProductService {

    final ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public void add(String name, String category, Double price, int stockQty, int isActive, String barcode) throws SQLException {
        productRepository.add(name,category,price,stockQty,isActive, barcode);
    }

    @Override
    public void update(String name, String category, Double price, int stockQty, int isActive, String barcode) throws SQLException {
        productRepository.update(name,category,price,stockQty,isActive, barcode);
    }

    @Override
    public void delete(String name) throws SQLException {
        productRepository.delete(name);
    }

    @Override
    public ObservableList<Product> getAll() throws SQLException {
        ObservableList<Product> all = javafx.collections.FXCollections.observableArrayList();
        ResultSet resultSet = productRepository.getAll();
        while (resultSet.next()){
            all.add(new Product(resultSet.getString("ProductName"),
                    resultSet.getString("Category"),
                    resultSet.getDouble("Price"),
                    resultSet.getInt("StockQty"),
                    resultSet.getInt("is_active"),
                    resultSet.getString("Barcode")));
        }
        resultSet.close();
        return all;
    }

    @Override
    public Product searchProduct(String barcode) throws SQLException {
        ResultSet resultSet = productRepository.searchProduct(barcode);
        Product product = null;
        if (resultSet.next()){
            product = new Product(resultSet.getString("ProductName"),
                    resultSet.getString("Category"),
                    resultSet.getDouble("Price"),
                    resultSet.getInt("StockQty"),
                    resultSet.getInt("is_active"),
                    resultSet.getString("Barcode"));
        }
        resultSet.close();
        return product;
    }

    @Override
    public int getProductCount() throws SQLException {
        return productRepository.getProductCount();
    }
}
