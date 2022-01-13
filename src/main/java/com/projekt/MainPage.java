package com.projekt;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainPage {

    // Variable Declarations
    private final ArrayList<String> MainKey = new ArrayList<String>();
    private final ArrayList<String> SubKey = new ArrayList<String>();
    private final ArrayList<String> Price = new ArrayList<String>();
    private String JSONstring;


    private static int KeyCount = 0;
    private static int subKeyCount = 0;
    private static int pricecount = 0;

    // Public MainPage to be able to use my Methods --> Just implement MainPage main = new MainPage()
    public MainPage() {}


    // Methods to be able to get the values Price/Key/SubKey --> They are all in Arraylist. Implement ArrayLists<String>
    public String getJSONstring() {
        return JSONstring;
    }
    public ArrayList<String> getMainKey() { return MainKey; }
    public ArrayList<String> getSubKey() { return SubKey; }
    public ArrayList<String> getPrice() { return Price; }

    // It is Loading the whole JSON File and its saving into JSONstring --> Its needed to get Keys and SubKey etc.
    public void loadDataFromJson(String FileName) {
        StringBuilder string = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(FileName);
            int i;
            while ((i = fileReader.read()) != -1) {
                string.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.JSONstring = string.toString();
    }

    // It gets the main keywords from our JSON file
    public void getKeyFromJson(String JSONstring) {
        JSONObject jsonObj = new org.json.JSONObject(JSONstring);

        // It is a better version for while-Loop --> while(xyz.hasNext())
        // The loop keep going through the JSON file till jsonObs.keySet() = xyz.hasNext() = NULL
        for (String value : jsonObj.keySet()) {
            MainKey.add(value);
            KeyCount++;
        }

    }

    // It gets the sub keywords from our JSON file
    public void getsubKeyFromJson(String JSONstring, String Key) {
        JSONObject jsonObj = new org.json.JSONObject(JSONstring);
        JSONArray jsonArr = jsonObj.getJSONArray(Key);
        JSONObject jsonObs = jsonArr.getJSONObject(subKeyCount);

        // It is a better version for while-Loop --> while(xyz.hasNext())
        // The loop keep going through the JSON file till jsonObs.keySet() = xyz.hasNext() = NULL
        for (String value : jsonObs.keySet()) {
            SubKey.add(value);
            subKeyCount++;
        }
    }

    //It gets the prices from our JSON file. But be carefull the Values are Strings. The reason that for is --> jsonObs.getString
    public void getPriceFromsubKeys (String JSONstring, String Key){

        //We have to creat our JsonObject through the JSONstring. The JSONstring contains the whole Filedata.
        JSONObject jsonObj = new org.json.JSONObject(JSONstring);
        //Our Subkeys are inside JSONArrays. On top is our main keyword
        JSONArray jsonArr = jsonObj.getJSONArray(Key);
        JSONObject jsonObs = jsonArr.getJSONObject(pricecount);

        //A simple for-loop to get through all Subkeys. --> subKeyCount = Amount of Subkeys
        for(int i=0; i < subKeyCount; i++ ){
            Price.add(jsonObs.getString(SubKey.get(pricecount)));
            pricecount ++;
        }
    }

    //Its a print Method to have a look about all Keys
    public void printMainKey() {
        for (int i = 0; i < KeyCount; i++){
            System.out.println(i+1 + " " + MainKey.get(i));
        }
    }

    //Its a print Method to have a look about all Subkeys
    public void printSubKey(){
        for (int i = 0; i < subKeyCount; i++){
            System.out.println(i+1 + " " + SubKey.get(i));
        }
    }

    //Its a print Method for a visualization Subkeys + Price in Doller
    public void printSubkeywithPrice(){
        for (int i = 0; i < subKeyCount; i++) {
            System.out.println(i+1 + " " + SubKey.get(i)+ " " + Price.get(i) + "$");
        }
    }

    public static void main(String[] args) {
        MainPage Main = new MainPage();
        ArrayList<String> Key;
        ArrayList<String> SubKey;
        ArrayList<String> Price;

        Main.loadDataFromJson("Supermarkt.json");

        Main.getKeyFromJson(Main.getJSONstring());
        Key = Main.getMainKey();
        Main.printMainKey();

        System.out.println();
        Main.getsubKeyFromJson(Main.getJSONstring(), Key.get(1));
        SubKey = Main.getSubKey();
        Main.printSubKey();

        System.out.println();
        Main.getPriceFromsubKeys(Main.getJSONstring(), Key.get(1));
        Price = Main.getPrice();
        Main.printSubkeywithPrice();

    }

}



