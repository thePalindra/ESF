package pt.unl.fct.di.www.zoom.ui.server;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.List;

import pt.unl.fct.di.www.zoom.R;
import pt.unl.fct.di.www.zoom.ui.classes.Chat;
import pt.unl.fct.di.www.zoom.ui.classes.Examples;
import pt.unl.fct.di.www.zoom.ui.classes.Meeting;
import pt.unl.fct.di.www.zoom.ui.classes.Server;

public class ServerFragment extends Fragment {

    private View root;
    private Server server;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.individual_server, container, false);
        ListView calls = (ListView) this.root.findViewById(R.id.calls);
        ListView chats = (ListView) this.root.findViewById(R.id.chats);
        TextView name = (TextView) this.root.findViewById(R.id.name);

        this.server = Examples.currentServer;

        chats.setAdapter(new MySimpleArrayAdapter(getContext(), this.server.chats));
        calls.setAdapter(new MySimpleArrayAdapter2(getContext(), this.server.calls));

        name.setText(this.server.name);

        return this.root;
    }

    private class MySimpleArrayAdapter extends ArrayAdapter<Chat> {

        private final Context context;
        private final List<Chat> chats;

        public MySimpleArrayAdapter(Context context, List<Chat> chats) {
            super(context, 0, chats);
            this.context = context;
            this.chats = chats;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.chats_item, parent, false);
            TextView name = (TextView) rowView.findViewById(R.id.name);

            name.setText(chats.get(position).name);

            rowView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Examples.currentChat = chats.get(position);
                    Navigation.findNavController(v).navigate(R.id.navigation_chat);
                }
            });

            return rowView;
        }
    }

    private class MySimpleArrayAdapter2 extends ArrayAdapter<Meeting> {

        private final Context context;
        private final List<Meeting> calls;

        public MySimpleArrayAdapter2(Context context, List<Meeting> calls) {
            super(context, 0, calls);
            this.context = context;
            this.calls = calls;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.calls_item, parent, false);
            TextView count = (TextView) rowView.findViewById(R.id.count);
            TextView name = (TextView) rowView.findViewById(R.id.name);

            count.setText(String.valueOf(calls.get(position).online.size()));
            name.setText(calls.get(position).description);

            rowView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    calls.get(position).online.add(Examples.users.get(0));
                    Examples.users.get(0).currentMeeting = calls.get(position);
                    Navigation.findNavController(v).navigate(R.id.navigation_individual_meeting);
                }
            });

            return rowView;
        }
    }

}
