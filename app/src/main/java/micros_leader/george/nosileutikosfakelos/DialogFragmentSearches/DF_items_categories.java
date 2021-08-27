package micros_leader.george.nosileutikosfakelos.DialogFragmentSearches;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import androidx.recyclerview.widget.RecyclerView;

import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended_str;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Diaitologio.Diaitologio;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.TableView.Table;
import micros_leader.george.nosileutikosfakelos.TableView.TableFragment;

public class DF_items_categories   extends DialogFragment implements AsyncCompleteTask2 {

    private Toolbar toolbar;
    private ExpandableListView rv;
    private Activity act;
    private String lookupTable;
    private DataSended_str dataSendListener ;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<Diaitologio>> listDataChild;
    public static final String IDS = "ids";
    private String listIDs;
    DialogFragment df;

    public DF_items_categories(DialogFragment df){
        this.df = df;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_df_items_categories, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        expListView = view.findViewById(R.id.rv);
        act = getActivity();
        dataSendListener = (DataSended_str) df;
        toolbar.setTitle("Επιλογή διαίτων");

        Bundle bundle = getArguments();
        if (bundle != null){
            listIDs = bundle.getString(IDS);
        }



        //listviewListener();
        getData();

        return view;
    }


    private void listviewListener(){
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(act, listDataHeader.get(groupPosition) + " Expanded", Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(act, listDataHeader.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(act, listDataHeader.get(groupPosition) + " : " +
                        listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }




    private void getData() {


        String query = "select * from nursing_diets order by diet_categoryID" ;
        AsyncTaskGetJSON2 t = new AsyncTaskGetJSON2(query, act);
        t.listener = this;


        t.execute();
    }


    @Override
    public void taskComplete2(JSONArray results) throws JSONException {

        if (results != null){
            listDataHeader = new ArrayList<>();
            listDataChild = new HashMap<>();
            List<Diaitologio> lista = new ArrayList<>();

            boolean isSameCategory = false;
            for (int i=0; i<results.length(); i++){
                Diaitologio dieta = new Diaitologio();
                JSONObject s = results.getJSONObject(i);
                int  categoryID = s.getInt("diet_categoryID");

                //ΚΟΙΤΑΖΩ ΑΝ ΕΙΝΑΙ ΙΔΙΑ ΜΕ ΤΗΝ ΠΡΟΗΓΟΥΜΕΝΗ ΚΑΤΗΓΟΡΙΑ
                // ΑΝ ΕΙΝΑΙ ΔΗΜΙΟΥΡΓΙΑ ΚΑΙΝΟΥΡΙΑΣ ΛΙΣΤΑΣ
                // ΑΥΤΟ ΓΙΝΕΤΑΙ ΕΠΕΙΔΗ ΣΤΟ listDataChild ΠΡΕΠΕΙ ΝΑ ΜΠΟΥΝ ΟΣΕς ΚΑΤΗΓΟΡΙΕς ΜΑΖΙ ΜΕ ΤΟ ΠΕΡΙΕΧΟΕΜΝΟ ΤΟΥΣ
                // ΔΙΑΦΟΕΤΙΚΑ ΤΑ ΒΑΖΕΙ ΟΛΑ ΠΑΝΤΟΥ

                isSameCategory = i > 0 && categoryID == results.getJSONObject(i - 1).getInt("diet_categoryID");

                if (!isSameCategory)
                    lista = new ArrayList<Diaitologio>();


                String category = "";
                if (categoryID == 1)
                    category = "ΒΑΣΙΚΕΣ ΔΙΑΙΤΕΣ";
                else if (categoryID == 2)
                    category = "ΣΥΜΠΛΗΡΩΜΑΤΙΚΕΣ ΔΙΑΙΤΕΣ";
                else if (categoryID == 3)
                    category = "ΕΙΔΙΚΕΣ ΔΙΑΙΤΕΣ";

                if (!listDataHeader.contains(category))
                     listDataHeader.add(category);

                dieta.setId(s.getString("ID"));
                dieta.setName(s.getString("Name"));
                dieta.setPeriektikotita(s.getString("periektikotita"));
                dieta.setXrisi_odigies(s.getString("xrisi_odigies"));
                dieta.setCategory(category);

                lista.add(dieta);
                String [] arrayIds = listIDs.split(",");
                for (String id : arrayIds) {
                    if (id.equals(dieta.getId())) {
                        dieta.setSelected(true);
                        break;
                    }
                }


                if (!isSameCategory)
                    listDataChild.put(category,lista);

            }
            if (listDataChild.isEmpty())
                return;
//
            if (listAdapter == null) {
                listAdapter = new ExpandableListAdapter(act, listDataHeader, listDataChild);
                expListView.setAdapter(listAdapter);
            }
            else{
                listAdapter._listDataChild = listDataChild;
                listAdapter.notifyDataSetChanged();
            }
        }
    }





    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (dialog.getWindow() != null)
                dialog.getWindow().setLayout(width, height);
        }
    }






    public static class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final Context _context;
        private final List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, List<Diaitologio>> _listDataChild;

        public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<Diaitologio>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @SuppressLint({"InflateParams", "SetTextI18n"})
        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            final Diaitologio childText = (Diaitologio) getChild(groupPosition, childPosition);


            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.custom_df_items_categories_child_rv, null);
            }


            TextView diaitaTV = (TextView) convertView.findViewById(R.id.info1TV);
            TextView periektikotitaTV = (TextView) convertView.findViewById(R.id.info2TV);
            TextView xrisi_odigiesTV = (TextView) convertView.findViewById(R.id.info3TV);

            diaitaTV.setText(childText.getName().replace("\n", " ").trim());

            CheckBox selectedCH = convertView.findViewById(R.id.selectedCH);
            selectedCH.setChecked(childText.isSelected());

            if (childText.getPeriektikotita() != null && !childText.getPeriektikotita().isEmpty() )
                periektikotitaTV.setText("ΠΕΡΙΕΚΤΙΚΟΤΗΤΑ: \n" + childText.getPeriektikotita());

            if (childText.getXrisi_odigies() != null && !childText.getXrisi_odigies().isEmpty())
                xrisi_odigiesTV.setText("ΠΙΘΑΝΗ ΧΡΗΣΗ: \n" + childText.getXrisi_odigies());

            selectedCH.setOnClickListener(view -> childText.setSelected(selectedCH.isChecked()));


            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.custom_df_items_categories_parent_rv, null);
            }


            TextView lblListHeader = (TextView) convertView.findViewById(R.id.titleTV);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }


    @Override
    public void onDestroy() {
        StringJoiner sj = new StringJoiner(",");
        for (Map.Entry<String, List<Diaitologio>> entry :  listAdapter._listDataChild.entrySet()) {
            List<Diaitologio>  diaita = entry.getValue();
            for (Diaitologio s : diaita)
                if (s.isSelected())
                    sj.add(s.getId());
        }

        dataSendListener.hereIsYourStr_Data(sj.toString());
        super.onDestroy();
    }
}


