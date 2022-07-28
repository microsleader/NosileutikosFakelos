package micros_leader.george.nosileutikosfakelos.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.Server;
import micros_leader.george.nosileutikosfakelos.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskUpdate_JSON extends AsyncTask<String, Void, String> {

    public Context ctx;
    public String base_URL,id, transgroupid;
    public AsyncGetUpdate_JSON listener = null;
    public String URL, table , patientID,doctorID,date,period,watchID;
    private  int titloi_position[];
    private JSONObject json = new JSONObject();
    private String insertOrUpdate;
    public ArrayList <String> names, values;
    public String names_col[];
    private String [] ekseresiPedion;
    private Response response;

    String data ;



    public AsyncTaskUpdate_JSON (Context ctx ,String id, String transgroupid,String table, ArrayList<String> names,
                                 ArrayList<String> values, int titloi_position[]) {

        this.ctx = ctx;
        this.id = id;
        this.transgroupid  = transgroupid;
        this.table = table;
        this.names = names;
        this.values = values;
        this.titloi_position = titloi_position;


    }



    public AsyncTaskUpdate_JSON (Context ctx , String transgroupid, String table, ArrayList<String> names,
                                 ArrayList<String> values,  int titloi_position[]) {

        this.ctx = ctx;
        this.transgroupid  = transgroupid;
        this.table = table;
        this.names = names;
        this.values = values;
        this.titloi_position = titloi_position;


    }


    public void deleteFromTitlesValues(String names_col[]){

        if (names_col != null) {
            for (String s : names_col) {
                names.remove(s);
            }
        }
    }



    public void setEkseresiPedion(String[] ekseresiPedion) {
        this.ekseresiPedion = ekseresiPedion;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();



        deleteFromTitlesValues(names_col);//ΤΑ ΔΙΑΓΡΑΦΩ ΕΠΕΙΔΗ ΤΑ ΒΑΖΩ ΜΕ ΑΛΛΟΝ ΤΡΟΠΟ ΩΣΤΕ ΝΑ ΜΗΝ ΥΠΑΡΧΕΙ ΔΙΠΛΟΕΓΓΡΑΦΗ ΚΑΙ ΣΠΑΣΕΙ Η ΕΦΑΡΜΟΓΗ ΛΟΓΩ
                                             //ΔΙΑΦΟΡΕΤΙΚΟΥ ΜΕΓΕΘΟΥΣ ΛΙΣΤΑΣ

        ArrayList <Integer> lista_titloi = new ArrayList();

        if (titloi_position != null) {   // ΑΝ ΕΙΝΑΙ null ΣΗΜΑΙΝΕΙ ΠΩς Η ΛΙΣΤΑ ΜΑΣ ΔΕΝ ΕΧΕΙ ΚΑΘΟΛΟΥ ΤΙΤΛΟΥΣ

            for (int i = 0; i < titloi_position.length; i++) {
                lista_titloi.add(titloi_position[i]);
            }

        }

    // ΓΙΑ ΚΑΠΟΙΟ ΛΟΓΟ ΣΕ ΜΕΡΙΚΑ ΦΥΛΛΑΔΙΑ ΜΠΑΙΝΕΙ ΠΕΔΙΟ ΚΕΝΟ ΠΕΔΙΟ("") ΟΠΟΤΕ ΠΡΕΠΕΙ ΝΑ ΣΒΗΣΤΕΙ
        for (int i=0; i<names.size() && i<values.size(); i++)
            if (names.get(i).equals("")){
                names.remove(i);
                values.remove(i);
        }
    //-----------------------------------

        try {
        json.put("_table", table);
        if (id != null && !id.equals(""))
            json.put("id", id);

        if (transgroupid != null && !transgroupid.equals(""))
            json.put("transgroupid",transgroupid);

            if (patientID != null && !patientID.equals(""))
                json.put("patientID", patientID);
            if (doctorID != null && !doctorID.equals(""))
                json.put("doctorID", doctorID);

//            if (date != null && !date.equals(""))
//                json.put("date", Utils.convertDateTomilliseconds(date));

            if (period != null && !period.equals(""))
                json.put("period", period);

            if (watchID != null && !watchID.equals(""))
                json.put("watch", watchID);

        //    Log.e("sizeName", names.size() + "");
        //    Log.e("sizeValues", values.size() + "");




        for (int i=0; i<names.size(); i++) {
            if (!names.get(i).equalsIgnoreCase("username")) {   // ΕΠΕΙΔΗ ΘΑ ΤΟ ΕΧΩ ΤΡΑΒΗΞΕΙ ΑΠΟ FUNCTION ΟΠΟΤΕ ΔΕΝ ΕΙΝΑΙ ΠΡΑΓΜΑΤΙΚΟ ΠΕΔΙΟ
                if (ekseresiPedion != null) {
                    if (!Utils.checkValueInArray(ekseresiPedion, names.get(i)))
                        json.put(names.get(i), values.get(i));
                } else
                    json.put(names.get(i), values.get(i));

            }

        }


            if (Utils.containsCaseInsensitive("userID",names)){
                if (json.has("UserID"))
                    json.remove("UserID");
            }


             //   Log.e("eleos",json.toString());


        }
        catch (JSONException e) {
            e.printStackTrace();
        }



        if (id == null || id.equals(""))
            insertOrUpdate = "cinsertGETID"; // ΤΟ ΧΡΗΣΙΜΟΠΟΙΩ ΜΕ ΑΥΤΟΝ ΤΟΝ ΤΡΟΠΟ ΩΣΤΕ ΟΤΑΝ ΚΑΝΕΙ ΙΝΣΕΡΤ ΝΑΙ ΕΠΙΣΤΡΕΦΕΙ ID
        else
            insertOrUpdate = "cinsert";   // ΚΑΝΕΙ UPDATE ΔΕΝ ΣΠΙΤΡΕΦΕΙ ΚΑΤΙ


      //  Log.e("elaaaaaaaaa", json.toString());
        base_URL = Utils.getAddress(ctx) + ":" + Utils.getPort(ctx) + "/rquery?";

        URL = Utils.URLreplaceBlanks("http://" + base_URL + "userid=" + Utils.getUserID(ctx) + "&"  +
                "companyid=" + Utils.getcompanyID(ctx) + "&" + "languageid=2" +"&"+ insertOrUpdate + "="
                + Server.Crypt.encrypt(String.valueOf(json)));

        Log.e("inserturl",Utils.URLreplaceBlanks("http://" + base_URL + "userid=" + Utils.getUserID(ctx) + "&"  +
                "companyid=" + Utils.getcompanyID(ctx) + "&" + "languageid=2" +"&"+ insertOrUpdate + "="
                + String.valueOf(json)));

        //Log.e("inserturl",URL);
        //Log.e("inserturl",String.valueOf(json));
    }

    @Override
    protected String doInBackground(String... params) {




        final OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(URL)
                .get()
                .build();

        try {

            response = client.newCall(request).execute();
            String jsonData = response.body().string();
            if (jsonData.equals(""))
                data = "";
            else
                data = Server.Crypt.decrypt(jsonData);

          //  data = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
            closeResponse();

        }

        return data;

    }

    @Override
    protected void onPostExecute(String str) {

        closeResponse();
        try {



            if ((str != null && str.equals(""))  ||  (str != null && str.contains("ID"))) {

                if ( str.contains("ID")){
                    JSONObject jsonObject = new JSONObject(str);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    String eggrafiID = Utils.convertObjToString(jsonArray.getJSONObject(0).get("ID"));
                    listener.getIDofInsert(eggrafiID);

                }

                listener.update_JSON("Πετυχημένη ενημέρωση");



            } else {
              //  str = "Κάτι πήγε στραβά";
                listener.update_JSON(str);
            }

        }

        catch (Exception e){
            str = "Κάτι πήγε στραβά ";
            listener.update_JSON(str);

        }



    }

    private void closeResponse(){
        if (response != null) {
            response.body().close();
            response.close();
        }
    }
}
