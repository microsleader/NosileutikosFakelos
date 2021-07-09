package micros_leader.george.nosileutikosfakelos.BaseAct;

import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public abstract  class BasicActivityAbs extends BasicActivity {

    public ArrayList <ItemsRV>  copyOF_info_RV_list;
    private int patientsTV, floorSP , layoutID;
    private int rv_id ;
    private String main_query ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutID = getLayoutResourceId();
        setContentView(layoutID);

        if (isThereFloorAndPatientTVs())
            getPatientsList(this, R.id.patientsTV, R.id.floorsSP);
        if (isThereFabSingle()){
            fab = findViewById(R.id.fab);
            fab.setOnClickListener(v -> fabSingleListener());
        }


        table =  setTable();
        titloi_positions = positionTitlesInRV();
        if (titloi_positions == null)
            titloi_positions = new int[]{};

       if (isThereImageUpdateButton())
           thereIsImageUpdateButton();

        main_query = setMainQuery();
        listaAdaptor =  set_RV_list();
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

    protected abstract int getLayoutResourceId();  //Το layout id (γραφικα)
    protected abstract int rv_ID();                 // to recylerviewID
    protected abstract String setTable();           //απο ποιο πινακα στη βαση θα παιρνω δεδομενα
    protected abstract ArrayList <ItemsRV> set_RV_list();   //λιστα του recylerview με τα πεδια
    protected abstract  int[] positionTitlesInRV();         //λιστα με τις θεσεις που έχει τίτλους το recylerview
    protected abstract boolean isThereImageUpdateButton();  //αν εχει κουμπι αποθηκευσευης (δηλαδη αν ειναι θα εχει δυνατοτητα αποθηκευσης ο χρηστης)
    protected abstract String[] colNames_not_in_RV();       // πεδια που δεν ειναι στο recylerview  (σε εξιδικευμενες περιπτωσεις)  πιθανο να θελει βελτιωση ο κωδικας
    protected abstract String setMainQuery();               // το βασικο query που κατεβαζει τα δεδομενα απο τη βαση
    protected abstract void runAfterPost(JSONArray results) throws JSONException;   // κωδικας που θα τρεχει αφου κατέβουν τα δεδομενα


    public boolean isThereFloorAndPatientTVs(){
        //ta id na einai panta R.id.patientsTV, R.id.floorsSP
        return true;
    }

    public boolean isThereFabSingle(){
        //ta id na einai panta R.id.fab
        return false;
    }

    public void fabSingleListener(){
        //κενος  το εχω μονο για override
    }


    public boolean isThereFabMulti(){
        //ta id na einai panta R.id.fab
        return false;
    }

    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);
        newList = getCopyList();
        runAfterPost(results);
    }

    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);
       // transgroupID =  Utils.getSplitSPartString(patientsTV. , "," ,3);

        if (main_query != null && !main_query.isEmpty() )
            getJSON_DATA(setMainQuery());
    }

    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);
        transgroupID =  Utils.getSplitSPartString(transgroupid , "," ,3);
        if (main_query != null && !main_query.isEmpty() )
            getJSON_DATA(setMainQuery());
    }







}
