package com.projekt;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MainPage {

    private final String[] price = new String[50];
    private final String[] key = new String[50];
    private final String[] subKey = new String[50];
    private String JSONstring;


    private static int KeyCount = 0;
    private static int subKeyCount = 0;

    public MainPage() {}

    public String getJSONstring() {
        return JSONstring;
    }
    public String[] getKey() {
        return key;
    }
    public String[] getSubKey() {
        return subKey;
    }
    public String[] getPrice() {
        return price;
    }

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

    public void getKeyFromJson(String JSONstring) {

        JSONObject jsonObj = new org.json.JSONObject(JSONstring);
        // Ist eine while-Schleife mit --> while(xyz.hasNext())
        for (String value : jsonObj.keySet()) {
            this.key[KeyCount] = value;
            KeyCount++;
        }

    }

    public void getsubKeyFromJson(String JSONstring, String Key) {
        JSONObject jsonObj = new org.json.JSONObject(JSONstring);
        JSONArray jsonArr = jsonObj.getJSONArray(Key);
        JSONObject jsonObs = jsonArr.getJSONObject(subKeyCount);
        for (String s : jsonObs.keySet()) {
            this.subKey[subKeyCount] = s;
            subKeyCount++;
        }
    }

    public void getPriceFromsubKeys (String JSONstring, String Key){
        int pricecount = 0;
        JSONObject jsonObj = new org.json.JSONObject(JSONstring);
        JSONArray jsonArr = jsonObj.getJSONArray(Key);
        JSONObject jsonObs = jsonArr.getJSONObject(pricecount);

        for(int i=0; i < subKeyCount; i++ ){
            this.price[pricecount] = jsonObs.getString(this.subKey[pricecount]);
            pricecount ++;
        }
    }

    @Override
    public String toString() {
        return "Subkey: " + this.price[3];
    }
    //float[] parser = new float[100];

    // muss verbessert werden um die Preise von Strings in Floating Zahlen umzuwandeln
        /*for(int i =0; i<5;i++) {
            parser[i] = Float.parseFloat(price[i]);
            System.out.print(Test[i]);
            System.out.print(" ");
            System.out.println(parser[i]);
        }
        System.out.println(parser[0]+parser[1]);*/


    public static void main(String[] args) {
        MainPage Test = new MainPage();
        Test.loadDataFromJson("Supermarkt.json");



        Test.getKeyFromJson(Test.getJSONstring());
        Test.getsubKeyFromJson(Test.getJSONstring(), "Lebensmittel");
        Test.getPriceFromsubKeys(Test.getJSONstring(), "Lebensmittel");


    }

}



