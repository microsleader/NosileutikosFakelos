package micros_leader.george.nosileutikosfakelos.DialogFragmentSearches;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.R;


public class DialogFragmentSearchPatientNosileuomenos extends DialogFragment {


    private EditText searchName;
    private RecyclerView apotelesmataRecyclerView;
    private ArrayList<PatientsOfTheDay> patientsArraylist = new ArrayList();
    private ArrayList<PatientsOfTheDay> filteredList = new ArrayList<>();
    private Toolbar toolbar;
    private Context ctx;
    private Activity activityFromSigxoneusi;


    public DialogFragmentSearchPatientNosileuomenos(){

    }
    public DialogFragmentSearchPatientNosileuomenos(Activity activityFromSigxoneusi){
        this.activityFromSigxoneusi = activityFromSigxoneusi;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_search_dialog, container, false);
        //transItemID = getArguments().getString("transItemID");

        ctx = getActivity();

        patientsArraylist = (ArrayList<PatientsOfTheDay>)getArguments().getSerializable("patients");

        searchName = view.findViewById(R.id.patientsTV);
        apotelesmataRecyclerView =  view.findViewById(R.id.searchRecyclerView);
        apotelesmataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        apotelesmataRecyclerView.addItemDecoration(new DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL));
        apotelesmataRecyclerView.setItemViewCacheSize(32);
        apotelesmataRecyclerView.setHasFixedSize(true);


        SearchPatientNosilAdapter adapter;
        if (activityFromSigxoneusi == null)
            adapter =  new SearchPatientNosilAdapter(getContext(), patientsArraylist, this);
        else
            adapter =  new SearchPatientNosilAdapter(getContext(), activityFromSigxoneusi, patientsArraylist, this);
        apotelesmataRecyclerView.setAdapter(adapter);


        searchListener();

        return view;
    }




    private void filter(String text){

         filteredList =  new ArrayList();

        for (PatientsOfTheDay item : patientsArraylist){

            if (item.getBed().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }

            else if (item.getFirstName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }

            else if(item.getLastName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

            }

            else if (String.valueOf(item.getTransgroupID()).toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

            }

        }


        SearchPatientNosilAdapter customAdapter =  new SearchPatientNosilAdapter(getContext(),filteredList,this);
        customAdapter.filterList(filteredList);
        apotelesmataRecyclerView.setAdapter(customAdapter);
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

                filter(editable.toString());
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
    }






}
