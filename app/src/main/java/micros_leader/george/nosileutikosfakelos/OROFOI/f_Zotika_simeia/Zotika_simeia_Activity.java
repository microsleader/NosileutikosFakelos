package micros_leader.george.nosileutikosfakelos.OROFOI.f_Zotika_simeia;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.ag.floatingactionmenu.OptionsFabLayout;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;

import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.get24HoursLista;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.getZotika24oroAnaOraLista;

public class Zotika_simeia_Activity extends BasicActivity {

    private Spinner hoursSP;
    private boolean isFirstTime = true;
    private ArrayList <String> watchLista;
    private TextView epilogiTV;
    private Button diagramBT;
    private RV_zotika_ana_ora_adapter adapterRV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zotika_simeia_ana_ora_);


        table = "Nursing_Zotika_Simeia";
        getAll_col_names( getZotika24oroAnaOraLista());
        diagramBT = findViewById(R.id.diagramButton);
        diagramButtonListener();
        epilogiTV = findViewById(R.id.epilogiTV);
        epilogiTV.setText("Ανά ώρα");
        epilogiTVListener();
        hoursSP = findViewById(R.id.hoursSP);
        fabMenu = findViewById(R.id.fabMenu);
        fabListener();
        watchLista = get24HoursLista();
        spinnersAdapter();
        watchID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());
        titloi_positions = new int[]{};
        adapterRV = new RV_zotika_ana_ora_adapter(this, getZotika24oroAnaOraLista(),titloi_positions);
        listaAdaptor = adapterRV.result;



        setRecyclerViewgridrLayaout( R.id.zotikaRV,adapterRV,2,titloi_positions);

        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);
        thereIsDatePicker(R.id.dateTV);
        thereIsImageUpdateButton();
        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID","Watch","date"});



    }


    private void diagramButtonListener() {
        diagramBT.setOnClickListener(v -> new FancyAlertDialog.Builder(Zotika_simeia_Activity.this)
            .setTitle("Επιλογή διαγράμματος")
            .setBackgroundColor(Color.parseColor("#2288c1"))  //Don't pass R.color.colorvalue
            .setMessage("")
            .setPositiveBtnBackground(Color.parseColor("#2288c1"))  //Don't pass R.color.colorvalue
            .setPositiveBtnText("ανά ώρα")
            .OnPositiveClicked(() -> showDiagram(transgroupID,dateTV.getText().toString(),InfoSpecificLists.getZotikaKatigoriesDiagramLista(),1,this))
            .setNegativeBtnText("ανά 3ωρο")
            .OnNegativeClicked(() -> showDiagram(transgroupID,dateTV.getText().toString(),InfoSpecificLists.getZotikaKatigoriesDiagramLista(),3,this))
            .setAnimation(Animation.POP)
            .isCancellable(false)
            .setIcon(R.drawable.error_icon, Icon.Visible)
            .isCancellable(true)
            .build());
    }




    private void fabListener() {


        fabMenu.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabMenu.isOptionsMenuOpened())
                    fabMenu.closeOptionsMenu();
            }
        });


        fabMenu.setMiniFabsColors(
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary

        );



        fabMenu.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {

                final String [] panoTitloi =  new String[]{"Ωρα","Συστολική πίεση","Διαστολική πίεση",
                        "Καρδ.ρυμός","Σφύξεις","Θερμοκρασία","Κλίμακα πόνου","SPO2 (%)","Stick glu" };
                final  String [] columnNames = new String[]{"Watch","sistoliki_piesi","diastoliki_piesi", "rithmos" ,"sfixeis",
                        "thermokrasia","ponos","spo2","stick_glu"};

                switch (fabItem.getItemId()) {



                    case R.id.sigkentrotika_olwn_ton_asthenwn:
                        String watch_ID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());

                        startActivity(   tableView_sigkentrotika(Str_queries.getZOTIKA_SIMEIA_SIGKENTROTIKA_TON_ASETHENWN_SIGKEKRIMENI_HOUR(dateTV.getText().toString(),watch_ID),
                                null,null,InfoSpecificLists.getSigkentrotika_zotika(),false,false,false));
                        break;
                    case R.id.sigkentrotika_ana_ora:

                        startActivity(   tableView_sigkentrotika(Str_queries.getZOTIKA_SIMEIA_SIGKENTROTIKA_ANA_HOUR(transgroupID, dateTV.getText().toString()),
                                transgroupID,null,InfoSpecificLists.getSigkentrotika_zotika(),false,false,false));
                        break;

                    case R.id.sigkentrotika_ana_3oro:
                        startActivity(   tableView_sigkentrotika( Str_queries.getZOTIKA_SIMEIA_SIGKENTROTIKA_ANA_3_ORO(transgroupID, dateTV.getText().toString()),
                                transgroupID,null,InfoSpecificLists.getSigkentrotika_zotika(),false,false,false));
                        break;
                }
            }
            });



    }




    private void epilogiTVListener() {

        epilogiTV.setOnClickListener(v -> new FancyAlertDialog.Builder(Zotika_simeia_Activity.this)
                .setTitle("Επιλογή φυλλαδίου")
                .setBackgroundColor(Color.parseColor("#2288c1"))  //Don't pass R.color.colorvalue
                .setMessage("επιλέξτε Ζωτικά σημεία")
                .setPositiveBtnBackground(Color.parseColor("#2288c1"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("ανά ώρα")
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        epilogiTV.setText("Ανά ώρα");

                        setSpinnerAdapter(hoursSP, get24HoursLista());

                    }
                })
                .setNegativeBtnText("ανά 3ωρο")
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        epilogiTV.setText("Ανά 3ωρο");
                        setSpinnerAdapter(hoursSP, InfoSpecificLists.get3HoursLista());

                    }
                })
                .setAnimation(Animation.POP)
                .isCancellable(false)
                .setIcon(R.drawable.error_icon, Icon.Visible)
                .isCancellable(true)
                .build());
    }


    private void getZotikaSimeia24oro(){

        getJSON_DATA(new Str_queries().getZOTIKA_24ORO_ANA_ORA_PERSON(transgroupID,dateTV.getText().toString(), watchID));
    }


    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);

        newList = InfoSpecificLists.getZotika24oroAnaOraLista();


        if (weHaveData){

            for (int i = 0; i < nameJson.size(); i++) {
                switch (nameJson.get(i)) {
                    case "ID":
                        id = valuesJson.get(i);
                        break;
                    case "TransGroupID":
                        transgroupID = valuesJson.get(i);
                        break;
//                else if (nameJson.get(i).equals("Date"))
//                    date = valuesJson.get(i);
                    case "Period":
                        period = valuesJson.get(i);
                        break;
                    case "Watch":
                        watchID = valuesJson.get(i);
                        break;
                }

            }

            setValuesTolistaAdaptor(titloi_positions,newList);

        }
        else {
            //  ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΔΝΕ ΒΡΗΚΕ ΔΕΔΟΜΕΝΑ ΝΑ ΕΠΑΝΕΛΘΕΙ Η ΛΙΣΤΑ
            // ΣΤΗΝ ΑΡΧΙΚΗ ΑΔΕΙΑ ΤΙΜΗ
          //  clearListaAdaptor(listaAdaptor);

        }

        date = dateTV.getText().toString();

        adapterRV.updateLista(newList);


    }









    private void spinnersAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout_for_vardies, watchLista);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        hoursSP.setAdapter(arrayAdapter);

        hoursSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (isFirstTime)
                    isFirstTime = false;

                else {

                    if (epilogiTV.getText().toString().equals("Ανά ώρα"))
                         watchID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());
                    else
                        watchID = InfoSpecificLists.get3hoursID(hoursSP.getSelectedItem().toString());


                    getZotikaSimeia24oro();   //ΚΑΛΕΙΤΑΙ ΣΙΓΟΥΡΑ
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }




    @Override
    public void updateLabel(TextView datetv) {
        super.updateLabel(datetv);
        getZotikaSimeia24oro();
    }


    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        getZotikaSimeia24oro();

    }

    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);

        getZotikaSimeia24oro();
    }

}
