package micros_leader.george.nosileutikosfakelos.OROFOI.f_programmatismos_p_a;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityProgrammatismosBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Programmatismos_p_a_Activity extends BasicActivity {

    private ActivityProgrammatismosBinding bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityProgrammatismosBinding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);

        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);

        floorSPListener();
        show_checklist_ana_klini();
        show_checklist_gia_olous();


    }


    private void floorSPListener(){

        bd.floorsSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                floorID = floorsMap.get( bd.floorsSP.getSelectedItem().toString());
               // createInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }


    private void show_checklist_ana_klini(){

        bd.anaKliniBT.setOnClickListener(view -> {



            Bundle  bundle  =   tableView_sigkentrotika_dialogFragment(
                     Str_queries.getProgrammatismos_p_a(Utils.getCurrentDate(),String.valueOf(floorID), Utils.getcompanyID(this)),
                    null,
                    null,
                     InfoSpecificLists.getSigkentrotika_Programmatismos_p_a(),
                    false,
                    false,
                    true);

            bundle.putString("table", "Nursing_programmatismos_p_a");
            showFragment(bundle,bd.fragment1);









        });

    }




    private void show_checklist_gia_olous(){

        bd.giaOlousBT.setOnClickListener(view -> {

        Bundle  bundle  =   tableView_sigkentrotika_dialogFragment(
                Str_queries.getProgrammatismos_p_a_checklist() ,
                null,
                new String[]{"ID","Ημέρα","Χρήστης","Βάρδια", "xaxa",
                        "ΕΛΕΓΧΟΣ ΚΕΝΩΣΕΩΝ","ΑΔΕΙΑΣΜΑ ΟΥΡΩΝ","ΕΠΙΒΕΒΑΙΩΣΗ ΟΥΡΗΣΗΣ","ΕΛΕΓΧΟΣ ΠΑΡΟΧΕΥΤΕΣΕΩΝ",
                        "ΕΛΕΓΧΟΣ ΠΑΓΟΘΕΡΑΠΕΙΑΣ","ΕΛΕΓΧΟΣ ΟΞΥΓΟΝΟΥ","ΕΛΕΓΧΟΣ ΠΕΡΙΦ.\nΦΛΕΒ ΓΡΑΜΜΗΣ","ΕΛΕΓΧΟΣ ΟΡΟΥ",
                        "ΕΛΕΓΧΟΣ ΚΕΝΤΡ.\nΦΛΕΒ ΓΡΑΜΜΗΣ","ΕΛΕΓΧΟΣ ΑΡΤ.\nΦΛΕΒ ΓΡΑΜΜΗΣ","ΕΛΕΓΧΟΣ ΕΠΙΣΚΛΗΡΙΔΙΟΥ\nΚΑΘΕΤΗΡΑ","ΕΛΕΓΧΟΣ ΤΟΠ. ΚΑΘΑΡΙΟΤ\n ΑΛΛΑΓΗ ΠΑΝΑΣ",
                        "ΕΛΕΓΧΟΣ ΚΑΘΑΡΙΟΤΗΤΑΣ\n ΣΩΜΑΤΟΣ","ΕΛΕΓΧΟΣ ΚΑΤΑΚΛΙΣΕΩΝ","ΑΛΛΑΓΗ ΘΕΣΗ\nΣΩΜΑΤΟΣ","ΕΠΙΒΕΒΑΙΩΣΗ ΣΙΤΙΤΣΗΣ",
                        "ΘΕΣΗ ΥΠΝΟΥ","ΠΡΟΣΒΑΣΙΜΟ ΚΟΥΔΟΥΝΙ","ΗΣΥΧΙΑ ΘΑΛΑΜΟΥ","ΤΟΥΛ. 1 ΜΑΞΙΛΑΡΙ/ΚΛΙΝΗ",
                        "ΕΛΕΓΧΟΣ ΛΕΥΧΗΜΑΤΩΝ ΑΣΘΕΝΩΝ","ΕΛΕΓΧΟΣ ΚΑΘΑΡ.\nΚΛΙΝΩΝ & ΚΟΜΟΔΙΝΩΝ","ΕΛΕΓΧΟΣ ΚΟΥΡΤΙΝΩΝ\nΘΑΛΑΜΟΥ & ΜΠΑΝΙΟΥ","ΕΛΕΓΧΟΣ ΝΤΟΥΛΑΠΙΩΝ\nΘΑΛΑΜΟΥ & ΜΠΑΝΙΟΥ",
                        "ΔΙΑΘΕΣΙΜ. ΜΑΞΙΛΑΡΙΩΝ ,\nΣΤΑΤΩ, ΑΝΤΛ. ΦΑΡΜΑΚΩΝ, ΚΑΓΚΕΛΑ ΠΡΟΣΤΑΣ.,\n ΦΟΡΕΙΟΥ,ΒΟΗΘ.ΤΡΑΠΕΖΙΔ.,\n ΚΑΡΕΚΛΑΣ ΜΕΤΑΦ.",
                        "ΤΑΚΤΟΠΟΙΗΣΗ ΣΤΗ \n ΜΙΚΡΗ ΑΠΟΘΗΚΗ",
                        "ΤΑΚΤΟΠΟΙΗΣΗ ΣΤΟ \n ΚΑΜΑΡΑΚΙ ΚΑΘΑΡΙΟΤΗΤΑΣ","ΤΑΚΤΟΠΟΙΗΣΗ ΣΤΟ \n ΔΩΜΑΤΙΟ ΙΜΑΤΙΣΜΟΥ","ΕΛΕΓΧΟΣ ΑΠΟΘΕΜΑΤΟ ΙΜΑΤΙΣΜΟΥ:\n ΕΦΟΔΙΑΣΜΟΣ"},
                null,
                InfoSpecificLists.getSigkentrotika_Programmatismos_p_a_checklist(),
                false,
                false,
                true);



            //bundle.putString("toolbar_title","Συγκεντρωτικά φαρμάκων");
        showFragment(bundle,bd.fragment1);
        });

    }
}
