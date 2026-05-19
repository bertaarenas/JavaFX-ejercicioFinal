module com.example.paint2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.desktop;


    opens com.example.paint2 to javafx.fxml;
    exports com.example.paint2;
}