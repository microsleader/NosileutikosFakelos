package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskDelete;
import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetDelete;
import micros_leader.george.nosileutikosfakelos.Main_menu.SigxoneusiFiladiwnActivity;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Simple_Items;
import micros_leader.george.nosileutikosfakelos.Spinner_items_lists;
import micros_leader.george.nosileutikosfakelos.Spinner_new_Image_Adapter;
import micros_leader.george.nosileutikosfakelos.TableView.Table;
import micros_leader.george.nosileutikosfakelos.TableView.TableActivity;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Kathetires_RV extends  RecyclerView.Adapter<Kathetires_RV.MyViewHolder> {


    private final Activity act;
    private final boolean isKathetiresMeth;
    public ArrayList<Simple_Items> result;


    public Kathetires_RV(Activity act , ArrayList <Simple_Items> result , boolean isKathetiresMeth){
        this.act = act;
        this.result = result;
        this.isKathetiresMeth = isKathetiresMeth;
    }

    @NonNull
    @Override
    public Kathetires_RV.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_kathetires_rv, parent, false);
        Kathetires_RV.MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder

        return vh;

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int pos) {

        Simple_Items x = result.get(pos);
        long id =  result.get(pos).getId();
        int itemID =  result.get(pos).getItemID();
        String title = result.get(pos).getTitle();
        String dateIN = result.get(pos).getDatein();
        String dateout = result.get(pos).getDateout();
        String val_et1 = result.get(pos).valET1;
        String val_et2 = result.get(pos).valET2;
        String val_et3 = result.get(pos).valET3;

        String val_sp1 = result.get(pos).valSP1;
        String val_sp2 = result.get(pos).valSP2;
        String val_sp3 = result.get(pos).valSP3;


        result.get(pos).hasDateoutFromServer = !dateout.isEmpty();


        if (itemID >= 18 && itemID <= 27 &&  val_et3 != null && !val_et3.isEmpty()) {  //παροχευτεσεις 1-10
            holder.mainTitleTV.setText(val_et3); // τιτλος θεση
        }
        else
            holder.mainTitleTV.setText(title);



        holder.date_inTV.setText(dateIN);
        holder.date_outTV.setText(dateout);

        holder.textET1.setTag(pos);holder.textET2.setTag(pos);holder.textET3.setTag(pos);
        holder.textET1.setText(val_et1);
        holder.textET2.setText(val_et2);
        holder.textET3.setText(val_et3);



        holder.valSP1.setTag(pos);holder.valSP2.setTag(pos);holder.valSP3.setTag(pos);


        holder.date_inTV.setTag(pos);
        holder.date_outTV.setTag(pos);

        manageItems( holder,itemID,x);



    }

    @Override
    public int getItemCount() {
        return result.size();
    }



    private void manageItems(MyViewHolder holder, int itemID , Simple_Items x){

        if ((itemID >=1 && itemID <= 5) ||  itemID == 8 ||  itemID == 9 || itemID == 14 || itemID == 28 || itemID == 32){ //περιφερικες αρτηριακες και αλλα
            holder.childLayout1.setVisibility(View.VISIBLE);
            holder.childLayout2.setVisibility(View.VISIBLE);

            if (itemID == 1 || itemID == 2 || itemID == 32) { //περιφερικες 1 ,2 ,3
                holder.titleTV1.setText("Μέγεθος");
                holder.titleTV2.setText("Θέση");

                addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_periferiki_grammi_megethos_choices() , x.valSP1);
                addSpinnerAdapter(holder.valSP2 , Spinner_items_lists.get_periferiki_grammi_thesi_choices() , x.valSP2);
            }

            else if (itemID == 3){ //αρτηριακή 1

                holder.titleTV1.setText("Θέση");
                holder.titleTV2.setText("Μέγεθος");
                holder.titleTV3.setText("Είδος");
                holder.childLayout3.setVisibility(View.VISIBLE);

                addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_artiriaki_grammi_thesi_choices() , x.valSP1);
                addSpinnerAdapter(holder.valSP2 , Spinner_items_lists.get_artiriaki_grammi_megethos_choices() , x.valSP2);
                addSpinnerAdapter(holder.valSP3 , Spinner_items_lists.get_artiriaki_grammi_eidos_choices() , x.valSP3);

            }

            else if (itemID == 28){ //αρτηριακή 2

                holder.titleTV1.setText("Θέση");
                holder.titleTV2.setText("Μέγεθος");
                holder.titleTV3.setText("Είδος");
                holder.childLayout3.setVisibility(View.VISIBLE);

                addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_artiriaki_grammi_thesi_choices() , x.valSP1);
                addSpinnerAdapter(holder.valSP2 , Spinner_items_lists.get_artiriaki_grammi_megethos_choices() , x.valSP2);
                addSpinnerAdapter(holder.valSP3 , Spinner_items_lists.get_artiriaki_grammi_eidos_choices() , x.valSP3);

            }


            else if (itemID == 4){ //folley
                holder.titleTV1.setText("Θέση");
                holder.titleTV2.setText("Μέγεθος");
                addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_folley_eidos_choices() , x.valSP1);
                addSpinnerAdapter(holder.valSP2 , Spinner_items_lists.get_folley_megethos_choices() , x.valSP2);

            }

            else if (itemID == 5){ //levin
                holder.titleTV1.setText("Θέση");
                holder.titleTV2.setText("Μέγεθος");
                addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_levin_eidos_choices() , x.valSP1);
                addSpinnerAdapter(holder.valSP2 , Spinner_items_lists.get_levin_megethos_choices() , x.valSP2);

            }


            else if (itemID == 8){ //κεντρική γραμμή
                holder.titleTV1.setText("είδος");
                holder.titleTV2.setText("Θέση");
                addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_kentr_eidos_choices() , x.valSP1);
                addSpinnerAdapter(holder.valSP2 , Spinner_items_lists.kentr_thesi_and_kath_aim_and_thikari_choices() , x.valSP2);

            }

            else if (itemID == 9){ //καθετήρας αιμοκάθαρσης
                holder.titleTV1.setText("είδος");
                holder.titleTV2.setText("Θέση");
                addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_kath_aim_eidos_choices() , x.valSP1);
                addSpinnerAdapter(holder.valSP2 , Spinner_items_lists.kentr_thesi_and_kath_aim_and_thikari_choices() , x.valSP2);

            }


            else if (itemID == 14){ //ΘΗΚΑΡΙ
                holder.titleTV1.setText("είδος");
                holder.titleTV2.setText("Θέση");
                addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_thikari_eidos_choices() , x.valSP1);
                addSpinnerAdapter(holder.valSP2 , Spinner_items_lists.kentr_thesi_and_kath_aim_and_thikari_choices() , x.valSP2);

            }

        }



        else if (itemID == 6){ //επισκληρίδιος καθετήρας
            holder.childLayout1.setVisibility(View.VISIBLE);
            holder.childLayout2.setVisibility(View.VISIBLE);
            holder.textET1.setVisibility(View.GONE);
            holder.textET2.setVisibility(View.GONE);
            holder.titleTV1.setText("Θέση");
            holder.titleTV2.setText("φέρει");
            addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_episk_ferei_choices() , x.valSP1);
            addSpinnerAdapter(holder.valSP2 , Spinner_items_lists.getYesNo() , x.valSP2);

        }





        else if (itemID == 10){  // ΕΝΔΟΤΡΑΧΕΙΑΚΟΣ ΣΩΛΗΝΑΣ  έση (cm εξόδου), μέγεθος, πίεση cuff
            holder.childLayout1.setVisibility(View.VISIBLE);
            holder.childLayout2.setVisibility(View.VISIBLE);
            holder.childLayout3.setVisibility(View.VISIBLE);
            holder.valSP2.setVisibility(View.GONE);
            holder.valSP3.setVisibility(View.GONE);
            holder.titleTV1.setText("μέγεθος");
            holder.titleTV2.setText("πίεση cuff");
            holder.titleTV3.setText("Θέση");

            holder.textET2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            holder.textET3.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);


            addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.end_sol_size_choices() , x.valSP1);

        }



        else if (itemID == 11){  // ΣΩΛΗΝΑΣ ΤΡΑΧΕΙΟΣΤΟΜΙΑΣ
            holder.childLayout1.setVisibility(View.VISIBLE);
            holder.childLayout2.setVisibility(View.VISIBLE);
            holder.valSP2.setVisibility(View.GONE);
            holder.titleTV1.setText("μέγεθος");
            holder.titleTV2.setText("πίεση cuff");

            holder.textET2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

            addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.sol_trax_size_choices() , x.valSP1);

        }



        else if (itemID == 12){  // Ρινοφαρυγγικός Αεραγωγός
            holder.childLayout1.setVisibility(View.VISIBLE);
            holder.titleTV1.setText("μέγεθος");
            addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_rin_aer_size_choices_choices() , x.valSP1);


        }

        else if (itemID == 13){  // Στοματοφαρυγγικός Αεραγωγός
            holder.childLayout1.setVisibility(View.VISIBLE);
            holder.titleTV1.setText("μέγεθος");
            addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_stoma_aer_size_choices_choices() , x.valSP1);

        }


        else if (itemID == 15) { //ΘΗΚΑΡΙ (+ Ενδοαορτική Αντλία(IABP)
            holder.childLayout1.setVisibility(View.VISIBLE);
            holder.textET1.setVisibility(View.GONE);
            holder.titleTV1.setText("ΘΗΚΑΡΙ (IABP) θέση");
            addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_thikari_antlia_thesi_choices() , x.valSP1);


        }

        else if (itemID == 16) { //ΚΑΘΕΤΗΡΑΣ ICP
            holder.childLayout1.setVisibility(View.VISIBLE);
            holder.valSP1.setVisibility(View.GONE);
            holder.titleTV1.setText("ICP");

        }




        else if (itemID>= 18 &&  itemID <= 27) {// παροχευτευσεις 1-10
            holder.childLayout1.setVisibility(View.VISIBLE);
            holder.childLayout2.setVisibility(View.VISIBLE);
            holder.childLayout3.setVisibility(View.VISIBLE);


            holder.titleTV1.setText("Είδος");
            holder.titleTV2.setText("Περιγραφή");
            holder.titleTV3.setText("Θέση");
            holder.valSP3.setVisibility(View.GONE);
            addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_paroxeteusi_eidos_choices(), x.valSP1);
            addSpinnerAdapter(holder.valSP2 , Spinner_items_lists.get_paroxeteusi_perigrafi_choices(), x.valSP2);

        }

        else if (itemID == 29) { //ΘΗΚΑΡΙ (+ Ενδοαορτική Αντλία(IABP)
            holder.childLayout1.setVisibility(View.VISIBLE);
            holder.childLayout2.setVisibility(View.VISIBLE);
            holder.textET1.setVisibility(View.GONE);
            holder.textET2.setVisibility(View.GONE);
            holder.titleTV1.setText("θέση");
            holder.titleTV2.setText("gripper");
            addSpinnerAdapter(holder.valSP1 , Spinner_items_lists.get_port_a_cath_thesi_choices() , x.valSP1);
            addSpinnerAdapter(holder.valSP2 , Spinner_items_lists.get_port_a_cath_gripper_choices() , x.valSP1);


        }

        else {
            holder.childLayout1.setVisibility(View.VISIBLE);
        }





    }



    private void addSpinnerAdapter(Spinner sp, ArrayList<Spinner_item> lista, String valueSelection){

        ArrayAdapter adapter = new Spinner_new_Image_Adapter(act, R.layout.spinner_layout2, (Spinner_item[]) lista.toArray(new Spinner_item[lista.size()]));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        if (valueSelection == null || valueSelection.isEmpty())
            valueSelection = "0";
        sp.setSelection(Integer.parseInt(valueSelection));


    }


      class  MyViewHolder  extends RecyclerView.ViewHolder{


        public TextView mainTitleTV, titleTV1, titleTV2, titleTV3 , date_inTV, date_outTV;
        public EditText textET1, textET2, textET3;
        public Spinner valSP1, valSP2, valSP3;
        public LinearLayout childLayout1 , childLayout2, childLayout3;
        public Button neaEggrafiBT;
        public ImageView sigkentrotikaBT;

        public MyViewHolder(View itemView) {
            super(itemView);
            mainTitleTV = itemView.findViewById(R.id.mainTitleTV);
            titleTV1 = itemView.findViewById(R.id.titleTV1);
            titleTV2 = itemView.findViewById(R.id.titleTV2);
            titleTV3 = itemView.findViewById(R.id.titleTV3);

            textET1 = itemView.findViewById(R.id.textET1);
            textET2 = itemView.findViewById(R.id.textET2);
            textET3 = itemView.findViewById(R.id.textET3);

            valSP1 = itemView.findViewById(R.id.valSP1);
            valSP2 = itemView.findViewById(R.id.valSP2);
            valSP3 = itemView.findViewById(R.id.valSP3);

            childLayout1 = itemView.findViewById(R.id.childLayout1);
            childLayout2 = itemView.findViewById(R.id.childLayout2);
            childLayout3 = itemView.findViewById(R.id.childLayout3);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = (int) textET1.getTag();
                    long id = result.get(pos).getId();
                    String row_userID = result.get(pos).getUserID();
                    if (!row_userID.equals(Utils.getUserID(act)) && !Utils.get_is_nursing_unlock(act))
                        Toast.makeText(act, "Δεν μπορείτε να διαγράψετε την συγκεκριμένη εγγραφή", Toast.LENGTH_SHORT).show();
                    else {
                        new AlertDialog.Builder(act)
                                .setTitle("Διαγραφή εγγραφης;")
                                .setMessage("Είστε σίγουροι πως θέλετε να διαγράψετε ;")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        deleteListener(id, pos);

                                    }
                                })

                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                    return false;

                }
            });



            {

                date_inTV = itemView.findViewById(R.id.date_inTV);
                //  Utils.dateListener(act, date_inTV);
                final Calendar myCalendar = Calendar.getInstance();


                final DatePickerDialog.OnDateSetListener date12 = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub

                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String myFormat = "dd-MM-yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        date_inTV.setText(sdf.format(myCalendar.getTime()));
                        int pos = (int) date_inTV.getTag();
                        result.get(pos).setDatein(date_inTV.getText().toString());

                    }

                };

                date_inTV.setOnClickListener(v -> {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(act, date12, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                });


            }


            {

                date_outTV = itemView.findViewById(R.id.date_outTV);
                //   Utils.dateListener(act, date_outTV);


                final Calendar myCalendar = Calendar.getInstance();


                final DatePickerDialog.OnDateSetListener date12 = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub

                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String myFormat = "dd-MM-yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        date_outTV.setText(sdf.format(myCalendar.getTime()));
                        int pos = (int) date_outTV.getTag();
                        result.get(pos).setDateout(date_outTV.getText().toString());
                    }

                };

                date_outTV.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        new DatePickerDialog(act, date12, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();


                    }
                });


            }






            if (textET1 != null && textET1.getVisibility() == View.VISIBLE) {
                textET1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        int pos = (int) textET1.getTag();
                        result.get(pos).valET1 = textET1.getText().toString();


                    }
                });
            }


            if (textET2 != null && textET2.getVisibility() == View.VISIBLE) {
                textET2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        int pos = (int) textET2.getTag();
                        result.get(pos).valET2 = textET2.getText().toString();


                    }
                });
            }


            if (textET3 != null && textET3.getVisibility() == View.VISIBLE) {
                textET3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        int pos = (int) textET3.getTag();
                        result.get(pos).valET3 = textET3.getText().toString();


                    }
                });
            }


            if (valSP1 != null && valSP1.getVisibility() == View.VISIBLE ) {
                valSP1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (valSP1.getTag() != null) {
                            int pos = (int) valSP1.getTag();
                            Spinner_item item = (Spinner_item) valSP1.getSelectedItem();

                            result.get(pos).valSP1 = String.valueOf(item.getId());
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }


            if (valSP2 != null && valSP2.getVisibility() == View.VISIBLE ) {
                valSP2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (valSP2.getTag() != null) {
                            int pos = (int) valSP2.getTag();
                            Spinner_item item = (Spinner_item) valSP2.getSelectedItem();

                            result.get(pos).valSP2 = String.valueOf(item.getId());
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }


            if (valSP3 != null && valSP3.getVisibility() == View.VISIBLE ) {
                valSP3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (valSP3.getTag() != null) {

                            int pos = (int) valSP3.getTag();
                            Spinner_item item = (Spinner_item) valSP3.getSelectedItem();

                            result.get(pos).valSP3 = String.valueOf(item.getId());
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }


            neaEggrafiBT = itemView.findViewById(R.id.neaEggrafiBT);
            neaEggrafiBT.setOnClickListener(view -> neaEggrafiListener());


            sigkentrotikaBT = itemView.findViewById(R.id.sigkentrotikaBT);
            sigkentrotikaBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = (int) textET1.getTag();
                    int itemID = result.get(pos).getItemID();
                    String title = result.get(pos).getTitle();


                    String transgroupID = "";
                    Kathetires_Activity x = null;
                    try{
                        //Αυτο γινεται για να παρει  το transgroupID οταν καλειται απο την οθονη των συγχωνευσεων φυλλαδιων

                        SigxoneusiFiladiwnActivity s  = (SigxoneusiFiladiwnActivity) act;
                        x = (Kathetires_Activity) s.activityFromSigxoneusi;
                        transgroupID = x.transgroupID;
                    }
                    catch (Exception ignored){

                    }

                    BasicActivity basicAct = (BasicActivity) act;

                    String query = "select *, dbo.datetostr(date) as dateStr ," +
                            " name, dbo.NAMEUSER(userid) as username, \n" +
                            " dbo.datetostr(datestart) as datestartStr,  dbo.datetostr(datestop) as datestopStr \n" +
                            "from " + ( isKathetiresMeth ? "Nursing_Kathethres_Topos " : "Nursing_Kathethres_orofoi " + " t \n") +
                            "join  Nursing_Kathethres_Meth s on s.id = t.itemID \n" +
                            "where itemID = " + itemID +
                            " and transgroupID = " + (x != null ? transgroupID : basicAct.transgroupID ) +
                            " order by t.id desc ";


                    Intent in = Table.tableView_sigkentrotika(query,"", act,
                            null,null, InfoSpecificLists.getNursing_Kathethres_Topos_for_item(itemID , isKathetiresMeth),
                            false,false,false);
                    in.putExtra("toolbar_title", title);
                    act.startActivity(in);
                }
            });


        }






        private void neaEggrafiListener (){

            int pos = (int) date_outTV.getTag();
            boolean hasDateoutFromServer = result.get(pos).hasDateoutFromServer;
            if (hasDateoutFromServer){
                textET1.setText("");
                textET2.setText("");
                textET3.setText("");
                if (valSP1.getAdapter() != null)
                    valSP1.setSelection(0);
                if (valSP2.getAdapter() != null)
                    valSP2.setSelection(0);
                if (valSP3.getAdapter() != null)
                    valSP3.setSelection(0);
                date_inTV.setText("");
                date_outTV.setText("");



                Simple_Items s = result.get(pos);
                s.setId(0);
                s.setUserID("");
                s.valET1 = "";
                s.valET2 = "";
                s.valET3 = "";
                s.valSP1 = "";
                s.valSP2 = "";
                s.valSP3 = "";
                s.setDatein("");
                s.setDateout("");
                s.setValue("");

                Toast.makeText(act, "Μπορείτε να κάνετε μία νέα καταχώρηση", Toast.LENGTH_SHORT).show();

            }
            else
                Toast.makeText(act, "Δεν μπορείτε να κάνετε νέα καταχώρηση χωρις να έχει καταχωρηθεί\n " +
                        " η ημ/νία εξόδου της προηγούμενης εγγαρφής", Toast.LENGTH_SHORT).show();
        }



        private void deleteListener(long id, int pos){
            if (id > 0) {
                AsyncTaskDelete task = new AsyncTaskDelete(act, "Nursing_Kathethres_Topos", String.valueOf(id));
                task.ctx = act;
                task.listener = new AsyncGetDelete() {
                    @Override
                    public void deleteResult(String str) {
                        if (str.equals(act.getString(R.string.successful_update))) {
                            Toast.makeText(act, "Διαγραφή επιτυχής", Toast.LENGTH_SHORT).show();
                            result.remove(pos);
                            notifyItemChanged(pos);
                        }
                        else
                            Toast.makeText(act, "Κάτι πήγε στραβά", Toast.LENGTH_SHORT).show();

                    }
                };
                task.execute();
            }
        }
     }



}
