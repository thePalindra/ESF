package pt.unl.fct.di.www.zoom.ui.chat;

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

import java.util.List;

import pt.unl.fct.di.www.zoom.R;
import pt.unl.fct.di.www.zoom.ui.classes.Chat;
import pt.unl.fct.di.www.zoom.ui.classes.Examples;
import pt.unl.fct.di.www.zoom.ui.classes.Message;
import pt.unl.fct.di.www.zoom.ui.classes.User;

public class ChatFragment extends Fragment {

    private View root;
    private ListView list;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_chat, container, false);
        this.list = (ListView) this.root.findViewById(R.id.list);
        EditText text = (EditText) this.root.findViewById(R.id.message);
        Button send = (Button) this.root.findViewById(R.id.send);
        Button back = (Button) this.root.findViewById(R.id.back);
        TextView name = (TextView) this.root.findViewById(R.id.title);

        this.user = Examples.users.get(0);
        Chat chat = Examples.currentChat;

        name.setText(chat.name);

        this.list.setAdapter(new MySimpleArrayAdapter(getContext(), chat.messages));

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.currentMeeting.chat.messages.add(new Message(user, String.valueOf(text.getText())));
                list.setAdapter(new MySimpleArrayAdapter(getContext(), chat.messages));

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return this.root;
    }

    public class MySimpleArrayAdapter extends ArrayAdapter<Message> {

        private final Context context;
        private final List<Message> chats;

        public MySimpleArrayAdapter(Context context, List<Message> chats) {
            super(context, 0, chats);
            this.context = context;
            this.chats = chats;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.chat_item, parent, false);

            TextView name = rowView.findViewById(R.id.name);
            TextView message = rowView.findViewById(R.id.message);

            name.setText(chats.get(position).username.profile.name);

            message.setText(chats.get(position).message);

            return rowView;

        }
    }
}
