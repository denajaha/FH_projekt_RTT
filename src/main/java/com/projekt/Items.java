package com.projekt;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Items {

    private String[] items = new String[999999];

    public static void readItemfromJson() {
            JSONParser parser = new JSONParser();

            try{
                Object obj = parser.parse(new FileReader("C:\\Users\\tinko\\IdeaProjects\\FH_projekt_RTT\\src\\main\\java\\com\\projekt\\Supermarkt.json"));

                JSONObject jsonObject = (JSONObject) obj;

                String[] array = new String[10];

                //array = getKeyWordfromJson(jsonObject);

                JSONArray Lebensmittel = (JSONArray) jsonObject.get("Gemüse");
                Iterator<String> iterator = Lebensmittel.iterator();
                while (iterator.hasNext()){
                    System.out.println(iterator.next());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
    }

    public static String[] getKeyWordfromJson (JSONObject jsonObject){ //Hiermit kann man die Keywords der Json Datei erhalten
        int counter = 0;
        Iterator<String> keys = jsonObject.keySet().iterator();
        //To count the length of the Array
        while(keys.hasNext()) counter++;
        String[] keyword = new String[counter];

        return keyword;
    }

    public static void main(String[] args) {
        readItemfromJson();
    }


}
