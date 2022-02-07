package micros_leader.george.nosileutikosfakelos.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.fragment.app.DialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import micros_leader.george.nosileutikosfakelos.DialogFragmentSearches.DF_SearchMedicineFromBase;
import micros_leader.george.nosileutikosfakelos.Interfaces.IMedLista;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon.Medicines;
import micros_leader.george.nosileutikosfakelos.Server;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskSearchMedicine extends AsyncTask<String, Void, JSONArray> {

    private Context ctx;
    public IMedLista listener = null;
    private ArrayList<Medicines> medicinesLista = new ArrayList();
    private HashMap<String, Integer> medicinesMap = new HashMap();
    private JSONArray JArray;
    private String URL ,MED_TEXT;


    public AsyncTaskSearchMedicine(DialogFragment df , String MED_TEXT){
        this.ctx = df.getContext();
        this.MED_TEXT = MED_TEXT;
        listener = (IMedLista) df;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (Utils.isNetworkAvailable2(ctx)) {


            String base_URL = Utils.getAddress(ctx) + ":" + Utils.getPort(ctx) + "/rquery?cquery=";
            String query = Str_queries.SEARCH_MEDICINES(MED_TEXT,Utils.getcompanyID(ctx)).replace("%","%25");

           // URL = Utils.URLreplaceBlanks("http://" + base_URL + Server.Crypt.encrypt(query));
            URL = Utils.URLreplaceBlanks("http://" + base_URL + Server.Crypt.encrypt(query));

             Log.e("url", URL);

        }

    }

    @Override
    protected JSONArray doInBackground(String... params) {


        if (URL == null)
            return null;
        else {


            final OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .build();

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
                    // Log.e("mydecr",decr);
                    Jobject = new JSONObject(decr);
                    JArray = Jobject.getJSONArray("results");


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {

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


                    for (int i = 0; i < result.length(); i++) {

                        JSONObject medicine = null;
                        try {
                            medicine = result.getJSONObject(i);
                            String id = Utils.convertObjToString(medicine.getInt("id"));
                            String name = medicine.getString("name");
                            medicinesLista.add(new Medicines(id,name));

                         //   medicinesMap.put(name,id);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }





                    listener.getMedList(medicinesLista);
                    //listener.taskCompleteMedicinesHashMap(medicinesMap);

                } else {
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        else {

            listener.getMedList(null);
          //  listener.taskCompleteMedicinesLista(null);

        }
    }

}
