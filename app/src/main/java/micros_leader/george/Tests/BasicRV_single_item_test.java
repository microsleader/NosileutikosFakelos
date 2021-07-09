package micros_leader.george.Tests;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended;
import micros_leader.george.nosileutikosfakelos.R;

public class BasicRV_single_item_test extends RecyclerView.Adapter<BasicRV_single_item_test.MyViewHolder>  {


    public ArrayList<ItemsRV> result;
    public  ArrayList<String> oldValuesLista;
    public Activity act;
    private int [] titloi_positions;
    public DataSended dataSendedListener;


    private ArrayList<String> lista =  new ArrayList<>();



    private ArrayList <Integer> titloi_position_lista =  new ArrayList<>();


    private boolean isIncludingOldValues = false;



    BasicRV_single_item_test(Activity act, ArrayList<ItemsRV> result, @NotNull int[] titloi_positions) {


        this.act = act;
        this.result = result;
        this.titloi_positions = titloi_positions;

        for (int i=0; i<titloi_positions.length; i++){
            titloi_position_lista.add(titloi_positions[i]);
        }

    }

    public ArrayList<ItemsRV> getItems(){
        return result;
    }

    public void setItems(ArrayList<ItemsRV> lista){
        result = lista;
    }



    @Override
    public int getItemViewType(int position) {

        return result.get(position).getType();

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NotNull
    @Override
    public BasicRV_single_item_test.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = null;


        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_edittext_rec_adapter, parent, false);
        BasicRV_single_item_test.MyViewHolder vh = new BasicRV_single_item_test.MyViewHolder(v,viewType,dataSendedListener); // pass the view to View Holder
   //     vh.getAdapterPosition();


        return vh;
    }




    @Override
    public void onBindViewHolder(final BasicRV_single_item_test.MyViewHolder holder, final int position) {
        // set the data in items

        holder.titleTV.setText(result.get(position).getTitleID());

    }


    @Override
    public int getItemCount() {

        return result.size();
    }








    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView oldValueTV;
        public TextView valueTV;
        public EditText valueET;
        public TextView titleTV;
        public CheckBox oldValueCH,valueCH;
        public Spinner valueSP;
        public DataSended dataSendedListener;





        public MyViewHolder(final View itemView, int viewType, DataSended dataSendedListener) {
            super(itemView);
            titleTV =  itemView.findViewById(R.id.titleTV);





        }

//        @Override
//        public void onClick(View v) {
//
//            dataSendedListener.hereIsYourData(getAdapterPosition());
//
//
//        }




    }





}


