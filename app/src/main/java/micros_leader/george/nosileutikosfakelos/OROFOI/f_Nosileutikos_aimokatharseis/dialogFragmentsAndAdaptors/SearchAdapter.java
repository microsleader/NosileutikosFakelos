package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors;

import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataListener;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended_str;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities.Nephroxenia_Main_Activity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> implements SearchView.OnQueryTextListener{


    private Context context;

    private ArrayList<PatientsOfTheDay> result = null;
    private Nephroxenia_Main_Activity main;
    private micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim main_aim_test3;

    private DataListener dataListener;
    private DataSended_str dataSendedStr;
    private DialogFragmentSearchPat dialog;
    private boolean isFrontis;


    // create constructor to innitilize context and data sent from MainActivity
    public SearchAdapter(Context context, ArrayList result,DialogFragmentSearchPat dialog){
        this.context = context;
        this.result = result;
        if (context instanceof micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim)
        dataListener = (DataListener) context;

        if (context instanceof Nephroxenia_Main_Activity)
             main = (Nephroxenia_Main_Activity) context;

        else if (context instanceof micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim)
            main_aim_test3 = (micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim) context;

        else
            dataSendedStr = (DataSended_str) context;


        this.dialog = dialog;
        isFrontis  = Customers.isFrontis(Utils.getCustomerID(context));



    }

    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_search_recycler_layout, parent, false);
        SearchAdapter.MyViewHolder vh = new SearchAdapter.MyViewHolder(v); // pass the view to View Holder

        return vh;
    }



    @Override
    public void onBindViewHolder(final SearchAdapter.MyViewHolder holder, final int position) {
        // set the data in items
        final PatientsOfTheDay apotelesmata = result.get(position);


        final String namePat = apotelesmata.getLastName() + " " + apotelesmata.getFirstName();
        final int patID = apotelesmata.getPatientID();
        final String code = apotelesmata.getCode();
        final String amka = apotelesmata.getAmka();

        final String age = apotelesmata.getAge();
        final String height = apotelesmata.getHeight();
        final String patCode = apotelesmata.getPatCode();

        final String info = (code != null ? code + " , " : "") +
                namePat + " , " + amka +
                (isFrontis ? "\n,"  +
                "Κωδ: " + patCode + " , " +
                "Ηλικία: " + age + " , " +
                "Ύψος: " + height + " cm" : "");
        holder.name.setText(info);

        final String isEmergency = apotelesmata.getIsEmergency();


        if (isEmergency != null && (isEmergency.equals("true") || isEmergency.equals("1"))){
            holder.name.setTextColor(Color.RED);
        }


        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // PALIO ACTIVITY
                if (context instanceof Nephroxenia_Main_Activity) {

                    main.search_TV.setText(info);
                    if (isEmergency != null && (isEmergency.equals("true") || isEmergency.equals("1")))
                        main.search_TV.setTextColor(Color.RED);
                    else
                        main.search_TV.setTextColor(Color.BLACK);

                }


                else if (context instanceof micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim){
                    main_aim_test3.bd.patientsTV.setText(info);
                    if (isEmergency != null && (isEmergency.equals("true") || isEmergency.equals("1")))
                        main_aim_test3.bd.patientsTV.setTextColor(Color.RED);
                    else
                        main_aim_test3.bd.patientsTV.setTextColor(Color.BLACK);

                   // main_aim_test3.addFragments();  //gia na ksanavalei ta dedomena me to neo epilegmeno astheni

                    dataListener.dataHasArrived(true);
                }

                else
                    dataSendedStr.hereIsYourStr_Data(patID + " , " + info);




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


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name ;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameItem);





        }
    }
}
