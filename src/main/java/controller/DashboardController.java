package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.Customer;
import service.CustomerService;
import service.imple.CustomerServiceImple;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button addCustomerBtn;

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
    private Button customerUpdateBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button productBtn;

    @FXML
    private ScrollPane productPane;

    @FXML
    private TableColumn<?, ?> tcIsActive;

    @FXML
    private TableColumn<?, ?> tcName;

    @FXML
    private TableColumn<?, ?> tcPhone;

    @FXML
    private TableView<Customer> customerTable;

    CustomerService customerService = new CustomerServiceImple();
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

        customerTable.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, newValue) -> {
            if(newValue != null){
                setSelectedCustomer(newValue);
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

    public void setSelectedCustomer(Customer customer){
        customerNameTf.setText(customer.getName());
        customerPhoneTf.setText(customer.getPhone());
        customerEmailTf.setText(customer.getEmail());
        customerAddressTf.setText(customer.getAddress());
        customerIsActive.setSelected(customer.getIsActive() == 1);
    }
}
