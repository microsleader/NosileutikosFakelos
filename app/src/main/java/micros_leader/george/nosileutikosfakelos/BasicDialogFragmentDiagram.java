package micros_leader.george.nosileutikosfakelos;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jjoe64.graphview.GraphView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Zotika_simeia.Zotika_Activity_Meth;
import micros_leader.george.nosileutikosfakelos.Main_menu.SigxoneusiFiladiwnActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Parakolouthisi.ParakolouthisiActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Zotika_simeia.Zotika_simeia_Activity;


public class BasicDialogFragmentDiagram extends DialogFragment  {




    private String transgroupID  , date ,katigoriaItem;
    private final int ANA_HOUR = 1;
    private final int ANA_3_HOURS = 3;
    private int kathe_poses_ores = 0;
    private Context CTX;
    private GraphView graph;
    private Spinner katigoriesSP;
    private ArrayList <String> katigoriesDiagramLista;
    private ArrayList watchLista;
    private LineChart lineChart;
    private Button resetZoomBT;
    private Activity act;
    private ArrayAdapter adapter;
    private ArrayList <Double> currentDiagramInfo;
    private ArrayList<Entry> lineEntries;
    private ArrayList<String> x_entries; //οι κατω ωρες
    private boolean isFirstTime = true;
    private JSONArray main_results;
    private int x_axis_metritis = 0;
    private int results_rows = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_diagram_dialog, container, false);
        CTX = view.getContext();

        katigoriesSP = view.findViewById(R.id.hoursSP);
        resetZoomBT = view.findViewById(R.id.resetZoomBT);

        act = getActivity();
        initializeParameters();

        //graph =  view.findViewById(R.id.graph);
        lineChart = view.findViewById(R.id.linechart);
        if (kathe_poses_ores == 1)
            watchLista = InfoSpecificLists.get24HoursLista();
        else
            watchLista = InfoSpecificLists.getWatchList_3Hours();



        resetZoomBTListener();

        spinnersAdapter();


    //   test();


    return view;

    }


    private void initializeParameters(){
        if (getArguments() != null) {

            transgroupID = getArguments().getString("transgroupID");
            date = getArguments().getString("date");
            katigoriesDiagramLista = getArguments().getStringArrayList("katigoriesLista");
            if (getArguments().containsKey("kathe_poses_ores"))
                kathe_poses_ores = getArguments().getInt("kathe_poses_ores");
        }
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


    private String getAvtivityQuery(){
        String query ="";
        if (act instanceof Zotika_Activity_Meth || act instanceof SigxoneusiFiladiwnActivity)
            katigoriaItem = getKatigoria_meth_ForTheQuery(katigoriesSP.getSelectedItem().toString());
        else
            katigoriaItem = getKatigoriaForTheQuery(katigoriesSP.getSelectedItem().toString());

        if (act instanceof ParakolouthisiActivity)
            query = Str_queries.getPARAKOLOUTHISI_DIAGRAM_INFO(transgroupID,katigoriaItem,date);

        else if (act instanceof Zotika_simeia_Activity) {
            if (kathe_poses_ores == ANA_HOUR)
                query = Str_queries.getZOTIKA_DIAGRAM_INFO_ANA_HOUR(transgroupID, katigoriaItem, date);
            else if (kathe_poses_ores == ANA_3_HOURS)
                query = Str_queries.getZOTIKA_DIAGRAM_INFO_ANA_3ORO(transgroupID, katigoriaItem, date);

        }
        else if (act instanceof Zotika_Activity_Meth || act instanceof SigxoneusiFiladiwnActivity)
            query = Str_queries.getZOTIKA_METH_DIAGRAM_INFO(transgroupID, katigoriaItem, date);

        return query;
    }


    private void getDiagramma() {



        String query = getAvtivityQuery();
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.ctx = CTX;
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {


                float f = 0.45f;

                if (results != null) {

                    currentDiagramInfo = new ArrayList<>();
                    lineEntries = new ArrayList<Entry>();
                    x_entries = new ArrayList();
                    ArrayList<Float> floatValuesLista =new ArrayList<>();
                    float hour = 0.0f;                                 // ΑΝΤΙΠΡΟΣΩΠΕΥΕΙ ΤΟΝ Χ ΑΞΟΝΑ ΜΕ ΤΙΣ ΩΡΕΣ
                                                                 // ΓΙΑ ΚΑΘΕ ΤΙΜΗ ΘΑ ΑΥΞΑΝΕΤΑΙ Η ΤΙΜΗ ΑΝΑ 3 ΩΡΕΣ

                    // ΕΑΝ ΔΕΝ ΕΧΕΙ ΣΤΑΤΟΥΣ ΔΗΛΑΔΗ ΕΑΝ ΕΧΕΙ ΑΠΟΤΕΛΕΣΜΑΤΑ ΝΑ ΦΕΡΕΙ ΝΑ ΚΑΝΕΙ ΤΟ ΑΠΟ ΚΑΤΩ

                    if (!results.getJSONObject(0).has("status")) {
                        for (int i = 0; i < results.length(); i++) {

                            main_results = results;
                            results_rows = results.length();
                            JSONObject katigoriaMetrisi = results.getJSONObject(i);

                            String metrisi = Utils.convertObjToString(katigoriaMetrisi.get(katigoriaItem));
                            int watch = 0;
                               if (act instanceof ParakolouthisiActivity)
                                   watch = getWatchNameParakolouthisi(katigoriaMetrisi.getInt("watch"));
                               else if (act instanceof Zotika_simeia_Activity || act instanceof Zotika_Activity_Meth || act instanceof SigxoneusiFiladiwnActivity) {
                                   if (katigoriaMetrisi.has("watch"))
                                       watch = getWatchNameZotika(katigoriaMetrisi.getInt("watch"));
                                   else if (katigoriaMetrisi.has("timeStr")) {
                                       String timeStr = katigoriaMetrisi.getString("timeStr");
                                       x_entries.add(timeStr);
                                       watch = Integer.parseInt(timeStr.split(":")[0]);
                                   }
                               }
                            float metr ;


                            if (!metrisi.equals("")) {

                                // ΜΟΝΟ ΟΤΑΝ ΥΠΑΡΧΕΙ ΜΕΤΡΗΣΗ ΝΑ ΠΡΟΣΘΕΙΤΕΙ ΚΑΙ ΟΧΙ ΟΤΑΝ ΒΡΕΙ ΚΕΝΟ
                                metr = (float) katigoriaMetrisi.getDouble(katigoriaItem);
                              //  lineEntries.add(new Entry(hour, metr));

                                lineEntries.add(new Entry(watch, metr));

                            }


                        }
                        createDiagram(lineEntries);

                    }
                }


            }
        };

        task.query = query;
        task.execute();


    }





    private void createDiagram(ArrayList lineEntr){



        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);


        LineDataSet  lineDataSet = new LineDataSet(lineEntr, katigoriesSP.getSelectedItem().toString());

        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setLineWidth(3);
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setCircleColor(Color.RED);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleHoleRadius(3);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(Color.BLACK);



        LineData lineData = new LineData(lineDataSet);

        //  lineChart.getXAxis().setDrawLabels(false);  //eksafanizei tis times stin panw kai katw orizontia mpara


        lineChart.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "" + (int) value; // yVal is a string array
            }
        });

       String x  = lineChart.getXAxis().getLongestLabel();
       String z  = lineChart.getXAxis().getFormattedLabel(0);

        lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                String v;
                try {
                    //ΑΥΤΟ ΕΓΙΝΕ ΕΠΕΙΔΗ ΣΤΑ ΖΩΙΤΙΚΑ ΕΙΧΑ ΤΟ ΣΠΙΝΝΕΡ ΜΕ ΤΙΣ ΒΑΡΔΙΕΣ ΚΑΙ ΤΟ ΑΛΛΑΞΑΜΕ ΚΑΙ ΒΑΛΑΜΕ ΚΑΝΟΝΙΚΗ ΩΡΑ
                    //--------------------------------------------
                    if (main_results != null && main_results.length() > 0 && main_results.getJSONObject(0).has("timeStr")) {
                        v = main_results.getJSONObject(x_axis_metritis).optString("timeStr");

                        x_axis_metritis++;
                        if (x_axis_metritis == results_rows)
                            x_axis_metritis = 0; //παει απο την αρχη

                        return v;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //----------------------------------------------------
                return  (int) value + ":00"; // yVal is a string array
            }
        });



        lineChart.getAxisRight().setEnabled(false);
        lineChart.getDescription().setText("Μέτρηση τελευταίων ωρών");
        lineChart.getDescription().setTextSize(12);
        lineChart.setDrawMarkers(true);
        //  lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        lineChart.animateY(1000);
        lineChart.getXAxis().setGranularityEnabled(true);
        lineChart.getXAxis().setGranularity(1.0f);
        //lineChart.zoomIn();
        lineChart.setData(lineData);
    }



        private void resetZoomBTListener(){
            resetZoomBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lineChart.fitScreen();
                }
            });
        }





    private void spinnersAdapter() {
                adapter = new ArrayAdapter<>(CTX,
                        R.layout.spinner_layout_for_vardies, katigoriesDiagramLista);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                katigoriesSP.setAdapter(adapter);

                katigoriesSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            getDiagramma();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }



            // ----------------- ΠΑΡΑΜΕΤΡΟΙ ΚΑΙ ΕΞΑΙΡΕΣΕΙΣ -------------------------
        private String getKatigoriaForTheQuery (String spinnerItem){

            String katigoria = "";
            switch (spinnerItem) {
                case "Θερμοκρασία":
                    katigoria = "thermokrasia";
                    break;

                case "Σφύξεις":
                    katigoria = "sfixeis";
                    break;
//
                case "Πιεση" :
                    katigoria = "piesi";
                    break;

                case "Οξυμετρία" :
                    katigoria = "oximetria";
                    break;

                case "Αναπνοές" :
                    katigoria = "anapnoes";
                    break;

                case "Πόνος" :
                    katigoria = "ponos";
                    break;

                case "Συστολική πίεση" :
                    katigoria = "sistoliki_piesi";
                    break;

                case "Διαστολική πίεση" :
                    katigoria = "diastoliki_piesi";
                    break;

                case "SPO2" :
                    katigoria = "spo2";
                    break;

                case "Stick glu" :
                    katigoria = "stick_glu";
                    break;

                case "Ούρα" :
                    katigoria = "oura";
                    break;

                case "Κλίμακα πόνου" :
                    katigoria = "ponos";
                    break;
            }



            return katigoria;
        }


        private String getKatigoria_meth_ForTheQuery(String spinnerItem){
            switch (spinnerItem) {
                case "Θερμοκρασία":
                    return  "thermokrasia";

                case "Σφύξεις":
                    return "sfiksis";

                case "Πιεση" :
                    return "piesi";

                case "Οξυμετρία" :
                    return "oximetria";

                case "Αναπνοές (per min)" :
                    return  "sinolo_anapnown";


                case "Πόνος" :
                    return "ponos";

                case "Συστολική πίεση" :
                    return "sistoliki_artiriaki_piesi";

                case "Διαστολική πίεση" :
                    return "diastoliki_artiriaki_piesi";

//                case "SPO2" :
//                    return "spo2";

                case "Stick glu" :
                    return "glu";

//                case "Ούρα" :
//                    return "oura";

                case "Κλίμακα πόνου" :
                    return "ponos";

                default:
                    return  "";
            }


        }



    public static int getWatchNameParakolouthisi (int id){

        int hour;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(1,0);map.put(2,3);map.put(3,6);
        map.put(4,9);map.put(5,12);map.put(6,15);
        map.put(7,18);map.put(8,21);

        hour = map.get(id);

        return hour;
    }

    public  int getWatchNameZotika (int id){

            if (id < 100) //ΓΙΑ ΔΙΨΗΦΙΟΣΣ
                return id;
            else {

                String str = String.valueOf(id);
                id = Integer.parseInt(str.substring(1));

                return id;
            }
    }


}