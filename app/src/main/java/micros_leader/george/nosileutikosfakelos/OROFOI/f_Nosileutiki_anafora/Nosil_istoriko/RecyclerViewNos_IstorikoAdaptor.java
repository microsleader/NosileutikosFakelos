package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_istoriko;

import android.app.Activity;
import android.app.DatePickerDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.InputFilterMinMax;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.Utils.convertMillisecondsToDateTime;


public class RecyclerViewNos_IstorikoAdaptor extends RecyclerView.Adapter<RecyclerViewNos_IstorikoAdaptor.MyViewHolder> implements SearchView.OnQueryTextListener {


    public ArrayList<Nosil_IstorikoList> result;
    private Activity act;


    public int VIEW_TYPE_HEADER = 100;
    public int VIEW_TYPE_ITEM = 200;

    private static int TEXTVIEW_ITEM = 1;
    private static int TITLE_ITEM = 2;
    private static int EDITTEXT_ITEM = 3;
    private static int CHECKBOX_ITEM = 4;
    private static int SPINNER_ITEM = 5;




    // create constructor to innitilize context and data sent from MainActivity
    public RecyclerViewNos_IstorikoAdaptor(Activity act, ArrayList<Nosil_IstorikoList> result) {

        this.act = act;
        this.result = result;


    }


    @Override
    public int getItemViewType(int position) {

        if (result.get(position).getTextViewSForRV() != null)
            return TEXTVIEW_ITEM;

        if (result.get(position).getTitlesForRV() != null)
            return TITLE_ITEM;

        if (result.get(position).getEditTextForRV() != null)
            return EDITTEXT_ITEM;

        else if (result.get(position).getCheckBoxesForRV() != null)
            return CHECKBOX_ITEM;

        else if (result.get(position).getSpinnersForRV() != null)
            return SPINNER_ITEM;

        else
            return 0;
    }



    @Override
    public RecyclerViewNos_IstorikoAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = null;
        switch (viewType) {

            case 1: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_textview_rec_adapter, parent, false);
                break;
            }

            case 2: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_titles_rec_adapter, parent, false);
                break;
            }

            case 3: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_edittext_rec_adapter, parent, false);
                break;
            }
            case 4: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_item_checkbox_rec_adapter, parent, false);
                break;
            }

            case 5: {
                    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_spinner_rec_adapter, parent, false);
                    break;
                }
        }


        RecyclerViewNos_IstorikoAdaptor.MyViewHolder vh = new RecyclerViewNos_IstorikoAdaptor.MyViewHolder(v,viewType); // pass the view to View Holder

        return vh;
    }


    @Override
    public void onBindViewHolder(final RecyclerViewNos_IstorikoAdaptor.MyViewHolder holder, final int position) {
        // set the data in items
        String title;
        String value;



            // TITLE

            if (result.get(position).getTitlesForRV() != null) {
                title = result.get(position).getTitlesForRV().getTitle();

                holder.titleTV.setText(title);
            }



            // TEXTVIEW

            if (result.get(position).getTextViewSForRV() != null) {
                title = result.get(position).getTextViewSForRV().getTitle();
                value = result.get(position).getTextViewSForRV().getValue();

                holder.titleTV.setText(title);
                holder.valueTV.setText(convertMillisecondsToDateTime(value));

                holder.valueTV.setTag(position);
            }


            // EDITTEXT
            if (result.get(position).getEditTextForRV() != null) {
                holder.valueET.setTag(position);

                title = result.get(position).getEditTextForRV().getTitle();
                value = result.get(position).getEditTextForRV().getValue();

                holder.titleTV.setText(title);
                holder.valueET.setText(value);

                int edittextType = result.get(position).getEditTextForRV().getType();

                specifyEdittext(holder.valueET, title,edittextType);




            }


            // SPINNER
            else if (result.get(position).getSpinnersForRV() != null) {


                title = result.get(position).getSpinnersForRV().getTitle();
                String id = result.get(position).getSpinnersForRV().getValue();
                if (id.equals(""))
                    id = "0";


                holder.titleTV.setText(title);
                holder.valueSP.setTag(position);

                addadapterToSpinner(title, holder.valueSP,id);


            }


            //  CHECKBOX
            else if (result.get(position).getCheckBoxesForRV() != null) {

                holder.valueCH.setTag(position);

                title = result.get(position).getCheckBoxesForRV().getTitle();
                holder.valueCH.setText(title);

                if (result.get(position).getCheckBoxesForRV().isChecked())
                    holder.valueCH.setChecked(true);
                else
                    holder.valueCH.setChecked(false);



            }




    }

    private void specifyEdittext(EditText valueET, String title, int edittextType) {

        if (edittextType == 1)
            valueET.setInputType(InputType.TYPE_CLASS_TEXT );

        else if (edittextType == 2){
            valueET.setInputType(InputType.TYPE_CLASS_NUMBER );
        }

        else if(edittextType == 3){
            valueET.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        else if(edittextType == 4){
            valueET.setInputType(InputType.TYPE_CLASS_NUMBER );

            if (title.equals("Κλίμακα πόνου"))
                 valueET.setFilters(new InputFilter[]{new InputFilterMinMax(0, 10)});
          //  else if (title.equals("Έλκη κατάκλισης (σημείο/βαθμός)"))
           //     valueET.setFilters(new InputFilter[]{new InputFilterMinMax(1, 4)});



        }
    }


    @Override
    public int getItemCount() {

        return result.size();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    public void filterList(ArrayList<Nosil_IstorikoList> filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView valueTV;
        EditText valueET;
        TextView titleTV;
        CheckBox valueCH;
        Spinner valueSP;



        public MyViewHolder(final View itemView, int viewType) {
            super(itemView);

            // INITIALIZE KAI LISTENERS ANALOGA TO PEDIO

            if (viewType == TEXTVIEW_ITEM) {
                valueTV =  itemView.findViewById(R.id.valueTV);

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

                        valueTV.setText(sdf.format(myCalendar.getTime()));
                        //   updateLabel(textView);


                        int position = (int) valueTV.getTag();

                        result.get(position).getTextViewSForRV().setValue(Utils.convertDateTomilliseconds(valueTV.getText().toString()));


                    }

                };

                valueTV.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        new DatePickerDialog(act, date12, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });



            }





            if (viewType == TITLE_ITEM) {
                titleTV =  itemView.findViewById(R.id.titleTV);
                valueTV =  itemView.findViewById(R.id.valueTV);

            }

            if (viewType == EDITTEXT_ITEM) {
                valueET =  itemView.findViewById(R.id.valueET);
                valueET.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        int position = (int)valueET.getTag();
                        result.get(position).getEditTextForRV().setValue(valueET.getText().toString());



                    }
                });


            } else if (viewType == CHECKBOX_ITEM) {
                valueCH =  itemView.findViewById(R.id.valueCH);
                valueCH.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int position = (int)valueCH.getTag();
                        result.get(position).getCheckBoxesForRV().setChecked(valueCH.isChecked());

                    }
                });

            }


            else if (viewType == SPINNER_ITEM){
                valueSP = itemView.findViewById(R.id.valueSP);

                valueSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                              int pos = (int)valueSP.getTag();
                        result.get(pos).getSpinnersForRV().setValue(String.valueOf(valueSP.getSelectedItemPosition()));



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }


            titleTV =  itemView.findViewById(R.id.titleTV);

        }
    }







    private void addadapterToSpinner(String title, Spinner valueSP, String id) {
        ArrayAdapter adapter;
        ArrayList<String> lista = new ArrayList<>();


        if (title.equals("Είδος Εισαγωγής"))
            lista = InfoSpecificLists.getEidosEisagogisLista();
        else if (title.equals("Τρόπος μεταφοράς"))
            lista = InfoSpecificLists.getTroposMetaforasLista();
        else if (title.equals("Τις πληροφορίες δίδει"))
            lista = InfoSpecificLists.getTisPliroforiesDideiLista();

        else if (title.equals("Ομιλία"))
            lista = InfoSpecificLists.getomiliaLista();
        else if (title.equals("Ακοή"))
            lista = InfoSpecificLists.getAkoiLista();
        else if (title.equals("Ακουστικά"))
            lista = InfoSpecificLists.getAkoustikaLista();
        else if (title.equals("Όραση"))
            lista = InfoSpecificLists.getOrasiLista();
        else if (title.equals("Δέρμα"))
            lista = InfoSpecificLists.getDermaLista();
        else if (title.equals("Μέτρα λοιμώξεων (είδος)"))
            lista = InfoSpecificLists.getMetraLoimokseisLista();
        else if (title.equals("Αναπνοή"))
            lista = InfoSpecificLists.getAnapnoiLista();
        else if (title.equals("Οδοντοστοιχία"))
            lista = InfoSpecificLists.getOdontostoixiaLista();
        else if (title.equals("Βήχας"))
            lista = InfoSpecificLists.getVixasLista();
        else if (title.equals("Καρδιακός ρυθμός"))
            lista = InfoSpecificLists.getKardiakosRithmosLista();
        else if (title.equals("Όρεξη"))
            lista = InfoSpecificLists.getOreksiLista();
        else if (title.equals("Παχύ έντερο"))
            lista = InfoSpecificLists.getPaxiEnteroLista();
        else if (title.equals("Ουροκαθετήρας"))
            lista = InfoSpecificLists.getOurokathetirasLista();
        else if (title.equals("Επικοινωνία"))
            lista = InfoSpecificLists.getEpikoinoniaLista();





        adapter = new ArrayAdapter<>(act, R.layout.spinner_layout2, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valueSP.setAdapter(adapter);
        valueSP.setSelection(Integer.parseInt(id));


    }








}

