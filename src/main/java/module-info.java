module com.bankwibu.tubespbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.bankwibu.tubespbo to javafx.fxml;
    exports com.bankwibu.tubespbo;
    exports com.bankwibu.tubespbo.Controllers;
    exports com.bankwibu.tubespbo.Controllers.Admin;
    exports com.bankwibu.tubespbo.Controllers.Client;
    exports com.bankwibu.tubespbo.Models;
    exports com.bankwibu.tubespbo.Views;
}