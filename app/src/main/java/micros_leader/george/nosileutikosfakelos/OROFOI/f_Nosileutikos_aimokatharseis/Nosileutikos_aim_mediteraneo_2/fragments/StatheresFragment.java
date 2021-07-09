package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_2.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors.DialogFragmentKataxorisiVelonon;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_2.MainActivity_Aim;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_2.MetriseisStatheresRecyclerView;

import static micros_leader.george.nosileutikosfakelos.BasicActivity.isHeader;
import static micros_leader.george.nosileutikosfakelos.Utils.convertObjToString;
import static micros_leader.george.nosileutikosfakelos.Utils.getCustomerID;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatheresFragment extends Fragment {


    private View view;
    private MainActivity_Aim main;
    private String  patientid,companyID,table,ipefIatrosVardiasID = "";
    private RecyclerView statheresRV;
    private MetriseisStatheresRecyclerView adapter;
    public  ArrayList<ItemsRV> listaAdaptor;
    private ArrayList <String> namesGiaApothikeusi,oldValuesLista,valuesGiaApothikeusi ;
    private CircularProgressButton updateButton;
    private String id;
    private TextView theraponIatrosTV, oldDocTV;
    private SearchableSpinner docSP;
    private Button velonesBT;
    private ArrayList<String> doctorsLista = new ArrayList<>();
    private Map<Integer, String> doctorsHashMap = new HashMap<>();


    public StatheresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_statheres, container, false);

        main = (MainActivity_Aim) getActivity();

//        Runnable r = new Runnable() {
//            public void run() {
//            }
//        };
//
//        new Thread(r).start();
        getOldTeleutaiesMetriseis();


//        Runnable run = new Runnable() {
//            public void run() {
//              //  getCurrentStableMeasurement();
//            }
//        };
//
//        new Thread(run).start();

        companyID = Utils.getcompanyID(main);
        oldValuesLista = new ArrayList<>();

        for (int i = 0; i<InfoSpecificLists.getStathersMetriseisLista().size(); i++ )
            oldValuesLista.add("");

        theraponIatrosTV = view.findViewById(R.id.theraponIatrosTV);
        adapter = new MetriseisStatheresRecyclerView(main, InfoSpecificLists.getStathersMetriseisLista(),oldValuesLista,true);
        listaAdaptor = adapter.result;
        namesGiaApothikeusi = new ArrayList<>();
        namesGiaApothikeusi = new ArrayList<>();
        valuesGiaApothikeusi = new ArrayList<>();
        main.getAll_col_names(InfoSpecificLists.getStathersMetriseisLista(),namesGiaApothikeusi);

        oldDocTV = view.findViewById(R.id.oldDocTV);
        docSP = view.findViewById(R.id.valueTV);
        docSP.setTitle("Επιλογή νεφρολόγου");
        docSP.setPositiveButton("OK");
        velonesBT = view.findViewById(R.id.kataxorisiVelononBT);
        velonesBT.setVisibility(View.GONE);
        statheresRV = view.findViewById(R.id.sinexeisMetriseisRV);

        statheresRV.setItemViewCacheSize(30);
        statheresRV.setHasFixedSize(true);
        statheresRV.setNestedScrollingEnabled(false);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                boolean isHeader = isHeader(position,null);
                if (isHeader)
                    return  2;
                else
                    return  1;

            }
        });
        statheresRV.setLayoutManager(manager);
//        if (adapter != null)
//            statheresRV.setAdapter(adapter);

        updateButton = view.findViewById(R.id.updateButton);


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update();
            }
        });


        //velonesListener();
        return view;
    }


    private void update(){

        updateButton.startAnimation();

        AsyncTaskUpdate_JSON task;

                String code = Utils.getfirstPartSplitCommaString(main.search_TV.getText().toString());
                main.transgroupID =  main.getTransgroupIDUsingCode.get(code);

        main.setValuesTo_valuesJSON(null,listaAdaptor,valuesGiaApothikeusi);

        //APOKTISI ID OF SELECTED DOCTOR VARDIAS
        int key = 0;
        for (Map.Entry<Integer, String> e : doctorsHashMap.entrySet()) {
            String value = e.getValue();
            if (value.equals(docSP.getSelectedItem().toString())){
                 key = e.getKey();
                valuesGiaApothikeusi.add(String.valueOf(key));
              //  Toast.makeText(main, docSP.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

        }
        namesGiaApothikeusi.add("ipefthinos_iatros_vardias");
        valuesGiaApothikeusi.add(ipefIatrosVardiasID);
      //  Toast.makeText(main, ipefIatrosVardiasID, Toast.LENGTH_SHORT).show();




        if (id == null || id.equals(""))
            task = new AsyncTaskUpdate_JSON(main, main.transgroupID, "Nursing_Hemodialysis_initial2_MEDIT",
                    namesGiaApothikeusi, Utils.replaceTrueOrFalse(valuesGiaApothikeusi), null);
        else
            task = new AsyncTaskUpdate_JSON(main, id, main.transgroupID, "Nursing_Hemodialysis_initial2_MEDIT",
                    namesGiaApothikeusi, Utils.replaceTrueOrFalse(valuesGiaApothikeusi), null);

        task.setEkseresiPedion(new String []{"diafora_varous"});

        final String strKey = String.valueOf(key);
        task.listener = new AsyncGetUpdate_JSON() {
            @Override
            public void update_JSON(String str) {
                if (str.equals(getString(R.string.successful_update))) {
                    namesGiaApothikeusi.remove("ipefthinos_iatros_vardias");
                    valuesGiaApothikeusi.remove(strKey);

                    Utils.timeHandlerDoneButton(updateButton, main);
                    getOldTeleutaiesMetriseis();
                    getCurrentStableMeasurement();  // ΤΟ ΞΑΝΑΤΡΕΧΩ ΕΠΕΙΔΗ ΔΕΝ ΕΧΩ ΤΟ ID

                } else {
                    namesGiaApothikeusi.remove("ipefthinos_iatros_vardias");
                    valuesGiaApothikeusi.remove(strKey);

                    Utils.timeHandlerErrorButton(updateButton, main);
                    Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void getIDofInsert(String id) {

            }
        };
        task.execute();
    }



    public void velonesListener(){

        velonesBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id == null || id.equals(""))
                    Toast.makeText(main, "Δεν έχει γίνει ακόμα κάποια καταχώρηση μετρήσεων", Toast.LENGTH_SHORT).show();
                else {

                    DialogFragmentKataxorisiVelonon df = new DialogFragmentKataxorisiVelonon();
                    Bundle putextra = new Bundle();
                    putextra.putString("id", id);
                    df.setArguments(putextra);
                    df.setTargetFragment(StatheresFragment.this,1337);
                    if (getFragmentManager() != null)
                        df.show(getFragmentManager(),"dialog");
                    // df.show(( act).getSupportFragmentManager(), "Dialog");
                }

            }
        });

    }




    public void getOldTeleutaiesMetriseis() {

        if (Utils.isNetworkAvailable(main)) {

            String code = Utils.getfirstPartSplitCommaString(main.search_TV.getText().toString());
            String patientID = main.getPatientIDUsingCode.get(code);

            String query = Str_queries.getOldteleytaiesStatheresMetriseis(patientID,Utils.getCustomerID(getActivity()));

            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = view.getContext();
            task.query = query;
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {

                    if (results != null) {

                        if (!results.getJSONObject(0).has("status")) {

                            oldValuesLista.clear();

                            if ( main.alertDialog != null)
                                 main.alertDialog.show();

                            JSONObject tel_metr = results.getJSONObject(0);

                            if (namesGiaApothikeusi != null) {
                                for (int i = 0; i < namesGiaApothikeusi.size(); i++) {
                                    oldValuesLista.add(Utils.convertObjToString(tel_metr.get(namesGiaApothikeusi.get(i))));
                                }
                                oldDocTV.setText(convertObjToString(tel_metr.get("docName")));
                            }

                        }

                    }
                  //  adapter.updateLista(newList);
                    getCurrentStableMeasurement();
                  //  adapter.notifyDataSetChanged();
                    if ( main.alertDialog != null)
                        main.alertDialog.dismiss();
                }
            };

            task.execute();
            }
        }



    public void getCurrentStableMeasurement() {

        if (Utils.isNetworkAvailable(main)) {
            String code = Utils.getfirstPartSplitCommaString(main.search_TV.getText().toString());
            main.transgroupID =  main.getTransgroupIDUsingCode.get(code);
            String query= Str_queries.getCURRENT_METRISEIS_MAZI_ME_MEDICAL_INSTRUCTIONS(main.transgroupID,patientid,Utils.getCustomerID(getActivity()));

            final AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = main;
            task.query = query;
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {

                    ArrayList <ItemsRV> newList = InfoSpecificLists.getStathersMetriseisLista();


                    if (results != null) {
                        JSONObject metrisi = results.getJSONObject(0);

                        if (!metrisi.has("status")) { // TO STATUS EINAI OTAN DEN IPARXOUN STOIXEIA

                            valuesGiaApothikeusi.clear();

                            main.alertDialog.show();

                            JSONObject tel_metr = results.getJSONObject(0);
                            for (int i = 0; i < namesGiaApothikeusi.size(); i++) {
                                valuesGiaApothikeusi.add(Utils.convertObjToString(tel_metr.get(namesGiaApothikeusi.get(i))));

                            }
                                main.setValuesTolistaAdaptor(null,listaAdaptor,valuesGiaApothikeusi,null);

                             //   main.setValuesTolistaAdaptor(null,newList,valuesGiaApothikeusi);

                            id = Utils.convertObjToString(tel_metr.get("ID"));
                            theraponIatrosTV.setText("Θεράπων ιατρός: \n " + convertObjToString(metrisi.get("therapon_iatros")));
                            ipefIatrosVardiasID = convertObjToString(metrisi.get("ipefthinos_iatros_vardias"));

                            main.alertDialog.dismiss();

                        }
                        else
                            id = null;

                    }
                    else
                        id = null;

                    getDoctors();

                    if (statheresRV.getAdapter() == null)
                        statheresRV.setAdapter(adapter);
                    else
                        //adapter.updateLista(newList);
                        adapter.notifyDataSetChanged();
                    main.alertDialog.dismiss();
                }
            };
            task.execute();

        }

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
                            int id = doctors.getInt("id");
                            String name = doctors.getString("Name");


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

}
