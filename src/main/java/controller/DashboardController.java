package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.dto.BillingProduct;
import model.dto.Customer;
import model.dto.Product;
import org.w3c.dom.Text;
import service.CustomerService;
import service.ProductService;
import service.UserService;
import service.imple.CustomerServiceImple;
import service.imple.ProductServiceImple;
import service.imple.UserServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button addCustomerBtn;

    @FXML
    private Button addProductBtn;

    @FXML
    private Button billingBtn;

    @FXML
    private Button billingProductAddBtn;


    @FXML
    private Button billingProductRemoveBtn;


    @FXML
    private TextField billingSubTotalTf;

    @FXML
    private ScrollPane billingPane;

    @FXML
    private TextField billingBuyStockTf;

    @FXML
    private Button billingBarcodeEnterBtn;

    @FXML
    private TextField billingBarcodeTf;

    @FXML
    private TextField billingProductNameTf;

    @FXML
    private TextField billingStockTf;

    @FXML
    private TextField billingFinalTotalTf;

    @FXML
    private TextField billingDiscountTf;

    @FXML
    private TextField billingProductUnitPrice;

    @FXML
    private TextField billingCusNameTf;

    @FXML
    private TextField billingCusPhoneTf;

    @FXML
    private Button billingCusSearchBtn;

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
    private Button dashboardBtn;

    @FXML
    private ScrollPane dashboardPane;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField loginEmailTf;

    @FXML
    private Text loginFP;

    @FXML
    private PasswordField loginPasswordTf;

    @FXML
    private TextField productBarcodeTf;

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
    private Button deleteProductBtn;

    @FXML
    private Button productBtn;

    @FXML
    private Button updateProductBtn;

    @FXML
    private ScrollPane productPane;

    @FXML
    private TextField totalCustomerTf;

    @FXML
    private TextField totalItemTf;

    @FXML
    private TableColumn<?, ?> tbName;

    @FXML
    private TableColumn<?, ?> tbQty;

    @FXML
    private TableColumn<?, ?> tbTotal;

    @FXML
    private TableColumn<?, ?> tbUnitPrice;

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

    @FXML
    private TableView<BillingProduct> billingProductTable;

    final CustomerService customerService = new CustomerServiceImple();
    final ProductService productService = new ProductServiceImple();
    final UserService userService = new UserServiceImpl();
    ObservableList<Customer> allCustomers;
    ObservableList<BillingProduct> billingProducts = javafx.collections.FXCollections.observableArrayList();

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
        try {
            customerService.update(customerNameTf.getText(), customerPhoneTf.getText(),
                    customerEmailTf.getText(), customerAddressTf.getText(),
                    customerIsActive.isSelected() ? 1 : 0);
            loadAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addProductBtnAction(ActionEvent actionEvent) {
        try {
            productService.add(productNameTf.getText(),
                    productCategoryTf.getText(),
                    Double.parseDouble(productPriceTf.getText()),
                    Integer.parseInt(productStockTf.getText()),
                    productIsActive.isSelected() ? 1 : 0, productBarcodeTf.getText());
            loadAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProductBtnAction(ActionEvent actionEvent) {
        try {
            productService.update(productNameTf.getText(),
                    productCategoryTf.getText(),
                    Double.parseDouble(productPriceTf.getText()),
                    Integer.parseInt(productStockTf.getText()),
                    productIsActive.isSelected() ? 1 : 0, productBarcodeTf.getText());
            loadAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProductBtnAction(ActionEvent actionEvent) {
        try {
            productService.delete(productNameTf.getText());
            loadAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void customerBtnAction(ActionEvent event) {
        productPane.setVisible(false);
        billingPane.setVisible(false);
        dashboardPane.setVisible(false);
        customerPane.setVisible(true);
    }

    @FXML
    void productBtnAction(ActionEvent event) {
        customerPane.setVisible(false);
        billingPane.setVisible(false);
        dashboardPane.setVisible(false);
        productPane.setVisible(true);
    }

    public void billingBtnAction(ActionEvent actionEvent) {
        productPane.setVisible(false);
        customerPane.setVisible(false);
        dashboardPane.setVisible(false);
        billingPane.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadDashboard();

        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tcIsActive.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        loadAllCustomers();

        tpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tpCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tpPrice.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        loadAllProducts();

        tbName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tbQty.setCellValueFactory(new PropertyValueFactory<>("buyQty"));
        tbTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));


        customerTable.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, newValue) -> {
            if (newValue != null) {
                setSelectedCustomer(newValue);
            }
        });

        productTable.getSelectionModel().selectedItemProperty().addListener((observableValue, product, newValue) -> {
            if (newValue != null) {
                setSelectedProduct(newValue);
            }
        });

        billingProductTable.getSelectionModel().selectedItemProperty().addListener((observableValue, product, newValue) -> {
            if (newValue != null) {
                setSelectedBillingProduct(newValue);
            }
        });
    }

    public void loadAllCustomers() {
        try {
            customerTable.setItems(customerService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAllProducts() {
        try {
            productTable.setItems(productService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAllBillingProducts() {
        billingProductTable.setItems(billingProducts);
    }

    public void setSelectedCustomer(Customer customer) {
        customerNameTf.setText(customer.getName());
        customerPhoneTf.setText(customer.getPhone());
        customerEmailTf.setText(customer.getEmail());
        customerAddressTf.setText(customer.getAddress());
        customerIsActive.setSelected(customer.getIsActive() == 1);
    }

    public void setSelectedProduct(Product product) {
        productNameTf.setText(product.getName());
        productCategoryTf.setText(product.getCategory());
        productPriceTf.setText(String.valueOf(product.getPrice()));
        productStockTf.setText(String.valueOf(product.getStockQty()));
        productIsActive.setSelected(product.getIsActive() == 1);
        productBarcodeTf.setText(product.getBarcode());
    }

    // -------------------------- billing section --------------------------------------

    public void billingBarcodeEnterBtnAction(ActionEvent actionEvent) {
        try {
            Product product = productService.searchProduct(billingBarcodeTf.getText());
            billingProductNameTf.setText(product.getName());
            billingStockTf.setText(String.valueOf(product.getStockQty()));
            billingProductUnitPrice.setText(String.valueOf(product.getPrice()));
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Product Search");
            alert.setHeaderText(null); // optional, removes header
            alert.setContentText("No product found for the given barcode.");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void billingProductAddBtnAction(ActionEvent actionEvent) {
        String barcode = billingBarcodeTf.getText();
        int buyQty = Integer.parseInt(billingBuyStockTf.getText());
        int stockQty = Integer.parseInt(billingStockTf.getText());
        double unitPrice = Double.parseDouble(billingProductUnitPrice.getText());

        // Try to find existing product by barcode
        Optional<BillingProduct> existing = billingProducts.stream()
                .filter(p -> p.getBarcode().equals(barcode))
                .findFirst();

        if (existing.isPresent()) {
            // Update existing product

            if ((existing.get().getBuyQty() + buyQty) > stockQty) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Quantity is too much");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a less amount than stock quantity.");
                alert.showAndWait();
            } else {

                BillingProduct bp = existing.get();
                bp.setBuyQty(bp.getBuyQty() + buyQty); // increase quantity
                bp.setTotalPrice(bp.getBuyQty() * bp.getUnitPrice()); // recalc total
                billingProductTable.refresh(); // refresh table view
            }
        } else {
            // Add new product
            billingProducts.add(new BillingProduct(
                    barcode,
                    billingProductNameTf.getText(),
                    buyQty,
                    stockQty,
                    unitPrice,
                    buyQty * unitPrice
            ));
            loadAllBillingProducts();
            calculateBillingTotal();
        }
    }

    public void setSelectedBillingProduct(BillingProduct product) {
        billingBarcodeTf.setText(product.getBarcode());
        billingProductNameTf.setText(product.getName());
        billingStockTf.setText(String.valueOf(product.getStockQty()));
        billingBuyStockTf.setText(String.valueOf(product.getBuyQty()));
        billingProductUnitPrice.setText(String.valueOf(product.getUnitPrice()));
    }


    public void billingProductRemoveBtnAction(ActionEvent actionEvent) {
        BillingProduct selected = billingProductTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            billingProducts.remove(selected);
            billingProductTable.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a product to remove.");
            alert.showAndWait();
        }
    }

    public void calculateBillingTotal() {
        double total = billingProducts.stream()
                .mapToDouble(BillingProduct::getTotalPrice) // p -> p.getTotalPrice()
                .sum();
        billingSubTotalTf.setText(String.valueOf(total));

    }

    public void billingCalculateTotalBtnAction(ActionEvent actionEvent) {
        double finalTotal = Double.parseDouble(billingSubTotalTf.getText()) - ((Double.parseDouble(billingSubTotalTf.getText())
                * Double.parseDouble(billingDiscountTf.getText())) / 100);
        billingFinalTotalTf.setText(String.valueOf(finalTotal));
    }

    public void billingCusSearchBtnAction(ActionEvent actionEvent) {
        try {
            Customer customer = customerService.searchCustomer(billingCusPhoneTf.getText());
            billingCusNameTf.setText(customer.getName());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Search");
            alert.setHeaderText(null);
            alert.setContentText("No customer found for given number.");
            alert.showAndWait();
        }
    }

    // -------------------- dashboard -----------------------------------

    public void dashboardBtnAction(ActionEvent actionEvent) {
        customerPane.setVisible(false);
        billingPane.setVisible(false);
        productPane.setVisible(false);
        dashboardPane.setVisible(true);

        loadDashboard();
    }

    private void loadDashboard() {
        try {
            totalItemTf.setText(String.valueOf(productService.getProductCount()));
            totalCustomerTf.setText(String.valueOf(customerService.getCustomerCount()));
        } catch (SQLException e) {
            totalCustomerTf.setText(String.valueOf(0));
            totalItemTf.setText(String.valueOf(0));

        }
    }

    // ------------- login page ------------------

    public void loginBtnAction(ActionEvent actionEvent) {

        String email = loginEmailTf.getText();
        String password = loginPasswordTf.getText();

        try {
            if (userService.isUserExisted(email, password)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login success");
                alert.setHeaderText(null);
                alert.setContentText("You are logged in as a cashier");
                alert.showAndWait();
            }else{
                throw new  SQLException("Invalid credentials");
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid credential");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void loginFPOnMouse(MouseEvent mouseEvent) {

    }
}
