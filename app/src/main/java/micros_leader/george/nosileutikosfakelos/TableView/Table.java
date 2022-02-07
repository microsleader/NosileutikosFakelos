package micros_leader.george.nosileutikosfakelos.TableView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringJoiner;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskDelete;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;
import micros_leader.george.nosileutikosfakelos.DialogFragmentSearches.DF_items_categories;
import micros_leader.george.nosileutikosfakelos.Dialogs;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetDelete;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended_str;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentMedicineCloseListener;
import micros_leader.george.nosileutikosfakelos.Listeners.SearchMedicineListener_Base;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Zotika_simeia.Zotika_Activity_Meth;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Diaitologio.DiaitologioActivity;
import micros_leader.george.nosileutikosfakelos.Permissions;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Spinner_new_Image_Adapter;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV.CHECKBOX_TYPE;
import static micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV.SPINNER_TYPE_NEW;
import static micros_leader.george.nosileutikosfakelos.StaticFields.AKERAIOS;
import static micros_leader.george.nosileutikosfakelos.StaticFields.AKERAIOS_WITH_NEGATIVE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.CHECKBOX_ITEM;
import static micros_leader.george.nosileutikosfakelos.StaticFields.CHECKBOX_TYPE_READ_ONLY_VALUE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.DEKADIKOS;
import static micros_leader.george.nosileutikosfakelos.StaticFields.DEKADIKOS_WITH_NEGATIVE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.EDITTEXT_ITEM;
import static micros_leader.george.nosileutikosfakelos.StaticFields.HOUR;
import static micros_leader.george.nosileutikosfakelos.StaticFields.KEIMENO;
import static micros_leader.george.nosileutikosfakelos.StaticFields.MULTI_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TABLE_NO_ELEMENT;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TABLE_NO_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TABLE_TYPE_LISTENER;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_CLOCK_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_DATE_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_DIETA_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_ITEM_READ_ONLY_VALUE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_LISTENER;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_MEDICINE_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_PHOTO;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_VALUE_FROM_VIEW;
import static micros_leader.george.nosileutikosfakelos.Utils.convertObjToString;

public class Table implements AsyncCompleteTask2, AsyncGetUpdate_JSON, MyDialogFragmentMedicineCloseListener, AsyncGetDelete , DataSended_str {

    private TextView infoTV;
    private EditText infoET;
    private CheckBox infoCH;
    private Spinner  infoSP;
    private TableRow firstRow;
    private final TableLayout llheader, lldetailTitles, ll;
    private final HorizontalScrollView scrollHeader, scrollDetail;
    private final ScrollView scrollVertTitle, scrollVertDetail;
    public String str_query , table ,transgroupID;
    private String column_class; //ΓΙΑ ΝΑ ΞΕΡΩ ΣΕ ΠΙΟ ΠΕΔΙΟ ΕΙΜΑΙ ΩΣΤΕ ΜΗΠΩς ΧΡΕΙΑΣΤΕΙ ΝΑ ΤΟ ΧΡΗΣΙΜΟΠΟΙΗΣΩ ΑΛΛΟΥ ΣΕ ΑΛΛΕΣ ΜΕΘΟΔΟΥΣ
    private JSONObject jsonObject_class;  //ΓΙΑ ΝΑ ΞΕΡΩ ΣΕ ΠΙΟ ΠΕΔΙΟ ΕΙΜΑΙ ΩΣΤΕ ΜΗΠΩς ΧΡΕΙΑΣΤΕΙ ΝΑ ΤΟ ΧΡΗΣΙΜΟΠΟΙΗΣΩ ΑΛΛΟΥ ΣΕ ΑΛΛΕΣ ΜΕΘΟΔΟΥΣ
    private boolean exeiWatchID; // AN IPARXEI WATCH ID DEN XREIAZETAI NA DILOSOUME PANO TITLOUS
    private boolean exeiSinolo;
    private boolean isEditable;
    private boolean needDoctor;
    private boolean hasValuesForCH;
    public boolean currentTableFR;
    public boolean plagioiTitlesAreItemIDs;

    private String date,currentUser;
    public String patientID;
    private String [] panoTitloi, plagioiTitloi ,col_names;
    public AlertDialog alertDialog;
    private ArrayList<String> nameJson , valuesJson    ,extraCols, extraValues;
    private HashMap<Integer,ArrayList<String>> arxikes_timesValuesMap ;
    private HashMap <Integer,ArrayList<String>>  valuesMap ;
    private ArrayList<TableViewItem> tableViewArraylist;
    private boolean isPinakas = false , saveTransgroupID = true , setOnlyFirstRowAvalaible = false;
    private boolean watchID_as_simpleSpinner;
    private ArrayList<String> col_namesFromViesLista;
    private TextView currentTextView;
    private final Toolbar t;
    private final static String  LOOK_UP_TEXT = "_text";
    private int currentPosRow , currentIndexOfCol;
    private  DialogFragment dialogFragment;
    public boolean editextsUsingDialogs;

    private final Activity act;
    private final Context ctx;
    private Bundle bundle;
    private Intent in;





    public Table(TableLayout ll, Toolbar t, View view, Bundle bundle, DialogFragment dialogFragment) {
        llheader = view.findViewById(R.id.displayLinearHeader);
        lldetailTitles = view.findViewById(R.id.displayLinearTitle);
        scrollHeader = view.findViewById(R.id.scrollHeader);
        scrollDetail = view.findViewById(R.id.scrollDetail);
        scrollVertTitle = view.findViewById(R.id.scrollVertTitle);
        scrollVertDetail = view.findViewById(R.id.scrollVertDetail);

        this.ll = ll;
        this.t = t;
        this.ctx = dialogFragment.getContext();
        this.bundle = bundle;
        this.act = dialogFragment.getActivity();
        this.dialogFragment = dialogFragment;

        initializeAndCreateTable();
    }

    public Table(TableLayout ll, Toolbar t,  Intent in  , Activity act) {
        llheader = act.findViewById(R.id.displayLinearHeader);
        lldetailTitles = act.findViewById(R.id.displayLinearTitle);
        scrollHeader = act.findViewById(R.id.scrollHeader);
        scrollDetail = act.findViewById(R.id.scrollDetail);
        scrollVertTitle = act.findViewById(R.id.scrollVertTitle);
        scrollVertDetail = act.findViewById(R.id.scrollVertDetail);

        this.ll = ll;
        this.t = t;
        this.ctx = act;
        this.in = in;
        this.act = act;

        initializeAndCreateTable();
    }





    private void initializeAndCreateTable(){
        initialize();
        initializeParameters();

        if (isEditable)
            thereIsImageUpdateButton();

        if (tableViewArraylist == null) {


            if (col_names != null && plagioiTitloi != null) {


                if (col_names.length == plagioiTitloi.length) {
                    if (Utils.isNetworkAvailable2(ctx))
                        getDataForTable();
                } else
                    Toast.makeText(ctx, "Ο αριθμός πλάγιων τίτλων και col_names δεν είναι ίδιος", Toast.LENGTH_SHORT).show();

            } else if (col_names != null && plagioiTitloi == null) {

                if (Utils.isNetworkAvailable2(ctx))
                    getDataForTable();
            }

        }

        else{
            if (panoTitloi != null && plagioiTitloi !=null)
                isPinakas = true;

            try {
                if (Utils.isNetworkAvailable2(ctx))
                    getDataForTable();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }




    }

    private void initialize(){



        firstRow = new TableRow(ctx);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        firstRow.setLayoutParams(lp);
        alertDialog = Utils.setLoadingAlertDialog(act);
        alertDialog.setCancelable(true);
        alertDialog.show();

        nameJson = new ArrayList<>();
        valuesJson = new ArrayList<>();
        arxikes_timesValuesMap = new HashMap<>();
        valuesMap = new HashMap<>();
        col_namesFromViesLista = new ArrayList<>();
        currentUser = Utils.getUserID(ctx);


        //ΓΙΑ ΣΥΓΧΡΟΝΙΣΜΟ ΤΩΝ ΔΥΟ ΟΡΙΖΟΝΤΙΩΝ SCROLLVIEW
        if (scrollHeader != null && scrollDetail != null  ) {
            scrollDetail.getViewTreeObserver().addOnScrollChangedListener(() -> scrollHeader.smoothScrollTo(scrollDetail.getScrollX(), 0));
            scrollVertDetail.getViewTreeObserver().addOnScrollChangedListener(() -> scrollVertTitle.smoothScrollTo( 0,scrollVertDetail.getScrollY()));
        }



    }




    private void initializeParameters() {


        if (bundle != null)
                getParametersFromBandle();
        else if (in != null)
            getParametersFromIntent();


    }


    private void getParametersFromBandle(){
        if (bundle.containsKey("str_query")) {
            str_query = bundle.getString("str_query");
            String afterFrom = str_query.split("from")[1].trim();
            if (bundle.containsKey("table"))
                table = bundle.getString("table");
            else
                table = afterFrom.split(" ")[0].trim();
        }
        if (bundle.containsKey("date"))
            date = bundle.getString("date");

        if (bundle.containsKey("watchID_as_simpleSpinner"))
            watchID_as_simpleSpinner = bundle.getBoolean("watchID_as_simpleSpinner");


        if (bundle.containsKey("transgroupID"))
            transgroupID = bundle.getString("transgroupID");

        if (bundle.containsKey("panoTitloi"))
            panoTitloi = bundle.getStringArray("panoTitloi");
        if (bundle.containsKey("plagioiTitloi"))
            plagioiTitloi = bundle.getStringArray("plagioiTitloi");

        if (bundle.containsKey("exeiWatchID"))
            exeiWatchID = bundle.getBoolean("exeiWatchID", false);
        if (bundle.containsKey("needDoctor"))
            needDoctor = bundle.getBoolean("needDoctor", false);
        if (bundle.containsKey("exeiSinolo"))
            exeiSinolo = bundle.getBoolean("exeiSinolo", false);
        if (bundle.containsKey("editable"))
            isEditable = bundle.getBoolean("editable", false);
        if (bundle.containsKey("saveTransgroupID"))
            saveTransgroupID = bundle.getBoolean("saveTransgroupID", true);
        if (bundle.containsKey("toolbar_title"))
            t.setTitle(bundle.getString("toolbar_title"));
        if (bundle.containsKey("patientID"))
            patientID = bundle.getString("patientID");
        if (bundle.containsKey("plagioiTitlesAreItemIDs"))
            plagioiTitlesAreItemIDs = bundle.getBoolean("plagioiTitlesAreItemIDs");

        if (bundle.containsKey("hasValuesForCH"))
            hasValuesForCH = bundle.getBoolean("hasValuesForCH", false);


        if (bundle.containsKey("setOnlyFirstRowAvalaible"))
            setOnlyFirstRowAvalaible = bundle.getBoolean("setOnlyFirstRowAvalaible", false);


        if (bundle.containsKey("tableView_cols")) {
            tableViewArraylist = (ArrayList<TableViewItem>) bundle.getSerializable("tableView_cols");
            ArrayList <String> tles = new ArrayList();
            if (tableViewArraylist != null) {


                for (TableViewItem item : tableViewArraylist) {
                    nameJson.add(item.getColumn());
                    if (item.typeElement == TEXTVIEW_VALUE_FROM_VIEW) {
                        col_namesFromViesLista.add(item.getColumn());
                    }

                    if (item.getTitle() != null) {  //ΕΔΩ ΘΑ ΜΠΕΙ ΣΤΗΝ ΟΥΣΙΑ ΟΤΑΝ panoTitloi == null ΔΗΛΑΔΗ ΘΑ ΠΑΡΕΙ ΤΟΥς ΤΙΤΛΟΥΣ ΑΠΟ ΤΗΝ tableViewArraylist
                        tles.add(item.getTitle());
                    }
                }


                col_names = nameJson.toArray(new String[nameJson.size()]);
                if (panoTitloi == null) //ΑΝ ΔΕΝ ΕΧΟΥΜΕ ΔΩΣΕΙ ΛΙΣΤΑ ARRAY ΚΑΙ ΤΑ ΠΑΙΡΝΕΙ ΑΠΟ ΤΗΝ tableViewArraylist
                    panoTitloi = tles.toArray(new String[tles.size()]);
            } else {
                col_names = bundle.getStringArray("col_names");
                nameJson = new ArrayList<>(Arrays.asList(col_names));
            }

        }
    }



    private void getParametersFromIntent(){

        if (in.hasExtra("str_query")) {
            str_query = in.getStringExtra("str_query");
            String afterFrom = str_query.split("from")[1].trim();
            if (in.hasExtra("table"))
                table = in.getStringExtra("table");
            else
                 table = afterFrom.split(" ")[0].trim();
        }
        if (in.hasExtra("date"))
            date = in.getStringExtra("date");

        if (in.hasExtra("watchID_as_simpleSpinner"))
            watchID_as_simpleSpinner = in.getBooleanExtra("watchID_as_simpleSpinner",false);


        if (in.hasExtra("transgroupID"))
            transgroupID = in.getStringExtra("transgroupID");

        if (in.hasExtra("panoTitloi"))
            panoTitloi = in.getStringArrayExtra("panoTitloi");
        if (in.hasExtra("plagioiTitloi"))
            plagioiTitloi = in.getStringArrayExtra("plagioiTitloi");

        if (in.hasExtra("exeiWatchID"))
            exeiWatchID = in.getBooleanExtra("exeiWatchID",false);
        if (in.hasExtra("needDoctor"))
            needDoctor = in.getBooleanExtra("needDoctor",false);
        if (in.hasExtra("exeiSinolo"))
            exeiSinolo = in.getBooleanExtra("exeiSinolo",false);
        if (in.hasExtra("editable"))
            isEditable = in.getBooleanExtra("editable",false);
        if (in.hasExtra("saveTransgroupID"))
            saveTransgroupID = in.getBooleanExtra("saveTransgroupID",true);
        if (in.hasExtra("toolbar_title"))
            t.setTitle(in.getStringExtra("toolbar_title"));
        if (in.hasExtra("patientID"))
            patientID = in.getStringExtra("patientID");
        if (in.hasExtra("hasValuesForCH"))
            hasValuesForCH = in.getBooleanExtra("hasValuesForCH",false);
        if (in.hasExtra("plagioiTitlesAreItemIDs"))
            plagioiTitlesAreItemIDs = in.getBooleanExtra("plagioiTitlesAreItemIDs",false);

        if (in.hasExtra("setOnlyFirstRowAvalaible"))
            setOnlyFirstRowAvalaible = in.getBooleanExtra("setOnlyFirstRowAvalaible",false);



        if (in.hasExtra("tableView_cols")) {
            tableViewArraylist = (ArrayList<TableViewItem>) in.getSerializableExtra("tableView_cols");

            ArrayList <String> tles = new ArrayList();

            for (TableViewItem item : tableViewArraylist) {
                nameJson.add(item.getColumn());
                if (item.typeElement == TEXTVIEW_VALUE_FROM_VIEW){
                    col_namesFromViesLista.add(item.getColumn());
                }
                if (item.getTitle() != null)
                    tles.add(item.getTitle());
            }

            col_names = nameJson.toArray(new String[nameJson.size()]);
            if (panoTitloi == null) {
                panoTitloi = tles.toArray(new String[tles.size()]);
            }
        }

        else  {
            col_names = in.getStringArrayExtra("col_names");
            nameJson =  new ArrayList<String>(Arrays.asList(col_names));
        }

    }

    public void set_editextsUsingDialogs (boolean x){
        editextsUsingDialogs = x;
    }

    private void addTheFirstBlankTextView(){


        TextView blankTV = new TextView(ctx);
        blankTV.setText("");
        firstRow.addView(blankTV);

    }


    @SuppressLint("SetTextI18n")
    private void addPanoTitles(String [] titles){

        if (plagioiTitloi !=null) {
            addTheFirstBlankTextView();
        }


        if (tableViewArraylist != null ){
            for (int i = 0; i < tableViewArraylist.size(); i++)
                if (tableViewArraylist.get(i).getTypeElement() != TABLE_NO_ELEMENT && plagioiTitlesAreItemIDs) {
                    if (i <= titles.length)
                        createTitleTV(titles[i] );
                }
            else if (i < titles.length && !plagioiTitlesAreItemIDs)
                if (!titles[i].toLowerCase().equals("id") && !titles[i].toLowerCase().equals("userid")  && !titles[i].toLowerCase().equals("bedid") )
                    createTitleTV(titles[i]);
        }
        else {
            for (int i = 0; i < titles.length; i++) {
                if (!titles[i].toLowerCase().equals("id") && !titles[i].toLowerCase().equals("userid") && !titles[i].toLowerCase().equals("bedid")) {
                    createTitleTV(titles[i]);
                }
            }
        }


        if (llheader != null) {
            llheader.removeAllViews();
            llheader.addView(firstRow);
        }
    }

    @SuppressLint("SetTextI18n")
    private void createTitleTV(String title){
        infoTV = new TextView(ctx);
        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.setMarginStart(10);
        infoTV.setLayoutParams(lp);

        infoTV.setGravity(Gravity.CENTER);
        infoTV.setBackgroundResource(R.drawable.edittext_table);
        infoTV.setText(" " + title + " ");

        firstRow.addView(infoTV);
    }



    private void addPlagiousTitlesAndValues(String[] plagioiTitloi, String[] col_names, JSONArray results) {

        double [] [] valuesForTotal= new double[plagioiTitloi.length][results.length()];

        int rowID =0;  //ΤΟ ΕΒΑΛΑ ΕΠΕΙΔΗ ΥΠΑΡΧΕ ΘΕΜΑ ΤΗ ΝΕΥΡΟΛΟΓΙΚΗ ΑΞΙΟΛΟΓΗΣΗ ΟΤΑΝ ΤΑ ΑΠΟΤΕΛΕΣΜΑΤΑ ΗΤΑΝ ΛΙΓΟΤΕΡΑ ΑΠΟ ΤΟΥΣ ΠΛΑΓΙΟΥΣ ΤΙΤΛΟΥΣ
        for (int rowIndex = 0; rowIndex < plagioiTitloi.length; rowIndex++) {

            rowID ++;
            // ΤΟΠΟΘΕΤΗΣΗ ΠΛΑΓΙΟΥ ΤΙΤΛΟΥ
            TableRow row = getRowWithLP(rowID,"0"); //ΤΟ ΕΒΑΛΑ ΕΠΕΙΔΗ ΥΠΑΡΧΕ ΘΕΜΑ ΤΗ ΝΕΥΡΟΛΟΓΙΚΗ ΑΞΙΟΛΟΓΗΣΗ ΟΤΑΝ ΤΑ ΑΠΟΤΕΛΕΣΜΑΤΑ ΗΤΑΝ ΛΙΓΟΤΕΡΑ ΑΠΟ ΤΟΥΣ ΠΛΑΓΙΟΥΣ ΤΙΤΛΟΥΣ
            // TableRow row = getRowWithLP(results.getJSONObject(rowIndex).getInt("ID"));
            TableRow.LayoutParams lp = getLayoutsParams();
            infoTV = getTextview(lp, plagioiTitloi[rowIndex]); // layoutParameters , onomasia
            row.addView(infoTV);
            valuesJson = new ArrayList<>();

//----------------------------


            for (int colIndex = 0; colIndex < results.length(); colIndex++) {

                JSONObject jsonObject = null;

                try {
                    jsonObject = results.getJSONObject(colIndex);

                    boolean sameUser = true;
                    String userID = Utils.convertObjToString(jsonObject.get("UserID"));
                    if (!userID.equals(currentUser) && !userID.equals(""))
                        sameUser = false;


                    String  value = convertObjToString(jsonObject.get(col_names[rowIndex]));

                    // ΠΡΟΣΘΕΣΗ ΤΩΝ ΑΠΟΤΕΛΕΣΜΑΤΩΝ ΠΟΥ ΕΧΟΥΝ ΑΠΟΘΗΚΕΥΤΕΙ ΣΕ ΔΥΣΔΙΑΣΤΑΤΟ ΠΙΝΑΚΑ
                    if (exeiSinolo) {
                        if (value.equals(""))
                            value = "0";

                        valuesForTotal[rowIndex][colIndex] = Double.parseDouble(value);
                    }


                    // ΕΔΩ ΑΝ ΤΟΥ ΕΧΟΥΜΕ ΔΩΣΕΙ ΛΙΣΤΑ ΜΕ ΤΑΒΛΕ ΙΤΕΜΣ STO ACTIVITY
                    if (tableViewArraylist != null) {

                        int typeElement = tableViewArraylist.get(colIndex).getTypeElement();
                        int textType = tableViewArraylist.get(colIndex).getTextType();
                        ArrayList<Spinner_item> lista = tableViewArraylist.get(colIndex).getSpinnerLista();

                        checkTypesAndAddViewToRow( row,typeElement,textType ,lp, value, rowIndex, colIndex ,lista,sameUser);


                    }

                    else {

                        // ΕΔΩ ΑΝ ΕΧΟΥΜΕ ΔΩΣΕΙ ΑΠΛΗ ΛΙΣΤΑ ΜΕ COLUMNS STO ACTIVITY
                        if (!col_names[rowIndex].toLowerCase().equals("id") && !col_names[rowIndex].equalsIgnoreCase("userID"))
                            infoTV = getTextview(lp, value);
                        row.addView(infoTV);

                    }

                   addValueToValueList(colIndex,value);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            ll.addView(row);
            arxikes_timesValuesMap.put(rowIndex,valuesJson);
            valuesMap.put(rowIndex,(ArrayList<String>)valuesJson.clone());


        }



        if (exeiSinolo)
            setTotal(valuesForTotal, true);
    }





    private void addPinakasPanoPlagioiTitloi(String[] plagioiTitloi, String[] col_names, JSONArray results) throws JSONException {

        double [] [] valuesForTotal= new double[plagioiTitloi.length][results.length()];
        HashMap <Integer,ArrayList<String>> tempHorizontalMap = new HashMap<>();
        ArrayList <String> tempHorList;

        int rowID =0;
        int col_index =0;
        lldetailTitles.removeAllViews();



        if (isEditable){

            JSONObject x = results.getJSONObject(0);
            JSONObject clone = new JSONObject(x.toString());
            for (int i=0; i<clone.length(); i++){
                clone.put(clone.names().getString(i),"");
            }
            results.put(results.length()  ,clone);

        }

        for (int rowIndex = 0; rowIndex < plagioiTitloi.length; rowIndex++) {
            tempHorList = new ArrayList<>();
            rowID ++;
            // ΤΟΠΟΘΕΤΗΣΗ ΠΛΑΓΙΟΥ ΤΙΤΛΟΥ
            TableRow row = getRowWithLP(rowID,"0"); //ΤΟ ΕΒΑΛΑ ΕΠΕΙΔΗ ΥΠΑΡΧΕ ΘΕΜΑ ΤΗ ΝΕΥΡΟΛΟΓΙΚΗ ΑΞΙΟΛΟΓΗΣΗ ΟΤΑΝ ΤΑ ΑΠΟΤΕΛΕΣΜΑΤΑ ΗΤΑΝ ΛΙΓΟΤΕΡΑ ΑΠΟ ΤΟΥΣ ΠΛΑΓΙΟΥΣ ΤΙΤΛΟΥΣ
            TableRow titlerow = getRowWithLP(rowID,"0"); //ΤΟ ΕΒΑΛΑ ΕΠΕΙΔΗ ΥΠΑΡΧΕ ΘΕΜΑ ΤΗ ΝΕΥΡΟΛΟΓΙΚΗ ΑΞΙΟΛΟΓΗΣΗ ΟΤΑΝ ΤΑ ΑΠΟΤΕΛΕΣΜΑΤΑ ΗΤΑΝ ΛΙΓΟΤΕΡΑ ΑΠΟ ΤΟΥΣ ΠΛΑΓΙΟΥΣ ΤΙΤΛΟΥΣ
            // TableRow row = getRowWithLP(results.getJSONObject(rowIndex).getInt("ID"));

            TableRow.LayoutParams lp = getLayoutsParams();
            if ( plagioiTitloi[rowIndex] != null &&  !plagioiTitloi[rowIndex].equalsIgnoreCase("id")) {
                infoTV = getTextview(lp, plagioiTitloi[rowIndex]); // layoutParameters , onomasia
                titlerow.addView(infoTV);
                lldetailTitles.addView(titlerow);
            }


            valuesJson = new ArrayList<>();

//----------------------------




            try {




                JSONObject jsonObject;

                for (int colIndex = 0; colIndex < results.length() ; colIndex++) {

                    col_index = colIndex;
                    jsonObject = results.getJSONObject(colIndex);
                    jsonObject_class = jsonObject;   //ΓΙΑ ΝΑ ΞΕΡΩ ΣΕ ΠΙΟ ΠΕΔΙΟ ΕΙΜΑΙ ΩΣΤΕ ΜΗΠΩς ΧΡΕΙΑΣΤΕΙ ΝΑ ΤΟ ΧΡΗΣΙΜΟΠΟΙΗΣΩ ΑΛΛΟΥ ΣΕ ΑΛΛΕΣ ΜΕΘΟΔΟΥΣ

                    boolean sameUser = true;

                    if (jsonObject.has("UserID") || jsonObject.has("userID")) {
                        String userID = Utils.convertObjToString(jsonObject.opt("UserID"));
                        if (!userID.equals(currentUser) && !userID.equals(""))
                            sameUser = false;

                    }


                    String column = col_names[rowIndex];
                    column_class = column;                    //ΓΙΑ ΝΑ ΞΕΡΩ ΣΕ ΠΙΟ ΠΕΔΙΟ ΕΙΜΑΙ ΩΣΤΕ ΜΗΠΩς ΧΡΕΙΑΣΤΕΙ ΝΑ ΤΟ ΧΡΗΣΙΜΟΠΟΙΗΣΩ ΑΛΛΟΥ ΣΕ ΑΛΛΕΣ ΜΕΘΟΔΟΥΣ
                    String value;


                    int TYPE_ELEMENT = 0;

                    if (tableViewArraylist != null)
                        TYPE_ELEMENT = tableViewArraylist.get(rowIndex).getTypeElement();

                    //-------------
                    if (col_names[rowIndex].toLowerCase().contains("date"))
                        value = Utils.convertMillisecondsToDateTime(convertObjToString(jsonObject.opt(column)));
                    else if (TYPE_ELEMENT == TEXTVIEW_CLOCK_TYPE ||
                            //col_names[colIndex].toLowerCase().contains("hour") ||
                            col_names[rowIndex].toLowerCase().contains("time") ||
                            col_names[rowIndex].toLowerCase().equals("duration")) {

                        value = Utils.convertMilliSecondsToTime(convertObjToString(jsonObject.opt(column)));

                    }

//                    else if (TYPE_ELEMENT == SPINNER_ITEM_NEW && tableViewArraylist.get(colIndex).lookup_query != null ){
//                        value = jsonObject.optString(col_names[colIndex] + LOOK_UP_TEXT);
//                    }

                    else
                        value = convertObjToString(jsonObject.opt(col_names[rowIndex]));

                    //-------------
                    value = checkEksereseis(column, value);




                    // ΕΔΩ ΑΝ ΤΟΥ ΕΧΟΥΜΕ ΔΩΣΕΙ ΛΙΣΤΑ ΜΕ ΤΑΒΛΕ ΙΤΕΜΣ STO ACTIVITY
                    if (tableViewArraylist != null) {

                        String title = tableViewArraylist.get(rowIndex).getTitle();
                        if (title == null || title.isEmpty())
                            title = panoTitloi[rowIndex];
                        int typeElement = tableViewArraylist.get(rowIndex).getTypeElement();
                        int textType = tableViewArraylist.get(rowIndex).getTextType();
                        ArrayList<Spinner_item> lista = tableViewArraylist.get(rowIndex).getSpinnerLista();


                        //TODO:(ΔΕΝΕ ΙΝΑΙ ΓΙΑ ΦΤΙΑΞΙΜΟ ΑΠΛΑ ΝΑ ΦΑΙΝΕΤΑΙ ΚΑΛΥΤΕΡΑ ΕΔΩ ΦΑΙΝΕΤΑΙ ΑΝΑΛΟΓΑ ΤΙΣ ΠΑΡΑΜΕΤΡΟΥΣ ΤΙ ΘΑ ΕΜΦΑΝΙΣΤΕΙ ΣΤΗ ΓΡΑΜΜΗ

                        //----------------------------------------------------------------------------------
                        //ΓΙΑ ΑΡΧΗ ΘΑ ΠΡΕΠΕΙ ΝΑ ΕΧΟΥΜΕ ΔΩΣΕΙ ΕΝΤΟΛΗ ΜΟΝΟ Η ΠΡΩΤΗ ΓΡΑΜΜΗ ΝΑ ΕΙΝΑΙ ΔΙΑΘΕΣΙΜΗ ΣΤΟΝ ΧΡΗΣΤΗ ΓΙΑ ΑΛΛΑΓΗ

                        if (setOnlyFirstRowAvalaible && colIndex != 0 ) {
                            //ΜΟΝΟ ΣΤΑ ΣΥΓΚΕΝΤΡΩΤΙΚΑ ΣΥΝΕΧΩΝ ΩΣΤΕ ΜΟΝΟ Η ΠΙΟ ΠΡΟΣΦΑΤΗ ΚΑΤΑΧΩΡΗΣΗ ΝΑ ΕΙΝΑΙ ΕΠΕΞΕΡΓΑΣΙΜΗ
                            // ΕΔΩ ΜΠΑΙΝΕΙ ΣΕ ΟΛΑ ΕΚΤΟΣ ΤΗΣ   ΠΡΩΤΗΣ ΚΑΙ ΤΕΛΕΥΤΑΙΑΣ ΓΡΑΜΜΗΣ ΟΤΑΝ ΕΙΝΑΙ   setOnlyFirstRowAvalaible = TRUE


                            if (typeElement != TABLE_NO_ELEMENT) {

                                if (TYPE_ELEMENT == SPINNER_TYPE_NEW && tableViewArraylist.get(rowIndex).lookup_query == null
                                        && tableViewArraylist.get(rowIndex).spinnerLista != null && value != null && !value.isEmpty()) {

                                    value = tableViewArraylist.get(rowIndex).spinnerLista.get(Integer.parseInt(value)).name;

                                } else if (TYPE_ELEMENT == SPINNER_TYPE_NEW && tableViewArraylist.get(rowIndex).lookup_query != null) {
                                    value = jsonObject.optString(col_names[rowIndex] + LOOK_UP_TEXT);
                                }


                                infoET = getEditText(lp, value, colIndex, rowIndex, textType, sameUser);
                                infoET.setEnabled(false);
                                infoET.setTextColor(act.getResources().getColor(R.color.cyan2));
                                //      infoET.setText("xaxaxaxax");
                                row.addView(infoET);
                            }
                        } else if (isEditable)
                            if (TYPE_ELEMENT == SPINNER_TYPE_NEW && tableViewArraylist.get(rowIndex).lookup_query != null && !sameUser) { //ΕΔΩ ΜΠΑΙΕΝΙ ΑΝ ΕΧΟΥΜΕ ΕΝΤΙ, ΔΙΑΦΟΡΕΤΙΚΟ ΧΡΗΣΤΗ ΚΑΙ ΛΟΟΚ ΑΠ
                                value = jsonObject.optString(col_names[rowIndex] + LOOK_UP_TEXT);
                                infoTV = getTextview(lp, value);
                                row.addView(infoTV);
                            } else
                                checkTypesAndAddViewToRow(title, row, typeElement, textType, lp, value, colIndex, rowIndex, lista, sameUser);

                        else {

                            //ΕΔΩ ΟΤΑΝ ΔΕΝ ΕΧΕΙ Ο ΧΡΗΣΤΗΣ ΕΠΙΛΟΓΗ EDIT (isEditable false)
                            ArrayList<Spinner_item> spList = tableViewArraylist.get(rowIndex).spinnerLista;
                            if (typeElement != TABLE_NO_ELEMENT) {
                                if (typeElement == TEXTVIEW_MEDICINE_TYPE) {
                                    infoTV = getTextviewMedicine(lp, value, colIndex, rowIndex, sameUser);
                                    row.addView(infoTV);
                                }

                                else if (typeElement == TEXTVIEW_DIETA_TYPE) {
                                    infoTV = getTextviewDieta(lp, value, colIndex, rowIndex, sameUser);
                                    row.addView(infoTV);
                                }


                                else if (typeElement == SPINNER_TYPE_NEW && tableViewArraylist.get(rowIndex).lookup_query != null) {
                                    value = jsonObject.optString(col_names[rowIndex] + LOOK_UP_TEXT);
                                    infoET = getEditText(lp, value, colIndex, rowIndex, textType, sameUser);
                                    row.addView(infoET);
                                } else if (typeElement == SPINNER_TYPE_NEW && spList != null) {
                                    if (!value.isEmpty())
                                        value = spList.get(Integer.parseInt(value)).name;
                                    infoTV = getTextview(lp, value);
                                    row.addView(infoTV);
                                } else if (typeElement == CHECKBOX_TYPE || typeElement == CHECKBOX_TYPE_READ_ONLY_VALUE) {
                                    infoCH = getCheckbox(lp, value, colIndex, rowIndex, false, typeElement);
                                    row.addView(infoCH);
                                } else if (typeElement == MULTI_TYPE || spList != null) {
                                    infoTV = getTextviewMulti(lp, value, colIndex, rowIndex, spList, false);
                                    row.addView(infoTV);
                                } else {

                                    infoET = getEditText(lp, value, colIndex, rowIndex, textType, sameUser);
                                    row.addView(infoET);
                                }
                            }
                        }


                        //ΓΙΑ ΝΑ ΑΠΟΘΗΚΕΥΤΕΙ ΣΤΗ ΛΙΣΤΑ ΣΑΝ ΜΙΛΛΣ ΟΤΑΝ ΕΡΧΕΤΑΙ ΑΠΟ ΤΗ ΒΑΣΗ ΚΑΙ ΟΧΙ ΣΑΝ ΚΑΝΟΝΙΚΗ ΜΟΡΦΗ ΩΡΑΣ
                        if (typeElement == TEXTVIEW_CLOCK_TYPE)
                            value = Utils.convertHourTomillisecondsGR(value);


                        //------------------------------------------------------------


                    } else {

                        // ΕΔΩ ΑΝ ΕΧΟΥΜΕ ΔΩΣΕΙ ΑΠΛΗ ΛΙΣΤΑ ΜΕ COLUMNS STO ACTIVITY
                        if (!column.equalsIgnoreCase("id") && !column.equalsIgnoreCase("userID")) {

                            infoTV = getTextview(lp, value);
                            row.addView(infoTV);


                        }

                    }

                    tempHorList.add(value);
                 //   addValueToValueList(rowIndex, value);




                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            tempHorizontalMap.put(rowIndex,tempHorList);



            ll.addView(row);



        }




        for (int row=0; row < results.length(); row++){
                valuesJson = new ArrayList<>();

            for (int col=0; col < tempHorizontalMap.size(); col++) {
                ArrayList<String> hor_values = tempHorizontalMap.get(col);
                if (hor_values != null){
                    valuesJson.add(hor_values.get(row));
                }
            }

            arxikes_timesValuesMap.put(row,valuesJson);
            valuesMap.put(row,(ArrayList<String>)valuesJson.clone());



        }



        if (exeiSinolo)
            setTotal(valuesForTotal, true);


        setHeadersSizesAsDetail();
        setPlagioiTitloiSizes();


    }




    private void setHeadersSizesAsDetail(){
        //ΑΥΤΟ ΧΡΗΣΙΜΟΠΟΕΙΤΑΙ ΓΙΑ ΟΤΑΝ ΤΕΛΕΙΩΣΟΥΝ ΤΑ ΓΡΑΦΙΚΑ ΚΑΙ ΕΧΟΥΝ ΔΩΘΕΙ ΟΙ ΔΙΑΣΤΑΣΕΙΣ
        // ΣΤΟ DETAIL ΝΑ ΠΑΕΙ ΝΑ ΔΩΣΕΙ ΤΙΣ ΙΔΙΕΣ ΔΙΑΣΤΑΣΕΙΣ ΚΑΙ ΣΤΟ HEADER
        // ΑΝ Η ΔΙΑΣΤΑΣΗ ΤΟΥ HEADER ΕΙΝΑΙ ΜΕΓΑΛΥΤΕΡΗ ΤΟΥ DETAIL ΤΟΤΕ ΘΑ ΓΙΝΕΙ ΤΟ ΑΝΤΙΘΕΤΟ
        ll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                //ΑΥΤΟ ΕΔΩ ΤΟΝ ΕΛΕΓΧΟ ΤΟΝ ΚΑΝΩ ΕΠΕΙΔΗ ΟΤΑΝ ΕΧΩ
                TableRow tableRow ;
                if (ll.getChildCount() > 0 && plagioiTitloi != null)
                    tableRow = (TableRow)ll.getChildAt(1);
                else
                    tableRow = (TableRow)ll.getChildAt(0);



                if (tableRow != null) {
                    for (int x = 0 ; x < tableRow.getChildCount(); x ++) { //παιρνω την πρωτη γραμμη απο το detail για να παρω τις διαστασεις του καθε πεδιου
                        View detailView = tableRow.getChildAt(x);
                        int detal_width = detailView.getWidth();

                        TextView headerTV = (TextView) firstRow.getChildAt(x);
                        if (headerTV == null)
                            return;
                        int header_width = headerTV.getWidth();
                        int header_height = headerTV.getHeight();


                        if (x == 0 && plagioiTitloi != null){

                            TableRow detailTitlerow = (TableRow) lldetailTitles.getChildAt(0);
                            if (detailTitlerow != null) {
                                TextView detailTitleTV = (TextView) detailTitlerow.getChildAt(0);

                                int detailTitle_width = detailTitleTV.getWidth();

                                headerTV.setWidth(detailTitle_width);
                                TableRow.LayoutParams lp = new TableRow.LayoutParams(detailTitle_width, TableRow.LayoutParams.MATCH_PARENT);
                                lp.setMargins(20, 0, 0, 0);
                                headerTV.setLayoutParams(lp);
                            }

                        }



                        else if (header_width > detal_width){
                            TableRow.LayoutParams lp = new TableRow.LayoutParams(header_width, TableRow.LayoutParams.MATCH_PARENT);
                            lp.setMargins(10,10,0,0);
                            detailView.setLayoutParams(lp);
                        }
                        else
                            headerTV.setWidth(detal_width);


                    }
                }

            }
        });

    }



    private void setPlagioiTitloiSizes(){


        ll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll.getViewTreeObserver().removeOnGlobalLayoutListener(this);


                for (int i = 0 ; i < ll.getChildCount(); i ++){
                    TableRow tableRow ;
                    tableRow = (TableRow)ll.getChildAt(i);

                    View detailView = tableRow.getChildAt(0);
                    if (detailView != null) {
                        int detail_height = detailView.getHeight();

                        TableRow titleRow = (TableRow) lldetailTitles.getChildAt(i);
                        if (titleRow != null) {
                            TextView titleTV = (TextView) titleRow.getChildAt(0);
                            titleTV.setHeight(detail_height);
                        }
                    }
                }

            }
        });

    }






    private void addValueToValueList(int colIndex ,String v){
        if (tableViewArraylist != null) {

            if (tableViewArraylist.get(colIndex).force_value != null)
                valuesJson.add(tableViewArraylist.get(colIndex).force_value);
            else
                valuesJson.add(v);

        }

    }


    private void addPlagiousTitles_usingItemIDs_PanoTitles_andValues(JSONArray results) throws JSONException {

        //ΕΔΩ ΘΑ ΜΠΕΙ ΟΤΑΝ ΤΟ plagioiTitlesAreItemIDs = true ΟΙ ΠΛΑΓΙΟΙ ΤΙΤΛΟΙ ΕΙΝΑΙ ITEMIDS (ΙΣΟΖΥΓΙΟ ΕΡΓΑΣΤΗΡΙΑΚΑ)


        for (int rowIndex = 0; rowIndex < plagioiTitloi.length; rowIndex++) {

            String itemID = Utils.getSplitSPartString(plagioiTitloi[rowIndex],",",0);
            String name = Utils.getSplitSPartString(plagioiTitloi[rowIndex],",",1);

            TableRow row;

            if (rowIndex < results.length() && results.getJSONObject(rowIndex).has("ID")) {

                int id = results.getJSONObject(rowIndex).getInt("ID");
                String userID = checkIfThereIsUserID(results.getJSONObject(rowIndex));
                row = getRowWithLP(id,userID);
            }
            else
                row = new TableRow(ctx);

            TableRow.LayoutParams lp = getLayoutsParams();
            infoTV = getTextview(lp, name); // layoutParameters , onomasia
            row.addView(infoTV);
            valuesJson = new ArrayList<>();



            for (int colIndex = 0; colIndex < results.length(); colIndex++) {

                JSONObject jsonObject;

                try {
                    jsonObject = results.getJSONObject(colIndex);

                    boolean sameUser = true;
                    if (jsonObject.has("UserID")){
                        String userID = Utils.convertObjToString(jsonObject.get("UserID"));
                        if (!userID.equals(currentUser) && !userID.equals(""))
                            sameUser = false;
                    }




                    if (jsonObject.has("itemID") && Utils.convertObjToString(jsonObject.get("itemID")).equals(itemID)){

                        for (int jsonItemIndex = 0; jsonItemIndex < tableViewArraylist.size(); jsonItemIndex ++) {

                            // AN TAIRIAZEI TO ID ME TO ITEMID


                            String column = col_names[jsonItemIndex];
                            String value;

                            if (col_names[jsonItemIndex].toLowerCase().contains("date"))
                                value = Utils.convertMillisecondsToDateTime(convertObjToString(jsonObject.get(column)));
                            else if(col_names[jsonItemIndex].toLowerCase().contains("hour") ||
                                    col_names[jsonItemIndex].toLowerCase().contains("time") ||
                                    col_names[jsonItemIndex].toLowerCase().equals("duration") )

                                value = Utils.convertMilliSecondsToTime(convertObjToString(jsonObject.get(column)));

                            else
                                value = convertObjToString(jsonObject.get(col_names[jsonItemIndex]));

                            //-------------
                            value = checkEksereseis(column, value);


                            // ΠΡΟΣΘΕΣΗ ΤΩΝ ΑΠΟΤΕΛΕΣΜΑΤΩΝ ΠΟΥ ΕΧΟΥΝ ΑΠΟΘΗΚΕΥΤΕΙ ΣΕ ΔΥΣΔΙΑΣΤΑΤΟ ΠΙΝΑΚΑ
                            if (exeiSinolo) {
                                if (value.equals(""))
                                    value = "0";

//                                try {
//                                    valuesForTotal[rowIndex][colIndex] = Double.parseDouble(value);
//                                } catch (NumberFormatException e) {
//                                    //not a double
//                                    valuesForTotal[rowIndex][colIndex] = 0;
//
//                                }
                            }

                            // ΕΔΩ ΑΝ ΤΟΥ ΕΧΟΥΜΕ ΔΩΣΕΙ ΛΙΣΤΑ ΜΕ ΤΑΒΛΕ ΙΤΕΜΣ STO ACTIVITY

                            int typeElement = tableViewArraylist.get(jsonItemIndex).getTypeElement();
                            int textType = tableViewArraylist.get(jsonItemIndex).getTextType();
                            ArrayList<Spinner_item> lista = tableViewArraylist.get(jsonItemIndex).getSpinnerLista();

                            checkTypesAndAddViewToRow(row, typeElement, textType, lp, value, rowIndex, jsonItemIndex, lista, sameUser);

                           addValueToValueList(colIndex,value);


                        }



                        break;
                    }

                    // ΕΔΩ ΜΠΑΙΝΕΙ ΜΟΝΟ ΟΤΑΝ ΔΕΝ ΒΡΕΙ ΤΟ ID ΝΑ ΤΑΙΡΙΑΖΕΙ ΜΕ ΤΟ ITEMID ΚΑΠΟΙΟΥ ΑΠΟΤΕΛΕΣΜΑΤΟΣ
                    else if (colIndex == results.length() - 1 ){

                        for (int jsonItemIndex = 0; jsonItemIndex < tableViewArraylist.size(); jsonItemIndex ++) {

                            String col = tableViewArraylist.get(jsonItemIndex).getColumn();
                            column_class = col;


                            int typeElement = tableViewArraylist.get(jsonItemIndex).getTypeElement();
                            int textType = tableViewArraylist.get(jsonItemIndex).getTextType();
                            ArrayList<Spinner_item> lista = tableViewArraylist.get(jsonItemIndex).getSpinnerLista();

                            String value = "";
                            if (col.equalsIgnoreCase("itemID"))
                                value = itemID;



                            checkTypesAndAddViewToRow(row, typeElement, textType, lp, value, rowIndex, jsonItemIndex, lista, true);

                           addValueToValueList(colIndex,value);


                        }
                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }



            ll.addView(row);
            arxikes_timesValuesMap.put(rowIndex,valuesJson);
            valuesMap.put(rowIndex,(ArrayList<String>)valuesJson.clone());


        }

//----------------------------
    }


    private String checkIfThereIsUserID(JSONObject json) throws JSONException {
        if (json.has("userID"))
            return json.getString("userID");
        else if (json.has("UserID"))
            return json.getString("UserID");

        return null;

    }

    private void addValues(String[] col_names, JSONArray results) throws JSONException {

        double [] [] valuesForTotal= new double[results.length()][col_names.length];

//--------------
        for (int rowIndex = 0; rowIndex < results.length(); rowIndex++) {

            TableRow row;
            int id = 0;
            if (results.getJSONObject(rowIndex).has("ID")) {
                id = results.getJSONObject(rowIndex).optInt("ID");
                String userID = checkIfThereIsUserID(results.getJSONObject(rowIndex));

                row = getRowWithLP(id,userID);
            }
            else
                row = new TableRow(ctx);
            TableRow.LayoutParams lp = getLayoutsParams();

            JSONObject jsonObject;
            try {
                jsonObject = results.getJSONObject(rowIndex);
                jsonObject_class = jsonObject;   //ΓΙΑ ΝΑ ΞΕΡΩ ΣΕ ΠΙΟ ΠΕΔΙΟ ΕΙΜΑΙ ΩΣΤΕ ΜΗΠΩς ΧΡΕΙΑΣΤΕΙ ΝΑ ΤΟ ΧΡΗΣΙΜΟΠΟΙΗΣΩ ΑΛΛΟΥ ΣΕ ΑΛΛΕΣ ΜΕΘΟΔΟΥΣ
                valuesJson = new ArrayList<>();

                boolean sameUser = true;

                if (jsonObject.has("UserID") || jsonObject.has("userID")) {
                    String userID = Utils.convertObjToString(jsonObject.opt("UserID"));
                    if (!userID.equals(currentUser) && !userID.equals(""))
                        sameUser = false;

                }



                for (int colIndex = 0; colIndex < col_names.length; colIndex ++) {

                    String column = col_names[colIndex];
                    column_class = column;                    //ΓΙΑ ΝΑ ΞΕΡΩ ΣΕ ΠΙΟ ΠΕΔΙΟ ΕΙΜΑΙ ΩΣΤΕ ΜΗΠΩς ΧΡΕΙΑΣΤΕΙ ΝΑ ΤΟ ΧΡΗΣΙΜΟΠΟΙΗΣΩ ΑΛΛΟΥ ΣΕ ΑΛΛΕΣ ΜΕΘΟΔΟΥΣ
                    String value;


                    int TYPE_ELEMENT = 0;

                    if (tableViewArraylist != null)
                    TYPE_ELEMENT = tableViewArraylist.get(colIndex).getTypeElement();

                    //-------------
                    if (col_names[colIndex].toLowerCase().contains("date"))
                        value = Utils.convertMillisecondsToDateTime(convertObjToString(jsonObject.opt(column)));
                    else if(TYPE_ELEMENT == TEXTVIEW_CLOCK_TYPE ||
                            //col_names[colIndex].toLowerCase().contains("hour") ||
                            col_names[colIndex].toLowerCase().contains("time") ||
                            col_names[colIndex].toLowerCase().equals("duration") ) {

                        value = Utils.convertMilliSecondsToTime(convertObjToString(jsonObject.opt(column)));

                    }

//                    else if (TYPE_ELEMENT == SPINNER_ITEM_NEW && tableViewArraylist.get(colIndex).lookup_query != null ){
//                        value = jsonObject.optString(col_names[colIndex] + LOOK_UP_TEXT);
//                    }

                    else
                        value = convertObjToString(jsonObject.opt(col_names[colIndex]));

                    //-------------
                    value = checkEksereseis(column, value);




                    // ΠΡΟΣΘΕΣΗ ΤΩΝ ΑΠΟΤΕΛΕΣΜΑΤΩΝ ΠΟΥ ΕΧΟΥΝ ΑΠΟΘΗΚΕΥΤΕΙ ΣΕ ΔΥΣΔΙΑΣΤΑΤΟ ΠΙΝΑΚΑ
                    if (exeiSinolo) {
                        if (value.equals(""))
                            value = "0";

                        try
                        {
                            valuesForTotal[rowIndex][colIndex] = Double.parseDouble(value);
                        }
                        catch(NumberFormatException e)
                        {
                            //not a double
                            valuesForTotal[rowIndex][colIndex] = 0;

                        }
                    }

                    // ΕΔΩ ΑΝ ΤΟΥ ΕΧΟΥΜΕ ΔΩΣΕΙ ΛΙΣΤΑ ΜΕ ΤΑΒΛΕ ΙΤΕΜΣ STO ACTIVITY
                    if (tableViewArraylist != null) {

                        String title = tableViewArraylist.get(colIndex).getTitle();
                        if (title == null || title.isEmpty())
                            title = panoTitloi[colIndex];
                        int typeElement = tableViewArraylist.get(colIndex).getTypeElement();
                        int textType = tableViewArraylist.get(colIndex).getTextType();
                        ArrayList<Spinner_item> lista = tableViewArraylist.get(colIndex).getSpinnerLista();



                       //TODO:(ΔΕΝΕ ΙΝΑΙ ΓΙΑ ΦΤΙΑΞΙΜΟ ΑΠΛΑ ΝΑ ΦΑΙΝΕΤΑΙ ΚΑΛΥΤΕΡΑ ΕΔΩ ΦΑΙΝΕΤΑΙ ΑΝΑΛΟΓΑ ΤΙΣ ΠΑΡΑΜΕΤΡΟΥΣ ΤΙ ΘΑ ΕΜΦΑΝΙΣΤΕΙ ΣΤΗ ΓΡΑΜΜΗ

                        //----------------------------------------------------------------------------------
                        //ΓΙΑ ΑΡΧΗ ΘΑ ΠΡΕΠΕΙ ΝΑ ΕΧΟΥΜΕ ΔΩΣΕΙ ΕΝΤΟΛΗ ΜΟΝΟ Η ΠΡΩΤΗ ΓΡΑΜΜΗ ΝΑ ΕΙΝΑΙ ΔΙΑΘΕΣΙΜΗ ΣΤΟΝ ΧΡΗΣΤΗ ΓΙΑ ΑΛΛΑΓΗ

                        if ( setOnlyFirstRowAvalaible && rowIndex != 0 && rowIndex != results.length() - 1) {
                            //ΜΟΝΟ ΣΤΑ ΣΥΓΚΕΝΤΡΩΤΙΚΑ ΣΥΝΕΧΩΝ ΩΣΤΕ ΜΟΝΟ Η ΠΙΟ ΠΡΟΣΦΑΤΗ ΚΑΤΑΧΩΡΗΣΗ ΝΑ ΕΙΝΑΙ ΕΠΕΞΕΡΓΑΣΙΜΗ
                            // ΕΔΩ ΜΠΑΙΝΕΙ ΣΕ ΟΛΑ ΕΚΤΟΣ ΤΗΣ   ΠΡΩΤΗΣ ΚΑΙ ΤΕΛΕΥΤΑΙΑΣ ΓΡΑΜΜΗΣ ΟΤΑΝ ΕΙΝΑΙ   setOnlyFirstRowAvalaible = TRUE



                            if (typeElement == CHECKBOX_ITEM){
                                column_class = column_class;
                                infoCH = getCheckbox(lp, value, rowIndex, colIndex, sameUser ,textType );
                                infoCH.setClickable(false);
                                infoCH.setTextColor(act.getResources().getColor(R.color.cyan2));
                                row.addView(infoCH);
                            }

                            else  if (typeElement != TABLE_NO_ELEMENT ) {

                                if (TYPE_ELEMENT == SPINNER_TYPE_NEW && tableViewArraylist.get(colIndex).lookup_query == null
                                        &&   tableViewArraylist.get(colIndex).spinnerLista != null && value != null && !value.isEmpty()) {

                                    value = tableViewArraylist.get(colIndex).spinnerLista.get(Integer.parseInt(value)).name;

                                }
                                else if (TYPE_ELEMENT == SPINNER_TYPE_NEW && tableViewArraylist.get(colIndex).lookup_query != null ){
                                      value = jsonObject.optString(col_names[colIndex] + LOOK_UP_TEXT);
                                }


                                infoET = getEditText(lp, value, rowIndex, colIndex, textType, sameUser);
                                infoET.setEnabled(false);
                                infoET.setTextColor(act.getResources().getColor(R.color.cyan2));
                                row.addView(infoET);
                            }
                        }


                        else if (isEditable) {
                            if (TYPE_ELEMENT == SPINNER_TYPE_NEW && tableViewArraylist.get(colIndex).lookup_query != null && !sameUser) { //ΕΔΩ ΜΠΑΙΕΝΙ ΑΝ ΕΧΟΥΜΕ ΕΝΤΙ, ΔΙΑΦΟΡΕΤΙΚΟ ΧΡΗΣΤΗ ΚΑΙ ΛΟΟΚ ΑΠ
                                value = jsonObject.optString(col_names[colIndex] + LOOK_UP_TEXT);
                                infoTV = getTextview(lp, value);
                                row.addView(infoTV);
                            }
//                            else if (TYPE_ELEMENT == CHECKBOX_ITEM && tableViewArraylist.get(colIndex).lookup_query != null && !sameUser) { //ΕΔΩ ΜΠΑΙΕΝΙ ΑΝ ΕΧΟΥΜΕ ΕΝΤΙ, ΔΙΑΦΟΡΕΤΙΚΟ ΧΡΗΣΤΗ ΚΑΙ ΛΟΟΚ ΑΠ
//                                value = jsonObject.optString(col_names[colIndex] + LOOK_UP_TEXT);
//                                infoTV = getTextview(lp, value);
//                                row.addView(infoTV);
//                            }
                            else
                                checkTypesAndAddViewToRow(title, row, typeElement, textType, lp, value, rowIndex, colIndex, lista, sameUser);
                        }

                        else {

                            //ΕΔΩ ΟΤΑΝ ΔΕΝ ΕΧΕΙ Ο ΧΡΗΣΤΗΣ ΕΠΙΛΟΓΗ EDIT (isEditable false)
                            ArrayList<Spinner_item> spList = tableViewArraylist.get(colIndex).spinnerLista;
                            if (typeElement != TABLE_NO_ELEMENT  ) {
                                 if (typeElement == TEXTVIEW_MEDICINE_TYPE ){
                                    infoTV = getTextviewMedicine(lp, value, rowIndex, colIndex ,sameUser);
                                    row.addView(infoTV);
                                }


                                 else  if (typeElement == TEXTVIEW_DIETA_TYPE ){
                                    infoTV = getTextviewDieta(lp, value, rowIndex, colIndex ,sameUser);
                                    row.addView(infoTV);
                                }


                                 else if (typeElement == SPINNER_TYPE_NEW && tableViewArraylist.get(colIndex).lookup_query != null){
                                     value = jsonObject.optString(col_names[colIndex] + LOOK_UP_TEXT);
                                     infoET = getEditText(lp, value, rowIndex, colIndex, textType, sameUser);
                                     row.addView(infoET);
                                 }
                                 else if (typeElement == SPINNER_TYPE_NEW && spList != null){
                                     if (!value.isEmpty())
                                        value =  spList.get(Integer.parseInt(value)).name;
                                     infoTV = getTextview(lp, value);
                                     row.addView(infoTV);
                                    // value =  tableViewArraylist.get(colIndex).getValueFromSpinnerList(Integer.parseInt(value));
                                 }
                                 else if (typeElement == CHECKBOX_TYPE || typeElement == CHECKBOX_TYPE_READ_ONLY_VALUE ){
                                     infoCH = getCheckbox(lp,value,currentPosRow,colIndex,false,typeElement);
                                     row.addView(infoCH);
                                 }
                                 else if (typeElement == MULTI_TYPE ||  spList != null ){
                                     infoTV = getTextviewMulti(lp,value,rowIndex,colIndex,spList, false);
                                     row.addView(infoTV);
                                 }

                                 else {

                                     infoET = getEditText(lp, value, rowIndex, colIndex, textType, sameUser);
                                     row.addView(infoET);
                                 }
                            }
                        }



                        //ΓΙΑ ΝΑ ΑΠΟΘΗΚΕΥΤΕΙ ΣΤΗ ΛΙΣΤΑ ΣΑΝ ΜΙΛΛΣ ΟΤΑΝ ΕΡΧΕΤΑΙ ΑΠΟ ΤΗ ΒΑΣΗ ΚΑΙ ΟΧΙ ΣΑΝ ΚΑΝΟΝΙΚΗ ΜΟΡΦΗ ΩΡΑΣ
                        if (typeElement == TEXTVIEW_CLOCK_TYPE)
                            value = Utils.convertHourTomillisecondsGR(value);
                        else if (typeElement == TEXTVIEW_DATE_TYPE)
                            value = Utils.convertDateTomillisecondsTable(value);



                        //------------------------------------------------------------
                    }

                    else {

                        // ΕΔΩ ΑΝ ΕΧΟΥΜΕ ΔΩΣΕΙ ΑΠΛΗ ΛΙΣΤΑ ΜΕ COLUMNS STO ACTIVITY
                        if (!column.equalsIgnoreCase("id") && !column.equalsIgnoreCase("userID")) {

                            infoTV = getTextview(lp, value);
                            row.addView(infoTV);
                        }
                    }



                   addValueToValueList(colIndex,value);


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



            ll.addView(row);
            arxikes_timesValuesMap.put(rowIndex,valuesJson);
            valuesMap.put(rowIndex,(ArrayList<String>)valuesJson.clone());



        }


        if (exeiSinolo)
            setTotal(valuesForTotal, false);

        setHeadersSizesAsDetail();

    }

    private void checkTypesAndAddViewToRow( TableRow row, int typeElement, int textType, TableRow.LayoutParams lp,
                                           String value, int rowIndex, int colIndex, ArrayList<Spinner_item> lista, boolean sameUser) {

        checkTypesAndAddViewToRow( "",  row,  typeElement,  textType,  lp, value,  rowIndex,  colIndex,  lista,  sameUser);

    }


    private void checkTypesAndAddViewToRow(String title, TableRow row, int typeElement, int textType, TableRow.LayoutParams lp,
                                           String value, int rowIndex, int colIndex, ArrayList<Spinner_item> lista, boolean sameUser) {


//            if (rowIndex >= tableViewArraylist.size() && plagioiTitloi == null)
//                return;



        if (typeElement == TABLE_NO_ELEMENT ) {
        }
        else  if (typeElement == TEXTVIEW_ITEM_READ_ONLY_VALUE || typeElement == TEXTVIEW_VALUE_FROM_VIEW){
            infoTV = getTextview(lp, value);
            row.addView(infoTV);

        }

        else  if (typeElement == TEXTVIEW_MEDICINE_TYPE ){
            infoTV = getTextviewMedicine(lp, value, rowIndex, colIndex ,sameUser);
            row.addView(infoTV);

        }

        else  if (typeElement == TEXTVIEW_DIETA_TYPE ){
            infoTV = getTextviewDieta(lp, value.replace("\ufffd",","), rowIndex, colIndex ,sameUser);
            row.addView(infoTV);

        }

        else  if (typeElement == TEXTVIEW_DATE_TYPE){
            infoTV = getTextviewDate(lp, value, rowIndex, colIndex ,sameUser);
            row.addView(infoTV);
        }

        else  if (typeElement == TEXTVIEW_CLOCK_TYPE){
            infoTV = getTextviewClock(lp, value, rowIndex, colIndex ,sameUser);
            row.addView(infoTV);
        }


        else if (typeElement == EDITTEXT_ITEM ){
            if (editextsUsingDialogs) {
                infoTV = getTextviewWithDialog(title, textType, lp, value, rowIndex, colIndex, sameUser);
                row.addView(infoTV);
            }

            else {
                infoET = getEditText(lp, value, rowIndex, colIndex, textType, sameUser);
                row.addView(infoET);
            }

        }

        else if (typeElement == CHECKBOX_ITEM || typeElement == CHECKBOX_TYPE_READ_ONLY_VALUE  ){

            infoCH = getCheckbox(lp,value,rowIndex, colIndex,sameUser,typeElement);
            row.addView(infoCH);

        }

        else if (typeElement == SPINNER_TYPE_NEW){
            infoSP = getSpinner(lp,value,rowIndex, colIndex,lista,sameUser);

            row.addView(infoSP);

        }

        else if (typeElement == TEXTVIEW_LISTENER){
            infoTV = getTextViewWithListener(lp, value, colIndex, sameUser, textType);
            row.addView(infoTV);

        }

        else if (typeElement == TEXTVIEW_PHOTO){
            infoTV = getTextViewPhoto(lp, value, rowIndex, colIndex ,sameUser);
            row.addView(infoTV);

        }


    }



    private void createNewPanoTitlous(JSONArray results) {
        ArrayList <String> hours = new ArrayList<>();

        for (int i=0; i<results.length(); i ++){
            try {
                JSONObject jsonObject = results.getJSONObject(i);
                String watchID = Utils.convertObjToString(jsonObject.get("Watch"));


                if ( watchID.length() < 3)
                    hours.add(watchID + ":00");
                else if ( watchID.length() == 3)
                    hours.add(watchID.substring(1) + ":00");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



            panoTitloi =  hours.toArray(new String[hours.size()]);



    }



    private void setTotal(double[][] totalValues, boolean exeiPlagiousTitlous) {

        TableRow row = null;


        row = new TableRow(ctx);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        lp.setMargins(10, 10, 0, 0);
        row.setLayoutParams(lp);

        infoTV = getTextview(lp, "Σύνολο: "); // layoutParameters , onomasia
        infoTV.setTextColor(Color.RED);
        row.addView(infoTV);

        if (!exeiPlagiousTitlous){

            //GIA NA PAEI STIN APO KATV GRAMMI OTAN DEN EXOUME SINOLO
            ll.addView(row);
            row = new TableRow(ctx);

        }



    try {
        for (int i = 0; i < totalValues[0].length; i++) {
            double num = 0;
            for (double[] totalValue : totalValues) {
                num = num + totalValue[i];
            }
            infoTV = getTextview(lp, String.valueOf(num));
            infoTV.setTextColor(Color.RED);
            infoTV.setTypeface(null, Typeface.BOLD);

            row.addView(infoTV);

        }

        ll.addView(row);

    }
    catch (Exception e){
        //ΤΟ ΕΒΑΛΑ ΤΟ try ΕΔΩ ΕΠΕΙΔΗ ΟΠΟΙΟΝ ΕΛΕΓΧΟ ΚΑΙ ΝΑ ΕΚΑΝΑ ΕΣΠΑΓΕ ΤΟ ACTIVITY
    }


    }






    private void getDataForTable() {

        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.ctx = ctx;
        task.listener =  this;
        task.query = str_query;
        task.execute();
    }





    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null) {


            if (results.getJSONObject(0).has("status") && plagioiTitloi == null) {

                Toast.makeText(ctx, "Δεν υπάρχουν δεδομένα για τις συγκεκριμένες παραμέτρους", Toast.LENGTH_SHORT).show();


                if (ll != null)
                    ll.removeAllViews();


                results.remove(0);
                addGrammiEggrafis(results);
                addPanoTitles(panoTitloi);
                addValues(col_names, results);


            }


            else if (!results.getJSONObject(0).has("status") || isPinakas) {

                if (!act.isFinishing())
                    alertDialog.show();


                if (firstRow !=null)
                    firstRow.removeAllViews();
                if (ll != null)
                    ll.removeAllViews();






                // ΕΔΩ ΘΑ ΜΠΕΙ ΟΤΑΝ ΘΕΛΟΥΜΕ ΟΛΕΣ ΤΙΣ ΠΕΡΙΠΤΩΣΕΙΣ
                // ΠΑΝΩ ΤΙΛΟΙ , ΠΛΑΓΙΟΙ ΤΙΤΛΟΙ ΜΕ ΣΥΝΔΙΑΣΜΟ ΠΕΔΙΩΝ ΑΠΟ ΤΗ tableViewArraylist
                if (tableViewArraylist != null && plagioiTitloi != null && panoTitloi != null && plagioiTitlesAreItemIDs) {

                    addPanoTitles(panoTitloi);
                    addPlagiousTitles_usingItemIDs_PanoTitles_andValues(results);
                }

                else {

                    // ΕΔΩ ΟΙ ΥΠΟΛΟΙΠΕΣ ΠΕΡΙΠΤΩΣΕΙΣ
                    if (exeiWatchID)
                        createNewPanoTitlous(results);

          //---------------
                    if (tableViewArraylist != null && plagioiTitloi != null && panoTitloi != null && isEditable && results.length() == panoTitloi.length){
                        //ΕΔΩ ΜΠΑΙΝΕΙ ΟΤΑΝ ΕΧΟΥΜΕ ΠΑΝς ΚΑΙ ΠΛΑΓΙΟΥΣ ΤΙΤΛΟΥΣ ΚΑΙ ΜΠΟΡΕΙ Ο ΧΡΗΣΤΗΣ ΝΑ ΚΑΝΕΙ ΑΛΛΑΓΕΣ
                        //ΑΥΤΟ ΓΙΝΕΤΑΙ ΕΠΕΙΔΗ ΕΙΝΑΙ ΕΝΑ ΑΠΟ ΤΑ ΒΗΜΑΤΑ ΩΣΤΕ ΝΑ ΔΟΥΛΕΨΕΙ ΣΩΣΤΑ ΤΟ ΝΑ ΕΜΦΑΝΙΖΕΤΑΙ ΚΑΙΝΟΥΡΙΑ ΕΛΕΘΕΡΗ ΣΤΗΛΗ
                        //ΓΙΑ ΝΑ ΚΑΝΕΙ ΙΣΝΕΡΤ Ο ΧΡΗΣΤΗΣ

                        String [] temp = panoTitloi;
                        panoTitloi = new String[temp.length + 1];
                        System.arraycopy(temp, 0, panoTitloi, 0, temp.length);
                        panoTitloi[temp.length ] = "";
                    }
            //------------------------------
                    addPanoTitles(panoTitloi);



                    if (plagioiTitloi != null && !plagioiTitlesAreItemIDs )
                        addPinakasPanoPlagioiTitloi(plagioiTitloi, col_names, results);
                    else if (plagioiTitloi != null)
                        addPlagiousTitlesAndValues(plagioiTitloi, col_names, results);
                    else{
                        addGrammiEggrafis(results);
                        addValues(col_names, results);
                    }

                }




            }


        }


        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }


    //Η ΓΡΑΜΜΗ ΠΟΥ ΔΙΝΕΙ ΤΗ ΔΥΝΑΤΟΤΗΤΑ ΣΤΤΟΝ ΧΡΗΣΤΗ ΓΙΑ ΝΕΑ ΕΓΓΡΑΦΗ
    // ΒΓΑΖΕΙ ΠΡΟΕΙΔΟΠΟΙΗΣΗ ΤΟ ANDROID STUDIO ΠΩΣ ΔΕΝ ΧΡΗΣΙΜΟΠΟΙΕΙΤΑΙ ΠΟΥΘΕΝΑ ΑΛΛΑ ΚΑΝΕΙ ΛΑΘΟΣ
    public JSONArray addGrammiEggrafis(JSONArray results) throws JSONException {

        if (isEditable){
            JSONObject jsonObject = new JSONObject();
            for (String n : nameJson) {
                if (n.equalsIgnoreCase("id"))
                    jsonObject.put(n, 0);
                else
                    jsonObject.put(n, "");

            }
            results.put(jsonObject);

        }

        return results;

    }




    //------------------------------


    private TableRow getRowWithLP(final int id, final String userID){

        TableRow row = new TableRow(ctx);
        TableRow.LayoutParams lp = getLayoutsParams();
        row.setLayoutParams(lp);


        if (isEditable)
            row.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if (userID == null || (!userID.equals(currentUser) && !userID.equals("")))
                        Toast.makeText(ctx,  R.string.error_user_id, Toast.LENGTH_SHORT).show();
                    else {

                        final AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
                        alertDialog.setTitle("Διαγραφη");
                        alertDialog.setMessage("Διαγραφη της επιλεγμένης εγγραφής ;");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ΟΚ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        deleteData(id);

                                    }
                                });


                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "ΑΚΥΡΩΣΗ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }

                    return false;

                }
            });



        return row;
    }

    private TableRow.LayoutParams getLayoutsParams(){
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        lp.setMargins(10,10,0,0);
        return lp;
    }


    private TableRow.LayoutParams getLayoutsParams_wrapWidth(){
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT);
        lp.setMargins(10,10,0,0);

        return lp;
    }


    @SuppressLint("SetTextI18n")
    private TextView getTextview(TableRow.LayoutParams lp, String text ){

        TextView  textView = new TextView(ctx);
        textView.setLayoutParams(lp);
        textView.setTextSize(18);

        textView.setGravity(Gravity.CENTER);
        textView.setPadding(1,0,1,0);
        textView.setBackgroundResource(R.drawable.table_row_cell);
        textView.setText(" " + text + " ");

        textView.setMaxEms(12);
        textView.setSingleLine(false);

        return textView;
    }



    private TextView getTextviewMedicine(TableRow.LayoutParams lp, String value,final int positionRow, final int indexOfColumn, boolean sameUser) {

        final TextView textView = getTextview(lp,value);
        //ΑΠΟ ΤΗΝ ΣΥΣΚΕΥΗ
//        if (value != null && !value.trim().equals(""))
//            textView.setText(value + " , " + Utils.getMedicineKeyByValue(ctx,value));

        //ΑΠΟ ΤΗΝ ΒΑΣΗ
        if (value != null && !value.trim().equals("")){
            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2("select name from item where id = " + value );
            task.ctx = act;
            task.listener = new AsyncCompleteTask2() {
                @SuppressLint("SetTextI18n")
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {
                    if (results != null){
                        textView.setText(value + " , " + results.getJSONObject(0).getString("name"));
                    }
                }
            };
            task.execute();
        }

        if (sameUser) {
            textView.setBackgroundResource(R.color.light_green);
            if (isEditable) {
                textView.setOnClickListener(new SearchMedicineListener_Base(act));
            }


            textView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    currentTextView = textView;
                    currentTableFR = true;
                    currentPosRow = positionRow;
                    currentIndexOfCol = indexOfColumn;
                    return false;
                }
            });


        }
        return textView;


    }


     private TextView getTextviewDieta(TableRow.LayoutParams lp, final String value,final int positionRow, final int indexOfColumn, boolean sameUser) {

        final TextView textView = getTextview(lp,value);
        if (sameUser){


            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DF_items_categories df = new DF_items_categories(dialogFragment);
                    Bundle args = new Bundle();

                    if (value.isEmpty())
                        args.putString(DF_items_categories.IDS, textView.getText().toString());
                    else
                        args.putString(DF_items_categories.IDS, value);

                    df.setArguments(args);
                    if (act != null)
                        df.show(((FragmentActivity)act).getSupportFragmentManager(), "Dialog");
                    else
                        df.show(((FragmentActivity) dialogFragment.requireContext()).getSupportFragmentManager(), "Dialog");

                  //  df.show(act.getcontex, "Dialog");
                 //   df.show(((FragmentActivity)act).getSupportFragmentManager(), "Dialog");

                }
            });




            textView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    currentTextView = textView;
                    currentTableFR = true;
                    currentPosRow = positionRow;
                    currentIndexOfCol = indexOfColumn;
                    return false;
                }
            });


        }

        return textView;

    }

    @Override
    public void hereIsYourStr_Data(String info) {
//        if (currentTextView != null)
//            currentTextView.setText(id_name);
//
//        ArrayList<String> valuesLista = valuesMap.get(currentPosRow);
//        if (valuesLista != null) {
//            valuesLista.set(currentIndexOfCol, Utils.getfirstPartSplitCommaString(currentTextView.getText().toString()));
//            valuesMap.put(currentPosRow, valuesLista);
//        }
        Toast.makeText(act, "", Toast.LENGTH_SHORT).show();

    }


    private TextView getTextviewMulti(TableRow.LayoutParams lp, String value, final int positionRow, final int indexOfColumn, final  ArrayList<Spinner_item> itemsLista, boolean sameUser){
        final TextView tv = getTextview(lp,value);

        String [] v_ids = value.replace("\ufffd",",").split(",");
        if (v_ids.length == 0){
            tv.setBackgroundResource(R.drawable.table_row_cell);
            tv.setText("");
            return tv;
        }


        StringJoiner names = new StringJoiner(",");
        for (int i=0; i<v_ids.length;i++) {
            for (int x = 0; x < itemsLista.size(); x++) {
                if (!v_ids[i].isEmpty() && Integer.parseInt(v_ids[i]) == itemsLista.get(x).id ) {
                    boolean isMonos =  (i > 0  &&  i % 2  != 0 );
                    names.add(itemsLista.get(x).name + ( isMonos ? "\n" : "")) ;
                    break;
                }
            }
        }
        tv.setText(names.toString());

        if (isEditable) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> valuesLista = valuesMap.get(positionRow);
                    String [] v_ids = new String[0];
                    if (valuesLista != null) {
                        v_ids = valuesLista.get(indexOfColumn).replace("\ufffd",",").split(",");
                    }


                    for (Spinner_item x : itemsLista){
                        x.checked = false;              //ΤΟ ΚΑΝΩ ΑΥΤΟ ΕΠΕΙΔΗ ΕΠΑΙΡΝΕ ΤA TRUE ΤΟΥ ΠΡΟΗΓΟΥΜΕΝΟΥ ΜΟΥΛΤΙ ΑΝ ΕΙΧΕ ΠΑΤΗΘΕΙ
                    }

                    AlertDialog.Builder builder =  Dialogs.getAlertMultipleChoice(act, itemsLista, v_ids);
                    builder.setTitle(R.string.choose_item);
                    builder.setPositiveButton("OK", (dialog, which) -> {

                        StringJoiner s_ids = new StringJoiner(",");
                        StringJoiner s_names = new StringJoiner(",");
                        for (int i=0; i<itemsLista.size();i++){
                            Spinner_item x = itemsLista.get(i);
                            if (x.isChecked()) {
                                s_ids.add(String.valueOf(x.id));
                                s_names.add(x.name);
                            }
                        }
                        if (valuesLista != null) {

                            valuesLista.set(indexOfColumn,s_ids.toString().replace(",","\ufffd"));
                            valuesMap.put(positionRow, valuesLista);
                        }
                        tv.setText(s_names.toString());

                    });

                    builder.setNegativeButton("Ακύρωση", (dialog, which) -> {});
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });


        }



        return tv;
    }



    @Override
    public void dialogMedicineClose(String id_name) {
        if (currentTextView != null)
            currentTextView.setText(id_name);

        ArrayList<String> valuesLista = valuesMap.get(currentPosRow);
        if (valuesLista != null) {
            valuesLista.set(currentIndexOfCol, Utils.getfirstPartSplitCommaString(currentTextView.getText().toString()));
            valuesMap.put(currentPosRow, valuesLista);
        }
    }



    public void setInfoFromMedicineDialogFragment(String id_name){

        if (currentTextView != null) {
            currentTextView.setText(id_name);
            ArrayList<String> valuesLista = valuesMap.get(currentPosRow);
            if (valuesLista != null) {
                valuesLista.set(currentIndexOfCol, Utils.getfirstPartSplitCommaString(currentTextView.getText().toString()));
                valuesMap.put(currentPosRow, valuesLista);
            }
        }
        currentTableFR = false;

    }


    public void setInfoDietesDialogFragment(String ids_name){

        if (currentTextView != null) {
            currentTextView.setText(ids_name);
            ArrayList<String> valuesLista = valuesMap.get(currentPosRow);
            if (valuesLista != null) {
                valuesLista.set(currentIndexOfCol, ids_name.replace(",","\ufffd"));
                valuesMap.put(currentPosRow, valuesLista);
            }
        }
        currentTableFR = false;

    }






    private TextView getTextviewDate(TableRow.LayoutParams lp, String value,final int positionRow, final int indexOfColumn, boolean sameUser) {

        final TextView textView = getTextview(lp,value);
        final Calendar myCalendar = Calendar.getInstance();

        if (sameUser || tableViewArraylist.get(indexOfColumn).isEditWithNoSameUserID()) {
            textView.setBackgroundResource(R.color.light_green);

         


            final DatePickerDialog.OnDateSetListener dateStr = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String myFormat = "dd-MM-yyyy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                    textView.setText(sdf.format(myCalendar.getTime()));
                    ArrayList<String> valuesLista = valuesMap.get(positionRow);
                    if (valuesLista != null) {
                        valuesLista.set(indexOfColumn, Utils.convertDateTomilliseconds(textView.getText().toString()));
                        valuesMap.put(positionRow, valuesLista);
                    }
                }

            };


            textView.setOnClickListener(v -> {
                // TODO Auto-generated method stub
                new DatePickerDialog(ctx, dateStr, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            });

        }

        return textView;

    }



    private TextView getTextviewClock(TableRow.LayoutParams lp, String value,final int positionRow, final int indexOfColumn, boolean sameUser) {

        final TextView textView = getTextview(lp,value);
        ArrayList<String> valuesLista = valuesMap.get(positionRow);
        if (valuesLista != null) {
            valuesLista.set(indexOfColumn, String.valueOf(Utils.convertTime(textView.getText().toString().trim())));
            valuesMap.put(positionRow, valuesLista);
        }

        if (sameUser) {
            textView.setBackgroundResource(R.color.light_green);

            textView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(ctx, new TimePickerDialog.OnTimeSetListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            textView.setText((selectedHour < 10 ? "0" + selectedHour : selectedHour) + ":"
                                    + (selectedMinute < 10 ? "0" + selectedMinute : selectedMinute));
                            ArrayList<String> valuesLista = valuesMap.get(positionRow);
                            if (valuesLista != null) {
                                valuesLista.set(indexOfColumn, String.valueOf(Utils.convertTime(textView.getText().toString().trim())));
                                valuesMap.put(positionRow, valuesLista);
                            }
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.show();

                }
            });

        }

        return textView;

    }


    private TextView getTextViewWithListener(TableRow.LayoutParams lp, String value, int colIndex, boolean sameUser, int textType) {
        final TextView textView = getTextview(lp,value);


        if (textType == TABLE_TYPE_LISTENER){
            textView.setOnClickListener(v -> {
                TableViewItem.TextViewTableListener tl = tableViewArraylist.get(colIndex).getTextViewTableListener();
                act.startActivity( tableView_sigkentrotika(tl.query,tl.transgroupID,null,null, tl.lista,  tl.exeiWatchID,tl.exeiSinolo,tl.editable));
            });

        }

        return textView;
    }




    private TextView getTextViewPhoto(TableRow.LayoutParams lp, String value,final int positionRow, final int indexOfColumn, boolean sameUser){
        final TextView textView = getTextview(lp,value);


        if (sameUser || tableViewArraylist.get(indexOfColumn).isEditWithNoSameUserID()) {
            if (value.isEmpty())
                textView.setBackgroundResource(R.color.light_green);
            else
                textView.setBackgroundResource(R.color.cyan);

            //READ
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (value.isEmpty())
                        Toast.makeText(act, "Δεν υπάρχει επισυναπτόμενη εικόνα", Toast.LENGTH_SHORT).show();
                    else {
                        alertDialog.show();
                       // String q = "select fileData, fileExt from medicalRecords where id = " + "10464";
                        String q = "SELECT top 1  fileExt , CAST('' as XML).value('xs:base64Binary(sql:column(\"filedata\"))', 'NVARCHAR(MAX)') as [Content]\n" +
                                "FROM MedicalRecords where id = " + value;

                        AsyncTaskGetJSON2 t = new AsyncTaskGetJSON2(q,act);
                        t.listener = results -> {
                            if(results != null){
                                JSONObject img = results.getJSONObject(0);
                                String fileData = Utils.convertObjToString(img.get("Content"));
                                String ext = img.getString("fileExt");
                                if (ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png") ) {

                                    byte[] data = Base64.decode(fileData, Base64.DEFAULT);
                                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    Utils.showImageDialog(act, bmp);
                                }
                                else
                                    Toast.makeText(act, "Δεν υποστηρίζεται ο τύπος του αρχείου", Toast.LENGTH_SHORT).show();
                            }
                            alertDialog.dismiss();
                        };
                        t.execute();
                    }
                }
            });

            //UPDATE
            textView.setOnLongClickListener(v -> {
                Permissions.requestPhotoPermission(act);
                return true;
            });

        }

        return textView;
    }






    @SuppressLint("SetTextI18n")
    private TextView getTextviewWithDialog(String title, int textType, TableRow.LayoutParams lp, String value, final int positionRow, final int indexOfColumn, boolean sameUser){
        final TextView  tv = new TextView(ctx);
        tv.setLayoutParams(lp);
        tv.setTextSize(18);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(1,0,1,0);
        tv.setText(" " + value + " ");
        tv.setMaxEms(14);
        tv.setSingleLine(false);

        if (!sameUser)
            tv.setBackgroundResource(R.drawable.table_row_cell);
        else{
            
            tv.setBackgroundResource(R.color.light_green);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ArrayList<String> valuesLista = valuesMap.get(positionRow);
                    if (valuesLista != null)
                        Dialogs.setDialogInputTextTable(act, tv, title, valuesMap, valuesLista, positionRow, indexOfColumn ,value,textType);
                }
            });
        }

        return tv;
    }





    private EditText getEditText(TableRow.LayoutParams lp, String text, final int positionRow, final int indexOfColumn, int edittextType, boolean sameUser){


        final EditText  editText = new EditText(ctx);
        editText.setLayoutParams(getLayoutsParams_wrapWidth());
        editText.setTextSize(18);
        editText.setPadding(1,0,1,0);
        editText.setGravity(Gravity.CENTER);
        editText.setBackgroundResource(R.drawable.table_row_cell);
        editText.setText(text);

        editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        editText.setMaxEms(14);
        editText.setSingleLine(false);

        if (!isEditable)
            editText.setTextColor(act.getResources().getColor(R.color.cyan2));





        if ( (!sameUser && !tableViewArraylist.get(indexOfColumn).isEditWithNoSameUserID())  || !isEditable) {
            editText.setTextColor(act.getResources().getColor(R.color.cyan2));
            editText.setEnabled(false);
        }

        else{

            editText.setBackgroundResource(R.color.light_green);

            if (edittextType == KEIMENO)
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE );

            else if (edittextType == AKERAIOS) {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            } else if (edittextType == DEKADIKOS) {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            } else if (edittextType == HOUR)
                editText.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);


            else if (edittextType == AKERAIOS_WITH_NEGATIVE) {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
            } else if (edittextType == DEKADIKOS_WITH_NEGATIVE) {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            }

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @SuppressLint("SetTextI18n")
                @Override
                public void afterTextChanged(Editable s) {



                    ArrayList<String> valuesLista = valuesMap.get(positionRow);
                    if (valuesLista != null) {
                        valuesLista.set(indexOfColumn, s.toString());
                        valuesMap.put(positionRow, valuesLista);
                    }


                }
            });
        }

        return editText;
    }


    private CheckBox getCheckbox(TableRow.LayoutParams lp, String value , final int positionRow , final int indexOfColumn ,boolean sameUser,int typeElement){

        final CheckBox  cbx = new CheckBox(ctx);
        cbx.setLayoutParams(lp);
        cbx.setTextSize(18);
        cbx.setGravity(Gravity.CENTER);
        cbx.setBackgroundResource(R.drawable.table_row_cell);



        if (hasValuesForCH){
            setValuesForSigkentrotikaSinexon(cbx);
        }

        if (value.equals("1")
                ||
           (tableViewArraylist.size() > positionRow &&  tableViewArraylist.get(positionRow).force_value != null && tableViewArraylist.get(positionRow).force_value.equals("1")))
            cbx.setChecked(true);
        else
            cbx.setChecked(false);

        if (typeElement == CHECKBOX_TYPE_READ_ONLY_VALUE) {
            cbx.setEnabled(false);

            if (cbx.isChecked())
                cbx.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#f31a1a")));



            return cbx;
        }


        if (sameUser) {
            cbx.setBackgroundResource(R.color.light_green);

            cbx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ArrayList<String> valuesLista = valuesMap.get(positionRow);
                    if (valuesLista != null) {

                        valuesLista.set(indexOfColumn, cbx.isChecked() ? "1" : null);
                        valuesMap.put(positionRow, valuesLista);
                    }

                }
            });

        }
        else
            cbx.setEnabled(false);


        return cbx;
    }

    private void setValuesForSigkentrotikaSinexon(CheckBox cbx) {


            switch (column_class) {
                case "vit_b":
                    cbx.setText(jsonObject_class.optString("vitB_text"));
                    break;
                case "carnitine":
                    cbx.setText(jsonObject_class.optString("carnitine_text"));
                    break;
                case "alphacalcidol":
                    cbx.setText(jsonObject_class.optString("alfacalcidol_text"));
                    break;
                case "epo_alpha":
                    cbx.setText(jsonObject_class.optString("alpha_text"));
                    break;
                case "epo_zeta":
                    cbx.setText(jsonObject_class.optString("zeta_text"));
                    break;
                case "epo_darbepoetin":
                    cbx.setText(jsonObject_class.optString("darbepoetin_text"));
                    break;
                case "paricalcitol":
                    cbx.setText(jsonObject_class.optString("paracalcitol_text"));
                    break;
            }



    }

//--------------------------------


    private Spinner getSpinner(TableRow.LayoutParams lp, String value, final int positionRow, final int indexOfColumn, ArrayList<Spinner_item> lista, boolean sameUser){

        final Spinner spinner = new Spinner(ctx);
        spinner.setLayoutParams(lp);
        spinner.setGravity(Gravity.CENTER);
        spinner.setBackgroundResource(R.drawable.table_row_cell);


        if (lista != null && sameUser) {

            Spinner_new_Image_Adapter adapter = new Spinner_new_Image_Adapter(ctx, R.layout.spinner_layout2,
                    lista.toArray(new Spinner_item[lista.size()]));


            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);


            String forceValue = null;
            if (tableViewArraylist.size() > indexOfColumn)
                forceValue = tableViewArraylist.get(indexOfColumn).force_value;
            //TΟ ΑΝΑΛΟΓΟ ID ΕΙΝΑΙ ΚΑΙ Η ΘΕΣΗ ΤΟΥ ΣΠΙΝΝΕΡ  (ΒΟΛΕΥΕΙ ΑΡΚΕΤΑ)
            if (value == null || value.equals(""))
                value = "0";


            if (forceValue != null && !forceValue.isEmpty()) {
                value = forceValue;
            }




            spinner.setSelection(Integer.parseInt(value));

            spinner.setBackgroundResource(R.color.light_green);

            checkSameUserAndSetSpinnerListener(spinner, positionRow, indexOfColumn,sameUser);

        }

        else if (lista == null && sameUser){
            lista = new ArrayList<>();

            String lookup_query = tableViewArraylist.get(indexOfColumn).getLookup_query();

            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.query = "select ID, Name from " +  lookup_query;
            task.ctx = act;
            ArrayList<Spinner_item> finalLista = lista;
            final String[] finalValue = {value};
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {
                    if (results != null && !results.getJSONObject(0).has("status")){
                        Spinner_item item  = new Spinner_item();
                        //Η πρωτη επιλογή να ειναι κενή
                        item.setId(0);
                        item.setName("");
                        finalLista.clear();
                        finalLista.add(item);


                        for (int i=0; i<results.length(); i ++) {

                            JSONObject jsonObject = results.getJSONObject(i);
                            int id = jsonObject.getInt("ID");
                            String name = jsonObject.getString("Name");
                            item = new Spinner_item();
                            item.setId(id);
                            item.setName(name);
                            finalLista.add(item);

                        }

                        Spinner_new_Image_Adapter adapter = new Spinner_new_Image_Adapter(ctx, R.layout.spinner_layout2,
                                finalLista.toArray(new Spinner_item[finalLista.size()]));


                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);

                        //TΟ ΑΝΑΛΟΓΟ ID ΕΙΝΑΙ ΚΑΙ Η ΘΕΣΗ ΤΟΥ ΣΠΙΝΝΕΡ  (ΒΟΛΕΥΕΙ ΑΡΚΕΤΑ)
                        if (finalValue[0] == null || finalValue[0].equals("")) {
                            finalValue[0] = "0";
                            spinner.setSelection(0);
                        }
                        else{
                            int realPos  =  adapter.getPosition(new Spinner_item(Integer.parseInt(finalValue[0]), null));
                            spinner.setSelection(realPos);
                        }



                        spinner.setBackgroundResource(R.color.light_green);

                        checkSameUserAndSetSpinnerListener(spinner, positionRow, indexOfColumn,sameUser);



                    }
                }
            };
            task.execute();



        }

        return spinner;
    }





        private String  getLookUpInfo(String look_up, int id){
            String q = "select ID, Name from " +  look_up + " where id = " + id;

            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2(q, act);
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {
                    if (results != null){
                        results.getJSONObject(0).getString("");
                    }
                }
            };

            return "";
        }











    private void checkSameUserAndSetSpinnerListener(Spinner spinner, int positionRow, int indexOfColumn, boolean sameUser){

        if (sameUser) {
            spinner.setBackgroundResource(R.color.light_green);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Spinner_item item = (Spinner_item) spinner.getSelectedItem();
                    String value = String.valueOf(item.getId());

                    ArrayList<String> valuesLista = valuesMap.get(positionRow);
                    if (valuesLista != null) {

                        valuesLista.set(indexOfColumn, value.equals("0") ? null : value);
                        valuesMap.put(positionRow, valuesLista);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        else
            spinner.setEnabled(false);

    }





    public void thereIsImageUpdateButton(){

        //view.setSupportActionBar(t);
        ImageButton updateIMB = new ImageButton(ctx);
        Toolbar.LayoutParams l1=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l1.gravity= Gravity.START;
        updateIMB.setLayoutParams(l1);
        updateIMB.setBackgroundResource(R.drawable.save_icon_48px);
        updateIMB.setPadding(20,20,20,20);
        updateIMB.setScaleType(ImageButton.ScaleType.FIT_CENTER);
        t.addView(updateIMB);
        Toolbar.LayoutParams l3=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l3.gravity= Gravity.END;
        updateIMB.setLayoutParams(l3);
        updateIMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateListener();
            }
        });

    }


    private String checkEksereseis(String column,String value) {

        if (watchID_as_simpleSpinner)
            return value;

        if (value == null || value.equals(""))
            return "";
        if (column.equals("SitisiSinodou"))
            value = InfoSpecificLists.getSitisiSinodouName(Integer.parseInt(value));
        else if (column.equals("Watch")&& value.length() < 3)
            value = value + ":00";
        else if (column.equals("Watch")&& value.length() == 3)
            value = value.substring(1) + ":00";

        return value;


    }


    private void updateListener(){


        ArrayList <String> arxikesValuesLista = new ArrayList<>();
        ArrayList <String> updateValuesLista = new ArrayList<>();
        AsyncTaskUpdate_JSON task;



        for (int i=0; i<valuesMap.size(); i++){

            arxikesValuesLista = arxikes_timesValuesMap.get(i);
            updateValuesLista = valuesMap.get(i);


            if (arxikesValuesLista!= null && updateValuesLista != null && !updateValuesLista.isEmpty()) {


                if (!Utils.replaceTrueOrFalse(arxikesValuesLista).equals(Utils.replaceTrueOrFalse(updateValuesLista))) {

                    alertDialog.show();


                    String id = updateValuesLista.get(0);
                    int pos = 0;

                    if (nameJson.contains("Date"))
                        pos = nameJson.indexOf("Date");
                    else if (nameJson.contains("date"))
                        pos = nameJson.indexOf("date");


                    //ΕΠΕΙΔΗ ΠΡΩΤΟ ΛΟΓΙΚΑ ΘΑ ΕΙΝΑΙ ΠΑΝΤΑ Η ΗΜΕΡΟΜΗΝΙΑ ΚΑΙ Η ΩΡΑ ΜΑΖΙ ΣΑΝ ΠΕΔΙΟ ΠΟΥ ΔΕΝ ΘΑ ΑΛΛΑΖΕΙ
                    if (pos != 0) {
                        try{

                            Long.parseLong(updateValuesLista.get(pos));  // ΑΝ ΜΠΟΡΕΙ ΝΑ ΤΟ ΜΕΤΑΤΕΨΕΙ ΣΗΜΑΙΝΕΙ ΠΩΣ ΕΧΕΙ ΗΔΗ ΤΙΜΗ ΛΟΝΓΚ ΟΠΟΤΕ
                            // ΔΕΝ ΘΕΛΩ ΜΕΤΑΤΡΟΠΗ ΓΙΑ ΜΙΛΛΙΣΕΚΟΝΤΣ
                        }
                        catch (Exception e){

                            // ΣΗΜΑΙΝΕΙ ΠΩς ΕΧΕΙ ΗΜΕΡΟΜΗΝΙΑ ΚΑΙ ΤΗΝ ΚΑΝΕΙ ΛΟΝΓΚ ΓΙΑ ΤΗΝ ΕΠΟΜΕΝΗ ΦΟΡΑ
                            if (date != null )
                                updateValuesLista.set(pos, Utils.convertDateTomilliseconds(date));
                            else
                                updateValuesLista.set(pos, Utils.convertDateTomilliseconds(updateValuesLista.get(pos)));


                        }
                    }


                    int posID = nameJson.indexOf("ID");

                    ArrayList<String> nameJsonClone = (ArrayList<String>) nameJson.clone();
                    ArrayList<String> updateValuesListaClone = (ArrayList<String>) updateValuesLista.clone();
                    //----------
                    for (int x=0; x< tableViewArraylist.size(); x++){
                        int type = tableViewArraylist.get(x).getTypeElement();
                        String  nameCol = tableViewArraylist.get(x).getColumn();

                        if (type == TEXTVIEW_ITEM_READ_ONLY_VALUE || type == TEXTVIEW_LISTENER || type == TEXTVIEW_PHOTO){
                            int posCol = nameJsonClone.indexOf(nameCol);
                            nameJsonClone.remove(posCol);
                            updateValuesListaClone.remove(posCol);

                            if (nameCol.equalsIgnoreCase("date") ){
                                nameJsonClone.add("date");
                                updateValuesListaClone.add(Utils.getCurrentDateTimeConverted());
                            }
                        }

                        else if (type == TABLE_NO_TYPE && tableViewArraylist.get(x).force_value != null){
                            nameJsonClone.add(nameCol);
                            updateValuesListaClone.add(tableViewArraylist.get(x).force_value);
                        }





                    }
                    //-------------

                    if (!saveTransgroupID)
                        transgroupID = null;


                    if (needDoctor) {
                        nameJsonClone.add("doctorID");
                        updateValuesListaClone.add(Utils.getLinkDoctorID(ctx));
                    }

                    // ΕΛΕΓΧΕΙ ΑΝ ΤΟΥ ΕΧΟΥΜΕ ΔΩΣΕΙ ΕΠΙΠΛΕΟΝ ΠΕΔΙΑ ΚΑΙ ΣΥΓΚΕΚΡΙΜΕΝΕΣ ΤΙΜΕΣ
                    // ΠΕΡΑ ΑΠΟ ΤΗΝ ΑΡΧΙΚΗ ΛΙΣΤΑ ΩΣΤΕ ΑΝ ΧΡΕΙΑΣΤΕΙ ΝΑ ΤΟΥ ΒΑΖΩ ΤΙΜΕΣ ΣΕ ΠΕΔΙΑ ΣΤΑΘΕΡΑ
                    // ΣΑΝ ΤΟ TABLE_ID
                    checkExtraColAndValues(nameJsonClone, updateValuesListaClone);

                    if (updateValuesLista.get(posID).equals("")){

                        //ΤΑ ΚΑΝΩ ΚΛΟΝΟΥΣ ΕΠΕΙΔΗ ΕΧΟΥΝ ΚΕΝΟ ID ΣΕ ΠΕΡΙΠΤΩΣΗ ΙΝΣΕΡΤ

                        removeValuesFromViewTables(nameJsonClone, updateValuesListaClone);

                        nameJsonClone.remove(posID);
                        updateValuesListaClone.remove(posID);
                        task = new AsyncTaskUpdate_JSON(ctx, id, transgroupID, table, nameJsonClone, Utils.replaceTrueOrFalse(updateValuesListaClone), null);

                    }else {
                        removeValuesFromViewTables(nameJsonClone, updateValuesListaClone);
                        task = new AsyncTaskUpdate_JSON(ctx, id, transgroupID, table, nameJsonClone, Utils.replaceTrueOrFalse(updateValuesListaClone), null);
                    }

                    if (patientID != null)
                        task.patientID = patientID; // ΕΔΩ ΚΑΠΟΙΕΣ ΦΟΡΕΣ ΑΝΤΙ ΓΙΑ transgroupID ΘΑ ΧΡΗΣΙΜΟΠΟΙΕΙΤΑΙ ΑΝΑΛΟΓΑ ΠΟΙΟ ΔΕΝ ΕΙΝΑΙ NULL ΜΠΟΡΕΙ ΟΜΩΣ ΚΑΙ ΤΑ ΔΥΟ

                    task.listener =  this;



                    task.execute();

                }

            }


        }



    }

    public void addExtraColAndValue(ArrayList<String> extraCols , ArrayList <String> extraValues){

       this.extraCols = extraCols;
       this.extraValues = extraValues;

    }

    private void checkExtraColAndValues(ArrayList<String> nameJsonClone, ArrayList<String> updateValuesListaClone){
        if (extraCols != null && extraValues != null && extraCols.size() == extraValues.size() ){
            nameJsonClone.addAll(extraCols);
            updateValuesListaClone.addAll(extraValues);
        }
        else
            Log.e("method_error","checkExtraColAndValues method is null or size is not the same");
           // Toast.makeText(act, "checkExtraColAndValues method is null or size is not the same", Toast.LENGTH_SHORT).show();
    }


    public void removeValuesFromViewTables(ArrayList<String> names ,ArrayList<String> values  ) {

        if (col_namesFromViesLista != null  && !col_namesFromViesLista.isEmpty()){

            for (int i=0; i<col_namesFromViesLista.size(); i++)
            {
                String col_name = col_namesFromViesLista.get(i);
                int pos = names.indexOf(col_name);
                names.remove(pos);
                values.remove(pos);
            }
        }

    }


    @Override
    public void update_JSON(String str) {
        alertDialog.dismiss();
        Toast.makeText(ctx, str, Toast.LENGTH_SHORT).show();
        getDataForTable();

    }

    @Override
    public void getIDofInsert(String id) {

    }




    public void deleteData( int id){

        alertDialog.show();
        AsyncTaskDelete task = new AsyncTaskDelete(ctx,table ,String.valueOf(id));
        task.ctx = ctx;
        task.listener = this;

        task.execute();
    }


    @Override
    public void deleteResult(String str) {
        if (str.equals(ctx.getString(R.string.successful_update))) {
            Toast.makeText(ctx, "Διαγραφή επιτυχής", Toast.LENGTH_SHORT).show();
//            firstRow.removeAllViews();
//            ll.removeAllViews();

            getDataForTable();
        }
        else
            Toast.makeText(ctx, "Κάτι πήγε στραβά", Toast.LENGTH_SHORT).show();

        alertDialog.dismiss();
    }



    public Intent tableView_sigkentrotika(String query, String transgroupID, String [] panoTitloi,
                                          String [] plagioiTitloi , ArrayList<TableViewItem> lista,
                                          boolean exeiWatchID, boolean exeiSinolo, boolean editable){

        // AN IPARXEI WATCH ID DEN XREIAZETAI NA DILOSOUME PANO TITLOUS
        // τΤΟ ΧΗΡΙΣΜΟΠΟΙΟΥΜΕ ΟΤΑΝ ΘΕΛΟΥΜΕ ΟΙ ΩΡΕΣ ΝΑ ΕΙΝΑΙ ΑΠΟ ΠΑΝΩ

        Intent in = new Intent(act , TableActivity.class);
        in.putExtra("str_query",query);
        in.putExtra("transgroupID",transgroupID);
        in.putExtra("panoTitloi",panoTitloi);
        in.putExtra("tableView_cols" , lista);
        in.putExtra("plagioiTitloi",plagioiTitloi);
        in.putExtra("exeiWatchID",exeiWatchID);
        in.putExtra("exeiSinolo",exeiSinolo);
        in.putExtra("editable",editable);

        return in;


        //startActivity(in);



    }


    public static Intent tableView_sigkentrotika(String query, String transgroupID, Activity act, String [] panoTitloi,
                                          String [] plagioiTitloi , ArrayList<TableViewItem> lista,
                                          boolean exeiWatchID, boolean exeiSinolo, boolean editable){



        Intent in = new Intent(act, TableActivity.class);
        in.putExtra("str_query",query);
        in.putExtra("transgroupID",transgroupID);
        in.putExtra("panoTitloi",panoTitloi);
        in.putExtra("tableView_cols" , lista);
        in.putExtra("plagioiTitloi",plagioiTitloi);
        in.putExtra("exeiWatchID",exeiWatchID);
        in.putExtra("exeiSinolo",exeiSinolo);
        in.putExtra("editable",editable);

        return in;

    }



}
