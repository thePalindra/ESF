package pt.unl.fct.di.www.zoom.ui.classes;

import java.util.ArrayList;
import java.util.List;

public class Server {

    public String name;
    public User admin;
    public List<User> users;
    public List<Chat> chats;
    public List<Meeting> calls;


    public Server(String name, User admin) {
        this.name = name;
        this.admin = admin;
        this.users = new ArrayList<User>();
        this.chats = new ArrayList<Chat>();
        this.calls = new ArrayList<Meeting>();
        this.users.add(this.admin);
    }

    public void addChat(Chat chat) {
        this.chats.add(chat);
    }

    public void addCall (Meeting meeting) {
        this.calls.add(meeting);
    }
}
