package micros_leader.george.nosileutikosfakelos.Listeners;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.DialogFragmentSearches.DialogFragmentSearchPatientNosileuomenos;
import micros_leader.george.nosileutikosfakelos.Utils;

public class SearchNosileuomenoListener implements View.OnClickListener {


private ArrayList<PatientsOfTheDay> lista;

private Activity activity;

    public SearchNosileuomenoListener(Activity activity, ArrayList<PatientsOfTheDay> lista) {
        // keep references for your onClick logic

        this.activity = activity;
        this.lista = lista;
    }

    @Override public void onClick(View v) {
        // TODO: add code here

        if (Utils.isNetworkAvailable2(v.getContext())) {

            if (lista == null) {
                Toast.makeText(v.getContext(), "Η λίστα ειναί κενή", Toast.LENGTH_SHORT).show();
            }
            else {


                    DialogFragmentSearchPatientNosileuomenos df = new DialogFragmentSearchPatientNosileuomenos();
                    Bundle putextra = new Bundle();
                    putextra.putSerializable("patients", lista);

                    df.setArguments(putextra);
                    df.show(((FragmentActivity)activity).getSupportFragmentManager(), "Dialog");

                   // transgroupID = Utils.getfirstPartSplitCommaString(patientsTV.getText().toString());

            }
        }
    }

}
