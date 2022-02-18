package micros_leader.george.nosileutikosfakelos.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import micros_leader.george.nosileutikosfakelos.Interfaces.IData;
import micros_leader.george.nosileutikosfakelos.Server;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskGetMedicines extends AsyncTask<String, Void, JSONArray> {



    public Context ctx;
    private String base_URL ;
    private String query;

    public IData listener = null;
    private ArrayList<String> medicinesLista = new ArrayList();
    private HashMap <String, Integer> medicinesMap = new HashMap();
    private JSONArray JArray;
    private String URL ;
    private Response response;





    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (Utils.isNetworkAvailable2(ctx)) {


            base_URL = Utils.getAddress(ctx) + ":" + Utils.getPort(ctx) + "/rquery?query=";
            query = new Str_queries().MEDICINES;

            URL = Utils.URLreplaceBlanks("http://" + base_URL + Server.Crypt.encrypt(query));

           // Log.e("url", URL);

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

                response = client.newCall(request).execute();
                String jsonData = response.body().string();


                try {
                    String decr = Server.Crypt.decrypt(jsonData);
                    if (decr == null)
                        return null;
                    Jobject = new JSONObject(decr);
                    JArray = Jobject.getJSONArray("results");


                } catch (JSONException e) {
                    e.printStackTrace();
                    closeResponse();
                }


            } catch (IOException e) {

                e.printStackTrace();
                closeResponse();

            }


            return JArray;
        }
    }

    @Override
    protected void onPostExecute(JSONArray result) {

        closeResponse();

//        Log.e("results", result.toString());

        if (!(result == null)) {
            // ΕΑΝ ΔΕΝ ΕΧΕΙ ΣΤΑΤΟΥΣ ΔΗΛΑΔΗ ΕΑΝ ΕΧΕΙ ΑΠΟΤΕΛΕΣΜΑΤΑ ΝΑ ΦΕΡΕΙ ΝΑ ΚΑΝΕΙ ΤΟ ΑΠΟ ΚΑΤΩ
            try {
                if (!result.getJSONObject(0).has("status")) {


                    for (int i = 0; i < result.length(); i++) {

                        JSONObject medicine = null;
                        try {
                            medicine = result.getJSONObject(i);
                            int id = medicine.getInt("id");
                            String name = medicine.getString("name");
                            medicinesLista.add(name);

                            medicinesMap.put(name,id);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }





                    listener.taskCompleteMedicinesLista(medicinesLista);
                    listener.taskCompleteMedicinesHashMap(medicinesMap);

                } else {
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        else {

            listener.taskCompleteMedicinesHashMap(null);
            listener.taskCompleteMedicinesLista(null);

        }
    }


    private void closeResponse(){
        if (response != null) {
            response.body().close();
            response.close();
        }
    }
}
