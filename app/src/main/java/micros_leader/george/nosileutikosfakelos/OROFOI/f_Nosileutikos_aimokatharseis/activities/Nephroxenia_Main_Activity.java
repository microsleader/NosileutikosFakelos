package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.leinardi.android.speeddial.SpeedDialActionItem;
//import com.leinardi.android.speeddial.SpeedDialView;
import com.github.ag.floatingactionmenu.OptionsFabLayout;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Vardies;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors.DIalogFragmentXreosimaIlika;
import micros_leader.george.nosileutikosfakelos.SettingsActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors.DialogFragmentSearchPat;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.fragments.CheckInternetConnectionFragment;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.fragments.ContinuousMeasurementsFragment;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.fragments.ContinuousMeasurementsFragment_OLD;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.fragments.StableMeasurementsFragment;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.LoginActiity;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.fragments.StableMeasurementsFragment_OLD;

public class Nephroxenia_Main_Activity extends AppCompatActivity implements   NavigationView.OnNavigationItemSelectedListener , AsyncCompleteTask,  AsyncGetUpdateResult {




    private String name ,companyID ,vardiaID, transgroupID;
    private TextView currentDate_TV;
    public  Map <String,String> getTransgroupIDUsingCode , getPatientIDUsingCode;
    private ImageView logo_image;
    public  DrawerLayout drawer;
    private ArrayList <PatientsOfTheDay> patientsArrayList =  new ArrayList<>();
    private ArrayList<String> namesOfPatients =  new ArrayList<>();
    private ArrayList<Vardies> namesOfVardies =  new ArrayList<>();
    private NavigationView navigationView;
    private static String NO_PATIENT_FOUND = "0 , Δεν υπάρχουν ασθενείς στη συγκεκριμένη βάρδια";
    private static String FRAGMENT_STABLE_MEAS_TAG = "FRAG_STABLE";
    public String userID;
    public  TextView search_TV;
    private Button buttonREF;
    private OptionsFabLayout  fabMenu;
    private Spinner vardiesSP;
    private int custID;
    private Toolbar toolbar;
    private RotateLoading loading ;
    public AlertDialog.Builder builder;
    public AlertDialog alertDialog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Νοσηλευτικός φάκελος");
        setSupportActionBar(toolbar);

        userID = Utils.getUserID(this);
        companyID = getIntent().getStringExtra("companyID");

        patientsArrayList.clear();
        namesOfPatients.clear();
        getTransgroupIDUsingCode = new HashMap<>();
        getPatientIDUsingCode = new HashMap<>();
        setAlertDialog();

        custID = Utils.getCustomerID(this);
       // pd.setMessage("loading");
         fabMenu =  findViewById(R.id.fabMenu);
         managefabMenuIcon();


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        if (custID == Customers.CUSTID_NEPHROXENIA || custID == 20)
            hideMedicalInstructions();

        name = getIntent().getStringExtra("name");


       // logo_image = headerView.findViewById(R.id.image_logo);
        currentDate_TV = headerView.findViewById(R.id.currentDateTextView);
        currentDate_TV.setText(Utils.getCurrentDate());
        buttonREF = headerView.findViewById(R.id.buttonRef);

        vardiesSP = headerView.findViewById(R.id.vardiesSP);
        vardiesSP.setPopupBackgroundResource(R.drawable.custom_spinner_list);


        search_TV = headerView.findViewById(R.id.patientsTV);

        setNavMenuItemThemeColors();

        buttonRefreshPatientsListener();
        //getLogo();

       // drawerListener();
        search_TV_listener();
        getVardies();







    }

    private void hideMedicalInstructions(){
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.iatrikes_odigies).setVisible(false);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Nephroxenia_Main_Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
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

            }
        });

    }


    private void buttonRefreshPatientsListener(){
        buttonREF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation rotation = AnimationUtils.loadAnimation(Nephroxenia_Main_Activity.this, R.anim.rotate);
                rotation.setFillAfter(true);
                buttonREF.startAnimation(rotation);
                alertDialog.show();
                getVardies();
            }
        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.nosil_aim_new, menu);
//        return true;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.startWeightTV) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



    private void managefabMenuIcon(){

        fabMenu.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabMenu.isOptionsMenuOpened())
                    fabMenu.closeOptionsMenu();
            }
        });


        fabMenu.setMiniFabsColors(
                R.color.green_fab,
                R.color.green_fab,
                R.color.green_fab,
                R.color.green_fab,
                R.color.green_fab,
                R.color.green_fab

        );


        //  LISTENER  GIA TA ITEMS TOY
        fabMenu.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {

                Intent intent;
                Bundle putextra;
                DialogFragment df ;
                String code = Utils.getfirstPartSplitCommaString(search_TV.getText().toString());
                String patientID = getPatientIDUsingCode.get(code);
                transgroupID = getTransgroupIDUsingCode.get(code);
                BasicActivity x;
                String [] panoTitloi ;
                String [] plagioiTiloi ;
                String [] colNames ;
                String query;

                switch (fabItem.getItemId()) {


                    case R.id.efapax_medicine:


                         query = "select x.* , i.name from Nursing_xorigisi_farmakon_efapax x " +
                                "left join item i on i.id = x.itemID " +
                                " where patientID = " + patientID + " and x.efapax = 1";
                         panoTitloi = new String[]{"Φάρμακο","Δοσολογία","Μονάδα","Δόση","Συχνότητα\nημέρας" , "Συχνότητα\nώρας"};
                         colNames = new String[]{"name","dosologia","monada","dosi","sixnotita_imeras","sixnotita_oras"};x = new BasicActivity();
                        x.extendedActivity = Nephroxenia_Main_Activity.this;
                       intent = x.tableView_sigkentrotika(query,null,panoTitloi,null,colNames,
                               false,false,false);
                       startActivity(intent);

                        break;


                    case R.id.medicine_sinedrias:


                        query = "select x.* , i.name from Nursing_xorigisi_farmakon_sinedrias x " +
                                "left join item i on i.id = x.itemID " +
                                " where x.transgroupid = " + transgroupID ;
                        panoTitloi = new String[]{"Κωδικός","Φάρμακο","Δοσολογία","Μονάδα","Δόση","Συχνότητα\nημέρας" , "Συχνότητα\nώρας"};
                        x = new BasicActivity();
                        x.extendedActivity = Nephroxenia_Main_Activity.this;
                        intent = x.tableView_sigkentrotika(query,null,panoTitloi,null,
                                InfoSpecificLists.getSigkentrotikaFarmakaSinedrias_Nephroxenia(),
                                false,false,true);
                        intent.putExtra("transgroupID",transgroupID);
                        startActivity(intent);

                        break;



                    case R.id.xreosima_ilika:


                        df =  new DIalogFragmentXreosimaIlika();
                        putextra = new Bundle();
                        putextra.putString("transgroupID", transgroupID);
                        df.setArguments(putextra);
                        df.show(getSupportFragmentManager() , "Dialog");

                        break;

                        //ΔΕΝ ΧΡΗΣΙΜΟΠΟΙΟΥΝΤΑΙ ΕΠΕΙΔΗ ΦΕΡΝΕΙ ΑΠΟΤΕΛΕΣΜΑΤΑ ΑΠΟ ΜΕΝΤ
                        /*
                    case R.id.oles_oi_statheres_metriseis:


                        intent =  new Intent(Nephroxenia_Main_Activity.this, StatheresMetriseis_All_Activity.class);
                        intent.putExtra("transgroupID",transgroupID);
                        intent.putExtra("patientID",patientID);
                        startActivity(intent);


                        break;

                    case R.id.oles_oi_sinexeis_metriseis:

                        intent =  new Intent(Nephroxenia_Main_Activity.this, SinexeisMetriseis_All_Activity.class);
                        intent.putExtra("transgroupID",transgroupID);
                        intent.putExtra("patientID",patientID);
                        startActivity(intent);

                        break;


                         */
                    default:
                        break;
                }
            }
        });
    }





    private void drawerListener() {
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


            }

            @Override
            public void onDrawerClosed(@NonNull View view) {


            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (fabMenu.isOptionsMenuOpened())
            fabMenu.closeOptionsMenu();

        else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }

    }

    public void getDefaultFragment(){

        navigationView.getMenu().getItem(1).setChecked(true);
        setNavMenuItemThemeColors();


        Bundle bundle = new Bundle();
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();

        String code = Utils.getfirstPartSplitCommaString(search_TV.getText().toString());
        transgroupID = getTransgroupIDUsingCode.get(code);
        String patientID = getPatientIDUsingCode.get(code);
        transgroupID = (transgroupID == null ? "0" : transgroupID); //TSEKAREI AN EINAI NULL GIA NA MIN SPASEI I EFARMOGI
        bundle.putString("transgroupID", transgroupID);
        bundle.putString("patientID",patientID);
        bundle.putString("companyID", companyID);

        Fragment frag;
        if (custID == Customers.CUSTID_NEPHROXENIA || custID == 20)
             frag =  new StableMeasurementsFragment_OLD();
        else
            frag = new StableMeasurementsFragment();

        frag.setArguments(bundle);

        tx.replace(R.id.container, frag);
        tx.commit();

    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Bundle bundle = new Bundle();
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();

        int id = item.getItemId();

        if (Utils.isNetworkAvailable(Nephroxenia_Main_Activity.this)) {

            if (id == R.id.iatrikes_odigies){
               // setNavMenuItemThemeColors();
               // clearStack();

                String code = search_TV.getText().toString();
                transgroupID = getTransgroupIDUsingCode.get(code);
                String patientID = getPatientIDUsingCode.get(Utils.getfirstPartSplitString(code ," , "));
                transgroupID = (transgroupID == null ? "0" : transgroupID); //TSEKAREI AN EINAI NULL GIA NA MIN SPASEI I EFARMOGI

                Intent in = new Intent(Nephroxenia_Main_Activity.this,MedicalInstructionsActivity.class);
                in.putExtra("transgroupID",transgroupID);
                in.putExtra("patientID",patientID);
                in.putExtra("companyID", companyID);
                startActivity(in);

//                bundle.putString("transgroupID", transgroupID);
//                bundle.putString("patientID",patientID);
//                bundle.putString("companyID", companyID);
//                MedicalInstructionsFragment frag = new MedicalInstructionsFragment();
//                frag.setArguments(bundle);
//
//                tx.replace(R.id.container, frag, FRAGMENT_MED_INSTRUCTIONS_TAG);
//                tx.commit();
            }



          else  if (id == R.id.stable_measurements) {

                setNavMenuItemThemeColors();
                clearStack();

                String code = search_TV.getText().toString();
                transgroupID = getTransgroupIDUsingCode.get(code);
                String patientID = getPatientIDUsingCode.get(code);
                transgroupID = (transgroupID == null ? "0" : transgroupID); //TSEKAREI AN EINAI NULL GIA NA MIN SPASEI I EFARMOGI
                bundle.putString("transgroupID", transgroupID);
                bundle.putString("patientID",patientID);
                bundle.putString("companyID", companyID);

                Fragment frag;
                if (custID == Customers.CUSTID_NEPHROXENIA || custID == 20)
                    frag =  new StableMeasurementsFragment_OLD();
                else
                    frag = new StableMeasurementsFragment();
                frag.setArguments(bundle);

                tx.replace(R.id.container, frag, FRAGMENT_STABLE_MEAS_TAG);
                tx.commit();


            } else if (id == R.id.continuous_measurements) {

                setNavMenuItemThemeColors();

                clearStack();

                String code = search_TV.getText().toString();
                transgroupID = getTransgroupIDUsingCode.get(code);
                transgroupID = (transgroupID == null ? "0" : transgroupID); //TSEKAREI AN EINAI NULL GIA NA MIN SPASEI I EFARMOGI
                bundle.putString("transgroupID", transgroupID);
                bundle.putString("companyID", companyID);

                Fragment frag;
                if (custID == Customers.CUSTID_NEPHROXENIA || custID == 20)
                    frag =  new ContinuousMeasurementsFragment_OLD();
                else
                    frag = new ContinuousMeasurementsFragment();
                frag.setArguments(bundle);

                tx.replace(R.id.container, frag, "MY_FRAGMENT");
                tx.commit();


            } else if (id == R.id.settings) {
                setNavMenuItemThemeColors();

                startActivity(new Intent(Nephroxenia_Main_Activity.this, SettingsActivity.class));

            } else if (id == R.id.log_out) {

                Utils.logout(Nephroxenia_Main_Activity.this);
                startActivity(new Intent(Nephroxenia_Main_Activity.this, LoginActiity.class));
                finish();

            }
        }
        else{
            CheckInternetConnectionFragment frag = new CheckInternetConnectionFragment();
            tx.replace(R.id.container, frag, FRAGMENT_STABLE_MEAS_TAG);
            tx.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public void setNavMenuItemThemeColors(){
        //Setting default colors for menu item Text and Icon
        int selectedItem = Color.parseColor("#cc0000");

        int navDefaultTextColor = Color.parseColor("#202020");
        int navDefaultIconColor = Color.parseColor("#737373");

        //Defining ColorStateList for menu item Text
        ColorStateList navMenuTextList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_pressed}
                },
                new int[] {
                        selectedItem,
                        navDefaultTextColor,
                        navDefaultTextColor,
                        navDefaultTextColor,
                        navDefaultTextColor
                }
        );

        //Defining ColorStateList for menu item Icon
        ColorStateList navMenuIconList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_pressed}
                },
                new int[] {
                        selectedItem,
                        navDefaultIconColor,
                        navDefaultIconColor,
                        navDefaultIconColor,
                        navDefaultIconColor
                }
        );

        navigationView.setItemTextColor(navMenuTextList);
        navigationView.setItemIconTintList(navMenuIconList);
    }





    public void getVardies(){


        alertDialog.show();

        if (Utils.isNetworkAvailable(Nephroxenia_Main_Activity.this)) {
            String query = "select id, name as vardiaName " +
                    " from MTNWatch where companyid = " + companyID +
                    " and cancelled is null";
            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = getApplicationContext();
            task.listener = Nephroxenia_Main_Activity.this;
            task.query = query;
            task.execute();
        }
        else {
            alertDialog.dismiss();
            buttonREF.clearAnimation();
            Toast.makeText(this, R.string.check_internet_access, Toast.LENGTH_SHORT).show();

            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            CheckInternetConnectionFragment frag = new CheckInternetConnectionFragment();
            tx.replace(R.id.container, frag, FRAGMENT_STABLE_MEAS_TAG);
            tx.commit();
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


        }


            ArrayAdapter dataAdapter = new ArrayAdapter<>(Nephroxenia_Main_Activity.this,
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







    private void getPatients(String vardiaID){

        if (Utils.isNetworkAvailable(Nephroxenia_Main_Activity.this)) {

            if (Utils.getAddress(Nephroxenia_Main_Activity.this).equals(""))
            {
                startActivity(new Intent(Nephroxenia_Main_Activity.this, LoginActiity.class));
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
                task.listener = Nephroxenia_Main_Activity.this;
                task.query = query;
                task.execute();




        }
        else {
            alertDialog.dismiss();
            buttonREF.clearAnimation();
            Toast.makeText(Nephroxenia_Main_Activity.this, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
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
                    pat.setAmka(currentPatient.getString("amka"));
                    pat.setPatientID(currentPatient.getInt("PatientID"));
                    pat.setDatein(currentPatient.getString("datein"));
                    pat.setVardiaID(currentPatient.getInt("MTNWatchID"));
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
                        patientsArrayList.get(0).getLastName());

                if (patientsArrayList.get(0).getIsEmergency().equals("true") || patientsArrayList.get(0).getIsEmergency().equals("1"))
                    search_TV.setTextColor(Color.RED);
                else
                    search_TV.setTextColor(Color.BLACK);


            } else {
                search_TV.setText(NO_PATIENT_FOUND);
                namesOfPatients.add(NO_PATIENT_FOUND);
            }

            buttonREF.clearAnimation();

            // --  KALESMA TOU DEFAULT FRAGMENT
            getDefaultFragment();

        }
    }








    public void clearStack() {
        //Here we are clearing back stack fragment entries
        int backStackEntry = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) {
                getSupportFragmentManager().popBackStackImmediate();
            }
        }

        //Here we are removing all the fragment that are shown here
        if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().size() > 0) {
            for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
                Fragment mFragment = getSupportFragmentManager().getFragments().get(i);
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
                }
            }
        }
    }







    private void setAlertDialog(){

        final Context mContext = this;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_dialog,
                (ViewGroup) findViewById(R.id.custom_layout_dialog));

        loading = layout.findViewById(R.id.rotateloading);
        loading.start();

        builder = new AlertDialog.Builder(mContext);
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);

    }





    @Override
    public void update(String str) {
        //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }



}
