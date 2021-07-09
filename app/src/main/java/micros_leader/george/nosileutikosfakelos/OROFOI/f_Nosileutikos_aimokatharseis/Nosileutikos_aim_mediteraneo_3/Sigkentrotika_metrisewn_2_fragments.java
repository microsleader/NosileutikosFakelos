package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.TableView.TableFragment;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.customers.Frontis;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityTable2FragmentsBinding;
import okhttp3.internal.Util;

public class Sigkentrotika_metrisewn_2_fragments extends BasicActivity {

    private Bundle bundle1, bundle2;
    private String patientID, transgroupID;
    private ActivityTable2FragmentsBinding bd;
    private int custID;
    private boolean isEditableNurse, is_onlyView ;



    private final static String[] DEFAULT_SIGKENTROTIKA_STATHERON_TITLES =
            new String[]{"ID","Ημ/νία","Υπευθ. Ιατρός \nβάρδιας","Προγρ. \nώρα έναρξης" , "Ώρα έναρξης" ,"Διάρκεια",
            "Αρχικό βάρος","Ξηρό βάρος","Διαφορά βάρους","Βάρος εξόδου","Ένδειξη υπερ/τος\n Μηχαν.τ.ν. (UF)" ,
            "Θερμοκ. \nδιαλύματος (°C)","Kt/V","fistula",
            "Μόσχευμα","Βελόνες","SN-Y LINE ADAPTOR \n Συνδετικό","Είδος αιμοκάθαρσης",
            "Κεντρ. φλεβ. \nκαθετηρας μονιμος","Κεντρ. φλεβ. \nκαθετηρας προσωρινος","Κεντρ. φλεβ. \nκαθετηρας_κειμενο","Χωρητ. ηπαρινισμού σκελών Α",
            "Χωρητ. ηπαρινισμού \nσκελών Φ","Διάλυμα","Φίλτρο","LOT Φίλτρου",  "Ηπαρίνη"};


    private final static String[] DEFAULT_SIGKENTROTIKA_SINEXON_TITLES =
            new String[]{"ID","ΗΜ/ΝΙΑ / ΩΡΑ","Συστολική πίεση (Hg)","Διαστολική πίεση (mm)",
                    "Σφύξεις:","Θερμοκρασία (°C)",  "SPO2 (%)",  "Ρυθμός ροή αντλίας (ml/min)","Πίεση φλεβ. γραμμής",
                    "Πίεση αρτ. γραμμής:","Ρυθμός υπερδιηθησης \n ανά ώρα(U.F.) (l/h)","Αγωγιμότητα (ms/cm)","HCO 3 (ms/cm)",
                    "Μετρήσεις-Παραμβάσεις","Παρατηρήσεις","Εγχύσεις - Φάρμακα","Vit - B",
                    "L - carnittine","Alphacalcidol","EPO","Epoetin alpha",
                    "Epoetin zeta","Darbepoetin","Paricalcitol","Σίδηρος"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityTable2FragmentsBinding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        patientID = getIntent().getStringExtra("patientID");
        transgroupID = getIntent().getStringExtra("transgroupID");
        is_onlyView = getIntent().getBooleanExtra("is_onlyView",false);

        custID = Utils.getCustomerID(this);
        isEditableNurse = Utils.getIsNurse(this);
        statheresMetriseis();
        sinexeissMetriseis();

        if (Customers.isFrontis(custID))
            showFragments();

        alertDialog.dismiss();




    }



    private void statheresMetriseis (){


        if (!Customers.isFrontis(custID)) {


            AsyncTaskGetJSON2 t = new AsyncTaskGetJSON2();
            t.ctx = this;
            t.query =
                    "select  top 3 \n" +
                            "dbo.datetostr(tg.datein) + ' , ' +  dbo.timetostr(tg.datein) as datestr \n" +
                            "from Nursing_Hemodialysis_initial2_MEDIT n \n" +
                            " join transgroup tg on tg.id = n.TransGroupID\n" +
                            " left join nursing_medical_instructions ins on  n.patientID = ins.patientID \n" +
                            " where n.transgroupID = " + transgroupID +
                            "  group by  tg.Datein, n.id \n" +
                            " order by tg.Datein desc, n.id desc";


            t.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {
                    ArrayList <TableViewItem> lista = getSigkentrotikaStatheronLista();
                    String[] panoTitloi = new String[]{};
                    String[] plagioiTitloi = new String[lista.size()];

                    if (results != null && !results.getJSONObject(0).has("status")){
                        panoTitloi = new String[results.length()];


                        for (int i=0; i<results.length(); i++){
                            panoTitloi[i] = results.getJSONObject(i).getString("datestr");
                        }

                        for (int i=0; i<lista.size(); i++){
                            plagioiTitloi[i] = lista.get(i).getTitle();
                        }


                    }


                    bundle1  =   tableView_sigkentrotika_dialogFragment( Str_queries.getSigkentrotika_statherwn_metrisewn(patientID,transgroupID,custID),
                            transgroupID,
                            panoTitloi ,
                            plagioiTitloi,
                            lista,
                            false,
                            false,
                            !is_onlyView && isEditableNurse);

                    bundle1.putString("toolbar_title","Συγκεντρωτικά σταθερών μετρήσεων");
                    bundle1.putBoolean("setOnlyFirstRowAvalaible", !is_onlyView && isEditableNurse);


                }

            };

            t.execute();




        }

        else{

            //frontis
            bundle1  =   tableView_sigkentrotika_dialogFragment( Str_queries.getSigkentrotika_statherwn_metrisewn(patientID,transgroupID, custID),
                    transgroupID,
                    null,
                    getSigkentrotikaStatheronLista(),
                    false,
                    false,
                    !is_onlyView && isEditableNurse);

            bundle1.putString("toolbar_title","Συγκεντρωτικά σταθερών μετρήσεων");
            bundle1.putBoolean("setOnlyFirstRowAvalaible", !is_onlyView && isEditableNurse);

        }

    }



    private void sinexeissMetriseis (){

        if (!Customers.isFrontis(custID)) {


            AsyncTaskGetJSON2 t = new AsyncTaskGetJSON2();
            t.ctx = this;
            t.query =
                    "select  top 8 \n" +
                            "dbo.datetostr(date) + ' , ' +  dbo.timetostr(date) as datestr \n" +
                            "from Nursing_Hemodialysis_2_MEDIT  ni  \n" +
                            "left join ( \n" +
                            "select   \n" +
                            "top 1 \n" +
                            "patientid \n" +

                            "from Nursing_Medical_Instructions  \n" +
                            "where patientid =  " + patientID + "\n" +
                            "order by id desc \n" +
                            ")  m  on m.PatientID = ni.PatientID \n" +
                            "where  ni.PatientID =  " + patientID +
                            "\n" +
                            "order by id desc ";
//
//                    "select top 8 dbo.datetostr(date) + ' , ' +  dbo.timetostr(date) as datestr  from Nursing_Hemodialysis_2_MEDIT " +
//                    " where transgroupID = " + transgroupID + " order by id desc " ;
            t.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {
                    ArrayList <TableViewItem> lista = getSigkentrotikaSinexonLista();
                    String[] panoTitloi = new String[]{};
                    String[] plagioiTitloi = new String[lista.size()];

                    if (results != null && !results.getJSONObject(0).has("status")){
                        panoTitloi = new String[results.length()];


                        for (int i=0; i<results.length(); i++){
                            panoTitloi[i] = results.getJSONObject(i).getString("datestr");
                        }

                        for (int i=0; i<lista.size(); i++){
                            plagioiTitloi[i] = lista.get(i).getTitle();
                        }


                    }

                    bundle2 = tableView_sigkentrotika_dialogFragment(Str_queries.getSigkentrotika_sinexwn_metrisewn(patientID),
                            transgroupID,
                            panoTitloi,
                            plagioiTitloi,
                            lista,
                            false, false, !is_onlyView && isEditableNurse);
                    bundle2.putString("toolbar_title", "Συγκεντρωτικά συνεχών μετρήσεων");
                    bundle2.putBoolean("setOnlyFirstRowAvalaible", !is_onlyView && isEditableNurse);
                    bundle2.putBoolean("hasValuesForCH", true);

                    showFragments();

                }

            };

            t.execute();




        }

        else {

            //mono gia frontis

            bundle2 = tableView_sigkentrotika_dialogFragment(Str_queries.getSigkentrotika_sinexwn_metrisewn(patientID),
                    transgroupID,
                    null,
                    getSigkentrotikaSinexonLista(),
                    false,
                    false,
                    !is_onlyView && isEditableNurse);

            bundle2.putString("toolbar_title", "Συγκεντρωτικά συνεχών μετρήσεων");
            bundle2.putBoolean("setOnlyFirstRowAvalaible", !is_onlyView && isEditableNurse);
            bundle2.putBoolean("hasValuesForCH", true);

        }
    }

    private void showFragments(){

        TableFragment tf1 = new TableFragment();
        TableFragment tf2 = new TableFragment();
        tf1.setArguments(bundle1);
        tf1.set_editextsUsingDialogs(true);
        tf2.setArguments(bundle2);
        tf2.set_editextsUsingDialogs(true);


        getSupportFragmentManager().beginTransaction()
                .add(bd.fragment1.getId(), tf1)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(bd.fragment2.getId(), tf2)
                .commit();

    }




//    private String[] getSigkentrotikaStatheronTitles(){
//        switch (custID){
//            case Customers.CUSTID_FRONTIS:
//            case Customers.CUSTID_FRONTIS_2:
//                return Frontis.getSigkentrotikaStatherwnTitles();
//
//            case Customers.CUSTID_MEDITERRANEO:
//                return DEFAULT_SIGKENTROTIKA_STATHERON_TITLES;
//
//            default:
//                return DEFAULT_SIGKENTROTIKA_STATHERON_TITLES;
//
//
//
//        }
//    }


//
//    private String[] getSigkentrotikaSinexonTitles(){
//        switch (custID){
//            case Customers.CUSTID_FRONTIS:
//            case Customers.CUSTID_FRONTIS_2:
//                return Frontis.getSigkentrotikaSinexeisTitles();
//
//            case Customers.CUSTID_MEDITERRANEO:
//                return DEFAULT_SIGKENTROTIKA_SINEXON_TITLES;
//
//            default:
//                return DEFAULT_SIGKENTROTIKA_SINEXON_TITLES;
//        }
//    }


    private ArrayList<TableViewItem> getSigkentrotikaStatheronLista(){
        switch (custID){
            case Customers.CUSTID_FRONTIS:
            case Customers.CUSTID_FRONTIS_2:
                return Frontis.getSigkentrotikaStatheresMetriseis();

            case Customers.CUSTID_MEDITERRANEO:
                return InfoSpecificLists.getSigkentrotikaStatheresMetriseis_MEDIT();

            default:
                return InfoSpecificLists.getSigkentrotikaStatheresMetriseis_MEDIT();



        }
    }


    private ArrayList<TableViewItem> getSigkentrotikaSinexonLista(){
        switch (custID){
            case Customers.CUSTID_FRONTIS:
            case Customers.CUSTID_FRONTIS_2:
                return Frontis.getSigkentrotikaSinexeisMetriseis();

            case Customers.CUSTID_MEDITERRANEO:
                return InfoSpecificLists.getSigkentrotikaSinexeisMetriseis_MEDIT();

            default:
                return InfoSpecificLists.getSigkentrotikaSinexeisMetriseis_MEDIT();



        }
    }



}
