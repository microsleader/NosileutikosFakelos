package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

public class StatheresMetriseis_All_Activity extends AppCompatActivity implements AsyncCompleteTask2 {

    private RecyclerView statheresRV;
    public AlertDialog alertDialog;
    private ArrayList <Map <String, String> >lista;
    private RecyclerView_Metriseis_Adapter adapter;

    private String transgroupID, patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statheres_metriseis__all_);

        transgroupID = getIntent().getStringExtra("transgroupID");
        patientID = getIntent().getStringExtra("patientID");

        alertDialog = Utils.setLoadingAlertDialog(this);

        lista = new ArrayList<>();

        statheresRV = findViewById(R.id.statheresRV);
        statheresRV.setLayoutManager(new LinearLayoutManager(StatheresMetriseis_All_Activity.this, LinearLayoutManager.VERTICAL, false));
        statheresRV.addItemDecoration(new DividerItemDecoration(StatheresMetriseis_All_Activity.this, LinearLayout.VERTICAL));
        statheresRV.setItemViewCacheSize(300);
        statheresRV.setDrawingCacheEnabled(true);
        statheresRV.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        statheresRV.setHasFixedSize(true);
        statheresRV.setNestedScrollingEnabled(false);

        getMetriseis();

    }




    private void getMetriseis() {

        if (Utils.isNetworkAvailable2(this)) {


            alertDialog.show();
            String query ;


        if (Utils.getCustomerID(this) == Customers.CUSTID_NEPHROXENIA)
            //  GIA TOUS ALLOUS
            query = "select   dbo.datetostr(tg.datein) as datestr," +
                    " dbo.timeToStr(ni.diarkia) as diarkiaStr ," +
                    " nos1.name as nosileutis1, " +
                    " nos2.name as nosileutis2," +
                    " nos_flev.name as nos_flev," +
                    " ni.* " +
                    "from TransGroup tg " +
                    "inner join Nursing_Hemodialysis_initial ni on tg.id = ni.TransGroupID " +
                    "left join users nos1 on nos1.ID = ni.ipefthinos_nosileftis " +
                    "left join users nos2 on nos2.ID = ni.nosileftis_2 " +
                    "left join users nos_flev on nos_flev.ID = ni.nosileftis_flevokentisis " +
                    " where  tg.PatientID = " + patientID +
                    " order by tg.DateIn desc";
            else

             //GIA TIN MEDITERANEO
             query = " select  *  , dbo.dateToStr(date) as datestr, " +
                     " dbo.NAMEPERSON(ipefthinos_iatros_vardias) as docName, " +
                     " dbo.timetostr(schedule_time_start) as schtimeStart, " +
                     " dbo.timetostr(time_start) as timeStart, " +
                     " dbo.timetostr(duration) as dur " +
                    " from v_Nursing_Hemodialysis_initial2_MEDIT     " +
                    " where  PatientID = " + patientID +
                    " order by date desc " ;



            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = getApplicationContext();
            task.listener = StatheresMetriseis_All_Activity.this;
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
                        if (str_Name.equals("fistula") || str_Name.equals("mosxeuma")) {
                            int val = 0;
                            try {
                                 val = value.equals("") ? 0 : Integer.parseInt(value);
                                value = InfoSpecificLists.getAkra_by_id(val);


                            }
                            catch (NumberFormatException e){

                            }

                        }
                        map.put(str_Name,value);
                    }
                    lista.add(map);

                }

                adapter = new RecyclerView_Metriseis_Adapter(this,lista);
                statheresRV.setAdapter(adapter);

            }
            else
                Toast.makeText(this, R.string.no_data, Toast.LENGTH_SHORT).show();

        }

        alertDialog.dismiss();

    }
}
