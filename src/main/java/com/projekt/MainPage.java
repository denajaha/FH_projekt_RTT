package com.projekt;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainPage {

    // Variable Declarations
    private final ArrayList<String> MainKey = new ArrayList<>();
    private final ArrayList<String> SubKey = new ArrayList<>();
    private final ArrayList<String> Price = new ArrayList<>();
    private String JSONstring;


    private  int KeyCount = 0;
    private  int subKeyCount = 0;
    private  int pricecount = 0;

    // Public MainPage to be able to use my Methods --> Just implement MainPage main = new MainPage()
    public MainPage() {}


    // Methods to be able to get the values Price/Key/SubKey --> They are all in Arraylist. Implement ArrayLists<String>
    public String getJSONstring() {
        return JSONstring;
    }
    public ArrayList<String> getMainKey() { return MainKey; }
    public ArrayList<String> getSubKey() { return SubKey; }
    public ArrayList<String> getPrice() { return Price; }
    public int getKeyCount() { return KeyCount; }
    public int getSubKeyCount() { return subKeyCount; }
    public int getPricecount() { return pricecount; }

    // It is Loading the whole JSON File and its saving into JSONstring --> It's needed to get Keys and SubKey etc.
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

    public JSONObject creatJsonObject (String Key){
        loadDataFromJson("Supermarkt.json");
        JSONObject jsonObj = new org.json.JSONObject(getJSONstring());
        JSONArray jsonArr = jsonObj.getJSONArray(Key);

        return jsonArr.getJSONObject(0);
    }

    // It gets the main keywords from our JSON file
    public void getKeyFromJson(String JSONstring) {
        JSONObject jsonObj = new org.json.JSONObject(JSONstring);

        /* It is a better version for while-Loop --> while(xyz.hasNext())
           The loop keep going through the JSON file till jsonObs.keySet() = xyz.hasNext() = NULL
        */
        for (String value : jsonObj.keySet()) {
            MainKey.add(value);
            KeyCount++;
        }

    }

    // It gets the sub keywords from our JSON file
    public void getsubKeyFromJson(String Key) {
        JSONObject jsonObs = creatJsonObject(Key);

        /* It is a better version for while-Loop --> while(xyz.hasNext())
           The loop keep going through the JSON file till jsonObs.keySet() = xyz.hasNext() = NULL
        */
        for (String value : jsonObs.keySet()) {
            SubKey.add(value);
            subKeyCount++;
        }
    }

    /* It gets the prices from our JSON file.
       But be carefull the Values are Strings. The reason that for is --> jsonObs.getString
    */
    public void getPriceFromsubKeys (String Key){

        /* We have to create our JsonObject through the JSONstring. The JSONstring contains the whole file data.
           Our SubKeys are inside JSONArrays. On top is our main keyword
        */
        JSONObject jsonObs = creatJsonObject(Key);

        // A simple for-loop to get through all SubKeys. --> subKeyCount = Amount of SubKeys
        for(int i=0; i < subKeyCount; i++ ){
            Price.add(jsonObs.getString(SubKey.get(pricecount)));
            pricecount ++;
        }
    }

    // It's a print Method to have a look about all Keys
    public void printMainKey() {
        for (int i = 0; i < KeyCount; i++){
            System.out.println(i+1 + " " + MainKey.get(i));
        }
    }

    // It's a print Method to have a look about all SubKeys
    public void printSubKey(){
        for (int i = 0; i < subKeyCount; i++){
            System.out.println(i+1 + " " + SubKey.get(i));
        }
    }

    // It's a print Method for a visualization SubKeys + Price in Euro
    public void printSubkeywithPrice(){
        for (int i = 0; i < subKeyCount; i++) {
            System.out.println(i+1 + " " + SubKey.get(i)+ " " + Price.get(i) + "€");
        }
    }

    // Methods to clear the Lists
    public  void clearSubKeylist () { SubKey.clear();}
    public  void clearPricelist () { Price.clear(); }
    public void clearMainKeyList () { MainKey.clear(); }
    public  void ResetPriceCount () { pricecount = 0; }
    public  void ResetSubKeyCount () { subKeyCount = 0; }
    public  void ResetKeyCount () { KeyCount = 0; }

    // Method to clear all Lists at Once
    public  void ClearAllArrayLists (){
        SubKey.clear();
        Price.clear();
        MainKey.clear();
    }

    public  void ResetCountVariables (){
        KeyCount = 0;
        subKeyCount = 0;
        pricecount = 0;
    }

    public static void main(String[] args) {
        MainPage Main = new MainPage();
        ArrayList<String> Key;

        Main.loadDataFromJson("Supermarkt.json");

        Main.getKeyFromJson(Main.getJSONstring());
        Key = Main.getMainKey();
        Main.printMainKey();

        System.out.println();
        Main.getsubKeyFromJson(Key.get(0));
        //SubKey = Main.getSubKey();
        Main.printSubKey();

        System.out.println();
        Main.getPriceFromsubKeys(Key.get(0));
        //Price = Main.getPrice();
        Main.printSubkeywithPrice();



    }

}



