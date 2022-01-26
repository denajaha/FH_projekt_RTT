package com.projekt;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

import static javafx.scene.input.KeyEvent.*;

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

    public Button cashPaymentPayButton;

    // cardPage
    public Button doneButton;

    //ErrorPopu
    public Button error_ok;
    //Usermanagement
    @FXML
    public TableView<User> tableusermgmt;
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
    public TableColumn<User, String> firstname;
    @FXML
    public TableColumn<User, String> surname;
    @FXML
    public TableColumn<User, String> role;
    public Text productsId;
    private User loadedUser;

    @FXML
    public TextField totalAmount;

    //Denis&Filip buttons
    @FXML
    public Button listButton;
    public ListView listView;
    public ListView listViewSubKeys;
    public Button listButtonSubcattegories;
    public Button addInOrder;
    public ListView listViewOrder;
    @FXML
    public TextField moneyGiven;
    @FXML
    public TextField moneyTipped;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public FXMLController() {

    }

    // Welcome Page Methods
    public void loginButtonClicked(ActionEvent actionEvent) {
        if (User.checkCredentials(usernameInput.getText(), passwordInput.getText())) {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("mainPage.fxml"));
                Stage stage = new Stage();
                stage.setTitle("RTT Cash Register");
                stage.setScene(new Scene(root, 1100, 550));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Session session = new Session(User.getUserCredentials(usernameInput.getText(), passwordInput.getText()));
            App.setSession(session);
        } else {
            try {
                Parent popup = FXMLLoader.load(getClass().getClassLoader().getResource("errorLogin.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Passwort Falsch");
                primaryStage.setScene(new Scene(popup, 200, 150));
                primaryStage.show();
            }catch (IOException e) {
            e.printStackTrace();
            }
        }
    }

    public void buttonPressed(KeyEvent c)
    {
        if(c.getCode().toString().equals("ENTER"))
        {
            if (User.checkCredentials(usernameInput.getText(), passwordInput.getText())) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("mainPage.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("RTT Cash Register");
                    stage.setScene(new Scene(root, 1100, 550));
                    stage.show();
                    // Hide this current window (if this is what you want)
                    //((Node) (KeyEvent.getSource())).getScene().getWindow().hide();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Session session = new Session(User.getUserCredentials(usernameInput.getText(), passwordInput.getText()));
                App.setSession(session);
            } else {
                try {
                    Parent popup = FXMLLoader.load(getClass().getClassLoader().getResource("errorLogin.fxml"));
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Passwort Falsch");
                    primaryStage.setScene(new Scene(popup, 200, 150));
                    primaryStage.show();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

        tableusermgmt.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() >= 1 && (!row.isEmpty())) {
                    User rowdata = row.getItem();
                    nameusermgmt.setText(rowdata.getFirstname());
                    surnameusermgmt.setText(rowdata.getSurname());
                    roleuserusermgmt.setText(rowdata.getRole());
                    loadedUser = rowdata;
                }
            });
            return row;
        });
    }

    public void savemodifieduser(ActionEvent e) {

        for (int i = 0; i < User.getUsers().size(); i++) {
            if (User.getUsers().get(i).equals(loadedUser)) {
                if (!(nameusermgmt.getText().equals(loadedUser.getFirstname()))) {
                    User.getUsers().get(i).setFirstname(nameusermgmt.getText());
                }
                if (!(surnameusermgmt.getText().equals(loadedUser.getSurname()))) {
                    User.getUsers().get(i).setSurname(surnameusermgmt.getText());
                }
            }
        }

        //User.updateUserDatabase();
        //User.loadDataFromJson();

        ObservableList<User> peter = FXCollections.<User>observableArrayList();
        peter.addAll(User.getUsers());
        //System.out.println(peter.toString());
        firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        tableusermgmt.setItems(peter);
        tableusermgmt.getColumns().get(0).setVisible(false);
        tableusermgmt.getColumns().get(0).setVisible(true);

    }

    public void createNewUser(ActionEvent actionEvent) {


        User.createNewUser(nameusermgmt1.getText(), surnameusermgmt1.getText(), usernameusermgmt1.getText(), passwordusermgmt1.getText(), roleusermgmt1.getText());

        ObservableList<User> data = FXCollections.<User>observableArrayList();
        data.addAll(User.getUsers());
        firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        tableusermgmt.setItems(data);


    }

    public void closePopUp(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void deleteUser(ActionEvent actionEvent) {
        for (int i = 0; i < User.getUsers().size(); i++) {
            if (User.getUsers().get(i).equals(loadedUser)) {
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
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
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
    /*
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

                //ArrayList<String> arrayListOfSubkeysInBarPage = clickedSubKeyInProducts;
                System.out.println("New list:" + clickedSubKeyInProducts);
                listViewProducts2.getItems().addAll(clickedSubKeyInProducts);



            } catch (IOException e) {
                e.printStackTrace();
            }
        }

     */
    public void cashPaymentButtonClicked(ActionEvent actionEvent) {
        int moneyGivenVar = Integer.parseInt(moneyGiven.getText());
        int moneyTippedVar = Integer.parseInt(moneyTipped.getText());
        if(moneyGiven.getText().trim().isEmpty() || moneyGiven.getText().length() == 0){
            moneyGiven.setText(String.valueOf(0));
            //moneyGivenVar = 0;
        }
        if (moneyTipped.getText().trim().isEmpty() || moneyTipped.getText().length() == 0){
            //moneyTippedVar = 0;
            moneyTipped.setText(String.valueOf(0));
        }
        returnAmount.setText(String.valueOf(moneyGivenVar - moneyTippedVar - sum));
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
            root = FXMLLoader.load(getClass().getClassLoader().getResource("mainPage.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("RTT Cash Register");
            Scene scene = new Scene(root, 1100, 546);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
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

    public void backToSettingsPageButtonClicked(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("settings.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("RTT Cash Register");
            Scene scene = new Scene(root, 750, 550);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Denis&Filip
    //Button - Load Categories
    //displays the list of Main JSON Keys, when the button is clicked
    public ArrayList<String> listButtonClicked() {
        MainPage mainPage = new MainPage();
        ArrayList<String> listOfMainKeys;
        //JSON data injection
        mainPage.loadDataFromJson("Supermarkt.json");
        mainPage.getKeyFromJson(mainPage.getJSONstring());
        listOfMainKeys = mainPage.getMainKey();
        //Show at listView - GUI element
        listView.getItems().addAll(listOfMainKeys);
        return listOfMainKeys;
    }

    //getter
    public String getMainKey(String mainKeyGetter) {
        return mainKeyGetter;
    }
    //shared variable - later used for indexing
    String sharedVar;

    //event listener for main keys
    //gets shared variable
    public void categoryClicked(MouseEvent mouseEvent) {
        sharedVar = getMainKey(listView.getSelectionModel().getSelectedItem().toString());
    }

    //Button 2
    //loads Subkeys on Buttonclick
    //displays subkeys on viewList
    ArrayList<String> listOfSubkeys;
    public String isButtonSubcattegoriesClicked() {
        ArrayList<String> listOfMainKeys = listButtonClicked();
        int indexOfMainKey = listOfMainKeys.indexOf(sharedVar);

        String mainKeyIndexString = listOfMainKeys.get(indexOfMainKey);

        MainPage mainPage = new MainPage();
        mainPage.getsubKeyFromJson(mainKeyIndexString);


        listOfSubkeys = mainPage.getSubKey();
        getListOfSubKeys();
        listViewSubKeys.getItems().addAll(listOfSubkeys);
        return mainKeyIndexString;
    }
    public ArrayList<String> getListOfSubKeys() {
        return listOfSubkeys;
    }


    ArrayList<String> clickedSubKeyInProducts = new ArrayList<>();
    int sum = 0;
    String sum2 = String.valueOf(sum);
    //Add Button
    //Adds Subkeys to order list
    public void addInOrderClicked() {
        //mainPage object
        MainPage mainPage = new MainPage();
        BarPage barPage = new BarPage();

        /*
        -------------------------------------------------------------------------------------------
         */


        listViewSubKeys.getSelectionModel().getSelectedItem().toString();
        System.out.println("test.......!!!!!!!" + listViewSubKeys.getSelectionModel().getSelectedItem().toString() );
        String subKeyForOrder = getSubKey(listViewSubKeys.getSelectionModel().getSelectedItem().toString());

        listViewOrder.getItems().add(subKeyForOrder);





        String selectedSubkey = listViewSubKeys.getSelectionModel().getSelectedItem().toString();
        // clickedSubKeyInProducts.add(selectedSubkey);

        System.out.println("Selected subkey: " + selectedSubkey);
        System.out.println("subkeyforOrder" + subKeyForOrder);
        System.out.println("Lista: " + clickedSubKeyInProducts.toString());

/*
-------------------------------------------------------------------------------------------
 */


/*
        ArrayList<String> listSubsCLEAN = getListOfSubKeys();       //we need clean list os subkeys and not filtered list in "Order" ( because we want to extract price from original list in JSON file
        int indexOfSubKey = listSubsCLEAN.indexOf(subKeyForOrder);

        String subKeyIndexString = listSubsCLEAN.get(indexOfSubKey);
        mainPage.getPriceFromsubKeys(subKeyIndexString);
        System.out.println("-----------------------------");
        ArrayList<String> prices;
        prices = mainPage.getPrice();
        System.out.println(prices);
        System.out.println("-----------------------------");

*/
        // Variables to help us to choice between MainKey and SubKey
        int ChoiceMainKey;
        int ChoiceSubKey;


        // ArrayList to help us to store our Keys, Subkeys and Prices
        ArrayList<String> keys;
        ArrayList<String> subkeys;
        ArrayList<String> price;

        ArrayList<String> listSubsCLEAN = getListOfSubKeys();

        // Loading our Json File from Destiny
        mainPage.loadDataFromJson("Supermarkt.json");

        // Loading our MainKeys from Json
        mainPage.getKeyFromJson(mainPage.getJSONstring());

        keys = mainPage.getMainKey();

        String pera = listViewSubKeys.getSelectionModel().getSelectedItem().toString();
        int indexOfSubKey = listSubsCLEAN.indexOf(pera);
        //clickedSubKeyInProducts.add(pera);


        // Loading SubKeys from MainPage and also the Prices of the respective SubKey
        String mainKeyIndexStringClicked = isButtonSubcattegoriesClicked();
        mainPage.getsubKeyFromJson(mainKeyIndexStringClicked);
        mainPage.getPriceFromsubKeys(mainKeyIndexStringClicked);

        // Saving our Subkeys and Prices into a List to work with them in our Simulation
        subkeys = mainPage.getSubKey();
        price = mainPage.getPrice();


        barPage.listofallselecteditems.add(subkeys.get(indexOfSubKey));
        barPage.finalorder.put(subkeys.get(indexOfSubKey), Float.parseFloat(price.get(indexOfSubKey)));


        // Method to eliminate duplicates but also to count them --> Simulating clicks
        barPage.orderlist(barPage.listofallselecteditems);


        for (int i = 0; i < barPage.listofallselecteditems.size(); i++) {
            sum += Integer.parseInt(price.get(indexOfSubKey));
        }
        barPage.total();

        barPage.printorder();

        System.out.println("Total: " + sum + "€");

        //return sum;
        //Display sum in textfield totalAmount when button is pressed
        totalAmount.setText(String.valueOf(sum));


    }


    public void mouseClickedInSubkeys(MouseEvent mouseEvent) {

    }
    String sharedSubKeyVar;
    //Getter subkey
    public String getSubKey(String subKeyGetter){
        return subKeyGetter;
    }


    public void subCategoryClicked(MouseEvent mouseEvent) {
        sharedSubKeyVar = getSubKey(listViewSubKeys.getSelectionModel().getSelectedItem().toString());

    }
}