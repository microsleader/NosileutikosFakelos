package micros_leader.george.nosileutikosfakelos;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.Main_menu.Menu_generalActivity_NEW;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities.Nephroxenia_Main_Activity;

public class LoginActiity extends AppCompatActivity implements AsyncCompleteTask , AsyncGetUpdate_JSON{

    private EditText username , password;
    private ImageView settingsButton;
    private Button button;
    private String query;
    private SharedPreferences sp1;
    private ProgressBar progressBar;
    private String id, name , companyID , linkdoctorID;
    private int custID;
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editorForLogin;
    private static final int LOGIN_ACTIVITY_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.GONE);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        button = findViewById(R.id.loginButton);
        settingsButton = findViewById(R.id.settingsButton);


       // signIn_and_getDeviceID();



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);   //gia na min emafnizetai automata to keyboard

//------------------------------------------------
        prefs = getSharedPreferences("loginSettings", Activity.MODE_PRIVATE);
        editorForLogin = prefs.edit();

        sp1 = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        editor = sp1.edit();


  //-----------------------------------------------------------------
        checkIfIsLoggedIn();
        buttonClick();
        settingsButtonListener();


    }


    //ΓΙΑ ΕΔΙΠΟΙΗΣΕΙΣ ΣΤΟ ΤΑΜΠΛΕΤ ΑΛΛΑ ΘΑ ΓΙΝΕΙ ΜΕ ΑΛΛΟ ΤΡΟΠΟ
    private void signIn_and_getDeviceID() {

        if (!Utils.isDeviceSignedToDatabase(this)) {
            OneSignal.startInit(this)
                    .inFocusDisplaying(OneSignal
                            .OSInFocusDisplayOption
                            .Notification).unsubscribeWhenNotificationsAreDisabled(true).init();

            String deviceID = "";
            boolean isSubscribed = false;

            if (OneSignal.getPermissionSubscriptionState() != null && OneSignal.getPermissionSubscriptionState().getSubscriptionStatus() != null) {
                deviceID = OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getUserId();
                isSubscribed = OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getSubscribed();
            }

            if (deviceID != null && !deviceID.equals("") && isSubscribed) {

                String deviceModel = android.os.Build.MODEL;

                ArrayList<String> namesLista = new ArrayList();
                namesLista.add("deviceID");
                namesLista.add("information");

                ArrayList<String> valuesLista = new ArrayList();
                valuesLista.add(deviceID);
                valuesLista.add(deviceModel);


                AsyncTaskUpdate_JSON task = new AsyncTaskUpdate_JSON(this, null, "mobile_devices", namesLista, valuesLista, null);

                task.date = Utils.getCurrentDateTimeConverted();
                task.listener = this;
                task.execute();
            }
        }
    }

    @Override
    public void update_JSON(String str) {

    }

    @Override
    public void getIDofInsert(String id) {

//        if (!id.equals(""))
//            Utils.setDeviceSignedIn(this, true);

    }


    private void checkIfIsLoggedIn() {


        Boolean restoredText = prefs.getBoolean("login", false);

        if (restoredText != null) {

            Boolean login = prefs.getBoolean("login", false);//"false" is the default value.

          //  MyApplication.setCustID(Customers.CUSTID_FRONTIS); //  // ΓΙΑ ΤΕΣΤΑΡΙΣΜΑ


            name = prefs.getString("name","");
            id = prefs.getString("id","");
            companyID = prefs.getString("companyID", companyID);
            custID = prefs.getInt("custID",custID);

            if (login)
            {


                Intent intent;
                if (custID == Customers.CUSTID_NEPHROXENIA )
                    intent  =  new Intent(LoginActiity.this, Nephroxenia_Main_Activity.class);
                else if (custID == Customers.CUSTID_FRONTIS || custID == Customers.CUSTID_FRONTIS_2)
                    intent =  new Intent(LoginActiity.this, MainActivity_Aim.class);
                else
                    intent  =  new Intent(LoginActiity.this, Menu_generalActivity_NEW.class);

                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("companyID",companyID);


                startActivity(intent);
                finish();
            }
        }
    }


    private void buttonClick(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null)
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                if (!(Utils.getAddress(LoginActiity.this).equals("")) || !(Utils.getPort(LoginActiity.this).equals(""))) {



                    if (Utils.isNetworkAvailable(LoginActiity.this)) {
                        progressBar.setVisibility(View.VISIBLE);

                        query = "SELECT ID,Name," +
                                "isNurse, " +
                                " dbo.custID() as custID, " +
                                "linkdoctorID , " +
                                "companyID " +
                                "FROM USERS " +
                                "WHERE LoginName = '" + username.getText().toString().trim() + "'" + " AND WebPassword = '" + password.getText().toString().trim() + "'" ;

                        AsyncTaskGetJSON task = new AsyncTaskGetJSON();
                        task.ctx = getApplicationContext();
                        task.listener = LoginActiity.this;
                        task.query = query;
                        task.execute();


                    } else {
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(LoginActiity.this, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
                    }

                }

                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActiity.this, "Δεν έχετε ορίσει διεύθυνση για τον σέρβερ", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public void taskComplete(JSONArray results) throws JSONException {

        progressBar.setVisibility(View.GONE);


        if (results != null){
            JSONObject jsonObject = results.getJSONObject(0);


            if (jsonObject.has("Name") ){
                try {
                    boolean isNurse = Utils.convertObjToString(jsonObject.get("isNurse")).equals("1");
                    linkdoctorID = Utils.convertObjToString(jsonObject.get("linkdoctorID"));

                    if (isNurse || !linkdoctorID.equals("") ) {


                        id = String.valueOf(jsonObject.getInt("ID"));
                        name = jsonObject.optString("Name");
                        companyID = String.valueOf(jsonObject.optInt("companyID"));
                        custID = jsonObject.optInt("custID");

                        //GIA NA MIN KSANAKANEI LOGIN
                        editorForLogin.putInt("custID", custID);
                        editorForLogin.putBoolean("login", true);
                        editorForLogin.putString("id", id);
                        editorForLogin.putString("name", name);
                        editorForLogin.putString("companyID", companyID);
                        editorForLogin.putString("linkdoctorID", linkdoctorID);
                        editorForLogin.putBoolean("isNurse", isNurse);

                        if (editorForLogin.commit()) {


                            // MyApplication.setCustID(Customers.CUSTID_FRONTIS); //  // ΓΙΑ ΤΕΣΤΑΡΙΣΜΑ

                            Intent intent;

                            if (custID == Customers.CUSTID_NEPHROXENIA)
                                intent = new Intent(LoginActiity.this, Nephroxenia_Main_Activity.class);
                            else if (custID == Customers.CUSTID_FRONTIS || custID == Customers.CUSTID_FRONTIS_2)
                                intent = new Intent(LoginActiity.this, MainActivity_Aim.class);
                            else
                                intent = new Intent(LoginActiity.this, Menu_generalActivity_NEW.class);

                            intent.putExtra("id", id);
                            intent.putExtra("name", name);
                            intent.putExtra("companyID", companyID);
                            startActivity(intent);

                            finish();
                        }
                    }

                    else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, "Ο συγκεκριμένος χρήστης δεν έχει οριστεί σαν νοσηλευτής", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    progressBar.setVisibility(View.GONE);

                    e.printStackTrace();
                }



            }
            else{
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Λάθος όνομα χρήστη ή κωδικός", Toast.LENGTH_SHORT).show();
            }


        }

        else{
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Πρόβλημα με τη συνδεσή σας , ελέγχτε τις ρυθμίσεις σας ή τη συνδεσή σας", Toast.LENGTH_LONG).show();
        }



    }

    @Override
    public void taskCompleteGetVardies(JSONArray results) throws JSONException {

    }


    public void settingsButtonListener(){

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(LoginActiity.this, SettingsActivity.class);
                intent.putExtra("activityCode", LOGIN_ACTIVITY_CODE);
                startActivity(intent);
            }
        });
    }



}
