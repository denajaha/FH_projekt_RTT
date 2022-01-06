package com.projekt;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Items {

    public static void loadDataFromJson() {
        StringBuilder string = new StringBuilder();
        try {
            FileReader fileReader = new FileReader("Supermarkt.json");
            int i;
            while ((i = fileReader.read()) != -1) {
                //System.out.print((char) i);
                string.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String JSONstring = string.toString();

        // Muss so erweitert werden damit der String dynamisch wird
        int counter=0;
        String[] price = new String[100];
        String[] keyword = new String[100];

        //Dynamische Funktion um die Keywords herauszubekommen falls man diese nicht kennt aus der JSON Datei
        JSONObject jsonObj= new org.json.JSONObject(JSONstring);
        Iterator<String> keys = jsonObj.keySet().iterator();
        while (keys.hasNext()){
            keyword[counter] = keys.next();
            counter++;
        }
        JSONArray jsonArr = jsonObj.getJSONArray(keyword[0]);
        String[] Test = new String[100];

        //Dynamische Funktion um die SubKeywords aus dem Array herauszubekommen falls man diese nicht kennt
        int test =0;
        JSONObject jsonObs = jsonArr.getJSONObject(test);
        Iterator<String > penis = jsonObs.keySet().iterator();
        while(penis.hasNext()) {
            Test[test] = penis.next();
            test ++;
        }

        // Muss erweitert werden um Sub-Keys zu bekommen ohne diese zu wissen um dieses universel zu machen
        for(int i=0; i<5; i++){
            //JSONObject jsonOb = jsonArr.getJSONObject(i);
            price[i] = jsonObs.getString(Test[i]);
            //keyword[0] = jsonOb.getString("Konserven");
            //keyword[1] = jsonOb.getString("Essig");
        }

        float[] parser = new float[100];

        // muss verbessert werden um die Preise von Strings in Floating Zahlen umzuwandeln
        for(int i =0; i<5;i++) {
            parser[i] = Float.parseFloat(price[i]);
            System.out.print(Test[i]);
            System.out.print(" ");
            System.out.println(parser[i]);
        }



    }
    public static void main(String[] args) {
        loadDataFromJson();
    }




}
