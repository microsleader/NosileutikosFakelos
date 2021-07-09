package micros_leader.george.nosileutikosfakelos.OROFOI.f_Diaitologio;

import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.Listeners.SearchNosileuomenoListener;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class DiaitologioActivity_UNCLOMPLETED extends BasicActivity implements AsyncCompleteTask2,AsyncCompleteGetPatientsTask,AsyncGetUpdateResult,MyDialogFragmentCloseListener {


    private String companyID;
    private TextView dateTV, hoursTV, patientsTV;
    private EditText diaitaET, sxoliaET;
    private Spinner sitisiSinodouSP;
    private RecyclerView diaitesRV;
    private CircularProgressButton updateButton;
    private List<EditText> editTextList;
    private ArrayList sitisiSinodouLista;
    public RecyclerwViewDiaitologio_new_NOT_COMPLETED adapterRV;
    private ArrayList<Diaitologio> diaitologioLista;
    private LinearLayout linearLayout;
    public AlertDialog alertDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaitologio);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        companyID = Utils.getcompanyID(this);

        dateTV = findViewById(R.id.dateTV);
        hoursTV = findViewById(R.id.hourTV);
        patientsTV = findViewById(R.id.patientsTV);
        diaitaET = findViewById(R.id.diaitaET);
        sxoliaET = findViewById(R.id.sxoliaET);
        sitisiSinodouSP = findViewById(R.id.sitisiSinodouSP);
        updateButton = findViewById(R.id.updateButton);
        linearLayout = findViewById(R.id.katigoriesLayout);
        linearLayout.setVisibility(View.GONE);
        diaitesRV = findViewById(R.id.diaitesRV);

        editTextList = new ArrayList<>();
        editTextList.add(diaitaET);
        editTextList.add(sxoliaET);

        diaitologioLista = new ArrayList<>();
        patientsNosileuomenoi = new ArrayList<>();

        alertDialog = Utils.setLoadingAlertDialog(this);
        sitisiSinodouLista = InfoSpecificLists.getSitisiSinodouNames();
        //listaAdaptor = adapterRV.result;






        Utils.dateListener(DiaitologioActivity_UNCLOMPLETED.this, dateTV);
        Utils.timeListener(DiaitologioActivity_UNCLOMPLETED.this, hoursTV);

        getPatientsList(this,R.id.patientsTV,R.id.floorsSP);


        adapterRV = new RecyclerwViewDiaitologio_new_NOT_COMPLETED(this, diaitologioLista, R.layout.custom_diaitologio_adapter_layout);
        setRecyclerViewLinearLayout(diaitesRV ,R.id.diaitesRV,adapterRV);
//        diaitesRV.setLayoutManager(new LinearLayoutManager(DiaitologioActivity.this, LinearLayoutManager.VERTICAL, false));
//        diaitesRV.addItemDecoration(new DividerItemDecoration(DiaitologioActivity.this, LinearLayout.VERTICAL));
//        diaitesRV.setItemViewCacheSize(300);
//        diaitesRV.setHasFixedSize(true);
//        diaitesRV.setNestedScrollingEnabled(false);
//        adapter = new RecyclerViewDiaitologioAdaptor(DiaitologioActivity.this, diaitologioLista);
//        diaitesRV.setAdapter(adapter);




        spinnerAdapter();

        updateButtonListener();


    }

    private void fabListener() {

    }

    private void spinnerAdapter() {
        ArrayAdapter adapter = new ArrayAdapter<>(DiaitologioActivity_UNCLOMPLETED.this,
                R.layout.spinner_layout2, sitisiSinodouLista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sitisiSinodouSP.setAdapter(adapter);
    }


    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        getDiaitologio(transgroupID);

        patientsTV.setOnClickListener(new SearchNosileuomenoListener(DiaitologioActivity_UNCLOMPLETED.this, patientsNosileuomenoi));


    }




    @Override
    public void handleDialogClose(String transgroupid) {

        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT

        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);

        getDiaitologio(transgroupID);

        clearTexts();
    }


    public void getDiaitologio(String transgroupID) {

        if (Utils.isNetworkAvailable2(this)) {

            diaitologioLista.clear();

            alertDialog.show();
            String query = new Str_queries().getDIAITOLOGIO_PERSON(transgroupID);
            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = getApplicationContext();
            task.listener = DiaitologioActivity_UNCLOMPLETED.this;
            task.query = query;
            task.execute();


        }

    }

    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null) {

            // ΕΑΝ ΔΕΝ ΕΧΕΙ ΣΤΑΤΟΥΣ ΔΗΛΑΔΗ ΕΑΝ ΕΧΕΙ ΑΠΟΤΕΛΕΣΜΑΤΑ ΝΑ ΦΕΡΕΙ ΝΑ ΚΑΝΕΙ ΤΟ ΑΠΟ ΚΑΤΩ
            if (!results.getJSONObject(0).has("status")) {

                for (int i = 0; i < results.length(); i++) {


                    JSONObject currentDiaitologio = results.getJSONObject(i);

                    Diaitologio diaitologio = new Diaitologio();

                    diaitologio.setTransgroupid(Utils.convertObjToString(currentDiaitologio.get("TransGroupID")));
                    diaitologio.setId(Utils.convertObjToString(currentDiaitologio.get("ID")));
                    diaitologio.setDatefrom(Utils.convertObjToString(currentDiaitologio.get("dateF")));
                    diaitologio.setHourFrom(Utils.convertObjToString(currentDiaitologio.get("timeF")));
                    diaitologio.setDieta(Utils.convertObjToString(currentDiaitologio.get("Dieta")));
                    diaitologio.setSxolia(Utils.convertObjToString(currentDiaitologio.get("Remarks")));
                    diaitologio.setSitisiSinodou(InfoSpecificLists.getSitisiSinodouName(currentDiaitologio.getInt("sitisi_sinodou")));


                    diaitologioLista.add(diaitologio);

                }


                adapterRV.notifyDataSetChanged();

                alertDialog.dismiss();


            } else {
                diaitologioLista.clear();

                adapterRV.notifyDataSetChanged();
                alertDialog.dismiss();
            }


        }

        else
            alertDialog.dismiss();


        showHideLayout();
    }




    private void updateButtonListener(){

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) DiaitologioActivity_UNCLOMPLETED.this.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


                if (Utils.isNetworkAvailable2(DiaitologioActivity_UNCLOMPLETED.this ) &&
                        !Utils.isEmpty(patientsTV, DiaitologioActivity_UNCLOMPLETED.this)) {

                    String date = dateTV.getText().toString();
                    String time = hoursTV.getText().toString();



                        if (date.equals("") && !time.equals(""))
                                date = Utils.getCurrentDate();

                        else if (!date.equals("") && time.equals(""))
                            time = Utils.getCurrentTime();

                        else if (date.equals("") && time.equals("")){
                            date = "";
                            time = "";

                        }


                    String diaita = diaitaET.getText().toString().trim();
                    String sxolia = sxoliaET.getText().toString().trim();
                    String sitisiSinodou = InfoSpecificLists.getSitisiSinodouID(sitisiSinodouSP.getSelectedItem().toString());


                    updateButton.startAnimation();

                    String query = new Str_queries().getDIAITOLOGIO_INSERT(transgroupID,date,time, diaita, sxolia,sitisiSinodou,userID);
                    AsyncTaskUpdate task = new AsyncTaskUpdate(DiaitologioActivity_UNCLOMPLETED.this, query);
                    task.listener = DiaitologioActivity_UNCLOMPLETED.this;
                    task.execute();



                }

            }
        });

    }



    @Override
    public void update(String str) {

        if (str.equals("Πετυχημένη ενημέρωση")) {
            Utils.timeHandlerDoneButton(updateButton, DiaitologioActivity_UNCLOMPLETED.this);
            adapterRV.notifyDataSetChanged();

            getDiaitologio(transgroupID);
            clearTexts();

        } else {

            Utils.timeHandlerErrorButton(updateButton, DiaitologioActivity_UNCLOMPLETED.this);

        }
    }

    private void clearTexts() {
        dateTV.setText("");
        hoursTV.setText("");
        diaitaET.setText("");
        sxoliaET.setText("");
        sitisiSinodouSP.setSelection(0);
    }


    private void showHideLayout(){
        if (diaitologioLista.isEmpty())
            linearLayout.setVisibility(View.GONE);
        else
            linearLayout.setVisibility(View.VISIBLE);


    }


}

