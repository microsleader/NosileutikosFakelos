package micros_leader.george.nosileutikosfakelos.OROFOI.f_EktimisiEpigonton;

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.ClassItemsCheckboxesForRV;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Eksitirio.RecyclerViewEksitirioAdaptor;
import micros_leader.george.nosileutikosfakelos.R;

public class RecyclerViewEktimisiEpigontonAdaptor extends RecyclerView.Adapter<RecyclerViewEktimisiEpigontonAdaptor.MyViewHolder> implements SearchView.OnQueryTextListener {


    public ArrayList<ClassItemsCheckboxesForRV> result;
    private int  pos;
    private Context ctx;
    private Activity act;
    public int VIEW_TYPE_HEADER = 1;
    public int VIEW_TYPE_ITEM = 2;

    public RecyclerViewEksitirioAdaptor.MyViewHolder hold = null;



    // create constructor to innitilize context and data sent from MainActivity
    public RecyclerViewEktimisiEpigontonAdaptor(Activity act, ArrayList<ClassItemsCheckboxesForRV> result) {

        this.act = act;
        this.result = result;



    }

    @Override
    public RecyclerViewEktimisiEpigontonAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout

        if (viewType == VIEW_TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_checkbox_rec_adapter_title, parent, false);
            RecyclerViewEktimisiEpigontonAdaptor.MyViewHolder vh = new RecyclerViewEktimisiEpigontonAdaptor.MyViewHolder(v); // pass the view to View Holder
            return vh;
        } else{

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_checkbox_rec_adapter_item, parent, false);
            RecyclerViewEktimisiEpigontonAdaptor.MyViewHolder vh = new RecyclerViewEktimisiEpigontonAdaptor.MyViewHolder(v); // pass the view to View Holder
            return vh;
    }

    }


    @Override
    public void onBindViewHolder(final RecyclerViewEktimisiEpigontonAdaptor.MyViewHolder holder, final int position) {
        // set the data in items


        String title = result.get(position).getTitleID();
        boolean value = result.get(position).isTrue();


        holder.itemView.setTag(title);
        holder.valueCH.setTag(position);

        if (position == 0 || position == 11 || position == 36)  //ΓΙΑ ΝΑ ΜΠΑΙΝΕΙ ΜΟΝΟ ΣΤΙΣ ΘΕΣΕΙΣ ΠΟΥ ΘΕΛΩ Ο ΤΙΤΛΟΣ
        holder.titleTV.setText(title);
       // holder.titleTV.setVisibility(View.GONE);



        holder.valueCH.setText(title);

        holder.valueCH.setChecked(value);






        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pos = position;



            }
        });


//        if (isHeader(position)) {
//            Toast.makeText(act, String.valueOf(position), Toast.LENGTH_SHORT).show();
//            return;
//        }


    }

//    @Override
//    public int getItemViewType(int position) {
//        return isHeader(position) ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
//    }


    @Override
    public int getItemCount() {
        return result.size();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    public void filterList(ArrayList<ClassItemsCheckboxesForRV> filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }






    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTV;
        public CheckBox valueCH;



        public MyViewHolder(View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.titleTV);
            valueCH = itemView.findViewById(R.id.valueCH);


            // ΜΕ ΚΑΘΕ ΑΛΛΑΓΗ ΣΤΑ CHECKBOXES ΚΑΝΕΙΣ ΑΥΤΟΜΑΤΑΝΑ ΕΝΗΜΕΡΩΣΗ ΣΤΗ ΛΙΣΤΑ

            valueCH.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = (int)valueCH.getTag();
                    result.get(position).setTrue(valueCH.isChecked());

                }
            });








        }




    }













}


