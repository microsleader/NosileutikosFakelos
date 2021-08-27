package micros_leader.george.nosileutikosfakelos.OROFOI.f_Diaitologio;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;


public class DialogFragmentUpdateDiaitologio extends DialogFragment  {


    private TextView dateTV, hoursTV;
    private EditText diaitaET, sxoliaET;
    private Spinner sitisiSinodouSP;
    private ArrayList sitisiSinodouLista;
    private CircularProgressButton updateButton;
    private View view;
    private String id,transgroupid,dateFrom,hourFrom,diaita,sxolia,sitisiSinodou;
    private String userID;
    private Context CTX;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.custom_update_diaitologio_dialog, container, false);
        CTX = view.getContext();

        id = getArguments().getString("id");
        transgroupid = getArguments().getString("transgroupID");
        dateFrom = getArguments().getString("dateFrom");
        hourFrom = getArguments().getString("hourFrom");
        diaita = getArguments().getString("diaita");
        sxolia = getArguments().getString("sxolia");
        userID = getArguments().getString("userID");

        sitisiSinodou= getArguments().getString("sitisiSinodou");

        sitisiSinodouLista = InfoSpecificLists.getSitisiSinodouNames();


        dateTV = view.findViewById(R.id.dateTV);
        hoursTV = view.findViewById(R.id.hourTV);
        diaitaET = view.findViewById(R.id.diaitaET);
        sxoliaET = view.findViewById(R.id.sxoliaET);
        sitisiSinodouSP = view.findViewById(R.id.sitisiSinodouSP);
        updateButton = view.findViewById(R.id.updateButton);

        dateTV.setText(dateFrom);
        hoursTV.setText(hourFrom);
        diaitaET.setText(diaita);
        sxoliaET.setText(sxolia);

        Utils.dateListener(view.getContext(), dateTV);
        Utils.timeListener(view.getContext(), hoursTV);

        spinnerAdapter();

        sitisiSinodouSP.setSelection(Integer.parseInt(InfoSpecificLists.getSitisiSinodouID((sitisiSinodou))));

        updateButtonListener();


        return view;
    }


    private void spinnerAdapter() {
        ArrayAdapter adapter = new ArrayAdapter<>(CTX,
                R.layout.spinner_layout2, sitisiSinodouLista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sitisiSinodouSP.setAdapter(adapter);
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


    }




    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

//        if (getActivity() != null)
//         ((DiaitologioActivity)getActivity()).getDiaitologio(transgroupid);


    }

    private void updateButtonListener(){

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (!userID.equals(Utils.getUserID(CTX))){
                    Toast.makeText(CTX, R.string.error_user_id, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Utils.isNetworkAvailable2(CTX)) {

                    String date = dateTV.getText().toString();
                    String time = hoursTV.getText().toString();



                    if (date.equals("") && !time.equals(""))
                        date = Utils.getCurrentDate();

                    else if (!date.equals("") && time.equals(""))
                        time = Utils.getCurrentTime();

                    else if (date.equals("") && time.equals("")){
                        date = "";
                        time = "";

                    }


                    String diaita = diaitaET.getText().toString().trim();
                    String sxolia = sxoliaET.getText().toString().trim();
                    String sitisiSinodou = InfoSpecificLists.getSitisiSinodouID(sitisiSinodouSP.getSelectedItem().toString());


                    updateButton.startAnimation();

                    String query = new Str_queries().getDIAITOLOGIO_UPDATE(id,date,time, diaita, sxolia,sitisiSinodou,userID);
                    AsyncTaskUpdate task = new AsyncTaskUpdate(CTX, query);
                    task.listener = new AsyncGetUpdateResult() {
                        @Override
                        public void update(String str) {
                            if (str.equals("Πετυχημένη ενημέρωση")) {
                                Utils.timeHandlerDoneButton(updateButton, CTX);

                            } else {

                                Utils.timeHandlerErrorButton(updateButton, CTX);

                            }
                        }
                    };
                    task.execute();



                }

            }
        });

    }


}
