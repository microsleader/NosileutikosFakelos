package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_istoriko;

import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_logodosia.Nosil_Anafora_Logodosia_Activity;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Nosil_IstorikoActivity_OLD extends BasicActivity implements AsyncCompleteGetPatientsTask, AsyncCompleteTask2, AsyncGetUpdateResult, MyDialogFragmentCloseListener {

    private RecyclerView nos_IstorikoRV;
    private RecyclerViewNos_IstorikoAdaptor adapter;
    private ArrayList<PatientsOfTheDay> patientsNosileuomenoi;
    private ArrayList<Nosil_IstorikoList> adapterLista;
    public AlertDialog alertDialog;
    private FloatingActionButton fabMenu;
    private CircularProgressButton updateButton;
    public TextView patientsTV;
    private boolean isThereTransgroupID = false;
    public String transgroupID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nosil__istoriko);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        patientsTV = findViewById(R.id.patientsTV);

        nos_IstorikoRV = findViewById(R.id.nos_IstorikoRV);
        updateButton = findViewById(R.id.updateButton);
        alertDialog = Utils.setLoadingAlertDialog(this);
      //  fabMenu = findViewById(R.id.fabMenu);

       // nos_IstorikoRV.setLayoutManager(new LinearLayoutManager(Nosil_IstorikoActivity.this, LinearLayoutManager.VERTICAL, false));
      //  nos_IstorikoRV.addItemDecoration(new DividerItemDecoration(Nosil_IstorikoActivity.this, LinearLayout.VERTICAL));
       // nos_IstorikoRV.setItemViewCacheSize(300);
        nos_IstorikoRV.setItemViewCacheSize(20);
        nos_IstorikoRV.setDrawingCacheEnabled(true);
        nos_IstorikoRV.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        nos_IstorikoRV.setHasFixedSize(true);
        nos_IstorikoRV.setNestedScrollingEnabled(false);

        adapter = new RecyclerViewNos_IstorikoAdaptor(Nosil_IstorikoActivity_OLD.this, InfoSpecificLists.getNosil_Istoriko_lista());
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

        //  ΣΤΙΣ ΣΥΓΚΕΚΡΙΜΕΝΕΣ ΘΕΣΕΙΣ ΝΑ ΕΙΝΑΙ ΤΟ GridLayoutManager.SpanSize ΜΙΑ ΘΕΣΗ ΕΠΕΙΔΗ ΕΙΝΑΙ ΟΙ ΤΙΤΛΟΙ
              if (position == 0 || position == 20 || position == 29
                      || position == 32  || position == 41
                      || position == 57  || position == 68 || position == 83  || position == 92
                      || position == 104  || position == 110 || position == 115)
                  return 2;
              else  return 1;

            }
        });

        nos_IstorikoRV.setLayoutManager(manager);


        managefabMenuIcon();
        updateButtonListener();


    }

    @Override
    protected void onStart() {
        super.onStart();

        nos_IstorikoRV.setAdapter(adapter);

        adapterLista = adapter.result;

        getPatientsList(this, R.id.patientsTV ,R.id.floorsSP);

    }

    private void managefabMenuIcon() {



     fabMenu.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             DialogFragmentFarmaka df =  new DialogFragmentFarmaka();
             Bundle putextra = new Bundle();
             putextra.putString("transgroupID", transgroupID);
             df.setArguments(putextra);
             df.show(getSupportFragmentManager() , "Dialog");         }
     });
    }








    private void getNosil_Istoriko(){

        if (Utils.isNetworkAvailable2(this)) {


            alertDialog.show();
            String query = new Str_queries().getNOSIL_ISTORIKO_PERSON(transgroupID);
            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = getApplicationContext();
            task.listener = Nosil_IstorikoActivity_OLD.this;
            task.query = query;
            task.execute();



        }
    }


    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null) {


            // ΕΑΝ ΔΕΝ ΕΧΕΙ ΣΤΑΤΟΥΣ ΔΗΛΑΔΗ ΕΑΝ ΕΧΕΙ ΑΠΟΤΕΛΕΣΜΑΤΑ ΝΑ ΦΕΡΕΙ ΝΑ ΚΑΝΕΙ ΤΟ ΑΠΟ ΚΑΤΩ
            if (!results.getJSONObject(0).has("status")) {

                isThereTransgroupID = true;

                ArrayList <String> valuesFromJSONlista = new ArrayList();

                JSONObject curIstoriko = results.getJSONObject(0);



                Iterator<String> keys = curIstoriko.keys();
                while (keys.hasNext()) {

                    String str_Name = keys.next();
                    String value = Utils.convertObjToString(curIstoriko.optString(str_Name));

                    valuesFromJSONlista.add(value);




                }

            // ΔΗΛΩΝΟΥΜΕ ΘΕΣΗ 0 Η ΟΠΟΙΑ ΣΕ ΚΑΘΕ ΕΠΑΝΑΛΑΨΗ ΑΝΕΒΑΙΝΕΙ ΚΑΤΑ ΜΙΑ ΤΙΜΗ (1) ΚΑΙ ΕΠΙΣΗΣ
                // ΜΙΑ ΑΚΟΜΑ ΟΤΑΝΒΡΙΣΚΕΙΣ ΣΥΓΚΕΚΡΙΜΕΝΕΣ ΘΕΣΕΙΣ ΟΠΟΥ ΕΙΝΑΙ ΟΙ ΤΙΤΛΟΙ
                //ΜΕ ΑΠΟΤΕΛΕΣΜΑ ΟΙ ΤΙΜΕΣ ΠΟΥ ΤΡΑΒΑΩ ΑΠΟ ΤΟΝ ΣΕΡΒΕΡ ΝΑ ΙΣΟΔΥΝΑΜΟΥΝ ΜΕ ΤΗ ΛΙΣΤΑ ΠΟΥ ΕΧΩ ΜΕ ΤΑ ΠΕΔΙΑ

                int position = 0;
                //ΔΙΑΓΡΑΦΩ ΤΑ ΔΥΟ ΠΡΩΤΑ ΠΟΥ ΔΕΝ ΤΑ ΘΕΛΩ
                valuesFromJSONlista.remove(0);
                valuesFromJSONlista.remove(0);



                //------------------- PROSORINO GIA DOKIMES-----------
//                for (int i=38; i<104; i++) {
//                    valuesFromJSONlista.remove(valuesFromJSONlista.size()-1);
//                }
                //-------------------------------------------------------

                for (int i=0; i<valuesFromJSONlista.size(); i++) {

                    if (position == 0 || position == 20 || position == 29
                            || position == 32  || position == 41
                            || position == 57  || position == 68 || position == 83  || position == 92
                            || position == 104  || position == 110 || position == 115) {
                        position++;
                    }


                    if (adapterLista.get(position).getTextViewSForRV() != null)
                                adapterLista.get(position).getTextViewSForRV().setValue(valuesFromJSONlista.get(i));


                            else if (adapterLista.get(position).getEditTextForRV() != null)
                                adapterLista.get(position).getEditTextForRV().setValue(valuesFromJSONlista.get(i));


                            else if (adapterLista.get(position).getSpinnersForRV() != null)
                                adapterLista.get(position).getSpinnersForRV().setValue(valuesFromJSONlista.get(i));

                            else if (adapterLista.get(position).getCheckBoxesForRV() != null)
                                adapterLista.get(position).getCheckBoxesForRV().setChecked(!valuesFromJSONlista.get(i).equals(""));


                    position++;


                }


            }

            else{
                adapter.result  = InfoSpecificLists.getNosil_Istoriko_lista();
                adapterLista = adapter.result;
                isThereTransgroupID = false;
            }

          //  adapter = new RecyclerViewNos_IstorikoAdaptor(Nosil_IstorikoActivity.this, InfoSpecificLists.getNosil_Istoriko_lista());


         //   Log.e("listaaaaa1",String.valueOf(adapterLista.size()));

        }





        adapter.notifyDataSetChanged();

        alertDialog.dismiss();

    }








    private void updateButtonListener(){

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) Nosil_IstorikoActivity_OLD.this.getSystemService(INPUT_METHOD_SERVICE);

               // imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                if (Utils.isNetworkAvailable2(Nosil_IstorikoActivity_OLD.this ) &&
                        !Utils.isEmpty(patientsTV, Nosil_IstorikoActivity_OLD.this)) {

                    updateButton.startAnimation();



                    if (!isThereTransgroupID)

                        insertInfo();
                    else
                        updateInfo();
                }
            }
        });
    }



    private void insertInfo(){
        String query = Str_queries.getNOSILEUTIKO_ISTORIKO_INSERT(transgroupID ,adapterLista);
        AsyncTaskUpdate task = new AsyncTaskUpdate(Nosil_IstorikoActivity_OLD.this, query);
        task.listener =  Nosil_IstorikoActivity_OLD.this;
        task.execute();
    }



    private void updateInfo(){

        String query = Str_queries.getNOSILEUTIKO_ISTORIKO_UPDATE(transgroupID ,adapterLista);

        AsyncTaskUpdate task = new AsyncTaskUpdate(Nosil_IstorikoActivity_OLD.this,query);
        task.listener = Nosil_IstorikoActivity_OLD.this;
        task.execute();



    }



    @Override
    public void update(String str) {
        if (str.equals(getString(R.string.successful_update))) {
            isThereTransgroupID = true; // ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΞΑΝΑΠΑΤΗΣΕΙ ΤΗΝ ΙΔΙΑ ΣΤΙΓΜΗ ΤΟ ΚΟΥΜΠΙ ΓΙΑ ΝΑ ΜΗΝ ΚΑΝΕΙ ΠΑΛΙ ΙΝΣΕΡΤ

            Utils.timeHandlerDoneButton(updateButton, Nosil_IstorikoActivity_OLD.this);
           // getEktimisiEpigonton();
        }
        else
            Utils.timeHandlerErrorButton(updateButton, Nosil_IstorikoActivity_OLD.this);
    }

    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);
        transgroupID = Utils.getSplitSPartString(patientsTV.getText().toString(),",",3);

        getNosil_Istoriko();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filladio_nosil_anaforas:
                // do something
            startActivity(new Intent(Nosil_IstorikoActivity_OLD.this, Nosil_Anafora_Logodosia_Activity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public void handleDialogClose(String transgroupid) {

        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT
        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);
        getNosil_Istoriko();
    }
}
