module demo2.demo2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens demo2.demo2 to javafx.fxml;
    exports demo2.demo2;
}