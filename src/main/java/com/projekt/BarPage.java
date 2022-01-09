package com.projekt;

import java.util.Scanner;

public class BarPage {

    public static void order() {
        for (int i = 0; i < anzahlGeklickterItems; i++) {
            System.out.println(anzahl + "x" + item(Subkey) + preisDesEinzelnenItems);
            System.out.println(); //Um immer einen Abstand zwischen den Zeilen zu lassen
        }

    }
    public static void deleteItem(Keyword<Subkey> listeDerGewähltenItems) { //Hier habe ich Von den Überkathegorien den Unterkathegorien dies Liste der gewählten Items eingegrenzt
        System.out.println("Wählen Sie das gewählte Produkt, welches Sie entfernen wollen: ");
        Scanner loeschen = new Scanner(System.in);
        String item = loeschen.nextLine();
        for (int i = 0; i < listeDerGewähltenItems.size(); i++) {
            Subkey item = listeDerGewähltenItems.get(i);
            if (item.loeschen == loeschen) { //Wenn das eingegebene auch existiert dann lösche eines
                listeDerGewähltenItems.remove(i);
                System.out.println("Gelöscht");
                anzahl --;//die anzahl von order
                return;
            }
        }
    }

    public static void addItem(Keyword<Subkey> listeDerGewähltenItems) {
        System.out.println("Wählen Sie das gewählte Produkt, welches Sie hinzufügen wollen: ");
        Scanner hinzufuegen = new Scanner(System.in);
        String item = hinzufuegen.nextLine();
        for (int i = 0; i < listeDerGewähltenItems.size(); i++) {
            Subkey item = listeDerGewähltenItems.get(i);
            if (item.hinzufuegen == hinzufuegen) {
                listeDerGewähltenItems.add(i);
                System.out.println("Hinzugefügt");
                anzahl ++; //wieder anzahl von order
                return;
            }

        }


    }

    public static void total() {
        for (int i = 0; i < anzahlGeklickterItems; i++) { //Frage wie das mit der multiplikation aussieht? Reicht gesammt = anzahl * preisDesEinzelnenItems?
            gesammt[i] = anzahl[i] * preisDesEinzelnenItems;
            endsumme += gesammt[i];
        }
        System.out.println("Der Gesammtpreis beträgt: ");
        System.out.println(endsumme);

    }


    public static void main(){
        order();
        total();
    }
}


