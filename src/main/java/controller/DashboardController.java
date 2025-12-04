package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

public class DashboardController {

    @FXML
    private Button customerBtn;

    @FXML
    private ScrollPane customerPane;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button productBtn;

    @FXML
    private ScrollPane productPane;

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

}
