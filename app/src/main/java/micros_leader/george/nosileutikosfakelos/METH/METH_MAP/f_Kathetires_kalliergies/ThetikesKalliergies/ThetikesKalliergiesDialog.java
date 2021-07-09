package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.ThetikesKalliergies;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
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
import org.json.JSONObject;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.KalliergiesDialog.KalliergiesRVAdaptor;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.KalliergiesItems;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.Utils.dateListener;

public class ThetikesKalliergiesDialog extends DialogFragment{

        private RecyclerView recyclerView;
        private Activity act;
        private String date , transgroupID;
    public TextView dateDeigmatosTV;
        private ImageButton updateIMB;
        private Toolbar toolbar;
        private ArrayList<KalliergiesItems> lista = new ArrayList<>();
        private KalliergiesRVAdaptor adapter;
        public EditText deigmaET,apotelesmaET;
        public int id =0;
        public Button neaeggrafiBT;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.custom_thetikes_kalliergies_dialog, container, false);
            // view.setSupportActionBar(t);
            act = getActivity();
            if (getArguments() != null) {
                date = getArguments().getString("date");
                transgroupID = getArguments().getString("transgroupID");

            }

            toolbar = view.findViewById(R.id.toolbar);
            deigmaET = view.findViewById(R.id.deigmaET);
            apotelesmaET = view.findViewById(R.id.apotelesmaET);
            neaeggrafiBT = view.findViewById(R.id.neaEggrafiBT);
            dateDeigmatosTV = view.findViewById(R.id.dateDeigmatosTV);
            dateDeigmatosTV.setText(Utils.getCurrentDate());
            dateListener(act,dateDeigmatosTV);

            neaeggrafiBTListener();


            recyclerView = view.findViewById(R.id.kalliergiesRV);
            recyclerView.setLayoutManager(new LinearLayoutManager(act, LinearLayoutManager.VERTICAL, false));
            recyclerView.addItemDecoration(new DividerItemDecoration(act, DividerItemDecoration.VERTICAL));
            recyclerView.setItemViewCacheSize(300);
            recyclerView.setHasFixedSize(true);

            imageUpdateButton();
            getKalliergies();

            return view;
        }




    public void getKalliergies(){


            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = act;
            task.query = "select *, dbo.datetostr(date) as dateStr from Nursing_thetikes_kalliergies_meth" +
                    " where transgroupID =  " + transgroupID ;
                  //  " and dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + date + "' , 103))";
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {

                    if (results!= null && !results.getJSONObject(0).has("status")){

                        lista.clear();

                        for (int i=0; i<results.length(); ++i){
                            JSONObject jsonObject = results.getJSONObject(i);
                            int id =  jsonObject.getInt("ID");
                            int nurseID =  jsonObject.getInt("UserID");

                            String deigma = jsonObject.getString("deigma");
                            String dateDeigmatos = jsonObject.getString("dateStr");
                            String apotelesma = jsonObject.getString("apotelesma");

                            lista.add(new KalliergiesItems(id,deigma,apotelesma,nurseID,dateDeigmatos));

                        }
                        adapter = new KalliergiesRVAdaptor(ThetikesKalliergiesDialog.this, lista);
                        recyclerView.setAdapter(adapter);
                    }
                }
            };

            task.execute();

        }


        private void imageUpdateButton(){


            updateIMB =new ImageButton(act);
            Toolbar.LayoutParams l1=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
            l1.gravity= Gravity.START;
            updateIMB.setLayoutParams(l1);
            updateIMB.setBackgroundResource(R.drawable.save_icon_48px);
            updateIMB.setPadding(20,20,20,20);
            updateIMB.setScaleType(ImageButton.ScaleType.FIT_CENTER);
            toolbar.addView(updateIMB);
            Toolbar.LayoutParams l3=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
            l3.gravity= Gravity.END;
            updateIMB.setLayoutParams(l3);


            updateIMB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertOrUpdate();



                }
            });
        }




    private void neaeggrafiBTListener() {
        neaeggrafiBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deigmaET.setText("");
                apotelesmaET.setText("");
                id = 0;
            }
        });
    }



        private void insertOrUpdate() {

            StringBuilder query = new StringBuilder();

            if (id == 0) {
                query.append("exec disable_triggers insert Nursing_thetikes_kalliergies_meth (TransGroupID ,date, UserID , deigma , apotelesma) " +
                        "values (");
                query.append(transgroupID + ",");
                query.append(Utils.convertDateTomilliseconds(dateDeigmatosTV.getText().toString()) + ",");
                query.append(Utils.getUserID(act) + ",");
                query.append("'" +deigmaET.getText().toString() + "',");
                query.append("'" + apotelesmaET.getText().toString() + "')");
                query.append(" exec enable_triggers");
            }
            else{
                query.append("exec disable_triggers update Nursing_thetikes_kalliergies_meth set ");
                query.append("deigma = '" + deigmaET.getText().toString() + "', ");
                query.append("date = '" + Utils.convertDateTomilliseconds(dateDeigmatosTV.getText().toString()) + "', ");
                query.append("apotelesma = '" + apotelesmaET.getText().toString() + "' " );
                query.append(" where id  = " + id);
                query.append(" exec enable_triggers");


            }



            AsyncTaskUpdate task = new AsyncTaskUpdate(act, query.toString());
            task.listener = new AsyncGetUpdateResult() {
                @Override
                public void update(String str) {

                    getKalliergies();
                    Toast.makeText(act, str, Toast.LENGTH_SHORT).show();
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

}

