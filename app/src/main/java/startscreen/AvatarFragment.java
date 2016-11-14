package startscreen;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cille_000.laesomondo.R;

public class AvatarFragment extends Fragment implements View.OnClickListener {

    private ImageButton[] list;
    private ImageButton current;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avatar, container, false);

        list = new ImageButton[] {
                (ImageButton) view.findViewById(R.id.imageButton1),
                (ImageButton) view.findViewById(R.id.imageButton2),
                (ImageButton) view.findViewById(R.id.imageButton3),
                (ImageButton) view.findViewById(R.id.imageButton4),
                (ImageButton) view.findViewById(R.id.imageButton5),
                (ImageButton) view.findViewById(R.id.imageButton6),
                (ImageButton) view.findViewById(R.id.imageButton7),
                (ImageButton) view.findViewById(R.id.imageButton8),
                (ImageButton) view.findViewById(R.id.imageButton9)
        };

        for (ImageButton i: list) {
            i.setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        for (ImageButton i: list) {
            if(v == i)
                current = i;
        }

        transaction.remove(this).commit();
    }

    public int getCurrent() {
        switch (current.getTag().toString()) {

            case "av1": return R.drawable.av1;
            case "av2": return R.drawable.av2;
            case "av3": return R.drawable.av3;
            case "av4": return R.drawable.av4;
            case "av5": return R.drawable.av5;
            case "av6": return R.drawable.av6;
            case "av7": return R.drawable.av7;
            case "av8": return R.drawable.av8;
            case "av9": return R.drawable.av9;

            default: return R.drawable.av1;
        }
    }
}
