package com.projekt;
import java.security.Timestamp;
import java.text.SimpleDateFormat;

public class Session {
    public User user;
    public String start;

    public Session(User user) {
        this.user = user;
        start = "Hello";
        //start =  new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp());
    }

    public User getUser() {
        return user;
    }

    public String getStart(){
        return start;
    }
}
