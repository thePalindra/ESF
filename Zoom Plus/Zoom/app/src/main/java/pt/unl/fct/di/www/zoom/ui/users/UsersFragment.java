package pt.unl.fct.di.www.zoom.ui.users;

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
import pt.unl.fct.di.www.zoom.ui.classes.Examples;
import pt.unl.fct.di.www.zoom.ui.classes.User;
import pt.unl.fct.di.www.zoom.ui.home.HomeFragment;

public class UsersFragment extends Fragment {

    private View root;
    private List<User> users;
    private ListView list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_users, container, false);

        this.list = (ListView) root.findViewById(R.id.list);

        this.users = Examples.users;

        this.list.setAdapter(new MySimpleArrayAdapter(getContext(), this.users));

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
            View rowView = inflater.inflate(R.layout.user_item, parent, false);

            TextView usernameView = (TextView) rowView.findViewById(R.id.username);
            TextView nameView = (TextView) rowView.findViewById(R.id.name);

            usernameView.setText(users.get(position).profile.username);
            nameView.setText(users.get(position).profile.name);

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Examples.changeUser(users.get(position));
                    Navigation.findNavController(v).navigate(R.id.navigation_home);
                }
            });

            return rowView;
        }

    }
}
