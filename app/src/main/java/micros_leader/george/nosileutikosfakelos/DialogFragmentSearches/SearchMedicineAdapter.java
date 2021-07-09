package micros_leader.george.nosileutikosfakelos.DialogFragmentSearches;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentMedicineCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon.Medicines;

public class SearchMedicineAdapter extends RecyclerView.Adapter<SearchMedicineAdapter.MyViewHolder> implements SearchView.OnQueryTextListener{


    private Context context;

    public ArrayList<Medicines> result = null;
    private DialogFragment dialog;
    TextView txtView;
    private MyDialogFragmentMedicineCloseListener listener = null;

    private SharedPreferences sp1 ;
    private SharedPreferences.Editor editor ;

    //DF_SearchMedicineFromMemory

    public SearchMedicineAdapter(Context context, ArrayList result, DialogFragment dialog){
        this.context = context;
        this.result = result;
        this.dialog = dialog;

        this.listener = (MyDialogFragmentMedicineCloseListener) context;
        txtView = (TextView) ((Activity)context).findViewById(R.id.valueTV);


        sp1 =  context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        editor= sp1.edit();

    }

    @Override
    public SearchMedicineAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_search_recycler_layout, parent, false);
        SearchMedicineAdapter.MyViewHolder vh = new SearchMedicineAdapter.MyViewHolder(v); // pass the view to View Holder

        return vh;
    }



    @Override
    public void onBindViewHolder(final SearchMedicineAdapter.MyViewHolder holder, final int position) {
        // set the data in items
        final Medicines apotelesmata = result.get(position);


        final String id = apotelesmata.getItemID();
        final String itemName = String.valueOf(apotelesmata.getItemname());
        holder.name.setText(id + " , " + itemName);



        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtView != null)
                  txtView.setText(id + " , " + itemName);
                editor.putString("medicineValueJson", id);
                editor.putString("medicineNameJson", id + " , " + itemName );

                editor.apply();
                listener.dialogMedicineClose(id + " , " + itemName);

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

    public void filterList(ArrayList<Medicines> filteredList) {

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
