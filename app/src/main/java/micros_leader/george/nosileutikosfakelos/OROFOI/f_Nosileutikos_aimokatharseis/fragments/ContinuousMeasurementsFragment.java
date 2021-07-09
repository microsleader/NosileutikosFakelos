package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities.Nephroxenia_Main_Activity;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static micros_leader.george.nosileutikosfakelos.Utils.setChecked;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContinuousMeasurementsFragment extends Fragment  {

    private View view;
    public TextView currentDateTimeTV;
    private EditText    artPiesiET,  sfikseisET , thermokrasiaET,  roiET ,   flevET,  artET ,  iperdiithisiET  ,
            agogimotitaET  , hco3ET , paremvaseisET, paratiriseisET,  egxiseisET;

    private TextView olddateTV,  dateTV, oldartPiesiTV, oldsfikseisTV , oldthermokrasiaTV,  oldroiTV ,  oldflevTV, oldartTV , oldiperdiithisiTV  ,
            oldagogimotitaTV  ,oldhco3TV ,oldparemvaseisTV,
            oldparatiriseisTV, oldEgxiseisTV ;

    private CheckBox oldvitCH,oldcarnitineCH,oldalphaCH,oldepoCH,oldEpoAlphaCh, oldEpoZetaCH,oldDarbepoetinCH,oldparcalcitolCH,oldsidirosCH,
            vitCH,carnitineCH,alphaCH,epoCH,epoAlphaCh, epoZetaCH,darbepoetinCH,parcalcitolCH,sidirosCH;



    private String  transgroupID , date, artPiesi,  sfikseis ,   roi ,   flev,  art ,  iperdiithisi  ,
            agogimotita  ,thermokrasia, hco3 , paremvaseis, paratiriseis,  egxiseis , vit,
            carnitine,alpha,epo,epoAlpha,epoZeta,epoDarbepoetin,parcalcitol,sidiros;

    public String id, patientid,companyID;
    private CircularProgressButton updateButton;
    private String currentSpinnerPatient;
    private Bitmap bitmap;
    private Context CTX;
    private Nephroxenia_Main_Activity main;
    private ArrayList<String> nameJson, valuesJson;


    public ContinuousMeasurementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_continuous_med, container, false);

        CTX =   view.getContext();

        initializeElements();
        Bundle bundle = this.getArguments();
        transgroupID = bundle.getString("transgroupID");
        patientid = bundle.getString("patientID");
        companyID = bundle.getString("companyID");


        updateButtonListener();

        main = (Nephroxenia_Main_Activity)getActivity();

        currentSpinnerPatient = main.search_TV.getText().toString();


        drawerListener();


        return view;
    }



    private void getTeleutaiaMetrisi() {

        if (Utils.isNetworkAvailable2(CTX)) {

            String query = " select top 1 ni.* , " +
                    " (select max(m.VitB)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as VitB," +
                    " (select max(m.Carnitine)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as Carnitine, " +
                    " (select max(m.Alfacalcidol)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as Alfacalcidol," +
                    "  (select max(m.zeta)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as zeta," +
                    "  (select max(m.alpha)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as alpha," +
                    "  (select max(m.darbepoetin)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as darbepoetin," +
                    "  (select max(m.Paracalcitol)  from Nursing_Medical_Instructions m where m.PatientID=ni.PatientID) as Paracalcitol " +

                    " from Nursing_Hemodialysis_2_MEDIT  ni " +
                    " where ni.patientid =  "  + patientid +

                    " order by date desc";

            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = CTX;
            task.query = query;
            task.listener = new AsyncCompleteTask() {
                @Override
                public void taskComplete(JSONArray results) throws JSONException {

                    if (results != null) {
                        if (!results.getJSONObject(0).has("status")) {


                            JSONObject telMetriseis = results.getJSONObject(0);

                            olddateTV.setText(Utils.convertMillisecondsToDateTime(Utils.convertObjToString(telMetriseis.get("Date"))));
                            oldartPiesiTV.setText(Utils.convertObjToString(telMetriseis.get("art_piesi")));
                            oldsfikseisTV.setText(Utils.convertObjToString(telMetriseis.get("sfikseis")));
                            oldthermokrasiaTV.setText(Utils.convertObjToString(telMetriseis.get("thermokrasia")));
                            oldroiTV.setText(Utils.convertObjToString(telMetriseis.get("roi")));

                            oldflevTV.setText(Utils.convertObjToString(telMetriseis.get("piesi_flev")));
                            oldartTV.setText(Utils.convertObjToString(telMetriseis.get("piesi_art")));
                            oldiperdiithisiTV.setText(Utils.convertObjToString(telMetriseis.get("iperd")));
                            oldagogimotitaTV.setText(Utils.convertObjToString(telMetriseis.get("agogimotita")));

                            oldhco3TV.setText(Utils.convertObjToString(telMetriseis.get("hco3")));
                            oldparemvaseisTV.setText(Utils.convertObjToString(telMetriseis.get("paremvaseis")));
                            oldparatiriseisTV.setText(Utils.convertObjToString(telMetriseis.get("paratiriseis")));
                            oldEgxiseisTV.setText(Utils.convertObjToString(telMetriseis.get("egxiseis")));

                            oldvitCH.setChecked(setChecked(telMetriseis.get("vit_b")));
                            //      if (oldvitCH.isChecked())
                            oldvitCH.setText("Vit-B");
                            vitCH.setText(Utils.convertObjToString(telMetriseis.get("VitB")));

                            oldcarnitineCH.setChecked(setChecked(telMetriseis.get("carnitine")));
                            //  if (oldcarnitineCH.isChecked())
                            oldcarnitineCH.setText("Carnitine");
                            oldcarnitineCH.setClickable(false);
                            carnitineCH.setText(Utils.convertObjToString(telMetriseis.get("Carnitine")));


                            oldalphaCH.setChecked(setChecked(telMetriseis.get("alphacalcidol")));
                            //  if (oldalphaCH.isChecked())
                            oldalphaCH.setText("Alfacalcidol");
                            oldalphaCH.setClickable(false);
                            alphaCH.setText(Utils.convertObjToString(telMetriseis.get("Alfacalcidol")));


                            oldepoCH.setChecked(setChecked(telMetriseis.get("epo")));

                            oldEpoAlphaCh.setChecked(setChecked(telMetriseis.get("epo_alpha")));
                            oldEpoAlphaCh.setText("Epo alpha");
                            oldEpoAlphaCh.setClickable(false);
                            epoAlphaCh.setText(Utils.convertObjToString(telMetriseis.get("alpha")));

                            oldEpoZetaCH.setChecked(setChecked(telMetriseis.get("epo_zeta")));
                            oldEpoZetaCH.setText("Epo zeta");
                            oldEpoZetaCH.setClickable(false);
                            epoZetaCH.setText(Utils.convertObjToString(telMetriseis.get("zeta")));

                            oldDarbepoetinCH.setChecked(setChecked(telMetriseis.get("epo_darbepoetin")));
                            oldDarbepoetinCH.setText("Epo darbepoetin");
                            oldDarbepoetinCH.setClickable(false);
                            darbepoetinCH.setText(Utils.convertObjToString(telMetriseis.get("darbepoetin")));

                            oldparcalcitolCH.setChecked(setChecked(telMetriseis.get("paricalcitol")));
                            //   if (oldparcalcitolCH.isChecked())
                            oldparcalcitolCH.setText("Paracalcitol");
                            oldparcalcitolCH.setClickable(false);
                            parcalcitolCH.setText(Utils.convertObjToString(telMetriseis.get("Paracalcitol")));

                            oldsidirosCH.setText("Σίδηρος");
                            oldsidirosCH.setChecked(setChecked(telMetriseis.get("sidiros")));
                            oldcarnitineCH.setClickable(false);



                        }
                        else
                            clearOldTexts();
                    }
                }

                @Override
                public void taskCompleteGetVardies(JSONArray results) throws JSONException {

                }
            };

            task.execute();
        }

    }


    private void drawerListener() {
        final Nephroxenia_Main_Activity main = (Nephroxenia_Main_Activity)getActivity();
        main.drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        main.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(main.getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

//                String newSpinnerItem = main.search_TV.getText().toString();
//                if (!currentSpinnerPatient.equals(newSpinnerItem)) {
//                    id = null;
//                    String code = Utils.getfirstPartSplitCommaString(newSpinnerItem);
//                    transgroupID =  main.getTransgroupIDUsingCode.get(code);
//                    transgroupID = transgroupID == null ? "0" : transgroupID;
//                    patientid = main.getPatientIDUsingCode.get(code);
//
//
//                    clearTexts();
//                }
//                else {
//                    String code = Utils.getfirstPartSplitCommaString(newSpinnerItem);
//                    transgroupID =  main.getTransgroupIDUsingCode.get(code);
//                    transgroupID = transgroupID == null ? "0" : transgroupID;
//                    patientid = main.getPatientIDUsingCode.get(code);
//
//                }
//
//
//                getTeleutaiaMetrisi();



            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

        String newSpinnerItem = main.search_TV.getText().toString();
        if (!currentSpinnerPatient.equals(newSpinnerItem)) {
            id = null;
            String code = Utils.getfirstPartSplitCommaString(newSpinnerItem);
            transgroupID =  main.getTransgroupIDUsingCode.get(code);
            transgroupID = transgroupID == null ? "0" : transgroupID;
            patientid = main.getPatientIDUsingCode.get(code);


            clearTexts();
        }
        else {
            String code = Utils.getfirstPartSplitCommaString(newSpinnerItem);
            transgroupID =  main.getTransgroupIDUsingCode.get(code);
            transgroupID = transgroupID == null ? "0" : transgroupID;
            patientid = main.getPatientIDUsingCode.get(code);

        }


        getTeleutaiaMetrisi();
    }

    private void initializeElements(){


        currentDateTimeTV = view.findViewById(R.id.currentDateTimeTV);

        dateTV = view.findViewById(R.id.dateTV);
        artPiesiET = view.findViewById(R.id.artPieshET);
        sfikseisET = view.findViewById(R.id.sfikseisET);
        thermokrasiaET = view.findViewById(R.id.thermokrasiaET);
        roiET = view.findViewById(R.id.roiET);
        flevET = view.findViewById(R.id.flevET);
        artET = view.findViewById(R.id.artET);
        iperdiithisiET = view.findViewById(R.id.iperdiithisiET);
        agogimotitaET = view.findViewById(R.id.agogimotitaET);
        hco3ET = view.findViewById(R.id.hco3ET);
        paremvaseisET = view.findViewById(R.id.paremvaseisET);
        paratiriseisET = view.findViewById(R.id.paratiriseisET);
        egxiseisET = view.findViewById(R.id.egxiseisET);

        vitCH = view.findViewById(R.id.vitCH);
        carnitineCH = view.findViewById(R.id.carnitineCH);
        alphaCH = view.findViewById(R.id.alphaCH);
        epoCH = view.findViewById(R.id.epoCH);
        epoAlphaCh = view.findViewById(R.id.epoAlphaCH);
        epoZetaCH = view.findViewById(R.id.epoZetaCH);
        darbepoetinCH = view.findViewById(R.id.darbepoetinCH);
        parcalcitolCH = view.findViewById(R.id.parcalcitolCH);
        sidirosCH = view.findViewById(R.id.sidirosCH);


        olddateTV = view.findViewById(R.id.olddateTV);
        oldartPiesiTV = view.findViewById(R.id.oldartPiesiTV);
        oldsfikseisTV = view.findViewById(R.id.oldsfikseisTV);
        oldthermokrasiaTV = view.findViewById(R.id.oldthermokrasiaTV);
        oldroiTV = view.findViewById(R.id.oldroiTV);
        oldflevTV = view.findViewById(R.id.oldflevTV);
        oldartTV = view.findViewById(R.id.oldartTV);
        oldiperdiithisiTV = view.findViewById(R.id.oldiperdiithisiTV);
        oldagogimotitaTV = view.findViewById(R.id.oldagogimotitaTV);
        oldhco3TV = view.findViewById(R.id.oldhco3TV);
        oldparemvaseisTV = view.findViewById(R.id.oldparemvaseisTV);
        oldparatiriseisTV = view.findViewById(R.id.oldparatiriseisTV);
        oldEgxiseisTV = view.findViewById(R.id.oldEgxiseisTV);

        oldvitCH = view.findViewById(R.id.oldvitCH);
        oldcarnitineCH = view.findViewById(R.id.oldcarnitineCH);
        oldalphaCH = view.findViewById(R.id.oldalphaCH);
        oldepoCH = view.findViewById(R.id.oldepoCH);
        oldEpoAlphaCh = view.findViewById(R.id.oldepoAlphaCH);
        oldEpoZetaCH = view.findViewById(R.id.oldEpoZetaCH);
        oldDarbepoetinCH = view.findViewById(R.id.oldDarbepoetinCH);
        oldparcalcitolCH = view.findViewById(R.id.oldparcalcitolCH);
        oldsidirosCH = view.findViewById(R.id.oldsidirosCH);


        updateButton = view.findViewById(R.id.updateButton);



    }





    private void updateButtonListener(){



        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentDateTimeTV.setText(Utils.getCurrentDate() + " " + Utils.getCurrentTime());


                InputMethodManager imm = (InputMethodManager) CTX.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

            //    clearAllFocus();

                if (Utils.isNetworkAvailable2(CTX)) {
                    nameJson = new ArrayList<>();
                    valuesJson = new ArrayList<>();
                    getTexts();

                    updateButton.startAnimation();

                    if (id == null || id.equals(""))
                        insertInfo();
                    else
                        getAlertDialog();

                }

            }
        });
    }



    private void insertInfo(){
        if (transgroupID != null && !transgroupID.equals("0") ) {


          //  date = currentDateTimeTV.getText().toString();

            AsyncTaskUpdate_JSON task = new AsyncTaskUpdate_JSON(CTX, transgroupID, "Nursing_Hemodialysis_2_MEDIT",
                    nameJson, Utils.replaceTrueOrFalse(valuesJson), null);

            task.listener = new AsyncGetUpdate_JSON() {
                @Override
                public void update_JSON(String str) {
                    if (str.equals(getString(R.string.successful_update))) {

                        Utils.timeHandlerDoneButton(updateButton, CTX);
                        getTeleutaiaMetrisi();
                        getCurrentContinuousMeasurement();  // ΤΟ ΞΑΝΑΤΡΕΧΩ ΕΠΕΙΔΗ ΔΕΝ ΕΧΩ ΤΟ ID

                    } else {
                        Utils.timeHandlerErrorButton(updateButton, CTX);
                        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void getIDofInsert(String id) {

                }
            };
            task.execute();

        } else{
            updateButton.revertAnimation();
            Toast.makeText(CTX, "Δεν μπορείτε να κάνετε ενημέρωση χωρίς ασθενή", Toast.LENGTH_SHORT).show();

        }
    }


    private void updateInfo(){



        if (id != null) {


            AsyncTaskUpdate_JSON  task = new AsyncTaskUpdate_JSON(CTX, id, transgroupID, "Nursing_Hemodialysis_2_MEDIT",
                    nameJson, Utils.replaceTrueOrFalse(valuesJson), null);

            task.listener = new AsyncGetUpdate_JSON() {
                @Override
                public void update_JSON(String str) {
                    if (str.equals(getString(R.string.successful_update))) {

                        Utils.timeHandlerDoneButton(updateButton, CTX);
                        getTeleutaiaMetrisi();
                        //getCurrentContinuousMeasurement();  // ΤΟ ΞΑΝΑΤΡΕΧΩ ΕΠΕΙΔΗ ΔΕΝ ΕΧΩ ΤΟ ID

                    } else {
                        Utils.timeHandlerErrorButton(updateButton, CTX);
                        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void getIDofInsert(String id) {

                }
            };
            task.execute();



                }
            };



    private void getCurrentContinuousMeasurement(){

        if (Utils.isNetworkAvailable(CTX)) {

            String query =
                    " select * from Nursing_Hemodialysis_2_MEDIT where TransGroupid =  " + transgroupID +
                            " order by date desc";


            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = CTX.getApplicationContext();
            task.query = query;
            task.listener = new AsyncCompleteTask() {
                @Override
                public void taskComplete(JSONArray results) throws JSONException {

                    JSONObject metrisi = results.getJSONObject(0);
                    if (!metrisi.has("status")){ // TO STATUS EINAI OTAN DEN IPARXOUN STOIXEIA

                        id = String.valueOf(metrisi.getInt("ID"));

                        //Toast.makeText(CTX, id, Toast.LENGTH_SHORT).show();

                    }

                  //  timeHandlerDoneButton();

                }

                @Override
                public void taskCompleteGetVardies(JSONArray results) throws JSONException {

                }
            };
            task.execute();

        } else
            Toast.makeText(CTX, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
    }





    private void getTexts(){

        date = Utils.checkZeroAndComma(dateTV.getText().toString().trim());
        artPiesi = Utils.checkZeroAndComma(artPiesiET.getText().toString().trim());
        sfikseis = Utils.checkZeroAndComma(sfikseisET.getText().toString().trim());
        thermokrasia = Utils.checkZeroAndComma(thermokrasiaET.getText().toString().trim());
        art = Utils.checkZeroAndComma(artET.getText().toString().trim());
        roi = Utils.checkZeroAndComma(roiET.getText().toString().trim());
        flev = Utils.checkZeroAndComma(flevET.getText().toString().trim());
        iperdiithisi = Utils.checkZeroAndComma(iperdiithisiET.getText().toString().trim());
        agogimotita = Utils.checkZeroAndComma(agogimotitaET.getText().toString().trim());
        hco3 = Utils.checkZeroAndComma(hco3ET.getText().toString().trim());
        paremvaseis = Utils.checkNull(paremvaseisET.getText().toString());
        paratiriseis = Utils.checkZeroAndComma(paratiriseisET.getText().toString().trim());
        egxiseis = Utils.checkZeroAndComma(egxiseisET.getText().toString().trim());

        vit = Utils.getChecked(vitCH.isChecked());
        carnitine = Utils.getChecked(carnitineCH.isChecked());
        alpha = Utils.getChecked(alphaCH.isChecked());
        epo = Utils.getChecked(epoCH.isChecked());
        epoAlpha = Utils.getChecked(oldEpoAlphaCh.isChecked());
        epoZeta = Utils.getChecked(epoZetaCH.isChecked());
        epoDarbepoetin = Utils.getChecked(darbepoetinCH.isChecked());
        parcalcitol =  Utils.getChecked(vitCH.isChecked());
        sidiros = Utils.getChecked(sidirosCH.isChecked());

        nameJson.add("date"); nameJson.add("art_piesi"); nameJson.add("sfikseis");
        nameJson.add("thermokrasia");
        nameJson.add("roi"); nameJson.add("piesi_flev"); nameJson.add("piesi_art");
        nameJson.add("iperd"); nameJson.add("agogimotita"); nameJson.add("hco3");
        nameJson.add("paremvaseis"); nameJson.add("paratiriseis"); nameJson.add("egxiseis");
        nameJson.add("vit_b"); nameJson.add("carnitine");
        nameJson.add("alphacalcidol"); nameJson.add("epo");
        nameJson.add("epo_alpha"); nameJson.add("epo_zeta"); nameJson.add("epo_darbepoetin");
        nameJson.add("paricalcitol"); nameJson.add("sidiros");
        nameJson.add("UserID");


        valuesJson.add(Utils.convertDateTomilliseconds(Utils.getCurrentDate())); valuesJson.add(artPiesi); valuesJson.add(sfikseis);
        valuesJson.add(thermokrasia);
        valuesJson.add(roi); valuesJson.add(flev); valuesJson.add(art);
        valuesJson.add(iperdiithisi); valuesJson.add(agogimotita); valuesJson.add(hco3);
        valuesJson.add(paremvaseis); valuesJson.add(paratiriseis); valuesJson.add(egxiseis);
        valuesJson.add(vit); valuesJson.add(carnitine);
        valuesJson.add(alpha); valuesJson.add(epo);
        valuesJson.add(epoAlpha); valuesJson.add(epoZeta); valuesJson.add(epoDarbepoetin);
        valuesJson.add(parcalcitol); valuesJson.add(sidiros);
        valuesJson.add(Utils.getUserID(CTX));


    }



    private void clearOldTexts(){


        olddateTV.setText("");
        oldartPiesiTV.setText("");
        oldsfikseisTV.setText("");
        oldthermokrasiaTV.setText("");
        oldroiTV.setText("");
        oldflevTV.setText("");
        oldartTV.setText("");
        oldiperdiithisiTV.setText("");
        oldagogimotitaTV.setText("");
        oldhco3TV.setText("");
        oldparemvaseisTV.setText("");
        oldparatiriseisTV.setText("");
        oldEgxiseisTV.setText("");

        oldvitCH.setChecked(false);
        oldcarnitineCH.setChecked(false);
        oldalphaCH.setChecked(false);
        oldepoCH.setChecked(false);
        oldalphaCH.setChecked(false);
        oldEpoZetaCH.setChecked(false);
        oldDarbepoetinCH.setChecked(false);
        oldparcalcitolCH.setChecked(false);
        oldsidirosCH.setChecked(false);


    }

    private void clearTexts(){


        dateTV.setText("");
        artPiesiET.setText("");
        sfikseisET.setText("");
        thermokrasiaET.setText("");
        roiET.setText("");
        flevET.setText("");
        artET.setText("");
        iperdiithisiET.setText("");
        agogimotitaET.setText("");
        hco3ET.setText("");
        paremvaseisET.setText("");
        paratiriseisET.setText("");
        egxiseisET.setText("");

        vitCH.setChecked(false);
        carnitineCH.setChecked(false);
        alphaCH.setChecked(false);
        epoCH.setChecked(false);
        parcalcitolCH.setChecked(false);
        sidirosCH.setChecked(false);



    }

//    private void clearAllFocus(){
//
//        sapET.clearFocus(); ufET.clearFocus(); sfikseisET.clearFocus(); artPiesiET.clearFocus();  sakxaroET.clearFocus();
//        ktvET.clearFocus(); roiAimatosET.clearFocus();roiIpokET.clearFocus();  agogimotitaET.clearFocus();epanakikloforiaET.clearFocus();
//        dapET.clearFocus(); ufhET .clearFocus(); tmpET.clearFocus(); flevPiesiET.clearFocus(); fisOrosET.clearFocus();
//    }

    private void  getAlertDialog(){

        new FancyAlertDialog.Builder(getActivity())
                .setTitle("Η συγκεκριμένη μέτρηση υπάρχει ηδη")
                .setBackgroundColor(Color.parseColor("#ecae10"))  //Don't pass R.color.colorvalue
                .setMessage("Ενημέρωση της υπάρχουσας συνεχής μέτρησης; ")
                .setNegativeBtnText("Ακύρωση")
                .setPositiveBtnBackground(Color.parseColor("#ecae10"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("ΟΚ")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(false)
                .setIcon(R.drawable.error_icon,Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                        updateInfo();
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                        updateButton.revertAnimation();

                    }
                })
                .build();
    }





}
