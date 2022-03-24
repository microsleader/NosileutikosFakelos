package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Simple_Items;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Kathetires_Activity_NEW extends Kathetires_Activity{

    public Button neaEggrafiBT;
   // public ArrayList<Simple_Items> lista_RV;
    private Kathetires_RV adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        neaEggrafiBT = findViewById(R.id.newEntryBT);
    }

    @Override
    public void initialize() {
        simple_items_lista = new ArrayList<>();
        super.initialize();
        nameJson.clear();

        nameJson.add("itemID");
        nameJson.add("dateStart");
        nameJson.add("dateStop");

        adapter = new Kathetires_RV(extendedAct,  simple_items_lista);
        recyclerView.setAdapter(adapter);

        getValuesForSimpleItems();

    }


    private void neaEggrafiListener(ArrayList<Spinner_item> spinner_items_lista, String[] names){


        neaEggrafiBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(extendedAct)
                        .setSingleChoiceItems(names, 0, null)
                        .setTitle("Επιλογή καθετήρα")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                                int pos = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                                addToRV(spinner_items_lista.get(pos));
                            }
                        });

                AlertDialog d = builder.create();
                d.show();
            }
        });

    }



    private void addToRV(Spinner_item spinner_item){
        int itemID = spinner_item.getId();
        String name = spinner_item.getName();

        if (isItemExistsOnRV(itemID)){
            Toast.makeText(extendedAct, "Υπάρχει ήδη ο καθετήρας " + name + " στην λίστα ", Toast.LENGTH_LONG).show();
            return;
        }

        Simple_Items s = new Simple_Items(itemID , name);

        adapter.result.add(s);
        adapter.notifyDataSetChanged();

        Toast.makeText(extendedAct, "Ο καθετήρας " + name + " προστέθηκε.", Toast.LENGTH_LONG).show();


    }


    private void addToRV(Simple_Items s){

        if (isItemExistsOnRV(s.getItemID()))
            return;

        adapter.result.add(s);
        adapter.notifyDataSetChanged();

    }


    private boolean isItemExistsOnRV(int itemID){
        for (int i=0; i<adapter.result.size(); i++){
            if (itemID == adapter.result.get(i).getItemID()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void getSimpleItems() {
        //super.getSimpleItems();
        alertDialog.show();

        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.ctx = extendedAct;
        task.query = "select * from  Nursing_Kathethres_Meth ";
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {
                if (results != null){
                    spinner_items_lista = new ArrayList<>();
                    String [] names = new String[results.length()];

                    for (int i=0; i<results.length(); i++) {
                        JSONObject item = results.getJSONObject(i);
                        int id = item.getInt("ID");
                        String name = item.getString("Name").replace("\n","");
                        spinner_items_lista.add(new Spinner_item(id,name));
                        names[i] = name;


                    }




                    neaEggrafiListener(spinner_items_lista, names);

                }




                alertDialog.dismiss();
            }
        };
        task.execute();

    }


    @Override
    public void getValuesForSimpleItems() {
        alertDialog.show();
        clearLista();

        final AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.query =  Str_queries.getKathetiresValues_Meth(transgroupID ,dateTV.getText().toString()) ;
        task.ctx = extendedAct;
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {


                if (results != null && !results.getJSONObject(0).has("status")) {
                    clearLista();
                    ArrayList <Simple_Items> lista = adapter.result;

                    for (int i=0; i<results.length(); i++){

                        JSONObject jsonObject = results.getJSONObject(i);
                        int id = jsonObject.getInt("ID");
                        int itemID = jsonObject.getInt("itemID");
                        String dateIN = Utils.convertObjToString(jsonObject.get("dateinStr"));
                        String dateOut = Utils.convertObjToString(jsonObject.get("dateoutStr"));
                        String topos = Utils.convertObjToString(jsonObject.get("topos"));
                        String itemName = Utils.convertObjToString(jsonObject.get("itemName")).replace("\n","");
                        String userID = Utils.convertObjToString(jsonObject.get("UserID"));

                        Simple_Items item = new Simple_Items(itemID,itemName);
                        item.setId(id);
                        item.setDatein(dateIN);
                        item.setValue(topos);
                        item.setDateout(dateOut);
                        item.setUserID(userID);
                        getValuesForEachItem(item,jsonObject );
                        addToRV(item);



                    }

                }

                else
                    clearLista();

                adapter.notifyDataSetChanged();


                alertDialog.dismiss();

            }


        };

        task.execute();
    }




    private void getValuesForEachItem(Simple_Items s, JSONObject vals){
        try {
            int itemID = s.getItemID();


            if (itemID == 1) { //περιφερικη 1
                s.valSP1 = vals.getString("perif1_megethosID");
                s.valET1 = vals.getString("perif1_megethos_text");
                s.valSP2 = vals.getString("perif1_thesiID");
                s.valET2 = vals.getString("perif1_thesi_text");
            } else if (itemID == 2) { //περιφερικη 12
                s.valSP1 = vals.getString("perif2_megethosID");
                s.valET1 = vals.getString("perif2_megethos_text");
                s.valSP2 = vals.getString("perif2_thesiID");
                s.valET2 = vals.getString("perif2_thesi_text");
            } else if (itemID == 3) { // αρτ
                s.valSP1 = vals.getString("art_thesiID");
                s.valET1 = vals.getString("art_eidos_text");
                s.valSP2 = vals.getString("art_megethosID");
                s.valET2 = vals.getString("art_megethos_text");
            } else if (itemID == 4) {  // folley
                s.valSP1 = vals.getString("folley_eidosID");
                s.valET1 = vals.getString("folley_eidos_text");
                s.valSP2 = vals.getString("folley_megethosID");
                s.valET2 = vals.getString("folley_megethos_text");
            } else if (itemID == 5) {  // levin
                s.valSP1 = vals.getString("levin_eidosID");
                s.valET1 = vals.getString("levin_eidos_text");
                s.valSP2 = vals.getString("levin_megethosID");
                s.valET2 = vals.getString("levin_megethos_text");

            }  else if (itemID == 6) {  // Επισκληρίδιος καθετήρας

                s.valSP1 = vals.getString("episk_thesiID");
                s.valSP2 = vals.getString("episk_fereiID");

            }  else if (itemID == 8) {  // ΚΕΝΤΡΙΚΗ ΓΡΑΜΜΗ
                s.valSP1 = vals.getString("kentr_eidosID");
                s.valET1 = vals.getString("kentr_eidos_text");
                s.valSP2 = vals.getString("kentr_thesiID");
                s.valET2 = vals.getString("kentr_thesi_text");

            }  else if (itemID == 9) {  // ΚΑΘΕΤΗΡΑΣ ΑΙΜΟΚΑΘΑΡΣΗΣ
                s.valSP1 = vals.getString("kath_aim_eidosID");
                s.valET1 = vals.getString("kath_aim_eidos_text");
                s.valSP2 = vals.getString("kath_aim_thesiID");
                s.valET2 = vals.getString("kath_aim_thesi_text");

            } else if (itemID == 10) {  // ΕΝΔΟΤΡΑΧΕΙΑΚΟΣ ΣΩΛΗΝΑΣ
                s.valSP1 = vals.getString("end_sol_sizeID");
                s.valET1 = vals.getString("end_sol_size_text");
                s.valET2 = vals.getString("end_sol_piesi_text"); // αριθμος
                s.valET3 = vals.getString("end_sol_thesi_text"); // αριθμος

            }  else if (itemID == 11) {  // ΣΩΛΗΝΑΣ ΤΡΑΧΕΙΟΣΤΟΜΙΑΣ
                s.valSP1 = vals.getString("sol_trax_sizeID");
                s.valET1 = vals.getString("sol_trax_size_text");
                s.valET2 = vals.getString("sol_trax_piesi_text"); // αριθμος

            }  else if (itemID == 12) {  // Ρινοφαρυγγικός Αεραγωγός
                s.valSP1 = vals.getString("rin_aer_sizeID");
                s.valET1 = vals.getString("rin_aer_size_text");


            }  else if (itemID == 13) {  // Στοματοφαρυγγικός Αεραγωγός
                s.valSP1 = vals.getString("stoma_aer_sizeID");
                s.valET1 = vals.getString("stoma_aer_size_text");

            }  else if (itemID == 14) {  // θηκάρι
                s.valSP1 = vals.getString("thikari_eidosID");
                s.valET1 = vals.getString("thikari_eidos_text");
                s.valSP2 = vals.getString("thikari_thesiID");
                s.valET2 = vals.getString("thikari_thesi_text");

            }  else if (itemID == 15) {  // ΘΗΚΑΡΙ (+ Ενδοαορτική Αντλία(IABP)
                s.valSP1 = vals.getString("thikari_antl_thesiID");


            }  else if (itemID == 16) {  // ΚΑΘΕΤΗΡΑΣ ICP
                s.valET1 = vals.getString("icp_text");


            }


               else if (itemID >= 18 && itemID <= 27) {  //παροχευτεσεις 1-10
                s.valSP1 = vals.getString("parox_eidosID");
                s.valET1 = vals.getString("parox_eidos_text");
                s.valSP2 = vals.getString("parox_perigrafiID");
                s.valET2 = vals.getString("parox_perigrafi_text");
                s.valET3 = vals.getString("parox_thesi_text");
            }

        }
        catch (Exception e){

        }

    }











    @Override
    public void updateButtonListener(){
        if (updateIMB != null) {

            updateIMB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alertDialog.show();
                    ArrayList<Simple_Items> lista = adapter.result;
                    String curUser = Utils.getUserID(extendedAct);

                    for (int i = 0; i < lista.size(); i++) {

                        if (i == lista.size() - 1)
                            isTeleutaio = true;

                        int id = lista.get(i).getId();
                        int itemID = simple_items_lista.get(i).getItemID();
                        String dateIN = simple_items_lista.get(i).getDatein();
                        String dateout = simple_items_lista.get(i).getDateout();
                        String userID = simple_items_lista.get(i).getUserID();
                        if (userID.equals(""))
                            userID = curUser;

                        if (curUser.equals(userID))
                            // ΜΟΝΟ ΟΤΑΝ ΕΧΟΥΜΕ ΚΕΙΜΕΝΟ ΠΕΡΙΓΡΑΦΗ ΝΑ ΚΑΝΕΙ ΕΓΓΡΑΦΗ
                        //    if (value != null && !value.trim().equals("")) {

                                valuesJson = new ArrayList<>();

                                valuesJson.add(String.valueOf(itemID));
                                valuesJson.add(dateIN.trim().equals("") ? "" : Utils.convertDateTomilliseconds(dateIN));
                                valuesJson.add(dateout.trim().equals("") ? ""  : Utils.convertDateTomilliseconds(dateout));

                                manageValuesForUpdate(lista.get(i));
                                AsyncTaskUpdate_JSON task;

                                if (id != 0)
                                    task = new AsyncTaskUpdate_JSON(extendedAct, String.valueOf(id) , transgroupID,table, nameJson,replaceTrueOrFalse(valuesJson), titloi_positions);
                                else
                                    task = new AsyncTaskUpdate_JSON(extendedAct,  transgroupID,table, nameJson, replaceTrueOrFalse(valuesJson),titloi_positions);


                                task.names_col = new String[]{"ID","TransgroupID"};
                                task.date = dateTV.getText().toString();
                                task.period = period;
                                task.watchID = watchID;
                                task.listener = (AsyncGetUpdate_JSON) (activityFromSigxoneusi != null ? activityFromSigxoneusi : extendedAct);
                                task.execute();



                         //   }


                    }


                }


            });

        }


    }

    private void manageValuesForUpdate(Simple_Items s) {
        int itemID = s.getItemID();
        nameJson.subList(3, nameJson.size()).clear();
        if ((itemID >=1 && itemID <= 5) || itemID == 11 || itemID == 14){
            valuesJson.add(s.valSP1);valuesJson.add(s.valET1);
            valuesJson.add(s.valSP2);valuesJson.add(s.valET2);

            if (itemID == 1 ) { //περιφερικη 1
                nameJson.add("perif1_megethosID");nameJson.add("perif1_megethos_text");
                nameJson.add("perif1_thesiID");nameJson.add("perif1_thesi_text");
            }
            else if (itemID == 2 ) { //περιφερικη 12
                nameJson.add("perif2_megethosID");nameJson.add("perif2_megethos_text");
                nameJson.add("perif2_thesiID");nameJson.add("perif2_thesi_text");
            }

            else if (itemID == 3){ // αρτ
                nameJson.add("art_thesiID");nameJson.add("art_eidos_text");
                nameJson.add("art_megethosID");nameJson.add("art_megethos_text");
            }

            else if (itemID == 4){  // levin
                nameJson.add("folley_eidosID");nameJson.add("folley_eidos_text");
                nameJson.add("folley_megethosID");nameJson.add("folley_megethos_text");
            }

            else if (itemID == 5){  // folley
                nameJson.add("levin_eidosID");nameJson.add("levin_eidos_text");
                nameJson.add("levin_megethosID");nameJson.add("levin_megethos_text");
            }
        }



        else if (itemID >= 18 &&  itemID <= 27){  //παροχευτεσεις 1-10
            valuesJson.add(s.valSP1);valuesJson.add(s.valET1);
            valuesJson.add(s.valSP2);valuesJson.add(s.valET2);
            valuesJson.add(s.valET3);

            nameJson.add("parox_eidosID");nameJson.add("parox_eidos_text");
            nameJson.add("parox_perigrafiID");nameJson.add("parox_perigrafi_text");
            nameJson.add("parox_thesi_text");
        }

    }


    @Override
    public void clearLista() {
        adapter.result.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateLabel(TextView datetv) {
        super.updateLabel(datetv);

        if (simple_items_lista != null && !simple_items_lista.isEmpty())
            getValuesForSimpleItems();
    }

    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        if (simple_items_lista != null )
            getValuesForSimpleItems();
    }




    @Override
    public void handleDialogClose(String transgroupid) {
        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT
        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);

        if (simple_items_lista != null )
            getValuesForSimpleItems();
    }


}
