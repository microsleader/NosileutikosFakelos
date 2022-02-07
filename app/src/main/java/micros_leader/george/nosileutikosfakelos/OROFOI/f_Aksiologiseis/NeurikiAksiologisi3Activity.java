package micros_leader.george.nosileutikosfakelos.OROFOI.f_Aksiologiseis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.github.ag.floatingactionmenu.OptionsFabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetCurrentMetrisi;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.PrintReport;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetCurrentMetrisi;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.ReportIDs;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityNeurikiAksiologisi3Binding;

public class NeurikiAksiologisi3Activity extends BasicActivity implements  AsyncGetUpdateResult, AsyncGetCurrentMetrisi ,
        AsyncCompleteGetPatientsTask, MyDialogFragmentCloseListener,View.OnClickListener {


    public  TextView   wraProseleusisNewTV, wraProtokollouTV, wraThromvolisisTV, synoloTV;
    public Spinner hoursSP;
    public EditText epipSinidisisET, kinAnoAristerouAkrouET, kinAnoDexiouAkrouET, kinKatoAkrouDexiouET, kinKatoAristerouAkrouET, afasiaET, egrigorsiET, monthAgeET, eyesHandsET, ofthalmokinET, imianopsiaET, paresiFaceET, ptwsiAnwAkrouET, ptwsiKatwAkrouET,
            ataxiaET, astheticET, afasiaNihssET, disarthriaET, neglectET;
    public List <EditText> editTextList;
    public CircularProgressButton buttonEnimerosi;
    public ImageView infoimage1 ,infoimage2 ,infoimage3 ,infoimage4, infoimage5, infoimage6, infoimage8, infoimage9, infoimage10, infoimage11, infoimage12, infoimage13, infoimage14, infoimage15, infoimage16, infoimage17, infoimage18;
    private ArrayList<String> watchList;
    private Boolean isThereTransgroupID , hasProtokollo;
    private String   date,
            epipedo_sinidisis , kinitikotita_ano_aristerou_akrou, kinitikotita_kato_aristerou_akrou, kinitikotita_ano_dexiou_akrou, kinitikotita_kato_dexiou_akrou, afasia,
            egrigorsi, minas_ilikia, matia_xeria, ofthalmokinitikotita, imianopsia, paresi_proswpou,
            ptwsi_anw_akrou, ptwsi_katw_akrou, ataxia, aisthitikotita, afasia_nihss, disarthria, neglect, wraProseleusis, wraProtokollou, wraThromvolisis, sumNIHSS;

    public CheckBox timeCH;

    public OptionsFabLayout fab;

    public AlertDialog alertDialog;

    public ActivityNeurikiAksiologisi3Binding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuriki_aksiologisi3);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        bd = ActivityNeurikiAksiologisi3Binding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);

        dateTV = findViewById(R.id.dateTV);

        hoursSP = findViewById(R.id.hoursSP);
        epipSinidisisET = findViewById(R.id.epipSinidisisET);
        kinAnoAristerouAkrouET = findViewById(R.id.kinAnoAristerouAkrouET);
        kinKatoAristerouAkrouET = findViewById(R.id.kinKatoAristerouAkrouET);
        kinAnoDexiouAkrouET = findViewById(R.id.kinAnoDexiouAkrouET);
        kinKatoAkrouDexiouET = findViewById(R.id.kinKatoAkrouDexiouET);
        afasiaET = findViewById(R.id.afasiaET);
        timeCH = findViewById(R.id.timeCH);
        buttonEnimerosi = findViewById(R.id.updateButton);

        wraProseleusisNewTV = findViewById(R.id.wraProseleusisNewTV);
        wraProtokollouTV = findViewById(R.id.wraProtokollouTV);
        wraThromvolisisTV = findViewById(R.id.wraThromvolisisTV);
        egrigorsiET = findViewById(R.id.egrigorsiET);
        monthAgeET = findViewById(R.id.monthAgeET);
        eyesHandsET = findViewById(R.id.eyesHandsET);
        ofthalmokinET = findViewById(R.id.ofthalmokinET);
        imianopsiaET = findViewById(R.id.imianopsiaET);
        paresiFaceET = findViewById(R.id.paresiFaceET);
        ptwsiAnwAkrouET = findViewById(R.id.ptwsiAnwAkrouET);
        ptwsiKatwAkrouET = findViewById(R.id.ptwsiKatwAkrouET);
        ataxiaET = findViewById(R.id.ataxiaET);
        astheticET = findViewById(R.id.astheticET);
        afasiaNihssET = findViewById(R.id.afasiaNihssET);
        disarthriaET = findViewById(R.id.disarthriaET);
        neglectET = findViewById(R.id.neglectET);
        infoimage1 = findViewById(R.id.infoIV1);
        infoimage2 = findViewById(R.id.infoIV2);
        infoimage3 = findViewById(R.id.infoIV3);
        infoimage4 = findViewById(R.id.infoIV4);
        infoimage5 = findViewById(R.id.egrigorsiIV);
        infoimage6 = findViewById(R.id.monthAgeIV);
        infoimage8 = findViewById(R.id.eyesHandsIV);
        infoimage9 = findViewById(R.id.ofthalmokinIV);
        infoimage10 = findViewById(R.id.imianopsiaIV);
        infoimage11 = findViewById(R.id.paresiFaceIV);
        infoimage12 = findViewById(R.id.ptwsiAnwAkrouIV);
        infoimage13 = findViewById(R.id.ptwsiKatwAkrouIV);
        infoimage14 = findViewById(R.id.ataxiaIV);
        infoimage15 = findViewById(R.id.astheticIV);
        infoimage16 = findViewById(R.id.afasiaNihssIV);
        infoimage17 = findViewById(R.id.disarthriaIV);
        infoimage18 = findViewById(R.id.neglectIV);
        synoloTV = findViewById(R.id.synoloTV);

        watchList = InfoSpecificLists.get3HoursLista();


        fab = findViewById(R.id.fab);
        initialize();

    }



    public void initialize(){
        if (extendedAct == null)
            extendedAct = this;

        userID = Utils.getUserID(extendedAct);


        alertDialog = Utils.setLoadingAlertDialog(extendedAct);

        thereIsDatePicker(R.id.dateTV);
        dateTV.setText(Utils.getCurrentDate());
        buttonEnimerosi.setOnClickListener(this);


        infoimage1.setOnClickListener(this);
        infoimage2.setOnClickListener(this);
        infoimage3.setOnClickListener(this);
        infoimage4.setOnClickListener(this);
        infoimage5.setOnClickListener(this);
        infoimage6.setOnClickListener(this);
        infoimage8.setOnClickListener(this);
        infoimage9.setOnClickListener(this);
        infoimage10.setOnClickListener(this);
        infoimage11.setOnClickListener(this);
        infoimage12.setOnClickListener(this);
        infoimage13.setOnClickListener(this);
        infoimage14.setOnClickListener(this);
        infoimage15.setOnClickListener(this);
        infoimage16.setOnClickListener(this);
        infoimage17.setOnClickListener(this);
        infoimage18.setOnClickListener(this);

        watchList = InfoSpecificLists.get3HoursLista();

        editTextList = new ArrayList<>();
        editTextList.add(egrigorsiET);
        editTextList.add(monthAgeET);
        editTextList.add(eyesHandsET);
        editTextList.add(ofthalmokinET);
        editTextList.add(imianopsiaET);
        editTextList.add(paresiFaceET);
        editTextList.add(ptwsiAnwAkrouET);
        editTextList.add(ptwsiKatwAkrouET);
        editTextList.add(ataxiaET);
        editTextList.add(astheticET);
        editTextList.add(afasiaNihssET);
        editTextList.add(disarthriaET);
        editTextList.add(neglectET);

        fabInitialize();

        checkboxListener();

        spinnerAdapter(watchList);

        getPatientsList(extendedAct,R.id.patientsTV,R.id.floorsSP);

        spinnerListener(); // ΜΕ ΤΟ ΠΟΥ ΜΠΑΙΝΕΙ Ο ΛΙΣΕΝΕΡ ΑΜΕΣΩΣ ΚΑΛΕΙ ΤΗ ΜΕΘΟΔΟ getCurrentMetrisi();


        Utils.timeListener(extendedAct, wraProseleusisNewTV);
        Utils.timeListener(extendedAct, wraProtokollouTV);
        Utils.timeListener(extendedAct, wraThromvolisisTV);

        thereIsImagePrinterButton(ReportIDs.NEUROLOGIKI_AKSIOLOGISI_ANA_ORA , PrintReport.ReportParams.TRANSGROUP_ID_AND_DATE_STR);

    }

    @Override
    public void printerListener(int reportID, PrintReport.ReportParams repParam) {
        dateStr = dateTV.getText().toString();
        if (timeCH.isChecked())
            reportID = ReportIDs.NEUROLOGIKI_AKSIOLOGISI_ANA_ORA;
        else
            reportID = ReportIDs.NEUROLOGIKI_AKSIOLOGISI_ANA_TRIORO;

        super.printerListener(reportID, repParam);
    }

    private void fabInitialize() {

        fab.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fab.isOptionsMenuOpened())
                    fab.closeOptionsMenu();
            }
        });

        fab.setMiniFabsColors(
                R.color.colorPrimary,
                R.color.colorPrimary);
        //-------------------  PROSTHIKI ANTIKEIMENON STO MENOY TOY



        //  LISTENER  GIA TA ITEMS TOY
        fab.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {



                switch (fabItem.getItemId()) {

                    case R.id.sigkentrotika_ana_ora:

                        showSigkentrotika_ana_ora();
                        break;

                    case R.id.sigkentrotika_ana_3oro:

                        showSigkentrotika_ana_3oro();

                        break;

                    default:

                        break;
                }
            }
        });
    }


    private void showSigkentrotika_ana_ora(){

        extendedAct.startActivity(    tableView_sigkentrotika(Str_queries.getNEURIKI_AKSIOLOGISI_SIGKENTROTIKA(transgroupID,dateTV.getText().toString() , true),
                transgroupID,
                null,
                new String[]{"Επίπεδο συνείδησης", "Κινητηκότητα άνω άκρου", "Κινητηκότητα κάτω άκρου", "Αφασία",
                        "Κινητικότητα άνω αριστερού άκρου", "Κινητικότητα κάτω αριστερού άκρου", "Κινητικότητα άνω δεξιού άκρου", "Κινητικότητα κάτω δεξιού άκρου",
                        "Εγρήγορση", "Μήνας? Ηλικία?", "Μάτια, Χέρια", "οφθαλμοκινητικότητας", "Ημιανοψία", "Πάρεση προσωπικού",
                        "Πτώση άνω άκρου", "Πτώση κάτω άκρου", "Αταξία", "Αισθητικότητα", "Αφασία", "Δυσαρθρία", "Neglect", "Ώρα προσέλευσης" ,
                        "Ώρα έναρξης Πρωτοκόλλου", "Ώρα έναρξης θρομβόλυσης"},
                new String[]{"epipedo_sinidisis", "kinitikotita_ano_akrou", "kinitikotita_kato_akrou", "afasia",
                        "kinitikotita_ano_aristerou_akrou", "kinitiokotita_kato_aristerou_akrou", "kinitikotita_ano_dexiou_akrou", "kinitiokotita_kato_dexiou_akrou",
                        "egrigorsi", "minas_ilikia", "matia_xeria", "ofthalmokinitikotita", "imianopsia", "paresi_proswpou", "ptwsi_anw_akrou",
                        "ptwsi_katw_akrou", "ataxia", "aisthitikotita", "afasia_nihss", "disarthria", "neglect", "wraProseleusis", "wraProtokollou", "wraThromvolisis"},
                true,true,false));
    }

    private void showSigkentrotika_ana_3oro(){


        extendedAct.startActivity(    tableView_sigkentrotika(Str_queries.getNEURIKI_AKSIOLOGISI_SIGKENTROTIKA(transgroupID,dateTV.getText().toString() ,false),
                transgroupID,
                null,
                new String[]{"Επίπεδο συνείδησης","Κινητηκότητα άνω άκρου","Κινητηκότητα κάτω άκρου","Αφασία",
                        "Κινητικότητα άνω αριστερού άκρου", "Κινητικότητα κάτω αριστερού άκρου", "Κινητικότητα άνω δεξιού άκρου", "Κινητικότητα κάτω δεξιού άκρου",
                        "Εγρήγορση", "Μήνας? Ηλικία?", "Μάτια, Χέρια", "οφθαλμοκινητικότητας", "Ημιανοψία", "Πάρεση προσωπικού",
                        "Πτώση άνω άκρου", "Πτώση κάτω άκρου", "Αταξία", "Αισθητικότητα", "Αφασία", "Δυσαρθρία", "Neglect", "Ώρα προσέλευσης" ,
                        "Ώρα έναρξης Πρωτοκόλλου", "Ώρα έναρξης θρομβόλυσης"},
                new String[]{"epipedo_sinidisis","kinitikotita_ano_akrou","kinitikotita_kato_akrou","afasia",
                        "kinitikotita_ano_aristerou_akrou", "kinitiokotita_kato_aristerou_akrou", "kinitikotita_ano_dexiou_akrou", "kinitiokotita_kato_dexiou_akrou",
                        "egrigorsi", "minas_ilikia", "matia_xeria", "ofthalmokinitikotita", "imianopsia", "paresi_proswpou", "ptwsi_anw_akrou",
                        "ptwsi_katw_akrou", "ataxia", "aisthitikotita", "afasia_nihss", "disarthria", "neglect", "wraProseleusis", "wraProtokollou", "wraThromvolisis"},
                true,true,false));
    }



    private void checkboxListener() {
        timeCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeCH.isChecked()) {
                    watchList = InfoSpecificLists.get24HoursLista();
                    spinnerAdapter(watchList);
                    timeCH.setChecked(true);
                }
                else {
                    watchList = InfoSpecificLists.get3HoursLista();
                    spinnerAdapter(watchList);
                    timeCH.setChecked(false);
                }
            }
        });
    }


    private void spinnerListener() {

        hoursSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (timeCH.isChecked())
                    watchID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());
                else
                    watchID = InfoSpecificLists.get3hoursID(hoursSP.getSelectedItem().toString());

                getCurrentMetrisi(transgroupID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    public void getCurrentMetrisi(String transgroupid) {

        if (Utils.isNetworkAvailable(extendedAct)) {
            alertDialog.show();
            String query = "select * " +
                    " from Nursing_Parakolouthisi_Neurologiki " +
                    " where transGroupID = " + transgroupid +
                    " and  date = " + " dbo.timeToNum(CONVERT(datetime, " + "'" + dateTV.getText().toString() + "' , 103)) " +
                    " and watch = " + watchID;
            AsyncTaskGetCurrentMetrisi task = new AsyncTaskGetCurrentMetrisi();
            task.ctx = extendedAct;
            task.listener = (AsyncGetCurrentMetrisi) (activityFromSigxoneusi != null ? activityFromSigxoneusi : extendedAct);
            task.query = query;
            task.execute();


            task = new AsyncTaskGetCurrentMetrisi();
            task.ctx = extendedAct;
            task.listener = (AsyncGetCurrentMetrisi) (activityFromSigxoneusi != null ? activityFromSigxoneusi : extendedAct);
            task.query = "select wraProseleusis as pros, wraProtokollou as prot , wraThromvolisis as thr \n" +
                    "from Nursing_Parakolouthisi_Neurologiki_protokolo \n" +
                    "where transGroupID = " + transgroupid;

            task.execute();

        }


    }

    @Override
    public void taskCompleteCurrentMetrisi(JSONArray results) throws JSONException {
        if (results != null && results.getJSONObject(0).has("ID")) {
            isThereTransgroupID = true;
            //  Log.e("array",array.toString());
            JSONObject jsonObject = results.getJSONObject(0);
            epipSinidisisET.setText(Utils.convertObjToString(jsonObject.getString("epipedo_sinidisis")));
            kinAnoAristerouAkrouET.setText(Utils.convertObjToString(jsonObject.getString("kinitikotita_ano_aristerou_akrou")));
            kinKatoAristerouAkrouET.setText(Utils.convertObjToString(jsonObject.getString("kinitikotita_kato_aristerou_akrou")));
            kinAnoDexiouAkrouET.setText(Utils.convertObjToString(jsonObject.getString("kinitikotita_ano_dexiou_akrou")));
            kinKatoAkrouDexiouET.setText(Utils.convertObjToString(jsonObject.getString("kinitikotita_kato_dexiou_akrou")));
            afasiaET.setText(Utils.convertObjToString(jsonObject.getString("afasia")));
            egrigorsiET.setText(Utils.convertObjToString(jsonObject.getString("egrigorsi")));
            monthAgeET.setText(Utils.convertObjToString(jsonObject.getString("minas_ilikia")));
            eyesHandsET.setText(Utils.convertObjToString(jsonObject.getString("matia_xeria")));
            ofthalmokinET.setText(Utils.convertObjToString(jsonObject.getString("ofthalmokinitikotita")));
            imianopsiaET.setText(Utils.convertObjToString(jsonObject.getString("imianopsia")));
            paresiFaceET.setText(Utils.convertObjToString(jsonObject.getString("paresi_proswpou")));
            ptwsiAnwAkrouET.setText(Utils.convertObjToString(jsonObject.getString("ptwsi_anw_akrou")));
            ptwsiKatwAkrouET.setText(Utils.convertObjToString(jsonObject.getString("ptwsi_katw_akrou")));
            ataxiaET.setText(Utils.convertObjToString(jsonObject.getString("ataxia")));
            astheticET.setText(Utils.convertObjToString(jsonObject.getString("aisthitikotita")));
            afasiaNihssET.setText(Utils.convertObjToString(jsonObject.getString("afasia_nihss")));
            disarthriaET.setText(Utils.convertObjToString(jsonObject.getString("disarthria")));
            neglectET.setText(Utils.convertObjToString(jsonObject.getString("neglect")));


            synoloTV.setText(calculateNIHSSscore(editTextList));

        }


        else if (results != null && !results.getJSONObject(0).has("pros")){
            isThereTransgroupID = false;
            Utils.edittextSetText(editTextList, "");
            wraProseleusisNewTV.setText("");
            wraProtokollouTV.setText("");
            wraThromvolisisTV.setText("");
            epipSinidisisET.setText("");
            kinAnoAristerouAkrouET.setText("");
            kinKatoAristerouAkrouET.setText("");
            kinAnoDexiouAkrouET.setText("");
            kinKatoAkrouDexiouET.setText("");
            afasiaET.setText("");
            synoloTV.setText("");
            alertDialog.dismiss();
        }


        if (results != null && results.getJSONObject(0).has("pros")){

            hasProtokollo = true;
            JSONObject jsonObject = results.getJSONObject(0);
            wraThromvolisisTV.setText(Utils.convertMilliSecondsToTime(Utils.convertObjToString(jsonObject.getString("thr"))));
            wraProtokollouTV.setText(Utils.convertMilliSecondsToTime(Utils.convertObjToString(jsonObject.getString("prot"))));
            wraProseleusisNewTV.setText(Utils.convertMilliSecondsToTime(Utils.convertObjToString(jsonObject.getString("pros"))));
        }
        else
            hasProtokollo = false;

        alertDialog.dismiss();

    }




    private void spinnerAdapter(ArrayList <String> lista) {
        ArrayAdapter dataAdapter = new ArrayAdapter<>(extendedAct,
                R.layout.spinner_layout_for_vardies, lista);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout_for_vardies);
        hoursSP.setAdapter(dataAdapter);
    }






    @Override
    public void onClick(View v) {

        int vId = v.getId();
        if (vId == R.id.infoIV1) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_epipedo_sinidisis));
        } else if (vId == R.id.infoIV2) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_kinitikotita_anw_akrou));
        } else if (vId == R.id.infoIV3) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_kinitikotita_katw_akrou));
        } else if (vId == R.id.infoIV4) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_epipedo_sinidisis));
        } else if (vId == R.id.egrigorsiIV) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_egrigorsi));
        } else if (vId == R.id.monthAgeIV) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_monthAge));
        } else if (vId == R.id.eyesHandsIV) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_eyesHands));
        } else if (vId == R.id.ofthalmokinIV) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_ofthalmokin));
        } else if (vId == R.id.imianopsiaIV) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_imianopsia));
        } else if (vId == R.id.paresiFaceIV) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_paresiFace));
        } else if (vId == R.id.ptwsiAnwAkrouIV || vId == R.id.ptwsiKatwAkrouIV) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_ptwsiAnwAkrou));
        } else if (vId == R.id.ataxiaIV) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_ataxia));
        } else if (vId == R.id.astheticIV) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_asthetic));
        } else if (vId == R.id.afasiaNihssIV) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_afasia));
        } else if (vId == R.id.disarthriaIV) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_disarthria));
        } else if (vId == R.id.neglectIV) {
            Utils.getAlertDialogInfo(extendedAct, extendedAct.getString(R.string.info), extendedAct.getString(R.string.info_neglect));
        } else if (vId == R.id.updateButton) {

            insertUpdateButtonListener();
        }


    }

    private void insertUpdateButtonListener() {

        InputMethodManager imm = (InputMethodManager) extendedAct.getSystemService(INPUT_METHOD_SERVICE);
        if (extendedAct.getCurrentFocus() != null)
            imm.hideSoftInputFromWindow(extendedAct.getCurrentFocus().getWindowToken(), 0);

        if (Utils.isNetworkAvailable2(extendedAct ) &&
                !Utils.isEmpty(patientsTV, extendedAct)) {
            getSetTexts();
            transgroupID = Utils.getSplitSPartString(patientsTV.getText().toString(),",",3);

            buttonEnimerosi.startAnimation();

            if (!isThereTransgroupID)
                insertInfo();
            else
                updateInfo();
        }

    }



    private void insertInfo(){



        String query = Str_queries.setglobals(Utils.getUserID(extendedAct) , "2", Utils.getcompanyID(extendedAct)) + " \n " +
                getqueryProtokollo() +


                "INSERT INTO Nursing_Parakolouthisi_Neurologiki (TransGroupID, Date, Watch, epipedo_sinidisis, " +
                "kinitikotita_ano_aristerou_akrou,kinitikotita_kato_aristerou_akrou,kinitikotita_ano_dexiou_akrou,kinitikotita_kato_dexiou_akrou , afasia ," +
                "egrigorsi, minas_ilikia, matia_xeria, ofthalmokinitikotita, imianopsia, paresi_proswpou, ptwsi_anw_akrou, ptwsi_katw_akrou, ataxia, aisthitikotita," +
                "afasia_nihss, disarthria, neglect, userID) "
                + "VALUES (" + transgroupID + " , " +
                " dbo.timeToNum(CONVERT(datetime, " + "'" + date + " " + Utils.getCurrentTime() + "' , 103)), " +
                watchID + ", " + epipedo_sinidisis + ", " +
                kinitikotita_ano_aristerou_akrou + "," + kinitikotita_kato_aristerou_akrou + ", " +
                kinitikotita_ano_dexiou_akrou + ", " + kinitikotita_kato_dexiou_akrou + ", " + afasia + ", " +
                egrigorsi + ", " + minas_ilikia + ", " + matia_xeria + ", " + ofthalmokinitikotita + ", " +
                imianopsia + ", " + paresi_proswpou + ", " + ptwsi_anw_akrou + ", " + ptwsi_katw_akrou + ", " +
                ataxia + ", " + aisthitikotita + ", " + afasia_nihss + ", " + disarthria + ", " + neglect + " , " +

                userID + " );";

        AsyncTaskUpdate task = new AsyncTaskUpdate(extendedAct, query);
        task.listener = (AsyncGetUpdateResult) (activityFromSigxoneusi != null ? activityFromSigxoneusi : extendedAct);
        task.execute();

        synoloTV.setText(calculateNIHSSscore(editTextList));
    }




    private void updateInfo(){

        String query = "" +
                getqueryProtokollo() +
                "update Nursing_Parakolouthisi_Neurologiki " +
                " set Watch =" + watchID + " , epipedo_sinidisis = " + epipedo_sinidisis +
                " , kinitikotita_ano_aristerou_akrou = " + kinitikotita_ano_aristerou_akrou +
                " ,kinitikotita_kato_aristerou_akrou = " + kinitikotita_kato_aristerou_akrou +
                " ,kinitikotita_ano_dexiou_akrou = " + kinitikotita_ano_dexiou_akrou +
                " ,kinitikotita_kato_dexiou_akrou = " + kinitikotita_kato_dexiou_akrou +
                " ,afasia = " + afasia +
                " ,egrigorsi = " + egrigorsi +
                " ,minas_ilikia = " + minas_ilikia +
                " ,matia_xeria = " + matia_xeria +
                " ,ofthalmokinitikotita = " + ofthalmokinitikotita +
                " ,imianopsia = " + imianopsia +
                " ,paresi_proswpou = " + paresi_proswpou +
                " ,ptwsi_anw_akrou = " + ptwsi_anw_akrou +
                " ,ptwsi_katw_akrou = " + ptwsi_katw_akrou +
                " ,ataxia = " + ataxia +
                " ,aisthitikotita = " + aisthitikotita +
                " ,afasia_nihss = " + afasia_nihss +
                " ,disarthria = " + disarthria +
                " ,neglect = " + neglect +
                " , userID = " + userID +

                " where transgroupid = " + transgroupID +
                " and watch = " + watchID;
        AsyncTaskUpdate task = new AsyncTaskUpdate(extendedAct, query);
        task.listener = (AsyncGetUpdateResult) (activityFromSigxoneusi != null ? activityFromSigxoneusi : extendedAct);
        task.execute();

        synoloTV.setText(calculateNIHSSscore(editTextList));

    }




    private String getqueryProtokollo (){
        String q = "";
        if (!hasProtokollo) {
            q = "INSERT INTO Nursing_Parakolouthisi_Neurologiki_protokolo (TransGroupID, date, wraProseleusis, wraThromvolisis, wraProtokollou, userID)\n" +
                    "VALUES(\n" +
                    transgroupID + " , " +
                    " dbo.localtime() , " +
                    (wraProseleusisNewTV.getText().toString().isEmpty() ? "null"
                            : Utils.convertHourTomillisecondsGR(wraProseleusisNewTV.getText().toString())) + " , " +
                    (wraThromvolisisTV.getText().toString().isEmpty() ? "null"
                            : Utils.convertHourTomillisecondsGR(wraThromvolisisTV.getText().toString())) + " , " +
                    (wraProtokollouTV.getText().toString().isEmpty() ? "null"
                            : Utils.convertHourTomillisecondsGR(wraProtokollouTV.getText().toString())) + " , " +
                    userID + ") " +
                    "\n" +
                    "\n" ;
        }

        else {

          q =  "update Nursing_Parakolouthisi_Neurologiki_protokolo " +
                    " set" +
                    " wraProseleusis = "
                    + (wraProseleusisNewTV.getText().toString().isEmpty() ? "null"
                    : Utils.convertHourTomillisecondsGR(wraProseleusisNewTV.getText().toString())) +
                    " ,wraProtokollou = "
                    + (wraProtokollouTV.getText().toString().isEmpty() ? "null"
                    : Utils.convertHourTomillisecondsGR(wraProtokollouTV.getText().toString())) +
                    " ,wraThromvolisis = "
                    + (wraThromvolisisTV.getText().toString().isEmpty() ? "null"
                    : Utils.convertHourTomillisecondsGR(wraThromvolisisTV.getText().toString())) +
                    ", userID = " + userID +
                    " where transgroupid = " + transgroupID +
                    "\n" +
                    "\n" +
                    "\n";
        }

        return q;
    }


    private void getSetTexts(){

        watchID = Utils.checkNull(hoursSP.getSelectedItem().toString());
        epipedo_sinidisis = Utils.checkNull(epipSinidisisET.getText().toString());
        kinitikotita_ano_aristerou_akrou = Utils.checkNull(kinAnoAristerouAkrouET.getText().toString());
        kinitikotita_kato_aristerou_akrou = Utils.checkNull(kinKatoAristerouAkrouET.getText().toString());
        kinitikotita_ano_dexiou_akrou = Utils.checkNull(kinAnoDexiouAkrouET.getText().toString());
        kinitikotita_kato_dexiou_akrou = Utils.checkNull(kinKatoAkrouDexiouET.getText().toString());
        afasia = Utils.checkNull(afasiaET.getText().toString());
        egrigorsi = Utils.checkNull(egrigorsiET.getText().toString());
        minas_ilikia = Utils.checkNull(monthAgeET.getText().toString());
        matia_xeria = Utils.checkNull(eyesHandsET.getText().toString());
        ofthalmokinitikotita = Utils.checkNull(ofthalmokinET.getText().toString());
        imianopsia = Utils.checkNull(imianopsiaET.getText().toString());
        paresi_proswpou = Utils.checkNull(paresiFaceET.getText().toString());
        ptwsi_anw_akrou = Utils.checkNull(ptwsiAnwAkrouET.getText().toString());
        ptwsi_katw_akrou = Utils.checkNull(ptwsiKatwAkrouET.getText().toString());
        ataxia = Utils.checkNull(ataxiaET.getText().toString());
        aisthitikotita = Utils.checkNull(astheticET.getText().toString());
        afasia_nihss = Utils.checkNull(afasiaNihssET.getText().toString());
        disarthria = Utils.checkNull(disarthriaET.getText().toString());
        neglect = Utils.checkNull(neglectET.getText().toString());
        date = dateTV.getText().toString();
        wraProseleusis = Utils.checkNull(wraProseleusisNewTV.getText().toString());
        wraProtokollou = Utils.checkNull(wraProtokollouTV.getText().toString());
        wraThromvolisis = Utils.checkNull(wraThromvolisisTV.getText().toString());
        if (timeCH.isChecked())
            watchID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());
        else
            watchID = InfoSpecificLists.get3hoursID(hoursSP.getSelectedItem().toString());




    }

    public String calculateNIHSSscore(@NonNull List <EditText> lista){
        int sum =0;
        for (EditText t : lista){
            if (t != null && !t.getText().toString().trim().isEmpty()) {
                sum += Integer.parseInt(t.getText().toString());
            }
        }

        if(sum == 0){
            return "";
        }else{
            return String.valueOf(sum);
        }
    }


    @Override
    public void update(String str) {
        if (str.equals(extendedAct.getString(R.string.successful_update))) {
            isThereTransgroupID = true; // ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΞΑΝΑΠΑΤΗΣΕΙ ΤΗΝ ΙΔΙΑ ΣΤΙΓΜΗ ΤΟ ΚΟΥΜΠΙ ΓΙΑ ΝΑ ΜΗΝ ΚΑΝΕΙ ΠΑΛΙ ΙΝΣΕΡΤ

            Utils.timeHandlerDoneButton(buttonEnimerosi, extendedAct);
        }
        else
            Utils.timeHandlerErrorButton(buttonEnimerosi, extendedAct);


    }






    @Override
    public void updateLabel(TextView datetv) {
        super.updateLabel(datetv);
        getCurrentMetrisi(transgroupID);
    }


    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        getCurrentMetrisi(transgroupID);

    }

    @Override
    public void handleDialogClose(String transgroupid) {

        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);
        getCurrentMetrisi(transgroupID);


    }


}
