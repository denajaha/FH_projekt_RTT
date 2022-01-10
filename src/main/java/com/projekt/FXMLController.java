package com.projekt;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class FXMLController implements Initializable {

    //welcome
    public TextField usernameInput;
    public Button loginButton;
    public TextField passwordInput;

    //settings
    public Button backToMainPageButton;
    public Button saveButton;
    public ToggleButton toggleButtonOne;
    public ToggleButton toggleButtonTwo;
    public ToggleButton toggleButtonThree;

    //mainPage
    public ImageView userImage;
    public Text userName;
    public Text userRole;
    public Button userChangeButton;
    public Button settingsButton;
    public Button cashPaymentButton;
    public Button cardPaymentButton;
    public ScrollPane order;

    // barPage
    public TextField returnAmount;
    public TextField money_given;
    public TextField money_tipped;
    public TextField totalAmount;
    public Button cashPaymentPayButton;

    //Usermanagement
    @FXML
    public TableView <User> tableusermgmt;
    public TextField nameusermgmt;
    public TextField surnameusermgmt;
    public TextField passwordusermgmt;
    public TextField usernameusermgmt;
    public TextField roleuserusermgmt;
    public TextField nameusermgmt1;
    public TextField surnameusermgmt1;
    public TextField roleusermgmt1;
    public TextField passwordusermgmt1;
    public TextField usernameusermgmt1;
    public Button goBack;
    @FXML
    public TableColumn <User,String> firstname;
    @FXML
    public TableColumn <User,String> surname;
    @FXML
    public TableColumn <User,String> role;
    private User loadedUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        //label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    }

    public FXMLController(){

    }

    // Welcome Page Methods
    public void loginButtonClicked(ActionEvent actionEvent) {
        if(User.checkCredentials(usernameInput.getText(),passwordInput.getText())){
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("mainPage.fxml"));
                Stage stage = new Stage();
                stage.setTitle("RTT Cash Register");
                stage.setScene(new Scene(root, 750, 550));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Session session = new Session(User.getUserCredentials(usernameInput.getText(),passwordInput.getText()));
            App.setSession(session);
        }else{
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Passwort Falsch");
            Button btn = new Button();
            btn.setText("PASSWORT FALSCH");
            btn.setOnAction( (event) -> Platform.exit() );
            Pane root = new StackPane();
            root.getChildren().add(btn);
            primaryStage.setScene(new Scene(root, 200, 150));
            primaryStage.show();
        }

    }

    //Usermanagement Methods
    public void loadUser(ActionEvent actionEvent) {
        ObservableList<User> data = FXCollections.<User>observableArrayList();
        data.addAll(User.getUsers());
        firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        tableusermgmt.setItems(data);

        tableusermgmt.setRowFactory( tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() >= 1 && (! row.isEmpty()) ) {
                    User rowdata = row.getItem();
                    nameusermgmt.setText(rowdata.getFirstname());
                    surnameusermgmt.setText(rowdata.getSurname());
                    roleuserusermgmt.setText(rowdata.getRole());
                    loadedUser = rowdata;
                }
            });
            return row ;
        });
    }

    public void savemodifieduser(ActionEvent e){

        for(int i=0; i<User.getUsers().size();i++){
                if(User.getUsers().get(i).equals(loadedUser)){
                    if(!(nameusermgmt.getText().equals(loadedUser.getFirstname()))) {
                        User.getUsers().get(i).setFirstname(nameusermgmt.getText());
                    }
                    if(!(surnameusermgmt.getText().equals(loadedUser.getSurname()))){
                        User.getUsers().get(i).setSurname(surnameusermgmt.getText());
                    }
                }
        }

        User.updateUserDatabase();
        User.loadDataFromJson();

        ObservableList<User> data = FXCollections.<User>observableArrayList();
        data.addAll(User.getUsers());
        firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        tableusermgmt.setItems(data);


    }

    public void createNewUser(ActionEvent actionEvent) {


        User.createNewUser(nameusermgmt1.getText(), surnameusermgmt1.getText(),usernameusermgmt1.getText(), passwordusermgmt1.getText(), roleusermgmt1.getText());

        ObservableList<User> data = FXCollections.<User>observableArrayList();
        data.addAll(User.getUsers());
        firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        tableusermgmt.setItems(data);


    }

    public void deleteUser(ActionEvent actionEvent) {
        for(int i=0; i<User.getUsers().size();i++){
            if(User.getUsers().get(i).equals(loadedUser)){
                User.deleteUser(User.getUsers().get(i));
            }
        }



        ObservableList<User> data = FXCollections.<User>observableArrayList();
        data.addAll(User.getUsers());
        firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        tableusermgmt.setItems(data);


    }



    // Main Page Methods
    public void userChangeButtonClicked(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("welcome.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 750, 550));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void settingsButtonClicked(ActionEvent actionEvent) {
        Parent root;
        try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("settings.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Settings");
                stage.setScene(new Scene(root, 750, 550));
                stage.show();
                // Hide this current window (if this is what you want)
                //((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void cashPaymentButtonClicked(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("barPage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("View your Order");
            stage.setScene(new Scene(root, 750, 550));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cardPaymentButtonClicked(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("cardPage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Goodbye!");
            stage.setScene(new Scene(root, 750, 550));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Settings Page Methods
    public void backToMainPageButtonClicked(ActionEvent actionEvent) {
        Parent root;
        try {
        root= FXMLLoader.load(getClass().getClassLoader().getResource("mainPage.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("RTT Cash Register");
        Scene scene = new Scene(root, 750, 550);
        stage.setScene(scene);
        stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void userSettingOpen(ActionEvent actionEvent) {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("UserManagement.fxml"));
                Stage stage = new Stage();
                stage.setTitle("User Settings");
                stage.setScene(new Scene(root, 750, 550));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}