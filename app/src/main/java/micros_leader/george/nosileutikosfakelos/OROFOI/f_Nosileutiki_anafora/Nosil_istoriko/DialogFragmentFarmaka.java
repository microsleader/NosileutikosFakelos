package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_istoriko;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class DialogFragmentFarmaka extends DialogFragment {



    private RecyclerView farmakaRV;
    private String transgroupID = "";
    private Activity act;
    private Context CTX;
    private ArrayList <Farmaka> lista = new ArrayList<>();
    private CircularProgressButton updateButton;
    private EditText eidosET, dosiET ,paratiriseisET;
    private TextView dateTV , hourTV;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_farmaka_layout, container, false);
        CTX = view.getContext();
        if (getArguments() != null)
        transgroupID = getArguments().getString("transgroupID");
        act = getActivity();
        eidosET = view.findViewById(R.id.eidosET);
        dosiET = view.findViewById(R.id.dosiET);
        paratiriseisET = view.findViewById(R.id.paratiriseisET);
        updateButton = view.findViewById(R.id.updateButton);
        dateTV = view.findViewById(R.id.dateTV);
        dateListener();
        hourTV = view.findViewById(R.id.hourTV);
        hourListener();
        farmakaRV =  view.findViewById(R.id.farmakaRV);

        farmakaRV.setLayoutManager(new LinearLayoutManager(act, LinearLayoutManager.VERTICAL, false));
        farmakaRV.addItemDecoration(new DividerItemDecoration(act, LinearLayout.VERTICAL));
        farmakaRV.setItemViewCacheSize(300);
        farmakaRV.setHasFixedSize(true);


        updateButtonListener();




        return view;
    }


    private void dateListener() {


        final Calendar myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date12 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                dateTV.setText(sdf.format(myCalendar.getTime()));
                //   updateLabel(textView);




            }

        };

        dateTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(act, date12, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




    }


    private void hourListener(){
        hourTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(act, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hourTV.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.show();

            }
        });
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


        getFarmaka();
    }



    private void getFarmaka(){

        lista.clear();

        if (Utils.isNetworkAvailable(CTX)) {
            String query = new Str_queries().getFARMAKA_POU_XRISIMOPOIOUNTAI_PERSON(transgroupID);

            AsyncTaskGetJSON task = new AsyncTaskGetJSON();
            task.ctx = CTX;
            task.listener = new AsyncCompleteTask() {
                @Override
                public void taskComplete(JSONArray results) throws JSONException {

                    if (results != null) {

                        for (int i = 0; i < results.length(); i++) {

                            JSONObject curFarmako = results.getJSONObject(i);

                            Farmaka frm = new Farmaka();

                            frm.setID(String.valueOf(curFarmako.getInt("id")));
                            frm.setEidos(curFarmako.getString("eidos"));
                            frm.setDosi(curFarmako.getString("dosi"));
                            frm.setTeleutaia_lipsi(curFarmako.getString("teleftea_lipsi"));
                            frm.setParatiriseis(curFarmako.getString("paratiriseis"));

                            lista.add(frm);


                        }

                        FarmakaAdapter adapter = new FarmakaAdapter(act, lista);
                        farmakaRV.setAdapter(adapter);
                    }
                    else
                        Toast.makeText(CTX, "Δεν βρέθηκαν φάρμακα", Toast.LENGTH_SHORT).show();
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



    private void updateButtonListener() {
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) CTX.getSystemService(INPUT_METHOD_SERVICE);
                if (getActivity() != null)
                     if (getActivity().getCurrentFocus() != null)
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

                String eidos = Utils.checkNull(eidosET.getText().toString().trim());
                String dosi = Utils.checkNull(dosiET.getText().toString().trim());
                String teleufaia_lipsi  =  dateTV.getText().toString() + " " + hourTV.getText().toString();
                String paratiriseis = paratiriseisET.getText().toString();

                if (eidos ==null || eidos.equals(""))
                    Toast.makeText(CTX, "Συμπληρώστε είδος φαρμάκου", Toast.LENGTH_SHORT).show();

               else {

                    if (Utils.isNetworkAvailable2(CTX)) {

                        updateButton.startAnimation();


                        String query = Str_queries.getFARMAKA_POU_XRISIMOPOIOUNTAI_INSERT(Utils.getUserID(act),transgroupID,
                                eidos, dosi, teleufaia_lipsi , paratiriseis);

                        AsyncTaskUpdate task = new AsyncTaskUpdate(CTX, query);
                        task.listener = new AsyncGetUpdateResult() {
                            @Override
                            public void update(String str) {

                                if (str.equals(getString(R.string.successful_update))) {

                                    Utils.timeHandlerDoneButton(updateButton, CTX);

                                    eidosET.setText("");

                                    dosiET.setText("");

                                    getFarmaka();
                                } else
                                    Utils.timeHandlerErrorButton(updateButton, CTX);

                            }
                        };
                        task.execute();
                    }
                }
            }
        });

    }






}
