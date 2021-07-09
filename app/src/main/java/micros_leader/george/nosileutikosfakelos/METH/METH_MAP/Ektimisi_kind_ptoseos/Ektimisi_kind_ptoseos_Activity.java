package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.Ektimisi_kind_ptoseos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import micros_leader.george.nosileutikosfakelos.BaseAct.BasicActivityAbs;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.NpaGridLayoutManager;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Ektimisi_kind_ptoseos_Activity extends BasicActivityAbs {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NpaGridLayoutManager manager = (NpaGridLayoutManager) recyclerView.getLayoutManager();
        if (manager != null) {
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {

                    boolean isHeader = isHeader(position, titloi_positions) || listaAdaptor.get(position).getCol_name().equals("remarks");
                    if (isHeader)
                        return 2;
                    else
                        return 1;

                }
            });

        }


    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ektimisi_kind_ptoseos;
    }

    @Override
    protected int rv_ID() {
        return R.id.rv;
    }

    @Override
    protected String setTable() {
        return "Nursing_nosil_ektimisi_kindynou_ptwsews_Meth";
    }

    @Override
    protected ArrayList<ItemsRV> set_RV_list() {
        return InfoSpecificLists.getEktimisi_kind_ptoseos();
    }

    @Override
    protected int[] positionTitlesInRV() {
        return new int[]{4,11,13};
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
        return Str_queries.setglobals(userID,"2", Utils.getcompanyID(this)) +  "select * " +
                " from v_Nursing_nosil_ektimisi_kindynou_ptwsews_Meth " +
                "where transgroupID = " + transgroupID;
    }

    @Override
    protected void runAfterPost(JSONArray results) throws JSONException {

        if (weHaveData){
            setValuesTolistaAdaptor(titloi_positions,newList);
        }
        else{

        }

        adapter.updateLista(newList);


    }
}