package micros_leader.george.nosileutikosfakelos.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGet_All_Column_Names;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Server;
import micros_leader.george.nosileutikosfakelos.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskGet_All_Columns_names extends AsyncTask<String, Void, JSONArray> {




    public Context ctx;
    public String base_URL ;
    public String query;
    public AsyncGet_All_Column_Names listener = null;
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

        //    Log.e("url", URL);
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

        if (result != null) {

            ArrayList<String> col_names = new ArrayList<>();


            try {

               for (int i=0; i< result.length(); i++){
                    String name = result.getJSONObject(i).getString("COLUMN_NAME");
                    col_names.add(name);

               }


                col_names.remove("ID");
                col_names.remove("TransGroupID");

                listener.taskGet_all_col_names(col_names);

            } catch (JSONException e) {

                e.printStackTrace();
            }


        }

        else {


                listener.taskGet_all_col_names(null);
            }
        }



        private void closeResponse(){
            if (response != null) {
                response.body().close();
                response.close();
            }
        }
    }

