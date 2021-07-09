package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities.Nephroxenia_Main_Activity;
import micros_leader.george.nosileutikosfakelos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckInternetConnectionFragment extends Fragment {

    private ImageView image;
    private View view;
    private Button retryBut;

    public CheckInternetConnectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_check_internet_connection, container, false);

        image = view.findViewById(R.id.imageInt);
        retryBut = view.findViewById(R.id.buttonRetry);

        retryBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nephroxenia_Main_Activity main = (Nephroxenia_Main_Activity)getActivity();
                main.getVardies();
            }
        });

        return view;
    }




}
