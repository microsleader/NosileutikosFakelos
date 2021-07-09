package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Nosil_Elegxos;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended;

import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Spinner_items_lists;
import micros_leader.george.nosileutikosfakelos.Spinner_new_Image_Adapter;
import micros_leader.george.nosileutikosfakelos.Utils;

public class DialogKlimakaGlaskovis extends DialogFragment {


    private RecyclerView recyclerView;
    private Activity act;
    private String  transgroupID ,vardiaID,patientName ,date;
    private ImageButton updateIMB;
    private Toolbar toolbar;
    private TextView patientsTV;
    private Spinner vardiaSP;
    private ArrayList<ItemsRV> lista = new ArrayList<>() ,listaAdaptor;
    private BasicRV adapter ;
    public int id =0 , mati,omilia,kinisi;
    private ArrayAdapter adapterSP;
    private boolean weHaveData;
    private AlertDialog alertDialog;
    private DataSended listener;
    private ArrayList<String> eidiLista;
    private HashMap<String, Integer> eidiMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_klimaka_glaskovis_dialog, container, false);
        // view.setSupportActionBar(t);
        act = getActivity();
        listener = (DataSended) act;
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            id = getArguments().getInt("id");
            transgroupID = bundle.getString("transgroupID");
            patientName = bundle.getString("name");
            vardiaID = bundle.getString("vardiaID");
            weHaveData = bundle.getBoolean("weHaveData");
            date = bundle.getString("date");
            mati = bundle.getInt("mati");
            omilia = bundle.getInt("omilia");
            kinisi = bundle.getInt("kinisi");

        }


        toolbar = view.findViewById(R.id.toolbar);
        patientsTV = view.findViewById(R.id.patientsTV);
        vardiaSP = view.findViewById(R.id.vardiaSP);
        adapterSP = new Spinner_new_Image_Adapter(act, R.layout.spinner_layout2,
                Spinner_items_lists.getVardiesLista().toArray(new Spinner_item[3]));


        adapterSP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vardiaSP.setAdapter(adapterSP);
        alertDialog = Utils.setLoadingAlertDialog(act);

       // valueSP.setSelection(spinner_pos);

        patientsTV.setText(patientName);

        recyclerView = view.findViewById(R.id.klimakaRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(act, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(act, DividerItemDecoration.VERTICAL));
        recyclerView.setItemViewCacheSize(300);
        recyclerView.setHasFixedSize(true);
        lista = InfoSpecificLists.getKlimakaGlaskovis();
        adapter = new BasicRV(act,lista);
        listaAdaptor = adapter.result;
        recyclerView.setAdapter(adapter);

        imageUpdateButton();
        getKlimakaGlaskovis();



        return view;
    }


    public void getKlimakaGlaskovis(){

        alertDialog.show();
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.ctx = act;
        task.query = "select id, mati,omilia,kinisi from Nursing_meth_nosil_elegxos " +
                "  where transgroupID = " + transgroupID + " and vardiaID = " + vardiaID
        +   " and  dbo.datetostr(date) = dbo.datetostr(dbo.timeToNum(CONVERT(datetime, '" +  date  + "' , 103)))";
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {

                if (results!= null && !results.getJSONObject(0).has("status")){

                    //lista.clear();

                        JSONObject jsonObject = results.getJSONObject(0);
                        adapter.result.get(0).setValue(Utils.convertObjToString(jsonObject.get("mati")));
                        adapter.result.get(1).setValue(Utils.convertObjToString(jsonObject.get("omilia")));
                        adapter.result.get(2).setValue(Utils.convertObjToString(jsonObject.get("kinisi")));



                    alertDialog.dismiss();
                    adapter.notifyDataSetChanged();

                }

            }
        };

        task.execute();

    }






    private void imageUpdateButton(){


        updateIMB = new ImageButton(act);
        Toolbar.LayoutParams l1=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l1.gravity= Gravity.START;
        updateIMB.setLayoutParams(l1);
        updateIMB.setBackgroundResource(R.drawable.save_icon_48px);
        updateIMB.setPadding(20,20,20,20);
        updateIMB.setScaleType(ImageButton.ScaleType.FIT_CENTER);
        toolbar.addView(updateIMB);
        Toolbar.LayoutParams l3=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l3.gravity= Gravity.END;
        updateIMB.setLayoutParams(l3);


        updateIMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.show();
                insertOrUpdate();

            }
        });
    }





    private void insertOrUpdate() {

        String matiID  = lista.get(0).getValue();
        String omiliaID = lista.get(1).getValue();;
        String kinisiID = lista.get(2).getValue();;

        StringBuilder query = new StringBuilder();
        if (id == 0) {
            query.append("exec dbo.disable_triggers " +
                    "insert Nursing_meth_nosil_elegxos (transgroupid ,date,userid, vardiaid, mati ,omilia, kinisi) values ( " );

            query.append(transgroupID + ", " );
            query.append(Utils.convertDateTomilliseconds(Utils.getCurrentDate()) + ",");
            query.append(Utils.getUserID(act) + ", " );
            query.append(vardiaID + ", " );
            query.append(matiID + ", " );
            query.append(omiliaID + ", " );
            query.append(kinisiID + " ) " );

            query.append("exec dbo.enable_triggers");
        }
        else{
            query.append("exec dbo.disable_triggers ");
            query.append("update Nursing_meth_nosil_elegxos set ");
            query.append("userid = " + Utils.getUserID(act) + ",")  ;
            query.append("mati = " +  matiID + ",");
            query.append("omilia = " +  omiliaID + ",");
            query.append("kinisi = " +  kinisiID );
            query.append(" where id = " + id);
            query.append(" exec dbo.enable_triggers");

        }






            AsyncTaskUpdate task = new AsyncTaskUpdate(act, query.toString());
        task.listener = new AsyncGetUpdateResult() {
            @Override
            public void update(String str) {

                alertDialog.dismiss();
                Toast.makeText(act, str, Toast.LENGTH_SHORT).show();

            }
        };
        task.execute();

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





    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        int totalScore = Integer.parseInt(lista.get(0).getValue()) +  Integer.parseInt(lista.get(1).getValue())
                +  Integer.parseInt(lista.get(2).getValue());

        listener.hereIsYourData(totalScore);

    }
}
