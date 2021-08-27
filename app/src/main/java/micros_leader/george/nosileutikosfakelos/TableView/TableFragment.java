package micros_leader.george.nosileutikosfakelos.TableView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended_str;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;


public class TableFragment extends DialogFragment implements DataSended_str {

    // ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΘΕΛΟΥΜΕ CHECKBOX ΣΕ ΚΑΠΟΙΟ COLUMN NAME ΤΟΤΕ ΠΡΕΠΕΙ ΝΑ ΓΡΑΦΤΕΙ "column_name,checkbox" MONO ETSI DOYLEYEI


    private Activity act;
    public AlertDialog alertDialog;
    private Toolbar t;
    private TableLayout ll;
    private Table tableClass;
    private ArrayList <String> extraCols = new ArrayList<>() ,extraValues = new ArrayList<>();
    private View view;
    private boolean editextsUsingDialogs;


    @Override
    public void onStart()
    {
        super.onStart();
//        Dialog dialog = getDialog();
//
//        if (dialog != null && dialog.getWindow() != null)
//        {
//            int width = ViewGroup.LayoutParams.MATCH_PARENT;
//            int height = ViewGroup.LayoutParams.MATCH_PARENT;
//            dialog.getWindow().setLayout(width, height);
//
//
//        }


    }
    public void set_editextsUsingDialogs (boolean x){
        editextsUsingDialogs = x;
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for act fragment
        view = inflater.inflate(R.layout.fragment_table, container, false);
        setRetainInstance(true);

        act = getActivity();
        if (act != null)
            alertDialog = Utils.setLoadingAlertDialog(act);
        ll =  view.findViewById(R.id.displayLinear);
        t = view.findViewById(R.id.toolbar);



        callTable();
        alertDialog.dismiss();





        return  view;

    }

    private void callTable() {

        Bundle bundle = this.getArguments();
        tableClass = new Table(ll, t,view, bundle, this);
        tableClass.addExtraColAndValue(extraCols,extraValues);
        tableClass.set_editextsUsingDialogs(editextsUsingDialogs);

    }


    public void setMedicineInfo(String id_name) {
        tableClass.setInfoFromMedicineDialogFragment(id_name);
    }

    public void setMedicineInfoWith2FRagments(String id_name) {
        if (tableClass.currentTableFR)
            tableClass.setInfoFromMedicineDialogFragment(id_name);
    }



    public void setDietesInfo(String id_name) {
        tableClass.setInfoDietesDialogFragment(id_name);
    }

    public void addColAndValue(String col , String value){
        extraCols.add(col);
        extraValues.add(value);

    }

    @Override
    public void hereIsYourStr_Data(String info) {
        setDietesInfo(info);
    }
}

