package com.projekt;
import java.util.*;

public class BarPage {

    // Variable declaration
    private final ArrayList<String> listofallselecteditems = new ArrayList<>();
    private final Map<String, Float> finalorder = new TreeMap<>();
    private final ArrayList<Integer> clicks = new ArrayList<>();
    private final ArrayList<String> items = new ArrayList<>();
    private final ArrayList<Float> price = new ArrayList<>();
    private final Map<String, Integer> mp = new TreeMap<>();

    private float total = 0;

    /* Methods to be able to get the values Clicks/Total/finalorder etc --> They are Arraylist/Maps.
       Implement ArrayLists<String> or Map<String, Integer>
    */
    public float getTotal() { return total; }
    public ArrayList<Integer> getClicks() { return clicks; }
    public Map<String, Float> getFinalorder() { return finalorder; }
    public ArrayList<String> getlistofallselecteditems() { return listofallselecteditems;}

    // Public BarPage to be able to use the Methods --> Just implement BarPage bar = new Barpage()
    public BarPage() {}

    // Method to sort our duplicated clicked purchasing
    public void orderlist(ArrayList<String> list) {
        /* We need to create a Map to load our list inside to double check how often an item is in our List
           Loop to get our Subkeys from our list
        */
        for (int i = 0; i < listofallselecteditems.size(); i++) {
            // Checking if our Subkey is in our list and adding how often it was found
            if(mp.containsKey((list.get(i)))){
                mp.put(list.get(i), mp.get(list.get(i))+1);
            }
            else mp.put(list.get(i),1);
        }

        /* Loop to get our Values how often a Product was clicked/chosen for every Product one separate List entry
           items.add --> is a list with all chosen non duplicated products.
        */
        for(Map.Entry<String, Integer> entry: mp.entrySet()){
            items.add(entry.getKey());
            clicks.add(entry.getValue());
        }
    }

// These are features to add or remove an item to the order list
/* public void deleteItem(mainPage.getKey<mainPage.getSubKey()> listeDerGewähltenItems) { //Hier habe ich Von den Überkathegorien den Unterkathegorien dies Liste der gewählten Items eingegrenzt
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
    }*/

    // print Method to get the amount of clicked items and price per clicks.
    public void printorder (){
        for (int i = 0; i < clicks.size(); i++) {
            System.out.println(clicks.get(i)+ "x " + items.get(i) + " " + price.get(i) + "€");
        }
    }

    // The total price --> From finalorder: Sum of clicks * price
    public void total() {

        float[] multiplier = new float[clicks.size()];

        // Loop to get our price associated to the Order we choose
        for (Map.Entry<String, Float> entry: finalorder.entrySet()) {
            price.add(entry.getValue());
        }
            for (int i = 0; i < finalorder.size(); i++) {
               multiplier[i] = price.get(i) * clicks.get(i);
               total += multiplier[i];
            }
    }


    public static void main(String[] args) {
        // Created Objects --> MainPag and BarPage
        MainPage main = new MainPage();
        BarPage bar = new BarPage();

        Scanner scanner = new Scanner(System.in);

        // Variables to help us to choice between MainKey and SubKey
        int ChoiceMainKey;
        int ChoiceSubKey;

        // ArrayList to help us to store our Keys, Subkeys and Prices
        ArrayList<String> keys;
        ArrayList<String> subkeys;
        ArrayList<String> price;

        // Loading our Json File from Destiny
        main.loadDataFromJson("Supermarkt.json");

        // Loading our MainKeys from Json
        main.getKeyFromJson(main.getJSONstring());


        /* A Simple For-Loop to simulated clicks and a grocery store. Variable attempt how often we can choose
           between two MainCategories
        */
        for (int attempt  = 0; attempt < 3; attempt++) {

            System.out.println("Wähle die Kategorie 1 bis 11: ");
            main.printMainKey();
            keys = main.getMainKey();
            System.out.print("Auswahl: ");
            ChoiceMainKey = scanner.nextInt();

            // -1 to get the position from list because the list is starting with position 0 like an Array
            ChoiceMainKey -= 1;

            // Loading SubKeys from MainPage and also the Prices of the respective SubKey
            main.getsubKeyFromJson(keys.get(ChoiceMainKey));
            main.getPriceFromsubKeys( keys.get(ChoiceMainKey));

            // Saving our Subkeys and Prices into a List to work with them in our Simulation
            subkeys = main.getSubKey();
            price = main.getPrice();

            // Placeholder
            System.out.println();

            main.printSubKey();

            // A simple For-Loop to simulate five products from same MainCategory in our Shopping venture
            for (int i = 0; i < 5; i++) {

                System.out.print("Auswahl: ");
                ChoiceSubKey = scanner.nextInt();

                // -1 to get the position from list because the list is starting with position 0 like an Array
                ChoiceSubKey -= 1;

                // We are adding all choices to our List --> listofallselecteditems (also duplicates)
                bar.listofallselecteditems.add(subkeys.get(ChoiceSubKey));
                /* We are adding to our choices the price so customer get an overview
                   The Map is also need for the Total Methods to calculate the Total amount --> See public void Total()
                 */
                bar.finalorder.put(subkeys.get(ChoiceSubKey), Float.parseFloat(price.get(ChoiceSubKey)));
            }

            // Placeholder
            System.out.println();

            // !!! Important the Lists has to be cleared after a use !!!
            main.clearSubKeylist();
            main.clearPricelist();
            main.ResetPriceCount();
            main.ResetSubKeyCount();

            // !!! Also our Variable/Lists for Simulating need to be cleared after using them !!!
            subkeys.clear();
            price.clear();
        }

        // Method to eliminate duplicates but also to count them --> Simulating clicks
        bar.orderlist(bar.listofallselecteditems);

        // Method to calculate the total amount of price
        bar.total();

        // Placeholder
        System.out.println();

        bar.printorder();

        System.out.println("Total: " + bar.getTotal() + "€");

    }
}


