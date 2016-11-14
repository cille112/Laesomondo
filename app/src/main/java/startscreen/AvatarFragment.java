package startscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cille_000.laesomondo.R;

import logic.StartLogic;

public class AvatarFragment extends Fragment implements View.OnClickListener {

    private ImageButton av1, av2, av3, av4, av5, av6, av7, av8, av9;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avatar, container, false);

        av1 = (ImageButton) view.findViewById(R.id.imageButton1);
        av2 = (ImageButton) view.findViewById(R.id.imageButton2);
        av3 = (ImageButton) view.findViewById(R.id.imageButton3);
        av4 = (ImageButton) view.findViewById(R.id.imageButton4);
        av5 = (ImageButton) view.findViewById(R.id.imageButton5);
        av6 = (ImageButton) view.findViewById(R.id.imageButton6);
        av7 = (ImageButton) view.findViewById(R.id.imageButton7);
        av8 = (ImageButton) view.findViewById(R.id.imageButton8);
        av9 = (ImageButton) view.findViewById(R.id.imageButton9);

        av1.setOnClickListener(this);
        av2.setOnClickListener(this);
        av3.setOnClickListener(this);
        av4.setOnClickListener(this);
        av5.setOnClickListener(this);
        av6.setOnClickListener(this);
        av7.setOnClickListener(this);
        av8.setOnClickListener(this);
        av9.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (v == av1) {CreateUserFragment.setAvatar(R.drawable.av1);}
        if (v == av2) CreateUserFragment.setAvatar(R.drawable.av2);
        if (v == av3) CreateUserFragment.setAvatar(R.drawable.av3);
        if (v == av4) CreateUserFragment.setAvatar(R.drawable.av4);
        if (v == av5) CreateUserFragment.setAvatar(R.drawable.av5);
        if (v == av6) CreateUserFragment.setAvatar(R.drawable.av6);
        if (v == av7) CreateUserFragment.setAvatar(R.drawable.av7);
        if (v == av8) CreateUserFragment.setAvatar(R.drawable.av8);
        if (v == av9) CreateUserFragment.setAvatar(R.drawable.av9);

        transaction.remove(this).commit();
    }
}
