package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Nosil_logodosia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Logodosia_Meth_Activity extends BasicActivity {


    private TextView vardiaTV;
    private EditText nosilAksiologisiET;
    private BasicRV adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logodosia__meth);

        dateTV = findViewById(R.id.dateTV);
        vardiaTV = findViewById(R.id.vardiaTV);
        nurseTV = findViewById(R.id.nurseTV);
        nosilAksiologisiET = findViewById(R.id.nosilAksiologisiET);
        Intent in = getIntent();
        transgroupID = in.getStringExtra("transgroupID");
        date = in.getStringExtra("date");
        dateTV.setText(date);
        vardiaID = in.getStringExtra("vardiaID");
        vardiaTV.setText(in.getStringExtra("vardiaText"));

        table = "Nursing_nosil_logodosia_Meth";

        thereIsImageUpdateButton();

        titloi_positions = new int[]{2};
        adapter = new BasicRV(this, InfoSpecificLists.getNosileutikiLogodosiaMeth(),titloi_positions);
        listaAdaptor = adapter.result;

        getAll_col_names(InfoSpecificLists.getNosileutikiLogodosiaMeth());

        setRecyclerViewgridrLayaout(recyclerView, R.id.logodosiaMethRV,  adapter, 2,titloi_positions );
        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID","Date"});


        getLogodosia_meth();
    }


    private void getLogodosia_meth(){

        getJSON_DATA(new Str_queries().getNosileutikiLogodosiaMeth(transgroupID,date,vardiaID) );

    }


    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);

        if (weHaveData){

            vardiaID = Utils.convertObjToString(results.getJSONObject(0).get("vardiaID"));
            setValuesTolistaAdaptor(titloi_positions,listaAdaptor);     // ΤΟΠΟΘΕΤΗΣΗ ΤΙΜΩΝ ΣΤΗ ΛΙΣΤΑ ΑΝΤΑΠΤΟΡΑ

            nosilAksiologisiET.setText(Utils.convertObjToString(results.getJSONObject(0).get("aksiologisi")));
        }
        else
            nosilAksiologisiET.setText("");


        adapter.notifyDataSetChanged();

    }

    @Override
    public void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor) {
        super.setValuesTo_valuesJSON(titles_positions, listAdaptor);

        nameJson.add("vardiaID");
        valuesJson.add(vardiaID);

        nameJson.add("aksiologisi");
        valuesJson.add(nosilAksiologisiET.getText().toString());
    }
}
