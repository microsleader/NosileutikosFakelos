package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_logodosia;

import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

public class Nosil_logodosia_Adapter  extends RecyclerView.Adapter <Nosil_logodosia_Adapter.MyViewHolder> implements SearchView.OnQueryTextListener {

    public ArrayList<Nosil_Logodosies> result;
    private Nosil_Anafora_Logodosia_Activity activity;

    public Nosil_logodosia_Adapter(Nosil_Anafora_Logodosia_Activity activity, ArrayList<Nosil_Logodosies> result){
        this.activity = activity;
        this.result = result;

    }

    @Override
    public Nosil_logodosia_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_nosil_logodosies_layout, parent, false);
        Nosil_logodosia_Adapter.MyViewHolder vh = new Nosil_logodosia_Adapter.MyViewHolder(v); // pass the view to View Holder



        return vh;
    }


    @Override
    public void onBindViewHolder(final Nosil_logodosia_Adapter.MyViewHolder holder, final int position) {
        // set the data in items

        final String date = result.get(position).getDate();
        final String nurse = result.get(position).getUserName();
        final String id = result.get(position).getId();
        final String nurseID = result.get(position).getUserID();
        final String tilemetria = result.get(position).getTilemetria();
        final String kard_rithmos = result.get(position).getKard_rithmos();
        final String paralamvanetai = result.get(position).getParalamvanetai();
        final String kinitopoiisi = result.get(position).getKinitopoiisi();
        final String alles_paremvaseis = result.get(position).getAlles_paremvaseis();
        final String logodosia = result.get(position).getLogodosia();
        String orario = result.get(position).getOrario();
        final String finalOrario = orario.equals("") ? "0" : orario;



        holder.dateTV.setText(Utils.utc_to_localtime(date));
        holder.nurseTV.setText(nurse);
        holder.orarioTV.setText(InfoSpecificLists.getNosileutiki_logodosia_orario(orario));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity.id = id;
                activity.nurseID = nurseID;
                String parts[] = date.split(" ");
                String partDate = parts[0];
                activity.date = partDate;
                activity.tilemetriaCH.setChecked(tilemetria.equals("1"));
                activity.checkTilemetria();
                activity.kard_rithmosET.setText(kard_rithmos);
                activity.paralamvanetaiET.setText(paralamvanetai);
                activity.kinitopoiisiET.setText(kinitopoiisi);
                activity.alles_paremvaseisET.setText(alles_paremvaseis);
                activity.setSpinnerPositionUsingID(Integer.parseInt(finalOrario));
                activity.logodosiaET.setText(logodosia);

                activity.weHaveData = true;

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (nurseID.equals(Utils.getUserID(activity)))
                    getAlertDialogInfo(id);
                else
                    Toast.makeText(activity, "Δεν μπορείτε να διαγράψετε μέτρηση άλλου νοσηλευτή", Toast.LENGTH_SHORT).show();
                return false;
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

    public void filterList(ArrayList<Nosil_Logodosies> filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  dateTV, nurseTV, orarioTV;


        public MyViewHolder(View itemView) {
            super(itemView);

            dateTV = itemView.findViewById(R.id.dateTV);
            nurseTV = itemView.findViewById(R.id.nurseTV);
            orarioTV = itemView.findViewById(R.id.orarioTV);


        }
    }


    public  void  getAlertDialogInfo (final String id){

        new FancyAlertDialog.Builder(activity)
                .setTitle("Διαγραφή μέτρησης")
                .setBackgroundColor(Color.parseColor("#2288c1"))  //Don't pass R.color.colorvalue
                .setMessage("Ακύρωση ή διαγραφή")
                .setPositiveBtnBackground(Color.parseColor("#2288c1"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("Διαγραφή")
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        delete(id);
                    }
                })
                .setNegativeBtnText("Ακύρωση")
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                    }
                })
                .setAnimation(Animation.POP)
                .isCancellable(false)
                .setIcon(R.drawable.error_icon, Icon.Visible)
                .build();
    }


    private void delete(String id) {


        activity.deleteData("Nursing_Nosileutiki_logodosia", id);
    }




}
