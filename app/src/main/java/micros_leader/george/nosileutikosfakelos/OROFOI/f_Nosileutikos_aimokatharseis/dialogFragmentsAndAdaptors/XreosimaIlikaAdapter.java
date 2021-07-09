package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.R;

public class XreosimaIlikaAdapter  extends RecyclerView.Adapter<XreosimaIlikaAdapter.MyViewHolder> {


    private Context context;

    private ArrayList<XreosimaIlika> result = null;
    private DIalogFragmentXreosimaIlika dialog;


    // create constructor to innitilize context and data sent from MainActivity
    public XreosimaIlikaAdapter(Context context, ArrayList<XreosimaIlika> result, DIalogFragmentXreosimaIlika dialog){
        this.context = context;
        this.result = result;
        this.dialog = dialog;



    }

    @Override
    public XreosimaIlikaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_xreosima_ilika_recycler_layout, parent, false);
        XreosimaIlikaAdapter.MyViewHolder vh = new XreosimaIlikaAdapter.MyViewHolder(v); // pass the view to View Holder

        return vh;
    }



    @Override
    public void onBindViewHolder(final XreosimaIlikaAdapter.MyViewHolder holder, final int position) {
        // set the data in items

        holder.name.setText(result.get(position).getName());
        holder.tmima.setText(result.get(position).getTmima());
        holder.posotita.setText(result.get(position).getPosotita());


    }


    @Override
    public int getItemCount() {
        return result.size();
    }






    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, tmima, posotita;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameitemTV);
            tmima = itemView.findViewById(R.id.tmimaTV);
            posotita = itemView.findViewById(R.id.posotitaTV);


        }
    }
}
