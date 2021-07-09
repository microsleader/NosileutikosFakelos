package micros_leader.george.nosileutikosfakelos.DialogFragmentSearches;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskSearchMedicine;
import micros_leader.george.nosileutikosfakelos.Interfaces.IMedLista;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon.Medicines;
import micros_leader.george.nosileutikosfakelos.R;

public class DF_SearchMedicineFromBase  extends DialogFragment implements IMedLista {


    private EditText searchName;
    private RecyclerView apotelesmataRecyclerView;
    private ArrayList<Medicines> medicinesArraylist = new ArrayList();
    private ArrayList<Medicines> filteredList = new ArrayList<>();

    private final int SECONDS_TO_EXECUTE = 1000; //ms
    private Handler handler = new Handler();
    private AsyncTaskSearchMedicine task;
    SearchMedicineAdapter adapter;
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            if (task != null && task.getStatus() == AsyncTask.Status.RUNNING)
                task.cancel(true);
            task =   new AsyncTaskSearchMedicine(DF_SearchMedicineFromBase.this,searchName.getText().toString().trim());
            task.execute();
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_search_dialog, container, false);

        medicinesArraylist = (ArrayList<Medicines>)getArguments().getSerializable("medicines");

        searchName = view.findViewById(R.id.patientsTV);
        apotelesmataRecyclerView =  view.findViewById(R.id.searchRecyclerView);
        apotelesmataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        if (getContext() != null)
            apotelesmataRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        apotelesmataRecyclerView.setItemViewCacheSize(300);
        apotelesmataRecyclerView.setHasFixedSize(true);


  if (getActivity() != null) {
            adapter = new SearchMedicineAdapter(getActivity(), medicinesArraylist, this);
            apotelesmataRecyclerView.setAdapter(adapter);
        }

        searchListener();

        return view;
    }




    private void filter(String text){

        filteredList =  new ArrayList();

        for (Medicines item : medicinesArraylist){
            if (item.getItemID().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }

            else if(item.getItemname().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

            }



        }

        if (getContext() != null) {

            SearchMedicineAdapter customAdapter = new SearchMedicineAdapter(getContext(), filteredList, this);
            customAdapter.filterList(filteredList);
            apotelesmataRecyclerView.setAdapter(customAdapter);
        }
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



}
