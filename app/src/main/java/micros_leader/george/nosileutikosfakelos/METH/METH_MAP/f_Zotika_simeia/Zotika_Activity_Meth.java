package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Zotika_simeia;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
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

import micros_leader.george.nosileutikosfakelos.AsyncTasks.PrintReport;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Zotika_simeia.Zotika_simeia_Activity;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.ReportIDs;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.get24HoursLista;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.getZotika24oroAnaOraLista_Meth;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.Icon;


public class Zotika_Activity_Meth extends BasicActivity {

    public Spinner hoursSP;

    public BasicRV adapterRV;
    public TextView timeTV;
    public Button neaEggrafiBT , diagramBT;
    private ArrayList <String> watchLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zotika___meth);
        neaEggrafiBT = findViewById(R.id.neaEggrafiBT);
        hoursSP = findViewById(R.id.hoursSP);
        diagramBT = findViewById(R.id.diagramBT);
        fab = findViewById(R.id.fab);


        initialize();

    }


    public void initialize(){
        if (extendedAct == null)
            extendedAct = this;
        neaEgrafiListener();
        table = "Nursing_Zotika_Simeia_Meth";
        thereIsImagePrinterButton(ReportIDs.ZOTIKA_SIMEIA_FLOOR_AND_METH, PrintReport.ReportParams.TRANSGROUP_ID_AND_DATE_STR);
        //timeListener();
        diagramButtonListener();

        watchLista = get24HoursLista();
        spinnersAdapter();
        watchID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());

        getAll_col_names( getZotika24oroAnaOraLista_Meth(true));
        fabListener();
        titloi_positions = new int[]{20};
        adapterRV = new ZotikaRV_adapter(extendedAct, getZotika24oroAnaOraLista_Meth(true), titloi_positions);
        listaAdaptor = adapterRV.result;



        setRecyclerViewgridrLayaout(R.id.zotikaMethRV ,adapterRV,2,titloi_positions);

        getPatientsList(extendedAct,R.id.patientsTV, R.id.floorsSP);
        thereIsDatePicker(R.id.dateTV);
        date = dateTV.getText().toString();


        thereIsImageUpdateButton();
        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID","Date","watch"});
    }




    private void spinnersAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(extendedAct,
                R.layout.spinner_layout_for_vardies, watchLista);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        hoursSP.setAdapter(arrayAdapter);

        hoursSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                watchID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void fabListener() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query =  Str_queries.getZotikaMeth(transgroupID, dateTV.getText().toString());

                Intent in = tableView_sigkentrotika(query,
                        transgroupID,

                        null,
                         InfoSpecificLists.getZotika24oroAnaOraLista_Meth(false),//getSigkentrotikaZotikaMeth
                        false,
                        false,
                        true);
                in.putExtra("watchID_as_simpleSpinner",true);

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

                adapterRV.updateLista(getZotika24oroAnaOraLista_Meth(true));

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
        diagramBT.setOnClickListener(v ->
                 showDiagram(transgroupID,dateTV.getText().toString(),
                         InfoSpecificLists.getZotikaKatigories_meth_DiagramLista(),1, extendedAct));

    }


    @Override
    public void update_JSON(String str) {
        super.update_JSON(str);

        if (str.equals(extendedAct.getString(R.string.successful_update))) {
            id = eggrafiID;
            weHaveData = true;
        }
        else {
            id = "";
            weHaveData = false;
        }
    }



    @Override
    public void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor) {
        super.setValuesTo_valuesJSON(titles_positions, listAdaptor);

        date = dateTV.getText().toString() + " " + Utils.getCurrentTime2();

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

//            if (result.get(position).getTitleID().contains("κόρη")){
//                holder.titleTV.setTypeface(null, Typeface.BOLD);
//                holder.titleTV.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Utils.showImageDialog(Zotika_Activity_Meth.this,R.drawable.megethos_koris);
//
//
//
//                    }
//                });
//            }

        }
    }


}
