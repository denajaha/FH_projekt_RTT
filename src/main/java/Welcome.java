import com.projekt.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.w3c.dom.events.Event;

public class Welcome {

    public Button loginButton;
    public Label usernameInput;
    public Label passwordInput;

    public Welcome(){

    }

    @FXML
    public void loginButtonClicked(ActionEvent actionEvent) {
        if (User.checkCredentials(usernameInput.toString(), passwordInput.toString())) {


        }
    }
}
