package pt.unl.fct.di.www.zoom.ui.classes;

import java.util.ArrayList;
import java.util.List;

public class Meeting {

    public String description;
    public int id;
    public List<User> participants;
    public List<User> online;
    public Chat chat;
    public User admin;
    public boolean available;

    public Meeting(String description, int id) {
        this.description = description;
        this.id = id;
        this.available = false;
        this.admin = null;
        this.participants = new ArrayList<User>(10);
        this.online = new ArrayList<User>(10);
        this.chat = null;
        this.participants.add(admin);
        //this.admin.meetings.add(this);

    }

    public Meeting(String description, int id, User admin, Chat chat) {
        this.description = description;
        this.id = id;
        this.available = false;
        this.admin = admin;
        this.participants = new ArrayList<User>(10);
        this.online = new ArrayList<User>(10);
        this.chat = chat;
        this.participants.add(admin);
        this.admin.meetings.add(this);
    }

    public boolean start() {
        this.available = true;
        this.online.add(admin);
        this.admin.currentMeeting = this;
        return available;
    }

    public boolean end() {
        this.available = false;
        this.admin.leaveCurr();
        this.chat = new Chat ("Global");
        this.online = new ArrayList<User>(10);
        return available;
    }


}
