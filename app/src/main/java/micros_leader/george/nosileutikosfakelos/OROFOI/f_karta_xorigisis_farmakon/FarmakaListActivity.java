package micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon;


import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.View;

import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentMedicineCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.TableView.TableFragment;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityItemListBinding;

import java.util.ArrayList;




public class FarmakaListActivity extends BasicActivity implements MyDialogFragmentMedicineCloseListener {


    public String patientID,oresXorigisisFromFragment;
    public ArrayList<XorigisiOres> listaOresFromFragment = new ArrayList<>();


    private Bundle bundle1;
    private TableFragment tf1 ;

    private ActivityItemListBinding bd;
    private boolean[] ITEMS_HOURS_VALUES = { false, false, false, false, false,false, false, false, false, false,
            false, false, false, false, false,false, false, false, false, false,false, false, false, false};

    private  CharSequence[] ITEMS_HOURS = { "00:00", "01:00","02:00", "03:00","04:00", "05:00","06:00", "07:00",
            "08:00", "09:00","10:00", "11:00","12:00", "13:00", "14:00", "15:00","16:00", "17:00","18:00", "19:00","20:00", "21:00",
            "22:00", "23:00"};
    private CharSequence[] ITEMS;
    private boolean[] ITEMS_VALUES;
    private boolean isForSetHours ;
    private String ITEM_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityItemListBinding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);


        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);
        medicinesBTListener();
        medicinesBTSetHoursListener();
        medicinesBTDisplayHoursListener();
        alertDialog.dismiss();
    }

    private void medicinesBTListener() {
        bd.medicinesBT.setOnClickListener(v -> medicines());
    }


    private void medicinesBTSetHoursListener() {
        bd.medicinesHoursBT.setOnClickListener(v -> {
            isForSetHours = true;
            getMedicinesForPatientAndSelectOrViewHours();
        });

    }

    private void medicinesBTDisplayHoursListener() {
        bd.medicinesDisplayHoursBT.setOnClickListener(v -> {
            isForSetHours = false;
            getMedicinesForPatientAndSelectOrViewHours();
        });
    }






    private void medicines (){
        bundle1  =   tableView_sigkentrotika_dialogFragment( Str_queries.getSigkentrotika_karta_xorigisis_farmakon(transgroupID),
                transgroupID,
                null,
                InfoSpecificLists.getKartaXorigisisFarmakwn(),
                false,
                false,
                true);

        bundle1.putString("toolbar_title","Συγκεντρωτικά φαρμάκων");
        showFragment();

    }


    private void getMedicinesForPatientAndSelectOrViewHours(){
        getJSON_DATA(Str_queries.get_karta_xorigisis_farmakon_items(transgroupID));
    }

    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null && !results.getJSONObject(0).has("status")){

            ArrayList<String> ids = new ArrayList();
            ArrayList<String> items = new ArrayList();
            String [] meds = new String[results.length()];

            for (int i = 0; i < results.length(); i++) {
                JSONObject jsonObject = results.getJSONObject(i);
                String xorigisiID = Utils.convertObjToString(jsonObject.getInt("id"));
                String item = jsonObject.getString("item");

            if (isForSetHours){
                //ΚΑΤΑΧΩΡΗΣΗ ΩΡΩΝ
                meds[i] = xorigisiID + "," + item;

            }
            else {
                //ΠΡΟΒΟΛΗ ΩΡΩΝ
                ids.add(xorigisiID);
                items.add(item);

                }

            }

            //ΕΔΩ ΑΦΟΥ ΤΕΛΕΙΩΣΕΙ Η ΕΠΑΝΑΛΗΨΗ ΑΠΟ ΠΑΝΩ ΚΑΛΕΙ ΤΗΝ ΑΝΑΛΟΓΗ ΜΕΘΟΔΟ
            if (isForSetHours){
                medicines_set_hours(meds);
            }
            else
                manageLists(ids, items);
        }






        else
            Toast.makeText(this, R.string.no_data, Toast.LENGTH_SHORT).show();

        alertDialog.dismiss();


    }






    private void medicines_set_hours(String[] meds){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_item)
        .setSingleChoiceItems(meds, 0, null)
                .setPositiveButton("ΟΚ", (dialog, whichButton) -> {
                    dialog.dismiss();
                    int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                    select_hours(meds[selectedPosition]);

                });

        builder.show();


    }

    private void select_hours(String itemID){

        ArrayList <String> medsHours = new ArrayList();
        AlertDialog.Builder builder = Utils.getAlertMultipleChoice(this, ITEMS_HOURS, ITEMS_HOURS_VALUES);
        builder.setPositiveButton("ΟΚ", (dialog, whichButton) -> {
            dialog.dismiss();
            for (int i=0; i<ITEMS_HOURS.length; i ++){

                if (ITEMS_HOURS_VALUES[i]){
                    medsHours.add(String.valueOf(ITEMS_HOURS[i]));
                }
            }

            if (!medsHours.isEmpty()){
                setHoursToDatabase(Utils.getSplitSPartString(itemID,",",0),medsHours);

            }

       });


       builder.show();
    }


    private void setHoursToDatabase(String itemID, ArrayList<String> medsHours){

        StringBuilder sb = new StringBuilder("");
        sb.append("exec dbo.disable_triggers " );

        for (String hour: medsHours) {
            sb.append(" insert into Nursing_ores_xorigisis(date,xorigisiID, hour) " + "values( dbo.timeToNum(CONVERT(datetime,GETDATE() , 103)) , ")
                    .append(itemID).append(",").append(Utils.convertHourTomillisecondsGR(hour)).append(")");
        }
        sb.append(" exec dbo.enable_triggers ");

//        String query = sb.toString();
//        AsyncTaskUpdate task = new AsyncTaskUpdate(this, query);
//        task.listener = this;
//        task.execute();
        runORupdate_simple_query(sb.toString());
        ITEM_ID = itemID;
    }


    private void medicines_display_hours (String medicineIDS){

        bundle1  = tableView_sigkentrotika_dialogFragment(Str_queries.getSigkentrotika_karta_xorigisis_farmakon_hours(transgroupID, medicineIDS),
                null,
                new String[]{"ID","Χρήστης","Είδος","Ωρα χορήγησης","Χορηγήθηκε"},
                null,
                InfoSpecificLists.getKartaXorigisisFarmakwn_provoli_hours(),
                false,
                false,
                true);

        bundle1.putString("toolbar_title","Συγκεντρωτικά ώρες χορήγησης");
        showFragment();
    }




    private void showFragment(){



         tf1 = new TableFragment();
         tf1.setArguments(bundle1);


        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
        bd.fragment1.clearChildFocus(bd.fragment1);

        getSupportFragmentManager().beginTransaction()
                .add(bd.fragment1.getId(), tf1)
                .commit();


    }





    private void manageLists(ArrayList<String> ids, ArrayList<String> items){

        ITEMS = new CharSequence[items.size()];
        ITEMS_VALUES = new boolean[items.size()];

        for (int i=0; i<items.size(); i ++){
            ITEMS[i] = ids.get(i) + "," + items.get(i);
            ITEMS_VALUES[i] = true;
        }



        showCheckboxDialog();

    }






    private void showCheckboxDialog(){

        AlertDialog.Builder builder = Utils.getAlertMultipleChoice(this, ITEMS, ITEMS_VALUES);
        builder.setTitle(R.string.choose_item);
        builder.setPositiveButton("OK",
                (dialog, which) -> {

                    StringBuilder sb = new StringBuilder();
                    String medicinesIDS = "";

                    for (int i=0; i< ITEMS_VALUES.length; i  ++){
                        if (ITEMS_VALUES[i])
                            sb.append(Utils.getSplitSPartString((String) ITEMS[i],",",0) + ",");
                    }

                    if (sb.length() > 1)
                        medicinesIDS = sb.substring(0,sb.length() - 1);


                    medicines_display_hours(medicinesIDS);
                });

        builder.setNegativeButton("Ακύρωση", (dialog, which) -> {});


        AlertDialog dialog = builder.create();
        dialog.show();

    }





    @Override
    public void dialogMedicineClose(String id_name) {
        tf1.setMedicineInfo(id_name);

    }




    @Override
    public void taskCompleteGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompleteGetPatients(lista);
        transgroupID = Utils.getSplitSPartString(patientsTV.getText().toString(),"," , 3);

    }






    @Override
    public void update(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        if (str.equals(getString(R.string.successful_update)) && isForSetHours)
            medicines_display_hours(ITEM_ID);
        alertDialog.dismiss();


    }







}
