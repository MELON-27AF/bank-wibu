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
    public Label username_lbl;
    public TextField username_fld;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccounType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccounType(acc_selector.getValue()));
        login_btn.setOnAction(actionEvent -> onLogin());
    }

    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        if (Model.getInstance().getViewFactory().getLoginAccounType() == AccountType.CLIENT){
            Model.getInstance().getViewFactory().showClientWindow();
        } else {
            Model.getInstance().getViewFactory().showAdminWindow();
        }
    }
}