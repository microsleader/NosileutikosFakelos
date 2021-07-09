package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_2.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_2.MainActivity_Aim;

import static micros_leader.george.nosileutikosfakelos.BasicActivity.isHeader;

/**
 * A simple {@link Fragment} subclass.

 */
public class Iatrikes_odigies_fragment extends Fragment  {

    private View view;
    private MainActivity_Aim main;
    private String transgroupID, patientid,companyID,table;
    private RecyclerView instractionsRV;
    private BasicRV adapter;
    private ArrayList <ItemsRV>  listaAdaptor , newList = InfoSpecificLists.getMedicalInsLista();



    public Iatrikes_odigies_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_iatrikes_odigies_fragment, container, false);
        main = (MainActivity_Aim) getActivity();


        if (main != null)
            companyID = Utils.getcompanyID(main);
        table = "Nursing_Medical_Instructions";



        newList = InfoSpecificLists.getMedicalInsLista();
        main.getAll_col_names(InfoSpecificLists.getMedicalInsLista());


        instractionsRV = view.findViewById(R.id.instructionsRV);

        instractionsRV.setItemViewCacheSize(20);
        instractionsRV.setHasFixedSize(true);
        instractionsRV.setNestedScrollingEnabled(false);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                boolean isHeader = isHeader(position, null);
                if (isHeader)
                    return 2;
                else
                    return 1;

            }
        });

        instractionsRV.setLayoutManager(manager);



        adapter = new BasicRV(main,  InfoSpecificLists.getMedicalInsLista());
        listaAdaptor = adapter.result;
        instractionsRV.setAdapter(adapter);








        getMedicalInsructions();


        return view;
    }



    private void getMedicalInsructions(){

        if ( main != null && main.search_TV != null) {

            String code = Utils.getfirstPartSplitCommaString(main.search_TV.getText().toString());
            String patientID = main.getPatientIDUsingCode.get(code);

            main.nameJson.clear();
            main.getAll_col_names(InfoSpecificLists.getMedicalInsLista());

            //ΑΥΤΟ ΕΔΩ ΕΧΕΙ ΓΙΝΕΙ ΜΕ ΤΗΝ ΠΙΟ ΠΑΛΙΑ ΜΕΘΟΛΟΓΙΑ ΚΑΙ  ΔΕΝ ΧΡΗΣΙΜΟΠΟΙΕΙΤΑΙ ΑΛΛΟΥ

            String cols = main.getFROM_NAMEJSON_COLUMNS(main.nameJson, "n");
            cols = cols.replace("n.eidosName,","").replace("n.filName,","")
                    .replace("n.agogiName,","").replace("n.dialeimaName,","")
                    .replace("n.epoName,","");
            main.getJSON_DATA( Str_queries.getMEDICAL_INSTRACTIONS_NEW(cols, patientID));
        }

    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //if (isVisibleToUser)
        //    getMedicalInsructions();

    }

    public void getDoctorAndOtherInfo(JSONArray results){
        try {

            newList =  InfoSpecificLists.getMedicalInsLista();


            if (main.weHaveData && results != null) {


                String doctorName = results.getJSONObject(0).getString("doctorName");
                String month = results.getJSONObject(0).getString("Month");
                String yearName = results.getJSONObject(0).getString("yearName");
                String durationTime = results.getJSONObject(0).getString("durationTime");


                // for (int i = 0; i < main.nameJson.size(); i++)
                main.setValuesTolistaAdaptor(null, newList);

                newList.get(0).setValue(month + " / " + yearName);
                newList.get(1).setValue(doctorName);
                newList.get(4).setValue(durationTime);


            }




            adapter.updateLista(newList);



            main.alertDialog.dismiss();

            main.weHaveData = false; //gia na min epireastoun ta alla fragment
        }

        catch (Exception e){

        }
    }






}
