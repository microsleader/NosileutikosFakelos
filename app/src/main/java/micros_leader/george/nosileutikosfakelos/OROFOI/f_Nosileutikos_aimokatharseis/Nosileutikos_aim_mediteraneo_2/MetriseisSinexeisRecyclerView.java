package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_2;

import android.app.Activity;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.Utils;

public class MetriseisSinexeisRecyclerView extends BasicRV {


    public MetriseisSinexeisRecyclerView(Activity act, ArrayList<ItemsRV> result, ArrayList oldValuesLista, boolean isIncludingOldValues) {
        super(act, result, oldValuesLista, isIncludingOldValues);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        if (result.get(position).getTitleID().equals("Ημ/νία και ώρα")) {
            holder.valueTV.setOnClickListener(null);
            holder.valueTV.setText(Utils.getCurrentDate());

        }
        if (result.get(position).getType() == 4) {
            holder.oldValueCH.setText(result.get(position).getTitleID());
            holder.valueCH.setText(result.get(position).getValue() == null ? "" : result.get(position).getValue());

        }
    }


}

