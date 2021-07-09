package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities;

import android.app.Activity;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors.DialogFragment_emfanisi_epilegmenis_metrisis;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.R;


public class RecyclerView_Metriseis_Adapter extends RecyclerView.Adapter<RecyclerView_Metriseis_Adapter.MyViewHolder> implements SearchView.OnQueryTextListener {


    private ArrayList<Map<String, String>> result;
    private Activity act;
    private FragmentManager fg;
    private boolean isStatheres;
    private String currentDay;


    // create constructor to innitilize context and data sent from MainActivity
    public RecyclerView_Metriseis_Adapter(Activity act, ArrayList<Map<String, String>> result) {

        this.act = act;
        this.result = result;

        if (act instanceof StatheresMetriseis_All_Activity){
           fg =((StatheresMetriseis_All_Activity)act).getSupportFragmentManager();
             isStatheres = true;
        }
        else {
            fg = ((SinexeisMetriseis_All_Activity) act).getSupportFragmentManager();
            isStatheres = false;
        }


        currentDay = Utils.getCurrentDate();


    }

    @Override
    public RecyclerView_Metriseis_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_metriseis_rv_layout, parent, false);
        RecyclerView_Metriseis_Adapter.MyViewHolder vh = new RecyclerView_Metriseis_Adapter.MyViewHolder(v); // pass the view to View Holder

        return vh;
    }


    @Override
    public void onBindViewHolder(final RecyclerView_Metriseis_Adapter.MyViewHolder holder, final int position) {
        // set the data in items



        if (position == 0 && !isStatheres ){

            String dateString = Objects.requireNonNull(
                    Utils.getSplitSPartString(result.get(position).get("datestr")," ",0)).replace("-","/");

            if (currentDay.equals(dateString)) {
                holder.editableTextTV.setVisibility(View.VISIBLE);
                holder.editableTextTV.setText("Τροποποιήσιμη μέτρηση");
            }
        }

        String dateTime;
        if (isStatheres)
            dateTime = result.get(position).get("datestr") + "   " +  result.get(position).get("timeStart");
        else
            dateTime = (Utils.convertMillisecondsToDateTime(result.get(position).get("Date")));

        holder.dateTV.setText(dateTime);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    DialogFragment_emfanisi_epilegmenis_metrisis df = new DialogFragment_emfanisi_epilegmenis_metrisis();
                    Bundle putextra = new Bundle();
                    putextra.putSerializable("map", (Serializable) result.get(position));
                    putextra.putBoolean("statheresMetriseis",isStatheres);

                if (!isStatheres && position == 0 && currentDay.equals(Objects.requireNonNull(
                                Utils.getSplitSPartString(result.get(position).get("datestr")," ",0)).replace("-","/"))) {

                    putextra.putBoolean("teleutaiaSinexiMetrisi", true);
                }
                    df.setArguments(putextra);

                    df.show(fg , "Dialog");

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

    public void filterList(ArrayList<Map<String, String>> filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  dateTV,editableTextTV;



        public MyViewHolder(View itemView) {
            super(itemView);

            dateTV = itemView.findViewById(R.id.dateTV);
            editableTextTV = itemView.findViewById(R.id.editableTextTV);
        }
    }
















}

