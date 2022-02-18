package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.github.ag.floatingactionmenu.OptionsFabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Vardies;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.DialogFragmentSearches.DF_SendDoctorOrders;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataListener;
import micros_leader.george.nosileutikosfakelos.LoginActiity;
import micros_leader.george.nosileutikosfakelos.Notifications.DF_Notifications;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.fragments.Iatrikes_odigies_fragment;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.fragments.StatheresFragment;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.fragments.SinexeisFragment;

import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors.DIalogFragmentXreosimaIlika;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors.DialogFragmentSearchPat;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.StaticFields;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.customers.Frontis;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityMainAim3Binding;


public class MainActivity_Aim extends BasicActivity implements   AsyncCompleteTask, AsyncGetUpdateResult, DataListener {

    public String name ,companyID ,vardiaID;
    private final ArrayList<String> namesOfPatients =  new ArrayList<>();
    private final ArrayList<Vardies> namesOfVardies =  new ArrayList<>();
    public  Map<String,String> getTransgroupIDUsingCode , getPatientIDUsingCode;
    private final ArrayList <PatientsOfTheDay> patList =  new ArrayList<>();
    private Iatrikes_odigies_fragment iatr_fragment;
    public boolean isLoaded = true , firstTime = true ,isNurse,isDoctor;
    public ActivityMainAim3Binding bd;
    private         TimerTask task , not_timerTask;
    private         OptionsFabLayout fabMain ;
    private boolean timerstate = false;
    private boolean notificationState = true;
    private boolean isIconPressed;
    public int id_not;
    public int custID ;
    public HashMap<String ,String> docNurseValues ;

    private BottomNavigationView navigationView;

    public final Fragment medFragment = new Iatrikes_odigies_fragment();
    final Fragment statFragemnt =  new StatheresFragment();
    final Fragment sinexFragment = new SinexeisFragment();

    Iatrikes_odigies_fragment x = (Iatrikes_odigies_fragment) medFragment;

    StatheresFragment y = (StatheresFragment) statFragemnt;

    SinexeisFragment z = (SinexeisFragment) sinexFragment;

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = medFragment;



    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.updateButton:

                    if (transgroupID == null || transgroupID.isEmpty())
                        Toast.makeText(MainActivity_Aim.this, "Δεν υπάρχει περιστατικό", Toast.LENGTH_SHORT).show();


                    else if (active == statFragemnt) {
                        StatheresFragment fr = (StatheresFragment) statFragemnt;
                        fr.update();
                    }
                    else if (active == sinexFragment){
                        SinexeisFragment fr = (SinexeisFragment) sinexFragment;
                        fr.update();
                    }
                    else if (active == medFragment && isDoctor && Customers.isFrontis(custID)) {
                            Iatrikes_odigies_fragment fr = (Iatrikes_odigies_fragment) medFragment;
                            fr.update();
                    }

                    else if (active == medFragment)
                        Toast.makeText(MainActivity_Aim.this, "Δεν δίνεται η δυνατότητα αποθήκευσης στις ιατρικές οδηγίες", Toast.LENGTH_SHORT).show();


                    return false;


                case R.id.med_instr_fr:
                    fm.beginTransaction().hide(active).show(medFragment).commitAllowingStateLoss();
                    active = medFragment;

                    return true;

                case R.id.statheres_fr:
                    fm.beginTransaction().hide(active).show(statFragemnt).commitAllowingStateLoss();
                    active = statFragemnt;
                    return true;

                case R.id.sinexeis_fr:
                    fm.beginTransaction().hide(active).show(sinexFragment).commitAllowingStateLoss();
                    active = sinexFragment;

                    return true;



            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityMainAim3Binding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);

        extendedAct = this;
        alertDialog = Utils.setLoadingAlertDialog(MainActivity_Aim.this);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        //-------------------------

        navigationView = findViewById(R.id.navigationView);
        navigationView.setSelectedItemId(R.id.med_instr_fr);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        companyID = Utils.getcompanyID(this);
        getTransgroupIDUsingCode = new HashMap<>();
        getPatientIDUsingCode = new HashMap<>();
        custID = Utils.getCustomerID(this);
        isNurse = Utils.getIsNurse(this);
        isDoctor = Utils.getIsDoctor(this);

        managefabMenuIcon();

        bd.vardiesSP.setPopupBackgroundResource(R.drawable.custom_spinner_list);

        log_out_iv();
        buttonRefreshPatientsListener();

        newNotificationListener();
        search_TV_listener();

        getVardies();

        changeColorOfTV();
        if (isNurse && Customers.isFrontis(custID))
            check_notifications_thread();

        alertDialog.dismiss();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.nosil_aim_new, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        //Closes menu if its opened.
        if (fabMain.isOptionsMenuOpened())
            fabMain.closeOptionsMenu();
        else
            super.onBackPressed();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.startWeightTV) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void log_out_iv() {
        if (custID == Customers.CUSTID_FRONTIS  || custID == Customers.CUSTID_FRONTIS_2){
            bd.logOutIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.logout(MainActivity_Aim.this);
                    startActivity(new Intent(MainActivity_Aim.this, LoginActiity.class));
                    finish();
                }
            });

        }
        else
            bd.logOutIv.setVisibility(View.GONE);
    }



    private void newNotificationListener(){


        bd.newNotificationsTV.setOnClickListener(view -> {
            isIconPressed = false;
            if (id_not == DF_Notifications.NOTIFICATIONS_BOTH_CATEGORIES){

                transgroupID = getTransgroupID();
                setNotificationDialog(transgroupID,getPatientID());
            }

            else if (id_not == DF_Notifications.NOTIFICATIONS_TO_CONFIRM)
                notificationsToConfirm(DF_Notifications.NOTIFICATIONS_TO_CONFIRM,null);
            else if (id_not == DF_Notifications.MEDICAL_INS_NOTIFICATIONS)
                notificationsToConfirm(DF_Notifications.MEDICAL_INS_NOTIFICATIONS,null);
        });
    }



    private void managefabMenuIcon() {
        if (Customers.isFrontis(custID)){
            bd.fabMenu.setVisibility(View.GONE);
            bd.fabMenuFrontis.setVisibility(View.VISIBLE);
            fabMain = bd.fabMenuFrontis;
        }
        else{
            bd.fabMenu.setVisibility(View.VISIBLE);
            bd.fabMenuFrontis.setVisibility(View.GONE);
            fabMain = bd.fabMenu;

        }


//Set main fab clicklistener.
        fabMain.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabMain.isOptionsMenuOpened())
                    fabMain.closeOptionsMenu();
            }
        });


        if (Customers.isFrontis(custID)){
            fabMain.setMiniFabsColors(
                    R.color.green,
                    R.color.yellow,
                    R.color.colorPrimary,
                    R.color.blue,
                    R.color.blue,
                    R.color.colorPrimary
            );
        }
        else {

            fabMain.setMiniFabsColors(
                    R.color.green,
                    R.color.yellow,
                    R.color.colorPrimary,
                    R.color.grey,
                    R.color.blue,
                    R.color.blue,
                    R.color.colorPrimary
            );
        }


        //  LISTENER  GIA TA ITEMS TOY
        fabMain.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {

                Intent intent;
                String code = Utils.getfirstPartSplitCommaString(bd.patientsTV.getText().toString());
                String patientID = getPatientIDUsingCode.get(code);
                transgroupID = getTransgroupIDUsingCode.get(code);

                switch (fabItem.getItemId()) {

                    case R.id.xreosima_ilika:

                        DIalogFragmentXreosimaIlika df =  new DIalogFragmentXreosimaIlika();
                        Bundle putextra = new Bundle();
                        putextra.putString("transgroupID", transgroupID);
                        df.setArguments(putextra);
                        df.show(getSupportFragmentManager() , "Dialog");
                        fabMain.closeOptionsMenu();

                        break; // true to keep the Speed Dial open


                    case R.id.far_agogi:


//                        intent = new Intent(MainActivity_Aim.this, Sigkentrotika_Farm_agogis.class);
//                        intent.putExtra("patientID",patientID);
//                        intent.putExtra("transgroupID",transgroupID);
//                        startActivity(intent);

                        AlertDialog ad = new AlertDialog.Builder(MainActivity_Aim.this).create(); //Read Update
                        ad.setTitle("Επιλέξτε φαρμ. αγωγή");

                        ad.setMessage("");
                        ad.setButton( Dialog.BUTTON_POSITIVE, "Μόνιμη φαρμακευτική αγωγή", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                               Intent intent  =    tableView_sigkentrotika( Str_queries.getFarm_agogi(custID,patientID , true),
                                        null,  //ΤΟ ΚΑΝΩ NULL ΕΠΕΙΔΗ Ο ΠΙΑΝΑΚΣ ΠΟΥ ΘΑ ΚΑΤΑΧΩΡΩ ΔΕΝ ΕΧΕΙ TRANSGROUPID ΑΛΛΑ ΕΧΕΙ patientID
                                        null,
                                        Customers.isFrontis(custID) ? Frontis.getFarm_agogi_lista(false ,true) : InfoSpecificLists.getFarm_agogi_lista(true),
                                        false,
                                        false,
                                        isDoctor );
                                intent.putExtra("toolbar_title","Μόνιμη φαρμακευτική αγωγή");
                                if (isDoctor)
                                    intent.putExtra("patientID",patientID);

                                startActivity(intent);

                            }});

                        ad.setButton( Dialog.BUTTON_NEGATIVE, "Προσωρινή φαρμακευτική αγωγή", new DialogInterface.OnClickListener()    {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent in  =    tableView_sigkentrotika( Str_queries.getFarm_agogi(custID,patientID , false),
                                            null,  //ΤΟ ΚΑΝΩ NULL ΕΠΕΙΔΗ Ο ΠΙΑΝΑΚΣ ΠΟΥ ΘΑ ΚΑΤΑΧΩΡΩ ΔΕΝ ΕΧΕΙ TRANSGROUPID ΑΛΛΑ ΕΧΕΙ patientID

                                            null,
                                            Customers.isFrontis(custID) ?  Frontis.getFarm_agogi_lista(true,false) : InfoSpecificLists.getFarm_agogi_lista(false),
                                            false,
                                            false,
                                            true);


                                    in.putExtra("toolbar_title","Φαρμακευτική αγωγή συνεδρίας");
                                    in.putExtra("patientID",patientID);
                                    startActivity(in);

                                }});

                                ad.show();







                        break; // true to keep the Speed Dial open



/*
                    case R.id.oles_oi_statheres_metriseis:

                        intent =  new Intent(MainActivity_Aim.this, StatheresMetriseis_All_Activity.class);
                        intent.putExtra("transgroupID",getTransgroupIDUsingCode.get(code));
                        intent.putExtra("patientID",patientID);
                        startActivity(intent);
                        fabMain.closeOptionsMenu();


                        break; // true to keep the Speed Dial open


                    case R.id.oles_oi_sinexeis_metriseis:

                        intent =  new Intent(MainActivity_Aim.this, SinexeisMetriseis_All_Activity.class);
                        intent.putExtra("transgroupID",getTransgroupIDUsingCode.get(code));
                        intent.putExtra("patientID",patientID);
                        startActivity(intent);
                        fabMain.closeOptionsMenu();

                        break; // true to keep the Speed Dial open


                    case R.id.sigkentrotika_sinexeis_metriseis:

                        transgroupID = getTransgroupIDUsingCode.get(code);


                       intent =   tableView_sigkentrotika( Str_queries.getSigkentrotika_sinexwn_metrisewn(patientID),
                                transgroupID,
                                new String[]{"ID","ΗΜ/ΝΙΑ / ΩΡΑ","Συστολική πίεση (Hg)","Διαστολική πίεση (mm)",
                                        "Σφύξεις:","Θερμοκρασία (°C)","Ρυθμός ροή αντλίας (ml/min)","Πίεση φλεβ. γραμμής",
                                        "Πίεση αρτ. γραμμής:","Ρυθμός υπερδιηθησης \n ανά ώρα(U.F.) (l/h)","Αγωγιμότητα (ms/cm)","HCO 3 (ms/cm)",
                                        "Μετρήσεις-Παραμβάσεις","Παρατηρήσεις","Εγχύσεις - Φάρμακα","Vit - B",
                                        "L - carnittine","Alphacalcidol","EPO","Epoetin alpha",
                                        "Epoetin zeta","Darbepoetin","Paricalcitol","Σίδηρος"},
                                null,
                                InfoSpecificLists.getSigkentrotikaSinexeisMetriseis_MEDIT(),
                                false,
                                false,
                                true);

                       intent.putExtra("setOnlyFirstRowAvalaible",true);
                       intent.putExtra("hasValuesForCH",true);
                       startActivity(intent);

                        break;



                    case R.id.sigkentrotika_all_metrisewn_read_only:

                        intent = new Intent(MainActivity_Aim.this, Sigkentrotika_metrisewn_2_fragments.class);
                        intent.putExtra("patientID",patientID);
                        intent.putExtra("transgroupID",transgroupID);
                        intent.putExtra("is_onlyView",true);
                        startActivity(intent);

                        break;
*/


                    case R.id.sigkentrotika_statherwn:

                        if (!Customers.isFrontis(custID)) {


                            AsyncTaskGetJSON2 t = new AsyncTaskGetJSON2();
                            t.ctx = MainActivity_Aim.this;
                            t.query =
                                    "select  top 3 \n" +
                                            "dbo.datetostr(tg.datein) + ' , ' +  dbo.timetostr(tg.datein) as datestr \n" +
                                            "from Nursing_Hemodialysis_initial2_MEDIT n \n" +
                                            " join transgroup tg on tg.id = n.TransGroupID\n" +
                                            " left join nursing_medical_instructions ins on  n.patientID = ins.patientID and N.Med_instr_ID = INS.ID\n" +
                                            " where n.transgroupID = " + transgroupID +
                                            "  group by  tg.Datein, n.id \n" +
                                            " order by tg.Datein desc, n.id desc";


                            t.listener = new AsyncCompleteTask2() {
                                @Override
                                public void taskComplete2(JSONArray results) throws JSONException {
                                    ArrayList <TableViewItem> lista = InfoSpecificLists.getSigkentrotikaStatheresMetriseis_MEDIT();
                                    String[] panoTitloi = new String[]{};
                                    String[] plagioiTitloi = new String[lista.size()];

                                    if (results != null && !results.getJSONObject(0).has("status")){
                                        panoTitloi = new String[results.length()];


                                        for (int i=0; i<results.length(); i++){
                                            panoTitloi[i] = results.getJSONObject(i).getString("datestr");
                                        }

                                        for (int i=0; i<lista.size(); i++){
                                            plagioiTitloi[i] = lista.get(i).getTitle();
                                        }


                                    }


                                    Intent in  =   tableView_sigkentrotika( Str_queries.getSigkentrotika_statherwn_metrisewn(patientID,transgroupID,custID),
                                            transgroupID,
                                            panoTitloi ,
                                            plagioiTitloi,
                                            lista,
                                            false,
                                            false,
                                            isNurse);

                                    in.putExtra("toolbar_title","Συγκεντρωτικά σταθερών μετρήσεων");
                                    in.putExtra("setOnlyFirstRowAvalaible", isNurse);
                                    startActivity(in);


                                }

                            };

                            t.execute();




                        }




                        break;


                    case R.id.sigkentrotika_sinexwn:



                        if (!Customers.isFrontis(custID)) {


                            AsyncTaskGetJSON2 t = new AsyncTaskGetJSON2();
                            t.ctx = MainActivity_Aim.this;
                            t.query =
                                    "select  top 8 \n" +
                                            "dbo.datetostr(date) + ' , ' +  dbo.timetostr(date) as datestr \n" +
                                            "from Nursing_Hemodialysis_2_MEDIT  ni  \n" +
                                            "left join ( \n" +
                                            "select   \n" +
                                            "top 1 \n" +
                                            "patientid \n" +

                                            "from Nursing_Medical_Instructions  \n" +
                                            "where patientid =  " + patientID + "\n" +
                                            "order by id desc \n" +
                                            ")  m  on m.PatientID = ni.PatientID \n" +
                                            "where  ni.PatientID =  " + patientID +
                                            "\n" +
                                            "order by id desc ";


                            t.listener = new AsyncCompleteTask2() {
                                @Override
                                public void taskComplete2(JSONArray results) throws JSONException {
                                    ArrayList <TableViewItem> lista = InfoSpecificLists.getSigkentrotikaSinexeisMetriseis_MEDIT();
                                    String[] panoTitloi = new String[]{};
                                    String[] plagioiTitloi = new String[lista.size()];

                                    if (results != null && !results.getJSONObject(0).has("status")){
                                        panoTitloi = new String[results.length()];


                                        for (int i=0; i<results.length(); i++){
                                            panoTitloi[i] = results.getJSONObject(i).getString("datestr");
                                        }

                                        for (int i=0; i<lista.size(); i++){
                                            plagioiTitloi[i] = lista.get(i).getTitle();
                                        }


                                    }

                                    Intent in = tableView_sigkentrotika(Str_queries.getSigkentrotika_sinexwn_metrisewn(patientID),
                                            transgroupID,
                                            panoTitloi,
                                            plagioiTitloi,
                                            lista,
                                            false, false, isNurse);
                                    in.putExtra("toolbar_title", "Συγκεντρωτικά συνεχών μετρήσεων");
                                    in.putExtra("setOnlyFirstRowAvalaible", isNurse);
                                    in.putExtra("hasValuesForCH", true);

                                    startActivity(in);

                                }

                            };

                            t.execute();




                        }


                        break;



                    case R.id.sigkentrotika_all_metrisewn:


                        intent = new Intent(MainActivity_Aim.this, Sigkentrotika_metrisewn_2_fragments.class);
                        intent.putExtra("patientID",patientID);
                        intent.putExtra("transgroupID",transgroupID);
                        startActivity(intent);

                        break;


                    case R.id.sigkentrotika_medical_instructions:


                        getSigkentrotikaMedicalInstructions();

                        break;



                    case R.id.notifications:

                        isIconPressed = true;
                        if (patientID == null) {
                            Toast.makeText(MainActivity_Aim.this, "Δεν υπάρχει επιλεγμένος ασθενής", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (isDoctor){

                            String patientIName = Utils.getSplitSPartString(bd.patientsTV.getText().toString(),",",1);
                            DF_SendDoctorOrders dfs = new DF_SendDoctorOrders();
                            Bundle extra = new Bundle();
                            extra.putString("patientID",patientID);
                            extra.putString("patientName",patientIName);
                            dfs.setArguments(extra);
                            dfs.show(getSupportFragmentManager() , "Dialog");
                        }
                        else {
                            setNotificationDialog(transgroupID, patientID);
                        }

                        break;


                    case R.id.prepairPatients:
                        startActivity(new Intent(MainActivity_Aim.this, PrepairPatientActivity.class));

                        break;

                    default:

                        break; // true to keep the Speed Dial open


                }
            }
        });
    }




    private void getSigkentrotikaMedicalInstructions() {

        //alertDialog.show();
        final String patientID = getPatientID();
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.query = "select y.Name as yearName , m.Name as monthName \n" +
                "from Nursing_Medical_Instructions  n \n" +
                "left join years y on y.ID = n.Year \n " +
                "left join v_Month m on m.ID = n.month \n" +
                "left join Nursing_duration_aim dur on dur.ID = n.duration_aim_id \n" +
                "where patientid = " + patientID +
                " order by n.year desc , n.Month desc , n.id desc" ;
        task.ctx = this;
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {


                if (results != null && !results.getJSONObject(0).has("status")) {


                    String []  panoTitloi =new String[results.length()];

                  //  ArrayList<TableViewItem> tableLista = InfoSpecificLists.getSigkentrotika_MedicalInst_lista();


                    for (int i=0; i<results.length(); i++){

                        JSONObject jsonObject = results.getJSONObject(i);
                        String yearName = jsonObject.getString("yearName");
                        String name = jsonObject.getString("monthName");
                        if (name.length() >= 2)
                            name = name.substring(2);

                        panoTitloi[i] = yearName + "," + name;


                    }

                    String [] colsMedit = new String[]{"doctorName","eidos" ,
                            "sixnotita_aim" ,"durationName",
                            "fil",

                            "agogName" , "agogiDosiName"  ,
                            "max_uf","ksiro_varos",

                            "roi_aima" ,"roi_dialima" ,
                            "dial","agogimotita" ,

                            "Dittanthrakika" ,"temparture",

                            "epoName" ,"vitB_Name" ,
                            "carnitine_Name", "vitD_Name",
                            "fe_Name", "etel_Name",

                            "genikes_odigies",
                            "emvolio_ip_b" ,"emvolio_antigrip",
                            "emvolio_pneum_str" ,"embolio_covid1_str",

                    };


                    String []  plagioiTitloiMedit = new String[]{"Ιατρός","Είδος Αιμ." ,
                            "Συχνότητα αιμοκ.\nανά εβδομάδα" ,"Διάρκεια",
                            "Φίλτρο",

                            "Αντιπηκτική αγωγή" ,"Αρχική δόση\nαντιπ. αγωγ."  ,
                            "Μέγιστος Ρυθμός\n Αφυδάτωσης", "Ξηρό βάρος",

                            "Ροή αίματος" ,"Ροή διαλύματος" ,
                            "Διάλυμα - είδος",  "Νάτριο"  ,

                            "Διττανθρακικά (meq/l)" ,"Θερμοκρασία",

                            "Epoetin" ,"Vit-B" ,
                            "L-carnitine", "Vit-D",
                            "Fe", "Etelalcetide",

                            "Γενικές οδηγίες",
                            "Εμβόλιο ηπατίτιδας Β" ,"Αντιγριπικό εμβόλιο",
                            "Εμβόλιο πνευμονιόκοκκου" ,
                            "Εμβόλιο COVID 19"

                    };




                    String [] colsFrontis = new String[]{"doctorName","eidos" ,
                             "ipok_ogkos" ,
                            "sixnotita_aim" ,"durationName",
                            "fil",  "lot_filter" ,

                            "agogName" ,
                            "roi_aima" ,"roi_dialima" ,
                            "dial","agogimotita" ,
                            "profile_uf",

                            "Dittanthrakika" ,"temparture", "rithmos_afidatosis",
                            "ksiro_varos","max_uf",

                            "Darbepoetin",
                            "feName" ,"carnName" ,"vitName","epoName" ,
                            "Paracalcitol" ,
                             "Alfacalcidol" ,
                            "etelalcetide",
                            "emvolio_ip_b" ,"emvolio_antigrip",
                            "emvolio_pneum_str" ,"embolio_covid1_str",
                             "embolio_covid2_str" ,
                             "embolio_covid3_str" ,
                             "embolio_covid4_str" ,
                            "other_vac" ,
                             "other_meds" ,
                            "genikes_odigies"
                    };



                    String []  plagioiTitloiFrontis = new String[]{"Ιατρός",
                            "Είδος Αιμ." , "όγκος υποκατάστασης (k)" ,
                            "Συχνότητα αιμοκ.\nανά εβδομάδα" ,"Διάρκεια",
                            "Φίλτρο", "LOT Φίλτρου"  ,

                            "Αντιπηκτική αγωγή" ,
                            "Ροή αίματος" ,"Ροή διαλύματος" ,
                            "Διάλυμα - είδος", "Αγωγιμότητα" ,
                            "Προφίλ UF" ,

                            "Διττανθρακικά (meq/l)" ,"Θερμοκρασία", "Μέγιστος Ρυθμός\n Αφυδάτωσης",
                            "Ξηρό βάρος","Max UF/Rate",
                            "Darbepoetin",
                            "Fe" ,"L-Carnitine" ,"Vit B", "Epoetin" ,
                             "Paracalcitol" ,
                             "Alfacalcidol" ,
                            "Etelalcetide",
                            "Εμβόλιο ηπατίτιδας Β" ,"Αντιγριπικό εμβόλιο",
                            "Εμβόλιο πνευμονιόκοκκου" ,
                            "Εμβ. COVID 19 δόση 1" ,
                            "Εμβ. COVID 19 δόση 2" ,
                            "Εμβ. COVID 19 δόση 3" ,
                            "Εμβ. COVID 19 δόση 4" ,
                            "Άλλα εμβόλια" ,
                            "Λοιπά φάρμακα" ,
                            "Γενικές οδηγίες"
                    };

                    String [] cols;
                    String [] plagioiTitloi;
                    if (Customers.isFrontis(custID)){
                        cols = colsFrontis;
                        plagioiTitloi = plagioiTitloiFrontis;
                    }
                    else{
                        cols = colsMedit;
                        plagioiTitloi = plagioiTitloiMedit;
                    }

                    cols = ArrayUtils.removeAll(cols, "");
                    plagioiTitloi = ArrayUtils.removeAll(plagioiTitloi, "");

                    Intent in = tableView_sigkentrotika(Str_queries.getSigkentrotika_Medical_instructions(patientID , custID),
                            transgroupID ,
                            panoTitloi,plagioiTitloi,cols,false, false, false);

                     startActivity(in);

                }
                else
                    Toast.makeText(MainActivity_Aim.this, R.string.no_data, Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();
            }
        };

        task.execute();
    }



    private void search_TV_listener() {

        bd.patientsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragmentSearchPat df= new DialogFragmentSearchPat();
                Bundle putextra = new Bundle();
                putextra.putSerializable("patients", patList);

                df.setArguments(putextra);
                df.show(getSupportFragmentManager() , "Dialog");

                isLoaded = false;
            }
        });

    }



    public void getVardies(){


        alertDialog.show();

        if (Utils.isNetworkAvailable(this)) {
            String query = "select id, name as vardiaName " +
                    " from MTNWatch where companyid = " + companyID +
                    " and cancelled is null";
            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = getApplicationContext();
            task.listener = MainActivity_Aim.this;
            task.query = query;
            task.execute();
        }
        else {
            alertDialog.dismiss();
            bd.buttonRef.clearAnimation();
            Toast.makeText(this, R.string.check_internet_access, Toast.LENGTH_SHORT).show();

        }
    }




    @Override
    public void taskCompleteGetVardies(JSONArray results) throws JSONException {

        namesOfVardies.clear();

        for (int i=0; i < results.length(); i++){

            JSONObject jsonObject = results.getJSONObject(i);

            Vardies vardies = new Vardies();
            vardies.setId(jsonObject.getInt("id"));
            vardies.setName(jsonObject.getString("vardiaName"));


            namesOfVardies.add(vardies);
            //namesOfPatients.add();


            alertDialog.dismiss();

        }

        isLoaded = false;

        ArrayAdapter dataAdapter = new ArrayAdapter<>(MainActivity_Aim.this,
                R.layout.spinner_layout_for_vardies, Utils.getVardiesCodeName(namesOfVardies));
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout_for_vardies);
        bd.vardiesSP.setAdapter(dataAdapter);



//  ΑΥΤΟ ΕΙΝΑΙ ΓΙΑ ΝΑ ΒΡΙΣΚΕΙ ΤΗΝ ΒΑΡΔΙΑ ΚΑΙ ΣΤΗ ΣΥΝΕΧΕΙΑ ΤΟΥΣ ΕΚΑΣΤΟΤΕ ΑΣΘΕΝΕΙΣ ΑΝΑΛΟΓΑ ΜΕ ΤΗΝ ΕΚΑΣΤΟΤΕ ΩΡΑ ΤΟΥ ΡΟΛΟΓΙΟΥ
        //String vardiaID = Utils.getVardiaID(namesOfVardies);


        //-------------------------------------------------------------------------------------------------------------

        // ΑΥΤΟ ΕΙΝΑΙ ΓΙΑ ΝΑ ΒΡΙΣΚΕΙ ΤΟΥΣ ΑΣΘΕΝΕΙΣ ΑΝΑΛΟΓΑ ΜΕ ΤΗΝ ΕΠΙΛΕΓΜΕΝΗ ΒΑΡΔΙΑ ΑΠΟ ΤΟΝ ΧΡΗΣΤΗ
        vardiaID = "0";
        bd.vardiesSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vardiaID = Utils.getfirstPartSplitCommaString(bd.vardiesSP.getSelectedItem().toString());

                //KALESMA getPatients NA TRAVIKSEI TOUS ASTHENEIS TIS SIGKEKRIMENEIS VARDIAS
                getPatients(vardiaID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //---------------------------------


    }




    public void addFragments(){
        if (!sinexFragment.isAdded())
            fm.beginTransaction().add(R.id.main_container, sinexFragment, "3").hide(sinexFragment).commitAllowingStateLoss();
        if (!statFragemnt.isAdded())
            fm.beginTransaction().add(R.id.main_container, statFragemnt, "2").hide(statFragemnt).commitAllowingStateLoss();
        if (!medFragment.isAdded())
            fm.beginTransaction().add(R.id.main_container, medFragment, "1").commitAllowingStateLoss();

    }



    private void buttonRefreshPatientsListener(){

        bd.buttonRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Animation rotation = AnimationUtils.loadAnimation(MainActivity_Aim.this, R.anim.rotate);
                rotation.setFillAfter(true);
                bd.buttonRef.startAnimation(rotation);
                alertDialog.show();
               // getVardies();
                if (bd.vardiesSP.getSelectedItem() != null) {
                    vardiaID = Utils.getfirstPartSplitCommaString(bd.vardiesSP.getSelectedItem().toString());
                    getPatients(vardiaID);
                }
            }
        });
    }






    private void getPatients(String vardiaID){

        if (alertDialog != null)
            alertDialog.show();

        if (!isLoaded) {

            if (Utils.isNetworkAvailable(this)) {

                if (Utils.getAddress(this).equals("")) {
                    startActivity(new Intent(this, LoginActiity.class));
                    finish();
                }


                String query = "" +
                        Str_queries.CUR_DATE +

                        " select \n" +
                        "tg.id as transgroupID, p.FirstName, p.LastName, \n" +
                        "p.amka, \n " +
                        "tg.PatientID, \n" +
                        " dbo.datetostr(tg.DateIn) as datein, \n" +
                        " tg.isEmergency,\n " +
                        " tg.code, \n" +
                        (Customers.isFrontis(custID) ? " p.code as patCode,p.height,dbo.CALCULATEAGE(P.DATEBIRTH, TG.DATEOUT) as age," : "") +
                        " tg.MTNWatchID \n" +
                        " from TransGroup tg \n" +
                        " left join Person p on p.id = tg.PatientID \n " +
                        (Customers.isFrontis(custID) ? " left join transitem ti on ti.transgroupID = tg.id \n" : "" ) +
                        " where \n" +
                        " tg.Category = 3  \n" +
                        " AND tg.datein >= @curDate and tg.datein < @curDate + 86400000 \n" +
                        " and tg.companyid = " + companyID + "\n" +
                        " and tg.MTNWatchID = " + vardiaID + "\n" +
                        (Customers.isFrontis(custID) ?
                                " group by tg.id,p.FirstName, p.LastName, p.amka, tg.PatientID, dbo.datetostr(tg.DateIn) , tg.isEmergency, tg.code, \n" +
                                " p.code ,p.height,dbo.CALCULATEAGE(P.DATEBIRTH, TG.DATEOUT) , tg.MTNWatchID \n" +
                                        " HAVING count(distinct ti.ID) > 2" : "" ) +
                        "\n" +
                        " order by p.LastName, p.FirstName "
                        ;

                AsyncTaskGetJSON task = new AsyncTaskGetJSON();
                task.ctx = getApplicationContext();
                task.listener = MainActivity_Aim.this;
                task.query = query;
                task.execute();


            } else {
                alertDialog.dismiss();
                bd.buttonRef.clearAnimation();
                Toast.makeText(this, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
            }

        }
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void taskComplete(JSONArray results) throws JSONException {

        int patPos = 0;

        patList.clear();
        if (results == null) {
            alertDialog.dismiss();
            Toast.makeText(this, "Δεν υπάρχουν αυτή τη στιγμή δεδομένα", Toast.LENGTH_SHORT).show();
            bd.buttonRef.clearAnimation();
        }
        else {

            if (!(results.getJSONObject(0).has("status"))) {

                for (int i = 0; i < results.length(); i++) {

                    JSONObject currentPatient = results.getJSONObject(i);

                    PatientsOfTheDay pat = new PatientsOfTheDay();

                    pat.setFirstName(currentPatient.getString("FirstName"));
                    pat.setLastName(currentPatient.getString("LastName"));
                    pat.setTransgroupID(currentPatient.getInt("transgroupID"));
                    pat.setPatientID(currentPatient.getInt("PatientID"));
                    pat.setDatein(currentPatient.getString("datein"));
                    pat.setVardiaID(currentPatient.getInt("MTNWatchID"));
                    pat.setAmka(currentPatient.getString("amka"));
                    pat.setIsEmergency(currentPatient.getString("isEmergency"));
                    pat.setCode(currentPatient.getString("code"));
                    if (Customers.isFrontis(custID)){
                        pat.setPatCode(currentPatient.getString("patCode"));
                        pat.setAge(currentPatient.getString("age"));
                        pat.setHeight(currentPatient.getString("height"));
                    }

                    if (transgroupID != null && transgroupID.equals(String.valueOf(currentPatient.getInt("transgroupID"))))
                        patPos = i;


                    patList.add(pat);
                    getTransgroupIDUsingCode.put(currentPatient.getString("code"), String.valueOf(currentPatient.getInt("transgroupID")));
                    getPatientIDUsingCode.put(currentPatient.getString("code"), String.valueOf(currentPatient.getInt("PatientID")));

                    namesOfPatients.add(currentPatient.getString("code") + " , "
                            + currentPatient.getString("FirstName") +
                            " " + currentPatient.getString("LastName"));


                }



                bd.patientsTV.setText(patList.get(patPos).getCode() + " , " +
                        patList.get(patPos).getLastName() + " " +
                        patList.get(patPos).getFirstName() + " , " +
                        patList.get(patPos).getAmka() +
                        (Customers.isFrontis(custID) ? ",\n"  +
                               "Κωδ:  " + patList.get(patPos).getPatCode() + "  ,  " +
                               "Ηλικία:  " + patList.get(patPos).getAge() + "  , " +
                                "Ύψος:  " + patList.get(patPos).getHeight() + " cm" : "")

                );

                if (patList.get(patPos).getIsEmergency().equals("true") || patList.get(patPos).getIsEmergency().equals("1"))
                    bd.patientsTV.setTextColor(Color.RED);
                else
                    bd.patientsTV.setTextColor(Color.BLACK);


            } else {
                bd.patientsTV.setText(StaticFields.NO_PATIENT_FOUND);
                namesOfPatients.add(StaticFields.NO_PATIENT_FOUND);
            }

            bd.buttonRef.clearAnimation();


           if (firstTime) {
               addFragments();
               firstTime = false;
           }else
               refreshFrgamentsData();

        }
    }


    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);

        x.getDoctorAndOtherInfo(results);

      //  y.getOldTeleutaiesMetriseis();
       // z.getTeleutaiaMetrisi();

    }

    private void refreshFrgamentsData(){

        x.getMedicalInsructions();
        y.getOldTeleutaiesMetriseis();
        z.getTeleutaiaMetrisi(true);

    }


    @Override
    public void dataHasArrived(boolean isArrived) {
        if (isArrived)
            refreshFrgamentsData();

    }




    private void setNotificationDialog(String transgroupID, String patientID){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ειδοποιήσεις")
                .setCancelable(true)
                .setPositiveButton("Ιατρικές εντολές", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        notificationsToConfirm(DF_Notifications.MEDICAL_INS_NOTIFICATIONS,patientID);
                        dialog.cancel();

                    }
                })


                .setNegativeButton("Προς επιβεβαίωση", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        notificationsToConfirm(DF_Notifications.NOTIFICATIONS_TO_CONFIRM,null);
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public String getPatientID(){
        String code = Utils.getfirstPartSplitCommaString(bd.patientsTV.getText().toString());
        return getPatientIDUsingCode.get(code);
    }

    public String getTransgroupID(){
        String code = Utils.getfirstPartSplitCommaString(bd.patientsTV.getText().toString());
        return getTransgroupIDUsingCode.get(code);
    }


    private void notificationsToConfirm(int typeOfNotification,String patientID ){

        DF_Notifications df = new DF_Notifications();
        Bundle putextra = new Bundle();
        putextra.putBoolean("iconPressed",isIconPressed);
        putextra.putInt(DF_Notifications.TYPE_OF_NOTIFICATION, typeOfNotification);
        if (patientID != null)
            putextra.putString("patientID", patientID);
        df.setArguments(putextra);
        df.show(getSupportFragmentManager() , "Dialog");
    }





    private void changeColorOfTV(){
            final Handler handler = new Handler();
            Timer timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            if (bd.newNotificationsTV.getCurrentTextColor() == getResources().getColor(R.color.colorAccent))
                                bd.newNotificationsTV.setTextColor(getResources().getColor(R.color.black));
                            else
                                bd.newNotificationsTV.setTextColor(getResources().getColor(R.color.colorAccent));

                        }
                    });
                }
            };

            timer.schedule(task, 0, 800);

    }


    public void check_notifications_thread(){

        //ΕΔΩ ΔΟΥΛΕΥΕΙ ΟΤΑΝ Η ΕΦΑΡΜΟΓΗ ΕΙΝΑΙ ΑΝΟΙΧΤΗ Η ΚΛΕΙΣΤΗ ΜΕΣΩ ΤΟΥ HOME
        //ΑΝ ΤΗΝ ΚΛΕΙΣΕΙ ΤΕΛΕΙΩΣ Η ΑΠ[ΛΑ ΠΑΤΗΣΕΙ ΤΟ BACK ΔΕΝ ΘΑ ΛΕΙΤΟΥΡΓΕΙ
        String query = Str_queries.getNotification_Messages(companyID);

 //       public static void check_data_thread(String query,Activity act, long period , AsyncCompleteTask2 listener ){
//
//
        final Handler handler = new Handler();
        Timer timer = new Timer();

         not_timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (notificationState) {
                            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2(query, MainActivity_Aim.this);
                            task.listener = new AsyncCompleteTask2() {
                                @Override
                                public void taskComplete2(JSONArray results) throws JSONException {
                                    if (results != null && results.length() > 0 && !results.getJSONObject(0).has("status")) {

                                        String msg = null;
                                        id_not = results.getJSONObject(0).getInt("ID");
                                        if (id_not == DF_Notifications.NOTIFICATIONS_BOTH_CATEGORIES) {
                                            msg = "Νέες ειδοποιήσεις και στις 2 ενότητες ";
                                            bd.newNotificationsTV.setText(msg);
                                        }
                                        else if (id_not == DF_Notifications.NOTIFICATIONS_TO_CONFIRM) {
                                            msg = "Eιδοποιήσεις έγκρισης ";
                                            bd.newNotificationsTV.setText(msg);
                                        }
                                        else if (id_not == DF_Notifications.MEDICAL_INS_NOTIFICATIONS) {
                                            msg = "Eιδοποιήσεις \n ιατρ.εντολών ";
                                            bd.newNotificationsTV.setText(msg);
                                        }

                                        if (msg != null) {
                                            int reqCode = 1;
                                            Intent intent = new Intent(getApplicationContext(), MainActivity_Aim.class);
                                            Utils.showNotification(MainActivity_Aim.this, "Νέα ειδοποίηση", msg, intent, reqCode);
                                        }

                                        bd.newNotificationsTV.setVisibility(View.VISIBLE);

                                        timerstate = true;


                                    } else {
                                        if (bd.newNotificationsTV.getVisibility() == View.VISIBLE)
                                            bd.newNotificationsTV.setVisibility(View.GONE);

                                        task.cancel(true);
                                        timerstate = false;


                                    }

                                }
                            };
                            task.execute();

                        }
                    }
                });

            }
        };

         timer.schedule(not_timerTask, 0, 83200);
        }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (not_timerTask != null)
            not_timerTask.cancel();
    }

    @Override
    protected void onPause() {
        if (task != null)
            task.cancel();
        //notificationState = false;
        super.onPause();
    }


    @Override
    protected void onResume() {
      //  check_new_data_thread();
        notificationState = true;

        super.onResume();
    }
}