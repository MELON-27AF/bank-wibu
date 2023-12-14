package com.bankwibu.tubespbo;

import com.bankwibu.tubespbo.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage Stage) {
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}