package com.example.user.eventcalendar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SecondFragment extends Fragment implements View.OnClickListener {
    public static final String SECOND_FRAGMENT_TAG = "SecondFragment";


    public SecondFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_second, container, false);


        Button button = (Button) v.findViewById(R.id.button2);

        button.setOnClickListener(this);

            return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                Toast.makeText(getActivity(), "Button 2 clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
