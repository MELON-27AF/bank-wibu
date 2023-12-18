package com.bankwibu.tubespbo.Controllers;

import com.bankwibu.tubespbo.Models.Model;
import com.bankwibu.tubespbo.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label pAddress_lbl;
    public TextField pAddress_fld;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> setAcc_selector());
        login_btn.setOnAction(actionEvent -> onLogin());
    }

    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT){
            // Evaluate Client Login Credentials
            Model.getInstance().evaluateClientCred(pAddress_fld.getText(), password_fld.getText());
            if (Model.getInstance().getClientLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showClientWindow();
                // Close the login stage
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                pAddress_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No Such Login Credentials.");
            }
        } else {
            // Evaluate Admin Login Credentials
            Model.getInstance().evaluateAdminCred(pAddress_fld.getText(), password_fld.getText());
            if (Model.getInstance().getAdminLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showAdminWindow();
                // Close the login stage
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                pAddress_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No Such Login Credentials.");
            }
        }
    }

    private void setAcc_selector() {
        Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());
        // Change Payee Address label accordingly
        if (acc_selector.getValue() == AccountType.ADMIN){
            pAddress_lbl.setText("Username:");
        } else {
            pAddress_lbl.setText("Payee Address:");
        }
    }
}