package com.bankwibu.tubespbo.Controllers.Client;

import com.bankwibu.tubespbo.Models.Model;
import com.bankwibu.tubespbo.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener() {
        dashboard_btn.setOnAction(actionEvent -> onDashboard());
        transaction_btn.setOnAction(actionEvent -> onTransactions());
        logout_btn.setOnAction(actionEvent -> onLogout());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    private void onTransactions() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }

    private void onAccount() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    private void onLogout() {
        // Get Stage
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();
        // Close the client window
        Model.getInstance().getViewFactory().closeStage(stage);
        // Show login window
        Model.getInstance().getViewFactory().showLoginWindow();
        // Set Client Login Success Flag To False
        Model.getInstance().setClientLoginSuccessFlag(false);
    }
}