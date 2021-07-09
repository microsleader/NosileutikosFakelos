package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_logodosia;

import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.R;

public class Nosil_logodosia_dates_Adapter extends RecyclerView.Adapter <Nosil_logodosia_dates_Adapter.MyViewHolder> implements SearchView.OnQueryTextListener {

    public ArrayList<Nosil_Logodosies> result;
    private Nosil_Anafora_Logodosia_Activity activity;

    public Nosil_logodosia_dates_Adapter(Nosil_Anafora_Logodosia_Activity activity, ArrayList<Nosil_Logodosies> result){
        this.activity = activity;
        this.result = result;

    }

    @Override
    public Nosil_logodosia_dates_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_nosil_log_dates_layout, parent, false);
        Nosil_logodosia_dates_Adapter.MyViewHolder vh = new Nosil_logodosia_dates_Adapter.MyViewHolder(v); // pass the view to View Holder



        return vh;
    }


    @Override
    public void onBindViewHolder(final Nosil_logodosia_dates_Adapter.MyViewHolder holder, final int position) {
        // set the data in items


        final String dateStr = result.get(position).getDateStr();

        if (position == 0   ||  !dateStr.equals(result.get(position - 1).getDateStr()))
            holder.dateTV.setText(dateStr);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                activity.setItemsDependingDate(dateStr);
                activity.clearElements();
            }
        });




    }


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

    public void filterList(ArrayList<Nosil_Logodosies> filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dateTV;


        public MyViewHolder(View itemView) {
            super(itemView);

            dateTV = itemView.findViewById(R.id.dateTV);


        }
    }







}

