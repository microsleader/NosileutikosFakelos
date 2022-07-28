package micros_leader.george.nosileutikosfakelos;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.databinding.ActivitySettingsBinding;


public class SettingsActivity extends BasicActivity  {



    private SharedPreferences sp;
    private SharedPreferences.Editor editor ;

    private static final int LOGIN_ACTIVITY_CODE = 1;
    ActivitySettingsBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivitySettingsBinding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);

        sp = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        editor= sp.edit();
        String address = sp.getString("address","");
        String port = sp.getString("port","");
        String lastUpdateFarmakaLista = sp.getString("lastUpdateFarmakaLista","Δεν έχει γίνει κάποια ενημέρωση");



        extendedAct = this;
        Utils.setLoadingAlertDialog(this);
        alertDialog.show();
        bd.addressText.setText(address);
        bd.lastUpdateTV.setText(lastUpdateFarmakaLista);

        int activityCode = getIntent().getIntExtra("activityCode",0);
        if (activityCode == LOGIN_ACTIVITY_CODE){
            bd.medicineTV.setVisibility(View.GONE);
            bd.layoutMedicine.setVisibility(View.GONE);


        }

        bd.portText.setText(port);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Ρυθμίσεις");




        bd.portText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    doneListener();

                    return true;
                }
                return false;
            }
        });



        changePasswordSection();




         alertDialog.dismiss();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_done:
                // do something

                doneListener();
                    return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }



    private void doneListener(){

        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null)
             imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        String address = bd.addressText.getText().toString().trim();
       // if (Patterns.IP_ADDRESS.matcher(address).matches() || Patterns.WEB_URL.matcher(address).matches()){

            sp = getSharedPreferences("settings", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("address",bd.addressText.getText().toString().trim());
            editor.putString("port",bd.portText.getText().toString().trim());
            editor.apply();

            Toast.makeText(this, "Οι ρυθμίσεις σας αποθηκεύτηκαν", Toast.LENGTH_SHORT).show();

            finish();

      //  }

//        else{
//            Toast.makeText(this, "Δεν είναι έγκυρη η διεύθυνση", Toast.LENGTH_SHORT).show();
//        }
    }


    private void changePasswordSection(){
        bd.changePswTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bd.changePswLayout.getVisibility() == View.VISIBLE)
                    bd.changePswLayout.setVisibility(View.GONE);
                else
                    bd.changePswLayout.setVisibility(View.VISIBLE);

            }
        });




        bd.updatePSWBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bd.newPswET.getText().toString().equals(bd.confirmPSWET.getText().toString())){
                    changePassword();
                }
                else
                    Toast.makeText(extendedAct, "Ο νέος κωδικός δεν ταιριάζει με την επιβεβαίωση κωδικού", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void changePassword(){



       // AsyncTaskUpdate_JSON t = new AsyncTaskUpdate_JSON();

        String q = "IF EXISTS (select top 1 id from users where loginname = '" + bd.usernameET.getText().toString().trim() + "' and webpassword = '" + bd.oldPswET.getText().toString().trim() + "' )" +
                " BEGIN" +
                "  UPDATE USERS SET WEBPASSWORD = '" + bd.newPswET.getText().toString().trim() + "' WHERE  loginname = '" + bd.usernameET.getText().toString().trim() + "' and webpassword = '" + bd.oldPswET.getText().toString().trim() + "'" +
                " SELECT 1 AS ID " +
                " END " +
                "ELSE " +
                " SELECT 0 AS ID ";

        alertDialog.show();
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2(q);
        task.ctx = this;
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {

                alertDialog.dismiss();

                if (results != null && !results.getJSONObject(0).has("status") && results.getJSONObject(0).getInt("ID") == 1){
                        Toast.makeText(extendedAct, getString(R.string.successful_update) + "\n Συνδεθείτε με τα νέα στοιχεία", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(extendedAct, "Δεν βρέθηκε χρήστης με τα στοιχεία που δώσατε", Toast.LENGTH_LONG).show();

               // Toast.makeText(extendedActivity, "xaxaxaxa σατε", Toast.LENGTH_SHORT).show();

            }
        };


        task.execute();

    }






    public void onRefreshMedicinesList(View v){

        Toast.makeText(this, "Παρακαλώ περιμένετε ίσως καθυστερήσει λίγο ...", Toast.LENGTH_SHORT).show();
        alertDialog.show();

      //  if (!Utils.getIsFirstTimeLogin(SettingsActivity.this)) //ΕΔΩ ΤΟ ΘΕΛΩ ΦΑΛΣΕ ΩΣΤΕ ΠΡΩΤΑ ΝΑ ΕΧΕΙ ΚΑΝΕΙ ΣΥΝΔΕΣΗ ΕΣΤΩ ΜΙΑ ΦΟΡΑ Ο ΧΡΗΣΤΗΣ
                                                                        // ΕΠΕΙΤΑ ΝΑ ΜΠΟΡΕΙ ΝΑ ΕΝΗΜΕΡΩΝΕΙ
            getAllMedicines(false);
//        else {
//            Toast.makeText(this, "Συνδεθείτε πρώτα στην εφαρμογή", Toast.LENGTH_SHORT).show();
//            alertDialog.dismiss();
//        }

    }



    @Override
    public void taskCompleteMedicinesLista(ArrayList medicinesLista) {

        alertDialog.show();
        Set<String> medLista = new HashSet<String>(medicinesLista);
        editor.putStringSet("medicinesArrayList",medLista);
        editor.commit();
        alertDialog.dismiss();

        updateInfo();

    }

    @Override
    public void taskCompleteMedicinesHashMap(HashMap<String, Integer> medicinesHashMap) {

        alertDialog.show();
        String jsonString = new Gson().toJson(medicinesHashMap);
        editor.putString("medicinesMap", jsonString);
        editor.apply();
        alertDialog.dismiss();


    }

    private void updateInfo() {

        String curDate = Utils.getCurrentDate();
        editor.putString("lastUpdateFarmakaLista",curDate);
        editor.commit();
        bd.lastUpdateTV.setText(curDate);
        alertDialog.dismiss();

        Toast.makeText(this, "Η λίστα φαρμάκων ενημερώθηκε", Toast.LENGTH_SHORT).show();
    }





}
