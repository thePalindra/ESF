package pt.unl.fct.di.www.zoom.ui.classes;

import java.util.ArrayList;
import java.util.List;

public class User {

    public Profile profile;
    public List<Meeting> meetings;
    public Meeting currentMeeting;
    public List<Server> servers;
    public boolean emote;

    public User (Profile profile){
        this.profile = profile;
        this.meetings = new ArrayList<Meeting>(10);
        this.currentMeeting = null;
        this.servers = new ArrayList<Server>(10);
        this.emote = false;
    }

    public void joinServer (Server newServer){
        this.servers.add(newServer);
    }

    public void abandonServer (Server leftServer) {
        this.servers.remove(leftServer);
    }

    public void addMeeitng(Meeting newMeet) {
        this.meetings.add(newMeet);
    }

    public void changeCurr(Meeting newCurr) {
        this.currentMeeting = newCurr;
    }

    public void leaveCurr () {
        this.currentMeeting.participants.remove(this);
        this.currentMeeting = null;
    }

    public void changeEmote() {
        this.emote = !this.emote;
    }


}
