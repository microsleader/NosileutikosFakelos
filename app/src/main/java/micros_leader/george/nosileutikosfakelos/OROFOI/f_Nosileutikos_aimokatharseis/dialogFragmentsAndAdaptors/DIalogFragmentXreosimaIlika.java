package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class DIalogFragmentXreosimaIlika extends DialogFragment {



    private RecyclerView ilikaRV;
    private Toolbar toolbar;
    private String transgroupID = "";
    private Context CTX;
    private  ArrayList <XreosimaIlika> lista  =  new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_xreosima_ilika_dialog, container, false);
        CTX = view.getContext();
        transgroupID = getArguments().getString("transgroupID");

         toolbar = view.findViewById(R.id.toolbar);
         toolbar.setTitle("Χρεώσεις συνεδρίας");


        ilikaRV =  view.findViewById(R.id.ilikaRecyclerView);
        ilikaRV.setLayoutManager(new LinearLayoutManager(CTX, LinearLayoutManager.VERTICAL, false));
        ilikaRV.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        ilikaRV.setItemViewCacheSize(300);
        ilikaRV.setHasFixedSize(true);




        return view;
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
            dialog.getWindow().setLayout(width, height);
        }


        getXreosimaIlika();
    }



    private void getXreosimaIlika(){

       lista.clear();

        if (Utils.isNetworkAvailable(CTX)) {
            String query = new Str_queries().getXREOSIMA_ILIKA(transgroupID);

            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = CTX;
            task.listener = new AsyncCompleteTask() {
                @Override
                public void taskComplete(JSONArray results) throws JSONException {

                    if (results != null) {

                        for (int i = 0; i < results.length(); i++) {

                            JSONObject jsonObject = results.getJSONObject(i);

                            XreosimaIlika xreosimaIlika = new XreosimaIlika();
                            xreosimaIlika.setName(jsonObject.getString("name"));
                            xreosimaIlika.setTmima(jsonObject.getString("tmima"));
                            xreosimaIlika.setPosotita(String.valueOf(jsonObject.getString("posotita")));

                            lista.add(xreosimaIlika);

                        }

                        XreosimaIlikaAdapter adapter = new XreosimaIlikaAdapter(CTX, lista, DIalogFragmentXreosimaIlika.this);
                        ilikaRV.setAdapter(adapter);
                    }
                    else
                        Toast.makeText(CTX, "Δεν βρέθηκαν χρεώσιμα υλικά", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void taskCompleteGetVardies(JSONArray results) throws JSONException {

                }
            };

            task.query = query;
            task.execute();
        }
        else {
            Toast.makeText(CTX, R.string.check_internet_access, Toast.LENGTH_SHORT).show();

        }



    }




}
