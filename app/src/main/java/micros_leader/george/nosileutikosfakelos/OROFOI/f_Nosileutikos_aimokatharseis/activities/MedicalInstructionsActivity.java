package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;

public class MedicalInstructionsActivity extends BasicActivity {

    private RecyclerView instractionsRV;
    private String patientid,companyID;
    private BasicRV adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_instructions);

        transgroupID = getIntent().getStringExtra("transgroupID");
        patientid = getIntent().getStringExtra("patientID");
        companyID = getIntent().getStringExtra("companyID");
        table = "Nursing_Medical_Instructions";

        adapter = new BasicRV(this, InfoSpecificLists.getMedicalInsLista());
        listaAdaptor = adapter.result;

        getAll_col_names(InfoSpecificLists.getMedicalInsLista());
        setRecyclerViewgridrLayaout(instractionsRV, R.id.instructionsRV,  adapter, 2,null );

        getMedicalInsructions();
    }

    private void getMedicalInsructions(){
        String cols = getFROM_NAMEJSON_COLUMNS(nameJson ,"n");
        getJSON_DATA( Str_queries.getMEDICAL_INSTRACTIONS_OLD(cols,patientid));

    }



    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);

        if (weHaveData){

            String doctorName = results.getJSONObject(0).getString("doctorName");
            String month = results.getJSONObject(0).getString("Month");
            String yearName = results.getJSONObject(0).getString("yearName");
            String durationTime = results.getJSONObject(0).getString("durationTime");


            for (int i = 0; i < nameJson.size(); i++)
                setValuesTolistaAdaptor(null,listaAdaptor);

            listaAdaptor.get(0).setValue(month + " / " + yearName);
            listaAdaptor.get(1).setValue(doctorName);
            listaAdaptor.get(3).setValue(durationTime);

        }


        else
            clearListaAdaptor(listaAdaptor);



        adapter.notifyDataSetChanged();

        alertDialog.dismiss();
    }




}
