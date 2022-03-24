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

public class AsyncTaskGetPlanoKlinonPatients extends AsyncTask<String, Void, JSONArray> {



    private Context ctx;
    private String base_URL ;
    public String query;
    private  String companyID;
    private int floorID;
    private Activity activity;
    private IData listener = null;
    private ArrayList<PatientsOfTheDay> patientsNosileuomenoi;
    private AlertDialog alertDialog;
    private TextView patientsTV;
    private JSONArray JArray;
    private String URL ;



    public AsyncTaskGetPlanoKlinonPatients(Activity activity , TextView patientsTV , int floorID) {

        this.patientsTV = patientsTV;
        this.activity = activity;
        this.floorID = floorID;
        ctx = activity.getApplicationContext();
        listener = (IData) activity;
        alertDialog = Utils.setLoadingAlertDialog(activity);

    }


    public AsyncTaskGetPlanoKlinonPatients(Activity activity , TextView patientsTV , int floorID,Activity actFromSigxoneusi) {

        this.patientsTV = patientsTV;
        this.activity = activity;
        this.floorID = floorID;
        ctx = activity.getApplicationContext();
        listener = (IData) actFromSigxoneusi;
        alertDialog = Utils.setLoadingAlertDialog(activity);

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (Utils.isNetworkAvailable2(ctx)) {

            alertDialog.show();

            base_URL = Utils.getAddress(ctx) + ":" + Utils.getPort(ctx) + "/rquery?cquery=";
            companyID = Utils.getcompanyID(ctx);

            if (query == null ) //ΑΥΤΟ ΕΙΝΑΙ ΤΟ DEFAULT ΑΛΛΑ ΜΠΟΡΕΙ ΝΑ ΤΟΥ ΕΧΟΥΜΕ ΔΩΣΕΙ ΚΑΠΟΙΟ QUERY
                 query =  Str_queries.getNosileuomenousFloors_v2( String.valueOf(floorID),companyID);

            URL = Utils.URLreplaceBlanks("http://" + base_URL + Server.Crypt.encrypt(query));

            //Log.e("url_patients" , query);

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

                Response response = client.newCall(request).execute();
                String jsonData = response.body().string();


                try {
                    String decr = Server.Crypt.decrypt(jsonData);
                    if (decr == null)
                        return null;
                    Jobject = new JSONObject(decr);
                    JArray = Jobject.getJSONArray("results");


                } catch (JSONException e) {
                    alertDialog.dismiss();
                    e.printStackTrace();
                }


            } catch (IOException e) {
                alertDialog.dismiss();

                e.printStackTrace();

            }


            return JArray;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onPostExecute(JSONArray result) {


//        Log.e("results", result.toString());

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
                            if (currentPatient.has("bed"))
                               pat.setBed(Utils.convertObjToString(currentPatient.get("bed")));
                            else
                                pat.setBed("");

                            pat.setFirstName(Utils.convertObjToString(currentPatient.get("FirstName")));
                            pat.setLastName(Utils.convertObjToString(currentPatient.get("LastName")));
                            pat.setFatherName(Utils.convertObjToString(currentPatient.get("fatherName")));
                            pat.setDatebirth(Utils.convertObjToString(currentPatient.get("datebirthStr")));
                            pat.setTransgroupID(currentPatient.getInt("transgroupID"));
                            pat.setSex(currentPatient.getInt("sex"));
                      //      pat.setPatientID(currentPatient.getInt("PatientID"));
                            pat.setDatein(Utils.convertObjToString(currentPatient.get("datein")));
                            pat.setIsEmergency(Utils.convertObjToString(currentPatient.get("IsEmergency")));
                            pat.setCode(Utils.convertObjToString(currentPatient.getString("code")));


                            patientsNosileuomenoi.add(pat);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    int pos = Utils.getPosPatientID(activity);
                    if (pos >= patientsNosileuomenoi.size())
                        pos = 0;

                    patientsTV.setText(patientsNosileuomenoi.get(pos).getBed() + " , " +
                            patientsNosileuomenoi.get(pos).getCode() + " , " +
                            patientsNosileuomenoi.get(pos).getFirstName() + " " +
                            patientsNosileuomenoi.get(pos).getLastName() +
                            " ( " + patientsNosileuomenoi.get(pos).getDatebirth() + " ) "
                            + " του " +
                            patientsNosileuomenoi.get(pos).getFatherName() + " , " +
                            patientsNosileuomenoi.get(pos).getTransgroupID());

                    alertDialog.dismiss();

                    if (patientsNosileuomenoi.get(pos).getIsEmergency().equals("true") || patientsNosileuomenoi.get(pos).getIsEmergency().equals("1")) {
                        patientsTV.setTextColor(Color.RED);

                    }

                    listener.taskCompletePlanoKlinonGetPatients(patientsNosileuomenoi);

                } else {
                    listener.taskCompletePlanoKlinonGetPatients(new ArrayList<PatientsOfTheDay>());
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

                listener.taskCompletePlanoKlinonGetPatients(null);
            }
        }

}
