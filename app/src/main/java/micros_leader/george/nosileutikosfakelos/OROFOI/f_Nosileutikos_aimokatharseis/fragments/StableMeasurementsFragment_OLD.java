package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
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
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities.Nephroxenia_Main_Activity;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

import static android.content.Context.INPUT_METHOD_SERVICE;

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


public class StableMeasurementsFragment_OLD extends Fragment  {



    private TextView dateTV, timeStartTV, timefinishTV, durationMetrisisTV, diaforaVarousTV ;



    private EditText  arxikoVarosET, ksiroVarosET, UF_arxikoET, UF_telikoET, varosEksodouET,
            durationET, Max_UF_ET,  aimosfairiniET, aimatokritisET , roiAimatosET, roiDialimatosET, roiIpokatastatouET, agogimotitaET, thermokDialimatosET, HCO3_ET,
            ktvET, piesiEksodouET, hemoscanET;

    private Spinner ipef_nosil_Sp, ipef_nosil_2_SP, nosil_FlevSP, aggProspelasiSP, katastasiFiltrouSP;

    private String  transgroupID ,date, durationMetrisis, diaforaVarous, timeStart, timeFinish, arxikoVaros, ksiroVaros, UF_arxiko, UF_teliko, varosEksodou,
            duration, Max_UF_, aimosfairini, aimatokritis, roiAimatos, roiDialimatos, roiIpokatastatou, agogimotita, thermokDialimatos, HCO3_,
            ktv, piesiEksodou, ipef_nosil, ipef_nosil_2, nosil_Flev, aggProspelasi, katastasiFiltrou, hemoscan;

    public String id , patientid , companyID ;

    private CircularProgressButton updateButton;
    private ArrayAdapter<String> dataAdapter , dataAdapter2, dataAdapter3;
    private Bitmap bitmap;
    private View view;
    private DrawerLayout sirtari;
    private ArrayList<String> nosileutesLista =  new ArrayList<>();
    private Map <String,Integer> nosileutesHashmap =  new HashMap<>();
    private String currentSpinnerPatient , code;
    private Context CTX;
    private Nephroxenia_Main_Activity main;
    private Calendar myCalendar;

    public StableMeasurementsFragment_OLD() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_stable_measurements, container, false);

        CTX = view.getContext();  //KALITERA AUTO NA XRISIMOPOIETAI STA ASYNCTASK

        myCalendar = Calendar.getInstance();
        initializeElements();
        Bundle bundle = this.getArguments();
        transgroupID = bundle.getString("transgroupID");
        patientid = bundle.getString("patientID");
        companyID = bundle.getString("companyID");
        updateButtonListener();

        getNosileutes();


        main = (Nephroxenia_Main_Activity)getActivity();

        main.alertDialog.show();
        currentSpinnerPatient = main.search_TV.getText().toString();

        drawerListener();

        timefinishTVListener();

        getCurrentStableMeasurement();

        getTeleutaiesMetriseis();

        return view;
    }




    private void drawerListener() {
        final Nephroxenia_Main_Activity main = (Nephroxenia_Main_Activity)getActivity();
        main.drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        main.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (main.getCurrentFocus() != null)
                  inputMethodManager.hideSoftInputFromWindow(main.getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

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




                //  Toast.makeText(main, newSpinnerItem, Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }




    private void initializeElements() {




        dateTV = view.findViewById(R.id.dateTV);
        durationMetrisisTV = view.findViewById(R.id.durationTV);
        diaforaVarousTV = view.findViewById(R.id.diffrenceWeightTV);
        timeStartTV  = view.findViewById(R.id.timeStartTV);
        timefinishTV = view.findViewById(R.id.timeFinishedTV);
        arxikoVarosET = view.findViewById(R.id.startWeightET);
        ksiroVarosET = view.findViewById(R.id.ksiroWeightET);
        UF_arxikoET = view.findViewById(R.id.ufStartET);
        UF_telikoET = view.findViewById(R.id.ufFinishedET);
        varosEksodouET = view.findViewById(R.id.exitWeightET);
        durationET = view.findViewById(R.id.duration2ET);
        Max_UF_ET = view.findViewById(R.id.maxUFET);
        aimosfairiniET = view.findViewById(R.id.aimosfairniET);
        aimatokritisET = view.findViewById(R.id.aimatokrtisET);
        roiAimatosET = view.findViewById(R.id.roiAimatosET);
        roiDialimatosET = view.findViewById(R.id.roiDialimatosET);
        roiIpokatastatouET = view.findViewById(R.id.roiIpokatastatouET);
        agogimotitaET = view.findViewById(R.id.agogimotitaET);
        thermokDialimatosET = view.findViewById(R.id.thermDialimatosET);
        HCO3_ET = view.findViewById(R.id.hco3ET);
        ktvET = view.findViewById(R.id.ktvET);
        piesiEksodouET = view.findViewById(R.id.piesiEksodosET);
        ipef_nosil_Sp = view.findViewById(R.id.ipeuthnosNosSP);
        ipef_nosil_2_SP = view.findViewById(R.id.secondNosSP);
        nosil_FlevSP = view.findViewById(R.id.flevoNosSP);
        aggProspelasiSP = view.findViewById(R.id.prospelasiSP);
        katastasiFiltrouSP = view.findViewById(R.id.katastasiFiltrouSP);
        hemoscanET = view.findViewById(R.id.hemoscanET);

        updateButton = view.findViewById(R.id.updateButton);


    }


    private void getCurrentStableMeasurement() {

        if (Utils.isNetworkAvailable(CTX)) {

            String query =
                    " select * , dbo.timeToStr(time_start) as timeS, dbo.timeToStr(time_finish) as timeF, dbo.timeToStr(diarkia) as diarkeia," +
                            " dbo.NAMEUSER(ipefthinos_nosileftis) as ipefNosil ,dbo.NAMEUSER(nosileftis_2) as ipefNosil2 , " +
                            " aimatokritis, aimosfairini,  dbo.NAMEUSER(nosileftis_flevokentisis) as nosilFlev , hemoscan" +
                            " from Nursing_Hemodialysis_initial where TransGroupid =  " + transgroupID;


            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = CTX;
            task.query = query;
            task.listener = new AsyncCompleteTask() {
                @Override
                public void taskComplete(JSONArray results) throws JSONException {

                    if (results != null) {
                        JSONObject metrisi = results.getJSONObject(0);
                        if (!metrisi.has("status")) { // TO STATUS EINAI OTAN DEN IPARXOUN STOIXEIA

                            dateTV.setText(Utils.getCurrentDate());
                            id = String.valueOf(metrisi.getInt("ID"));
                            timeStartTV.setText(metrisi.getString("timeS"));
                            timefinishTV.setText(metrisi.getString("timeF"));
                            durationMetrisisTV.setText(Utils.chechZeroInTime(Utils.timeDiffrence(timeStartTV.getText().toString(), timefinishTV.getText().toString())));

                            arxikoVarosET.setText(metrisi.getString("arxiko_varos"));
                            ksiroVarosET.setText(metrisi.getString("xiro_varos"));
                            diaforaVarousTV.setText(Utils.afairesiDouble(arxikoVarosET.getText().toString(), ksiroVarosET.getText().toString()));
                            UF_arxikoET.setText(metrisi.getString("Uf_arxiko"));
                            UF_telikoET.setText(metrisi.getString("Uf_teliko"));
                            varosEksodouET.setText(metrisi.getString("varos_exodou"));

                            //------------------------- SPINNER VALUES ----------------------------
                            String nameOfNurse = metrisi.getString("ipefNosil");
                            if (nameOfNurse == null) {
                                nameOfNurse = "";
                            }

                            String nameOfNurse2 = metrisi.getString("ipefNosil2");
                            if (nameOfNurse2 == null) {
                                nameOfNurse2 = "";

                            }
                            String nurseFlev = metrisi.getString("nosilFlev");
                            if (nurseFlev == null) {
                                nurseFlev = "";
                            }


                            int spinnerPosition = dataAdapter.getPosition(nameOfNurse);
                            int spinnerPosition2 = dataAdapter.getPosition(nameOfNurse2);
                            int spinnerPosition3 = dataAdapter.getPosition(nurseFlev);



                            ipef_nosil_Sp.setSelection(spinnerPosition);
                            ipef_nosil_2_SP.setSelection(spinnerPosition2);
                            nosil_FlevSP.setSelection(spinnerPosition3);
                            //-------------------------------------------------------------

                            aggProspelasi = metrisi.getString("agg_prospelasi");
                            if (aggProspelasi.equals("")) {
                                aggProspelasi = "0";
                            }
                            aggProspelasiSP.setSelection(Integer.parseInt(aggProspelasi));

                            katastasiFiltrou = metrisi.getString("katastasi_filtou");
                            if (katastasiFiltrou.equals("")) {
                                katastasiFiltrou = "0";
                            }
                            katastasiFiltrouSP.setSelection(Integer.parseInt(katastasiFiltrou));
                            //-------------------------------------------------------------------

                            durationET.setText(metrisi.getString("diarkeia"));
                            Max_UF_ET.setText(metrisi.getString("Max_UFh"));
                            aimosfairiniET.setText(metrisi.getString("aimosfairini"));
                            aimatokritisET.setText(metrisi.getString("aimatokritis"));
                            roiAimatosET.setText(metrisi.getString("roi_aimatos"));
                            roiDialimatosET.setText(metrisi.getString("roi_dialimatos"));
                            roiIpokatastatouET.setText(metrisi.getString("roi_ipokatastatou"));
                            agogimotitaET.setText(metrisi.getString("agogimotita"));
                            thermokDialimatosET.setText(metrisi.getString("thermokrasia_dialimatos"));
                            HCO3_ET.setText(metrisi.getString("hco3"));
                            ktvET.setText(metrisi.getString("Kt_V"));
                            piesiEksodouET.setText(metrisi.getString("piesi_exodou"));
                            hemoscanET.setText(Utils.convertObjToString(metrisi.get("hemoscan")));

                            main.alertDialog.dismiss();
                        } else
                            main.alertDialog.dismiss();

                    }





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

                InputMethodManager imm = (InputMethodManager) CTX.getSystemService(INPUT_METHOD_SERVICE);
                if (main.getCurrentFocus() != null)
                 imm.hideSoftInputFromWindow(main.getCurrentFocus().getWindowToken(), 0);

                clearAllFocus();


//                if (durationMetrisis.equals("")){  //SIMAINEI POS I ORA LIKSIS EINAI MIKROTERO APO TIN ORA ENARKSIS
//                    Toast.makeText(CTX, "Ελέγχτε τις ώρες", Toast.LENGTH_SHORT).show();
//                    durationMetrisisTV.setText("");
//                }
//                else {

                if (Utils.isNetworkAvailable(CTX)) {

                    getSetTexts();

                    updateButton.startAnimation();

                    if (id == null)

                        insertInfo();
                    else
                        updateInfo();





                } else
                    Toast.makeText(CTX, R.string.check_internet_access, Toast.LENGTH_SHORT).show();

                //}
            }
        });
    }



    private void insertInfo(){
        if (transgroupID!=null && !transgroupID.equals("0")) {

            String dateTimeFinish = " dbo.timeToNum(CONVERT(datetime, " + "'" + date + " " + timeFinish + "' , 103)) " ;
            if (timeFinish == null)   //ΓΙΑ ΝΑ ΜΗ ΣΠΑΕΙ ΣΤΟ CONERT TIMETONUΜ ΣΤΗ ΒΑΣΗ
                dateTimeFinish = null;

            String query = Str_queries.setglobals(main.userID , "2",companyID) +
                    " insert into Nursing_Hemodialysis_initial(time_start, time_finish, arxiko_varos,xiro_varos," +
                    "Uf_arxiko,Uf_teliko,varos_exodou, ipefthinos_nosileftis, nosileftis_flevokentisis, nosileftis_2 ," +
                    "agg_prospelasi, diarkia , Max_UFh, aimatokritis, aimosfairini, roi_aimatos , roi_dialimatos, roi_ipokatastatou, agogimotita, thermokrasia_dialimatos," +
                    "hco3, Kt_V, piesi_exodou, katastasi_filtou, hemoscan ,TransGroupID )" +

                    "select time_start = " + " dbo.timeToNum(CONVERT(datetime, " + "'" + date + " " + timeStart  + "' , 103)), " +
                    "time_finish = " + dateTimeFinish +
                    ",arxiko_varos =" + arxikoVaros + " ,xiro_varos =" + ksiroVaros + " ," +
                    " Uf_arxiko =" + UF_arxiko + " ,Uf_teliko = " + UF_teliko + "" +
                    " ,varos_exodou =" + varosEksodou + " , ipefthinos_nosileftis =" + ipef_nosil +
                    " , nosileftis_flevokentisis =" + nosil_Flev + " , " +
                    "nosileftis_2  =" + ipef_nosil_2 + " ," + "agg_prospelasi =" + aggProspelasi +
                    " , diarkia =" + " dbo.timeToNum(CONVERT(datetime,'" + Utils.getCurrentDate() + " " + duration + "',103)), " +
                    " Max_UFh =" + Max_UF_ + " ,aimatokritis =" + aimatokritis + " ,aimosfairini =" + aimosfairini + " , roi_aimatos  =" + roiAimatos +
                    " , roi_dialimatos =" + roiDialimatos + " , " +
                    "roi_ipokatastatou =" + roiIpokatastatou + " , agogimotita =" + agogimotita + " , thermokrasia_dialimatos =" + thermokDialimatos+ " ," +
                    "hco3 =" + HCO3_+ " , Kt_V =" + ktv + " , piesi_exodou =" + piesiEksodou + " , katastasi_filtou =" + katastasiFiltrou + ", " +
                    "hemoscan = " + hemoscan + " , " +

                    "transgroupid = " + transgroupID;



            AsyncTaskUpdate task = new AsyncTaskUpdate(CTX, query);
            task.listener = new AsyncGetUpdateResult() {
                @Override
                public void update(String str) {
                    //TO APOTELESMA TO UPDATE ERXETAI EDW
                    // STO FRAGMENT ME AYTON TON TROPO GINETAI DILONETAI


                    if (str.equals("Πετυχημένη ενημέρωση")) {
                        timeHandlerDoneButton();
                        getCurrentStableMeasurement(); // ΜΕ ΤΟ ΠΟΥ ΚΑΝΕΙ ΕΝΜΗΕΡΩΣΗ ΚΑΝΕΙ SELECT ΓΙΑ ΝΑ ΠΑΡΕΙ ΤΟ ID ΤΗΣ ΕΓΓΡΑΦΗΣ
                    }
                    else
                        timeHandlerErrorButton();

                }
            };

            task.execute();
        } else{
            updateButton.revertAnimation();
            Toast.makeText(CTX, "Δεν μπορείτε να κάνετε ενημέρωση χωρίς ασθενή", Toast.LENGTH_SHORT).show();

        }
    }


    private void updateInfo(){

        if (id != null) {

            String dateTimeFinish = " dbo.timeToNum(CONVERT(datetime, " + "'" + date + " " + timeFinish + "' , 103)) " ;
            if (timeFinish == null)   //ΓΙΑ ΝΑ ΜΗ ΣΠΑΕΙ ΣΤΟ CONERT TIMETONUΜ ΣΤΗ ΒΑΣΗ
                dateTimeFinish = null;

            String query =  Str_queries.setglobals(main.userID ,"2",companyID) +
                    "  update Nursing_Hemodialysis_initial" +
                    "  set time_finish = " + dateTimeFinish + "  , arxiko_varos = " + arxikoVaros + " " +
                    " ,xiro_varos = " + ksiroVaros + " ,Uf_arxiko = " + UF_arxiko + " ,Uf_teliko = " + UF_teliko +
                    " ,varos_exodou = " + varosEksodou + " ,ipefthinos_nosileftis = " + ipef_nosil + " , nosileftis_flevokentisis = " + nosil_Flev +
                    " , nosileftis_2  = " + ipef_nosil_2 + " ,agg_prospelasi = " + aggProspelasi +
                    "  ,diarkia = " + " dbo.timeToNum(CONVERT(datetime,'" + Utils.getCurrentDate() + " " + duration + "',103))" +
                    "  , Max_UFh = " + Max_UF_ +
                    " , roi_aimatos  = " + roiAimatos + " ,aimatokritis =" + aimatokritis + " ,aimosfairini =" + aimosfairini + " , roi_dialimatos = " + roiDialimatos + " " +
                    "  ,roi_ipokatastatou = " + roiIpokatastatou + " , agogimotita = " + agogimotita + " , thermokrasia_dialimatos = "  + thermokDialimatos +
                    "  ,hco3 = " + HCO3_ + " , Kt_V = " + ktv +
                    "  , piesi_exodou = " + piesiEksodou  + " , katastasi_filtou = " + katastasiFiltrou + " , hemoscan = " + hemoscan +
                    "   where id = " + id;


            AsyncTaskUpdate task = new AsyncTaskUpdate(CTX, query);
            task.listener = new AsyncGetUpdateResult() {
                @Override
                public void update(String str) {
                    //TO APOTELESMA TO UPDATE ERXETAI EDW
                    // STO FRAGMENT ME AYTON TON TROPO GINETAI DILONETAI

                    if (str.equals("Πετυχημένη ενημέρωση")) {
                        timeHandlerDoneButton();
                    }
                    else
                        timeHandlerErrorButton();

                }
            };

            task.execute();
        } else
            Toast.makeText(CTX, "Σφάλμα κατά την ενμέρωση (error 103)", Toast.LENGTH_SHORT).show();
    }



    private void timeHandlerDoneButton(){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp);

                updateButton.doneLoadingAnimation(Color.GREEN, bitmap);


                Toast.makeText(CTX, "Πετυχημένη ενημέρωση", Toast.LENGTH_SHORT).show();


            }
        }, 2000);

        deMorphing();

    }

    private void timeHandlerErrorButton(){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cancel);

                updateButton.doneLoadingAnimation(Color.GREEN, bitmap);
                Toast.makeText(CTX, "Προέκυψε σφάλμα κατά την ενημέρωση (error 101)", Toast.LENGTH_SHORT).show();

            }
        }, 2000);

        deMorphing();

    }

    public void deMorphing()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                updateButton.revertAnimation();
            }
        }, 3800);



    }




    private void getSetTexts() {

        if (dateTV.getText().toString().equals(""))  //ΓΙΑ ΝΑ ΠΑΙΡΝΕΙ ΤΗΝ ΗΜΕΡΟΜΗΝΙΑ ΜΟΝΟ ΣΤΟ ΙΝΣΕΡΤ ΚΑΙ ΟΧΙ ΣΤΗΝ ΕΝΗΜΕΡΩΣΗ
            dateTV.setText(Utils.getCurrentDate());

        date = dateTV.getText().toString();

        // ME AYTI TI SEIRA GIA NA GINEI PROTA I DIAFORA KAI META OI TIMES NA GINOUN LONG GIA NA PANE STI VASI SE SOSTI MORFI
        if (timeStartTV.getText().toString().equals(""))      //ΓΙΑ ΝΑ ΠΑΙΡΝΕΙ ΤΗΝ ΩΡΑ ΜΟΝΟ ΣΤΟ ΙΝΣΕΡΤ ΚΑΙ ΟΧΙ ΣΤΗΝ ΕΝΗΜΕΡΩΣΗ
            timeStartTV.setText(Utils.getCurrentTime2());

        timeStart = Utils.checkNull(timeStartTV.getText().toString());
        timefinishTV.setText(Utils.hourFormat(timefinishTV.getText().toString()));
        timeFinish = Utils.checkNull(timefinishTV.getText().toString());

        durationMetrisisTV.setText(Utils.timeDiffrence(timeStart , timeFinish));
        durationMetrisisTV.setText(Utils.chechZeroInTime(durationMetrisisTV.getText().toString()));
        durationMetrisis = Utils.checkNull(durationMetrisisTV.getText().toString());


        // timeStart = timeStart == null ? null : Utils.convertTime(timeStart);
        //   timeFinish = timeFinish == null ? null : Utils.convertTime(timeFinish);


//-------------------------------------------------------------------------------------------------
        arxikoVarosET.setText(Utils.checkFormat1(arxikoVarosET.getText().toString()));
        arxikoVaros =Utils.checkNull( arxikoVarosET.getText().toString());
        ksiroVarosET.setText(Utils.checkFormat1(ksiroVarosET.getText().toString()));
        ksiroVaros = Utils.checkNull(ksiroVarosET.getText().toString());
        diaforaVarousTV.setText(Utils.afairesiDouble(arxikoVaros,ksiroVaros));
        diaforaVarous = Utils.checkNull(diaforaVarousTV.getText().toString());
        UF_arxikoET.setText(Utils.checkFormat1(UF_arxikoET.getText().toString()));
        UF_arxiko = Utils.checkNull(UF_arxikoET.getText().toString());
        UF_telikoET.setText(Utils.checkFormat1(UF_telikoET.getText().toString()));
        UF_teliko = Utils.checkNull(UF_telikoET.getText().toString());
        varosEksodouET.setText(Utils.checkFormat1(varosEksodouET.getText().toString()));
        varosEksodou = Utils.checkNull(varosEksodouET.getText().toString());

        // ---------------------- SPINNER VALUES -----------------------------
        ipef_nosil = String.valueOf(nosileutesHashmap.get(ipef_nosil_Sp.getSelectedItem().toString()));
        ipef_nosil = ipef_nosil.equals("0") ? null : ipef_nosil;

        ipef_nosil_2 = String.valueOf(nosileutesHashmap.get(ipef_nosil_2_SP.getSelectedItem().toString()));
        ipef_nosil_2 = ipef_nosil_2.equals("0") ? null : ipef_nosil_2;

        nosil_Flev = String.valueOf(nosileutesHashmap.get(nosil_FlevSP.getSelectedItem().toString()));
        nosil_Flev = nosil_Flev.equals("0") ? null : nosil_Flev;

        aggProspelasi = InfoSpecificLists.getAggProspelasiID(aggProspelasiSP.getSelectedItem().toString());
        aggProspelasi = aggProspelasi.equals("0") ? null : aggProspelasi;

        katastasiFiltrou = InfoSpecificLists.getKatastasiID(katastasiFiltrouSP.getSelectedItem().toString());
        katastasiFiltrou = katastasiFiltrouSP.equals("0") ? null : katastasiFiltrou;

        // ---------------------------------------------------------------------------------------

        durationET.setText(durationET.getText().toString());
        duration = durationET.getText().toString();

        Max_UF_ET.setText(Utils.checkFormat2(Max_UF_ET.getText().toString()));
        Max_UF_= Utils.checkNull(Max_UF_ET.getText().toString());

        aimatokritisET.setText(Utils.checkFormat2(aimatokritisET.getText().toString()));
        aimatokritis = Utils.checkNull(aimatokritisET.getText().toString());
        aimosfairiniET.setText(Utils.checkFormat2(aimosfairiniET.getText().toString()));
        aimosfairini = Utils.checkNull(aimosfairiniET.getText().toString());

        roiAimatosET.setText(Utils.checkFormat2(roiAimatosET.getText().toString()));
        roiAimatos = Utils.checkNull(roiAimatosET.getText().toString()) ;
        roiDialimatosET.setText(Utils.checkFormat2(roiDialimatosET.getText().toString()));
        roiDialimatos = Utils.checkNull(roiDialimatosET.getText().toString());
        roiIpokatastatouET.setText(Utils.checkFormat2(roiIpokatastatouET.getText().toString()));
        roiIpokatastatou = Utils.checkNull(roiIpokatastatouET.getText().toString());

        agogimotitaET.setText(Utils.checkFormat2(agogimotitaET.getText().toString()));
        agogimotita = Utils.checkNull(agogimotitaET.getText().toString());
        thermokDialimatosET.setText(Utils.checkFormat2(thermokDialimatosET.getText().toString()));
        thermokDialimatos = Utils.checkNull(thermokDialimatosET.getText().toString());
        HCO3_ET.setText(Utils.checkFormat2(HCO3_ET.getText().toString()));
        HCO3_ = Utils.checkNull(HCO3_ET.getText().toString());

        ktvET.setText(Utils.checkFormat2(ktvET.getText().toString()));
        ktv = Utils.checkNull(ktvET.getText().toString());
        piesiEksodouET.setText(Utils.checkFormat2(piesiEksodouET.getText().toString()));
        piesiEksodou = Utils.checkNull(piesiEksodouET.getText().toString());

        //hemoscanET.setText(Utils.checkNull(hemoscanET.getText().toString()));
        hemoscan = Utils.checkNull(hemoscanET.getText().toString());




    }



    private void clearTexts(){

        dateTV.setText("");
        timeStartTV.setText("");
        timefinishTV.setText("");
        durationMetrisisTV.setText("");

//-------------------------------------------------------------------------------------------------
        arxikoVarosET.setText("");
        ksiroVarosET.setText("");
        diaforaVarousTV.setText("");
        UF_arxikoET.setText("");
        UF_telikoET.setText("");
        varosEksodouET.setText("");

        // ---------------------- SPINNER VALUES -----------------------------
        ipef_nosil_Sp.setSelection(0);
        ipef_nosil_2_SP.setSelection(0);
        nosil_FlevSP.setSelection(0);
        aggProspelasiSP.setSelection(0);
        katastasiFiltrouSP.setSelection(0);
        // ---------------------------------------------------------------------------------------

        durationET.setText("");
        Max_UF_ET.setText("");
        aimatokritisET.setText("");
        aimosfairiniET.setText("");
        roiAimatosET.setText("");
        roiDialimatosET.setText("");
        roiIpokatastatouET.setText("");
        agogimotitaET.setText("");
        thermokDialimatosET.setText("");
        HCO3_ET.setText("");
        ktvET.setText("");
        piesiEksodouET.setText("");
        hemoscanET.setText("");
    }

    private void clearAllFocus(){
        arxikoVarosET.clearFocus();
        arxikoVarosET.clearFocus(); ksiroVarosET.clearFocus(); UF_arxikoET.clearFocus(); UF_telikoET.clearFocus(); varosEksodouET.clearFocus();
        durationET.clearFocus(); Max_UF_ET.clearFocus(); roiAimatosET.clearFocus(); roiDialimatosET.clearFocus();
        roiIpokatastatouET.clearFocus();agogimotitaET.clearFocus(); thermokDialimatosET.clearFocus(); HCO3_ET.clearFocus();
        ktvET.clearFocus(); piesiEksodouET.clearFocus();
    }



    private void getNosileutes(){

        if (Utils.isNetworkAvailable(CTX)) {


            nosileutesLista.clear();
            nosileutesHashmap.clear();

            String query = "select id , name from users where IsNurse = 1 and companyID = " + companyID;

            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = CTX.getApplicationContext();
            task.query = query;
            task.listener = new AsyncCompleteTask() {
                @Override
                public void taskComplete(JSONArray results) throws JSONException {

                    nosileutesLista.add("");
                    nosileutesHashmap.put("", 0);


                    for (int i=0; i<results.length(); i ++){

                        JSONObject nurses = results.getJSONObject(i);
                        int id = nurses.getInt("id");
                        String name = nurses.getString("name");


                        nosileutesHashmap.put(name, id);
                        nosileutesLista.add(name);

                    }

                    addSpinnerAdaptors();

                    getCurrentStableMeasurement();  //  PROTA NA FORTOSOUN OI ADAPTORES KAI META NA TREKSEI DIAFORETIKA EXEI ERROR


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






    private void getTeleutaiesMetriseis() {

        if (Utils.isNetworkAvailable(CTX)) {

            String query = "select top 1 ni.TransGroupID , ni.xiro_varos as ksiro_varos, " +
                    "agg_prospelasi , dbo.timeToStr(diarkia) as diarkia , Max_UFh, " +
                    "roi_aimatos,roi_dialimatos, roi_ipokatastatou, aimatokritis, aimosfairini ,agogimotita, " +
                    "thermokrasia_dialimatos ,hco3" +
                    " from TransGroup tg" +
                    " inner join Nursing_Hemodialysis_initial ni on tg.id = ni.TransGroupID " +
                    " where " +
                    " PatientID = " + patientid +
                    " order by tg.ID desc";

            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = CTX;
            task.query = query;
            task.listener = new AsyncCompleteTask() {
                @Override
                public void taskComplete(JSONArray results) throws JSONException {

                    if (results != null){

                        JSONObject teleutaiesMetriseis = results.getJSONObject(0);
                        if (teleutaiesMetriseis.has("ksiro_varos"))
                            ksiroVarosET.setText(String.valueOf(teleutaiesMetriseis.getDouble("ksiro_varos")));

                        if (teleutaiesMetriseis.has("agg_prospelasi"))
                            aggProspelasiSP.setSelection(teleutaiesMetriseis.getInt("agg_prospelasi"));

                        if (teleutaiesMetriseis.has("diarkia"))
                            durationET.setText(teleutaiesMetriseis.getString("diarkia"));

                        if (teleutaiesMetriseis.has("Max_UFh"))
                            Max_UF_ET.setText(Utils.convertObjToString(teleutaiesMetriseis.get("Max_UFh")));


                        if (teleutaiesMetriseis.has("roi_aimatos"))
                            roiAimatosET.setText(Utils.convertObjToString(teleutaiesMetriseis.get("roi_aimatos")));


                        if (teleutaiesMetriseis.has("roi_dialimatos"))
                            roiDialimatosET.setText(Utils.convertObjToString(teleutaiesMetriseis.get("roi_dialimatos")));

                        if (teleutaiesMetriseis.has("roi_ipokatastatou"))
                            roiIpokatastatouET.setText(Utils.convertObjToString(teleutaiesMetriseis.get("roi_ipokatastatou")));

                        if (teleutaiesMetriseis.has("aimatokritis"))
                            aimatokritisET.setText(Utils.convertObjToString(teleutaiesMetriseis.get("aimatokritis")));

                        if (teleutaiesMetriseis.has("aimosfairini"))
                            aimosfairiniET.setText(Utils.convertObjToString(teleutaiesMetriseis.get("aimosfairini")));

                        if (teleutaiesMetriseis.has("agogimotita"))
                            agogimotitaET.setText(Utils.convertObjToString(teleutaiesMetriseis.get("agogimotita")));

                        if (teleutaiesMetriseis.has("thermokrasia_dialimatos"))
                            thermokDialimatosET.setText(Utils.convertObjToString(teleutaiesMetriseis.get("thermokrasia_dialimatos")));

                        if (teleutaiesMetriseis.has("hco3"))
                            HCO3_ET.setText(Utils.convertObjToString(teleutaiesMetriseis.get("hco3")));

                        if (teleutaiesMetriseis.has("hemoscan"))
                            hemoscanET.setText(Utils.convertObjToString(teleutaiesMetriseis.get("hemoscan")));


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




    private void timefinishTVListener(){
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

        timefinishTV.setOnClickListener(new View.OnClickListener() {

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
                        timefinishTV.setText( Utils.chechZeroInTime(selectedHour + ":" + selectedMinute));
                    }
                }, hour, minute, true);
                // mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }





    private void addSpinnerAdaptors(){

        dataAdapter = new ArrayAdapter<>(CTX,
                R.layout.spinner_layout2, nosileutesLista);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ipef_nosil_Sp.setAdapter(dataAdapter);
        ipef_nosil_2_SP.setAdapter(dataAdapter);
        nosil_FlevSP.setAdapter(dataAdapter);

        //---------------------
        dataAdapter2 = new ArrayAdapter<>(CTX,
                R.layout.spinner_layout2, InfoSpecificLists.getAggProspelasiList());
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aggProspelasiSP.setAdapter(dataAdapter2);


        //--------------

        dataAdapter3 = new ArrayAdapter<>(CTX,
                R.layout.spinner_layout2, InfoSpecificLists.getKatastasiList());
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        katastasiFiltrouSP.setAdapter(dataAdapter3);


    }




}



