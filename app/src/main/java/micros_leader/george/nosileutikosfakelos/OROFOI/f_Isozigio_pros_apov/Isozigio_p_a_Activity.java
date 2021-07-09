package micros_leader.george.nosileutikosfakelos.OROFOI.f_Isozigio_pros_apov;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.TableView.TableView_Codes;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Parakolouthisi.RVparakolouthisiAdapter;

public class Isozigio_p_a_Activity extends BasicActivity {

    private TextView dateTV ,total_pros,total_apov,total_isozigio;
    private Spinner hoursSP;
    private RecyclerView isozigioRV;
    public  AlertDialog alertDialog;
    private BasicRV adapterRV;
    private ArrayAdapter adapter;
    private ArrayList watchLista;
    private boolean isThereID;
    private String  id, userID;
    private boolean isFirstTime = true;
    private Button statheresMetriseisBT;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isozigio_p_a_layout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        dateTV = findViewById(R.id.dateTV);

        total_pros = findViewById(R.id.total_pros);
        total_apov = findViewById(R.id.total_apov);
        total_isozigio = findViewById(R.id.total_isozigio);

        hoursSP = findViewById(R.id.hoursSP);
        fab = findViewById(R.id.fab);
        fabListener();

        dateTV.setText(Utils.getCurrentDate());
        watchLista = InfoSpecificLists.get24HoursLista();
        statheresMetriseisBT = findViewById(R.id.statheresMetriseisBT);
        statheresListener();
        spinnersAdapter();
        watchID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());
        titloi_positions   = new int[]{0, 3};
        adapterRV = new RVparakolouthisiAdapter(this, InfoSpecificLists.getIsozigio_Apov_Pros(),titloi_positions);
        listaAdaptor = adapterRV.result;

        table = "Nursing_isozigio_proslamvanomenon_apovallomenon";
        getAll_col_names(InfoSpecificLists.getIsozigio_Apov_Pros());

        setRecyclerViewgridrLayaout(isozigioRV, R.id.isozigioRV,adapterRV,2,titloi_positions);

        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);


        thereIsDatePicker(R.id.dateTV);

        thereIsImageUpdateButton();
        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID","Date","Watch"});


        date = dateTV.getText().toString();

    }

    private void fabListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent in =   tableView_sigkentrotika(new Str_queries().getISOZIGIO_PRO_APOV_SIGKENTROTIKA_ANA_ORA(transgroupID,dateTV.getText().toString()),
                        transgroupID,
                        new String[]{"Ώρα","Ενδοφλεβίως","Διεντερικώς","Ούρα","Παροχέτευση 1","Παροχέτευση 2",
                                "Παροχέτευση 3","Παροχέτευση 4","Παροχέτευση 5","Εμετοί","Εφιδρώσεις","Κενώσεις",
                                "Συν. Προσ.", "Συν. Αποβ.","ΙΣΟΖΥΓΙΟ"},

                        null,
                        new String[]{"Watch","endoflevios","dienterikos","out_oura","out_paroxetefsi1","out_paroxetefsi2",
                                "out_paroxetefsi3","out_paroxetefsi4","out_paroxetefsi5","out_emetoi","out_efidroseis","out_kenoseis",
                                "total_pros","total_apov","total_isozigio"},
                        false,
                        true,
                        false);

                startActivityForResult(in, TableView_Codes.SET_TOTAL_PER_8_HOURS);
            }
        });
    }

    private void statheresListener() {

        statheresMetriseisBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Isozigio_statheresDialog df = new Isozigio_statheresDialog();
                Bundle putextra = new Bundle();
                putextra.putString("transgroupID", transgroupID);
                df.setArguments(putextra);
                df.show(getSupportFragmentManager(), "Dialog");
            }
        });


    }


    private void spinnersAdapter() {

        adapter = new ArrayAdapter<>(Isozigio_p_a_Activity.this,
                R.layout.spinner_layout_for_vardies, watchLista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        hoursSP.setAdapter(adapter);

        hoursSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (isFirstTime)
                    isFirstTime = false;

                else {

                    watchID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());

                    getIsozigio();   //ΚΑΛΕΙΤΑΙ ΣΙΓΟΥΡΑ
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getIsozigio() {
        getJSON_DATA(Str_queries.getIsozigio_A_P(transgroupID,date,watchID));
    }



    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);


        if (weHaveData){

            total_pros.setText(Utils.convertObjToString(results.getJSONObject(0).get("total_pros")));
            total_apov.setText(Utils.convertObjToString(results.getJSONObject(0).get("total_apov")));
            userID = Utils.convertObjToString(results.getJSONObject(0).get("UserID"));
            total_isozigio.setText(String.valueOf(Double.parseDouble(total_pros.getText().toString()) - Double.parseDouble(total_apov.getText().toString())));

            setValuesTolistaAdaptor(titloi_positions,listaAdaptor);     // ΤΟΠΟΘΕΤΗΣΗ ΤΙΜΩΝ ΣΤΗ ΛΙΣΤΑ ΑΝΤΑΠΤΟΡΑ



        }
        else {
            //  ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΔΝΕ ΒΡΗΚΕ ΔΕΔΟΜΕΝΑ ΝΑ ΕΠΑΝΕΛΘΕΙ Η ΛΙΣΤΑ
            // ΣΤΗΝ ΑΡΧΙΚΗ ΑΔΕΙΑ ΤΙΜΗ
            clearListaAdaptor(listaAdaptor);
            total_pros.setText("");
            total_apov.setText("");
            total_isozigio.setText("");

        }

        date = dateTV.getText().toString();

        adapterRV.notifyDataSetChanged();


    }



    @Override
    public void updateLabel(TextView datetv) {
        super.updateLabel(datetv);
        getIsozigio();

    }


    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        getIsozigio();


    }


    @Override
    public void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor) {
        super.setValuesTo_valuesJSON(titles_positions, listAdaptor);

//        nameJson.add("userID");
//        valuesJson.add(Utils.getUserID(this));

        if (nameJson.contains("")) {

            int pos = nameJson.indexOf("");
            nameJson.remove("");
            valuesJson.remove(pos);
        }

    }








    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);

        getIsozigio();
    }

}
