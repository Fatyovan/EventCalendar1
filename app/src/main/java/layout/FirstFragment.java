package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.eventcalendar.R;

public class FirstFragment extends Fragment implements View.OnClickListener {
    public static final String FIRST_FRAGMENT_TAG = "FirstFragment";

    public FirstFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_camera, container, false);

        Button button = (Button) v.findViewById(R.id.button1);

        button.setOnClickListener(this);

                return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Toast.makeText(getActivity(), "Button 1 clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
