package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.KalliergiesDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentMedicineCloseListener;
import micros_leader.george.nosileutikosfakelos.Listeners.SearchEidosListener;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.KalliergiesItems;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

public class KalliergiesDialog extends DialogFragment {


    private RecyclerView recyclerView;
    private Activity act;
    private String date , transgroupID;
    private ImageButton updateIMB;
    private Toolbar toolbar;
    private ArrayList<KalliergiesItems> lista = new ArrayList<>();
    private KalliergiesRVAdaptor adapter;
    public EditText mikrovioET;
    public TextView eidosTV, datesentET;
    public int id =0;
    public String dateFilladiou;
    private Button neaeggrafiBT;
    private int departmentID = 30;
    private ArrayList<String> eidiLista;
    private HashMap <String, Integer> eidiMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_kalliergies_dialog, container, false);
       // view.setSupportActionBar(t);
        act = getActivity();
        if (getArguments() != null) {
            date = getArguments().getString("date");
            transgroupID = getArguments().getString("transgroupID");

        }

        toolbar = view.findViewById(R.id.toolbar);
        eidosTV = view.findViewById(R.id.eidosTV);
        datesentET = view.findViewById(R.id.dateSentET);
        Utils.setDatePicker(datesentET,act);
        mikrovioET = view.findViewById(R.id.mikrovioET);
        neaeggrafiBT = view.findViewById(R.id.neaEggrafiBT);
        neaeggrafiBTListener();

        recyclerView = view.findViewById(R.id.kalliergiesRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(act, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(act, DividerItemDecoration.VERTICAL));
        recyclerView.setItemViewCacheSize(300);
        recyclerView.setHasFixedSize(true);

        imageUpdateButton();
        getEidi();
        getKalliergies();


        MyDialogFragmentMedicineCloseListener listener = new MyDialogFragmentMedicineCloseListener() {
            @Override
            public void dialogMedicineClose(String id_name) {
                eidosTV.setText(id_name);
            }
        };
        return view;
    }


    public void getKalliergies(){

        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.ctx = act;
        task.query = "select * , " +
                " dbo.nameitem(itemID) as itemName, \n" +
                "dbo.datetostr(date) as dateStr, dbo.datetostr(datesent) as dateSendStr from Nursing_kalliergies_meth \n" +
                "where transgroupID = " + transgroupID +
                " and dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + date + "' , 103))";;
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {

                if (results!= null && !results.getJSONObject(0).has("status")){

                    lista.clear();

                    for (int i=0; i<results.length(); ++i){
                        JSONObject  jsonObject = results.getJSONObject(i);
                        int id =  jsonObject.getInt("ID");
                        int itemID =  jsonObject.getInt("itemID");
                        int userID =  jsonObject.getInt("UserID");

                        String itemName = jsonObject.getString("itemName");
                        String date = jsonObject.getString("dateStr");
                        String mikrovio = jsonObject.getString("mikrovio");
                        String dateSendStr = jsonObject.getString("dateSendStr");

                        lista.add(new KalliergiesItems(id,itemID,itemName,date,dateSendStr,mikrovio,userID));
                        
                    }
                    adapter = new KalliergiesRVAdaptor(KalliergiesDialog.this, lista);
                    recyclerView.setAdapter(adapter);
                }
            }
        };

        task.execute();

    }




    private void getEidi() {
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.query = "select id, name from item where departmentid = " + departmentID;
        task.ctx = act;
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {
                if (results != null && !results.getJSONObject(0).has("status")){

                    eidiLista = new ArrayList<>();
                    eidiMap = new HashMap<>();

                    for (int i=0; i<results.length(); i++ ){
                        JSONObject jsonObject = results.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        eidiMap.put(name,id);
                        eidiLista.add(id + "," + name);

                    }

                    eidosTV.setOnClickListener(new SearchEidosListener(act,eidiLista,eidiMap,KalliergiesDialog.this));

                }
                else
                    Toast.makeText(act, "Δεν βρέθηκαν είδη", Toast.LENGTH_SHORT).show();
            }
        };

        task.execute();
    }


    private void imageUpdateButton(){


        updateIMB =new ImageButton(act);
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
                insertOrUpdate();



            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            String editTextString = data.getStringExtra("eidos");
            eidosTV.setText(editTextString);
        }
    }

    private void neaeggrafiBTListener() {
        neaeggrafiBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eidosTV.setText("");
                mikrovioET.setText("");
                id = 0;
            }
        });
    }


    private void insertOrUpdate() {

        StringBuilder query = new StringBuilder();

        if (id == 0) {
            query.append("exec disable_triggers insert Nursing_kalliergies_meth (TransGroupID ,date, UserID , itemID , mikrovio , datesent) " +
                    "values (");
            query.append(transgroupID).append(",");
            query.append(Utils.convertDateTomilliseconds(date) + ",");
            query.append(Utils.getUserID(act) + ",");
            query.append(Utils.getSplitSPartString(eidosTV.getText().toString().trim(),",",0) + ",");
            query.append("'" + mikrovioET.getText().toString() + "',");
            query.append(Utils.convertDateTomilliseconds(datesentET.getText().toString()));
            query.append(")");
        }
        else{
            query.append("exec disable_triggers update Nursing_kalliergies_meth  ");
            query.append("set itemID = " + Utils.getSplitSPartString(eidosTV.getText().toString().trim(),",",0) + ",");
            query.append("mikrovio = '" + mikrovioET.getText().toString() + "', ");
            query.append("datesent = " + Utils.convertDateTomilliseconds(datesentET.getText().toString()));
            query.append(" where id  = " + id);


        }



        AsyncTaskUpdate task = new AsyncTaskUpdate(act, query.toString());
        task.listener = new AsyncGetUpdateResult() {
            @Override
            public void update(String str) {

                getKalliergies();
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

}
