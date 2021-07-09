package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities.Nephroxenia_Main_Activity;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContinuousMeasurementsFragment_OLD extends Fragment  {

    private View view;
    public TextView currentDateTimeTV;
    private EditText sapET, ufET, sfikseisET ,  artPiesiET ,  sakxaroET, ktvET , roiAimatosET  ,roiIpokET  ,agogimotitaET ,epanakikloforiaET,
                        dapET, ufhET , tmpET, flevPiesiET , fisOrosET;

//    private TextView sapoldET, ufoldET, sfikseisoldET ,  artPiesioldET ,  sakxarooldET, ktvoldET , roiAimatosoldET  ,
//            roiIpokoldET  ,agogimotitaoldET ,epanakikloforiaoldET,
//            dapoldET, ufholdET , tmpoldET, flevPiesioldET , fisOrosoldET;

    private String  transgroupID , date, sap, uf, sfikseis ,  artPiesi ,  sakxaro, ktv , roiAimatos  ,roiIpok  ,agogimotita ,epanakikloforia,
            dap, ufh , tmp, flevPiesi,  fisOros;

    public String id, patientid,companyID;
    private CircularProgressButton updateButton;
    private String currentSpinnerPatient;
    private Bitmap bitmap;
    private Context CTX;
    private Nephroxenia_Main_Activity main;


    public ContinuousMeasurementsFragment_OLD() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_continuous_measurements, container, false);

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

//        if (Utils.isNetworkAvailable(CTX)) {
//
//            String query = "select  top 1   ni.* " +
//                    " from TransGroup tg " +
//                    " inner join Nursing_Hemodialysis ni on tg.id = ni.TransGroupID " +
//                    " where  tg.PatientID = " + patientid +
//                    " order by tg.DateIn desc";
//
//            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
//            task.ctx = CTX;
//            task.query = query;
//            task.listener = new AsyncCompleteTask() {
//                @Override
//                public void taskComplete(JSONArray results) throws JSONException {
//
//                    if (results != null) {
//
//
//                        JSONObject telMetriseis = results.getJSONObject(0);
//
//                        sapoldET.setText(Utils.convertObjToString(telMetriseis.get("sap")));
//                        ufoldET.setText(Utils.convertObjToString(telMetriseis.get("uf")));
//                        sfikseisoldET.setText(Utils.convertObjToString(telMetriseis.get("sfixeis")));
//                        artPiesioldET.setText(Utils.convertObjToString(telMetriseis.get("art_piesi")));
//
//                        sakxarooldET.setText(Utils.convertObjToString(telMetriseis.get("sakxaro")));
//                        ktvoldET.setText(Utils.convertObjToString(telMetriseis.get("kt_v")));
//                        roiAimatosoldET.setText(Utils.convertObjToString(telMetriseis.get("roi_aimatos")));
//                        roiIpokoldET.setText(Utils.convertObjToString(telMetriseis.get("roi_ipokatastatou")));
//
//                        agogimotitaoldET.setText(Utils.convertObjToString(telMetriseis.get("agogimotita")));
//                        epanakikloforiaoldET.setText(Utils.convertObjToString(telMetriseis.get("epanakikloforia")));
//                        dapoldET.setText(Utils.convertObjToString(telMetriseis.get("dap")));
//                        ufholdET.setText(Utils.convertObjToString(telMetriseis.get("uf")));
//
//                        tmpoldET.setText(Utils.convertObjToString(telMetriseis.get("tmp")));
//                        flevPiesioldET.setText(Utils.convertObjToString(telMetriseis.get("flev_piesi")));
//                        fisOrosoldET.setText(Utils.convertObjToString(telMetriseis.get("fisiologikos_oros")));
//
//
//                    }
//                }
//
//                @Override
//                public void taskCompleteGetVardies(JSONArray results) throws JSONException {
//
//                }
//            };
//
//            task.execute();
    //    }

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
                if (main.getCurrentFocus() != null)
                  inputMethodManager.hideSoftInputFromWindow(main.getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

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

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }






    private void initializeElements(){


        currentDateTimeTV = view.findViewById(R.id.currentDateTimeTV);

        sapET = view.findViewById(R.id.sapET);
        ufET = view.findViewById(R.id.ufET);
        sfikseisET = view.findViewById(R.id.sfikseisET);
        artPiesiET = view.findViewById(R.id.artPiesiET);
        sakxaroET = view.findViewById(R.id.sakxaroET);
        ktvET = view.findViewById(R.id.ktvET);
        roiAimatosET = view.findViewById(R.id.roiAimatosET);
        roiIpokET = view.findViewById(R.id.roiIpokET);
        agogimotitaET = view.findViewById(R.id.agogimotitaET);
        epanakikloforiaET = view.findViewById(R.id.epanakikloforiaET);
        dapET = view.findViewById(R.id.dapET);
        ufhET = view.findViewById(R.id.ufhET);
        tmpET = view.findViewById(R.id.tmpET);
        flevPiesiET = view.findViewById(R.id.flevPiesiET);
        fisOrosET = view.findViewById(R.id.fisOrosET);

//        sapoldET = view.findViewById(R.id.sapoldET);
//        ufoldET = view.findViewById(R.id.ufoldET);
//        sfikseisoldET = view.findViewById(R.id.sfikseisoldET);
//        artPiesioldET = view.findViewById(R.id.artPiesioldET);
//        sakxarooldET = view.findViewById(R.id.sakxarooldET);
//        ktvoldET = view.findViewById(R.id.ktvoldET);
//        roiAimatosoldET = view.findViewById(R.id.roiAimatosoldET);
//        roiIpokoldET = view.findViewById(R.id.roiIpokoldET);
//        agogimotitaoldET = view.findViewById(R.id.agogimotitaoldET);
//        epanakikloforiaoldET = view.findViewById(R.id.epanakikloforiaoldET);
//        dapoldET = view.findViewById(R.id.dapoldET);
//        ufholdET = view.findViewById(R.id.ufholdET);
//        tmpoldET = view.findViewById(R.id.tmpoldET);
//        flevPiesioldET = view.findViewById(R.id.flevPiesioldET);
//        fisOrosoldET = view.findViewById(R.id.fisOrosoldET);


        updateButton = view.findViewById(R.id.updateButton);





    }




    private void updateButtonListener(){



        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentDateTimeTV.setText(Utils.getCurrentDate() + " " + Utils.getCurrentTime());


                InputMethodManager imm = (InputMethodManager) CTX.getSystemService(INPUT_METHOD_SERVICE);
                 if (main.getCurrentFocus() != null)
                     imm.hideSoftInputFromWindow(main.getCurrentFocus().getWindowToken(), 0);

                clearAllFocus();

                if (Utils.isNetworkAvailable(CTX)) {

                    getTexts();

                    updateButton.startAnimation();

                    if (id == null)
                        insertInfo();
                    else
                        getAlertDialog();


                }
                else
                    Toast.makeText(CTX, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void insertInfo(){
        if (transgroupID != null && !transgroupID.equals("0") ) {


            date = currentDateTimeTV.getText().toString();

            String query = "insert into Nursing_Hemodialysis(sfixeis, sap, dap,uf, uf_h , sakxaro, art_piesi, flev_piesi, kt_v, tmp, " +
                    "roi_aimatos,roi_ipokatastatou, agogimotita ,fisiologikos_oros ,epanakikloforia ,Date ,TransGroupID) " +

                    "select sfixeis = " + sfikseis + ",sap = " + sap + ", dap = " + dap + " ,uf = " + uf + " ,uf_h = " + ufh + " " +
                    ",sakxaro = " + sakxaro  + " ,art_piesi = " + artPiesi + ", " +
                    "flev_piesi = " + flevPiesi + " , kt_v = " + ktv  + " ,tmp = " + tmp + " , " +
                    "roi_aimatos = " + roiAimatos  + " ,roi_ipokatastatou = " + roiIpok +
                    " , agogimotita = " + agogimotita + " , fisiologikos_oros = " + fisOros + " ,epanakikloforia = " + epanakikloforia + ", " +

                    " Date = " + "dbo.timeToNum(CONVERT(datetime,'" + date + "'" + ",103))" +
                    //" , TransGroupid = " + "32105";
                    " , TransGroupid = " + transgroupID;


            AsyncTaskUpdate task = new AsyncTaskUpdate(CTX, query);
            task.listener = new AsyncGetUpdateResult() {
                @Override
                public void update(String str) {
                    //TO APOTELESMA TO UPDATE ERXETAI EDW
                    // STO FRAGMENT ME AYTON TON TROPO GINETAI DILONETAI

                    if (str.equals("Πετυχημένη ενημέρωση")) {

                        getCurrentContinuousMeasurement();
                    }
                    else
                        timeHandlerErrorButton();

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


            date = currentDateTimeTV.getText().toString();

            String query = "update Nursing_Hemodialysis " +


                    " set sfixeis = " + sfikseis + ",sap = " + sap + ", dap = " + dap + " ,uf = " + uf + " ,uf_h = " + ufh + " " +
                    ",sakxaro = " + sakxaro  + " ,art_piesi = " + artPiesi + ", " +
                    "flev_piesi = " + flevPiesi + " , kt_v = " + ktv  + " ,tmp = " + tmp + " , " +
                    "roi_aimatos = " + roiAimatos +  " ,roi_ipokatastatou = " + roiIpok +
                    " , agogimotita = " + agogimotita + " , fisiologikos_oros = " + fisOros + " ,epanakikloforia = " + epanakikloforia + ", " +

                    " Date = " + "dbo.timeToNum(CONVERT(datetime,'" + date + "'" + ",103))" +
                    " WHERE id = " + id;


            AsyncTaskUpdate task = new AsyncTaskUpdate(CTX, query);
            task.listener = new AsyncGetUpdateResult() {
                @Override
                public void update(String str) {
                    //TO APOTELESMA TO UPDATE ERXETAI EDW
                    // STO FRAGMENT ME AYTON TON TROPO GINETAI DILONETAI

                    if (str.equals("Πετυχημένη ενημέρωση"))
                        timeHandlerDoneButton();


                    else
                        timeHandlerErrorButton();

                }
            };

            task.execute();
        } else
            Toast.makeText(CTX, "Σφάλμα κατά την ενμέρωση (error 104)", Toast.LENGTH_SHORT).show();    }


    private void getCurrentContinuousMeasurement(){

        if (Utils.isNetworkAvailable(CTX)) {

            String query =
                    " select * from Nursing_Hemodialysis where TransGroupid =  " + transgroupID +
                            " and date = (SELECT MAX(Date) FROM Nursing_Hemodialysis )";


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

                    timeHandlerDoneButton();

                }

                @Override
                public void taskCompleteGetVardies(JSONArray results) throws JSONException {

                }
            };
            task.execute();

        } else
            Toast.makeText(CTX, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
    }




    private void timeHandlerDoneButton(){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp);

                updateButton.doneLoadingAnimation(Color.GREEN, bitmap);
                Toast.makeText(CTX, "Πετυχημένη ενημέρωση", Toast.LENGTH_SHORT).show();

            }
        }, 2000);

       deMorphing();

    }

    private void timeHandlerErrorButton(){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cancel);

                updateButton.doneLoadingAnimation(Color.GREEN, bitmap);
                Toast.makeText(CTX, "Προέκυψε σφάλμα κατά την ενημέρωση", Toast.LENGTH_SHORT).show();

            }
        }, 2000);

        deMorphing();

    }

    public void deMorphing()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                updateButton.revertAnimation();
            }
        }, 3800);



    }

    private void getTexts(){

        sap = Utils.checkZeroAndComma(sapET.getText().toString().trim());

        uf = Utils.checkZeroAndComma(ufET.getText().toString().trim());
        sfikseis = Utils.checkZeroAndComma(sfikseisET.getText().toString().trim());
        artPiesi = Utils.checkZeroAndComma(artPiesiET.getText().toString().trim());
        sakxaro = Utils.checkZeroAndComma(sakxaroET.getText().toString().trim());
        ktv = Utils.checkZeroAndComma(ktvET.getText().toString().trim());
        roiAimatos = Utils.checkZeroAndComma(roiAimatosET.getText().toString().trim());
        roiIpok = Utils.checkZeroAndComma(roiIpokET.getText().toString().trim());
        agogimotita = Utils.checkZeroAndComma(agogimotitaET.getText().toString().trim());
        epanakikloforia = Utils.checkZeroAndComma(epanakikloforiaET.getText().toString().trim());
        dap = Utils.checkZeroAndComma(dapET.getText().toString().trim());
        ufh = Utils.checkZeroAndComma(ufhET.getText().toString().trim());
        tmp = Utils.checkZeroAndComma(tmpET.getText().toString().trim());
        flevPiesi = Utils.checkZeroAndComma(flevPiesiET.getText().toString().trim());
        fisOros = Utils.checkZeroAndComma(fisOrosET.getText().toString().trim());
    }


    private void clearTexts(){


        sapET.setText("");
        ufET.setText("");
        sfikseisET.setText("");
        artPiesiET.setText("");
        sakxaroET.setText("");
        ktvET.setText("");
        roiAimatosET.setText("");
        roiIpokET.setText("");
        agogimotitaET.setText("");
        epanakikloforiaET.setText("");

        dapET.setText("");
        ufhET.setText("");
        tmpET.setText("");
        flevPiesiET.setText("");
        fisOrosET.setText("");


    }

    private void clearAllFocus(){

        sapET.clearFocus(); ufET.clearFocus(); sfikseisET.clearFocus(); artPiesiET.clearFocus();  sakxaroET.clearFocus();
        ktvET.clearFocus(); roiAimatosET.clearFocus();roiIpokET.clearFocus();  agogimotitaET.clearFocus();epanakikloforiaET.clearFocus();
        dapET.clearFocus(); ufhET .clearFocus(); tmpET.clearFocus(); flevPiesiET.clearFocus(); fisOrosET.clearFocus();
    }

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
