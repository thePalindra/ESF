package pt.unl.fct.di.www.zoom.ui.classes;

import java.util.ArrayList;
import java.util.List;

public final class Examples {

    public static List<User> users = new ArrayList<User>();
    public static List<Server> servers = new ArrayList<Server>();
    public static List<Meeting> meetings = new ArrayList<Meeting>();
    public static Server currentServer = null;
    public static Chat currentChat = null;


    public static void examples() {
        users();
        servers();
        meetings();
    }

    public static void users() {
        Profile a,b,c,d,e;

        a = new Profile("Maria", "MariaAna", "F");
        b = new Profile("Pedro", "p123", "M");
        c = new Profile("Donatelo", "Dona_hotelo");
        d = new Profile("Admin", "admin", "Tavira", "F");
        e = new Profile("Garcia", "pj1", "Faro");

        User f,g,h,i,j;

        f = new User(a);
        g = new User(b);
        h = new User(c);
        i = new User(d);
        j = new User(e);

        users.add(f);
        users.add(g);
        users.add(h);
        users.add(i);
        users.add(j);

    }

    public static void servers() {
        Server a,b;
        a = new Server("Server 1", users.get(0));
        b = new Server("Server 2", users.get(0));

        Chat c = new Chat("geral");
        Chat d = new Chat("memes");

        Meeting e = new Meeting("geral", 3);

        a.chats.add(c);
        a.chats.add(d);
        b.chats.add(d);
        a.calls.add(e);

        servers.add(a);
        servers.add(b);

        users.get(0).joinServer(a);
        users.get(0).joinServer(b);

    }

    public static void meetings() {
        Meeting a,b,c;

        a = new Meeting("description1", 1, users.get(0), new Chat ("Global"));
        b = new Meeting("description1", 1, users.get(1), new Chat ("Global"));
        c = new Meeting("description1", 1, users.get(0), new Chat ("Global"));

        addToMeeting(users.get(1), a);
        addToMeeting(users.get(2), a);
        addToMeeting(users.get(4), b);
        addToMeeting(users.get(1), c);

        meetings.add(a);
        meetings.add(b);
        meetings.add(c);
    }

    private static void addToMeeting (User user, Meeting meeting) {
        meeting.participants.add(user);
        user.meetings.add(meeting);
    }

    public static void changeUser (User user) {
        User temp = users.get(0);
        users.set(users.indexOf(user),temp);
        users.set(0, user);
    }

    public static void createServer(String name) {
        Server server = new Server (name, users.get(0));
        servers.add(server);
        users.get(0).joinServer(server);

    }

}
