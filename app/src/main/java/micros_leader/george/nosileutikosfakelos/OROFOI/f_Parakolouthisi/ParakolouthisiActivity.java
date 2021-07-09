package micros_leader.george.nosileutikosfakelos.OROFOI.f_Parakolouthisi;

import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
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
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;

import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class ParakolouthisiActivity extends BasicActivity implements AsyncCompleteGetPatientsTask, MyDialogFragmentCloseListener, AsyncCompleteTask2 {


    private TextView dateTV;
    private Spinner hoursSP;
    private RecyclerView parakolouthisiRV;
    private Button diagramButton;
    public  AlertDialog alertDialog;
    private RVparakolouthisiAdapter adapterRV;
    private ArrayAdapter adapter;
    private ArrayList watchLista;
    private boolean isThereID;
    private String  id;
    private boolean isFirstTime = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parakolouthisi);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        dateTV = findViewById(R.id.dateTV);
        hoursSP = findViewById(R.id.hoursSP);
        diagramButton = findViewById(R.id.diagramButton);

        dateTV.setText(Utils.getCurrentDate());
        watchLista = InfoSpecificLists.getWatchList_3Hours();
        spinnersAdapter();
        watchID = InfoSpecificLists.getWatchID(hoursSP.getSelectedItem().toString());
        titloi_positions   = new int[]{11, 14};
        adapterRV = new RVparakolouthisiAdapter(this, InfoSpecificLists.getParakolouthisiListaForRV2(),titloi_positions);
        listaAdaptor = adapterRV.result;

        table = "nursing_parakolouthisi";
        getAll_col_names(InfoSpecificLists.getParakolouthisiListaForRV2());

        setRecyclerViewgridrLayaout(parakolouthisiRV, R.id.parakolouthisiRV,adapterRV,2,titloi_positions);

        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);


        thereIsDatePicker(R.id.dateTV);

        thereIsUpdateButton(R.id.updateButton);
        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID","Date","Watch"});


        diagramButtonListener();
    }


    private void diagramButtonListener(){

//        diagramButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                showDiagram(transgroupID,dateTV.getText().toString(),InfoSpecificLists.getParakolouthisiKatigoriesDiagramLista(),false);
//
//            }
//        });
    }



    private void spinnersAdapter() {

        adapter = new ArrayAdapter<>(ParakolouthisiActivity.this,
                R.layout.spinner_layout_for_vardies, watchLista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        hoursSP.setAdapter(adapter);

        hoursSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (isFirstTime)
                    isFirstTime = false;

                else {

                    watchID = InfoSpecificLists.getWatchID(hoursSP.getSelectedItem().toString());

                    getParakolouthisi();   //ΚΑΛΕΙΤΑΙ ΣΙΓΟΥΡΑ
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void getParakolouthisi(){

        getJSON_DATA(new Str_queries().getPARAKOLOUTHISI_PERSON(transgroupID,dateTV.getText().toString(), watchID));
    }




    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);


        if (weHaveData){

            for (int i = 0; i < nameJson.size(); i++) {

                 if (nameJson.get(i).equals("out_kenoseis"))
                    valuesJson.set(i,InfoSpecificLists.getKenoseisName(valuesJson.get(i))) ;

            }

           // valuesJson =   Utils.deleteProtesTheseis(valuesJson, 4); // ΔΙΑΓΡΑΦΗ ΚΑΠΟΙΩΝ ΠΡΩΤΩΝ ΤΙΜΩΝ ΑΠΟ ΤΗ ΛΙΣΤΑ ΠΟΥ ΚΑΤΕΒΑΣΑΜΕ

            setValuesTolistaAdaptor(titloi_positions,listaAdaptor);     // ΤΟΠΟΘΕΤΗΣΗ ΤΙΜΩΝ ΣΤΗ ΛΙΣΤΑ ΑΝΤΑΠΤΟΡΑ

        }
        else {
            //  ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΔΝΕ ΒΡΗΚΕ ΔΕΔΟΜΕΝΑ ΝΑ ΕΠΑΝΕΛΘΕΙ Η ΛΙΣΤΑ
            // ΣΤΗΝ ΑΡΧΙΚΗ ΑΔΕΙΑ ΤΙΜΗ
            clearListaAdaptor(listaAdaptor);

        }

        date = dateTV.getText().toString();

        adapterRV.notifyDataSetChanged();


    }




    @Override
    public void insert_or_update_data(ArrayList<ItemsRV> listaAdaptor, String[] names_col) {

        for (int i = 0; i < listaAdaptor.size(); i++) {

            if (listaAdaptor.get(i).getCol_name() != null) {

                if (listaAdaptor.get(i).getCol_name().equals("out_kenoseis"))
                    // ΓΙΑ ΝΑ ΠΑΡΕΙ ΤΟ ID ΤΗΣ ΚΕΝΩΣΗΣ ΩΣΤΕ ΝΑ ΤΟ ΑΠΟΘΗΚΕΥΣΕΙ ΣΤΗ ΒΑΣΗ ΑΦΟΥ ΤΟ ΒΑΛΕΙ
                    // ΣΤΗ listaAdaptor ΕΠΕΙΤΑ ΥΠΑΡΧΕΙ ΜΕΘΟΔΟς ΠΟΥ ΤΑ ΒΑΖΕΙ ΣΤΗ valuesjson
                    listaAdaptor.get(i).setValue(InfoSpecificLists.getKenoseisID( listaAdaptor.get(i).getValue()));

            }

        }
        super.insert_or_update_data(listaAdaptor, names_col);
    }

    @Override
    public void updateLabel(TextView datetv) {
        super.updateLabel(datetv);
        getParakolouthisi();
    }




    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        getParakolouthisi();


    }

    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);

        getParakolouthisi();
    }


}
