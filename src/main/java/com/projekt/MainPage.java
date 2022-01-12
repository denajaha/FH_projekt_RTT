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
    public static int getSubKeyCount() {
        return subKeyCount;
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


    public ArrayList print() {
        ArrayList<String> value = new ArrayList<String>();

        for (int i = 0; i < KeyCount; i++){
            value.add(this.key[i]);
            System.out.println(i+1 + " " + value.get(i));
        }


        return value;
    }

    public static void main(String[] args) {
        MainPage Test = new MainPage();
        Test.loadDataFromJson("Supermarkt.json");


        Test.getKeyFromJson(Test.getJSONstring());
        Test.getsubKeyFromJson(Test.getJSONstring(), "Lebensmittel");
        Test.getPriceFromsubKeys(Test.getJSONstring(), "Lebensmittel");

    }

}



