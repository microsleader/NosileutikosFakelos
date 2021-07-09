package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3;

import android.app.Activity;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.StaticFields.CHECKBOX_ITEM;

public class MetriseisSinexeisRecyclerView extends BasicRV {

    private int custID;

    public MetriseisSinexeisRecyclerView(Activity act, ArrayList<ItemsRV> result, ArrayList oldValuesLista, boolean isIncludingOldValues) {
        super(act, result, oldValuesLista, isIncludingOldValues);
        custID = Utils.getCustomerID(act);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        if (result.get(position).getTitleID().equals("Ημ/νία και ώρα")) {
           // holder.valueTV.setOnClickListener(null);
            holder.valueTV.setText(Utils.getCurrentDate());

        }

        if (!Customers.isFrontis(custID)) {

            if (result.get(position).getType() == CHECKBOX_ITEM) {
                holder.oldValueCH.setText(result.get(position).getTitleID());
                String value = result.get(position).getValue() == null ? "" : result.get(position).getValue();
                holder.valueCH.setText(value);
                if (value.isEmpty())
                    holder.valueCH.setEnabled(false);
                else
                    holder.valueCH.setEnabled(true);


            }
        }
    }



}

