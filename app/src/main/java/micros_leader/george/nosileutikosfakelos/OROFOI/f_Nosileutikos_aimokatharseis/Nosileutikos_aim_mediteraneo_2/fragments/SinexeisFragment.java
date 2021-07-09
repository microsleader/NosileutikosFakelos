package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_2.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_2.MainActivity_Aim;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_2.MetriseisSinexeisRecyclerView;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static micros_leader.george.nosileutikosfakelos.BasicActivity.isHeader;

/**
 * A simple {@link Fragment} subclass.
 */
public class SinexeisFragment extends Fragment {

    private String id, patientid,companyID,table;
    private View view;
    private RecyclerView sinexeisMetriseisRV;
    private MainActivity_Aim main;
    private MetriseisSinexeisRecyclerView adapter;
    public  ArrayList<ItemsRV> listaAdaptor;
    private ArrayList <String> namesGiaApothikeusi,oldValuesLista,valuesGiaApothikeusi ;
    private CircularProgressButton updateButton;


    public SinexeisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_sinexeis, container, false);

        main = (MainActivity_Aim) getActivity();
        companyID = Utils.getcompanyID(main);
        oldValuesLista = new ArrayList<>();

        Runnable r = new Runnable() {
            public void run() {
                getTeleutaiaMetrisi();
            }
        };

        new Thread(r).start();

        adapter = new MetriseisSinexeisRecyclerView(main, InfoSpecificLists.getSinexeisMetriseisLista(),oldValuesLista,true);
        listaAdaptor = adapter.result;
        namesGiaApothikeusi = new ArrayList<>();
        namesGiaApothikeusi = new ArrayList<>();
        valuesGiaApothikeusi = new ArrayList<>();
        main.getAll_col_names(InfoSpecificLists.getSinexeisMetriseisLista(),namesGiaApothikeusi);
        sinexeisMetriseisRV = view.findViewById(R.id.sinexeisMetriseisRV);
        sinexeisMetriseisRV.setItemViewCacheSize(30);
        sinexeisMetriseisRV.setHasFixedSize(true);
        sinexeisMetriseisRV.setDrawingCacheEnabled(true);
        sinexeisMetriseisRV.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);



        //sinexeisMetriseisRV.setNestedScrollingEnabled(false);
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
        sinexeisMetriseisRV.setLayoutManager(manager);
//        if (adapter != null)
//            sinexeisMetriseisRV.setAdapter(adapter);

        updateButton = view.findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update();
            }
        });




        return view;
    }

    private void update() {

        InputMethodManager imm = (InputMethodManager) main.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(Objects.requireNonNull(Objects.requireNonNull(getActivity()).getCurrentFocus()).getWindowToken(), 0);

        updateButton.startAnimation();

        AsyncTaskUpdate_JSON task;

        String code = Utils.getfirstPartSplitCommaString(main.search_TV.getText().toString());
        main.transgroupID =  main.getTransgroupIDUsingCode.get(code);

        main.setValuesTo_valuesJSON(null,listaAdaptor,valuesGiaApothikeusi);

       valuesGiaApothikeusi.set(0,Utils.convertDateTomilliseconds(Utils.getCurrentDate()));

      //  Toast.makeText(main, id, Toast.LENGTH_SHORT).show();

        if (id == null || id.equals(""))
            task = new AsyncTaskUpdate_JSON(main, main.transgroupID, "Nursing_Hemodialysis_2_MEDIT",
                    namesGiaApothikeusi, Utils.replaceTrueOrFalse(valuesGiaApothikeusi), null);
        else
            task = new AsyncTaskUpdate_JSON(main, id, main.transgroupID, "Nursing_Hemodialysis_2_MEDIT",
                    namesGiaApothikeusi, Utils.replaceTrueOrFalse(valuesGiaApothikeusi), null);


        task.listener = new AsyncGetUpdate_JSON() {
            @Override
            public void update_JSON(String str) {
                if (str.equals(getString(R.string.successful_update))) {

                    Utils.timeHandlerDoneButton(updateButton, main);
                    getTeleutaiaMetrisi();
                    getCurrentContinuousMeasurement();  // ΤΟ ΞΑΝΑΤΡΕΧΩ ΕΠΕΙΔΗ ΔΕΝ ΕΧΩ ΤΟ ID

                } else {

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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

//        if (isVisibleToUser)
//            Toast.makeText(main, id + "    +1", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();

        id = null;
      //  Toast.makeText(main, id + "    +2", Toast.LENGTH_SHORT).show();

    }

    public void getTeleutaiaMetrisi() {

        if (Utils.isNetworkAvailable2(main)) {

            String code = Utils.getfirstPartSplitCommaString(main.search_TV.getText().toString());
            String patientID = main.getPatientIDUsingCode.get(code);

            String query = " select top 1 ni.* , " +
                    " (select max(m.VitB)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as VitB," +
                    " (select max(m.Carnitine)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as Carnitine, " +
                    " (select max(m.Alfacalcidol)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as Alfacalcidol," +
                    "  (select max(m.zeta)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as zeta," +
                    "  (select max(m.alpha)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as alpha," +
                    "  (select max(m.darbepoetin)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as darbepoetin," +
                    "  (select max(m.Paracalcitol)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as Paracalcitol " +

                    " from Nursing_Hemodialysis_2_MEDIT  ni " +
                    " where ni.patientid = " + patientID +

                    " order by date desc";

            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = main;
            task.query = query;
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {
                    if (results != null) {

                    if (!results.getJSONObject(0).has("status")) {
                        oldValuesLista.clear();

                        main.alertDialog.show();

                        JSONObject tel_metr = results.getJSONObject(0);
                        for (int i = 0; i < namesGiaApothikeusi.size(); i++) {
                            String col_name = namesGiaApothikeusi.get(i);
                            oldValuesLista.add(Utils.convertObjToString(tel_metr.get(col_name)));

                        }
                        for (int i = 0; i < listaAdaptor.size(); i++) {
                            String title = listaAdaptor.get(i).titleID;

                            if (title.equals("L - carnittine"))
                                listaAdaptor.get(i).setValue(Utils.convertObjToString(tel_metr.get("Carnitine")));
                            if (title.equals("Alphacalcidol"))
                                listaAdaptor.get(i).setValue(Utils.convertObjToString(tel_metr.get("Alfacalcidol")));
                            if (title.equals("Epoetin alpha"))
                                listaAdaptor.get(i).setValue(Utils.convertObjToString(tel_metr.get("alpha")));
                            if (title.equals("Epoetin zeta"))
                                listaAdaptor.get(i).setValue( Utils.convertObjToString(tel_metr.get("zeta")));
                            if (title.equals("Darbepoetin"))
                                listaAdaptor.get(i).setValue( Utils.convertObjToString(tel_metr.get("darbepoetin")));
                            if (title.equals("Paricalcitol"))
                                listaAdaptor.get(i).setValue( Utils.convertObjToString(tel_metr.get("Paracalcitol")));
                        }

                    }
                        //else
                        // clearOldTexts();
                    }

                    if (sinexeisMetriseisRV.getAdapter() == null)
                            sinexeisMetriseisRV.setAdapter(adapter);
                    else
                         adapter.notifyDataSetChanged();

                    main.alertDialog.dismiss();
                }
            };


            task.execute();
        }

    }




    private void getCurrentContinuousMeasurement(){

        if (Utils.isNetworkAvailable(main)) {

            String query =
                    " select * from Nursing_Hemodialysis_2_MEDIT where TransGroupid =  " + main.transgroupID +
                            " order by date desc";


            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = main;
            task.query = query;
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {

                    JSONObject metrisi = results.getJSONObject(0);
                    if (!metrisi.has("status")) // TO STATUS EINAI OTAN DEN IPARXOUN STOIXEIA
                        id = String.valueOf(metrisi.getInt("ID"));
                }

            };
            task.execute();

        } else
            Toast.makeText(main, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
    }






}
