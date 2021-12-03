package micros_leader.george.nosileutikosfakelos;

import android.app.Activity;
import android.app.DatePickerDialog;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.Kathetires_kalliergies_Meth_Activity;
import micros_leader.george.nosileutikosfakelos.Main_menu.SigxoneusiFiladiwnActivity;
import micros_leader.george.nosileutikosfakelos.TableView.Table;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;


public class Simple_items_rv_adapter extends RecyclerView.Adapter<Simple_items_rv_adapter.MyViewHolder>{


    private final Activity act;
    public ArrayList<Simple_Items> result;


    public Simple_items_rv_adapter(Activity act , ArrayList <Simple_Items> result){
        this.act = act;
        this.result = result;
    }




    @NonNull
    @Override
    public Simple_items_rv_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_isozigio_p_a, parent, false);
        Simple_items_rv_adapter.MyViewHolder vh = new Simple_items_rv_adapter.MyViewHolder(v); // pass the view to View Holder

        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull Simple_items_rv_adapter.MyViewHolder holder, int pos) {

       int id =  result.get(pos).getId();
       int itemID =  result.get(pos).getItemID();
       String title = result.get(pos).getTitle();
       String dateIN = result.get(pos).getDatein();
       String dateout = result.get(pos).getDateout();
       result.get(pos).hasDateoutFromServer = !dateout.isEmpty();

       String value = result.get(pos).getValue();

       holder.titleTV.setText(title);
       holder.date_inTV.setText(dateIN);
       holder.date_outTV.setText(dateout);

       holder.textET.setTag(pos);
       holder.date_inTV.setTag(pos);
       holder.date_outTV.setTag(pos);

       holder.textET.setText(value);



    }

    @Override
    public int getItemCount() {
        return result.size();
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV, date_inTV, date_outTV;
        EditText textET;
        Button neaEggrafiBT;
        ImageView sigkentrotikaBT;


        public MyViewHolder(View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.titleTV);


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






            textET = itemView.findViewById(R.id.textET);
            textET.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    int pos = (int) textET.getTag();
                    result.get(pos).setValue(textET.getText().toString());


                }
            });



            neaEggrafiBT = itemView.findViewById(R.id.neaEggrafiBT);
            neaEggrafiBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) date_outTV.getTag();
                    boolean hasDateoutFromServer = result.get(pos).hasDateoutFromServer;
                    if (hasDateoutFromServer){
                        textET.setText("");
                        date_inTV.setText("");
                        date_outTV.setText("");


                        result.get(pos).setDatein("");
                        result.get(pos).setDateout("");
                        result.get(pos).setValue("");
                        result.get(pos).setId(0);
                        result.get(pos).setUserID("");

                        Toast.makeText(act, "Μπορείτε να κάνετε μία νέα καταχώρηση", Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(act, "Δεν μπορείτε να κάνετε νέα καταχώρηση χωρις να έχει καταχωρηθεί\n " +
                                " η ημ/νία εξόδου της προηγούμενης εγγαρφής", Toast.LENGTH_SHORT).show();
                }
            });


            sigkentrotikaBT = itemView.findViewById(R.id.sigkentrotikaBT);
            sigkentrotikaBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = (int) textET.getTag();
                    int itemID = result.get(pos).getItemID();


                    String transgroupID = "";
                    Kathetires_kalliergies_Meth_Activity x = null;
                    try{
                        //Αυτο γινεται για να παρει  το transgroupID οταν καλειται απο την οθονη των συγχωνευσεων φυλλαδιων

                        SigxoneusiFiladiwnActivity s  = (SigxoneusiFiladiwnActivity) act;
                        x = (Kathetires_kalliergies_Meth_Activity) s.activityFromSigxoneusi;
                        transgroupID = x.transgroupID;
                    }
                    catch (Exception ignored){

                    }

                    BasicActivity basicAct = (BasicActivity) act;

                    String query = "select *, dbo.datetostr(date) as dateStr ," +
                            " name, dbo.NAMEUSER(userid) as username, \n" +
                            " dbo.datetostr(datestart) as datestartStr,  dbo.datetostr(datestop) as datestopStr \n" +
                            "from Nursing_Kathethres_Topos t \n" +
                            "join  Nursing_Kathethres_Meth s on s.id = t.itemID \n" +
                            "where itemID = " + itemID +
                            " and transgroupID = " + (x != null ? transgroupID : basicAct.transgroupID ) +
                            " order by t.id desc ";



                    act.startActivity(Table.tableView_sigkentrotika(query,"",act,
                            null,null,InfoSpecificLists.getNursing_Kathethres_Topos_for_item(),
                            false,false,false));
                }
            });
        }

    }






}
