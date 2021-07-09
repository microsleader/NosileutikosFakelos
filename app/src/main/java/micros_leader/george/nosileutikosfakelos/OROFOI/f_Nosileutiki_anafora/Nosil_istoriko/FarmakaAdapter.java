package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_istoriko;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class FarmakaAdapter extends RecyclerView.Adapter<FarmakaAdapter.MyViewHolder> {

    private String id, eidostext, dositext, paratiriseis;
    private Activity act;
    private ArrayList<Farmaka> result ;
    private int pos;


    // create constructor to innitilize context and data sent from MainActivity
    public FarmakaAdapter( Activity act, ArrayList<Farmaka> result ){

        this.act = act;
        this.result = result;



    }

    @Override
    public FarmakaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_farmaka_recycler_layout, parent, false);
        FarmakaAdapter.MyViewHolder vh = new FarmakaAdapter.MyViewHolder(v); // pass the view to View Holder

        return vh;
    }



    @Override
    public void onBindViewHolder(final FarmakaAdapter.MyViewHolder holder, final int position) {
        // set the data in items

        String lastLipsi = result.get(position).getTeleutaia_lipsi();
        lastLipsi = lastLipsi.substring(0,lastLipsi.length() - 7);  // ΓΙΑ ΝΑ ΜΗΝ ΦΑΙΝΟΝΤΑΙ ΤΑ ΔΕΥΤΕΡΟΛΕΠΤΑ

        holder.eidosTV.setText(result.get(position).getEidos());
        holder.dosiTV.setText(result.get(position).getDosi());
        holder.teleutaia_lipsiTV.setText(lastLipsi);
        holder.paratiriseisTV.setText(result.get(position).getParatiriseis());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pos = position;
                id = result.get(position).getID();
                eidostext = result.get(position).getEidos();
                dositext = result.get(position).getDosi();
                paratiriseis = result.get(position).getParatiriseis();
                showAlertDialog();

            }
        });

    }


    private void showAlertDialog(){
        new FancyAlertDialog.Builder(act)
                .setTitle("Επιλογές")
                .setBackgroundColor(Color.parseColor("#ecae10"))  //Don't pass R.color.colorvalue
                .setMessage("Διαγραφή ή Ενημέρωση κειμένου ")
                .setPositiveBtnBackground(Color.parseColor("#ecae10"))  //Don't pass R.color.colorvalue

                .setPositiveBtnText("Ενημέρωση")
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                        showInputAlertDialog();
                    }
                })

                .setNegativeBtnText("Διαγραφή")
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                        delete();
                    }
                })
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.error_icon, Icon.Visible)
                .build();

    }






    private void showInputAlertDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle("Ενημέρωση κειμένου");

        LinearLayout layout = new LinearLayout(act);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText inputEidos = new EditText(act);
        inputEidos.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        inputEidos.setSingleLine(false);  //add this
        inputEidos.setLines(4);
        inputEidos.setMaxLines(5);
        inputEidos.setGravity(Gravity.LEFT | Gravity.TOP);
        inputEidos.setHorizontalScrollBarEnabled(false);
        inputEidos.setText(eidostext);
        layout.addView(inputEidos);


        final EditText inputDosi = new EditText(act);
        inputDosi.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        inputDosi.setSingleLine(false);  //add this
        inputDosi.setLines(4);
        inputDosi.setMaxLines(5);
        inputDosi.setGravity(Gravity.LEFT | Gravity.TOP);
        inputDosi.setHorizontalScrollBarEnabled(false);
        inputDosi.setText(dositext);
        layout.addView(inputDosi);


        final EditText parat = new EditText(act);
        parat.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        parat.setSingleLine(false);  //add this
        parat.setLines(4);
        parat.setMaxLines(5);
        parat.setGravity(Gravity.LEFT | Gravity.TOP);
        parat.setHorizontalScrollBarEnabled(false);
        parat.setText(paratiriseis);
        layout.addView(parat);



        builder.setView(layout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String  eidos = Utils.checkNull(inputEidos.getText().toString().trim());
                String  dosi = Utils.checkNull(inputDosi.getText().toString().trim());
                String  paratiriseis = Utils.checkNull(parat.getText().toString().trim());

                update(eidos, dosi, paratiriseis);
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



    private void update(final String eidos, final String dosi , final String paratiriseis){

        String query = new Str_queries().getFARMAKA_POU_XRISIMOPOIOUNTAI_UPDATE(id,eidos,dosi, paratiriseis) ;
        AsyncTaskUpdate task = new AsyncTaskUpdate(act, query);
        task.listener = new AsyncGetUpdateResult() {
            @Override
            public void update(String str) {
                //TO APOTELESMA TO UPDATE ERXETAI EDW
                // STO FRAGMENT ME AYTON TON TROPO GINETAI DILONETAI

                if (str.equals("Πετυχημένη ενημέρωση")) {

                    result.get(pos).setEidos(eidos);
                    result.get(pos).setDosi(dosi);
                    result.get(pos).setParatiriseis(paratiriseis);

                    notifyDataSetChanged();
                    Toast.makeText(act, "Πετυχημένη ενημέρωση", Toast.LENGTH_SHORT).show();

                }

                else
                    Toast.makeText(act, "Κάτι πήγε στραβά", Toast.LENGTH_SHORT).show();
            }
        };

        task.execute();
    }







    private void delete(){
        String query = new Str_queries().getFARMAKA_POU_XRISIMOPOIOUNTAI_DELETE(id);
        AsyncTaskUpdate task = new AsyncTaskUpdate(act, query);
        task.listener = new AsyncGetUpdateResult() {
            @Override
            public void update(String str) {


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





    @Override
    public int getItemCount() {
        return result.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView eidosTV, dosiTV, teleutaia_lipsiTV,  paratiriseisTV;


        public MyViewHolder(View itemView) {
            super(itemView);

            eidosTV = itemView.findViewById(R.id.eidosTV);
            dosiTV = itemView.findViewById(R.id.dosiTV);
            teleutaia_lipsiTV = itemView.findViewById(R.id.lastdateTV);
            paratiriseisTV = itemView.findViewById(R.id.paratiriseisTV);




        }
    }
}
