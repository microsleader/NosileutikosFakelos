package micros_leader.george.nosileutikosfakelos.BaseAct;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public abstract  class BaseActivity extends BasicActivity {

    public ArrayList <ItemsRV>  copyOF_info_RV_list;
    private int patientsTV, floorSP;
    private int rv_id ;
    private String main_query ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        getPatientsList(this, R.id.patientsTV, R.id.floorsSP);

        table =  setTable();
        titloi_positions = positionTitlesInRV();
        if (titloi_positions == null)
            titloi_positions = new int[]{};

       if (isThereImageUpdateButton())
           thereIsImageUpdateButton();
        listaAdaptor =  set_RV_list();
        getCopyList();
        adapter = new BasicRV(this, listaAdaptor,titloi_positions);
        listaAdaptor = adapter.result;

        getAll_col_names(listaAdaptor);
        if (colNames_not_in_RV() == null || colNames_not_in_RV().length == 0)
             insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID"});

        rv_id = rv_ID();
        setRecyclerViewgridrLayaout( rv_id,  adapter, 2,titloi_positions );


    }


    protected ArrayList<ItemsRV> getCopyList(){
        copyOF_info_RV_list = new ArrayList<>();
        copyOF_info_RV_list.addAll(set_RV_list());
        return copyOF_info_RV_list;
    }

    protected abstract int getLayoutResourceId();
    protected abstract int rv_ID();
    protected abstract String setTable();
    protected abstract ArrayList <ItemsRV> set_RV_list();
    protected abstract int[] positionTitlesInRV();
    protected abstract boolean isThereImageUpdateButton();
    protected abstract String[] colNames_not_in_RV();
    protected abstract String setMainQuery();
    protected abstract void runAfterPost(JSONArray results) throws JSONException;


    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);
        runAfterPost(results);
    }

    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);
       // transgroupID =  Utils.getSplitSPartString(patientsTV. , "," ,3);

        getJSON_DATA(setMainQuery());
    }

    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);
        transgroupID =  Utils.getSplitSPartString(transgroupid , "," ,3);
        getJSON_DATA(setMainQuery());
    }







}
