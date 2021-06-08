package pt.unl.fct.di.www.zoom.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.gson.Gson;

import java.util.List;

import pt.unl.fct.di.www.zoom.R;
import pt.unl.fct.di.www.zoom.ui.classes.Examples;
import pt.unl.fct.di.www.zoom.ui.classes.Server;
import pt.unl.fct.di.www.zoom.ui.classes.User;
import pt.unl.fct.di.www.zoom.ui.util.internal_storage;

public class HomeFragment extends Fragment {

    private View root;
    private Gson gson = new Gson();
    private ListView list;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayout server = (LinearLayout) this.root.findViewById(R.id.linearserver);
        ImageView users = (ImageView) this.root.findViewById(R.id.users);
        ImageView profile = (ImageView) this.root.findViewById(R.id.profile);
        Button button = (Button) this.root.findViewById(R.id.meetings);
        this.list = (ListView) this.root.findViewById(R.id.serverlist);

        if (Examples.users.isEmpty())
            Examples.examples();

        user = Examples.users.get(0);

        this.list.setAdapter(new MySimpleArrayAdapter(getContext(), user.servers));

        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_server);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.currentMeeting == null)
                    Navigation.findNavController(v).navigate(R.id.navigation_meetings);
                else
                    Navigation.findNavController(v).navigate(R.id.navigation_individual_meeting);
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_users);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_profile);
            }
        });

        return root;
    }

    public class MySimpleArrayAdapter extends ArrayAdapter<Server> {

        private final Context context;
        private final List<Server> servers;

        public MySimpleArrayAdapter(Context context, List<Server> servers) {
            super(context, 0,servers);
            this.context = context;
            this.servers = servers;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.server_item, parent, false);
            TextView titleView = (TextView) rowView.findViewById(R.id.title);
            TextView countView = (TextView) rowView.findViewById(R.id.counter);
            TextView adminView = (TextView) rowView.findViewById(R.id.admin);

            titleView.setText(this.servers.get(position).name);
            countView.setText(String.valueOf(this.servers.get(position).users.size()));
            adminView.setText("Admin: " + this.servers.get(position).admin.profile.username);

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.navigation_server);
                }
            });

            return rowView;
        }
    }
}