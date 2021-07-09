package micros_leader.george.nosileutikosfakelos.OROFOI.f_Logodosia;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

public class RecyclerViewLogodosiesAdaptor extends RecyclerView.Adapter<RecyclerViewLogodosiesAdaptor.MyViewHolder> implements SearchView.OnQueryTextListener {


    private ArrayList<Logodosies> result;
    private Activity act;
    private String logodosia_text, id_logodosias;
    private int  pos;
    private Context ctx;


    // create constructor to innitilize context and data sent from MainActivity
    public RecyclerViewLogodosiesAdaptor(Activity act, ArrayList<Logodosies> result) {

        this.act = act;
        this.result = result;



    }

    @Override
    public RecyclerViewLogodosiesAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_logodosies__adapter_layout, parent, false);
        RecyclerViewLogodosiesAdaptor.MyViewHolder vh = new RecyclerViewLogodosiesAdaptor.MyViewHolder(v); // pass the view to View Holder

        return vh;
    }


    @Override
    public void onBindViewHolder(final RecyclerViewLogodosiesAdaptor.MyViewHolder holder, final int position) {
        // set the data in items

        String name = result.get(position).getName();
        final String userID = result.get(position).getUserID();
        final String id = result.get(position).getId();
        final String transgroupID = result.get(position).getTransgroupid();
        String date = result.get(position).getDate();
        String time = result.get(position).getTime();
        final String logodosia = result.get(position).getLogodosia();

        holder.nameTV.setText(name);
        holder.dateTV.setText(date + " , " + time );
        holder.textTV.setText(logodosia);


                holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userID.equals(Utils.getUserID(act))){
                    pos = position;
                    id_logodosias = result.get(position).getId();
                    logodosia_text =  result.get(position).getLogodosia();
                    ctx = holder.itemView.getContext();
                    showAlertDialog(id,position);
                }
                else
                    Toast.makeText(holder.itemView.getContext(), "Δεν είναι καταχωρημένο από εσάς", Toast.LENGTH_SHORT).show();


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

    public void filterList(ArrayList<Logodosies> filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, dateTV, textTV;


        public MyViewHolder(View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.namesTV);
            dateTV = itemView.findViewById(R.id.dateTV);
            textTV = itemView.findViewById(R.id.textTV);


        }
    }


    private void showAlertDialog(final String id, final int position) {

        new FancyAlertDialog.Builder(act)
                .setTitle("Επιλογές")
                .setBackgroundColor(Color.parseColor("#ecae10"))  //Don't pass R.color.colorvalue
                .setMessage("Διαγραφή ή Ενημέρωση κειμένου ")
                .setPositiveBtnBackground(Color.parseColor("#ecae10"))  //Don't pass R.color.colorvalue

                .setPositiveBtnText("Ενημέρωση")
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        updateShowdialog();
                    }
                })

                .setNegativeBtnText("Διαγραφή")
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                        delete(id);
                    }
                })
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.error_icon, Icon.Visible)
                .build();
    }


    private void updateShowdialog() {

        showInputAlertDialog();



    }




    private void showInputAlertDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("Ενημέρωση κειμένου");

        final EditText input = new EditText(ctx);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        input.setSingleLine(false);  //add this
        input.setLines(4);
        input.setMaxLines(5);
        input.setGravity(Gravity.LEFT | Gravity.TOP);
        input.setHorizontalScrollBarEnabled(false); 
        input.setText(logodosia_text);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String  text = input.getText().toString();


                update(text);
            }
        });
        builder.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

    
                dialog.cancel();
            }
        });



        builder.show();
    }


    private void update(final String text){

        String query = new Str_queries().getLOGODSIA_UPDATE(id_logodosias,text);
        AsyncTaskUpdate task = new AsyncTaskUpdate(act, query);
        task.listener = new AsyncGetUpdateResult() {
            @Override
            public void update(String str) {
                //TO APOTELESMA TO UPDATE ERXETAI EDW
                // STO FRAGMENT ME AYTON TON TROPO GINETAI DILONETAI

                if (str.equals("Πετυχημένη ενημέρωση")) {

                    result.get(pos).setLogodosia(text);
                    notifyDataSetChanged();
                    Toast.makeText(act, "Πετυχημένη ενημέρωση", Toast.LENGTH_SHORT).show();

                }

                else
                    Toast.makeText(act, "Κάτι πήγε στραβά", Toast.LENGTH_SHORT).show();
            }
        };

        task.execute();
    }


        private void delete(String id) {

            String query = new Str_queries().getLOGODOSIES_DELETE(id);
            AsyncTaskUpdate task = new AsyncTaskUpdate(act, query);
            task.listener = new AsyncGetUpdateResult() {
                @Override
                public void update(String str) {
                    //TO APOTELESMA TO UPDATE ERXETAI EDW
                    // STO FRAGMENT ME AYTON TON TROPO GINETAI DILONETAI

                    if (str.equals("Πετυχημένη ενημέρωση")) {
                        Toast.makeText(act, "Διαγραφή επιτυχής", Toast.LENGTH_SHORT).show();
                        //KANEI UPDATE STI LISTA KAI EPEITA ENIMEROSI STON ADAPTER
                        result.remove(pos);
                        notifyDataSetChanged();



                }

                    else
                        Toast.makeText(act, "Κάτι πήγε στραβά", Toast.LENGTH_SHORT).show();
                }
            };

            task.execute();
        }




}

