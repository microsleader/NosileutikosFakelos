package micros_leader.george.nosileutikosfakelos.RV_version2_mixanismos_UNCOMPLETED;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.R;

public class BasicRV_version2 extends RecyclerView.Adapter<BasicRV_version2.MyViewHolder> {

    //TEXTVIEWS
    public final static int TV_KATIGORIA_TITLE= 0;
    public final static int TV_TITLE= 1;
    public final static int TV_DATE = 2;
    public final static int TV_TIME = 3;

    //EDITTEXT
    public final static int EDITTEXT_KEIMENO = 11;
    public final static int EDITTEXT_AKERAIOS = 12;
    public final static int EDITTEXT_DEKADIKOS = 13;

    public ArrayList<RV_version2> result;
    private Activity act;

    public  BasicRV_version2(ArrayList<RV_version2> result, Activity act){
        this.result = result;
        this.act = act;

    }

    @Override
    public int getItemViewType(int position) {

        int type = result.get(position).getTypeChild();


        return type;

    }



    @Override
    public BasicRV_version2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = null;
        switch (viewType) {



            case TV_KATIGORIA_TITLE: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_titles_rec_adapter, parent, false);
                break;
            }

            case TV_TITLE: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_textview_rec_adapter, parent, false);
                break;
            }
            case EDITTEXT_AKERAIOS: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_edittext_rec_adapter, parent, false);
                break;
            }
            case EDITTEXT_DEKADIKOS: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_edittext_rec_adapter, parent, false);
                break;
            }
            case EDITTEXT_KEIMENO: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_edittext_rec_adapter, parent, false);
                break;
            }

        }

        BasicRV_version2.MyViewHolder vh = new BasicRV_version2.MyViewHolder(v, viewType); // pass the view to View Holder
        //     vh.getAdapterPosition();


        return vh;
    }




    @Override
    public void onBindViewHolder( BasicRV_version2.MyViewHolder holder, int position) {

        RV_version2 itemRV = result.get(position);

        String title = itemRV.getTitle();

        if (getItemViewType(position) == TV_TITLE){
            holder.itemView.getRootView();
            holder.valueTV = itemRV.getTextView();
            holder.valueTV.setText("xaxaxaxa");


        }

        else if (getItemViewType(position) == EDITTEXT_KEIMENO){
            holder.valueET = itemRV.getEditText();
            holder.valueET.setText("keimeno");


        }


        else if (getItemViewType(position) == EDITTEXT_AKERAIOS){
            holder.valueET = itemRV.getEditText();
            holder.valueET.setText("akeraios");


        }



    }

    @Override
    public int getItemCount() {
        return result.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView valueTV;
        public EditText valueET;
        TextView titleTV;
        public CheckBox  valueCH;
        public Spinner valueSP;


        public MyViewHolder(final View itemView, int viewType) {
            super(itemView);


            if (viewType == TV_KATIGORIA_TITLE)
                titleTV = itemView.findViewById(R.id.titleTV);

            else if (viewType == TV_TITLE){
                valueTV = result.get(viewType).getTextView();
                valueTV = itemView.findViewById(R.id.valueTV);

            }

            else if (viewType == EDITTEXT_KEIMENO) {
                valueET = itemView.findViewById(R.id.valueET);

                valueET = result.get(getAdapterPosition()).getEditText();

                valueET.setText("keimeno");
            }

            else if (viewType == EDITTEXT_DEKADIKOS) {
                valueET = result.get(getAdapterPosition()).getEditText();

                valueET = itemView.findViewById(R.id.valueET);
                valueET.setText("dek");

            }

            else if (viewType == EDITTEXT_AKERAIOS) {
                valueET = result.get(getAdapterPosition()).getEditText();

                valueET = itemView.findViewById(R.id.valueET);
                valueET.setText("ak");

            }



        }


    }
}
