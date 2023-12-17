package com.bankwibu.tubespbo.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin {
    private final StringProperty userName;
    private final StringProperty password;

    public Admin(String userName, String password) {
        this.userName = new SimpleStringProperty(this, "Username", userName);
        this.password = new SimpleStringProperty(this, "Password", password);
    }

    public StringProperty usernameProperty() {return userName;}
    public StringProperty passwordProperty() {return password;}
}