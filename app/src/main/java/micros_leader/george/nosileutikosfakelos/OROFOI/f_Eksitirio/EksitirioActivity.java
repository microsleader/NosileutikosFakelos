package micros_leader.george.nosileutikosfakelos.OROFOI.f_Eksitirio;

import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ClassItemsForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class EksitirioActivity extends BasicActivity implements AsyncCompleteGetPatientsTask , MyDialogFragmentCloseListener , AsyncCompleteTask2, AsyncGetUpdateResult {

    private RecyclerView eksitirioRV;
    private CircularProgressButton updateButton;
    private String id ,  atomiko_istoriko, poreia_nosou,iatrikes_odigies,protinomeni_iatriki_adeia;
    private RecyclerViewEksitirioAdaptor adapter;
    public  AlertDialog alertDialog;
    private ArrayList<PatientsOfTheDay> patientsNosileuomenoi;
    private ArrayList<ClassItemsForRV> exitirioListaAdaptor;
    private TextView patientsTV, atomikoIstorikoTV, poreiaTV, iatrikes_digiesTV,proteinomeniAdeiaTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eksitirio);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        eksitirioRV = findViewById(R.id.eksitirioRV);
       // updateButton = findViewById(R.id.updateButton);
        patientsTV = findViewById(R.id.patientsTV);
        atomikoIstorikoTV = findViewById(R.id.atomikoIstorikoTV);
        poreiaTV = findViewById(R.id.poreiaTV);
        iatrikes_digiesTV = findViewById(R.id.iatrikes_odigiesTV);
        proteinomeniAdeiaTV = findViewById(R.id.proteinomeniAdeiaTV);



        eksitirioRV.setLayoutManager(new LinearLayoutManager(EksitirioActivity.this, LinearLayoutManager.VERTICAL, false));
      //  eksitirioRV.addItemDecoration(new DividerItemDecoration(EksitirioActivity.this, LinearLayout.VERTICAL));
        eksitirioRV.setItemViewCacheSize(300);
        eksitirioRV.setHasFixedSize(true);
        eksitirioRV.setNestedScrollingEnabled(false);
        adapter = new RecyclerViewEksitirioAdaptor(EksitirioActivity.this,InfoSpecificLists.getExitirioListaForRV());
        eksitirioRV.setAdapter(adapter);

        exitirioListaAdaptor = adapter.result;

        alertDialog = Utils.setLoadingAlertDialog(this);

        getPatientsList(this,R.id.patientsTV,R.id.floorsSP);


    }



    private void getEksitirio(){
        if (Utils.isNetworkAvailable2(this)) {


            alertDialog.show();
            String query = new Str_queries().getEKSITIRIO_PERSON(transgroupID);
            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = getApplicationContext();
            task.listener = EksitirioActivity.this;
            task.query = query;
            task.execute();



        }
    }

    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null) {

            int position = 0;

            // ΕΑΝ ΔΕΝ ΕΧΕΙ ΣΤΑΤΟΥΣ ΔΗΛΑΔΗ ΕΑΝ ΕΧΕΙ ΑΠΟΤΕΛΕΣΜΑΤΑ ΝΑ ΦΕΡΕΙ ΝΑ ΚΑΝΕΙ ΤΟ ΑΠΟ ΚΑΤΩ
            if (!results.getJSONObject(0).has("status")) {

             JSONObject currentEksitirio = results.getJSONObject(0);


                Iterator<String> keys = currentEksitirio.keys();
                while (keys.hasNext()) {


                    String str_Name = keys.next();
                    String value = currentEksitirio.optString(str_Name);

                    switch (str_Name) {
                        case "ID":
                            id = value;
                            break;
                        case "TransGroupID":
                            transgroupID = value;
                            break;
                        case "atomiko_istoriko":
                            atomiko_istoriko = value;
                            atomikoIstorikoTV.setText(atomiko_istoriko);

                            break;
                        case "poreia_nosou":
                            poreia_nosou = value;
                            poreiaTV.setText(poreia_nosou);
                            break;
                        case "iatrikes_odigies":
                            iatrikes_odigies = value;
                            iatrikes_digiesTV.setText(iatrikes_odigies);

                            break;
                        case "protinomeni_iatriki_adeia":
                            protinomeni_iatriki_adeia = value;
                            proteinomeniAdeiaTV.setText(protinomeni_iatriki_adeia);

                            break;
                        default:

                            //   Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
                            exitirioListaAdaptor.get(position).setValue(value);

                            position++;

                            break;
                    }

                }


                adapter.notifyDataSetChanged();

                alertDialog.dismiss();


            }
            else {
                clearValuesOfLista();
                alertDialog.dismiss();
            }

        }

        else {
            alertDialog.dismiss();
        }

    }

    private void clearValuesOfLista() {

        atomikoIstorikoTV.setText("");
        poreiaTV.setText("");
        iatrikes_digiesTV.setText("");
        proteinomeniAdeiaTV.setText("");


        for (int i =0; i<exitirioListaAdaptor.size(); i ++){

            exitirioListaAdaptor.get(i).setValue("");
        }
        adapter.notifyDataSetChanged();

    }


    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        getEksitirio();

    }





    @Override
    public void handleDialogClose(String transgroupid) {
        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT
        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);

        getEksitirio();
    }



    @Override
    public void update(String str) {

    }
}
