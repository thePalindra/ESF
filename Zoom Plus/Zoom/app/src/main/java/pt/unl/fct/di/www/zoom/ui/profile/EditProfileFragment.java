package pt.unl.fct.di.www.zoom.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.List;

import pt.unl.fct.di.www.zoom.R;
import pt.unl.fct.di.www.zoom.ui.classes.Examples;
import pt.unl.fct.di.www.zoom.ui.classes.User;

public class EditProfileFragment extends Fragment {
    private View root;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        TextView username = (TextView) this.root.findViewById(R.id.username);
        EditText name = (EditText) this.root.findViewById(R.id.name);
        EditText location = (EditText) this.root.findViewById(R.id.location);
        EditText sex = (EditText) this.root.findViewById(R.id.sex);
        Button button = (Button) this.root.findViewById(R.id.button);
        Button cancel = (Button) this.root.findViewById(R.id.cancel);

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
                user.profile.name = String.valueOf(name.getText());

                if (!String.valueOf(location.getText()).equals("Não definido"))
                    user.profile.location = String.valueOf(location.getText());

                if (String.valueOf(sex.getText()).equals("Não definido")
                    || String.valueOf(sex.getText()).equals("F")
                    || String.valueOf(sex.getText()).equals("M"))
                    user.profile.sex = String.valueOf(sex.getText());

                Navigation.findNavController(v).navigate(R.id.navigation_profile);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_profile);
            }
        });

        return this.root;
    }
}
