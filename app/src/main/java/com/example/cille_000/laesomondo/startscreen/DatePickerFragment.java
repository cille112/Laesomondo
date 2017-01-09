package com.example.cille_000.laesomondo.startscreen;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.cille_000.laesomondo.R;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment {

    public interface OnSubmitListener {
        void onSubmitted(int day, int month, int year);
    }

    private OnSubmitListener callback;

    private DatePicker datePicker;
    private Button cancelButton, okButton;

    public DatePickerFragment() {
        callback = null;
    }

    public void setCallback(OnSubmitListener callback) {
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_datepicker, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();

        datePicker = (DatePicker) view.findViewById(R.id.datepicker);
        cancelButton = (Button) view.findViewById(R.id.datepicker_cancel);
        okButton = (Button) view.findViewById(R.id.datepicker_ok);

        datePicker.updateDate(1989, 0, 1);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onSubmitted(datePicker.getDayOfMonth(), datePicker.getMonth() + 1, datePicker.getYear());
                dismiss();
            }
        });
    }
}
