package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.github.ag.floatingactionmenu.OptionsFabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Vardies;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataListener;
import micros_leader.george.nosileutikosfakelos.LoginActiity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_2.fragments.Iatrikes_odigies_fragment;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_2.ui.main.SectionsPagerAdapter;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities.SinexeisMetriseis_All_Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities.StatheresMetriseis_All_Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors.DIalogFragmentXreosimaIlika;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors.DialogFragmentSearchPat;

public class MainActivity_Aim extends BasicActivity implements   AsyncCompleteTask, AsyncGetUpdateResult, DataListener {

    public  TextView search_TV;
    private Button buttonREF;
    private OptionsFabLayout fabMenu;
    private Spinner vardiesSP;
    public String name ,companyID ,vardiaID;
    private ArrayList<String> namesOfPatients =  new ArrayList<>();
    private ArrayList<Vardies> namesOfVardies =  new ArrayList<>();
    public Map<String,String> getTransgroupIDUsingCode , getPatientIDUsingCode;
    private static String NO_PATIENT_FOUND = "0 , Δεν υπάρχουν ασθενείς στη συγκεκριμένη βάρδια";
    private ArrayList <PatientsOfTheDay> patientsArrayList =  new ArrayList<>();
    private ViewPager viewPager;
    private Iatrikes_odigies_fragment iatr_fragment;
    public boolean isLoaded = true;
    public CircularProgressButton updateBT;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__aim);

        iatr_fragment = new Iatrikes_odigies_fragment();
        extendedActivity = this;

        //-------------------------

        companyID = Utils.getcompanyID(this);
        getTransgroupIDUsingCode = new HashMap<>();
        getPatientIDUsingCode = new HashMap<>();
        buttonREF = findViewById(R.id.buttonRef);
        fabMenu =  findViewById(R.id.fabMenu);
        managefabMenuIcon();
        vardiesSP = findViewById(R.id.vardiesSP);
        vardiesSP.setPopupBackgroundResource(R.drawable.custom_spinner_list);
        search_TV = findViewById(R.id.patientsTV);


        buttonRefreshPatientsListener();

        search_TV_listener();
        getVardies();


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
        if (fabMenu.isOptionsMenuOpened())
            fabMenu.closeOptionsMenu();
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






    private void managefabMenuIcon() {

//Set main fab clicklistener.
        fabMenu.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabMenu.isOptionsMenuOpened())
                    fabMenu.closeOptionsMenu();
            }
        });


        fabMenu.setMiniFabsColors(
                R.color.colorPrimary,
           //     R.color.colorPrimary,
           //     R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary);



        //  LISTENER  GIA TA ITEMS TOY
        fabMenu.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {

                Intent intent;
                String code = Utils.getfirstPartSplitCommaString(search_TV.getText().toString());
                String patientID = getPatientIDUsingCode.get(code);

                switch (fabItem.getItemId()) {

                    case R.id.xreosima_ilika:

                        DIalogFragmentXreosimaIlika df =  new DIalogFragmentXreosimaIlika();
                        Bundle putextra = new Bundle();
                        transgroupID = getTransgroupIDUsingCode.get(code);
                        putextra.putString("transgroupID", transgroupID);
                        df.setArguments(putextra);
                        df.show(getSupportFragmentManager() , "Dialog");
                        fabMenu.closeOptionsMenu();

                        break; // true to keep the Speed Dial open

//                    case R.id.oles_oi_statheres_metriseis:
//
//                        intent =  new Intent(MainActivity_Aim.this, StatheresMetriseis_All_Activity.class);
//                        intent.putExtra("transgroupID",getTransgroupIDUsingCode.get(code));
//                        intent.putExtra("patientID",patientID);
//                        startActivity(intent);
//                        fabMenu.closeOptionsMenu();
//
//
//                        break; // true to keep the Speed Dial open
//
//
//                    case R.id.oles_oi_sinexeis_metriseis:
//
//                        intent =  new Intent(MainActivity_Aim.this, SinexeisMetriseis_All_Activity.class);
//                        intent.putExtra("transgroupID",getTransgroupIDUsingCode.get(code));
//                        intent.putExtra("patientID",patientID);
//                        startActivity(intent);
//                        fabMenu.closeOptionsMenu();
//
//                        break; // true to keep the Speed Dial open


//                    case R.id.sigkentrotika_sinexeis_metriseis:
//
//                        transgroupID = getTransgroupIDUsingCode.get(code);
//
//                       intent =   tableView_sigkentrotika(new Str_queries().getSigkentrotika_sinexwn_metrisewn(patientID),
//                                transgroupID,
//                                new String[]{"ID","ΗΜ/ΝΙΑ / ΩΡΑ","Συστολική πίεση (Hg)","Διαστολική πίεση (mm)",
//                                        "Σφύξεις:","Θερμοκρασία (°C)","Ρυθμός ροή αντλίας (ml/min)","Πίεση φλεβ. γραμμής",
//                                        "Πίεση αρτ. γραμμής:","Ρυθμός υπερδιηθησης \n ανά ώρα(U.F.) (l/h)","Αγωγιμότητα (ms/cm)","HCO 3 (ms/cm)",
//                                        "Μετρήσεις-Παραμβάσεις","Παρατηρήσεις","Εγχύσεις - Φάρμακα","Vit - B",
//                                        "L - carnittine","Alphacalcidol","EPO","Epoetin alpha",
//                                        "Epoetin zeta","Darbepoetin","Paricalcitol","Σίδηρος"},
//                                null,
//                                InfoSpecificLists.getSigkentrotikaSinexeisMetriseis_MEDIT(),
//                                false,
//                                false,
//                                true);
//
//                       intent.putExtra("setOnlyFirstRowAvalaible",true);
//                      startActivity(intent);
//
//                        break;


                    case R.id.sigkentrotika_medical_instructions:

                        transgroupID = getTransgroupIDUsingCode.get(code);

                        getSigkentrotikaMedicalInstructions();

                        break;


                    default:

                        break; // true to keep the Speed Dial open


                }
            }
        });
    }




    private void getSigkentrotikaMedicalInstructions() {

        //alertDialog.show();
        String code = Utils.getfirstPartSplitCommaString(search_TV.getText().toString());
        final String patientID = getPatientIDUsingCode.get(code);
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.query = "select y.Name as yearName , m.Name as monthName  " +
                "from Nursing_Medical_Instructions  n " +
                "left join years y on y.ID = n.Year " +
                "left join v_Month m on m.ID = n.month " +
                "where patientid = " + patientID +
                " order by n.year desc , n.Month desc " ;
        task.ctx = this;
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {


                if (results != null && !results.getJSONObject(0).has("status")) {

                    String []  panoTitloi =new String[results.length()];

                    ArrayList<TableViewItem> tableLista = InfoSpecificLists.getSigkentrotika_MedicalInst_lista();


                    for (int i=0; i<results.length(); i++){

                        JSONObject jsonObject = results.getJSONObject(i);
                        int id = jsonObject.getInt("yearName");
                        String name = jsonObject.getString("monthName");

                        panoTitloi[i] = id + "," + name;


                    }


                    String []  cols = new String[]{"doctorName","eidos_aim" ,"sixnotita_aim" ,"durationStr",
                            "Filter","lot_filter", "agogi" ,"roi_aima" ,"roi_dialima" ,
                            "dialima","agogimotita" ,"Dittanthrakika" ,"temparture", "rithmos_afidatosis",
                            "ksiro_varos","max_uf","zeta" ,"alpha" ,"Darbepoetin",
                            "etelalcetide","Fe" ,"Carnitine" ,"VitB",
                            "Paracalcitol","Alfacalcidol" ,"emvolio_ip_b" ,"emvolio_antigrip",
                            "genikes_odigies"
                    };

                    String []  plagioiTitloi = new String[]{"Ιατρός","Είδος Αιμ." ,"Συχνότητα αιμοκ.\nανά εβδομάδα" ,"Διάρκεια",
                            "Φίλτρο","LOT Φίλτρου","Αντιπηκτική αγωγή" ,"Ροή αίματος" ,"Ροή διαλύματος" ,
                            "Διάλυμα - είδος","Αγωγιμότητα" ,"Διττανθρακικά" ,"Θερμοκρασία", "Μέγιστος Ρυθμός\n Αφυδάτωσης",
                            "Ξηρό βάρος","Max UF/Rate","Epoetin zeta" ,"Epoetin alpha" ,"Darbepoetin",
                            "Etelalcetide","Fe" ,"L-Carnitine" ,"Vit B",
                            "Paracalcitol","Alfacalcidol" ,"Εμβόλιο ηπατίτιδας Β" ,"Αντιγριπικό εμβόλιο",
                            "Γενικές οδηγίες"
                    };

                    Intent in = tableView_sigkentrotika(Str_queries.getSigkentrotika_Medical_instructions(patientID ,Utils.getCustomerID(MainActivity_Aim.this)),
                            transgroupID ,
                            panoTitloi,plagioiTitloi,cols,false, false, false);

                    //in.putExtra("date",dateTV.getText().toString());
                    startActivity(in);
                }

                alertDialog.dismiss();
            }
        };

        task.execute();
    }



    private void search_TV_listener() {

        search_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragmentSearchPat df= new DialogFragmentSearchPat();
                Bundle putextra = new Bundle();
                putextra.putSerializable("patients", patientsArrayList);

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
            buttonREF.clearAnimation();
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
        vardiesSP.setAdapter(dataAdapter);



//  ΑΥΤΟ ΕΙΝΑΙ ΓΙΑ ΝΑ ΒΡΙΣΚΕΙ ΤΗΝ ΒΑΡΔΙΑ ΚΑΙ ΣΤΗ ΣΥΝΕΧΕΙΑ ΤΟΥΣ ΕΚΑΣΤΟΤΕ ΑΣΘΕΝΕΙΣ ΑΝΑΛΟΓΑ ΜΕ ΤΗΝ ΕΚΑΣΤΟΤΕ ΩΡΑ ΤΟΥ ΡΟΛΟΓΙΟΥ
        //String vardiaID = Utils.getVardiaID(namesOfVardies);


        //-------------------------------------------------------------------------------------------------------------

        // ΑΥΤΟ ΕΙΝΑΙ ΓΙΑ ΝΑ ΒΡΙΣΚΕΙ ΤΟΥΣ ΑΣΘΕΝΕΙΣ ΑΝΑΛΟΓΑ ΜΕ ΤΗΝ ΕΠΙΛΕΓΜΕΝΗ ΒΑΡΔΙΑ ΑΠΟ ΤΟΝ ΧΡΗΣΤΗ
        vardiaID = "0";
        vardiesSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vardiaID = Utils.getfirstPartSplitCommaString(vardiesSP.getSelectedItem().toString());

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
        viewPager = null;
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);  // DOULEUEI DEN KANEI REFRESH
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);

        //setOffScreenLimit(); dokimi
        //setRetainInstance(true); //  STO FRAGMENT NA MPEI AUTO GIA NA MIN KANEI REFRESH APLA DOKIMI
    }



    private void buttonRefreshPatientsListener(){

        buttonREF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewPager.setSaveFromParentEnabled(false);

                Animation rotation = AnimationUtils.loadAnimation(MainActivity_Aim.this, R.anim.rotate);
                rotation.setFillAfter(true);
                buttonREF.startAnimation(rotation);
                alertDialog.show();
                getVardies();
            }
        });
    }






    private void getPatients(String vardiaID){

        alertDialog.show();
        if (!isLoaded) {

            if (Utils.isNetworkAvailable(this)) {

                if (Utils.getAddress(this).equals("")) {
                    startActivity(new Intent(this, LoginActiity.class));
                    finish();
                }


                String query = "select " +
                        "tg.id as transgroupID, " +
                        "p.FirstName, " +
                        "p.LastName, " +
                        "p.amka, " +
                        "tg.PatientID, " +
                        " dbo.datetostr(tg.DateIn) as datein, " +
                        " tg.isEmergency, " +
                        " tg.code, " +
                        " tg.MTNWatchID " +
                        " from TransGroup tg " +
                        " left join Person p on p.id = tg.PatientID " +
                        " where " +
                        " tg.Category = 3  AND dbo.dateToStr(tg.datein) = '" + Utils.getCurrentDate() + "'" +
                        " and tg.companyid = " + companyID +
                        " and tg.MTNWatchID = " + vardiaID;

                AsyncTaskGetJSON task = new AsyncTaskGetJSON();
                task.ctx = getApplicationContext();
                task.listener = MainActivity_Aim.this;
                task.query = query;
                task.execute();


            } else {
                alertDialog.dismiss();
                buttonREF.clearAnimation();
                Toast.makeText(this, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
            }

        }
    }


    @Override
    public void taskComplete(JSONArray results) throws JSONException {

        patientsArrayList.clear();
        if (results == null) {
            alertDialog.dismiss();
            Toast.makeText(this, "Δεν υπάρχουν αυτή τη στιγμή δεδομένα", Toast.LENGTH_SHORT).show();
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
                    pat.setIsEmergency(String.valueOf(currentPatient.getString("isEmergency")));
                    pat.setCode(currentPatient.getString("code"));

                    patientsArrayList.add(pat);
                    getTransgroupIDUsingCode.put(currentPatient.getString("code"), String.valueOf(currentPatient.getInt("transgroupID")));
                    getPatientIDUsingCode.put(currentPatient.getString("code"), String.valueOf(currentPatient.getInt("PatientID")));

                    namesOfPatients.add(currentPatient.getString("code") + " , "
                            + currentPatient.getString("FirstName") +
                            " " + currentPatient.getString("LastName"));


                }

                search_TV.setText(patientsArrayList.get(0).getCode() + " , "
                        + patientsArrayList.get(0).getFirstName() + " " +
                        patientsArrayList.get(0).getLastName() + " , " +
                        patientsArrayList.get(0).getAmka());

                if (patientsArrayList.get(0).getIsEmergency().equals("true") || patientsArrayList.get(0).getIsEmergency().equals("1"))
                    search_TV.setTextColor(Color.RED);
                else
                    search_TV.setTextColor(Color.BLACK);


            } else {
                search_TV.setText(NO_PATIENT_FOUND);
                namesOfPatients.add(NO_PATIENT_FOUND);
            }

            buttonREF.clearAnimation();


            addFragments();

        }
    }


    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);

        if (viewPager != null)
        if (getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + viewPager.getCurrentItem())
        instanceof Iatrikes_odigies_fragment) {


    Iatrikes_odigies_fragment page = (Iatrikes_odigies_fragment) getSupportFragmentManager()
            .findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + viewPager.getCurrentItem());
    page.getDoctorAndOtherInfo(results);

        }
    }


    @Override
    public void dataHasArrived(boolean isArrived) {
        if (isArrived){

//            SinexeisFragment sinexeisFragment = (SinexeisFragment) getSupportFragmentManager()
//                    .findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + viewPager.getCurrentItem());
//            System.out.println("xaxa");
//            sinexeisFragment.getTeleutaiaMetrisi();


        }
    }
}