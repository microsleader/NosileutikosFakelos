package micros_leader.george.nosileutikosfakelos.OROFOI.f_Diaitologio;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicRV_many_items_NOT_COMPLETED;
import micros_leader.george.nosileutikosfakelos.R;

public class RecyclerwViewDiaitologio_new_NOT_COMPLETED extends BasicRV_many_items_NOT_COMPLETED {

    private  ArrayList<Diaitologio> result;
    private MyVHolder hold;

    public RecyclerwViewDiaitologio_new_NOT_COMPLETED(Activity act, ArrayList result, int recyclerview_layout_id) {
        super(act, result, recyclerview_layout_id);

        this.result = result;


    }

//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//
//
//        MyVHolder hol = new MyVHolder(v);
//
//        return v;
//
//    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        holder = hold;

        final String diaita = result.get(position).getDieta();
        final String transgroupID = result.get(position).getTransgroupid();
        final String id = result.get(position).getId();
        final String hourFrom = result.get(position).getHourFrom();
        final String dateFrom = result.get(position).getDatefrom();
        final String sxolia = result.get(position).getSxolia();
        final String sitisiSinodou = result.get(position).getSitisiSinodou();

        // AN KAI TA DIO EXOUN KEIMENO NA VALEI STI MESI KOMMA
        String comma = "";
        if (!dateFrom.equals("") && !hourFrom.equals(""))
            comma = " , ";

//        holder.diaitaTV.setText(diaita);
//
//
//        holder.dateHourTV.setText(dateFrom + comma + hourFrom);
//        holder.sxoliaTV.setText(sxolia);
//        holder.sitisiSinodouTV.setText(sitisiSinodou);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pos = position;
                showAlertDialog(id, transgroupID,dateFrom,hourFrom,diaita,sxolia,sitisiSinodou);



            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pos = position;
                showAlertDialog(id, transgroupID,dateFrom,hourFrom,diaita,sxolia,sitisiSinodou);



            }
        });



    }




    public class MyVHolder extends BasicRV_many_items_NOT_COMPLETED.MyViewHolder {

        TextView dateHourTV,diaitaTV, sxoliaTV, sitisiSinodouTV;

        public MyVHolder(View itemView) {
            super(itemView);

            dateHourTV = itemView.findViewById(R.id.dateHourTV);
            diaitaTV = itemView.findViewById(R.id.diaitaTV);
            sxoliaTV = itemView.findViewById(R.id.sxoliaTV);
            sitisiSinodouTV = itemView.findViewById(R.id.sitisiSinodouTV);

        }
    }
}
