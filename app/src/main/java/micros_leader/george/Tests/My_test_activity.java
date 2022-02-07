package micros_leader.george.Tests;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.MainGraphicsActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_istoriko.DialogFragmentFarmaka;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_logodosia.Nosil_Anafora_Logodosia_Activity;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class My_test_activity extends MainGraphicsActivity {

    private TextView sinoloTV;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setMainLayoutScreen("Α) Νοσηλευτικό Ιστορικό",true);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT,
                58);
        params.setMargins(0,12,0,0);
        sinoloTV = createTextview(200,"sinolo",params, R.drawable.textview_shape);
        linearLayout.addView(sinoloTV);

        //-------------
        managefabMenuIcon();


        titloi_positions   = new int[]{0, 20,29,32,41,57,70,85,94,106,112,117};
        table = "Nursing_nosil_istoriko";
        getAll_col_names_and_set_adapter(this, InfoSpecificLists.getNosil_Istoriko_lista_new() ,titloi_positions);


        //setRecyclerViewgridrLayaout( adapter, 2,titloi_positions );
        getPatientsList(this);

        thereIsImageUpdateButton();

        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID"});



    }



    @Override
    public void createDateTV() {
    }









    private void managefabMenuIcon() {



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragmentFarmaka df =  new DialogFragmentFarmaka();
                Bundle putextra = new Bundle();
                putextra.putString("transgroupID", transgroupID);
                df.setArguments(putextra);
                df.show(getSupportFragmentManager() , "Dialog");
            }
        });
    }











    private void getNosil_Istoriko(){
        getJSON_DATA( new Str_queries().getNOSIL_ISTORIKO_PERSON(transgroupID),new String[]{"ID","TransGroupID"},nameJson);
    }



    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);

        newList = InfoSpecificLists.getNosil_Istoriko_lista_new();


        if (weHaveData){


            for (int i = 0; i < nameJson.size(); i++) {
                if (nameJson.get(i).equals("ID"))
                    id = valuesJson.get(i);
                else if (nameJson.get(i).equals("TransGroupID"))
                    transgroupID = valuesJson.get(i);


            }

            if (results.getJSONObject(0).has("blue_count")) {

                int sinolo = results.getJSONObject(0).getInt("blue_count");

                if (sinolo == 0)
                    sinoloTV.setText("");
                else if (sinolo < 5)
                    sinoloTV.setText("Πρέπει να δωθεί στον ασθενή/συγγενή ΦΥΛΛΟ ΟΔΗΓΙΩΝ για την ΠΡΟΛΗΨΗ ΠΤΩΣΕΩΝ");
                else
                    sinoloTV.setText("Πρέπει να διενεργηθεί ΑΞΙΟΛΟΓΗΣΗ ΑΣΘΕΝΟΥΣ για την ΠΡΟΛΗΨΗ ΠΤΩΣΕΩΝ");
            }


            setValuesTolistaAdaptor(titloi_positions,newList);

        }
        else {
            //  ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΔΝΕ ΒΡΗΚΕ ΔΕΔΟΜΕΝΑ ΝΑ ΕΠΑΝΕΛΘΕΙ Η ΛΙΣΤΑ
            // ΣΤΗΝ ΑΡΧΙΚΗ ΑΔΕΙΑ ΤΙΜΗ
            sinoloTV.setText("");

        }
        adapter.updateLista(newList);

        // adapter.notifyDataSetChanged();

    }




    @Override
    public void updateSuccess(String str) {
        super.updateSuccess(str);

        getNosil_Istoriko();
    }

    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);
        transgroupID = Utils.getSplitSPartString(patientsTV.getText().toString(),",",3);

        getNosil_Istoriko();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filladio_nosil_anaforas:
                // do something
                startActivity(new Intent(My_test_activity.this, Nosil_Anafora_Logodosia_Activity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public void handleDialogClose(String transgroupid) {

        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT
        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);
        getNosil_Istoriko();
    }
}
