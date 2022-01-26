package com.projekt;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import org.json.simple.parser.JSONParser;


public class User {
    private static int users;
    private int userid;
    private static ArrayList<User> userlist = new ArrayList<User>();
    private static FileWriter file;
    private String firstname;
    private String surname;
    private String username;
    private String password;
    private String integrityKey;
    //Admin, Supervisor, Kassierer ?
    private String role;


 

    //Neuer User erstellen mit privaten Kontruktor
    // Neuer User wird über nachfolgende Methode erstellt
    // Kontrollierte erstellung somit sichergestellt
    private User(String firstname, String surname, String username, String password, boolean newUser, String role, int userid) {

        users++;
        if(newUser==true){
            this.userid = (int)(Math.random()*(2147483646-2000000000)+2000000000);
        }else{
            this.userid=userid;
        }
        this.firstname = firstname;
        this.surname = surname;
        if(newUser==true){
            this.username = sha256(username);
        }else{
            this.username = username;
        }
        if(newUser==true){
            this.password = sha256(password);
        }else{
            this.password = password;
        }
        this.role = role;

        if(newUser==true){
            this.integrityKey = sha256(sha256(username)+sha256(password)+sha256(role));;
        }else{
            this.integrityKey = sha256(username+password+sha256(role));
        }

        userlist.add(this);
    }

    //Methode zum erstellen eines neuen Users
    // Gibt ein User zurück
    public static User createNewUser(String firstname, String surname, String username, String password, String role){
        return new User(firstname,surname,username,password, true,role,5);
    }

    //Beim Aufrufen der Methode wird der User, der als Parameter übergeben wird aus der Datenbank gelöscht
    public static void deleteUser(User user){
        for(int i=0; i<userlist.size();i++){
            if(userlist.get(i).equals(user)){
                userlist.remove(i);
            }
        }
        updateUserDatabase();
    }

    //Gibt Liste aller momentanen User zurück
    public static ArrayList<User> getUsers() {
        return userlist;
    }

    //Gibt Vornamen zurück
    public String getFirstname() {
        return firstname;
    }

    //Gibt Familienname zurück
    public String getSurname() {
        return surname;
    }

    //Gibt Username zurück
    //Achtung, Username ist Hashverschlüsselt
    public String getUsername() {
        return username;
    }

    //Gibt Password zurück
    //Achtung, Passwort ist Hashverschlüsselt
    public String getPassword() {
        return password;
    }

    //Gibt Rolle zurück
    public String getRole() {
        return role;
    }


    //Gibt IntegrityKey zurück
    //IntegrityKey besteht zur Kontrolle der Rolle, Username und Passwort
    //3 kritische Merkmale
    // Wird aus den 3 Kompenten errechnet
    public String getIntegrityKey(){
        return integrityKey;
    }

    //Setter für Vorname
    //DB wird nicht automatisch geupdated, wird lediglich in geladener Liste sowie im User selbst aktualisiert
    public void setFirstname(String firstname) {
        int index=-1;
        for(int i=0; i<userlist.size();i++){
            if(userlist.get(i).equals(this)){

                    index=i;
                    break;
            }
        }
        this.firstname = firstname;
        userlist.get(index).firstname = firstname;
        updateUserDatabase();
        //System.out.println(userlist.size());
    }

    //Setter für Familienname
    //DB wird nicht automatisch geupdated, wird lediglich in geladener Liste sowie im User selbst aktualisiert
    //Zum update in DB updateMethode aufrufen

    public void setSurname(String surname) {
        int index=-1;
        for(int i=0; i<userlist.size();i++){
            if(userlist.get(i).equals(this)){
                    index=i;
                    break;

            }
        }
        userlist.get(index).surname = surname;
        this.surname = surname;
    }

    //Setter für Username
    //DB wird nicht automatisch geupdated, wird lediglich in geladener Liste sowie im User selbst aktualisiert
    //Zum update in DB updateMethode aufrufen

    public void setUsername(String username) {
        int index=-1;
        for(int i=0; i<userlist.size();i++){
            if(userlist.get(i).equals(this)){
                    index=i;
                    break;
            }
        }
        userlist.get(index).username = sha256(username);
        this.username = sha256(username);
        setIntegrityKey(sha256(this.username+this.password+sha256(this.role)));
        updateUserDatabase();
    }

    //Setter für Passwort
    //DB wird nicht automatisch geupdated, wird lediglich in geladener Liste sowie im User selbst aktualisiert
    //Zum update in DB updateMethode aufrufen
    public void setPassword(String password) {
        int index=-1;
        for(int i=0; i<userlist.size();i++){
            if(userlist.get(i).equals(this)){
                    index=i;
                    break;
            }
        }
        userlist.get(index).password = sha256(password);;
        this.password = sha256(password);
        setIntegrityKey(sha256(this.username+this.password+sha256(this.role)));
        updateUserDatabase();
    }

    //Setter für integrityKey
    //DB wird nicht automatisch geupdated, wird lediglich in geladener Liste sowie im User selbst aktualisiert
    //Zum update in DB updateMethode aufrufen
    private void setIntegrityKey(String integrityKey) {
        this.integrityKey = integrityKey;
        for(int i=0; i<userlist.size();i++){
            if(this.equals(userlist.get(i))){
                userlist.get(i).integrityKey = integrityKey;
            }
        }
    }

    //Rechner für integrityKey, gibt String zurück
    //DB wird nicht automatisch geupdated, wird lediglich in geladener Liste sowie im User selbst aktualisiert

    private String calculateIntegrityKey(String username, String password){
        return sha256(sha256(username)+sha256(password)+sha256(this.role));
    }

    //Einfacher vergleich von 2 Strings, ob Hashwert der gleiche ist
    public boolean compareHash(String input, String toCompare){
        if(sha256(input)==sha256(toCompare)){
            return true;
        }else{
            return false;
        }
    }

    //Methode zum laden der Daten aus dem json File
    //json einfach im Ordner abspeichern oder Pfad ändern
    public static void loadDataFromJson(){
        StringBuilder string = new StringBuilder();
        try {
            FileReader fileReader = new FileReader("users.json");
            int i;
            while((i = fileReader.read()) != -1){
                string.append((char)i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String JSONstring = string.toString();

        JSONObject jsonObj= new JSONObject(JSONstring);
        String checkSumJson = jsonObj.getString("CheckSum");
        JSONArray jsonArr = jsonObj.getJSONArray("Userlist");
        if(!(checkSumJson.equals(sha256(jsonArr.toString())))){
            System.out.println("JSON-File nicht gut bro");
            return;
        }
        ArrayList<User> userl = new ArrayList<>();
        for(int i=0; i<jsonArr.length(); i++){
            JSONObject jsonOb = jsonArr.getJSONObject(i);
            User user = new User(jsonOb.getString("firstname"), jsonOb.getString("surname"), jsonOb.getString("username"), jsonOb.getString("password"), false, jsonOb.getString("role"),jsonOb.getInt("userid"));
            if(user.getIntegrityKey().equals(jsonOb.getString("integrityKey"))){
                userl.add(user);
            }else{
                System.out.println("Userdata not integer -- User not loaded from DB");
            }
        }
        System.out.println("Userdbloaded with "+ userl.size());
        userlist = userl;
    }

    /*
    ///** METHODE VON STACKOVERFLOW --- https://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha256-in-java
    ///Methode zum berechnen eines Hash sha256 Werts.
    // Für Passwörter, Username etc.
     */

    public static String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }


    //toString Methode für die Ausgabe auf der Konsole
    //Zum testen
    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", integrityKey='" + integrityKey + '\'' +
                '}';
    }

    //Methode zum schauen ob ein User in der geladenen Liste existiert
    // ZUm checken in DB, DB laden und dann Methode ausführen
    public boolean checkIfUsernameExists(String username){
        for (int i=0; i<userlist.size();i++){
            if(userlist.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    //Aktuell geladene Liste in Datenbank (JSONFILE abspeichern)
    // Methode einfach aufrufen
    protected static void updateUserDatabase(){
        JSONArray jsonArray = new JSONArray();
        for(int i=0; i<userlist.size(); i++){
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("firstname", userlist.get(i).firstname);
            jsonobject.put("surname", userlist.get(i).surname);
            jsonobject.put("username", userlist.get(i).username);
            jsonobject.put("password", userlist.get(i).password);
            jsonobject.put("role", userlist.get(i).role);
            jsonobject.put("userid", userlist.get(i).userid);
            jsonobject.put("integrityKey", userlist.get(i).integrityKey);
            jsonArray.put(jsonobject);
        }
        try {
            //true besagt, dass daten angehängt werden sollen
            JSONObject jsonObjecttoFile = new JSONObject();
            jsonObjecttoFile.put("Userlist", jsonArray);
            jsonObjecttoFile.put("CheckSum", sha256(jsonArray.toString()));
            file = new FileWriter("users.json",false);
            file.write(jsonObjecttoFile.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Userdbupdated with "+ userlist.size());
    }

    //Wird benötigt für login
    //Returned User mit den korrespondierenden Daten
    public static final User getUserCredentials(String username, String password){
        User usera = null;
        for (User user : userlist) {
            if (user.getUsername().equals(sha256(username))) {
                if (user.getPassword().equals(sha256(password)) && user.getIntegrityKey().equals(user.calculateIntegrityKey(username, password))) {
                    usera = user;
                }
            }
        }

        return usera;

    }

    //Wird benötigt für login
    //DA Username und PW gehashed, checkt methode ob es ein User mit beidem gibt und returned antwort
    public static final boolean checkCredentials(String username, String password){

        boolean result = false;

        for (User user : userlist) {
            if (user.getUsername().equals(sha256(username))) {
                if (user.getPassword().equals(sha256(password)) && user.getIntegrityKey().equals(user.calculateIntegrityKey(username, password))) {
                    result = true;
                }
            }
        }
        return result;
    }
}
