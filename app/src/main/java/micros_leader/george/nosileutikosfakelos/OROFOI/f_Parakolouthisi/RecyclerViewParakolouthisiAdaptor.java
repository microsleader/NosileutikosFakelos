package micros_leader.george.nosileutikosfakelos.OROFOI.f_Parakolouthisi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.ClassItemsForRV;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Eksitirio.RecyclerViewEksitirioAdaptor;
import micros_leader.george.nosileutikosfakelos.InputFilterMinMax;
import micros_leader.george.nosileutikosfakelos.R;

public class RecyclerViewParakolouthisiAdaptor extends RecyclerView.Adapter<RecyclerViewParakolouthisiAdaptor.MyViewHolder> implements SearchView.OnQueryTextListener {


    public ArrayList<ClassItemsForRV> result;
    private int  pos;
    private Context ctx;
    private Activity act;
    public int VIEW_TYPE_HEADER = 1;
    public int VIEW_TYPE_ITEM = 2;

    public RecyclerViewEksitirioAdaptor.MyViewHolder hold = null;



    // create constructor to innitilize context and data sent from MainActivity
    public RecyclerViewParakolouthisiAdaptor(Activity act, ArrayList<ClassItemsForRV> result) {

        this.act = act;
        this.result = result;



    }

    @Override
    public RecyclerViewParakolouthisiAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout


            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_textview_and_title_rec_adapter, parent, false);
             RecyclerViewParakolouthisiAdaptor.MyViewHolder vh = new RecyclerViewParakolouthisiAdaptor.MyViewHolder(v); // pass the view to View Holder
            return vh;


    }


    @Override
    public void onBindViewHolder(final RecyclerViewParakolouthisiAdaptor.MyViewHolder holder, final int position) {
        // set the data in items


        String title = result.get(position).getTitleID();
        String value = result.get(position).getValue();


        holder.itemView.setTag(title);
        holder.valueET.setTag(position);

        // ΤΑ POSITION ΠΟΥ ΟΡΙΖΟΝΤAI ΓΙΑ ΤΟ ΤΙ ΤΥΠΟ ΘΑ ΠΑΡΕΙ
        // ΤΟ ΚΑΘΕ EDITTEXT EΙΝΑΙ ΑΠΟ ΤΟ InfospecificLists.getParakolouthisiListaForRV();


        if (position == 9 || position == 12 ) { //ΓΙΑ ΝΑ ΜΠΑΙΝΕΙ ΜΟΝΟ ΣΤΙΣ ΘΕΣΕΙΣ ΠΟΥ ΘΕΛΩ Ο ΤΙΤΛΟΣ
            holder.mainTitleTV.setText(title);
            holder.titleTV.setVisibility(View.GONE);
            holder.valueET.setVisibility(View.GONE);
        }
        else {

            holder.mainTitleTV.setVisibility(View.GONE);
            holder.titleTV.setText(title);

        }

            holder.valueET.setText(value);

        // ΟΙ ΘΕΣΕΙΣ ΑΥΤΕΣ ΠΑΙΡΝΟΥΝ ΑΚΕΡΑΙΟ
        if ( position == 1 || position == 5 || position == 7 ) {

            holder.valueET.setInputType(InputType.TYPE_CLASS_NUMBER);
            if (position == 5) {
                holder.valueET.setFilters(new InputFilter[]{new InputFilterMinMax(0, 10)}); // ΣΕ ΑΥΤΗ ΤΗ ΘΕΣΗ ΒΑΖΟΥΜΕ ΟΡΙΟ 0-10 ΤΙΜΕΣ
            }
        }
        //ΟΙ ΘΕΣΕΙΣ ΑΥΤΕΣ ΠΑΙΡΝΟΥΝ ΔΕΚΑΔΙΚΟ
        else if (position == 0 || position == 2 || position == 3 || position == 4 || position == 8)
            holder.valueET.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);


        // ΠΑΙΡΝΕΙ ALERT DIALOG LISTENER ΕΠΙΛΟΓΗΣ ΤΙΜΩΝ
        else if (position == 18){
            holder.valueET.setFocusable(false);
         //   Log.e("title",title);
            holder.valueET.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    edittextListener(holder.valueET);
                }
            });
        }




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pos = position;



            }
        });




    }

    private void edittextListener(final EditText valueET){
        final String[] values = {"'Κενό'", "Φυσιολογική", "Διαρροϊκή", "Μέλαινα"};

        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle("Διαλέχτε τιμή");
        builder.setItems(values, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    valueET.setText("");
                }
                else
                    valueET.setText(values[which]);
            }
        });
        builder.show();
    }




//    @Override
//    public int getItemViewType(int position) {
//        return isHeader(position) ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
//    }


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

    public void filterList(ArrayList<ClassItemsForRV> filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }






    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTV, mainTitleTV;
        public EditText valueET;



        public MyViewHolder(View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.titleTV);
            mainTitleTV = itemView.findViewById(R.id.mainTitleTV);
            valueET = itemView.findViewById(R.id.valueET);


            // ΜΕ ΚΑΘΕ ΑΛΛΑΓΗ ΣΤΑ EDITEXT ΚΑΝΕΙΣ ΑΥΤΟΜΑΤΑΝΑ ΕΝΗΜΕΡΩΣΗ ΣΤΗ ΛΙΣΤΑ

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
                    result.get(position).setValue(valueET.getText().toString());



                }
            });







        }




    }













}


