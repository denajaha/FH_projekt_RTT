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
        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
