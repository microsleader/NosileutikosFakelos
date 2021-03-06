package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Isozigio_Meth;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ag.floatingactionmenu.OptionsFabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentMedicineCloseListener;

import micros_leader.george.nosileutikosfakelos.OROFOI.f_Zotika_simeia.RV_zotika_ana_ora_adapter;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.StaticFields;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;
import micros_leader.george.nosileutikosfakelos.Utils;


public class Isozigio_Meth_Activity extends BasicActivity implements MyDialogFragmentMedicineCloseListener {


    private TextView total_pros,total_apov,total_isozigio ;
    private BasicRV adapterRV;
    private Button neaEggrafiBT;
    private OptionsFabLayout fabMenu;
    private boolean firstTimeResume = true;

    TextView idView;
    String med1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isozigio__meth_ );

        thereIsDatePicker(R.id.dateTV);
        thereIsTimePicker(R.id.timeTV);

        total_pros = findViewById(R.id.total_pros);
        total_apov = findViewById(R.id.total_apov);
        total_isozigio = findViewById(R.id.total_isozigio);

        neaEggrafiBT = findViewById(R.id.neaEggrafiBT);
        neaEgrafiListener();

        titloi_positions   = new int[]{0, 8};

        fabMenu = findViewById(R.id.fabMenu);
        fabListener();

        table = "v_Nursing_Isozigio_Meth";
        setRead_only_col("total_medicines","all_hours_meds","systemic_meds","one_time_meds","other_meds","metaggiseis");


        getAll_col_names(InfoSpecificLists.getIsozigio_Meth_Lista());
        adapterRV = new MethAdapter(this, InfoSpecificLists.getIsozigio_Meth_Lista(),titloi_positions);

        setRecyclerViewgridrLayaout( R.id.isozigioRV,adapterRV,2,titloi_positions);
        listaAdaptor = adapterRV.result;

        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);

        thereIsImageUpdateButton();
        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID","Date"});

        recyclerviewAddTouchListener();
    }




    @Override
    public void thereIsImageUpdateButton() {
        super.thereIsImageUpdateButton();

        Button previousIsozigioBT =new Button(this);
        Toolbar.LayoutParams l1=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l1.gravity= Gravity.START;
        previousIsozigioBT.setLayoutParams(l1);
        previousIsozigioBT.setPadding(20,20,20,20);
        previousIsozigioBT.setText("???????????????? ??????????. ??????????");
        toolbar.addView(previousIsozigioBT);
        Toolbar.LayoutParams l3=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l3.gravity= Gravity.END;
        previousIsozigioBT.setLayoutParams(l3);
        previousIsozigioBT.setBackgroundColor(Color.TRANSPARENT);


        previousIsozigioBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.show();

                BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(Isozigio_Meth_Activity.this);
                View parentView = getLayoutInflater().inflate(R.layout.custom_isizigio_meth_proigoumenis_meras_dialog,null);
                bottomSheerDialog.setContentView(parentView);

                final TextView prosTV = parentView.findViewById(R.id.prosTV);
                final TextView apovTV = parentView.findViewById(R.id.apovTV);
                final TextView isozigioTV = parentView.findViewById(R.id.isozigioTV);

                AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
                task.query = "select " +
                        " isnull(sum(total_pros),0)  as total_pros, " +
                        " isnull(sum(total_apov),0) as total_apov,  " +
                        " isnull(sum(total_isozigio),0) as total_isozigio " +
                        " from v_Nursing_Isozigio_Meth " +
                        "  where TransGroupid= " + transgroupID +
                        "  and  dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + Utils.getYesterdayDateString() +"' , 103))";
                task.ctx = Isozigio_Meth_Activity.this;
                task.listener = new AsyncCompleteTask2() {
                    @Override
                    public void taskComplete2(JSONArray results) throws JSONException {

                        if (results != null && !results.getJSONObject(0).has("status")){
                            JSONObject n = results.getJSONObject(0);
                            prosTV.setText(Utils.convertObjToString(n.get("total_pros")));
                            apovTV.setText(Utils.convertObjToString(n.get("total_apov")));
                            isozigioTV.setText(Utils.convertObjToString(n.get("total_isozigio")));

                        }

                        else
                            Toast.makeText(Isozigio_Meth_Activity.this, R.string.no_data, Toast.LENGTH_SHORT).show();


                        alertDialog.dismiss();
                    }
                };

                task.execute();


                bottomSheerDialog.show();
            }
        });
    }


    private void getTotalIsozigioTisImeras(){
        getJSON_DATA(Str_queries.getTotalIsozigioMeth(transgroupID, dateTV.getText().toString()));
    }



    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null && !results.getJSONObject(0).has("status")){

            JSONObject metriseis = results.getJSONObject(0);
            total_pros.setText(Utils.convertObjToString(metriseis.get("total_pros")));
            total_apov.setText(Utils.convertObjToString(metriseis.get("total_apov")));
            total_isozigio.setText(Utils.convertObjToString(metriseis.get("total_isozigio")));

           //ArrayList cloneList = (ArrayList ArrayList<ItemsRV>) adapterRV.result.clone();
            for (int i=0; i<adapterRV.result.size(); i++){
                if (adapterRV.result.get(i).getCol_name() !=null && adapterRV.result.get(i).getCol_name().equals("total_medicines")) {
                    adapterRV.result.get(i).setValue(String.valueOf(metriseis.getInt("total_medicines")));
                    adapterRV.notifyItemChanged(i);
                    break;
                }

            }



        }
        else{
            total_pros.setText("");
            total_apov.setText("");
            total_isozigio.setText("");
        }

    //    adapterRV.updateLista(adapterRV.result);
        alertDialog.dismiss();
    }




    private void fabListener() {

        fabMenu.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabMenu.isOptionsMenuOpened())
                    fabMenu.closeOptionsMenu();
            }
        });


        fabMenu.setMiniFabsColors(
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary);



        //  LISTENER  GIA TA ITEMS TOY
        fabMenu.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {

                String query = "";
                Intent in = null;

                switch (fabItem.getItemId()) {
                    case R.id.sigkentrotika_iatrikes_odigies:

                         query = Str_queries.getIatrikes_Odigies_Medicines_Meth(transgroupID,dateTV.getText().toString());

                         in = tableView_sigkentrotika(query,
                                transgroupID,
                                new String[]{"ID","UserID","????/??????","??????????????",
                                        "Ml/h","????????","??????????????????","?????? ??????????????????","????????????????????",
                                },
                                null,
                                InfoSpecificLists.getIatrikesOdigies_Medicines_Meth(),
                                false,
                                false,
                                true);
                         in.putExtra("saveTransgroupID" ,false);
                        startActivity(in);


                        break; // true to keep the Speed Dial open


                    case R.id.sigkentrotika_poreia_alles_eksetaseis:


                        query = Str_queries.getPoreia_alles_eksetaseis_Meth(transgroupID,dateTV.getText().toString());

                        in = tableView_sigkentrotika(query,
                                transgroupID,
                                new String[]{"????/??????","????????????","???????????? ??????????","?????????? ??????????????????",

                                },
                                null,
                                new String[]{"dateStr","doctor","poreia","alles",
                                },
                                false,
                                false,
                                false);
                        startActivity(in);

                        break;



                    case R.id.sigkentrotika_ergastiriaka:

                        getErgastiriaka();

                        break;


                    case R.id.sigkentrotika_isozigio:


                         query = Str_queries.getTotalIsozigio_Sigkentrotika_Meth(transgroupID,dateTV.getText().toString());

                         in = tableView_sigkentrotika(query,
                                transgroupID,
                                null,
                                InfoSpecificLists.getSigkentrotikaIsozigioMeth(transgroupID),
                                false,
                                false,
                                true);

                        startActivity(in);


                        break;

                    default:

                        break; // true to keep the Speed Dial open


                }
            }
        });





    }




    private void getErgastiriaka() {

        //alertDialog.show();
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.query = "select * from Nursing_Ergastiriaka_Meth " ;
        task.ctx = Isozigio_Meth_Activity.this;
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {


                if (results != null && !results.getJSONObject(0).has("status")) {

                    String []  plagioiTitloi =new String[results.length()];

                    ArrayList<TableViewItem> tableLista = InfoSpecificLists.getErgastiriakaMeth();


                    for (int i=0; i<results.length(); i++){

                        JSONObject jsonObject = results.getJSONObject(i);
                        int id = jsonObject.getInt("ID");
                        String name = jsonObject.getString("Name");

                        plagioiTitloi[i] = id + "," + name;



                    }


                    String []  panoTitloi = new String[]{"ID","UserID","????/??????" ,"????????????????","??????" ,"?????? ??????????" };
                    Intent in = tableView_sigkentrotika("select * from  Nursing_Ergasthriaka_Datetime_Meth " +
                                    " where transgroupID = " + transgroupID +
                                    " and dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + dateTV.getText().toString() + "' , 103)) ",
                            transgroupID ,
                            panoTitloi,plagioiTitloi,tableLista,false, false, true);
                    in.putExtra("date",dateTV.getText().toString());
                    in.putExtra("plagioiTitlesAreItemIDs",true);
                    startActivity(in);
                }

                alertDialog.dismiss();
            }
        };

        task.execute();
    }



    @Override
    public void update_JSON(String str) {
        super.update_JSON(str);

        if (str.equals(getString(R.string.successful_update))) {
            id = eggrafiID;
            weHaveData = true;
        }
        else {
            id = "";
            weHaveData = false;
        }
    }


    private void neaEgrafiListener(){

        neaEggrafiBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weHaveData = false;
                id = "";
                clearListaAdaptor(listaAdaptor);
                adapterRV.notifyDataSetChanged();

                Toast.makeText(extendedActivity, "???????????????? ???? ???????????? ?????? ?????? ??????????????", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        getTotalIsozigioTisImeras();
    }

    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);

        getTotalIsozigioTisImeras();
    }


    @Override
    public void updateLabel(TextView datetv) {
        super.updateLabel(datetv);

        getTotalIsozigioTisImeras();


    }

    @Override
    public void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor) {
        super.setValuesTo_valuesJSON(titles_positions, listAdaptor);

        date = dateTV.getText().toString() + " " + timeTV.getText().toString();

    }





// ???????? ?????? ?????????????? ?????? ???????????????? ???????????? ?????? ?????? TEXTVIEWS ?????? RECYCLERVIEW ???? ?????????????? ?????? ???????? ?????????????? ?????????????? ?????? ??????????????
    //  ???? ?????????? ?????? ?????????? ???????? VIEW ?????????? ???? ???? ?????????? ?????? ???????????????? ???????? ??????????
    // ???? ?????? ?????????????? ???????? ???? ?????????? ???? ???????????? ?????????? ???? ?????????????????????? ??????????????????


    //--------------
    private void recyclerviewAddTouchListener() {
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());

                if(child!=null)
                    idView = (TextView) child.findViewById(R.id.valueTV);

                return false;

            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });
    }



    @Override
    public void dialogMedicineClose(String id_name) {
        if ((int) idView.getTag() == 1){
             med1 = id_name;
              listaAdaptor.get(1).setValue(Utils.getfirstPartSplitCommaString(id_name));

        }
        else if((int) idView.getTag() == 7){

            if ( recyclerView.findViewHolderForAdapterPosition(1) !=null)
                ((TextView) recyclerView.findViewHolderForAdapterPosition(1).itemView.findViewById(R.id.valueTV)).setText(med1);


            idView.setText(id_name);

            listaAdaptor.get(7).setValue(Utils.getfirstPartSplitCommaString(id_name));

        }

//--------------------------------------------------------



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_iatriko, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        //Closes menu if its opened.
        if (fabMenu.isOptionsMenuOpened())
            fabMenu.closeOptionsMenu();
        else
            super.onBackPressed();

    }



    class MethAdapter extends BasicRV {

        public MethAdapter(Activity act, ArrayList<ItemsRV> result, int[] titloi_positions) {
            super(act, result, titloi_positions);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);

            String col = result.get(position).getCol_name();
            if (col != null)
                switch (col) {
                    case "all_hours_meds":
                        showSigkentrotikaTypeMeds(holder.titleTV, StaticFields.Type_meds.MEDS_24_HOURS);
                        break;

                    case "systemic_meds":
                        showSigkentrotikaTypeMeds(holder.titleTV, StaticFields.Type_meds.STABLE_CONTINIOUSLY_MEDS);
                        break;

                    case "one_time_meds":
                        showSigkentrotikaTypeMeds(holder.titleTV, StaticFields.Type_meds.ONE_TIME_MEDS);
                        break;

                    case "other_meds":
                        showSigkentrotikaTypeMeds(holder.titleTV, StaticFields.Type_meds.OTHER_MEDS);
                        break;

                    case "metaggiseis":
                        holder.titleTV.setTypeface(null, Typeface.BOLD);
                        holder.titleTV.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String query = Str_queries.getSigkentrotika_metagiseis_meth(transgroupID);
//                                        +
//                                        " and datestop is null " +
//                                        " and  dbo.datetostr(datestart) = dbo.datetostr(dbo.timeToNum(CONVERT(datetime, '" + dateTV.getText().toString() + "' , 103)))";
                                Intent in = tableView_sigkentrotika(query, transgroupID,
                                        null,
                                        InfoSpecificLists.getMetaggiseis(),
                                        false,
                                        false,
                                        true);

                                startActivity(in);

                            }
                        });
                        break;


                }
        }




       public void showSigkentrotikaTypeMeds(  TextView tv , String type_medID){
           tv.setTypeface(null, Typeface.BOLD);
           tv.setOnClickListener(v -> {
               String query = Str_queries.getSigkentrotika_meds_meth_types(transgroupID, type_medID);
               Intent in = tableView_sigkentrotika(query, transgroupID,
                       null,
                       InfoSpecificLists.getFarmaka_isozigeio_meth(type_medID),
                       false,
                       false,
                       true);


               startActivity(in);

           });
        }

    }




    @Override
    protected void onResume() {
        super.onResume();
        if (firstTimeResume)
            firstTimeResume = false;
        else
            getTotalIsozigioTisImeras();
    }





}
