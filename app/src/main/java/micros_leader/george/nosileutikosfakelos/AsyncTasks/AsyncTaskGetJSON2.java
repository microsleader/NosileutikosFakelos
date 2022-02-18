package micros_leader.george.nosileutikosfakelos.AsyncTasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Server;
import micros_leader.george.nosileutikosfakelos.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskGetJSON2 extends AsyncTask<String, Void, JSONArray> {




    public Context ctx;
    private String base_URL ;
    public String query;
    public AsyncCompleteTask2 listener = null;
    private JSONArray JArray;
    private String URL ;
    private java.net.URL url ;
    private Response response;

    public AsyncTaskGetJSON2(){

    }
    public AsyncTaskGetJSON2(String query){
        this.query = query;
    }

    public AsyncTaskGetJSON2(String query, Context ctx){
            this.query = query;
            this.ctx = ctx;
            this.listener = (AsyncCompleteTask2) ctx;
    }

    public AsyncTaskGetJSON2(String query, Activity act){
        this.query = query;
        this.ctx = act;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (Utils.isNetworkAvailable(ctx)) {
            base_URL = Utils.getAddress(ctx) + ":" + Utils.getPort(ctx) + "/rquery?cquery=";

            URL = Utils.URLreplaceBlanks("http://" + base_URL + Server.Crypt.encrypt(query));
            try {
                url = new URL(Utils.URLreplaceBlanks("http://" + base_URL + Server.Crypt.encrypt(query)));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            Log.e("url2", query);

        }
        else {
            Toast.makeText(ctx, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
            cancel(true);
        }
    }

    @Override
    protected JSONArray doInBackground(String... params) {



        try {


        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(URL)
                .get()
                .build();

        JSONObject Jobject = null;


            response = client.newCall(request).execute();
            String jsonData = response.body().string();



            try {
                String decr = Server.Crypt.decrypt(jsonData);
                if (decr == null)
                    return null;
               // Log.e("mydecr",decr);
                Jobject = new JSONObject(decr);
                JArray = Jobject.getJSONArray("results");


            } catch (JSONException e) {
                closeResponse();
                Log.e("eleos",Server.Crypt.decrypt(jsonData));
                e.printStackTrace();
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

        if (result != null) {


            try {
                if (listener != null && ctx != null)
                    listener.taskComplete2(result);
            } catch (JSONException e) {

                e.printStackTrace();
            }


        }

        else {


            try {
                listener.taskComplete2(null);
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
