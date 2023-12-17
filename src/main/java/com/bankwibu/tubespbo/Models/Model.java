package com.bankwibu.tubespbo.Models;

import com.bankwibu.tubespbo.Views.AccountType;
import com.bankwibu.tubespbo.Views.ViewFactory;


import java.sql.ResultSet;
import java.time.LocalDate;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private AccountType loginAccountType = AccountType.CLIENT;

    // Client Data Section
    private final Client client;
    private final CheckingAccount checkingAcc;
    private boolean clientLoginSuccessFlag;

    // Admin Data Section
    private final Admin admin;
    private boolean adminLoginSuccessFlag;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();

        // Client Data Section
        this.clientLoginSuccessFlag = false;
        this.client = new Client("", "", "", null, null, null);
        this.checkingAcc = new CheckingAccount("","",0,0);

        // Admin Data Section
        this.adminLoginSuccessFlag = false;
        this.admin = new Admin("", "");
    }

    public static synchronized Model getInstance() {
        if (model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {return databaseDriver;}

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /*
    * Client Method Section
    * */
    public boolean getClientLoginSuccessFlag() {return this.clientLoginSuccessFlag;}

    public void setClientLoginSuccessFlag(boolean flag) {this.clientLoginSuccessFlag = flag;}

    public Client getClient() {
        return client;
    }

    public CheckingAccount getCheckingAcc(){
        return  checkingAcc;
    }

    public void evaluateClientCred(String username, String password) {
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getClientData(username, password);

        try {
            if (resultSet.isBeforeFirst()){
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastNameProperty().set(resultSet.getString("LastName"));
                this.client.usernameProperty().set(resultSet.getString("PayeeAddress"));
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                this.client.dateProperty().set(date);
                this.clientLoginSuccessFlag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    * Admin Metdhod Section
     **/
    public boolean getAdminLoginSuccessFlag() {return this.adminLoginSuccessFlag;}

    public void setAdminLoginSuccessFlag(boolean flag) {this.adminLoginSuccessFlag = flag;}

    public Admin getAdmin () {
        return admin;
    }

    public void evaluateAdminCred(String username, String password) {
        ResultSet resultSet = databaseDriver.getAdminData(username, password);

        try{
            if (resultSet.isBeforeFirst()){
                // Admin credentials are valid
                this.admin.usernameProperty().set(username);
                this.admin.passwordProperty().set(password);
                this.adminLoginSuccessFlag = true;
            } else {
                // Admin credentials are invalid
                this.adminLoginSuccessFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}