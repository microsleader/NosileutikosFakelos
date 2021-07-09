package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.KalliergiesDialog;

import android.app.Activity;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskDelete;

import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetDelete;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.KalliergiesItems;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.ThetikesKalliergies.ThetikesKalliergiesDialog;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

public class KalliergiesRVAdaptor extends RecyclerView.Adapter<KalliergiesRVAdaptor.MyViewHolder> {


    private ArrayList<KalliergiesItems> lista;
    private DialogFragment dialogFragment;
    private Activity act;
    private String table;
    private int adapterPos;


    public KalliergiesRVAdaptor(DialogFragment dialogFragment, ArrayList<KalliergiesItems> lista) {
        this.dialogFragment = dialogFragment;
        this.lista = lista;

        act = dialogFragment.getActivity();


    }




    @NonNull
    @Override
    public KalliergiesRVAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.kalliergies_rv_adaptor, viewGroup, false);

        KalliergiesRVAdaptor.MyViewHolder vh = new KalliergiesRVAdaptor.MyViewHolder(v); // pass the view to View Holder



        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final KalliergiesRVAdaptor.MyViewHolder viewHolder, int i) {

        KalliergiesItems item = lista.get(i);

         final String deigma= item.getDigma(), apotelesma= item.getApotelesma(), eidos= item.getEidos(), itemName = item.itemname,
                 date= item.getDate(), dateSent= item.getDateSent(), mikrovio = item.getMikrovio();
         final int id = item.getId(), itemID = item.getItemID() , nurseID = item.getNurseID();

       if (itemID !=0) {
           viewHolder.eidosDigmaTV.setText(String.valueOf(itemID));
           if (itemName != null && !itemName.isEmpty())
               viewHolder.eidosDigmaTV.append(" , " + itemName);
         //  viewHolder.eidosDigmaTV.setOnClickListener(new SearchEidosListener(act, eidiLista, eidiMap));
       }

       if (deigma != null)
           viewHolder.eidosDigmaTV.setText(deigma);



        if (dateSent !=null)
            viewHolder.datesentTV.setText(dateSent);


        if (apotelesma !=null)
            viewHolder.apotelesmaMikrovioRV.setText(apotelesma);

        else if (mikrovio !=null)
            viewHolder.apotelesmaMikrovioRV.setText(mikrovio);



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (String.valueOf(nurseID).equals(Utils.getUserID(act))) {


                    if (dialogFragment instanceof KalliergiesDialog){

                        table = "Nursing_kalliergies_meth";

                        ((KalliergiesDialog) dialogFragment).datesentET.setText(dateSent);
                        ((KalliergiesDialog) dialogFragment).eidosTV.setText(String.valueOf(itemID + " , " + itemName));
                        ((KalliergiesDialog) dialogFragment).mikrovioET.setText(mikrovio);
                        ((KalliergiesDialog) dialogFragment).id = id;

                    }

                    else if (dialogFragment instanceof ThetikesKalliergiesDialog){

                        table = "Nursing_thetikes_kalliergies_meth";

                        ((ThetikesKalliergiesDialog) dialogFragment).deigmaET.setText(String.valueOf(deigma));
                        ((ThetikesKalliergiesDialog) dialogFragment).dateDeigmatosTV.setText(dateSent);
                        ((ThetikesKalliergiesDialog) dialogFragment).apotelesmaET.setText(apotelesma);
                        ((ThetikesKalliergiesDialog) dialogFragment).id = id;
                    }


                }

                else
                    Toast.makeText(act, R.string.error_user_id, Toast.LENGTH_SHORT).show();

            }
        });



        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (String.valueOf(nurseID).equals(Utils.getUserID(act))) {


                    if (dialogFragment instanceof KalliergiesDialog)
                        table = "Nursing_kalliergies_meth";
                    else
                        table = "Nursing_thetikes_kalliergies_meth";



                    new AlertDialog.Builder(act)
                            .setTitle("Διαγραφή εγγραφης;")
                            .setMessage("Είστε σίγουροι πως θέλετε να διαγράψετε")

                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    adapterPos = viewHolder.getAdapterPosition();
                                    delete(String.valueOf(id));

                                }
                            })

                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

                else
                    Toast.makeText(act, R.string.error_user_id, Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView eidosDigmaTV,datesentTV,apotelesmaMikrovioRV;


        public MyViewHolder(final View itemView) {
            super(itemView);

            eidosDigmaTV = itemView.findViewById(R.id.eidosDigmaTV);
            datesentTV = itemView.findViewById(R.id.datesentTV);
            apotelesmaMikrovioRV = itemView.findViewById(R.id.apotelesmaMikrovioRV);

        }


    }


    private void delete(String id){
        AsyncTaskDelete task = new AsyncTaskDelete(act , table, id);
        task.listener = new AsyncGetDelete() {
            @Override
            public void deleteResult(String str) {
                Toast.makeText(act, str, Toast.LENGTH_SHORT).show();
                if (str.equals("Πετυχημένη ενημέρωση")) {
                    lista.remove(adapterPos);
                    notifyItemRemoved(adapterPos);
                    notifyItemRangeChanged(adapterPos,lista.size());

                    if (dialogFragment instanceof KalliergiesDialog){
                        ((KalliergiesDialog) dialogFragment).datesentET.setText("");
                        ((KalliergiesDialog) dialogFragment).eidosTV.setText("");
                        ((KalliergiesDialog) dialogFragment).mikrovioET.setText("");
                        ((KalliergiesDialog) dialogFragment).id = 0;
                    }

                    else if (dialogFragment instanceof ThetikesKalliergiesDialog){
                        ((ThetikesKalliergiesDialog) dialogFragment).deigmaET.setText("");
                        ((ThetikesKalliergiesDialog) dialogFragment).apotelesmaET.setText("");
                        ((ThetikesKalliergiesDialog) dialogFragment).id = 0;
                    }
                }
            }
        };
        task.execute();
    }
}
