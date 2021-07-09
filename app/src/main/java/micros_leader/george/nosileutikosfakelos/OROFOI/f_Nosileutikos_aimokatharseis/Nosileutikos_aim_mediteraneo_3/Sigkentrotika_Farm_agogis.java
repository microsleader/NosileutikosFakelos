package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3;

import android.os.Bundle;
import android.view.View;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentMedicineCloseListener;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.TableView.TableFragment;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.customers.Frontis;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityTable2FragmentsBinding;

public class Sigkentrotika_Farm_agogis extends BasicActivity implements MyDialogFragmentMedicineCloseListener {

    private Bundle bundle1, bundle2;
    private String patientID, transgroupID;
    private ActivityTable2FragmentsBinding bd;
    private TableFragment tf1 ;
    private TableFragment tf2 ;
    private int customerID ;
    private boolean isDoctor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityTable2FragmentsBinding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);

        patientID = getIntent().getStringExtra("patientID");
        //transgroupID = getIntent().getStringExtra("transgroupID");
        customerID = Utils.getCustomerID(this);
        isDoctor = Utils.getIsDoctor(this);

        monimi_farm_agogi();
        sinedrias_farm_agogi();

        showFragments();
        alertDialog.dismiss();




    }

    private void monimi_farm_agogi (){
        bundle1  =    tableView_sigkentrotika_dialogFragment( Str_queries.getFarm_agogi(customerID,patientID , true),
                null,  //ΤΟ ΚΑΝΩ NULL ΕΠΕΙΔΗ Ο ΠΙΑΝΑΚΣ ΠΟΥ ΘΑ ΚΑΤΑΧΩΡΩ ΔΕΝ ΕΧΕΙ TRANSGROUPID ΑΛΛΑ ΕΧΕΙ patientID

                null,
                Customers.isFrontis(customerID) ? Frontis.getFarm_agogi_lista(false ,true) : InfoSpecificLists.getFarm_agogi_lista(true),
                false,
                false,
                 isDoctor );

        bundle1.putString("toolbar_title","Μόνιμη φαρμακευτική αγωγή");
        if (isDoctor)
            bundle1.putString("patientID",patientID);
    }



    private void sinedrias_farm_agogi (){
        bundle2  =    tableView_sigkentrotika_dialogFragment( Str_queries.getFarm_agogi(customerID,patientID , false),
                null,  //ΤΟ ΚΑΝΩ NULL ΕΠΕΙΔΗ Ο ΠΙΑΝΑΚΣ ΠΟΥ ΘΑ ΚΑΤΑΧΩΡΩ ΔΕΝ ΕΧΕΙ TRANSGROUPID ΑΛΛΑ ΕΧΕΙ patientID

                null,
                Customers.isFrontis(customerID) ?  Frontis.getFarm_agogi_lista(true,false) : InfoSpecificLists.getFarm_agogi_lista(false),
                false,
                false,
                true);


        bundle2.putString("toolbar_title","Φαρμακευτική αγωγή συνεδρίας");
        bundle2.putString("patientID",patientID);
        //bundle2.putBoolean("setOnlyFirstRowAvalaible",true);
      //  bundle2.putBoolean("hasValuesForCH",true);
    }

    private void showFragments(){

        tf1 = new TableFragment();
        tf2 = new TableFragment();

        tf1.setArguments(bundle1);
        tf2.setArguments(bundle2);


        getSupportFragmentManager().beginTransaction()
                .add(bd.fragment1.getId(), tf1)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(bd.fragment2.getId(), tf2)
                .commit();

    }

    @Override
    public void dialogMedicineClose(String id_name) {
            tf1.setMedicineInfoWith2FRagments(id_name);
            tf2.setMedicineInfoWith2FRagments(id_name);

    }
}
