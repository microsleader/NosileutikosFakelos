package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Nosil_Elegxos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Nosil_logodosia.Logodosia_Meth_Activity;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Spinner_items_lists;
import micros_leader.george.nosileutikosfakelos.Spinner_new_Adapter;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityNosilIstorikoMethBinding;

public class Nosil_elegxos_Meth extends BasicActivity  implements DataSended{


    private NosilRV_adapter adapter;
    private Button logodosiaBT;
    private Boolean firstTime = true;
    private ArrayList<ItemsRV> listaAdaptor;
    private String vardiaText;
    private ActivityNosilIstorikoMethBinding bd;
    private boolean firstTimeResume = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityNosilIstorikoMethBinding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);
        extendedAct = this;

        logodosiaBTListener();
        setSupportActionBar(toolbar);
        thereIsVardiesSpinner(R.id.vardiaSP, Spinner_items_lists.getVardiesLista());

       // titloi_positions = new int[]{0,11,27,29,35,41,57,69,83,93,108}; //χωρις ΝΟΣΗΛΕΥΤΙΚΟΣ ΕΛΕΓΧΟΣ
        titloi_positions = new int[]{0,4,8,19,35,37,43,49,67,79,93,103,118}; //με ΝΟΣΗΛΕΥΤΙΚΟΣ ΕΛΕΓΧΟΣ

        adapter = new NosilRV_adapter(this, InfoSpecificLists.getNosil_Elegxos_Meth(),titloi_positions);
        adapter.editextsUsingDialogs = true;
        listaAdaptor = adapter.result;

        table = "v_Nursing_meth_nosil_elegxos";


        getAll_col_names(InfoSpecificLists.getNosil_Elegxos_Meth());

        setRecyclerViewgridrLayaout( R.id.nosilIstorikoMethRV,  adapter, 2,titloi_positions );

        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);

        thereIsDatePicker(bd.dateTV);
        thereIsImageUpdateButton();

        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID","Date"});

        date = bd.dateTV.getText().toString();

        vardiaSPListener();

    }



    private void vardiaSPListener() {
        vardiaSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner_new_Adapter adapter = (Spinner_new_Adapter) vardiaSP.getAdapter();

                // Here you get the current item (a Spinner_item object) that is selected by its position
                Spinner_item spinner_item = adapter.getItem(position);
                vardiaID = String.valueOf(spinner_item.getId());
                vardiaText = spinner_item.getName();
                if (firstTime)
                    firstTime = false;
                else
                    getMethMap();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void logodosiaBTListener() {

        bd.logodosiaBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Nosil_elegxos_Meth.this, Logodosia_Meth_Activity.class);
                in.putExtra("transgroupID", transgroupID);
                in.putExtra("date", bd.dateTV.getText().toString());
                in.putExtra("vardiaID", vardiaID);
                in.putExtra("vardiaText", vardiaText);
                startActivity(in);

            }
        });

    }


    private void getMethMap(){
        getJSON_DATA(Str_queries.getMethNosileutikosElegxos(transgroupID,date,vardiaID) );

    }

    @Override
    public void updateLabel(TextView datetv) {
        super.updateLabel(datetv);
        getMethMap();
    }

    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);
        newList = InfoSpecificLists.getNosil_Elegxos_Meth();

        if (weHaveData){

            JSONObject jsonObject =  results.getJSONObject(0);
            vardiaID = Utils.convertObjToString(jsonObject.get("vardiaID"));


            setValuesTolistaAdaptor(titloi_positions,newList);     // ΤΟΠΟΘΕΤΗΣΗ ΤΙΜΩΝ ΣΤΗ ΛΙΣΤΑ ΑΝΤΑΠΤΟΡΑ
        }

        adapter.updateLista(newList);

    }

    @Override
    public void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor) {
        super.setValuesTo_valuesJSON(titles_positions, listAdaptor);

        nameJson.add("vardiaID");
        valuesJson.add(vardiaID);
        read_only_col = new String[]{"glaskovi_score"};

    }

    @Override
    public void updateSuccess(String str) {
        super.updateSuccess(str);
    }

    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);
        transgroupID = Utils.getSplitSPartString(patientsTV.getText().toString(),",",3);

        getMethMap();
    }





    @Override
    public void handleDialogClose(String transgroupid) {

        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT
        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);
       getMethMap();
    }



    class NosilRV_adapter extends BasicRV {

        public NosilRV_adapter(Activity act, ArrayList<ItemsRV> result, int[] titloi_positions) {
            super(act, result, titloi_positions);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder,  int position) {
            super.onBindViewHolder(holder, position);

            String title = result.get(position).getTitleID();
            String col = result.get(position).getCol_name();

            if (title.contains("RAMSAY")){
                holder.titleTV.setTypeface(null, Typeface.BOLD);
                holder.titleTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.showImageDialog(Nosil_elegxos_Meth.this, R.drawable.klimaka_ramsay );

                    }
                });

            }

            else if(col != null && col.equals("glaskovi_score")){
                holder.titleTV.setTypeface(null, Typeface.BOLD);
                holder.titleTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String query = "select ID, Date, UserID,vardiaID, mati,omilia,kinisi ,dbo.datetostr(date) as dateStr" +
                                " from Nursing_meth_nosil_elegxos " +
                                " where transgroupID = " + transgroupID +
                                " and  dbo.datetostr(date) = dbo.datetostr(dbo.timeToNum(CONVERT(datetime, '" +  date  + "' , 103)))";
                       Intent in  = tableView_sigkentrotika(query, transgroupID,
                                new String[]{"ID","UserID","ΗΜ/ΝΙΑ","ΒΑΡΔΙΑ","ΟΡΑΣΗ","ΟΜΙΛΙΑ","ΚΙΝΗΣΗ"},
                                null,
                                InfoSpecificLists.geτKlimakaGlaskovis(),
                                false,
                                false,
                                true);

                       startActivity(in);

//                        if (id == null || id.equals(""))
//                            Toast.makeText(act, "Κάντε πρώτα μία καταχώρηση για τη συγκεκριμένη βάρδια", Toast.LENGTH_SHORT).show();
//
//                        else {
//                            DialogKlimakaGlaskovis df = new DialogKlimakaGlaskovis();
//                            Bundle putextra = new Bundle();
//                            putextra.putInt("id", Integer.parseInt(id));
//                            putextra.putString("transgroupID", transgroupID);
//                            putextra.putString("name", Utils.getSplitSPartString(patientsTV.getText().toString(), ",", 2));
//                            putextra.putString("vardiaID", vardiaID);
//                            putextra.putBoolean("weHaveData", weHaveData);
//                            putextra.putString("date", dateTV.getText().toString());
//                            putextra.putInt("mati", mati);
//                            putextra.putInt("omilia", omilia);
//                            putextra.putInt("kinisi", kinisi);
//
//
//                            df.setArguments(putextra);
//                            df.show(getSupportFragmentManager(), "Dialog");
//                        }
                    }
                });
            }

            else{
                if (holder.titleTV != null) {
                    holder.titleTV.setTypeface(null, Typeface.NORMAL);
                    holder.titleTV.setOnClickListener(null);
                }

            }


        }
    }




    @Override
    protected void onResume() {
        super.onResume();
        if (firstTimeResume)
            firstTimeResume = false;
        else
            getMethMap();
    }


}












