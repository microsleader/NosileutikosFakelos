package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities.Nephroxenia_Main_Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors.DialogFragmentKataxorisiVelonon;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.TextviewWithAlertDialogSpinner;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static micros_leader.george.nosileutikosfakelos.Utils.convertObjToString;
import static micros_leader.george.nosileutikosfakelos.Utils.setChecked;

/**
 * A simple {@link Fragment} subclass.
 */

//        Διαδικασία κλάσσης:
//
//    1)    ΠΑΙΡΝΕΙ ΤΟ TRANSGROUP_ID
//    2)   ΚΑΝΕΙ ΙΝΙΤΙΑΛΙΖΕ ΤΑ ΕΛΕΜΕΝΤΣ
//    3)   ΠΑΙΡΝΕΙ ΑΠΟ ΤΗΝ ΒΑΣΗ ΤΟΥΣ ΝΟΣΙΛΕΥΤΕΣ
//    4)   ΠΕΡΝΑΕΙ ΠΛΗΡΟΦΟΡΙΕΣ ΣΤΑ ΣΠΙΝΝΕΡΣ ΜΕΣΑ ΑΠΟ ΤΟΥΣ ΑΝΤΑΠΤΟΡΕΣ
//    5)   ΒΛΕΠΕΙ ΑΝ ΥΠΑΡΧΕΙ ΗΔΗ ΕΓΓΡΑΦΗ ΜΕ ΤΟ TRANSGROUP_ID ΓΙΑ ΝΑ ΞΕΡΕΙ ΑΝ ΘΑ ΚΑΝΕΙ ΕΓΓΡΑΦΗ Η ΕΝΗΜΕΡΩΣΗ ΣΤΟΝ ΠΙΝΑΚΑ Nursing_Hemodialysis_initial (ΑΝ ΥΠΑΡΧΕΙ ΚΑΝΕΙ ΕΝΜΕΡΩΣΗ ΔΙΑΦΟΡΕΤΙΚΑ ΕΓΓΡΑΦΗ )
//    6)   ΚΑΘΕ ΦΟΡΑ ΠΟΥ ΠΑΤΑΜΕ ΤΟ ΚΟΥΜΠΙ ΠΑΙΡΝΕΙ ΟΛΕΣ ΤΙΣ ΠΛΗΡΟΦΟΡΙΕΣ ΠΟΥ ΕΧΟΥΝ ΕΙΣΑΧΘΕΙ ΤΙς ΕΠΕΞΕΡΓΑΖΕΙ ΑΠΟ ΚΑΠΟΙΕΣ ΜΕΘΟΔΟΥΣ ΚΑΙ ΤΙΣ ΔΙΝΕΙ ΣΤΟ QUERY
//    7)   ΚΑΛΟΥΜΕ ΑΣΥΝΚ ΤΑΣΚ ΟΤΑΝ ΕΧΟΥΜΕ QUERY ΕΙΤΕ ΓΙΑ ΕΙΣΑΓΩΓΗ , ΕΝΗΜΕΡΩΣΗ, Η ΤΡΑΒΗΓΜΑ ΠΛΗΡΟΦΟΡΙΩΝ
//    8)   ΤΟ ΚΟΥΜΠΙ ΚΑΝΕΙ ΤΟ ΑΝΑΛΟΓΟ ΑΝΙΜΑΤΙΟΝ ΑΝΑΛΟΓΑ ΤΟ ΑΠΟΤΕΛΕΣΜΑ


public class StableMeasurementsFragment extends Fragment  {



    private TextView olddateTV,dateTV, oldschTimestartTV,schTimestartTV,oldtimeStartTV, timeStartTV, olddurationTV,
               durationTV,durationMetrisisTV,dialimaTV,filtroTV, fistulaTV ,mosxeumaTV;

    private TextView
            oldarxikoVarosTV, oldksiroVarosTV, oldUFTV,  oldvarosEksodouTV,
             oldMax_UF_TV, oldthermokDialimatosTV, oldktvTV, oldfistulaTV ,oldmosxeumaTV, oldiatrosVardiasTV,
             old_flev_textTV, oldskelonATV, oldskelonFTV ,olddialimaTV,oldfiltroTV,oldlot_filtrouTV, oldSindetikoTV,
             oldiparEfapaxTV ,theraponIatrosTV;

    private EditText  arxikoVarosET, ksiroVarosET, UFET,  varosEksodouET,
             Max_UF_ET, thermokDialimatosET ,ktvET,
            flev_textET, skelonAET, skelonFET ,lot_filtrouET, sindetikoET,iparEfapaxET;

    private Spinner iatrosVardiasSP;
    private CheckBox oldvelona15CH,oldvelona16CH,oldvelona17CH, velona15CH,velona16CH,velona17CH,oldonlinePFCH,onlinePFCH , oldonlineMFCH,onlineMFCH,
                     oldonlineHDCH,onlineHDCH,oldflevMonimosCH,flevMonimosCH,oldflevProsorinosCH,flevProsorinosCH;

    private String  transgroupID ,date, durationMetrisis, diaforaVarous, schTimestart,timeStart,  arxikoVaros, ksiroVaros, UF,  varosEksodou,
            duration,  aimatokritis, iatrosVardiasID,   thermokDialimatos,
            ktv , iparEfapax,velona15,velona16,velona17, sindetiko, onlineMF,onlinePF,onlineHD,flev_text,flevMonimos,flevProsorinos,
    fistula,mosxeuma,skelonA,skelonF,dialima,filtro,lot_filtrou,ipar_filtrou;


    public String id , patientid , companyID ;

    private Activity act;
    private CircularProgressButton updateButton;
    private ArrayAdapter<String> dataAdapter , dataAdapter2, dataAdapter3;
    private Button velonesBT;
    private View view;
    private DrawerLayout sirtari;
    private ArrayList<String> nameJson, valuesJson;

    private ArrayList<String> doctorsLista =  new ArrayList<>();
    private Map <String,Integer> doctorsHashMap =  new HashMap<>();
    private String currentSpinnerPatient , code;

    private Context CTX;
    private Nephroxenia_Main_Activity main;
    private Calendar myCalendar;


    public StableMeasurementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_stable_med, container, false);

        act = getActivity();
        CTX = view.getContext();  //KALITERA AUTO NA XRISIMOPOIETAI STA ASYNCTASK

        myCalendar = Calendar.getInstance();
        initializeElements();
        Bundle bundle = this.getArguments();
        transgroupID = bundle.getString("transgroupID");
        patientid = bundle.getString("patientID");
        companyID = bundle.getString("companyID");

        velonesListener();
        updateButtonListener();

        getDoctors();


        main = (Nephroxenia_Main_Activity)getActivity();

        main.alertDialog.show();
        currentSpinnerPatient = main.search_TV.getText().toString();

        drawerListener();

        timefinishTVListener(schTimestartTV);
        timefinishTVListener(timeStartTV);
        timefinishTVListener(durationTV);



        return view;
    }


    public void velonesListener(){

        velonesBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id == null || id.equals(""))
                    Toast.makeText(CTX, "Δεν έχει γίνει ακόμα κάποια καταχώρηση μετρήσεων", Toast.LENGTH_SHORT).show();
                else {

                    DialogFragmentKataxorisiVelonon df = new DialogFragmentKataxorisiVelonon();
                    Bundle putextra = new Bundle();
                    putextra.putString("id", id);
                    df.setArguments(putextra);
                    df.setTargetFragment(StableMeasurementsFragment.this,1337);
                    df.show(getFragmentManager(),"dialog");
                   // df.show(( act).getSupportFragmentManager(), "Dialog");
                }

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        String newSpinnerItem = main.search_TV.getText().toString();
        if (!currentSpinnerPatient.equals(newSpinnerItem)) {
            id = null;
            code = Utils.getfirstPartSplitCommaString(newSpinnerItem);
            transgroupID =  main.getTransgroupIDUsingCode.get(code);
            patientid = main.getPatientIDUsingCode.get(code);
            clearTexts();
            getCurrentStableMeasurement();
            getTeleutaiesMetriseis();

        }
        else{
            code = Utils.getfirstPartSplitCommaString(currentSpinnerPatient);
            transgroupID =  main.getTransgroupIDUsingCode.get(code);
            patientid = main.getPatientIDUsingCode.get(code);
            main.alertDialog.dismiss();
            getCurrentStableMeasurement();
            getTeleutaiesMetriseis();

        }

    }


    private void drawerListener() {
        final Nephroxenia_Main_Activity main = (Nephroxenia_Main_Activity)act;
        main.drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        main.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(main.getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

//                String newSpinnerItem = main.search_TV.getText().toString();
//                if (!currentSpinnerPatient.equals(newSpinnerItem)) {
//                    id = null;
//                    code = Utils.getfirstPartSplitCommaString(newSpinnerItem);
//                    transgroupID =  main.getTransgroupIDUsingCode.get(code);
//                    patientid = main.getPatientIDUsingCode.get(code);
//                    clearTexts();
//                    getCurrentStableMeasurement();
//                    getTeleutaiesMetriseis();
//
//                }
//                else{
//                    code = Utils.getfirstPartSplitCommaString(currentSpinnerPatient);
//                    transgroupID =  main.getTransgroupIDUsingCode.get(code);
//                    patientid = main.getPatientIDUsingCode.get(code);
//                    main.alertDialog.dismiss();
//                    getCurrentStableMeasurement();
//                    getTeleutaiesMetriseis();
//
//                }




                //  Toast.makeText(main, newSpinnerItem, Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }




    private void initializeElements() {

        velonesBT = view.findViewById(R.id.kataxorisiVelononBT);

        // ------------- old -------------------------

        olddateTV = view.findViewById(R.id.olddateTV);
        oldschTimestartTV = view.findViewById(R.id.oldschtimeStartTV);
        oldtimeStartTV  = view.findViewById(R.id.oldtimeStartTV);
        olddurationTV = view.findViewById(R.id.olddurationTV);
        oldarxikoVarosTV = view.findViewById(R.id.oldstartWeightTV);
        oldksiroVarosTV = view.findViewById(R.id.oldksiroWeightTV);
        oldUFTV = view.findViewById(R.id.oldufTV);
        oldvarosEksodouTV = view.findViewById(R.id.oldexitWeightTV);
       // oldMax_UF_TV = view.findViewById(R.id.oldmaxUFTV);
        oldthermokDialimatosTV = view.findViewById(R.id.oldThermDialimTV);
        oldktvTV = view.findViewById(R.id.oldktvTV);
        oldfistulaTV = view.findViewById(R.id.oldfistulaTV);
        oldmosxeumaTV = view.findViewById(R.id.oldmosxeumaTV);
        oldiatrosVardiasTV = view.findViewById(R.id.oldiatrosVardiasTV);
        old_flev_textTV = view.findViewById(R.id.oldflevKeimenoTV);
        oldskelonATV = view.findViewById(R.id.oldskelonATV);
        oldskelonFTV = view.findViewById(R.id.oldskelonFTV);
        olddialimaTV = view.findViewById(R.id.olddialimaTV);
        oldfiltroTV = view.findViewById(R.id.oldfiltroTV);
        oldlot_filtrouTV = view.findViewById(R.id.oldlotfiltrouTV);
     //   oldipar_filtrouTV = view.findViewById(R.id.oldiparFiltrouTV);
        oldiparEfapaxTV = view.findViewById(R.id.oldiparefapaxTV);
        oldSindetikoTV = view.findViewById(R.id.oldsindetikoTV);

        oldvelona15CH = view.findViewById(R.id.oldvelona15CH);
        oldvelona15CH.setClickable(false);
        oldvelona16CH = view.findViewById(R.id.oldvelona16CH);
        oldvelona16CH.setClickable(false);
        oldvelona17CH = view.findViewById(R.id.oldvelona17CH);
        oldvelona17CH.setClickable(false);
        oldonlineMFCH = view.findViewById(R.id.oldonlineMFCH);
        oldonlineMFCH.setClickable(false);
        oldonlinePFCH = view.findViewById(R.id.oldonlinePFCH);
        oldonlinePFCH.setClickable(false);
        oldonlineHDCH = view.findViewById(R.id.oldonlineHDCH);
        oldonlineHDCH.setClickable(false);
        oldflevMonimosCH  = view.findViewById(R.id.oldflevMonimosCH);
        oldflevMonimosCH.setClickable(false);
        oldflevProsorinosCH = view.findViewById(R.id.oldflevProsorinosCH);
        oldflevProsorinosCH.setClickable(false);

        // velona15CH = view.findViewById(R.id.oldflevoNosTV);
        // velona16CH,velona17CH,oldonlinePFCH,onlinePFCH , oldonlineMFCH,onlineMFCH,

        theraponIatrosTV = view.findViewById(R.id.theraponIatrosTV);

        // ------------- new -------------------------
        dateTV = view.findViewById(R.id.dateTV);
        durationMetrisisTV = view.findViewById(R.id.durationTV);
        schTimestartTV = view.findViewById(R.id.schtimeStartTV);
        timeStartTV  = view.findViewById(R.id.timeStartTV);
        durationMetrisisTV = view.findViewById(R.id.durationTV);

        arxikoVarosET = view.findViewById(R.id.startWeightET);
        ksiroVarosET = view.findViewById(R.id.ksiroWeightET);
        UFET = view.findViewById(R.id.ufET);
        varosEksodouET = view.findViewById(R.id.exitWeightET);
        durationTV = view.findViewById(R.id.durationTV);
        thermokDialimatosET = view.findViewById(R.id.ThermDialimET);
        ktvET = view.findViewById(R.id.ktvET);
        fistulaTV = view.findViewById(R.id.fistulaTV);
        fistulaTV.setOnClickListener(new TextviewWithAlertDialogSpinner(act,fistulaTV,"Επιλογές", InfoSpecificLists.getFistoulaOrMosxeumaListaArray()));
        mosxeumaTV = view.findViewById(R.id.mosxeumaTV);
        mosxeumaTV.setOnClickListener(new TextviewWithAlertDialogSpinner(act,fistulaTV,"Επιλογές", InfoSpecificLists.getFistoulaOrMosxeumaListaArray()));

        iatrosVardiasSP = view.findViewById(R.id.iatrosVardiasSP);
        flev_textET = view.findViewById(R.id.flevKeimenoET);
        skelonAET = view.findViewById(R.id.skelonAET);
        skelonFET = view.findViewById(R.id.skelonFET);
        dialimaTV = view.findViewById(R.id.dialimaTV);
        dialimaTV.setOnClickListener(new TextviewWithAlertDialogSpinner(act,dialimaTV,"Επιλογές", InfoSpecificLists.getDialimaListaArray()));
        filtroTV = view.findViewById(R.id.filtroTV);
        filtroTV.setOnClickListener(new TextviewWithAlertDialogSpinner(act,filtroTV,"Επιλογές", InfoSpecificLists.getFiltroArray()));
        lot_filtrouET = view.findViewById(R.id.lotfiltrouET);
      //  ipar_filtrouET = view.findViewById(R.id.iparFiltrouET);
        iparEfapaxET = view.findViewById(R.id.iparefapaxT);

        sindetikoET = view.findViewById(R.id.sindetikoET);


        velona15CH = view.findViewById(R.id.velona15CH);
        velona16CH = view.findViewById(R.id.velona16CH);
        velona17CH = view.findViewById(R.id.velona17CH);
        onlineMFCH = view.findViewById(R.id.onlineMFCH);
        onlinePFCH = view.findViewById(R.id.onlinePFCH);
        onlineHDCH = view.findViewById(R.id.onlineHDCH);
        flevMonimosCH  = view.findViewById(R.id.flevMonimosCH);
        flevProsorinosCH = view.findViewById(R.id.flevProsorinosCH);

        updateButton = view.findViewById(R.id.updateButton);


    }


    public void getCurrentStableMeasurement() {

        if (Utils.isNetworkAvailable(CTX)) {

                String query =  Str_queries.getCURRENT_METRISEIS_MAZI_ME_MEDICAL_INSTRUCTIONS(transgroupID,patientid,Utils.getCustomerID(getContext()));



            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = CTX;
            task.query = query;
            task.listener = new AsyncCompleteTask() {
                @Override
                public void taskComplete(JSONArray results) throws JSONException {

                    if (results != null) {
                        JSONObject metrisi = results.getJSONObject(0); // ΠΙΘΑΝΟ ΝΑ ΕΜΦΑΝΙΣΤΟΥΝ ΠΟΛΛΕΣ ΕΓΓΡΑΦΕΣ ΛΟΓΩ ΠΟΛΛΑΠΛΩΝ
                                                                            // ΕΝΗΜΕΡΩΣΕΝ ΟΠΟΤΕ ΕΜΑΙΣ ΑΠΙΡΝΟΥΜΕ ΜΟΝΟ ΤΗΝ ΤΕΛΕΥΤΑΙΑ

                        if (!metrisi.has("status")) { // TO STATUS EINAI OTAN DEN IPARXOUN STOIXEIA

                            dateTV.setText(convertObjToString(metrisi.get("datestr")));
                            id = String.valueOf(metrisi.getInt("ID"));
                            timeStartTV.setText(metrisi.getString("timeS"));
                            theraponIatrosTV.setText("Θεράπων ιατρός: \n " + convertObjToString(metrisi.get("therapon_iatros")));
                            schTimestartTV.setText(convertObjToString(metrisi.get("schtimeStart")));
                            durationTV.setText(convertObjToString(metrisi.get("dur")));
                            arxikoVarosET.setText(convertObjToString(metrisi.get("arxiko_varos")));
                            ksiroVarosET.setText(convertObjToString(metrisi.get("ksiro_varos")));
                            UFET.setText(convertObjToString(metrisi.get("UF")));

                            varosEksodouET.setText(convertObjToString(metrisi.get("varos_exodou")));
                            thermokDialimatosET.setText(convertObjToString(metrisi.get("therm_dialimatos")));
                            ktvET.setText(convertObjToString(metrisi.get("Kt_V")));
                            fistulaTV.setText(convertObjToString(metrisi.get("fistula")));
                            mosxeumaTV.setText(convertObjToString(metrisi.get("mosxeuma")));
                            String doc = convertObjToString(metrisi.get("docName"));
                            if (doc !=null && !doc.equals("")) {
                                int spinnerPosition = dataAdapter.getPosition(doc);
                                iatrosVardiasSP.setSelection(spinnerPosition);
                            }else
                                iatrosVardiasSP.setSelection(0);


                            flev_textET.setText(convertObjToString(metrisi.get("flev_kathtiras_text")));
                            skelonAET.setText(convertObjToString(metrisi.get("skelon_A")));
                            skelonFET.setText(convertObjToString(metrisi.get("skelon_f")));
                            dialimaTV.setText(convertObjToString(metrisi.get("dialimaaa")));
                            filtroTV.setText(convertObjToString(metrisi.get("Filter")));
                            lot_filtrouET.setText(convertObjToString(metrisi.get("lot_filtrou")));
                        //    ipar_filtrouET.setText(convertObjToString(metrisi.get("ipar_filtrou")));
                            iparEfapaxET.setText(convertObjToString(metrisi.get("ipar_efapax")));

                            velona15CH.setChecked(setChecked(metrisi.get("velona15")));
                            velona16CH.setChecked(setChecked(metrisi.get("velona16")));
                            velona17CH.setChecked(setChecked(metrisi.get("velona17")));

                            sindetikoET.setText(convertObjToString(metrisi.get("sindetiko")));

                            String online = convertObjToString(metrisi.get("Online"));
                            if (online.equals("1")) {
                                onlinePFCH.setSelected(true);
                                onlineMFCH.setSelected(true);
                                onlineHDCH.setSelected(false);
                                onlineHDCH.setChecked(false);


                            }
                            else{
                                onlinePFCH.setSelected(false);
                                onlineMFCH.setSelected(false);
                                onlineHDCH.setSelected(true);
                                onlineHDCH.setChecked(true);


                            }

                            onlinePFCH.setChecked(setChecked(metrisi.get("online_pf")));
                            onlineMFCH.setChecked(setChecked(metrisi.get("online_mf")));
                            onlineHDCH.setChecked(setChecked(metrisi.get("online_hd")));

                            flevMonimosCH.setChecked(setChecked(metrisi.get("flev_kathtiras_monimos")));
                            flevProsorinosCH.setChecked(setChecked(metrisi.get("flev_kathtiras_prosorinos")));


                        } else {
                            id = null;
                            clearTexts();
                        }
                    }
                    else{
                        id = null;
                        clearTexts();
                    }

                    main.alertDialog.dismiss();



                }



                @Override
                public void taskCompleteGetVardies(JSONArray results) throws JSONException {

                }
            };
                task.execute();

        } else{
           // Toast.makeText(main, R.string.check_internet_access, Toast.LENGTH_SHORT).show();

        }
    }





    private void updateButtonListener(){

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (transgroupID != null && !transgroupID.equals("0") ) {

                InputMethodManager imm = (InputMethodManager) CTX.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(), 0);

            //    clearAllFocus();


                    if (Utils.isNetworkAvailable2(CTX)) {

                        nameJson = new ArrayList<>();
                        valuesJson = new ArrayList<>();
                        getSetTexts();

                        updateButton.startAnimation();


                        AsyncTaskUpdate_JSON task;


                        if (id == null || id.equals(""))
                            task = new AsyncTaskUpdate_JSON(CTX, transgroupID, "Nursing_Hemodialysis_initial2_MEDIT",
                                    nameJson, Utils.replaceTrueOrFalse(valuesJson), null);
                        else
                            task = new AsyncTaskUpdate_JSON(CTX, id, transgroupID, "Nursing_Hemodialysis_initial2_MEDIT",
                                    nameJson, Utils.replaceTrueOrFalse(valuesJson), null);

                        task.listener = new AsyncGetUpdate_JSON() {
                            @Override
                            public void update_JSON(String str) {
                                if (str.equals(getString(R.string.successful_update))) {

                                    Utils.timeHandlerDoneButton(updateButton, CTX);
                                    getTeleutaiesMetriseis();
                                    getCurrentStableMeasurement();  // ΤΟ ΞΑΝΑΤΡΕΧΩ ΕΠΕΙΔΗ ΔΕΝ ΕΧΩ ΤΟ ID

                                } else {
                                    Utils.timeHandlerErrorButton(updateButton, CTX);
                                    Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void getIDofInsert(String id) {

                            }
                        };
                        task.execute();

                    }

                    }

                else{
                    updateButton.revertAnimation();
                    Toast.makeText(CTX, "Δεν μπορείτε να κάνετε ενημέρωση χωρίς ασθενή", Toast.LENGTH_SHORT).show();

                }

                //}
            }
        });
    }

    private void getSetTexts() {


    if (dateTV.getText().toString().equals(""))  //ΓΙΑ ΝΑ ΠΑΙΡΝΕΙ ΤΗΝ ΗΜΕΡΟΜΗΝΙΑ ΜΟΝΟ ΣΤΟ ΙΝΣΕΡΤ ΚΑΙ ΟΧΙ ΣΤΗΝ ΕΝΗΜΕΡΩΣΗ
        dateTV.setText(Utils.getCurrentDate());

        date = dateTV.getText().toString();


        durationMetrisis = durationMetrisisTV.getText().toString();
        // ME AYTI TI SEIRA GIA NA GINEI PROTA I DIAFORA KAI META OI TIMES NA GINOUN LONG GIA NA PANE STI VASI SE SOSTI MORFI
    if (timeStartTV.getText().toString().equals(""))      //ΓΙΑ ΝΑ ΠΑΙΡΝΕΙ ΤΗΝ ΩΡΑ ΜΟΝΟ ΣΤΟ ΙΝΣΕΡΤ ΚΑΙ ΟΧΙ ΣΤΗΝ ΕΝΗΜΕΡΩΣΗ
        timeStartTV.setText(Utils.getCurrentTime2());
        timeStart = Utils.checkNull(timeStartTV.getText().toString());

        if (schTimestartTV.getText().toString().equals(""))
            schTimestartTV.setText(Utils.getCurrentTime2());
        schTimestart= Utils.checkNull(schTimestartTV.getText().toString());

        durationMetrisis =  durationMetrisisTV.getText().toString();
       // timeStart = timeStart == null ? null : Utils.convertTime(timeStart);
     //   timeFinish = timeFinish == null ? null : Utils.convertTime(timeFinish);


//-------------------------------------------------------------------------------------------------
        arxikoVaros = Utils.checkNull( arxikoVarosET.getText().toString());
        ksiroVaros = Utils.checkNull(ksiroVarosET.getText().toString());
        varosEksodou = Utils.checkNull(varosEksodouET.getText().toString());
        duration = Utils.checkNull(durationTV.getText().toString());
        // ---------------------- SPINNER VALUES -----------------------------
        iatrosVardiasID = String.valueOf(doctorsHashMap.get(iatrosVardiasSP.getSelectedItem().toString()));


        // ---------------------------------------------------------------------------------------

        durationTV.setText(durationTV.getText().toString());
        duration = durationTV.getText().toString();

        UF =  Utils.checkNull(UFET.getText().toString());
        thermokDialimatos = Utils.checkNull(thermokDialimatosET.getText().toString());
        ktv = Utils.checkNull(ktvET.getText().toString());
        fistula = Utils.checkNull(fistulaTV.getText().toString());
        mosxeuma = Utils.checkNull(mosxeumaTV.getText().toString());
        flev_text = Utils.checkNull(flev_textET.getText().toString());
        skelonA = Utils.checkNull(skelonAET.getText().toString());
        skelonF = Utils.checkNull(skelonFET.getText().toString());
        dialima = Utils.checkNull(dialimaTV.getText().toString());
        filtro = Utils.checkNull(filtroTV.getText().toString());
        lot_filtrou = Utils.checkNull(lot_filtrouET.getText().toString());
      //  ipar_filtrou = Utils.checkNull(ipar_filtrouET.getText().toString());
        iparEfapax = Utils.checkNull(iparEfapaxET.getText().toString());

        sindetiko = Utils.checkNull(sindetikoET.getText().toString());

        velona15 = Utils.getChecked(velona15CH.isChecked());
        velona16 = Utils.getChecked(velona16CH.isChecked());
        velona17 = Utils.getChecked(velona17CH.isChecked());
        onlineMF = Utils.getChecked(onlineMFCH.isChecked());
        onlinePF = Utils.getChecked(onlinePFCH.isChecked());
        onlineHD = Utils.getChecked(onlineHDCH.isChecked());
        flevMonimos = Utils.getChecked(flevMonimosCH.isChecked());
        flevProsorinos =  Utils.getChecked(flevProsorinosCH.isChecked());





        nameJson.add("schedule_time_start");
        nameJson.add("time_start");nameJson.add("duration");nameJson.add("arxiko_varos");
        nameJson.add("xiro_varos");nameJson.add("UF");nameJson.add("varos_exodou");
        nameJson.add("therm_dialimatos");nameJson.add("Kt_V");nameJson.add("ipefthinos_iatros_vardias");
        nameJson.add("fistula");nameJson.add("mosxeuma");nameJson.add("velona15");
        nameJson.add("velona16");nameJson.add("velona17"); nameJson.add("sindetiko");
        nameJson.add("online_pf");
        nameJson.add("online_mf");nameJson.add("online_hd");nameJson.add("flev_kathtiras_monimos");
        nameJson.add("flev_kathtiras_prosorinos");nameJson.add("flev_kathtiras_text"); nameJson.add("skelon_A");
        nameJson.add("skelon_f"); nameJson.add("dialima");nameJson.add("filtro");
        nameJson.add("lot_filtrou");nameJson.add("ipar_filtrou");nameJson.add("ipar_efapax");


        valuesJson.add(String.valueOf(Utils.convertTime(schTimestart)));
        valuesJson.add(String.valueOf(Utils.convertTime(timeStart))); valuesJson.add(String.valueOf(Utils.convertTime(duration))); valuesJson.add(arxikoVaros);
        valuesJson.add(ksiroVaros); valuesJson.add(UF); valuesJson.add(varosEksodou);
        valuesJson.add(thermokDialimatos); valuesJson.add(ktv); valuesJson.add(String.valueOf(doctorsHashMap.get(iatrosVardiasSP.getSelectedItem().toString())));
        valuesJson.add(fistula); valuesJson.add(mosxeuma); valuesJson.add(velona15);
        valuesJson.add(velona16); valuesJson.add(velona17);
        valuesJson.add(sindetiko);
        valuesJson.add(onlinePF);
        valuesJson.add(onlineMF); valuesJson.add(onlineHD); valuesJson.add(flevMonimos);
        valuesJson.add(flevProsorinos); valuesJson.add(flev_text); valuesJson.add(skelonA);
        valuesJson.add(skelonF); valuesJson.add(dialima); valuesJson.add(filtro);
        valuesJson.add(lot_filtrou);valuesJson.add(ipar_filtrou); valuesJson.add(iparEfapax);

    }




    private void clearTexts(){

        dateTV.setText("");
       // durationMetrisisTV.setText("");
        schTimestartTV.setText("");
        timeStartTV .setText("");

        arxikoVarosET.setText("");
        ksiroVarosET.setText("");
        UFET.setText("");
        varosEksodouET.setText("");
        durationTV.setText("");
       // Max_UF_ET.setText("");
        thermokDialimatosET.setText("");
        ktvET.setText("");
        fistulaTV.setText("");
        mosxeumaTV.setText("");
        iatrosVardiasSP.setSelection(0);
        flev_textET.setText("");
        skelonAET.setText("");
        skelonFET.setText("");
        dialimaTV.setText("");
        filtroTV.setText("");
        lot_filtrouET.setText("");
       // ipar_filtrouET.setText("");
        iparEfapaxET.setText("");

        theraponIatrosTV.setText("");

        velona15CH.setChecked(false);
        velona16CH.setChecked(false);
        velona17CH.setChecked(false);
        onlineMFCH.setChecked(false);
        onlinePFCH.setChecked(false);
        onlineHDCH.setChecked(false);;
        flevMonimosCH.setChecked(false);
        flevProsorinosCH.setChecked(false);


    }


    private void clearOldTexts(){

        olddateTV.setText("");
        oldschTimestartTV.setText("");
        oldtimeStartTV.setText("");
        olddurationTV.setText("");
        oldarxikoVarosTV.setText("");
        oldksiroVarosTV.setText("");
        oldUFTV.setText("");

        oldvarosEksodouTV.setText("");

        oldthermokDialimatosTV.setText("");
        oldktvTV.setText("");
        oldfistulaTV.setText("");
        oldmosxeumaTV.setText("");
        oldiatrosVardiasTV.setText("");

        old_flev_textTV.setText("");
        oldskelonATV.setText("");
        oldskelonFTV.setText("");
        olddialimaTV.setText("");
        oldfiltroTV.setText("");
        oldlot_filtrouTV.setText("");
       // oldipar_filtrouTV.setText("");
        oldiparEfapaxTV.setText("");
        oldSindetikoTV.setText("");

        oldvelona15CH.setChecked(false);
        oldvelona16CH.setChecked(false);
        oldvelona17CH.setChecked(false);
        oldonlinePFCH.setChecked(false);
        oldonlineMFCH.setChecked(false);
        oldonlineHDCH.setChecked(false);
        oldflevMonimosCH.setChecked(false);
        oldflevProsorinosCH.setChecked(false);

    }






//    private void clearAllFocus(){
//        arxikoVarosET.clearFocus();
//        arxikoVarosET.clearFocus(); ksiroVarosET.clearFocus(); UF_arxikoET.clearFocus(); UF_telikoET.clearFocus(); varosEksodouET.clearFocus();
//        durationTV.clearFocus(); Max_UF_ET.clearFocus(); roiAimatosET.clearFocus(); roiDialimatosET.clearFocus();
//        roiIpokatastatouET.clearFocus();agogimotitaET.clearFocus(); thermokDialimatosET.clearFocus(); HCO3_ET.clearFocus();
//        ktvET.clearFocus(); piesiEksodouET.clearFocus();
//    }



    private void getDoctors(){

        if (Utils.isNetworkAvailable(CTX)) {


            doctorsLista.clear();
            doctorsHashMap.clear();

            String query = Str_queries.getNefrologous(companyID);

            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = CTX.getApplicationContext();
            task.query = query;
            task.listener = new AsyncCompleteTask() {
                @Override
                public void taskComplete(JSONArray results) throws JSONException {

                    if (results != null) {
                        doctorsLista.add("");
                        doctorsHashMap.put("", 0);


                        for (int i = 0; i < results.length(); i++) {

                            JSONObject doctors = results.getJSONObject(i);
                            int id = doctors.getInt("id");
                            String name = doctors.getString("Name");


                            doctorsHashMap.put(name, id);
                            doctorsLista.add(name);

                        }

                        addSpinnerAdaptors();

                        //  getCurrentStableMeasurement();  //  PROTA NA FORTOSOUN OI ADAPTORES KAI META NA TREKSEI DIAFORETIKA EXEI ERROR

                    }

                }

                @Override
                public void taskCompleteGetVardies(JSONArray results) throws JSONException {

                }
            };
            task.execute();




        }
        else {
            Toast.makeText(CTX, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
        }
    }






    public void getTeleutaiesMetriseis() {

        if (Utils.isNetworkAvailable(CTX)) {

            String query = "select top 1 n.* , dbo.namedoctor(n.ipefthinos_iatros_vardias) as docName, dbo.timeTostr(n.duration) as Duration," +
                    " dbo.timeToStr(n.schedule_time_start) as schtimeStart, " +
                    " dbo.timeToStr(n.time_start) as timeStart, " +
                    " dbo.timeToStr(n.duration) as dur, " +
                    " dbo.dateToStr(n.date) as datestr " +
                   // " m.Filter, m.online ,m.dialima as dial, m.ksiro_varos " +

                    " from v_Nursing_Hemodialysis_initial2_MEDIT n " +
                    //" left join Nursing_Medical_Instructions m on m.PatientID = n.PatientID " +
                    " left join doctor doc on doc.id = n.ipefthinos_iatros_vardias" +
                    " where n.patientid =  " + patientid +
                    " order by n.date desc";

            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = CTX;
            task.query = query;
            task.listener = new AsyncCompleteTask() {
                @Override
                public void taskComplete(JSONArray results) throws JSONException {

                    if (results != null){
                        if (!results.getJSONObject(0).has("status")) {

                            main.alertDialog.show();

                            JSONObject tel_metr = results.getJSONObject(0);

                            olddateTV.setText(convertObjToString(tel_metr.get("datestr")));
                            oldschTimestartTV.setText(convertObjToString(tel_metr.get("schtimeStart")));
                            oldtimeStartTV.setText(convertObjToString(tel_metr.get("timeStart")));
                            olddurationTV.setText(convertObjToString(tel_metr.get("dur")));
                            oldarxikoVarosTV.setText(convertObjToString(tel_metr.get("arxiko_varos")));
                            oldksiroVarosTV.setText(convertObjToString(tel_metr.get("xiro_varos")));
                            oldUFTV.setText(convertObjToString(tel_metr.get("UF")));

                            oldvarosEksodouTV.setText(convertObjToString(tel_metr.get("varos_exodou")));
                            oldthermokDialimatosTV.setText(convertObjToString(tel_metr.get("therm_dialimatos")));
                            oldktvTV.setText(convertObjToString(tel_metr.get("Kt_V")));
                            oldfistulaTV.setText(convertObjToString(tel_metr.get("fistula")));
                            oldmosxeumaTV.setText(convertObjToString(tel_metr.get("mosxeuma")));
                            oldiatrosVardiasTV.setText(convertObjToString(tel_metr.get("docName")));

                            old_flev_textTV.setText(convertObjToString(tel_metr.get("flev_kathtiras_text")));
                            oldskelonATV.setText(convertObjToString(tel_metr.get("skelon_A")));
                            oldskelonFTV.setText(convertObjToString(tel_metr.get("skelon_f")));
                            olddialimaTV.setText(convertObjToString(tel_metr.get("dialima")));
                            oldfiltroTV.setText(convertObjToString(tel_metr.get("filtro")));
                            oldlot_filtrouTV.setText(convertObjToString(tel_metr.get("lot_filtrou")));
                       //     oldipar_filtrouTV.setText(convertObjToString(tel_metr.get("ipar_filtrou")));
                       //     oldipar_filtrouTV.setText(convertObjToString(tel_metr.get("ipar_filtrou")));
                            oldiparEfapaxTV.setText(convertObjToString(tel_metr.get("ipar_efapax")));

                            String posotita ="";
                            oldvelona15CH.setChecked(setChecked(tel_metr.get("velona15")));
                            posotita = convertObjToString(tel_metr.get("posotita_vel15")).equals("") ? "0" : convertObjToString(tel_metr.get("posotita_vel15"));
                            oldvelona15CH.setText("βελόνα 15g (" + posotita + ")" );

                            oldvelona16CH.setChecked(setChecked(tel_metr.get("velona16")));
                            posotita = convertObjToString(tel_metr.get("posotita_vel16")).equals("") ? "0" : convertObjToString(tel_metr.get("posotita_vel16"));
                            oldvelona16CH.setText("βελόνα 16g (" + posotita + ")" );


                            oldvelona17CH.setChecked(setChecked(tel_metr.get("velona17")));
                            posotita = convertObjToString(tel_metr.get("posotita_vel17")).equals("") ? "0" : convertObjToString(tel_metr.get("posotita_vel17"));
                            oldvelona17CH.setText("βελόνα 17g (" + posotita + ")" );

                            oldSindetikoTV.setText(convertObjToString(tel_metr.get("sindetiko")));
                            oldonlinePFCH.setChecked(setChecked(tel_metr.get("online_pf")));
                            oldonlineMFCH.setChecked(setChecked(tel_metr.get("online_mf")));
                            oldonlineHDCH.setChecked(setChecked(tel_metr.get("online_hd")));
                            oldflevMonimosCH.setChecked(setChecked(tel_metr.get("flev_kathtiras_monimos")));
                            oldflevProsorinosCH.setChecked(setChecked(tel_metr.get("flev_kathtiras_prosorinos")));


                        }

                        else{
                            clearOldTexts();
                        }
                    }
                    main.alertDialog.dismiss();
                }

                @Override
                public void taskCompleteGetVardies(JSONArray results) throws JSONException {

                }
            };
            task.execute();
    }
        else {
        Toast.makeText(CTX, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
    }

    }



    private void timefinishTVListener(final TextView textView){
        final DatePickerDialog.OnDateSetListener dateDialog = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //updateLabel();
            }

        };

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CTX, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        textView.setText( Utils.chechZeroInTime(selectedHour + ":" + selectedMinute));
                    }
                }, hour, minute, true);
               // mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }




    private void addSpinnerAdaptors(){

        dataAdapter = new ArrayAdapter<>(CTX,
                R.layout.spinner_layout2, doctorsLista);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        iatrosVardiasSP.setAdapter(dataAdapter);


//        getCurrentStableMeasurement();
//
//        getTeleutaiesMetriseis();

    }




}

