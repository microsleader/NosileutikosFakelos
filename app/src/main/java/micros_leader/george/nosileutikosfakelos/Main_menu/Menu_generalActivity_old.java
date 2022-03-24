package micros_leader.george.nosileutikosfakelos.Main_menu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.LoginActiity;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Isozigio_Meth.Isozigio_Meth_Activity;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.Kathetires_Activity;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Nosil_Elegxos.Nosil_elegxos_Meth;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Zotika_simeia.Zotika_Activity_Meth;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Aksiologiseis.NeurikiAksiologisi3Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Diagnoseis_Istoriko.Diagnoseis_Istoriko_Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Diaitologio.DiaitologioActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Eksitirio.EksitirioActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_EktimisiEpigonton.EktimisiEpigontonActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Isozigio_pros_apov.Isozigio_p_a_Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Kathimerino_zigisma.Kathimerino_Zigisma_Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_istoriko.Nosil_IstorikoActivity_NEW;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Snel.SnelActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Zotika_simeia.Zotika_simeia_Activity;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon.FarmakaListActivity;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.SettingsActivity;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Menu_generalActivity_old extends BasicActivity implements View.OnClickListener , AsyncCompleteTask2   , AsyncCompleteTask {

    private String name, id,companyID;
    private LinearLayout layoutButton1,layoutButton2,layoutButton3,
            layoutButton4,layoutButton5,layoutButton6,layoutButton7,layoutButton8,layoutButton9,layoutButton10,
            layoutButton11,layoutButton12,layoutButton13,layoutButton14,layoutButton15,
            layoutButton16,layoutButton17,layoutButton18,layoutButton19,layoutButton20;

    private HashMap <String,String> map;
    private ArrayList <String> floorsArrayList;
    private String pano_titloi[] =new String[]{"ΚΛΙΝΗ","ΗΜ/ΝΙΑ ΕΙΣΑΓΩΓΗΣ", "ΗΜ/ΝΙΑ ΕΙΣΑΓ. ΚΛΙΝΙΚΗΣ ","ΟΝΟΜΑ","ΕΠΩΝΥΜΟ","ΟΝΟΜΑ ΠΑΤΡΟΣ", "ΚΩΔΙΚΟΣ ΑΣΘΕΝΟΥΣ" ,
            "ΑΣΦ. ΦΟΡΕΑΣ 1","ΑΣΦ. ΦΟΡΕΑΣ 2","ΑΣΦ. ΦΟΡΕΑΣ 3","ΑΣΦ. ΦΟΡΕΑΣ 4",
            "ΗΛΙΚΙΑ", "ΚΛΙΝΙΚΗ", "ΔΙΑΓΝΩΣΗ ΕΙΣΟΔΟΥ - \n ΔΙΑΓΝΩΣΗ ΝΟΣΗΛΕΙΑΣ",
            "ΘΕΡΑΠΩΝ ΙΑΤΡΟΣ 1","ΘΕΡΑΠΩΝ ΙΑΤΡΟΣ 2","ΘΕΡΑΠΩΝ ΙΑΤΡΟΣ 3","ΘΕΡΑΠΩΝ ΙΑΤΡΟΣ 4","ΠΑΡΑΤΗΡΗΣΕΙΣ"};

    private String columns[] =new String[]{"bed","datein","dateinF","FirstName","LastName","fatherName", "code",
            "insurance1","insurance2","insurance3","insurance4","patient_age","tg_clinic", "DiagnosisIn",
            "treatment1","treatment2","treatment3","treatment4","remarks",};

    private Button planoKlinonBt , sigxoneusiBT;
    private Toolbar toolbar;
    private int customerID = 0;
    private SharedPreferences sp1 ;
    private SharedPreferences.Editor editor ;
    private LinearLayout filladiaLayout;
    private Spinner tmimaSP;
    private List<String> tmimaLista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_general);

        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");
        companyID = getIntent().getStringExtra("companyID");
        extendedAct = this;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        filladiaLayout = findViewById(R.id.filadiaLayout);

        tmimaSP = findViewById(R.id.tmimaSP);
        tmimaLista.add("ΟΡΟΦΟΙ");
        tmimaLista.add("ΜΕΘ");

        ArrayAdapter adapterSP = new ArrayAdapter(this,R.layout.spinner_layout_for_vardies,tmimaLista);
        adapterSP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tmimaSP.setAdapter(adapterSP);
        tmimaSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                filladiaLayout.removeAllViews();
                if (position == 0) {

                    getLayoutInflater().inflate(R.layout.menu_general_orofoi, filladiaLayout);

                    initializeLayoutsOrofoi();
                }
                else if (position == 1) {

                    getLayoutInflater().inflate(R.layout.menu_general_meth, filladiaLayout);
                    initializeLayoutsMeth();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        planoKlinonBt = findViewById(R.id.planoKlinonBT);


        planoKlinonBT_listener();

        sp1 =  getSharedPreferences("settings", Activity.MODE_PRIVATE);
        editor= sp1.edit();

        customerID = Utils.getCustomerID(this);
        if (customerID == 0){
            getCustID();
        }

        checkFirstTimeLogin();



    }



    private void initializeLayoutsOrofoi(){

        layoutButton1 = findViewById(R.id.layoutButton1);
        layoutButton1.setOnClickListener(this);
        layoutButton2 = findViewById(R.id.layoutButton2);
        layoutButton2.setOnClickListener(this);
        layoutButton3 = findViewById(R.id.layoutButton3);
        layoutButton3.setOnClickListener(this);
        layoutButton4 = findViewById(R.id.layoutButton4);
        layoutButton4.setOnClickListener(this);
        layoutButton5 = findViewById(R.id.layoutButton5);
        layoutButton5.setOnClickListener(this);
        layoutButton6 = findViewById(R.id.layoutButton6);
        layoutButton6.setOnClickListener(this);
        layoutButton7 = findViewById(R.id.layoutButton7);
        layoutButton7.setOnClickListener(this);
        layoutButton8 = findViewById(R.id.layoutButton8);
        layoutButton8.setOnClickListener(this);
        layoutButton9 = findViewById(R.id.layoutButton9);
        layoutButton9.setOnClickListener(this);
        layoutButton10 = findViewById(R.id.layoutButton10);
        layoutButton10.setOnClickListener(this);
        layoutButton11 = findViewById(R.id.layoutButton11);
        layoutButton11.setOnClickListener(this);
        layoutButton12 = findViewById(R.id.layoutButton12);
        layoutButton12.setOnClickListener(this);
        layoutButton13 = findViewById(R.id.layoutButton13);
        layoutButton13.setOnClickListener(this);
        layoutButton14 = findViewById(R.id.layoutButton14);
        layoutButton14.setOnClickListener(this);
        layoutButton15 = findViewById(R.id.layoutButton15);
        layoutButton15.setOnClickListener(this);
        layoutButton16 = findViewById(R.id.layoutButton16);
        layoutButton16.setOnClickListener(this);
        layoutButton17 = findViewById(R.id.layoutButton17);
        layoutButton17.setOnClickListener(this);
        layoutButton18 = findViewById(R.id.layoutButton18);
        layoutButton18.setOnClickListener(this);
        layoutButton19 = findViewById(R.id.layoutButton19);
        layoutButton19.setOnClickListener(this);
        layoutButton20 = findViewById(R.id.layoutButton20);
        layoutButton20.setOnClickListener(this);
    }


    private void initializeLayoutsMeth() {
        layoutButton1 = findViewById(R.id.Nosil_Elegxos);
        layoutButton2 = findViewById(R.id.zotika_meth);
        layoutButton3 = findViewById(R.id.istorikoMeth);
        layoutButton4 = findViewById(R.id.kath_kal_meth);
        layoutButton5 = findViewById(R.id.isozigioMeth);


        layoutButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu_generalActivity_old.this, Nosil_elegxos_Meth.class);
                intent.putExtra("companyID",companyID);
                startActivity(intent);
            }
        });

        layoutButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu_generalActivity_old.this, Zotika_Activity_Meth.class);
                intent.putExtra("companyID",companyID);
                startActivity(intent);
            }
        });

        layoutButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu_generalActivity_old.this, Nosil_IstorikoActivity_NEW.class);
                intent.putExtra("companyID",companyID);
                startActivity(intent);
            }
        });


        layoutButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu_generalActivity_old.this, Kathetires_Activity.class);
                intent.putExtra("companyID",companyID);
                startActivity(intent);
            }
        });

        layoutButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu_generalActivity_old.this, Isozigio_Meth_Activity.class);
                intent.putExtra("companyID",companyID);
                startActivity(intent);


            }
        });








    }


        private void checkFirstTimeLogin() {
        //ΕΑΝ ΕΙΝΑΙ Η ΠΡΩΤΗ ΦΟΡΑ ΠΟΥ ΚΑΝΕΙ ΛΟΓΚΙΝ ΘΑ ΚΑΤΕΒΑΣΕΙ ΤΗ ΛΙΣΤΑ ΜΕ ΤΑ ΦΑΡΜΑΚΑ
        boolean x = Utils.getIsFirstTimeLogin(Menu_generalActivity_old.this);
        if (x)
            getAllMedicines(true);
        else
            alertDialog.dismiss();




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
               startActivity(new Intent(Menu_generalActivity_old.this, SettingsActivity.class));

                return true;
            case R.id.log_out:

                Utils.logout(Menu_generalActivity_old.this);
                startActivity(new Intent(Menu_generalActivity_old.this, LoginActiity.class));
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {

            case R.id.layoutButton1:
                // do your code
                startActivity(new Intent(Menu_generalActivity_old.this, Isozigio_p_a_Activity.class));
                break;

            case R.id.layoutButton2:
                // do your code

                intent = new Intent(Menu_generalActivity_old.this, MainActivity_Aim.class);
                intent.putExtra("companyID",companyID);
                startActivity(intent);
                break;

            case R.id.layoutButton3:
                // do your code

                intent = new Intent(Menu_generalActivity_old.this, NeurikiAksiologisi3Activity.class);
                intent.putExtra("companyID",companyID);
                startActivity(intent);
                break;

            case R.id.layoutButton4:
                // do your code
               // intent = new Intent(Menu_generalActivity.this, LogodosiaActivity.class);
             //   startActivity(intent);
                break;

            case R.id.layoutButton5:
                // do your code
                startActivity(new Intent(Menu_generalActivity_old.this, Diagnoseis_Istoriko_Activity.class));

                break;

            case R.id.layoutButton6:
                // do your code
                startActivity(new Intent(Menu_generalActivity_old.this, DiaitologioActivity.class));
                break;


            case R.id.layoutButton7:
                // do your code
                startActivity(new Intent(Menu_generalActivity_old.this, EktimisiEpigontonActivity.class));

                break;

            case R.id.layoutButton8:
                // do your code
                startActivity(new Intent(Menu_generalActivity_old.this, EksitirioActivity.class));

                break;


            case R.id.layoutButton9:
                // do your code
                startActivity(new Intent(Menu_generalActivity_old.this, Nosil_IstorikoActivity_NEW.class));

                break;


            case R.id.layoutButton10:
                // do your code
                startActivity(new Intent(Menu_generalActivity_old.this, SnelActivity.class));

                break;

            case R.id.layoutButton11:
                // do your code
                startActivity(new Intent(Menu_generalActivity_old.this, FarmakaListActivity.class));

                break;


            case R.id.layoutButton12:
                // do your code
                startActivity(new Intent(Menu_generalActivity_old.this, Zotika_simeia_Activity.class));


                break;

            case R.id.layoutButton13:
                // do your code
                startActivity(new Intent(Menu_generalActivity_old.this, Kathimerino_Zigisma_Activity.class));

                break;

            case R.id.layoutButton14:
                // do your code
              //  startActivity(new Intent(Menu_generalActivity.this, MainActivity_Aim.class));

                break;

            case R.id.layoutButton15:
                // do your code

               // startActivity(new Intent(Menu_generalActivity.this, PlanoKlinonActivity.class));

                break;

            case R.id.layoutButton16:
                // do your code
          //      startActivity(new Intent(Menu_generalActivity.this, Zotika_simeia_Activity.class));

                break;

            case R.id.layoutButton17:
                // do your code
           //     startActivity(new Intent(Menu_generalActivity.this, Zotika_simeia_Activity.class));

                break;

            case R.id.layoutButton18:
                // do your code
           //     startActivity(new Intent(Menu_generalActivity.this, Zotika_simeia_Activity.class));

                break;

            case R.id.layoutButton19:
                // do your code
             //   startActivity(new Intent(Menu_generalActivity.this, Zotika_simeia_Activity.class));

                break;

            case R.id.layoutButton20:
                // do your code
            //    startActivity(new Intent(Menu_generalActivity.this, Zotika_simeia_Activity.class));

                break;


            default:
                break;
        }
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void taskCompleteMedicinesLista(ArrayList medicinesLista) {

        if (medicinesLista != null) {

            alertDialog.show();
            Set<String> medLista = new HashSet<String>(medicinesLista);
            editor.putStringSet("medicinesArrayList", medLista);
            editor.putBoolean("firstTimeLogin", false);
            editor.commit();
            alertDialog.dismiss();
        }

    }

    @Override
    public void taskCompleteMedicinesHashMap(HashMap<String, Integer> medicinesHashMap) {
        alertDialog.show();

        String jsonString = new Gson().toJson(medicinesHashMap);
        editor.putString("medicinesMap", jsonString);
        editor.apply();
        alertDialog.dismiss();

    }


    private void planoKlinonBT_listener(){
        planoKlinonBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                methodKlinesDialog();
            }
        });
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


                    startActivity(  tableView_sigkentrotika(Str_queries.getNosileuomenousFloors(date,hour,
                            id,Utils.getcompanyID(Menu_generalActivity_old.this)),
                            transgroupID,
                            pano_titloi,
                            null,
                            columns,
                            false
                    ,       false,
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
