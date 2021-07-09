package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Nursing_katagrafi_efarmogis_metron_elegxou_loimokseon;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;
import micros_leader.george.nosileutikosfakelos.databinding.ActivityKatagrafiEfarmogisMetronElegxouLoimokseonBinding;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Katagrafi_efarmogis_metron_elegxou_loimokseon_Act extends BasicActivity {

    private ActivityKatagrafiEfarmogisMetronElegxouLoimokseonBinding bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityKatagrafiEfarmogisMetronElegxouLoimokseonBinding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);
        getPatientsList(this,bd.floorsSP,bd.patientsTV);


    }




    private void showSigkentrotika(){
        Bundle  bundle  =   tableView_sigkentrotika_dialogFragment(
                Str_queries.getKatagrafiMetron_loimoksewn(transgroupID),
                transgroupID,
                new String[]{"ID","Νοσηλευτής","Ημ/νία Έναρξης εφαρμογ.\n nμέτρων ελέγχου λοιμόξεων",
                        "ΕΝΤΕΛΛΩΝ ΙΑΤΡΟΣ","Είδος Μέτρων","ΕΙΔΟΣ ΘΕΤΙΚΗΣ ΚΑΛΛΙΕΡΓΕΙΑΣ(ΔΕΙΓΜΑ)\n'Η ΘΕΤΙΚΟΥ ΙΟΛΟΓΙΚΟΥ ΕΛΕΓΧΟΥ",
                        "ΗΜ/ΝΙΑ ΑΠΟΣΤΟΛΗΣ","ΑΠΟΤΕΛΕΣΜΑ ΚΑΛΛΙΕΡΓΕΙΑΣ\nΜΙΚΡΟΒΙΟ","ΗΜ/ΝΙΑ ΠΑΡΑΛΑΒΗΣ \nΤΕΛΙΚΟΥ ΑΠΟΤΕΛΕΣΜΑΤΟΣ",
                        "ΕΝΗΜΕΡΩΣΗ ΣΥΝΟΔΩΝ","ΑΠΟΜΟΝΩΣΗ","ΤΟΠΟΘΕΤΗΣΗ\n HOSPITAL BOX","ΛΟΥΤΡΟ ΜΕ HIBITANE","ΚΟΚΚΙΝΟ ΚΥΤΙΟ ΑΙΧΜΗΡΩΝ",
                        "ΚΥΚΛΩΜΑ ΚΛΕΙΣΤΗΣ \nΑΝΑΡΡΟΦΗΣΗΣ","ΧΡΗΣΗ ΜΑΣΚΑΣ FFP3 \n(ΕΠΙ ΑΕΡΟΓΕΝΩΝ ΠΡΟΦΥΛ.))",
                        "ΑΛΛΕΣ ΠΑΡΕΜΒΑΣΕΙΣ","ΗΜ/ΝΙΑ ΑΡΣΗΣ ΜΕΤΡΩΝ","ΕΝΤΕΛΛΩΝ ΙΑΤΡΟΣ \nΑΡΣΗΣ ΜΕΤΡΩΝ","ΗΜ/ΝΙΑ ΚΑΛΛΙΕΡΓΕΙΑΣ",
                        "ΕΡΓΑΣΤΗΡΙΑΚΟΙ ΔΕΙΚΤΕΣ","ΤΟΠΟΘΕΤΗΣΗ ΛΑΜΠΑΣ 12Ω - 24Ω","ΔΙΠΛΟ ΚΑΘΑΡΙΣΜΑ","ΚΑΘΑΡΙΣΜΑ ΤΟΙΧΩΝ",
                        "ΑΦΑΙΡΕΣΗ ΚΟΥΡΤΙΝΩΝ","ΚΑΘΑΡΙΣΜΑ  KLORKLEEN","ΑΛΛΕΣ ΠΑΡΕΜΒΑΣΕΙΣ\n ΜΕΤΑ ΤΗΝ ΕΞΟΔΟ",
                        },
                null,
                InfoSpecificLists.getKatagrafi_efarmogis_metron_elegxou_loimokseon_orofon_meth(),
                false,
                false,
                true);





        showFragment(bundle,bd.fragment1);
    }


    @Override
    public void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista) {
        super.taskCompletePlanoKlinonGetPatients(lista);
        transgroupID = Utils.getSplitSPartString(patientsTV.getText().toString(),",",3);

        showSigkentrotika();
    }





    @Override
    public void handleDialogClose(String transgroupid) {

        //GIA OTAN KLEINEI TO DIALOG NA APOTHIKEUEI TO TRANSGROUPID TOU EPILEGMENOU PATIENT
        transgroupID = Utils.getSplitSPartString(transgroupid,",",3);
        showSigkentrotika();
    }
}
