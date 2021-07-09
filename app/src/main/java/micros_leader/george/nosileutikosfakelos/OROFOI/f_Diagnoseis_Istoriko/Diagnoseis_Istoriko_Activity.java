package micros_leader.george.nosileutikosfakelos.OROFOI.f_Diagnoseis_Istoriko;

import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Diagnoseis_Istoriko_Activity extends BasicActivity implements AsyncCompleteTask, MyDialogFragmentCloseListener, AsyncGetUpdateResult,AsyncCompleteGetPatientsTask {

    private String username ,userid, companyID;
    private CircularProgressButton updateButton;
    private TextView patientsTV, diagnosiEisodouTV, diagnosiEisICD10TV,deutereuousaDiagnEisodouICD10TV;

    private String weight, height, epaggelma, oikKatastasi, oikIstoriko, parousaNosos, lipsiFarmakon, atomAnamnistiko;
    private TextView weightET, heightET , oikKatastasiET, oikIstorikoET, parousaNososET,lipsiFarmakonET, atomAnamnistikoET , epaggelmaET;
    private boolean isThereID;
    private List<TextView> textViewList;


    public AlertDialog alertDialog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnoseis_istoriko);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        userid = Utils.getUserID(this);
        username = Utils.getUserName(this);
        companyID = Utils.getcompanyID(this);

        diagnosiEisodouTV = findViewById(R.id.diagnEisodouTV);
        diagnosiEisICD10TV = findViewById(R.id.diagnEisodouICD10TV);
        deutereuousaDiagnEisodouICD10TV = findViewById(R.id.deutereuousaDiagnEisodouICD10TV);
        weightET = findViewById(R.id.weightET);
        heightET = findViewById(R.id.heightET);
        epaggelmaET = findViewById(R.id.epaggelmaET);
        oikKatastasiET = findViewById(R.id.oikKatastasiET);
        oikIstorikoET = findViewById(R.id.oikIstorikoET);
        parousaNososET = findViewById(R.id.parousaNososET);
        lipsiFarmakonET = findViewById(R.id.lipsiFarmakonET);
        atomAnamnistikoET = findViewById(R.id.atomAnamnistikoET);


        textViewList = new ArrayList<>();
        textViewList.add(weightET);
        textViewList.add(heightET);
        textViewList.add(epaggelmaET);
        textViewList.add(oikKatastasiET);
        textViewList.add(oikIstorikoET);
        textViewList.add(parousaNososET);
        textViewList.add(lipsiFarmakonET);
        textViewList.add(atomAnamnistikoET);

      //  updateButton = findViewById(R.id.updateButton);
        patientsTV = findViewById(R.id.patientsTV);

        patientsNosileuomenoi = new ArrayList<>();

       // updateButtonListener();

        alertDialog = Utils.setLoadingAlertDialog(this);

        getPatientsList(this, R.id.patientsTV,R.id.floorsSP);

    }




    private void getDiagnosisIstoriko(){

        if (Utils.isNetworkAvailable2(this)) {


            alertDialog.show();
            String query = new Str_queries().getDiagnosisIstoriko(companyID,transgroupID);
            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = Diagnoseis_Istoriko_Activity.this;
            task.listener = Diagnoseis_Istoriko_Activity.this;
            task.query = query;
            task.execute();


        }
    }


    @Override
    public void taskComplete(JSONArray results) throws JSONException {

        if (results != null){

            if (!results.getJSONObject(0).has("status")) {

                    isThereID = true;
                    for (int i = 0; i < results.length(); i++) {

                        JSONObject diagnIstoriko = results.getJSONObject(i);


                        weightET.setText(Utils.checkIfThereIsComma(Utils.convertObjToString(diagnIstoriko.get("varos"))));
                        heightET.setText(Utils.checkIfThereIsComma(Utils.convertObjToString(diagnIstoriko.get("ipsos"))));
                        oikKatastasiET.setText(Utils.convertObjToString(diagnIstoriko.get("ikogeniaki_katastasi")));
                        oikIstorikoET.setText(Utils.convertObjToString(diagnIstoriko.get("ikogeniako_istoriko")));
                        parousaNososET.setText(Utils.convertObjToString(diagnIstoriko.get("parousa_nosos")));
                        lipsiFarmakonET.setText(Utils.convertObjToString(diagnIstoriko.get("lipsi_farmakon")));
                        atomAnamnistikoET.setText(Utils.convertObjToString(diagnIstoriko.get("atomiko_anamnistiko")));
                        epaggelmaET.setText(Utils.convertObjToString(diagnIstoriko.get("epaggelma")));

                        diagnosiEisodouTV.setText(Utils.convertObjToString(diagnIstoriko.get("diagnosisIn")));
                        diagnosiEisICD10TV.setText(Utils.convertObjToString(diagnIstoriko.get("diagnosisInICD10")));
                        deutereuousaDiagnEisodouICD10TV.setText(Utils.convertObjToString(diagnIstoriko.get("diagnosisInSecond")));


                    }
                }

                else {
                    getSetTexts(false);
                    isThereID = false;
                }



            alertDialog.dismiss();

        }

    }

    @Override
    public void taskCompleteGetVardies(JSONArray results) throws JSONException {

    }


    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        getDiagnosisIstoriko();

    }




    private void updateButtonListener() {

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) Diagnoseis_Istoriko_Activity.this.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                if (Utils.isNetworkAvailable2(Diagnoseis_Istoriko_Activity.this)) {

                    getSetTexts(true);
                    //alertDialog.show();
                    updateButton.startAnimation();
                    String query ;


                    if (!isThereID){

                        // INSERT
                        query = new Str_queries().getISTORIKO_INSERT( transgroupID , weight,  height
                                , oikKatastasi,  oikIstoriko , parousaNosos,  lipsiFarmakon
                                , atomAnamnistiko,  epaggelma);


                    }

                    else{
                        //UPDATE
                         query = new Str_queries().getISTORIKO_UPDATE( transgroupID , weight,  height
                                , oikKatastasi,  oikIstoriko , parousaNosos,  lipsiFarmakon
                                , atomAnamnistiko,  epaggelma);

                    }

                    AsyncTaskUpdate task = new AsyncTaskUpdate(Diagnoseis_Istoriko_Activity.this, query);
                    task.listener = (AsyncGetUpdateResult) Diagnoseis_Istoriko_Activity.this;
                    task.execute();







                }

            }
        });
    }


    private void getSetTexts(Boolean isForQuery){

        if (isForQuery) {

            weight = Utils.checkNull(weightET.getText().toString());
            height = Utils.checkNull(heightET.getText().toString());
            oikKatastasi = oikKatastasiET.getText().toString().trim();
            oikIstoriko = oikIstorikoET.getText().toString().trim();
            parousaNosos = parousaNososET.getText().toString().trim();
            lipsiFarmakon = lipsiFarmakonET.getText().toString().trim();
            atomAnamnistiko = atomAnamnistikoET.getText().toString().trim();
            epaggelma = epaggelmaET.getText().toString().trim();

        }

        else
            Utils.textViewSetText(textViewList, "");




    }

    @Override
    public void update(String str) {

        if (str.equals(getString(R.string.successful_update))) {
            Utils.timeHandlerDoneButton(updateButton, Diagnoseis_Istoriko_Activity.this);
            isThereID = true;
        }

         else
            Utils.timeHandlerErrorButton(updateButton, Diagnoseis_Istoriko_Activity.this);



    }


    @Override
    public void handleDialogClose(String transgroupid) {

    //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT
        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);

        getDiagnosisIstoriko();
    }



}
