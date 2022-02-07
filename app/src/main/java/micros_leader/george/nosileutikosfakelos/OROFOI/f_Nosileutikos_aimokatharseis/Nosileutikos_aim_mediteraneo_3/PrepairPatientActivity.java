package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.BaseAct.BasicActivityAbs;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended_str;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.fragments.Iatrikes_odigies_fragment;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors.DialogFragmentSearchPat;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.StaticFields;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.customers.Frontis;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityPrepairPatientBinding;

public class PrepairPatientActivity extends BasicActivityAbs implements DataSended_str {

    ActivityPrepairPatientBinding bd;
    private TextView patientsTV;
    private final ArrayList <PatientsOfTheDay> patList =  new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        bd = ActivityPrepairPatientBinding.inflate(getLayoutInflater());
//        View view = bd.getRoot();
//        setContentView(view);

        patientsTV = findViewById(R.id.patientsTV);
        extendedAct = this;
        alertDialog = Utils.setLoadingAlertDialog(this);

        getPatients();
        patientsTV.setOnClickListener(view -> selectPatient());

    }



    private void selectPatient(){

        DialogFragmentSearchPat df = new DialogFragmentSearchPat();
        Bundle putextra = new Bundle();
        putextra.putSerializable("patients", patList);

        df.setArguments(putextra);
        df.show(getSupportFragmentManager() , "Dialog");

    }


    @Override
    public void hereIsYourStr_Data(String info) {

        patientsTV.setText(info);
        patientID = Utils.getSplitSPartString(info,",",0);
        runMainquery();

    }


    private void getPatients(){
        String q = "select \n" +
                "distinct\n" +
                "\n" +
                "p.id, \n" +
                "p.FirstName,\n" +
                "p.LastName,\n" +
                "p.code as patCode,\n" +
                "p.amka,\n" +
                "dbo.datetostr(P.DATEBIRTH) as age,\n" +
                "p.height\n" +
                "\n" +
                "from person p \n" +
                "join transgroup tg on tg.PatientID = p.id\n" +
                "where p.IsPatient = 1\n" +
                "and p.Cancelled is null\n" +
                "and tg.CompanyID =  " + companyID +
                " and tg.Category = 3 " +
                "\n" +
                "order by p.id";

        AsyncTaskGetJSON2 t = new AsyncTaskGetJSON2();
        t.ctx = this;
        t.query = q;
        t.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {
                parseResults(results);
            }
        };

        t.execute();
    }


    @SuppressLint("SetTextI18n")
    private void parseResults(JSONArray results) throws JSONException {
        patList.clear();
        if (results == null) {
            alertDialog.dismiss();
            Toast.makeText(this, "Δεν υπάρχουν αυτή τη στιγμή δεδομένα", Toast.LENGTH_SHORT).show();
        }
        else {

            if ( !results.getJSONObject(0).has("status")) {

                patList.clear();
                for (int i = 0; i < results.length(); i++) {

                    JSONObject currentPatient = results.getJSONObject(i);

                    PatientsOfTheDay pat = new PatientsOfTheDay();

                    pat.setFirstName(currentPatient.getString("FirstName"));
                    pat.setLastName(currentPatient.getString("LastName"));
                    pat.setPatientID(currentPatient.getInt("id"));
                    pat.setAmka(currentPatient.getString("amka"));
                    if (Customers.isFrontis(custID)){
                        pat.setPatCode(currentPatient.getString("patCode"));
                        pat.setAge(currentPatient.getString("age"));
                        pat.setHeight(currentPatient.getString("height"));
                    }


                    patList.add(pat);

                }



                patientsTV.setText(
                        patList.get(0).getLastName() + " " +
                        patList.get(0).getFirstName() + " , " +
                        patList.get(0).getAmka() +
                        (Customers.isFrontis(custID) ? ",\n"  +
                                "Κωδ:  " + patList.get(0).getPatCode() + "  ,  " +
                                "Ηλικία:  " + patList.get(0).getAge() + "  , " +
                                "Ύψος:  " + patList.get(0).getHeight() + " cm" : "")

                );

                patientID = String.valueOf(patList.get(0).getPatientID());

                runMainquery();


            } else
                patientsTV.setText(StaticFields.NO_PATIENT_FOUND);

        }

    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_prepair_patient;
    }

    @Override
    protected int rv_ID() {
        return R.id.rv;
    }

    @Override
    protected String setTable() {
        return "nursing_medical_instructions";
    }

    @Override
    protected ArrayList<ItemsRV> set_RV_list() {
        return getiatrikesOdigiesLista();
    }

    @Override
    protected int[] positionTitlesInRV() {
        return new int[0];
    }

    @Override
    protected boolean isThereImageUpdateButton() {
        return false;
    }

    @Override
    protected String[] colNames_not_in_RV() {
        return new String[0];
    }

    @Override
    protected String setMainQuery() {
        return "select * from person where ispatient = 1 and cancelled is null ";
    }


    private void runMainquery (){

        if (alertDialog != null && !isFinishing())
            alertDialog.show();

        String cols = getFROM_NAMEJSON_COLUMNS(nameJson, "n");
        cols = cols.replace("n.eidosName,","").replace("n.filName,","")
                .replace("n.durationName,","")
                .replace("n.agogiName,","").replace("n.agogiName120,","")
                .replace("n.ipokName,","")
                .replace("n.dialeimaName,","").replace("n.epoName,","")
                .replace("n.feName,","").replace("n.VitB_Name,","")
                .replace("n.CarnitineName,","").replace("n.vitD_Name,","")
                .replace("n.agogiDosisName,","")
                .replace("n.fe_Name,","").replace("n.carnitine_Name,","")
                .replace("n.vitB_Name,","").replace("n.etel_Name,","")
                .replace("n.velonesName,","")
        ;


        if ( Customers.isFrontis(custID))
            getJSON_DATA( Frontis.getMEDICAL_INSTRACTIONS(cols, patientID, isNurse));
        else
            getJSON_DATA( Str_queries.getMEDICAL_INSTRACTIONS_NEW(cols, patientID));
    }

    @Override
    protected void runAfterPost(JSONArray results) throws JSONException {

        newList =  getiatrikesOdigiesLista();

        if ( results != null &&  !results.getJSONObject(0).has("status")) {


            String doctorName = results.getJSONObject(0).getString("doctorName");
            String month = results.getJSONObject(0).getString("Month");
            String yearName = results.getJSONObject(0).getString("yearName");
            id = results.getJSONObject(0).getString("ID");

            setValuesTolistaAdaptor(null, newList);


            if (results.getJSONObject(0).has("agg_prospelasi")) {
                String ag_prosID = results.getJSONObject(0).getString("agg_prospelasi");
                if (!ag_prosID.isEmpty()) {
                    for (int i = 0; i < listaAdaptor.size(); i++) {
                        if (listaAdaptor.get(i).col_name.equals("agg_prospelasi")) {
                            newList.get(i).setValue(Iatrikes_odigies_fragment.getAggiakiProspelasi(Integer.parseInt(ag_prosID)));
                            break;
                        }
                    }
                }
            }

            if (isNurse) {
                newList.get(0).setValue(month + " / " + yearName);
                newList.get(1).setValue(doctorName);
            }
            else if (isDoctor){
                newList.get(2).setValue(doctorName);
            }


        }

        adapter.updateLista(newList);
        alertDialog.dismiss();


    }

    @Override
    public boolean isThereFloorAndPatientTVs() {
        return false;
    }



    private ArrayList<ItemsRV> getiatrikesOdigiesLista() {

        switch (custID) {
            case Customers.CUSTID_FRONTIS:
            case Customers.CUSTID_FRONTIS_2:
                return Frontis.getMedicalInsLista(isDoctor);

            case Customers.CUSTID_MEDITERRANEO:
                return InfoSpecificLists.getMedicalInsLista();

            default:
                return InfoSpecificLists.getMedicalInsLista();

        }
    }

}