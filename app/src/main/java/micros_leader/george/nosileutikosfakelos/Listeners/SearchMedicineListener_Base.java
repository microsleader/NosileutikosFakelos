package micros_leader.george.nosileutikosfakelos.Listeners;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import micros_leader.george.nosileutikosfakelos.DialogFragmentSearches.DF_SearchMedicineFromBase;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon.Medicines;
import micros_leader.george.nosileutikosfakelos.Utils;

public class SearchMedicineListener_Base implements View.OnClickListener {


    //    private ArrayList<String> lista;
    private final ArrayList<Medicines> medlista;
    private final HashMap<String,Integer> medMap;
    private final Activity activity;
    DialogFragment dialogFragment;


    public SearchMedicineListener_Base(Activity activity) {

        this.activity = activity;

        //  lista = Utils.getMedicinesArrayList(activity);
        medMap = Utils.getMedicinesHashMap(activity);

        medlista = new ArrayList<>();

        setMedicineMap();

    }





    private void setMedicineMap(){
        if (medMap != null)
            for (Map.Entry<String,Integer> entry : medMap.entrySet()) {

                String key = entry.getKey();
                int value = entry.getValue();

                Medicines med = new Medicines();
                med.setItemID(String.valueOf(value));
                med.setItemname(key);
                medlista.add(med);
            }
    }


    @Override
    public void onClick(View v) {

        if (Utils.isNetworkAvailable2(v.getContext())) {

            if (medlista == null) {
                Toast.makeText(v.getContext(), "Η λίστα ειναί κενή", Toast.LENGTH_SHORT).show();
            }
            else {


                DF_SearchMedicineFromBase df = new DF_SearchMedicineFromBase();
                Bundle putextra = new Bundle();
                putextra.putSerializable("medicines", medlista);

                df.setArguments(putextra);
                if (activity != null)
                    df.show(((FragmentActivity)activity).getSupportFragmentManager(), "Dialog");

                else
                    df.show(((FragmentActivity) dialogFragment.requireContext()).getSupportFragmentManager(), "Dialog");


                // transgroupID = Utils.getfirstPartSplitCommaString(patientsTV.getText().toString());


            }
        }
    }

}
