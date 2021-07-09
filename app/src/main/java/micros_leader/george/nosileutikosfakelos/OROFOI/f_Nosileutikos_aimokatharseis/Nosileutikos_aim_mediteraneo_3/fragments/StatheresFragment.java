package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MetriseisStatheresRecyclerView;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.customers.Frontis;
import micros_leader.george.nosileutikosfakelos.databinding.FragmentStatheresBinding;

import static micros_leader.george.nosileutikosfakelos.BasicActivity.isHeader;
import static micros_leader.george.nosileutikosfakelos.Utils.convertObjToString;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatheresFragment extends Fragment {


    private View view;
    private MainActivity_Aim main;
    private String  patientid,companyID,table,ipefIatrosVardiasID = "" ,userID;
    private MetriseisStatheresRecyclerView adapter;
    public  ArrayList<ItemsRV> listaAdaptor;
    private ArrayList <String> namesGiaApothikeusi,oldValuesLista,valuesGiaApothikeusi ;
    private String id;
    private SearchableSpinner docSP;
    private ArrayList<String> doctorsLista = new ArrayList<>();
    private Map<Integer, String> doctorsHashMap = new HashMap<>();
    private ArrayList <String> newList_oldValues ;
    private FragmentStatheresBinding bd;

    public StatheresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bd = FragmentStatheresBinding.inflate(inflater, container, false);
        view = bd.getRoot();
        main = (MainActivity_Aim) getActivity();

        if (main != null) {

            companyID = Utils.getcompanyID(main);
            if (!main.isNurse)
                bd.updateButton.setVisibility(View.INVISIBLE);


            oldValuesLista = new ArrayList<>();

            for (int i = 0; i<statheresLista().size(); i++ )
                oldValuesLista.add("");

            adapter = new MetriseisStatheresRecyclerView(main,statheresLista(),oldValuesLista,true);
            listaAdaptor = adapter.result;
            namesGiaApothikeusi = new ArrayList<>();
            valuesGiaApothikeusi = new ArrayList<>();
            main.getAll_col_names(statheresLista(),namesGiaApothikeusi);

            docSP = view.findViewById(R.id.valueTV);
            docSP.setTitle("Επιλογή νεφρολόγου");
            docSP.setPositiveButton("OK");


            bd.statheresMetriseisRV.setItemViewCacheSize(30);
            bd.statheresMetriseisRV.setHasFixedSize(true);
            bd.statheresMetriseisRV.setNestedScrollingEnabled(false);
            bd.statheresMetriseisRV.setAdapter(adapter);

            GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
            bd.statheresMetriseisRV.setLayoutManager(manager);
            bd.updateButton.setOnClickListener(v -> update());

            getOldTeleutaiesMetriseis();

            allergiesListener();

        }

        return view;
    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bd = null;
    }


    private void allergiesListener(){

        bd.allergiesBT.setOnClickListener(v -> {
            main.alertDialog.show();

            BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(main);
            View parentView = getLayoutInflater().inflate(R.layout.item_detail,null);
            bottomSheerDialog.setContentView(parentView);

            final TextView detailTV = parentView.findViewById(R.id.item_detail);

            String code = Utils.getfirstPartSplitCommaString(main.bd.patientsTV.getText().toString());
            String patientID = main.getPatientIDUsingCode.get(code);

            String query  = "select top 1 allergies from Nursing_Medical_Instructions where patientID = " + patientID +
                    " order by year desc , month desc ,id desc";
            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2(query, main);
            task.listener = results -> {

                if (results != null && !results.getJSONObject(0).has("status")){
                    JSONObject n = results.getJSONObject(0);
                    detailTV.setText(n.getString("allergies"));

                    if (detailTV.getText().toString().trim().equals(""))
                        detailTV.setText("Δεν υπάρχουν δεδομένα για τον συγκεκριμένο ασθενή");
                }

                else
                    Toast.makeText(main, R.string.no_data, Toast.LENGTH_SHORT).show();


                main.alertDialog.dismiss();
            };

            task.execute();


            bottomSheerDialog.show();

        });
    }




    public void update(){

        //bd.updateButton.startAnimation();
        main.alertDialog.show();
        AsyncTaskUpdate_JSON task;

                String code = Utils.getfirstPartSplitCommaString(main.bd.patientsTV.getText().toString());
                main.transgroupID =  main.getTransgroupIDUsingCode.get(code);

        main.setValuesTo_valuesJSON(null,listaAdaptor,valuesGiaApothikeusi);

        //APOKTISI ID OF SELECTED DOCTOR VARDIAS
        int key = 0;
        for (Map.Entry<Integer, String> e : doctorsHashMap.entrySet()) {
            String value = e.getValue();
            if (docSP.getSelectedItem() != null && value.equals(docSP.getSelectedItem().toString())){
                 key = e.getKey();
                valuesGiaApothikeusi.add(String.valueOf(key));
            }

        }
        namesGiaApothikeusi.add("ipefthinos_iatros_vardias");
        valuesGiaApothikeusi.add(ipefIatrosVardiasID);




        if (id == null || id.equals(""))
            task = new AsyncTaskUpdate_JSON(main, main.transgroupID, "Nursing_Hemodialysis_initial2_MEDIT",
                    namesGiaApothikeusi, Utils.replaceTrueOrFalse(valuesGiaApothikeusi), null);
        else
            task = new AsyncTaskUpdate_JSON(main, id, main.transgroupID, "Nursing_Hemodialysis_initial2_MEDIT",
                    namesGiaApothikeusi, Utils.replaceTrueOrFalse(valuesGiaApothikeusi), null);

        task.setEkseresiPedion(new String []{"ksiro_varos","teliko_arxiko_varos","teliko_varos_exodou",
                "diafora_varous","final_weight","med_instr_additional_weight","target_UF"});

        final String strKey = String.valueOf(key);
        task.listener = new AsyncGetUpdate_JSON() {
            @Override
            public void update_JSON(String str) {
                if (str.equals(getString(R.string.successful_update))) {
                    namesGiaApothikeusi.remove("ipefthinos_iatros_vardias");
                    valuesGiaApothikeusi.remove(strKey);

                  //  Utils.timeHandlerDoneButton(bd.updateButton, main);
                    getOldTeleutaiesMetriseis();
                    getCurrentStableMeasurement();  // ΤΟ ΞΑΝΑΤΡΕΧΩ ΕΠΕΙΔΗ ΔΕΝ ΕΧΩ ΤΟ ID

                } else {
                    namesGiaApothikeusi.remove("ipefthinos_iatros_vardias");
                    valuesGiaApothikeusi.remove(strKey);

                  //  Utils.timeHandlerErrorButton(bd.updateButton, main);
                   // Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                main.alertDialog.dismiss();

            }

            @Override
            public void getIDofInsert(String id) {

            }
        };
        task.execute();
    }







    public void getOldTeleutaiesMetriseis() {

        if (Utils.isNetworkAvailable(main)) {

            String code = Utils.getfirstPartSplitCommaString(main.bd.patientsTV.getText().toString());
            String patientID = main.getPatientIDUsingCode.get(code);

            String query = Str_queries.getOldteleytaiesStatheresMetriseis(patientID,main.custID);

            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = main;
            task.query = query;
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {
                    newList_oldValues = new ArrayList<>();

                    if (results != null) {

                        if (!results.getJSONObject(0).has("status")) {

                            if (!main.isFinishing() && main.alertDialog != null)
                                main.alertDialog.show();

                            JSONObject tel_metr = results.getJSONObject(0);

                            for (int i = 0; i < namesGiaApothikeusi.size(); i++)
                                newList_oldValues.add(Utils.convertObjToString(tel_metr.get(namesGiaApothikeusi.get(i))));

                            if (tel_metr != null && tel_metr.has("docName"))
                                bd.oldDocTV.setText(convertObjToString(tel_metr.get("docName")));

                        }

                        else{
                            for (int i = 0; i < namesGiaApothikeusi.size(); i++)
                                newList_oldValues.add("");

                        }

                    }
                 //   adapter.updateOldLista(newList);
                    getCurrentStableMeasurement();
                  //  adapter.notifyDataSetChanged();
                    if (!main.isFinishing() && main.alertDialog != null)
                        main.alertDialog.dismiss();
                }
            };

            task.execute();
            }
        }



    public void getCurrentStableMeasurement() {

        if (Utils.isNetworkAvailable(main)) {
            String code = Utils.getfirstPartSplitCommaString(main.bd.patientsTV.getText().toString());
            main.transgroupID =  main.getTransgroupIDUsingCode.get(code);

            String patientID = main.getPatientIDUsingCode.get(code);
            String query= Str_queries.getCURRENT_METRISEIS_MAZI_ME_MEDICAL_INSTRUCTIONS(main.transgroupID,patientID,main.custID);

            final AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = main;
            task.query = query;
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {

                    ArrayList <ItemsRV> newList = statheresLista();
                    if (results != null) {
                        JSONObject metrisi = results.getJSONObject(0);

                        if (!metrisi.has("status")) { // TO STATUS EINAI OTAN DEN IPARXOUN STOIXEIA

                            valuesGiaApothikeusi.clear();

                            if (main.alertDialog != null && !main.alertDialog.isShowing())
                                if (!main.isFinishing())
                                     main.alertDialog.show();

                            JSONObject tel_metr = results.getJSONObject(0);
                            for (int i = 0; i < namesGiaApothikeusi.size(); i++) {
                                if (tel_metr.has(namesGiaApothikeusi.get(i)))
                                     valuesGiaApothikeusi.add(Utils.convertObjToString(tel_metr.get(namesGiaApothikeusi.get(i))));
                                else
                                    valuesGiaApothikeusi.add("");

                            }


                              //  main.setValuesTolistaAdaptor(null,listaAdaptor,valuesGiaApothikeusi);

                                main.setValuesTolistaAdaptor(null,newList,valuesGiaApothikeusi,tel_metr);

                            id = Utils.convertObjToString(tel_metr.opt("ID"));
                            if (bd != null) {
                                bd.theraponIatrosTV.setText("Θεράπων ιατρός: \n " + convertObjToString(metrisi.opt("therapon_iatros")));
                                ipefIatrosVardiasID = convertObjToString(metrisi.opt("ipefthinos_iatros_vardias"));
                                bd.userTV.setText("Χρήστες καταχώρησης \n " + metrisi.optString("username"));
                            }
                            main.alertDialog.dismiss();

                        }
                        else {
                            if (main.custID == Customers.CUSTID_FRONTIS  || main.custID == Customers.CUSTID_FRONTIS_2)
                                getXiroAndProthetoVaros(patientID);
                            id = null;
                        }

                    }
                    else {
                        if (main.custID == Customers.CUSTID_FRONTIS  || main.custID == Customers.CUSTID_FRONTIS_2)
                            getXiroAndProthetoVaros(patientID);
                        id = null;

                    }
                   getDoctors();



                    adapter.updateOldLista(newList_oldValues);
                    for (int i=0; i<listaAdaptor.size(); i++)
                        if (listaAdaptor.get(i).getLista() != null &&  newList.get(i).getLista() == null)
                            newList.get(i).setLista(listaAdaptor.get(i).getLista());

                    adapter.updateLista(newList);



                    main.alertDialog.dismiss();
                }
            };
            task.execute();

        }

    }



    private void getXiroAndProthetoVaros(String patientID){
        String q = "select top 1 ksiro_varos,additional_weight from Nursing_Medical_Instructions where patientid = "  + patientID + " order by year desc , month desc";
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.ctx = main;
        task.query = q;
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {
                if (results != null) {
                    JSONObject metrisi = results.getJSONObject(0);

                    if (!metrisi.has("status")) { // TO STATUS EINAI OTAN DEN IPARXOUN STOIXEIA
                        ArrayList <ItemsRV> newList = statheresLista();

                        String xiro_varos = Utils.convertObjToString(metrisi.get("ksiro_varos"));
                        String additional_weight = Utils.convertObjToString(metrisi.get("ksiro_varos"));
                        for (int i=0; i<newList.size(); i++){
                            if (newList.get(i).getCol_name().equals("ksiro_varos")){
                                newList.get(i).setValue(xiro_varos);
                            }
                            else if (newList.get(i).getCol_name().equals("additional_weight")){
                                newList.get(i).setValue(additional_weight);
                                break;  // ΤΟ ΣΤΑΜΑΤΑΩ ΣΤΟ additional_weight ΜΕ ΤΗΝ ΠΡΟΥΠΟΘΕΣΗ ΠΩς ΕΙΝΑΙ ΜΕΤΑ ΤΟ ΞΗΡΟ ΒΑΡΟΣ ΣΤΗ ΛΙΣΤΑ
                            }

                        }
                        adapter.updateLista(newList);

                    }
                }
            }
        };
        task.execute();
    }




    private void getDoctors() {

        if (Utils.isNetworkAvailable(main)) {


            doctorsLista.clear();
            doctorsHashMap.clear();

            String query =  Str_queries.getNefrologous(companyID);

            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = main;
            task.query = query;
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {

                    if (results != null) {

                        doctorsLista.clear();
                        doctorsLista.add("");
                        doctorsHashMap.put(0, "");


                        for (int i = 0; i < results.length(); i++) {

                            JSONObject doctors = results.getJSONObject(i);
                            int id = doctors.optInt("id");
                            String name = doctors.optString("Name");


                            doctorsHashMap.put(id, name);
                            doctorsLista.add(name);

                            //Toast.makeText(main, id + " " + name,  Toast.LENGTH_SHORT).show();

                        }


                        ArrayAdapter dataAdapter = new ArrayAdapter<>(main,
                                R.layout.spinner_layout2, doctorsLista);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        docSP.setAdapter(dataAdapter);


                        if (ipefIatrosVardiasID != null && !ipefIatrosVardiasID.equals("")) {
                            int spinnerPosition = dataAdapter.getPosition(doctorsHashMap.get(Integer.parseInt(ipefIatrosVardiasID)));
                            docSP.setSelection(spinnerPosition);
                        }

                    }
                }
            };

            task.execute();

        }
    }


    private ArrayList<ItemsRV> statheresLista(){
       switch (main.custID){
           case Customers.CUSTID_FRONTIS:
           case Customers.CUSTID_FRONTIS_2:
               return Frontis.getStathersMetriseisLista();

           case Customers.CUSTID_MEDITERRANEO:
               return InfoSpecificLists.getStathersMetriseisLista();

           default:
               return InfoSpecificLists.getStathersMetriseisLista();



       }

    }



}
