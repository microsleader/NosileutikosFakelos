package micros_leader.george.nosileutikosfakelos.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetCurrentMetrisi;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Server;
import micros_leader.george.nosileutikosfakelos.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskGetCurrentMetrisi extends AsyncTask<String, Void, JSONArray> {

    //------------------ ΠΑΡΑΔΕΙΓΜΑ ΠΩΣ ΝΑ ΧΡΗΣΙΜΟΠΟΙΗΘΕΙ -------------------
//
//    ΑΠΟ ΤΗΝ ΚΛΑΣΣΗ ΠΟΥ ΤΟ ΚΑΛΟΥΜΕ ΓΡΑΦΟΥΜΕ ΤΟ ΕΞΗΣ
//
//             AsyncTaskGetJSON nameOfObject = new AsyncTaskGetJSON();
//             nameOfObject.URL = "www.example.com";
//             nameOfObject.execute();



    public Context ctx;
    public String base_URL ;
    public String query;
    public AsyncGetCurrentMetrisi listener = null;

    public JSONArray JArray;
    public String URL ;
    private Response response;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (Utils.isNetworkAvailable(ctx)) {
            base_URL = Utils.getAddress(ctx.getApplicationContext()) + ":" + Utils.getPort(ctx.getApplicationContext()) + "/rquery?cquery=";
            ;
            URL = Utils.URLreplaceBlanks("http://" + base_URL + Server.Crypt.encrypt(query));

           // Log.e("urlC", URL);
        }
        else {
            Toast.makeText(ctx, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected JSONArray doInBackground(String... params) {




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
                e.printStackTrace();
                closeResponse();
            }



        } catch (IOException e) {
            e.printStackTrace();
            closeResponse();
        }


        return JArray;
    }

    @Override
    protected void onPostExecute(JSONArray result) {


        closeResponse();
//        Log.e("results", result.toString());

        if (!(result == null)) {

            try {
                listener.taskCompleteCurrentMetrisi(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        else {

            try {
                listener.taskCompleteCurrentMetrisi(null);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    private void closeResponse(){
        if (response != null) {
            response.body().close();
            response.close();
        }
    }
}
