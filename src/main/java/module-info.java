module com.bill.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.bill.app to javafx.fxml;
    exports com.bill.app;
    exports com.bill.app.model;
    opens com.bill.app.model to javafx.fxml;
    exports com.bill.app.view;
    opens com.bill.app.view to javafx.fxml;
}