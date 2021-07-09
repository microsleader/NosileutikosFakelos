package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.fragments;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGet_All_Columns_names;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities.Nephroxenia_Main_Activity;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdate_JSON;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGet_All_Column_Names;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicalInstructionsFragment extends Fragment {


    private TextView monthDateTV;
    private TextView doctorTV;
    private RecyclerView instructionsRV;
    public  String id , patientid , companyID ;
    private ArrayList<String> nameJson, valuesJson;
    private CircularProgressButton updateButton;
    private ArrayAdapter<String> dataAdapter ;
    private View view;
    private DrawerLayout sirtari;
    private String currentSpinnerPatient , code, transgroupID,doctorID,month, yearid , year;
    private BasicRV adapter;
    private Context CTX;
    private Nephroxenia_Main_Activity main;
    private Calendar myCalendar;
    private boolean weHaveData;
    private ArrayList<JSONObject> jsonLista;
    private  ArrayList<ItemsRV> listaAdaptor;


    public MedicalInstructionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_medical_instructions, container, false);


        CTX = view.getContext();  //KALITERA AUTO NA XRISIMOPOIETAI STA ASYNCTASK
        main = (Nephroxenia_Main_Activity)getActivity();

        main.alertDialog.show();
        currentSpinnerPatient = main.search_TV.getText().toString();
        myCalendar = Calendar.getInstance();
        initializeElements();
        Bundle bundle = this.getArguments();
        transgroupID = bundle.getString("transgroupID");
        patientid = bundle.getString("patientID");
        companyID = bundle.getString("companyID");
      //  updateButtonListener();

        nameJson = new ArrayList<>();
        valuesJson = new ArrayList<>();

        instructionsRV.setItemViewCacheSize(300);
        instructionsRV.setHasFixedSize(true);
        instructionsRV.setNestedScrollingEnabled(false);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//
//                boolean isHeader = isHeader(position,theseisTitloi);
//                if (isHeader)
//                    return  2;
//                else
//                    return  1;
//
//            }
//        });
        instructionsRV.setLayoutManager(manager);

        adapter = new BasicRV(getActivity(), InfoSpecificLists.getMedicalInsLista());
        instructionsRV.setAdapter(adapter);
        listaAdaptor = adapter.result;






        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        getallColumns();
       // drawerListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        String newSpinnerItem = main.search_TV.getText().toString();
        if (!currentSpinnerPatient.equals(newSpinnerItem)) {
            id = null;
            code = Utils.getfirstPartSplitCommaString(newSpinnerItem);
            transgroupID =  main.getTransgroupIDUsingCode.get(code);
            patientid = main.getPatientIDUsingCode.get(code);
            clearListaAdaptor(listaAdaptor);
            getallColumns();
            //    getTeleutaiesMetriseis();

        }
        else{
            code = Utils.getfirstPartSplitCommaString(currentSpinnerPatient);
            transgroupID =  main.getTransgroupIDUsingCode.get(code);
            patientid = main.getPatientIDUsingCode.get(code);
            main.alertDialog.dismiss();
            getallColumns();
            //   getTeleutaiesMetriseis();

        }


    }

    private void initializeElements() {
        monthDateTV = view.findViewById(R.id.monthDateTV);

//        monthDateTV.setText(Utils.getCurrentMonthYear());
//        monthDateTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Utils.createDialogWithoutDateField(getActivity().getSupportFragmentManager(),monthDateTV);
//            }
//        });

        doctorTV = view.findViewById(R.id.doctorTV);
        instructionsRV = view.findViewById(R.id.instructionsRV);
        updateButton = view.findViewById(R.id.updateButton);
    }


    private void getallColumns(){
        AsyncTaskGet_All_Columns_names task = new AsyncTaskGet_All_Columns_names();
        task.ctx = CTX;
        task.listener = new AsyncGet_All_Column_Names() {
            @Override
            public void taskGet_all_col_names(ArrayList<String> col_names) {
                for (int i=0; i< col_names.size(); i++)
                    nameJson.add(col_names.get(i));

                if (nameJson != null && !nameJson.isEmpty())
                    getData();
            }

        };
        task.query = Str_queries.getOLA_TA_PEDIA_PINAKA("Nursing_Medical_Instructions");
        task.execute();
    }


    private void getData() {

        main.alertDialog.show();

        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.ctx = CTX;
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {

                if (results != null) {
                    if (!results.getJSONObject(0).has("status")) {

                        weHaveData = true;
                        nameJson = new ArrayList<>();
                        valuesJson = new ArrayList<>();

                        JSONObject jsonObject = results.getJSONObject(0);


                        Iterator<String> keys = jsonObject.keys();
                        while (keys.hasNext()) {

                            String str_Name = keys.next();
                            String value = jsonObject.optString(str_Name);

                            if (str_Name.equals("ID"))
                                id = value;
                            if (str_Name.equals("DoctorID"))
                                doctorID = value;

                            if (str_Name.equals("doctorName"))
                                doctorTV.setText(value);
                            if (str_Name.equals("yearName"))
                                year = value;
                            if (str_Name.equals("Month"))
                                month = value;

                            if (str_Name.equals("embolio_b"))
                                value = Utils.convertMillisecondsToDateTime(value);
                            if (str_Name.equals("embolio_antiFlu"))
                                value = Utils.convertMillisecondsToDateTime(value);





                            nameJson.add(str_Name);
                            valuesJson.add(value);
                        }
                        valuesJson = Utils.deleteProtesTheseis(valuesJson, 5); // ΔΙΑΓΡΑΦΗ ΚΑΠΟΙΩΝ ΠΡΩΤΩΝ ΤΙΜΩΝ ΑΠΟ ΤΗ ΛΙΣΤΑ ΠΟΥ ΚΑΤΕΒΑΣΑΜΕ
                        nameJson.remove(nameJson.size()-1);       //ΔΙΑΓΡΑΦΗ ΤΟΥ DOCTORNAME ΔΕΝ ΜΑΣ ΧΡΕΙΑΖΕΤΑΙ
                        valuesJson.remove(valuesJson.size()-1);
                        Utils.setValuesTolistaAdaptor(null, listaAdaptor, valuesJson);
                        monthDateTV.setText(month + " / " + year);

                    }
                    else{
                        monthDateTV.setText("");
                        doctorTV.setText("");
                    }

                }

                else{
                    weHaveData = false;
                    clearListaAdaptor(listaAdaptor);
                    monthDateTV.setText("");
                    doctorTV.setText("");

                }


                adapter.notifyDataSetChanged();
                main.alertDialog.dismiss();
            }
        };
        task.query = "select top 1 n.* , dbo.NAMEDOCTOR(n.doctorid) as doctorName, y.name as yearName " +
                " from Nursing_Medical_Instructions n " +
                " inner join years y on y.id = n.year " +
                " where n.patientID = " + patientid +
                " order by n.year desc, n.month desc ";
        task.execute();
    }







    private void updateButtonListener(){


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (transgroupID!=null && !transgroupID.equals("0")) {

                    AsyncTaskUpdate_JSON task;

                    main.alertDialog.show();
                    updateButton.startAnimation();

                    Utils.setValuesTo_valuesJSON(null, listaAdaptor, valuesJson);

                    if (weHaveData)
                        task = new AsyncTaskUpdate_JSON(CTX, id, transgroupID, "Nursing_Medical_Instructions",
                                nameJson, Utils.replaceTrueOrFalse(valuesJson), null);
                    else {
                        task = new AsyncTaskUpdate_JSON(CTX, transgroupID, "Nursing_Medical_Instructions",
                                nameJson, Utils.replaceTrueOrFalse(valuesJson), null);

                        task.period = Utils.convertDateTomilliseconds("01/" + monthDateTV.getText().toString());

                    }

                    task.patientID = patientid;
                    task.doctorID = doctorID;
                    task.names_col = new String[]{"ID", "TransGroupID", "PatientID", "DoctorID", "Period"};

                    task.listener = new AsyncGetUpdate_JSON() {
                        @Override
                        public void update_JSON(String str) {
                            if (str.equals(getString(R.string.successful_update))) {
                                weHaveData = true; // ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΞΑΝΑΠΑΤΗΣΕΙ ΤΗΝ ΙΔΙΑ ΣΤΙΓΜΗ ΤΟ ΚΟΥΜΠΙ ΓΙΑ ΝΑ ΜΗΝ ΚΑΝΕΙ ΠΑΛΙ ΙΝΣΕΡΤ

                                Utils.timeHandlerDoneButton(updateButton, CTX);
                                getData();
                            } else {
                                Utils.timeHandlerErrorButton(updateButton, CTX);
                                Toast.makeText(CTX, str, Toast.LENGTH_SHORT).show();
                            }

                            main.alertDialog.dismiss();
                        }

                        @Override
                        public void getIDofInsert(String id) {

                        }
                    };
                    task.execute();

                }
                else
                    Toast.makeText(CTX, "Δεν μπορείτε να κάνετε ενημέρωση χωρίς ασθενή", Toast.LENGTH_SHORT).show();

            }
        });
    }




    private void drawerListener() {
        final Nephroxenia_Main_Activity main = (Nephroxenia_Main_Activity)getActivity();
        main.drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        main.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(main.getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {




                //  Toast.makeText(main, newSpinnerItem, Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }


    private void clearListaAdaptor(ArrayList<ItemsRV> list){
        for (int x = 0; x<list.size(); x++) {
            if(list.get(x).getValue() == null)
                list.get(x).setTrue(false);
            else
                list.get(x).setValue("");
        }
        weHaveData = false;
    }



}
