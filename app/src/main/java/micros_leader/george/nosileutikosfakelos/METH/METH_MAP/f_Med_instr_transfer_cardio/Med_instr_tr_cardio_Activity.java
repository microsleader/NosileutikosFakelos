package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Med_instr_transfer_cardio;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.DialogFragmentSearches.DF_xorigisiFarmakon_general;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;

import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentMedicineCloseListener;
import micros_leader.george.nosileutikosfakelos.StaticFields;
import micros_leader.george.nosileutikosfakelos.TableView.TableFragment;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityMedInstrTrCardioBinding;

import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Med_instr_tr_cardio_Activity extends BasicActivity implements MyDialogFragmentMedicineCloseListener {


    private ActivityMedInstrTrCardioBinding bd;
    private DF_xorigisiFarmakon_general df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityMedInstrTrCardioBinding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);

        table = "Nursing_medical_instr_transfer_cardio_meth";
        getAll_col_names(InfoSpecificLists.getMed_Instr_trnasfer_cardio_meth());
        getPatientsList(this,bd.floorsSP,bd.patientsTV);
        thereIsImageUpdateButton();

        medsBTListener();

    }


    private void medsBTListener(){
        bd.medsBT.setOnClickListener(view -> {
            df = new DF_xorigisiFarmakon_general();
            Bundle putextra = new Bundle();
            putextra.putString("transgroupID",transgroupID);
            putextra.putString("tableID", StaticFields.TableIDs.NURSING_MED_INSTR_TRANSFER_CARDIO_METH_TABLE_ID);
            df.setArguments(putextra);
            df.show(getSupportFragmentManager(), "Dialog");
        });
    }






    private void getMedInstructions(){

        getJSON_DATA("select *, dbo.namedoctor(doctorid) as doctorName " +
                "from Nursing_medical_instr_transfer_cardio_meth where transgroupID = " + transgroupID);
    }



    @Override
    public void taskComplete2(JSONArray results) throws JSONException {
        super.taskComplete2(results);

        alertDialog.show();
        newList = InfoSpecificLists.getMed_Instr_trnasfer_cardio_meth();

        if (weHaveData)
            setValuesTolistaAdaptor(titloi_positions,newList);


        setAdapterAndUpdateValues(bd.rv.getId(),1,new String [] {"ID","TransGroupID"});



        alertDialog.dismiss();
    }



    @Override
    public void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor) {
        super.setValuesTo_valuesJSON(titles_positions, listAdaptor);


        read_only_col = new String[]{"doctorName"};

    }


    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);
        transgroupID = Utils.getSplitSPartString(patientsTV.getText().toString(),",",3);

        getMedInstructions();
    }





    @Override
    public void handleDialogClose(String transgroupid) {

        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT
        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);
        getMedInstructions();
    }



    @Override
    public void dialogMedicineClose(String id_name) {
        TableFragment tf = df.getTableFragment();
        tf.setMedicineInfo(id_name);

    }




}
