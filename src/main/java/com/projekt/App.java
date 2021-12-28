package com.projekt;

public class App {

    public static void main(String[] args) {
        System.out.println("hello");
        User user1 = new User("Max", "Mustermann", "MaMu", "test", "admin");
        System.out.println(user1.toString());
        User user2 = new User("Max", "Mustermann", "MaMu", "test", "admin");
        System.out.println(user1.toString());
        User.updateUserDatabase();

    }
}
