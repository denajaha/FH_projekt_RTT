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

    public Label usernameInput;
    public Button loginButton;
    public Label passwordInput;

    public Button backToMainPageButton;
    public Button saveButton;
    public ToggleButton toggleButtonOne;
    public ToggleButton toggleButtonTwo;
    public ToggleButton toggleButtonThree;

    public ImageView userImage;
    public Text userName;
    public Text userRole;
    public Button userChangeButton;
    public Button settingsButton;
    public Button cashPaymentButton;
    public Button cardPaymentButton;
    public ScrollPane order;
    public TextField returnAmount;
    public Label money_given;
    public Label money_tipped;
    public TextField totalAmount;

    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    }

    public void loginButtonClicked(ActionEvent actionEvent) {

    }

    public void userChangeButtonClicked(ActionEvent actionEvent) {

    }

    public void settingsButtonClicked(ActionEvent actionEvent) {

    }

    public void cashPaymentButtonClicked(ActionEvent actionEvent) {

    }

    public void cardPaymentButtonClicked(ActionEvent actionEvent) {

    }

    public void backToMainPageButtonClicked(ActionEvent actionEvent) {

    }
}