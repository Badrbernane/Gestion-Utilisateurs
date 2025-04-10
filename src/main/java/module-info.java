module org.bernard.myappjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens org.bernard.myappjavafx to javafx.fxml;
    exports org.bernard.myappjavafx;
    exports org.bernard.myappjavafx.Controllers;
    opens org.bernard.myappjavafx.Controllers to javafx.fxml;
    exports org.bernard.myappjavafx.Models;
    opens org.bernard.myappjavafx.Models to javafx.fxml;
}