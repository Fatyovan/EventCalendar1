package com.example.user.eventcalendar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class ThirdFragment extends Fragment implements View.OnClickListener {
    public static final String THIRD_FRAGMENT_TAG = "ThirdFragment";


    public ThirdFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_third, container, false);
        Button button = (Button) v.findViewById(R.id.button3);

        button.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                Toast.makeText(getActivity(), "Button 3 clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
