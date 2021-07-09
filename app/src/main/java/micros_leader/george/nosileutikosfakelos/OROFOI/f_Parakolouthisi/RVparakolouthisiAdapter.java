package micros_leader.george.nosileutikosfakelos.OROFOI.f_Parakolouthisi;

import android.app.Activity;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;


public class RVparakolouthisiAdapter extends BasicRV {


    public RVparakolouthisiAdapter(Activity act, ArrayList<ItemsRV> result) {
        super(act, result);
    }

    public RVparakolouthisiAdapter(Activity act, ArrayList<ItemsRV> result, int[] titloi_positions) {
        super(act, result, titloi_positions);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        if (result.get(position).getTitleID().equals("Κενώσεις")){
            holder.valueET.setFocusable(false);

            holder.valueET.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    edittextListener(holder.valueET);
                }
            });
        }



    }

    private void edittextListener(final TextView valueET){
        final String[] values = {"'Κενό'", "Φυσιολογική", "Διαρροϊκή", "Μέλαινα"};

        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle("Διαλέχτε τιμή");
        builder.setItems(values, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    valueET.setText("");
                }
                else
                    valueET.setText(values[which]);
            }
        });
        builder.show();
    }
}
