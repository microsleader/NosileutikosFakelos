package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_istoriko;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_logodosia.Nosil_Anafora_Logodosia_Activity;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityNosilIstorikoBinding;

public class Nosil_IstorikoActivity_NEW extends BasicActivity  {



    // ΑΥΤΟ ΤΟ ΦΥΛΛΑΔΙΟ ΕΙΝΑΙ ΤΟ ΠΡΩΤΟ ΠΟΥ ΣΥΜΠΛΗΡΩΝΕΤΑΙ ΚΑΙ ΕΙΝΑΙ ΜΟΝΟ ΜΙΑ ΕΓΓΡΑΦΗ ΑΝΑ ΝΟΣΗΛΕΙΑ
    // ΜΕ ΤΟ ΠΟΥ ΣΥΜΠΛΗΡΩΘΕΙ ΠΡΕΠΕΙ ΝΑ ΑΠΟΘΗΚΕΥΕΙ ΚΑΠΟΙΑ ΔΕΔΟΜΕΝΑ ΚΑΙ ΣΕ ΑΛΛΑ ΦΥΛΛΑΔΙΑ ΤΑ ΟΠΟΙΑ ΕΚΕΙ ΜΟΡΟΥΝ ΝΑ ΑΛΛΑΧΤΟΥΝ
    // ΑΥΤΟ ΘΑ ΓΙΝΕΙ ΜΕ ΤΑ ΤΡΙΓΚΕΡ

    ActivityNosilIstorikoBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityNosilIstorikoBinding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);

        managefabMenuIcon();

        titloi_positions   = new int[]{0, 24,33, 36, 43,63,78,93,102,114,120,125};

        table = "Nursing_nosil_istoriko";

        getAll_col_names(InfoSpecificLists.getNosil_Istoriko_lista_new());

        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);

        thereIsImageUpdateButton();



    }




    private void managefabMenuIcon() {

        bd.fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DialogFragmentFarmaka df =  new DialogFragmentFarmaka();
//                Bundle putextra = new Bundle();
//                putextra.putString("transgroupID", transgroupID);
//                df.setArguments(putextra);
//                df.show(getSupportFragmentManager() , "Dialog");

                Intent in  =   tableView_sigkentrotika( Str_queries.getSigkentrotika_karta_xorigisis_farmakon(transgroupID),
                        transgroupID,
                        null,
                        InfoSpecificLists.getKartaXorigisisFarmakwn(),
                        false,
                        false,
                        true);

                in.putExtra("toolbar_title","Φάρμακα που λαμβάνει");
                startActivity(in);

            }
        });
    }






    private void getNosil_Istoriko(){

        getJSON_DATA( new Str_queries().getNOSIL_ISTORIKO_PERSON(transgroupID),  new String[]{"ID","TransGroupID"}  ,nameJson);
    }



    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);

        alertDialog.show();
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
                    bd.sinoloTV.setText("");
                else if (sinolo < 5)
                    bd.sinoloTV.setText("Πρέπει να δωθεί στον ασθενή/συγγενή ΦΥΛΛΟ ΟΔΗΓΙΩΝ για την ΠΡΟΛΗΨΗ ΠΤΩΣΕΩΝ");
                else
                    bd.sinoloTV.setText("Πρέπει να διενεργηθεί ΑΞΙΟΛΟΓΗΣΗ ΑΣΘΕΝΟΥΣ για την ΠΡΟΛΗΨΗ ΠΤΩΣΕΩΝ");
            }


            setValuesTolistaAdaptor(titloi_positions,newList);

        }
        else
            bd.sinoloTV.setText("");



        setAdapterAndUpdateValues(R.id.nos_IstorikoRV,2,new String [] {"ID","TransGroupID"});



       alertDialog.dismiss();
    }




    @Override
    public void taskCompletePlanoKlinonGetPatients (ArrayList<PatientsOfTheDay> lista) {
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
        if (item.getItemId() == R.id.filladio_nosil_anaforas) {// do something
            startActivity(new Intent(Nosil_IstorikoActivity_NEW.this, Nosil_Anafora_Logodosia_Activity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void handleDialogClose(String transgroupid) {

        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT
        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);
        getNosil_Istoriko();
    }








}
