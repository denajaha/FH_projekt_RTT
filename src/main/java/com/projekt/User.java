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

   private User(String firstname, String surname, String username, String password, boolean newUser, String role) {
        users++;
        this.firstname = firstname;
        this.surname = surname;
        this.username = username;
        if(newUser==true){
            this.password = sha256(password);
        }else{
            this.password = password;
        }
        this.role = role;
        userlist.add(this);
    }

    public static User createNewUser(String firstname, String surname, String username, String password, String role){
        return new User(firstname,surname,username,password, true,role);
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
        int index=-1;
        for(int i=0; i<userlist.size();i++){
            if(userlist.get(i).getFirstname().equals(this.firstname)){
                if(userlist.get(i).getFirstname().equals(this.firstname)
                        && userlist.get(i).getSurname().equals(this.surname)
                        && userlist.get(i).getUsername().equals(this.username)
                        && userlist.get(i).getPassword().equals(this.password)
                        && userlist.get(i).getRole().equals(this.role)){
                    index=i;
                    break;
                }
            }
        }
        userlist.get(index).firstname = firstname;
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        int index=-1;
        for(int i=0; i<userlist.size();i++){
            if(userlist.get(i).getFirstname().equals(this.surname)){
                if(userlist.get(i).getFirstname().equals(this.firstname)
                        && userlist.get(i).getSurname().equals(this.surname)
                        && userlist.get(i).getUsername().equals(this.username)
                        && userlist.get(i).getPassword().equals(this.password)
                        && userlist.get(i).getRole().equals(this.role)){
                    index=i;
                    break;
                }
            }
        }
        userlist.get(index).firstname = surname;
        this.surname = surname;
    }

    public void setUsername(String username) {
        int index=-1;
        for(int i=0; i<userlist.size();i++){
            if(userlist.get(i).getFirstname().equals(this.username)){
                if(userlist.get(i).getFirstname().equals(this.firstname)
                        && userlist.get(i).getSurname().equals(this.surname)
                        && userlist.get(i).getUsername().equals(this.username)
                        && userlist.get(i).getPassword().equals(this.password)
                        && userlist.get(i).getRole().equals(this.role)){
                    index=i;
                    break;
                }
            }
        }
        userlist.get(index).firstname = username;
        this.username = username;
    }

    public void setPassword(String password) {
        int index=-1;
        for(int i=0; i<userlist.size();i++){
            if(userlist.get(i).getFirstname().equals(this.password)){
                if(userlist.get(i).getFirstname().equals(this.firstname)
                        && userlist.get(i).getSurname().equals(this.surname)
                        && userlist.get(i).getUsername().equals(this.username)
                        && userlist.get(i).getPassword().equals(this.password)
                        && userlist.get(i).getRole().equals(this.role)){
                    index=i;
                    break;
                }
            }
        }
        userlist.get(index).firstname = sha256(password);;
        this.password = sha256(password);;
    }


    public boolean compareHash(String input, String toCompare){
        if(sha256(input)==sha256(toCompare)){
            return true;
        }else{
            return false;
        }
    }

    public static void loadDataFromJson(){
        //Noch nicht fertig
        StringBuilder string = new StringBuilder();
        try {
            FileReader fileReader = new FileReader("users.json");
            int i;
            while((i = fileReader.read()) != -1){
                System.out.print((char)i);
                string.append((char)i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String JSONstring = string.toString();

        JSONObject jsonObj= new JSONObject(JSONstring);
        JSONArray jsonArr = jsonObj.getJSONArray("Userlist");
        ArrayList<User> userl = new ArrayList<>();
        for(int i=0; i<jsonArr.length(); i++){
            JSONObject jsonOb = jsonArr.getJSONObject(i);
            User user = new User(jsonOb.getString("firstname"), jsonOb.getString("surname"), jsonOb.getString("username"), jsonOb.getString("password"), false, jsonOb.getString("role"));
            userl.add(user);
        }
        userlist = userl;
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
