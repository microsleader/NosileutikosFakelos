package micros_leader.george.nosileutikosfakelos.DialogFragmentSearches;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskSearchMedicine;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.IMedLista;
import micros_leader.george.nosileutikosfakelos.Notifications.DF_Notifications;
import micros_leader.george.nosileutikosfakelos.Notifications.NotificationsAdapter;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.Notifications.DF_Notifications.MEDICAL_INS_DOCTOR;

public class DF_SendDoctorOrders extends DialogFragment implements AsyncGetUpdateResult {

        private Activity act ;
        private RecyclerView apotelesmataRecyclerView;
        private String companyID;
        private AlertDialog alertDialog;
        private ArrayList <DF_Notifications.Notifications_objects> lista =  new ArrayList<>();
        private TextView dateTV,patientNameTV;
        private EditText msgET;
        private Button newRecBT;
        private Toolbar toolbar;
         public ImageButton updateIMB;
         private RecyclerView rv;
        private NotificationsAdapter adapter;
        private String patientID, patientName;
        public boolean isClicked;
        private DF_Notifications.Notifications_objects notif_item;
        View view;


    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            view = inflater.inflate(R.layout.df_send_notifications_doctors, container, false);
            if (getActivity() != null) {
                act = getActivity();
            }
            if (getArguments() != null) {
                patientID = getArguments().getString("patientID");
                patientName = getArguments().getString("patientName");

            }
            companyID = Utils.getcompanyID(act);
            alertDialog = Utils.setLoadingAlertDialog(act);
            patientNameTV = view.findViewById(R.id.patientNameTV);
            patientNameTV.setText(patientName);
            toolbar =  view.findViewById(R.id.toolbar);


            dateTV = view.findViewById(R.id.dateTV);
            msgET = view.findViewById(R.id.messageET);
            newRecBT = view.findViewById(R.id.newRecBT);
            newRecBT.setOnClickListener(view -> clearData());
            rv = view.findViewById(R.id.rv);

            dateTV.setText(Utils.getCurrentDate());
            Utils.dateListener(act,dateTV);

            updateIMB = Utils.thereIsImageUpdateButton(toolbar,act);
            updateIMB.setOnClickListener(view -> insertUpdateListener());


            getMed_orders();

            return view;
        }




    public void getMed_orders(){
        String query = "select top 32 ID, patientID, dbo.datetostr(date) as date ,\n" +
                " message,dbo.NAMEDOCTOR(doctorid) as doctor ,doctorID \n" +
                " from Nursing_iatrikes_entoles where patientID =  " + patientID + " order by id desc";
        alertDialog.show();
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2(query,act);
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {
                if (results != null){
                    lista = new ArrayList<>();
                    DF_Notifications.Notifications_objects n;
                    for (int i=0; i<results.length(); i++){
                        n = new DF_Notifications.Notifications_objects();
                        n.id = results.getJSONObject(i).getLong("ID");
                        n.userID = results.getJSONObject(i).optLong("UserID");
                        n.patientID = results.getJSONObject(i).optLong("patientID");
                        n.date = results.getJSONObject(i).optString("date");
                        n.doctor = results.getJSONObject(i).getString("doctor");
                        n.doctorID = results.getJSONObject(i).getLong("doctorID");
                        n.message = results.getJSONObject(i).getString("message");

                        lista.add(n);

                    }

                    adapter = new RV_adapter(act, lista, MEDICAL_INS_DOCTOR);
                    BasicActivity.setRecyclerViewLinearLayout(rv,act,adapter);

                }

                alertDialog.dismiss();
            }
        };

        task.execute();
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


        private void clearData() {
            notif_item = null;
            isClicked = false;
            msgET.setText("");
        }


        private void insertUpdateListener(){

        String q;
        String [] cols  ;
        String [] vals ;
        String date = Utils.convertDateTomilliseconds(dateTV.getText().toString());

            alertDialog.show();
            if (isClicked){
                cols = new String[]{"date","message"};
                vals = new String[]{date,msgET.getText().toString()};
             q =  Str_queries.setglobals(Utils.getUserID(act),"2",companyID) + " \n " + Utils.createUpdate("Nursing_iatrikes_entoles",cols ,vals, " WHERE ID = " + notif_item.id , act);

           }
           else{
               //   INSERT

               cols = new String[]{"date","patientID","companyID", "userID","doctorID" ,"message"};
               vals = new String[]{date,patientID, companyID, Utils.getUserID(act), Utils.getLinkDoctorID(act),msgET.getText().toString()};
               q = Str_queries.setglobals(Utils.getUserID(act),"2",companyID) + " \n " +  Utils.createInsert("Nursing_iatrikes_entoles",cols ,vals,  act);

           }

            new AsyncTaskUpdate(act,q, this).execute();
        }

        @Override
        public void update(String str) {
            Toast.makeText(act, str, Toast.LENGTH_SHORT).show();
            alertDialog.dismiss();
            if (str.equals(getString(R.string.successful_update))) {
                getMed_orders();
                msgET.setText("");
            }
        }







        private class RV_adapter extends NotificationsAdapter{


            public RV_adapter(Activity act, ArrayList result, int typeOfNotification) {
                super(act, result, typeOfNotification);
            }

            @Override
            public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isClicked = true;
                        notif_item = result.get(position);
                        msgET.setText(notif_item.message);
                        String date = Utils.convertMillisecondsTO_onlyDate(notif_item.date);
                        dateTV.setText(date);
                    }
                });
            }
        }
}
