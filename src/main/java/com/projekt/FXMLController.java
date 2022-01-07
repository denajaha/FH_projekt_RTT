package com.projekt;
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

    //Usermanagement
    @FXML
    public TableView <User> tableusermgmt;
    public TextField nameusermgmt;
    public TextField surnameusermgmt;
    public TextField passwordusermgmt;
    public TextField usernameusermgmt;
    @FXML
    public TableColumn <User,String> firstname;
    @FXML
    public TableColumn <User,String> surname;
    @FXML
    public TableColumn <User,String> role;

    @FXML
    private Label label;

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
                stage.setTitle("RTT KASSENAPP");
                stage.setScene(new Scene(root, 1200, 1200));
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

    //UsermanagementMethod
    public void loadUser(ActionEvent actionEvent) {
        ObservableList<User> data = FXCollections.<User>observableArrayList();
        data.addAll(User.getUsers());
        firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        tableusermgmt.setItems(data);
    }


    // Main Page Methods
    public void userChangeButtonClicked(ActionEvent actionEvent) {

    }

    public void settingsButtonClicked(ActionEvent actionEvent) {

    }

    public void cashPaymentButtonClicked(ActionEvent actionEvent) {

    }

    public void cardPaymentButtonClicked(ActionEvent actionEvent) {

    }

    // Settings Page Methods
    public void backToMainPageButtonClicked(ActionEvent actionEvent) {

    }
}