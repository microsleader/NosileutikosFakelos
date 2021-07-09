package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities.SinexeisMetriseis_All_Activity;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static micros_leader.george.nosileutikosfakelos.Utils.setChecked;

public class DialogFragment_emfanisi_epilegmenis_metrisis extends DialogFragment {


    private  Map<String,String> map = new HashMap();
    private boolean statheresMetriseis, teleutaiaSinexiMetrisi;
    private View view;
    //STATHERES
    private TextView dateTV, schTimestartTV, timeStartTV,  durationTV,durationMetrisisTV ;


    private TextView
            arxikoVarosTV,  ksiroVarosTV,  UFTV,   varosEksodouTV,
            Max_UF_TV,  thermokDialimatosTV,  ktvTV,  fistulaTV , mosxeumaTV,  iatrosVardiasTV,
            _flev_textTV,  skelonATV,  skelonFTV , dialimaTV, filtroTV, lot_filtrouTV,  ipar_filtrouTV, iparEfapaxTV;

    private CheckBox  velona15CH,velona16CH,velona17CH,onlinePFCH , onlineMFCH,
            onlineHDCH,flevMonimosCH,flevProsorinosCH;

    private BasicActivity act;

// SINEXEIS


    private TextView date_sinex_TV;
    private EditText sistolikiET, diastolikiET,   sfikseisET ,thermokrasiaET,   roiET ,   flevET,  artET ,  iperdiithisiET  ,
            agogimotitaET  , hco3ET , paremvaseisET,
            paratiriseisET,  EgxiseisET ;

    private EditText [] editText_lista;


    private CheckBox  vitCH, carnitineCH, alphaCH, epoCH, epoAlphaCH, epoZetaCH, darbepoetinCH, parcalcitolCH, sidirosCH;

    private CircularProgressButton updateBT;

    private String id , transgroupID,companyID;
    private ArrayList<String> namesGiaApothikeusi ;


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
            teleutaiaSinexiMetrisi = getArguments().getBoolean("teleutaiaSinexiMetrisi");

            if (getActivity() instanceof SinexeisMetriseis_All_Activity)
                act = (SinexeisMetriseis_All_Activity) getActivity();
        }

        if (statheresMetriseis) {
             view = inflater.inflate(R.layout.fragm_dialog_statheres_metriseis_oles_layout, container, false);

        }

        else{
            view = inflater.inflate(R.layout.fragm_dialog_sinexeis_metriseis_oles_layout, container, false);
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
            dateTV = view.findViewById(R.id. dateTV);
            schTimestartTV = view.findViewById(R.id. schtimeStartTV);
            timeStartTV  = view.findViewById(R.id. timeStartTV);
            durationTV = view.findViewById(R.id. durationTV);
            arxikoVarosTV = view.findViewById(R.id. startWeightTV);
            ksiroVarosTV = view.findViewById(R.id. ksiroWeightTV);
            UFTV = view.findViewById(R.id. ufTV);
            varosEksodouTV = view.findViewById(R.id. exitWeightTV);
            thermokDialimatosTV = view.findViewById(R.id. ThermDialimTV);
            ktvTV = view.findViewById(R.id. ktvTV);
            fistulaTV = view.findViewById(R.id. fistulaTV);
            mosxeumaTV = view.findViewById(R.id. mosxeumaTV);
            iatrosVardiasTV = view.findViewById(R.id. iatrosVardiasTV);
            _flev_textTV = view.findViewById(R.id. flevKeimenoTV);
            skelonATV = view.findViewById(R.id. skelonATV);
            skelonFTV = view.findViewById(R.id. skelonFTV);
            dialimaTV = view.findViewById(R.id. dialimaTV);
            filtroTV = view.findViewById(R.id. filtroTV);
            lot_filtrouTV = view.findViewById(R.id. lotfiltrouTV);
            ipar_filtrouTV = view.findViewById(R.id. iparFiltrouTV);
            iparEfapaxTV = view.findViewById(R.id. iparefapaxTV);

            velona15CH = view.findViewById(R.id. velona15CH);
            velona15CH.setClickable(false);
            velona16CH = view.findViewById(R.id. velona16CH);
            velona16CH.setClickable(false);
            velona17CH = view.findViewById(R.id. velona17CH);
            velona17CH.setClickable(false);
            onlineMFCH = view.findViewById(R.id. onlineMFCH);
            onlineMFCH.setClickable(false);
            onlinePFCH = view.findViewById(R.id. onlinePFCH);
            onlinePFCH.setClickable(false);
            onlineHDCH = view.findViewById(R.id. onlineHDCH);
            onlineHDCH.setClickable(false);
            flevMonimosCH  = view.findViewById(R.id. flevMonimosCH);
            flevMonimosCH.setClickable(false);
            flevProsorinosCH = view.findViewById(R.id. flevProsorinosCH);
            flevProsorinosCH.setClickable(false);

        }

        else{

            date_sinex_TV = view.findViewById(R.id. dateTV);
            sistolikiET = view.findViewById(R.id.sistolikiTV);
            diastolikiET = view.findViewById(R.id.diastolikiTV);
            thermokrasiaET = view.findViewById(R.id.thermokrasiaTV);
            sfikseisET = view.findViewById(R.id. sfikseisTV);
            roiET = view.findViewById(R.id. roiTV);
            flevET = view.findViewById(R.id. flevTV);
            artET = view.findViewById(R.id. artTV);
            iperdiithisiET = view.findViewById(R.id. iperdiithisiTV);
            agogimotitaET = view.findViewById(R.id. agogimotitaTV);
            hco3ET = view.findViewById(R.id. hco3TV);
            paremvaseisET = view.findViewById(R.id.paremvaseisTV);
            paratiriseisET = view.findViewById(R.id. paratiriseisTV);
            EgxiseisET = view.findViewById(R.id.EgxiseisTV);

            vitCH = view.findViewById(R.id.vitCH);
            vitCH.setClickable(false);
            carnitineCH = view.findViewById(R.id.carnitineCH);
            carnitineCH.setClickable(false);
            alphaCH = view.findViewById(R.id.alphaCH);
            alphaCH.setClickable(false);
            epoCH = view.findViewById(R.id.epoCH);
            epoCH.setClickable(false);
            epoAlphaCH = view.findViewById(R.id.epoAlphaCH);
            epoAlphaCH.setClickable(false);
            epoZetaCH = view.findViewById(R.id.epoZetaCH);
            epoZetaCH.setClickable(false);
            darbepoetinCH = view.findViewById(R.id.darbepoetinCH);
            darbepoetinCH.setClickable(false);
            parcalcitolCH = view.findViewById(R.id.parcalcitolCH);
            parcalcitolCH.setClickable(false);
            sidirosCH = view.findViewById(R.id.sidirosCH);
            sidirosCH.setClickable(false);

            if (teleutaiaSinexiMetrisi) {
                act.nameJson = new ArrayList<>();
                editText_lista = new EditText[]{sistolikiET,diastolikiET,sfikseisET,thermokrasiaET,roiET,flevET,
                        artET ,  iperdiithisiET,agogimotitaET  , hco3ET , paremvaseisET,paratiriseisET,  EgxiseisET};

                updateBT = view.findViewById(R.id.updateButton);
                updateBT.setVisibility(View.VISIBLE);
                updateBTsetListener();
            }
            else
            {
                sistolikiET.setEnabled(false);
                diastolikiET.setEnabled(false);
                thermokrasiaET.setEnabled(false);
                sfikseisET.setEnabled(false);
                roiET.setEnabled(false);
                flevET.setEnabled(false);
                artET.setEnabled(false);
                iperdiithisiET.setEnabled(false);
                agogimotitaET.setEnabled(false);
                hco3ET.setEnabled(false);
                paremvaseisET.setEnabled(false);
                paratiriseisET.setEnabled(false);
                EgxiseisET.setEnabled(false);
            }



        }



    }


    private void updateBTsetListener() {

        updateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        InputMethodManager imm = (InputMethodManager) act.getSystemService(INPUT_METHOD_SERVICE);
        if (act.getCurrentFocus() != null)
            imm.hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(), 0);

        updateBT.startAnimation();

        AsyncTaskUpdate_JSON task;

       // act.setValuesTo_valuesJSON(null,listaAdaptor,valuesGiaApothikeusi);
       // act.nameJson.add("ID");
      //  act.nameJson.add("transgroupID");
        act.getAll_col_names(InfoSpecificLists.getSinexeisMetriseisLista()); // ΜΠΗΚΑΝ ΤΑ ΠΕΔΙΑ ΣΤΟ NAMEJSON
        act.nameJson.remove("Date");

      //  act.valuesJson.add(id);
      //  act.valuesJson.add(transgroupID);
        act.ET_setText(editText_lista,act.valuesJson,true); //ΜΠΑΙΝΟΥΝ ΟΙ ΤΙΜΕΣ ΣΤΟ valuesJson
        act.valuesJson.add(Utils.getChecked(vitCH.isChecked()));
        act.valuesJson.add(Utils.getChecked(carnitineCH.isChecked()));
        act.valuesJson.add(Utils.getChecked(alphaCH.isChecked()));
        act.valuesJson.add(Utils.getChecked(epoCH.isChecked()));
        act.valuesJson.add(Utils.getChecked(epoAlphaCH.isChecked()));
        act.valuesJson.add(Utils.getChecked(epoZetaCH.isChecked()));
        act.valuesJson.add(Utils.getChecked(darbepoetinCH.isChecked()));
        act.valuesJson.add(Utils.getChecked(parcalcitolCH.isChecked()));
        act.valuesJson.add(Utils.getChecked(sidirosCH.isChecked()));

        //  Toast.makeText(main, id, Toast.LENGTH_SHORT).show();


            task = new AsyncTaskUpdate_JSON(act, id, transgroupID, "Nursing_Hemodialysis_2_MEDIT",
                    act.nameJson, Utils.replaceTrueOrFalse(act.valuesJson), null);

            task.id = id;
            task.transgroupid = transgroupID;
        task.names_col = new String [] {"ID","TransGroupID"};
        task.listener = new AsyncGetUpdate_JSON() {
            @Override
            public void update_JSON(String str) {
                if (str.equals(getString(R.string.successful_update))) {

                    Utils.timeHandlerDoneButton(updateBT, act);



                } else {

                    Utils.timeHandlerErrorButton(updateBT, act);
                    Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void getIDofInsert(String id) {

            }
        };

        task.execute();

            }
        });
    }



    private void inputValues() {
        if (statheresMetriseis){

            dateTV.setText(map.get("datestr"));
            timeStartTV.setText(map.get("timeStart"));
            schTimestartTV.setText(map.get("schtimeStart"));
            durationTV.setText(map.get("dur"));
            arxikoVarosTV.setText(map.get("arxiko_varos"));
            ksiroVarosTV.setText(map.get("xiro_varos"));
            UFTV.setText(map.get("UF"));
            varosEksodouTV.setText(map.get("varos_exodou"));
            thermokDialimatosTV.setText(map.get("therm_dialimatos"));
            ktvTV.setText(map.get("Kt_V"));
            fistulaTV.setText(map.get("fistula"));
            mosxeumaTV.setText(map.get("mosxeuma"));
            iatrosVardiasTV.setText(map.get("docName"));
            _flev_textTV.setText(map.get("flev_kathtiras_text"));
            skelonATV.setText(map.get("skelon_A"));
            skelonFTV.setText(map.get("skelon_f"));
            dialimaTV.setText(map.get("dialima"));
            filtroTV.setText(map.get("filtro"));
            lot_filtrouTV.setText(map.get("lot_filtrou"));
            ipar_filtrouTV.setText(map.get("ipar_filtrou"));
            iparEfapaxTV.setText(map.get("ipar_efapax"));

            velona15CH.setChecked(setChecked(map.get("velona15")));
            velona16CH.setChecked(setChecked(map.get("velona16")));
            velona17CH.setChecked(setChecked(map.get("velona17")));
            onlinePFCH.setChecked(setChecked(map.get("online_pf")));
            onlineMFCH.setChecked(setChecked(map.get("online_mf")));
            onlineHDCH.setChecked(setChecked(map.get("online_hd")));
            flevMonimosCH.setChecked(setChecked(map.get("flev_kathtiras_monimos")));
            flevProsorinosCH.setChecked(setChecked(map.get("flev_kathtiras_prosorinos")));
        }

        else{


            date_sinex_TV.setText(Utils.convertMillisecondsToDateTime(map.get("Date")));
            sistolikiET.setText(map.get("sis_piesi"));
            diastolikiET.setText(map.get("dias_piesi"));
            thermokrasiaET.setText(map.get("thermokrasia"));
            sfikseisET.setText(map.get("sfikseis"));
            roiET.setText(map.get("roi"));
            flevET.setText(map.get("piesi_flev"));
            artET.setText(map.get("piesi_art"));
            iperdiithisiET.setText(map.get("iperd"));
            agogimotitaET.setText(map.get("agogimotita"));
            hco3ET.setText(map.get("haco3"));
            paremvaseisET.setText(map.get("paremvaseis"));
            paratiriseisET.setText(map.get("paratiriseis"));
            EgxiseisET.setText(map.get("egxiseis"));
            id = map.get("ID");
            transgroupID = map.get("TransGroupID");
            vitCH.setChecked(setChecked(map.get("vit_b")));
            carnitineCH.setChecked(setChecked(map.get("carnitine")));
            alphaCH.setChecked(setChecked(map.get("alphacalcidol")));
            epoCH.setChecked(setChecked(map.get("epo")));
            epoAlphaCH.setChecked(setChecked(map.get("epo_alpha")));
            epoZetaCH.setChecked(setChecked(map.get("epo_zeta")));
            darbepoetinCH.setChecked(setChecked(map.get("epo_darbepoetin")));
            parcalcitolCH.setChecked(setChecked(map.get("paricalcitol")));
            sidirosCH.setChecked(setChecked(map.get("sidiros")));

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
            if (dialog.getWindow() != null)
                dialog.getWindow().setLayout(width, height);
        }
    }






}
