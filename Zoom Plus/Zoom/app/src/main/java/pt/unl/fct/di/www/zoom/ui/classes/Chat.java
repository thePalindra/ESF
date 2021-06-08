package pt.unl.fct.di.www.zoom.ui.classes;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    public String name;
    public List<Message> messages;

    public Chat(String name) {
        this.name = name;
        this.messages = new ArrayList<Message>(100);
    }
}
