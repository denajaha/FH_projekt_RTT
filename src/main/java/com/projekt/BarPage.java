package com.projekt;

import java.time.Period;
import java.util.*;
import java.util.logging.Handler;

public class BarPage {

    MainPage main = new MainPage();
    private final ArrayList<String> listofchoosenitems = new ArrayList<String>();
    private final ArrayList<String> order = new ArrayList<String>();
    private final ArrayList<Integer> orderamount = new ArrayList<Integer>();


    public ArrayList<String> getlistofchoosenitems() { return listofchoosenitems;}
    public ArrayList<String> getOrder() { return order; }
    public ArrayList<Integer> getOrderamount() { return orderamount; }

    public BarPage() {}

    public void orderlist(ArrayList<String> list) {
        // We need to creat a Map to load our list inside to double check how often an Item is in our List
        Map<String, Integer> mp = new TreeMap<>();//???

        // Loop to get our Subkeys from our list
        for (int i = 0; i < listofchoosenitems.size(); i++) {
            // Checking if our Subkey is in our list and adding how often it was found
            if(mp.containsKey((list.get(i)))){
                mp.put(list.get(i), mp.get(list.get(i))+1);
            }
            else mp.put(list.get(i),1);
        }

        // Loop to get our Values and Keys in our Public list to get excess
        for(Map.Entry<String, Integer> entry: mp.entrySet()){
            order.add(entry.getKey());
            orderamount.add(entry.getValue());
        }
    }



    public void deleteItem(ArrayList<String> array) {
        System.out.println("Wählen Sie das gewählte Produkt, welches Sie entfernen wollen: ");
        Scanner loeschen = new Scanner(System.in);
        String ID = loeschen.nextLine();
        for (int i = 0; i < listofchoosenitems.size(); i++) {
            main.getsubKeyFromJson() item = listofchoosenitems.get(i);
            if (item.ID == ID) { //Wenn das eingegebene auch existiert dann lösche eines
                listofchoosenitems.remove(i);
                System.out.println("Gelöscht");
                orderamount --;//die provisorische anzahl von order
                return;
            }
        }
    }

    public void addItem(ArrayList<String> array) {
        System.out.println("Wählen Sie das gewählte Produkt, welches Sie hinzufügen wollen: ");
        Scanner hinzufuegen = new Scanner(System.in);
        String item = hinzufuegen.nextLine();
        for (int i = 0; i < listofchoosenitems.size(); i++) {
            main.getsubKeyFromJson() item = listofchoosenitems.get(i);
            if (item. == hinzufuegen) {
                listofchoosenitems.add(i);
                System.out.println("Hinzugefügt");
                orderamount ++; //wieder anzahl von order
                return;
            }

        }




    }



    public void total(float[] price) {
        float gesammt = 0;
        for (int i = 0; i < listofchoosenitems.size(); i++) {
            gesammt = gesammt + (orderamount.get() * main.getPrice());//Float.parseFloat(String.valueOf(price[i])) );//gesammt = gesammt + (anzahl des Produkts * preis des Produkts)
        }
        System.out.print("Der Gesammtpreis beträgt: ");
        System.out.print(gesammt);

    }


    public static void main(String[] args) {

        MainPage main = new MainPage();
        BarPage bar = new BarPage();
        String[] s = new String[50];
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        ArrayList<String> keys;
        ArrayList<String> subkeys;
        ArrayList<Integer> test = new ArrayList<Integer>();
        int x = 0;

        main.loadDataFromJson("Supermarkt.json");
        main.getKeyFromJson(main.getJSONstring());



        //main.getKeyFromJson(main.getJSONstring());
        // main.getsubKeyFromJson(main.getJSONstring(), "Lebensmittel");
        //main.getPriceFromsubKeys(main.getJSONstring(), "Lebensmittel");
        //s = main.getPrice();
        System.out.println("Wähle die Kathegorie 1 bis 11: ");
        main.printMainKey();
        keys = main.getMainKey();
        System.out.print("Auswahl: ");
        choice = scanner.nextInt();

        choice -= 1;

        switch (choice) {
            case 0:
                main.getsubKeyFromJson(main.getJSONstring(), keys.get(0));
                System.out.println();
                main.printSubKey();
                subkeys = main.getSubKey();
                for (int i = 0; i < 5; i++) {
                    System.out.print("Auswahl: ");
                    x = scanner.nextInt();
                    x -= 1;
                    bar.listofchoosenitems.add(subkeys.get(x));//??? hier fügst du das gewählte der liste hinzu
                }
                bar.orderlist(bar.listofchoosenitems);
                for (int i = 0; i < bar.order.size(); i++) {
                    System.out.println(bar.orderamount.get(i)+"x "+bar.order.get(i));//??? fehlt doch noch preis oder?
                }
                System.out.println();
                break;

            default:
                System.out.println("Auswahl nicht verfügbar.");
                break;

        }


    }
}


