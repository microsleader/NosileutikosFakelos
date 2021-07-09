package micros_leader.george.nosileutikosfakelos.EKSOTERIKA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetPlanoKlinonPatients;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Enimerotiko_simeioma_eksetasis_activity extends BasicActivity {


    private BasicRV adapterRV;
    private Button sigkentrotikaBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enimerotiko_simeioma_eksetasis);


        toolbar = findViewById(R.id.toolbar);
        sigkentrotikaBT = findViewById(R.id.sigkentrotikaBT);
        extendedActivity = this;
        adapterRV = new BasicRV(this, InfoSpecificLists.getEnimerotikoSimeiomaEksetasisEksoterika(),titloi_positions);

        listaAdaptor = adapterRV.result;

        table = "Nursing_enimerotiko_simeioma_eksetasis";


        getAll_col_names(InfoSpecificLists.getEnimerotikoSimeiomaEksetasisEksoterika());
        setRecyclerViewgridrLayaout(R.id.simeiomaRV,  adapterRV, 2,titloi_positions );

        //getPatientsList(this,R.id.patientsTV, null);
        patientsTV = findViewById(R.id.patientsTV);
        AsyncTaskGetPlanoKlinonPatients task = new AsyncTaskGetPlanoKlinonPatients(this, patientsTV, 0);
        task.query = Str_queries.getEKSOTERIKA_PATIENTS();
        task.execute();

        thereIsImageUpdateButton();

        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID"});

        sigkentrotikaListsner();
    }

    private void sigkentrotikaListsner() {

        sigkentrotikaBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              String   query = Str_queries.getEnimerotiko_Simeioma_sigkentrotika(transgroupID);

              Intent in = tableView_sigkentrotika(query,
                        transgroupID,
                        new String[]{"ID","UserID","ΗΜ/ΝΙΑ ΚΑΙ ΩΡΑ","ΑΙΤΙΑ ΠΡΟΣΕΛΕΥΣΗΣ",
                                "ΑTOMIKO AΝΑΜΝΗΣΤΙΚΟ","ΦΑΡΜΑΚΑ ΠΟΥ ΛΑΜΒΑΝΕΙ","ΚΛΙΝΙΚΑ ΕΥΡΗΜΑΤΑ","ΕΡΓΑΣΤΗΡΙΑΚΑ ΑΠΟΤΕΛΕΣΜΑΤΑ",
                                "ΔΙΑΓΝΩΣΗ","ΘΕΡΑΠΕΙΑ ΠΟΥ ΕΦΑΡΜΟΣΤΗΚΕ", "ΟΔΗΓΙΕΣ & ΘΕΡΑΠΕΙΑ ΠΟΥ ΘΑ ΣΥΝΕΧΙΣΘΕΙ\n ΜΕΤΑ ΤΗΝ ΕΞΟΔΟ ΤΟΥ ΑΣΘΕΝΗ"
                        },
                        null,
                        InfoSpecificLists.getEnimerotiko_Simeioma_eksoterika(),
                        false,
                        false,
                        true);
               // in.putExtra("saveTransgroupID" ,false);
                in.putExtra("transgroupID",transgroupID);
               // in.putExtra("date",Utils.getCurrentDate());
                in.putExtra("needDoctor",true);

                startActivity(in);

            }
        });
    }




    @Override
    public void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor) {
        super.setValuesTo_valuesJSON(titles_positions, listAdaptor);

        nameJson.add("date");
        valuesJson.add(Utils.convertDateTomilliseconds(Utils.getCurrentDate()));

        nameJson.add("doctorID");
        valuesJson.add(Utils.getLinkDoctorID(Enimerotiko_simeioma_eksetasis_activity.this));

    }


    @Override
    public void handleDialogClose(String transgroupid) {
        super.handleDialogClose(transgroupid);

        adapterRV.updateLista(InfoSpecificLists.getEnimerotikoSimeiomaEksetasisEksoterika());
    }
}
