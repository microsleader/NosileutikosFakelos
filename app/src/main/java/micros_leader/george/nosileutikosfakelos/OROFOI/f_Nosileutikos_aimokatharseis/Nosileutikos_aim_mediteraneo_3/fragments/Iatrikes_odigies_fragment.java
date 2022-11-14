package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.GridLayoutManagerWrapper;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.customers.Frontis;
import micros_leader.george.nosileutikosfakelos.customers.KianousStavros;
import micros_leader.george.nosileutikosfakelos.databinding.FragmentIatrikesOdigiesFragmentBinding;

import static micros_leader.george.nosileutikosfakelos.BasicActivity.isHeader;

/**
 * A simple {@link Fragment} subclass.

 */
public class Iatrikes_odigies_fragment extends Fragment  {


    private View view;
    private MainActivity_Aim main;
   // private String transgroupID, patientid,companyID,table;
    private BasicRV adapter;
    public ArrayList <ItemsRV>  listaAdaptor , newList; //= iatrikesOdigiesLista();
    private String  table;
    private FragmentIatrikesOdigiesFragmentBinding bd;
    private ArrayList <String> namesGiaApothikeusi,oldValuesLista,valuesGiaApothikeusi ;





    public Iatrikes_odigies_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bd = FragmentIatrikesOdigiesFragmentBinding.inflate(inflater, container, false);
        view = bd.getRoot();

        main = (MainActivity_Aim) getActivity();

        table = "Nursing_Medical_Instructions";
        newList = iatrikesOdigiesLista();
        if (main != null){
            main.getAll_col_names(newList);

        }

        bd.instructionsRV.setItemViewCacheSize(20);
        bd.instructionsRV.setHasFixedSize(true);
        bd.instructionsRV.setNestedScrollingEnabled(false);

        GridLayoutManagerWrapper manager = new GridLayoutManagerWrapper(getContext(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                String colName = listaAdaptor.get(position).getCol_name();
                if (//colName.equals("allergiesMeds") || colName.equals("farmAgogiMeds") ||
                        colName.equals("genikes_odigies") ) // εδω ειναι για να βγαλει τις γενικες οδηγιες σε μια γραμμη
                    return 2;
                else
                    return 1;

            }
        });

        bd.instructionsRV.setLayoutManager(manager);

        adapter = new BasicRV(main,  iatrikesOdigiesLista());
        listaAdaptor = adapter.result;
        bd.instructionsRV.setAdapter(adapter);


        if (main.isDoctor){
            namesGiaApothikeusi = new ArrayList<>();
            valuesGiaApothikeusi = new ArrayList<>();
            main.getAll_col_names(iatrikesOdigiesLista(),namesGiaApothikeusi);

            if (Customers.isFrontis(main.custID) || main.custID == Customers.CUSTID_KYANOS_STAVROS_MTN_PATRA) {
                bd.neaEggrafiBT.setVisibility(View.VISIBLE);
                bd.neaEggrafiBT.setOnClickListener(view -> { main.id =""; Toast.makeText(main, "Μπορείτε να κάνετε μία νέα εγγραφή", Toast.LENGTH_SHORT).show(); });
            }
        }
        getMedicalInsructions(false);


        return view;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bd = null;
    }


    public void getMedicalInsructions(boolean is_patient_changed){

        if (is_patient_changed) {
            ArrayList<ItemsRV> emptyList = iatrikesOdigiesLista();
            adapter.updateLista(emptyList);
        }

        if ( main != null ) {

            String code = Utils.getfirstPartSplitCommaString(main.bd.patientsTV.getText().toString());
            String patientID = main.getPatientIDUsingCode.get(code);

            main.nameJson.clear();
            main.getAll_col_names(iatrikesOdigiesLista());

            //ΑΥΤΟ ΕΔΩ ΕΧΕΙ ΓΙΝΕΙ ΜΕ ΤΗΝ ΠΙΟ ΠΑΛΙΑ ΜΕΘΟΛΟΓΙΑ ΚΑΙ  ΔΕΝ ΧΡΗΣΙΜΟΠΟΙΕΙΤΑΙ ΑΛΛΟΥ

            String cols = main.getFROM_NAMEJSON_COLUMNS(main.nameJson, "n");
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
                    .replace("n.velonesName,","").replace("n.doctorName,","")
            ;

            if ( Customers.isFrontis(main.custID))
                main.getJSON_DATA( Frontis.getMEDICAL_INSTRACTIONS(cols, patientID, main.isNurse));
            else
                main.getJSON_DATA( Str_queries.getMEDICAL_INSTRACTIONS_NEW(cols, patientID));
        }

    }






    public void getDoctorAndOtherInfo(JSONArray results){
        try {

            main.docNurseValues = new HashMap<>();
            newList =  iatrikesOdigiesLista();


            if (main.weHaveData) {

                String doctorName = results.getJSONObject(0).getString("doctorName");
                String month = results.getJSONObject(0).getString("Month");
                String yearName = results.getJSONObject(0).getString("yearName");
                main.id = results.getJSONObject(0).getString("ID");



                main.setValuesTolistaAdaptor(null, newList);


                if (results.getJSONObject(0).has("agg_prospelasi")) {
                    String ag_prosID = results.getJSONObject(0).getString("agg_prospelasi");
                    if (!ag_prosID.isEmpty()) {

                        for (int i = 0; i < listaAdaptor.size(); i++) {
                            if (listaAdaptor.get(i).col_name.equals("agg_prospelasi")) {
                                newList.get(i).setValue(getAggiakiProspelasi(Integer.parseInt(ag_prosID)));
                                break;
                            }
                        }
                    }
                }

                if (main.isNurse && !main.isDoctor) {
                    newList.get(0).setValue(month + " / " + yearName);
                    newList.get(1).setValue(doctorName);

                }
                else if (main.isDoctor){
                    newList.get(2).setValue(doctorName);

                }


                adapter.updateLista(newList);

            }

            else
                adapter.updateLista(newList);




            main.alertDialog.dismiss();

            main.weHaveData = false; //gia na min epireastoun ta alla fragment
        }

        catch (Exception e){

            e.printStackTrace();
        }
    }




    public static String getAggiakiProspelasi(int id){
        switch (id) {
            case 1: return "AVF";
            case 2: return  "AVF ΑΡ";
            case 3: return  "AVF ΔΕ";
            case 4: return  "AVG";
            case 5: return  "AVG ΑΡ";
            case 6: return  "AVG ΔΕ";
            case 7: return  "ΚΑΘΕΤΗΡΑΣ";
            case 8: return  "ΚΕΝΤΙΚΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΜΟΝΙΜΟΣ";
            case 9: return  "ΚΕΝΤΡΙΚΟΣ ΚΑΘΕΤΗΡΑΣ ΔΕ";
            case 10: return  "ΚΕΝΤΡΙΚΟΣ ΚΑΘΕΤΗΡΑΣ ΥΠΟΚΛΕΙΔΙΟΣ ΔΕ";
            case 11: return  "ΜOΣΧΕΥΜΑ";
            case 12: return  "ΜOΣΧΕΥΜΑ ΑΡ";
            case 13: return  "ΜΟΝΙΜΟΣ ΚΑΘΕΤΗΡΑΣ";
            case 14: return  "ΜΟΝΙΜΟΣ ΚΑΘΕΤΗΡΑΣ ΣΦΑΓΙΤΙΚΟΣ ΔΕ";
            case 15: return  "ΜΟΝΙΜΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ";
            case 16: return  "ΜΟΝΙΜΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΑΡ";
            case 17: return  "ΜΟΝΙΜΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΔΕ";
            case 18: return  "ΜΟΣΧΕΥΜΑ ΔΕ";
            case 19: return  "ΠΡΟΣΩΡΙΝΟΣ ΚΑΘΕΤΗΡΑΣ";
            case 20: return  "ΠΡΟΣΩΡΙΝΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΔΕ";
            case 21: return  "ΦΙΣΤΟΥΛΑ";

            default: return "";

        }

    }




    public void update() {
        if (!main.isDoctor) {
            Toast.makeText(main, "Μόνο οι ιατροί έχουν δικαίωμα για καταχώρηση και αλλαγή", Toast.LENGTH_SHORT).show();
            return;
        }



        main.alertDialog.show();



        AsyncTaskUpdate_JSON task = null;

        String code = Utils.getfirstPartSplitCommaString(main.bd.patientsTV.getText().toString());
        main.transgroupID =  main.getTransgroupIDUsingCode.get(code);

        main.setValuesTo_valuesJSON(null,listaAdaptor,valuesGiaApothikeusi);
        if (main.isDoctor) {
            int pos = namesGiaApothikeusi.indexOf("doctorid");
            valuesGiaApothikeusi.set(pos,Utils.getLinkDoctorID(main));
        }

        namesGiaApothikeusi.add("patientID");
        valuesGiaApothikeusi.add(main.getPatientID());




        if (main.id == null || main.id.equals(""))
            task = new AsyncTaskUpdate_JSON(main, main.transgroupID, table,
                    namesGiaApothikeusi, Utils.replaceTrueOrFalse(valuesGiaApothikeusi), null);
        else
            task = new AsyncTaskUpdate_JSON(main, main.id, main.transgroupID, table,
                    namesGiaApothikeusi, Utils.replaceTrueOrFalse(valuesGiaApothikeusi), null);


        task.listener = new AsyncGetUpdate_JSON() {
            @Override
            public void update_JSON(String str) {

                Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                main.alertDialog.dismiss();
                getMedicalInsructions(false);

            }

            @Override
            public void getIDofInsert(String id) {

            }
        };
        task.execute();
    }







    private ArrayList<ItemsRV> iatrikesOdigiesLista(){
        if (main == null)
            return new ArrayList<>();

        switch (main.custID){
            case Customers.CUSTID_FRONTIS:
            case Customers.CUSTID_FRONTIS_2:
                return Frontis.getMedicalInsLista(main.isDoctor);

            case Customers.CUSTID_KYANOS_STAVROS_MTN_PATRA:
                return KianousStavros.getMedicalInsLista(true,main.isDoctor);

            case Customers.CUSTID_MEDITERRANEO:
                return InfoSpecificLists.getMedicalInsLista();

            default:
                return InfoSpecificLists.getMedicalInsLista();



        }

    }











}
