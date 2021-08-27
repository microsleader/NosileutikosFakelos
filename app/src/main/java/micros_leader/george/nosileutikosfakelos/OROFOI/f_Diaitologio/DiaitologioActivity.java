package micros_leader.george.nosileutikosfakelos.OROFOI.f_Diaitologio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
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
import micros_leader.george.nosileutikosfakelos.DialogFragmentSearches.DF_SearchMultiLookup;
import micros_leader.george.nosileutikosfakelos.DialogFragmentSearches.DF_items_categories;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteGetPatientsTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended_str;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim;
import micros_leader.george.nosileutikosfakelos.Permissions;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Listeners.SearchNosileuomenoListener;
import micros_leader.george.nosileutikosfakelos.StaticFields;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.TableView.TableFragment;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityDiaitologioBinding;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityItemListBinding;


import static micros_leader.george.nosileutikosfakelos.Permissions.MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE;

public class DiaitologioActivity extends BasicActivity implements AsyncCompleteTask2,AsyncCompleteGetPatientsTask,AsyncGetUpdateResult,
        MyDialogFragmentCloseListener {


    private TextView dateTV, hoursTV;//, patientsTV;
    private TextView diaitaTV, sxoliaET;
    private Spinner sitisiSinodouSP;
    private RecyclerView diaitesRV;
    private CircularProgressButton updateButton;
    private ArrayList<String> sitisiSinodouLista;
    public RecyclerViewDiaitologioAdaptor adapter;
    private ArrayList<Diaitologio> diaitologioLista;
    private LinearLayout linearLayout;
    private OptionsFabLayout fab;
    private Bundle bundle1;
    private TableFragment tf1 ;



    ActivityDiaitologioBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityDiaitologioBinding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);
        buttonsListners();

        diaitologioLista = new ArrayList<>();
        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);
    }



    private void buttonsListners(){
        bd.diaitologioBT.setOnClickListener(view -> showDiaitologioAstheni());
        bd.dailyDietBT.setOnClickListener(view -> showDailyDiaitologioAstheni());
        bd.dietEktimisiBT.setOnClickListener(view -> showDiaitologikiEktimisi());
    }

    private void showFragment(){


        tf1 = new TableFragment();
        tf1.setArguments(bundle1);

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }

        bd.fragment1.clearChildFocus(bd.fragment1);
        getSupportFragmentManager().beginTransaction()
                .add(bd.fragment1.getId(), tf1)
                .commit();

    }


    @Override
    public void taskCompleteGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompleteGetPatients(lista);
        transgroupID = Utils.getSplitSPartString(patientsTV.getText().toString(),"," , 3);

    }




//
//    public void getDiaitologio(String transgroupID) {
//
//        if (Utils.isNetworkAvailable2(this)) {
//
//            diaitologioLista.clear();
//
//            alertDialog.show();
//            String query = new Str_queries().getDIAITOLOGIO_PERSON(transgroupID);
//            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
//            task.ctx = getApplicationContext();
//            task.listener = DiaitologioActivity.this;
//            task.query = query;
//            task.execute();
//
//
//        }
//
//    }
//
//    @Override
//    public void taskComplete2(JSONArray results) throws JSONException {
//
//        if (results != null) {
//
//            // ΕΑΝ ΔΕΝ ΕΧΕΙ ΣΤΑΤΟΥΣ ΔΗΛΑΔΗ ΕΑΝ ΕΧΕΙ ΑΠΟΤΕΛΕΣΜΑΤΑ ΝΑ ΦΕΡΕΙ ΝΑ ΚΑΝΕΙ ΤΟ ΑΠΟ ΚΑΤΩ
//            if (!results.getJSONObject(0).has("status")) {
//
//                for (int i = 0; i < results.length(); i++) {
//
//
//                    JSONObject currentDiaitologio = results.getJSONObject(i);
//
//                    Diaitologio diaitologio = new Diaitologio();
//
//                    diaitologio.setTransgroupid(Utils.convertObjToString(currentDiaitologio.get("TransGroupID")));
//                    diaitologio.setId(Utils.convertObjToString(currentDiaitologio.get("ID")));
//                    diaitologio.setDatefrom(Utils.convertObjToString(currentDiaitologio.get("dateF")));
//                    diaitologio.setHourFrom(Utils.convertObjToString(currentDiaitologio.get("timeF")));
//                    diaitologio.setDieta(Utils.convertObjToString(currentDiaitologio.get("Dieta")));
//                    diaitologio.setSxolia(Utils.convertObjToString(currentDiaitologio.get("Remarks")));
//                    diaitologio.setSitisiSinodou(InfoSpecificLists.getSitisiSinodouName(currentDiaitologio.getInt("sitisi_sinodou")));
//                    diaitologio.setUserID(Utils.convertObjToString(currentDiaitologio.get("UserID")));
//
//
//                    diaitologioLista.add(diaitologio);
//
//                }
//
//
//                adapter.notifyDataSetChanged();
//
//                alertDialog.dismiss();
//
//
//            } else {
//                diaitologioLista.clear();
//
//                adapter.notifyDataSetChanged();
//                alertDialog.dismiss();
//            }
//
//
//        }
//
//        else
//            alertDialog.dismiss();
//
//
//        showHideLayout();
//    }
//
//
//





        private void showDiaitologioAstheni() {

             String q = "select *, dbo.datetostr(dateFrom) as datestr , dbo.nameuser(userID) as username " +
                     "from nursing_Diaitologio " +
                    "where transgroupID = " + transgroupID + " order by datefrom desc";

                bundle1 =  tableView_sigkentrotika_dialogFragment(q,
                            transgroupID,
                            null,
                            InfoSpecificLists.getDiaitologio(),
                            false,
                            false,
                            true);

                showFragment();
         }


        private void showDailyDiaitologioAstheni() {


            AsyncTaskGetJSON2 t = new AsyncTaskGetJSON2();
            t.ctx = this;
            t.query = "select   \n" +
                            "dbo.datetostr(n.date)  as datestr \n" +
                            "from Nursing_Diaitologio_daily n \n" +
                            " where n.transgroupID = " + transgroupID +
                            " order by n.date desc, n.id desc";

            t.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {
                    ArrayList <TableViewItem> lista = InfoSpecificLists.getDailyDiaitologio();
                    String[] panoTitloi = new String[]{};
                    String[] plagioiTitloi = new String[lista.size()];


                    if (results != null && !results.getJSONObject(0).has("status")) {
                        panoTitloi = new String[results.length()];

                        for (int i = 0; i < results.length(); i++) {
                            panoTitloi[i] = results.getJSONObject(i).getString("datestr");
                        }
                    }

                        for (int i=0; i<lista.size(); i++){
                            plagioiTitloi[i] = lista.get(i).getTitle();
                        }



                        String q = "select *, dbo.datetostr(date) as datestr , dbo.nameuser(userID) as username \n" +
                                " from Nursing_Diaitologio_daily " +
                                "where transgroupID = " + transgroupID + " order by date desc";

                        bundle1 =  tableView_sigkentrotika_dialogFragment(q,
                                transgroupID,
                                panoTitloi,
                                plagioiTitloi,
                                lista,
                                false,
                                false,
                                true);

                        showFragment();

                    }



            };


            t.execute();





        }




        private void showDiaitologikiEktimisi() {

            String q = "select *, dbo.datetostr(date) as datestr , dbo.nameuser(userID) as username " +
                    " from Nursing_Diaitologiki_ektimisi " +
                    "where transgroupID = " + transgroupID + " order by date desc";

            bundle1 =  tableView_sigkentrotika_dialogFragment(q,
                    transgroupID,
                    null,
                    InfoSpecificLists.getDiaitologiki_ektimisi(),
                    false,
                    false,
                    true);

            showFragment();
        }







    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);

        //getDiaitologio(transgroupID);

        bd.patientsTV.setOnClickListener(new SearchNosileuomenoListener(DiaitologioActivity.this, patientsNosileuomenoi));
        transgroupID = Utils.getSplitSPartString(bd.patientsTV.getText().toString(),",",3);

        showDiaitologioAstheni();
    }




    @Override
    public void handleDialogClose(String transgroupid) {

        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT

        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);
        showDiaitologioAstheni();


    }









    @Override
    public void update(String str) {

        if (str.equals("Πετυχημένη ενημέρωση")) {
           // Utils.timeHandlerDoneButton(updateButton, DiaitologioActivity.this);
            adapter.notifyDataSetChanged();

            //getDiaitologio(transgroupID);
          //  clearTexts();

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.diaitologio_odigies) {// do something
            Permissions.requestFilePermission(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void clearTexts() {
        dateTV.setText("");
        hoursTV.setText("");
        diaitaTV.setText("");
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


//    @Override
//    public void hereIsYourStr_Data(String info) {
//        diaitaTV.setText(info);
//    }
}

