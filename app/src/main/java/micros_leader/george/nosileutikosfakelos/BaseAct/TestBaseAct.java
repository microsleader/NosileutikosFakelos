package micros_leader.george.nosileutikosfakelos.BaseAct;

import androidx.appcompat.app.AppCompatActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityProgrammatismosBinding;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityTestBaseBinding;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestBaseAct extends AppCompatActivity {

    private ActivityTestBaseBinding bd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        bd = ActivityTestBaseBinding.inflate(getLayoutInflater());
//        View view = bd.getRoot();
//        setContentView(view);

        long st ,f ;
        st = System.currentTimeMillis();

        String[] presidents = { "Dwight D. Eisenhower", "John F. Kennedy",
                "Lyndon B. Johnson", "Richard Nixon", "Gerald Ford",
                "Jimmy Carter", "Ronald Reagan", "George H. W. Bush",
                "Bill Clinton", "George W. Bush", "Barack Obama",
                "Dwight D. Eisenhower", "John F. Kennedy", "Lyndon B. Johnson",
                "Richard Nixon", "Gerald Ford", "Jimmy Carter",
                "Ronald Reagan", "George H. W. Bush", "Bill Clinton",
                "George W. Bush", "Barack Obama", "Dwight D. Eisenhower",
                "John F. Kennedy", "Lyndon B. Johnson", "Richard Nixon",
                "Gerald Ford", "Jimmy Carter", "Ronald Reagan",
                "George H. W. Bush", "Bill Clinton", "George W. Bush",
                "Barack Obama" ,"Dwight D. Eisenhower", "John F. Kennedy",
                "Lyndon B. Johnson", "Richard Nixon", "Gerald Ford",
                "Jimmy Carter", "Ronald Reagan", "George H. W. Bush",
                "Bill Clinton", "George W. Bush", "Barack Obama",
                "Dwight D. Eisenhower", "John F. Kennedy", "Lyndon B. Johnson",
                "Richard Nixon", "Gerald Ford", "Jimmy Carter",
                "Ronald Reagan", "George H. W. Bush", "Bill Clinton",
                "George W. Bush", "Barack Obama", "Dwight D. Eisenhower",
                "John F. Kennedy", "Lyndon B. Johnson", "Richard Nixon",
                "Gerald Ford", "Jimmy Carter", "Ronald Reagan",
                "George H. W. Bush", "Bill Clinton", "George W. Bush",
                "Barack Obama" ,"Dwight D. Eisenhower", "John F. Kennedy",
                "Lyndon B. Johnson", "Richard Nixon", "Gerald Ford",
                "Jimmy Carter", "Ronald Reagan", "George H. W. Bush",
                "Bill Clinton", "George W. Bush", "Barack Obama",
                "Dwight D. Eisenhower", "John F. Kennedy", "Lyndon B. Johnson",
                "Richard Nixon", "Gerald Ford", "Jimmy Carter",
                "Ronald Reagan", "George H. W. Bush", "Bill Clinton",
                "George W. Bush", "Barack Obama", "Dwight D. Eisenhower",
                "John F. Kennedy", "Lyndon B. Johnson", "Richard Nixon",
                "Gerald Ford", "Jimmy Carter", "Ronald Reagan",
                "George H. W. Bush", "Bill Clinton", "George W. Bush",
                "Barack Obama" ,"Dwight D. Eisenhower", "John F. Kennedy",
                "Lyndon B. Johnson", "Richard Nixon", "Gerald Ford",
                "Jimmy Carter", "Ronald Reagan", "George H. W. Bush",
                "Bill Clinton", "George W. Bush", "Barack Obama",
                "Dwight D. Eisenhower", "John F. Kennedy", "Lyndon B. Johnson",
                "Richard Nixon", "Gerald Ford", "Jimmy Carter",
                "Ronald Reagan", };

        ScrollView scrollView = new ScrollView(this);

        TableLayout tableLayout = new TableLayout(this);

        for (int i = 0; i < presidents.length; i++) {

            TableRow tableRow = new TableRow(this);

            for (int x = 0; x < 22; x++) {

                TableRow.LayoutParams lp = new TableRow.LayoutParams(320,120);
//                lp.setMargins(10,10,10,10);
//
//                TextView tv = new TextView(this);
//                tv.setLayoutParams(lp);
//                tv.setTextSize(18);
//                tv.setPadding(10, 10, 10, 10);
//                tv.setGravity(Gravity.CENTER);
//                tv.setBackgroundResource(R.drawable.table_row_cell);
//
//                // tv.setElegantTextHeight(true);
//                tv.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
//                tv.setMaxEms(22);
//                tv.setSingleLine(false);
//                tv.setText(presidents[i]);


                EditText tv = new EditText(this);

                tv.setPadding(10, 10, 10, 10);
                tv.setGravity(Gravity.CENTER);

               // tv.setTextSize(18);
               // tv.setBackgroundResource(R.drawable.table_row_cell);
                tv.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
               // tv.setMaxEms(22);
             //   tv.setSingleLine(false);

                tv.setText(presidents[i]);





                tableRow.addView(tv);

            }
            tableLayout.addView(tableRow);
            View v = new View(this);
            v.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, 1));
            v.setBackgroundColor(Color.rgb(51, 51, 51));
            tableLayout.addView(v);


        }

        scrollView.addView(tableLayout);

        setContentView(scrollView);

        Log.e("eleoooos",String.valueOf(System.currentTimeMillis() - st));


        //palio  = 5421
        //neo =  878


    }




//    @Override
//    protected int getLayoutResourceId() {
//        return R.layout.activity_test_base;
//    }
//
//    @Override
//    protected int rv_ID() {
//        return R.id.snelRV;
//    }
//
//
//    @Override
//    protected String setTable() {
//        return "nursing_snel";
//    }
//
//    @Override
//    protected int[] positionTitlesInRV() {
//        return new int[]{0, 14};
//    }
//
//    @Override
//    protected boolean isThereImageUpdateButton() {
//        return true;
//    }
//
//    @Override
//    protected String[] colNames_not_in_RV() {
//        return new String[0];
//    }
//
//    @Override
//    protected String setMainQuery() {
//        return Str_queries.getSNEL_PERSON(transgroupID);
//    }
//
//
//
//
//
//    @Override
//    protected void runAfterPost(JSONArray results) throws JSONException {
//        newList = getCopyList();
//
//        if (weHaveData){
//
//            JSONObject jsonObject =  results.getJSONObject(0);
//            //vardiaID = Utils.convertObjToString(jsonObject.get("vardiaID"));
//
//            setValuesTolistaAdaptor(titloi_positions,newList);     // ΤΟΠΟΘΕΤΗΣΗ ΤΙΜΩΝ ΣΤΗ ΛΙΣΤΑ ΑΝΤΑΠΤΟΡΑ
//        }
//
//        adapter.updateLista(newList);
//    }
//
//    @Override
//    protected ArrayList <ItemsRV>  set_RV_list() {
//        return  InfoSpecificLists.getSNEL_LISTA();
//    }
//
//
//

}