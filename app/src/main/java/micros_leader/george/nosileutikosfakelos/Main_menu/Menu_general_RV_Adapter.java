package micros_leader.george.nosileutikosfakelos.Main_menu;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Menu_general_RV_Adapter extends RecyclerView.Adapter<Menu_general_RV_Adapter.MyViewHolder> {

    public ArrayList<Menu_general_Item> result;
    public Activity act;
    public DataSended dataSendedListener;

    public Menu_general_RV_Adapter(ArrayList<Menu_general_Item> result, Activity act) {
        this.result = result;
        this.act = act;
        dataSendedListener = (DataSended) act;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Menu_general_RV_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_general_menu_item_layout, parent, false);
        return  new MyViewHolder(v,dataSendedListener);

    }


    @Override
    public void onBindViewHolder(@NonNull Menu_general_RV_Adapter.MyViewHolder holder, int i) {

        Menu_general_Item item = result.get(i);
        String title = item.getTitle();
     //   int image = item.getImageID();
        int color = item.getBackgroundColor();
       // Activity activity = item.getActivity();

        holder.layout.setBackgroundColor( act.getResources().getColor(color));
        holder.layout.setTag(i);
        holder.titleID.setText(title);
        holder.itemView.setTag(i);


    }


    @Override
    public int getItemCount() {
        return result.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        DataSended dataSendedListener;
        TextView titleID;
        LinearLayout layout;

        public MyViewHolder(final View itemView,DataSended dataSendedListener ) {
            super(itemView);

            this.dataSendedListener = dataSendedListener;

            layout = itemView.findViewById(R.id.linearLayoutID);
            titleID = itemView.findViewById(R.id.titleTV);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            int pos = (int) itemView.getTag();
            Activity activity = result.get(pos).getActivity();
           // dataSendedListener.hereIsYourData(getAdapterPosition());

            Intent intent = new Intent(act,activity.getClass());
            intent.putExtra("companyID", Utils.getcompanyID(act));
            //act.startActivity(intent);
            act.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(act).toBundle());
         //   Toast.makeText(act, activity.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();


        }
    }

}
