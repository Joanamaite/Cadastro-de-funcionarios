module com.example.cadastrofun {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.dlsc.formsfx;
    opens com.example.cadastrofun to javafx.fxml;

    opens com.example.cadastrofun.Controller to javafx.fxml;
    exports com.example.cadastrofun;
    exports com.example.cadastrofun.Controller;
}