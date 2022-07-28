package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Zotika_simeia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetFloors;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.PrintReport;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Zotika_simeia.Zotika_simeia_Activity;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.ReportIDs;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.get24HoursLista;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.getZotika24oroAnaOraLista_Meth;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ag.floatingactionmenu.OptionsFabLayout;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.Icon;

import org.json.JSONArray;
import org.json.JSONException;


public class Zotika_Activity_Meth extends BasicActivity {

    public Spinner hoursSP;

    public BasicRV adapterRV;
    public Button neaEggrafiBT , diagramBT;
    private boolean firstTime = true;
    private ArrayList <String> watchLista;
    private final int KARTELA_ZOTIKA = 1;
    private final int KARTELA_ANAPNOI = 2;
    private final int KARTELA_EIDIKES_METRISEIS = 3;
    private final int KARTELA_GENIKA = 4;
    private final int KAMIA_KARTELA = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zotika___meth);
        neaEggrafiBT = findViewById(R.id.neaEggrafiBT);
        hoursSP = findViewById(R.id.hoursSP);
        diagramBT = findViewById(R.id.diagramBT);

        fabMenu = findViewById(R.id.fabMenu);


        initialize();

    }


    public void initialize(){
        if (extendedAct == null)
            extendedAct = this;

        neaEgrafiListener();
        table = "Nursing_Zotika_Simeia_Meth";
        //thereIsImagePrinterButton(ReportIDs.ZOTIKA_SIMEIA_FLOOR_AND_METH, PrintReport.ReportParams.TRANSGROUP_ID_AND_DATE_STR);
        //timeListener();

        if (Utils.getCustomerID(extendedAct) == Customers.CUSTID_MEDITERRANEO)
            diagramBT.setVisibility(View.GONE);
        else
            diagramButtonListener();

        alertDialog = Utils.setLoadingAlertDialog(extendedAct);

        watchLista = get24HoursLista();
       // spinnersAdapter();
       // watchID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());

        getAll_col_names( getZotika24oroAnaOraLista_Meth(true ,KAMIA_KARTELA));
        fabListener();
        titloi_positions = new int[]{0,7,17,28};
        adapterRV = new ZotikaRV_adapter(extendedAct, getZotika24oroAnaOraLista_Meth(true,KAMIA_KARTELA), titloi_positions);
        listaAdaptor = adapterRV.result;



        setRecyclerViewgridrLayaout(R.id.zotikaMethRV ,adapterRV,2,titloi_positions);

        getPatientsList(extendedAct,R.id.patientsTV, R.id.floorsSP);
        thereIsDatePicker(R.id.dateTV);
        setTimePicker(R.id.hoursTV);
        date = dateTV.getText().toString();


        thereIsImageUpdateButton();
        insertOrUpdateListener_with_question(listaAdaptor,new String [] {"ID","TransGroupID","Date","watch"});
    }





    private void getZotikaSimeia_meth_24oro(){
        alertDialog.show();
        getJSON_DATA(Str_queries.getZotikaMeth(transgroupID,dateTV.getText().toString() , timeTV.getText().toString()));
    }


    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);

        newList = getZotika24oroAnaOraLista_Meth(true,KAMIA_KARTELA);
        if (weHaveData){
            setValuesTolistaAdaptor(titloi_positions,newList);
        }


        //adapterRV.updateLista(newList);
        // κανονικα αυτο αλλα θα βαζω καθε φορα καινουριο ανταπτορα
        //επειδη τα σπινερς μπερδευονται στο recycling

        recyclerView.setAdapter(null);
        adapterRV = new ZotikaRV_adapter(extendedAct, newList, titloi_positions);
        recyclerView.setAdapter(adapterRV);
        listaAdaptor = adapterRV.result;
        insertOrUpdateListener_with_question(listaAdaptor,new String [] {"ID","TransGroupID","Date","watch"});

        alertDialog.dismiss();
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
                R.color.colorPrimary,
                R.color.colorPrimary

        );


        fabMenu.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {

                int kartela = 0;

                switch (fabItem.getItemId()) {

                    case R.id.zotika:
                        kartela = KARTELA_ZOTIKA;
                        break;

                    case R.id.anapnoi:
                        kartela = KARTELA_ANAPNOI;
                        break;

                    case R.id.eidikes_metriseis:
                        kartela = KARTELA_EIDIKES_METRISEIS;
                        break;

                    case R.id.genika:
                        kartela = KARTELA_GENIKA;
                        break;
                }


                Intent in = tableView_sigkentrotika(Str_queries.getSigkentrotikaZotikaMeth(transgroupID,dateTV.getText().toString()),
                        transgroupID,null,InfoSpecificLists.getZotika24oroAnaOraLista_Meth(false , kartela),false,false,true);
                //in.putExtra("watchID_as_simpleSpinner",true);

                extendedAct.startActivity(in);


            }
        });







    }


    private void neaEgrafiListener(){

        neaEggrafiBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weHaveData = false;
                id = "";
                //clearListaAdaptor(listaAdaptor);

                //adapterRV.updateLista(getZotika24oroAnaOraLista_Meth(true));
                //το κανω ετσι επειδη μετα την απ[οθηκευση μου μπερδευει τις λιστες στα σπινερ
                // δεν εχω βρει ακομα τον λογο
                adapterRV = new ZotikaRV_adapter(extendedAct, getZotika24oroAnaOraLista_Meth(true,KAMIA_KARTELA), titloi_positions);
                recyclerView.setAdapter(adapterRV);

                Toast.makeText(extendedAct, "Μπορείτε να κάνετε μία νέα εγγραφη", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void timeListener(){
        timeTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(extendedAct, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeTV.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.show();

            }
        });
    }



    private void diagramButtonListener() {
        diagramBT.setOnClickListener(v -> showDiagram(transgroupID,dateTV.getText().toString(),
                InfoSpecificLists.getZotikaKatigories_meth_DiagramLista(),1, extendedAct));

    }



    private void setTimePicker(int timeID){

        timeTV = extendedAct.findViewById(timeID);
        timeTV.setText(Utils.getCurrentTime2());

        timeTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(extendedAct, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeTV.setText( Utils.chechZeroInTime(selectedHour + ":" + selectedMinute));
                        getZotikaSimeia_meth_24oro();
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });
    }







    @Override
    public void getPatientsList(AppCompatActivity extendedActivity, int textviewID, int spinnerID) {
        if (floorSP == null)
            floorSP = findViewById(spinnerID);

        if (patientsTV == null)
            patientsTV = findViewById(textviewID);

        this.extendedAct = extendedActivity;
        AsyncTaskGetFloors s ;
        if (activityFromSigxoneusi == null)
            s = new AsyncTaskGetFloors(extendedActivity, floorSP, floorAdapter);
        else
            s = new AsyncTaskGetFloors(extendedActivity, floorSP, floorAdapter,activityFromSigxoneusi);
        s.query = " select id,name from floor where companyid = " + Utils.getcompanyID(extendedActivity) + " and id  in (6)"; //μόνο ισογειο και μεθ
        s.execute();
    }




    @Override
    public void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor) {
        super.setValuesTo_valuesJSON(titles_positions, listAdaptor);

        date = dateTV.getText().toString() + " " + Utils.getCurrentTime2();
        nameJson.add("time");
        valuesJson.add(Utils.convertHourTomillisecondsGR(timeTV.getText().toString()));


    }




    @Override
    public void updateLabel(TextView datetv) {
        super.updateLabel(datetv);
        getZotikaSimeia_meth_24oro();
    }


    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);
        getZotikaSimeia_meth_24oro();

    }



    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);

        getZotikaSimeia_meth_24oro();
    }



    @Override
    public void insert_or_update_data(ArrayList<ItemsRV> listaAdaptor, String[] names_col) {
        super.insert_or_update_data(listaAdaptor, names_col);
    }




    @Override
    public void printerListener(int reportID, PrintReport.ReportParams repParam) {
        dateStr = dateTV.getText().toString();
        super.printerListener(reportID, repParam);
    }

    static class ZotikaRV_adapter extends BasicRV {


        public ZotikaRV_adapter(Activity act, ArrayList<ItemsRV> result, int[] titloi_positions) {
            super(act, result, titloi_positions);
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);


        }
    }



}
