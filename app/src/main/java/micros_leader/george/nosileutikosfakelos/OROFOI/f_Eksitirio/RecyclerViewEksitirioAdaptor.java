package micros_leader.george.nosileutikosfakelos.OROFOI.f_Eksitirio;

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.ClassItemsForRV;

import micros_leader.george.nosileutikosfakelos.R;

public class RecyclerViewEksitirioAdaptor extends RecyclerView.Adapter<RecyclerViewEksitirioAdaptor.MyViewHolder> implements SearchView.OnQueryTextListener {


    public ArrayList<ClassItemsForRV> result;
    private int  pos;
    private Context ctx;
    private Activity act;

    public MyViewHolder hold = null;



    // create constructor to innitilize context and data sent from MainActivity
    public RecyclerViewEksitirioAdaptor(Activity act, ArrayList<ClassItemsForRV> result) {

        this.act = act;
        this.result = result;



    }

    @Override
    public RecyclerViewEksitirioAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_textview_rec_adapter, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder

        return vh;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // set the data in items


        String title = result.get(position).getTitleID();
        String value = result.get(position).getValue();


        holder.itemView.setTag(title);
        holder.valueTV.setTag(position);

        holder.titleTV.setText(title);
        holder.valueTV.setText(value);






        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pos = position;

            }
        });


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

    public void filterList(ArrayList<ClassItemsForRV> filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }

    public void setText(int position, String value){

        result.get(position).setValue(value);
        notifyDataSetChanged();

        Toast.makeText(act,result.get(position).getValue() , Toast.LENGTH_SHORT).show();



    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTV;
        public TextView valueTV;



        public MyViewHolder(View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.titleTV);
            valueTV = itemView.findViewById(R.id.valueTV);


            valueTV.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    int position = (int)valueTV.getTag();
                    result.get(position).setValue(valueTV.getText().toString());


                }
            });




        }




    }













}

