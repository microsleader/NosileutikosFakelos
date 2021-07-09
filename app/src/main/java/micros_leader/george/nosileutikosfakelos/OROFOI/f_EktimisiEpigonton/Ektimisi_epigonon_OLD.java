

package micros_leader.george.nosileutikosfakelos.OROFOI.f_EktimisiEpigonton;

import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ClassItemsCheckboxesForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.Listeners.SearchNosileuomenoListener;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Ektimisi_epigonon_OLD extends BasicActivity implements AsyncCompleteGetPatientsTask, MyDialogFragmentCloseListener, AsyncCompleteTask2, AsyncGetUpdateResult {

    private TextView patientsTV, energeiaTV , sinolikosVathmos;
    private Spinner kinitikotitaSP, anapSixnotitaSP , kardSixnotitaSP, sapSP,
            thermokrasiaSP , epipSinProsoxisSP;

    private String kinitikotita, anapSixnotita , kardSixnotita, sap,
            thermokrasia , epipSinProsoxis;

    private CircularProgressButton updateButton;

    private CheckBox traumaCH;
    private RecyclerView ektimisiRV;
    private RecyclerViewEktimisiEpigontonAdaptor adapterRV;
    public AlertDialog alertDialog;
    private ArrayList<PatientsOfTheDay> patientsNosileuomenoi;
    private ArrayList <String> currentEktimisiLista ;
    private ArrayList<ClassItemsCheckboxesForRV> ektimisiListaAdaptor;
    private  ArrayAdapter adapter;
    private boolean isThereTransgroupID;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ektimisi_epigonton_old);

        patientsTV = findViewById(R.id.patientsTV);
        energeiaTV = findViewById(R.id.energeiaTV);
        sinolikosVathmos = findViewById(R.id.sinolVathmosTV);

        kinitikotitaSP = findViewById(R.id.kinitikotitaSP);
        anapSixnotitaSP = findViewById(R.id.anapSixnotitaSP);
        kardSixnotitaSP = findViewById(R.id.kardSixnotitaSP);
        sapSP = findViewById(R.id.sapSP);
        thermokrasiaSP = findViewById(R.id.thermokrasiaSP);
        epipSinProsoxisSP = findViewById(R.id.epipSinProsoxisSP);
        traumaCH = findViewById(R.id.traumaCH);
        ektimisiRV = findViewById(R.id.ektimisiRV);
        updateButton = findViewById(R.id.updateButton);

        //ektimisiRV.setLayoutManager(new LinearLayoutManager(EktimisiEpigontonActivity.this, LinearLayoutManager.VERTICAL, false));
        // ektimisiRV.setLayoutManager(new GridLayoutManager(this, 2));

        //  eksitirioRV.addItemDecoration(new DividerItemDecoration(EksitirioActivity.this, LinearLayout.VERTICAL));




        ektimisiRV.setItemViewCacheSize(300);
        ektimisiRV.setHasFixedSize(true);
        ektimisiRV.setNestedScrollingEnabled(false);
        adapterRV = new RecyclerViewEktimisiEpigontonAdaptor(Ektimisi_epigonon_OLD.this,InfoSpecificLists.getEktimisiEpigontonCheckboxes());

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                boolean isHeader = isHeader(position);
                if (isHeader)
                    return  2;
                else
                    return  1;

            }
        });
        ektimisiRV.setLayoutManager(manager);
        ektimisiRV.setAdapter(adapterRV);
        ektimisiListaAdaptor = adapterRV.result;
        alertDialog = Utils.setLoadingAlertDialog(this);


        updateButtonListener();

        getPatientsList(this, R.id.patientsTV ,R.id.floorsSP);

        spinnersAdapter();

    }

    public static boolean isHeader(int position) {

        if (position == 0 || position == 11 || position == 36) //  Η ΛΙΣΤΑ  getEktimisiEpigontonCheckboxes ΑΠΟ ΤΗΝ ΚΛΑΣΣΗ infospecificlist
            return true;
        else
            return false;
    }

    private void spinnersAdapter() {

        setSpinnerAdapters(kinitikotitaSP,InfoSpecificLists.getKinitikotitaLista());
        setSpinnerAdapters(anapSixnotitaSP,InfoSpecificLists.getAnapneustikiSixnotitaLista());
        setSpinnerAdapters(kardSixnotitaSP,InfoSpecificLists.getKardiakiSixnotitaLista());
        setSpinnerAdapters(sapSP,InfoSpecificLists.getSAP_lista());
        setSpinnerAdapters(thermokrasiaSP,InfoSpecificLists.getThermokrasiaLista());
        setSpinnerAdapters(epipSinProsoxisSP,InfoSpecificLists.getEpipedoSinProsoxisLista());

    }


    private void setSpinnerAdapters(Spinner spinner, ArrayList lista ) {


        adapter = new ArrayAdapter<>(Ektimisi_epigonon_OLD.this,
                R.layout.spinner_layout2, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


    }

    @Override
    public void taskCompleteGetPatients(ArrayList<PatientsOfTheDay> lista) {
        if (lista != null) {

            // ΤΟΠΟΘΕΤΗΣΗ ΤΟΥ ΛΙΣΕΝΕΡ ΕΔΩ ΕΠΕΙΔΗ ΔΙΑΦΟΡΕΤΙΚΑ ΠΑΙΡΝΕΙ ΚΕΝΗ ΛΙΣΤΑ
            patientsNosileuomenoi = lista;

            transgroupID = Utils.getSplitSPartString(patientsTV.getText().toString(),",",3);

            patientsTV.setOnClickListener(new SearchNosileuomenoListener(Ektimisi_epigonon_OLD.this, patientsNosileuomenoi));

            getEktimisiEpigonton();

        }
    }



    private void clearValues() {


        energeiaTV.setText("");
        kinitikotitaSP.setSelection(0);
        anapSixnotitaSP.setSelection(0);
        kardSixnotitaSP.setSelection(0);
        sapSP.setSelection(0);
        thermokrasiaSP.setSelection(0);
        epipSinProsoxisSP.setSelection(0);
        traumaCH.setChecked(false);

        sinolikosVathmos.setText("");


        for (int i =0; i<ektimisiListaAdaptor.size(); i ++){

            ektimisiListaAdaptor.get(i).setTrue(false);
        }
        adapter.notifyDataSetChanged();

    }


    private void getEktimisiEpigonton(){
        if (Utils.isNetworkAvailable2(this)) {


            alertDialog.show();
            String query = new Str_queries().getEKTIMISI_EPIGONTON_PERSON(transgroupID);
            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = getApplicationContext();
            task.listener = Ektimisi_epigonon_OLD.this;
            task.query = query;
            task.execute();



        }
    }



    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        currentEktimisiLista = new ArrayList();

        if (results != null) {

            // ΕΑΝ ΔΕΝ ΕΧΕΙ ΣΤΑΤΟΥΣ ΔΗΛΑΔΗ ΕΑΝ ΕΧΕΙ ΑΠΟΤΕΛΕΣΜΑΤΑ ΝΑ ΦΕΡΕΙ ΝΑ ΚΑΝΕΙ ΤΟ ΑΠΟ ΚΑΤΩ
            if (!results.getJSONObject(0).has("status")) {

                isThereTransgroupID = true;

                JSONObject currentEktimisi = results.getJSONObject(0);


                Iterator<String> keys = currentEktimisi.keys();
                while (keys.hasNext()) {

                    String str_Name = keys.next();
                    String value = String.valueOf(currentEktimisi.optString(str_Name));

//                    if (value.equals(""))
//                        value = "false";

                    if (str_Name.equals("ID"))
                        id = value;
                    else if (str_Name.equals("TransGroupID"))
                        transgroupID = value;
                    else if (str_Name.equals("kinitikotita")) {
                        kinitikotita = value;
                        kinitikotitaSP.setSelection(InfoSpecificLists.getKinitikotitaListaID(value));
                    }
                    else if (str_Name.equals("anapnefstiki_sixnotita")) {
                        anapSixnotita = value;
                        anapSixnotitaSP.setSelection(InfoSpecificLists.getAnapneustikiSixnotitaListaID(value));
                    }
                    else if (str_Name.equals("kardiaki_sixnotita")) {
                        kardSixnotita = value;
                        kardSixnotitaSP.setSelection(InfoSpecificLists.getKardiakiSixnotitaListaID(value));

                    }
                    else if (str_Name.equals("sistoliki_artiriaki_piesi")) {
                        sap = value;
                        sapSP.setSelection(InfoSpecificLists.getSAP_listaID(value));

                    }
                    else if (str_Name.equals("thermokrasia")) {
                        thermokrasia = value;

                        thermokrasiaSP.setSelection(InfoSpecificLists.getThermokrasiaListaID(value));

                    }
                    else if (str_Name.equals("sinidisi_prosoxi")) {
                        epipSinProsoxis = value;
                        epipSinProsoxisSP.setSelection(InfoSpecificLists.getEpipedoSinProsoxisListaID(value));

                    }
                    else if (str_Name.equals("travma")) {
                        if (value.equals("65"))
                            traumaCH.setChecked(true);
                        else
                            traumaCH.setChecked(false);
                    }

                    else if (str_Name.equals("sinolo_vathmou"))
                        sinolikosVathmos.setText(value);

                    else if (str_Name.equals("action"))
                        energeiaTV.setText(value);




                    currentEktimisiLista.add(value);

                }

                for (int i =0; i< 10; i ++){                     //Για να διαγραφουν οι πρωτες 10 τιμες
                    currentEktimisiLista.remove(0);
                }

                for (int i =0; i< 8; i ++){                     //Για να διαγραφουν οι τελευταιες 8 τιμες
                    currentEktimisiLista.remove(currentEktimisiLista.size() - 1);
                }

                // ΟΙ ΘΕΣΕΙΣ ΑΥΤΕΣ ΙΣΟΔΥΝΑΜΟΥΝ ΜΕ ΤΟΥΣ ΤΙΤΛΟΥΣ ΣΤΗ ΛΙΣΤΑ  ΠΟΥ ΕΧΩ ΦΤΙΑΞΕΙ
                // Η ΛΙΣΤΑ ΠΟΥ ΚΑΕΤΒΑΖΩ ΑΠΟ ΤΗ ΒΑΣΗ ΜΕ ΤΗ ΛΙΣΤΑ ΤΗΝ ΑΝΤΙΣΤΟΙΧΗ ΤΩΝ ΠΕΔΙΩΝ ΠΟΥ ΕΧΩ ΦΤΙΑΞΕΙ ΠΡΕΠΕΙ ΝΑ ΕΧΟΥΝ
                // ΤΟΝ ΙΔΙΟ ΑΡΙΘΜΟ ΜΕΓΕΘΟΥΣ

                // ΑΥΤΑ ΤΑ ΠΕΔΙΑ ΕΧΟΥΝ ΠΡΟΣΤΕΘΕΙ ΑΠΛΑ ΣΑΝ ΤΙΤΛΟΙ ΝΑ ΕΜΦΑΝΙΖΟΝΤΑΙ ΔΕΝ ΕΧΟΥΜΕ ΚΑΜΙΑ ΧΡΗΣΙΜΟΤΗΤΑ
                // ΔΕΝ ΒΡΗΚΑ ΑΛΛΟ ΤΡΟΠΟ ΓΙΑ ΤΟΥΣ ΤΙΤΛΟΥΣ

                currentEktimisiLista.add(0,"false");    //  ΕΔΩ ΘΕΣΗ ΤΙΤΛΟΥ ΟΠΟΤΕ ΑΔΙΑΦΟΡΟ
                currentEktimisiLista.add(11,"false");   //  ΕΔΩ ΘΕΣΗ ΤΙΤΛΟΥ ΟΠΟΤΕ ΑΔΙΑΦΟΡΟ
                currentEktimisiLista.add(36,"false");   //  ΕΔΩ ΘΕΣΗ ΤΙΤΛΟΥ ΟΠΟΤΕ ΑΔΙΑΦΟΡΟ



                // ΤΟΠΟΘΕΤΗΣΗ ΤΙΜΩΝ ΣΤΑ ΠΕΔΙΑ ΤΟΥ RECYCLERVIEW

                for (int i = 0; i<currentEktimisiLista.size(); i++){

                    boolean value = Boolean.parseBoolean(currentEktimisiLista.get(i));

                    ektimisiListaAdaptor.get(i).setTrue(value);

                }
            }

            else{
                isThereTransgroupID = false;
                clearValues();

            }

        }
        adapterRV.notifyDataSetChanged();


        alertDialog.dismiss();

    }


    private void updateButtonListener(){

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.isNetworkAvailable2(Ektimisi_epigonon_OLD.this ) &&
                        !Utils.isEmpty(patientsTV, Ektimisi_epigonon_OLD.this)) {

                    updateButton.startAnimation();

                    kinitikotita = InfoSpecificLists.getKinitikotitaListaName(kinitikotitaSP.getSelectedItem().toString());
                    anapSixnotita = String.valueOf(InfoSpecificLists.getAnapneustikiSixnotitaListaName(anapSixnotitaSP.getSelectedItem().toString()));
                    kardSixnotita = String.valueOf(InfoSpecificLists.getKardiakiSixnotitaListaName(kardSixnotitaSP.getSelectedItem().toString()));
                    sap = String.valueOf(InfoSpecificLists.getSAP_listaName(sapSP.getSelectedItem().toString()));
                    thermokrasia = String.valueOf(InfoSpecificLists.getThermokrasiaName(thermokrasiaSP.getSelectedItem().toString()));
                    epipSinProsoxis = String.valueOf(InfoSpecificLists.getEpipedoSinProsoxisListaName(epipSinProsoxisSP.getSelectedItem().toString()));


                    if (!isThereTransgroupID)

                        insertInfo();
                    else
                        updateInfo();
                }
            }
        });
    }


    private void insertInfo(){
        String query = new Str_queries().getEKTIMISI_EPIGONTON_INSERT(transgroupID,
                kinitikotita,anapSixnotita,kardSixnotita,sap, thermokrasia,epipSinProsoxis ,traumaCH.isChecked(),ektimisiListaAdaptor);

        AsyncTaskUpdate task = new AsyncTaskUpdate(Ektimisi_epigonon_OLD.this, query);
        task.listener =  Ektimisi_epigonon_OLD.this;
        task.execute();
    }



    private void updateInfo(){

        String query = new Str_queries().getEKTIMISI_EPIGONTON_UPDATE(transgroupID,
                kinitikotita,anapSixnotita,kardSixnotita,sap, thermokrasia,epipSinProsoxis ,traumaCH.isChecked(),ektimisiListaAdaptor);

        AsyncTaskUpdate task = new AsyncTaskUpdate(Ektimisi_epigonon_OLD.this,query);
        task.listener = Ektimisi_epigonon_OLD.this;
        task.execute();



    }


    @Override
    public void update(String str) {
        if (str.equals(getString(R.string.successful_update))) {
            isThereTransgroupID = true; // ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΞΑΝΑΠΑΤΗΣΕΙ ΤΗΝ ΙΔΙΑ ΣΤΙΓΜΗ ΤΟ ΚΟΥΜΠΙ ΓΙΑ ΝΑ ΜΗΝ ΚΑΝΕΙ ΠΑΛΙ ΙΝΣΕΡΤ

            Utils.timeHandlerDoneButton(updateButton, Ektimisi_epigonon_OLD.this);
            getEktimisiEpigonton();
        }
        else
            Utils.timeHandlerErrorButton(updateButton, Ektimisi_epigonon_OLD.this);
    }

    @Override
    public void handleDialogClose(String transgroupid) {
        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT
        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);

        getEktimisiEpigonton();
    }
}
