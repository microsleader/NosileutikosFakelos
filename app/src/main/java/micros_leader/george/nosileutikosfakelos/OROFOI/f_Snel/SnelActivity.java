package micros_leader.george.nosileutikosfakelos.OROFOI.f_Snel;

import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BaseAct.BasicActivityAbs;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;

public class SnelActivity extends BasicActivityAbs {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public boolean isThereFabSingle() {
        return true;
    }

    @Override
    public void fabSingleListener() {
        startActivity(   tableView_sigkentrotika(Str_queries.getSNEL_sigkentrotika(),
                null,null,InfoSpecificLists.getSNEL_Sigkentrotika(),false,false,false));
    }



    @Override
    protected int getLayoutResourceId() {
        return  R.layout.activity_snel;
    }

    @Override
    protected int rv_ID() {
        return R.id.snelRV;
    }

    @Override
    protected String setTable() {
        return "nursing_snel";
    }

    @Override
    protected ArrayList<ItemsRV> set_RV_list() {
        return InfoSpecificLists.getSNEL_LISTA();
    }

    @Override
    protected int[] positionTitlesInRV() {
        return new int[]{0, 15};
    }

    @Override
    protected boolean isThereImageUpdateButton() {
        return true;
    }

    @Override
    protected String[] colNames_not_in_RV() {
        return new String[0];
    }

    @Override
    protected String setMainQuery() {
        return  Str_queries.getSNEL_PERSON(transgroupID);
    }

    @Override
    protected void runAfterPost(JSONArray results) throws JSONException {
        if (weHaveData){

            for (int i = 0; i < nameJson.size(); i++) {
                if (nameJson.get(i).equals("ID"))
                    id = valuesJson.get(i);
                else if (nameJson.get(i).equals("TransGroupID"))
                    transgroupID = valuesJson.get(i);
            }

            setValuesTolistaAdaptor(titloi_positions,listaAdaptor);     // ΤΟΠΟΘΕΤΗΣΗ ΤΙΜΩΝ ΣΤΗ ΛΙΣΤΑ ΑΝΤΑΠΤΟΡΑ
        }
        else {
            //  ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΔΝΕ ΒΡΗΚΕ ΔΕΔΟΜΕΝΑ ΝΑ ΕΠΑΝΕΛΘΕΙ Η ΛΙΣΤΑ
            // ΣΤΗΝ ΑΡΧΙΚΗ ΑΔΕΙΑ ΤΙΜΗ
            clearListaAdaptor(listaAdaptor);

        }

        adapter.notifyDataSetChanged();
    }





}
