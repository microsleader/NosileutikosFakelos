package micros_leader.george.nosileutikosfakelos.OROFOI.f_example;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;

public class ExampleForBasicActivity extends BasicActivity {

    private RecyclerView exampleRV;

    private String id, kinitikotita, anapSixnotita , kardSixnotita, sap,
            thermokrasia , epipSinProsoxis;
    private RecyclerViewExampleAdaptor adapter;

    private Spinner spns [] = new Spinner[6];
    private int spns_ids[] = { R.id.kinitikotitaSP , R.id.anapSixnotitaSP,
            R.id.kardSixnotitaSP, R.id.sapSP , R.id.thermokrasiaSP , R.id.epipSinProsoxisSP };

    private TextView txvs [] = new TextView[2];
    private int txtvs_ids[]   =   {R.id.sinolVathmosTV ,R.id.energeiaTV };

    private int titles_positions[]   =   {0,11,36};


    private CheckBox traumaCH;
    private  ArrayList<ItemsRV> listaAdaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ektimisi_epigonton_old);

       // setLayout(R.layout.activity_example);

        initElements(spns,spns_ids);
        initElements(txvs,txtvs_ids);

        setSpinnerAdapter(spns[0],InfoSpecificLists.getKinitikotitaLista());
        setSpinnerAdapter(spns[1],InfoSpecificLists.getAnapneustikiSixnotitaLista());
        setSpinnerAdapter(spns[2],InfoSpecificLists.getKardiakiSixnotitaLista());
        setSpinnerAdapter(spns[3],InfoSpecificLists.getSAP_lista());
        setSpinnerAdapter(spns[4],InfoSpecificLists.getThermokrasiaLista());
        setSpinnerAdapter(spns[5],InfoSpecificLists.getEpipedoSinProsoxisLista());

        traumaCH = findViewById(R.id.traumaCH);
        thereIsUpdateButton(R.id.updateButton);

        adapter = new RecyclerViewExampleAdaptor(this, InfoSpecificLists.getEktimisiEpigonton_PERSON());
        listaAdaptor = adapter.result;

        setRecyclerViewgridrLayaout( R.id.ektimisiRV,  adapter, 2,titles_positions );
        getPatientsList(R.id.patientsTV);




        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateButton.startAnimation();
                nameJson.remove(0);
                nameJson.remove(0);
                nameJson.remove(0); nameJson.remove(0);
                nameJson.remove(0); nameJson.remove(0);
                nameJson.remove(0); nameJson.remove(0);
                nameJson.remove(0); nameJson.remove(0);

                ArrayList <String> final_values = new ArrayList<>();
                for (int i=0; i<listaAdaptor.size(); i++) {
                    final_values.add(String.valueOf(listaAdaptor.get(i).getTrue()));
                }

                final_values.remove(36);
                final_values.remove(11);

                final_values.remove(0);


                for (int i=0; i<8; i++) {
                    nameJson.remove(nameJson.size()-1);
                }


            //    insertOrUpdateListener(nameJson, valuesJson,true);
                }
        });



    }

    private void getEksitirio(){
        getJSON_DATA(new Str_queries().getEKTIMISI_EPIGONTON_PERSON(transgroupID));

    }


    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);

     if (weHaveData) {

         for (int i = 0; i < nameJson.size(); i++) {
             if (nameJson.get(i).equals("ID"))
                 id = valuesJson.get(i);
             else if (nameJson.get(i).equals("TransGroupID"))
                 transgroupID =  valuesJson.get(i);
             else if (nameJson.get(i).equals("kinitikotita")) {
                 kinitikotita = valuesJson.get(i);
                 spns[0].setSelection(InfoSpecificLists.getKinitikotitaListaID(valuesJson.get(i)));
             }
             else if (nameJson.get(i).equals("anapnefstiki_sixnotita")) {
                 anapSixnotita = valuesJson.get(i);
                 spns[1].setSelection(InfoSpecificLists.getAnapneustikiSixnotitaListaID(valuesJson.get(i)));
             }
             else if (nameJson.get(i).equals("kardiaki_sixnotita")) {
                 kardSixnotita = valuesJson.get(i);
                 spns[2].setSelection(InfoSpecificLists.getKardiakiSixnotitaListaID(valuesJson.get(i)));

             }
             else if (nameJson.get(i).equals("sistoliki_artiriaki_piesi")) {
                 sap = valuesJson.get(i);
                 spns[3].setSelection(InfoSpecificLists.getSAP_listaID(valuesJson.get(i)));

             }
             else if (nameJson.get(i).equals("thermokrasia")) {
                 thermokrasia = valuesJson.get(i);

                 spns[4].setSelection(InfoSpecificLists.getThermokrasiaListaID(valuesJson.get(i)));

             }
             else if (nameJson.get(i).equals("sinidisi_prosoxi")) {
                 epipSinProsoxis = valuesJson.get(i);
                 spns[5].setSelection(InfoSpecificLists.getEpipedoSinProsoxisListaID(valuesJson.get(i)));

             }
             else if (nameJson.get(i).equals("travma")) {
                 if (valuesJson.get(i).equals("65"))
                     traumaCH.setChecked(true);
                 else
                     traumaCH.setChecked(false);
             }

             else if (nameJson.get(i).equals("sinolo_vathmou"))
                 txvs[0].setText(valuesJson.get(i));

             else if (nameJson.get(i).equals("action"))
                 txvs[1].setText(valuesJson.get(i));


         }


         for (int x =0; x< 10; x ++){                     //Για να διαγραφουν οι πρωτες 10 τιμες
             valuesJson.remove(0);
         }

         for (int x =0; x< 8; x ++){                     //Για να διαγραφουν οι τελευταιες 8 τιμες
             valuesJson.remove(valuesJson.size() - 1);
         }

         valuesJson.add(0,"false");    //  ΕΔΩ ΘΕΣΗ ΤΙΤΛΟΥ ΟΠΟΤΕ ΑΔΙΑΦΟΡΟ
         valuesJson.add(11,"false");   //  ΕΔΩ ΘΕΣΗ ΤΙΤΛΟΥ ΟΠΟΤΕ ΑΔΙΑΦΟΡΟ
         valuesJson.add(36,"false");   //  ΕΔΩ ΘΕΣΗ ΤΙΤΛΟΥ ΟΠΟΤΕ ΑΔΙΑΦΟΡΟ


         for (int x = 0; x<valuesJson.size(); x++){

                 boolean value = Boolean.parseBoolean(valuesJson.get(x));

                 listaAdaptor.get(x).setTrue(value);

         }


     }

     else
         clearTexts(spns);


     adapter.notifyDataSetChanged();
    }

    @Override
    public void taskCompleteGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompleteGetPatients(lista);

                getEksitirio();
    }

    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);

        getEksitirio();
    }



}
