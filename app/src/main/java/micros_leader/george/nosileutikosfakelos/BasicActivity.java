package micros_leader.george.nosileutikosfakelos;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.speech.SpeechRecognizer;

import androidx.annotation.NonNull;

import com.github.ag.floatingactionmenu.OptionsFabLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskDelete;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetFloors;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetMedicines;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetPatients;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetPlanoKlinonPatients;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetDelete;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended;
import micros_leader.george.nosileutikosfakelos.Interfaces.IData;
import micros_leader.george.nosileutikosfakelos.Listeners.SearchNosileuomenoListener;
import micros_leader.george.nosileutikosfakelos.Listeners.VoiceListener;
import micros_leader.george.nosileutikosfakelos.TableView.TableFragment;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Logodosia.MyVoiceService;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.TableView.TableActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon.Medicines;

import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.CHECKBOX_ITEM;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_ITEM_READ_ONLY_VALUE;





/*
    ΧΡΗΣΙΜΟΠΟΙΟΥΜΕ ΤΟ BasicActivity ΓΙΑ ΝΑ ΚΑΝΟΥΜΕ ΠΙΟ ΕΥΚΟΛΑ ΤΗ ΔΟΥΛΕΙΑ ΜΑΣ ΣΤΟ ΝΑ ΚΑΤΕΒΑΣΟΥΜΕ
    ΔΕΔΟΜΕΝΑ ΑΠΟ ΤΗ ΒΑΣΗ ΜΕ ΣΥΝΔΙΑΣΜΟ ΤΗΣ ΛΙΣΤΑΣ ΠΟΥ ΤΗ ΦΤΙΑ;ΧΤΝΟΥΜΕ ΣΤΟ INFOSPECIFI_LIST ΚΑΙ ΤΗ
    ΧΡΗΣΙΜΟΠΟΙΟΥΜΕ ΣΤΟ RECYCLERVIEW

    ΤΙ ΠΡΕΠΕΙ ΝΑ ΟΡΙΣΟΥΜΕ :

    1) ΠΑΜΕ ΣΤΗΝ ΚΛΑΣΗ INFOSPECIFI_LIST ΚΑΙ ΔΓΜΙΟΥΡΓΟΥΜΕ ΜΙΑ ΛΙΣΤΑ ΜΕ ΤΟ ΟΝΟΜΑ ΤΟΥ ΠΕΔΙΟΥ ΚΑΙ ΤΙ ΤΥΠΟΣ ΕΙΝΑΙ Π.Χ.
        TEXTVIEW EDITTEΧΤ CHECKBOX ΚΑΙ ΑΛΛΑ
    2) ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΘΕΛΟΥΜΕ ΝΑ ΒΛΕΠΟΥΜΕ ΚΑΙ ΤΙΤΛΟΥΣ ΣΤΟ RECYCLERVIEW ΓΡΑΦΟΥΜΕ ΣΤΟ  titloi_positions   = new int[]{POSITION, POSITION...};

    3) table = ΤΟ ΟΝΟΜΑ ΑΠΟ ΤΟ table ΤΗΣ ΒΑΣΗΣ ΩΣΤΕ ΝΑ ΚΑΤΕΒΑΣΕΙ ΟΛΑ ΤΑ COL_NAMES ΚΑΙ ΝΑ ΤΑ ΑΠΟΘΗΚΕΥΣΕΙ ΣΤΟ namejson
    4) ΚΑΛΕΣΜΑ ΤΗΣ ΜΕΘΟΔΟΥ ΓΙΑ ΤΟ ΚΑΤΕΒΑΣΜΑ    getAll_col_names(table);
    5) ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΕΧΟΥΜΕ RECYCLERVIEW ΠΟΥ ΣΤΗΝ ΟΥΣΙΑ ΘΑ ΕΧΟΥΜΕ ΕΠΕΙΔΗ ΓΙΑ ΑΥΤΟ ΓΙΝΕΤΑΙ ΟΛΗ ΑΥΤΗ Η ΙΣΤΟΡΙΑ

         ΔΗΜΙΟΥΡΓΟΥΜΕ  RECYCLERVIEW ΣΤΟ ACTIVITY POY KANEI EXTEND TO BASICACTIVITY KAI ENAN ADAPTER

        Π.Χ. PRIVATE RECYCLERVIEW recyclerView;
             PRIVATE RVparakolouthisiAdapter adapterRV;
        adapterRV = new RVparakolouthisiAdapter(this, InfoSpecificLists.getParakolouthisiListaForRV2(),titloi_positions);
                                (ΠΑΡΑΜΕΤΡΟΙ ΕΙΝΑΙ ΟΙ ΕΞΗΣ 1)TO CONTEXT 2)Η ΛΙΣΤΑ ΠΟΥ ΠΡΕΠΕΙ ΝΑ ΔΙΑΒΑΣΕ 3) titloi_positions ΓΙΑ ΝΑ ΔΕΙ ΤΟΥΣ ΤΙΤΛΟΥΣ
                                                                                                          ΔΕΝ ΜΑΣ ΝΟΙΑΖΕΙ ΑΣ ΕΙΝΑΙ ΚΑΙ ΑΔΕΙΟ)
    TO RVparakolouthisiAdapter ΕΙΝΑΙ ΜΙΑ ΚΛΑΣΗ ΠΟΥ ΚΑΝΕΙ EXTEND TO BASICRECYCLERVIEW ,
    ΓΙΑ ΚΑΘΕ ACTIVITY ΠΡΕΠΕΙ ΝΑ ΦΤΙΑΧΝΕΤΑΙ ΑΝΤΙΣΤΟΙΧΟ RVparakolouthisiAdapter

         ΚΑΛΟΥΜΕ    setRecyclerViewgridrLayaout(parakolouthisiRV, R.id.parakolouthisiRV,adapterRV,2,titloi_positions);
         Η ΕΞΗΣ ΜΕΘΟΔΟΣ KANEI INITIALIZE TO RECYCLERVIEW
        ( 1) recyclerview , 2) recyclerview_id , 3)recyclerview_adapter, 4) σε καθε σειρα ποσα πεδια να φαινονται
             sto gridlayout, 5) titloi_positions ας ειναι και αδειο)

    6) ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΘΕΛΟΥΜΕ ΝΑ ΔΙΑΛΕΓΟΥΜΕ ΗΜΕΡΟΜΗΝΙΑ   thereIsDatePicker(ΤΟ ID ΤΟΥ TEXTVIEW);
    7) ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΕΧΟΥΜΕ ΚΟΥΜΠΙ ΕΝΗΜΕΡΩΣΗς ΓΙΑ ΤΗ ΒΑΣΗ
        Α) thereIsUpdateButton(R.id.updateButton);;
        Β) insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID","Date","Watch"});
            ΣΤΗ ΛΙΣΤΑ ΒΑΖΟΥΜΕ ΤΑ ΟΝΟΜΑΤΑ ΠΟΥ ΕΡΧΟΝΤΑΙ ΑΠΟ ΤΗ ΒΑΣΗ ΤΑ ΟΠΟΙΑ ΔΕΝΘΕΛΟΥΜΕ ΝΕ ΕΜΦΑΝΙΣΤΟΥΝ ΣΤΗΝ ΟΘΟΝΗ ΜΑΣ
            ΔΙΑΦΟΡΕΤΙΚΑ ΘΑ ΓΙΝΕΙ ΜΠΕΡΔΕΜΑ ΣΤΗΝ ΑΝΤΙΣΤΟΙΧΙΑ ΟΝΟΜΑΤΩΝ ΚΑΙ ΤΙΜΩΝ ΑΠΟ ΤΗ ΒΑΣΗ, ΜΠΟΡΕΙ ΝΑ ΣΠΑΣΕΙ ΚΑΙ Η ΕΦΑΡΜΟΓΗ

        thereIsUpdateButton(R.id.updateButton);
        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID","Date","Watch"});

        ΤΟ insertOrUpdateListener ΑΝΑΓΝΩΡΙΖΕΙ ΠΟΤΕ ΝΑ ΚΑΝΕΙ INSERT , UPDATE
        ΣΤΗΝ ΟΥΣΙΑ ΣΤΕΛΝΟΥΜΕ ΣΤΟΝ ΣΕΡΒΕΡ ΔΥΟ ΛΙΣΤΕΣ Η ΜΙΑ ΜΕ ΤΑ COL_NAMES (namejson )
        και η αλλη με τις τιμες values (valuesjson) και οταν του δινεται το ID κανει UPDATE διαφορετικα INSERT
        υπαρχει boolean μεταβλητη weHaveData που με βαση αυτη κινειται σε περιπτωση που εχεικατεβασει δεοδμενα για ασθενη θα κανει
                                                                                    update διαφορετικα insert

//-------------------------------------------------

        ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΣΤΟ ΜΕΛΛΟΝ ΠΡΟΣΘΕΣΟΥΜΕ ΠΕΔΙΑ ΣΕ ΚΑΠΟΙΟ TABLE ΤΑ ΟΠΟΙΑ ΟΜΩς ΔΕΝ ΘΕΛΟΥΜΕ ΝΑ ΕΜΦΑΝΙΖΟΝΤΑΙ ΣΤΗΝ ΟΘΟΝΗ ΜΑΣ
        ΜΕ ΤΗ ΣΕΙΡΑ ΠΟΥ ΕΙΝΑΙ ΣΤΗ ΒΑΣΗ ΘΑ ΠΡΕΠΕΙ ΝΑ ΚΑΝΟΥΜΕ OVERIDE THN ΕΞΗΣ ΜΕΘΟΔΟ ΚΑΙ ΝΑ ΚΑΝΟΥΜΕ ΑΛΛΑΓΗ ΘΕΣΕΩΝ ΣΤΙΣ
        ΛΙΣΤΕΣ namejson, valuesjson Π.Χ.

        (ΒΛΕΠΕ     ParakoloythisiActivity)

    @Override
    public void taskGet_all_col_names(ArrayList<String> col_names) {
        super.taskGet_all_col_names(col_names);

ΑΥΤΗ Η repositionNames ΜΕΘΟΔΟΣ ΔΕΧΕΤΑΙ ΤΟ col_name  ΚΑΙ ΤΟ POSITION ΠΟΥ ΘΕΛΟΥΜΕ ΝΑ ΤΟ ΒΑΛΟΥΜΕ

        repositionNames("out_paroxetefsi3",16);
        repositionNames("out_paroxetefsi4",17);
        repositionNames("sakxara",8);

    }



    ΣΕ ΑΥΤΟ ΤΟ   Override ΠΟΥ ΕΤΣΙ ΚΑΙ ΑΛΛΙΩς ΘΑ ΤΟ ΧΡΗΣΙΜΟΠΟΙΟΥΜΕ ΣΕ ΚΑΘΕ ΑΚΤΙΒΙΤΥ ΜΙΑΣ ΚΑΙ ΛΟΓΙΚΑ ΘΑ ΕΧΕΙ ΛΛΕΣ ΠΑΡΑΜΕΤΡΟΥΣ ΤΟ ΚΑΘΕΝΑ

     @Override
    public void etaskComplte2(JSONArray results) throws JSONException {
        super.taskComplete2(results);
        {
            ...
            .....
            ...

            ΑΥΤΗ Η ΜΕΘΟΔΟΣ ΔΕΧΕΤΑΙ ΤΗΝ ΠΑΛΙΑ ΘΕΣΗ ΚΑΙ ΤΗΝ ΚΑΙΝΟΥΡΓΙΑ ΠΟΥ ΘΕΛΟΥΜΕ ΝΑ ΒΑΛΟΥΜΕ ΣΤΟ valuesjson

            repositionValues(17,14);
            repositionValues(18,15);
            repositionValues(19,6);
        }



    ΕΠΙΣΗΣ ΠΡΕΠΕΙ ΝΑ ΚΑΝΟΥΜΕ Override ΤΑ ΑΠΟ ΚΑΤΩ ΚΑΙ ΝΑ ΤΟΥΣ ΑΝΑΦΕΡΟΥΜΕ ΤΗΝ ΜΕΘΟΔΟ ΣΤΟ ΕΚΑΣΤΟΤΕ ACTIVITY ΠΟΥ ΘΕΛΟΥΜΕ ΝΑ ΤΡΕΞΟΥΜΕ
    ΓΙΑ ΝΑ ΤΡΑΒΑΕΙ ΤΑ ΔΕΔΟΜΕΝΑ ΑΠΟ ΤΗ ΒΑΣΗ Π.Χ.


    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        getParakolouthisi();

    }

    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);

        getParakolouthisi();
    }
// ---------------------------------------------

             ------------   UPDATE!!!!!!!!!!!!!!!!!!!!!!!

             TO UPDATE ΑΦΟΡΑ ΤΟ ΠΩς ΠΑΙΡΝΟΥΜΕ ΤΑ ΠΕΔΙΑ ΠΛΕΟΝ ΔΕΝ ΤΑ ΤΡΑΒΑΜΕ ΟΛΑ ΑΠΟ ΤΗ ΒΑΣΗ ΑΛΛΑ
             ΣΕ ΚΑΘΕ ΑΝΤΙΚΕΙΜΕΝΟ ΤΟΥ ItemsRV ΣΤΟ InfoSpecificLists ΓΡΑΦΟΥΜΕ ΤΟ ΟΝΟΜΑ ΤΟΥ ΠΕΔΙΟΥ ΠΟΥ ΕΙΝΑΙΣ ΤΗ ΒΑΣΗ ΕΤΣΙ ΩΣΤΕ ΣΤΟ ΜΕΛΛΟΝ ΑΝ ΧΡΕΙΑΣΤΕΙ
             ΝΑ ΑΛΛΑΞΟΥΜΕ ΚΑΤΙ ΔΕΝ ΘΑ ΧΡΕΙΑΣΤΕΙ ΝΑ ΠΑΙΞΟΥΜΕ ΜΕ ΘΕΣΕΙΣ ΑΠΛΑ ΘΑ ΕΠΙΚΕΝΤΡΩΘΕΙ ΣΤΑ ΣΩΣΤΑ ΠΕΔΙΑ


 */












public class BasicActivity  extends AppCompatActivity implements IData, AsyncCompleteTask2,
        AsyncGetUpdate_JSON, MyDialogFragmentCloseListener  ,  DataSended, AsyncGetDelete ,
         AsyncGetUpdateResult  {

    public TextView dateTV,patientsTV, nurseTV;
    public TextView timeTV;
    public String id;
    public String transgroupID;
    public String patientID;
    public String nurseID = "";
    public String table;
    public String date;
    public String period;
    public String watchID;
    public String vardiaID;
    public String [] read_only_col;
    public AlertDialog alertDialog;
    public ArrayList<PatientsOfTheDay> patientsNosileuomenoi;
    public ArrayList<Medicines> medicinesArrayList;
    public ArrayList<String> nameJson = new ArrayList<>(), valuesJson = new ArrayList<>();
    public ArrayList<JSONObject> jsonLista;
    public ArrayList<ItemsRV> list;
    public RecyclerView recyclerView;
    public int titloi_positions[] = new int[]{};
    public int floorID;
    public int sinolikosVathmos;
    public String userID;
    public boolean weHaveData,  isVoiceOn = false;
    public ArrayAdapter arrayAdapter;
    public CircularProgressButton updateButton;
    public ImageButton updateIMB;
    public Spinner floorSP;
    public BasicRV adapter;
    public ArrayAdapter floorAdapter;
    public Spinner_new_Adapter spinnerNewAdapter;
    public HashMap<String, Integer> floorsMap;
    public Button voiceBT;
    public Calendar myCalendar;
    public ArrayList<ItemsRV> listaAdaptor ,newList;
    public Toolbar toolbar;
    public FloatingActionButton fab;
    public OptionsFabLayout fabMenu;
    public CheckBox checkOnceCH;
    public Activity extendedActivity;
    public String action ,str_query,eggrafiID ;
    public ArrayList<String> medicinesLista;
    public HashMap<String, Integer> medicinesHashMap;
    public Spinner vardiaSP;
    public NpaGridLayoutManager manager;
    public boolean loading = true;
    public int pastVisiblesItems, visibleItemCount, totalItemCount;


    // Για την ομιλία
    private SpeechRecognizer sr;
    private static final String TAG = "MyActivity";
    int code;
    private Messenger mServiceMessenger;

    boolean serviceconneted;
    static final Integer LOCATION = 0x1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        nameJson = new ArrayList<>();
        valuesJson = new ArrayList<>();
        jsonLista = new ArrayList<>();
        medicinesArrayList = new ArrayList<>();
        alertDialog = Utils.setLoadingAlertDialog(this);
        alertDialog.setCancelable(true);

        userID = Utils.getUserID(this);

    }


    public void getPatientsList( Activity extendedActivity) {

        this.extendedActivity = extendedActivity;
        new AsyncTaskGetFloors(this, floorSP, floorAdapter).execute();

    }

    public void getPatientsList( Activity extendedActivity, int textviewID, int spinnerID) {

        floorSP = findViewById(spinnerID);
        patientsTV = findViewById(textviewID);
        this.extendedActivity = extendedActivity;
        new AsyncTaskGetFloors(this, floorSP, floorAdapter).execute();

    }

    public void getPatientsList( Activity extendedActivity, Spinner floorSP, TextView patientsTV) {

        this.floorSP = floorSP;
        this.patientsTV = patientsTV;
        this.extendedActivity = extendedActivity;
        new AsyncTaskGetFloors(this, floorSP, floorAdapter).execute();

    }



        public void getFloors(Spinner floorSP, Activity extendedActivity){

        this.floorSP = floorSP;
        this.extendedActivity = extendedActivity;
        new AsyncTaskGetFloors(this, this.floorSP, floorAdapter).execute();

    }





    public void getPatientsList(int id){
        patientsTV = findViewById(id);
        new AsyncTaskGetPatients(this, patientsTV).execute();

    }

    public void initElements(Object[] obj, int [] ids){

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        for (int i=0; i<obj.length; i++){

                obj[i] =  findViewById(ids[i]);

        }


    }

    public void thereIsVoiceButton(int buttonID, EditText editText){

        voiceBT = findViewById(buttonID);

        sr = SpeechRecognizer.createSpeechRecognizer(extendedActivity);
        sr.setRecognitionListener(new VoiceListener(this, editText));

        voiceBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isVoiceOn){
                    // ΓΙΑ ΝΑ ΓΡΑΦΤΕΙ Η ΦΩΝΗ
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        askForVoicePermission(Manifest.permission.RECORD_AUDIO, LOCATION);
                    }
                    Intent i = new Intent(extendedActivity, MyVoiceService.class);
                    bindService(i, connection, code);
                    startService(i);
                    voiceBT.setText("Σταματημα εγγραφης");
                    Toast.makeText(extendedActivity, "Μιλήστε", Toast.LENGTH_SHORT).show();

                    isVoiceOn = true;
                }

                else{

                    Intent i = new Intent(extendedActivity, MyVoiceService.class);
                    stopService(i);
                    voiceBT.setText("Εγγραφή");
                    Toast.makeText(extendedActivity, "Τέλος εγγραφής", Toast.LENGTH_SHORT).show();
                    isVoiceOn = false;
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (extendedActivity !=null) {
            Intent i = new Intent(extendedActivity, MyVoiceService.class);
            stopService(i);
        }
    }


    public void thereIsDatePicker(TextView date_textview) {

        date_textview.setText(Utils.getCurrentDate());

        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener dateStr = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(date_textview);
            }

        };

        date_textview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(extendedActivity, dateStr, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void thereIsDatePicker(int dateID) {
        dateTV = findViewById(dateID);
        thereIsDatePicker(dateTV);

    }


    public void thereIsTimePicker(int timeID){

        timeTV = findViewById(timeID);
        timeTV.setText(Utils.getCurrentTime2());

        timeTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(extendedActivity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeTV.setText( Utils.chechZeroInTime(selectedHour + ":" + selectedMinute));
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });
    }

    public void thereIsFloatingButton(int id){
        fab = findViewById(id);

    }


    public void updateLabel(TextView date_tv) {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date_tv.setText(sdf.format(myCalendar.getTime()));
        date = date_tv.getText().toString();
    }

    public void thereIsImageUpdateButton(){

        if (toolbar == null)
            toolbar = findViewById(R.id.toolbar);
        if (toolbar == null)
            return;
        setSupportActionBar(toolbar);

        updateIMB = new ImageButton(this);
        Toolbar.LayoutParams l1=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l1.gravity= Gravity.START;
        updateIMB.setLayoutParams(l1);
        updateIMB.setBackgroundResource(R.drawable.save_icon_48px);
        updateIMB.setPadding(20,20,20,20);
        updateIMB.setScaleType(ImageButton.ScaleType.FIT_CENTER);
        toolbar.addView(updateIMB);
        Toolbar.LayoutParams l3=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l3.gravity= Gravity.END;
        updateIMB.setLayoutParams(l3);

    }




    public void thereIsCheckBox_toolbar(String text){

        Toolbar t= findViewById(R.id.toolbar);
        setSupportActionBar(t);

        checkOnceCH = new CheckBox(this);
        Toolbar.LayoutParams l1=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l1.gravity= Gravity.START;
        checkOnceCH.setLayoutParams(l1);
        checkOnceCH.setText(text);
        t.addView(checkOnceCH);
        Toolbar.LayoutParams l3=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l3.gravity= Gravity.END;
        checkOnceCH.setLayoutParams(l3);

    }

    public void thereIsUpdateButton(int id){

        updateButton = findViewById(id);


    }

    public void thereIsVardiesSpinner(int id, ArrayList<Spinner_item> lista ){

        vardiaSP = findViewById(id);
        spinnerNewAdapter =   new Spinner_new_Adapter(this, R.layout.spinner_layout2, (Spinner_item[]) lista.toArray(new Spinner_item[lista.size()]));
        spinnerNewAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vardiaSP.setAdapter(spinnerNewAdapter);

    }



    public void setSpinnerAdapter(Spinner spinner, ArrayList lista ) {


        arrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout2, lista);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);


    }


    public static void setRecyclerViewLinearLayout(RecyclerView recyclerView, Context ctx){

        recyclerView.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(ctx, LinearLayout.VERTICAL));
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);


    }



    public void setRecyclerViewLinearLayout(RecyclerView recyclerView, int id, RecyclerView.Adapter adapter){



        recyclerView = findViewById(id);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        if (adapter != null)
            recyclerView.setAdapter(adapter);

    }

    public static void setRecyclerViewLinearLayout(RecyclerView recyclerView, Activity act, RecyclerView.Adapter adapter){

        recyclerView.setLayoutManager(new LinearLayoutManager(act, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(act, LinearLayout.VERTICAL));
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        if (adapter != null)
            recyclerView.setAdapter(adapter);

    }


    public void setRecyclerViewHorizontalLinearLayout(RecyclerView recyclerView, int id, RecyclerView.Adapter adapter){



        recyclerView = findViewById(id);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.HORIZONTAL));
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        if (adapter != null)
            recyclerView.setAdapter(adapter);

    }

    public void setRecyclerViewgridrLayaout(  RecyclerView.Adapter adapter, int spanCount, final int[] theseisTitloi ){

        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        manager = new NpaGridLayoutManager(this, spanCount);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                boolean isHeader = isHeader(position,theseisTitloi);
                if (isHeader)
                    return  2;
                else
                    return  1;

            }
        });
        recyclerView.setLayoutManager(manager);
        if (adapter != null)
            recyclerView.setAdapter(adapter);
    }


    public void setRecyclerViewgridrLayaout( int id, RecyclerView.Adapter adapter, int spanCount, final int[] theseisTitloi ){

        recyclerView = findViewById(id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);

        manager = new NpaGridLayoutManager(this, spanCount);
        manager.setAutoMeasureEnabled(false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                boolean isHeader = isHeader(position,theseisTitloi);
                if (isHeader)
                    return  2;
                else
                    return  1;

            }
        });
        recyclerView.setLayoutManager(manager);
        if (adapter != null)
            recyclerView.setAdapter(adapter);
    }




    public void setRecyclerViewgridrLayaout(RecyclerView recyclerView, int id, RecyclerView.Adapter adapter, int spanCount, final int[] theseisTitloi ){

        recyclerView = findViewById(id);

        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
      //  recyclerView.setNestedScrollingEnabled(false);

        GridLayoutManager manager = new GridLayoutManager(this, spanCount);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                boolean isHeader = isHeader(position,theseisTitloi);
                if (isHeader)
                    return  2;
                else
                    return  1;

            }
        });
        recyclerView.setLayoutManager(manager);
        if (adapter != null)
            recyclerView.setAdapter(adapter);
    }



    public static boolean isHeader(int position , int[] theseisTitloi) {

        if (theseisTitloi != null) {
            for (int x : theseisTitloi) {   // ΕΛΕΓΧΕΙ ΑΝ ΤΟ position ΥΠΑΡΧΕΙ ΣΤΗ ΛΙΣΤΑ  theseisTitloi
                if (x == position) {
                    return true;
                }

            }
        }
        return false;

    }


    public  void ET_setText(EditText [] editText  , ArrayList <String> lista, boolean fromEditTextToList){

        for (int i=0; i<editText.length; i++) {
            if (fromEditTextToList)
                lista.add(editText[i].getText().toString());
            else
                editText[i].setText(lista.get(i));
        }
    }


    public void getAll_col_names( ArrayList<ItemsRV> lista){

        //newList = lista;
        ArrayList <String> read_only_cols_List = new ArrayList<>();
        for (int i = 0; i<lista.size(); i++){
            String colName = lista.get(i).getCol_name();
            if (colName != null) {
                nameJson.add(colName);
                if (lista.get(i).getType() == TEXTVIEW_ITEM_READ_ONLY_VALUE)
                    read_only_cols_List.add(colName);
            }
            if (read_only_cols_List.size() > 0)
                read_only_col = read_only_cols_List.toArray(new String[read_only_cols_List.size()]);
        }


    }


    public void setRead_only_col(String... cols) {
        this.read_only_col = cols;
    }

    public void  getAll_col_names_and_set_adapter(Activity act, ArrayList<ItemsRV> lista, int [] titloi_positions){

        adapter = new BasicRV(act, InfoSpecificLists.getNosil_Istoriko_lista_new(),titloi_positions);
        listaAdaptor = adapter.result;

        newList = lista;
        for (ItemsRV item : lista)
            if (item.getCol_name() != null )
                nameJson.add(item.getCol_name());



    }


    public void getAll_col_names( ArrayList<ItemsRV> lista, ArrayList<String> customList){

        for (int i = 0; i<lista.size(); i++){
            if (lista.get(i).getCol_name() != null)
                customList.add(lista.get(i).getCol_name());

        }

    }

    public void setAll_col_names( ArrayList<String> lista){
        nameJson.addAll(lista);
    }




    @Override
    public void taskCompleteGetPatients(ArrayList<PatientsOfTheDay> lista) {
    // ΤΟΠΟΘΕΤΗΣΗ ΤΟΥ ΛΙΣΕΝΕΡ ΕΔΩ ΕΠΕΙΔΗ ΔΙΑΦΟΡΕΤΙΚΑ ΠΑΙΡΝΕΙ ΚΕΝΗ ΛΙΣΤΑ
        patientsNosileuomenoi = lista;

        transgroupID = Utils.getSplitSPartString(patientsTV.getText().toString(),"," , 3);

     //   Toast.makeText(extendedActivity, transgroupID, Toast.LENGTH_SHORT).show();
        patientsTV.setOnClickListener(new SearchNosileuomenoListener(this, patientsNosileuomenoi));
        alertDialog.hide();


    }

    public Intent tableView_sigkentrotika(String query, String transgroupID, String [] panoTitloi,
                                        String [] plagioiTitloi , ArrayList<TableViewItem> lista,
                                        boolean exeiWatchID, boolean exeiSinolo, boolean editable){

        // AN IPARXEI WATCH ID DEN XREIAZETAI NA DILOSOUME PANO TITLOUS
        // τΤΟ ΧΗΡΙΣΜΟΠΟΙΟΥΜΕ ΟΤΑΝ ΘΕΛΟΥΜΕ ΟΙ ΩΡΕΣ ΝΑ ΕΙΝΑΙ ΑΠΟ ΠΑΝΩ

        Intent in = new Intent(extendedActivity , TableActivity.class);
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

    public static Intent tableView_sigkentrotika(Activity act,String query, String transgroupID, String [] panoTitloi,
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

    }

    public Intent tableView_sigkentrotika(String query, String transgroupID,
                                          String [] plagioiTitloi , ArrayList<TableViewItem> lista,
                                          boolean exeiWatchID, boolean exeiSinolo, boolean editable){



        return   tableView_sigkentrotika( query,  transgroupID, null,
                 plagioiTitloi ,  lista, exeiWatchID ,exeiSinolo ,editable);

        //startActivity(in);



    }



    public Intent tableView_sigkentrotika(String query, String transgroupID, String [] panoTitloi,
                                        String [] plagioiTitloi ,String [] col_names ,
                                        boolean exeiWatchID, boolean exeiSinolo, boolean editable){

        // AN IPARXEI WATCH ID DEN XREIAZETAI NA DILOSOUME PANO TITLOUS
        // τΤΟ ΧΗΡΙΣΜΟΠΟΙΟΥΜΕ ΟΤΑΝ ΘΕΛΟΥΜΕ ΟΙ ΩΡΕΣ ΝΑ ΕΙΝΑΙ ΑΠΟ ΠΑΝΩ

        Intent in = new Intent(extendedActivity , TableActivity.class);
        in.putExtra("str_query",query);

        ArrayList <TableViewItem> lista = new ArrayList();
        lista.add(new TableViewItem("pedio",StaticFields.EDITTEXT_ITEM,StaticFields.KEIMENO));


        in.putExtra("tableViewItem" , lista);

        in.putExtra("transgroupID",transgroupID);
        in.putExtra("panoTitloi",panoTitloi);
        in.putExtra("plagioiTitloi",plagioiTitloi);
        in.putExtra("col_names",col_names);
        in.putExtra("exeiWatchID",exeiWatchID);
        in.putExtra("exeiSinolo",exeiSinolo);
        in.putExtra("editable",editable);

        return in;

        //startActivity(in);

    }





    public Bundle tableView_sigkentrotika_dialogFragment(String query, String transgroupID, String [] panoTitloi,
                                          String [] plagioiTitloi , ArrayList<TableViewItem> lista,
                                          boolean exeiWatchID, boolean exeiSinolo, boolean editable){

        // AN IPARXEI WATCH ID DEN XREIAZETAI NA DILOSOUME PANO TITLOUS
        // τΤΟ ΧΗΡΙΣΜΟΠΟΙΟΥΜΕ ΟΤΑΝ ΘΕΛΟΥΜΕ ΟΙ ΩΡΕΣ ΝΑ ΕΙΝΑΙ ΑΠΟ ΠΑΝΩ

        Bundle bundle = new Bundle();

        bundle.putString("str_query",query);
        bundle.putString("transgroupID",transgroupID);
        bundle.putStringArray("panoTitloi",panoTitloi);
        bundle.putSerializable("tableView_cols" , lista);
        bundle.putStringArray("plagioiTitloi",plagioiTitloi);
        bundle.putBoolean("exeiWatchID",exeiWatchID);
        bundle.putBoolean("exeiSinolo",exeiSinolo);
        bundle.putBoolean("editable",editable);




        return bundle;



    }

    public Bundle tableView_sigkentrotika_dialogFragment(String query, String transgroupID,
                                                         String [] plagioiTitloi , ArrayList<TableViewItem> lista,
                                                         boolean exeiWatchID, boolean exeiSinolo, boolean editable){

        return         tableView_sigkentrotika_dialogFragment(query,transgroupID,plagioiTitloi,null,lista,exeiWatchID,exeiSinolo,editable);




    }


    public Bundle tableView_sigkentrotika_dialogFragment(String query, String transgroupID, String [] panoTitloi,
                                          String [] plagioiTitloi ,String [] col_names ,
                                          boolean exeiWatchID, boolean exeiSinolo, boolean editable){

        // AN IPARXEI WATCH ID DEN XREIAZETAI NA DILOSOUME PANO TITLOUS
        // τΤΟ ΧΗΡΙΣΜΟΠΟΙΟΥΜΕ ΟΤΑΝ ΘΕΛΟΥΜΕ ΟΙ ΩΡΕΣ ΝΑ ΕΙΝΑΙ ΑΠΟ ΠΑΝΩ

        Bundle bundle = new Bundle();

        bundle.putString("str_query",query);

        ArrayList <TableViewItem> lista = new ArrayList();
        lista.add(new TableViewItem("pedio", StaticFields.EDITTEXT_ITEM,StaticFields.KEIMENO));


        bundle.putSerializable("tableViewItem" , lista);

        bundle.putString("transgroupID",transgroupID);
        bundle.putStringArray("panoTitloi",panoTitloi);
        bundle.putStringArray("plagioiTitloi",plagioiTitloi);
        bundle.putStringArray("col_names",col_names);
        bundle.putBoolean("exeiWatchID",exeiWatchID);
        bundle.putBoolean("exeiSinolo",exeiSinolo);
        bundle.putBoolean("editable",editable);

        return bundle;


    }



    public void showFragment( Bundle bundle, FragmentContainerView frv){


        TableFragment tf1 = new TableFragment();
        tf1.setArguments(bundle);


        for (Fragment fr : getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fr).commit();
        }
        frv.clearChildFocus(frv);

        getSupportFragmentManager().beginTransaction()
                .add(frv.getId(), tf1)
                .commit();


    }

    public void showFragment( Bundle bundle, FrameLayout frv){


        TableFragment tf1 = new TableFragment();
        tf1.setArguments(bundle);


        for (Fragment fr : getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fr).commit();
        }
        frv.clearChildFocus(frv);

        getSupportFragmentManager().beginTransaction()
                .add(frv.getId(), tf1)
                .commit();


    }













    public void showDiagram(String transgroupID, String date,ArrayList<String> katigoriesLista , boolean isAnaOra){

        BasicDialogFragmentDiagram df =  new BasicDialogFragmentDiagram();
        Bundle putextra = new Bundle();
        putextra.putString("transgroupID", transgroupID);
        putextra.putString("date", date);
        putextra.putStringArrayList("katigoriesLista", katigoriesLista);
        putextra.putBoolean("isAnaOra", isAnaOra);
        df.setArguments(putextra);
        df.show(getSupportFragmentManager() , "Dialog");

    }


    public void showDiagram(String transgroupID, String date,ArrayList<String> katigoriesLista ,int kathe_poses_ores){

        BasicDialogFragmentDiagram df =  new BasicDialogFragmentDiagram();
        Bundle putextra = new Bundle();
        putextra.putString("transgroupID", transgroupID);
        putextra.putString("date", date);
        putextra.putStringArrayList("katigoriesLista", katigoriesLista);
        if (kathe_poses_ores != 0)
            putextra.putInt("kathe_poses_ores",kathe_poses_ores);
        df.setArguments(putextra);
        df.show(getSupportFragmentManager() , "Dialog");

    }

    public void getJSON_DATA(String str_query ){

        if (Utils.isNetworkAvailable2(this)) {
            this.str_query = str_query;

            if (alertDialog != null)
                alertDialog.show();
            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = getApplicationContext();
            task.listener = this;
            task.query = str_query;
            task.execute();
        }
    }

    public void getJSON_DATA(String str_query,String[] cols, ArrayList<String> cols_from_list ){

        // ΕΔΩ ΕΙΝΑΙ ΓΙΑ ΝΑ ΜΗ ΓΡΑΦΩ ΤΑ ΠΕΔΙΑ ΑΝ ΕΧΩ ΠΟΛΛΑ ΣΤΟ SELECT
        if (str_query.contains("select"))
            Toast.makeText(extendedActivity, "to query πρεπει να συνταχθει απο το FROM και μετα", Toast.LENGTH_SHORT).show();
        else {
            // ΕΔΩ ΕΙΝΑΙ ΓΙΑ QUERY ΟΠΟΥ ΠΑΙΡΝΕΙ ΤΑ ΟΝΟΜΑΤΑ ΠΕΔΙΩΝ ΑΠΟ ΤΗ ΛΙΣΤΑ ΠΟΥ ΕΧΟΥΜΕ ΔΗΜΙΟΥΡΓΗΣΕΙ
            // ΓΙΑ ΤΟ RECYCLERVIEW
            // ΤΟ QUERY ΠΟΥ ΔΙΝΟΥΜΕ ΘΑ ΠΡΕΠΕΙ ΝΑ ΕΧΕΙ ΣΥΝΤΑΞΗ ΑΠΟ ΤΟ ΚΑΙ FROM ΜΕΤΑ
            StringBuilder pedia = new StringBuilder();
            pedia.append("select ");
            if (cols != null) {
                for (int i = 0; i < cols.length; i++) {
                    pedia.append(cols[i] + ",");
                }
            }

            if (cols_from_list != null) {
                for (int i = 0; i < cols_from_list.size(); i++) {
                    if (i == cols_from_list.size() - 1)
                        pedia.append(cols_from_list.get(i) + " ");
                    else
                        pedia.append(cols_from_list.get(i) + ",");
                }
            }


            this.str_query = pedia.toString() + " " + str_query;
            if(alertDialog != null)
                alertDialog.show();
            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = getApplicationContext();
            task.listener = this;
            task.query = pedia.toString() + " " + str_query;
            task.execute();
        }
    }

    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null) {

            if (!results.getJSONObject(0).has("status")) {



                if (results.getJSONObject(0).has("UserID"))
                     nurseID = Utils.convertObjToString(results.getJSONObject(0).get("UserID"));

                if(nurseTV != null && results.getJSONObject(0).has("username") )
                    nurseTV.setText(Utils.convertObjToString(results.getJSONObject(0).get("username")));


                ArrayList <String> namesClone = nameJson;


                nameJson = new ArrayList<>();
                valuesJson = new ArrayList<>();
                while(namesClone.size() > nameJson.size() && namesClone.size()> valuesJson.size()){
                    nameJson.add("");
                    valuesJson.add("");
                }

                weHaveData = true;

                if (results.length() > 1) {
//-------------------- ΓΙΑ ΠΟΛΛΕΣ ΕΓΓΡΑΦΕΣ ΤΟΥ ΣΥΓΚΕΚΡΙΜΕΝΟΥ ΑΤΟΜΟΥ
                jsonLista = new ArrayList<>();

                    for (int i = 0; i < results.length(); i++) {

                        JSONObject jsonObject = results.getJSONObject(i);
                        jsonLista.add(jsonObject);
                    }

                    JSONObject jsonObject = results.getJSONObject(0);

                    Iterator<String> keys = jsonObject.keys();
                    while (keys.hasNext()) {

                        String str_Name = keys.next();
                        String value = jsonObject.optString(str_Name);

                        String name_lower = str_Name.toLowerCase();


                        switch (str_Name) {
                            case "id":
                                id = value;
                                break;
                            case "transgroupid":
                                transgroupID = value;
                                break;
                            case "patientid":
                                patientID = value;
                                break;
                            case "period":
                                period = value;
                                break;
                            case "watch":
                                watchID = value;
                                break;
                        }


                        if (namesClone.contains(str_Name)) {
                            int pos = namesClone.indexOf(str_Name);
                            nameJson.set(pos,str_Name);
                            valuesJson.set(pos,value);
                        }
                    }

                } else {
                    // ------------- ΤΟ ΤΙ ΚΑΝΕΙ ΟΤΑΝ ΕΙΝΙΑ ΝΑ ΜΑΣ ΦΕΡΕΙ ΜΙΑ ΕΓΓΡΑΦΗ ΤΟΥ ΣΥΓΚΕΚΡΙΜΕΝΟΥ ΑΤΟΜΟΥ -------------


                    JSONObject jsonObject = results.getJSONObject(0);


                    Iterator<String> keys = jsonObject.keys();
                    while (keys.hasNext()) {

                        String str_Name = keys.next();
                        String value = jsonObject.optString(str_Name);
                        String name_lower = str_Name.toLowerCase();

                        switch (name_lower) {
                            case "id":
                                id = value;
                                break;
                            case "transgroupid":
                                transgroupID = value;
                                break;
                            case "patientid":
                                patientID = value;
                                break;
                            case "period":
                                period = value;
                                break;
                            case "watch":
                                watchID = value;
                                break;
                            case "sinolo_vathmou":
                                if (value.equals(""))
                                    value = "0";
                                sinolikosVathmos = Integer.parseInt(value);
                                break;
                            case "action":
                                action = value;
                                break;
                        }


                        if (namesClone.contains(str_Name)) {
                            int pos = namesClone.indexOf(str_Name);
                            nameJson.set(pos,str_Name);
                            valuesJson.set(pos,value);
                        }

                    }


                }



     //       }

            } else {
                weHaveData = false;
                nurseID = "";
                id = "";
            }
        }
        else {
            weHaveData = false;
            nurseID = "";
            id = "";
        }





        if(alertDialog != null)
            alertDialog.dismiss();

    }



    public void setAdapterAndUpdateValues(int rv_id , int positionsToGrid, String [] cols){

        if (adapter == null) {
            listaAdaptor = newList;
            adapter = new BasicRV(this, listaAdaptor, titloi_positions);
            listaAdaptor = adapter.result;
            setRecyclerViewgridrLayaout(rv_id, adapter, positionsToGrid, titloi_positions);
            insertOrUpdateListener(listaAdaptor,cols);

        }
        else
            adapter.updateLista(newList);
    }


// ΒΟΛΕΥΕΙ ΓΙΑ ΦΡΑΓΚΜΕΝΤ


    public void insertOrUpdateListener(final CircularProgressButton but, final ArrayList<ItemsRV>  listaAdaptor, final String names_col[],
                                       final ArrayList<String> namesLista, final ArrayList<String> valuesLista){
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nurseID.equals(Utils.getUserID(BasicActivity.this)) || nurseID.equals("")) {

                    but.startAnimation();
                    insert_or_update_data(listaAdaptor, names_col, namesLista, valuesLista);

                }
                else
                    Toast.makeText(BasicActivity.this, R.string.error_user_id, Toast.LENGTH_SHORT).show();


            }
        });
    }
//---------
    public void insertOrUpdateListener(final ArrayList<ItemsRV>  listaAdaptor, final String names_col[]){

        if (updateButton != null) {

            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (nurseID.equals(Utils.getUserID(BasicActivity.this))|| nurseID.equals("")) {

                    updateButton.startAnimation();
                    insert_or_update_data(listaAdaptor, names_col);
                    }
                    else
                        Toast.makeText(BasicActivity.this, R.string.error_user_id, Toast.LENGTH_SHORT).show();
                }
            });

        }

        else if (updateIMB != null){
            updateIMB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nurseID.equals(Utils.getUserID(BasicActivity.this))|| nurseID.equals("")) {

                          insert_or_update_data(listaAdaptor, names_col);
                }
                else
                        Toast.makeText(BasicActivity.this, R.string.error_user_id, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void insertOrUpdateListener(final EditText editText [], final TextView textView[], final String names_col[]){

        if (updateButton != null) {

            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nurseID.equals(Utils.getUserID(BasicActivity.this)) || nurseID.equals("")) {

                    updateButton.startAnimation();
                    insert_or_update_data(editText, textView, names_col);
                    }
                    else
                        Toast.makeText(BasicActivity.this, R.string.error_user_id, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (updateIMB != null){
            updateIMB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nurseID.equals(Utils.getUserID(BasicActivity.this))) {

                    insert_or_update_data(editText, textView, names_col);
                }
                else
                    Toast.makeText(BasicActivity.this, R.string.error_user_id, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void insert_or_update_data(final EditText editText [], final TextView textView[], String names_col[]){

        alertDialog.show();
        AsyncTaskUpdate_JSON task;

        setValuesTo_valuesJSON(editText, textView);



        if (weHaveData)
            task = new AsyncTaskUpdate_JSON(this, id , transgroupID,table, nameJson,replaceTrueOrFalse(valuesJson), titloi_positions);
        else
            task = new AsyncTaskUpdate_JSON(this,  transgroupID,table, nameJson,replaceTrueOrFalse(valuesJson),titloi_positions);



        task.names_col = names_col;
        task.date = date;
        task.period = period;
        task.watchID = watchID;
        task.listener =  this;
        task.execute();
    }




    public void insert_or_update_data(ArrayList<ItemsRV>  listaAdaptor , String names_col[]){

        alertDialog.show();
        AsyncTaskUpdate_JSON task;


        setValuesTo_valuesJSON(titloi_positions, listaAdaptor);

        if (weHaveData)
            task = new AsyncTaskUpdate_JSON(this, id , transgroupID,table, nameJson,replaceTrueOrFalse(valuesJson), titloi_positions);
        else
            task = new AsyncTaskUpdate_JSON(this,  transgroupID,table, nameJson,replaceTrueOrFalse(valuesJson),titloi_positions);

    if (read_only_col != null)
        task.setEkseresiPedion(read_only_col);
        task.names_col = names_col;
        task.date = date;
        task.period = period;
        task.watchID = watchID;
        task.listener =  this;
        task.execute();
    }

// ΒΟΛΕΥΕΙ ΓΙΑ ΦΡΑΓΚΜΕΝΤ
    public void insert_or_update_data(ArrayList<ItemsRV>  listaAdaptor , String names_col[],ArrayList<String> namesLista, ArrayList<String> valuesLista){

        alertDialog.show();
        AsyncTaskUpdate_JSON task;


        setValuesTo_valuesJSON(titloi_positions, listaAdaptor,valuesLista);

        if (weHaveData)
            task = new AsyncTaskUpdate_JSON(this, id , transgroupID,table, namesLista,replaceTrueOrFalse(valuesLista), titloi_positions);
        else
            task = new AsyncTaskUpdate_JSON(this,  transgroupID,table, namesLista,replaceTrueOrFalse(valuesLista),titloi_positions);


        task.names_col = names_col;
        task.date = date;
        task.period = period;
        task.watchID = watchID;
        task.listener =  this;
        task.execute();
    }


    public ArrayList <String>  replaceTrueOrFalse(ArrayList <String> lista){
        for (int i=0; i<lista.size(); i++){
            String v = lista.get(i);
            if (v.equals("1") || v.equals("true"))
                lista.set(i,"1");
            if (v.equals("false"))
                lista.set(i,"");

        }

        return lista;
    }




    public void clearTexts(EditText editTexts []) {

        for (EditText editText : editTexts)
            editText.setText("");


    }

    public void clearTexts(Spinner spinners []) {

        for (Spinner spinner : spinners)
            spinner.setSelection(0);

    }






    public void setValuesTolistaAdaptor(int [] titles_positions , ArrayList<ItemsRV> listAdaptor){

        if (titles_positions == null)
            titles_positions  = new int [] {};

        ArrayList<Integer> lista_titles = new ArrayList<>();

        ArrayList <String> namesClon = nameJson;
        while (namesClon.size() != valuesJson.size()){

            namesClon.remove(0);
        }


        for (int x = 0; x<titles_positions.length; x++) {

            lista_titles.add(titles_positions[x]);     //ΑΠΟΘΗΚΕΥΟΥΜΕ ΤΙΣ ΘΕΣΕΙΣ ΤΩΝ ΤΙΤΛΩΝ
        }

        int position = 0;
        for (int x = 0; x<listAdaptor.size(); x++) {
            int posOfColumn =   namesClon.indexOf(listAdaptor.get(x).getCol_name());
            if (!lista_titles.contains(x) || listAdaptor.get(x).getType() == TEXTVIEW_ITEM_READ_ONLY_VALUE){   // ΕΔΩ ΟΤΑΝ ΔΕΝ ΣΥΜΠΙΠΤΕΙ Η ΘΕΣΗ ΤΗΣ ΛΙΣΤΑΣ ΤΟΥ ΑΝΤΑΠΤΟΡΑ
                                                        // ΜΕ ΤΗ ΘΕΣΗ ΤΗς ΛΙΣΤΑΣ ΤΩΝ ΤΙΤΛΩΝ ΝΑ ΒΑΖΕΙ ΤΙΜΗ

                if(listAdaptor.get(x).getValue() == null && listAdaptor.get(x).getLookup_query() != null)    //SPINNER ME LOOK UP
                    listAdaptor.get(x).setValue(valuesJson.get(position));

                else if(listAdaptor.get(x).getValue() == null && listAdaptor.get(x).getLista() == null)         //CHECKBOX
                    listAdaptor.get(x).setTrue(valuesJson.get(position).equals("1") || valuesJson.get(position).equals("true") );

                else if(listAdaptor.get(x).getValue() == null && listAdaptor.get(x).getLista() != null) {        //SPINNER SKETO
                    listAdaptor.get(x).setValue(valuesJson.get(posOfColumn));
                }
                else if (posOfColumn >= 0) //ΕΛΕΓΧΟΣ ΠΩς ΟΝΤΩΣ ΥΠΑΡΧΕΙ ΤΟ ΟΝΟΜΑ ΠΕΔΙΟΥ , ΔΙΑΦΟΡΕΤΙΚΑ ΘΑ ΕΙΝΑΙ -1
                    listAdaptor.get(x).setValue(valuesJson.get(posOfColumn));                               //TIMES APLA SETTEXT

                position ++;

            }
        }
    }



    //GIA FRAGMENT

    public void setValuesTolistaAdaptor(int titles_positions[] , ArrayList<ItemsRV> listAdaptor,ArrayList<String> valuesLista,JSONObject results) throws JSONException {

        if (titles_positions == null)
            titles_positions  = new int [] {};

        ArrayList<Integer> lista_titles = new ArrayList<>();

        for (int x = 0; x<titles_positions.length; x++) {

            lista_titles.add(titles_positions[x]);     //ΑΠΟΘΗΚΕΥΟΥΜΕ ΤΙΣ ΘΕΣΕΙΣ ΤΩΝ ΤΙΤΛΩΝ
        }

        int position = 0;
        for (int x = 0; x<listAdaptor.size(); x++) {

            if (!lista_titles.contains(x)){             // ΕΔΩ ΟΤΑΝ ΔΕΝ ΣΥΜΠΙΠΤΕΙ Η ΘΕΣΗ ΤΗΣ ΛΙΣΤΑΣ ΤΟΥ ΑΝΤΑΠΤΟΡΑ
                // ΜΕ ΤΗ ΘΕΣΗ ΤΗς ΛΙΣΤΑΣ ΤΩΝ ΤΙΤΛΩΝ ΝΑ ΒΑΖΕΙ ΤΙΜΗ
                String value = listAdaptor.get(x).getValue() ;
                String compCol = listAdaptor.get(x).getcompareColValue();

                //ΕΔΩ ΕΧΩ ΔΩΣΕΙ ΣΤΗ ΛΙΣΤΑ ΕΝΑ ΟΝΟΜΑ ΠΕΔΙΟΥ ΩΣΤΕ ΝΑ ΣΥΓΚΡΙΝΕΙ ΤΗΝ ΤΙΜΗ ΤΟΥ ΜΕ ΤΗΝ ΤΙΜΗ ΤΟΥ ΣΥΓΚΡΙΝΟΥΜΕΝΟΥ ΠΕΔΙΟΥ
                // ΑΝ ΔΕΝ ΕΙΝΑΙ ΙΔΙΑ ΣΤΟ RECYECLERVIEW ΤΟ ΠΕΔΙΟ ΤΟΥ ΘΑ ΕΙΝΑΙ ΚΟΚΚΙΝΟ
                if (compCol != null && results != null && results.has(compCol)){
                    String compColVal = results.getString(compCol);
                    String valFromList = valuesLista.get(position);

                    listAdaptor.get(x).isCompValueSame = valFromList != null && valFromList.equals(compColVal);
                }

                else if (compCol != null)
                    listAdaptor.get(x).isCompValueSame = true; // ΕΠΕΙΔΗ ΟΤΑΝ ΥΠΑΡΧΕΙ ΠΕΔΙΟ ΣΥΓΚΡΙΣΗς ΑΛΛΑ ΔΕΝ ΥΠΑΡΧΕΙ ΚΑΝΕΝΑ ΑΠΟΤΕΛΕΣΜΑ ΣΤΟ ΚΟΥΙΡΙ ΤΟ ΦΕΡΝΕΙ FALSE
                                                                // ΠΡΕΠΕΙ ΝΑ ΤΟ ΦΕΡΝΕΙ FALSE ΜΟΝΟ ΟΤΑΝ ΥΠΑΡΧΕΙ ΑΠΟΤΕΛΕΣΜΑ ΣΥΓΚΡΙΣΗς



                if(value == null && listAdaptor.get(x).getLookup_query() != null)    //SPINNER ME LOOK UP
                       listAdaptor.get(x).setValue(valuesLista.get(position));

                else if(value == null && listAdaptor.get(x).getLista() == null)      //CHECKBOX
                    listAdaptor.get(x).setTrue(valuesLista.get(position).equals("1"));

                else if(value == null && listAdaptor.get(x).getLista() != null) {   //SPINNER SKETO
                    listAdaptor.get(x).setValue(valuesLista.get(position));
                }
                else
                    listAdaptor.get(x).setValue(valuesLista.get(position));                             //TIMES APLA SETTEXT

                position ++;

            }
        }
    }



    public void setValuesTo_valuesJSON(final EditText[] editText, final TextView[] textView){

        if (textView != null) {
            for (TextView view : textView) {
                valuesJson.add(view.getText().toString().trim());
            }
        }

        if (editText != null){
            for (EditText text : editText) {
                valuesJson.add(text.getText().toString().trim());
            }
        }


    }





    public void setValuesTo_valuesJSON(int[] titles_positions , ArrayList<ItemsRV> listAdaptor) {


        ArrayList<Integer> lista_titles = new ArrayList<>();
        ArrayList<ItemsRV> lista_values;

        if (valuesJson == null)
            valuesJson = new ArrayList<>();

        lista_values = (ArrayList<ItemsRV>) listAdaptor.clone();

        //ΠΑΛΙΟ
        if (titles_positions != null) {
//            for (int x = 0; x < titles_positions.length; x++) {
//                lista_titles.add(titles_positions[x]);     //ΑΠΟΘΗΚΕΥΟΥΜΕ ΤΙΣ ΘΕΣΕΙΣ ΤΩΝ ΤΙΤΛΩΝ
//            }


            for (int x = titles_positions.length - 1; x >= 0; x--) {
                lista_values.remove((int) titles_positions[x]);
            }
        }




        /*



         */

    //  GIA INSERT
        if (valuesJson.size() == 0) {
            for (int x = 0; x < lista_values.size(); x++) {

                if (lista_values.get(x).getValue() == null)
                    if (lista_values.get(x).getTrue() == null)
                        valuesJson.add(x,""); //ΑΥΤΟ ΣΥΜΒΑΙΝΕΙ ΚΑΜΙΑ ΦΟΡΑ ΜΕ ΤΑ ΣΠΙΝΝΕΡ ΑΝ ΔΕΝ ΦΟΡΤΩΘΟΥΝ ΤΑ ΔΕΔΟΜΕΝΑ ΤΟΥΣ ΣΤΗΝ ΟΘΟΝΗ ΚΑΙ ΠΑΤΗΘΕΙ ΑΠΟΘΗΚΕΥΣΗ
                    else
                        valuesJson.add(x, lista_values.get(x).getTrue().toString());  // ΕΔΩ ΕΧΟΥΜΕ CHECKBOX
                else
                    valuesJson.add(x, lista_values.get(x).getValue());
            }
        }

        else {
// GIA UPDATE

            for (int x = 0; x < lista_values.size(); x++) {

                if (lista_values.get(x).getValue() == null) {
                    if (lista_values.get(x).getTrue() != null)
                         valuesJson.set(x, lista_values.get(x).getTrue().toString());
                    else
                        valuesJson.set(x,""); // ΑΥΤΟ ΣΥΜΒΑΙΝΕΙ ΑΠΟ ΟΤΑΝ ΕΒΑΛΑ ΤΟ RECYCLERVIEW  ΝΑ ΑΝΑΕΝΕΩΝΕΤΑΙ ΜΕ ΤΟ
                                                //         DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
                                                    // ΕΠΕΙΔΗ ΔΕΝ ΦΟΡΤΩΝΕΙ ΤΑ CHILD ΠΟΥ ΔΕΝ ΤΑ ΕΧΕΙ ΔΕΙ Ο ΧΡΗΣΤΗς
                }
                else
                    valuesJson.set(x, lista_values.get(x).getValue());
            }

        }

        if (!nameJson.contains("UserID")){
            nameJson.add("UserID");
            valuesJson.add(Utils.getUserID(this));
        }

    }


//GIA FRAGMENT

    public void setValuesTo_valuesJSON(int titles_positions[] , ArrayList<ItemsRV> listAdaptor, ArrayList<String> valuesLista) {

        ArrayList<Integer> lista_titles = new ArrayList<>();
        ArrayList<ItemsRV> lista_values;

        lista_values = (ArrayList<ItemsRV>) listAdaptor.clone();

        if (titles_positions != null){
            for (int x = 0; x < titles_positions.length; x++) {

                lista_titles.add(titles_positions[x]);     //ΑΠΟΘΗΚΕΥΟΥΜΕ ΤΙΣ ΘΕΣΕΙΣ ΤΩΝ ΤΙΤΛΩΝ
            }
        }

        for (int x = lista_titles.size()-1 ; x >= 0; x--) {

            lista_values.remove((int)lista_titles.get(x));

        }

        //  GIA INSERT
        if (valuesLista.size() == 0) {
            for (int x = 0; x < lista_values.size(); x++) {
                int type = lista_values.get(x).getType();

                String val = lista_values.get(x).getValue();

                if (val == null || type == CHECKBOX_ITEM) {
                   boolean ischecked_val =  lista_values.get(x).getTrue();
                   valuesLista.add(x, String.valueOf(ischecked_val));
                }

                else
                    valuesLista.add(x, val);
            }
        }

        else {
// GIA UPDATE

            for (int x = 0; x < lista_values.size(); x++) {

                String val = lista_values.get(x).getValue();

                if (val == null || lista_values.get(x).getType() == CHECKBOX_ITEM)
                    valuesLista.set(x, lista_values.get(x).getTrue().toString());
                else
                    valuesLista.set(x, val);
            }

        }


    }

//----------------

    public void clearListaAdaptor(ArrayList<ItemsRV> list){
        for (int x = 0; x<list.size(); x++) {
            if(list.get(x).getValue() == null)
                list.get(x).setTrue(false);
            else
                list.get(x).setValue("");

        }
        weHaveData = false;
    }





    public static  void runLater(Method method, Object myClass, Object[] values, long delayMills){
        /*
        ΜΕΘΟΔΟΣ ΣΤΗΝ ΟΠΟΙΑ ΠΕΡΝΑΜΕ ΣΑΝ ΠΑΡΑΜΕΤΡΟΥΣ
        ΤΗ ΜΕΘΟΔΟ ΠΟΥ ΘΕΛΟΥΜΕ ΝΑ ΤΡΕΞΟΥΜΕ , ΤΗΝ ΚΛΑΣΗ ΤΗΣ,
        ΤΙΣ ΤΙΜΕΣ ΤΩΝ ΠΑΡΑΜΕΤΡΩΝ ΤΗΣ ΚΑΙ ΤΟΝ ΧΡΟΝΟ ΚΑΘΥΣΤΈΡΗΣΗΣ

        ΠΑΡΑΔΕΙΓΜΑ ΠΩΣ ΝΑ ΔΩΣΟΥΜΕ ΤΙΜΕΣ

             Class[] parameterTypes = new Class[2];
                parameterTypes[0] = String.class;
                parameterTypes[1] = Integer.class;

                Object[] values = new Object[2];
                values[0] = "text" ;
                values[1] = 12 ;

                try {
                    Method tst = NotificationsAdapter.class.getMethod("nameOfMethod",parameterTypes);
                    runLater(tst, new NotificationsAdapter(),v,2000); // ΚΑΛΕΣΜΑ ΤΗΣ ΜΕΘΟΔΟΥ ΟΠΟΥ ΘΑ ΤΡΕΞΕΙ ΤΗ ΜΕΘΟΔΟ ΠΟΥ ΘΕΛΟΥΜΕ

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

            }

         */


        new Handler().postDelayed(() -> {
            Object[] parameters = new Object[values.length];
            System.arraycopy(values, 0, parameters, 0, values.length);
            try {
                method.invoke(myClass,parameters);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }, delayMills);

    }






    public  String getFROM_NAMEJSON_COLUMNS(ArrayList<String> namejson ,String arthroPinaka){

        if (arthroPinaka == null)
            arthroPinaka = "";
        else if (!arthroPinaka.equals(""))
            arthroPinaka = arthroPinaka + ".";

        StringBuilder sb = new StringBuilder();

        for (int i=0; i<namejson.size(); i++){
            if (i+1 == namejson.size())
                sb.append(arthroPinaka).append(namejson.get(i)).append(" ");
            else
                sb.append(arthroPinaka).append(namejson.get(i)).append(", ");
        }

        return sb.toString();
    }





    @Override
    public void handleDialogClose(String transgroupid) {

        transgroupID =  Utils.getSplitSPartString(transgroupid , "," ,3);
    }


    @Override
    public void update_JSON(String str) {

        if (str.equals(getString(R.string.successful_update)))
            updateSuccess(str);

        else
          updateError(str);


        alertDialog.dismiss();
    }

    @Override
    public void getIDofInsert(String id) {
        eggrafiID = id;
    }



    public void updateSuccess(String str){
        weHaveData = true; // ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΞΑΝΑΠΑΤΗΣΕΙ ΤΗΝ ΙΔΙΑ ΣΤΙΓΜΗ ΤΟ ΚΟΥΜΠΙ ΓΙΑ ΝΑ ΜΗΝ ΚΑΝΕΙ ΠΑΛΙ ΙΝΣΕΡΤ

        if (updateButton !=null)
             Utils.timeHandlerDoneButton(updateButton, this);
        if (str_query != null && !str_query.equals(""))
             getJSON_DATA(str_query);  // ΤΟ ΞΑΝΑΤΡΕΧΩ ΕΠΕΙΔΗ ΔΕΝ ΕΧΩ ΤΟ ID

        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

    }



    public void updateError(String str){
        if (updateButton !=null)
            Utils.timeHandlerErrorButton(updateButton, this);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void taskCompleteGetFloors(HashMap <String, Integer> mapFloor) {

        floorsMap = mapFloor;
       // new AsyncTaskGetPatients(this, patientsTV).execute();

        if (patientsTV != null) {


                floorSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        floorID = floorsMap.get(floorSP.getSelectedItem().toString());
                        Utils.setPosFloorID(extendedActivity, floorSP.getSelectedItemPosition());

                        new AsyncTaskGetPlanoKlinonPatients(extendedActivity, patientsTV, floorID).execute();


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {


                    }
                });
        }

    }

    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        patientsNosileuomenoi = lista;

        transgroupID = Utils.getSplitSPartString(patientsTV.getText().toString() , "," ,3);

        patientsTV.setOnClickListener(new SearchNosileuomenoListener(this, patientsNosileuomenoi));
        alertDialog.hide();
    }




    public void setTextViewListenerAlertDialogMultipleChoice(final TextView textView, final CharSequence[] ITEMS, final boolean[] ITEMS_VALUES){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(
                        extendedActivity);

                builder.setMultiChoiceItems(ITEMS, ITEMS_VALUES,
                        new DialogInterface.OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                if (isChecked) {
                                    ITEMS_VALUES[which] = true;
                                } else {
                                    ITEMS_VALUES[which] = false;
                                }
                            }
                        });

                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                StringBuilder sb = new StringBuilder();

                                //  ITEMS_HOURS_VALUES = TEMP_ITEMS_HOURS_VALUES.clone();

                                for (int i=0; i< ITEMS_VALUES.length; i  ++){
                                    if (ITEMS_VALUES[i])
                                        sb.append(ITEMS[i] + " , ");
                                }

                                if (sb.length() > 2)
                                    textView.setText(sb.toString().substring(0,sb.toString().length()-2));
                                else
                                    textView.setText("");



                            }
                        });

                builder.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }





    private void askForVoicePermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(extendedActivity, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(extendedActivity, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(extendedActivity, new String[]{permission}, requestCode);

            } else {
                ActivityCompat.requestPermissions(extendedActivity, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            }

        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }



    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

          //  Log.d("service", "connected");

            mServiceMessenger = new Messenger(service);
            Message msg = new Message();
            msg.what = MyVoiceService.MSG_RECOGNIZER_START_LISTENING;
            try {
                mServiceMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            serviceconneted = false;
       //     Log.d("service", "disconnetd");
        }
    };


    public void printNames(){
        for (int i=0; i<nameJson.size(); i++){
         //   Log.e("namesss",nameJson.get(i));
        }

    }

    public void printValues(){
        for (int i=0; i<valuesJson.size(); i++){
           // Log.e("valuesss",valuesJson.get(i));
        }

    }


    public void deleteData(String table, String id){

        alertDialog.show();
        AsyncTaskDelete task = new AsyncTaskDelete(extendedActivity,table ,id);
        task.ctx = getApplicationContext();
        task.listener = this;
        task.execute();
    }


    @Override
    public void deleteResult(String str) {
        if (str.equals(getString(R.string.successful_update)))
            Toast.makeText(extendedActivity, "Διαγραφή επιτυχής", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(extendedActivity, "Κάτι πήγε στραβά", Toast.LENGTH_SHORT).show();

        alertDialog.dismiss();
    }


    public void getAllMedicines(boolean isFirstTime){

        alertDialog.show();
        if (isFirstTime)
            Toast.makeText(extendedActivity, "Παρακαλώ περιμένετε λόγω πρώτης σύνδεσης θα υπάρξει μία μικρή καθυστέρηση", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Παρακαλώ περιμένετε ίσως καθυστερήσει λίγο ...", Toast.LENGTH_SHORT).show();

        AsyncTaskGetMedicines task = new AsyncTaskGetMedicines();
        task.ctx = getApplicationContext();
        task.listener = this;
        task.execute();

    }



    @Override
    public void taskCompleteMedicinesLista(ArrayList medicinesLista) {
        this.medicinesLista = medicinesLista;
        alertDialog.dismiss();

    }

    @Override
    public void taskCompleteMedicinesHashMap(HashMap<String, Integer> medicinesHashMap) {
        this.medicinesHashMap = medicinesHashMap;
        alertDialog.dismiss();
    }


    public void runORupdate_simple_query(String query ){
        runORupdate_simple_query(query, getApplicationContext(),this);
    }

    public void runORupdate_simple_query(String query, Context ctx, AsyncGetUpdateResult listener ){

        alertDialog.show();
        AsyncTaskUpdate task = new AsyncTaskUpdate(extendedActivity, query);
        task.ctx = ctx;
        task.listener = listener;
        task.execute();

    }

    @Override
    public void update(String str) {
        //eggrafiID = str;
    }

    @Override
    public void hereIsYourData(int pos ) {

    }
}
