package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.Customer;
import model.dto.Product;
import service.CustomerService;
import service.ProductService;
import service.imple.CustomerServiceImple;
import service.imple.ProductServiceImple;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button addCustomerBtn;

    @FXML
    private Button addProductBtn;

    @FXML
    private TextField customerAddressTf;

    @FXML
    private Button customerBtn;

    @FXML
    private TextField customerEmailTf;

    @FXML
    private CheckBox customerIsActive;

    @FXML
    private TextField customerNameTf;

    @FXML
    private ScrollPane customerPane;

    @FXML
    private TextField customerPhoneTf;

    @FXML
    private Button customerRemoveBtn;

    @FXML
    private TextField customerTotalSpentTf;

    @FXML
    private TextField productCategoryTf;

    @FXML
    private CheckBox productIsActive;

    @FXML
    private TextField productNameTf;

    @FXML
    private TextField productPriceTf;

    @FXML
    private TextField productStockTf;

    @FXML
    private Button customerUpdateBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button deleteProductBtn;

    @FXML
    private Button productBtn;

    @FXML
    private Button updateProductBtn;

    @FXML
    private ScrollPane productPane;

    @FXML
    private TableColumn<?, ?> tcIsActive;

    @FXML
    private TableColumn<?, ?> tcName;

    @FXML
    private TableColumn<?, ?> tcPhone;

    @FXML
    private TableColumn<?, ?> tpCategory;

    @FXML
    private TableColumn<?, ?> tpName;

    @FXML
    private TableColumn<?, ?> tpPrice;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableView<Product> productTable;

    final CustomerService customerService = new CustomerServiceImple();
    final ProductService productService = new ProductServiceImple();
    ObservableList<Customer> allCustomers;

    @FXML
    void addCustomerBtnAction(ActionEvent event) {
        try {
            customerService.add(customerNameTf.getText(), customerPhoneTf.getText(),
                    customerEmailTf.getText(), customerAddressTf.getText(),
                    customerIsActive.isSelected() ? 1 : 0);
            loadAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void customerRemoveBtnAction(ActionEvent event) {
        try {
            customerService.delete(customerPhoneTf.getText());
            loadAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void customerUpdateBtnAction(ActionEvent event) {
        try{
            customerService.update(customerNameTf.getText(), customerPhoneTf.getText(),
                    customerEmailTf.getText(), customerAddressTf.getText(),
                    customerIsActive.isSelected() ? 1 : 0);
            loadAllCustomers();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void addProductBtnAction(ActionEvent actionEvent) {
        try{
            productService.add(productNameTf.getText(),
                    productCategoryTf.getText(),
                    Double.parseDouble(productPriceTf.getText()),
                    Integer.parseInt(productStockTf.getText()),
                    productIsActive.isSelected() ? 1 : 0);
            loadAllProducts();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateProductBtnAction(ActionEvent actionEvent) {
        try{
            productService.update(productNameTf.getText(),
                    productCategoryTf.getText(),
                    Double.parseDouble(productPriceTf.getText()),
                    Integer.parseInt(productStockTf.getText()),
                    productIsActive.isSelected() ? 1 : 0);
            loadAllProducts();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteProductBtnAction(ActionEvent actionEvent) {
        try{
            productService.delete(productNameTf.getText());
            loadAllProducts();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void customerBtnAction(ActionEvent event) {
        productPane.setVisible(false);
        customerPane.setVisible(true);
    }

    @FXML
    void productBtnAction(ActionEvent event) {
        customerPane.setVisible(false);
        productPane.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tcIsActive.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        loadAllCustomers();

        tpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tpCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tpPrice.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        loadAllProducts();


        customerTable.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, newValue) -> {
            if(newValue != null){
                setSelectedCustomer(newValue);
            }
        });

        productTable.getSelectionModel().selectedItemProperty().addListener((observableValue, product, newValue) -> {
            if(newValue != null){
                setSelectedProduct(newValue);
            }
        });
    }

    public void loadAllCustomers(){
        try {
            customerTable.setItems(customerService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAllProducts(){
        try{
            productTable.setItems(productService.getAll());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void setSelectedCustomer(Customer customer){
        customerNameTf.setText(customer.getName());
        customerPhoneTf.setText(customer.getPhone());
        customerEmailTf.setText(customer.getEmail());
        customerAddressTf.setText(customer.getAddress());
        customerIsActive.setSelected(customer.getIsActive() == 1);
    }

    public void setSelectedProduct(Product product){
        productNameTf.setText(product.getName());
        productCategoryTf.setText(product.getCategory());
        productPriceTf.setText(String.valueOf(product.getPrice()));
        productStockTf.setText(String.valueOf(product.getStockQty()));
        productIsActive.setSelected(product.getIsActive() == 1);
    }


}
