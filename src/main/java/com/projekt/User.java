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
    private static ArrayList<User> userlist = new ArrayList<User>();
    private static FileWriter file;
    private String firstname;
    private String surname;
    private String username;
    private String password;
    //Admin, Supervisor, Kassierer ?
    private String role;

    public User(String firstname, String surname, String username, String password, String role) {
        users++;
        this.firstname = firstname;
        this.surname = surname;
        this.username = username;
        this.password = sha256(password);
        this.role = role;
        userlist.add(this);
    }

    public static ArrayList<User> getUsers() {
        return userlist;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = sha256(password);;
    }


    public boolean compareHash(String input, String toCompare){
        if(sha256(input)==sha256(toCompare)){
            return true;
        }else{
            return false;
        }
    }

    public void loadDataFromJson(){
        //Parser benutzen ?
    }

    ///** METHODE VON STACKOVERFLOW --- https://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha256-in-java
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


    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    protected static void updateUserDatabase(){
        //Nur ein Versuch
        JSONArray jsonArray = new JSONArray();
        for(int i=0; i<userlist.size(); i++){
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("firstname", userlist.get(i).firstname);
            jsonobject.put("surname", userlist.get(i).surname);
            jsonobject.put("username", userlist.get(i).username);
            jsonobject.put("password", userlist.get(i).password);
            jsonobject.put("role", userlist.get(i).role);
            System.out.println(jsonobject.toString());
            jsonArray.put(jsonobject);
        }
        try {
            //true besagt, dass daten angehängt werden sollen
            JSONObject jsonObjecttoFile = new JSONObject();
            jsonObjecttoFile.put("Userlist", jsonArray);
            file = new FileWriter("users.json",false);
            file.write(jsonObjecttoFile.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
