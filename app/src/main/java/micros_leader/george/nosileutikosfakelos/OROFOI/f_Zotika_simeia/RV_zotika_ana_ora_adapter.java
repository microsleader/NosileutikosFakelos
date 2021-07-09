package micros_leader.george.nosileutikosfakelos.OROFOI.f_Zotika_simeia;

import android.app.Activity;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;

public class RV_zotika_ana_ora_adapter extends BasicRV {

    private int number =0;


    public RV_zotika_ana_ora_adapter(Activity act, ArrayList<ItemsRV> result, int[] titloi_positions) {
        super(act, result, titloi_positions);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

//        if (result.get(position).getTitleID().equals("Κλίμακα πόνου")){
//
//            holder.valueET.setFilters(new InputFilter[]{new InputFilterMinMax(0,10)});
//        }




    }


    public void clearSpinnerLists(){

    }


}
