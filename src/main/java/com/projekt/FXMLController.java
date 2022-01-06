package com.projekt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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