module org.bernard.myappjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.bernard.myappjavafx to javafx.fxml;
    exports org.bernard.myappjavafx;
}