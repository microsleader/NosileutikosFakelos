package micros_leader.george.nosileutikosfakelos.DialogFragmentSearches;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

public class SearchPatientNosilAdapter extends RecyclerView.Adapter<SearchPatientNosilAdapter.MyViewHolder> implements SearchView.OnQueryTextListener{


    private Context context;

    private ArrayList<PatientsOfTheDay> result = null;
    private DialogFragmentSearchPatientNosileuomenos dialog;
    private Activity activityFromSigxoneusi;
    TextView txtView;
    public MyDialogFragmentCloseListener listener = null;


    // create constructor to innitilize context and data sent from MainActivity
    public SearchPatientNosilAdapter(Context context, ArrayList result, DialogFragmentSearchPatientNosileuomenos dialog){
        this.context = context;
        this.result = result;
        this.dialog = dialog;
        this.listener = (MyDialogFragmentCloseListener) context;
        txtView = (TextView) ((Activity)context).findViewById(R.id.patientsTV);


    }

    public SearchPatientNosilAdapter(Context context,Activity activityFromSigxoneusi, ArrayList result, DialogFragmentSearchPatientNosileuomenos dialog){
        this.context = context;
        this.result = result;
        this.dialog = dialog;
        this.listener = (MyDialogFragmentCloseListener) activityFromSigxoneusi;
        txtView = (TextView) ((Activity)context).findViewById(R.id.patientsTV);


    }



    @Override
    public SearchPatientNosilAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_search_recycler_layout, parent, false);
        SearchPatientNosilAdapter.MyViewHolder vh = new SearchPatientNosilAdapter.MyViewHolder(v); // pass the view to View Holder

        return vh;
    }



    @Override
    public void onBindViewHolder(final SearchPatientNosilAdapter.MyViewHolder holder, final int position) {
        // set the data in items
        final PatientsOfTheDay apotelesmata = result.get(position);

        final String bed = apotelesmata.getBed();

        final String namePat = apotelesmata.getFirstName() + " " + apotelesmata.getLastName() + " του " + apotelesmata.getFatherName();
        final String transgroupID = String.valueOf(apotelesmata.getTransgroupID());
        final String code = apotelesmata.getCode();

        holder.name.setText(bed + " , " + code + " , " + namePat + " , " + transgroupID);

        final String isEmergency = apotelesmata.getIsEmergency();


        if (isEmergency.equals("true") || isEmergency.equals("1")){
            holder.name.setTextColor(Color.RED);
        }


        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtView.setText(bed + " , " + code + " , " + namePat + " , " + transgroupID);
                if (isEmergency.equals("true") || isEmergency.equals("1"))
                    txtView.setTextColor(Color.RED);
                else
                    txtView.setTextColor(Color.BLACK);

                listener.handleDialogClose(bed + " , " + code + " , " + namePat + " , " + transgroupID);
                Utils.setPosPatientID(context,position);
                dialog.dismiss();


            }
        });


    }


    @Override
    public int getItemCount() {
        return result.size();
    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void filterList(ArrayList<PatientsOfTheDay> filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name ;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameItem);





        }
    }
}
