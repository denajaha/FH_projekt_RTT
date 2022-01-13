package com.projekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BarPage {

    public BarPage() {}
    MainPage mainPage = new MainPage();
     //String[] listeDerGewähltenItems = new Arrays.toString(mainPage.getSubKey()); //Hier sollen alle Items die geklickt wurden eingefügt werden


    public void order(Object order) {

        int anzahl = 0;
        System.out.println(order);
        ArrayList<String> listeDerGewaehltenItems = new ArrayList<String>();
        listeDerGewaehltenItems.add(String.valueOf(order));
        if (listeDerGewaehltenItems == order) {
            anzahl += 1;
        }
        else {
            listeDerGewaehltenItems.add(String.valueOf(order));
        }
        System.out.println(anzahl + " x " + mainPage.printSubKey() + " " + mainPage.getPrice());


        //int anzahl = 7;
        //for (int i = 0; i < 7; i++) {//7 ist hier nur provisorisch und soll die "listeDerGewähltenItems" als zahl ausgeben
        //    //listeDerGewähltenItems = Arrays.toString(mainPage.getSubKey()); //Vielleicht für die +/- Buttons?
        //    System.out.println(anzahl + " x " + Arrays.toString(mainPage.getSubKey()) + " " + Arrays.toString(mainPage.getPrice()));//7 ist nur eine provisorische Anzahl von wie oft man das jeweilige item wollte
        //    System.out.println(); //Um immer einen Abstand zwischen den Zeilen zu lassen
        //}

    }
/*
    public void deleteItem(mainPage.getKey<mainPage.getSubKey()> listeDerGewähltenItems) { //Hier habe ich Von den Überkathegorien den Unterkathegorien dies Liste der gewählten Items eingegrenzt
        System.out.println("Wählen Sie das gewählte Produkt, welches Sie entfernen wollen: ");
        Scanner loeschen = new Scanner(System.in);
        String item = loeschen.nextLine();
        for (int i = 0; i < listeDerGewähltenItems.size(); i++) {
            getsubKeyFromJson item = listeDerGewähltenItems.get(i);
            if (item.loeschen == loeschen) { //Wenn das eingegebene auch existiert dann lösche eines
                listeDerGewähltenItems.remove(i);
                System.out.println("Gelöscht");
                anzahl --;//die provisorische anzahl von order
                return;
            }
        }
    }

    public void addItem(getKeyFromJson<getsubKeyFromJson> listeDerGewähltenItems) {
        System.out.println("Wählen Sie das gewählte Produkt, welches Sie hinzufügen wollen: ");
        Scanner hinzufuegen = new Scanner(System.in);
        String item = hinzufuegen.nextLine();
        for (int i = 0; i < listeDerGewähltenItems.size(); i++) {
            getsubKeyFromJson item = listeDerGewähltenItems.get(i);
            if (item.hinzufuegen == hinzufuegen) {
                listeDerGewähltenItems.add(i);
                System.out.println("Hinzugefügt");
                anzahl ++; //wieder anzahl von order
                return;
            }

        }




    }

 */

    public void total(String[] price) {
        float gesammt = 0;

        for (int i = 0; i < 7; i++) {
            gesammt += Float.parseFloat(price[i]);
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
        int x = 0;

        main.loadDataFromJson("Supermarkt.json");
        main.getKeyFromJson(main.getJSONstring());



        //main.getKeyFromJson(main.getJSONstring());
       // main.getsubKeyFromJson(main.getJSONstring(), "Lebensmittel");
        //main.getPriceFromsubKeys(main.getJSONstring(), "Lebensmittel");
        //s = main.getPrice();
        System.out.println("Wähle die Kathegorie 0 bis 10: ");
        keys = main.printKey();
        System.out.print("Auswahl: ");
        choice = scanner.nextInt();


        switch (choice) {
            case 0:
                main.getsubKeyFromJson(main.getJSONstring(), keys.get(0));
                System.out.println();
                subkeys = main.printSubKey();
                do {
                    System.out.print("Auswahl: ");
                    x = scanner.nextInt();
                    bar.order(subkeys.get(x));
                } while (x != 666);
            case 1:
                main.getsubKeyFromJson(main.getJSONstring(), keys.get(1));
                System.out.println();
                subkeys = main.printSubKey();
                do {
                    System.out.print("Auswahl: ");
                    x = scanner.nextInt();
                    bar.order(subkeys.get(x));
                } while (x != 666);
            case 2:
                main.getsubKeyFromJson(main.getJSONstring(), keys.get(2));
                System.out.println();
                subkeys = main.printSubKey();
                do {
                    System.out.print("Auswahl: ");
                    x = scanner.nextInt();
                    bar.order(subkeys.get(x));
                } while (x != 666);
            default:
                System.out.println("Auswahl nicht verfügbar.");




        }

        //bar.total(s);

    }
}


