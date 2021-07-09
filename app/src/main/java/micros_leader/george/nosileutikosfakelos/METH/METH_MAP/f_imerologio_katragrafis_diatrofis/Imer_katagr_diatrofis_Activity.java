package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_imerologio_katragrafis_diatrofis;

import androidx.appcompat.app.AppCompatActivity;
import micros_leader.george.nosileutikosfakelos.BaseAct.BasicActivityAbs;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Imer_katagr_diatrofis_Activity extends BasicActivityAbs {

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_imer_katagr_diatrofis;
    }

    @Override
    protected int rv_ID() {
        return R.id.snelRV;
    }

    @Override
    protected String setTable() {
        return "Nursing_imerologio_katagrafis_diatrofis";
    }

    @Override
    protected ArrayList<ItemsRV> set_RV_list() {
        return InfoSpecificLists.getImerologio_katagr_diatrofis();
    }

    @Override
    protected int[] positionTitlesInRV() {
        return new int[0];
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
        return Str_queries.getImer_kat_diat(transgroupID);
    }

    @Override
    protected void runAfterPost(JSONArray results) throws JSONException {

    }
}