package pt.unl.fct.di.www.zoom.ui.meetings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import pt.unl.fct.di.www.zoom.R;
import pt.unl.fct.di.www.zoom.ui.classes.Examples;
import pt.unl.fct.di.www.zoom.ui.classes.User;
import pt.unl.fct.di.www.zoom.ui.home.HomeFragment;

public class MeetingFragment extends Fragment {

    private View root;
    private ListView list;
    private ImageView heart;
    private ImageView chat;
    private ImageView part;
    private Button button;
    private User user;
    private boolean participants;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.participants = false;
        this.root = inflater.inflate(R.layout.fragment_meeting, container, false);
        this.list = (ListView) this.root.findViewById(R.id.list);
        this.heart = (ImageView) this.root.findViewById(R.id.heart);
        this.chat = (ImageView) this.root.findViewById(R.id.chat);
        this.part = (ImageView) this.root.findViewById(R.id.participantsimage);
        this.button = (Button) this.root.findViewById(R.id.sair);
        TextView number = (TextView) this.root.findViewById(R.id.participants);
        TextView desc = (TextView) this.root.findViewById(R.id.desc);

        this.user = Examples.users.get(0);

        desc.setText(this.user.currentMeeting.description);
        number.setText(String.valueOf(this.user.currentMeeting.online.size()));

        this.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.changeEmote();
                if(participants) {
                    list.setAdapter(new MySimpleArrayAdapter(getContext(), user.currentMeeting.online));
                }
            }
        });

        this.chat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Examples.currentChat = user.currentMeeting.chat;
                Navigation.findNavController(v).navigate(R.id.navigation_chat);
            }
        });

        this.part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                participants = !participants;
                if(participants) {
                    list.setAdapter(new MySimpleArrayAdapter(getContext(), user.currentMeeting.online));
                } else {
                    list.setAdapter(new MySimpleArrayAdapter(getContext(), new ArrayList<User>()));

                }
            }
        });

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.currentMeeting.admin.equals(user))
                    user.currentMeeting.end();
                else {
                    user.currentMeeting.online.remove(user);
                    user.leaveCurr();
                }
                Navigation.findNavController(v).navigate(R.id.navigation_meetings);
            }
        });

        return this.root;
    }
    private class MySimpleArrayAdapter extends ArrayAdapter<User> {

        private final List<User> users;
        private final Context context;

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
            ImageView heart = (ImageView) rowView.findViewById(R.id.react);

            text.setText(users.get(position).profile.name);

            if (users.get(position).emote)
                heart.setImageResource(R.drawable.heart);
            else
                heart.setImageResource(R.drawable.blank);

            return rowView;
        }
    }
}
