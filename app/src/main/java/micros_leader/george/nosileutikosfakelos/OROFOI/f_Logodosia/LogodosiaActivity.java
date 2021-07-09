package micros_leader.george.nosileutikosfakelos.OROFOI.f_Logodosia;

import android.content.Intent;
import android.os.Handler;
import android.speech.RecognizerIntent;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class LogodosiaActivity extends BasicActivity implements  AsyncCompleteGetPatientsTask,AsyncCompleteTask2, AsyncGetUpdateResult, MyDialogFragmentCloseListener {

    private String username ,userid, companyID;
    public  TextView patientsTV;
    private EditText  searchET;
    public static EditText textET;
    private SwipeRefreshLayout refreshView;
    private RecyclerView logodosiaRV;
    private CircularProgressButton updateButton;
    public  AlertDialog alertDialog;
    public  RecyclerViewLogodosiesAdaptor adapter;
    private ArrayList<Logodosies>  logodosiesLista;
    private Button voiceBT2;
    private String keimenoText = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logodosia);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        userid = Utils.getUserID(this);
        username = Utils.getUserName(this);
        companyID = Utils.getcompanyID(this);

        patientsTV = findViewById(R.id.patientsTV);
        textET = findViewById(R.id.textET);
        searchET = findViewById(R.id.searchET);
        refreshView = findViewById(R.id.refreshView);
        updateButton = findViewById(R.id.updateButton);
        logodosiaRV = findViewById(R.id.logodosiaRV);
        voiceBT2 = findViewById(R.id.voiceBT2);
        logodosiesLista =  new ArrayList<>();

        logodosiaRV.setLayoutManager(new LinearLayoutManager(LogodosiaActivity.this, LinearLayoutManager.VERTICAL, false));
        logodosiaRV.addItemDecoration(new DividerItemDecoration(LogodosiaActivity.this, LinearLayout.VERTICAL));
        logodosiaRV.setItemViewCacheSize(300);
        logodosiaRV.setHasFixedSize(true);
        logodosiaRV.setNestedScrollingEnabled(false);

        adapter = new RecyclerViewLogodosiesAdaptor(LogodosiaActivity.this, logodosiesLista);
        logodosiaRV.setAdapter(adapter);

        alertDialog = Utils.setLoadingAlertDialog(this);

        getPatientsList(this, R.id.patientsTV ,R.id.floorsSP);

       // thereIsVoiceButton(R.id.voiceBT,textET);

        searcheETListener();

        updateButtonListener();

        swipeRefreshlayoutListsner();




        voiceBT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  getSpeechInput();
            }
        });




    }





    public void getSpeechInput() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "el-GR");


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Η συσκευή σας δεν υποστηρίζει την φωνητική γραφή", Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    keimenoText = textET.getText().toString() + " ";
                    textET.setText(keimenoText +=  result.get(0));
                }
                break;
        }
    }




    private void updateButtonListener(){

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) LogodosiaActivity.this.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                if (Utils.isNetworkAvailable2(LogodosiaActivity.this) &&
                    !Utils.isEmpty(patientsTV,LogodosiaActivity.this)) {

                    String logodosiaText = textET.getText().toString();
                    String date = Utils.getCurrentDate();
                    String time = Utils.getCurrentTime();

                    //alertDialog.show();
                    updateButton.startAnimation();

                    String query = new Str_queries().getLOGODOSIA_INSERT(transgroupID,date,time, userid, logodosiaText);
                    AsyncTaskUpdate task = new AsyncTaskUpdate(LogodosiaActivity.this, query);
                    task.listener = LogodosiaActivity.this;
                    task.execute();



                }

            }
        });

    }


    @Override
    public void update(String str) {

        if (str.equals("Πετυχημένη ενημέρωση")) {

            Utils.timeHandlerDoneButton(updateButton, LogodosiaActivity.this);
            textET.setText("");

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    getLogodosies();

                }
            }, 2000);


        } else {

            Utils.timeHandlerErrorButton(updateButton, LogodosiaActivity.this);

        }
    }





    private void getLogodosies(){

        if (Utils.isNetworkAvailable2(this)) {

            logodosiesLista.clear();

            alertDialog.show();
            String query = new Str_queries().getLOGODOSIES_PERSON(transgroupID);
            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = getApplicationContext();
            task.listener = LogodosiaActivity.this;
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



                    JSONObject cureentLogodosia = results.getJSONObject(i);

                    Logodosies logodosies = new Logodosies();

                    logodosies.setTransgroupid(Utils.convertObjToString(cureentLogodosia.get("transgroupID")));
                    logodosies.setId(Utils.convertObjToString(cureentLogodosia.get("id")));
                    logodosies.setUserID(Utils.convertObjToString(cureentLogodosia.get("userID")));
                    logodosies.setName(Utils.convertObjToString(cureentLogodosia.get("name")));
                    logodosies.setDate(Utils.convertObjToString(cureentLogodosia.get("date")));
                    logodosies.setTime(Utils.convertObjToString(cureentLogodosia.get("time")));
                    logodosies.setLogodosia(Utils.convertObjToString(cureentLogodosia.get("logodosia")));


                    logodosiesLista.add(logodosies);

                }


                adapter.notifyDataSetChanged();

                alertDialog.dismiss();


            }
            else {
                logodosiesLista.clear();
              //  adapter = new RecyclerViewLogodosiesAdaptor(LogodosiaActivity.this, logodosiesLista);
                adapter.notifyDataSetChanged();
                alertDialog.dismiss();
            }

        }

        else {
          //  patientsTV.setText(R.string.no_data);
            alertDialog.dismiss();
        }

        refreshView.setRefreshing(false);

    }


    private void swipeRefreshlayoutListsner() {
        refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getLogodosies();
            }
        });
    }



    @Override
    public void handleDialogClose(String transgroupid) {

        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT
        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);
        getLogodosies();
    }



    private void searcheETListener(){
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());
            }
        });
    }




    private void filter(String text){

        ArrayList <Logodosies> filteredList =  new ArrayList();

        for (Logodosies item : logodosiesLista){
//            if (item.getName().toLowerCase().contains(text.toLowerCase())){
//                filteredList.add(item);
//            }

            if (item.getDate().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }


        RecyclerViewLogodosiesAdaptor customAdapter =  new RecyclerViewLogodosiesAdaptor(LogodosiaActivity.this, filteredList);
        customAdapter.filterList(filteredList);
        logodosiaRV.setAdapter(customAdapter);
    }


    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

      //  getPatientsList(this, R.id.patientsTV ,R.id.floorsSP);

        getLogodosies();

    }






}
