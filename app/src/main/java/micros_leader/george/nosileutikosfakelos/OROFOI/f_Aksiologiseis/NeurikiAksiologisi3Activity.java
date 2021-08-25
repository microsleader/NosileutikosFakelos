package micros_leader.george.nosileutikosfakelos.OROFOI.f_Aksiologiseis;

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
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetCurrentMetrisi;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class NeurikiAksiologisi3Activity extends BasicActivity implements  AsyncGetUpdateResult, AsyncGetCurrentMetrisi ,
        AsyncCompleteGetPatientsTask, MyDialogFragmentCloseListener,View.OnClickListener {


    public  TextView dateTV, patientsTV, wraProseleusisNewTV, wraProtokollouTV, wraThromvolisisTV, synoloTV;
    private Spinner hoursSP;
    private EditText epipSinidisisET, kinAnoAristerouAkrouET, kinAnoDexiouAkrouET, kinKatoAkrouDexiouET, kinKatoAristerouAkrouET, afasiaET, egrigorsiET, monthAgeET, eyesHandsET, ofthalmokinET, imianopsiaET, paresiFaceET, ptwsiAnwAkrouET, ptwsiKatwAkrouET,
    ataxiaET, astheticET, afasiaNihssET, disarthriaET, neglectET;
    private List <EditText> editTextList;
    private CircularProgressButton buttonEnimerosi;
    private ImageView infoimage1 ,infoimage2 ,infoimage3 ,infoimage4, infoimage5, infoimage6, infoimage8, infoimage9, infoimage10, infoimage11, infoimage12, infoimage13, infoimage14, infoimage15, infoimage16, infoimage17, infoimage18;
    private ArrayList<String> watchList;
    private Boolean isThereTransgroupID;
    private String companyID ,  date,
            epipedo_sinidisis , kinitikotita_ano_aristerou_akrou, kinitikotita_kato_aristerou_akrou, kinitikotita_ano_dexiou_akrou, kinitikotita_kato_dexiou_akrou, afasia,
            egrigorsi, minas_ilikia, matia_xeria, ofthalmokinitikotita, imianopsia, paresi_proswpou,
            ptwsi_anw_akrou, ptwsi_katw_akrou, ataxia, aisthitikotita, afasia_nihss, disarthria, neglect, wraProseleusis, wraProtokollou, wraThromvolisis, sumNIHSS;

    private CheckBox timeCH;

    private OptionsFabLayout fab;

    public AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuriki_aksiologisi3);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        companyID = getIntent().getStringExtra("companyID");

        alertDialog =  Utils.setLoadingAlertDialog(this);


        dateTV = findViewById(R.id.dateTV);
        Utils.dateListener(this,dateTV);
        hoursSP = findViewById(R.id.hoursSP);
        patientsTV = findViewById(R.id.patientsTV);
        epipSinidisisET = findViewById(R.id.epipSinidisisET);
        kinAnoAristerouAkrouET = findViewById(R.id.kinAnoAristerouAkrouET);
        kinKatoAristerouAkrouET = findViewById(R.id.kinKatoAristerouAkrouET);
        kinAnoDexiouAkrouET = findViewById(R.id.kinAnoDexiouAkrouET);
        kinKatoAkrouDexiouET = findViewById(R.id.kinKatoAkrouDexiouET);
        afasiaET = findViewById(R.id.afasiaET);
        timeCH = findViewById(R.id.timeCH);
        buttonEnimerosi = findViewById(R.id.updateButton);
        buttonEnimerosi.setOnClickListener(this);
        dateTV.setText(Utils.getCurrentDate());
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
        infoimage1.setOnClickListener(this);
        infoimage2 = findViewById(R.id.infoIV2);
        infoimage2.setOnClickListener(this);
        infoimage3 = findViewById(R.id.infoIV3);
        infoimage3.setOnClickListener(this);
        infoimage4 = findViewById(R.id.infoIV4);
        infoimage4.setOnClickListener(this);
        infoimage5 = findViewById(R.id.egrigorsiIV);
        infoimage5.setOnClickListener(this);
        infoimage6 = findViewById(R.id.monthAgeIV);
        infoimage6.setOnClickListener(this);
        infoimage8 = findViewById(R.id.eyesHandsIV);
        infoimage8.setOnClickListener(this);
        infoimage9 = findViewById(R.id.ofthalmokinIV);
        infoimage9.setOnClickListener(this);
        infoimage10 = findViewById(R.id.imianopsiaIV);
        infoimage10.setOnClickListener(this);
        infoimage11 = findViewById(R.id.paresiFaceIV);
        infoimage11.setOnClickListener(this);
        infoimage12 = findViewById(R.id.ptwsiAnwAkrouIV);
        infoimage12.setOnClickListener(this);
        infoimage13 = findViewById(R.id.ptwsiKatwAkrouIV);
        infoimage13.setOnClickListener(this);
        infoimage14 = findViewById(R.id.ataxiaIV);
        infoimage14.setOnClickListener(this);
        infoimage15 = findViewById(R.id.astheticIV);
        infoimage15.setOnClickListener(this);
        infoimage16 = findViewById(R.id.afasiaNihssIV);
        infoimage16.setOnClickListener(this);
        infoimage17 = findViewById(R.id.disarthriaIV);
        infoimage17.setOnClickListener(this);
        infoimage18 = findViewById(R.id.neglectIV);
        infoimage18.setOnClickListener(this);
    //    synoloTV = findViewById(R.id.synoloTV);

        watchList = InfoSpecificLists.get3HoursLista();

        editTextList = new ArrayList<>();
        editTextList.add(epipSinidisisET);
        editTextList.add(kinAnoAristerouAkrouET);
        editTextList.add(kinKatoAristerouAkrouET);
        editTextList.add(kinAnoDexiouAkrouET);
        editTextList.add(kinKatoAkrouDexiouET);
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

        fab = findViewById(R.id.fab);
        fabInitialize();


        checkboxListener();

        spinnerAdapter(watchList);

        getPatientsList(this,R.id.patientsTV,R.id.floorsSP);

        spinnerListener(); // ΜΕ ΤΟ ΠΟΥ ΜΠΑΙΝΕΙ Ο ΛΙΣΕΝΕΡ ΑΜΕΣΩΣ ΚΑΛΕΙ ΤΗ ΜΕΘΟΔΟ getCurrentMetrisi();

        Utils.timeListener(this, wraProseleusisNewTV);
        Utils.timeListener(this, wraProtokollouTV);
        Utils.timeListener(this, wraThromvolisisTV);

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

        startActivity(    tableView_sigkentrotika(Str_queries.getNEURIKI_AKSIOLOGISI_SIGKENTROTIKA(transgroupID,dateTV.getText().toString() , true),
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


        startActivity(    tableView_sigkentrotika(Str_queries.getNEURIKI_AKSIOLOGISI_SIGKENTROTIKA(transgroupID,dateTV.getText().toString() ,false),
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

        if (Utils.isNetworkAvailable(NeurikiAksiologisi3Activity.this)) {
            alertDialog.show();
            String query = "select * from Nursing_Parakolouthisi_Neurologiki " +
                    " where transGroupID = " + transgroupid +
                    " and  date = " + " dbo.timeToNum(CONVERT(datetime, " + "'" + dateTV.getText().toString() + "' , 103)) " +
                    " and watch = " + watchID;
            AsyncTaskGetCurrentMetrisi task = new AsyncTaskGetCurrentMetrisi();
            task.ctx = getApplicationContext();
            task.listener = NeurikiAksiologisi3Activity.this;
            task.query = query;
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
        //    synoloTV.setText(Utils.convertObjToString(jsonObject.getString("sumNIHSS")));
            wraThromvolisisTV.setText(Utils.convertObjToString(jsonObject.getString("wraThromvolisis")));
            wraProtokollouTV.setText(Utils.convertObjToString(jsonObject.getString("wraProtokollou")));
            wraProseleusisNewTV.setText(Utils.convertObjToString(jsonObject.getString("wraProseleusis")));

            alertDialog.dismiss();
        } else {
            isThereTransgroupID = false;
            Utils.edittextSetText(editTextList, "");
            alertDialog.dismiss();
        }
    }




    private void spinnerAdapter(ArrayList <String> lista) {
        ArrayAdapter dataAdapter = new ArrayAdapter<>(NeurikiAksiologisi3Activity.this,
                R.layout.spinner_layout_for_vardies, lista);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout_for_vardies);
        hoursSP.setAdapter(dataAdapter);
    }






    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.infoIV1:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_epipedo_sinidisis));
                break;

            case R.id.infoIV2:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_kinitikotita_anw_akrou));
                break;

            case R.id.infoIV3:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_kinitikotita_katw_akrou));
                break;

            case R.id.infoIV4:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_epipedo_sinidisis));
                break;

            case R.id.egrigorsiIV:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_egrigorsi));
                break;

            case R.id.monthAgeIV:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_monthAge));
            break;

            case R.id.eyesHandsIV:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_eyesHands));
                break;

            case R.id.ofthalmokinIV:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_ofthalmokin));
                break;

            case R.id.imianopsiaIV:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_imianopsia));
                break;

            case R.id.paresiFaceIV:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_paresiFace));
                break;

            case R.id.ptwsiAnwAkrouIV:
            case R.id.ptwsiKatwAkrouIV:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_ptwsiAnwAkrou));
                break;

            case R.id.ataxiaIV:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_ataxia));
                break;

            case R.id.astheticIV:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_asthetic));
                break;

            case R.id.afasiaNihssIV:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_afasia));
                break;

            case R.id.disarthriaIV:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_disarthria));
                break;

            case R.id.neglectIV:
                // do your code
                Utils.getAlertDialogInfo(NeurikiAksiologisi3Activity.this, getString(R.string.info),
                        getString(R.string.info_neglect));
                break;

            case R.id.updateButton:
                // do your code

            insertUpdateButtonListener();
                break;


            default:
                break;
        }


    }

    private void insertUpdateButtonListener() {

        InputMethodManager imm = (InputMethodManager) NeurikiAksiologisi3Activity.this.getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null)
             imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        if (Utils.isNetworkAvailable2(NeurikiAksiologisi3Activity.this ) &&
                !Utils.isEmpty(patientsTV, NeurikiAksiologisi3Activity.this)) {
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
        String query = "INSERT INTO Nursing_Parakolouthisi_Neurologiki (TransGroupID, Date, Watch, epipedo_sinidisis, " +
                "kinitikotita_ano_aristerou_akrou,kinitikotita_kato_aristerou_akrou,kinitikotita_ano_dexiou_akrou,kinitikotita_kato_dexiou_akrou , afasia ," +
                "egrigorsi, minas_ilikia, matia_xeria, ofthalmokinitikotita, imianopsia, paresi_proswpou, ptwsi_anw_akrou, ptwsi_katw_akrou, ataxia, aisthitikotita," +
                "afasia_nihss, disarthria, neglect, wraProseleusis, wraThromvolisis, wraProtokollou, userID) "
                + "VALUES (" + transgroupID + " , " +
                " dbo.timeToNum(CONVERT(datetime, " + "'" + date + " " + Utils.getCurrentTime() + "' , 103)), " +
                watchID + ", " + epipedo_sinidisis + ", " +
                kinitikotita_ano_aristerou_akrou + "," + kinitikotita_kato_aristerou_akrou + ", " +
                kinitikotita_ano_dexiou_akrou + ", " + kinitikotita_kato_dexiou_akrou + ", " + afasia + ", " +
                egrigorsi + ", " + minas_ilikia + ", " + matia_xeria + ", " + ofthalmokinitikotita + ", " +
                imianopsia + ", " + paresi_proswpou + ", " + ptwsi_anw_akrou + ", " + ptwsi_katw_akrou + ", " +
                ataxia + ", " + aisthitikotita + ", " + afasia_nihss + ", " + disarthria + ", " + neglect + " , " +
                " dbo.timeToNum(CONVERT(time, " + "'" + wraProseleusisNewTV.getText().toString() + "' , 103)), "+
                " dbo.timeToNum(CONVERT(time, " + "'" + wraThromvolisisTV.getText().toString()  + "' , 103)), " +
                " dbo.timeToNum(CONVERT(time, " + "'" + wraProtokollouTV.getText().toString()  + "' , 103)), " +
                Utils.getUserID(this) + " );";

        AsyncTaskUpdate task = new AsyncTaskUpdate(NeurikiAksiologisi3Activity.this, query);
        task.listener =  NeurikiAksiologisi3Activity.this;
        task.execute();
    }




    private void updateInfo(){

        String query = "update Nursing_Parakolouthisi_Neurologiki " +
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
                " ,wraProseleusis = dbo.timeToNum(CONVERT(time, " + "'" + wraProseleusisNewTV.getText().toString() + "' , 103))" +
                " ,wraProtokollou = dbo.timeToNum(CONVERT(time, " + "'" + wraProtokollouTV.getText().toString()  +"' , 103)) " +
                " ,wraThromvolisis = dbo.timeToNum(CONVERT(time, " + "'" + wraThromvolisisTV.getText().toString() +"' , 103)) " +
               // " ,sumNIHSS = " + synoloTV +
                " , userID = " + Utils.getUserID(this) +
                " where transgroupid = " + transgroupID +
                " and watch = " + watchID;
        AsyncTaskUpdate task = new AsyncTaskUpdate(NeurikiAksiologisi3Activity.this,query);
        task.listener = NeurikiAksiologisi3Activity.this;
        task.execute();



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
       // sumNIHSS = Utils.checkNull(synoloTV.getText().toString());
        date = dateTV.getText().toString();
        wraProseleusis = Utils.checkNull(wraProseleusisNewTV.getText().toString());
        wraProtokollou = Utils.checkNull(wraProtokollouTV.getText().toString());
        wraThromvolisis = Utils.checkNull(wraThromvolisisTV.getText().toString());
        if (timeCH.isChecked())
            watchID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());
        else
            watchID = InfoSpecificLists.get3hoursID(hoursSP.getSelectedItem().toString());

    }


    @Override
    public void update(String str) {
        if (str.equals(getString(R.string.successful_update))) {
            isThereTransgroupID = true; // ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΞΑΝΑΠΑΤΗΣΕΙ ΤΗΝ ΙΔΙΑ ΣΤΙΓΜΗ ΤΟ ΚΟΥΜΠΙ ΓΙΑ ΝΑ ΜΗΝ ΚΑΝΕΙ ΠΑΛΙ ΙΝΣΕΡΤ

            Utils.timeHandlerDoneButton(buttonEnimerosi, NeurikiAksiologisi3Activity.this);
        }
        else
            Utils.timeHandlerErrorButton(buttonEnimerosi, NeurikiAksiologisi3Activity.this);


    }





//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_diaitologio, menu);
//        return true;
//    }






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
