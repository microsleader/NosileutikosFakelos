package micros_leader.george.nosileutikosfakelos.OROFOI.f_Aksiologiseis;

import android.app.DatePickerDialog;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.github.ag.floatingactionmenu.OptionsFabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetCurrentMetrisi;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetCurrentMetrisi;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class NeurikiAksiologisi3Activity extends BasicActivity implements  AsyncGetUpdateResult, AsyncGetCurrentMetrisi , AsyncCompleteGetPatientsTask, MyDialogFragmentCloseListener,View.OnClickListener {


    public  TextView dateTV, patientsTV;
    private Spinner hoursSP;
    private EditText epipSinidisisET, kinAnoAkrouET, kinKatoAkrouET, afasiaET;
    private List <EditText> editTextList;
    private CircularProgressButton buttonEnimerosi;
    private ImageView infoimage1 ,infoimage2 ,infoimage3 ,infoimage4;
    private ArrayList<String> watchList;
    private Boolean isThereTransgroupID;
    private String companyID ,  date,
            epipedo_sinidisis , kinitikotita_ano_akrou, kinitikotita_kato_akrou, afasia;

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
        hoursSP = findViewById(R.id.hoursSP);
        patientsTV = findViewById(R.id.patientsTV);
        epipSinidisisET = findViewById(R.id.epipSinidisisET);
        kinAnoAkrouET = findViewById(R.id.kinAnoAkrouET);
        kinKatoAkrouET = findViewById(R.id.kinKatoAkrouET);
        afasiaET = findViewById(R.id.afasiaET);
        timeCH = findViewById(R.id.timeCH);
        buttonEnimerosi = findViewById(R.id.updateButton);
        buttonEnimerosi.setOnClickListener(this);
        dateTV.setText(Utils.getCurrentDate());
        infoimage1 = findViewById(R.id.infoIV1);
        infoimage1.setOnClickListener(this);
        infoimage2 = findViewById(R.id.infoIV2);
        infoimage2.setOnClickListener(this);
        infoimage3 = findViewById(R.id.infoIV3);
        infoimage3.setOnClickListener(this);
        infoimage4 = findViewById(R.id.infoIV4);
        infoimage4.setOnClickListener(this);

        watchList = InfoSpecificLists.get3HoursLista();

        editTextList = new ArrayList<>();
        editTextList.add(epipSinidisisET);
        editTextList.add(kinAnoAkrouET);
        editTextList.add(kinKatoAkrouET);
        editTextList.add(afasiaET);

        fab = findViewById(R.id.fab);
        fabInitialize();




        dateTVListener();

        checkboxListener();

        spinnerAdapter(watchList);

        getPatientsList(this,R.id.patientsTV,R.id.floorsSP);

        spinnerListener(); // ΜΕ ΤΟ ΠΟΥ ΜΠΑΙΝΕΙ Ο ΛΙΣΕΝΕΡ ΑΜΕΣΩΣ ΚΑΛΕΙ ΤΗ ΜΕΘΟΔΟ getCurrentMetrisi();

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
                new String[]{"Επίπεδο συνείδησης","Κινητηκότητα \n   άνω άκρου","Κινητηκότητα \n   κάτω άκρου","Αφασία"},
                new String[]{"epipedo_sinidisis","kinitikotita_ano_akrou","kinitikotita_kato_akrou","afasia"},
                true,true,false));
    }

    private void showSigkentrotika_ana_3oro(){


        startActivity(    tableView_sigkentrotika(Str_queries.getNEURIKI_AKSIOLOGISI_SIGKENTROTIKA(transgroupID,dateTV.getText().toString() ,false),
                transgroupID,
                null,
                new String[]{"Επίπεδο συνείδησης","Κινητηκότητα \n   άνω άκρου","Κινητηκότητα \n   κάτω άκρου","Αφασία"},
                new String[]{"epipedo_sinidisis","kinitikotita_ano_akrou","kinitikotita_kato_akrou","afasia"},
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




    private void dateTVListener(){
        final Calendar myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date12 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                dateTV.setText(sdf.format(myCalendar.getTime()));
                //   updateLabel(textView);
                getCurrentMetrisi(transgroupID);

            }

        };

        dateTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(NeurikiAksiologisi3Activity.this, date12, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
            kinAnoAkrouET.setText(Utils.convertObjToString(jsonObject.getString("kinitikotita_ano_akrou")));
            kinKatoAkrouET.setText(Utils.convertObjToString(jsonObject.getString("kinitikotita_kato_akrou")));
            afasiaET.setText(Utils.convertObjToString(jsonObject.getString("afasia")));

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
                "kinitikotita_ano_akrou,kinitikotita_kato_akrou,afasia ,userID) "
                + "VALUES (" + transgroupID + " , " +
                " dbo.timeToNum(CONVERT(datetime, " + "'" + date + " " + Utils.getCurrentTime() + "' , 103)), " +
                watchID + ", " + epipedo_sinidisis + ", " +
                kinitikotita_ano_akrou + "," + kinitikotita_kato_akrou + ", " + afasia + " , " + Utils.getUserID(this) + " );";

        AsyncTaskUpdate task = new AsyncTaskUpdate(NeurikiAksiologisi3Activity.this, query);
        task.listener =  NeurikiAksiologisi3Activity.this;
        task.execute();
    }





    private void updateInfo(){

        String query = "update Nursing_Parakolouthisi_Neurologiki " +
                " set Watch =" + watchID + " , epipedo_sinidisis = " + epipedo_sinidisis +
                " , kinitikotita_ano_akrou = " + kinitikotita_ano_akrou + " ,kinitikotita_kato_akrou = " + kinitikotita_kato_akrou +
                " ,afasia = " + afasia +
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
        kinitikotita_ano_akrou = Utils.checkNull(kinAnoAkrouET.getText().toString());
        kinitikotita_kato_akrou = Utils.checkNull(kinKatoAkrouET.getText().toString());
        afasia = Utils.checkNull(afasiaET.getText().toString());

        date = dateTV.getText().toString();
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
