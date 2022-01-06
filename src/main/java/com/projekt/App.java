package com.projekt;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        /*primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Hello JavaFX!");
        btn.setOnAction( (event) -> Platform.exit() );
        Pane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 150));
        primaryStage.show();
        */

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/welcome.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 300, 275);
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        /* Testmethoden Antoine*/

        System.out.println("hello");
        User user1 = User.createNewUser("Max", "Mustermann", "MaMu", "test", "admin");
        System.out.println(user1.toString());
        User user2 = User.createNewUser("Peter", "Mustermann", "MaMu", "test", "admin");
        User user3 = User.createNewUser("Sebi", "Mustermann", "MaMu", "test", "admin");
        User user4 = User.createNewUser("Lisa", "Mustermann", "MaMu", "test", "admin");
        User user5 = User.createNewUser("Yan", "Mustermann", "MaMu", "test", "admin");
        System.out.println(user1.toString());
        User.updateUserDatabase();
        User.loadDataFromJson();
        ArrayList<User> userlist = User.getUsers();
        System.out.println(userlist.get(0).getFirstname());
        user1.setFirstname("Peter");
        ArrayList<User> userliste = User.getUsers();
        System.out.println(userliste.get(0).getFirstname());

        User.loadDataFromJson();
        System.out.println(User.checkCredentials("MaMu", "test"));
        launch(args);

    }
}
