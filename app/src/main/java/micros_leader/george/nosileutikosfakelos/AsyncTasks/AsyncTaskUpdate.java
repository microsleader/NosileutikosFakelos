package micros_leader.george.nosileutikosfakelos.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Server;
import micros_leader.george.nosileutikosfakelos.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskUpdate extends AsyncTask<String, Void, String> {

    public Context ctx;
    public String base_URL ;
    public String query;
    public AsyncGetUpdateResult listener = null;
    public String URL ;


    String data ;

    public AsyncTaskUpdate (Context ctx , String query) {

        this.ctx = ctx;
        this.query = query;
    }

    public AsyncTaskUpdate (Context ctx , String query, AsyncGetUpdateResult listener) {

        this.ctx = ctx;
        this.query = query;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (query == null || query.isEmpty())
            cancel(true);

        base_URL = Utils.getAddress(ctx) + ":" + Utils.getPort(ctx) + "/rquery?cquery=";;

        URL = Utils.URLreplaceBlanks("http://" + base_URL + Server.Crypt.encrypt(query));

        Log.e("url_update",URL);
        Log.e("url_update",query);
    }

    @Override
    protected String doInBackground(String... params) {

        final OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(URL)
                .get()
                .build();

        try {

            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            if (jsonData.equals(""))
                return ""; //successful
            else
                return jsonData;  //failed

        //     Log.e("datamore" , data);




        } catch (IOException e) {
            e.printStackTrace();

        }


        return data;
    }

    @Override
    protected void onPostExecute(String str) {

        try {

            if (str != null && str.equals("")) {
                str = "Πετυχημένη ενημέρωση";
                listener.update(str);

            } else  if (str != null && str.contains("wrong")){
                str = "Κάτι πήγε στραβά";
                listener.update(str);
            }
            else
                listener.update(str);


        }

        catch (Exception e){
            str = "Κάτι πήγε στραβά";
            listener.update(str);

        }



        }

}
