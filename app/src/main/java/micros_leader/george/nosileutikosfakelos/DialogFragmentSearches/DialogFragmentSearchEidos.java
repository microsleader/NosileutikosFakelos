package micros_leader.george.nosileutikosfakelos.DialogFragmentSearches;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentMedicineCloseListener;
import micros_leader.george.nosileutikosfakelos.R;

public class DialogFragmentSearchEidos  extends DialogFragment {


    private EditText searchName;
    private RecyclerView apotelesmataRecyclerView;
    private ArrayList<String> eidiArraylist = new ArrayList();
    private ArrayList<String> filteredList = new ArrayList<>();
    private Toolbar toolbar;
    private DialogFragment df;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_search_dialog, container, false);
        //transItemID = getArguments().getString("transItemID");

          df = (DialogFragment) getTargetFragment();


        eidiArraylist = (ArrayList<String>)getArguments().getSerializable("eidi");

        searchName = view.findViewById(R.id.patientsTV);
        apotelesmataRecyclerView =  view.findViewById(R.id.searchRecyclerView);
        apotelesmataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        if (getContext() != null)
            apotelesmataRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        apotelesmataRecyclerView.setItemViewCacheSize(300);
        apotelesmataRecyclerView.setHasFixedSize(true);


        SearchEidosAdapter adapter =  new SearchEidosAdapter(this, eidiArraylist);
        adapter.listener = new MyDialogFragmentMedicineCloseListener() {
            @Override
            public void dialogMedicineClose(String id_name) {
                Toast.makeText(getContext(), id_name, Toast.LENGTH_SHORT).show();

                sendResult(1, id_name);


            }


        };
        apotelesmataRecyclerView.setAdapter(adapter);


        searchListener();

        return view;
    }



    private void sendResult(int REQUEST_CODE, String str) {
        Intent intent = new Intent();
        intent.putExtra("eidos", str);
        if (getTargetFragment() != null)
           getTargetFragment().onActivityResult(
                getTargetRequestCode(), REQUEST_CODE, intent);
    }

    private void filter(String text){

        filteredList =  new ArrayList();

        for (String item : eidiArraylist){
            if (item.toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }




        }

        if (getContext() != null) {

            SearchEidosAdapter customAdapter = new SearchEidosAdapter(this, filteredList);
            customAdapter.listener = new MyDialogFragmentMedicineCloseListener() {
                @Override
                public void dialogMedicineClose(String id_name) {
                    Toast.makeText(getContext(), id_name, Toast.LENGTH_SHORT).show();

                    sendResult(1, id_name);

                }
            };
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
