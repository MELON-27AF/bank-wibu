module com.bankwibu.tubespbo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.bankwibu.tubespbo to javafx.fxml;
    exports com.bankwibu.tubespbo;
}