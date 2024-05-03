module src.lab8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens src.lab8 to javafx.fxml;
    exports src.lab8;
}
