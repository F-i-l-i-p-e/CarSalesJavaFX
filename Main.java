// Filipe Nogueira Santos
//200534531
// MidTerm Exam - 23S Adv Object Oriented Prog-Java - 200 COMP1011-23S-31928
package com.example.f22midterm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/f22midterm/CarSalesOverview.fxml"));
        primaryStage.setTitle("Car Sales");
        // Icon
        Image icon = new Image("file:/C:/Users/User/IdeaProjects/F22Midterm/src/main/java/com/example/Icon/car.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
