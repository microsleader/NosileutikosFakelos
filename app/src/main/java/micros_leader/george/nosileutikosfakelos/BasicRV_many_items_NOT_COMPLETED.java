package micros_leader.george.nosileutikosfakelos;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.ArrayList;
import java.util.HashMap;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;

public class BasicRV_many_items_NOT_COMPLETED extends RecyclerView.Adapter<BasicRV_many_items_NOT_COMPLETED.MyViewHolder> implements SearchView.OnQueryTextListener {


    public ArrayList result;
    public Activity act;
    public int pos;
    public int recyclerview_layout_id;
    public View v;
    public MyViewHolder vh;
    private ArrayList<String> lista =  new ArrayList<>();



    // create constructor to innitilize context and data sent from MainActivity
    public BasicRV_many_items_NOT_COMPLETED(Activity act, ArrayList result, int recyclerview_layout_id) {

        this.act = act;
        this.result = result;
        this.recyclerview_layout_id = recyclerview_layout_id;


    }





    @Override
    public BasicRV_many_items_NOT_COMPLETED.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout

        // View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_item_checkbox_rec_adapter, parent, false);
        v = LayoutInflater.from(parent.getContext()).inflate(recyclerview_layout_id, parent, false);

         vh = new BasicRV_many_items_NOT_COMPLETED.MyViewHolder(v); // pass the view to View Holder

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos =  vh.getAdapterPosition();
            }
        });

        return vh;
    }


    @Override
    public void onBindViewHolder(final BasicRV_many_items_NOT_COMPLETED.MyViewHolder holder, final int position) {
        // set the data in items


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

    public void filterList(ArrayList<ItemsRV> filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(final View itemView) {
            super(itemView);

        }


    }



    public void showAlertDialog(final String id, final String transgroupID,final String dateFrom, final String hourFrom, final String diaita,
                                 final String sxolia, final String sitisiSinodou) {

        new FancyAlertDialog.Builder(act)
                .setTitle("Επιλογές")
                .setBackgroundColor(Color.parseColor("#ecae10"))  //Don't pass R.color.colorvalue
                .setMessage("Διαγραφή ή Ενημέρωση κειμένου ")
                .setPositiveBtnBackground(Color.parseColor("#ecae10"))  //Don't pass R.color.colorvalue

                .setPositiveBtnText("Ενημέρωση")
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                      //  showUpdateFragmentDialog();
                    }
                })

                .setNegativeBtnText("Διαγραφή")
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                        delete(id);
                    }
                })
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.error_icon, Icon.Visible)
                .build();
    }



    public void showUpdateFragmentDialog(DialogFragment dialogFragment, HashMap<String,String> map) {


        DialogFragment df = dialogFragment;

        if (map != null && !map.isEmpty()) {
            Bundle putextra = new Bundle();

            for ( String key : map.keySet() ) {

                String value = map.get(key);
                putextra.putString(key, value);

            }
            df.setArguments(putextra);

        }
       // df.show(((DiaitologioActivity_UNCLOMPLETED)act).getSupportFragmentManager() , "Dialog");

    }







    public void delete(String query) {

        AsyncTaskUpdate task = new AsyncTaskUpdate(act, query);
        task.listener = new AsyncGetUpdateResult() {
            @Override
            public void update(String str) {
                //TO APOTELESMA TO UPDATE ERXETAI EDW
                // STO FRAGMENT ME AYTON TON TROPO GINETAI DILONETAI

                if (str.equals("Πετυχημένη ενημέρωση")) {
                    Toast.makeText(act, "Διαγραφή επιτυχής", Toast.LENGTH_SHORT).show();
                    //KANEI UPDATE STI LISTA KAI EPEITA ENIMEROSI STON ADAPTER
                    result.remove(pos);
                    notifyDataSetChanged();



                }

                else
                    Toast.makeText(act, "Κάτι πήγε στραβά", Toast.LENGTH_SHORT).show();
            }
        };

        task.execute();
    }


}


