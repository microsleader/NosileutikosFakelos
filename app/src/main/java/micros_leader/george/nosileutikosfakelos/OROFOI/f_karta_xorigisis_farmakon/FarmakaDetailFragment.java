package micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon;

import android.app.Activity;
import android.content.DialogInterface;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.Utils.getColorTextBlue;
import static micros_leader.george.nosileutikosfakelos.Utils.getColorTextGreen;
import static micros_leader.george.nosileutikosfakelos.Utils.getColorTextRed;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link FarmakaListActivity}
 * in two-pane mode (on tablets) or a {@link FarmakaDetailActivity}
 * on handsets.
 */
public class FarmakaDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";


    private String id;
    private String transgroupid ;
    private String dateStart  ;
    private String datestop ;
    private String itemID ;
    private String category ;
    private String ml_hour ;
    private String dosi ;
    private String odos_xorigisis;
    private String dosologia ;
    private String xorigisiID, ores_xorigisis;
    private boolean isXorigithike;
    private String itemname ;
    private ArrayList <XorigisiOres> xorigisiLista = new ArrayList<>();
    private FarmakaListActivity act;
    public View rootView;
    private String dateTextView;

    /*
     Mandatory empty constructor for the fragment manager to instantiate the
      fragment (e.g. upon screen orientation changes).
     */
    public FarmakaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        act = (FarmakaListActivity) getActivity();
        if (getArguments() != null){
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            // mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                //   appBarLayout.setTitle(mItem.content);
            }

            id = getArguments().getString("id");
            transgroupid = getArguments().getString("transGroupID");
            dateStart = getArguments().getString("dateStart");
            datestop = getArguments().getString("datestop");
            itemID = getArguments().getString("itemID");
            category = InfoSpecificLists.getKARTA_FARMAKON_KATIGORIA_NAME(getArguments().getString("category"));
            ml_hour = getArguments().getString("ml_hour");
            dosi = getArguments().getString("dosi");
            odos_xorigisis = getArguments().getString("odos_xorigisis");
            dosologia = getArguments().getString("dosologia");
            //ores_xorigisis = getArguments().getString("ores_xorigisis");
            itemname = getArguments().getString("itemname");
            dateTextView = getArguments().getString("dateTextView");




          }

        }
    }


    private void getOresXorigisis() {
        act.alertDialog.show();
        AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
        task.ctx = getActivity();
        task.query = "select * ,dbo.timetostr(hour) as hourSTR from Nursing_ores_xorigisis where xorigisiID = " + id +
                        " and date =  dbo.timeToNum(CONVERT(datetime, '" + dateTextView.replace("-","/") + "' , 103))";
        task.listener = new AsyncCompleteTask2() {
            @Override
            public void taskComplete2(JSONArray results) throws JSONException {

                if (results != null) {
                    if (!results.getJSONObject(0).has("status")) {

                        xorigisiLista.clear();

                        for (int i = 0; i < results.length(); i++) {

                            JSONObject curXorigisi = results.getJSONObject(i);

                            XorigisiOres xorigisi = new XorigisiOres();
                            xorigisi.setId(Utils.convertObjToString(curXorigisi.get("ID")));
                            xorigisi.setXorigisiID(Utils.convertObjToString(curXorigisi.get("xorigisiID")));
                            xorigisi.setHour(Utils.convertObjToString(curXorigisi.get("hourSTR")));
                            String xor = Utils.convertObjToString(curXorigisi.get("xorigithike"));
                            xorigisi.setXorigithike(!xor.equals(""));
                            xorigisiLista.add(xorigisi);

                        }
                    }
                }

                // ΕΔΩ ΑΡΧΙΖΕΙ ΚΑΙ ΦΤΙΑΧΝΕΙ ΤΑ ΔΕΔΟΜΕΝΑ ΑΦΟΥ ΕΧΕΙ ΚΑΤΕΒΑΣΕΙ ΤΙΣ ΩΡΕΣ ΚΑΙ ΑΝ ΕΧΕΙ ΧΟΡΗΓΗΘΕΙ
                // ΤΑ ΑΛΛΑ ΔΕΔΟΜΕΝΑ ΠΑΙΡΝΟΝΤΑΙ ΑΠΟ ΤΟ ACTIVITY


                setInfo();



                act.alertDialog.dismiss();
            }
        };

        task.execute();
    }


    private void setInfo(){
        if (getArguments() != null) {
            if (xorigisiLista != null && !xorigisiLista.isEmpty()) {

                textviewListener();

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < xorigisiLista.size(); i++) {
                    boolean xorigithike = xorigisiLista.get(i).isXorigithike();
                    String conHour = xorigisiLista.get(i).getHour();
                    if (xorigithike)
                        sb.append(getColorTextGreen(conHour) + ", ");
                    else
                        sb.append(getColorTextBlue(conHour) + ", ");
                }

                ores_xorigisis = sb.toString().substring(0, sb.toString().length() - 2);
            }
            ores_xorigisis = ores_xorigisis == null ?"":ores_xorigisis;
            ((TextView) rootView.findViewById(R.id.item_detail))


                    .setText(Html.fromHtml(getColorTextRed("Ημ/ έναρξης: ") + " " + getColorTextBlue(dateStart) +
                                      getColorTextRed("Ημ/νία διακοπής: ") + getColorTextBlue(datestop) +
                                    // getColorTextRed("ID: ") + " " + getColorTextBlue(id) +
                                    getColorTextRed("Φάρμακo: ") + " " + getColorTextBlue(itemname) +
                                    getColorTextRed("Κατηγορία: ") + " " + getColorTextBlue(category) +
                                    getColorTextRed("ml/ώρα: ") + getColorTextBlue(ml_hour) +
                                    getColorTextRed("Δόση: ") + getColorTextBlue(dosi) +
                                    getColorTextRed("Δοσολογία: ") + getColorTextBlue(dosologia) +
                                    getColorTextRed("Οδός χορήγησης: ") + getColorTextBlue(odos_xorigisis) +
                                    getColorTextRed("Ώρες χορήγησης: ") + ores_xorigisis

                            )
                    );

                                       act.listaOresFromFragment =  xorigisiLista;
                               // setOres_xorigisisFromFragment();

        }
    }


    private void setOres_xorigisisFromFragment(){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<xorigisiLista.size(); i++){
            sb.append(xorigisiLista.get(i).getHour() + " , ");
        }
        if (!xorigisiLista.isEmpty())
             act.oresXorigisisFromFragment = sb.toString().substring(0,sb.length() -2);
        else
            act.oresXorigisisFromFragment = "";
    }

    private void textviewListener(){

        ArrayList <Boolean> ITEMS_VAL_LISTA  = new ArrayList<>();
        ArrayList <CharSequence> ITEMS_LISTA = new ArrayList<>();



        for ( int i=0; i<xorigisiLista.size(); i ++){

            ITEMS_VAL_LISTA.add(xorigisiLista.get(i).isXorigithike());
            ITEMS_LISTA.add(xorigisiLista.get(i).getHour());

        }

        final boolean[] ITEMS_VALUES = new boolean[ITEMS_VAL_LISTA.size()];
        int index = 0;
        for (Boolean object : ITEMS_VAL_LISTA) {
            ITEMS_VALUES[index++] = object;
        }
        final CharSequence[] ITEMS = ITEMS_LISTA.toArray(new CharSequence[ITEMS_LISTA.size()]);


        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(
                        act);

                builder.setMultiChoiceItems(ITEMS, ITEMS_VALUES,
                        new DialogInterface.OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                if (isChecked) {
                                    ITEMS_VALUES[which] = true;
                                    xorigisiLista.get(which).setXorigithike(true);
                                } else {
                                    ITEMS_VALUES[which] = false;
                                    xorigisiLista.get(which).setXorigithike(false);

                                }
                            }
                        });

                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                act.alertDialog.show();


                                StringBuilder sb = new StringBuilder();
                                for (int i=0; i<xorigisiLista.size(); i++) {
                                    String xor = xorigisiLista.get(i).isXorigithike() ? "1" : null;
                                    sb.append(" update Nursing_ores_xorigisis " +
                                            " set xorigithike = " + xor +
                                            " where id = " + xorigisiLista.get(i).getId() + " ");
                                }


                                AsyncTaskUpdate task = new AsyncTaskUpdate(act, sb.toString());
                                // task.ctx = getApplicationContext();
                                task.listener = new AsyncGetUpdateResult() {
                                    @Override
                                    public void update(String str) {


                                        act.alertDialog.dismiss();
                                        Toast.makeText(act, str, Toast.LENGTH_SHORT).show();

                                        getOresXorigisis();

                                    }
                                };

                                task.execute();

                            }
                        });

                builder.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });


                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.item_detail, container, false);

        if (id != null)
            getOresXorigisis();




        return rootView;
    }





}
