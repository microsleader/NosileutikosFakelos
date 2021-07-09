package micros_leader.george.nosileutikosfakelos.Listeners;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import micros_leader.george.nosileutikosfakelos.DialogFragmentSearches.DialogFragmentSearchEidos;
import micros_leader.george.nosileutikosfakelos.Utils;

public class SearchEidosListener implements View.OnClickListener {


    //    private ArrayList<String> lista;
    private ArrayList<String> eidoslista;
    private DialogFragment dialogFragment;
    private HashMap<String,Integer> eidosMap;
    private Activity activity;
    public TextView txtView;

    public SearchEidosListener(Activity activity , ArrayList<String> eidoslista, HashMap<String,Integer> eidosMap, DialogFragment dialogFragment) {
        // keep references for your onClick logic

        this.activity = activity;
        this.eidoslista = eidoslista;
        this.eidosMap = eidosMap;
        this.dialogFragment = dialogFragment;



    }





    @Override public void onClick(View v) {
        // TODO: add code here


        if (Utils.isNetworkAvailable2(v.getContext())) {

            if (eidoslista == null || eidoslista.isEmpty()) {
                Toast.makeText(v.getContext(), "Η λίστα ειναί κενή", Toast.LENGTH_SHORT).show();
            }
            else {



                DialogFragmentSearchEidos df = new DialogFragmentSearchEidos();
                Bundle putextra = new Bundle();
                putextra.putSerializable("eidi", eidoslista);
                df.setTargetFragment(dialogFragment, 1);

                df.setArguments(putextra);
              //  df.show(((FragmentActivity)activity).getSupportFragmentManager(), "Dialog");


                if (dialogFragment.getFragmentManager() != null)
                     df.show(dialogFragment.getFragmentManager(), "Dialog");
            }
        }
    }

}
