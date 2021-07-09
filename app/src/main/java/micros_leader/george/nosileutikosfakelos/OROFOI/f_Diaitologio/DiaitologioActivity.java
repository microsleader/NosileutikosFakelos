package micros_leader.george.nosileutikosfakelos.OROFOI.f_Diaitologio;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ag.floatingactionmenu.OptionsFabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.Permissions;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Listeners.SearchNosileuomenoListener;
import micros_leader.george.nosileutikosfakelos.StaticFields;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;


import static micros_leader.george.nosileutikosfakelos.Permissions.MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE;

public class DiaitologioActivity extends BasicActivity implements AsyncCompleteTask2,AsyncCompleteGetPatientsTask,AsyncGetUpdateResult,MyDialogFragmentCloseListener {


    private String companyID;
    private TextView dateTV, hoursTV, patientsTV;
    private EditText diaitaET, sxoliaET;
    private Spinner sitisiSinodouSP;
    private RecyclerView diaitesRV;
    private CircularProgressButton updateButton;
    private List<EditText> editTextList;
    private ArrayList sitisiSinodouLista;
    public RecyclerViewDiaitologioAdaptor adapter;
    private ArrayList<Diaitologio> diaitologioLista;
    private LinearLayout linearLayout;
    public AlertDialog alertDialog;
    private OptionsFabLayout fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaitologio);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        companyID = Utils.getcompanyID(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dateTV = findViewById(R.id.dateTV);
        hoursTV = findViewById(R.id.hourTV);
        patientsTV = findViewById(R.id.patientsTV);
        diaitaET = findViewById(R.id.diaitaET);
        sxoliaET = findViewById(R.id.sxoliaET);
        sitisiSinodouSP = findViewById(R.id.sitisiSinodouSP);
       // updateButton = findViewById(R.id.updateButton);
        linearLayout = findViewById(R.id.katigoriesLayout);
        linearLayout.setVisibility(View.GONE);
        diaitesRV = findViewById(R.id.diaitesRV);



        fab = findViewById(R.id.fab12);


        fabInitialize();


        editTextList = new ArrayList<>();
        editTextList.add(diaitaET);
        editTextList.add(sxoliaET);

        diaitologioLista = new ArrayList<>();
        patientsNosileuomenoi = new ArrayList<>();

        alertDialog = Utils.setLoadingAlertDialog(this);
        sitisiSinodouLista = InfoSpecificLists.getSitisiSinodouNames();

        Utils.dateListener(this, dateTV);
        Utils.timeListener(this, hoursTV);

        getPatientsList(this,R.id.patientsTV,R.id.floorsSP);

        diaitesRV.setLayoutManager(new LinearLayoutManager(DiaitologioActivity.this, LinearLayoutManager.VERTICAL, false));
        diaitesRV.addItemDecoration(new DividerItemDecoration(DiaitologioActivity.this, LinearLayout.VERTICAL));
        diaitesRV.setItemViewCacheSize(30);
        diaitesRV.setHasFixedSize(true);
        diaitesRV.setNestedScrollingEnabled(false);
        adapter = new RecyclerViewDiaitologioAdaptor(DiaitologioActivity.this, diaitologioLista);
        diaitesRV.setAdapter(adapter);



        spinnerAdapter();

        thereIsImageUpdateButton();

        updateButtonListener();

       // Utils.copyAssets(this);


      //  ----------------

//         File myDir = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/nosileutikosFakelosMore" , "test.txt");
//                if (!myDir.exists())
//                    Log.e("testfile", String.valueOf(myDir.mkdirs()));
//
//           File     file1 = new File(myDir, "test" + "." + "txt");
//              //  os = new FileOutputStream(file1);
//              //  os.write(fileBytes);
//              //  os.close();
////
//                Utils.scanFile(this, file1);


      //   ----------------

    }


    private void spinnerAdapter() {
        ArrayAdapter adapter = new ArrayAdapter<>(DiaitologioActivity.this,
                R.layout.spinner_layout2, sitisiSinodouLista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sitisiSinodouSP.setAdapter(adapter);
    }


    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        getDiaitologio(transgroupID);

        patientsTV.setOnClickListener(new SearchNosileuomenoListener(DiaitologioActivity.this, patientsNosileuomenoi));


    }




    @Override
    public void handleDialogClose(String transgroupid) {

        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT

        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);

        getDiaitologio(transgroupID);

        clearTexts();
    }


    public void getDiaitologio(String transgroupID) {

        if (Utils.isNetworkAvailable2(this)) {

            diaitologioLista.clear();

            alertDialog.show();
            String query = new Str_queries().getDIAITOLOGIO_PERSON(transgroupID);
            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = getApplicationContext();
            task.listener = DiaitologioActivity.this;
            task.query = query;
            task.execute();


        }

    }

    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null) {

            // ΕΑΝ ΔΕΝ ΕΧΕΙ ΣΤΑΤΟΥΣ ΔΗΛΑΔΗ ΕΑΝ ΕΧΕΙ ΑΠΟΤΕΛΕΣΜΑΤΑ ΝΑ ΦΕΡΕΙ ΝΑ ΚΑΝΕΙ ΤΟ ΑΠΟ ΚΑΤΩ
            if (!results.getJSONObject(0).has("status")) {

                for (int i = 0; i < results.length(); i++) {


                    JSONObject currentDiaitologio = results.getJSONObject(i);

                    Diaitologio diaitologio = new Diaitologio();

                    diaitologio.setTransgroupid(Utils.convertObjToString(currentDiaitologio.get("TransGroupID")));
                    diaitologio.setId(Utils.convertObjToString(currentDiaitologio.get("ID")));
                    diaitologio.setDatefrom(Utils.convertObjToString(currentDiaitologio.get("dateF")));
                    diaitologio.setHourFrom(Utils.convertObjToString(currentDiaitologio.get("timeF")));
                    diaitologio.setDieta(Utils.convertObjToString(currentDiaitologio.get("Dieta")));
                    diaitologio.setSxolia(Utils.convertObjToString(currentDiaitologio.get("Remarks")));
                    diaitologio.setSitisiSinodou(InfoSpecificLists.getSitisiSinodouName(currentDiaitologio.getInt("sitisi_sinodou")));
                    diaitologio.setUserID(Utils.convertObjToString(currentDiaitologio.get("UserID")));


                    diaitologioLista.add(diaitologio);

                }


                adapter.notifyDataSetChanged();

                alertDialog.dismiss();


            } else {
                diaitologioLista.clear();

                adapter.notifyDataSetChanged();
                alertDialog.dismiss();
            }


        }

        else
            alertDialog.dismiss();


        showHideLayout();
    }



    private void fabInitialize() {

        fab.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fab.isOptionsMenuOpened())
                    fab.closeOptionsMenu();
            }
        });

        fab.setMiniFabsColors(
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary);
        //-------------------  PROSTHIKI ANTIKEIMENON STO MENOY TOY



        //  LISTENER  GIA TA ITEMS TOY
        fab.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {



                switch (fabItem.getItemId()) {

                    case R.id.sigkentrotika:

                        showSigkentrotika();
                        break;

                    case R.id.sigkentrotika_astheni:

                        showSigkentrotikaAstheni();

                        break;

                    default:

                        break;
                }
            }
        });
    }



    private void showSigkentrotika() {


        startActivity(   tableView_sigkentrotika(Str_queries.getDIAITOLOGIO_SIGKENTROTIKA(),
                transgroupID,
                new String[]{"Όροφος","Κλίνη","Όνομα","Ώρα",
                        "Διάγνωση","Δίαιτα","Σχόλια","Οδηγίες","Σίτιση συνοδού",
                        "Ασφ. Φορεας 1", "Ασφ. Φορεας 2","Ασφ. Φορεας 3","Ασφ. Φορεας 4"},


                null,
                new String[]{"floorName","Code","patName", "tme",
                        "clinicName", "Dieta","Remarks","odigies","SitisiSinodou",
                        "insurance1","insurance2","insurance3","insurance4"},
                false,
                false,
                false));

    }



        private void showSigkentrotikaAstheni() {


            startActivity(     tableView_sigkentrotika(Str_queries.getDIAITOLOGIO_SIGKENTROTIKA_ASTHENI(transgroupID),
                        transgroupID,
                        new String[]{"Ημ/νία από","Δίαιτα","Σχόλια","Σίτιση συνοδού"},
                        null,
                        new String[]{"DateFrom","Dieta","Remarks","SitisiSinodou"},
                        false,
                        false,
                        false));

    }







    private void updateButtonListener(){

        updateIMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) DiaitologioActivity.this.getSystemService(INPUT_METHOD_SERVICE);
                if ((getCurrentFocus()!=null))
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                alertDialog.show();


                if (Utils.isNetworkAvailable2(DiaitologioActivity.this ) &&
                        !Utils.isEmpty(patientsTV, DiaitologioActivity.this)) {

                    String date = dateTV.getText().toString();
                    String time = hoursTV.getText().toString();



                        if (date.equals("") && !time.equals(""))
                                date = Utils.getCurrentDate();

                        else if (!date.equals("") && time.equals(""))
                            time = Utils.getCurrentTime();

                        else if (date.equals("") && time.equals("")){
                            date = "";
                            time = "";

                        }


                    String diaita = diaitaET.getText().toString().trim();
                    String sxolia = sxoliaET.getText().toString().trim();
                    String sitisiSinodou = InfoSpecificLists.getSitisiSinodouID(sitisiSinodouSP.getSelectedItem().toString());



                    String query = new Str_queries().getDIAITOLOGIO_INSERT(transgroupID,date,time, diaita, sxolia,sitisiSinodou,userID);
                    AsyncTaskUpdate task = new AsyncTaskUpdate(DiaitologioActivity.this, query);
                    task.listener = DiaitologioActivity.this;
                    task.execute();



                }

            }
        });

    }



    @Override
    public void update(String str) {

        if (str.equals("Πετυχημένη ενημέρωση")) {
           // Utils.timeHandlerDoneButton(updateButton, DiaitologioActivity.this);
            adapter.notifyDataSetChanged();

            getDiaitologio(transgroupID);
            clearTexts();

        }


        alertDialog.dismiss();
//        else {
//
//            Utils.timeHandlerErrorButton(updateButton, DiaitologioActivity.this);
//
//        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diaitologio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.diaitologio_odigies:
                // do something

                Permissions.requestFilePermission(this);

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }



    private void clearTexts() {
        dateTV.setText("");
        hoursTV.setText("");
        diaitaET.setText("");
        sxoliaET.setText("");
        sitisiSinodouSP.setSelection(0);
    }


    private void showHideLayout(){
        if (diaitologioLista.isEmpty())
            linearLayout.setVisibility(View.GONE);
        else
            linearLayout.setVisibility(View.VISIBLE);


    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Toast.makeText(this, "write enabled", Toast.LENGTH_LONG).show();

                try {

                    Utils.copyFileFromAssetsToExternal(this, StaticFields.ASSETS_FILE_DIAITOLOGIO_ODIGIES);

                    File excelFile = new File(Environment.getExternalStorageDirectory() + "/" +
                            StaticFields.MAIN_FOLDER_NOSILEUTIKOS_FAKELOS, StaticFields.ASSETS_FILE_DIAITOLOGIO_ODIGIES);

                    Utils.openDocument(this, excelFile);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            } else {
                Toast.makeText(this, "permission denied ", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions,
                    grantResults);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }



}

