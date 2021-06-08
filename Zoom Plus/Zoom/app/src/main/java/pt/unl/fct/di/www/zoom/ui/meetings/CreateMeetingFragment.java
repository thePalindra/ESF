package pt.unl.fct.di.www.zoom.ui.meetings;

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
import pt.unl.fct.di.www.zoom.ui.classes.User;

public class CreateMeetingFragment extends Fragment {

    private View root;
    private ListView list;
    private User user;
    private List<User> participants;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_create_meeting, container, false);
        this.participants = new ArrayList<User>();
        this.list = (ListView) this.root.findViewById(R.id.users);
        EditText desc = (EditText) this.root.findViewById(R.id.desc);
        Button pub = (Button) this.root.findViewById(R.id.pub);
        Button create = (Button) this.root.findViewById(R.id.create);

        this.user = Examples.users.get(0);

        this.list.setAdapter(new MySimpleArrayAdapter(getContext(), Examples.users));

        pub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = String.valueOf(desc.getText());
                if (description != null && !description.equals("")) {
                    Chat chat = new Chat("Global");
                    Meeting temp = new Meeting(description, 1, user, chat);
                    temp.participants.clear();
                    temp.participants.addAll(Examples.users);
                    for (int i = 1; i < Examples.users.size(); i++) {
                        Examples.users.get(i).meetings.add(temp);
                    }
                    Examples.meetings.add(temp);
                    Navigation.findNavController(v).navigate(R.id.navigation_meetings);
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = String.valueOf(desc.getText());
                if (description != null && !description.equals("")) {
                    Chat chat = new Chat("Global");
                    Meeting temp = new Meeting(description, 1, user, chat);
                    temp.participants.addAll(participants);
                    Examples.meetings.add(temp);
                    Navigation.findNavController(v).navigate(R.id.navigation_meetings);
                }
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
