package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.dialogFragmentsAndAdaptors;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.activities.Nephroxenia_Main_Activity;
import micros_leader.george.nosileutikosfakelos.R;


public class DialogFragmentSearchPat extends DialogFragment {


    private EditText searchName;
    private RecyclerView apotelesmataRecyclerView;
    private ArrayList<PatientsOfTheDay> patientsArraylist = new ArrayList();
    private ArrayList<PatientsOfTheDay> filteredList = new ArrayList<>();
    private Nephroxenia_Main_Activity act ;
    private Activity activity;


    private String name;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_search_dialog, container, false);
        //transItemID = getArguments().getString("transItemID");

       // toolbar = view.findViewById(R.id.toolbar);
        //toolbar.setTitle(item);

        patientsArraylist = (ArrayList<PatientsOfTheDay>)getArguments().getSerializable("patients");
        activity = getActivity();

        searchName = view.findViewById(R.id.patientsTV);
        apotelesmataRecyclerView =  view.findViewById(R.id.searchRecyclerView);
        apotelesmataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        apotelesmataRecyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        apotelesmataRecyclerView.setItemViewCacheSize(30);
        apotelesmataRecyclerView.setHasFixedSize(true);

        SearchAdapter adapter =  new SearchAdapter(getContext(), patientsArraylist, this);
        apotelesmataRecyclerView.setAdapter(adapter);


        if (activity instanceof Nephroxenia_Main_Activity) {
            act = (Nephroxenia_Main_Activity) getActivity();
            if (act != null)
                name = act.search_TV.getText().toString();
        }


        searchListener();

        return view;
    }




    private void filter(String text){

         filteredList =  new ArrayList();

        for (PatientsOfTheDay item : patientsArraylist){
            if (item.getFirstName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }

            else if(item.getLastName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

            }

            else if (item.getCode().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

            }

            else if (item.getAmka() != null && item.getAmka().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

            }

        }


        SearchAdapter customAdapter =  new SearchAdapter(getContext(),filteredList,this);
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
            dialog.getWindow().setLayout(width, height);
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (patientsArraylist != null && !patientsArraylist.isEmpty()) {

            if (act != null) {
                if (!name.equals(act.search_TV.getText().toString()))
                act.getDefaultFragment();
            }

        }



    }
}
