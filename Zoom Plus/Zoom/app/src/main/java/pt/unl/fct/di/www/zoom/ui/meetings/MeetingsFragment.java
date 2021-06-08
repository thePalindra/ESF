package pt.unl.fct.di.www.zoom.ui.meetings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.List;

import pt.unl.fct.di.www.zoom.R;
import pt.unl.fct.di.www.zoom.ui.classes.Examples;
import pt.unl.fct.di.www.zoom.ui.classes.Meeting;
import pt.unl.fct.di.www.zoom.ui.classes.User;

public class MeetingsFragment extends Fragment {

    private View root;
    private ListView list;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_meetings, container, false);
        this.list = (ListView) this.root.findViewById(R.id.meetings);
        Button button = (Button) this.root.findViewById(R.id.button);

        this.user = Examples.users.get(0);

        this.list.setAdapter(new MySimpleArrayAdapter(getContext(), this.user.meetings));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_create_meeting);
            }
        });

        return this.root;
    }

    public class MySimpleArrayAdapter extends ArrayAdapter<Meeting> {

        private final Context context;
        private final List<Meeting> meetings;

        public MySimpleArrayAdapter(Context context, List<Meeting> meetings) {
            super(context, 0, meetings);
            this.context = context;
            this.meetings = meetings;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.meeting_item, parent, false);

            TextView usernameView = (TextView) rowView.findViewById(R.id.username);
            TextView activeView = (TextView) rowView.findViewById(R.id.active);

            if(!this.meetings.get(position).available) {
                activeView.setText("Unavailable");
            }

            usernameView.setText("Admin: " + this.meetings.get(position).admin.profile.username);

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (user.profile.username.equals(meetings.get(position).admin.profile.username)){
                        meetings.get(position).start();
                        Navigation.findNavController(v).navigate(R.id.navigation_individual_meeting);
                    } else if (meetings.get(position).available) {
                        if (user.currentMeeting != null)
                            user.currentMeeting.online.remove(user);
                        meetings.get(position).online.add(user);
                        user.changeCurr(meetings.get(position));
                        Navigation.findNavController(v).navigate(R.id.navigation_individual_meeting);
                    }
                }
            });

            return rowView;
        }
    }
}
