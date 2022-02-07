package micros_leader.george.nosileutikosfakelos.AsyncTasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import micros_leader.george.nosileutikosfakelos.Interfaces.IData;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Server;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskGetFloors extends AsyncTask<String, Void, JSONArray> {



    private Context ctx;
    private String base_URL ;
    private String query;
    private  String companyID;
    private Activity activity;
    private IData listener = null;
    private ArrayList<String> floorsLista;
    private AlertDialog alertDialog;
    //private TextView patientsTV;
    private Spinner floorsSP;
    private ArrayAdapter adapter;
    private JSONArray JArray;
    private String URL ;



    public AsyncTaskGetFloors(Activity activity , Spinner floorsSP , ArrayAdapter adapter ) {



        //this.patientsTV = patientsTV;
        this.floorsSP = floorsSP;
        this.adapter = adapter;
        this.activity = activity;
        ctx = activity;
        listener = (IData) activity;
        alertDialog = Utils.setLoadingAlertDialog(activity);

    }


    public AsyncTaskGetFloors(Activity activity , Spinner floorsSP , ArrayAdapter adapter, Activity actFromSigxoneusi ) {



        //this.patientsTV = patientsTV;
        this.floorsSP = floorsSP;
        this.adapter = adapter;
        this.activity = activity;
        ctx = activity;
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
            query = new Str_queries().getFloors(companyID);

            URL = Utils.URLreplaceBlanks("http://" + base_URL + Server.Crypt.encrypt(query));

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
                    e.printStackTrace();
                }


            } catch (IOException e) {
                alertDialog.dismiss();

                e.printStackTrace();

            }


            return JArray;
        }
    }

    @Override
    protected void onPostExecute(JSONArray result) {


//        Log.e("results", result.toString());

        if (!(result == null)) {
            // ΕΑΝ ΔΕΝ ΕΧΕΙ ΣΤΑΤΟΥΣ ΔΗΛΑΔΗ ΕΑΝ ΕΧΕΙ ΑΠΟΤΕΛΕΣΜΑΤΑ ΝΑ ΦΕΡΕΙ ΝΑ ΚΑΝΕΙ ΤΟ ΑΠΟ ΚΑΤΩ
            try {
                if (!result.getJSONObject(0).has("status")) {

                    floorsLista = new ArrayList<>();
                    HashMap <String,Integer> floorMap = new HashMap<>();

                    for (int i = 0; i < result.length(); i++) {

                        JSONObject curFloor = result.getJSONObject(i);

                        int id = curFloor.getInt("id");
                        String name = curFloor.getString("name");

                        floorsLista.add(name);
                        floorMap.put(name,id);

                    }


                    alertDialog.dismiss();

                    listener.taskCompleteGetFloors(floorMap);

                } else {
                    //patientsTV.setText(R.string.no_data);
                    alertDialog.dismiss();
                }

            } catch (JSONException e) {
               // patientsTV.setText(R.string.no_data);
                alertDialog.dismiss();
                e.printStackTrace();
            }


        }

        else {
            alertDialog.dismiss();

            listener.taskCompleteGetFloors(null);
        }


        alertDialog.dismiss();


            adapter = new ArrayAdapter<>(activity, R.layout.spinner_layout2, floorsLista);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (floorsLista != null) {
            floorsSP.setAdapter(adapter);
            floorsSP.setSelection(Utils.getPosFloorID(activity));
        }

    }

}

