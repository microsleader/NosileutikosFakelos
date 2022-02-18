package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.IBinder;
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

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.GridLayoutManagerWrapper;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MetriseisSinexeisRecyclerView;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.customers.Frontis;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityTestBaseBinding;
import micros_leader.george.nosileutikosfakelos.databinding.FragmentSinexeisBinding;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static micros_leader.george.nosileutikosfakelos.BasicActivity.isHeader;

/**
 * A simple {@link Fragment} subclass.
 */
public class SinexeisFragment extends Fragment {

    private String id, patientid,companyID,table;
    private View view;
    private MainActivity_Aim main;
    private MetriseisSinexeisRecyclerView adapter;
    public  ArrayList<ItemsRV> listaAdaptor;
    private ArrayList <String> namesGiaApothikeusi,oldValuesLista,valuesGiaApothikeusi ;
    private FragmentSinexeisBinding bd;
    private ArrayList<ItemsRV> emptyList;

    public SinexeisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bd = FragmentSinexeisBinding.inflate(inflater, container, false);
        view = bd.getRoot();

        main = (MainActivity_Aim) getActivity();

        if (main != null) {
            companyID = Utils.getcompanyID(main);
            if (!main.isNurse)
                bd.updateButton.setVisibility(View.INVISIBLE);

        }
        oldValuesLista = new ArrayList<>();


        emptyList = sinexeisLista();
        adapter = new MetriseisSinexeisRecyclerView(main, sinexeisLista(),oldValuesLista,true);
        listaAdaptor = adapter.result;
        namesGiaApothikeusi = new ArrayList<>();
        valuesGiaApothikeusi = new ArrayList<>();
        main.getAll_col_names(emptyList,namesGiaApothikeusi);
        bd.sinexeisMetriseisRV.setItemViewCacheSize(30);
        bd.sinexeisMetriseisRV.setHasFixedSize(true);
        bd.sinexeisMetriseisRV.setDrawingCacheEnabled(true);
        bd.sinexeisMetriseisRV.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        GridLayoutManagerWrapper manager = new GridLayoutManagerWrapper(getContext(), 1);
        bd.sinexeisMetriseisRV.setLayoutManager(manager);
        bd.sinexeisMetriseisRV.setAdapter(adapter);

        bd.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update();
            }
        });


        getTeleutaiaMetrisi(true);
        newEntryListenerBT();

        return view;
    }

    private void newEntryListenerBT() {
        bd.newEntryBT.setOnClickListener(v -> { getTeleutaiaMetrisi(true);        });
    }


    public void update() {


        Utils.dismissKeyboard(main);
      //  bd.updateButton.startAnimation();

        main.alertDialog.show();
        AsyncTaskUpdate_JSON task;

        String code = Utils.getfirstPartSplitCommaString(main.bd.patientsTV.getText().toString());
        main.transgroupID =  main.getTransgroupIDUsingCode.get(code);
        main.patientID = main.getPatientIDUsingCode.get(code);


        main.setValuesTo_valuesJSON(null,listaAdaptor,valuesGiaApothikeusi);

        if (valuesGiaApothikeusi.get(0).isEmpty()) //date
            valuesGiaApothikeusi.set(0,Utils.convertDateTomilliseconds(Utils.getCurrentDate()));


        if (id == null || id.equals(""))
            task = new AsyncTaskUpdate_JSON(main, main.transgroupID, "Nursing_Hemodialysis_2_MEDIT",
                    namesGiaApothikeusi, Utils.replaceTrueOrFalse(valuesGiaApothikeusi), null);
        else
            task = new AsyncTaskUpdate_JSON(main, id, main.transgroupID, "Nursing_Hemodialysis_2_MEDIT",
                    namesGiaApothikeusi, Utils.replaceTrueOrFalse(valuesGiaApothikeusi), null);

        task.patientID = main.patientID;

        task.listener = new AsyncGetUpdate_JSON() {
            @Override
            public void update_JSON(String str) {
                if (str.equals(main.getResources().getString(R.string.successful_update))) {

                 //   Utils.timeHandlerDoneButton(bd.updateButton, main);
                    getTeleutaiaMetrisi(false);
                    getCurrentContinuousMeasurement();  // ΤΟ ΞΑΝΑΤΡΕΧΩ ΕΠΕΙΔΗ ΔΕΝ ΕΧΩ ΤΟ ID

                } else {

                 //   Utils.timeHandlerErrorButton(bd.updateButton, main);
                 //   Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                }
                if (main != null) {
                    Toast.makeText(main, str, Toast.LENGTH_SHORT).show();
                    main.alertDialog.dismiss();
                }
            }

            @Override
            public void getIDofInsert(String id) {

            }
        };
        task.execute();


    }



    @Override
    public void onResume() {
        super.onResume();

//        id = null;
//        adapter.updateLista(sinexeisLista());

    }

    public void getTeleutaiaMetrisi(boolean emptyMainList) {

        ArrayList<String> emptyList = new ArrayList<>();

        adapter.updateLista(emptyList);

        if (namesGiaApothikeusi != null ){
            for (int i = 0; i < namesGiaApothikeusi.size(); i++)
                emptyList.add("");
            adapter.updateOldLista(emptyList);
        }



        if (Utils.isNetworkAvailable2(main)) {

            String code = Utils.getfirstPartSplitCommaString(main.bd.patientsTV.getText().toString());
            String patientID = main.getPatientIDUsingCode.get(code);

            String query ;
            if (Customers.isFrontis(main.custID)){
                query = " select top 1 ni.* , " +
                        " (select max(m.VitB)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as VitB, \n" +
                        " (select max(m.Carnitine)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as Carnitine, \n" +
                        " (select max(m.Alfacalcidol)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as Alfacalcidol,\n" +
                        "  (select max(m.zeta)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as zeta,\n" +
                        "  (select max(m.alpha)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as alpha,\n" +
                        "  (select max(m.darbepoetin)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as darbepoetin,\n" +
                        "  (select max(m.Paracalcitol)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as Paracalcitol \n " +

                        " from Nursing_Hemodialysis_2_MEDIT  ni \n" +
                        " where ni.patientid = " + patientID +

                        " order by date desc ,  id desc \n";

            }

            else{
                query =  " \n" +
                        " DECLARE @CUR_DATE BIGINT\n" +
                        " SET @CUR_DATE = dbo.datetime_to_date( DBO.TIMETONUM( GETDATE()))\n" +
                        " \n" +
                        " select top 1 ni.*, \n" +
                        " --ΑΝ Ο ΓΙΑΤΡΟΣ ΕΧΕΙ ΔΩΣΕΙ ΣΥΝΕΔΡΙΕΣ ΤΟ ΚΟΙΤΑΖΩ ΑΠΟ ΕΚΕΙ \n" +
                        "--ΑΝ ΟΧΙ ΚΟΙΤΑΖΩ ΤΗΝ ΣΥΧΝΟΤΗΤΑ \n" +
                        "--ΜΕΤΑ ΑΥΤΟ ΠΟΥ ΕΧΩ ΤΟ ΣΥΓΚΡΙΝΩ ΜΕ ΤΙς ΤΕΛΕΥΤΑΙΕς ΣΥΝΕΔΡΙΕΣ (ΤΕΛΕΥΤΑΙΕΣ = ΣΥΝΕΔΡΙΕΣ Η ΣΥΧΝΟΤΗΤΑ ) ΑΝ ΤΑ ΕΧΕΙ ΕΚΤΕΛΕΣΕΙ Ο ΝΟΗΣΛΕΥΤΗς ΣΤΟΝ ΑΣΘΕΝΗ\n" +
                        "(select top 1 \n" +
                        "\n" +
                        "--1\n" +
                        "CASE WHEN ISNULL(m.eopetinSinedries,0) > 0 \n" +
                        "THEN  \n" +
                        "      CASE WHEN   epoDateIn <= @CUR_DATE AND epoDateOUT >= @CUR_DATE AND ISNULL(m.eopetinSinedries,0) >  dbo.get_last_used_meds(m.eopetinSinedries ,ni.PatientID,1, m.epoet_period) \n" +
                        "      THEN\n" +
                        "         isnull(dbo.nameitem(m.epoetinMedsID),'')  + CHAR(10)  +\n" +
                        "         'Δόση: ' + isnull(cast(epoetinDose as varchar),'') + ' ' + isnull(epoetinMM,'') +  CHAR(10) +\n" +
                        "         'Συνεδρίες: ' +   cast(isnull(m.eopetinSinedries, 0) as varchar )  \n" +
                        "      ELSE ''\n" +
                        "\n" +
                        "      END \n" +
                        "\n" +
                        "ELSE\n" +
                        "  CASE WHEN ISNULL(M.epoetinFRQ,0) > 0 AND  ISNULL(m.epoetinFRQ,0) >  dbo.get_last_used_meds(m.epoetinFRQ ,ni.PatientID,1, m.epoet_period) -- \n" +
                        "  THEN\n" +
                        "    isnull(dbo.nameitem(m.epoetinMedsID),'')  + CHAR(10)  +\n" +
                        "    'Δόση: ' + isnull(cast(epoetinDose as varchar),'') + ' ' + isnull(epoetinMM,'') +  CHAR(10) +\n" +
                        "    'Συχνότητα: ' +   cast(isnull(epoetinFRQ, 0)as varchar )  \n" +
                        "  ELSE ''\n" +
                        "  end\n" +
                        "END  \n" +
                        "\n" +
                        "from Nursing_Medical_Instructions m  \n" +
                        "left join Nursing_items_meds_frequency fr on fr.id = m.epoetinFRQ_id\n" +
                        "where m.PatientID=ni.PatientID  order by year desc, month desc , m.id desc ) as epoetin_info,\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "--2\n" +
                        "(select top 1  \n" +
                        "CASE WHEN ISNULL(M.vitB_Sinedries,0) > 0 \n" +
                        "THEN \n" +
                        "     CASE WHEN   vitB_DateIn <= @CUR_DATE AND vitB_Dateout >= @CUR_DATE AND ISNULL(m.vitB_Sinedries,0) >  dbo.get_last_used_meds(m.vitB_Sinedries ,ni.PatientID,2, m.vitB_period) \n" +
                        "      THEN\n" +
                        "            isnull(dbo.nameitem(m.vitB_MedsID),'')  + CHAR(10)  +\n" +
                        "            'Δόση: ' + isnull(cast(vit_BDose as varchar),'') + ' ' + isnull(vit_B_MM,'') + CHAR(10) +\n" +
                        "            'Συνεδρίες: ' +   cast(isnull(vit_B_FRQ, 0)as varchar )  \n" +
                        "      ELSE ''\n" +
                        "\n" +
                        "      END \n" +
                        "ELSE\n" +
                        "  CASE WHEN ISNULL(M.vit_B_FRQ,0) > 0 AND  ISNULL(m.vit_B_FRQ,0) >  dbo.get_last_used_meds(m.vit_B_FRQ ,ni.PatientID,2, m.vitB_period) -- \n" +
                        "  THEN\n" +
                        "    isnull(dbo.nameitem(m.vitB_MedsID),'')  + CHAR(10)  +\n" +
                        "    'Δόση: ' + isnull(cast(vit_BDose as varchar),'') + ' ' + isnull(vit_B_MM,'') + CHAR(10) +\n" +
                        "    'Συχνότητα: ' +   cast(isnull(vit_B_FRQ, 0)as varchar ) \n" +
                        "  ELSE ''\n" +
                        "  end\n" +
                        "END  \n" +
                        "\n" +
                        "from Nursing_Medical_Instructions m  \n" +
                        "left join Nursing_items_meds_frequency fr on fr.id = m.vit_B_FRQ_ID \n" +
                        "where m.PatientID=ni.PatientID order by year desc, month desc , m.id desc) as vitB_info,\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "--3\n" +
                        "\n" +
                        "(select top 1\n" +
                        "CASE WHEN ISNULL(M.carnitSinedries,0) > 0 \n" +
                        "THEN \n" +
                        "     CASE WHEN   carnDateIn <= @CUR_DATE AND carnDateout >= @CUR_DATE AND ISNULL(m.carnitSinedries,0) >  dbo.get_last_used_meds(m.carnitSinedries ,ni.PatientID,3 , m.carnitine_period) \n" +
                        "      THEN\n" +
                        "           isnull(dbo.nameitem(m.CarnitineMedsID),'')  + CHAR(10)  +\n" +
                        "          'Δόση: ' + isnull(cast(carnitineDose as varchar),'') + ' ' + isnull(CarnitineMM,'') + CHAR(10) + \n" +
                        "         'Συνεδρίες: ' +   cast(isnull(CarnitineFRQ, 0)as varchar )   \n" +
                        "      ELSE ''\n" +
                        "\n" +
                        "      END \n" +
                        "ELSE\n" +
                        "  CASE WHEN ISNULL(M.CarnitineFRQ,0) > 0 AND  ISNULL(m.CarnitineFRQ,0) >  dbo.get_last_used_meds(m.CarnitineFRQ ,ni.PatientID,3 ,  m.carnitine_period) -- \n" +
                        "  THEN\n" +
                        "    isnull(dbo.nameitem(m.CarnitineMedsID),'')  + CHAR(10)  +\n" +
                        "    'Δόση: ' + isnull(cast(carnitineDose as varchar),'') + ' ' + isnull(CarnitineMM,'') + CHAR(10) + \n" +
                        "    'Συχνότητα: ' +   cast(isnull(CarnitineFRQ, 0)as varchar )  \n" +
                        "  ELSE ''\n" +
                        "  end\n" +
                        "END  \n" +
                        "\n" +
                        "\n" +
                        "from Nursing_Medical_Instructions m   \n" +
                        "left join Nursing_items_meds_frequency fr on fr.id = m.CarnitineFRQ_ID\n" +
                        "where m.PatientID=ni.PatientID order by year desc, month desc , m.id desc) as carnitine_info,\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "--4\n" +
                        "(select top 1 \n" +
                        "CASE WHEN ISNULL(M.vitD_Sinedries,0) > 0 \n" +
                        "THEN \n" +
                        "     CASE WHEN   vitD_DateIn <= @CUR_DATE AND vitD_Dateout >= @CUR_DATE AND ISNULL(m.vitD_Sinedries,0) >  dbo.get_last_used_meds(m.vitD_Sinedries ,ni.PatientID,4 ,m.vitD_period) \n" +
                        "      THEN\n" +
                        "             isnull(dbo.nameitem(m.vitD_MedsID),'')  + CHAR(10)  + \n" +
                        "            'Δόση: ' + isnull(cast(vit_DDose as varchar),'') + ' ' + isnull(vit_D_MM,'') + CHAR(10) + \n" +
                        "            'Συνεδρίες: ' +   cast(isnull(vit_D_FRQ, 0)as varchar )  \n" +
                        "      ELSE ''\n" +
                        "\n" +
                        "      END \n" +
                        "ELSE\n" +
                        "  CASE WHEN ISNULL(M.vit_D_FRQ,0) > 0 AND  ISNULL(m.vit_D_FRQ,0) >  dbo.get_last_used_meds(m.vit_D_FRQ ,ni.PatientID,4 ,m.vitD_period) -- \n" +
                        "  THEN\n" +
                        "    isnull(dbo.nameitem(m.vitD_MedsID),'')  + CHAR(10)  + \n" +
                        "    'Δόση: ' + isnull(cast(vit_DDose as varchar),'') + ' ' + isnull(vit_D_MM,'') + CHAR(10) + \n" +
                        "    'Συχνότητα: ' +   cast(isnull(vit_D_FRQ, 0)as varchar )    \n" +
                        "  ELSE ''\n" +
                        "  end\n" +
                        "END  \n" +
                        "\n" +
                        "from Nursing_Medical_Instructions m   \n" +
                        "left join Nursing_items_meds_frequency fr on fr.id = m.vit_D_FRQ_ID \n" +
                        "where m.PatientID=ni.PatientID order by year desc, month desc , m.id desc) as vitD_info,\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "--5\n" +
                        "(select top 1  \n" +
                        "CASE WHEN ISNULL(M.FeSinedries,0) > 0 \n" +
                        "THEN \n" +
                        "     CASE WHEN   FeDateIn <= @CUR_DATE AND FeDateout >= @CUR_DATE AND ISNULL(m.FeSinedries,0) >  dbo.get_last_used_meds(m.FeSinedries ,ni.PatientID,5 ,m.fe_period) \n" +
                        "      THEN\n" +
                        "           isnull(dbo.nameitem(m.FeMedsID),'') + CHAR(10)  + \n" +
                        "          'Δόση: ' + isnull(cast(FeDose as varchar),'') + ' ' + isnull(FeMM,'') + CHAR(10) + \n" +
                        "          'Συνεδρίες: ' +   cast(isnull(FeFRQ, 0) as varchar )   \n" +
                        "      ELSE ''\n" +
                        "\n" +
                        "      END \n" +
                        "ELSE\n" +
                        "  CASE WHEN ISNULL(M.FeFRQ,0) > 0 AND  ISNULL(m.FeFRQ,0) >  dbo.get_last_used_meds(m.FeFRQ ,ni.PatientID,5 ,m.fe_period) -- \n" +
                        "  THEN\n" +
                        "    isnull(dbo.nameitem(m.FeMedsID),'') + CHAR(10)  + \n" +
                        "    'Δόση: ' + isnull(cast(FeDose as varchar),'') + ' ' + isnull(FeMM,'') + CHAR(10) + \n" +
                        "    'Συχνότητα: ' +   cast(isnull(FeFRQ, 0) as varchar ) \n" +
                        "  ELSE ''\n" +
                        "  end\n" +
                        "END  \n" +
                        "\n" +
                        "from Nursing_Medical_Instructions m   \n" +
                        "left join Nursing_items_meds_frequency fr on fr.id = m.FeFRQ_ID \n" +
                        "where m.PatientID=ni.PatientID order by year desc, month desc , m.id desc) as fe_info,\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "--6\n" +
                        "(select top 1 \n" +
                        "CASE WHEN ISNULL(M.etelSinedries,0) > 0 \n" +
                        "THEN \n" +
                        "     CASE WHEN   etelDateIn <= @CUR_DATE AND etelDateout >= @CUR_DATE AND ISNULL(m.etelSinedries,0) >  dbo.get_last_used_meds(m.etelSinedries ,ni.PatientID,6, m.etel_period) \n" +
                        "      THEN\n" +
                        "             isnull(dbo.nameitem(m.etelalcetideMedsID),'') + CHAR(10)  + \n" +
                        "            'Δόση: ' + isnull(cast(etelalcetideDose as varchar),'') + ' ' + isnull(etelalcetideMM,'') + CHAR(10) + \n" +
                        "            'Συνεδρίες: ' +   cast(isnull(etelalcetideFRQ, 0)as varchar )   \n" +
                        "      ELSE ''\n" +
                        "\n" +
                        "      END \n" +
                        "ELSE\n" +
                        "  CASE WHEN ISNULL(m.etelalcetideFRQ,0) > 0 AND  ISNULL(m.etelalcetideFRQ,0) >  dbo.get_last_used_meds(m.etelalcetideFRQ ,ni.PatientID,6, m.etel_period) -- \n" +
                        "  THEN\n" +
                        "    isnull(dbo.nameitem(m.etelalcetideMedsID),'') + CHAR(10)  + \n" +
                        "    'Δόση: ' + isnull(cast(etelalcetideDose as varchar),'') + ' ' + isnull(etelalcetideMM,'') + CHAR(10) + \n" +
                        "    'Συχνότητα: ' +   cast(isnull(etelalcetideFRQ, 0)as varchar )  \n" +
                        "  ELSE ''\n" +
                        "  end\n" +
                        "END  \n" +
                        "\n" +
                        "from Nursing_Medical_Instructions m   \n" +
                        "left join Nursing_items_meds_frequency fr on fr.id = m.etelalcetideFRQ_ID where m.PatientID=ni.PatientID order by year desc, month desc , m.id desc) as etelalcetide_info\n" +
                        "  \n" +
                        "  \n" +
                        "  \n" +
                        " from Nursing_Hemodialysis_2_MEDIT  ni \n" +
                        " where ni.patientid = " + patientID + " order by date desc ,  id desc \n" +
                        "\n";


            }


            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = main;
            task.query = query;
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {
                   ArrayList <String> newList_oldValues = new ArrayList<>();

                    if (results != null) {

                        if (!results.getJSONObject(0).has("status")) {
                          //  oldValuesLista.clear();
                            if (!main.isFinishing())
                                main.alertDialog.show();

                            JSONObject tel_metr = results.getJSONObject(0);
                            for (int i = 0; i < namesGiaApothikeusi.size(); i++) {
                                String col_name = namesGiaApothikeusi.get(i);
                                newList_oldValues.add(Utils.convertObjToString(tel_metr.get(col_name)));

                            }
                            for (int i = 0; i < listaAdaptor.size(); i++) {

                                if (Customers.isFrontis(main.custID)) {
                                    String title = listaAdaptor.get(i).titleID;

                                    switch (title) {
                                        case "L - carnittine":
                                            listaAdaptor.get(i).setValue(Utils.convertObjToString(tel_metr.get("Carnitine")));
                                            break;
                                        case "Alphacalcidol":
                                            listaAdaptor.get(i).setValue(Utils.convertObjToString(tel_metr.get("Alfacalcidol")));
                                            break;
                                        case "Epoetin alpha":
                                            listaAdaptor.get(i).setValue(Utils.convertObjToString(tel_metr.get("alpha")));
                                            break;
                                        case "Epoetin zeta":
                                            listaAdaptor.get(i).setValue(Utils.convertObjToString(tel_metr.get("zeta")));
                                            break;
                                        case "Darbepoetin":
                                            listaAdaptor.get(i).setValue(Utils.convertObjToString(tel_metr.get("darbepoetin")));
                                            break;
                                        case "Paricalcitol":
                                            listaAdaptor.get(i).setValue(Utils.convertObjToString(tel_metr.get("Paracalcitol")));
                                            break;
                                    }
                                }


                            }

                        }

                        else{
                            for (int i = 0; i < namesGiaApothikeusi.size(); i++)
                                newList_oldValues.add("");
                            }

                    }

                    else{
                        for (int i = 0; i < namesGiaApothikeusi.size(); i++)
                            newList_oldValues.add("");
                        }


                    adapter.updateOldLista(newList_oldValues);

                    if (!main.isFinishing())
                         main.alertDialog.dismiss();


                    if (emptyMainList) {
                        id = "";
                        ArrayList<ItemsRV> sinexList = sinexeisLista();
                        if (results != null ) {
                            JSONObject tel_metr = results.getJSONObject(0);
                            //ΤΟ ΣΤΑΟΥΣ ΣΗΜΑΙΝΕΙΔΕΝ ΕΧΕΙ ΔΕΔΟΜΕΝΑ
                            // ΜΟΝΟ ΕΔΩ ΤΟ ΚΑΝΩ ΕΤΣΙ ΕΠΕΙΔΗ ΔΕΝ ΘΕΛΩ ΝΑ ΥΠΑΡΧΟΥΝ ΤΙΜΕς NULL ΣΤΙΣ ΠΑΡΑΚΑΤΩ ΤΙΜΕΣ
                            // ΕΠΕΙΔΗ ΔΗΜΙΟΥΡΓΕΙΤΑΙ ΠΡΟΒΛΗΜΑ ΣΤΟ DIFFUTILS --  if (newItem.getValue() != null && oldItem.getValue() != null)
                            boolean hasStatus = tel_metr.has("status");

                            if (!Customers.isFrontis(main.custID)) {

                                for (int i = 0; i < sinexList.size(); i++) {
                                    String colName = sinexList.get(i).col_name;

                                    switch (colName) {

                                        case "epo":
                                            sinexList.get(i).setValue(hasStatus ? "" : tel_metr.optString("epoetin_info"));
                                            break;
                                        case "vit_b":
                                            sinexList.get(i).setValue(hasStatus ? "" : tel_metr.optString("vitB_info"));
                                            break;
                                        case "carnitine":
                                            sinexList.get(i).setValue(hasStatus ? "" : tel_metr.optString("carnitine_info"));
                                            break;
                                        case "vit_d":
                                            sinexList.get(i).setValue(hasStatus ? "" : tel_metr.optString("vitD_info"));
                                            break;
                                        case "fe":
                                            sinexList.get(i).setValue(hasStatus ? "" : tel_metr.optString("fe_info"));
                                            break;
                                        case "etelalcetide":
                                            sinexList.get(i).setValue(hasStatus ? "" : tel_metr.optString("etelalcetide_info"));
                                            break;

                                    }
                                }
                            }


                            adapter.updateLista(sinexList);
                        }

                    }

                }


            };


            task.execute();
        }


    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bd = null;
    }


    private void getCurrentContinuousMeasurement(){

        if (Utils.isNetworkAvailable(main)) {

            String query = " select * from Nursing_Hemodialysis_2_MEDIT where TransGroupid =  " + main.transgroupID +
                            " order by date desc";


            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = main;
            task.query = query;
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {

                    if (results != null) {
                        JSONObject metrisi = results.getJSONObject(0);
                        if (!metrisi.has("status")) // TO STATUS EINAI OTAN DEN IPARXOUN STOIXEIA
                            id = String.valueOf(metrisi.getInt("ID"));
                    }
                }


            };
            task.execute();

        } else
            Toast.makeText(main, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
    }





    private ArrayList<ItemsRV> sinexeisLista(){
        switch (main.custID){
            case Customers.CUSTID_FRONTIS:
            case Customers.CUSTID_FRONTIS_2:
                return Frontis.getSinexeisMetriseisLista();

            case Customers.CUSTID_MEDITERRANEO:
                return InfoSpecificLists.getSinexeisMetriseisLista();

            default:
                return InfoSpecificLists.getSinexeisMetriseisLista();



        }

    }




}
