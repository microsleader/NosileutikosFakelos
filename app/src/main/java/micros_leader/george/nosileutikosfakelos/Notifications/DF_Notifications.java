package micros_leader.george.nosileutikosfakelos.Notifications;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class DF_Notifications extends DialogFragment {


    private Activity act;
    private AlertDialog alertDialog;
    private RecyclerView rv;
    private EditText searchET;
    private Toolbar toolbar;
    private NotificationsAdapter adapter;
    private String transgroupID,companyID,patientID,query;
    private int typeOfNotification;
    public  final static String TYPE_OF_NOTIFICATION = "typeOfNotification";
    private ArrayList <Notifications_objects> lista =  new ArrayList<>();
    private ArrayList<Notifications_objects> filteredList = new ArrayList<>();
    private boolean isIconPressed;

    public final static int NOTIFICATIONS_BOTH_CATEGORIES = 1;
    public final static int NOTIFICATIONS_TO_CONFIRM = 2;
    public final static int MEDICAL_INS_NOTIFICATIONS = 3;
    public final static int MEDICAL_INS_DOCTOR = 8;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_df_notifications, container, false);
        rv = view.findViewById(R.id.rv);
        toolbar = view.findViewById(R.id.toolbar);
        searchET = view.findViewById(R.id.searchET);


        searchETListener();
        if (getActivity() != null)
            act = getActivity();
        companyID = Utils.getcompanyID(act);
        alertDialog = Utils.setLoadingAlertDialog(act);

        if (getArguments() != null) {
            transgroupID = getArguments().getString("transgroupID");
            patientID = getArguments().getString("patientID");
            isIconPressed = getArguments().getBoolean("iconPressed");
            typeOfNotification = getArguments().getInt(TYPE_OF_NOTIFICATION);

        }
        manage_type_of_notification();


        alertDialog = Utils.setLoadingAlertDialog(act);

        if (typeOfNotification != 0)
            getNotifications();

        return view;
    }

        private void searchETListener(){

            searchET.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    filter(editable.toString());
                }
            });
        }



    public void getNotifications(){
        alertDialog.show();
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2(query,act);
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {
                if (results != null && !results.getJSONObject(0).has("status")){
                    lista = new ArrayList<>();
                    Notifications_objects n;
                    for (int i=0; i<results.length(); i++){
                        n = new Notifications_objects();
                        n.id = results.getJSONObject(i).getLong("ID");
                        n.userID = results.getJSONObject(i).optLong("UserID");
                        n.username  = results.getJSONObject(i).optString("username");
                        n.patient  = results.getJSONObject(i).optString("patient");
                        n.message = results.getJSONObject(i).getString("Message");
                        n.isConfirmed = results.getJSONObject(i).optInt("Confirmation") == 1;
                        n.confirmDate  = results.getJSONObject(i).optString("confirmDate");

                        lista.add(n);

                    }

                    adapter = new NotificationsAdapter(act,lista,typeOfNotification);
                    BasicActivity.setRecyclerViewLinearLayout(rv,act,adapter);

                }

                alertDialog.dismiss();
            }
        };

        task.execute();
    }


    private void filter(String text){

        filteredList =  new ArrayList();

        for (Notifications_objects item : lista){

            if (item.patient.toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
            else if (item.message.toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }

        }


        NotificationsAdapter customAdapter =  new NotificationsAdapter(act,lista,typeOfNotification);
        customAdapter.filterList(filteredList);
        rv.setAdapter(customAdapter);
    }




    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (dialog.getWindow() != null)
                dialog.getWindow().setLayout(width, height);
        }
    }






    private void manage_type_of_notification(){
        switch (typeOfNotification){
            case NOTIFICATIONS_TO_CONFIRM:

                query = Str_queries.CUR_DATE +
                        " select  * , dbo.DateTimeToString(confirmation_date) as confirmDate , dbo.NAMEUSER(UserID) as username " +
                        " from Notification_messages" +
                        " where companyid = " + companyID +
                        " and date > @curDate  - ( 86400000 * 14 ) " +
                        " order by id desc";
                toolbar.setTitle("Ειδοποιήσεις προς επιβεβαίωση");
                break;

            case MEDICAL_INS_NOTIFICATIONS:
                query = "declare @curDate bigint = dbo.datetime_to_date( dbo.timeToNum(GETDATE()))\n" +
                        "select top 100 * , dbo.DateTimeToString(confirmation_date) as confirmDate , dbo.NAMEUSER(Conifrmed_userID) as username ,dbo.nameperson(patientID) as patient \n" +
                        "from Nursing_iatrikes_entoles \n" +
                        " where companyid = " + companyID +
                        (patientID != null && isIconPressed ? " and  patientID = " + patientID : "" ) +
                        " and dbo.datetime_to_date(date) <= @curDate \n" +
                        " order by id desc";
                toolbar.setTitle("Ιατρικές εντολές προς επιβεβαίωση");
                break;

        }
    }




        public static class Notifications_objects{
        public long id,userID ,doctorID, patientID;
        public String message,date,confirmDate,username,patient,doctor;
        public boolean isConfirmed;
    }

}
