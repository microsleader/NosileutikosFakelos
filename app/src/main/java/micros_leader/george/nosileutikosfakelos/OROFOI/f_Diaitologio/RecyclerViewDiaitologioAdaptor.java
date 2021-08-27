package micros_leader.george.nosileutikosfakelos.OROFOI.f_Diaitologio;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;

import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class RecyclerViewDiaitologioAdaptor extends RecyclerView.Adapter<RecyclerViewDiaitologioAdaptor.MyViewHolder> implements SearchView.OnQueryTextListener {


    private ArrayList<Diaitologio> result;
    private Activity act;
    private String logodosia_text, id_logodosias;
    private int  pos;
    private Context ctx;



    // create constructor to innitilize context and data sent from MainActivity
    public RecyclerViewDiaitologioAdaptor(Activity act , ArrayList <Diaitologio> result) {

        this.act = act;
        this.result = result;



    }

    @Override
    public RecyclerViewDiaitologioAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_diaitologio_adapter_layout, parent, false);
        RecyclerViewDiaitologioAdaptor.MyViewHolder vh = new RecyclerViewDiaitologioAdaptor.MyViewHolder(v); // pass the view to View Holder

        return vh;
    }


    @Override
    public void onBindViewHolder(final RecyclerViewDiaitologioAdaptor.MyViewHolder holder, final int position) {
        // set the data in items

        final String diaita = result.get(position).getDieta().replace("\ufffd",",").replace("?",",");
        final String transgroupID = result.get(position).getTransgroupid();
        final String id = result.get(position).getId();
        final String hourFrom = result.get(position).getHourFrom();
        final String dateFrom = result.get(position).getDatefrom();
        final String sxolia = result.get(position).getSxolia();
        final String sitisiSinodou = result.get(position).getSitisiSinodou();
        final String row_userID = result.get(position).getUserID();

        // AN KAI TA DIO EXOUN KEIMENO NA VALEI STI MESI KOMMA
        String comma = "";
        if (!dateFrom.equals("") && !hourFrom.equals(""))
            comma = " , ";

        holder.diaitaTV.setText(diaita);


        holder.dateHourTV.setText(dateFrom + comma + hourFrom);
        holder.sxoliaTV.setText(sxolia);
        holder.sitisiSinodouTV.setText(sitisiSinodou);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pos = position;
                showAlertDialog(id, transgroupID,dateFrom,hourFrom,diaita,sxolia,sitisiSinodou,row_userID);



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

    public void filterList(ArrayList<Diaitologio> filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dateHourTV,diaitaTV, sxoliaTV, sitisiSinodouTV;


        public MyViewHolder(View itemView) {
            super(itemView);

            dateHourTV = itemView.findViewById(R.id.dateHourTV);
            diaitaTV = itemView.findViewById(R.id.diaitaTV);
            sxoliaTV = itemView.findViewById(R.id.sxoliaTV);
            sitisiSinodouTV = itemView.findViewById(R.id.sitisiSinodouTV);


        }
    }


    private void showAlertDialog(final String id, final String transgroupID,final String dateFrom, final String hourFrom, final String diaita,
                                 final String sxolia, final String sitisiSinodou, final String row_userID) {

        new FancyAlertDialog.Builder(act)
                .setTitle("Επιλογές")
                .setBackgroundColor(Color.parseColor("#ecae10"))  //Don't pass R.color.colorvalue
                .setMessage("Διαγραφή ή Ενημέρωση κειμένου ")
                .setPositiveBtnBackground(Color.parseColor("#ecae10"))  //Don't pass R.color.colorvalue

                .setPositiveBtnText("Ενημέρωση")
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                        showUpdateFragmentDialog( id ,transgroupID, dateFrom,hourFrom,diaita,sxolia,sitisiSinodou,row_userID);
                    }
                })

                .setNegativeBtnText("Διαγραφή")
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                        delete(id,row_userID);
                    }
                })
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.error_icon, Icon.Visible)
                .build();
    }


    private void showUpdateFragmentDialog(String id,String transgroupID, String dateFrom, String hourFrom, String diaita, String sxolia, String sitisiSinodou,String row_userID) {


        DialogFragmentUpdateDiaitologio df =  new DialogFragmentUpdateDiaitologio();
        Bundle putextra = new Bundle();
        putextra.putString("id", id);
        putextra.putString("transgroupID", transgroupID);
        putextra.putString("dateFrom", dateFrom);
        putextra.putString("hourFrom",hourFrom);
        putextra.putString("diaita",diaita);
        putextra.putString("sxolia",sxolia);
        putextra.putString("sitisiSinodou",sitisiSinodou);
        putextra.putString("userID",row_userID);
        df.setArguments(putextra);
        df.show(((DiaitologioActivity)act).getSupportFragmentManager() , "Dialog");

    }






    private void delete(String id,String row_userID) {

        String query = new Str_queries().getDIAITOLOGIO_DELETE(id);
        AsyncTaskUpdate task = new AsyncTaskUpdate(act, query);
        task.listener = new AsyncGetUpdateResult() {
            @Override
            public void update(String str) {
                //TO APOTELESMA TO UPDATE ERXETAI EDW
                // STO FRAGMENT ME AYTON TON TROPO GINETAI DILONETAI

                if (!row_userID.equals(Utils.getUserID(act))){
                    Toast.makeText(act, R.string.error_user_id, Toast.LENGTH_SHORT).show();
                    return;
                }

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

