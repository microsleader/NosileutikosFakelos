package micros_leader.george.nosileutikosfakelos.OROFOI.f_EktimisiEpigonton;

import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ClassItemsCheckboxesForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.R;


public class EktimisiEpigontonActivity extends BasicActivity implements AsyncCompleteGetPatientsTask, MyDialogFragmentCloseListener, AsyncCompleteTask2 {

    private RecyclerView ektimisiRV;
    private EktimisiEpigontonRV adapter;
    private ArrayList<ClassItemsCheckboxesForRV> ektimisiListaAdaptor;
    private TextView energeiaTV , sinolikosVathmosTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ektimisi_epigonton_new);

        energeiaTV = findViewById(R.id.energeiaTV);
        sinolikosVathmosTV = findViewById(R.id.sinolikosVathmosTV);

        titloi_positions   = new int[]{0,8, 19,44};
        adapter = new EktimisiEpigontonRV(this, InfoSpecificLists.getEktimisiEpigontonCheckboxes2(),titloi_positions);
        listaAdaptor = adapter.result;

        table = "nursing_ektimisi_epigonton";
        getAll_col_names(InfoSpecificLists.getEktimisiEpigontonCheckboxes2());


        setRecyclerViewgridrLayaout( R.id.ektimisiRV,  adapter, 2,titloi_positions );

        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);

        thereIsImageUpdateButton();
        //thereIsUpdateButton(R.id.updateButton);

        insertOrUpdateListener(listaAdaptor,new String [] {"TABLE_ID","ID","TransGroupID","sinolo_vathmou","action"});


    }




    private void getEktimisiEpigonton(){
   //     getJSON_DATA(new StringQueries().getEKTIMISI_EPIGONTON_PERSON(transgroupID));
    getJSON_DATA("select n1.*, n2.sinolo_vathmou, n2.action " +
            "from Nursing_Ektimisi_Epigonton n1 " +
            "left join v_Nursing_Ektimisi_Epigonton n2 on n2.TransGroupID = n1.TransGroupID where n1.TransGroupID = " + transgroupID);
    }



    @Override
    public void taskComplete2(JSONArray results) throws JSONException {



        super.taskComplete2(results);


        if (weHaveData) {


            for (int i = 0; i < nameJson.size(); i++) {


                if (nameJson.get(i).equals("ID"))
                    id = valuesJson.get(i);
                else if (nameJson.get(i).equals("TransGroupID"))
                    transgroupID = valuesJson.get(i);

                else if(nameJson.get(i).equals("travma"))
                    valuesJson.set(i,valuesJson.get(i).equals("65") ? "true" : "");


            }


               if(sinolikosVathmos != 0) {
                sinolikosVathmosTV.setText(String.valueOf(sinolikosVathmos));
                if (sinolikosVathmos <=2){
                    energeiaTV.setTextColor(Color.GREEN);
                    sinolikosVathmosTV.setTextColor(Color.GREEN);
                }
                else if (sinolikosVathmos <=4)
                {
                    energeiaTV.setTextColor(Color.YELLOW);
                    sinolikosVathmosTV.setTextColor(Color.YELLOW);
                }
                else if (sinolikosVathmos <=6) {
                    energeiaTV.setTextColor(Color.parseColor("#ff8000"));
                    sinolikosVathmosTV.setTextColor(Color.parseColor("#ff8000"));
                }
                else if (sinolikosVathmos >= 7) {
                    energeiaTV.setTextColor(Color.RED);
                    sinolikosVathmosTV.setTextColor(Color.RED);
                }


            }

                energeiaTV.setText(action);



          //  valuesJson =   Utils.deleteProtesTheseis(valuesJson, 2); // ΔΙΑΓΡΑΦΗ ΚΑΠΟΙΩΝ ΠΡΩΤΩΝ ΤΙΜΩΝ ΑΠΟ ΤΗ ΛΙΣΤΑ ΠΟΥ ΚΑΤΕΒΑΣΑΜΕ
         //   valuesJson =   Utils.deleteTeleutaiesTheseis(valuesJson, 2); // ΔΙΑΓΡΑΦΗ ΚΑΠΟΙΩΝ ΠΡΩΤΩΝ ΤΙΜΩΝ ΑΠΟ ΤΗ ΛΙΣΤΑ ΠΟΥ ΚΑΤΕΒΑΣΑΜΕ


            setValuesTolistaAdaptor(titloi_positions,listaAdaptor);     // ΤΟΠΟΘΕΤΗΣΗ ΤΙΜΩΝ ΣΤΗ ΛΙΣΤΑ ΑΝΤΑΠΤΟΡΑ

        } else {
            clearListaAdaptor(listaAdaptor);
            energeiaTV.setText("");
            sinolikosVathmosTV.setText("");
        }


        adapter.notifyDataSetChanged();



    }


    @Override
    public void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor) {
        super.setValuesTo_valuesJSON(titles_positions, listAdaptor);


        int pos =  nameJson.indexOf("travma");

        // TO -2 EINAI EPEIDH DEN TIMES GIA ID KAI TRANSGROUP ID STI VALUESJSON MIAS KAI DIAGRAFONTAI AFOU KATEVOUN
        // TO 7 EINAI I THESI GIA TO TRAVMA STI LISTA
        valuesJson.set(pos ,listAdaptor.get(7).getTrue() ? "65" : "64");





    }





    @Override
    public void updateLabel(TextView datetv) {
        super.updateLabel(datetv);
        getEktimisiEpigonton();
    }




    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        getEktimisiEpigonton();


    }

    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);

        getEktimisiEpigonton();
    }
}
