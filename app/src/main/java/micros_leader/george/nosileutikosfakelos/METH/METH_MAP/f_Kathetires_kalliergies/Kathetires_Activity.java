package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.ag.floatingactionmenu.OptionsFabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.KalliergiesDialog.KalliergiesDialog;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.ThetikesKalliergies.ThetikesKalliergiesDialog;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Simple_Items;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Kathetires_Activity extends BasicActivity implements  AsyncGetUpdateResult{


    public OptionsFabLayout fabMenu;
    private TextView nurseTV;
    public ArrayList<Simple_Items> simple_items_lista;
    public ArrayList<Spinner_item> spinner_items_lista;
    private Kathetires_RV adapter;
    public boolean isTeleutaio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kathetires_meth);

        fabMenu = findViewById(R.id.fabMenu);
        recyclerView = findViewById(R.id.kathetiresRV);

        initialize();

    }


    public void initialize(){

        if (extendedAct == null)
            extendedAct = this;

        managefabMenuIcon();

        alertDialog = Utils.setLoadingAlertDialog(extendedAct);

        recyclerView.setLayoutManager(new LinearLayoutManager(extendedAct, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(extendedAct, DividerItemDecoration.VERTICAL));
        recyclerView.setItemViewCacheSize(300);
        recyclerView.setHasFixedSize(true);
        getPatientsList(extendedAct,R.id.patientsTV, R.id.floorsSP);

        thereIsDatePicker(R.id.dateTV);
        thereIsImageUpdateButton();
        //thereIsImagePrinterButton(ReportIDs.KATHETIRES_METH , PrintReport.ReportParams.TRANSGROUP_ID_ONLY);

        updateButtonListener();

        date = dateTV.getText().toString();

        table = "Nursing_Kathethres_Topos";
        nameJson.add("itemID");nameJson.add("topos");
        nameJson.add("dateStart");nameJson.add("dateStop");

        getSimpleItems();
    }




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



                        long id = lista.get(i).getId();
                        int itemID = simple_items_lista.get(i).getItemID();
                        String dateIN = simple_items_lista.get(i).getDatein();
                        String dateout = simple_items_lista.get(i).getDateout();
                        String value = simple_items_lista.get(i).getValue();
                        String userID = simple_items_lista.get(i).getUserID();
                        if (userID.equals(""))
                            userID = curUser;

                        if (curUser.equals(userID))
                        // ΜΟΝΟ ΟΤΑΝ ΕΧΟΥΜΕ ΚΕΙΜΕΝΟ ΠΕΡΙΓΡΑΦΗ ΝΑ ΚΑΝΕΙ ΕΓΓΡΑΦΗ
                        if (value != null && !value.trim().equals("")) {

                            valuesJson = new ArrayList<>();

                            valuesJson.add(String.valueOf(itemID));
                            valuesJson.add(value);
                            valuesJson.add(dateIN.trim().equals("") ? "" : Utils.convertDateTomilliseconds(dateIN));
                            valuesJson.add(dateout.trim().equals("") ? ""  : Utils.convertDateTomilliseconds(dateout));


                            AsyncTaskUpdate_JSON task;

                            if (id != 0)
                                task = new AsyncTaskUpdate_JSON(extendedAct, String.valueOf(id) , transgroupID,table, nameJson,replaceTrueOrFalse(valuesJson), titloi_positions);
                            else
                                task = new AsyncTaskUpdate_JSON(extendedAct,  transgroupID,table, nameJson,replaceTrueOrFalse(valuesJson),titloi_positions);


                            task.names_col = new String[]{"ID","TransgroupID"};
                            task.date = dateTV.getText().toString();
                            task.period = period;
                            task.watchID = watchID;
                            task.listener = (AsyncGetUpdate_JSON) (activityFromSigxoneusi != null ? activityFromSigxoneusi : extendedAct);
                            task.execute();



                        }


                    }


                }


            });

        }


    }


    @Override
    public void updateSuccess(String str) {
        super.updateSuccess(str);

        if (isTeleutaio) {
            getValuesForSimpleItems();
            isTeleutaio = false;
        }
    }

    public void getSimpleItems() {

        alertDialog.show();
        
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.ctx = extendedAct;
        task.query = "select * from  Nursing_Kathethres_Meth order by orderID";
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {
                if (results != null){
                    simple_items_lista = new ArrayList<>();
                    
                    for (int i=0; i<results.length(); i++) {
                        JSONObject item = results.getJSONObject(i);
                        int id = item.getInt("ID");
                        String name = item.getString("Name");
                        simple_items_lista.add(manageItems(id,name));
                        if (id == 3)  //TEST
                            break;

                    }
                    
                    adapter = new Kathetires_RV(extendedAct,  simple_items_lista ,true);
                    recyclerView.setAdapter(adapter);
                    
                    getValuesForSimpleItems();
                }
                
                
                
                
                alertDialog.dismiss();
            }
        };
        task.execute();

    }




    private Simple_Items manageItems(int id , String name){

        Simple_Items x = new Simple_Items(id,name);

        if (id == 1){ // ΠΕΡΙΦΕΡΙΚΗ 1
            x.col1 = "perif1_megethosID";
            x.col2 = "perif1_megethos_text";
            x.col3 = "perif1_thesiID";
            x.col4 = "perif1_thesi_text";

        }
        else if (id == 2){ // ΠΕΡΙΦΕΡΙΚΗ 2
            x.col1 = "perif2_megethosID";
            x.col2 = "perif2_megethos_text";
            x.col3 = "perif2_thesiID";
            x.col4 = "perif2_thesi_text";
        }
        else if (id == 3){ // ΑΡΤΗΡΙΑΚΗ
            x.col1 = "art_eidosID";
            x.col2 = "art_eidos_text";
            x.col3 = "art_megethosID";
            x.col4 = "art_megethos_text";
        }

        return x;
    }







    public void getValuesForSimpleItems() {

        alertDialog.show();
        clearLista();

        final AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.query =  Str_queries.getKathetiresValues_Meth(transgroupID, true) ;
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
                        String userID = Utils.convertObjToString(jsonObject.get("UserID"));



                        for(Simple_Items item : lista){
                            if(item.getItemID() == itemID){
                                item.setId(id);
                                item.setDatein(dateIN);
                                item.setDateout(dateOut);
                                item.setValue(topos);
                                item.setUserID(userID);
                            }
                        }

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






    public void clearLista(){
        for (int x = 0; x < simple_items_lista.size(); x++) {
            if (simple_items_lista.get(x).getValue() != null)
                simple_items_lista.get(x).setValue("");

            simple_items_lista.get(x).setId(0);
            simple_items_lista.get(x).setDatein("");
            simple_items_lista.get(x).setDateout("");

        }

    }









    private void managefabMenuIcon() {

    //Set main fab clicklistener.
        fabMenu.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabMenu.isOptionsMenuOpened())
                    fabMenu.closeOptionsMenu();            }
        });


        fabMenu.setMiniFabsColors(
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary);



        //  LISTENER  GIA TA ITEMS TOY
        fabMenu.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {

                int itemId = fabItem.getItemId();

                if (itemId == R.id.kalliergies) {
                    KalliergiesDialog df = new KalliergiesDialog();
                    Bundle putextra = new Bundle();
                    putextra.putString("transgroupID", transgroupID);
                    putextra.putString("date", dateTV.getText().toString());
                    df.setArguments(putextra);
                    df.show(getSupportFragmentManager(), "Dialog");
                    fabMenu.closeOptionsMenu();

                    // true to keep the Speed Dial open
                } else if (itemId == R.id.thetikesKalliergies) {
                    ThetikesKalliergiesDialog df1 = new ThetikesKalliergiesDialog();
                    Bundle putextra1 = new Bundle();
                    putextra1.putString("transgroupID", transgroupID);
                    putextra1.putString("date", dateTV.getText().toString());
                    df1.setArguments(putextra1);
                    df1.show(getSupportFragmentManager(), "Dialog");
                    fabMenu.closeOptionsMenu();

                    // true to keep the Speed Dial open
                } else if (itemId == R.id.eidikiFrontida) {
                    getEidikiFrontida();


                    // true to keep the Speed Dial open
                }
            }
        });
    }




    private void getEidikiFrontida() {

        getJSON_DATA("select * from Nursing_Eidiki_Frontida_items_Meth");
    }


    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null && !results.getJSONObject(0).has("status")) {

            String []  plagioiTitloi =new String[results.length()];

            ArrayList<TableViewItem> tableLista = InfoSpecificLists.getEidikiFrontidaMeth();


            for (int i=0; i<results.length(); i++){

                JSONObject jsonObject = results.getJSONObject(i);
                int id = jsonObject.getInt("ID");
                String name = jsonObject.getString("Name");

                plagioiTitloi[i] = id + "," + name;



            }

            alertDialog.dismiss();

            String []  cols = new String[]{"ID","UserID" ,"ItemID","Date" ,"perigrafi" ,"datein","perigrafi" ,"datein"};

            String []  panoTitloi = new String[]{"ID","UserID","ΗΜ/ΝΙΑ" ,"Περιγραφή" ,"Ημ/νια Έναρξης" ,"Λήξη"};
            Intent in = tableView_sigkentrotika("select * from  Nursing_Eidiki_Frontida_metriseis_Meth " +
                            " where transgroupID = " + transgroupID +
                           " and dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + dateTV.getText().toString() + "' , 103)) ",
                    transgroupID ,
                    panoTitloi,plagioiTitloi,tableLista,false, false, true);
            in.putExtra("date",dateTV.getText().toString());
            extendedAct.startActivity(in);
        }

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

        if (simple_items_lista != null && !simple_items_lista.isEmpty())
              getValuesForSimpleItems();
    }




    @Override
    public void handleDialogClose(String transgroupid) {
        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT
        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);

        if (simple_items_lista != null && !simple_items_lista.isEmpty())
            getValuesForSimpleItems();
    }


}
