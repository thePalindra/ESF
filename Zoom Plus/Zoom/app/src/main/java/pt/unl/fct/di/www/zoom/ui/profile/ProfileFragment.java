package pt.unl.fct.di.www.zoom.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import pt.unl.fct.di.www.zoom.R;
import pt.unl.fct.di.www.zoom.ui.classes.Examples;
import pt.unl.fct.di.www.zoom.ui.classes.User;

public class ProfileFragment extends Fragment {
    private View root;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView username = (TextView) this.root.findViewById(R.id.username);
        TextView name = (TextView) this.root.findViewById(R.id.name);
        TextView location = (TextView) this.root.findViewById(R.id.location);
        TextView sex = (TextView) this.root.findViewById(R.id.sex);
        Button button = (Button) this.root.findViewById(R.id.button);

        this.user = Examples.users.get(0);
        username.setText(this.user.profile.username);
        name.setText(this.user.profile.name);

        if (this.user.profile.location != null)
            location.setText(this.user.profile.location);
        if (this.user.profile.sex != null)
            sex.setText(this.user.profile.sex);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_edit_profile);
            }
        });

        return this.root;
    }
}
