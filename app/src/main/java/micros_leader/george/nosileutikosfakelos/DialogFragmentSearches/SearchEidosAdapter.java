package micros_leader.george.nosileutikosfakelos.DialogFragmentSearches;

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

public class SearchEidosAdapter extends RecyclerView.Adapter<SearchEidosAdapter.MyViewHolder> implements SearchView.OnQueryTextListener{


    private DialogFragment dialogFragment;
    private ArrayList<String> result = null;
    TextView txtView;
    public MyDialogFragmentMedicineCloseListener listener = null;




    public SearchEidosAdapter(DialogFragment dialogFragment, ArrayList result){
        this.dialogFragment = dialogFragment;
        this.result = result;



    }

    @Override
    public SearchEidosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_search_recycler_layout, parent, false);
        SearchEidosAdapter.MyViewHolder vh = new SearchEidosAdapter.MyViewHolder(v); // pass the view to View Holder

        return vh;
    }



    @Override
    public void onBindViewHolder(final SearchEidosAdapter.MyViewHolder holder, final int position) {
        // set the data in items
        final String apotelesmata = result.get(position);


        holder.name.setText(apotelesmata);



        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                txtView.setText(apotelesmata);

                listener.dialogMedicineClose(apotelesmata);

                if (dialogFragment !=null)
                      dialogFragment.dismiss();

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

    public void filterList(ArrayList<String> filteredList) {

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
