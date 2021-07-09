package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Zotika_simeia;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.getZotika24oroAnaOraLista_Meth;

public class Zotika_Activity_Meth extends BasicActivity {


    private BasicRV adapterRV;
    private TextView timeTV;
    private boolean egineKataxorisi = false;
    private Button neaEggrafiBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zotika___meth);

        extendedActivity = this;
        neaEggrafiBT = findViewById(R.id.neaEggrafiBT);
        neaEgrafiListener();
        table = "Nursing_Zotika_Simeia_Meth";
        timeTV = findViewById(R.id.timeTV);
        timeListener();

        getAll_col_names( getZotika24oroAnaOraLista_Meth());
        fab = findViewById(R.id.fab);
        fabListener();
        titloi_positions = new int[]{17};
        adapterRV = new ZotikaRV_adapter(this, getZotika24oroAnaOraLista_Meth(),titloi_positions);
        listaAdaptor = adapterRV.result;



        setRecyclerViewgridrLayaout(R.id.zotikaMethRV ,adapterRV,2,titloi_positions);

        getPatientsList(this,R.id.patientsTV, R.id.floorsSP);
        thereIsDatePicker(R.id.dateTV);
        date = dateTV.getText().toString();


        thereIsImageUpdateButton();
        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID","Date"});
    }

    private void fabListener() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query =  Str_queries.getZotikaMeth(transgroupID, dateTV.getText().toString());

                Intent in = tableView_sigkentrotika(query,
                        transgroupID,
//                        new String[]{"ID","UserID","ΗΜ/ΝΙΑ / ΩΡΑ","Νοσηλευτής","V Συστολική Αρτ.Πίεση",
//                                "X  Μέση Αρτ.Πίεση","Λ Διαστ. Αρτ. Πίεση","Σφύξεις","Καρδ.Ρυθμός-ΒΗΜΑΤΟΔΟΤΗΣ\n(συχνότητα)",
//                                "ΘΕΡΜΟΚΡΑΣΙΑ","ΠΟΝΟΣ","ΙΑΒΡ-Σφυγμός Άκρου","ACT","CVP", "PCW",
//                                "PAS-PAD","ScvO2-CO/CI","ICP-CPP",
//                                //"Κόρες(μέγεθος/αντίδραση)",
//                                "Δεξια κόρη (μεγεθος mm)" ,"Δεξια κόρη\nσυμβολο", "Αριστερή κόρη (μεγεθος mm)","Αριστερή κόρη\nσυμβολο",
//                                "Τρόπος αερισμού","Άλλος τρόπος\nαερισμού","Μείγμα Οξυγόνου","Αναπνοές Αναπνευστήρα","Σύνολο αναπνοών",
//                                "Εισπνεόμενος όγκος(ντ)","PEEP","Pr.Control","Pr.Support",
//                                "ΟΞΥΜΕΤΡΊΑ(Ο2 Sat)","Peak Pressure-τύπος αναπνοής","ΘΕΣΗ ΚΡΕΒΑΤΙΟΥ","ΘΕΣΗ ΣΩΜΑΤΟΣ","ΑΝΑΡΡΟΦΗΣΗ",
//                                "ΕΝΔΟΦΛΕΒΙΑ ΡΥΘΜΙΣΗ ΣΑΚΧΑΡΟΥ" ,"ΥΠΟΔΟΡΙΑ ΡΥΘΜΙΣΗ ΣΑΚΧΑΡΟΥ" , "Ινσουλίνη" , "GLU","IABP-MODE"
//
//                        },
                        null,
                         InfoSpecificLists.getSigkentrotikaZotikaMeth(),
                        false,
                        false,
                        true);


                startActivity(in);
            }
        });


    }


    private void neaEgrafiListener(){

        neaEggrafiBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weHaveData = false;
                id = "";
                clearListaAdaptor(listaAdaptor);
                adapterRV.notifyDataSetChanged();

                Toast.makeText(extendedActivity, "Μπορείτε να κάνετε μία νέα εγγραφη", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void timeListener(){
        timeTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Zotika_Activity_Meth.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeTV.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.show();

            }
        });
    }




    @Override
    public void update_JSON(String str) {
        super.update_JSON(str);

        if (str.equals(getString(R.string.successful_update))) {
            id = eggrafiID;
            weHaveData = true;
        }
        else {
            id = "";
            weHaveData = false;
        }
    }



    @Override
    public void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor) {
        super.setValuesTo_valuesJSON(titles_positions, listAdaptor);

        date = dateTV.getText().toString() + " " + timeTV.getText().toString();

    }







    class ZotikaRV_adapter extends BasicRV {


        public ZotikaRV_adapter(Activity act, ArrayList<ItemsRV> result, int[] titloi_positions) {
            super(act, result, titloi_positions);
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);

//            if (result.get(position).getTitleID().contains("κόρη")){
//                holder.titleTV.setTypeface(null, Typeface.BOLD);
//                holder.titleTV.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Utils.showImageDialog(Zotika_Activity_Meth.this,R.drawable.megethos_koris);
//
//
//
//                    }
//                });
//            }

        }
    }


}
