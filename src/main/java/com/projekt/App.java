package com.projekt;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        /* Testmethoden Antoine*/

        System.out.println("hello");
        User user1 = new User("Max", "Mustermann", "MaMu", "test", true, "admin");
        System.out.println(user1.toString());
        User user2 = new User("Max", "Mustermann", "MaMu", "test", true, "admin");
        System.out.println(user1.toString());
        User.updateUserDatabase();
        User.loadDataFromJson();
        ArrayList<User> userlist = User.getUsers();
        System.out.println(userlist.get(0).getFirstname());
        user1.setFirstname("Peter");
        ArrayList<User> userliste = User.getUsers();
        System.out.println(userliste.get(0).getFirstname());



    }
}
