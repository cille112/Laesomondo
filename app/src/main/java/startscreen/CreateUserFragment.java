package startscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import com.example.cille_000.laesomondo.R;

import logic.StartLogic;


public class CreateUserFragment extends Fragment {

    private static ImageButton avatar;
    private EditText t1;
    private EditText t2;
    private SharedPreferences sharedPref;
    private StartLogic logic;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createuser, container, false);
        Context context = getActivity();

        sharedPref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        logic = new StartLogic(sharedPref);

        avatar = (ImageButton) view.findViewById(R.id.createuser_picturebtn);
        t1 = (EditText) view.findViewById(R.id.createuser_name);
        t2 = (EditText) view.findViewById(R.id.createuser_age);

        avatar.setBackgroundResource(logic.getAvatar());
        t1.setText(logic.getName());
        t2.setText(logic.getDate());

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment avatarFragment = new AvatarFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_start, avatarFragment );
                transaction.commit();
            }
        });

        return view;
    }

    public static void setAvatar(int index) {
        avatar.setTag(index);
        avatar.setBackgroundResource(index);
    }

    public String getName() {
        return t1.getText().toString();
    }

    public String getDate() {
        return t2.getText().toString();
    }

    public int getAvatar() {
        return Integer.valueOf(avatar.getTag().toString());
    }
}

