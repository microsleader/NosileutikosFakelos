package micros_leader.george.nosileutikosfakelos.OROFOI.f_Zotika_simeia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.ag.floatingactionmenu.OptionsFabLayout;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon.FarmakaListActivity;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.get24HoursLista;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.getZotika24oroAnaOraLista;

import androidx.appcompat.app.AlertDialog;

public class Zotika_simeia_Activity extends BasicActivity {

    private Spinner hoursSP;
    private boolean isFirstTime = true;
    private ArrayList <String> watchLista;
    private TextView epilogiTV ;
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

        //epilogiTVListener();
        //hoursSP = findViewById(R.id.hoursSP);
        fabMenu = findViewById(R.id.fabMenu);
        fabListener();
        //watchLista = get24HoursLista();
        //spinnersAdapter();
        //watchID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());
        titloi_positions = new int[]{};
        adapterRV = new RV_zotika_ana_ora_adapter(this, getZotika24oroAnaOraLista(),titloi_positions);
        listaAdaptor = adapterRV.result;



        setRecyclerViewgridrLayaout( R.id.zotikaRV,adapterRV,2,titloi_positions);

        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);
        thereIsDatePicker(R.id.dateTV);
        setTimePicker(R.id.hoursTV);
        thereIsImageUpdateButton();
        insertOrUpdateListener_with_question(listaAdaptor,new String [] {"ID","TransGroupID","Watch","date"});



    }


    private void diagramButtonListener() {
        diagramBT.setOnClickListener(v -> showDiagram(transgroupID,dateTV.getText().toString(),InfoSpecificLists.getZotikaKatigoriesDiagramLista(),1,this));
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


                Intent in;


                switch (fabItem.getItemId()) {


                    case R.id.sigkentrotika_olwn_ton_asthenwn:

                        selectHoursDialog();// μεσα καλειται το tableView_sigkentrotika();

                        break;
                    case R.id.sigkentrotika_ana_ora:

                        in = tableView_sigkentrotika(Str_queries.getZOTIKA_SIMEIA_SIGKENTROTIKA_ANA_HOUR(transgroupID, dateTV.getText().toString()),
                                transgroupID,null,InfoSpecificLists.getSigkentrotika_zotika(false),false,false,true);
                        startActivity(in);

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

        getJSON_DATA(new Str_queries().getZOTIKA_24ORO_ANA_ORA_PERSON(transgroupID,dateTV.getText().toString(), timeTV.getText().toString()));
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

                    //if (epilogiTV.getText().toString().equals("Ανά ώρα"))
                         watchID = InfoSpecificLists.get24hoursID(hoursSP.getSelectedItem().toString());
//                    else
//                        watchID = InfoSpecificLists.get3hoursID(hoursSP.getSelectedItem().toString());


                    getZotikaSimeia24oro();   //ΚΑΛΕΙΤΑΙ ΣΙΓΟΥΡΑ
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void setTimePicker(int timeID){

        timeTV = findViewById(timeID);
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
                        getZotikaSimeia24oro();
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });
    }


    @Override
    public void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor) {
        super.setValuesTo_valuesJSON(titles_positions, listAdaptor);

        nameJson.add("time");
        valuesJson.add(Utils.convertHourTomillisecondsGR(timeTV.getText().toString()));

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



    private void selectHoursDialog (){

        AlertDialog.Builder builder = new AlertDialog.Builder(extendedAct);
        builder.setTitle("Επιλέχτε ώρες");
        final EditText fromHour = new EditText(extendedAct);
        final EditText toHour = new EditText(extendedAct);

        fromHour.setInputType(InputType.TYPE_CLASS_NUMBER );
        fromHour.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
        fromHour.setHint("Από...");
        fromHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int num = s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString());
                int length = s.toString().length();
                if (num > 24)
                    s.delete(length - 1, length);
            }
        });

        toHour.setInputType(InputType.TYPE_CLASS_NUMBER );
        toHour.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
        toHour.setHint("Έως...");
        toHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int num = s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString());
                int length = s.toString().length();
                if (num > 24)
                    s.delete(length - 1, length);
            }
        });


        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(fromHour);
        ll.addView(toHour);

        LinearLayout ll_main=new LinearLayout(this);
        ll_main.setOrientation(LinearLayout.VERTICAL);
        ll_main.addView(ll);

        builder.setView(ll_main);

        builder.setCancelable(false);
        builder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                int from = fromHour.getText().toString().isEmpty() ? 0 : Integer.parseInt(fromHour.getText().toString());
                int to = toHour.getText().toString().isEmpty() ? 0 : Integer.parseInt(toHour.getText().toString());
                if (from > to)
                    Toast.makeText(extendedAct, "Η ώρα 'Από' πρέπει να είναι μικρότερη ή ίση της 'Έως'", Toast.LENGTH_SHORT).show();
                else {

                    String fromTime = from == 0 ? "00:00" : from + ":00";
                    String toTime = to == 0 ? "00:00" : to + ":00";

                    if (toTime.equals("24:00"))
                        toTime = "23:59";



                    startActivity(   tableView_sigkentrotika(Str_queries.getZOTIKA_SIMEIA_SIGKENTROTIKA_TON_ASETHENWN(dateTV.getText().toString(), fromTime , toTime, floorID),
                            null,null,InfoSpecificLists.getSigkentrotika_zotika(true),false,false,false));
                }
            }
        });

        builder.setNegativeButton("Ακύρωση", (dialog, which) -> {});
        AlertDialog dialog = builder.create();
        dialog.show();


    }
}
