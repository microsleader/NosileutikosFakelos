package micros_leader.george.nosileutikosfakelos.OROFOI.f_Isozigio_pros_apov;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Simple_Items;
import micros_leader.george.nosileutikosfakelos.Simple_items_rv_adapter;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Isozigio_statheresDialog extends DialogFragment {

    private String transgroupID;
    private Isozigio_p_a_Activity act;
    private RecyclerView isozigioRV;
    private CircularProgressButton updateBT;
    private Simple_items_rv_adapter adapter;
    private ArrayList<Simple_Items> result;
    public AlertDialog alertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_isozigio_statheres_dialog, container, false);

        if (getArguments() != null)
              transgroupID = getArguments().getString("transgroupID");

        act = (Isozigio_p_a_Activity) getActivity();

        isozigioRV = view.findViewById(R.id.isozigioRV);
        updateBT = view.findViewById(R.id.updateButton);

        result = InfoSpecificLists.getIsozigio_Apov_Pros_item();
        updateListener();

        alertDialog = Utils.setLoadingAlertDialog(act);
        isozigioRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        isozigioRV.addItemDecoration(new DividerItemDecoration(act, DividerItemDecoration.VERTICAL));
        isozigioRV.setItemViewCacheSize(300);
        isozigioRV.setHasFixedSize(true);
        adapter = new Simple_items_rv_adapter(act,result);
        isozigioRV.setAdapter(adapter);

        getStatheresMetriseis(transgroupID);

        return view;
    }




    private void getStatheresMetriseis(String transgroupID){

        alertDialog.show();

        final AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.query = "select *, dbo.dateToStr(datein) as dateinStr ,dbo.dateToStr(dateout) as dateoutStr " +
                " from  Nursing_isozigio_pros_apov_statheres_metriseis where transgroupID = " + transgroupID;
        task.ctx = act.getApplicationContext();
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {

                if (results != null){
                    ArrayList <Simple_Items> lista = adapter.result;

                    for (int i=0; i<results.length(); i++){

                        JSONObject jsonObject = results.getJSONObject(i);
                        int id = jsonObject.getInt("ID");
                        int itemID = jsonObject.getInt("itemID");
                        String dateIN = Utils.convertObjToString(jsonObject.get("dateinStr"));
                        String dateOut = Utils.convertObjToString(jsonObject.get("dateoutStr"));
                        String perigrafi = Utils.convertObjToString(jsonObject.get("perigrafi"));



                        for(Simple_Items item : lista){
                            if(item.getItemID() == itemID){
                                item.setId(id);
                                item.setDatein(dateIN);
                                item.setDateout(dateOut);
                                item.setValue(perigrafi);
                            }
                        }

                    }
                    adapter.notifyDataSetChanged();

                }
                alertDialog.dismiss();

            }


        };

        task.execute();
    }








    private void updateListener() {
        updateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateBT.startAnimation();
                insertUpdate();
            }
        });
    }


    private void insertUpdate(){

        ArrayList <Simple_Items> lista = adapter.result;

        for (int i=0; i<lista.size(); i++){

            StringBuilder query = new StringBuilder() ;

            long id = lista.get(i).getId();
            int itemID =  result.get(i).getItemID();
            String dateIN = result.get(i).getDatein();

            String dateout = result.get(i).getDateout();
            String value = result.get(i).getValue();

            // ΜΟΝΟ ΟΤΑΝ ΕΧΟΥΜΕ ΚΕΙΜΕΝΟ ΠΕΡΙΓΡΑΦΗ ΝΑ ΚΑΝΕΙ ΕΓΓΡΑΦΗ
            if (value != null && !value.trim().equals("")) {

                if (id == 0) {

                    query.append("insert Nursing_isozigio_pros_apov_statheres_metriseis (transGroupID, itemID, perigrafi,datein,dateout) " +
                            " values(");
                    query.append(transgroupID + ",");
                    query.append(itemID + ",");
                    query.append("'" + value + "' , ");
                    query.append(dateIN.trim().equals("") ? null + "," : Utils.convertDateToDatemilliseconds(dateIN) + ",");
                    query.append(dateout.trim().equals("") ? null + ")" : Utils.convertDateToDatemilliseconds(dateout) + ")");

                } else {
                    query.append("update Nursing_isozigio_pros_apov_statheres_metriseis ");
                    query.append("set ");
                    query.append("perigrafi = '" + value + "' , ");
                    query.append("datein = " + (dateIN.trim().equals("") ? null + "," : Utils.convertDateToDatemilliseconds(dateIN)  + ","));
                    query.append("dateout = " + (dateout.trim().equals("") ? null  : Utils.convertDateToDatemilliseconds(dateout)));
                    query.append(" where id = " + id);


                }



                AsyncTaskUpdate task = new AsyncTaskUpdate(act, query.toString());
                task.listener = new AsyncGetUpdateResult() {
                    @Override
                    public void update(String str) {

                        Toast.makeText(act, str, Toast.LENGTH_SHORT).show();

                        updateBT.revertAnimation();

                    }
                };

                task.execute();

            }
            else{
                updateBT.stopAnimation();
            }
        }




    }







    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (dialog.getWindow() != null)
                dialog.getWindow().setLayout(width, height);
        }
    }


}
