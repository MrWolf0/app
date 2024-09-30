module com.bill.app {
    requires java.sql;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;



    opens com.bill.app to javafx.fxml;
    exports com.bill.app;
    exports com.bill.app.model;
    opens com.bill.app.model to javafx.fxml;
    exports com.bill.app.view;
    opens com.bill.app.view to javafx.fxml;
}