package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities;

import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

public class SinexeisMetriseis_All_Activity extends BasicActivity implements AsyncCompleteTask2 {


    private RecyclerView sinexeisRV;
    private ArrayList<Map<String, String> > lista;
    private RecyclerView_Metriseis_Adapter adapter;

    private String transgroupID, patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinexeis_metriseis__all_);

        transgroupID = getIntent().getStringExtra("transgroupID");
        patientID = getIntent().getStringExtra("patientID");

        //alertDialog = Utils.setAlertDialog(this);

        lista = new ArrayList<>();

        sinexeisRV = findViewById(R.id.sinexeisRV);
        sinexeisRV.setLayoutManager(new LinearLayoutManager(SinexeisMetriseis_All_Activity.this, LinearLayoutManager.VERTICAL, false));
        sinexeisRV.addItemDecoration(new DividerItemDecoration(SinexeisMetriseis_All_Activity.this, LinearLayout.VERTICAL));
        sinexeisRV.setItemViewCacheSize(20);
        sinexeisRV.setDrawingCacheEnabled(true);
        sinexeisRV.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        sinexeisRV.setHasFixedSize(true);
        sinexeisRV.setNestedScrollingEnabled(false);

        getMetriseis();

    }


    private void getMetriseis() {

        if (Utils.isNetworkAvailable2(this)) {


            alertDialog.show();
            String query;

            if (Utils.getCustomerID(this) == Customers.CUSTID_NEPHROXENIA)
                // GIA ALLOUS
                query = "select   dbo.DateTimeToString(tg.datein) as datestr, " +
                        "dbo.TimeToStr(date) as timestr , ni.* " +
                        "from TransGroup tg " +
                        "inner join Nursing_Hemodialysis ni on tg.id = ni.TransGroupID " +
                        " where  tg.PatientID = " + patientID +
                        " order by tg.DateIn desc";
            else
            // GIA MEDITERANEO
            query = "select  *  , dbo.DateTimeToString(date) as datestr, " +
                    " dbo.TimeToStr(date) as timestr " +
                    " from Nursing_Hemodialysis_2_MEDIT  " +
                    " where  PatientID = " + patientID +
                    " order by date desc " ;




            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = getApplicationContext();
            task.listener = SinexeisMetriseis_All_Activity.this;
            task.query = query;
            task.execute();



        }
    }

    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null) {

            // ΕΑΝ ΔΕΝ ΕΧΕΙ ΣΤΑΤΟΥΣ ΔΗΛΑΔΗ ΕΑΝ ΕΧΕΙ ΑΠΟΤΕΛΕΣΜΑΤΑ ΝΑ ΦΕΡΕΙ ΝΑ ΚΑΝΕΙ ΤΟ ΑΠΟ ΚΑΤΩ
            if (!results.getJSONObject(0).has("status")) {

                for (int i=0; i<results.length(); i++){

                    JSONObject curMetrisi = results.getJSONObject(i);

                    Map <String, String> map= new HashMap<>();
                    Iterator<String> keys = curMetrisi.keys();

                    while (keys.hasNext()) {

                        String str_Name = keys.next();
                        String value = Utils.convertObjToString(curMetrisi.optString(str_Name));
                        map.put(str_Name,value);
                    }
                    lista.add(map);

                }

                adapter = new RecyclerView_Metriseis_Adapter(this,lista);
                sinexeisRV.setAdapter(adapter);

            }
            else
                Toast.makeText(this, R.string.no_data, Toast.LENGTH_SHORT).show();

        }

        alertDialog.dismiss();


    }
}
