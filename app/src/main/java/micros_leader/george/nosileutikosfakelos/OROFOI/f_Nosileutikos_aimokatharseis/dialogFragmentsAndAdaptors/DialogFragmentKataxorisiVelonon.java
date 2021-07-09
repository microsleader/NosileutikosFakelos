package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.fragments.StableMeasurementsFragment;

import static micros_leader.george.nosileutikosfakelos.Str_queries.getUPDATE_POSOTITA_VELONON;
import static micros_leader.george.nosileutikosfakelos.Utils.timeHandlerDoneButton;
import static micros_leader.george.nosileutikosfakelos.Utils.timeHandlerErrorButton;

public class DialogFragmentKataxorisiVelonon extends DialogFragment {

    private View view;
    private String id,transgroupID;
    private Context CTX;
    private EditText posVelonas15,posVelonas16,posVelonas17;
    private CircularProgressButton updateButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.custom_kataxoriseis_velonon, container, false);

        id = getArguments().getString("id");

        CTX = getActivity();
        posVelonas15 = view.findViewById(R.id.posotita15ET);
        posVelonas16 = view.findViewById(R.id.posotita16ET);
        posVelonas17 = view.findViewById(R.id.posotita17ET);
        updateButton = view.findViewById(R.id.updateButton);
        updateButtonListener();



        getCurrentVelones();

        return view;

    }

    private void updateButtonListener() {
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.isNetworkAvailable2(CTX)) {

                    updateButton.startAnimation();

                    AsyncTaskUpdate task = new AsyncTaskUpdate(getActivity(), getUPDATE_POSOTITA_VELONON(id,
                            Utils.checkNull(posVelonas15.getText().toString()),
                            Utils.checkNull(posVelonas16.getText().toString()),
                            Utils.checkNull(posVelonas17.getText().toString())));

                    task.listener = new AsyncGetUpdateResult() {
                        @Override
                        public void update(String str) {
                            if (str.equals("Πετυχημένη ενημέρωση"))
                                timeHandlerDoneButton(updateButton,CTX);

                            else
                                timeHandlerErrorButton(updateButton,CTX);

                        }
                    };

                    task.execute();

                }
            }
        });
    }


    private void getCurrentVelones() {

        if (Utils.isNetworkAvailable2(CTX)) {

            AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
            task.ctx = getActivity().getApplicationContext();
            task.listener = new AsyncCompleteTask2() {
                @Override
                public void taskComplete2(JSONArray results) throws JSONException {
                    if (results != null) {
                        JSONObject jsonObject = results.getJSONObject(0);
                        posVelonas15.setText(Utils.convertObjToString(jsonObject.get("posotita_vel15")));
                        posVelonas16.setText(Utils.convertObjToString(jsonObject.get("posotita_vel16")));
                        posVelonas17.setText(Utils.convertObjToString(jsonObject.get("posotita_vel17")));

                    }

                }
            };
            task.query = new Str_queries().getCURRENT_POSOTITA_VELONON(id);
            task.execute();
        }

    }


    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();

        if (dialog.getWindow() != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }


    }




    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);


        if (getTargetFragment() instanceof StableMeasurementsFragment) {
            StableMeasurementsFragment frag = (StableMeasurementsFragment) getTargetFragment();
            frag.getTeleutaiesMetriseis();

        }

//            else if(getTargetFragment() instanceof StatheresFragment){
//                StatheresFragment frag = (StatheresFragment)getTargetFragment();
//                frag.getOldTeleutaiesMetriseis();
//
//        }


    }
}
