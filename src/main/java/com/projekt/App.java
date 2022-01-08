package com.projekt;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;


public class App extends Application {
    private static Session session;

    public static void setSession(Session pSession){
        session = pSession;
    }

    public static Session getSession(){
        return session;
    }

    @Override
    public void start(Stage primaryStage) {
        User.loadDataFromJson();
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
        /*
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
        */
        launch(args);

    }
}
