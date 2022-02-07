package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Isozigio_Meth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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

import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.StaticFields;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;
import micros_leader.george.nosileutikosfakelos.Utils;


public class Isozigio_Meth_Activity extends BasicActivity
       // implements MyDialogFragmentMedicineCloseListener
{


    public TextView total_pros,total_apov,total_isozigio ;
    private BasicRV adapterRV;
    public Button neaEggrafiBT;
    public OptionsFabLayout fabMenu;
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

        fabMenu = findViewById(R.id.fabMenu);

        initialize();
    }



    public void initialize(){

        if (extendedAct == null)
            extendedAct = this;

        neaEgrafiListener();

        titloi_positions   = new int[]{0, 8};
        fabListener();
        alertDialog = Utils.setLoadingAlertDialog(extendedAct);


        table = "v_Nursing_Isozigio_Meth";
        setRead_only_col("total_medicines","all_hours_meds","systemic_meds","one_time_meds","other_meds","metaggiseis");


        getAll_col_names(InfoSpecificLists.getIsozigio_Meth_Lista());
        adapterRV = new MethAdapter(extendedAct, InfoSpecificLists.getIsozigio_Meth_Lista(),titloi_positions);

        setRecyclerViewgridrLayaout( R.id.isozigioRV,adapterRV,2,titloi_positions);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                boolean isHeader = isHeader(position,titloi_positions);
                String colName = listaAdaptor.get(position).getCol_name();

                if (isHeader
                      //  || (colName != null && colName.equals("levin"))
                )
                    return  2;
                else
                    return  1;



            }
        });


        listaAdaptor = adapterRV.result;

        getPatientsList(extendedAct,R.id.patientsTV, R.id.floorsSP);

        thereIsImageUpdateButton();
        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID","Date"});

        recyclerviewAddTouchListener();

        //getTotalIsozigioTisImeras();
    }



    @Override
    public void thereIsImageUpdateButton() {
        super.thereIsImageUpdateButton();

        Button previousIsozigioBT = new Button(extendedAct);
        Toolbar.LayoutParams l1 = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l1.gravity= Gravity.START;
        previousIsozigioBT.setLayoutParams(l1);
        previousIsozigioBT.setPadding(20,20,20,20);
        previousIsozigioBT.setText("ισοζυγιο προηγ. μερας");
        toolbar.addView(previousIsozigioBT);
        Toolbar.LayoutParams l3 = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l3.gravity= Gravity.END;
        previousIsozigioBT.setLayoutParams(l3);
        previousIsozigioBT.setBackgroundColor(Color.TRANSPARENT);


        previousIsozigioBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.show();

                BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(extendedAct);
                View parentView = extendedAct.getLayoutInflater().inflate(R.layout.custom_isizigio_meth_proigoumenis_meras_dialog,null);
                bottomSheerDialog.setContentView(parentView);

                final TextView prosTV = parentView.findViewById(R.id.prosTV);
                final TextView apovTV = parentView.findViewById(R.id.apovTV);
                final TextView isozigioTV = parentView.findViewById(R.id.isozigioTV);

                AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
                task.query =
                        Str_queries.setglobals(Utils.getUserID(extendedAct) , "2" , Utils.getcompanyID(extendedAct)) + "\n" +
                        " select " +
                        " isnull(sum(total_pros),0)  as total_pros, " +
                        " isnull(sum(total_apov),0) as total_apov,  " +
                        " isnull(sum(total_isozigio),0) as total_isozigio " +
                        " from v_Nursing_Isozigio_Meth " +
                        "  where TransGroupid= " + transgroupID +
                        "  and  dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + Utils.getYesterdayDateString() +"' , 103))";
                task.ctx = extendedAct;
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
                            Toast.makeText(extendedAct, R.string.no_data, Toast.LENGTH_SHORT).show();


                        alertDialog.dismiss();
                    }
                };

                task.execute();


                bottomSheerDialog.show();
            }
        });
    }


    private void getTotalIsozigioTisImeras(){
        getJSON_DATA(Str_queries.setglobals(Utils.getUserID(extendedAct),"2",Utils.getcompanyID(extendedAct)) +
                Str_queries.getTotalIsozigioMeth(transgroupID, dateTV.getText().toString()));
    }



    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null && !results.getJSONObject(0).has("status")){

            JSONObject metriseis = results.getJSONObject(0);
            total_pros.setText(Utils.convertObjToString(metriseis.get("total_pros")));
            total_apov.setText(Utils.convertObjToString(metriseis.get("total_apov")));
            total_isozigio.setText(Utils.convertObjToString(metriseis.get("total_isozigio")));

            for (int i=0; i<adapterRV.result.size(); i++){
                String colname = adapterRV.result.get(i).getCol_name();
                if (colname != null && metriseis.has(colname) ) {
                    adapterRV.result.get(i).setValue(metriseis.optString(colname));
                    adapterRV.notifyItemChanged(i);



//                    if (colname.equals("all_hours_meds") || colname.equals("systemic_meds") ||
//                            colname.equals("one_time_meds") || colname.equals("other_meds")  ||
//                            colname.equals("metaggiseis")) {
//                        adapterRV.result.get(i).setValue(String.valueOf(metriseis.getInt(colname)));
//                        adapterRV.notifyItemChanged(i);
//
//
//                        if (colname.equals("metaggiseis")) //ΕΠΕΙΔΗ ΘΑ ΕΙΝΙΑ ΤΕΛΕΥΤΑΙΟ ΟΠΟΤΕ ΔΕΝ ΕΧΕΙ ΝΟΗΜΑ ΝΑ ΨΑΞΕΙ ΑΛΛΟ
//                            break;
//                    }
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
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {

                String query = "";
                Intent in = null;

                switch (fabItem.getItemId()) {
                    case R.id.sigkentrotika_iatrikes_odigies:

                         query = Str_queries.getIatrikes_Odigies_Medicines_Meth(transgroupID,dateTV.getText().toString());

                         in = tableView_sigkentrotika(query,
                                transgroupID,
                                new String[]{"ID","UserID","ΗΜ/ΝΙΑ","ΦΑΡΜΑΚΟ",
                                        "Ml/h","Δόση","Δοσολογία","ΩΡΑ ΧΟΡΗΓΗΣΗΣ","ΧΟΡΗΓΗΘΗΚΕ",
                                },
                                null,
                                InfoSpecificLists.getIatrikesOdigies_Medicines_Meth(),
                                false,
                                false,
                                true);
                         in.putExtra("saveTransgroupID" ,false);
                        extendedAct.startActivity(in);


                        break; // true to keep the Speed Dial open


                    case R.id.sigkentrotika_poreia_alles_eksetaseis:


                        query = Str_queries.getPoreia_alles_eksetaseis_Meth(transgroupID,dateTV.getText().toString());

                        in = tableView_sigkentrotika(query,
                                transgroupID,
                                new String[]{"Ημ/νια","Ιατρός","Πορεία νόσου","Άλλες εξετάσεις",

                                },
                                null,
                                new String[]{"dateStr","doctor","poreia","alles",
                                },
                                false,
                                false,
                                false);
                        extendedAct.startActivity(in);

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

                        extendedAct.startActivity(in);


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
        task.ctx = extendedAct;
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


                    String []  panoTitloi = new String[]{"ID","UserID","ΗΜ/ΝΙΑ" ,"ΟΝΟΜΑΣΙΑ","ΩΡΑ" ,"ΓΙΑ ΑΥΡΙΟ" };
                    Intent in = tableView_sigkentrotika("select * from  Nursing_Ergasthriaka_Datetime_Meth " +
                                    " where transgroupID = " + transgroupID +
                                    " and dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + dateTV.getText().toString() + "' , 103)) ",
                            transgroupID ,
                            panoTitloi,plagioiTitloi,tableLista,false, false, true);
                    in.putExtra("date",dateTV.getText().toString());
                    in.putExtra("plagioiTitlesAreItemIDs",true);
                    extendedAct.startActivity(in);
                }

                alertDialog.dismiss();
            }
        };

        task.execute();
    }



    @Override
    public void update_JSON(String str) {
        super.update_JSON(str);

        if (str.equals(extendedAct.getString(R.string.successful_update))) {
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
                adapterRV.updateLista(InfoSpecificLists.getIsozigio_Meth_Lista());

                Toast.makeText(extendedAct, "Μπορείτε να κάνετε μία νέα εγγραφη", Toast.LENGTH_SHORT).show();
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





// ΑΥΤΟ ΕΔΩ ΓΙΝΕΤΑΙ ΚΑΤ ΕΞΕΡΑΙΣΗ ΕΠΕΙΔΗ ΕΧΩ ΔΥΟ TEXTVIEWS ΣΤΟ RECYCLERVIEW ΜΕ ΦΑΡΜΑΚΑ ΚΑΙ ΟΤΑΝ ΔΙΑΛΕΓΑ ΦΑΡΜΑΚΟ ΣΤΟ ΔΕΥΤΕΡΟ
    //  ΤΟ ΕΒΑΖΕ ΣΤΟ ΠΡΩΤΟ ΛΟΓΟ VIEW ΕΚΤΟΣ ΑΝ ΤΟ ΠΡΩΤΟ ΔΝΕ ΦΑΙΝΟΤΑΝ ΣΤΗΝ ΟΘΟΝΗ
    // ΜΕ ΤΗΝ ΠΑΤΕΝΤΑ ΑΥΤΗ ΤΟ ΒΑΖΕΙ ΤΟ ΚΑΘΕΝΑ ΣΩΣΤΑ ΣΕ ΟΠΟΙΑΔΗΠΟΤΕ ΠΕΡΙΠΤΩΣΗ


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

/*

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

 */




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



    class MethAdapter extends BasicRV  {



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
                        showSigkentrotikaTypeMeds(holder.titleTV, StaticFields.Type_meds.MEDS_SISTIMIKI_FARM_AGOGI ,position);
                        break;

                    case "systemic_meds":
                        showSigkentrotikaTypeMeds(holder.titleTV, StaticFields.Type_meds.MEDS_OROS,position);
                        break;

                    case "one_time_meds":
                        showSigkentrotikaTypeMeds(holder.titleTV, StaticFields.Type_meds.ONE_TIME_MEDS,position);
                        break;

                    case "other_meds":
                        showSigkentrotikaTypeMeds(holder.titleTV, StaticFields.Type_meds.OTHER_MEDS,position);
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

                                extendedAct.startActivity(in);

                            }
                        });
                        break;

                    default:
                        holder.titleTV.setOnClickListener(null);

                }
        }




       public void showSigkentrotikaTypeMeds(  TextView tv , String type_medID,int pos){
           Log.e("eleos",tv.getText().toString() + " " + tv.hasOnClickListeners());

           //tv.hasOnClickListeners()
           tv.setTypeface(null, Typeface.BOLD);
           tv.setOnClickListener(v -> {
               //String query = Str_queries.getSigkentrotika_meds_meth_types(transgroupID, type_medID);
               String query = Str_queries.getSigkentrotika_karta_xorigisis_farmakon(transgroupID, type_medID);
               Intent in = tableView_sigkentrotika(query, transgroupID,
                       null,
                      // InfoSpecificLists.getFarmaka_isozigeio_meth(type_medID),
                       InfoSpecificLists.getKartaXorigisisFarmakwn(false),
                       false,
                       false,
                       true);


               extendedAct.startActivity(in);

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
