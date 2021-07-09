package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

public class DialogFragment_emfanisi_epilegmenis_metrisis_OLD extends DialogFragment {


    private  Map<String,String> map = new HashMap();
    private boolean statheresMetriseis;
    private View view;
    //STATHERES
    private TextView dateTV, timeStartTV, timefinishTV, durationMetrisisTV, diaforaVarousTV ;

    private TextView arxikoVarosET, ksiroVarosET, UF_arxikoET, UF_telikoET, varosEksodouET,
            durationET, Max_UF_ET,  aimosfairiniET, aimatokritisET , roiAimatosET, roiDialimatosET, roiIpokatastatouET, agogimotitaET, thermokDialimatosET, HCO3_ET,
            ktvET, piesiEksodouET, ipef_nosil_Sp, ipef_nosil_2_SP, nosil_FlevSP, aggProspelasiSP, katastasiFiltrouSP;
// SINEXEIS

    private TextView sapET, ufET, sfikseisET ,  artPiesiET ,  sakxaroET, ktv_sinex_ET , roiAimatos_sinex_ET  ,roiIpokET  ,agogimotita_sinex_ET ,epanakikloforiaET,
            dapET, ufhET , tmpET, flevPiesiET , fisOrosET;


    private String companyID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            map = (Map) getArguments().getSerializable("map");
            statheresMetriseis = getArguments().getBoolean("statheresMetriseis");
        }

        if (statheresMetriseis) {
             view = inflater.inflate(R.layout.fragm_dialog_statheres_metriseis_oles_old_layout, container, false);

        }

        else{
            view = inflater.inflate(R.layout.fragm_dialog_sinexeis_metriseis_oles_old_layout, container, false);
        }

        companyID =  Utils.getcompanyID(view.getContext());

        initializeElements();

      //  getNosileutes();

        Set keys = map.keySet();

        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            String value = (String) map.get(key);

        }

        inputValues();

        return view;
    }


    private void initializeElements() {

        if (statheresMetriseis){
            dateTV = view.findViewById(R.id.dateTV);
            durationMetrisisTV = view.findViewById(R.id.durationTV);
            diaforaVarousTV = view.findViewById(R.id.diffrenceWeightTV);
            timeStartTV  = view.findViewById(R.id.timeStartTV);
            timefinishTV = view.findViewById(R.id.timeFinishedTV);
            arxikoVarosET = view.findViewById(R.id.startWeightET);
            ksiroVarosET = view.findViewById(R.id.ksiroWeightET);
            UF_arxikoET = view.findViewById(R.id.ufStartET);
            UF_telikoET = view.findViewById(R.id.ufFinishedET);
            varosEksodouET = view.findViewById(R.id.exitWeightET);
            durationET = view.findViewById(R.id.duration2ET);
            Max_UF_ET = view.findViewById(R.id.maxUFET);
            aimosfairiniET = view.findViewById(R.id.aimosfairniET);
            aimatokritisET = view.findViewById(R.id.aimatokrtisET);
            roiAimatosET = view.findViewById(R.id.roiAimatosET);
            roiDialimatosET = view.findViewById(R.id.roiDialimatosET);
            roiIpokatastatouET = view.findViewById(R.id.roiIpokatastatouET);
            agogimotitaET = view.findViewById(R.id.agogimotitaET);
            thermokDialimatosET = view.findViewById(R.id.thermDialimatosET);
            HCO3_ET = view.findViewById(R.id.hco3ET);
            ktvET = view.findViewById(R.id.ktvET);
            piesiEksodouET = view.findViewById(R.id.piesiEksodosET);
            ipef_nosil_Sp = view.findViewById(R.id.ipeuthnosNosSP);
            ipef_nosil_2_SP = view.findViewById(R.id.secondNosSP);
            nosil_FlevSP = view.findViewById(R.id.flevoNosSP);
            aggProspelasiSP = view.findViewById(R.id.prospelasiSP);
            katastasiFiltrouSP = view.findViewById(R.id.katastasiFiltrouSP);

        }

        else{

            sapET = view.findViewById(R.id.sapET);
            ufET = view.findViewById(R.id.ufET);
            sfikseisET = view.findViewById(R.id.sfikseisET);
            artPiesiET = view.findViewById(R.id.artPiesiET);
            sakxaroET = view.findViewById(R.id.sakxaroET);
            ktv_sinex_ET = view.findViewById(R.id.ktvET);
            roiAimatos_sinex_ET = view.findViewById(R.id.roiAimatosET);
            roiIpokET = view.findViewById(R.id.roiIpokET);
            agogimotita_sinex_ET = view.findViewById(R.id.agogimotitaET);
            epanakikloforiaET = view.findViewById(R.id.epanakikloforiaET);
            dapET = view.findViewById(R.id.dapET);
            ufhET = view.findViewById(R.id.ufhET);
            tmpET = view.findViewById(R.id.tmpET);
            flevPiesiET = view.findViewById(R.id.flevPiesiET);
            fisOrosET = view.findViewById(R.id.fisOrosET);
        }



    }



    private void inputValues() {
        if (statheresMetriseis){

            dateTV.setText(map.get("date"));
            timeStartTV.setText(map.get("time_start"));
            timefinishTV.setText(map.get("time_finish"));
            durationMetrisisTV.setText(Utils.chechZeroInTime(Utils.timeDiffrence(timeStartTV.getText().toString(), timefinishTV.getText().toString())));
            arxikoVarosET.setText(map.get("arxiko_varos"));
            ksiroVarosET.setText(map.get("xiro_varos"));
            diaforaVarousTV.setText(Utils.afairesiDouble(arxikoVarosET.getText().toString(), ksiroVarosET.getText().toString()));
            UF_arxikoET.setText(map.get("Uf_arxiko"));
            UF_telikoET.setText(map.get("Uf_teliko"));
            varosEksodouET.setText(map.get("varos_exodou"));
            durationET.setText(map.get("diarkiaStr"));
            Max_UF_ET.setText(map.get("Max_UFh"));
            aimosfairiniET.setText(map.get("aimosfairini"));
            aimatokritisET.setText(map.get("aimatokritis"));
            roiAimatosET.setText(map.get("roi_aimatos"));
            roiDialimatosET.setText(map.get("roi_dialimatos"));
            roiIpokatastatouET.setText(map.get("roi_ipokatastatou"));
            agogimotitaET.setText(map.get("agogimotita"));
            thermokDialimatosET.setText(map.get("thermokrasia_dialimatos"));
            HCO3_ET.setText(map.get("hco3"));
            ktvET.setText(map.get("Kt_V"));
            piesiEksodouET.setText(map.get("piesi_exodou"));
            ipef_nosil_Sp.setText(map.get("nosileutis1"));
            ipef_nosil_2_SP.setText(map.get("nosileutis2"));
            nosil_FlevSP.setText(map.get("nos_flev"));
            aggProspelasiSP.setText(map.get("agg_prospelasi"));
            katastasiFiltrouSP.setText(InfoSpecificLists.getKatastasiName(map.get("katastasi_filtou")));


        }

        else{

            sapET.setText(map.get("sap"));
            ufET.setText(map.get("uf"));
            sfikseisET.setText(map.get("sfixeis"));
            artPiesiET.setText(map.get("art_piesi"));
            sakxaroET.setText(map.get("sakxaro"));
            ktv_sinex_ET.setText(map.get("kt_v"));
            roiAimatos_sinex_ET.setText(map.get("roi_aimatos"));
            roiIpokET.setText(map.get("roi_ipokatastatou"));
            agogimotita_sinex_ET.setText(map.get("agogimotita"));
            epanakikloforiaET.setText(map.get("epanakikloforia"));
            dapET.setText(map.get("dap"));
            ufhET.setText(map.get("uf_h"));
            tmpET.setText(map.get("tmp"));
            flevPiesiET.setText(map.get("flev_piesi"));
            fisOrosET.setText(map.get("fisiologikos_oros"));
        }


    }



    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }






}
