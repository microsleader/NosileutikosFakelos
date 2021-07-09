package micros_leader.george.nosileutikosfakelos.Notifications;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Utils;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.MyViewHolder> implements SearchView.OnQueryTextListener {


    private Activity act;

    public ArrayList<DF_Notifications.Notifications_objects> result = null;
    public MyDialogFragmentCloseListener listener = null;
    private int typeOfNotification;




    public NotificationsAdapter(Activity act, ArrayList result, int typeOfNotification) {
        this.act = act;
        this.result = result;
        this.typeOfNotification = typeOfNotification;
    }



    @Override
    public NotificationsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rv_notifications, parent, false);
        return new NotificationsAdapter.MyViewHolder(v); // pass the view to View Holder;
    }


    @Override
    public void onBindViewHolder(final NotificationsAdapter.MyViewHolder holder, final int position) {
        // set the data in items
        final DF_Notifications.Notifications_objects msges = result.get(position);
        long id = msges.id;
        String msg = msges.message;
        String username = msges.username;
        String doctor = msges.doctor;
        long doctorID = msges.doctorID;
        String date = msges.date;
        String patient = msges.patient;
        String confirmDate = msges.confirmDate;
        boolean isConfirmed =  msges.isConfirmed;

        if (typeOfNotification == DF_Notifications.MEDICAL_INS_DOCTOR){
            holder.confLayout.setVisibility(View.INVISIBLE);

            holder.msgTV.setText(doctor);
            holder.msgTV.append("\n");
            holder.msgTV.append(date);
            holder.msgTV.append("\n\n");
            holder.msgTV.append(Html.fromHtml(Utils.getColorTextRed(msg)));


        }
        else {

            holder.msgTV.setText(Html.fromHtml(Utils.getColorTextRed(patient)));
            holder.msgTV.append("\n\n" + msg);


            if (confirmDate != null)
                holder.confirmDateTV.setText("Ημ/νία επιβεβαίωσης : \n" + confirmDate);

            holder.confirmedCH.setChecked(isConfirmed);
            if (isConfirmed) {
                holder.confirmedUserTV.setText("Χρήστης επιβεβαίωσης: " + username);
                holder.confirmedCH.setEnabled(false);
                holder.confirmedCH.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#f31a1a")));
            }
            else {
                holder.confirmedCH.setEnabled(true);
                holder.confirmedCH.setButtonTintList(null);

            }
            holder.confirmedCH.setTag(id);

        }



    }


    @Override
    public int getItemCount() {
        return result.size();
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void filterList(ArrayList<DF_Notifications.Notifications_objects > filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView msgTV,confirmDateTV,confirmedUserTV;
        CheckBox confirmedCH;
        LinearLayout confLayout ;


        public MyViewHolder(View itemView) {
            super(itemView);

            msgTV = itemView.findViewById(R.id.msgTV);
            confirmDateTV = itemView.findViewById(R.id.confirmDateTV);
            confirmedUserTV = itemView.findViewById(R.id.confirmedUserTV);
            confLayout = itemView.findViewById(R.id.confirmationLayout);
            confirmedCH = itemView.findViewById(R.id.confirmedCH);
            confirmedCH.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            if (view.getId() == confirmedCH.getId()){
                if (confirmedCH.isChecked())
                     setConfirmed(confirmedCH);

            }


        }
    }





    public void setConfirmed(CheckBox checkBox){
        long id = (long) checkBox.getTag();
        String query = "exec dbo.disable_triggers" +
                " update " +
                (typeOfNotification  == DF_Notifications.NOTIFICATIONS_TO_CONFIRM ? " notification_messages " : " Nursing_iatrikes_entoles ") +
                " set confirmation = 1, confirmation_date = dbo.localtime()," +
                (typeOfNotification  == DF_Notifications.NOTIFICATIONS_TO_CONFIRM ? " userID = " : " Conifrmed_userID = ") + Utils.getUserID(act) +
                " where id = " + id  +
                " exec dbo.enable_triggers";


        AsyncTaskUpdate task = new AsyncTaskUpdate(act,query);
        task.listener = str -> {
           // alertDialog.dismiss();
            Toast.makeText(act, str, Toast.LENGTH_SHORT).show();
            if (str.equals("Πετυχημένη ενημέρωση")){
                checkBox.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#f31a1a")));

                checkBox.setEnabled(false);

            }
        };
        task.execute();
    }

}
