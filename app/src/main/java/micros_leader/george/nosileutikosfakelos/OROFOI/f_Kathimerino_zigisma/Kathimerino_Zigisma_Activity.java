package micros_leader.george.nosileutikosfakelos.OROFOI.f_Kathimerino_zigisma;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;


import static micros_leader.george.nosileutikosfakelos.Utils.convertObjToString;
import static micros_leader.george.nosileutikosfakelos.Utils.getCurrentDate;

public class Kathimerino_Zigisma_Activity extends BasicActivity {

    private EditText anaf_varosET,varosET,proVarosET,ipsosET,textET;
  //  private Spinner daySP;
    private String anaf_varos,varos,proVaros,ipsos,text  , dayFromSpinner = "1";
    private ArrayAdapter spinnerAdapter;
    private ArrayList<String> columnNames;
    private TextView textViews[];
    private EditText editTexts[];
    private boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kathimerino__zigisma_);

        table = "Nursing_Kathimerino_Zigisma";

        anaf_varosET = findViewById(R.id.anafVarosET);
        varosET = findViewById(R.id.varosET);
        proVarosET = findViewById(R.id.proVarosET);
        ipsosET = findViewById(R.id.ipsosET);
        textET = findViewById(R.id.textET);
     //   daySP = findViewById(R.id.daysSP);
        fab = findViewById(R.id.fab);
        fabListener();
       // spinnerInitialize();


        textViews = new TextView[] {dateTV};
        editTexts = new EditText[] {anaf_varosET,varosET,proVarosET,ipsosET,textET};

        columnNames = new ArrayList<>();
        columnNames.add("date");
        columnNames.add("anaf_varos");columnNames.add("varos");
        columnNames.add("pro_varos"); columnNames.add("ipsos");
        columnNames.add("day_text"); columnNames.add("UserID");
        setAll_col_names(columnNames);

        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);
        thereIsDatePicker(R.id.dateTV);
        date = dateTV.getText().toString();
        thereIsUpdateButton(R.id.updateButton);

        insertOrUpdateListener(editTexts,null,new String [] {"ID","TransGroupID","date","dateS"});



    }


    private void fabListener() {
      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(  tableView_sigkentrotika( Str_queries.getKATHIMERINO_ZIGISMA_SIGKENTROTIKA(transgroupID),
                      transgroupID,
                      new String[]{"Ημ/νία","Ζύγισμα / Κείμενο ημέρας"},
                      null,
                      new String[]{"dateStr","day_text"},
                      false,
                      false,
                      false));
          }
      });
    }
/*
    private void spinnerInitialize() {
        spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout2, InfoSpecificLists.getDays(15));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySP.setAdapter(spinnerAdapter);

        daySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (isFirstTime)
                    isFirstTime = false;
                else {
                    dayFromSpinner = Utils.getfirstPartSplitString(daySP.getSelectedItem().toString()," ");
                    dayFromSpinner = dayFromSpinner.substring(0 , dayFromSpinner.length()-1);
                    getkathimerinoZigisma();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

*/
    private void getkathimerinoZigisma(){
        getJSON_DATA(Str_queries.getKATHIMERINO_ZIGISMA_PERSON(transgroupID,dateTV.getText().toString()));
    }

    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);

        alertDialog.show();

        if (weHaveData) {
            valuesJson.clear();
            textET.setText("");

          //  if (convertObjToString(results.getJSONObject(0).getString("ID")).equals("") )
                // ΕΞΑΙΡΕΣΗ ΣΕ ΑΥΤΟ ΤΟ ΦΥΛΛΑΔΙΟ ΕΔΩ ΛΟΓΩ QUERY ΘΕΛΟΥΜΕ ΝΑ ΒΛΕΠΟΥΜΕ ΑΝ ΥΠΑΡΧΕΙ ID ΓΙΑ ΤΗ\
                // ΣΥΓΚΕΚΡΙΜΕΝΗ ΜΕΡΑ ΩΣΤΕ ΝΑ ΞΕΡΕΙ ΑΝ ΘΑ ΚΑΝΕΙ UPDATE

                   // weHaveData = false;



                    JSONObject jsonObject = results.getJSONObject(0);
                    id = convertObjToString(jsonObject.get("ID"));
                    transgroupID = convertObjToString(jsonObject.get("TransGroupID"));
                    date = convertObjToString(jsonObject.get("dateS"));
                    anaf_varos = convertObjToString(jsonObject.get("anaf_varos"));
                    varos = convertObjToString(jsonObject.get("varos"));
                    proVaros = convertObjToString(jsonObject.get("pro_varos"));
                    ipsos = convertObjToString(jsonObject.get("ipsos"));
                 //   dayFromSpinner = convertObjToString(jsonObject.get("day"));
                    text = convertObjToString(jsonObject.get("day_text"));

                    dateTV.setText(date);
                    anaf_varosET.setText(anaf_varos);
                    varosET.setText(varos);
                    proVarosET.setText(proVaros);
                    ipsosET.setText(ipsos);
                    //daySP.setSelection(Integer.parseInt(dayFromSpinner) - 1);
                    textET.setText(text);




        }

        else {
            weHaveData = false;
            clearTexts(editTexts);

        }

        alertDialog.dismiss();
    }



    @Override
    public void setValuesTo_valuesJSON(EditText[] editText, TextView[] textView) {

//        if (dayFromSpinner == null || dayFromSpinner.equals("") ) {
//            dayFromSpinner = Utils.getfirstPartSplitString(daySP.getSelectedItem().toString(), " ");
//            dayFromSpinner = dayFromSpinner.substring(0, dayFromSpinner.length() - 1);
//        }
        if (date.equals(""))
            date = getCurrentDate();
        else
            date = dateTV.getText().toString();

      //  valuesJson.add(dayFromSpinner);

        super.setValuesTo_valuesJSON(editText, textView);
        valuesJson.add(Utils.getUserID(this));



    }

    @Override
    public void updateLabel(TextView datetv) {
        super.updateLabel(datetv);
        getkathimerinoZigisma();
    }



    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        getkathimerinoZigisma();


    }

    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);

        getkathimerinoZigisma();
    }
}
