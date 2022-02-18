package micros_leader.george.nosileutikosfakelos.AsyncTasks;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;

import micros_leader.george.nosileutikosfakelos.Interfaces.IData;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Server;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskGetPatients extends AsyncTask<String, Void, JSONArray> {



    @SuppressLint("StaticFieldLeak")
    private Context ctx;
    private String base_URL ;
    private String query;
    private  String companyID;
    private Activity activity;
    private IData listener = null;
    private ArrayList<PatientsOfTheDay> patientsNosileuomenoi;
    private AlertDialog alertDialog;
    private TextView patientsTV;
    private JSONArray JArray;
    private Response response;
    private String URL ;



    public AsyncTaskGetPatients(Activity activity , TextView patientsTV) {

        this.patientsTV = patientsTV;
        this.activity = activity;
        ctx = activity.getApplicationContext();
        listener = (IData) activity;
        alertDialog = Utils.setLoadingAlertDialog(activity);

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (Utils.isNetworkAvailable2(ctx)) {

            alertDialog.show();

            base_URL = Utils.getAddress(ctx) + ":" + Utils.getPort(ctx) + "/rquery?query=";
            companyID = Utils.getcompanyID(ctx);
            query = new Str_queries().getPATIENTS_NOSILEUOMENOI(companyID);

            URL = Utils.URLreplaceBlanks("http://" + base_URL + Server.Crypt.encrypt(query));

           // Log.e("urlPatients" , URL);


        }

    }

    @Override
    protected JSONArray doInBackground(String... params) {


        if (URL == null)
            return null;
        else {


            final OkHttpClient client = new OkHttpClient();


            final Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();


            JSONObject Jobject = null;
            try {

                response = client.newCall(request).execute();
                String jsonData = response.body().string();


                try {
                    String decr = Server.Crypt.decrypt(jsonData);
                    if (decr == null)
                        return null;
                    Jobject = new JSONObject(decr);
                    JArray = Jobject.getJSONArray("results");


                } catch (JSONException e) {
                    //alertDialog.dismiss();
                    e.printStackTrace();
                    closeResponse();
                }


            } catch (IOException e) {
               // alertDialog.dismiss();

                e.printStackTrace();
                closeResponse();
            }


            return JArray;
        }
    }

    @Override
    protected void onPostExecute(JSONArray result) {


//        Log.e("results", result.toString());
        closeResponse();
        if (!(result == null)) {
            // ΕΑΝ ΔΕΝ ΕΧΕΙ ΣΤΑΤΟΥΣ ΔΗΛΑΔΗ ΕΑΝ ΕΧΕΙ ΑΠΟΤΕΛΕΣΜΑΤΑ ΝΑ ΦΕΡΕΙ ΝΑ ΚΑΝΕΙ ΤΟ ΑΠΟ ΚΑΤΩ
            try {
                if (!result.getJSONObject(0).has("status")) {

                    patientsNosileuomenoi = new ArrayList<>();

                    for (int i = 0; i < result.length(); i++) {

                        JSONObject currentPatient = null;
                        try {
                            currentPatient = result.getJSONObject(i);


                            PatientsOfTheDay pat = new PatientsOfTheDay();

                            pat.setFirstName(Utils.convertObjToString(currentPatient.get("FirstName")));
                            pat.setLastName(Utils.convertObjToString(currentPatient.get("LastName")));
                            pat.setFatherName(Utils.convertObjToString(currentPatient.get("fatherName")));
                            pat.setTransgroupID(currentPatient.getInt("transgroupID"));
                            pat.setPatientID(currentPatient.getInt("PatientID"));
                            pat.setDatein(Utils.convertObjToString(currentPatient.get("datein")));
                            pat.setIsEmergency(String.valueOf(currentPatient.getString("isEmergency")));
                            pat.setCode(Utils.convertObjToString(currentPatient.getString("code")));


                            patientsNosileuomenoi.add(pat);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                    patientsTV.setText(patientsNosileuomenoi.get(0).getTransgroupID() + " , "
                            + patientsNosileuomenoi.get(0).getFirstName() + " " +
                            patientsNosileuomenoi.get(0).getLastName() + " του " +
                            patientsNosileuomenoi.get(0).getFatherName()) ;
                    alertDialog.dismiss();

                    if (patientsNosileuomenoi.get(0).getIsEmergency().equals("true") || patientsNosileuomenoi.get(0).getIsEmergency().equals("1")) {
                        patientsTV.setTextColor(Color.RED);

                    }

                    listener.taskCompleteGetPatients(patientsNosileuomenoi);

                } else {
                    patientsTV.setText(R.string.no_data);
                    alertDialog.dismiss();
                }

            } catch (JSONException e) {
                patientsTV.setText(R.string.no_data);
                alertDialog.dismiss();
                e.printStackTrace();
            }


        }

        else {
            alertDialog.dismiss();

                listener.taskCompleteGetPatients(null);
            }
        }


    private void closeResponse(){
        if (response != null) {
            response.body().close();
            response.close();
        }
    }
}
