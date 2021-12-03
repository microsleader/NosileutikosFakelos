package micros_leader.george.nosileutikosfakelos.Main_menu;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Isozigio_Meth.Isozigio_Meth_Activity;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.Kathetires_kalliergies_Meth_Activity;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Zotika_simeia.Zotika_Activity_Meth;
import micros_leader.george.nosileutikosfakelos.MainFragment;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Aksiologiseis.NeurikiAksiologisi3Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Kathimerino_zigisma.Kathimerino_Zigisma_Activity;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Menu_general_RV_Adapter extends RecyclerView.Adapter<Menu_general_RV_Adapter.MyViewHolder> {

    public ArrayList<Menu_general_Item> result;
    public Activity act;
    public DataSended dataSendedListener;
    private final boolean isGeneralMenu;
    private FrameLayout actFr;
    private SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS filladio_sigxoneusis;

    public Menu_general_RV_Adapter(ArrayList<Menu_general_Item> result, Activity act,boolean isGeneralMenu) {
        this.result = result;
        this.act = act;
        dataSendedListener = (DataSended) act;
        this.isGeneralMenu = isGeneralMenu;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @NonNull
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

    public void addFrameLayout(FrameLayout actFR) {
        this.actFr = actFR;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        DataSended dataSendedListener;
        TextView titleID;
        LinearLayout layout;

        public MyViewHolder(final View itemView,DataSended dataSendedListener ) {
            super(itemView);

            this.dataSendedListener = dataSendedListener;

            layout = itemView.findViewById(R.id.linearLayoutID);
            if (!isGeneralMenu){
                ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();
                layoutParams.width = 180;
                layoutParams.height = 120;
                layout.setLayoutParams(layoutParams);
            }
            titleID = itemView.findViewById(R.id.titleTV);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            int pos = (int) itemView.getTag();
            Activity activity = result.get(pos).getActivity();

            if (isGeneralMenu) {
                Intent intent = new Intent(act, activity.getClass());
                intent.putExtra("companyID", Utils.getcompanyID(act));
                act.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(act).toBundle());
            }
            else{

                actFr.removeAllViews();
                actFr.clearChildFocus(actFr);
                MainFragment myf = new MainFragment();
                Bundle bundle = new Bundle();



                if (activity instanceof Zotika_Activity_Meth)
                    filladio_sigxoneusis = SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS.ZOTIKA_METH;
                else if (activity instanceof Kathimerino_Zigisma_Activity)
                    filladio_sigxoneusis = SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS.KATHIMERINO_ZIGISMA;
                else if (activity instanceof Isozigio_Meth_Activity)
                    filladio_sigxoneusis = SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS.ISOZIGIO_METH;
                else if (activity instanceof NeurikiAksiologisi3Activity)
                    filladio_sigxoneusis = SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS.NEURIKI_AKSIOLOGISI;
                else if (activity instanceof Kathetires_kalliergies_Meth_Activity)
                    filladio_sigxoneusis = SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS.KATHETIRES_KALLIERGIES;

                bundle.putSerializable(SigxoneusiFiladiwnActivity.FILLADIO_KEY, filladio_sigxoneusis);
                myf.setArguments(bundle);
                ((FragmentActivity)act).getSupportFragmentManager().beginTransaction().replace(actFr.getId(), myf).commit();

               // act.getSupportFragmentManager().beginTransaction().add(actFr.getId(), myf).commit();
            }

        }
    }

}
