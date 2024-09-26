package com.bill.app;

import com.bill.app.model.HandelDataBaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class billMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Locale.setDefault(new Locale("ar", "SA")); // Arabic - Saudi Arabia
        FXMLLoader fxmlLoader = new FXMLLoader(billMain.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        scene.getStylesheets().add(getClass().getResource("/styles/custmize.css").toExternalForm());
        stage.setScene(scene);
        HandelDataBaseConnection handelDataBaseConnection = new HandelDataBaseConnection();
        handelDataBaseConnection.createDatabaseFolder("E:\\App");
        stage.show();
    }

    public static void main(String[] args) {
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");
        launch();
    }
}