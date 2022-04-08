package micros_leader.george.nosileutikosfakelos.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetDelete;
import micros_leader.george.nosileutikosfakelos.Server;
import micros_leader.george.nosileutikosfakelos.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskDelete extends AsyncTask<String, Void, String> {

    public Context ctx;
    public String base_URL ;
    public String id,table;
    public AsyncGetDelete listener = null;
    public String URL ;


    String data ;

    public AsyncTaskDelete (Context ctx ,String table, String id ) {

        this.ctx = ctx;
        this.id = id;
        this.table = table;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        base_URL = Utils.getAddress(ctx.getApplicationContext()) + ":" + Utils.getPort(ctx.getApplicationContext()) + "/rquery?cquery=";;

        URL = Utils.URLreplaceBlanks("http://" + base_URL + Server.Crypt.encrypt(" delete " + table + " where id = " + id));

      //  Log.e("url",URL);
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
            String decr = Server.Crypt.decrypt(response.body().string());
            if (decr == null)
                data = "";
            else
                data = decr;


            //   Log.e("datamore" , data);



        } catch (IOException e) {
            e.printStackTrace();

        }


        return data;
    }

    @Override
    protected void onPostExecute(String str) {

        try {

            if (!(str == null) && str.equals("")) {
                str = "Πετυχημένη ενημέρωση";
                listener.deleteResult(str);

            } else {
                str = "Κάτι πήγε στραβά";
                listener.deleteResult(str);
            }

        }

        catch (Exception e){
            str = "Κάτι πήγε στραβά";
            listener.deleteResult(str);

        }



    }

}
