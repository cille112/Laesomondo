package com.example.cille_000.laesomondo.startscreen;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cille_000.laesomondo.R;

public class AvatarFragment extends Fragment implements View.OnClickListener {

    public interface OnDoneListener {
        void onDone();
    }

    private ImageButton[] list;
    private int current;
    private OnDoneListener onDoneListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avatar, container, false);

        current = 0;

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

        for(int i = 0; i < list.length; i++) {
            if(v == list[i]) {
                current = i;
                break;
            }
        }

        if(onDoneListener != null) {
            onDoneListener.onDone();
        }

        transaction.remove(this).commit();
    }

    public int getCurrent() {
        switch (current) {

            case 0: return R.drawable.av1;
            case 1: return R.drawable.av2;
            case 2: return R.drawable.av3;
            case 3: return R.drawable.av4;
            case 4: return R.drawable.av5;
            case 5: return R.drawable.av6;
            case 6: return R.drawable.av7;
            case 7: return R.drawable.av8;
            case 8: return R.drawable.av9;

            default: return R.drawable.av1;
        }
    }

    public void setOnDoneListener(OnDoneListener odl) {
        onDoneListener = odl;
    }
}
