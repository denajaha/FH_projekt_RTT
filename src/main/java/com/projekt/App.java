package com.projekt;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Hello JavaFX!");
        btn.setOnAction( (event) -> Platform.exit() );
        Pane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 150));
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
