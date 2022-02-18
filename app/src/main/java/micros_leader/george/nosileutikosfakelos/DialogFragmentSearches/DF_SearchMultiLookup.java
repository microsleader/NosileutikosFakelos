package micros_leader.george.nosileutikosfakelos.DialogFragmentSearches;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringJoiner;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskSearchMedicine;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.Interfaces.IMedLista;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentMedicineCloseListener;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon.Medicines;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;

public class DF_SearchMultiLookup extends DialogFragment implements IMedLista , MyDialogFragmentMedicineCloseListener {


    private EditText searchName;
    private TextView chosenMedsTV,basicRV_TV;
    private ListView listView;
    private final Activity act;
    ArrayAdapter<String> adapterListView;
    private RecyclerView apotelesmataRecyclerView;
    private final ArrayList<Medicines> medicinesArraylist = new ArrayList();
    private ArrayList<String> savedMeds = new ArrayList<>();
    private String[] idsNames;
    private final int SECONDS_TO_EXECUTE = 1000; //ms
    private final Handler handler = new Handler();
    private AsyncTaskSearchMedicine task;
    ItemsRV itemsRV;
    TableViewItem tableViewItem;
    SearchMedicineAdapter adapter;
    HashMap<Integer, ArrayList<String>> valuesMap; ArrayList<String> valuesLista;
    int indexOfColumn;
    int positionRow;


    private final Runnable r = new Runnable() {
        @Override
        public void run() {
            if (task != null && task.getStatus() == AsyncTask.Status.RUNNING)
                task.cancel(true);
            task =   new AsyncTaskSearchMedicine(DF_SearchMultiLookup.this,searchName.getText().toString().trim());
            task.execute();
        }
    };

    public DF_SearchMultiLookup(Activity act, TextView basicRV_TV, ItemsRV itemsRV, String[] idsNames) {
        this.act = act;
        this.basicRV_TV = basicRV_TV;
        this.itemsRV = itemsRV;
        this.idsNames = idsNames;
    }



    public DF_SearchMultiLookup(Activity act, TextView tv, HashMap<Integer, ArrayList<String>> valuesMap, ArrayList<String> valuesLista, int indexOfColumn, int positionRow, String[] idsNames) {
        this.act = act;
        this.basicRV_TV = tv;
        this.valuesMap = valuesMap;
        this.valuesLista = valuesLista;
        this.idsNames = idsNames;
        this.indexOfColumn = indexOfColumn;
        this.positionRow = positionRow;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_search_multitype_lookup_dialog, container, false);
        searchName = view.findViewById(R.id.patientsTV);
        chosenMedsTV = view.findViewById(R.id.chosenMedsTV);
        listView = view.findViewById(R.id.savedItemslistview);
        adapterListView = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, savedMeds);
        listView.setAdapter(adapterListView);


        apotelesmataRecyclerView =  view.findViewById(R.id.searchRecyclerView);
        apotelesmataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        if (act != null)
            apotelesmataRecyclerView.addItemDecoration(new DividerItemDecoration(act, DividerItemDecoration.VERTICAL));
        apotelesmataRecyclerView.setItemViewCacheSize(20);
        apotelesmataRecyclerView.setHasFixedSize(true);

        if (getActivity() != null) {
            adapter = new SearchMedicineAdapter( medicinesArraylist, this);
            apotelesmataRecyclerView.setAdapter(adapter);
        }

        searchListener();
        initializeAndSetlistviewListener();



        return view;
    }





    private void searchListener(){

        searchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                handler.removeCallbacks(r);
                handler.postDelayed(r, SECONDS_TO_EXECUTE);
            }
        });
    }




    private void initializeAndSetlistviewListener(){

        if (idsNames != null && idsNames.length > 0){
            savedMeds.addAll(Arrays.asList(idsNames));
            chosenMedsTV.setVisibility(View.VISIBLE);
        }
        else
            chosenMedsTV.setVisibility(View.GONE);


        listView.setOnItemClickListener((parent, view, position, id) -> {
            savedMeds.remove(position);
            adapterListView.notifyDataSetChanged();

            if (savedMeds.isEmpty())
                chosenMedsTV.setVisibility(View.GONE);
        });


    }


    @Override
    public void getMedList(ArrayList<Medicines> list) {
        if (list != null && !list.isEmpty()) {
            adapter.result = list;
            adapter.notifyDataSetChanged();
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
            if (dialog.getWindow() != null)
                dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void dialogMedicineClose(String id_name) {
        if (!savedMeds.contains(id_name)) {
            savedMeds.add(id_name);
            adapterListView.notifyDataSetChanged();
            if (!savedMeds.isEmpty())
                chosenMedsTV.setVisibility(View.VISIBLE);
        }
    }







    @Override
    public void onDestroy() {

        StringJoiner ids = new StringJoiner(",");
        StringJoiner idsNames = new StringJoiner("\n");
        for (String med : savedMeds) {
            if (!med.trim().isEmpty()) {
                ids.add(med.split(",")[0].trim());
                idsNames.add(med.trim());
            }
        }



        basicRV_TV.setText(idsNames.toString());

        if (itemsRV != null)
            itemsRV.setValue(ids.toString().replace(",","\ufffd"));
        else{
//            tableViewItem.setValue(ids.toString().replace(",","\ufffd"));
            valuesLista.set(indexOfColumn, ids.toString().replace(",","\ufffd"));
            valuesMap.put(positionRow, valuesLista);
        }


        //ΑΝ ΕΙΝΑΙ ΠΑΝΩ ΑΠΟ 3 ΦΑΡΜΑΚΑ ΝΑ ΦΕΡΝΕΙ ΜΟΝΟ ΤΑ IDS , ΤΟ ΚΑΝΩ ΑΥΤΟ ΓΙΑ ΤΟΝ ΧΩΡΟ ΤΟΥ TEXTVIEW
//        if (savedMeds.size() > 3)
//            basicRV_TV.setText(ids.toString());
//        else
//            basicRV_TV.setText(idsNames.toString());

        super.onDestroy();
    }
}
