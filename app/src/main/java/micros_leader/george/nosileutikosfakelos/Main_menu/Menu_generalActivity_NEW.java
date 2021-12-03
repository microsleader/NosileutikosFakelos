package micros_leader.george.nosileutikosfakelos.Main_menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.LoginActiity;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.SettingsActivity;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Menu_generalActivity_NEW extends BasicActivity implements View.OnClickListener , AsyncCompleteTask2, AsyncCompleteTask {


    private String name, id,companyID;


    private HashMap<String,String> map;
    private ArrayList<String> floorsArrayList;
    private String pano_titloi[] =new String[]{"ΚΛΙΝΗ","ΗΜ/ΝΙΑ ΕΙΣΑΓΩΓΗΣ", "ΗΜ/ΝΙΑ ΕΙΣΑΓ. ΚΛΙΝΙΚΗΣ ","ΟΝΟΜΑ","ΕΠΩΝΥΜΟ","ΟΝΟΜΑ ΠΑΤΡΟΣ", "ΚΩΔΙΚΟΣ ΑΣΘΕΝΟΥΣ" ,
            "ΑΣΦ. ΦΟΡΕΑΣ 1","ΑΣΦ. ΦΟΡΕΑΣ 2","ΑΣΦ. ΦΟΡΕΑΣ 3","ΑΣΦ. ΦΟΡΕΑΣ 4",
            "ΗΛΙΚΙΑ", "ΚΛΙΝΙΚΗ", "ΔΙΑΓΝΩΣΗ ΕΙΣΟΔΟΥ - \n ΔΙΑΓΝΩΣΗ ΝΟΣΗΛΕΙΑΣ",
            "ΘΕΡΑΠΩΝ ΙΑΤΡΟΣ 1","ΘΕΡΑΠΩΝ ΙΑΤΡΟΣ 2","ΘΕΡΑΠΩΝ ΙΑΤΡΟΣ 3","ΘΕΡΑΠΩΝ ΙΑΤΡΟΣ 4","ΠΑΡΑΤΗΡΗΣΕΙΣ"};

    private String columns[] =new String[]{"bed","datein","dateinF","FirstName","LastName","fatherName", "code",
            "insurance1","insurance2","insurance3","insurance4","patient_age","tg_clinic", "DiagnosisIn",
            "treatment1","treatment2","treatment3","treatment4","remarks",};

    private Button planoKlinonBt, sigxoneusiBT;
    private Toolbar toolbar;
    private int customerID = 0;
    private SharedPreferences sp1 ;
    private SharedPreferences.Editor editor ;
    private LinearLayout filladiaLayout;
    private Spinner tmimaSP;
    private List<String> tmimaLista = new ArrayList<>();
    private RecyclerView menuRV;
    private Menu_general_RV_Adapter adapter;
    private ArrayList<Menu_general_Item> menu_lista;
    private boolean firstTime = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_general_new);
        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");
        companyID = getIntent().getStringExtra("companyID");
        extendedAct = this;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        filladiaLayout = findViewById(R.id.filadiaLayout);
        menu_lista = InfoSpecificLists.getFloorsMenu();
        initializeRV();

        tmimaSP = findViewById(R.id.tmimaSP);
        tmimaLista.add("ΟΡΟΦΟΙ");
        tmimaLista.add("ΜΕΘ");
        tmimaLista.add("ΕΞΩΤΕΡΙΚΑ ΙΑΤΡΕΙΑ");

        final ArrayAdapter adapterSP = new ArrayAdapter(this,R.layout.spinner_layout_for_vardies,tmimaLista);
        adapterSP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tmimaSP.setAdapter(adapterSP);
        tmimaSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (firstTime)
                    firstTime = false;
                else {

                    if (position == 0)
                        adapter.result = InfoSpecificLists.getFloorsMenu();
                    else if (position == 1)
                        adapter.result = InfoSpecificLists.getMethMenu();
                    else if (position == 2)
                        adapter.result = InfoSpecificLists.getEksoterikaIatreiaMenu();


                    adapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        planoKlinonBt = findViewById(R.id.planoKlinonBT);
        sigxoneusiBT = findViewById(R.id.sigxoneusiBT);
        sigxoneusiBT.setOnClickListener(this);


        sp1 =  getSharedPreferences("settings", Activity.MODE_PRIVATE);
        editor= sp1.edit();

        customerID = Utils.getCustomerID(this);
        if (customerID == 0){
            getCustID();
        }

      //  checkFirstTimeLogin();

    }

    private void initializeRV() {

        adapter = new Menu_general_RV_Adapter(menu_lista,this , true);
        setRecyclerViewgridrLayaout( R.id.recyclerView,  adapter, 2,titloi_positions );
    }


    private void checkFirstTimeLogin() {
        //ΕΑΝ ΕΙΝΑΙ Η ΠΡΩΤΗ ΦΟΡΑ ΠΟΥ ΚΑΝΕΙ ΛΟΓΚΙΝ ΘΑ ΚΑΤΕΒΑΣΕΙ ΤΗ ΛΙΣΤΑ ΜΕ ΤΑ ΦΑΡΜΑΚΑ
        boolean x = Utils.getIsFirstTimeLogin(Menu_generalActivity_NEW.this);
        if (x)
            getAllMedicines(true);
        else
            alertDialog.dismiss();

    }


    private void getCustID(){
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.ctx = getApplicationContext();
        task.listener = this;
        task.query = "select * from app.control";
        //  task.execute();
    }


    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null){
            if (!results.getJSONObject(0).has("status")) {
                customerID = results.getJSONObject(0).getInt("custID");
                editor.putInt("customerID" , customerID);
                editor.commit();
            }
        }
    }









    @Override
    public void taskCompleteMedicinesHashMap(HashMap<String, Integer> medicinesHashMap) {
        alertDialog.show();

        String jsonString = new Gson().toJson(medicinesHashMap);
        editor.putString("medicinesMap", jsonString);
        editor.putBoolean("firstTimeLogin", false);
        editor.apply();
        alertDialog.dismiss();

    }


    private void planoKlinonBT_listener(){
        planoKlinonBt.setOnClickListener(v -> methodKlinesDialog());
    }


//-------------------------
    // ΚΑΤΕΒΑΣΜΑ ΤΜΗΜΑΤΩΝ ΚΛΙΝΩΝ ΚΑΙ ΕΜΦΑΝΙΣΗ SPINNER ΣΕ ALERTDIALOG

    private void methodKlinesDialog() {

        alertDialog.show();

        AsyncTaskGetJSON task = new AsyncTaskGetJSON();
        task.ctx = getApplicationContext();
        task.listener = this;
        task.query = new Str_queries().getFloors(Utils.getcompanyID(this));
        task.execute();
    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(Menu_generalActivity_NEW.this, SettingsActivity.class));

                return true;
            case R.id.log_out:

                Utils.logout(Menu_generalActivity_NEW.this);
                startActivity(new Intent(Menu_generalActivity_NEW.this, LoginActiity.class));
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.planoKlinonBT)
            planoKlinonBT_listener();
        else if (v.getId() == R.id.sigxoneusiBT)
            startActivity(new Intent(this, SigxoneusiFiladiwnActivity.class));

    }


    @Override
    public void taskComplete(JSONArray results) throws JSONException {
        if (results != null){
            map = new HashMap();
            map.put("Όλοι οι όροφοι","0");

            floorsArrayList = new ArrayList<String>();
            floorsArrayList.add("Όλοι οι όροφοι");

            for (int i =0; i<results.length(); i++) {

                JSONObject klin = results.getJSONObject(i);
                String id = Utils.convertObjToString(klin.get("id"));
                String name = Utils.convertObjToString(klin.get("name"));

                map.put(name,id);
                floorsArrayList.add(name);


            }




            AlertDialog.Builder b = new AlertDialog.Builder(this);

            // LAYOUT
            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setGravity(Gravity.CENTER);
            layout.setLayoutParams(parms);
            layout.setPadding(2, 2, 2, 2);

            final TextView dateTV = new TextView(this);
            dateTV.setBackgroundResource(R.drawable.edittext_shape);
            dateTV.setHint("Επιλογή ημέρας");
            dateTV.setPadding(8, 8, 8, 8);
            dateTV.setGravity(Gravity.CENTER);
            dateTV.setTextSize(18);

            Utils.dateListener(this, dateTV);


            final TextView hourTV = new TextView(this);
            hourTV.setBackgroundResource(R.drawable.edittext_shape);
            hourTV.setHint("Επιλογή Ώρας");
            hourTV.setPadding(8, 8, 8, 8);

            hourTV.setGravity(Gravity.CENTER);
            hourTV.setTextSize(18);

            Utils.timeListener(this, hourTV);


            LinearLayout.LayoutParams tv1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tv1Params.topMargin = 22;
            tv1Params.rightMargin = 22;

            tv1Params.bottomMargin = 32;
            layout.addView(dateTV,tv1Params);
            layout.addView(hourTV, tv1Params);

            b.setView(layout);


            b.setTitle("Όροφοι");
            final String[] floors = Utils.getArraylistToStringArray(floorsArrayList);

            b.setItems(floors, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {



                    dialog.dismiss();
                    String id = map.get(floors[which]);

                    String date = dateTV.getText().toString();
                    String hour = hourTV.getText().toString();


                    startActivity(  tableView_sigkentrotika(Str_queries.setglobals(userID,"2",companyID) + "\n" + Str_queries.getNosileuomenousFloors(date,hour,
                            id,Utils.getcompanyID(Menu_generalActivity_NEW.this)),
                            transgroupID,
                            pano_titloi,
                            null,
                            columns,
                            false,
                            false,
                            false));
                }

            });

            b.show();

        }

        alertDialog.hide();
    }

    @Override
    public void taskCompleteGetVardies(JSONArray results) throws JSONException {


    }
}
