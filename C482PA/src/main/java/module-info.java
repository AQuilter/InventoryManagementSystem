module pa.c482pa {
    requires javafx.controls;
    requires javafx.fxml;


    opens pa.c482pa to javafx.fxml;
    exports pa.c482pa;
    exports controller;
    opens controller to javafx.fxml;
    exports model;
    opens model to javafx.base;
}