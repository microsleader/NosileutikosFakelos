package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3;

import android.app.Activity;
import android.widget.Spinner;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;


public class MetriseisStatheresRecyclerView extends BasicRV {


    private Spinner currentSP;
    private String posotita ="";


    public MetriseisStatheresRecyclerView(Activity act, ArrayList<ItemsRV> result, ArrayList<String> oldValuesLista, boolean isIncludingOldValues) {
        super(act, result,oldValuesLista, isIncludingOldValues);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        if (result.get(position).getTitleID().equals("Υπεύθυνος ιατρός βάρδιας")) {

//            currentSP = holder.valueSP;
//            if (currentSP.getAdapter().isEmpty())
//            getDoctors(position,holder);
        }

        if (result.get(position).getTitleID().equals("βελόνα 15g")) {
            posotita = getPosotita("velona15:");
            holder.oldValueCH.setText("βελόνα 15g (" + posotita + ")" );
        }
            else if (result.get(position).getTitleID().equals("βελόνα 16g")){
            posotita = getPosotita("velona16:");
            holder.oldValueCH.setText("βελόνα 16g (" + posotita + ")" );
        }

            else if (result.get(position).getTitleID().equals("βελόνα 17g")){
            posotita = getPosotita("velona17:");
            holder.oldValueCH.setText("βελόνα 17g (" + posotita + ")" );
        }

    }


    private String getPosotita(String title){
        String value;
        String parts[]= new String[]{};
        for (int i=0; i<oldValuesLista.size(); i++){
            value = oldValuesLista.get(i);
            if (value.contains(title)){
                parts = value.split(":");

            }
        }

        return parts.length > 1 ? parts[1] : "0";
    }


}


