package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_logodosia;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Spinner_items_lists;
import micros_leader.george.nosileutikosfakelos.Spinner_new_Adapter;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.Utils.convertObjToString;
import static micros_leader.george.nosileutikosfakelos.Utils.getUserID;

public class Nosil_Anafora_Logodosia_Activity extends BasicActivity {

    private RecyclerView nosilLogodosiaRV ,horizontalRV;
    private Nosil_logodosia_Adapter adapterRV;
    private Nosil_logodosia_dates_Adapter adapterDatesRV;
    private ArrayList<Nosil_Logodosies> result ,resultsDates;
    private LinearLayout aksiologiLayout;
    public  CheckBox tilemetriaCH;
    private ArrayList<Nosil_Logodosies> this_lista_adaptor;
    private ArrayList<String> columnNames;
    public  Spinner orarioSP;
    public  EditText kard_rithmosET,paralamvanetaiET,kinitopoiisiET,alles_paremvaseisET,logodosiaET;
    private TextView textViews[];
    private EditText editTexts[];
    private Spinner[] spinners;
    private Spinner_new_Adapter arrayAdapter;
    private String orarioID = "" ,tilemetriaStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nosil__anafora);

        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        columnNames = new ArrayList<>();
        columnNames.add("date");
        //columnNames.add("UserID");
        columnNames.add("orario");    columnNames.add("tilemetria");
        columnNames.add("kard_rithmos");  columnNames.add("paralamvanetai");  columnNames.add("kinitopoiisi");
        columnNames.add("alles_paremvaseis");
        setAll_col_names(columnNames);

        aksiologiLayout = findViewById(R.id.aksiologisiLayout);
        tilemetriaCH = findViewById(R.id.tilemetriaCH);
        tilemetriaListener();

        orarioSP = findViewById(R.id.orarioSP);
        kard_rithmosET = findViewById(R.id.kard_rithmosET);
        paralamvanetaiET = findViewById(R.id.paralamvanetaiET);
        kinitopoiisiET = findViewById(R.id.kinitopoiisiET);
        alles_paremvaseisET = findViewById(R.id.alles_paremvaseisET);
        logodosiaET = findViewById(R.id.logodosiaET);

        result = new ArrayList<>();
        resultsDates = new ArrayList<>();
        table = "Nursing_Nosileutiki_logodosia";
        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);
     //   thereIsDatePicker(R.id.dateTV);
        thereIsUpdateButton(R.id.updateButton);
        textViews = new TextView[] {dateTV};
        spinners = new Spinner[] {orarioSP};
        orarioSPListener();
        editTexts = new EditText[] {kard_rithmosET,paralamvanetaiET,kinitopoiisiET,alles_paremvaseisET,logodosiaET};



//        LinearLayoutManager horizontalLayoutManager
//                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//
//        horizontalRV = findViewById(R.id.horizontalRV);
//        horizontalRV.setLayoutManager(horizontalLayoutManager);
//        horizontalRV.addItemDecoration(new DividerItemDecoration(this, LinearLayout.HORIZONTAL));
//
//        horizontalRV.setAdapter(adapterDatesRV);

        adapterDatesRV = new Nosil_logodosia_dates_Adapter(this,resultsDates);
        setRecyclerViewHorizontalLinearLayout(horizontalRV,R.id.horizontalRV, adapterDatesRV);



        adapterRV = new Nosil_logodosia_Adapter(this,result);
        setRecyclerViewLinearLayout(nosilLogodosiaRV, R.id.nosilLogodosiaRV, adapterRV);
        this_lista_adaptor = adapterRV.result;

        insertOrUpdateListener(editTexts,null,new String [] {"ID","TransGroupID","date","datestr","UserID","Name"});
        //insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID","date","UserID"});

    }

    private void orarioSPListener() {

        ArrayList <Spinner_item> items = Spinner_items_lists.getNosileutiki_logodosia_vardies();
        Spinner_item[] lista = items.toArray(new Spinner_item[items.size()]);
        arrayAdapter = new Spinner_new_Adapter(this, R.layout.spinner_layout2, lista);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orarioSP.setAdapter(arrayAdapter);

        orarioSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                orarioID = String.valueOf(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setSpinnerPositionUsingID(int idFromBase){
        orarioSP.setSelection(idFromBase);

    }

    private void tilemetriaListener(){
        tilemetriaCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTilemetria();
            }
        });
    }


    public void checkTilemetria(){
        if (tilemetriaCH.isChecked()) {
            aksiologiLayout.setVisibility(View.VISIBLE);
            tilemetriaStr = "1";
        }
        else {
            aksiologiLayout.setVisibility(View.GONE);
            tilemetriaStr = "";

        }

    }

    private void get_Nosil_Logodosies(){

        getJSON_DATA(new Str_queries().getNOSILEUTIKES_LOGODOSIES(transgroupID));
    }


    @Override
    public void insert_or_update_data(EditText[] editText, TextView[] textView, String[] names_col) {
        if (!nurseID.equals(getUserID(Nosil_Anafora_Logodosia_Activity.this))){
            updateButton.revertAnimation();
            alertDialog.dismiss();
            Toast.makeText(this,"Η συγκεκριμένη καταχώρηση δεν έχει γίνει απο εσάς", Toast.LENGTH_LONG).show();
        }
        else
            super.insert_or_update_data(editText, textView, names_col);

    }

    @Override
    public void setValuesTo_valuesJSON(EditText[] editText, TextView[] textView) {

        if (date.equals("")) //GIA INSERT
        {


            date = Utils.getCurrentDate() ;
        }
        else {

        //    valuesJson.add(date); //GIA UPDATE PAIRNEI TIN TIMI APO TO RECYCLERVIEW
        }
           //  valuesJson.add(getUserID(Nosil_Anafora_Logodosia_Activity.this));

        if (orarioID.equals("0"))
            orarioID = "";



             valuesJson.add(orarioID);
             valuesJson.add(tilemetriaStr);
             if (!tilemetriaCH.isChecked())
                 kard_rithmosET.setText("");
             super.setValuesTo_valuesJSON(editText, textView);



    }

    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);

        if (weHaveData) {
            result.clear();
            resultsDates.clear();
            for (int i = 0; i < results.length(); i++) {

                JSONObject jsonObject = results.getJSONObject(i);
                Nosil_Logodosies x = new Nosil_Logodosies();
                Nosil_Logodosies datesStr = new Nosil_Logodosies();


                x.setId(convertObjToString(jsonObject.get("ID")));
                x.setTransgroupID(convertObjToString(jsonObject.get("TransGroupID")));
                x.setDate(convertObjToString(jsonObject.get("date")));
                x.setDateStr(convertObjToString(jsonObject.get("datestr")));
                datesStr.setDateStr(convertObjToString(jsonObject.get("datestr")));
                x.setUserID(convertObjToString(jsonObject.get("UserID")));
                x.setUserName(convertObjToString(jsonObject.get("Name")));
                x.setOrario(convertObjToString(jsonObject.get("orario")));
                x.setTilemetria(convertObjToString(jsonObject.get("tilemetria")));
                x.setKard_rithmos(convertObjToString(jsonObject.get("kard_rithmos")));
                x.setParalamvanetai(convertObjToString(jsonObject.get("paralamvanetai")));
                x.setKinitopoiisi(convertObjToString(jsonObject.get("kinitopoiisi")));
                x.setAlles_paremvaseis(convertObjToString(jsonObject.get("alles_paremvaseis")));
                x.setLogodosia(convertObjToString(jsonObject.get("logodosia")));


                result.add(x);
                resultsDates.add(datesStr);
            }

       //     deleteProtesTheseis(valuesJson,4);
        //    deleteTeleutaiesTheseis(valuesJson,2);

        }

        else {

            clearElements();

            result.clear();
            resultsDates.clear();
        }

        this_lista_adaptor = result;

        //RV DATES HORIZONTAL
        resultsDates = removeDuplicates(resultsDates);
        adapterDatesRV.result = resultsDates;
//---------------------------------------------


// ΕΔΩ ΤΑ ΚΑΝΟΥΜΕ ΚΑΤΑ ΕΞΑΙΡΕΣΗ ΑΥΤΑ ΕΠΕΙΔΗ ΕΝΩ ΘΕΛΟΥΜΕ ΝΑ ΚΑΝΟΥΜΕ ΙΝΣΕΡΤ BY DEFAULT ΑΝ ΕΧΕΙ ΑΠΟ ΤΗΝ ΑΡΧΗ ΔΕΔΟΜΕΝΑ ΘΑ ΠΗΓΑΙΝΕΙ ΓΙΑ UPDATE
        // TRUE ΘΑ ΓΙΝΕΤΑΙ ΟΤΑΝ ΠΑΤΑΜΕ ΠΑΝΩ ΣΤΟ ΑΝΤΙΚΕΜΕΝΟ ΤΟΥ RECYCLERVIEW
        weHaveData = false;
        valuesJson.clear();
        nurseID = getUserID(Nosil_Anafora_Logodosia_Activity.this);
        date = "";
        //------------------------

        adapterDatesRV.notifyDataSetChanged();

    //    adapterRV.notifyDataSetChanged();



    }

    public void clearElements() {
        clearTexts(editTexts);
        clearTexts(spinners);
        tilemetriaCH.setChecked(false);
        checkTilemetria();
    }

    public void setItemsDependingDate(String date){

        ArrayList<Nosil_Logodosies> newList = new ArrayList<>();

        for (int i=0; i < this_lista_adaptor.size(); i++){
            if (this_lista_adaptor.get(i).getDateStr().equals(date))
                newList.add(this_lista_adaptor.get(i));

        }


        adapterRV.result = newList;
        adapterRV.notifyDataSetChanged();
    }

    private ArrayList<Nosil_Logodosies> removeDuplicates(ArrayList<Nosil_Logodosies> lista) {

        if (lista == null || lista.isEmpty())
            return lista;

        ArrayList <Nosil_Logodosies>  newList = new ArrayList<>();
        newList.add(lista.get(0));

        if (lista.size() > 0)
            for (int i=1; i<lista.size(); i++){
                if (!lista.get(i).getDateStr().equals(lista.get(i - 1).getDateStr()))
                    newList.add(lista.get(i));
            }
        return newList;
    }


    @Override
    public void updateSuccess(String str) {
        super.updateSuccess(str);

        clearTexts(editTexts);
        weHaveData = false;
    }

    @Override
    public void updateLabel(TextView datetv) {
        super.updateLabel(datetv);
        get_Nosil_Logodosies();

    }



    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);
        get_Nosil_Logodosies();


    }

    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);
        get_Nosil_Logodosies();

    }

    @Override
    public void deleteData(String table, String id) {
        super.deleteData(table, id);
        get_Nosil_Logodosies();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
