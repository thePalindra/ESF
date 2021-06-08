package pt.unl.fct.di.www.zoom.ui.server;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import pt.unl.fct.di.www.zoom.R;
import pt.unl.fct.di.www.zoom.ui.classes.Chat;
import pt.unl.fct.di.www.zoom.ui.classes.Examples;
import pt.unl.fct.di.www.zoom.ui.classes.Meeting;
import pt.unl.fct.di.www.zoom.ui.classes.Server;
import pt.unl.fct.di.www.zoom.ui.classes.User;
import pt.unl.fct.di.www.zoom.ui.meetings.CreateMeetingFragment;

public class CreateServerFragment extends Fragment {
        private View root;
        private ListView list;
        private User user;
        private List<User> participants;
        private List<Chat> chats;
        private List<Meeting> meetings;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            this.root = inflater.inflate(R.layout.fragment_create_server, container, false);
            this.participants = new ArrayList<User>();
            this.meetings = new ArrayList<Meeting>();
            this.chats = new ArrayList<Chat>();
            this.list = (ListView) this.root.findViewById(R.id.users);
            Button addM = (Button) this.root.findViewById(R.id.adicionarm);
            Button addC = (Button) this.root.findViewById(R.id.adicionarc);
            Button terminar = (Button) this.root.findViewById(R.id.criar);
            EditText name = (EditText) this.root.findViewById(R.id.nome);
            EditText meeting = (EditText) this.root.findViewById(R.id.meeting);
            EditText chat = (EditText) this.root.findViewById(R.id.chat);

            this.user = Examples.users.get(0);
            this.participants.add(this.user);

            this.list.setAdapter(new MySimpleArrayAdapter(getContext(), Examples.users));

            addM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String temp = String.valueOf(meeting.getText());
                    if (temp != null && !temp.equals("") && !meetings.contains(new Meeting(temp, 3))) {
                        Meeting meeting = new Meeting(temp, 3);
                        meeting.participants.addAll(Examples.users);
                        meetings.add(meeting);
                    }
                }
            });

            addC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String temp = String.valueOf(chat.getText());
                    if (temp != null && !temp.equals("") && !chats.contains(new Chat(temp))) {
                        chats.add(new Chat(temp));
                    }
                }
            });

            terminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nome = String.valueOf(name.getText());
                    Server server = new Server(nome, user);

                    server.calls.addAll(meetings);
                    server.chats.addAll(chats);
                    server.users.addAll(participants);

                    for (int i = 0; i < participants.size(); i++)
                        participants.get(i).servers.add(server);

                    Examples.servers.add(server);
                    Navigation.findNavController(v).navigate(R.id.navigation_server);
                }
            });

            return this.root;
        }

    public class MySimpleArrayAdapter extends ArrayAdapter<User> {

        private final Context context;
        private final List<User> users;

        public MySimpleArrayAdapter(Context context, List<User> users) {
            super(context, 0, users);
            this.context = context;
            this.users = users;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.user_meeting_item, parent, false);
            TextView text = (TextView) rowView.findViewById(R.id.username);

            text.setText(this.users.get(position).profile.name);

            rowView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (!participants.contains(users.get(position))
                            && !users.get(position).equals(user)) {
                        participants.add(users.get(position));
                    }
                }
            });

            return rowView;
        }
    }
}
