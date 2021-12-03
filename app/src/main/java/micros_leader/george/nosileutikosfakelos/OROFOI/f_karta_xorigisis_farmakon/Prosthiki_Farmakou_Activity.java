package micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.BasicRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentMedicineCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Prosthiki_Farmakou_Activity extends BasicActivity implements MyDialogFragmentMedicineCloseListener {

    private RecyclerView recyclerView;
    private BasicRV adapterRV;
    private ArrayList <ItemsRV> infoLista;
    private TextView oresXorigisisTV;
    private static Medicines med;
    private Toolbar  toolbar;
    private String date_oron_farmakou;

    private  boolean[] ITEMS_HOURS_VALUES = { false, false, false, false, false,false, false, false, false, false,
            false, false, false, false, false,false, false, false, false, false,false, false, false, false};

    private  CharSequence[] ITEMS_HOURS = { "00:00", "01:00","02:00", "03:00","04:00", "05:00","06:00", "07:00",
            "08:00", "09:00","10:00", "11:00","12:00", "13:00", "14:00", "15:00","16:00", "17:00","18:00", "19:00","20:00", "21:00",
            "22:00", "23:00"};

     private String  query_ores_xorigisis, oresXorigisis, xorigisiID;
    public ArrayList <XorigisiOres> listaOresFromFragment;
    private ArrayList <String> iparxousesOres = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prosthiki__farmakou_);


        table = "Nursing_xorigisi_farmakon";
        med = new Medicines();
        extendedAct = this;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        oresXorigisisTV = findViewById(R.id.oresXorigisisTV);
       // oresXorigisisTV.setText(getIntent().getStringExtra("ores"));
        date_oron_farmakou = getIntent().getStringExtra("date_oron_farmakou");
        infoLista = InfoSpecificLists.getKARTA_XORGISIS_FARMAKON_LISTA();


        adapterRV = new BasicRV(this,infoLista);
        setRecyclerViewgridrLayaout(recyclerView,R.id.infoRV,adapterRV,2,null);

        listaAdaptor = adapterRV.result;

        thereIsUpdateButton(R.id.updateButton);
        insertOrUpdateListener(listaAdaptor,new String [] {"ID","TransGroupID"});

        getIntentInfo();

        setTextViewListenerAlertDialogMultipleChoice(oresXorigisisTV,ITEMS_HOURS,ITEMS_HOURS_VALUES);


        alertDialog.dismiss();
    }







    private void getIntentInfo() {

        if(getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
            xorigisiID = id;
            weHaveData = true;
            toolbar.setTitle("Ενημέρωση φαρμάκου");

        }

        if(getIntent().hasExtra("transgroupID"))
            transgroupID = getIntent().getStringExtra("transgroupID");


        listaAdaptor.get(0).setValue(getIntent().getStringExtra("medicine"));
        listaAdaptor.get(1).setValue(getIntent().getStringExtra("datestart"));
       // listaAdaptor.get(2).setValue(getIntent().getStringExtra("date"));
        listaAdaptor.get(2).setValue(getIntent().getStringExtra("category"));
        listaAdaptor.get(3).setValue(getIntent().getStringExtra("ml/h"));
        listaAdaptor.get(4).setValue(getIntent().getStringExtra("dosi"));
        listaAdaptor.get(5).setValue(getIntent().getStringExtra("odos"));
        listaAdaptor.get(6).setValue(getIntent().getStringExtra("dosologia"));

        listaOresFromFragment = (ArrayList<XorigisiOres>) getIntent().getExtras().getSerializable("ores");
        if (listaOresFromFragment != null && !listaOresFromFragment.isEmpty()){

            StringBuilder sb = new StringBuilder();
            for (int i=0; i<listaOresFromFragment.size(); i++) {
                iparxousesOres.add(listaOresFromFragment.get(i).getHour());
                sb.append(listaOresFromFragment.get(i).getHour() + " , ");
            }

                oresXorigisis = sb.toString().substring(0,sb.length() -2);
        }
        else
            oresXorigisis = "";

            oresXorigisisTV.setText(oresXorigisis);


        if (oresXorigisis!= null && !oresXorigisis.isEmpty()){
            List<Integer> lista = new ArrayList<>();
            String parts[] = oresXorigisis.trim().split(",");
            for (int i=0; i<parts.length; i++){
                // Η ΜΟΡΦΗ ΩΡΑ ΕΙΝΑΙ 03:00 ΟΠΟΤΕ ΚΟΙΤΑΖΩ ΑΝ ΞΕΚΙΝΑΕΙ ΜΕ 0 ΓΙΑ ΝΑ ΠΑΡΩ ΜΟΝΟ ΤΟ ΔΕΥΤΕΡΟ ΝΟΥΜΕΡΟ ΑΛΛΙΩς ΤΑ ΔΥΟ ΠΡΩΤΑ
                String p = parts[i].trim();
                p = p.substring(0,2);
                p = p.startsWith("0") ? p.substring(1,2) : p.substring(0,2);
                lista.add(Integer.parseInt(p));
            }

            // ΓΙΑ ΝΑ ΦΑΙΝΕΤΑΙ ΠΟΙΕΣ ΤΙΜΕΣ ΥΠΑΡΧΟΥΝ ΗΔΗ ΑΠΟ ΤΟΝ ΣΕΡΒΕΡ ΓΙΑ ΕΝΗΜΕΡΩΣΗ ΚΑΙ ΝΑ ΕΙΝΙΑ ΤΣΕΚΑΡΙΣΜΕΝΑ ΤΑ CHECKBOXES
            for (int i = 0 ; i<lista.size(); i++){
                int hour = lista.get(i);
               ITEMS_HOURS_VALUES[hour]  = true;
            }
        }

        adapterRV.notifyDataSetChanged();

       // adapterRV.result.get(1).setValue(Utils.convertDateTomilliseconds(listaAdaptor.get(1).getValue()));
       // adapterRV.result.get(2).setValue(Utils.convertDateTomilliseconds(listaAdaptor.get(2).getValue()));


    }


    @Override
    public void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor) {

        super.setValuesTo_valuesJSON(titles_positions, listAdaptor);
      //  if (weHaveData)
            setInfoMedicineForUpdate();

    }

    private void setInfoMedicineForUpdate(){

        nameJson.clear();
        valuesJson.clear();

        for (int i=0; i<listaAdaptor.size(); i++){
           String col_name = listaAdaptor.get(i).getCol_name();
           String value = listaAdaptor.get(i).getValue();
            nameJson.add(col_name);

            if (col_name.toLowerCase().trim().equals("itemid"))
               valuesJson.add(Utils.getMedicineIDForValueJson(this));  //ΜΕΤΑΦΟΡΑ ΙΤΕΜ ΑΠΟ ΠΡΙΝ ΜΕΣΑ ΑΠΟ ΤΗ ΜΝΗΜΗ ΤΗΣ ΣΥΣΚΕΥΗΣ
            else if (col_name.equals("dateStart")) {
                if (value.contains("-") || value.contains("/"))
                valuesJson.add(Utils.convertDateTomilliseconds(value));
                else
                    valuesJson.add(value);
            }
            else if (col_name.equals("date")) {
                if (value.contains("-") || value.contains("/"))
                    valuesJson.add(Utils.convertDateTomilliseconds(value));
                else
                    valuesJson.add(value);
            }


           else
               valuesJson.add(value);

        }

    }




    private void preExecuteSimpleQuery(boolean isInsert){

        String oresXorigisis = oresXorigisisTV.getText().toString();
        String parts[] = oresXorigisis.split(",");

        StringBuilder sb1 = new StringBuilder();

        if (isInsert)
            query_ores_xorigisis = "INSERT INTO Nursing_ores_xorigisis ( date,xorigisiID, hour ) VALUES ";
        else {

            query_ores_xorigisis = "DELETE Nursing_ores_xorigisis WHERE xorigisiID = " + id +
                    "  and hour not in (" ;
            for (int i = 0; i < parts.length; i++)
                if (iparxousesOres.contains(parts[i].trim()))
                    sb1.append("dbo.timeToNum(convert(datetime, '" + parts[i] + "' ,103)),");


            if (sb1.toString().length() > 18) // TIXAIO NOUMERO APLA NA FANEI POS EXEI ESTO MIA EGGRAFI
                    query_ores_xorigisis = query_ores_xorigisis + sb1.toString().trim().substring(0,sb1.toString().length() -1) +
                                                        ") INSERT INTO Nursing_ores_xorigisis (date, xorigisiID, hour ) VALUES ";
            else
                query_ores_xorigisis = query_ores_xorigisis.substring(0,query_ores_xorigisis.length() - 18)  +
                        " INSERT INTO Nursing_ores_xorigisis (date, xorigisiID, hour ) VALUES ";


        }



            if (parts.length > 0) {
                sb1 = new StringBuilder();
                for (int i = 0; i < parts.length; i++) {

                    if (isInsert)
                        sb1.append("( dbo.timeToNum(CONVERT(datetime, ' " + date_oron_farmakou +  "' , 103)), "
                                + eggrafiID + ", dbo.timeToNum(CONVERT(datetime, "
                                + "'"  + parts[i].trim() + "' , 103))), ");
                    else {
                            if (!iparxousesOres.contains(parts[i].trim()))
                            sb1.append("(" + "dbo.timeToNum(CONVERT(datetime, ' " + date_oron_farmakou +  "' , 103))," +
                                    id +
                                    ", dbo.timeToNum(CONVERT(datetime, " +
                                    "'" + parts[i].trim() + "' , 103))), ");
                    }
                }

                query_ores_xorigisis = query_ores_xorigisis + sb1.toString().trim();
                query_ores_xorigisis = query_ores_xorigisis.substring(0, query_ores_xorigisis.length() - 1); //gia na vgei to teleutaio komma

                // ΑΥΤΟ ΣΥΜΒΑΙΝΕΙ ΜΟΝΟ ΟΤΑΝ Ο ΧΡΗΣΤΗς ΑΠΛΑ ΑΦΑΙΡΕΙ ΩΡΕΣ ΧΩΡΗΓΗΣΗΣ ΣΤΗΝ ΕΝΗΜΕΡΩΣΗ
                // ΕΤΣΙ ΔΙΑΓΑΦΡΑΟΥΜΕ ΟΤ ΙΝΣΕΡΤ ΚΑΙ ΚΡΑΤΑΜΕ ΜΟΝΟ ΤΟ DELETE
                if (query_ores_xorigisis.endsWith("VALUES"))
                    query_ores_xorigisis =  query_ores_xorigisis.split("INSERT")[0];



            }


    }


    @Override
    public void updateSuccess(String str) {
        Utils.timeHandlerDoneButton(updateButton, this);

        xorigisiID = eggrafiID;
        // Toast.makeText(this, xorigisiID, Toast.LENGTH_SHORT).show();

                    // ΓΙΑ ΤΟ ΙΝΣΕΡΤ ΤΩΝ ΩΡΩΝ
        if (eggrafiID != null && !eggrafiID.equals(""))
            preExecuteSimpleQuery(true);

        else{
          //  if (listaOresFromFragment != null && listaOresFromFragment.isEmpty())
            preExecuteSimpleQuery(false);

        }


        runORupdate_simple_query(query_ores_xorigisis);

    }


    @Override
    public void update(String str) {
        if (str.equals("Πετυχημένη ενημέρωση")) {
            Toast.makeText(this, "Οι ώρες χορήγησης ενημερώθηκαν", Toast.LENGTH_SHORT).show();

            if (eggrafiID != null && !eggrafiID.equals("")) {
                clearListaAdaptor(listaAdaptor);
                adapterRV.notifyDataSetChanged();
                oresXorigisisTV.setText("");
            }


        }
        else
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void dialogMedicineClose(String id_name) {
        listaAdaptor.get(0).setValue(Utils.getfirstPartSplitCommaString(id_name));
    }
}
