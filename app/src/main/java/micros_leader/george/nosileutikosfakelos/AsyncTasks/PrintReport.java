package micros_leader.george.nosileutikosfakelos.AsyncTasks;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import micros_leader.george.nosileutikosfakelos.Interfaces.DataSendedList;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended_str;
import micros_leader.george.nosileutikosfakelos.Interfaces.IData;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.ReportIDs;
import micros_leader.george.nosileutikosfakelos.Server;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Callback;

public class PrintReport {



    private final String base_URL;
    private  String STATUS_PRINT;
    private  String printer;
    private final String patientID;
    private final String transgroupID;
    private final String datestr;
    private final int reportID;

    private final Activity act;
    private final ReportParams rep_param;
    public DataSendedList listener = null;
    private final AlertDialog alertDialog;
    private JSONArray JArray;
    private String URL ;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());



    public PrintReport(Activity act, String patientID, String transgroupID, String datestr,  int reportID, ReportParams rep_param){
        this.act = act;
        base_URL = Utils.getAddress(act) + ":" + Utils.getPort(act) + "/rquery?getprinters=1";
        URL = Utils.URLreplaceBlanks("http://" + base_URL);
        alertDialog = Utils.setLoadingAlertDialog(act);
        this.transgroupID = transgroupID;
        this.patientID = patientID;
        this.datestr = datestr;
        this.reportID = reportID;
        this.rep_param = rep_param;

        this.listener = (DataSendedList) act;

    }


    public void execute(){
        executeAsyncGetPrinters();
    }


    private  <r> void executeAsyncGetPrinters() {
        executor.execute(() -> {
            //Background work here
            if (URL == null)
                return ;
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

                    String decr = Server.Crypt.decrypt(jsonData);
                    if (decr == null)
                        return ;
                    Jobject = new JSONObject(decr);
                    JArray = Jobject.getJSONArray("results");



                } catch (IOException | JSONException e) {
                    alertDialog.dismiss();
                    e.printStackTrace();

                }


            }
            handler.post(() -> {
                //UI Thread work here

                if (JArray != null) {
                    try {
                        if (!JArray.getJSONObject(0).has("status")) {

                            ArrayList<String> printers = new ArrayList<>();

                            for (int i = 0; i < JArray.length(); i++) {
                                JSONObject s = JArray.getJSONObject(i);
                                String name = s.getString("name");
                                printers.add(name);
                            }

                            if (printers.size() >0)
                                showPrinters(act,printers);
                            else
                                Toast.makeText(act, "Η λίστα είναι κενή", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {

                        alertDialog.dismiss();
                        e.printStackTrace();
                    }
                }

                else {
                    alertDialog.dismiss();
                }


            });
        });

    }





    private  void showPrinters(Activity act, ArrayList <String> lista){

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(act);
        builderSingle.setIcon(R.drawable.save_icon_48px);
        builderSingle.setTitle("Επιλέξτε εκτυπωτή");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(act, android.R.layout.select_dialog_singlechoice);

        for (String  name : lista)
            arrayAdapter.add(name);

        builderSingle.setPositiveButton("ΟΚ", (dialog, which) -> executePrintReport());
        builderSingle.setNegativeButton("Ακύρωση", (dialog, which) -> dialog.dismiss());
        builderSingle.setSingleChoiceItems(arrayAdapter, 0, (dialogInterface, i) -> printer = arrayAdapter.getItem(i));

        //builderSingle.setAdapter(arrayAdapter, (dialog, which) -> printer = arrayAdapter.getItem(which));
        builderSingle.show();

    }






    private  <r> void executePrintReport() {
       URL = Utils.URLreplaceBlanks(getURL());
       //URL = Utils.URLreplaceBlanks("http://" + base_URL);


        executor.execute(() -> {
            //Background work here
            if (URL == null)
                return ;
            else {


                final OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url(URL)
                        .get()
                        .build();

                try {

                    Response response = client.newCall(request).execute();
                    String msg = response.body().string();

                    String status = Server.Crypt.decrypt(msg);

                    if (status == null)
                        return ;

                    if (status.toLowerCase(Locale.ROOT).contains("\"status\":\"ok\""))
                        STATUS_PRINT = "Η εκτύπωση στάλθηκε";
                    else
                        STATUS_PRINT = "Παρουσιάστηκε πρόβλημα στην εκτύπωση";



                } catch (IOException e) {
                    alertDialog.dismiss();
                    e.printStackTrace();

                }


            }
            handler.post(() -> {
                //UI Thread work here

                if (STATUS_PRINT != null)
                    Toast.makeText(act, STATUS_PRINT, Toast.LENGTH_SHORT).show();

                    alertDialog.dismiss();


            });
        });

    }








    private String getURL(){

        StringBuilder sb = new StringBuilder();
        sb.append("http://" + Utils.getAddress(act) + ":" + Utils.getPort(act) + "/rquery?");

        if (rep_param == ReportParams.TRANSGROUP_ID_ONLY)
            sb.append("transgroupID=").append(transgroupID);
        else if (rep_param == ReportParams.PATIENT_ID_ONLY)
            sb.append("patientID=").append(patientID);
        else if (rep_param == ReportParams.TRANSGROUP_ID_AND_DATE_STR){
            sb.append("transgroupID=").append(transgroupID);
            sb.append("&datestr=").append(datestr);
        }

        sb.append("&printer=").append(printer);
        sb.append("&report=").append(reportID);

//      String url =  "http://" + Utils.getAddress(act) + ":" + Utils.getPort(act) + "/rquery?";
//      //?printer=" + printer + "&report=1")
//        if (reportID == ReportIDs.KATHIMERINO_ZIGISMA_ASTHENI) {
//            url += "transgroupID=" + transgroupID + "&printer=" + printer + "&report=" + ReportIDs.KATHIMERINO_ZIGISMA_ASTHENI;
//        }

        return sb.toString();
    }


    public enum ReportParams{
            TRANSGROUP_ID_ONLY,
            PATIENT_ID_ONLY,
            TRANSGROUP_ID_AND_DATE_STR,
    }


}
